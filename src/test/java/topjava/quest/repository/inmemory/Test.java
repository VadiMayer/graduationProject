package topjava.quest.repository.inmemory;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) throws Exception {

        User user = new User(1, "John");
        Item item2 = new Item(2, "book2", user);
        Item item3 = new Item(3, "book3", user);
        user.addItem(item2);
        user.addItem(item3);

        System.out.println(new ObjectMapper().writeValueAsString(item3));

//        try {
//            System.err.print(" 0");
//            try {
//                System.err.print(" 1");
//                if (true) {
//                    throw new RuntimeException();
//                }
//                System.err.print(" 2");
//            }
//            finally {
//                System.err.print(" 4"); // заходим всегда
//            }
//            System.err.print(" 5");     // заходим - выполнение УЖЕ в норме
//        } catch (Exception e) {
//            System.err.println(" 6");
//            e.printStackTrace();
//        }
//        finally {
//            System.err.print(" 7");     // заходим всегда
//        }
//        System.err.print(" 8");         // заходим - выполнение УЖЕ в норме


//        try {
//            int l = args.length;
//            int a = 10 / l;
//            try {
//                if (l == 1) l = 1 / (l - 1);
//                if (l == 2) args[10] = "10";
//            } catch (ArrayIndexOutOfBoundsException e) {
//                System.out.println("Index Out Of Bounds " + e);
//            }
//        } catch (ArithmeticException e) {
//            System.out.println("/ by zero " + e);
//        }

//        try {
//            try {
//                try {
//                    throw new Exception("Error");
//                } catch (Exception e3) {
//                    throw new Exception("Ошибка на первом уровне", e3);
//                }
//            } catch (Exception e2) {
//                throw new Exception("Ошибка на первом уровне", e2);
//            }
//        } catch (Exception e1) {
//            throw new Exception("Ошибка на первом уровне", e1);
//        }


//        try {
//            System.err.print(" 0");
//            try {
//                System.err.print(" 1");
//                // НИЧЕГО
//                System.err.print(" 2");
//            } catch (RuntimeException e) {
//                System.err.print(" 3"); // НЕ заходим - нет исключения
//            } finally {
//                System.err.print(" 4"); // заходим всегда
//            }
//            System.err.print(" 5");     // заходим - выполнение в норме
//        } catch (Exception e) {
//            System.err.print(" 6");     // НЕ заходим - нет исключения
//        } finally {
//            System.err.print(" 7");     // заходим всегда
//        }
//        System.err.print(" 8");         // заходим - выполнение в норме


//        A ab = new B();
//        A ac = new C();
//        B b = new B();
//        C c = new C();
//        b.add();
//        c.add();
//        ac.add();
//        ab.add();
//        System.out.println(b.strings);
//        System.out.println(c.strings);
//        System.out.println(ac.strings);
//        System.out.println(ab.strings);
//
//
//        List<Integer> list = new ArrayList<>();
//
//        Map<Integer,String> nameForId = new HashMap<>();
//
//        // Adding new value:
//        nameForId.computeIfPresent(1, (key, oldVal) -> {
//            System.out.printf("BiFunction(%d,%s) for new%n",
//                    key, oldVal);
//            return "Java";
//        });
//        System.out.println("After add new: " + nameForId);
//
//        // Updating:
//        nameForId.put(1, "Java");
//        nameForId.computeIfPresent(1, (key, oldVal) -> {
//            System.out.printf("BiFunction(%d,%s) update%n",
//                    key, oldVal);
//            return oldVal.concat("Script");
//        });
//        System.out.println("After update: " + nameForId);
//
//        // null removes:
//        nameForId.computeIfPresent(1, (key, oldVal) -> {
//            System.out.printf("BiFunction(%d,%s) -> null%n",
//                    key, oldVal);
//            return null;
//        });
//        System.out.println("After null: " + nameForId);
    }
}

abstract class A {
    protected List<String> strings = new ArrayList<>();
    protected void doing() {
        System.out.println("a");
    }
    abstract void add();
}

class B extends A {
    @Override
    void add() {
        strings.add("BBBBBBB");
    }
}

class C extends A {

    @Override
    void add() {
        strings.add("CCCCCCC");
    }
}

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
class User {
    public int id;
    public String name;

//    @JsonManagedReference
//    @JsonBackReference
    public List<Item> userItems = new ArrayList<>();

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addItem(Item item) {
        userItems.add(item);
    }
}


@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
class Item {
    public int id;
    public String itemName;

//    @JsonBackReference
//    @JsonManagedReference
    public User owner;

    public Item(int id, String itemName, User owner) {
        this.id = id;
        this.itemName = itemName;
        this.owner = owner;
    }
}
