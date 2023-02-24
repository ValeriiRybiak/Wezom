package agency.wezom.evrika.pageobject.modals;

import agency.wezom.evrika.models.AccountType;
import agency.wezom.evrika.models.User;
import agency.wezom.evrika.pageobject.PersonalAccountPage;
import io.qameta.allure.Step;

import static agency.wezom.evrika.models.AccountType.INDIVIDUALS;
import static agency.wezom.evrika.utils.AllureHelper.attachScreenShot;
import static agency.wezom.evrika.utils.SlowInput.slowInput;
import static agency.wezom.evrika.utils.WebElementUtil.isDisplayed;
import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofMillis;
import static org.openqa.selenium.By.name;

public class RegistrationModal {

    @Step("Registration modal: Created account and sign in")
    public PersonalAccountPage createAccountAndSignIn(User user) {
        chooseAccountType(user.getAccountType());

        $(name("name"))
                .shouldBe(enabled)
                .execute(slowInput(user.getName(), 4));
        var surnameInput = $(name("surname")).execute(slowInput(user.getPassword(), 4));
        var emailInput = $(name("email"));

        do {
            surnameInput.pressTab();
            actions()
                    .sendKeys(user.getPhone())
                    .perform();
            emailInput.click();
        } while (isDisplayed($x("//input[@name='phone']/following-sibling::div[@class='form-error']"), ofMillis(500)));

        emailInput.execute(slowInput(user.getEmail(), 4));
        $(name("password")).execute(slowInput(user.getPassword(), 6));
        $x("//input[@type='password' and not(@name='password')]")
                .execute(slowInput(user.getPassword(), 6));
        $x("//input[@type='checkbox']").click(usingJavaScript());

        attachScreenShot();

        $(".popup__view button").click();

        return new PersonalAccountPage();
    }

    @Step("Registration modal: Chosen account type")
    private void chooseAccountType(AccountType accountType) {
        var radioBtnValue = accountType == INDIVIDUALS ? 0 : 1;
        $x(String.format("//form//input[@type='radio' and @value='%d']", radioBtnValue));
    }
}
