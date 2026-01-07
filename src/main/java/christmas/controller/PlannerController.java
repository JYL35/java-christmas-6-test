package christmas.controller;

import christmas.util.Parser;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class PlannerController {

    private final InputView inputView;
    private final OutputView outputView;

    public PlannerController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        try {
            int visitDay = readVisitDay();
            Map<String, Integer> order = readOrder();
        } catch (RuntimeException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private int readVisitDay() {
        while (true) {
            try {
                String inputVisitDay = inputView.readInputVisitDay();
                return Parser.parseVisitDay(inputVisitDay);
            } catch (IllegalArgumentException e) {
                outputView.printError(e);
            }
        }
    }

    private Map<String, Integer> readOrder() {
        while (true) {
            try {
                String inputOrder = inputView.readInputOrder();
                return Parser.parseOrder(inputOrder);
            } catch (IllegalArgumentException e) {
                outputView.printError(e);
            }
        }
    }
}
