package nl.iisg.ids05;

/*
* Naam:    Contxt
* Version: 0.1
* Author:  Cor Munnik
* Copyright
*/



import java.io.*;
import java.util.Date;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JTextArea;

import nl.iisg.idscontext.Contxt;
import nl.iisg.ref.Ref;

import org.eclipse.persistence.internal.sessions.remote.SequencingFunctionCall.GetNextValue;
 


/**
 * 
 * This is the top class of the application   
 * It is the main module
 *
 */
public class CivilCertificatesToIDS implements Runnable{

    static JTextArea textArea = null;
    static DataOutputStream out = null;

    public CivilCertificatesToIDS(DataOutputStream out) {
        setTextArea(textArea);
        setOut(out);
    }


    public void run() {

        main(new String[0]);
    }

	
  public static void main( String args[]) {

	ArrayList<ArrayList> all = new ArrayList<ArrayList>();
	String [] tables = {"A1", "B0", "B1", "B2", "B3", "B4", "B5", "B6", "M1", "M2", "M3", "M4", "M5", "M6", "D1", "D2", "D3", "D4"};

    print("\nCivil Certificates - to IDS      started\n");

	for(int i = 0; i < tables.length; i++){
		all.add(new ArrayList());
	}

    if(false)
    {
    	File file  = new File("h:/sysout.log"); 
    	try{
    		PrintStream printStream = new PrintStream(new FileOutputStream(file));
    		System.out.println("Output in h:/sysout.log");  
    		System.setOut(printStream); 

    	}
    	catch(FileNotFoundException e){}  
    }
     
    Runtime runtime = Runtime.getRuntime();
    
    System.out.println("Free Memory:"  + runtime.freeMemory() / (1024 * 1024)); 
    System.out.println("Total Memory:" + runtime.totalMemory() / (1024 * 1024));
    System.out.println("Max Memory:" + runtime.maxMemory() / (1024 * 1024));
    
    String inputDirectory = null;
    
    Date startTime = new Date();
    System.out.println("Start Time : " + startTime);
    
    // Initialization
    
    Initialiser i = new Initialiser();
    
    // Create or reset IDS tables (except CONTEXT and CONTEXT_CONTEXT)
    
    print("Creating or resetting IDS tables...");
    
    i.createIDSTables();
    
    // Load IDS Tables CONTEXT and CONTEXT_CONTEXT
    
    //ArrayList<CONTEXT> 			contextList =         i.loadIDSCONTEXTTable();
    //ArrayList<CONTEXT_CONTEXT>  context_contextList = i.loadIDSCONTEXT_CONTEXTTable();
    
    
    // Load B*_ST tables 

    
    
    // test 
    
    //print(all.get(0));
    
    // Create IDS

    // Initialize Context system
    
     

    
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("hsn_civrec_ids");
	EntityManager em = factory.createEntityManager();
	 
	print("Initializing Context system...");
	Contxt.initializeContext();  

	print("Converting....");
	//print("1 heapsize.size() = " +  Runtime.getRuntime().maxMemory()/ (1000*1000) + "    "  + Runtime.getRuntime().totalMemory()/ (1000*1000) + "  " + Runtime.getRuntime().freeMemory()/ (1000*1000));
	
    
	em.getTransaction().begin();
	//Query query;
	//query = em.createNativeQuery("LOCK TABLES individual WRITE, indiv_indiv WRITE, indiv_context WRITE;");  
	//query.executeUpdate();  

	Ref.loadRelation_B();  // to standardize relations
	Ref.loadRelation_C();  // we need it to transform some relation
	//Ref.loadMunicipality();  // should not be here, but we need it
	
	int cnt = 0;
	
	outer:for(int j = 0; j < 10; j++){  // TRM XYZ

		//print("2 heapsize.size() = " +  Runtime.getRuntime().maxMemory()/ (1000*1000) + "    "  + Runtime.getRuntime().totalMemory()/ (1000*1000) + "  " + Runtime.getRuntime().freeMemory()/ (1000*1000));

	    i.loadTables(all, j);
	    
		//print("3 heapsize.size() = " +  Runtime.getRuntime().maxMemory()/ (1000*1000) + "    "  + Runtime.getRuntime().totalMemory()/ (1000*1000) + "  " + Runtime.getRuntime().freeMemory()/ (1000*1000));


	    i.linkObjects(all);

	    

		List<B0> b0List = all.get(1);
		
		//print("b0List.size() = " + b0List.size());
		
		
		for(B0 b0: b0List){

			cnt++;
			if(cnt % 1000 == 0){
				System.out.println("Before commit");
				em.getTransaction().commit();
				System.out.println("After  commit");

				em.clear();
				em.getTransaction().begin();
				print("Processed " + cnt + " IDNRs");
				System.out.println("Processed " + cnt + " IDNRs");
				//print("4 heapsize.size() = " +  Runtime.getRuntime().maxMemory()/ (1000*1000) + "    "  + Runtime.getRuntime().totalMemory()/ (1000*1000) + "  " + Runtime.getRuntime().freeMemory()/ (1000*1000));
				
				//query = em.createNativeQuery("LOCK TABLES individual WRITE, indiv_indiv WRITE, indiv_context WRITE;");  
				//query.executeUpdate();  

				//if(cnt > 5000) break outer;

			}

			//if(b0.getB1L() == null || b0.getB1L().size() == 0)
			//	System.out.println("No RP for IDNR = " + b0.getIdnr());
			for(B1 b1: b0.getB1L()){			
				b1.convert(em);
				break outer; //XYZ

			}
		}
		//break; // XYZ
	}
	em.getTransaction().commit();
	em.clear();
	em.getTransaction().begin();

	print("Processed " + cnt + " IDNRs");
	
	
	print("Saving Context System...");
    
    Contxt.save();

    
	print("Saving IDS Tables...");

	em.getTransaction().commit();
	em.clear();
    
    Date endTime = new Date();
    System.out.println("End Time : " + endTime);
    
    
    //System.out.println("Free Memory:"  + runtime.freeMemory() / (1024 * 1024)); 
    //System.out.println("Total Memory:" + runtime.totalMemory() / (1024 * 1024));
    //System.out.println("Max Memory:" + runtime.maxMemory() / (1024 * 1024));
    
    long runTime = (endTime.getTime() - startTime.getTime())/1000;

    System.out.println("Run Time : " + runTime + " seconds");

    System.out.println("Done");
    print("\nCivil Certificates - to IDS      finished\n");

    
    }
  
