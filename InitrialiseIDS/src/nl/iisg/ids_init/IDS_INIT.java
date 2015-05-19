package nl.iisg.ids_init;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JTextArea;


public class IDS_INIT {
	
	public static void main(String [] s){
	
		
		System.out.println("Hallo world!!!!!!");
		print("Started Initialize IDS\n");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hsn_ids");
		EntityManager em = emf.createEntityManager();
		 print("Ended   Initialize IDS\n");
		
	}
	
	public static JTextArea getTextArea() {
		return textArea;
	}

	public static void setTextArea(JTextArea textArea) {
		IDS_INIT.textArea = textArea;
	}

	public static DataOutputStream getOut() {
		return out;
	}

	public static void setOut(DataOutputStream out) {
		IDS_INIT.out = out;
	}

	static JTextArea textArea = null;
    static DataOutputStream out = null;
    
	 public IDS_INIT(DataOutputStream out) {
	        setTextArea(textArea);
	        setOut(out);
	    }
	 

	 public static void print(String line) {
		 if (out != null) {
			 try {
				 out.writeUTF(line);
			 } catch (IOException e) {
				 // e.printStackTrace();
				 System.out.println("Client Message: " + line);
			 }
		 } else {
			 System.out.println(line);
		 }
	 }

}
