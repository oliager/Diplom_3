package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.page.objects.LoginPage;
import org.example.page.objects.RegisterPage;
import org.example.utils.Utils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisterTest {

    private WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        // драйвер для браузера Chrome
        driver = new ChromeDriver();
    }

    @Test
    public void loginPageOpensWhenRegistrationCompletedTest() {
        driver.get(Utils.URL_BURGERS_REGISTER);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.setName(RandomStringUtils.randomAlphabetic(6));
        registerPage.setEmail(RandomStringUtils.randomAlphabetic(6) + "@mail.ru");
        registerPage.setPassword(RandomStringUtils.randomAlphabetic(6));

        registerPage.clickRegisterButton();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoginButton();

        Assert.assertEquals(Utils.URL_BURGERS_LOGIN, driver.getCurrentUrl());
    }

    @Test
    public void errorOccursWhenPasswordIsTooShortTest() {
        driver.get(Utils.URL_BURGERS_REGISTER);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.setName(RandomStringUtils.randomAlphabetic(6));
        registerPage.setEmail(RandomStringUtils.randomAlphabetic(6) + "@mail.ru");
        registerPage.setPassword(RandomStringUtils.randomAlphabetic(5));

        registerPage.clickRegisterButton();
        registerPage.waitForError();
        String actualMessageError = registerPage.getTextOfError();
        String expectedMessageError = "Некорректный пароль";

        Assert.assertEquals(expectedMessageError, actualMessageError);
    }

    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }
}
