package org.example.page.objects;

import io.qameta.allure.Step;
import org.example.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait waitDriver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        waitDriver = new WebDriverWait(driver, Utils.EXPLICIT_WAIT_3SEC);
    }

    @Step ("Ожидание аттрибута, включающего таб")
    public boolean isCurrent(By locator){
        try {
            waitDriver.until(ExpectedConditions.attributeContains(locator, "class", "tab_tab_type_current__2BEPc"));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
