package Testing_Pack;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Testing_Pack {

 public static void main(String[] args) throws IOException {

  System.setProperty("webdriver.gecko.driver", "E:\\geckodriver-v0.11.1-win64\\geckodriver.exe");
  WebDriver driver = new FirefoxDriver();
     driver.get("https://roudou.zead.jp/Account/Login?");
	 
	 //driver.findElement(By.id("spc_login")).click();
	 //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	 //String url1=  driver.getCurrentUrl();
	 //driver.navigate().to (url1);
	 driver.findElement(By.id("Email")).sendKeys("admin@gmail.com");
	 driver.findElement(By.id("Password")).sendKeys("Splus@12345");
	 //driver.findElement(By.id("login_memory")).click();
	 driver.findElement(By.name("RememberMe")).click();
	 driver.findElement(By.xpath("//*[contains(text(),'ログイン')]")).click();
	 
  
  /*
  driver.get("http://only-testing-blog.blogspot.in/2013/09/testing.html");
  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  
  //Find total No of links on page and print In console.
  List<WebElement> total_links = driver.findElements(By.tagName("a"));
  System.out.println("Total Number of links found on page = " + total_links.size());
  
  //for loop to open all links one by one to check response code.
  boolean isValid = false;
  for (int i = 0; i < total_links.size(); i++) {
   String url = total_links.get(i).getAttribute("href");

   if (url != null)
   {
    
    //Call getResponseCode function for each URL to check response code.
    isValid = getResponseCode(url);
    
    //Print message based on value of isValid which Is returned by getResponseCode function.
    if (isValid) {
     System.out.println("Valid Link:" + url);
     System.out.println("----------XXXX-----------XXXX----------XXXX-----------XXXX----------");
     System.out.println();
    } else {
     System.out.println("Broken Link ------> " + url);
     System.out.println("----------XXXX-----------XXXX----------XXXX-----------XXXX----------");
     System.out.println();
    }
   } else {    
    //If <a> tag do not contain href attribute and value then print this message
    System.out.println("String null");
    System.out.println("----------XXXX-----------XXXX----------XXXX-----------XXXX----------");
    System.out.println();
    continue;
   }
  }
  */
  Testing_Pack readXlsx = new Testing_Pack();
  readXlsx.readXLSXFile("C:/links.xlsx");	
  driver.close();
  driver.quit();
 }
 
	public void readXLSXFile(String fileName) {
		InputStream XlsxFileToRead = null;
		XSSFWorkbook workbook = null;
		try {
			XlsxFileToRead = new FileInputStream(fileName);
			
			//Getting the workbook instance for xlsx file
			workbook = new XSSFWorkbook(XlsxFileToRead);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//getting the first sheet from the workbook using sheet name. 
		// We can also pass the index of the sheet which starts from '0'.
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		XSSFRow row;
		XSSFCell cell;
		
		//Iterating all the rows in the sheet
		Iterator rows = sheet.rowIterator();

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			
			//Iterating all the cells of the current row
			Iterator cells = row.cellIterator();
			boolean isValid = false;

			while (cells.hasNext()) {
				cell = (XSSFCell) cells.next();
				String Urllist = cell.getStringCellValue();
				  if (Urllist != null)
				   {
				    
				    //Call getResponseCode function for each URL to check response code.
				    isValid = getResponseCode(Urllist);
				    
				    //Print message based on value of isValid which Is returned by getResponseCode function.
				    if (isValid) {
				     System.out.println("Valid Link:" + Urllist);
				     System.out.println("----------XXXX-----------XXXX----------XXXX-----------XXXX----------");
				     System.out.println();
				    } else {
				     System.out.println("Broken Link ------> " + Urllist);
				     System.out.println("----------XXXX-----------XXXX----------XXXX-----------XXXX----------");
				     System.out.println();
				    }
				   } else {    
				    //If <a> tag do not contain href attribute and value then print this message
				    System.out.println("String null");
				    System.out.println("----------XXXX-----------XXXX----------XXXX-----------XXXX----------");
				    System.out.println();
				    continue;
				   }
				  }
				

				/*
				if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
					System.out.print(cell.getStringCellValue() + " ");
				} else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
					System.out.print(cell.getNumericCellValue() + " ");
				} else if (cell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
					System.out.print(cell.getBooleanCellValue() + " ");

				} else { // //Here if require, we can also add below methods to
							// read the cell content
							// XSSFCell.CELL_TYPE_BLANK
							// XSSFCell.CELL_TYPE_FORMULA
							// XSSFCell.CELL_TYPE_ERROR
				}
				
				*/
			}
			System.out.println();
			try {
				XlsxFileToRead.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}

 //Function to get response code of link URL.
 //Link URL Is valid If found response code = 200.
 //Link URL Is Invalid If found response code = 404 or 505.
 public static boolean getResponseCode(String chkurl) {
  boolean validResponse = false;
  try {   
   //Get response code of URL
   HttpResponse urlresp = new DefaultHttpClient().execute(new HttpGet(chkurl));
   int resp_Code = urlresp.getStatusLine().getStatusCode();
   System.out.println("Response Code Is : "+resp_Code);
   if ((resp_Code == 404) || (resp_Code == 505)) {
    validResponse = false;
   } else {
    validResponse = true;
   }
  } catch (Exception e) {

  }
  return validResponse;
 }
}