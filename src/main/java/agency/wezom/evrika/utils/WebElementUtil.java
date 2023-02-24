package agency.wezom.evrika.utils;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

@UtilityClass
public final class WebElementUtil {
    @Step("[x] Waited for {element} to be displayed")
    public static boolean isDisplayed(SelenideElement element, Duration timeout) {
        try {
            return element
                    .shouldBe(visible, timeout)
                    .isDisplayed();
        } catch (AssertionError error) {
            return false;
        }
    }
}
