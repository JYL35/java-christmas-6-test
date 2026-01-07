package christmas.dto;

import java.util.Map;

public record OrderSheet(
        int visitDay,
        Map<String, Integer> order
) {
}
