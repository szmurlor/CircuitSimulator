package gui;

import view.*;
import view.components.*;
import writers.CircuitSimulatorWriter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by GR5 on 24.03.15.
 */
public class CicuitSimulatorMain implements ActionListener {
    private JPanel rootPanel;
    private JButton zamknijButton;
    private CircuitPanel circuitPanel;
    private JToolBar toolbar;

    public CicuitSimulatorMain() {
        zamknijButton.setActionCommand("exit");
        zamknijButton.addActionListener(this);

        setupToolbar();
    }

    private void setupToolbar() {
        JButton btnResistor = new JButton();
        btnResistor.setActionCommand("addResistor");
        btnResistor.setText("Rezystor");
        btnResistor.addActionListener(this);
        toolbar.add(btnResistor);

        JButton btnCapacitor = new JButton();
        btnCapacitor.setActionCommand("addCapacitor");
        btnCapacitor.setText("Kondensator");
        btnCapacitor.addActionListener(this);
        toolbar.add(btnCapacitor);

        JButton btnCoil = new JButton();
        btnCoil.setActionCommand("addCoil");
        btnCoil.setText("Cewka");
        btnCoil.addActionListener(this);
        toolbar.add(btnCoil);

        JButton btnVoltageSource = new JButton();
        btnVoltageSource.setActionCommand("addVoltage");
        btnVoltageSource.setText("Źródło napięciowe");
        btnVoltageSource.addActionListener(this);
        toolbar.add(btnVoltageSource);

        JButton btnCurrentSource = new JButton();
        btnCurrentSource.setActionCommand("addCurrent");
        btnCurrentSource.setText("Źródło prądowe");
        btnCurrentSource.addActionListener(this);
        toolbar.add(btnCurrentSource);
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
            ResistorView rv = new ResistorView(getRootPanel().getWidth() / 2, getRootPanel().getHeight() / 2);
            circuitPanel.addCircuitComponent(rv);
        } else if (actionEvent.getActionCommand().equals("addCapacitor")) {
                CapacitorView cv = new CapacitorView(getRootPanel().getWidth() / 2, getRootPanel().getHeight() / 2);
                circuitPanel.addCircuitComponent(cv);
        } else if (actionEvent.getActionCommand().equals("addCoil")) {
            CoilView c = new CoilView(getRootPanel().getWidth() / 2, getRootPanel().getHeight() / 2);
            circuitPanel.addCircuitComponent(c);
        } else if (actionEvent.getActionCommand().equals("addVoltage")) {
            VoltageSourceView c = new VoltageSourceView(getRootPanel().getWidth() / 2, getRootPanel().getHeight() / 2);
            circuitPanel.addCircuitComponent(c);
        } else if (actionEvent.getActionCommand().equals("addCurrent")) {
            CurrentSourceView c = new CurrentSourceView(getRootPanel().getWidth() / 2, getRootPanel().getHeight() / 2);
            circuitPanel.addCircuitComponent(c);
        } else if (actionEvent.getActionCommand().equals("repaint")) {
            circuitPanel.repaint();
        } else if (actionEvent.getActionCommand().equals("exit")) {
            System.exit(0);
        } else if (actionEvent.getActionCommand().equals("saveas")) {
            JFileChooser jfChooser = new JFileChooser();
            jfChooser.setFileFilter(new FileNameExtensionFilter("Circuit Simulator file (*.csm)","csm"));

            int ret = jfChooser.showSaveDialog(rootPanel);
            if (ret == JFileChooser.APPROVE_OPTION) {
                // System.out.println(jfChooser.getSelectedFile().getAbsoluteFile());
                CircuitSimulatorWriter csw = new CircuitSimulatorWriter();
                try {
                    csw.write(jfChooser.getSelectedFile(), circuitPanel.getCircuitComponents(), circuitPanel.getCircuitConnnections());
                } catch (IOException e) {
                    int mc = JOptionPane.ERROR_MESSAGE;
                    JOptionPane.showMessageDialog (null, "Podczas zapisu wystąpił błąd: " + e.getMessage(), "Błąd zapisu", mc);
                }
            }

        }
    }

    public JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Plik");

        JMenuItem item = new JMenuItem("Nowy");
        item.setActionCommand("new");
        item.addActionListener(this);
        menu.add(item);

        item = new JMenuItem("Otwórz...");
        item.setActionCommand("open");
        item.addActionListener(this);
        menu.add(item);

        item = new JMenuItem("Zapisz");
        item.setActionCommand("save");
        item.addActionListener(this);
        menu.add(item);

        item = new JMenuItem("Zapisz jako...");
        item.setActionCommand("saveas");
        item.addActionListener(this);
        menu.add(item);

        menu.addSeparator();

        item = new JMenuItem("Zakończ");
        item.setActionCommand("exit");
        item.addActionListener(this);
        menu.add(item);

        menuBar.add(menu);
        return menuBar;
    }
}
