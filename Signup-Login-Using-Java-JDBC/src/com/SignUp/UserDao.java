package com.SignUp;
import java.sql.*;
import java.util.*;

public class UserDao {
	
static Scanner sc=new Scanner(System.in);
	
	private String dbUrl="jdbc:mysql://localhost:3306/instagram";
	private String dbUname="root";
	private String dbPass="Yogi@2837";
	private String dbDriver="com.mysql.cj.jdbc.Driver";
	private static final String insert="insert into signup(name,mobile_num,gmail,age,password,gender,dob,address,altMobile_num,otp)"
			+ "values(?,?,?,?,?,?,?,?,?,?)";
	
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
	
	public void insert(UserDto userData)
	{	
		loadDriver(dbDriver);
		Connection con=createConnection();
		PreparedStatement prepare;
		try {
			prepare = con.prepareStatement(insert);
			prepare.setString(1, userData.getName());
			prepare.setLong(2, userData.getMobile_num());
			prepare.setString(3, userData.getGmail());
			prepare.setInt(4, userData.getAge());
			prepare.setString(5, userData.getPassword());
			prepare.setString(6, userData.getGender());
			prepare.setString(7, userData.getDob());
			prepare.setString(8, userData.getAddress());
			prepare.setLong(9, userData.getAltMobile_num());
			prepare.setInt(10, userData.getOtp());
			prepare.executeUpdate();
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
	}
	
	public void read(UserDto userData)
	{
		loadDriver(dbDriver);
		Connection con=createConnection();
		String read="select * from signup where mobile_num=?";
		PreparedStatement prepare;
		try
		{
			prepare=con.prepareStatement(read);
			prepare.setLong(1, userData.getMobile_num());
			ResultSet rs=prepare.executeQuery();
			while(rs.next())
			{
				String userName=rs.getString(1);
				long userMobile=rs.getLong(2);
				String userGmail=rs.getString(3);
				String userAge=rs.getString(5);
				String userGender=rs.getString(6);
				String userDob=rs.getString(7);
				
				System.out.println(userName+" "+userMobile+" "+userGmail+" "+userAge+" "+userGender+" "+userDob);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void update(UserDto userData)
	{
		loadDriver(dbDriver);
		Connection con=createConnection();
		String update="update school set schlName=? where schlId=?";
		PreparedStatement prepare;
		
		try {
			prepare=con.prepareStatement(update);
			prepare.setString(1, "alpha public");
			prepare.setInt(2, 1);
			prepare.executeUpdate();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(UserDto userData)
	{
		loadDriver(dbDriver);
		Connection con=createConnection();
		String delete="delete school where schlId=?";
		System.out.println("enter the column datatype");
		String colData=sc.next();
		if(colData.equalsIgnoreCase("int"))
		{
			System.out.println("enter the column name");
			String colName=sc.next();
		}
		PreparedStatement prepare;
		
		try {
			prepare=con.prepareStatement(delete);
			//prepare.set(1, schlData.getClass().getMethod(colData));
			prepare.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
