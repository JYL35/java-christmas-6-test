package christmas.service;

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
        List<EventDiscount> eventDiscounts = new ArrayList<>();
        if (totalPrice >= 10000) {
            eventDiscounts = calculateEventDiscount(orderSheet, totalPrice);
        }
        return new PlannerResult(totalPrice, eventDiscounts);
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
