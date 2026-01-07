package christmas.util;

import christmas.constant.ErrorMessage;
import christmas.domain.Menu;
import java.util.Map;

public class Validator {

    public static int validateVisitDay(String input) {
        validateEmpty(input, ErrorMessage.INVALID_DATE);
        int visitDay = validateNumber(input, ErrorMessage.INVALID_DATE);
        validateRange(1, 31, visitDay, ErrorMessage.INVALID_DATE);
        return visitDay;
    }

    public static void validateMenu(String input, Map<String, Integer> order) {
        validateEmpty(input, ErrorMessage.INVALID_ORDER);
        validateMenuContains(input);
        validateMenuDuplicate(input, order);
    }

    public static int validateCount(String input) {
        validateEmpty(input, ErrorMessage.INVALID_ORDER);
        int count = validateNumber(input, ErrorMessage.INVALID_ORDER);
        validateRange(1, 20, count, ErrorMessage.INVALID_ORDER);
        return count;
    }

    public static void validateOrder(Map<String, Integer> order) {
        validateOrderEmpty(order);
        validateType(order);
        int totalCount = order.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        validateRange(1, 20, totalCount, ErrorMessage.INVALID_ORDER);
    }

    private static void validateType(Map<String, Integer> order) {
        for (String menuName : order.keySet()) {
            Menu menu = Menu.findMenu(menuName);
            if (!menu.getType().equals("음료")) return;
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
    }

    private static void validateOrderEmpty(Map<String, Integer> order) {
        if (order.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private static void validateMenuDuplicate(String input, Map<String, Integer> order) {
        if (order.containsKey(input)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private static void validateMenuContains(String input) {
        for (Menu menu : Menu.values()) {
            if (input.equals(menu.name())) return;
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
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
