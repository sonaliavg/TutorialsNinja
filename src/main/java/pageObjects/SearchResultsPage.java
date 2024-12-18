package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;



import java.time.Duration;
import java.util.List;

public class SearchResultsPage{
	public static WebDriver driver;
	
	static SearchResultsPage SearchResultsPage;
    // Locator for the list of products
    private By productList = By.xpath("//div[@class='DOjaWF gdgoEp']");
    
    // Locator for product names within the product list
    private By productName = By.xpath("//div[@class='KzDlHZ']");

    // Locator for product prices within the product list
    private By productPrice = By.xpath("//div[@class='Nx9bqj _4b5DiR']");

    // Locator for the next page button (if pagination is available)
    private By nextPageButton = By.xpath("//span[normalize-space()='Next']");

    public SearchResultsPage() {
		
		PageFactory.initElements(driver, this);
	}
	// Get the list of product names from the search results page
    public List<WebElement> getProductNames() {
        return driver.findElements(productName);
    }

    // Get the list of product prices from the search results page
    public List<WebElement> getProductPrices() {
        return driver.findElements(productPrice);
    }

    // Get the total number of products on the page
    public int getProductCount() {
        return getProductNames().size();
    }

    // Get the name of a specific product by index
    public String getProductNameByIndex(int index) {
        return getProductNames().get(index).getText();
    }

    // Get the price of a specific product by index
    public String getProductPriceByIndex(int index) {
        return getProductPrices().get(index).getText();
    }

    // Click on a specific product by index (to view the product details)
    public void clickProductByIndex(int index) {
        getProductNames().get(index).click();
    }

    // Check if the "Next Page" button is available
    public boolean isNextPageButtonAvailable() {
        return driver.findElements(nextPageButton).size() > 0;
    }

    // Click on the "Next Page" button to load the next page of search results
    public void clickNextPageButton() {
        if (isNextPageButtonAvailable()) {
            driver.findElement(nextPageButton).click();
        } else {
            System.out.println("No next page available");
        }
    }

    // Method to print all the products and their prices on the current page
    public void printAllProductNamesAndPrices() {
        List<WebElement> names = getProductNames();
        List<WebElement> prices = getProductPrices();

        for (int i = 0; i < names.size(); i++) {
            System.out.println("Product: " + names.get(i).getText() + " | Price: " + prices.get(i).getText());
        }
    }
    public static void testSearchResults() {
    	
        // Print the total number of products and their details
        System.out.println("Total products found: " + SearchResultsPage.getProductCount());
        SearchResultsPage.printAllProductNamesAndPrices();

        // Test clicking on the first product
        SearchResultsPage.clickProductByIndex(0);

        String windowhandle = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
        // Wait for the product details page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='VU-ZEz']")));

        // You can add additional assertions here based on the product details page
        System.out.println("Navigated to product details page.");
    }
    
}

