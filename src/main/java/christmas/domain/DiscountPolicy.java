package christmas.domain;

import christmas.dto.OrderSheet;

public interface DiscountPolicy {

    int calculateDiscountAmount(OrderSheet orderSheet);

    String getEventName();
}
