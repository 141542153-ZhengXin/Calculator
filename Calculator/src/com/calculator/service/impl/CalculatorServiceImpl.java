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
		
		//计算日利率
		BigDecimal dayRate = getDayRate(c.getRate(),c.getRateIsDay());
		
		//计算到期日期
		Calendar endCalendar = getEndCalendar(c.getDate(), c.getLimit(), c.getLimitIsDay());
		
		//将借出期限转化为天数
		int totalDay = getTotalDay(c.getDate(), endCalendar);
		
		//计算利息
		BigDecimal interst=c.getMoney().multiply(dayRate).multiply(new BigDecimal(totalDay));
		
		//设置result
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
		
		//计算日利率
		BigDecimal dayRate = getDayRate(c.getRate(),c.getRateIsDay());
		
		//计算每期结果
		int i=0;
		Date startDate=c.getDate();
		while(i<c.getLimit()){
			Result r = new Result();
			
			//计算到期日期
			Calendar endCalendar = getEndCalendar(startDate, 1, c.getLimitIsDay());
			
			//将借出期限转化为天数
			int totalDay = getTotalDay(startDate, endCalendar);
			
			//计算利息
			BigDecimal interst=c.getMoney().multiply(dayRate).multiply(new BigDecimal(totalDay));
			
			//设置result
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
	
	//将借出期限转化为天数
	private int getTotalDay(Date startDate, Calendar endCalendar) {
		int totalDay;
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		totalDay = endCalendar.get(Calendar.DAY_OF_YEAR)-startCalendar.get(Calendar.DAY_OF_YEAR);
		return totalDay;
	}
	
	//计算日利率
	private BigDecimal getDayRate(BigDecimal rate,int rateIsDay) {
		BigDecimal dayRate;
		if(rateIsDay==1){
			dayRate=rate;
		}else{
			dayRate=rate.divide(new BigDecimal(365),7, BigDecimal.ROUND_HALF_UP);
		}
		return dayRate;
	}
	
	//计算到期日期
	private Calendar getEndCalendar(Date d,int limit,int limitIsDay){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		if(limitIsDay==1){
			//借出期限按天算
			calendar.add(Calendar.DAY_OF_YEAR, limit);
		}else{
			//借出期限按月算
			calendar.add(Calendar.MONTH, limit);
		}
		return calendar;
	}
}
