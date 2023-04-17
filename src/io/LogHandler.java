package io;

import java.io.*;
import java.util.*;

import model.Food;
import model.Log;

public class LogHandler {

    private static ArrayList<Log> logList = new ArrayList<>();
    private static String FILE_PATH = null;

    // method that reads and loads the data from the log.csv file that re to be
    // displayed on the UI to show the latest relevant data the user added
    public static ArrayList<Log> load(String fileName, ArrayList<Food> allFoods) {

        Scanner scan = null;

        FILE_PATH = "csv/" + fileName;
        String[] attributes = null;

        try {

            // check if the file that was asked for exists
            if (!new File(FILE_PATH).exists()) {

                // if not, create a file with that name
                new File(FILE_PATH).createNewFile();
            }

            // initializing the scanner which will read from the file that was passed in
            scan = new Scanner(new BufferedReader(new FileReader(new File(FILE_PATH))));

            // loop for as long as there are lines to be read to make sure we have all the
            // data
            while (scan.hasNextLine()) {

                String line = scan.nextLine();
                // store the lines that have been read inside an array of attributes and remove
                // the commas
                attributes = line.split(",");

                for (int i = 0; i < attributes.length; i++) {
                    attributes[i] = attributes[i].trim();
                }

                // extracting the log for that specific date and passing in the date values as
                // parameters
                Log logEntry = null;

                boolean add = false;

                for (Log log : logList) {
                    if (log.getDate().equals(attributes[0] + "," + attributes[1] + "," + attributes[2])) {
                        logEntry = log;
                        break;
                    }
                }

                if (logEntry == null) {
                    logEntry = new Log(attributes[0], attributes[1], attributes[2]);
                    add = true;
                }

                // recognizing which log is necessary by checking the fourth value inside the
                // attributes array, which signalizes whether it is a food, calories or weight
                // log
                switch (attributes[3]) {

                    // new food log entry
                    case "f":

                        try {
                            String name = attributes[4];
                            Float count = Float.parseFloat(attributes[5]);

                            for (Food food : allFoods) {
                                if (food.getName().equals(name)) {
                                    logEntry.addFood(food, count);
                                }
                            }
                        } catch (NumberFormatException nfe) {
                            System.out.println("Error when loading");
                            System.exit(0);
                        }
                        break;

                    // new calorie log entry
                    case "c":

                        try {
                            Float calories = Float.parseFloat(attributes[4]);
                            logEntry.setCalorieLimit(calories);
                        } catch (NumberFormatException nfe) {
                            System.out.println("Error when loading");
                            System.exit(0);
                        }
                        break;

                    // new weight log entry
                    case "w":

                        try {
                            // get the weight value
                            Float weight = Float.parseFloat(attributes[4]);
                            logEntry.setWeight(weight);
                        } catch (NumberFormatException nfe) {
                            System.out.println("Error when loading");
                            System.exit(0);
                        }
                        break;

                }

                // add log entry to the log map
                if (add) {
                    logList.add(logEntry);
                }

            }

        } catch (IOException ioe) {

            ioe.printStackTrace();
        }

        return logList;
    } // end of load log

    public static void saveLogs() {

        File logCSV = new File(FILE_PATH);

        try {

            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(logCSV, false)));

            for (Log log : logList) {

                String date = log.getDate();

                pw.println(date + "," + log.printW());
                pw.println(date + "," + log.printC());

                String foodF = log.printF(date);

                if (!foodF.isEmpty()) {
                    pw.println(foodF);
                }

            }

            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
