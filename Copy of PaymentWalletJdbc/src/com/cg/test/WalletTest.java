package com.cg.test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cg.Exception.PaymentException;
import com.cg.bean.Account;
import com.cg.service.IWalletService;
import com.cg.service.WalletServiceImpl;


public class WalletTest {
	private IWalletService service;

	@Before
	public void init()
	{
		service = new WalletServiceImpl();
	}
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	@Test
	
	public void testCreateAccountForMobile()
	{//test1
		Account ac=new Account("924611077","BGK","BGK@cg.com",200,Date.valueOf(LocalDate.now()));
		try{
		service.CreateAccount(ac);
			}
	catch(PaymentException e){
		assertEquals("mobile number should contain 10 digits",e.getMessage());
		
	}
	}
	@Test
	public void testCreateAccountForName()
	{//test2
		Account ac=new Account("9989336633", "adi", "adi@cg.com", 200,Date.valueOf(LocalDate.now()));
		try{
			service.CreateAccount(ac);
		}catch(PaymentException e)
		{
			assertEquals("Name should start with capital letter", e.getMessage());
		}
	}
	@Test
	public void testCreateAccountForNameIsEmpty()
	{//test3
		Account ac=new Account("9989336633", "", "adi@cg.com", 200,Date.valueOf(LocalDate.now()));
		try{
			service.CreateAccount(ac);
		}catch(PaymentException e)
		{
			assertEquals("Name cannot be null or empty", e.getMessage());
		}
	}
	
	@Test
	public void TestCreateAccountForBalance()
	{//test5
		Account ac=new Account("9289336633", "Jalu", "balu1@gmail.com", 0,Date.valueOf(LocalDate.now()));
		try{
			service.CreateAccount(ac);
			
		}catch(PaymentException e)
		{
			assertEquals("Name should start with capital letter",e.getMessage());
		}
	}
	@Test
	public void TestCreateAccount()
	{//test6
		Account ac=new Account("928933633", "Balu", "balu1@gmail.com", 100,Date.valueOf(LocalDate.now()));
		try{
			String s=service.CreateAccount(ac);
			Assert.assertNotNull(s);
			
		}catch(PaymentException e)
		{
			assertEquals("mobile number should contain 10 digits",e.getMessage());
		}
	}
	@Test
	public void testShowBalanceForMobile()
	{//test7
		Account ac=new Account("1234","Anand","anand@gmail.com",300,Date.valueOf(LocalDate.now()));
		try {
			service.ShowBalance(ac.getPhoneNumber());
		} catch (PaymentException e) {
			// TODO Auto-generated catch block
			 assertEquals("mobile number should contain 10 digits",e.getMessage());
		
		}
	}
	
	
	@Test
	public void testDepositForMobile()
	{//test10
		Account ac=new Account();
		ac.setPhoneNumber("998933663");
		try {
			service.deposit(ac.getPhoneNumber(), 230);
		} catch (PaymentException e) {
			assertEquals("mobile number should contain 10 digits",e.getMessage());
		}
		
	}
	@Test
	public void testDepositForMobileNotExist()
	{//test11
		Account ac=new Account();
		ac.setPhoneNumber("9989336633");
		try {
			service.deposit(ac.getPhoneNumber(), 230);
		} catch (PaymentException e) {
			assertEquals("no update",e.getMessage());
			System.out.println(e.getMessage());
		}
		
	}
	@Test
	public void testDepositForDeposit1()
	{//test12
		Account ac=new Account();
		ac.setPhoneNumber("9246110777");
		try {
			service.deposit(ac.getPhoneNumber(), 0);
		} catch (PaymentException e) {
			assertEquals("deposit amount should be above zero",e.getMessage());
		}
		
		
	}
	@Test
	public void testDepositForDeposit2()
	{//test13
		Account ac=new Account();
		ac.setPhoneNumber("9246110777");
		try {
			service.deposit(ac.getPhoneNumber(), -20);
		} catch (PaymentException e) {
			assertEquals("deposit amount should be above zero",e.getMessage());
		}
		
		
	}
	public void testWithdrawForMobile()
	{//test14
		Account ac=new Account();
		ac.setPhoneNumber("998933663");
		try {
			service.deposit(ac.getPhoneNumber(), 230);
		} catch (PaymentException e) {
			assertEquals("mobile number should contain 10 digits",e.getMessage());
		}
		
		
	}
	
