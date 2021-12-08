package hu.nye.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Value;



import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static hu.nye.config.TestConfig.PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS;

public class WebDriverFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverFactory.class);
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";

    @Value("${browserName:chrome}")
    private String browserName;

    @Value("${headless:false}")
    private Boolean headless;

    private WebDriver webDriver;

    public WebDriver getWebDriver() {
        if (Objects.isNull(webDriver)) {
            switch (browserName) {
                case CHROME:
                    webDriver = setUpChromeDriver();
                    break;
                case FIREFOX:
                    webDriver = setUpFirefoxDriver();
                    break;
                default:
                    throw new RuntimeException("Unsupported driver for browser=" + browserName);
            }
        }

        webDriver.manage().window().setSize(new Dimension(1920, 1080));
        webDriver.manage().timeouts().pageLoadTimeout(PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS, TimeUnit.SECONDS);

        return webDriver;
    }

    private WebDriver setUpChromeDriver() {
        WebDriverManager.chromedriver().setup();

        LOGGER.info("ChromeDriver was created");
        return new ChromeDriver(new ChromeOptions().setHeadless(headless));
    }

    private WebDriver setUpFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();

        LOGGER.info("FirefoxDriver was created");
        return new FirefoxDriver(new FirefoxOptions().setHeadless(headless));
    }

    public void closeWebDriver() {
        if (Objects.nonNull(webDriver)) {
            webDriver.quit();
            webDriver = null;
            LOGGER.info("WebDriver was closed");
        }
    }

}

