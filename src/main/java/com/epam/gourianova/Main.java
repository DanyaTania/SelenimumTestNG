package com.epam.gourianova;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {

    public  static void main(String[] args) throws InterruptedException{
        WebDriver driver=new FirefoxDriver();
        driver.get("https://www.selenium.dev/documentation/webdriver/getting_started/install_drivers/");
        Thread.sleep(2000);
        driver.quit();
    }
}
