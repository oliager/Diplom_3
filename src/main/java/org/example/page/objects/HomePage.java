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

    //локатор кнопки Войти в аккаунт на главной
    public static final By BUTTON_LOGIN= By.xpath(".//button[text()='Войти в аккаунт']");

    public static final By SECTION_BUNS = By.xpath(".//span[text()='Булки']/parent::div");
    public static final By SECTION_SOUSES = By.xpath(".//span[text()='Соусы']/parent::div");
    public static final By SECTION_FILLINGS = By.xpath(".//span[text()='Начинки']/parent::div");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        waitDriver = new WebDriverWait(driver, Utils.EXPLICIT_WAIT_3SEC);
    }

    public void clickOnSection(By locator){
        driver.findElement(locator).click();
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
