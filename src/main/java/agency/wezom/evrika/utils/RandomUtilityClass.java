package agency.wezom.evrika.utils;

import com.github.curiousoddman.rgxgen.RgxGen;
import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class RandomUtilityClass {

    private static final Faker FAKER = new Faker();

    public static String getRandomStringFromRegex(String regex) {
        return new RgxGen(regex).generate();
    }

    public static String getRandomPhone() {
        return FAKER
                .number()
                .digits(10);
    }
}
