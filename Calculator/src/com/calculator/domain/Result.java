package com.calculator.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Result {
	
	private int num;				//�ں�
	private BigDecimal total;		//����
	private BigDecimal principal;	//����
	private BigDecimal interest;	//��Ϣ
	private String date;			//��������
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getPrincipal() {
		return principal;
	}
	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}
	public BigDecimal getInterest() {
		return interest;
	}
	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
