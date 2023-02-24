package agency.wezom.evrika.pageobject.modals;

import agency.wezom.evrika.pageobject.BasePage;

import static agency.wezom.evrika.utils.WebElementUtil.isDisplayed;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

public class OrderSubmittedModal extends BasePage<OrderSubmittedModal> {

    public boolean isOrderSuccessfullyCompleted() {
        return isDisplayed($x("//*[text()='Order Submitted']"), ofSeconds(5));
    }
}
