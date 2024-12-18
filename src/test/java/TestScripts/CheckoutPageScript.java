package TestScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseTest;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.SearchResultsPage;

public class CheckoutPageScript extends BaseTest{

    WebDriver driver;
    HomePage homePage;
    SearchResultsPage searchResultsPage;
    ProductPage productPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    

    // Set up WebDriver before each test
    @BeforeMethod
   	public void setup() {
    
   		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
   		HomePage homePage = new HomePage(driver);
   		//loginPage = homePage.naviageToLoginPage();
   		
   	
        // Initialize CheckoutPage object
        checkoutPage = new CheckoutPage(driver);
    }

    // Test method to fill in shipping details and select payment method
    @Test
    public void testCheckoutProcess() {
    	
        // Fill in shipping details
        checkoutPage.addNewAddress("1234 Main St", "Mumbai", "400001", "9876543210");
        
        // Select a payment method
        checkoutPage.selectPaymentMethod();
        
        // Print the total price
        System.out.println("Total Price: ₹" + checkoutPage.getOrderTotalPrice());
    }

    // Clean up and close the browser after each test
    @AfterMethod
    public void teardown() {
        // Close the browser after the test
        driver.quit();
    }
}
