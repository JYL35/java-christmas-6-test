package christmas.dto;

import christmas.domain.Menu;
import java.util.Map;

public record OrderSheet(
        int visitDay,
        Map<String, Integer> order
) {
    public int getTotalPrice() {
        int result = 0;
        for (String menu : order.keySet()) {
            result += (Menu.findMenu(menu).getPrice() * order.get(menu));
        }
        return result;
    }
}
