package agency.wezom.evrika.sanity;

import agency.wezom.evrika.models.User;
import agency.wezom.evrika.runners.Runner;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static agency.wezom.evrika.models.AccountType.INDIVIDUALS;
import static agency.wezom.evrika.models.repo.GoodsRepo.getMonitorHPEurope22f2XN58AA;
import static agency.wezom.evrika.models.repo.GoodsRepo.getMonitorSamsungLC27R500FHIXCI;
import static agency.wezom.evrika.utils.RandomUtilityClass.getRandomPhone;
import static agency.wezom.evrika.utils.RandomUtilityClass.getRandomStringFromRegex;
import static org.assertj.core.api.Assertions.assertThat;

public class SignUpAndBuyGoodsTest extends Runner {

    @Test(dataProvider = "signUpAndByGoodsTestSource", description = "Sanity scenario")
    @Description("Sign up user, buy 2 Monitors and order goods")
    public void signUpAndBuyGoodsTest(User user) {
        var goodModal = openHomePage()
                .openSignInPage()
                .openSignUpPage()
                .createAccountAndSignIn(user)
                .chooseMenuCategory("Ноутбуки и компьютеры", "Мониторы")
                .addGoodToTheCard(getMonitorSamsungLC27R500FHIXCI().getName())
                .continueShopping()
                .addGoodToTheCard(getMonitorHPEurope22f2XN58AA().getName());

        assertThat(goodModal.getCostValue())
                .as("Incorrect cost value")
                .isEqualTo("139 980 ₸");

        var orderSubmittedModal = goodModal.openCartPage()
                .setMiddleName(user.getMiddleName())
                .chooseDeliveryType("Самовывоз из магазина", "Проспект Аль-Фараби 1")
                .choosePaymentMethod("Наличными")
                .submitOrder();

        assertThat(orderSubmittedModal.isOrderSuccessfullyCompleted())
                .as("Successfully order message should be displayed")
                .isTrue();
    }

    @DataProvider(name = "signUpAndByGoodsTestSource")
    public static Object[][] signUpAndByGoodsTestSource() {
        return new Object[][]{
                {User
                        .builder()
                        .accountType(INDIVIDUALS)
                        .name(getRandomStringFromRegex("[A-Za-z']{10}"))
                        .middleName(getRandomStringFromRegex("[A-Za-z']{10}"))
                        .lastName(getRandomStringFromRegex("[A-Za-z']{10}"))
                        .phone(getRandomPhone())
                        .email(getRandomStringFromRegex("[a-zA-Z0-9]{1,35}@email[.]com"))
                        .password(getRandomStringFromRegex("[A-Za-z0-9']{10}"))
                        .build()
                }
        };
    }
}
