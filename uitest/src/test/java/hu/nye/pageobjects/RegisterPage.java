package hu.nye.pageobjects;

import hu.nye.factory.WebDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RegisterPage extends CommonPageObject {
    @FindBy(id = "register-button")
    private static WebElement registrationButton;

    @FindBy(id = "username")
    private static WebElement usernameInput;

    @FindBy(id = "exampleInputEmail1")
    private static WebElement emailInput;

    @FindBy(id = "exampleInputPassword1")
    private static WebElement passwordInput;

    @FindBy(id = "exampleInputPassword2")
    private static WebElement confirmpasswordInput;

    private static final Map<String, WebElement> inputFieldsMap = Map.of(
            "Add meg a felhasználói nevedet.", usernameInput,
            "Add meg az email címedet.", emailInput,
            "Adj meg egy jelszót.", passwordInput,
            "Add meg újra a jelszódat", confirmpasswordInput
    );

    public RegisterPage(final WebDriverFactory factory) {
        super(factory);
    }

    public void clickOnRegistrationButtonOnRegisterPage() {
        waitForElementToBeClickable(registrationButton);
        registrationButton.click();
        waitForPageReadiness();
    }

    public static WebElement getInputFieldByName(final String name) {
        return inputFieldsMap.get(name);
    }

}
