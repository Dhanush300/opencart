package testBase;
/* some problem here so created new base class
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.Logger;

//All pre-requisite stuff and re-usable stuffs are placed inside one class, so no need to create again and again
public class BaseClass{

	 public static WebDriver driver; //public webdriver, so can be accessed anywhere inside the source folder
	
	
	 public Logger logger; //Logger is a pre-defined class
	public Properties pr;
	
	
	@Parameters ({"browser","os"})
	@BeforeClass (groups={"sanity","regression","master"})
public void setup(String br,String os) throws IOException
	{
		FileReader fr=new FileReader(System.getProperty("user.dir")+"\\src/test/resources\\config.properties");
		pr=new Properties(); //Properties is a javaclass to load the values inside congig.properties inside base class
		pr.load(fr);
		
		//we have to set the condition based on the executiojn environment
		
		//1
		if(pr.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			String nodeURL = "http://localhost:4444/wd/hub"; //fixed url to see the sessions
			DesiredCapabilities cap=new DesiredCapabilities(); //a selenium class to laumch the code in the desired os
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WINDOWS);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("no matching os");
				return;
			}
			
			//browser
			 switch(br.toLowerCase())
			{
			 
			 case "chrome":cap.setBrowserName("chrome");
			 break;
			 
			 case "edge":cap.setBrowserName("MicrosoftEdge");
			 break;
			 
			 default:System.out.println("no matcing browser");
			 return;
			 
			 
			}
			 WebDriver driver = new RemoteWebDriver(new URL(nodeURL),cap);//instead of WebDriver driver; we use RemoteWebDriver here for setting os
			 
			 driver.manage().deleteAllCookies(); 
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.get(pr.getProperty("appURL"));
			    driver.manage().window().maximize();
			
		}
		
	//2
	else if(pr.getProperty("execution_env").equalsIgnoreCase("local"))
		
	{
        switch(br.toLowerCase()) //only using br in this instance(session 3)
	     {
		   case "chrome":driver=new ChromeDriver(); //switch case statements for getting different browsers
		   break; //skips the current code
		   case "edge":driver= new EdgeDriver();
		   break;
		   case "firefox":driver=new FirefoxDriver();
	       break;
	       default: System.out.println("no matching browser");
	       return; //skips the entire block
	     }
    
		//for loading logger class(session 3)
		
		logger=LogManager.getLogger(this.getClass()); //this.getclass() returns the current class name we are working in
		//LogManager is also a pre-defined class which will return a logger type, that we storing in logger varaiable
		
		driver.manage().deleteAllCookies(); //deletes all cookies everytime before proceeding further
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(pr.getProperty("appURL"));
	    driver.manage().window().maximize();
	}
}
	
	
	@AfterClass(groups={"sanity","regression","master"})
	public void teardown()
	{
		driver.close();
	}
	
//[from session 2]
	//RandomStringUtils can be used to generate random strings which will generate new random string everytime.This is useful when test case needs to be ran multiple times
		
		 public String randomAlphabets()
		 {
			 String randomAlpha=RandomStringUtils.randomAlphabetic(5);
			 return randomAlpha;
			 
		 }
		
		 public String randomAlphanumerics()
		 {
			 String randomalnum=RandomStringUtils.randomAlphanumeric(6);
			 return randomalnum;
			 
		 }
		 public String randomNumbers()
		 {
			 String randomnm=RandomStringUtils.randomNumeric(8);
			 return randomnm; //not used anywhere
		 }
		 
	public String captureScreen(String tname) throws IOException {
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdatetimestamp=df.format(dt);
		
		//String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		
		String target=System.getProperty("user.dir")+"\\screenshots\\"+tname+"__"+currentdatetimestamp+".png";
		File destination=new File(target);
		
	
		source.renameTo(destination);
		return target;
		
	
		
	}
		 
}*/