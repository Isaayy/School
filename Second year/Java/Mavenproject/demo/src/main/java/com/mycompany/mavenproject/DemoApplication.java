package com.mycompany.mavenproject;

import model.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AccountService;

import java.util.List;

public class DemoApplication {
	public static void main(String[] args) throws Exception {

		ApplicationContext appCtx =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		AccountService accountService =
				(AccountService)appCtx.getBean("accountService");

		List<Account> delinquentAccounts = accountService.findDeliquentAccounts();

		for (Account a : delinquentAccounts)
			System.out.println("Niesolidny to " + a.getAccountNo());
	}
}
