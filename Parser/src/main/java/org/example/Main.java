package org.example;

import org.example.gui.InfoPopupGUI;  // Importing the GUI class because staring at plain text all day is boring.

import javax.swing.*;  // Swing: The library that puts the 'fun'(SUFFERING) in totally different LEVEL .

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {  //  Swing's rules, not mine.
            InfoPopupGUI parserGUI = new InfoPopupGUI();  // Creating an instance of our shiny new GUI. Behold its glory!
            parserGUI.setVisible(true);  // Making the GUI visible. Otherwise, it's like having a party and forgetting to invite people.
        });
    }
}
