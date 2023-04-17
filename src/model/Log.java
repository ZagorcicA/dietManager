package model;

/*
 * The Log class is a Java class that maps the values stored inside the
 * log.csv file which.
 * Instances of this class each represent the values the user wishes to store in the application.
 */
import java.util.*;

public class Log {

    // log attributes needed in the csv file
    private String year;
    private String month;
    private String day;
    // making 2 maps to have the food linked with the quantity that has been
    // consumed
    // that day
    private ArrayList<Food> dailyFoodConsumed = new ArrayList<>();
    private ArrayList<Double> dailyQuantityConsumed = new ArrayList<>();
    private double weight = -1;
    private double calorieLimit = -1;

    // default constructor
    public Log() {
    }

    public Log(String year, String month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.weight = 68.0F;
        this.calorieLimit = 2000.0F;
    }

    public String printF(String date) {

        StringBuilder sBuilder = new StringBuilder();

        for (int i = 0; i < dailyFoodConsumed.size(); i++) {

            String foodName = dailyFoodConsumed.get(i).getName();
            String quantity = String.format("%.1f", dailyQuantityConsumed.get(i));

            sBuilder.append(date + ",f," + foodName + "," + quantity + "\n");

        }

        String finalString = "";

        if (sBuilder.length() > 0) {
            finalString = sBuilder.toString().substring(0, sBuilder.length() - 1);
        }

        return finalString;
    }

    public String printW() {
        return "w," + getWeight();
    }

    public String printC() {
        return "c," + getCalorieLimit();
    }

    // a toString method which formats the attributes as they are read from and
    // written to the file
    @Override
    public String toString() {
        return "Log [year=" + year + ", month=" + month + ", day=" + day + ", dailyFoodConsumed=" + dailyFoodConsumed
                + ", dailyQuantityConsumed=" + dailyQuantityConsumed + ", weight=" + weight + ", calorieLimit="
                + calorieLimit + "]";
    }

    // mutators for class attributes
    public String getDate() {
        return getYear() + "," + getMonth() + ","
                + getDay();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCalorieLimit() {
        return calorieLimit;
    }

    public void setCalorieLimit(double calorieLimit) {
        this.calorieLimit = calorieLimit;
    }

    // adding food and the quantity consumed to the list of foods consumed on that
    // specific day that is to be added to that date entry in the log file
    public void addFood(Food item, double quantity) {
        dailyFoodConsumed.add(item);
        dailyQuantityConsumed.add(quantity);
    } // end of add food

    public ArrayList<Food> getDailyFoodConsumed() {
        return dailyFoodConsumed;
    }

    public void setDailyFoodConsumed(ArrayList<Food> dailyFoodConsumed) {
        this.dailyFoodConsumed = dailyFoodConsumed;
    }

    public ArrayList<Double> getDailyQuantityConsumed() {
        return dailyQuantityConsumed;
    }

    public void setDailyQuantityConsumed(ArrayList<Double> dailyQuantityConsumed) {
        this.dailyQuantityConsumed = dailyQuantityConsumed;
    }

} // log class end
