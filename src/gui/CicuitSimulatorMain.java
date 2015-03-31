package gui;

import view.CapacitorView;
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
        JButton btn = new JButton();
        btn.setActionCommand("addResistor");
        btn.setText("Rezystor");
        btn.addActionListener(this);
        toolbar.add(btn);

        btn = new JButton();
        btn.setActionCommand("addCapacitor");
        btn.setText("Kondensator");
        btn.addActionListener(this);
        toolbar.add(btn);
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
        } else if (actionEvent.getActionCommand().equals("addCapacitor")) {
            CapacitorView cv = new CapacitorView(getRootPanel().getWidth() / 2,
                    getRootPanel().getHeight() / 2);
            circuitPanel.addCircuitComponent(cv);
        } else if (actionEvent.getActionCommand().equals("repaint")) {
            circuitPanel.repaint();
        }
    }
}
