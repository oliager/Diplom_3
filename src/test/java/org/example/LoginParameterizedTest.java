package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.page.objects.LoginPage;
import org.example.page.objects.ProfilePage;
import org.example.page.objects.RegisterPage;
import org.example.utils.Utils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class LoginParameterizedTest {

    private WebDriver driver;

    private String email;
    private String password;

    private final String url;
    private final By locatorOfButton;

    public LoginParameterizedTest(String url, By locatorOfButton) {
        this.url = url;
        this.locatorOfButton = locatorOfButton;
    }
    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        // драйвер для браузера Chrome
        driver = new ChromeDriver();

        driver.get(Utils.URL_BURGERS_REGISTER);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.setName(RandomStringUtils.randomAlphabetic(6));

        email = RandomStringUtils.randomAlphabetic(6) + "@mail.ru";
        registerPage.setEmail(email);

        password = RandomStringUtils.randomAlphabetic(6);
        registerPage.setPassword(password);

        registerPage.clickRegisterButton();

    }

    @Parameterized.Parameters
    public static Object[][] getLoginData() {
        return new Object[][] {
                {Utils.URL_BURGERS, Utils.BUTTON_LOGIN},
                {Utils.URL_BURGERS, Utils.BUTTON_PROFILE},
                {Utils.URL_BURGERS_REGISTER, Utils.BUTTON_REGISTER},
                {Utils.URL_BURGERS_FORGOT_PASSWORD, Utils.BUTTON_FORGOT_PASSWORD},
        };
    }

    @Test
    public void loginOnClickButtonLoginAccountTest() {
        driver.get(url);

        driver.findElement(locatorOfButton).click();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForAuthorizationForm();
        loginPage.setEmail(email);
        loginPage.setPassword(password);

        loginPage.clickLoginButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickButtonProfile();
        String actualLogin = profilePage.getTextOfLogin();

        Assert.assertEquals(email.toLowerCase(), actualLogin);

    }



    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }
}
