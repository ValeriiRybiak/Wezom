package agency.wezom.evrika.pageobject;

import agency.wezom.evrika.pageobject.modals.OrderSubmittedModal;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class CartPage extends BasePage<CartPage> {

    private final String PAYMENT_DELIVERY_OPTION_TEMPLATE = """
            //div[text()='%s']/ancestor::div[@class='checkout-services__row']
            //span[text()='%s']/ancestor::label
            """;

    @Step("Cart page: Set middle name to {middleName}")
    public CartPage setMiddleName(String middleName) {
        $x("//label[text()='Отчество']/preceding-sibling::input")
                .shouldBe(enabled)
                .setValue(middleName);

        return this;
    }

    @Step("Cart page: Chosen delivery method {deliveryMethod} and partial address to {partialAddress}")
    public CartPage chooseDeliveryType(String deliveryMethod, String partialAddress) {
        $x(format(PAYMENT_DELIVERY_OPTION_TEMPLATE, "Доставка", deliveryMethod)).click();
        $(".delivery-pickup__choice .pickup").click();
        $(".pickup__dropdown.is-open")
                .shouldBe(visible)
                .$x(format(".//span[@class='pickup__option-address' and contains(text(),'%s')]/ancestor::label", partialAddress))
                .click();

        return this;
    }

    @Step("Cart page: Chosen payment method {paymentMethod}")
    public CartPage choosePaymentMethod(String paymentMethod) {
        $x(format(PAYMENT_DELIVERY_OPTION_TEMPLATE, "Оплата", paymentMethod)).click();

        return this;
    }

    @Step("Cart page: Submit order")
    public OrderSubmittedModal submitOrder() {
        $("div.order__submit button").click();

        return new OrderSubmittedModal();
    }
}
