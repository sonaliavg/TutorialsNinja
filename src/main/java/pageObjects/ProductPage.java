package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;



public class ProductPage{
	public WebDriver driver;

    // Locator for the product title (product name)
     By productTitle = By.xpath("//span[@class='VU-ZEz']");

    // Locator for the product price
     By productPrice = By.xpath("//div[@class='hl05eU']");

    // Locator for the "Add to Cart" button
     By addToCartButton = By.xpath("//button[@class='QqFHMw vslbG+ In9uk2']");

    // Locator for the product rating
     By productRating = By.xpath("//div[@class='XQDdHH']");

    // Locator for the product description
     By productDescription = By.xpath("//div[@class='_3Fm-hO']");

    // Locator for the product specifications section
     By productSpecifications = By.xpath("//div[@class='_8tSq3v']");

    // Locator for the delivery information section
     By deliveryInfo = By.xpath("//div[@class='row xO1qhs']");

    By cartIcon=By.xpath("//span[normalize-space()='Cart']");
   
  public ProductPage() {
    	  
    			
    			PageFactory.initElements(driver, this);
    			
    		}

	// Method to get the product title
    public String getProductTitle() {
        return driver.findElement(productTitle).getText();
    }

    // Method to get the product price
    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    // Method to click the "Add to Cart" button
    public void clickAddToCart() {
    	
        driver.findElement(addToCartButton).click();
    }
    
public void clickHeaderCartIcon() {
    	
        driver.findElement(cartIcon).click();
    }
    

    // Method to get the product rating
    public String getProductRating() {
        return driver.findElement(productRating).getText();
    }

    // Method to get the product description
    public String getProductDescription() {
        return driver.findElement(productDescription).getText();
    }

    // Method to get the product specifications
    public String getProductSpecifications() {
        return driver.findElement(productSpecifications).getText();
    }

    // Method to get the delivery information
    public String getDeliveryInfo() {
        return driver.findElement(deliveryInfo).getText();
    }

    // Method to print all product details
    public void printProductDetails() {
        System.out.println("Product Title: " + getProductTitle());
        System.out.println("Product Price: " + getProductPrice());
        System.out.println("Product Rating: " + getProductRating());
        System.out.println("Product Description: " + getProductDescription());
        System.out.println("Product Specifications: " + getProductSpecifications());
        System.out.println("Delivery Information: " + getDeliveryInfo());
    }
}

