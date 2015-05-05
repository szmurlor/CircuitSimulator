package gui;

import view.CircuitSimulator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;

public class PreferencesDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtNgSpiceExe;
    private JButton cmdNgSpiceExe;
    private JTextField txtWorkdir;
    private JButton cmdbrowseWorkdir;

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

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        cmdNgSpiceExe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fc = new JFileChooser();
                fc.addChoosableFileFilter(new FileNameExtensionFilter("NgSpice executable (*.exe)", "exe"));
                if (fc.showOpenDialog(PreferencesDialog.this) == JFileChooser.APPROVE_OPTION) {
                    txtNgSpiceExe.setText( fc.getSelectedFile().getAbsolutePath() );
                }
            }
        });

        data2form();
        cmdbrowseWorkdir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (fc.showOpenDialog(PreferencesDialog.this) == JFileChooser.APPROVE_OPTION) {
                    txtWorkdir.setText( fc.getSelectedFile().getAbsolutePath() );
                }
            }
        });
    }

    private void onOK() {
// add your code here
        form2data();
        CircuitSimulator.savePreferences();
        dispose();
    }

    private void form2data() {
        CircuitSimulator.ngSpiceExe = txtNgSpiceExe.getText();
        CircuitSimulator.workdir = txtWorkdir.getText();
    }

    private void data2form() {
        txtNgSpiceExe.setText(CircuitSimulator.ngSpiceExe);
        txtWorkdir.setText(CircuitSimulator.workdir);
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }
}
