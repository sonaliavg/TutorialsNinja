package MainTestScripts;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.util.Assert;

import Base.BaseTest;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.SearchResultsPage;

public class Order extends BaseTest{
	WebDriver driver;
    HomePage homePage;
    SearchResultsPage searchResultsPage;
    ProductPage productPage;
    CartPage cartpage;
    CheckoutPage checkout; 
    
    @BeforeClass
   	public void setup() {
    
   		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
   		HomePage homePage = new HomePage();
   	}
    @Test(priority=1)
	public void verifySearch() {
    	homePage.testSearchFunctionality(prop.getProperty("searchKeyword"));
		
	}
    @Test(priority=2)
   	public void clickFirstProduct() {
    	homePage.testSearchFunctionality(prop.getProperty("searchKeyword"));
    	searchResultsPage.clickProductByIndex(0);
    	String windowhandle = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
   	}
    @Test(priority=3)
   	public void VerifyProductDetails() {
    	productPage.getProductTitle();
    	productPage.getProductPrice();
    	productPage.getProductRating();
    	productPage.getProductDescription();
    	productPage.getDeliveryInfo();
    	productPage.printProductDetails();
    	
		}
    @Test(priority=4)
   	public void AddProductToCart() {
    	productPage.clickAddToCart();
   	}
    @Test(priority=5)
   	public void goToCartPage() {
    	productPage.clickHeaderCartIcon();
   	}
    @Test(priority=5)
   	public void verifyCartPage() {
    	cartpage.printCartDetails();
    	cartpage.applyDiscount("discountCode");
   	}
    
    @Test(priority=6)
   	public void goToCheckoutPage() {
    	cartpage.proceedToCheckout();
   	}
    
    @Test(priority=7)
   	public void testCheckoutProcess() {
    	checkout.addNewAddress("1234 Main St", "Mumbai", "400001", "9876543210");
    	checkout.selectPaymentMethod();
        System.out.println("Total Price: ₹" + checkout.getOrderTotalPrice());
    }

@AfterClass
public void teardown() {
    // Close the browser after the test
    driver.quit();
}
}
    

    
 
   

