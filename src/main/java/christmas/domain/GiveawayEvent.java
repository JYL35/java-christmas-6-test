package christmas.domain;

import christmas.dto.EventDiscount;

public enum GiveawayEvent {
    GIVEAWAY_EVENT("증정 이벤트", 120000, Menu.샴페인);

    private final String type;
    private final int condition;
    private final Menu giveaway;

    GiveawayEvent(String type, int condition, Menu giveaway) {
        this.type = type;
        this.condition = condition;
        this.giveaway = giveaway;
    }

    public static EventDiscount calculateDiscount(int totalDiscount) {
        int discount = 0;
        if (totalDiscount >= GIVEAWAY_EVENT.condition) {
            discount = GIVEAWAY_EVENT.giveaway.getPrice();
        }
        return new EventDiscount(GIVEAWAY_EVENT.type, discount);
    }

    public Menu getGiveaway() {
        return giveaway;
    }
}
