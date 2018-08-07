package com.cg.Dao;

import com.cg.Exception.PaymentException;
import com.cg.bean.Account;

public interface IWalletDao {
	String CreateAccount(Account account) throws PaymentException;		
	double ShowBalance(String mobileNo) throws PaymentException;
	Account deposit(String mobileNo, double deposit) throws PaymentException;
	Account withdraw(String mobileNo, double withdraw) throws PaymentException;
	Account FundTransfer(String mobileNo1, String mobileNo2, double transferAmt) throws PaymentException;
	Account printDetails(String mobile)throws PaymentException;

}
