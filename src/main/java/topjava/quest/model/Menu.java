//package topjava.quest.model;
//
//import topjava.quest.to.DishTo;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//public class Menu {
//
//    private List<DishTo> dishTos;
//
//    private final int restaurantId;
//
//    private LocalDateTime updateDate;
//
//    private boolean notRequiresAnUpdate;
//
//    public List<DishTo> getDishTos() {
//        return dishTos;
//    }
//
//    public void setDishTos(List<DishTo> dishTos) {
//        this.dishTos = dishTos;
//    }
//
//    public int getRestaurantId() {
//        return restaurantId;
//    }
//
//    public void setUpdateDate(LocalDateTime updateDate) {
//        this.updateDate = updateDate;
//    }
//
//    public void setNotRequiresAnUpdate(boolean notRequiresAnUpdate) {
//        this.notRequiresAnUpdate = notRequiresAnUpdate;
//    }
//
//    public LocalDateTime getUpdateDate() {
//        return updateDate;
//    }
//
//    public boolean isNotRequiresAnUpdate() {
//        return notRequiresAnUpdate;
//    }
//
//    public Menu(List<DishTo> dishTos, int restaurantId, LocalDateTime updateDate, boolean notRequiresAnUpdate) {
//        this.dishTos = dishTos;
//        this.restaurantId = restaurantId;
//        this.updateDate = updateDate;
//        this.notRequiresAnUpdate = notRequiresAnUpdate;
//    }
//}