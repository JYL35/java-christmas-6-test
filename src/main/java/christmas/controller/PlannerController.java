package christmas.controller;

import christmas.util.Parser;
import christmas.view.InputView;
import christmas.view.OutputView;

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
}
