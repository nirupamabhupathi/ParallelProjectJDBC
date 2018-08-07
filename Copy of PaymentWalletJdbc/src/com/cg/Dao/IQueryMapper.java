package com.cg.Dao;

public interface IQueryMapper {
public String insert="insert into account(phoneNumber,name,email,balance) values(?,?,?,?)"; 
public String getBal="select balance from account where phoneNumber=?";
public String getAcc="select * from account where phoneNumber=?"; 
public String update="update account set balance=?,date1=? where phoneNumber=?";
}
