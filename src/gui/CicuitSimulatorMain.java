package gui;

import view.CircuitPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by GR5 on 24.03.15.
 */
public class CicuitSimulatorMain {
    private JPanel rootPanel;
    private JButton zamknijButton;
    private CircuitPanel circuitPanel;

    public CicuitSimulatorMain() {
        zamknijButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        circuitPanel = new CircuitPanel();
    }
}
