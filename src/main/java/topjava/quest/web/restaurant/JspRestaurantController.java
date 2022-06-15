package topjava.quest.web.restaurant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import topjava.quest.model.Restaurant;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Objects;

@Controller
@RequestMapping("/restaurants")
public class JspRestaurantController extends AbstractRestaurantController {

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("restaurant", new Restaurant(""));
        return "restaurantForm";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("restaurant", super.get(getRestaurantId(request)));
        return "restaurantForm";
    }

    @PostMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getRestaurantId(request));
        return "redirect:/restaurants";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {
        Restaurant restaurant = new Restaurant(getRestaurantId(request),
                request.getParameter("name"),
                Integer.parseInt(request.getParameter("rating")), new ArrayList<>());
        if (request.getParameter("id").isEmpty()) {
            super.create(restaurant);
        } else {
            super.update(restaurant, getRestaurantId(request));
        }
        return "redirect:/restaurants";
    }

    @GetMapping("/filter")
    public String getBetweenRating(HttpServletRequest request, Model model) {
        int startRating = Integer.parseInt(request.getParameter("startRating"));
        int endRating = Integer.parseInt(request.getParameter("endRating"));
//        model.addAttribute();
        return "restaurants";
    }

    private int getRestaurantId(HttpServletRequest request) {
        return Integer.parseInt(Objects.requireNonNull(request.getParameter("id")));
    }

}
