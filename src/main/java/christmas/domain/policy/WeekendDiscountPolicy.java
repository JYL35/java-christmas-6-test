package christmas.domain.policy;

import christmas.dto.OrderSheet;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendDiscountPolicy extends AbstractDiscountPolicy {
    private static final String EVENT_NAME = "주말 할인";

    @Override
    public int calculateDiscountAmount(OrderSheet orderSheet) {
        LocalDate localDate = LocalDate.of(2023, 12, orderSheet.visitDay());
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        if (dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY) {
            return 0;
        }
        return calculateDiscountOfDay(orderSheet, "메인");
    }

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }
}
