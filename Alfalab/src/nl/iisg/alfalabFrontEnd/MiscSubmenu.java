package nl.iisg.alfalabFrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import com.intellij.uiDesigner.core.*;

public class MiscSubmenu extends JDialog implements ActionListener {

    private final static String MISC_PRINT_HSN_POP_REG_ERRORS = "miscPrintHSNPopRegErrors";

    
    private final static String MISC_PRINT_HSN_POP_REG_ALL = "miscPrintHSNPopRegAll";
    private final static String MISC_PRINT_CONTROLE_A_1000 = "miscPrintControleA1000";
    private final static String MISC_PRINT_CONTROLE_B_1000 = "miscPrintControleB1000";
    private final static String MISC_PRINT_CONTROLE_AB_1000 = "miscPrintControleAB1000";
    private final static String MISC_PRINT_CONTROLE_A_4000 = "miscPrintControleA4000";
    private final static String MISC_PRINT_CONTROLE_B_4000 = "miscPrintControleB4000";
    private final static String MISC_PRINT_CONTROLE_AB_4000 = "miscPrintControleAB_400";



    private Frame parentFrame;

    private String action;

    public MiscSubmenu(Frame parent) {

        initComponents();
		parentFrame = parent;
        setContentPane(contentPane);
        setModal(true);
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);

        buttonCancel.addActionListener(this);
        printingHSNFilesButton.addActionListener(this);
        printingHSNSelectedMessagesButton.addActionListener(this);
        printingHSNMessagesButton.addActionListener(this);


 /*       buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });


        printingHSNFilesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action = MISC_PRINT_HSN_POP_REG_ALL;
                dispose();
            }
        });

        printingHSNFilesErrorsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PrintSubmenu printSubmenu = new PrintSubmenu();
                action = MISC_PRINT_HSN_POP_REG_ERRORS;
                dispose();
            }
        });

        a3ControleAMeldingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action = MISC_PRINT_CONTROLE_A_1000;
                dispose();
            }
        });

        a4ControleBMeldingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action = MISC_PRINT_CONTROLE_B_1000;
                dispose();
            }
        });

        a5ControleABButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action = MISC_PRINT_CONTROLE_AB_1000;
                dispose();
            }
        });

        a6ControleAMeldingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action = MISC_PRINT_CONTROLE_A_4000;
                dispose();
            }
        });

        a7ControleBMeldingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action = MISC_PRINT_CONTROLE_B_4000;
                dispose();
            }
        });

        a8ControleABButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action = MISC_PRINT_CONTROLE_AB_4000;
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
 */
    }

    public void actionPerformed(ActionEvent event){

        Object source = event.getSource();

        if ( source == buttonCancel ) {

            dispose();

        } else if( source == printingHSNFilesButton ) {

            action = MISC_PRINT_HSN_POP_REG_ALL;
            dispose();

        } else if( source == printingHSNMessagesButton ) {

            action = MISC_PRINT_HSN_POP_REG_ERRORS;
            dispose();

        } else if( source == printingHSNSelectedMessagesButton ) {

            PrintSubmenu printSubmenu = new PrintSubmenu(parentFrame);
            printSubmenu.setVisible(true);
            action = printSubmenu.getAction();

            if(action != "cancel") dispose();              // don't close misc submenu if printmenu is cancelled.

        }

//        } else if( source == a3ControleAMeldingButton ){
//
//        } else if ( source == a4ControleBMeldingButton ) {
//
//        } else if ( source == a5ControleABButton ) {
//
//        } else if ( source == a6ControleAMeldingButton ) {
//
//        } else if ( source == a7ControleBMeldingButton ) {
//
//        } else if ( source == a8ControleABButton ) {
//
//        }

    }

    public String getAction(){
        return action;
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
		printingHSNSelectedMessagesButton = new JButton();
		printingHSNMessagesButton = new JButton();
		printingHSNFilesButton = new JButton();
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
			contentPane.setSize(1250, 1250);//


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
				panel3.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));

				//---- printingHSNFilesSelectedErrorsButton ----
				printingHSNSelectedMessagesButton.setHorizontalAlignment(SwingConstants.LEFT);
				printingHSNSelectedMessagesButton.setText("1 Printing HSN files Population Registers based on *Selected* messages");
				panel3.add(printingHSNSelectedMessagesButton, new GridConstraints(0, 0, 1, 1,
					GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
					GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
					GridConstraints.SIZEPOLICY_FIXED,
					null, null, null));

				//---- printingHSNFilesAll ----
				printingHSNFilesButton.setHorizontalAlignment(SwingConstants.LEFT);
				printingHSNFilesButton.setText("2 Printing HSN files Population Registers (all)");
				panel3.add(printingHSNFilesButton, new GridConstraints(1, 0, 1, 1,
					GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
					GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
					GridConstraints.SIZEPOLICY_FIXED,
					null, null, null));
				//---- printingHSNFilesButton ----
				printingHSNMessagesButton.setHorizontalAlignment(SwingConstants.LEFT);
				printingHSNMessagesButton.setText("3 Printing HSN files Population based on messages");
				panel3.add(printingHSNMessagesButton, new GridConstraints(2, 0, 1, 1,
					GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
					GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
					GridConstraints.SIZEPOLICY_FIXED,
					null, null, null));
				panel3.add(vSpacer1, new GridConstraints(3, 0, 1, 1,
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
	private JButton printingHSNMessagesButton;
	private JButton printingHSNSelectedMessagesButton;
	private JButton printingHSNFilesButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
