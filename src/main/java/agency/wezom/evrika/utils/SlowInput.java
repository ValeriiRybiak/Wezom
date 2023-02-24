package agency.wezom.evrika.utils;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSource;
import lombok.AllArgsConstructor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.codeborne.selenide.Selenide.sleep;
import static java.lang.String.valueOf;

@AllArgsConstructor
public class SlowInput implements Command<SelenideElement> {
    private final String value;
    private final long delayBetweenInputs;

    public static SlowInput slowInput(String value, long delayBetweenInputsInMilliseconds) {
        return new SlowInput(value, delayBetweenInputsInMilliseconds);
    }

    @Override
    @Nonnull
    public SelenideElement execute(SelenideElement proxy, WebElementSource locator, @Nullable Object[] args) {
        for (var singleChar : value.toCharArray()) {
            sleep(delayBetweenInputs);
            proxy.sendKeys(valueOf(singleChar));
        }

        return proxy;
    }
}

