package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

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
import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;   //log4j


public class BaseClass1 {

	public static WebDriver driver;
	public Logger logger;
	public Properties pr;
	
	

	@Parameters ({"browser","os"})
	@BeforeClass (groups={"sanity","regression","master"})
	public void setup(String br,String os) throws IOException
	
	{
		/*if(pr.getProperty("execution_env").equalsIgnoreCase("remote"))
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
		else if(pr.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		*/
		switch(br.toLowerCase())
		{
		case "chrome":driver=new ChromeDriver();break;
		case "edge":driver=new EdgeDriver();break;
		case "firefox":driver=new FirefoxDriver();break;
		default:System.out.println("No matching browser");return;
		}
//		}

		FileReader fr=new FileReader(System.getProperty("user.dir")+"\\src/test/resources\\config.properties");
		pr=new Properties(); //Properties is a javaclass to load the values inside congig.properties inside base class
		pr.load(fr);
	
		logger=LogManager.getLogger(this.getClass());//Log4j
		
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("http://localhost/opencart/upload/index.php");
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	

	public String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomeNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
	public String randomAlphaNumeric()
	{
		String str=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		
		return (str+num);
	}
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		File sourceFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}

}
