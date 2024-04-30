package testCases;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
//import testBase.BaseClass;
import testBase.BaseClass1;

public class TC_002_LoginTest extends BaseClass1 {
	
	@Test (groups= {"sanity","master"})
	public void Logging_in()
	{
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
	
		lp.setEmail(pr.getProperty("email"));
		lp.setPassword(pr.getProperty("password"));
		lp.LoginButton();
		
		MyAccountPage mp=new MyAccountPage(driver);
		boolean status=mp.ispagedisplayed();
		
		if(status==true)
		{
			Assert.assertTrue(true);
			mp.logout();
		}
		else
		{
			Assert.fail();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	

}
