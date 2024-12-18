package TestScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseTest;
import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.SearchResultsPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class ProductPageScript extends BaseTest{
    WebDriver driver;
    HomePage homePage;
    SearchResultsPage searchResultsPage;
    ProductPage productPage;

    @BeforeMethod
    public void setup() {
       
        // Initialize WebDriver
        driver = new ChromeDriver();
driver.manage().window().maximize();
        // Open the Flipkart homepage
        driver.get("https://www.flipkart.com");

        // Initialize HomePage, SearchResultsPage, and ProductPage objects
        homePage = new HomePage(driver);
        searchResultsPage = new SearchResultsPage(driver);

        // Close the login popup
        homePage.closeLoginPopup();

        // Search for "laptop"
        homePage.searchForProduct("laptop");

        // Wait for search results to be displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='WOvzF4']")));

        // Click on the first product in the search results
        searchResultsPage.clickProductByIndex(0);
        
        String windowhandle = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
        // Initialize ProductPage object
        productPage = new ProductPage(driver);
    }

    @Test
    public void testProductDetails() throws InterruptedException {
        // Print the product details
        productPage.printProductDetails();

        // Example assertions (adjust based on actual product details)
        assert productPage.getProductTitle().contains("Laptop");
        assert productPage.getProductPrice().contains("₹");
        assert productPage.getProductRating().length() > 0;
        Thread.sleep(2000);
    }

    @Test
    public void testAddToCart() throws InterruptedException {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,3700);");
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='QqFHMw vslbG+ In9uk2']")));
	    
         // Click the "Add to Cart" button
         productPage.clickAddToCart();
         Thread.sleep(2000);

        System.out.println("Product added to cart successfully.");
    }
    @Test
    public void clickHeaderCartButton() {
    	
         // Click the "Header Cart" button
         productPage.clickHeaderCartIcon();

        System.out.println("User lands on cart page.");
    }

    @AfterMethod
    public void teardown() {
        // Close the browser after the test
        driver.quit();
    }
}


