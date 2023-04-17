package controller;

import java.awt.event.*;

import io.*;
import model.*;
import view.*;
import java.util.ArrayList;

class SaveNewFoodListener implements ActionListener {

    private DMModel model;
    private DMView view;

    public SaveNewFoodListener(DMModel model, DMView view) {
        this.model = model;
        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {

        String foodName = this.view.getNewFoodFoodNameText();
        String calorieText = this.view.getNewFoodCalorieText();
        String carbsText = this.view.getNewFoodCarbsText();
        String proteinsText = this.view.getNewFoodProteinsText();
        String fatText = this.view.getNewFoodFatText();

        double calorie = 0;
        double carbs = 0;
        double proteins = 0;
        double fat = 0;

        try {
            calorie = Double.parseDouble(calorieText);
            carbs = Double.parseDouble(carbsText);
            proteins = Double.parseDouble(proteinsText);
            fat = Double.parseDouble(fatText);
        } catch (NumberFormatException nfe) {
            this.view.showError("Please enter valid new food nutritional values");
            return;
        }
        ArrayList<Food> foods=this.model.getFoodList();
        for(Food food:foods){
            if(foodName.equals(food.getName())){
                this.view.showError("Name already exists");
                return;
            }
        }
        this.model.addNewFood(foodName, calorie, fat, carbs, proteins);
        this.view.updateComboBoxes(this.model.getFoodNameList());
        this.view.showSuccess("New Food added: " + foodName);

        FoodHandler.saveFoodsFile();
    }

}