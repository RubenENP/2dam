package org.example;

import javax.swing.*;
import java.awt.*;
import org.example.JFecha;

public class Main {
    private static JFrame jFrame;

    private static Panel topPanel;
    private static Panel bottomPanel;

    private static JFecha jFecha;

    private static JButton jButton;


    public static void main(String[] args) {
        jFrame = new JFrame("FECHA AAA");
        jFrame.setSize(500, 300);
        jFrame.setVisible(true);

        jFecha = new JFecha(12, 12, 2005);
        topPanel = jFecha.getPanel();
        topPanel.setSize(500, 250);
        bottomPanel = new Panel();
        bottomPanel.setLayout(new CardLayout());
        jButton = new JButton("Comprobar");
        bottomPanel.add(jButton);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
        jFrame.getContentPane().add(splitPane);

    }
}