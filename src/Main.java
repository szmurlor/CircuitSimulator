import javax.swing.*;

public class Main {

    public static final String VERSION = "v0.1Alpha";

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

        JFrame frame = new JFrame("Circuits Simulator " + VERSION);
        frame.setContentPane(new CicuitSimulatorMain().getRootPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
