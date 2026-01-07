package christmas.util;

import christmas.constant.ErrorMessage;

public class Validator {

    public static int validateVisitDay(String input) {
        validateEmpty(input, ErrorMessage.INVALID_DATE);
        int visitDay = validateNumber(input, ErrorMessage.INVALID_DATE);
        validateRange(1, 31, visitDay, ErrorMessage.INVALID_DATE);
        return visitDay;
    }

    private static void validateEmpty(String input, ErrorMessage errorMessage) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(errorMessage.getMessage());
        }
    }

    private static int validateNumber(String input, ErrorMessage errorMessage) {
        try {
            return Integer.parseInt(input.strip());
        } catch (RuntimeException e) {
            throw new IllegalArgumentException(errorMessage.getMessage());
        }
    }

    private static void validateRange(int min, int max, int input, ErrorMessage errorMessage) {
        if (input < min || input > max) {
            throw new IllegalArgumentException(errorMessage.getMessage());
        }
    }
}
