package gui;

import view.CircuitComponent;
import view.CircuitPanel;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ElectricalComponentDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtName;
    private JRadioButton rdHorizontal;
    private JRadioButton rdVertical;

    private CircuitComponent current;

    private List<ActionListener> listeners = new ArrayList<ActionListener>();

    public ElectricalComponentDialog(CircuitComponent cc) {
        this.current = cc;

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

        data2Form();
    }

    private void data2Form() {
        txtName.setText( current.name );
        if (current.getOrientation().equals(CircuitComponent.Orientation.Horizontal)) {
            rdHorizontal.setSelected(true);
        } else {
            rdVertical.setSelected(true);
        }
    }

    private void onOK() {
// add your code here
        form2Data();

        for (ActionListener al: listeners) {
            ActionEvent ae = new ActionEvent(this, 1, "repaint");
            al.actionPerformed(ae);
        }
        dispose();
    }

    private void form2Data() {
        current.name = txtName.getText();
        if (rdVertical.isSelected())
            current.setOrientation(CircuitComponent.Orientation.Vertical);
        else
            current.setOrientation(CircuitComponent.Orientation.Horizontal);
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public void addActionListener(ActionListener al) {
        listeners.add(al);
    }
}
