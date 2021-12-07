import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainTest {



    @Test
    void RegisterPage() {
        open("http://localhost:8080");
        $("#register-button").click();
        $("#username").sendKeys("Username");
        $("#exampleInputEmail1").sendKeys("example@example.com");
        $("#exampleInputPassword1").sendKeys("examplePassword");
        $("#exampleInputPassword2").sendKeys("examplePassword");
    }

    @Test
    void ExampleEmailShouldBeVisibleOnRegisterPage() {
        open("http://localhost:8080");
        $("#register-button").click();
        $(withText("example@example.com")).shouldBe(visible);
    }

    @Test
    void LoginPage() {
        open("http://localhost:8080/login");
        $("#login-button").click();
        $("#exampleInputEmail1").sendKeys("example@example.com");
        $("#exampleInputPassword1").sendKeys("examplePassword");

    }
    @Test
    void ExampleEmailShouldBeVisibleOnLoginPage() {
        open("http://localhost:8080");
        $("#login-button").click();
        $(withText("example@example.com")).shouldBe(visible);
    }

    @Test
    void LoginButtonShouldBeVisible() {
        open("http://localhost:8080");
        $("#login-button").click();
        $("#login-button").shouldBe(visible);
    }

    @Test
    void RegisterButtonShouldBeVisible() {
        open("http://localhost:8080");
        $("#register-button").click();
        $("#register-button").shouldBe(visible);
    }
}
