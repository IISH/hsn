package nl.iisg.alfalabFrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.intellij.uiDesigner.core.*;

public class PopulationRegisterSubmenu extends JDialog {
    private final static String POP_REG_TEST_ERRORS = "popRegTestErrors";// = popRegTestErrors2 for mySQL input
    private final static String POP_REG_DELETE_FROM_DEF_DB = "popRegDeleteFromDefDB";
    private final static String POP_REG_APPEND_TO_DEF_DB = "popRegAppendToDefDB";
    private final static String POP_REG_TEST_IDNR_DOUBLES = "popRegTestIDnrDoubles";
    private final static String POP_REG_REPLACE_DEF_WITH_TEMP = "popRegReplaceDefWithTemp";

    private String action;

    public PopulationRegisterSubmenu(Frame parent) {
        initComponents();
		this.setTitle("Population Register Submenu");
        setContentPane(contentPane);
        setModal(true);
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        testOnErrorsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action = POP_REG_TEST_ERRORS;
                dispose();
            }
        });

        deleteDataFromButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action = POP_REG_DELETE_FROM_DEF_DB;
                dispose();
            }
        });

        appendDataFromButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action = POP_REG_APPEND_TO_DEF_DB;
                dispose();
            }
        });

        testOnDoublesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action = POP_REG_TEST_IDNR_DOUBLES;
                dispose();
            }
        });

        replaceRecordsOfButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                action = POP_REG_REPLACE_DEF_WITH_TEMP;
                dispose();
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

    public String getAction(){
        return action;
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
		buttonCancel = new JButton();
		JPanel panel3 = new JPanel();
		testOnErrorsButton = new JButton();
		deleteDataFromButton = new JButton();
		appendDataFromButton = new JButton();
		testOnDoublesButton = new JButton();
		replaceRecordsOfButton = new JButton();
		Spacer vSpacer1 = new Spacer();

		//======== contentPane ========
		{

			// JFormDesigner evaluation mark
			contentPane.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"HSN Control Program", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.black), contentPane.getBorder())); contentPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
			contentPane.setBackground(Color.lightGray);

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

					//---- buttonCancel ----
					buttonCancel.setText("Cancel");
					panel2.add(buttonCancel, new GridConstraints(0, 0, 1, 1,
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
				panel3.setLayout(new GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));

				//---- testOnErrorsButton ----
				testOnErrorsButton.setText("1 Test on errors -> Data to Temporary Database");
				panel3.add(testOnErrorsButton, new GridConstraints(0, 0, 1, 1,
					GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
					GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
					GridConstraints.SIZEPOLICY_FIXED,
					null, null, null));

				//---- deleteDataFromButton ----
				deleteDataFromButton.setText("2 Delete data from Definitive Database (based on idnr.dbf)");
				panel3.add(deleteDataFromButton, new GridConstraints(1, 0, 1, 1,
					GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
					GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
					GridConstraints.SIZEPOLICY_FIXED,
					null, null, null));

				//---- appendDataFromButton ----
				appendDataFromButton.setText("3 Append data from Temporary Database to Definitive Database");
				panel3.add(appendDataFromButton, new GridConstraints(2, 0, 1, 1,
					GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
					GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
					GridConstraints.SIZEPOLICY_FIXED,
					null, null, null));

				//---- testOnDoublesButton ----
				testOnDoublesButton.setText("4 Test on doubles in Idnr between Temporary and Definitive Database");
				panel3.add(testOnDoublesButton, new GridConstraints(3, 0, 1, 1,
					GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
					GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
					GridConstraints.SIZEPOLICY_FIXED,
					null, null, null));

				//---- replaceRecordsOfButton ----
				replaceRecordsOfButton.setText("5 Replace records of Definitive Database with those from Temporary Database");
				panel3.add(replaceRecordsOfButton, new GridConstraints(4, 0, 1, 1,
					GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
					GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
					GridConstraints.SIZEPOLICY_FIXED,
					null, null, null));
				panel3.add(vSpacer1, new GridConstraints(5, 0, 1, 1,
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
	private JButton buttonCancel;
	private JButton testOnErrorsButton;
	private JButton deleteDataFromButton;
	private JButton appendDataFromButton;
	private JButton testOnDoublesButton;
	private JButton replaceRecordsOfButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
