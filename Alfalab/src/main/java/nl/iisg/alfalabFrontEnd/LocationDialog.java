package nl.iisg.alfalabFrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import com.intellij.uiDesigner.core.*;

public class LocationDialog extends JDialog {
    private boolean succeeded;
    private File[] originalFiles;

    public LocationDialog(Frame parent) {
        initComponents();
		setContentPane(contentPane);
		
        setModal(true);
        pack();
//        getRootPane().setDefaultButton(buttonOK);
        setResizable(false);
        setLocationRelativeTo(parent);


        versioningButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                openVersioningDialog();
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                succeeded = true;
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                succeeded = false;
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                succeeded = false;
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                succeeded = false;
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


    }

    private void openVersioningDialog() {

    }

    private void openLocationOriginalFileDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);

        //FileFilter filter1 = new ExtensionFileFilter("*.dbf files", new String[] { "DBF"} );
        //fileChooser.setFileFilter(filter1);
        int returnVal = fileChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            originalFiles = fileChooser.getSelectedFiles();

        } else {
//            textArea.append("Open command cancelled by user.\n");
        }
    }

    private void openLocationTargetFileDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);

        int returnVal = fileChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File[] files = fileChooser.getSelectedFiles();
            for (File file : files) {
            }
        } else {
        }
    }

    public File[] getOriginalFileArray(){
        return originalFiles;
    }

    private void onOK() {
// add your code here
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public boolean isSucceed(){
        return succeeded;
    }

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Christian Roosendaal
		contentPane = new JPanel();
		JPanel panel1 = new JPanel();
		Spacer hSpacer1 = new Spacer();
		JPanel panel2 = new JPanel();
		buttonOK = new JButton();
		buttonCancel = new JButton();
		JPanel panel3 = new JPanel();
		Spacer vSpacer1 = new Spacer();
		versioningButton = new JButton();

		//======== contentPane ========
		{

			// JFormDesigner evaluation mark
			contentPane.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"HSN Control Program", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.black), contentPane.getBorder())); contentPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
			contentPane.setBackground(Color.lightGray);//
			contentPane.setSize(250, 50);//


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
					panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));

					//---- buttonOK ----
					buttonOK.setText("OK");
					panel2.add(buttonOK, new GridConstraints(0, 0, 1, 1,
						GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
						GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
						GridConstraints.SIZEPOLICY_FIXED,
						null, null, null));

					//---- buttonCancel ----
					buttonCancel.setText("Cancel");
					panel2.add(buttonCancel, new GridConstraints(0, 1, 1, 1,
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
				panel3.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
				panel3.add(vSpacer1, new GridConstraints(3, 0, 1, 1,
					GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
					GridConstraints.SIZEPOLICY_CAN_SHRINK,
					GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
					null, null, null));

				//---- versioningButton ----
				versioningButton.setText("Versioning");
				panel3.add(versioningButton, new GridConstraints(2, 0, 1, 1,
					GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
					GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
					GridConstraints.SIZEPOLICY_FIXED,
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
	private JButton buttonOK;
	private JButton buttonCancel;
	private JButton versioningButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
