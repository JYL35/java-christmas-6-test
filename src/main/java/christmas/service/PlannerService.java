package christmas.service;

import christmas.domain.Badge;
import christmas.domain.ChristmasDiscount;
import christmas.domain.GiveawayEvent;
import christmas.domain.Menu;
import christmas.domain.SpecialDiscount;
import christmas.domain.WeekDiscount;
import christmas.dto.EventDiscount;
import christmas.dto.OrderSheet;
import christmas.dto.PlannerResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlannerService {

    public PlannerResult calculateBenefits(OrderSheet orderSheet) {
        int totalPrice = calculateTotalPrice(orderSheet.order());
        List<EventDiscount> eventDiscounts = calculateEventDiscount(orderSheet, totalPrice);
        Menu giveawayMenu = calculateGiveawayMenu(eventDiscounts);
        if (totalPrice < 10000) {
            return new PlannerResult(totalPrice, giveawayMenu, new ArrayList<>(), 0, Badge.NONE);
        }
        int totalDiscount = calculateTotalDiscount(eventDiscounts);
        Badge badge = Badge.findBadge(totalDiscount);

        return new PlannerResult(totalPrice, giveawayMenu, eventDiscounts, totalDiscount, badge);
    }

    private Menu calculateGiveawayMenu(List<EventDiscount> eventDiscounts) {
        for (EventDiscount eventDiscount : eventDiscounts) {
            if (eventDiscount.type().equals("증정 이벤트")) {
                return Menu.샴페인;
            }
        }
        return Menu.없음;
    }

    private int calculateTotalDiscount(List<EventDiscount> eventDiscounts) {
        int result = 0;
        for (EventDiscount eventDiscount : eventDiscounts) {
            result += eventDiscount.discountAmount();
        }
        return result;
    }

    private List<EventDiscount> calculateEventDiscount(OrderSheet orderSheet, int totalPrice) {
        List<EventDiscount> eventDiscounts = new ArrayList<>();
        eventDiscounts.add(ChristmasDiscount.calculateDiscount(orderSheet.visitDay()));
        eventDiscounts.add(WeekDiscount.calculateDiscount(orderSheet));
        eventDiscounts.add(SpecialDiscount.calculateDiscount(orderSheet.visitDay()));
        eventDiscounts.add(GiveawayEvent.calculateDiscount(totalPrice));

        return eventDiscounts;
    }

    private int calculateTotalPrice(Map<String, Integer> order) {
        int result = 0;
        for (String menu : order.keySet()) {
            result += Menu.findMenu(menu).getPrice();
        }
        return result;
    }
}
