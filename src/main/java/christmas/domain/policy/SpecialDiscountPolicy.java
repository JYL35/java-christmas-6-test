package christmas.domain.policy;

import christmas.domain.DiscountPolicy;
import christmas.dto.OrderSheet;
import java.util.List;

public class SpecialDiscountPolicy implements DiscountPolicy {
    private static final List<Integer> SPECIAL_DAY = List.of(3, 10, 17, 24, 25, 31);
    private static final String EVENT_NAME = "특별 할인";
    private static final int DISCOUNT = 1000;

    @Override
    public int calculateDiscountAmount(OrderSheet orderSheet) {
        if (SPECIAL_DAY.contains(orderSheet.visitDay())) {
            return DISCOUNT;
        }
        return 0;
    }

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }
}
