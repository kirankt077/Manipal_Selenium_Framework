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

public class TC044_Delete_Multiple_product {
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
	@Test     // UNF_044 - To Verify whether application allows the admin to delete multiple product from product list
	public void validLoginTest() throws InterruptedException {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		Thread.sleep(3000);
        WebElement product=driver.findElement(By.xpath("//i[@class='fa fa-tags fa-fw']"));
		Actions act =new Actions(driver);
		act.moveToElement(product).perform();
        driver.findElement(By.xpath("/html/body/div[1]/nav/ul/li[2]/ul/li[2]/a")).click();
        driver.findElement(By.id("input-name")).sendKeys("Blazer");
        driver.findElement(By.id("button-filter")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/form/div/table/tbody/tr[3]/td[1]/input")).click();
        driver.findElement(By.xpath("//i[@class='fa fa-trash-o']")).click();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        driver.findElement(By.xpath("//i[@class='fa fa-check-circle']")).isDisplayed();
        System.out.println("Successfully modified the selected product");
        //driver.close(); 
	}
}
