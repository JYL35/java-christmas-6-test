package christmas.config;

import christmas.controller.PlannerController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class AppConfig {

    public static PlannerController createPlannerController() {
        return new PlannerController(new InputView(), new OutputView());
    }
}
