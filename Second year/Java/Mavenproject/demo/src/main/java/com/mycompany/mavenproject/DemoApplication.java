package com.mycompany.mavenproject;

import spring.jdbc.impl.AccountServiceImp;
import spring.model.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.service.AccountService;

import java.util.List;

/**
 Application which contents and fetch data from database
 Program is using beans and other spring features
 @author Jakub Serwin
 @version 1.0
 */

/**
 * This is the main method which set the context of the app.
 * @return Nothing.
 */
public class DemoApplication {
	public static void main(String[] args) throws Exception {

		// Zad 6 - zrodlo danych - plik csv

//		ApplicationContext appCtx =
//				new ClassPathXmlApplicationContext("applicationContext.xml");
//		AccountService accountService =
//				(AccountService)appCtx.getBean("accountService");
//
//		List<Account> delinquentAccounts = accountService.findDeliquentAccounts();


		// Zad 7 - zrodlo danych - baza danych

		ApplicationContext appCtx =
				new ClassPathXmlApplicationContext("applicationContext.xml");

		AccountServiceImp accountServiceImp = (AccountServiceImp)appCtx.getBean("AccountServiceImp");


		List<Account> delinquentAccounts = accountServiceImp.findAll();

		for (Account a : delinquentAccounts)
			System.out.println("Niesolidna osoba to: " + a.getAccountNo());
	}
}
