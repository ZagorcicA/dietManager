package controller;

import java.awt.event.*;

import view.*;

public class RecipeNameListener implements KeyListener {

    private DMView view;

    public RecipeNameListener(DMView view) {
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
        String recipeName = this.view.getRecipeNameText();
        String quantityText = this.view.getIngredientQuantityText();
        if (!recipeName.isEmpty() && !quantityText.isEmpty()) {
            this.view.enableNewRecipeButton();
            return;
        }
        this.view.disableNewRecipeButton();
    }

}