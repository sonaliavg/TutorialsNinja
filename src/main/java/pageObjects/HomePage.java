package pageObjects;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	public WebDriver driver;
	HomePage homePage;
	
	By loginButton = By.xpath("//span[normalize-space()='Login']");
	By otpButton = By.xpath("//button[normalize-space()='Request OTP']");
    // Locator for the close button of the login popup
     By closeButton = By.xpath("//button[contains(text(),'✕')]");
     By HeaderLoginButton = By.xpath("//span[normalize-space()='Login']");
    // Locator for the search bar
     By searchBox = By.name("q");

    // Locator for the search button 
     By searchButton = By.cssSelector("button[type='submit']");

   public HomePage() {
			PageFactory.initElements(driver, this);
   }
		
	// Action to close the login popup
    public void closeLoginPopup() {
        try {
            WebElement closeBtn = driver.findElement(closeButton);
            if (closeBtn.isDisplayed()) {
                closeBtn.click();
            }
        } catch (Exception e) {
            System.out.println("No login popup found.");
        }
    }

    // Action to perform search
    public void searchForProduct(String product) {
        WebElement searchField = driver.findElement(searchBox);
        searchField.clear();
        searchField.sendKeys(product);  
        driver.findElement(searchButton).click();  
    }

    // Method to get the title of the page
    public String getPageTitle() {
    	HomePage homePage = new HomePage();
    	String tittle = driver.getTitle();
		System.out.println("title is " + tittle);
        return driver.getTitle();
    }

    public void testSearchFunctionality(String string) {
       

        // Search for a product (e.g., laptop)
        homePage.searchForProduct("searchKeyword");

        // Wait for the page to load after search (using WebDriverWait)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productList)));
        System.out.println("Search page loaded successfully " );
        // Verify the page title contains the search query
        String pageTitle = homePage.getPageTitle();
        System.out.println("Test passed: " + pageTitle);
    }


	

}

