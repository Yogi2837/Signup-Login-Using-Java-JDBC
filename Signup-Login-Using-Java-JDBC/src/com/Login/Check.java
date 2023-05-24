package com.Login;
import java.sql.*;
public class Check {
	
	private static String dbUrl="jdbc:mysql://localhost:3306/instagram";
	private static String dbUname="root";
	private static String dbPass="Yogi@2837";
	private static String dbDriver="com.mysql.cj.jdbc.Driver";
	
	public static void loadDriver(String dbDriver)
	{
		try 
		{
			Class.forName(dbDriver);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static Connection createConnection()
	{
		Connection con=null;
		try 
		{
			con=DriverManager.getConnection(dbUrl,dbUname,dbPass);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public static boolean isMobileThere(long num)
	{
		loadDriver(dbDriver);
		Connection con=createConnection();
		String mobileCheck="select mobile_num from signup";
		try 
		{
			Statement smt=con.createStatement();
			ResultSet rs = smt.executeQuery(mobileCheck);
			while(rs.next())
			{
				long dbNum=rs.getLong("mobile_num");
				if(num==dbNum)
				{
					return true;
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean isGmailThere(String gmail,long num)
	{
		loadDriver(dbDriver);
		Connection con=createConnection();
		String mobileCheck="select gmail from signup";
		try 
		{
			Statement smt=con.createStatement();
			ResultSet rs = smt.executeQuery(mobileCheck);
			String dbGmail=rs.getString("gmail");
			if(gmail.equals(dbGmail))
			{
				return true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	public static boolean isPasswordThere(String password,long num)
	{
		loadDriver(dbDriver);
		Connection con=createConnection();
		String mobileCheck="select password from signup where mobile_num="+num;
		try 
		{
			Statement smt=con.createStatement();
			ResultSet rs = smt.executeQuery(mobileCheck);
			rs.next();
			String dbPassword=rs.getString("password");
			if(password.equals(dbPassword))
			{
				return true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

}
