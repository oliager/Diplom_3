package org.example.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;

public class DriverHelper {

    public static WebDriver initDriver() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/browser.properties"));
        String browserProperty = properties.getProperty("testBrowser");
        System.out.println("browserProperty = " + browserProperty);
        BrowserType browserType = BrowserType.valueOf(browserProperty);

        WebDriver driver;
        switch (browserType) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case YANDEX:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-gpu");
                driver = new ChromeDriver(options);
                break;
            default:
                throw new RemoteException("Browser undefined");
        }

        return driver;
    }

    enum BrowserType {
        CHROME,
        YANDEX
    }
}
