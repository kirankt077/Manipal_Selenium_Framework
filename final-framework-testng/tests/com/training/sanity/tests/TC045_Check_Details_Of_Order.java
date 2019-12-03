package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC045_Check_Details_Of_Order {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		//driver.quit();
	}	

	@Test // UNF_045 - To Verify whether application allows admin to check the details of order placed by the user
	public void validLoginTest() throws InterruptedException {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		Thread.sleep(3000);
        WebElement product=driver.findElement(By.xpath("//i[@class='fa fa-shopping-cart fa-fw']"));
		Actions act =new Actions(driver);
		act.moveToElement(product).perform();
		
        driver.findElement(By.xpath("/html/body/div[1]/nav/ul/li[5]/ul/li[1]/a")).click();
        
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div[2]/form/div/table/tbody/tr[8]/td[8]/a[1]")).click();
       
        driver.findElement(By.xpath("[button-invoice']")).click();
        System.out.println("Invoice number should get displayed");

        //driver.close();  
	
	}
}
