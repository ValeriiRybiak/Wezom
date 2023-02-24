package agency.wezom.evrika.runners;

import agency.wezom.evrika.pageobject.HomePage;
import agency.wezom.evrika.utils.FailedTestsListener;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static java.time.Duration.ofSeconds;

@Listeners({FailedTestsListener.class})
public abstract class Runner {

    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser() {
        timeout = ofSeconds(30).toMillis();
        browser = "chrome";
        browserSize = "1920x1080";
        pageLoadTimeout = ofSeconds(100).toMillis();
        screenshots = false;
        savePageSource = false;
        var opts = new ChromeOptions();
        opts.addArguments("--window-size=1920,1080");
        chromedriver().setup();
        var webDriver = new ChromeDriver(opts);
        setWebDriver(webDriver);
    }

    @Step("Open home page")
    protected HomePage openHomePage() {
        open("https://evrika.wezom.agency/");

        return new HomePage();
    }

    @AfterMethod
    protected void closeWebDriver() {
        WebDriverRunner.closeWebDriver();
    }
}
