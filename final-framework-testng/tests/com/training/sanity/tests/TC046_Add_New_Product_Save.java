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

public class TC046_Add_New_Product_Save {
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
	@Test     // UNF_078 - To verify whether application allows admin to create multiple category & add product on the created category
	public void validLoginTest() throws InterruptedException {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		Thread.sleep(3000);
        WebElement product=driver.findElement(By.xpath("//i[@class='fa fa-tags fa-fw']"));
		Actions act =new Actions(driver);
		act.moveToElement(product).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/nav/ul/li[2]/ul/li[2]/a")).click(); // Go to product page
        System.out.println("Product page should open");
        driver.findElement(By.xpath("//i[@class='fa fa-plus']")).click(); //As per discussed with Amit, my IP is not whitelisted so change the script accordingly to add new product 
        driver.findElement(By.id("input-name1")).sendKeys("Blazers(3-8)"); 
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/form/div/div[1]/div/div/div[2]/div/div/div[3]/div[2]/p/br")).sendKeys("Blazers for Kiran");
        driver.findElement(By.id("input-meta-title1")).sendKeys("Meta tag for Kiran");
        driver.findElement(By.id("input-meta-description1")).sendKeys("Meta Discription for Kiran");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()='Data']")).click();
        driver.findElement(By.id("input-model")).sendKeys("1223");
        WebElement inputField = driver.findElement(By.id("input-model"));
        inputField.sendKeys(Keys.TAB);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.xpath("//i[@class='fa fa-check-circle']")).isDisplayed();
        System.out.println("Successfully able to add the Product");
        //driver.close();  
	
	}
}
