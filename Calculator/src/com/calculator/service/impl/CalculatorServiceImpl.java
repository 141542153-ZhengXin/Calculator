package com.calculator.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.calculator.domain.Condition;
import com.calculator.domain.Result;
import com.calculator.service.CalculatorService;

public class CalculatorServiceImpl implements CalculatorService {

	public Result[] alsoDuePrincipalAndInterest(Condition c) {
		Result[] tr = new Result[1];
		Result r = new Result();
		
		//����������
		BigDecimal dayRate = getDayRate(c.getRate(),c.getRateIsDay());
		
		//���㵽������
		Calendar endCalendar = getEndCalendar(c.getDate(), c.getLimit(), c.getLimitIsDay());
		
		//���������ת��Ϊ����
		int totalDay = getTotalDay(c.getDate(), endCalendar);
		
		//������Ϣ
		BigDecimal interst=c.getMoney().multiply(dayRate).multiply(new BigDecimal(totalDay));
		
		//����result
		r.setNum(1);
		r.setDate(new SimpleDateFormat("yyyy-MM-dd").format(endCalendar.getTime()));
		r.setInterest(interst);
		r.setPrincipal(c.getMoney());
		r.setTotal(interst.add(r.getPrincipal()));
		
		tr[0]=r;
		return tr;
	}
	
	@Override
	public Result[] monthInterestDueDebt(Condition c) {
		Result[] tr = new Result[c.getLimit()];
		
		//����������
		BigDecimal dayRate = getDayRate(c.getRate(),c.getRateIsDay());
		
		//����ÿ�ڽ��
		int i=0;
		Date startDate=c.getDate();
		while(i<c.getLimit()){
			Result r = new Result();
			
			//���㵽������
			Calendar endCalendar = getEndCalendar(startDate, 1, c.getLimitIsDay());
			
			//���������ת��Ϊ����
			int totalDay = getTotalDay(startDate, endCalendar);
			
			//������Ϣ
			BigDecimal interst=c.getMoney().multiply(dayRate).multiply(new BigDecimal(totalDay));
			
			//����result
			r.setNum(i+1);
			Date endDate=endCalendar.getTime();
			r.setDate(new SimpleDateFormat("yyyy-MM-dd").format(endDate));
			r.setInterest(interst);
			r.setPrincipal(c.getMoney());
			r.setTotal(i==c.getLimit()-1?interst.add(r.getPrincipal()):interst);
			
			tr[i]=r;
			startDate=endDate;
			i++;
		}
		
		return tr;
	}
	
	//���������ת��Ϊ����
	private int getTotalDay(Date startDate, Calendar endCalendar) {
		int totalDay;
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		totalDay = endCalendar.get(Calendar.DAY_OF_YEAR)-startCalendar.get(Calendar.DAY_OF_YEAR);
		return totalDay;
	}
	
	//����������
	private BigDecimal getDayRate(BigDecimal rate,int rateIsDay) {
		BigDecimal dayRate;
		if(rateIsDay==1){
			dayRate=rate;
		}else{
			dayRate=rate.divide(new BigDecimal(365),7, BigDecimal.ROUND_HALF_UP);
		}
		return dayRate;
	}
	
	//���㵽������
	private Calendar getEndCalendar(Date d,int limit,int limitIsDay){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		if(limitIsDay==1){
			//������ް�����
			calendar.add(Calendar.DAY_OF_YEAR, limit);
		}else{
			//������ް�����
			calendar.add(Calendar.MONTH, limit);
		}
		return calendar;
	}
}
