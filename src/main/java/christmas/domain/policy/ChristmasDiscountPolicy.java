package christmas.domain.policy;

import christmas.domain.DiscountPolicy;
import christmas.dto.OrderSheet;

public class ChristmasDiscountPolicy implements DiscountPolicy {
    private static final String EVENT_NAME = "크리스마스 디데이 할인";
    private static final int START_DATE = 1;
    private static final int END_DATE = 25;
    private static final int BASE_DISCOUNT = 1000;
    private static final int DAILY_INCREMENT = 100;

    @Override
    public int calculateDiscountAmount(OrderSheet orderSheet) {
        int visitDay = orderSheet.visitDay();
        if (visitDay >= START_DATE && visitDay <= END_DATE) {
            return (visitDay - START_DATE) * DAILY_INCREMENT + BASE_DISCOUNT;
        }
        return 0;
    }

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }
}