  	private static void print(List<B0> b0List) {
  		
  		for(B0 b0: b0List){
  			
  			System.out.println();
  			System.out.println(b0 + "  " + b0.getIdnr());
  			for(B1 b1: b0.getB1L()){			
  				System.out.println("  " + b1 + "  " + b1.getIdnr());
  				for(B2 b2: b1.getB2L()){
  					System.out.println("    " + b2 + "  " + b2.getIdnr());
  				}
  				if(b1.getB3() != null)
  					System.out.println("    " + b1.getB3() + "  " + b1.getB3().getIdnr());
  				
  				for(B4 b4: b1.getB4L()){
  					System.out.println("    " + b4 + "  " + b4.getIdnr());
  				}
  				if(b1.getB5() != null)
  					System.out.println("    " + b1.getB5() + "  " + b1.getB5().getIdnr());
  				
  				for(M1 m1: b1.getM1L()){
  					System.out.println("    " + m1 + "  " + m1.getIdnr());
  					
  					for(M2 m2: m1.getM2L()){
  						System.out.println("      " + m2 + "  " + m2.getIdnr());
  					}

  					for(M3 m3: m1.getM3L()){
  						System.out.println("      " + m3 + "  " + m3.getIdnr());
  					}

  					for(M4 m4: m1.getM4L()){
  						System.out.println("      " + m4 + "  " + m4.getIdnr());
  					}

  					for(M5 m5: m1.getM5L()){
  						System.out.println("      " + m5 + "  " + m5.getIdnr());
  					}

  					if(m1.getM6() != null)
  						System.out.println("    " + m1.getM6() + "  " + m1.getM6().getIdnr());

  					
  				}
  				
  				if(b1.getD1() != null){
  					
  					System.out.println("    " + b1.getD1() + "  " + b1.getD1().getIdnr());
  					
  					for(D2 d2: b1.getD1().getD2L()){
  						System.out.println("      " + d2 + "  " + d2.getIdnr());
  					}

  					for(D3 d3: b1.getD1().getD3L()){
  						System.out.println("      " + d3 + "  " + d3.getIdnr());
  					}


  					if(b1.getD1().getD4() != null)
  						System.out.println("      " + b1.getD1().getD4() + "  " + b1.getD1().getD4().getIdnr());
  					
  					
  				}
  				
  				
  			}
  			for(B6 b6: b0.getB6L()){			
  				System.out.println("  " + b6 + "  " + b6.getIdnr());
  			}
  		}
  	    
  		
  		
  		
  		
  		
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

	public static JTextArea getTextArea() {
		return textArea;
	}


	public static void setTextArea(JTextArea textArea) {
		CivilCertificatesToIDS.textArea = textArea;
	}


	public static DataOutputStream getOut() {
		return out;
	}


	public static void setOut(DataOutputStream out) {
		CivilCertificatesToIDS.out = out;
	}
  	
  	
  
}


