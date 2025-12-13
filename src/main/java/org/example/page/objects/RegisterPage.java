package org.example.page.objects;

import org.example.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    private final WebDriver driver;
    private final WebDriverWait waitDriver;


    //локатор для поля 'Имя'
    private final By nameField = By.xpath(".//label[text()='Имя']/parent::div/input");
    private final By emailField = By.xpath(".//label[text()='Email']/parent::div/input");
    private final By passwordField = By.xpath(".//input[@name='Пароль']");

    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By errorMessage =  By.className("input__error");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        waitDriver = new WebDriverWait(driver, Utils.EXPLICIT_WAIT_3SEC);
    }

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public void waitForError(){
        waitForElementVisible(errorMessage);
    }

    //метод для ожидания появления элемента
    public void waitForElementVisible(By locator) {
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String getTextOfError(){
        waitForElementVisible(errorMessage);
        return driver.findElement(errorMessage).getText();
    }
}
