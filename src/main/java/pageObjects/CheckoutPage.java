package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import java.time.Duration;

public class CheckoutPage{

	public WebDriver driver;

    // Locators for checkout page elements
    private By LoginSection = By.xpath("(//div[@class='Pg+ADy AWaTLw'])[1]");
    private By Enteremailorphone = By.xpath("//input[@type='text']");
    private By LoginButton = By.xpath("//button[@type='submit']");
    private By Enterotp = By.xpath("//input[@class='r4vIwl zgwPDa Jr-g+f']");
    private By deliveryAddressSection = By.xpath("//span[normalize-space()='Delivery Address']");
    private By addNewAddressButton = By.xpath("//div[@class='_2vQwZw']"); 
    private By NameField = By.xpath("//input[@name='name']");
    private By addressField = By.xpath("//textarea[@name='addressLine1']");
    private By cityField = By.xpath("//input[@name='city']");
    private By pinCodeField= By.xpath("//input[@name='pincode']");
    private By stateField= By.xpath("//select[@name='state']");
    private By addressHomeType = By.xpath("//input[@id='HOME']");
    private By addressWorkType = By.xpath("//input[@id='WORK']");
    private By phoneNumberField = By.xpath("//input[@name='phone']");
    private By saveAddressButton = By.xpath("//button[normalize-space()='Save and Deliver Here']");
    private By paymentMethodSection = By.xpath("//span[normalize-space()='Payment Options']");
    private By selectPaymentMethod = By.xpath("//input[@id='COD']");
    private By captcha = By.xpath("//input[@name='captcha']");
    private By placeOrderButton = By.xpath("//button[@type='button']");
    private By totalPrice = By.xpath("//div[@class='uJ4ZKF']");

    // Constructor to initialize WebDriver
    public CheckoutPage() {
        
        PageFactory.initElements(driver, this);
    }

    // Method to verify that the checkout page is loaded
    public boolean isCheckoutPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryAddressSection));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

       
    // Method to add a new delivery address
    public void addNewAddress(String address, String city, String pincode, String phoneNumber) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click on Add New Address button
        WebElement addNewAddressBtn = driver.findElement(addNewAddressButton);
        addNewAddressBtn.click();

        // Fill in the new address details
        wait.until(ExpectedConditions.visibilityOfElementLocated(addressField));
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(pinCodeField).sendKeys(pincode);
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);

        // Save the address
        driver.findElement(saveAddressButton).click();
    }

    // Method to select the payment method
   
    public void enterCaptcha() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the payment section to be visible and select a payment option (e.g., Cash on Delivery)
        wait.until(ExpectedConditions.visibilityOfElementLocated(paymentMethodSection));
        WebElement captchafield = driver.findElement(captcha);
        captchafield.click();
    }
/*
    // Method to place the order
    public void placeOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the Place Order button to be visible and click it
        wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
        WebElement placeOrderBtn = driver.findElement(placeOrderButton);
        placeOrderBtn.click();
    }

    // Method to get the order confirmation message
    public String getOrderConfirmationMessage() {
        By orderConfirmationMessage = By.xpath("//div[contains(text(),'Your order has been placed successfully')]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmationMessage));
        return confirmationMessage.getText();
    }*/

	public void selectPaymentMethod() {
		
		 {
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		        // Wait for the payment section to be visible and select a payment option (e.g., Cash on Delivery)
		        wait.until(ExpectedConditions.visibilityOfElementLocated(paymentMethodSection));
		        WebElement paymentMethodBtn = driver.findElement(selectPaymentMethod);
		        paymentMethodBtn.click();
		    }	
	}

	public double getOrderTotalPrice() {
        WebElement totalPriceElement = driver.findElement(totalPrice);

        // Get the text from the total price element and remove unwanted characters like '₹' and commas
        String priceText = totalPriceElement.getText().replaceAll("[^0-9.]", "");

        // Convert the cleaned price text to a double and return it
        return Double.parseDouble(priceText);
    }
}
