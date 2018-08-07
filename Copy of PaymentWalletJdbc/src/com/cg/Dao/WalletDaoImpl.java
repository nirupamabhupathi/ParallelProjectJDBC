package com.cg.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

import com.cg.Exception.PaymentException;
import com.cg.bean.Account;
import com.cg.db.DBUtil;

public class WalletDaoImpl implements IWalletDao {
	//private static HashMap<String, Account> AccountMap= AccountDb.getAccountEntry();


	@Override
	public String CreateAccount(Account account) throws PaymentException {
//		// TODO Auto-generated method stub
		Connection con=DBUtil.getConnection();
		PreparedStatement stat;
		try {
			con.setAutoCommit(false);
			stat=con.prepareStatement(IQueryMapper.insert);
			stat.setString(1, account.getPhoneNumber());
			stat.setString(2, account.getName());
			stat.setString(3, account.getEmail());
			stat.setDouble(4, account.getBalance());
			int res=stat.executeUpdate();
			if(res==1)
			{
				con.commit();
				return account.getPhoneNumber();
			}
			else
			{
				throw new PaymentException("account creation failed");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		throw new PaymentException(e.getMessage());
		}
		
		
	}

	@Override
	public double ShowBalance(String mobileNo) throws PaymentException {
		Connection con=DBUtil.getConnection();
		PreparedStatement stat;
		try {
			con.setAutoCommit(false);
			stat=con.prepareStatement(IQueryMapper.getAcc);
			stat.setString(1,mobileNo);
			ResultSet res=stat.executeQuery();
			con.commit();
			if(res!=null)
			{
				res.next();
				return res.getDouble("balance");
			}
			else
			{
				throw new PaymentException("account with mobile number does not exist");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new PaymentException(e.getMessage());
		}
	
		
		
	}

	@Override
	public Account deposit(String mobileNo, double deposit) throws PaymentException {
		Connection con=DBUtil.getConnection();
		PreparedStatement stat;
		PreparedStatement stat1;
		try {
			con.setAutoCommit(false);
			stat=con.prepareStatement(IQueryMapper.getAcc);
			stat.setString(1,mobileNo);
			ResultSet res=stat.executeQuery();
		
			con.commit();
			if(res!=null)
			{
				res.next();
				Account acc=new Account();
				double balance=res.getDouble("balance")+deposit;
				acc.setName(res.getString("name"));
				acc.setPhoneNumber(res.getString("phoneNumber"));
				acc.setEmail(res.getString("email"));
				acc.setBalance((balance));
				acc.setDate(Date.valueOf(LocalDate.now()));
				
				stat1=con.prepareStatement(IQueryMapper.update);
				stat1.setDouble(1, acc.getBalance());
				stat1.setDate(2,acc.getDate());
				stat1.setString(3, acc.getPhoneNumber());
				int rs=stat1.executeUpdate();
				if(rs==1)
				{
					con.commit();
					return acc;
				}
				else
				{
					throw new PaymentException("no update");
				}
				
			}
			else
			{
				throw new PaymentException("account with mobile number does not exist");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new PaymentException(e.getMessage());
		}
		
	}

	@Override
	public Account withdraw(String mobileNo, double withdraw) throws PaymentException {
		Connection con=DBUtil.getConnection();
		PreparedStatement stat;
		PreparedStatement stat1;
		try {
			con.setAutoCommit(false);
			stat=con.prepareStatement(IQueryMapper.getAcc);
			stat.setString(1,mobileNo);
			ResultSet res=stat.executeQuery();
		
			con.commit();
			if(res!=null)
			{
				res.next();
				Account acc=new Account();
				double balance=res.getDouble("balance")-withdraw;
				acc.setName(res.getString("name"));
				acc.setPhoneNumber(res.getString("phoneNumber"));
				acc.setEmail(res.getString("email"));
				acc.setBalance((balance));
				acc.setDate(Date.valueOf(LocalDate.now()));
				
				stat1=con.prepareStatement(IQueryMapper.update);
				stat1.setDouble(1, acc.getBalance());
				stat1.setDate(2,acc.getDate());
				stat1.setString(3, acc.getPhoneNumber());
				int rs=stat1.executeUpdate();
				if(rs==1)
				{
					con.commit();
					return acc;
				}
				else
				{
					throw new PaymentException("Account with mobile numbe"+acc.getPhoneNumber()+" does not exist");
				}
				
			}
			else
			{
				throw new PaymentException("account with mobile number does not exist");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new PaymentException(e.getMessage());
		}
	}

	@Override
	public Account FundTransfer(String mobileNo1, String mobileNo2,double transferAmt) throws PaymentException
	{
		Connection con=DBUtil.getConnection();
		PreparedStatement stat;
		PreparedStatement stat1;
		try {
			con.setAutoCommit(false);
			stat=con.prepareStatement(IQueryMapper.getAcc);
			stat.setString(1,mobileNo1);
			ResultSet res=stat.executeQuery();
			con.commit();
			stat1=con.prepareStatement(IQueryMapper.getAcc);
			stat1.setString(1, mobileNo2);
			ResultSet res1=stat1.executeQuery();
			con.commit();
			
			if(res!=null && res1!=null)
			{
				IWalletDao dao=new WalletDaoImpl();
				dao.deposit(mobileNo2, transferAmt);
				dao.withdraw(mobileNo1, transferAmt);
			}
			else
			{
				throw new PaymentException("not possible");
			}
			return null;
			
			/*if(res!=null && res1!=null )
			{
				res.next();
				Account acc=new Account();
				double balance=res.getDouble("balance")-transferAmt;
				acc.setName(res.getString("name"));
				acc.setPhoneNumber(res.getString("phoneNumber"));
				acc.setEmail(res.getString("email"));
				acc.setBalance((balance));
				acc.setDate(Date.valueOf(LocalDate.now()));
				
				
				
				
				
				return acc;
				
			}
			
				
			else if(res1!=null)
			{
				res.next();
				Account acc=new Account();
				double balance=res.getDouble("balance")+transferAmt;
				acc.setName(res.getString("name"));
				acc.setPhoneNumber(res.getString("phoneNumber"));
				acc.setEmail(res.getString("email"));
				acc.setBalance((balance));
				acc.setDate(Date.valueOf(LocalDate.now()));
				return acc;
				
			}
			else
			{
				throw new PaymentException(mobileNo2+"not exist");
			}*/
				
				
				
				
		}
		catch(SQLException e)
		{
			throw new PaymentException(e.getMessage());
		}
		/*if(acc1==null){
			throw new PaymentException("account with source mobile does not exist");
		}	if(acc2==null){
			throw new PaymentException("account with destination mobile does not exist");
		}
		if(mobileNo1==mobileNo2){
			throw new PaymentException("transfer should be done to another account");
		}
		Account ac1=dao.withdraw(mobileNo1,transferAmt);
		Account ac2=dao.deposit(mobileNo2,transferAmt);
		return ac2 ;*/
		
	}

	@Override
	public Account printDetails(String mobile) throws PaymentException {
		
		
		
		Connection con=DBUtil.getConnection();
		PreparedStatement stat;
		try {
			stat=con.prepareStatement(IQueryMapper.getAcc);
			stat.setString(1,mobile);
			ResultSet res=stat.executeQuery();
			con.commit();
			if(res!=null)
			{
				res.next();
				Account acc=new Account();
				acc.getName();
				acc.getPhoneNumber();
				acc.getEmail();
				acc.getBalance();
				acc.getBalance();
				return acc;
			}
			else
			{
				throw new PaymentException("account with mobile number does not exist");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new PaymentException(e.getMessage());
		}

	}
		
		
	}


