package serviceimplementation;

import pojo.*;
import repository.*;

public class LoginOperations {
	public LoginOperations()
	{
		
	}
	
	public static boolean checkexistance(Credentials user)
	{
		if(new LoginDao().check(user) != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean validatePassword(Credentials user)
	{
		if(new LoginDao().check(user).getPassword().equals(user.getPassword()))
			return true;
		return false;
	}
	
	public static  String getType(Credentials user)
	{
		return new LoginDao().check(user).getRoles().getRoleName();
	}
	
//	public void addUser(Credentials user)
//	{
//		new LoginDao().registerUser(user);
//	}

	
}