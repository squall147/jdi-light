package io.github.epam;

import com.epam.jdi.light.driver.ScreenshotMaker;
import com.epam.jdi.light.driver.WebDriverFactory;
import com.epam.jdi.light.ui.html.PageFactory;
import io.github.com.StaticSite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import sun.print.PageableDoc;

import static com.epam.jdi.light.logger.LogLevels.INFO;
import static com.epam.jdi.light.settings.WebSettings.SMART_SEARCH_LOCATORS;
import static com.epam.jdi.light.settings.WebSettings.logger;
import static com.epam.jdi.light.ui.html.PageFactory.initElements;
import static io.github.com.StaticSite.homePage;

public class TestsInit {
    @BeforeSuite(alwaysRun = true)
    public static void setUp() {
        logger.setLogLevel(INFO);
        SMART_SEARCH_LOCATORS.add("[ui=%s]");
        initElements(StaticSite.class);
        homePage.open();
        logger.toLog("Run Tests");
    }


    @AfterSuite(alwaysRun = true)
    public static void tearDown() {
        WebDriverFactory.close();
    }
}
