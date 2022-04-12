package com.example.demo;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestSpringApplicationTests {

	@Autowired
	coindeskApi coindeskApi;
	
	//1.測試呼叫查詢幣別對應表資料API，並顯示其內容
	@Test
	void queryTest() {
		System.out.println("query測試開始");
		coindeskApi.InsertCurrency("GBP", "英鎊", (float)36.71);
		System.out.println(coindeskApi.QueryCurrency());
		System.out.println("query測試結束");
	}
	//2.測試呼叫新增幣別對應表資料API
	@Test
	void insertTest() {
		System.out.println("insert測試開始：\"USD\", \"美元\", 3.14");
		coindeskApi.InsertCurrency("USD", "美元", (float)3.14);
		System.out.println("insert測試結束");
	}
	//3.測試呼叫更新幣別對應表資料API，並顯示其內容
	@Test
	void updateTest() throws Exception{
		System.out.println("update測試開始");
		int i = 0;
		coindeskApi.InsertCurrency("AUD", "澳幣", (float)22.05);
		i =coindeskApi.UpdateCurrency("AUD", 3.256);
		System.out.println("更新數量："+i);
		System.out.println("update測試結束");
	}
	//4.測試呼叫刪除幣別對應表資料API。
	@Test
	void deleteTest() {
		System.out.println("delete測試開始");
		int i = 0;
		i =coindeskApi.DeleteCurrency("USD");
		System.out.println("刪除數量："+i);
		System.out.println("delete測試結束");
	}
	//5.測試呼叫coindesk API，並顯示其內容。
	@Test
	void callapi() throws ClientProtocolException, IOException {
		System.out.println("callApi測試開始");
		String reString =coindeskApi.callApi();
		System.out.println(reString);
		System.out.println("callApi測試結束");
	}
	//6.測試呼叫資料轉換的API，並顯示其內容。
	@Test
	void tranApi() throws ClientProtocolException, IOException {
		System.out.println("tranApi測試開始");
		coindeskApi.InsertCurrency("USD", "美元", (float)29.335);
		coindeskApi.InsertCurrency("GBP", "英鎊", (float)38.81);
		coindeskApi.InsertCurrency("EUR", "歐元", (float)32.16);
		
		System.out.println(coindeskApi.tranApi());
		System.out.println("tranApi測試結束");
	}
	
	
	
	
	
	
	
	
	

}
