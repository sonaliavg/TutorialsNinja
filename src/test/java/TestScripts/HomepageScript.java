package TestScripts;

import org.testng.annotations.Test;

import Base.BaseTest;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import org.openqa.selenium.By;
import pageObjects.HomePage;

public class HomepageScript extends BaseTest{
    WebDriver driver;
    HomePage homePage;

    
    
	public HomepageScript() {
		super();
	}
  
    @BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		homePage.getPageTitle();
	}
    
@AfterMethod
public void tearDown() throws InterruptedException {
Thread.sleep(2000);
	driver.quit();
}

//
//    @Test
//    public void testHomepageTitle() {
//        // Get the page title
//    	  homePage.getPageTitle();
//        // Validate the title of the homepage
//    	  //  assert title.equals("Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");  // Adjust this based on actual page title
//    	  //System.out.println("Homepage title is correct: " + title);
//    }
}


