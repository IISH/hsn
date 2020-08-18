package nl.iisg.alfalabFrontEnd;



import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {

    JPanel panel;
    JLabel user_label, password_label, message;
    JTextField userName_text;
    JPasswordField password_text;
    JButton submit, cancel;
    private boolean logonOK = false;
    
    DataInputStream in; 
    DataOutputStream out;
    
    private final static String SERVER_LOGIN = "login ";

    Login(DataInputStream in1, DataOutputStream out1) {
    	
    	in = in1;
    	out = out1;
    	
        
        // User Label
        user_label = new JLabel();
        user_label.setText("User Name :");
        userName_text = new JTextField();
        
        // Password

        password_label = new JLabel();
        password_label.setText("Password :");
        password_text = new JPasswordField();

        // Submit

        submit = new JButton("SUBMIT");

        panel = new JPanel(new GridLayout(3, 1));

        panel.add(user_label);
        panel.add(userName_text);
        panel.add(password_label);
        panel.add(password_text);

        message = new JLabel();
        panel.add(message);
        panel.add(submit);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Adding the listeners to components..
        submit.addActionListener(this);
        add(panel, BorderLayout.CENTER);
        setTitle("Login");
        setSize(300, 100);
        setVisible(true);

    }

    
    @Override
    public void actionPerformed(ActionEvent ae)  {
    	
        String userName = userName_text.getText();
        String password = password_text.getText();
        
        try {
			out.writeUTF(SERVER_LOGIN + userName.trim() + "/" + password.trim());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        String s = null;
        
        try {
			s = in.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println(s);
        
        if (userName.trim().equals("admin") && password.trim().equals("admin")) {
        	 logonOK = true;
             dispose();
            //message.setText(" Hello " + userName
              //      + "");
        } else {
       	 logonOK = false;
         dispose();

            //message.setText(" Invalid user.. ");
        }

    }


	public boolean isLogonOK() {
		return logonOK;
	}


	public void setLogonOK(boolean logonOK) {
		this.logonOK = logonOK;
	}
    
    

}
