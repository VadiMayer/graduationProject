package topjava.quest;

import topjava.quest.model.Dish;

import java.time.LocalDateTime;
import java.util.List;

import static topjava.quest.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {

    public static final List<Dish> dishList = List.of
            (
                    new Dish(START_SEQ + 19, "тест", 50, 1, LocalDateTime.of(2022, 4, 14, 0, 0)),
                    new Dish(START_SEQ + 10, "Суп \"Жабо\"",1400,1, LocalDateTime.now()),
                    new Dish(START_SEQ + 11, "Фрикадельки \"Мисьён\"", 1850, 1, LocalDateTime.of(2022, 4, 14, 0, 0)),
                    new Dish(START_SEQ + 12, "Коктейль \"Агара\"", 100, 1, LocalDateTime.now()),
                    new Dish(START_SEQ + 13, "Котлеты \"Банпулье\"", 1540, 2, LocalDateTime.now()),
                    new Dish(START_SEQ + 14, "Салат \"Жандарм\"", 470, 2, LocalDateTime.of(2022, 4, 12, 23, 59)),
                    new Dish(START_SEQ + 15, "Суп \"Аладин\"", 720, 3, LocalDateTime.now()),
                    new Dish(START_SEQ + 16, "Второе \"Плов\"", 910, 4, LocalDateTime.now()),
                    new Dish(START_SEQ + 17, "Суп \"Анастасия\"", 847, 5, LocalDateTime.of(2022, 4, 10, 10, 0)),
                    new Dish(START_SEQ + 18, "Фрукты \"Питахайя\"", 260, 5, LocalDateTime.now())

            );
}
