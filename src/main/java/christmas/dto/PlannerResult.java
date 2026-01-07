package christmas.dto;

import java.util.List;

public record PlannerResult(
        int totalPrice,
        List<EventDiscount> eventDiscounts
) {
}
