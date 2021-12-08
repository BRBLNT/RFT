package hu.nye.stepdefinitions;

import hu.nye.config.TestConfig;
import hu.nye.pageobjects.HomePage;
import hu.nye.pageobjects.RegisterPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = TestConfig.class)
public class RegisterStepDefinitions {
    @Autowired
    private HomePage homePage;

    @Autowired
    private RegisterPage registerPage;

    @Given("the home page is opened")
    public void theHomePageIsOpened() {
        homePage.navigateToHomePage();
    }

    @And("The Registration Button is clicked")
    public void theRegistrationButtonIsClicked() {
        homePage.clickOnRegistrationButtonOnHomepage();
    }

    @When("the {string} is filled in with {string}")
    public void theFieldIsFilledWithParameter(final String field, final String content) {
        RegisterPage.getInputFieldByName(field).sendKeys(content);
    }

    @When("the Tab button is pressed")
    public void theTabButtonIsPressed() {
        new Actions(RegisterPage.getWebDriverFromFactory()).sendKeys(Keys.TAB).build().perform();
        RegisterPage.waitForPageReadiness();
    }


}
