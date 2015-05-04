package gui;

import view.*;
import view.components.*;
import writers.CircuitSimulatorReader;
import writers.CircuitSimulatorWriter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GR5 on 24.03.15.
 */
public class CicuitSimulatorMain implements ActionListener {
    private JPanel rootPanel;
    private JButton zamknijButton;
    private CircuitPanel circuitPanel;
    private JToolBar toolbar;

    private File lastFile;
    private JMenuItem rbItemStatic;
    private JMenuItem rbItemDynamic;

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
        } else if (actionEvent.getActionCommand().equals("new")) {
            circuitPanel.getCircuitComponents().clear();
            circuitPanel.getCircuitConnnections().clear();
            circuitPanel.repaint();
        } else if (actionEvent.getActionCommand().equals("saveas")) {
            JFileChooser jfChooser = new JFileChooser();
            jfChooser.setFileFilter(new FileNameExtensionFilter("Circuit Simulator file (*.csm)","csm"));
            jfChooser.setCurrentDirectory(lastFile);

            int ret = jfChooser.showSaveDialog(rootPanel);
            if (ret == JFileChooser.APPROVE_OPTION) {
                lastFile = jfChooser.getSelectedFile();

                CircuitSimulatorWriter csw = new CircuitSimulatorWriter();
                try {
                    csw.write(jfChooser.getSelectedFile(), circuitPanel.getCircuitComponents(), circuitPanel.getCircuitConnnections());
                } catch (IOException e) {
                    int mc = JOptionPane.ERROR_MESSAGE;
                    JOptionPane.showMessageDialog(null, "Podczas zapisu wystąpił błąd: " + e.getMessage(), "Błąd zapisu", mc);
                }
            }
        } else if (actionEvent.getActionCommand().equals("saveasNgSpice")) {
            JFileChooser jfChooser = new JFileChooser();
            jfChooser.setFileFilter(new FileNameExtensionFilter("NgSpice file (*.cir)","cir"));
            jfChooser.setCurrentDirectory(lastFile);

            int ret = jfChooser.showSaveDialog(rootPanel);
            if (ret == JFileChooser.APPROVE_OPTION) {
                lastFile = jfChooser.getSelectedFile();

                CircuitSimulatorWriter csw = new CircuitSimulatorWriter();
                try {
                    csw.writeNgSpice(jfChooser.getSelectedFile(), circuitPanel.getCircuitComponents(), circuitPanel.getCircuitConnnections());
                } catch (IOException e) {
                    int mc = JOptionPane.ERROR_MESSAGE;
                    JOptionPane.showMessageDialog(null, "Podczas zapisu wystąpił błąd: " + e.getMessage(), "Błąd zapisu", mc);
                }
            }
        } else if (actionEvent.getActionCommand().equals("open")) {
            JFileChooser jfChooser = new JFileChooser();
            jfChooser.setFileFilter(new FileNameExtensionFilter("Circuit Simulator file (*.csm)","csm"));
            jfChooser.setCurrentDirectory(lastFile);

            int ret = jfChooser.showOpenDialog(rootPanel);
            if (ret == JFileChooser.APPROVE_OPTION) {
                lastFile = jfChooser.getSelectedFile();

                CircuitSimulatorReader csr = new CircuitSimulatorReader();
                try {
                    csr.read(jfChooser.getSelectedFile(), circuitPanel.getCircuitComponents(), circuitPanel.getCircuitConnnections());
                    circuitPanel.repaint();
                } catch (Exception e) {
                    int mc = JOptionPane.ERROR_MESSAGE;
                    JOptionPane.showMessageDialog (null, "Podczas otwierania wystąpił błąd: " + e.getMessage(), "Błąd zapisu", mc);
                }
            }
        } else if (actionEvent.getActionCommand().equals("simulationTypeStatic")) {
            circuitPanel.setSimulationType(CircuitSimulator.SIMULATION_TYPE.STATIC);
            circuitPanel.repaint();
        } else if (actionEvent.getActionCommand().equals("simulationTypeDynamic")) {
            circuitPanel.setSimulationType(CircuitSimulator.SIMULATION_TYPE.DYNAMIC);
            circuitPanel.repaint();
        } else if (actionEvent.getActionCommand().equals("simulate")) {
            circuitPanel.repaint();
        } else if (actionEvent.getActionCommand().equals("showConsole")) {
            ProcessBuilder ps = new ProcessBuilder("/bin/bash", "-c", "ngspice -b"); //, "-b", "/home/szmurlor/src/idea/CircuitSimulator/examples/circuit2.cir");
            // ProcessBuilder ps = new ProcessBuilder("ngspice", "-i");
            ps.redirectErrorStream(true);

            try {
                Process pr = ps.start();
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(pr.getOutputStream()));
                NgSpiceConsole dialogConsole = new NgSpiceConsole(pr, pr.getInputStream());
                dialogConsole.setTitle("Konsolas NgSpice");
                dialogConsole.setVisible(true);
                dialogConsole.pack();
                circuitPanel.repaint();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Plik");
        menuBar.add(menu);

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
        item = new JMenuItem("Eksportuj NgSpice");
        item.setActionCommand("saveasNgSpice");
        item.addActionListener(this);
        menu.add(item);
        menu.addSeparator();

        item = new JMenuItem("Zakończ");
        item.setActionCommand("exit");
        item.addActionListener(this);
        menu.add(item);



        menu = new JMenu("Symulacja");
        menuBar.add(menu);

        item = new JMenuItem("Symuluj");
        item.setActionCommand("simulate");
        item.addActionListener(this);
        menu.add(item);


        menu.addSeparator();


        ButtonGroup group = new ButtonGroup();
        rbItemStatic = new JRadioButtonMenuItem("Symulacja statyczna");
        rbItemStatic.setActionCommand("simulationTypeStatic");
        rbItemStatic.addActionListener(this);
        rbItemStatic.setSelected(true);
        menu.add(rbItemStatic);
        group.add(rbItemStatic);

        rbItemDynamic = new JRadioButtonMenuItem("Symulacja czasowa");
        rbItemDynamic.setActionCommand("simulationTypeDynamic");
        rbItemDynamic.addActionListener(this);
        rbItemDynamic.setSelected(false);
        menu.add(rbItemDynamic);
        group.add(rbItemDynamic);


        menu.addSeparator();


        item = new JMenuItem("Wyświetl konsolę");
        item.setActionCommand("showConsole");
        item.addActionListener(this);
        menu.add(item);

        return menuBar;
    }
}
