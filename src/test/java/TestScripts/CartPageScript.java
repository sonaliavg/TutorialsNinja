package TestScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseTest;
import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.SearchResultsPage;

import java.time.Duration;

public class CartPageScript extends BaseTest{

    WebDriver driver;
    HomePage homePage;
    SearchResultsPage searchResultsPage;
    ProductPage productPage;
    CartPage cartPage;

    @BeforeMethod
    public void setup() {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        // Open the Flipkart homepage
        driver.get("https://www.flipkart.com");
        
        // Close the login popup
        homePage.closeLoginPopup();

        // Search for a product (e.g., "laptop")
        homePage.searchForProduct("laptop");

        // Wait for the search results to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='WOvzF4']")));

        // Click on the first product in the search results
        searchResultsPage.clickProductByIndex(0);

        // Switch to the new window
        String windowhandle = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

      
    }

    @Test
    public void testAddToCart() {
        // Add product to cart
        productPage.clickAddToCart();
        System.out.println("Product added to cart successfully.");
    }

    @Test
    public void testGoToCart() {
        // Go to the cart page
    	productPage.clickHeaderCartIcon();
        
        // Assert that the cart page is opened
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='_8X-K8p']")));
        
        System.out.println("Navigated to the cart page successfully.");
    }

    @Test
    public void testViewCartDetails() {
        // Go to the cart page and print details
    	productPage.clickHeaderCartIcon();
        cartPage.printCartDetails();
    }

    @Test
    public void testApplyDiscount() {
        // Go to the cart page
    	productPage.clickHeaderCartIcon();
        
        // Apply a discount and verify if total price is reduced
        cartPage.applyDiscount("5% Unlimited Cashback on Flipkart Axis Bank Credit Card");
        
        // Get the total price after applying discount
        double finalPrice = cartPage.getTotalPrice();
        Assert.assertTrue(finalPrice < cartPage.getTotalPrice(), "Final price should be lower after applying discount.");
        
        System.out.println("Discount applied successfully.");
    }

    @Test
    public void testProceedToCheckout() {
        // Go to the cart page and proceed to checkout
    	productPage.clickHeaderCartIcon();
        cartPage.proceedToCheckout();
        
        System.out.println("User proceeded to checkout successfully.");
    }

    @AfterMethod
    public void teardown() {
        // Close the browser after the test
        driver.quit();
    }
}
