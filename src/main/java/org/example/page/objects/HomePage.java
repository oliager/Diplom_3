package org.example.page.objects;

import org.example.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.example.utils.Utils.BUTTON_PROFILE;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait waitDriver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        waitDriver = new WebDriverWait(driver, Utils.EXPLICIT_WAIT_3SEC);
    }

    //метод для ожидания появления элемента
    public void waitForElementVisible(By locator) {
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public boolean isCurrent(By locator){
        try {
            waitDriver.until(ExpectedConditions.attributeContains(locator, "class", "tab_tab_type_current__2BEPc"));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
