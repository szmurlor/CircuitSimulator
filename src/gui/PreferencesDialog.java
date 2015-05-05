package gui;

import view.CircuitSimulator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;

public class PreferencesDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtNgSpice;
    private JButton button1;
    private JTextField txtWorkdir;
    private JButton browseWorkDir;

    public PreferencesDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfChooser = new JFileChooser();
                jfChooser.addChoosableFileFilter(new FileNameExtensionFilter("Executable NgSpice (*.exe)", "exe"));
                if (jfChooser.showOpenDialog(PreferencesDialog.this) == JFileChooser.APPROVE_OPTION) {
                    txtNgSpice.setText(jfChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        browseWorkDir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfChooser = new JFileChooser();
                jfChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (jfChooser.showOpenDialog(PreferencesDialog.this) == JFileChooser.APPROVE_OPTION) {
                    txtWorkdir.setText(jfChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        data2form();
    }

    private void onOK() {
        form2data();
        dispose();
    }

    private void form2data() {
        CircuitSimulator.ngspiceExe = txtNgSpice.getText();
        CircuitSimulator.workdir = txtWorkdir.getText();
        CircuitSimulator.savePreferences();
    }

    private void data2form() {
        txtNgSpice.setText(CircuitSimulator.ngspiceExe);
        txtWorkdir.setText(CircuitSimulator.workdir);
    }

    private void onCancel() {
        dispose();
    }
}
