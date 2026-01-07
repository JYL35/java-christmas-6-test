package christmas.config;

import christmas.controller.PlannerController;
import christmas.service.PlannerService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class AppConfig {

    public static PlannerController createPlannerController() {
        return new PlannerController(new PlannerService(), new InputView(), new OutputView());
    }
}
