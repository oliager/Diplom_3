package org.example.page.objects;

import org.example.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait waitDriver;

    private final By buttonLoginAccount = By.xpath(".//button[text()='Войти в аккаунт']");


    public HomePage(WebDriver driver) {
        this.driver = driver;
        waitDriver = new WebDriverWait(driver, Utils.EXPLICIT_WAIT_3SEC);
    }

    public void clickButtonLoginAccount() {
        driver.findElement(buttonLoginAccount).click();
    }



}
