package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
//import testBase.BaseClass;
import testBase.BaseClass1;
import utilities.DataProviders;

public class TC_003_DDTest extends BaseClass1 {
	
	@Test(dataProvider="logindata",dataProviderClass=DataProviders.class)//WE've to specify class name if the dataprovider is in another class
	public void LoginTest(String email,String pass,String exp)
	
	//Once code for DataProviders is done, it will return the data exactly as it is in the excel sheet.so we just need to capture
	//the return datas in a string variable,dataproviders will take care of execution
	
	{
		try {

		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
	
		lp.setEmail(email);
		lp.setPassword(pass);
		lp.LoginButton();
		
		MyAccountPage mp=new MyAccountPage(driver);
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(mp.ispagedisplayed()==true)
			{
				Assert.assertTrue(true);
				mp.logout();
			}
			else
			{
				Assert.fail();
			}
			
		}
		
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(mp.ispagedisplayed()==false)
			{
				Assert.assertTrue(true);
			}
			else
			{
				Assert.fail();
			}
	     }
	
		}
		catch(Exception e)
		{
		
		 System.out.println(e.getMessage());
		}
		
		
		
		
	}

}
