package com.tutorialninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;

public class Search extends Base {
	public Search() {
		super();
	}
	
	WebDriver driver;

	@BeforeMethod
	public void setup()  {
		
		driver = initializeBrowserOpenApplicationURL(prop.getProperty("browser"));
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void VerifySearchwithValidProduct() {
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));
		driver.findElement(By.xpath("//button[contains(@class,'btn btn-default btn-lg')]")).click();
		driver.findElement(By.linkText("HP LP3065"));
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(),
				"Valid product in not displed in search results");
	}

	@Test(priority =  2)
	public void VerifySearchWithinvalidProduct() {
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidproduct"));
		driver.findElement(By.xpath("//button[contains(@class,'btn btn-default btn-lg')]")).click();

		String actualMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualMessage, dataProp.getProperty("noProductInSearchInresults"),
				"No product search in search result");

	}
	@Test(priority = 3)
	public void VerifySearchWithoutAnySearchingProduct() {
		driver.findElement(By.name("search")).sendKeys("");
		
		
	}

}
