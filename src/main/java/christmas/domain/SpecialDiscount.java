package christmas.domain;

import christmas.dto.EventDiscount;
import java.util.List;

public enum SpecialDiscount {
    SPECIAL_DAY("특별 할인", 1000, List.of(3, 10, 17, 24, 25, 31));

    private final String type;
    private final int discountAmount;
    private final List<Integer> days;

    SpecialDiscount(String type, int discountAmount, List<Integer> days) {
        this.type = type;
        this.discountAmount = discountAmount;
        this.days = days;
    }

    public static EventDiscount calculateDiscount(int visitDay) {
        int discount = 0;
        if (SPECIAL_DAY.days.contains(visitDay)) {
            discount = SPECIAL_DAY.discountAmount;
        }
        return new EventDiscount(SPECIAL_DAY.type, discount);
    }
}
