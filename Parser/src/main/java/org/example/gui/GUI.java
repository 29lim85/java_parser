package org.example.gui;

import org.example.parser.HTMLParser;
import org.example.exporter.ExcelExporter;

import javax.swing.*;  // Swing: Because plain text interfaces are boring.
import javax.swing.table.DefaultTableModel;  // DefaultTableModel: Because tables are the new black.
import java.awt.*;  // AWT: Making your GUIs slightly prettier since 1995.
import java.awt.event.ActionEvent;  // ActionEvent: The thing that happens when you click stuff.
import java.awt.event.ActionListener;  // ActionListener: The thing that listens to the thing that happens when you click stuff.
import java.io.IOException;  // IOException: For when you try to do IO and the universe says 'no'.

public class GUI extends JFrame {  // Extending JFrame: Because why not inherit all the things?

    private JTextField urlTextField;  // URL input field: Type your URLs here.
    private JButton parseButton;  // Parse button: Click it to unleash the parsing magic.
    private JButton exportButton;  // Export button: Click it to save your hard-earned data.
    private JTable resultTable;  // Result table: Where parsed HTML elements go to hang out.
    private DefaultTableModel tableModel;  // Table model: Keeps track of what goes in the table.
    private HTMLParser htmlParser;  // HTML parser: The hero of our story.
    private ExcelExporter excelExporter;  // Excel exporter: Because data needs a good home.

    public GUI() {
        htmlParser = new HTMLParser();  // Creating a new HTMLParser: Because parsing doesn't happen by itself.
        excelExporter = new ExcelExporter();  // Creating a new ExcelExporter: Because spreadsheets rule the world.

        setTitle("HTML Parser was compiled by Lukov with big help from chatGPT4o ");  // Set the title: Give credit where credit is due.
        setSize(800, 600);  // Set the size: Bigger is better.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Close operation: Because nobody likes zombies.
        setLayout(new BorderLayout());  // Set layout: BorderLayout, because it's fancy.

        JPanel topPanel = new JPanel();  // Top panel: Holds our lovely input fields and buttons.
        topPanel.setLayout(new FlowLayout());  // FlowLayout: Because we like things to flow.
        JLabel urlLabel = new JLabel("URL:");  // URL label: So you know where to type the URL.
        urlTextField = new JTextField(30);  // URL text field: Type the URL here. No pressure.
        parseButton = new JButton("Parse");  // Parse button: Click me to start parsing.
        parseButton.addActionListener(new ParseButtonListener());  // Add action listener to parse button: It needs to do something.
        exportButton = new JButton("Export to Excel");  // Export button: Click me to save your data.
        exportButton.addActionListener(new ExportButtonListener());  // Add action listener to export button: It needs to do something too.

        topPanel.add(urlLabel);  // Add URL label to top panel: So you know where to type.
        topPanel.add(urlTextField);  // Add URL text field to top panel: So you can type.
        topPanel.add(parseButton);  // Add parse button to top panel: So you can click.
        topPanel.add(exportButton);  // Add export button to top panel: So you can save.

        tableModel = new DefaultTableModel(new Object[]{"Tag", "Attribute", "Content"}, 0);  // Table model: Keeping track of tags, attributes, and content.
        resultTable = new JTable(tableModel);  // Result table: Showing off our parsed data.
        JScrollPane scrollPane = new JScrollPane(resultTable);  // Scroll pane: Because our data might need room to grow.

        add(topPanel, BorderLayout.NORTH);  // Add top panel to frame: At the top, obviously.
        add(scrollPane, BorderLayout.CENTER);  // Add scroll pane to frame: In the center, where it belongs.
    }

    private class ParseButtonListener implements ActionListener {  // Inner class: Because the parse button needs a brain.
        @Override
        public void actionPerformed(ActionEvent e) {  // This happens when you click the parse button.
            String url = urlTextField.getText();  // Get the URL from the text field.
            try {
                tableModel.setRowCount(0);  // Clear previous results: Out with the old, in with the new.
                DefaultTableModel parsedData = htmlParser.parseURL(url);  // Parse the URL: HTMLParser, do your thing!
                for (int i = 0; i < parsedData.getRowCount(); i++) {  // Loop through parsed data: Because we need to add it to the table.
                    tableModel.addRow(new Object[]{  // Add a new row to the table model.
                            parsedData.getValueAt(i, 0),  // Tag: Check.
                            parsedData.getValueAt(i, 1),  // Attribute: Check.
                            parsedData.getValueAt(i, 2)   // Content: Check.
                    });
                }
            } catch (IOException ex) {  // Catch any IOExceptions: Because stuff happens.
                JOptionPane.showMessageDialog(GUI.this, "Error fetching URL: " + ex.getMessage(),  // Show error message: Oops!
                        "Error", JOptionPane.ERROR_MESSAGE);  // Title: Error. Type: Error message.
            }
        }
    }

    private class ExportButtonListener implements ActionListener {  // Inner class: Because the export button needs a brain too.
        @Override
        public void actionPerformed(ActionEvent e) {  // This happens when you click the export button.
            JFileChooser fileChooser = new JFileChooser();  // File chooser: So you can choose where to save.
            int option = fileChooser.showSaveDialog(GUI.this);  // Show save dialog: Make your choice.
            if (option == JFileChooser.APPROVE_OPTION) {  // If you chose to save...
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();  // Get the selected file path.
                if (!filePath.endsWith(".xlsx")) {  // If the file doesn't end with .xlsx...
                    filePath += ".xlsx";  // Add .xlsx to the file path: Because Excel demands it.
                }

                try {
                    excelExporter.exportToExcel(resultTable.getModel(), filePath);  // Export data to Excel: Make it so!
                } catch (IOException ex) {  // Catch any IOExceptions: Because stuff happens.
                    JOptionPane.showMessageDialog(GUI.this, "Error exporting data: " + ex.getMessage(),  // Show error message: Oops again!
                            "Error", JOptionPane.ERROR_MESSAGE);  // Title: Error. Type: Error message.
                }
            }
        }
    }

    public static void main(String[] args) {  // Main method: Because every program needs one.
        SwingUtilities.invokeLater(() -> {  // Invoke later: Because the GUI needs to be created on the event dispatch thread.
            GUI parserGUI = new GUI();  // Create a new instance of GUI: The star of our show.
            parserGUI.setVisible(true);  // Make the GUI visible: Let the fun begin!
        });
    }
}
