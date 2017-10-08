package com.calculator.control;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.calculator.domain.Condition;
import com.calculator.domain.Result;
import com.calculator.service.CalculatorService;
import com.calculator.service.impl.CalculatorServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CalculatorControl extends HttpServlet {

	public CalculatorControl() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Condition c = new Condition();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = sdf.parse((String) request.getParameter("date"));
			c.setDate(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setMoney(new BigDecimal(request.getParameter("money")));
		c.setLimit(Integer.parseInt(request.getParameter("limit")));
		c.setMethod(request.getParameter("method"));
		c.setRate(new BigDecimal(request.getParameter("rate"))
				.divide(new BigDecimal(100)));
		c.setLimitIsDay(Integer.parseInt(request.getParameter("limitIsDay")));
		c.setRateIsDay(Integer.parseInt(request.getParameter("rateIsDay")));
		
		CalculatorService cs = new CalculatorServiceImpl();
		Result[] tr = null;
		switch (c.getMethod()) {
		case "B":
			tr = cs.alsoDuePrincipalAndInterest(c);
			break;
		case "A":
			tr = cs.monthInterestDueDebt(c);
			break;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(tr);

		response.setContentType("text/javascript"); 
		response.getWriter().print(result); 
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
