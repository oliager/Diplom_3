package org.example.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.model.User;

import static io.restassured.RestAssured.given;

public class UsersSteps {

    @Step("Создание пользователя")
    public ValidatableResponse create(User user) {
        return given()
                .body(user)
                .when()
                .post("api/auth/register")
                .then();

    }
    @Step("Авторизация пользователя")
    public ValidatableResponse login(User user) {
        return given()
                .body(user)
                .when()
                .post("api/auth/login")
                .then();

    }
    @Step("Удаляется аккаунт пользователя")
    public ValidatableResponse delete(String authorization) {
        return given()
                .header("Authorization", authorization)
                .when()
                .delete("api/auth/user")
                .then();
    }

}
