package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column
	String currency;
	
	@Column
	String currencyTW;
	
	@Column
	float rate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrencyTW() {
		return currencyTW;
	}

	public void setCurrencyTW(String currencyTW) {
		this.currencyTW = currencyTW;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}
	
	public String toString() {
		return "Currency:"+currency+",CurrencyTW:"+currencyTW+",Rate:"+rate;
	}
	
	
}
