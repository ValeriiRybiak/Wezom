package agency.wezom.evrika.utils;

import io.qameta.allure.Attachment;
import lombok.experimental.UtilityClass;

import static com.codeborne.selenide.Selenide.screenshot;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;
import static org.openqa.selenium.OutputType.BYTES;

@UtilityClass
public final class AllureHelper {

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] attachScreenShot() {
        if (hasWebDriverStarted())
            return screenshot(BYTES);

        return null;
    }
}
