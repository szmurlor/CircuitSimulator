package gui;

import view.CircuitSimulator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class NgSpiceConsoleDialog extends JDialog {
    private Timer timer;
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextArea txtLog;

    public NgSpiceConsoleDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            new FileInputStream(CircuitSimulator.workdir + File.separator + CircuitSimulator.logNgSpiceFile) {
                            }));
                    String line;
                    while ((line = br.readLine()) != null) {
                        txtLog.append(line);
                        txtLog.append("\n");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                timer.stop();
            }
        });
        timer.start();
    }

    private void onOK() {
// add your code here
        timer.stop();
        dispose();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
