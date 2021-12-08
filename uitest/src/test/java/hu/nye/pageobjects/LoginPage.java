package hu.nye.pageobjects;


import hu.nye.factory.WebDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LoginPage extends CommonPageObject{
    @FindBy(id = "login-button")
    private static WebElement loginButton;

    @FindBy(id = "exampleInputEmail1")
    private static WebElement emailInput;

    @FindBy(id = "exampleInputPassword1")
    private static WebElement passwordInput;

    private static final Map<String, WebElement> inputFieldsMap = Map.of(
            "Add meg az email címedet", emailInput,
            "Add meg a jelszavadat", passwordInput
    );

    public LoginPage(final WebDriverFactory factory) {
        super(factory);
    }

    public void clickOnLoginButtonOnLoginPage() {
        waitForElementToBeClickable(loginButton);
        loginButton.click();
        waitForPageReadiness();
    }

    public static WebElement getInputFieldByName(final String name) {
        return inputFieldsMap.get(name);
    }
}
