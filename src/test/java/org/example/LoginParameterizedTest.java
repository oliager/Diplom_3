package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.helpers.DriverHelper;
import org.example.model.User;
import org.example.page.objects.LoginPage;
import org.example.page.objects.ProfilePage;
import org.example.steps.UsersSteps;
import org.example.utils.Utils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;


@RunWith(Parameterized.class)
public class LoginParameterizedTest {

    private WebDriver driver;
    private UsersSteps usersSteps = new UsersSteps();

    private String email;
    private String password;

    private String name;

    private final String url;
    private final By locatorOfButton;

    public LoginParameterizedTest(String url, By locatorOfButton) {
        this.url = url;
        this.locatorOfButton = locatorOfButton;
    }
    @Before
    public void startUp() throws IOException {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(Utils.URL_BURGERS)
                .setContentType(ContentType.JSON)
                .build();

        WebDriverManager.chromedriver().setup();

        driver = DriverHelper.initDriver();

        email = RandomStringUtils.randomAlphabetic(6) + "@mail.ru";
        password = RandomStringUtils.randomAlphabetic(6);
        name = RandomStringUtils.randomAlphabetic(6);

        User user = new User(email, password, name);

        usersSteps.create(user)
                .statusCode(200);
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
    @DisplayName("После клика на кнопку(locatorOfButton) и ввода данных, " +
            "в Профиле заполнен Логин")
    public void loginOnClickButtonTest() {
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
        driver.quit();
        UsersSteps usersSteps = new UsersSteps();
        User user = new User(email, password, name);

        String accessToken = usersSteps.login(user)
                .extract().path("accessToken");
        if (accessToken!=null) {
            usersSteps.delete(accessToken);
        }
    }
}
