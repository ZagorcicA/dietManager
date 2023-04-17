package controller;

import java.awt.event.*;

import view.*;

public class QuantityIngredientListener implements KeyListener {

    private DMView view;

    public QuantityIngredientListener(DMView view) {
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
        String quantityText = this.view.getIngredientQuantityText();
        String recipeName = this.view.getRecipeNameText();
        if (!quantityText.isEmpty()) {
            this.view.enableAddIngredientButton();
            if (!recipeName.isEmpty()) {
                this.view.enableNewRecipeButton();
                return;
            }
            this.view.disableNewRecipeButton();
            return;
        }

        this.view.disableAddIngredientButton();
    }

}
