package com.cg.service;

import com.cg.Exception.PaymentException;
import com.cg.bean.Account;

public interface IWalletService {
	String CreateAccount(Account account) throws PaymentException;
	public boolean validatePhone(String mobile)throws PaymentException;
	public boolean validateName(String name)throws PaymentException;
	public boolean validateEmail(String email)throws PaymentException;
	public boolean validateBalance(double balance) throws PaymentException;
	public double ShowBalance(String mobileNo) throws PaymentException;
	Account deposit(String mobileNo, double deposit) throws PaymentException;
	Account withdraw(String mobileNo, double withdraw) throws PaymentException;
	Account FundTransfer(String mobileNo1, String mobileNo2, double transferAmt) throws PaymentException;
	Account printDetails(String mobile)throws PaymentException;
}
