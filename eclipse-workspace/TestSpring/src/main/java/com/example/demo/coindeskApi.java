package com.example.demo;

import java.io.IOException;
import java.net.http.HttpClient;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Join;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.entity.Todo;
import com.example.demo.entity.coinRepository;
import com.example.demo.model.coinResponse;


@Controller
public class coindeskApi {
	
	@Autowired
	coinRepository coinRepository;
	
	
	//呼叫coindesk的API。
	@ResponseBody
	@RequestMapping(value = "/callapi",method = RequestMethod.GET)
	public String callApi() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpPost = new HttpGet("https://api.coindesk.com/v1/bpi/currentprice.json");
		httpPost.addHeader("Content-Type","application/json");
		CloseableHttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String responseContent = EntityUtils.toString(entity,"UTF-8");
		response.close();
		httpClient.close();
		return responseContent;
		
	}
	
	//呼叫coindesk的API，並進行資料轉換，組成新API。
	@ResponseBody
	@RequestMapping(value = "/tranApi",method = RequestMethod.GET)
	public String tranApi() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpPost = new HttpGet("https://api.coindesk.com/v1/bpi/currentprice.json");
		httpPost.addHeader("Content-Type","application/json");
		CloseableHttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String responseContent = EntityUtils.toString(entity,"UTF-8");
		JSONObject jsonO = new JSONObject(new LinkedHashMap<>());
		coinResponse coinResponse = jsonO.parseObject(responseContent,coinResponse.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		coinResponse.getTime().setUpdated(sdf.format(System.currentTimeMillis()));
		
		List<Todo> todolist = coinRepository.findAll();
		for(Todo todo : todolist) {
			if(coinResponse.getBpi().containsKey(todo.getCurrency())) {
				coinResponse.getBpi().get(todo.getCurrency()).setSymbol(todo.getCurrencyTW());
				coinResponse.getBpi().get(todo.getCurrency()).setRate_float(todo.getRate());
			}

		}
		
		
		response.close();
		httpClient.close();
		return jsonO.toJSONString(coinResponse,SerializerFeature.SortField);
		
	}

	//幣別DB維護功能-查詢。
	@ResponseBody
	@RequestMapping(value = "/query",method = RequestMethod.GET)
	public String QueryCurrency() {
		String outString ="";
		List<Todo> allList = coinRepository.findByAll();
		
		for(Todo todo: allList) {
			outString="{"+todo.toString()+"}";
		}
		
		return outString;
	}
	//幣別DB維護功能-新增。
	@ResponseBody
	@RequestMapping(value = "/insert",method = RequestMethod.GET)
	public void InsertCurrency(@RequestParam("currency")String currency ,
							   @RequestParam("currencyTW")String currencyTW,
							   @RequestParam("rate")float rate
							   ) {
		Todo todo = new Todo();
		todo.setCurrency(currency);
		todo.setCurrencyTW(currencyTW);
		todo.setRate(rate);
		coinRepository.save(todo);
		
	}
	//幣別DB維護功能-更新。
	@ResponseBody
	@RequestMapping(value = "/update",method = RequestMethod.GET)
	public int UpdateCurrency(@RequestParam("currency")String currency ,
							   @RequestParam("rate")Double rate) {
		 return coinRepository.updateRate(rate, currency);
	}
	//幣別DB維護功能-刪除。
	@ResponseBody
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	public int DeleteCurrency(@RequestParam("currency")String currency) {
		 return coinRepository.deleteCurrency(currency);
		
	}
	

}
