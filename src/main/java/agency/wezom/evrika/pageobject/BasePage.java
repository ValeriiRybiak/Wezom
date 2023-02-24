package agency.wezom.evrika.pageobject;

import agency.wezom.evrika.pageobject.modals.SignInModal;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public abstract class BasePage<T extends BasePage> {

    @Step("Open sign in page")
    public SignInModal openSignInPage() {
        $x("//div[contains(@x-data, 'login')]")
                .shouldBe(enabled.because("SingIn/SignUp link should be enabled"))
                .click();

        return new SignInModal();
    }

    @Step("Chosen menu category")
    public GoodsPage chooseMenuCategory(String menuItem, String subMenuItem) {
        actions()
                .moveToElement($(".menu.menu_compact"))
                .perform();

        var menuItemElement =
                $x(format("//nav[contains(@class, 'menu')]//li[contains(@class,'menu__li')]/a/span[text()='%s']", menuItem))
                        .shouldBe(visible);
        actions()
                .moveToElement(menuItemElement)
                .perform();

        var subMenuItemElement = menuItemElement.
                $x(format("./ancestor::li/div//a[text()=' %s ']", subMenuItem))
                .shouldBe(visible);
        actions()
                .moveToElement(subMenuItemElement)
                .click()
                .perform();

        return new GoodsPage();
    }
}
