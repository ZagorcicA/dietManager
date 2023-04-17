package model;

/**
 * 
 * This Foods class represents an abstract class for the composite pattern.
 * 
 * It will be extended by BasicFood and Recipe classes, and it will have the
 * 
 * common methods and attributes that these classes share.
 */
public abstract class Food {

    protected String name;
    protected double calories;
    protected double fat;
    protected double carbs;
    protected double protein;

    // Getters and setters for the class attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

}
