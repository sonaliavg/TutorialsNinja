package TestScripts;


import org.testng.annotations.Test;

import Base.BaseTest;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObjects.HomePage;
import pageObjects.SearchResultsPage;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import org.openqa.selenium.By;

public class SearchResultsScript extends BaseTest{
    WebDriver driver;
    HomePage homePage;
    SearchResultsPage searchResultsPage;

    public void setup() {
        
   		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
   		HomePage homePage = new HomePage(driver);

        // Search for "laptop"
        homePage.searchForProduct(prop.getProperty("searchKeyword"));

        // Wait for search results to be displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='WOvzF4']")));
    }
    @AfterMethod
    public void teardown() {
        // Close the browser after test
        driver.quit();
    }
}


