package org.example.page.objects;

import org.example.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.example.utils.Utils.BUTTON_PROFILE;

public class ProfilePage {

    private final WebDriver driver;
    private final WebDriverWait waitDriver;

    private final By fieldLogin = By.xpath(".//label[text()='Логин']/parent::div/input");



    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        waitDriver = new WebDriverWait(driver, Utils.EXPLICIT_WAIT_3SEC);
    }

    public void clickButtonProfile() {
        driver.findElement(BUTTON_PROFILE).click();
    }

    //метод для ожидания появления элемента
    public void waitForElementVisible(By locator) {
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public String getTextOfLogin(){
        waitForElementVisible(fieldLogin);
        return driver.findElement(fieldLogin).getAttribute("value");
    }
}
