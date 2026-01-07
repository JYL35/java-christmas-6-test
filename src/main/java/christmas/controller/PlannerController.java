package christmas.controller;

import christmas.dto.OrderSheet;
import christmas.dto.PlannerResult;
import christmas.service.PlannerService;
import christmas.util.Parser;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class PlannerController {

    private final PlannerService plannerService;
    private final InputView inputView;
    private final OutputView outputView;

    public PlannerController(PlannerService plannerService, InputView inputView, OutputView outputView) {
        this.plannerService = plannerService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        try {
            OrderSheet orderSheet = new OrderSheet(readVisitDay(), readOrder());
            PlannerResult plannerResult = plannerService.calculateBenefits(orderSheet);
            outputView.printStartPhrase(orderSheet);
            outputView.printPlannerResult(plannerResult);
        } catch (IllegalArgumentException e) {
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
