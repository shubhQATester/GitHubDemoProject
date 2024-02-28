package com.tutorialninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;

public class Login extends Base{
	public Login() {
		super();
	}
	
	WebDriver driver;
	@BeforeMethod
	public void Setup() {
		
		driver=initializeBrowserOpenApplicationURL(prop.getProperty("browser"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();	
	}  
	
	@Test(priority=1)
	public void verifyLoginWithValidCredential() {
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPass"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),"Edit your account information is not Displayed");
}
	@Test(priority=2)
	public void verifyWithInvalidCredential() {
		driver.findElement(By.id("input-email")).sendKeys(dataProp.getProperty("invalidEmail"));
		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualWarningMessage=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage ="Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	}
	@Test (priority=3)
	public void verifyLoginWithValidMailInvalidPassword() {
		
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualWarningMessage=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage ="Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	}
	
	@Test(priority=4)
	public void verifyLoginWithInvalidMailAndValidPassword() {
	
		driver.findElement(By.id("input-email")).sendKeys(dataProp.getProperty("invalidEmail"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPass"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualWarningMessage=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage ="Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	}
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}