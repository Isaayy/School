package com.mycompany.mavenproject;

import spring.jdbc.impl.AccountServiceImp;
import spring.model.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class DemoApplication {
	public static void main(String[] args) throws Exception {

		ApplicationContext appCtx =
				new ClassPathXmlApplicationContext("applicationContext.xml");

		AccountServiceImp accountServiceImp = (AccountServiceImp)appCtx.getBean("AccountServiceImp");

		List<Account> delinquentAccounts = accountServiceImp.findAll();

		for (Account a : delinquentAccounts)
			System.out.println("Niesolidna osoba to: " + a.getAccountNo());
	}
}
