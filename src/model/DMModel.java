package model;

import java.util.*;

import io.FoodHandler;
import io.LogHandler;

/**
 * This class represents the model. It is completely independent of the user
 * interface and it can be used by a command line or web interface. The methods
 * inside this class revolve around the CRUD operations and are focused on
 * reading and writing data in the .csv files inside the application storage.
 *
 * The class contains attributes which represent the items that are written to
 * the files so as to enable manipulation of the information inside the
 * application's methods.
 *
 */
public class DMModel {

   // attributes
   private ArrayList<Food> foods = new ArrayList<>();
   private ArrayList<Log> logList = new ArrayList<>();

   // private Map<String, Double> caloriesCount = new LinkedHashMap<>();
   // private Map<String, Float> caloriesLimit = new LinkedHashMap<>();

   // private ArrayList<Recipe> recipes;
   // public Map<LocalDate, Log> logList = new LinkedHashMap<LocalDate, Log>();

   // constructor
   public DMModel() {

      // reading the files as soon as the model constructor is called so as to have
      // the data ready to be displayed and delivered upon request
      foods = FoodHandler.load("foods.csv");
      logList = LogHandler.load("log.csv", foods);

   }

   public BasicFood addNewFood(String name, double calories, double fat, double carbs, double protein) {
      BasicFood newBasicFood = new BasicFood(name, calories, fat, carbs, protein);
      foods.add(newBasicFood);
      return newBasicFood;
   }

   public Log newLog() {
      Log newLog = new Log();
      return newLog;
   }

   public void addToFood(ArrayList<Object> arrayList) {
      Food foodItem = (Food) arrayList.get(0);
      Log logItem = (Log) arrayList.get(1);
      foods.add(foodItem);
      addToLog(logItem);
   }

   public void addToLog(Log log) {
      logList.add(log);
   }

   public ArrayList<Log> getLog() {
      return logList;
   }

   public void setCaloriesLimit(String date, String limit) {
      this.getLogOnDate(date).setCalorieLimit(Float.parseFloat(limit));
   }

   public Log getLogOnDate(String date) {

      for (Log log : logList) {
         if (log.getDate().equals(date)) {
            return log;
         }
      }

      // 2016,10,10
      String[] dateInfo = date.split(",");
      Log newLog = new Log(dateInfo[0], dateInfo[1], dateInfo[2]);
      logList.add(newLog);
      return newLog;
   }

   public void addNewFoodToList(Food food) {
      this.foods.add(food);
   }

   public Log addLogFood(String foodName, String date) {
      Log log = this.getLogOnDate(date);
      if (log == null) {
         String[] dateInfo = date.split(",");
         log = new Log(dateInfo[0], dateInfo[1], dateInfo[2]);
         logList.add(log);
      }

      for (Food food : foods) {
         if (foodName.equals(food.getName())) {
            log.addFood(food, 1);
            return log;
         }
      }
      return log;
   }

   public ArrayList<Food> getFoodList() {
      return foods;
   }

   public ArrayList<String> getFoodNameList() {
      ArrayList<String> foodNames = new ArrayList<>();

      for (Food food : foods) {
         foodNames.add(food.getName());
      }

      return foodNames;
   }

   public void setFoods(ArrayList<Food> foods) {
      this.foods = foods;
   }

   public ArrayList<Log> getLogList() {
      return logList;
   }

   public void setLogList(ArrayList<Log> logList) {
      this.logList = logList;
   }
} // model class end
