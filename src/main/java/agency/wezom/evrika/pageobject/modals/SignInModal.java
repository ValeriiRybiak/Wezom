package agency.wezom.evrika.pageobject.modals;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SignInModal {

    public SignInModal() {
        $(".popup-content").shouldBe(visible);
    }

    @Step("Sign in modal: Opened sign up page")
    public RegistrationModal openSignUpPage() {
        $x("//button[contains(@x-data, 'register')]").click();

        return new RegistrationModal();
    }
}
