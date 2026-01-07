package christmas.view;

import christmas.domain.GiveawayEvent;
import christmas.dto.EventDiscount;
import christmas.dto.OrderSheet;
import christmas.dto.PlannerResult;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printStartPhrase(OrderSheet orderSheet) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", orderSheet.visitDay());
        printNewLine();
        printNewLine();
        printOrder(orderSheet.order());
    }

    public void printPlannerResult(PlannerResult plannerResult) {
        printResult("<할인 전 총주문 금액>", formattedUnit(plannerResult.totalPrice()));
        String giveawayMenu = plannerResult.giveawayMenu().name();
        if (giveawayMenu.equals(GiveawayEvent.GIVEAWAY_EVENT.getGiveaway().name())) {
            giveawayMenu += " 1개";
        }
        printResult("<증정 메뉴>", giveawayMenu);
        printBenefits("<혜택 내역>", plannerResult.eventDiscounts());
        printResult("<총혜택 금액>", "-" + formattedUnit(plannerResult.totalDiscount()));
        int totalAmount = plannerResult.totalPrice() - plannerResult.totalDiscount() + plannerResult.giveawayMenu().getPrice();
        printResult("<할인 후 예상 결제 금액>", formattedUnit(totalAmount));
        printResult("<12월 이벤트 배지>", plannerResult.badge().getName());
    }

    private String formattedUnit(int amount) {
        return String.format("%,d원", amount);
    }

    private void printResult(String type, String contents) {
        System.out.println(type);
        System.out.println(contents);
        printNewLine();
    }

    private void printBenefits(String type, List<EventDiscount> eventDiscounts) {
        System.out.println(type);
        if (eventDiscounts.isEmpty()) {
            System.out.println("없음");
        }
        for (EventDiscount eventDiscount : eventDiscounts) {
            System.out.printf("%s: -%s", eventDiscount.type(), formattedUnit(eventDiscount.discountAmount()));
            printNewLine();
        }
        printNewLine();
    }

    private void printOrder(Map<String, Integer> order) {
        System.out.println("<주문 메뉴>");
        for (String menu : order.keySet()) {
            System.out.printf("%s %d개", menu, order.getOrDefault(menu, 0));
            printNewLine();
        }
        printNewLine();
    }

    public void printError(RuntimeException e) {
        System.out.println(e.getMessage());
        printNewLine();
    }

    private static void printNewLine() {
        System.out.print(System.lineSeparator());
    }
}
