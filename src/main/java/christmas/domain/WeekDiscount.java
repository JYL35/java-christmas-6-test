package christmas.domain;

import christmas.dto.EventDiscount;
import christmas.dto.OrderSheet;
import java.util.List;
import java.util.Map;

public enum WeekDiscount {
    평일("평일 할인", "디저트", List.of(3,4,5,6,7,10,11,12,13,14,17,18,19,20,21,24,25,26,27,28,31)),
    주말("주말 할인", "메인", List.of(1,2,8,9,15,16,22,23,29,30));

    private final String type;
    private final String foodType;
    private final List<Integer> days;

    WeekDiscount(String type, String foodType, List<Integer> days) {
        this.type = type;
        this.foodType = foodType;
        this.days = days;
    }

    public static EventDiscount calculateDiscount(OrderSheet orderSheet) {
        WeekDiscount weekDiscount = WeekDiscount.평일;
        if (주말.days.contains(orderSheet.visitDay())) {
            weekDiscount = WeekDiscount.주말;
        }
        return new EventDiscount(weekDiscount.type, calculateDiscountOfDay(orderSheet.order(), weekDiscount.foodType));
    }

    private static int calculateDiscountOfDay(Map<String, Integer> order, String type) {
        List<String> typeOfFoods = Menu.getTypeOfFoods(type);
        int totalDiscountAmount = 0;
        for (String menu : order.keySet()) {
            if (typeOfFoods.contains(menu)) {
                totalDiscountAmount += (2023 * order.get(menu));
            }
        }
        return totalDiscountAmount;
    }
}
