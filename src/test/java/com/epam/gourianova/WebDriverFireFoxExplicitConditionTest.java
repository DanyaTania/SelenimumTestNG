package com.epam.gourianova;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class WebDriverFireFoxExplicitConditionTest {
    private WebDriver driver;
    private static WebElement waitForElementLocatedBy ( WebDriver driver, By by ) {
        return new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds()).until(ExpectedConditions.presenceOfElementLocated(by));
    }
    @BeforeMethod(alwaysRun = true)
    public void browserSetup () {
        driver = new FirefoxDriver();
    }
    @Test(description = "Just first test..")
    public void commonSearchTermTestResultsNotEmpty () {
        driver.get("https://addons.mozilla.org/en-US/firefox/addon/selenium-ide/");
      WebElement until = new WebDriverWait(driver,
                Duration.ofSeconds(10).getSeconds()).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//span[contains(@class, 'AddonTitle-author')]")));
        WebElement searchInput = waitForElementLocatedBy(driver, By.id("AutoSearchInput-q"));
        searchInput.sendKeys("java");
        WebElement search = driver.findElement(By.xpath("//*[@class='AutoSearchInput-submit-button']"));
        search.click();

        new WebDriverWait(driver, Duration.ofSeconds(15).getSeconds()).until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.xpath
                        ("//a[contains(@class, 'SearchResult-link')]")));

        List<WebElement> searchResults = driver.findElements(By.xpath
                ("//a[contains(@class, 'SearchResult-link')]"));

        System.out.println("Search results number for requested term: " + searchResults.size());
        Assert.assertTrue(searchResults.size() > 0, "Search results are empty!");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown () {
        driver.quit();
        driver = null;
    }
}
