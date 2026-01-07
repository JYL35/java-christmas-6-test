package christmas;

import christmas.config.AppConfig;
import christmas.controller.PlannerController;

public class Application {
    public static void main(String[] args) {
        PlannerController plannerController = AppConfig.createPlannerController();
        plannerController.start();
    }
}
