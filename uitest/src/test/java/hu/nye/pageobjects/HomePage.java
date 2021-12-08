package hu.nye.pageobjects;

import hu.nye.factory.WebDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class HomePage extends CommonPageObject {
    private static final String HOME_PAGE_URL = "http://localhost:8080/";

    @FindBy(id = "register-button")
    private WebElement registrationButton;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public HomePage(final WebDriverFactory factory) {
        super(factory);
    }

    public void navigateToHomePage() {
        navigateToUrl(HOME_PAGE_URL);
    }

    public void clickOnRegistrationButtonOnHomepage() {
        waitForElementToBeClickable(registrationButton);
        registrationButton.click();
        waitForPageReadiness();
    }

    public void clickOnLoginButton() {
        waitForElementToBeClickable(loginButton);
        loginButton.click();
        waitForPageReadiness();
    }

}