	@Test
	public void testWithdrawForWithdraw1()
	{//test16
		Account ac=new Account();
		ac.setPhoneNumber("9246110777");
		try {
			service.withdraw(ac.getPhoneNumber(), 0);
		} catch (PaymentException e) {
			assertEquals("withdraw amount should be above zero",e.getMessage());
		}
		
	}
	@Test
	public void testWithdrawForWithdraw2()
	{//test17
		Account ac=new Account();
		ac.setPhoneNumber("9246110777");
		try {
			service.withdraw(ac.getPhoneNumber(), 300);
		} catch (PaymentException e) {
			assertEquals("withdraw amount is more than the available balance",e.getMessage());
		}
		
	}
	@Test
	public void testWithdrawForWithdraw3()
	{//test18
		Account ac=new Account();
		ac.setPhoneNumber("9246110777");
		try {
			service.withdraw(ac.getPhoneNumber(), -20);
		} catch (PaymentException e) {
			assertEquals("withdraw amount should be above zero",e.getMessage());
		}
		
	}
	@Test
	public void testFundTranfer1()
	{//test19
		try {
			service.FundTransfer("1234567898","9989336633", 100);
		} catch (PaymentException e) {
			// TODO Auto-generated catch block
			assertEquals("no update",e.getMessage());
		}
		
	}
	
	@Test
	public void testFundTransfer3()
	{//test21
		try {
			service.FundTransfer("9246110777","9246110777", 100);
		} catch (PaymentException e) {
			// TODO Auto-generated catch block
			assertEquals("transfer should be done to another account",e.getMessage());
		}
		
	}
	@Test
	public void testFundTransfer4()
	{//test22
		try {
			service.FundTransfer("6666666666","589", 100);
		} catch (PaymentException e) {
			// TODO Auto-generated catch block
			assertEquals("destination mobile num contain 10 digits",e.getMessage());
		}
		
	}
	@Test
	public void testFundTransfer5()
	{//test23
		try {
			service.FundTransfer("66666","5897865432", 100);
		} catch (PaymentException e) {
			// TODO Auto-generated catch block
			assertEquals("source mobile num contain 10 digits",e.getMessage());
		}
		
	}
	@Test
	public void testFundTransfer6()
	{//test24
		try {
			service.FundTransfer("6666666666","9999999999", 0);
		} catch (PaymentException e) {
			// TODO Auto-generated catch block
			assertEquals("transfer amount should be >0",e.getMessage());
		}
		
	}
	@Test
	public void testFundTransfer7()
	{//test25
		try {
			service.FundTransfer("6666666666","9999999999", -50);
		} catch (PaymentException e) {
			// TODO Auto-generated catch block
			assertEquals("transfer amount should be >0",e.getMessage());
		}
		
	}
	@Test
	public void testPrintDetails()
	{//test26
		try {
			service.printDetails("5646757");
		} catch (PaymentException e) {
			// TODO Auto-generated catch block
			assertEquals("mobile num contain 10 digits",e.getMessage());
		}
		
	}
	@Test
	public void testPrintDetails1()
	{//test27
		try {
			service.printDetails("9246110755");
		} catch (PaymentException e) {
			// TODO Auto-generated catch block
			assertEquals("account with mobile does not exist",e.getMessage());
		}
		
	}
	@Test
	public void testPrintDetails2()
	{//test28
		try {
	Account acc=service.printDetails("9246110777");
	Assert.assertNotNull(acc);
		} catch (PaymentException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	@Test
	public void testCreateAccountForEmail1()
	{//test29
		Account ac=new Account("9289336633", "balu", "Balu@gmail.com", 10,null);
		try {
			service.CreateAccount(ac);
		} catch (PaymentException e) {
			// TODO Auto-generated catch block
			assertEquals("Enter valid EmailID",e.getMessage());
		}
	}
	@Test
	public void testDeposit()
	{//test30
		try {
		Account acc=service.deposit("9246110777",500);
		Assert.assertNotNull(acc);
		} catch (PaymentException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	
	
	}
	
}
	
	
	
	


