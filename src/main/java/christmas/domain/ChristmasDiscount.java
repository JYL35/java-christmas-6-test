package christmas.domain;

import christmas.dto.EventDiscount;

public enum ChristmasDiscount {
    EVENT_PERIOD("크리스마스 디데이 할인", 1, 25);

    private final String type;
    private final int start;
    private final int end;

    ChristmasDiscount(String type, int start, int end) {
        this.type = type;
        this.start = start;
        this.end = end;
    }

    public static EventDiscount calculateDiscount(int visitDay) {
        int discount = 0;
        if (visitDay >= EVENT_PERIOD.start && visitDay <= EVENT_PERIOD.end) {
            discount = (visitDay - EVENT_PERIOD.start) * 100 + 1000;
        }
        return new EventDiscount(EVENT_PERIOD.type, discount);
    }
}
