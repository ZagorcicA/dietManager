package model;

/* The BasicFood class is a java class that maps the values stored inside the food.csv file which 
* defines individual new basic food entries and their values.
* this class each represents a food item with its nutritive values.
 */
public class BasicFood extends Food {

    // a parameterized constructor which sets all of the attributes
    public BasicFood(String name, double calories, double fat, double carbs, double protein) {
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }

    public BasicFood() {
    }

    public BasicFood(String name) {
        this.name = name;
    }

    // A toString method which formats the attributes as they are read from and
    // written to the file
    @Override
    public String toString() {
        return name + "," + calories + "," + fat + "," + carbs + "," + protein;
    }

}
