package com.cg.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cg.Exception.PaymentException;

public class DBUtil {
public static Connection getConnection() throws PaymentException
{
	String url="jdbc:mysql://localhost:3306/jdbc";
	try{
	//Class.forName("oracle.jdbc.driver.OracleDriver");
		Class.forName("com.mysql.jdbc.Driver");
	return DriverManager.getConnection(url,"root","root");
	}catch(ClassNotFoundException e){
	throw new PaymentException(e.getMessage());
	}catch(SQLException e1){
	throw new PaymentException(e1.getMessage());
	}  
	
}
}
