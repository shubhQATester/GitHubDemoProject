package com.tutorialninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.utils.Utilities;

public class Register extends Base{
	public Register() {
		super();
	}
	
	WebDriver driver;
	
	@BeforeMethod	
	public void Setup() {
		
		driver  = initializeBrowserOpenApplicationURL(prop.getProperty("browser"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}
	
	@Test(priority=1)
	public  void verifyRegisterAccountWithmandatoryfield() {
		
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephone"));
		driver.findElement(By.id("input-password")).sendKeys("@SHubhzzzz");
		driver.findElement(By.id("input-confirm")).sendKeys("@SHubhzzzz");
		driver.findElement(By.xpath("//*[@name='agree']")).click();
		driver.findElement(By.xpath("//*[@value='Continue']")).click();
		String AcutalHeading=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(AcutalHeading,dataProp.getProperty("accountSuccefullyCreatedHeading"),"Account Account Success page is not displed");
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
} 
