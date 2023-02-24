package agency.wezom.evrika.pageobject.modals;

import agency.wezom.evrika.pageobject.CartPage;
import agency.wezom.evrika.pageobject.GoodsPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class GoodModal {

    public GoodsPage continueShopping() {
        $(".cart__button-continue button").click();

        return new GoodsPage();
    }

    @Step("Good modal: opened cart page")
    public CartPage openCartPage() {
        $(".cart-resume__button-order a").click();

        return new CartPage();
    }

    public String getCostValue() {
        return $("div[class='cart-resume__cost-value']")
                .shouldBe(visible)
                .getText();
    }
}
