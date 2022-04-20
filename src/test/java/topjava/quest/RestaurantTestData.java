package topjava.quest;

import topjava.quest.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

import static topjava.quest.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {

    public static final int NOT_FOUND = 10;

    public static final List<Restaurant> rests = List.of
            (
                    new Restaurant(START_SEQ + 5, "White rabbit", 201, 1, new ArrayList<>()),
                    new Restaurant(START_SEQ + 6, "Sixty", 153, 2, new ArrayList<>()),
                    new Restaurant(START_SEQ + 7, "Русский паб", 102, 3, new ArrayList<>()),
                    new Restaurant(START_SEQ + 8, "АндерСон", 126, 4, new ArrayList<>()),
                    new Restaurant(START_SEQ + 9, "Обломов", 145, 5, new ArrayList<>())
            );

}
