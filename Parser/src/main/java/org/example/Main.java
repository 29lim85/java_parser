package org.example;
// This is the place where all main things happening..
import org.example.gui.InfoPopupGUI;  // Importing the GUI class because staring at plain text all day is boring.

import javax.swing.*;  // Swing: The library that puts the 'fun'(SUFFERING) in totally different LEVEL .

public class Main {
    public static void main(String[] args) { // It's been 10 years since I got introduce to Java, and I still don't understand WHY main(String[] args) I need to write code like this in this place???
        SwingUtilities.invokeLater(() -> {  //  Swing's rules, not mine.
            InfoPopupGUI parserGUI = new InfoPopupGUI();  // Creating an instance of our shiny new GUI. Behold its glory!
            parserGUI.setVisible(true);  // Making the GUI visible. Otherwise, it's like having a party and forgetting to invite people.
        });
    }
}
