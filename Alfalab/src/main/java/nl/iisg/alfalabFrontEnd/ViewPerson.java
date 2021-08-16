package nl.iisg.alfalabFrontEnd;

import com.intellij.uiDesigner.core.*;
import nl.iisg.ids03.AppendToOpslag;
import nl.iisg.ids03.Person;
import nl.iisg.ids03.Utils;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cro
 * Date: 12-1-12
 * Time: 9:45
 * To change this template use File | Settings | File Templates.
 */
public class ViewPerson extends JDialog {
    int i = 0;
    List<Person> persons;

    public ViewPerson(Frame parent) {
        initComponents();
		setContentPane(contentPane);
        setModal(true);
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);

        persons = getPersons();

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(i < persons.size() - 1){
            		i++;
            		textField1.setText(persons.get(i).getFamilyName().trim() + ", " + persons.get(i).getFirstName().trim());
            	}
            }
        });

        previousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(i > 0){
            		i--;
            		textField1.setText(persons.get(i).getFamilyName().trim() + ", " + persons.get(i).getFirstName().trim());
            	}
            }
        });

		textField1.setText(persons.get(i).getFamilyName().trim() + ", " + persons.get(i).getFirstName().trim());

    }


    private List<Person> getPersons() {
        List<Person> persons;

        EntityManagerFactory factory_original_tabs = Persistence.createEntityManagerFactory("b_tables");
        EntityManager emMySQLOpslag = factory_original_tabs.createEntityManager();

        javax.persistence.Query qTemp = emMySQLOpslag.createQuery(AppendToOpslag.SELECT_ALL_FROM_B2);
        persons = (List<Person>) qTemp.getResultList();

        return persons;
    }



	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Christian Roosendaal
		contentPane = new JPanel();
		previousButton = new JButton();
		nextButton = new JButton();
		textField1 = new JTextField();

		//======== contentPane ========
		{

			// JFormDesigner evaluation mark
			contentPane.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"HSN Control Program", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.black), contentPane.getBorder())); contentPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			contentPane.setBackground(Color.lightGray);//
			contentPane.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
			contentPane.setPreferredSize(new Dimension(500, 50));

			//---- previousButton ----
			previousButton.setText("Previous");
			contentPane.add(previousButton, new GridConstraints(0, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_FIXED,
				null, null, null));

			//---- nextButton ----
			nextButton.setText("Next");
			contentPane.add(nextButton, new GridConstraints(0, 1, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_FIXED,
				null, null, null));
			contentPane.add(textField1, new GridConstraints(0, 2, 1, 1,
				GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
				GridConstraints.SIZEPOLICY_FIXED,
				null, null, null));
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Christian Roosendaal
	private JPanel contentPane;
	private JButton previousButton;
	private JButton nextButton;
	private JTextField textField1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
