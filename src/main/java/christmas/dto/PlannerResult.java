package christmas.dto;

import christmas.domain.Badge;
import christmas.domain.Menu;
import java.util.List;

public record PlannerResult(
        int totalPrice,
        Menu giveawayMenu,
        List<EventDiscount> eventDiscounts,
        int totalDiscount,
        Badge badge
) {
}
