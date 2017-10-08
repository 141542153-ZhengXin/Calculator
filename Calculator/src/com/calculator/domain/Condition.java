package com.calculator.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Condition {
	
	private Date date;			//�������
	private BigDecimal money;	//������
	private int limit;			//�������
	private BigDecimal rate;	//����
	private String method;			//���ʽ
	
	private int limitIsDay;		//��������Ƿ�����,0�ǰ����㣬1������
	private int rateIsDay;		//�Ƿ��������ʣ�0�������ʣ�1��������
	
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
