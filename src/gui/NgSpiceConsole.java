package gui;

import view.CircuitSimulator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class NgSpiceConsole extends JDialog {

    private Timer timer;
    private Process pr;
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextArea txtOutput;

    public NgSpiceConsole() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
/*
        JScrollPane scroll = new JScrollPane(txtOutput);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(scroll);
*/
    }

    public NgSpiceConsole(Process proc) {
        this();
        this.pr = proc;

        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                String line;
                try {
                    while ((line = br.readLine()) != null) {
                        txtOutput.append(line);
                        txtOutput.append("\n");
                    }
                    br.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


                try {
                    txtOutput.append("\n\nLog:\n");
                    br = new BufferedReader(new FileReader(CircuitSimulator.workdir + File.separator + CircuitSimulator.logFileName));
                    while ((line = br.readLine()) != null) {
                        txtOutput.append(line);
                        txtOutput.append("\n");
                    }
                    br.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                try {
                    txtOutput.append("\n\nwrdata:\n");
                    br = new BufferedReader(new FileReader(CircuitSimulator.workdir + File.separator + CircuitSimulator.wrdataNgSpice + ".data"));
                    while ((line = br.readLine()) != null) {
                        txtOutput.append(line);
                        txtOutput.append("\n");
                    }
                    br.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        timer.start();
    }

    private void onOK() {
        dispose();
    }

}
