package model;

import java.util.*;

public class Recipe extends Food {

    // Attributes
    private List<Food> foods = new ArrayList<>();
    private List<Double> countList = new ArrayList<>();

    public Recipe(String name) {
        this.name = name;

    }

    // Implement the abstract methods
    public double getCalories() {
        double totalCalories = 0.0;
        for (int i = 0; i < foods.size(); i++) {
            totalCalories += foods.get(i).getCalories() * countList.get(i);
        }
        return totalCalories;
    }

    public double getFat() {
        double totalFat = 0.0;
        for (int i = 0; i < foods.size(); i++) {
            totalFat += foods.get(i).getFat() * countList.get(i);
        }
        return totalFat;
    }

    public double getCarbs() {
        double totalCarbs = 0.0;
        for (int i = 0; i < foods.size(); i++) {
            totalCarbs += foods.get(i).getCarbs() * countList.get(i);
        }
        return totalCarbs;
    }

    public double getProtein() {
        double totalProtein = 0.0;
        for (int i = 0; i < foods.size(); i++) {
            totalProtein += foods.get(i).getProtein() * countList.get(i);
        }
        return totalProtein;
    }

    // Add an ingredient to the recipe
    public void addIngredient(Food ingredient, double count) {
        foods.add(ingredient);
        countList.add(count);
    }

    public Food removeIngredient(int index) {
        Food food = foods.remove(index);
        countList.remove(index);
        return food;
    }

    public Food getIngredient(int index) {
        return foods.get(index);
    }

    public Double getCount(int index) {
        return countList.get(index);
    }

    @Override
    public String toString() {
        String print = name + ",";
        for (int i = 0; i < foods.size(); i++) {
            print += foods.get(i).getName() + "," + countList.get(i) + ",";
        }
        System.out.println(print.substring(0, print.length() - 1));
        return print.substring(0, print.length() - 1);
    }

}