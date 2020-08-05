package ir.mine.project.base.Util.random;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Utility class for generating random Strings.
 */
public final class RandomUtil {

    private static final int DEF_COUNT = 20;

    private RandomUtil() {
    }

    /**
     * Generate a password.
     *
     * @return the generated password
     */
    public static String generatePassword(Optional<Integer> defCount) {
        return RandomStringUtils.randomAlphanumeric(defCount.isPresent() ? defCount.get() : DEF_COUNT);
    }

    /**
     * Generate an activation key.
     *
     * @return the generated activation key
     */
    public static String generateActivationKey(Optional<Integer> defCount) {
        return RandomStringUtils.randomNumeric(defCount.isPresent() ? defCount.get() : DEF_COUNT);
    }

    /**
     * Generate a reset key.
     *
     * @return the generated reset key
     */
    public static String generateResetKey(Optional<Integer> defCount) {
        return RandomStringUtils.randomNumeric(defCount.isPresent() ? defCount.get() : DEF_COUNT);
    }

}
