package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.helpers.DriverHelper;
import org.example.model.User;
import org.example.page.objects.LoginPage;
import org.example.page.objects.RegisterPage;
import org.example.steps.UsersSteps;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static org.example.utils.Utils.*;

public class RegisterTest {

    private WebDriver driver;
    private String email;
    private String password;
    private String name;


    @Before
    public void startUp() throws IOException {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(URL_BURGERS)
                .setContentType(ContentType.JSON)
                .build();

        RestAssured.config = RestAssured
                .config()
                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails());


        WebDriverManager.chromedriver().setup();
        driver = DriverHelper.initDriver();

        email = RandomStringUtils.randomAlphabetic(6) + "@mail.ru";
        password = RandomStringUtils.randomAlphabetic(6);
        name = RandomStringUtils.randomAlphabetic(6);

    }

    @Test
    @DisplayName("После успешной регистрации открывается страница авторизации")
    @Description("Проверка, что URL текущей страницы равен URL страницы логина" +
            "после заполнения всех обязательных полей и клика на кнопку регистрации")
    public void loginPageOpensWhenRegistrationCompletedTest() {
        driver.get(URL_BURGERS_REGISTER);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.setName(name);
        registerPage.setEmail(email);
        registerPage.setPassword(password);

        registerPage.clickRegisterButton();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoginButton();

        Assert.assertEquals(URL_BURGERS_LOGIN, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Ошибка Некорректный пароль появляется, если пароль слишком короткий")
    @Description("Проверка, что при вводе пароля длиной 5 символов " +
            "появляется сообщение об ошибке Некорректный пароль")
    public void errorOccursWhenPasswordIsTooShortTest() {
        driver.get(URL_BURGERS_REGISTER);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.setName(name);
        registerPage.setEmail(email);
        registerPage.setPassword(RandomStringUtils.randomAlphabetic(5));

        registerPage.clickRegisterButton();
        registerPage.waitForError();
        String actualMessageError = registerPage.getTextOfError();
        String expectedMessageError = "Некорректный пароль";

        Assert.assertEquals(expectedMessageError, actualMessageError);
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
