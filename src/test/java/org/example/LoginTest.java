package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.page.objects.HomePage;
import org.example.page.objects.LoginPage;
import org.example.page.objects.ProfilePage;
import org.example.page.objects.RegisterPage;
import org.example.utils.Utils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    private WebDriver driver;

    private String email;
    private String password;

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

    @Test
    public void loginOnClickButtonLoginAccountTest() {
        driver.get(Utils.URL_BURGERS);

        HomePage homePage = new HomePage(driver);
        homePage.clickButtonLoginAccount();

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
        //driver.quit();
    }
}
