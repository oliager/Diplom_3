package org.example.utils;

import org.openqa.selenium.By;

public class Utils {
    public static final String URL_BURGERS = "https://stellarburgers.education-services.ru";
    public static final String URL_BURGERS_REGISTER = URL_BURGERS + "/register";
    public static final String URL_BURGERS_LOGIN = URL_BURGERS + "/login";
    public static final String URL_BURGERS_FORGOT_PASSWORD = URL_BURGERS + "/forgot-password";


    public static final int EXPLICIT_WAIT_3SEC = 3; //явное ожидание

    //локатор кнопки Войти в аккаунт на главной
    public static final By BUTTON_LOGIN= By.xpath(".//button[text()='Войти в аккаунт']");

    //локатор кнопки Личный Кабинет в хэдере
    public static final By BUTTON_PROFILE = By.xpath(".//p[text()='Личный Кабинет']/parent::a");

    //локатор кнопки Войти на странице регистрации
    public static final By BUTTON_REGISTER= By.xpath(".//a[text()='Войти']");

    //локатор кнопки Войти на странице Забыли пароль
    public static final By BUTTON_FORGOT_PASSWORD= By.xpath(".//a[text()='Войти']");

}
