package gui;

import gui.CicuitSimulatorMain;
import view.CircuitSimulator;

import javax.swing.*;

public class Main {

    public static final String VERSION = "v0.3Alpha";

    public static void main(String[] args) {
        System.out.println("Hello World!");


        /*
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        */

        CircuitSimulator.loadPreferences();

        UIManager.put("swing.boldMetal", Boolean.FALSE);
        JFrame frame = new JFrame("Circuits Simulator " + VERSION);

        CicuitSimulatorMain csMain = new CicuitSimulatorMain();
        frame.setJMenuBar(csMain.createMenu());
        frame.setContentPane(csMain.getRootPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
