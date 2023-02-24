package agency.wezom.evrika.pageobject;

import agency.wezom.evrika.pageobject.modals.GoodModal;
import io.qameta.allure.Step;

import static agency.wezom.evrika.utils.AllureHelper.attachScreenShot;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class GoodsPage extends BasePage<GoodsPage> {

    public GoodsPage() {
        $("h1.catalog-title").shouldBe(visible);
    }

    @Step("Goods page: added good with name {goodName} to the card")
    public GoodModal addGoodToTheCard(String goodName) {
        $x(format("""
                 //div[@class='goods-tile__name']/a[text()='%s']/ancestor::div
                /following-sibling::div[@class='goods-tile__row']//button
                 """, goodName))
                .scrollTo()
                .click();

        $(".cart__title.h3").shouldBe(visible);
        attachScreenShot();

        return new GoodModal();
    }
}
