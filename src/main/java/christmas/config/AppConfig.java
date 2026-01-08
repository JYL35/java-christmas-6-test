package christmas.config;

import christmas.controller.PlannerController;
import christmas.domain.DiscountPolicy;
import christmas.domain.policy.ChristmasDiscountPolicy;
import christmas.domain.policy.GiveawayEventPolicy;
import christmas.domain.policy.SpecialDiscountPolicy;
import christmas.domain.policy.WeekdayDiscountPolicy;
import christmas.domain.policy.WeekendDiscountPolicy;
import christmas.service.PlannerService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class AppConfig {

    public static PlannerController createPlannerController() {
        List<DiscountPolicy> discountPolicies = List.of(
                new ChristmasDiscountPolicy(),
                new WeekdayDiscountPolicy(),
                new WeekendDiscountPolicy(),
                new SpecialDiscountPolicy(),
                new GiveawayEventPolicy()
        );
        return new PlannerController(new PlannerService(discountPolicies), new InputView(), new OutputView());
    }
}
