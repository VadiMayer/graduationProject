package topjava.quest.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import topjava.quest.service.DishService;
import topjava.quest.service.RestaurantService;

@Controller
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    private DishService dishService;

    private RestaurantService restaurantService;

    public String root() {
        log.info("root");
        return "index";
    }

    public String getRestaurant(Model model) {
        log.info("restaurants");
        model.addAttribute("restaurants");
        return "restaurants";
    }
}
