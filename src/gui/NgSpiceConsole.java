package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NgSpiceConsole extends JDialog {

    private Timer timer;
    private Process pr;
    private InputStream in;
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
    }

    public NgSpiceConsole(Process pr, InputStream in) {
        this();
        this.pr = pr;
        this.in = in;

        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (NgSpiceConsole.this.in.available() != 0) {
                        InputStreamReader r = new InputStreamReader(NgSpiceConsole.this.in);
                        BufferedReader br = new BufferedReader(r);
                        String line = br.readLine();
                        if (line != null) {
                            txtOutput.append(line);
                            txtOutput.append("\n");
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        timer.start();
    }

    private void onOK() {
        pr.destroy();
        dispose();
    }
}
