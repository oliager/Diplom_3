package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.helpers.DriverHelper;
import org.example.page.objects.HomePage;
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

import static org.example.utils.Utils.*;

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
        driver.get(Utils.URL_BURGERS);

    }

    @Parameterized.Parameters
    public static Object[][] getLoginData() {
        return new Object[][] {
                {SECTION_SOUSES, SECTION_BUNS},
                {SECTION_SOUSES, SECTION_FILLINGS},
                {SECTION_FILLINGS, SECTION_SOUSES},
                {SECTION_FILLINGS, SECTION_BUNS},
        };
    }

    @Test
    public void changeSectionBurgerConstructorTest() {
        driver.findElement(locatorOfInitialSection).click();

        driver.findElement(locatorOfFinalSection).click();

        HomePage homePage = new HomePage(driver);

       Assert.assertTrue(homePage.isCurrent(locatorOfFinalSection));
    }



    @After
    public void teardown() {

        driver.quit();
    }
}
