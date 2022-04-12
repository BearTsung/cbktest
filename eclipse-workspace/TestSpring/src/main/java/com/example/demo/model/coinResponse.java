package com.example.demo.model;

import java.util.HashMap;

public class coinResponse {
	
	Time time;
	
	String disclaimer;
	
	String chartName;
	
	HashMap<String,currencyContent> bpi;

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public HashMap<String, currencyContent> getBpi() {
		return bpi;
	}

	public void setBpi(HashMap<String, currencyContent> bpi) {
		this.bpi = bpi;
	}

	
}