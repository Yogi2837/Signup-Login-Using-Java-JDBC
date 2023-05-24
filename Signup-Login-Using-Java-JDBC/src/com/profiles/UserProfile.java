package com.profiles;
import java.util.*;
import java.sql.*;
import com.SignUp.*;
import com.validations.*;
public class UserProfile {
	
	Scanner sc=new Scanner(System.in);
	ValidateUpdatingDetails toValidate=new ValidateUpdatingDetails();
	private  String dbUrl="jdbc:mysql://localhost:3306/instagram";
	private  String dbUname="root";
	private  String dbPass="Yogi@2837";
	private  String dbDriver="com.mysql.cj.jdbc.Driver";
	
	private  UserDto dt=new UserDto();
	
	public  void loadDriver(String dbDriver)
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
	
	public void showUserData(long num)
	{
		System.out.println("enter 1-to see Profile\n2-update profile\n3-delete profile");
		int enter=sc.nextInt();
		switch(enter)
		{
			case 1:
				seeProfile(num);
				break;
			case 2:
				updateProfile(num);
				break;
			case 3:
				deleteProfile(num);
		}
	}
	
	public  void seeProfile(long num)
	{
		loadDriver(dbDriver);
		Connection con=createConnection();
		String see="select * from signup where mobile_num=?";
		PreparedStatement prepare;
		try {
			prepare=con.prepareStatement(see);
			prepare.setLong(1, num);
			ResultSet rs=prepare.executeQuery();
			if(rs.next())
			{
				String name=rs.getString("name");
				long mobile=rs.getLong("mobile_num");
				String gmail=rs.getString("gmail");
				int age=rs.getInt("age");
				String gender=rs.getString("gender");
				String dob=rs.getString("dob");
				String address=rs.getString("address");
				String altMobile=rs.getString("altMobile_num");
				
				System.out.println("your name :"+name);
				System.out.println("your mobile_num :"+mobile);
				System.out.println("your gmail :"+gmail);
				System.out.println("your age :"+age);
				System.out.println("your gender :"+gender);
				System.out.println("your dob :"+dob);
				System.out.println("your address :"+address);
				System.out.println("your altMobile :"+altMobile);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public  void deleteProfile(long num)
	{
		loadDriver(dbDriver);
		Connection con=createConnection();
		String delete="delete from signup where mobile_num=?";
		PreparedStatement prepare;
		try {
			prepare=con.prepareStatement(delete);
			prepare.setLong(1, num);
			System.out.println("your account deleted successfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public  void updateProfile(long num)
	{
		loadDriver(dbDriver);
		Connection con=createConnection();
		PreparedStatement prepare;
		System.out.println("1-name 2-mobile 3-gmail 4-password 5-dob 6-address 7-altmobile");
		int enter=sc.nextInt();
		switch(enter)
		{
			case 1:
				System.out.println("enter the new name");
				String name=sc.next();
				boolean isValidName=toValidate.validateName(name);
				if(isValidName)
				{
					try {
						prepare=con.prepareStatement("update signup set name=? where mobile_num=?");
						prepare.setString(1, name);
						prepare.setLong(2, num);
						prepare.execute();
						System.out.println("your name updated successfully");
					} catch (SQLException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 2:
				System.out.println("enter the new mobile num");
				long mobile=sc.nextLong();
				boolean isValidNum=toValidate.validateMobile_Num(mobile);
				if(isValidNum)
				{
					try {
						prepare=con.prepareStatement("update signup set mobile_num=? where mobile_num=?");
						prepare.setLong(1, mobile);
						prepare.setLong(2, num);
						prepare.execute();
						System.out.println("your mobile num updated successfully");
					} catch (SQLException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 3:
				System.out.println("enter the new gmail");
				String gmail=sc.next();
				boolean isValidGmail=toValidate.validateGmail(gmail);
				if(isValidGmail)
				{
					try {
						prepare=con.prepareStatement("update signup set gmail=? where mobile_num=?");
						prepare.setString(1, gmail);
						prepare.setLong(2, num);
						prepare.execute();
						System.out.println("your gmail updated successfully");
					} catch (SQLException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 4:
				System.out.println("enter the new password");
				String password=sc.next();
				boolean isValidPass=toValidate.validatePassword(password);
				if(isValidPass)
				{
					try {
						prepare=con.prepareStatement("update signup set password=? where mobile_num=?");
						prepare.setString(1, password);
						prepare.setLong(2, num);
						prepare.execute();
						System.out.println("your password updated successfully");
					} catch (SQLException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 5:
				System.out.println("enter the new dob");
				String dob=sc.next();
				boolean isValidDob=toValidate.validateDob(dob);
				if(isValidDob)
				{
					try {
						prepare=con.prepareStatement("update signup set dob=? where mobile_num=?");
						prepare.setString(1, dob);
						prepare.setLong(2, num);
						prepare.execute();
					} catch (SQLException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 6:
				System.out.println("enter the new address");
				String address=sc.next();
				try {
					prepare=con.prepareStatement("update signup set address=? where mobile_num=?");
					prepare.setString(1, address);
					prepare.setLong(2,num);
					prepare.execute();
				} catch (SQLException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 7:
				System.out.println("enter the new altmobile");
				long altMobile=sc.nextLong();
				boolean isValidAltMobile=toValidate.validateAltMobile_Num(altMobile,num);
				if(isValidAltMobile)
				{
					try {
						prepare=con.prepareStatement("update signup set altMobile_num=? where mobile_num=?");
						prepare.setLong(1, altMobile);
						prepare.setLong(2, num);
						prepare.execute();
					} catch (SQLException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;	
		}
	}
	public void logOut()
	{
		
	}
	
	
	
	

}
