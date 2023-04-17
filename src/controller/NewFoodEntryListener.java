package controller;

import java.awt.event.*;

import view.DMView;

public class NewFoodEntryListener implements KeyListener {

    private DMView view;

    public NewFoodEntryListener(DMView view) {
        this.view = view;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        return;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        String foodName = this.view.getNewFoodFoodNameText();
        String calorie = this.view.getNewFoodCalorieText();
        String carbs = this.view.getNewFoodCarbsText();
        String proteins = this.view.getNewFoodProteinsText();
        String fat = this.view.getNewFoodFatText();

        if (!foodName.isEmpty() && !calorie.isEmpty() && !carbs.isEmpty() && !proteins.isEmpty() && !fat.isEmpty()) {
            this.view.enableNewFoodButton();
            return;
        }

        this.view.disableNewFoodButton();

    }

}
