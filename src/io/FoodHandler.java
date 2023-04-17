package io;

import java.io.*;
import java.util.*;

import model.BasicFood;
import model.Food;
import model.Recipe;

public class FoodHandler {

    private static ArrayList<Food> foods = new ArrayList<Food>();

    private static String FILE_PATH = null;

    public static ArrayList<Food> load(String fileName) {

        Scanner scan = null;
        FILE_PATH = "csv/" + fileName;

        try {

            File foodFile = new File(FILE_PATH);

            if (!foodFile.exists()) {
                foodFile.createNewFile();
            }

            scan = new Scanner(new BufferedReader(new FileReader(foodFile)));

            while (scan.hasNextLine()) {

                String line = scan.nextLine();
                String[] attributes = line.split(",");

                // check if the entry is an instance of basic foods
                if (attributes[0].equals("b")) {

                    try {

                        String name = attributes[1];
                        double calories = Double.parseDouble(attributes[2]);
                        double fat = Double.parseDouble(attributes[3]);
                        double carbs = Double.parseDouble(attributes[4]);
                        double protein = Double.parseDouble(attributes[5]);

                        BasicFood item = new BasicFood(name, calories, fat, carbs, protein);

                        foods.add(item);

                    } catch (NumberFormatException nfe) {
                        System.out.println("Error when loading");
                        System.exit(0);
                    }

                } else if (attributes[0].equals("r")) {

                    Recipe recipe = new Recipe(attributes[1]);

                    for (int i = 2; i < attributes.length; i = i + 2) {

                        for (Food food : foods) {
                            if (food.getName().equals(attributes[i])) {
                                recipe.addIngredient(food, Double.parseDouble(attributes[i + 1]));
                                break;
                            }
                        }
                    }

                    foods.add(recipe);

                }
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (scan != null) {
                scan.close();
            }
        }

        return foods;
    }

    public static void saveFoodsFile() {

        File foodsCSV = new File(FILE_PATH);

        try {

            // instantiate a print writer which will write to the file to save the input
            // data
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(foodsCSV, false)));

            // loop through the map of all basic food entries
            for (Food food : foods) {

                if (food instanceof BasicFood) {

                    // write to the file and add the char 'f' that marks this as a basic food entry
                    // in the file
                    pw.println("b," + food.toString());

                } else if (food instanceof Recipe) {

                    pw.println("r," + food.toString());

                }

            }

            // close the print writer
            pw.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    } // end of save to food file method
      // saving to the log.csv file

}
