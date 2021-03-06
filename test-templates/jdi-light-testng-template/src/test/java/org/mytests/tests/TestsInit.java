package org.mytests.tests;

import com.epam.jdi.light.driver.WebDriverFactory;
import org.mytests.uiobjects.example.site.SiteJdi;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.epam.jdi.light.driver.WebDriverUtils.*;
import static com.epam.jdi.light.elements.init.PageFactory.*;
import static com.epam.jdi.light.settings.WebSettings.*;
import static org.mytests.uiobjects.example.site.SiteJdi.*;

public class TestsInit {
    @BeforeSuite(alwaysRun = true)
    public static void setUp() {
        WebDriverFactory.getDriver("chrome");
        initSite(SiteJdi.class);
        homePage.open();
        logger.info("Run Tests");
    }

    @AfterSuite(alwaysRun = true)
    public void teardown() {
        killAllSeleniumDrivers();
    }
}
