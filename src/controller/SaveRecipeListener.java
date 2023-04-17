package controller;

import java.awt.event.*;

import io.*;
import model.*;
import view.*;
import java.util.ArrayList;


class SaveNewRecipeListener implements ActionListener {

    private DMModel model;
    private DMView view;

    public SaveNewRecipeListener(DMModel model, DMView view) {
        this.model = model;
        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {

        String recipeName = this.view.getRecipeNameText();
        ArrayList<Food> foods = this.model.getFoodList();
        for (Food food : foods) {
            if (recipeName.equals(food.getName())) {
                this.view.showError("Name already exists");
                return;
            }
        }
        Recipe recipe = new Recipe(recipeName);

        String taText = this.view.getIngredientTextAreaText();

        String[] foodItems = taText.split("\n");

        for (String foodItem : foodItems) {

            String foodName = foodItem.split(",")[0];
            double foodQuantity = Double.parseDouble(foodItem.split(",")[1]);

            for (Food food : this.model.getFoodList()) {

                if (food.getName().equals(foodName)) {
                    recipe.addIngredient(food, foodQuantity);
                }
            }
        }

        this.model.addNewFoodToList(recipe);

        this.view.updateComboBoxes(this.model.getFoodNameList());
        this.view.showSuccess("New Recipe added: " + recipeName);

        FoodHandler.saveFoodsFile();
    }
}
