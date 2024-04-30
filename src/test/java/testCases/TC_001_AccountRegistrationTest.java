package testCases;




import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
//import testBase.BaseClass;
import testBase.BaseClass1;

//All logger methods are from session 3

public class TC_001_AccountRegistrationTest extends BaseClass1 //all methods inside base class is accessible
{
	
	
	
	@Test(groups={"regression","master"})
	public void Registration() 
	{
		
		
		logger.debug("Test case start");
		try 
		{
		logger.info("navigating to Registration page");
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		
		RegistrationPage rp=new RegistrationPage(driver);
		
		logger.info("Filling details");
		rp.setFirstName(randomeString().toUpperCase());
		rp.setLastName(randomeString().toUpperCase());
		rp.setEmail(randomAlphaNumeric()+"@gmail.com");
		//rp.setTelephone(randomNumbers());
		
		String pass=randomAlphaNumeric();
		
		rp.setPassword(pass);
		rp.setPrivacyPolicy();
		rp.clickContinue();
		
		logger.info("Validation");
		if(rp.getConfirmationMsg().equals("Your Account Has Been Created!"))
		{
			logger.info("Test case pass");
			Assert.assertTrue(true);
					
		}
		else
		{
			logger.info("Confirmation message mismatch");
			Assert.fail();
			
			
		}
		}//end of try block
		
		catch(Exception e) 
		{
			logger.error("Test case fail");
			Assert.fail();
		}
		
		logger.debug("Test case finish");
		
	}

	 
	 
}