package christmas.view;

import java.util.Map;

public class OutputView {

    public void printStartPhrase(int visitDay) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", visitDay);
        printNewLine();
        printNewLine();
    }

    public void printOrder(Map<String, Integer> order) {
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
