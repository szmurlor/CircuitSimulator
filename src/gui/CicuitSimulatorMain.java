package gui;

import view.CircuitPanel;
import view.ResistorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by GR5 on 24.03.15.
 */
public class CicuitSimulatorMain implements ActionListener {
    private JPanel rootPanel;
    private JButton zamknijButton;
    private CircuitPanel circuitPanel;
    private JToolBar toolbar;

    public CicuitSimulatorMain() {
        zamknijButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        JButton resistor = new JButton();
        resistor.setActionCommand("addResistor");
        resistor.setToolTipText("Dodaj nowy rezystor");
        resistor.addActionListener(this);
        toolbar.add(resistor);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        circuitPanel = new CircuitPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addResistor")) {
            ResistorView r = new ResistorView(200,200);
            circuitPanel.addElectricalComponent(r);
        }

        circuitPanel.repaint();
    }
}
