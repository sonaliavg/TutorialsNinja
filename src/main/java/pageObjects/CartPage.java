package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class CartPage {
	public WebDriver driver;
	ProductPage productPage;

    // Constructor
    public CartPage() {
        
        PageFactory.initElements(driver, this);
    }

    // Locate the cart page button in the header
    private By cartIcon = By.xpath("//a[@class='cart-icon']");
    private By EnterDeliveryAdddres = By.xpath("//button[normalize-space()='Enter Delivery Pincode']");
    
    
    // Locate the elements for cart details (such as product names, total price, etc.)
    private By cartItems = By.xpath("//div[@class='_8X-K8p']");
    private By totalPrice = By.xpath("//span[@class='total-price']");
    private By checkoutButton = By.xpath("//span[normalize-space()='Place Order']");
    
    
    public void testGoToCart() {
		// Go to the cart page
    	productPage.clickHeaderCartIcon();
        // Assert that the cart page is opened
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='_8X-K8p']")));
        
        System.out.println("Navigated to the cart page successfully.");
    }
    // Method to print the details of items in the cart
    public void printCartDetails() {
        WebElement cart = driver.findElement(cartItems);
        System.out.println("Cart contains: " + cart.getText());
        
        WebElement total = driver.findElement(totalPrice);
        System.out.println("Total price: " + total.getText());
    }

    // Method to apply a discount (this would typically involve interacting with a discount input field)
    public void applyDiscount(String discountCode) {
        // Assuming there's an input field for applying a discount
        WebElement discountField = driver.findElement(By.xpath("//div[@class='VU2A2V']"));
        discountField.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='AEyO3k diqXu5 hR3rL5 CpC9HS']")));

        driver.findElement(By.xpath("//div[normalize-space()='5% Unlimited Cashback on Flipkart Axis Bank Credit Card']")).click();
    }

    // Method to get the total price from the cart
    public double getTotalPrice() {
        WebElement total = driver.findElement(totalPrice);
        return Double.parseDouble(total.getText().replace("₹", "").trim());
    }

    // Method to proceed to checkout
    public void proceedToCheckout() {
        WebElement checkout = driver.findElement(checkoutButton);
        checkout.click();
    }
    
}
