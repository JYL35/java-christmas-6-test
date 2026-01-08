package christmas.domain.policy;

import christmas.domain.DiscountPolicy;
import christmas.domain.Menu;
import christmas.dto.OrderSheet;
import java.util.List;
import java.util.Map;

public abstract class AbstractDiscountPolicy implements DiscountPolicy {
    private static final int DISCOUNT_AMOUNT = 2023;

    protected int calculateDiscountOfDay(OrderSheet orderSheet, String type) {
        List<String> typeOfFoods = Menu.getTypeOfFoods(type);
        Map<String, Integer> orders = orderSheet.order();

        int totalDiscountAmount = 0;
        for (String menu : orders.keySet()) {
            if (typeOfFoods.contains(menu)) {
                totalDiscountAmount += (DISCOUNT_AMOUNT * orders.get(menu));
            }
        }
        return totalDiscountAmount;
    }
}
