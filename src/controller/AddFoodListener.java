package controller;

import view.*;

import java.awt.event.*;

public class AddFoodListener implements ActionListener {

    private DMView view;

    public AddFoodListener(DMView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.view.askNewFood();

    }
}