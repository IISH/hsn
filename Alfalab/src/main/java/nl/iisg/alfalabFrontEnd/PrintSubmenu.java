package nl.iisg.alfalabFrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.intellij.uiDesigner.core.*;

/**
 * Created by IntelliJ IDEA.
 * User: cro
 * Date: 16-1-12
 * Time: 14:41
 * To change this template use File | Settings | File Templates.
 */
public class PrintSubmenu extends JDialog {
    private final static String MISC_PRINT_CONTROLE_A_1000 = "miscPrintControleA1000";
    private final static String MISC_PRINT_CONTROLE_B_1000 = "miscPrintControleB1000";
    private final static String MISC_PRINT_CONTROLE_AB_1000 = "miscPrintControleAB1000";
    private final static String MISC_PRINT_CONTROLE_A_4000 = "miscPrintControleA4000";
    private final static String MISC_PRINT_CONTROLE_B_4000 = "miscPrintControleB4000";
    private final static String MISC_PRINT_CONTROLE_AB_4000 = "miscPrintControleAB_400";


    private String action;

    public PrintSubmenu(Frame parent){

        initComponents();
		setContentPane(panel1);
        setModal(true);
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);

        a3ControleAMeldingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setAction(MISC_PRINT_CONTROLE_A_1000);
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

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                action = "cancel";
                dispose();
            }
        });


    }

    public void setAction(String action){
        this.action = action;
    }

    public String getAction(){
        return this.action;
    }


	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Christian Roosendaal
		panel1 = new JPanel();
		a3ControleAMeldingButton = new JButton();
		a4ControleBMeldingButton = new JButton();
		a5ControleABButton = new JButton();
		a6ControleAMeldingButton = new JButton();
		a7ControleBMeldingButton = new JButton();
		a8ControleABButton = new JButton();
		Spacer vSpacer1 = new Spacer();

		//======== panel1 ========
		{

			// JFormDesigner evaluation mark
			panel1.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"HSN Control Program", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.black), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			panel1.setLayout(new GridLayoutManager(7, 1, new Insets(0, 0, 0, 0), -1, -1));
			panel1.setBackground(Color.lightGray);


			//---- a3ControleAMeldingButton ----
			a3ControleAMeldingButton.setHorizontalAlignment(SwingConstants.LEFT);
			a3ControleAMeldingButton.setText("1 Controle A  melding 1000-serie Ronde 2");
			panel1.add(a3ControleAMeldingButton, new GridConstraints(0, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_FIXED,
				null, null, null));

			//---- a4ControleBMeldingButton ----
			a4ControleBMeldingButton.setHorizontalAlignment(SwingConstants.LEFT);
			a4ControleBMeldingButton.setText("2 Controle B melding 1000-serie Ronde 2");
			panel1.add(a4ControleBMeldingButton, new GridConstraints(1, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_FIXED,
				null, null, null));

			//---- a5ControleABButton ----
			a5ControleABButton.setHorizontalAlignment(SwingConstants.LEFT);
			a5ControleABButton.setText("3 Controle A/B melding 1000-serie Ronde 3");
			panel1.add(a5ControleABButton, new GridConstraints(2, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_FIXED,
				null, null, null));

			//---- a6ControleAMeldingButton ----
			a6ControleAMeldingButton.setHorizontalAlignment(SwingConstants.LEFT);
			a6ControleAMeldingButton.setText("4 Controle A melding 4000-serie Ronde 4");
			panel1.add(a6ControleAMeldingButton, new GridConstraints(3, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_FIXED,
				null, null, null));

			//---- a7ControleBMeldingButton ----
			a7ControleBMeldingButton.setHorizontalAlignment(SwingConstants.LEFT);
			a7ControleBMeldingButton.setText("5 Controle B melding 4000-serie Ronde 4");
			panel1.add(a7ControleBMeldingButton, new GridConstraints(4, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_FIXED,
				null, null, null));

			//---- a8ControleABButton ----
			a8ControleABButton.setHorizontalAlignment(SwingConstants.LEFT);
			a8ControleABButton.setText("6 Controle A/B melding 4000-serie Ronde 5");
			panel1.add(a8ControleABButton, new GridConstraints(5, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_FIXED,
				null, null, null));
			panel1.add(vSpacer1, new GridConstraints(6, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK,
				GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
				null, null, null));
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Christian Roosendaal
	private JPanel panel1;
	private JButton a3ControleAMeldingButton;
	private JButton a4ControleBMeldingButton;
	private JButton a5ControleABButton;
	private JButton a6ControleAMeldingButton;
	private JButton a7ControleBMeldingButton;
	private JButton a8ControleABButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
