package com.calculator.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Condition {
	
	private Date date;			//借出日期
	private BigDecimal money;	//借出金额
	private int limit;			//借出期限
	private BigDecimal rate;	//利率
	private String method;			//还款方式
	
	private int limitIsDay;		//借出期限是否按天算,0是按月算，1按天算
	private int rateIsDay;		//是否是日利率，0是年利率，1是日利率
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public int getLimitIsDay() {
		return limitIsDay;
	}
	public void setLimitIsDay(int limitIsDay) {
		this.limitIsDay = limitIsDay;
	}
	public int getRateIsDay() {
		return rateIsDay;
	}
	public void setRateIsDay(int rateIsDay) {
		this.rateIsDay = rateIsDay;
	}
	
}
