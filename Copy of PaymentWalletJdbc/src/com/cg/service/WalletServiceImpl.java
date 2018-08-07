package com.cg.service;

import com.cg.Dao.IWalletDao;
import com.cg.Dao.WalletDaoImpl;
import com.cg.Exception.PaymentException;
import com.cg.bean.Account;

public class WalletServiceImpl implements IWalletService {
	IWalletDao walletDao=new WalletDaoImpl();
	
	@Override
	public String CreateAccount(Account account) throws PaymentException {
		IWalletService walletService=new WalletServiceImpl();
		// TODO Auto-generated method stub
		if(walletService.validatePhone(account.getPhoneNumber())
				&& walletService.validateName(account.getName()) 
				&& walletService.validateEmail(account.getEmail())
				&& walletService.validateBalance(account.getBalance())
				)
		
			return walletDao.CreateAccount(account);
			
		else
			throw new PaymentException("failed");

	}
	
	public boolean validatePhone(String mobile)throws PaymentException
	{
		if(!mobile.matches("\\d{10}"))
		{
			throw new PaymentException("mobile number should contain 10 digits");
		}
		else
			return true;
	}
	public boolean validateName(String name)throws PaymentException
	{
		if(name==null || name.isEmpty())
		{
			throw new PaymentException("Name cannot be null or empty");
		}
		else if(name.matches("[A-Z][A_Za-z]{3,}"))
		{
			throw new PaymentException("Name should start with capital letter");
		}
			
		return true;
	}

	@Override
	public boolean validateEmail(String email) throws PaymentException {
		// TODO Auto-generated method stub
		if(!email.matches("[a-z0-9]+@[a-z]+\\.com"))
		{
			throw new PaymentException("Enter valid EmailID");
		}
		return true;
	}
	public boolean validateBalance(double balance) throws PaymentException{
		if(balance<=0)
		{
			throw new PaymentException("balance cannot be less than or equal to  zero");
		}
		else
		{
			return true;
		}
	}

	@Override
	public double ShowBalance(String mobileNo) throws PaymentException {
		// TODO Auto-generated method stub
		IWalletService walletService=new WalletServiceImpl();
		if(walletService.validatePhone(mobileNo)){
		
			return walletDao.ShowBalance(mobileNo);
		}
		else
			throw new PaymentException("fail");
		
	}

	@Override
	public Account deposit(String mobileNo, double deposit)throws PaymentException {
		IWalletService walletService=new WalletServiceImpl();
		Account acc=new Account();
		
		if(walletService.validatePhone(mobileNo))
		{
			if(deposit<=0)
			{
				throw new PaymentException("deposit amount should be above zero");
			
			}
			return walletDao.deposit(mobileNo, deposit);
		}
		
		
		return null;
	}

	@Override
	public Account withdraw(String mobileNo, double withdraw)throws PaymentException {
		IWalletService walletService=new WalletServiceImpl();
		
		if(walletService.validatePhone(mobileNo))
		{
			if(withdraw<=0)
			{
				throw new PaymentException("withdraw amount should be above zero");
			}
			
				
		
			
		}
		return walletDao.withdraw(mobileNo, withdraw);
		 
	}

	

	@Override
	public Account FundTransfer(String mobileNo1, String mobileNo2,double transferAmt) throws PaymentException {
		// TODO Auto-generated method stub
		if(!mobileNo1.matches("\\d{10}")){
			throw new PaymentException("source mobile num contain 10 digits");
			
		}if(!mobileNo2.matches("\\d{10}")){
			throw new PaymentException("destination mobile num contain 10 digits");
			
		}if(transferAmt<=0){
			throw new PaymentException("transfer amount should be >0");
		}
		return walletDao.FundTransfer(mobileNo1, mobileNo2, transferAmt);
	
	}

	@Override
	public Account printDetails(String mobile) throws PaymentException {
		// TODO Auto-generated method stub
		if(!mobile.matches("\\d{10}")){
			throw new PaymentException("mobile num contain 10 digits");
			
		}
		return walletDao.printDetails(mobile);
		
	}
	


	

	
}
