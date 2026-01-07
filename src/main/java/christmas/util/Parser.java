package christmas.util;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static int parseVisitDay(String input) {
        return Validator.validateVisitDay(input);
    }

    public static Map<String, Integer> parseOrder(String input) {
        Map<String, Integer> order = new HashMap<>();

        for (String orderSheet : input.split(",")) {
            String[] parseOrderSheet = orderSheet.strip().split("-");
            String menu = parseOrderSheet[0];
            Validator.validateMenu(menu, order);
            int count = Validator.validateCount(parseOrderSheet[1]);
            order.put(menu, count);
        }
        Validator.validateOrder(order);
        return Map.copyOf(order);
    }
}
