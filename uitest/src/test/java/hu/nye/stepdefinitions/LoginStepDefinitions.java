package hu.nye.stepdefinitions;

import hu.nye.config.TestConfig;
import hu.nye.pageobjects.HomePage;
import hu.nye.pageobjects.LoginPage;
import hu.nye.pageobjects.RegisterPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = TestConfig.class)

public class LoginStepDefinitions {

    @Autowired
    private HomePage homePage;

    @Autowired
    private LoginPage loginPage;

    @Given("the home page is opened")
    public void theHomePageIsOpened() {
        homePage.navigateToHomePage();
    }

    @And("The Login Button is clicked")
    public void theLoginButtonIsClicked() {
        homePage.clickOnLoginButton();
    }

    @When("the {string} is filled in with {string}")
    public void theFieldIsFilledWithParameter(final String field, final String content) {
        LoginPage.getInputFieldByName(field).sendKeys(content);
    }

    @When("the Tab button is pressed")
    public void theTabButtonIsPressed() {
        new Actions(LoginPage.getWebDriverFromFactory()).sendKeys(Keys.TAB).build().perform();
        LoginPage.waitForPageReadiness();
    }
}
