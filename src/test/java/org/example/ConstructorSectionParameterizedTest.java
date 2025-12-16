package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.helpers.DriverHelper;
import org.example.page.objects.HomePage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import static org.example.utils.Utils.*;
import static org.example.page.objects.HomePage.*;

@RunWith(Parameterized.class)
public class ConstructorSectionParameterizedTest {
    private WebDriver driver;

    private final By locatorOfInitialSection;
    private final By locatorOfFinalSection;
    public ConstructorSectionParameterizedTest(By locatorOfInitialSection, By locatorOfFinalSection) {
        this.locatorOfInitialSection = locatorOfInitialSection;
        this.locatorOfFinalSection = locatorOfFinalSection;
    }


    @Before
    public void startUp() throws IOException {
        WebDriverManager.chromedriver().setup();

        driver = DriverHelper.initDriver();
        driver.get(URL_BURGERS);
    }

    @Parameterized.Parameters(name = "section= {0}, change to= {1}")
    public static Object[][] getLoginData() {
        return new Object[][] {
                {SECTION_SOUSES, SECTION_BUNS},
                {SECTION_SOUSES, SECTION_FILLINGS},
                {SECTION_FILLINGS, SECTION_SOUSES},
                {SECTION_FILLINGS, SECTION_BUNS},
        };
    }

    @Test
    @DisplayName("После клика на вкладку она становится текущей открытой")
    @Description("Проверка, что при клике на вкладку " +
            "в значении ее атрибута класс есть класс с активным табом")
    public void changeSectionBurgerConstructorTest() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnSection(locatorOfInitialSection);
        homePage.clickOnSection(locatorOfFinalSection);

       Assert.assertTrue(homePage.isCurrent(locatorOfFinalSection));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
