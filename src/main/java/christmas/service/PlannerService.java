package christmas.service;

import christmas.domain.Badge;
import christmas.domain.DiscountPolicy;
import christmas.domain.Menu;
import christmas.dto.EventDiscount;
import christmas.dto.OrderSheet;
import christmas.dto.PlannerResult;
import java.util.ArrayList;
import java.util.List;

public class PlannerService {

    private final List<DiscountPolicy> discountPolicies;

    public PlannerService(List<DiscountPolicy> discountPolicies) {
        this.discountPolicies = discountPolicies;
    }

    public PlannerResult calculateBenefits(OrderSheet orderSheet) {
        int totalPrice = orderSheet.getTotalPrice();
        if (totalPrice < 10000) {
            return new PlannerResult(totalPrice, Menu.없음, new ArrayList<>(), 0, totalPrice, Badge.NONE);
        }
        List<EventDiscount> eventDiscounts = calculateEventDiscount(orderSheet);
        Menu giveawayMenu = calculateGiveawayMenu(eventDiscounts);
        int totalDiscount = calculateTotalDiscount(eventDiscounts);
        int expectedPaymentAmount = totalPrice - totalDiscount + giveawayMenu.getPrice();
        Badge badge = Badge.findBadge(totalDiscount);
        return new PlannerResult(totalPrice, giveawayMenu, eventDiscounts,
                totalDiscount, expectedPaymentAmount, badge);
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

    private List<EventDiscount> calculateEventDiscount(OrderSheet orderSheet) {
        List<EventDiscount> eventDiscounts = new ArrayList<>();

        for (DiscountPolicy discountPolicy : discountPolicies) {
            int discountAmount = discountPolicy.calculateDiscountAmount(orderSheet);
            if (discountAmount > 0) {
                eventDiscounts.add(new EventDiscount(discountPolicy.getEventName(), discountAmount));
            }
        }

        return eventDiscounts;
    }
}
