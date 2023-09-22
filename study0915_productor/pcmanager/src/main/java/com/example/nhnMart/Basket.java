package com.example.nhnMart;

import java.util.*;

public class Basket {
    // private final List<Food> foodList = new ArrayList<>();
    // private static final ThreadLocal<List<Food>> foodLocal = new ThreadLocal<>();
    private static final ThreadLocal<List<Food>> foodLocal = ThreadLocal.withInitial(ArrayList::new);

    public static void addFood(Food food) {
        // foodList.add(food);
        // foodLocal.set(new ArrayList<>());
        foodLocal.get().add(food);
    }

    public static List<Food> getFoodList() {
        return foodLocal.get();
    }

    public static void reset() {
        foodLocal.remove();
    }

    public static String info() {
        List<Food> foods = foodLocal.get();
        StringBuilder stringBuilder = new StringBuilder();
        for (Food food : foods) {
            stringBuilder.append(food);
            stringBuilder.append(System.lineSeparator()); // \n같은거. 이거를 쓰는 이유? =>'\n'이 플랫폼마다 달라서 그럼.
        }
        return stringBuilder.toString();
    }

}
