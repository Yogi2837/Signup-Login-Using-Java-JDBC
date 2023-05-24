package com.Login;
import java.util.*;

import com.validations.LoginValidations;
import java.sql.*;
public class ForgotPassword {
	
	private String dbUrl="jdbc:mysql://localhost:3306/instagram";
	private String dbUname="root";
	private String dbPass="Yogi@2837";
	private String dbDriver="com.mysql.cj.jdbc.Driver";
	
	LoginValidations validate=new LoginValidations();
	
	
	Scanner sc=new Scanner(System.in);
	
	public void loadDriver(String dbDriver)
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
	
	public Connection createConnection()
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
	
	public void forgotPassword(long num)
	{
		System.out.println("enter the mobile num");
		
		loadDriver(dbDriver);
		Connection con=createConnection();
		long userNum=sc.nextLong();
		PreparedStatement psmt;
		try {
			psmt = con.prepareStatement("select mobile_num,otp from signup where mobile_num=?");
			psmt.setLong(1, num);
			ResultSet rs= psmt.executeQuery();
			while(rs.next())
			{
				long dbNum=rs.getLong("mobile_num");
				int dbOtp=rs.getInt("otp");
				if(userNum==dbNum)
				{
					boolean notValidOtp=true;
					System.out.println("your otp is:"+dbOtp);
					do {
						System.out.println("enter the otp");
						int userOtp=sc.nextInt();
						if(userOtp==dbOtp)
						{
							notValidOtp=false;
							System.out.println("enter the new password");
							String usrPass=sc.next();
							boolean isPassValid=validate.validatePassword(usrPass);
							if(isPassValid)
							{
								boolean notValid=true;
								do {
									
									System.out.println("confirm your password");
									String cnfrmPass=sc.next();
									if(cnfrmPass.equals(usrPass))
									{
										notValid=false;
										System.out.println("password matching");
										String updateQuery="update signup set password=? where mobile_num=?";
										PreparedStatement psmt2=con.prepareStatement(updateQuery);
										psmt2.setString(1, usrPass);
										psmt2.setLong(2, num);
										psmt2.executeUpdate();
										System.out.println("password updated successfully");
										System.out.println("1-for login\n\n2-for logout");
										
									}
									else
									{
										System.out.println("password is not matching");
										System.out.println("enter the crct password");
									}
								} 
								while (notValid);
							}
						}
						else
						{
							System.out.println("enter the valid otp ");
							System.out.println("the entered otp should be match with given otp");
						}
					} 
					while (notValidOtp);
				}
				else
				{
					System.out.println("no user found with this num");
					System.out.println(" 1-enter the crct num\n2- signup");
				}
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
