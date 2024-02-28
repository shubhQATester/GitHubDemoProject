package com.tutorialninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialninja.qa.utils.Utilities;

public class Base {
	
	WebDriver driver;
	 public Properties prop;
	 public Properties dataProp;
     public  Base ()  {
		prop =new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\config\\config.properties");
		dataProp=new Properties();
		File dataPropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\testdata\\testdata.properties");
		
		try {
		FileInputStream dataFis= new FileInputStream(dataPropFile); 
		dataProp.load(dataFis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		try {
		FileInputStream fis=new FileInputStream(propFile);
		prop.load(fis);
	}catch(Throwable e){
		e.printStackTrace();
	}
}
	
	public WebDriver initializeBrowserOpenApplicationURL(String BrowserName) {
	
		if(BrowserName.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if (BrowserName.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}else if (BrowserName.equalsIgnoreCase ("edge")) {
			driver=new EdgeDriver();
		}
		driver=new ChromeDriver();
		driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
    	
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get(prop.getProperty("url"));
		return driver;
	}
}