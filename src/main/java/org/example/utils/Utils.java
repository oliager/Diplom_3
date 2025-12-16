package org.example.utils;

import org.openqa.selenium.By;

public class Utils {
    public static final String URL_BURGERS = "https://stellarburgers.education-services.ru";
    public static final String URL_BURGERS_REGISTER = URL_BURGERS + "/register";
    public static final String URL_BURGERS_LOGIN = URL_BURGERS + "/login";
    public static final String URL_BURGERS_FORGOT_PASSWORD = URL_BURGERS + "/forgot-password";


    public static final int EXPLICIT_WAIT_3SEC = 3; //явное ожидание

    //эндпойнты для юзера
    public static final String POST_REGISTER = "api/auth/register";
    public static final String POST_LOGIN = "api/auth/login";
    public static final String DELETE_USER = "api/auth/user";


}
