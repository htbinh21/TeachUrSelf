package CANProject;

import org.junit.Assert;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.lang.System;


public class CANProject 
{
 WebDriver driver;
 

 @Test
 public void testcase1() 
 {
  System.setProperty("webdriver.gecko.driver", "E:\\geckodriver-v0.11.1-win64\\geckodriver.exe");
  driver = new FirefoxDriver();

  driver.manage().window().maximize();
  System.out.print("Window maximise");
  
  driver.get("http://113.161.60.217:10081/");
  //System.out.print("Site Open");
 driver.quit();
  //System.out.print("End of Test");
 }
 
 @Test
 public void testcase2()
 {
	 System.setProperty("webdriver.gecko.driver", "E:\\geckodriver-v0.11.1-win64\\geckodriver.exe");
	 driver = new FirefoxDriver();
	 driver.get("http://113.161.60.217:10081/");
	// driver.get("http://113.161.60.217:10081/spc/index.php");
	 
	 driver.findElement(By.id("spc_login")).click();
	 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	 //String url1=  driver.getCurrentUrl();
	 //driver.navigate().to (url1);
	 driver.findElement(By.id("login_email")).sendKeys("thienbinh21@gmail.com");
	 driver.findElement(By.id("login_pass")).sendKeys("12345");
	 driver.findElement(By.id("login_memory")).click();
	 driver.findElement(By.name("commit")).click();
	 driver.quit();
 
 }
 
 @Test
 public void testcase3()
 {
	 System.setProperty("webdriver.gecko.driver", "E:\\geckodriver-v0.11.1-win64\\geckodriver.exe");
	 driver = new FirefoxDriver();
	 driver.get("http://live.guru99.com/");
	 //2. verify title
	 Assert.assertEquals("Home page", driver.getTitle()); 
	 //3.click on 'MOBILE' menu
	
	driver.findElement(By.xpath("//*[contains(text(),'Mobile')]")).click();
			 //4. Verify title of page
	 
	 //5.In list all mobile, select 'Sort By' dropdown  as 'name'
	
	 
 }
 

}