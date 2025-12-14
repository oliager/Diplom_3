package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
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
import org.openqa.selenium.chrome.ChromeDriver;

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
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        // драйвер для браузера Chrome
        driver = new ChromeDriver();
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
    public void changeSectionBurgerCostructorTest() {
        driver.findElement(locatorOfInitialSection).click();

        driver.findElement(locatorOfFinalSection).click();

        HomePage homePage = new HomePage(driver);

       Assert.assertTrue(homePage.isCurrent(locatorOfFinalSection));
    }



    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }
}
