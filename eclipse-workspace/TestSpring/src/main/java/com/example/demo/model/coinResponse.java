package com.example.demo.model;

import antlr.collections.List;

public class coinResponse {
	
	Time time;
	
	String disclaimer;
	
	String chartName;
	
	Bpi bpi;

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

	public Bpi getBpi() {
		return bpi;
	}

	public void setBpi(Bpi bpi) {
		this.bpi = bpi;
	}
	
	
	
}
