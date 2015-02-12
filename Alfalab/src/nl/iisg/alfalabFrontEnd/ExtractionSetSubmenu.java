package nl.iisg.alfalabFrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.intellij.uiDesigner.core.*;

public class ExtractionSetSubmenu extends JDialog {
    private JButton buttonCancel;

    public ExtractionSetSubmenu(Frame parent) {
        initComponents();
		setContentPane(contentPane);
        setModal(true);
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);

        cancelButton.addActionListener(new ActionListener() {
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
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Christian Roosendaal
		contentPane = new JPanel();
		JPanel panel1 = new JPanel();
		Spacer hSpacer1 = new Spacer();
		JPanel panel2 = new JPanel();
		cancelButton = new JButton();
		JPanel panel3 = new JPanel();
		hsnStandardOutputfileButton = new JButton();
		migrationFileButton = new JButton();
		Spacer vSpacer1 = new Spacer();

		//======== contentPane ========
		{

			// JFormDesigner evaluation mark
			contentPane.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), contentPane.getBorder())); contentPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));

			//======== panel1 ========
			{
				panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
				panel1.add(hSpacer1, new GridConstraints(0, 0, 1, 1,
					GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
					GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
					GridConstraints.SIZEPOLICY_CAN_SHRINK,
					null, null, null));

				//======== panel2 ========
				{
					panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));

					//---- cancelButton ----
					cancelButton.setText("Cancel");
					panel2.add(cancelButton, new GridConstraints(0, 0, 1, 1,
						GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
						GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
						GridConstraints.SIZEPOLICY_FIXED,
						null, null, null));
				}
				panel1.add(panel2, new GridConstraints(0, 1, 1, 1,
					GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
					GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
					GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
					null, null, null));
			}
			contentPane.add(panel1, new GridConstraints(1, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK,
				null, null, null));

			//======== panel3 ========
			{
				panel3.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));

				//---- hsnStandardOutputfileButton ----
				hsnStandardOutputfileButton.setText("1 Hsn Standard outputfile");
				panel3.add(hsnStandardOutputfileButton, new GridConstraints(0, 0, 1, 1,
					GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
					GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
					GridConstraints.SIZEPOLICY_FIXED,
					null, null, null));

				//---- migrationFileButton ----
				migrationFileButton.setText("2 Migration file");
				panel3.add(migrationFileButton, new GridConstraints(1, 0, 1, 1,
					GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
					GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
					GridConstraints.SIZEPOLICY_FIXED,
					null, null, null));
				panel3.add(vSpacer1, new GridConstraints(2, 0, 1, 1,
					GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
					GridConstraints.SIZEPOLICY_CAN_SHRINK,
					GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
					null, null, null));
			}
			contentPane.add(panel3, new GridConstraints(0, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Christian Roosendaal
	private JPanel contentPane;
	private JButton cancelButton;
	private JButton hsnStandardOutputfileButton;
	private JButton migrationFileButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
