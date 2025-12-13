package org.example.page.objects;

import org.example.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait waitDriver;

    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By authorizationForm = By.className("Auth_form__3qKeq");

    private final By emailField = By.xpath(".//label[text()='Email']/parent::div/input");
    private final By passwordField = By.xpath(".//input[@name='Пароль']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        waitDriver = new WebDriverWait(driver, Utils.EXPLICIT_WAIT_3SEC);
    }

    public void waitForLoginButton(){
        waitForElementVisible(loginButton);
    }

    public void waitForAuthorizationForm(){
        waitForElementVisible(authorizationForm);
    }

    //метод для ожидания появления элемента
    public void waitForElementVisible(By locator) {
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
