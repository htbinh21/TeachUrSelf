package Upload_File;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Upload_File1 {

	@Test
	public void test() {
		System.setProperty("webdriver.gecko.driver", "E:\\geckodriver-v0.11.1-win64\\geckodriver.exe");

		WebDriver driver;
		driver = new FirefoxDriver();

		String workingDir = System.getProperty("user.dir");
		String filepath = workingDir + "\\SeleniumWebdriverUploadFile.html";
		driver.get(filepath);

		//WebElement fileInput = driver.findElement(By.name("uploadfile"));
		//fileInput.sendKeys(filepath);

		// Added a wait to make you notice the difference.
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElement(By.name("uploadfile")).sendKeys("D:\\images.jpg");

		// Added sleep to make you see the difference.
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

}
