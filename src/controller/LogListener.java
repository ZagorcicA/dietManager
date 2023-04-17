package controller;

import java.awt.event.*;
import java.util.*;

import io.*;
import model.*;
import view.*;

// ChangeListener will process the registered action events
class LogListener implements ActionListener {

    private DMModel model;
    private DMView view;

    public LogListener(DMModel model, DMView view) {
        this.model = model;
        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {

        String quantityText = this.view.getFoodQuantityText();
        double quantity = 0;

        try {
            quantity = Double.parseDouble(quantityText);
        } catch (NumberFormatException nfe) {
            this.view.showError("Please enter a valid quantity");
            return;
        }

        String date = this.view.getDate();

        Log log = this.model.getLogOnDate(date);
        log.setWeight(Double.parseDouble(this.view.getWeight()));
        log.setCalorieLimit(Double.parseDouble(this.view.getCaloriesLimit()));

        ArrayList<Food> foods = this.model.getFoodList();

        String foodItem = this.view.getFoodComboBoxSelectedItem();

        for (Food food : foods) {
            if (food.getName().equals(foodItem)) {
                log.addFood(food, quantity);
                this.view.updateCaloriesCount(food.getCalories()*quantity);
                this.view.updateCaloriesLimit(log.getCalorieLimit());
                this.view.updateProteinsCount(food.getProtein()*quantity);
                this.view.updateCarbsCount(food.getCarbs()*quantity);
                this.view.updateFatCount(food.getFat()*quantity);
                this.view.updatePercentage();
                break;
            }
        }

        System.out.println(this.model.getLog());

        this.view.addLogFood(foodItem + ", " + quantity);

        LogHandler.saveLogs();
    }

}