package com.calculator.service;

import com.calculator.domain.Condition;
import com.calculator.domain.Result;

public interface CalculatorService {
	
	Result[] alsoDuePrincipalAndInterest(Condition c);	//���ڻ���Ϣ
	
	Result[] monthInterestDueDebt(Condition c);			//�»�Ϣ���ڻ���
}