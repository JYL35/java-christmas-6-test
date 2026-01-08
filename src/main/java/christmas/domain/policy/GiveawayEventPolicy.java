package christmas.domain.policy;

import christmas.domain.DiscountPolicy;
import christmas.domain.Menu;
import christmas.dto.OrderSheet;

public class GiveawayEventPolicy implements DiscountPolicy {
    private static final String EVENT_NAME = "증정 이벤트";
    private static final int CONDITION = 120000;
    private static final Menu GIVEAWAY = Menu.샴페인;

    @Override
    public int calculateDiscountAmount(OrderSheet orderSheet) {
        if (orderSheet.getTotalPrice() >= CONDITION) {
            return GIVEAWAY.getPrice();
        }
        return 0;
    }

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }
}
