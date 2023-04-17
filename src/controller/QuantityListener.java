package controller;

import java.awt.event.*;

import view.DMView;

public class QuantityListener implements KeyListener {

    private DMView view;

    public QuantityListener(DMView view) {
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
        String quantityText = this.view.getFoodQuantityText();
        if (!quantityText.isEmpty()) {
            this.view.enableLogButton();
            return;
        }
        this.view.disableLogButton();
    }

}