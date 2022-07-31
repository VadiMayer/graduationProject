package topjava.quest.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import topjava.quest.model.Dish;
import topjava.quest.model.Restaurant;
import topjava.quest.model.User;
import topjava.quest.model.Vote;
import topjava.quest.repository.datajpa.DataJpaVoteRepository;
import topjava.quest.service.AbstractServiceTest;
import topjava.quest.service.VoteService;
import topjava.quest.to.DishTo;
import topjava.quest.to.RestaurantTo;

import java.time.LocalDate;
import java.util.*;

import static topjava.quest.model.AbstractBaseEntity.START_SEQ;

public class RestaurantsUtilTest extends AbstractServiceTest {

    @Autowired
    private static VoteService voteService;

    static DataJpaVoteRepository dataJpaVoteRepository;

//    @Test
//    public void

    public static final List<Dish> dishList = List.of(
            new Dish(START_SEQ + 11, "Фрикадельки \"Мисьён\"", 1850, 100005, LocalDate.of(2022, 4, 14)),
            new Dish(START_SEQ + 11, "Фрикадельки \"Мисьён\"", 1850, 100005, LocalDate.of(2022, 4, 14)),
            new Dish(START_SEQ + 11, "Фрикадельки \"Мисьён\"", 1850, 100005, LocalDate.of(2022, 4, 14)),
            new Dish(START_SEQ + 11, "Фрикадельки \"Мисьён\"", 1850, 100005, LocalDate.of(2022, 4, 14)),
            new Dish(START_SEQ + 11, "Фрикадельки \"Мисьён\"", 1850, 100005, LocalDate.of(2022, 4, 14))
    );

    public static void main(String[] args) {

//        System.out.println(getTORestsList(RestaurantTestData.rests, convertDishListInDishToList(DishTestData.dishList)));

//        System.out.println(getFilteredTOsForAdmin(RestaurantTestData.rests, DishTestData.dishList, false));

//        System.out.println(getFilteredRatingRestForUser(RestaurantTestData.rests, convertDishListInDishToList(DishTestData.dishList), 100, 145));

        Vote vote1 = new Vote(1, new User(), new Restaurant(), LocalDate.of(2022, 4, 14));
        Vote vote2 = new Vote(2, new User(), new Restaurant(), LocalDate.of(2022, 4, 14));
        System.out.println("DataJpaVoteRepository.getAll()");
        List<Vote> list = dataJpaVoteRepository.getAll();
        System.out.println("\n");
        System.out.println("DataJpaVoteRepository.save(vote1)");
        dataJpaVoteRepository.save(vote1);
        System.out.println("\n");
        System.out.println("DataJpaVoteRepository.getAll()");
        dataJpaVoteRepository.getAll();
        System.out.println("\n");
        System.out.println("VoteService.getAllForRestaurant(100005)");
        voteService.getAllForRestaurant(100005);
        System.out.println("\n");
        System.out.println("voteService.create(vote2)");
        voteService.create(vote2);
        System.out.println("\n");
        System.out.println("getAllForRestaurant(100005)");
        voteService.getAllForRestaurant(100005);
        System.out.println("\n");
        System.out.println("DataJpaVoteRepository.getAll()");
        dataJpaVoteRepository.getAll();

    }

    @Test
    public static List<RestaurantTo> getTORestsList(List<Restaurant> restaurants, List<DishTo> dishes) {
        return Util.getTORestsList(restaurants, dishes);
    }


    @Test
    public static List<RestaurantTo> getFilteredTOsForAdmin(List<Restaurant> restaurants, List<Dish> dishes, boolean filter) {
        return Util.getFilteredTOsForAdmin(restaurants, dishes, filter);
    }

    @Test
    public static List<DishTo> convertDishListInDishToList(List<Dish> dishes) {
        return Util.convertDishListInDishToList(dishes);
    }

//    @Test
//    public static List<RestaurantTo> getFilteredRatingRestForUser(List<Restaurant> rest, List<DishTo> dishes, int start, int end) {
//        return RestaurantsAndDishesUtil.getFilteredRatingRestForUser(rest, dishes, start, end);
//    }

}