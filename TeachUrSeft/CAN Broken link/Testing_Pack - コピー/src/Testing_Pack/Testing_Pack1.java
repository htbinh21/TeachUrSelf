package Testing_Pack1;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileWriter;

public class Testing_Pack1 
{
 public static void main(String[] args) throws IOException 
 {

	 System.setProperty("webdriver.gecko.driver", "E:\\geckodriver-v0.11.1-win64\\geckodriver.exe");
     WebDriver driver = new FirefoxDriver();
     driver.get("https://roudou.zead.jp/Account/Login?"); 	
	 driver.findElement(By.id("Email")).sendKeys("admin@gmail.com");
	 driver.findElement(By.id("Password")).sendKeys("Splus@12345");
	 driver.findElement(By.name("RememberMe")).click();
	 driver.findElement(By.xpath("//*[contains(text(),'ログイン')]")).click();

     Testing_Pack1 readXlsx = new Testing_Pack1();
     readXlsx.readXLSXFile("C:/links.xlsx");	
     driver.quit();
 }
 
	public void readXLSXFile(String fileName) 
	{
		InputStream XlsxFileToRead = null;
		XSSFWorkbook workbook = null;
		try 
		{
			XlsxFileToRead = new FileInputStream(fileName);
			//Getting the workbook instance for xlsx file
			workbook = new XSSFWorkbook(XlsxFileToRead);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		//getting the first sheet from the workbook using sheet name. 
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		XSSFRow row;
		XSSFCell cell;
		
		//Run all the rows in the sheet
		Iterator rows = sheet.rowIterator();
		while (rows.hasNext())
		{
			row = (XSSFRow) rows.next();
		
		//Run all the cells of the current row
	
			     boolean isValid = false;
                 cell = (XSSFCell) row.getCell(0);
				 String Urllist = cell.getStringCellValue();
				  if (Urllist != null)
				   {
				    
				    //Call getResponseCode function for each URL to check response code.
				    isValid = getResponseCode(Urllist);
				    
				    //Print message based on value of isValid which Is returned by getResponseCode function.
				    if (isValid) 
				    {
				     System.out.println("Valid Link:" + Urllist);
				     System.out.println();
				    } 
				    else 
				    {
				     System.out.println("Broken Link ------> " + Urllist);
				     System.out.println();
				     WriteNGresult(Urllist);	    
				     
				    }
				   } 
				  else 
				  {    
				    System.out.println("String null");
				    System.out.println();
				    continue;
				  }
				
		}
			System.out.println();
			try 
			{
				XlsxFileToRead.close();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		
	}
	//Write result NG into file 
 
	public static void WriteNGresult(String url)
	  {
		try
		{
		FileWriter writer = new FileWriter("C:/NGLink.txt", true);
        writer.write(url);
        writer.write("\r\n");   // write new line
        writer.close();
		}
		catch (IOException e) {
            e.printStackTrace();
        }
	  }


 //Function to get response code of link URL.
 //Link URL Is valid If found response code = 200.
 //Link URL Is Invalid If found response code = 404 or 505.
     public static boolean getResponseCode(String chkurl) 
     {
       boolean validResponse = false;
       
       try 
       {   
       //Get response code of URL
      HttpResponse urlresp = new DefaultHttpClient().execute(new HttpGet(chkurl));
      int resp_Code = urlresp.getStatusLine().getStatusCode();
      System.out.println("Response Code Is : "+resp_Code);
      if ((resp_Code == 404) || (resp_Code == 505)) 
      {
         validResponse = false;
         } 
      else 
         {
        validResponse = true;
          }
         } catch (Exception e) 
       {

       }
       return validResponse;
 }
     
}