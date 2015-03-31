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

        setupToolbar();
    }

    private void setupToolbar() {
        JButton btnResistor = new JButton();
        btnResistor.setActionCommand("addResistor");
        btnResistor.setText("Rezystor");
        btnResistor.addActionListener(this);

        toolbar.add(btnResistor);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        circuitPanel = new CircuitPanel();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("addResistor")) {
            ResistorView rv = new ResistorView(getRootPanel().getWidth() / 2,
                    getRootPanel().getHeight() / 2);
            circuitPanel.addCircuitComponent(rv);
        }
    }
}
