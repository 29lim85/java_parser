package org.example.gui;

import javax.swing.*;  // Swing: Because we like our GUIs to have style - NO WE DO NOT CARE !
import java.awt.*;     // AWT: For when you need to paint the town (or your GUI) red.
import java.io.*;      // File I/O: Because data needs to go somewhere, right?
import java.util.Properties;  // Properties: For when you need to save data but are too stupid  to use a database.

// THIS IS A VERY IMPORTANT LINE. IT REPRESENTS THE HOLY POWER OF OOP JAVA MESS
public class InfoPopupGUI extends GUI {  // Extending GUI: Because we like to inherit the chaos.

    private static final String CONFIG_FILE = "config.properties";  // Config file: Where our program's secrets are stored.
    private static final String SHOW_POPUP_KEY = "showPopup";  // Key to show the popup: LET SUFFERING for USERS continued.

    public InfoPopupGUI() {
        if (shouldShowPopup()) {  // Check if the user can handle another popup.JUST TO BE SHORE :)
            showInfoPopup();  // Unleash the popup! LET IT FLY...
        }
    }

    private boolean shouldShowPopup() {
        Properties config = loadConfig();  // Load our mysterious config file.(i google it like for 15 minute to do it , chat did it less than 5sec. )
        return Boolean.parseBoolean(config.getProperty(SHOW_POPUP_KEY, "true"));  // Default to true, because more popups = more fun!
    }

    private void showInfoPopup() {
        JPanel panel = new JPanel(new BorderLayout());  // A panel to hold all our popup elements.
        JLabel label = new JLabel("<html><h1>Welcome to the HTML Parser Program!</h1><h3>Brace yourself for a wild ride through the labyrinth of HTML!<br> Remember, in the world of HTML, even divs need a little structure. \uD83D\uDD75️\u200D♂️\uD83D\uDD0D </h3><br><br>" +
                "Instructions:<br>" +
                "1. Enter the URL of the webpage you want to parse.<br>" +
                "2. Click 'Parse' to extract the data.<br>" +
                "3. Click 'Export to Excel' to save the extracted data to an Excel file.<br>"+
                "4. I am too lazy to do decent style.css file" +
                "<p>PS: This is made just for fun, so don't expect much... \uD83C\uDF89 </p></html>");  // A label with some HTML magic to make it look fancy.
        JCheckBox checkBox = new JCheckBox("Don't show this again");  // Checkbox to stop the popup madness.

        panel.add(label, BorderLayout.CENTER);  // Add the label to the center of the panel.
        panel.add(checkBox, BorderLayout.SOUTH);  // Add the checkbox to the bottom of the panel.

        JOptionPane.showMessageDialog(this, panel, "Program Information", JOptionPane.INFORMATION_MESSAGE);  // Show the popup dialog.

        if (checkBox.isSelected()) {  // If the user checks the box...
            saveConfig(SHOW_POPUP_KEY, "false");  // Save the user's preference to stop the popup madness.
        }
    }

    private Properties loadConfig() {
        Properties config = new Properties();  // Create a new Properties object.
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {  // Try to open the config file.
            config.load(input);  // Load the properties from the file.
        } catch (IOException ex) {  // If there's an issue loading the file...
            // If there's an issue loading the file, return default properties
            // ...we'll just pretend nothing happened.
        }
        return config;  // Return the loaded or default properties.
    }

    private void saveConfig(String key, String value) {
        Properties config = new Properties();  // Create a new Properties object.
        config.setProperty(key, value);  // Set the key-value pair.
        try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {  // Try to open the config file for writing.
            config.store(output, null);  // Save the properties to the file.
        } catch (IOException ex) {  // If there's an issue saving the file...
            ex.printStackTrace();  // Print the stack trace to console. Debugging fun!
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {  // Schedule a job for the event-dispatching thread.
            InfoPopupGUI parserGUI = new InfoPopupGUI();  // Create an instance of InfoPopupGUI.
            parserGUI.setVisible(true);  // Make the GUI visible.
        });
    }
}
