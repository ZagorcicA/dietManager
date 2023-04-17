package controller;

import java.awt.event.*;

import view.DMView;

public class IngredientListener implements ActionListener {

    private DMView view;

    public IngredientListener(DMView view) {
        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {

        String quantityText = this.view.getIngredientQuantityText();
        double quantity = 0;

        try {
            quantity = Double.parseDouble(quantityText);
        } catch (NumberFormatException nfe) {
            this.view.showError("Please enter a valid quantity");
            return;
        }

        String foodItem = this.view.getIngredientComboBoxSelectedItem();

        this.view.addRecipeFood(foodItem + ", " + quantity);

    }

}
