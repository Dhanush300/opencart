package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage  {

	public MyAccountPage(WebDriver driver) 
	{
		super(driver);
		
	}

	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement msg;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement logout_btn;
	
	public boolean ispagedisplayed() 
	{
		try
		{
          return msg.isDisplayed(); //Validations should not be part of Page object class
       
		}
    	   
		catch(Exception e)
		{
			return false;
		}
		
		
	}
	
	public void logout()
	{
		logout_btn.click();
	}
   
	
	
}
