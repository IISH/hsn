package nl.iisg.ids;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JTextArea;

import nl.iisg.hsncommon.Common1;
import nl.iisg.hsncommon.ConstRelations2;
import nl.iisg.ref.Ref;
import nl.iisg.ref.Ref_FirstName;
import nl.iisg.ref.Ref_Relation_B;
import iisg.hsn.messages.Message;

public class IDS implements Runnable {
	
	static List<INDIVIDUAL>     individualL    = new ArrayList<INDIVIDUAL>();
	static List<INDIV_INDIV>    indiv_indivL   = new ArrayList<INDIV_INDIV>();
	static List<INDIV_CONTEXT>  indiv_contextL = new ArrayList<INDIV_CONTEXT>();
	static List<Person>         personL        = new ArrayList<Person>();
	static int                  indexPerson    = 0;
	
	static int indiv_count;
	static int indiv_indiv_count;
	static int indiv_context_count;
	
    static JTextArea textArea = null;
    static DataOutputStream out = null; 

    public IDS(DataOutputStream out) {
        setTextArea(textArea);
        setOut(out);
    }

    static String version;	
    static int idnr; 
    static int icount;
    
    
    
	static String [] sources = {"HSN BC", "HSN MC", "HSN PC", "HSN DC", "HSN PR"};


    public void run() {

        main(new String[0]);
    }
	
	
    public static void main(String [] s){
	
    if (false) {
        File file = new File("h:/sysout.log");
        try {
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            System.out.println("Output in h:/sysout.log");
            System.setOut(printStream);

        } catch (FileNotFoundException e) {
        }
    }

		
	System.out.println("started processing");	
	
    print("\nIDS - Integrate           started\n");
    
    
    
   // print("Creating or resetting IDS tables");

	
	createIDSTables();
	Ref.createRefTables();
	Ref.loadRelation_B();
	
	Message.initialise();
	
	//int [] idnr = getIDNRs();

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("hsn_ids");
	EntityManager em = emf.createEntityManager();
	
	
	
	
	//for(int i = 0; i < idnr.length; i++){
	idnr = 0;
	icount = 0;
	 for(int i = 0; i < 10; i++){ // we run in 10 batches
		
		setIndexPerson(0);
		indiv_count = 0;
		indiv_indiv_count = 0;
		indiv_context_count = 0;
		

		personL = new ArrayList<Person>();
		
		String s1 = String.format("%01d", i);
		
	    //print("\nReading IDNRs ending with " + s1);

		
		loadIDS("HSN BC", i);		
		handler();
		
		loadIDS("HSN MC", i);		
		handler();
		
		loadIDS("HSN DC", i);		
		handler();
		
		
		loadIDS("HSN PC", i);	
		handler();
		
		
		loadIDS("HSN_PR", i);	
		handler();

		integratePersons();
		

		//print("Writing IDS tables.....");
		writeIDS(em);

		em.getTransaction().begin();
		
		// Write Persons table
		
		for(Person pp: personL ){ // xxx
			//System.out.println("---> id = " + pp.getId() + "  " + pp.getFamilyName() + "   " + pp.getFirstName() + "  " +  pp.getId_D());
			if(pp.getRelationRP() != null && pp.getRelationRP().trim().length() > 0)						
				pp.setRelationRP(Common1.standardizeRelation(pp.getRelationRP()));				

			if(pp.getFamilyName() != null && !pp.getFamilyName().equalsIgnoreCase("N"))
				em.persist(pp);
		}
		
		em.getTransaction().commit();
		em.clear();
		

	}
	 print("Processed " + idnr + " IDNRs"); 
	
	//Ref.finalise(); 
    Message.finalise();

     print("\nIDS - Integrate           finished\n");
	
	 System.out.println("Finished processing");
	
	if(1==1)
		return;
	
	
	// Now we must decide which elements to save 

	
	
	//for(INDIVIDUAL i: getIndividualL())
		
		//System.out.println(" Id = " + i.getId() + ", type = " + i.getType() + ", value = "  + i.getValue());
	
		
}
    

    /**
     * 
     * This routine writes out the final IDS INDIVIDUAL, INDIV and INDIV_CONTEXT entries
     * 
     */
    private static void writeIDS(EntityManager em){
    	
    	// Sort the person list on (id_i_new, start code)
    	
    	for(Person p: personL){
    		String id_i_new = p.getId_I_new() == null ? "NULL" : p.getId_I_new();
    		//if(p.getSource().substring(0,6).equalsIgnoreCase("HSN DC"))
    		//	System.out.println(p.getIdnr() + "  " + p.getSource() + " " + p.getIdWithinGroup() + " " + p.getOriginalRelationRP() +  " " + id_i_new);
    	}
    	
    	Collections.sort(personL, new Comparator<Person>() // this gives all identified Persons, preferred source first
    			{
    		public int compare(Person p1, Person p2){
    			
    			//System.out.println(p1.getId_I_new() + "  " + p2.getId_I_new());
    			
    			int id_i_new_1 = new Integer(p1.getId_I_new());
    			int id_i_new_2 = new Integer(p2.getId_I_new()); 
    			
    			if(id_i_new_1 < id_i_new_2)
    				return -1;  
    			if(id_i_new_1 > id_i_new_2)
    				return  1;
    			
    			// sort inverse on start code to get startcode = 1 entries first
    			
    			//if(p1.getStartCode() < p2.getStartCode())
    				//return +1;
    			//if(p1.getStartCode() > p2.getStartCode())
    				//return -1;
    			
    			// Source = "HSN MC B4"
    			//           012345678
    			
    			String source1 = p1.getSource().substring(0, 8);
    			int    tab1    = new Integer(p1.getSource().substring(8,9));  
    			String source2 = p2.getSource().substring(0, 8);
    			int    tab2    = new Integer(p2.getSource().substring(8,9));  

    			
    			// sort on sources
    			
    			if(Arrays.asList(sources).indexOf(source1) < Arrays.asList(sources).indexOf(source2))
    				return -1;
    			if(Arrays.asList(sources).indexOf(source1) > Arrays.asList(sources).indexOf(source2))
    				return +1;
    			
    			// sort on tables e.g. M1 < M2
    			
    			if(tab1 < tab2)
    				return -1;
    			if(tab1 > tab2)
    				return +1;
    			

    				
    			return 0;
    		}
    			});
    	
    	
    	int indexP = 0;
    	int oldIDNR = -1;
    	//String ID_I_New = "";
    	//System.out.println("Number of Persons: " + personL.size());
    	ArrayList<Person> group = new ArrayList<Person>();
    	
    	em.getTransaction().begin();
    	while(indexP < personL.size()){
    		if(personL.get(indexP).getIdnr()!=oldIDNR){ // new group
    			oldIDNR = personL.get(indexP).getIdnr();
    			idnr++;
    			if(!group.isEmpty())
    				writeGroup(group, em);
    			group.clear();
        		if(idnr % 100 == 0)
        			print("Processed " + idnr + " IDNRs");
    		}
    		if(personL.get(indexP).getFamilyName() != null && !personL.get(indexP).getFamilyName().equalsIgnoreCase("N") && 
    				personL.get(indexP).getStartCode() > 0)
    			group.add(personL.get(indexP));
    		indexP++;
    		if(indexP % 1000 == 0){
    			em.getTransaction().commit();
    			em.clear();
    			em.getTransaction().begin();
    		}
    		
    	}
		if(!group.isEmpty())
			writeGroup(group, em);
		em.getTransaction().commit();
		em.clear();
		
		//String s = String.format("%8d", indiv_count);		
    	//print("  INDIVIDUAL     " + s + " rows");
		//s = String.format("%8d", indiv_indiv_count);		
    	//print("  INDIV_INDIV    " + s + " rows");
		//s = String.format("%8d", indiv_context_count);		
    	//print("  INDIV_CONTEXT  " + s + " rows");



    	
    }
    // if(++idCount % 100 == 0)
    
    /**
     * 
     * This routine writes out the INDIVIDUAL, INDIV and INDIV_CONTEXT entries for this idnr
     * 
     * @param p
     */
    private static void writeGroup(ArrayList<Person> group, EntityManager em){ 	
    	
    	
    	// xxx
    	
    	String[]  identPerson1 = {"BIRTH_DATE", "BIRTH_LOCATION", "LAST_NAME", "PREFIX_LAST_NAME", "FIRST_NAME", "DEATH_DATE", "DEATH_LOCATION", "HSN_RESEARCH_PERSON", "HSN_IDENTIFIER"};
    	String[]  identPerson  = null;
    	
    	HashSet<Integer> h = new HashSet<Integer>();
    	
    	//System.out.println("In write group");
    	
    	int id_prev = -1;
    	String prevSex = "";
    	
    	for(Person p: group){
    		if(new Integer(p.getId_I_new()) != id_prev){
    			id_prev = new Integer(p.getId_I_new());
    			identPerson = Arrays.copyOf(identPerson1, identPerson1.length);
    			//System.out.println("Person " + p.getId_I_new() + " " + p.getSource());
    			prevSex = "";
    		}
    		
    		
    		//System.out.println("P heeft id_i = " + p.getId_I_new());
    		
    		
    		
    		for(INDIVIDUAL ind: p.getIndividual()){
    			
	//			System.out.println("INDIVIDUAL  = " + ind.getType()  + "    " + ind.getValue());

    			boolean found = false;
    			//if(ind.getType().equalsIgnoreCase(("BIRTH_DATE")))	System.out.println("1->" + ind.getId() + "  " + ind.getType() + "  " + ind.getSource());
    			for(int j = 0; j < identPerson.length; j++){				

    				if(ind.getType().equalsIgnoreCase(identPerson[j])){
    					
    					found = true;    				

    	    			//if(ind.getType().equalsIgnoreCase(("BIRTH_DATE")))	System.out.println("2->" + ind.getId() + "  " + ind.getType() + "  " + ind.getSource() + " " + identPerson[j]);

    					
    					if(ind.getType().equals(identPerson[j])){  // This is the first time

    		    			//if(ind.getType().equalsIgnoreCase(("BIRTH_DATE")))	System.out.println("3->" + ind.getId() + "  " + ind.getType() + "  " + ind.getSource());

    						
    						identPerson[j] = identPerson[j].toLowerCase(); // So that we know it was used 
    						ind.setId_I(new Integer(p.getId_I_new()));
    						ind.setId_D(getVersion());
    						em.persist(ind);
    						indiv_count++;   					

    					}

    				}
    			}
				if(found == false){
				
					if(ind.getType().equalsIgnoreCase("SEX")){
						if(!ind.getValue().equalsIgnoreCase(prevSex)){
						
							prevSex = ind.getValue();
							ind.setId_I(new Integer(p.getId_I_new()));
							ind.setId_D(getVersion());
							em.persist(ind);
							indiv_count++;   					
						}
						
					}
					else
						if(ind.getType().equalsIgnoreCase("MARRIAGE_DATE")){ // only once
							
							if(!h.contains(ind.getYear() * 100 + ind.getMonth())){
								
								h.add(ind.getYear() * 100 + ind.getMonth());
								ind.setId_I(new Integer(p.getId_I_new()));
								ind.setId_D(getVersion());
								em.persist(ind);
								indiv_count++;
							}
							
						}
						else{
							ind.setId_I(new Integer(p.getId_I_new()));
							ind.setId_D(getVersion());
							em.persist(ind);
							indiv_count++;
						}
					
				}
    		}
    	}
    	


    	for(String x: sources){
    		//System.out.println("source = " + x);
    		for(Person p1: group){

    			if(p1.getSource() != null && p1.getSource().substring(0, 6).equals(x)){


    				//System.out.println("P IDNR = " + p1.getIdnr() + "  ID_I_New    " + p1.getId_I_new() + "      " + p1.getSource() +  "  " + p1.getFirstName() + "  "+ p1.getFamilyName());

    				if(p1.getId_I_new() != null /* && !p1.getId_I_new().equals("0") */){  // Person still valid    			

    					for(INDIV_INDIV ii: p1.getIndiv_indiv()){
    						//System.out.println("   ii = " + ii.getId_I_1() +  "  " + ii.getId_I_2() +    "  "+ ii.getRelation());

    						ii.setId_I_1(new Integer(p1.getId_I_new()));
    					}

    					//}
    					//}

    					for(Person p2: group){

    						if(p2 != p1 && p2.getSource() != null && p2.getSource().substring(0, 6).equals(x) /*!p2.getId_I_new().equals("0") */){

    							//System.out.println("   P2 IDNR = " + p2.getIdnr() + "  ID_I_New    " + p2.getId_I_new() + "      " + p2.getSource() +  "  " + p2.getFirstName() + "  "+ p2.getFamilyName());

    							for(INDIV_INDIV ii: p2.getIndiv_indiv()){    

    								//System.out.println("      ii = " + ii.getId_I_1() +  "  " + ii.getId_I_2() +    "  "+ ii.getRelation());

    								if(ii.getId_I_2() == p1.getId_I()){
    									//System.out.println("      match!");

    									ii.setId_I_2(new Integer(p1.getId_I_new()));
    								}
    							}
    						}
    					}
    				}
    			}
    		}
    	}


		//HashMap<<Integer, Integer>, Boolean> relatives = new <<Integer, Integer>, Boolean>();
    	
    	//INDIV_INDIV[][] relatives = new INDIV_INDIV[1000][1000];
    	ArrayList<INDIV_INDIV>[][] relations = new ArrayList[1000][1000];
    	Map<Integer, String> s = new HashMap<Integer, String>();
    	
    	
    	//System.out.println();
    	for(Person p: group){
			//System.out.println("P IDNR = " + p.getIdnr() + "  ID_I_New    " + p.getId_I_new() + "      " + p.getSource() +  "  " + p.getFirstName() + "  "+ p.getFamilyName());
    		for(INDIV_INDIV ii: p.getIndiv_indiv()){
    			//System.out.println("   " + ii.getId_I_1() +    "      " + ii.getId_I_2() + "    "+ ii.getRelation());
    			if (ii.getId_I_1() > 1000 * 1000 * 1000	&& ii.getId_I_2() > 1000 * 1000 * 1000 &&
    					ii.getRelation() != null && ii.getRelation().trim().length() > 0){
    				//System.out.println("rel in  " + ii.getRelation());
    				ii.setRelation(Common1.standardizeRelation(ii.getRelation()));
    				//System.out.println("rel out " + ii.getRelation());

    				if(ii.getRelation() != null){
    					if(relations[ii.getId_I_1() % 1000] [ii.getId_I_2() % 1000] == null)
    						relations[ii.getId_I_1() % 1000] [ii.getId_I_2() % 1000] = new ArrayList<INDIV_INDIV>();
    						relations[ii.getId_I_1() % 1000] [ii.getId_I_2() % 1000].add(ii);
    				}
    			}
				else{
					System.out.println("Skipping INDIV_INDIV, ID_D = "
							+ ii.getId() + ", ID_I_1 = " + ii.getId_I_1()
							+ ", ID_I_2 = " + ii.getId_I_2()
							+ ", Source = " + ii.getSource()
							+ ", Relation = " + ii.getRelation());
					System.out.println("p = " + p.toString());
					System.out.println("p.getId_I_new = " + p.getId_I_new());
				}
    		}
    	}
    	
    	
    	for(int i1 = 0; i1 < 1000; i1++)
    		for(int i2 = 0; i2 < 1000; i2++)
    			if(relations[i1][i2] != null){
    				INDIV_INDIV ii1 = null;
    				// Find first time invariant ii
    			o1: for(INDIV_INDIV ii: relations[i1][i2]){
    					//System.out.println(ii.getSource() + "   " + ii.getId_I_1() +    "      " + ii.getId_I_2() + "    "+ ii.getRelation());
    					if(ii.getMissing() != null && ii.getMissing().equalsIgnoreCase("Time_invariant")){
    						ii1 = ii;
    						break o1;
    					}
    				}
    				if(ii1 != null){
        				// persist first time invariant ii
						//System.out.println(ii1.getSource() + "   " + ii1.getId_I_1() +    "      " + ii1.getId_I_2() + "    "+ ii1.getRelation());

    					ii1.setId_D(getVersion());
    					em.persist(ii1);
						String r1 = ii1.getRelation().trim();

    					// message if different invariant relation
    					for(INDIV_INDIV ii2: relations[i1][i2]){
    						//System.out.println("    " + ii2.getSource() + "   " + ii2.getId_I_1() +    "      " + ii2.getId_I_2() + "    "+ ii2.getRelation());
    						if(ii2.getMissing() != null && ii2.getMissing().equalsIgnoreCase("Time_invariant")){
    							String r2 = ii2.getRelation().trim();

    							if(!r1.equalsIgnoreCase(r2) /* &&
    							   !((r1.equalsIgnoreCase("Spouse") && r2.equalsIgnoreCase("Wife")) || (r2.equalsIgnoreCase("Spouse") && r1.equalsIgnoreCase("Wife")) ||
    								 (r1.equalsIgnoreCase("Unknown")                                ||  r2.equalsIgnoreCase("Unknown"))) */){
       	    						//System.out.println(ii2.getSource() + "   " + ii2.getId_I_1() +    "      " + ii2.getId_I_2() + "    "+ ii2.getRelation());
   	    	     					//System.out.println(ii1.getSource() + "   " + ii1.getId_I_1() +    "      " + ii1.getId_I_2() + "    "+ ii1.getRelation());

    								message(new Integer((ii2.getId_I_1() / 1000) - (1000 * 1000)), "9106", 
    										String.format("%03d", ii2.getId_I_1() % 1000),
    										String.format("%03d", ii2.getId_I_2() % 1000),
    										r2 + " (" + ii1.getSource() + ") ",
    										r1 + " (" + ii2.getSource() + ") ");
    							}
    						}
    					}
    				}
    				// write time variant relations
    				boolean somethingWritten = (ii1 == null) ? false : true;
    				s.clear();
    				INDIV_INDIV iiu = null;
    				for(INDIV_INDIV ii: relations[i1][i2]){
    					//System.out.println(ii.getSource() + "   " + ii.getId_I_1() +    "      " + ii.getId_I_2() + "    "+ ii.getRelation());
    					int t = ii.getYear() * 10000 + ii.getMonth() * 100 + ii.getDay();
    					if(t > 0 && s.putIfAbsent(t, ii.getRelation()) == null){
							somethingWritten = true;
   							ii.setId_D(getVersion());
   							em.persist(ii);
    					}
    					else
    						if(t == 0)
    							iiu = ii;
    						
    				}
        			if(somethingWritten == false && iiu != null){
    					iiu.setId_D(getVersion());
    					em.persist(iiu);
        			}
    			}
        				
    	/*
		for (Person p : group) {
			
			//System.out.println("P IDNR = " + p.getIdnr() + "  ID_I_New    " + p.getId_I_new() + "      " + p.getSource() +  "  " + p.getFirstName() + "  "+ p.getFamilyName());

			outer: for (INDIV_INDIV ii : p.getIndiv_indiv()) {
				
				//System.out.println("   " + ii.getId_I_1() +    "      " + ii.getId_I_2() + "    "+ ii.getRelation());

				if (ii.getId_I_1() > 1000 * 1000 * 1000	&& ii.getId_I_2() > 1000 * 1000 * 1000) { // it has both Id_I update to new value


					if (ii.getRelation() != null && ii.getRelation().trim().length() > 0)
						ii.setRelation(Common1.standardizeRelation(ii.getRelation()));


					boolean timeInvariant = false;
					if(relations[ii.getId_I_1() % 1000] [ii.getId_I_2() % 1000] != null){

						//System.out.println(relatives[ii.getId_I_1() % 1000] [ii.getId_I_2() % 1000] + "   "+  ii.getRelation());
						for(INDIV_INDIV ii2: relations[ii.getId_I_1() % 1000] [ii.getId_I_2() % 1000]){

							if(!ii2.getRelation().equalsIgnoreCase(ii.getRelation())){
								
								String r1 = ii.getRelation();
								String r2 = ii2.getRelation();
								
								if(!((r1.equalsIgnoreCase("Spouse") && r2.equalsIgnoreCase("Wife")) || (r2.equalsIgnoreCase("Spouse") && r1.equalsIgnoreCase("Wife")) ||
			                         (r1.equalsIgnoreCase("Unknown")                                ||  r2.equalsIgnoreCase("Unknown"))))
									
									message(new Integer(p.getIdnr()), "9106", 
											String.format("%03d", ii.getId_I_1() % 1000),
											String.format("%03d", ii.getId_I_2() % 1000),
											ii2.getRelation() + " (" + ii2.getSource() + ") ",
											ii.getRelation() + " (" + ii.getSource() + ") ");
							}

							if(ii.getMissing() != null && ii.getMissing().equalsIgnoreCase("Time_invariant"))
								timeInvariant = true;
						}

						//continue outer;


					}
					else
						relations[ii.getId_I_1() % 1000] [ii.getId_I_2() % 1000] = new ArrayList<INDIV_INDIV>();

					relations[ii.getId_I_1() % 1000] [ii.getId_I_2() % 1000].add(ii);

					if(ii.getMissing() != null && ii.getMissing().equalsIgnoreCase("Time_invariant") & timeInvariant == true)
						continue outer;
					else{
						ii.setId_D(getVersion());
						em.persist(ii);

						// System.out.println("Adding   INDIV_INDIV, ID_D = " +
						// ii.getId() + ", ID_I_1 = " + ii.getId_I_1() +
						// ", ID_I_2 = " + ii.getId_I_2() +
						// ", Source = " + ii.getSource() + ", Relation = " +
						// ii.getRelation());

						indiv_indiv_count++;
					}
				} 
				else {
					
					if (!(ii.getId_I_1() == -1 || ii.getId_I_2() == -1)) { // Change!!
						System.out.println("Skipping INDIV_INDIV, ID_D = "
								+ ii.getId() + ", ID_I_1 = " + ii.getId_I_1()
								+ ", ID_I_2 = " + ii.getId_I_2()
								+ ", Source = " + ii.getSource()
								+ ", Relation = " + ii.getRelation());
						System.out.println("p = " + p.toString());
						System.out.println("p.getId_I_new = " + p.getId_I_new());
					}
					
				}
			}
		}
        */ 
    	// Write the INDIV_CONTEXT entries

    	for(Person p: group){
    		for(INDIV_CONTEXT ic: p.getIndiv_context()){
    			ic.setId_I(new Integer(p.getId_I_new()));
    			ic.setId_D(getVersion());
    			em.persist(ic);
    			indiv_context_count++;
    		}
    	}
    	
    	
    }
    
    /**
     * 
     * This routine determines if the Type of the INDIVIDUAL Object is one of the defining one for the Person object
     * If so, they must *not* be repeated.
     * 
     * 
     */
    private static boolean typeInPerson(String s){
    	
    	String[] inPerson = {"BIRTH_DATE", "BIRTH_LOCATION", "LAST_NAME", "PREFIX_LAST_NAME", "FIRST_NAME", "DEATH_DATE", "DEATH_LOCATION", "HSN_RESEARCH_PERSON", "HSN_IDENTIFIER"};
    	
    	for(String t: inPerson)
    		if(t.equals(s))
    			return true;
    	
    	
    	return false;
    	
    }
    
    

/**
 * 
 * This routine reads INDIVIDUAL entries and creates Person objects from them.
 * It then links the INDIV_INDIV and INDIV_CONTEXT entries to the Person objects
 * 
 * 
 * 
 * 
 * @param component
 */
private static void handler(){
	
	//System.out.println("Handler 2");
	
	int i_i = 0;
	String  idnrO = "";
	int relationO = -1;

	//EntityManagerFactory emf = Persistence.createEntityManagerFactory("hsn_ids");
	//EntityManager em = emf.createEntityManager();
	//em.getTransaction().begin();

	
	Person p = null; 
	int oldId = -1;
	String oldIdnr = "";
	//System.out.println("INDIVIDUAL Array: " + individualL.size());
	for(int i = 0; i < individualL.size(); i++){
		
		// Next code executes once when a new person appears (date and/or idnr and or relation to RP/PersonID changes)
		
		if(p == null ||
				(!individualL.get(i).getId_D().equalsIgnoreCase(oldIdnr) || individualL.get(i).getId_I() != oldId)){ // new person
			
			if(!individualL.get(i).getId_D().equalsIgnoreCase(oldIdnr)) icount++;
					
			oldIdnr = individualL.get(i).getId_D();
			oldId = individualL.get(i).getId_I();
			if(p != null && p.getFamilyName() != null && !p.getFamilyName().equalsIgnoreCase("N"))   // If there is no family name, we drop the person altogether
				personL.add(p);  // (1) save old person 
			else
				if(p != null)
					System.out.println("Skipping Person with IDNR = " + p.getIdnr());
			
			 p = new Person(); // (2) allocate new person, it will be saved at either (1) or (3)
			 
			 p.setIdnr(new Integer(individualL.get(i).getId_D()));
			 //p.setRelationRP(individualL.get(i).getId_I());			 
			 p.setId_I(individualL.get(i).getId_I());
			 p.setSource(individualL.get(i).getSource());
		}

	    p.getIndividual().add(individualL.get(i));  // Save the INDIVIDUAL object
		

		
		// Next code executes a number of times for the person allocated above
		// Gather the identifying information fields from various rows of the INDIVIDUAL table
		
	    if(individualL.get(i).getType().equalsIgnoreCase("BIRTH_DATE")){
	    	
	    	if(individualL.get(i).getYear() > 0){  // date
	    		//System.out.println("Setting Birthdate");
	    		if(p.getBirthDay() == 0){
	    			p.setBirthDay  (individualL.get(i).getDay());
	    			p.setBirthMonth(individualL.get(i).getMonth());
	    			p.setBirthYear (individualL.get(i).getYear());
	    		}
	    	}
	    	else
	    		if(individualL.get(i).getStart_year() > 0){  // interval
	    			if(p.getBirthStartDay() == 0){
	    				p.setBirthStartDay(individualL.get(i).getStart_day());
	    				p.setBirthStartMonth(individualL.get(i).getStart_month());
	    				p.setBirthStartYear(individualL.get(i).getStart_year());
	    				p.setBirthEndDay(individualL.get(i).getEnd_day());
	    				p.setBirthEndMonth(individualL.get(i).getEnd_month());
	    				p.setBirthEndYear(individualL.get(i).getEnd_year());
	    			}
	    		}
	    	continue;
	    }
	    
	    
	    else
	    if(individualL.get(i).getType().equalsIgnoreCase("BIRTH_LOCATION")){
	    	if(p.getId_BC() == null)
	    		p.setId_BC     (individualL.get(i).getId_C());
			//p.setId_D(individualL.get(i).getSource());

	    	continue;
	    }
	    else
	    if(individualL.get(i).getType().equalsIgnoreCase("LAST_NAME")){
	    	if(p.getFamilyName() == null)
	    		p.setFamilyName(individualL.get(i).getValue());
			//p.setId_D(individualL.get(i).getSource());

	    	continue;
	    }
	    else
		if(individualL.get(i).getType().equalsIgnoreCase("FIRST_NAME")){
			if(p.getFirstName() == null)
				p.setFirstName(individualL.get(i).getValue());
			
	    	continue;
		}
		else
		if(individualL.get(i).getType().equalsIgnoreCase("PREFIX_LAST_NAME")){
			//p.setId_D(individualL.get(i).getSource());
			if(p.getPrefix() == null)
				p.setPrefix(individualL.get(i).getValue());
	    	continue;
		}
		else
	    if(individualL.get(i).getType().equalsIgnoreCase("DEATH_DATE")){
			//p.setId_D(individualL.get(i).getSource());
	    	if(p.getDeathDay() == 0){
	    		if(individualL.get(i).getYear() > 0){
	    			p.setDeathDay  (individualL.get(i).getDay());
	    			p.setDeathMonth(individualL.get(i).getMonth());
	    			p.setDeathYear (individualL.get(i).getYear());
	    		}
	    	}
	    	continue;
	    }
	    if(individualL.get(i).getType().equalsIgnoreCase("DEATH_LOCATION")){
			//p.setId_D(individualL.get(i).getSource());
	    	if(p.getId_DC() == null)
	    		p.setId_DC     (individualL.get(i).getId_C());
	    	continue;
	    }
	    
	    else
		if(individualL.get(i).getType().equalsIgnoreCase("SEX")){
			//p.setId_D(individualL.get(i).getSource());
			if(p.getSex() == null)
				p.setSex(individualL.get(i).getValue().substring(0, 1).toUpperCase());
	    	continue;
		}
		
		else
		    if(individualL.get(i).getType().equalsIgnoreCase("HSN_RESEARCH_PERSON")){
				p.setSource(individualL.get(i).getSource());  // because everybody has this
				
				if(individualL.get(i).getValue().equalsIgnoreCase("HSN RP")){
					if(p.getRelationRP() == null)
						p.setRelationRP("RP");
					//personID_RP = individualL.get(i).getId_I();
					//System.out.println("RP nr = " + personID_RP);
					continue;
				}
		    }
	
	}
	if(p != null && p.getFamilyName() != null && !p.getFamilyName().equalsIgnoreCase("N"))
		personL.add(p);  // (3) because there still is a Person p that must be saved
	else
		if(p != null)
			System.out.println("Skipping Person with IDNR = " + p.getIdnr());

	
	//print("Number of IDNRs: " + icount);
	
	// Now we must link the INDIV_INDIV elements to the Persons
	// Note: We should still remove the duplicates from INDIV_INDIV
	// Note: This must be improved on, it is too slow
	
	
	int indexP = getIndexPerson();
	for(INDIV_INDIV ii: getIndiv_indivL()){
		//System.out.println("ii Id_D = " + ii.getId_D() + " ID_I_1 = " + ii.getId_I_1() + " ID_I_2 = " + ii.getId_I_2());
		while(indexP < personL.size() && (personL.get(indexP).getIdnr() != new Integer(ii.getId_D()) || personL.get(indexP).getId_I() != ii.getId_I_1())){
			//System.out.println("Skip  p  Id_D = " + personL.get(indexP).getIdnr() + " ID_I = " + personL.get(indexP).getId_I());
			indexP++;
		}
		
		if(indexP < personL.size() && personL.get(indexP).getIdnr() == new Integer(ii.getId_D()) && personL.get(indexP).getId_I() == ii.getId_I_1()){			
			personL.get(indexP).getIndiv_indiv().add(ii);
			//System.out.println("Add   p  Id_D = " + personL.get(indexP).getIdnr() + " ID_I = " + personL.get(indexP).getId_I());

			//if(personL.get(indexP).getIdnr() == 344004){
				//personL.get(indexP).setRelationRP(ii.getRelation());
			//	System.out.println("Adding IDNR = " + ii.getId_D() + " I1 " +   ii.getId_I_1() + " I2 " +   ii.getId_I_2() + "rel = " + ii.getRelation() );
			//}
		}
	}

	
	// Now we must link the INDIV_CONTEXT elements to the Persons
	// Note: This must be improved on, it is too slow

	indexP = getIndexPerson();
	for(INDIV_CONTEXT ic: getIndiv_contextL()){
		//System.out.println(" ii Id_D = " + ii.getId_D() + " ID_I_1 = " + ii.getId_I_1());
		while(indexP < personL.size() && (personL.get(indexP).getIdnr() != new Integer(ic.getId_D()) || personL.get(indexP).getId_I() != ic.getId_I())){
			//System.out.println("p  Id_D = " + personL.get(indexP).getIdnr() + " ID_I = " + personL.get(indexP).getId_I());
			indexP++;
		}
		
		if(indexP < personL.size() && personL.get(indexP).getIdnr() == new Integer(ic.getId_D()) && personL.get(indexP).getId_I() == ic.getId_I()){
			personL.get(indexP).getIndiv_context().add(ic);
			//System.out.println("Adding");
		}
	}
	
	
	// Now we must set the relation to the RP of every person
	// It is somewhere in it's INDIV_INDIV elements
	// But first we have to know (per IDNR) what the personID of the RP is!
	// Worse, there can be more than one RP per IDNR because the Civil Certificates 
	// have RP = 1 at birth, xxxxx11/xxxxx12 at Marriage and 51 at death.
	// So we have to find all RPs
	
	
	indexP = getIndexPerson();  // set to first person in this slot
	
	//System.out.println("Handler 3");

	
	int oldIDNR = -1;
	ArrayList<Person> t = new ArrayList<Person>(); // will contain all persons for an IDNR
	while(indexP < personL.size()){
		
		//System.out.println("Handler 3.4, indexP = " + indexP);

		if(personL.get(indexP).getIdnr() != oldIDNR){
			
			oldIDNR = personL.get(indexP).getIdnr();
			if(t.size() > 0){				
				//System.out.println("Handler 4");

				int iDRP = -1;
				for(Person p1: t){  // find all RPs
					//System.out.println("Found " + p1.getRelationRP());
					if(p1.getRelationRP() != null && p1.getRelationRP().equals("RP")){						
						iDRP = p1.getId_I();
						for(Person p2: t){ // Check all persons
							for(INDIV_INDIV ii: p2.getIndiv_indiv()){ // Check all INDIV_INDIV entries
								if(ii.getId_I_2() == iDRP){   // If it is to the RP...
									p2.setOriginalRelationRP(ii.getRelation().trim()); // ... save the relation
									p2.setRelationRP(ii.getRelation().trim());         // ... save the relation
								}
							}
						}
					}
				}
			}
			t.clear();
		}
		t.add(personL.get(indexP));
		//System.out.println("t.size() = " + t.size());

		//System.out.println("  p  Id_D = " + personL.get(indexP).getIdnr() + " ID_I = " + personL.get(indexP).getId_I());
		indexP++;
	}
	if(t.size() > 0){				
		int iDRP = -1;
		for(Person p1: t){
			if(p1.getRelationRP() != null && p1.getRelationRP().equals("RP")){
				iDRP = p1.getId_I();
				for(Person p2: t){
					for(INDIV_INDIV ii: p2.getIndiv_indiv()){
						if(ii.getId_I_2() == iDRP){
							p2.setOriginalRelationRP(ii.getRelation().trim()); // ... save the relation
							p2.setRelationRP(ii.getRelation());
						}
					}
				}
			}
		}
	}

	// print xxy
	/*
	indexP = getIndexPerson();
	for(Person pp: personL){
		System.out.println("Person = " + pp.getIdnr());
		for(INDIVIDUAL i: pp.getIndividual())
			System.out.println("INDIVIDUAL  = " + i.getType()  + "    " + i.getValue());
		for(INDIV_INDIV ii: pp.getIndiv_indiv())
			System.out.println("INDIV_INDIV = " + ii.getId_I_1()   + "        " + ii.getId_I_2() + "   " + ii.getRelation());
		System.out.println();
	}
		*/
	// print end
	setIndexPerson(personL.size());  // next slot
	
	//for(Person pp: personL ){
		
		//System.out.println(pp);
		
		//for(INDIV_INDIV iii: pp.getIndiv_indiv())
			//System.out.println("        " + iii);
		
		//for(INDIV_CONTEXT iic: pp.getIndiv_context())
			//System.out.println("        " + iic);
		
	//}
	
	
	//System.out.println("Start saving");

	//em.getTransaction().commit();

	//System.out.println("Finished saving");
	//System.out.println("Finished processing " + component);

	
}
	
private static void loadIDS(String component, int lastDigit){

	//System.out.println("Start loading " + component); 

	//print(" " + component + ":");
	//Civil Certificates Marriage Certificates
	String lastD = String.format("%01d", lastDigit);
	
	
	String persistence = "";
	if(component.equalsIgnoreCase("HSN BC") || component.equalsIgnoreCase("HSN MC") || component.equalsIgnoreCase("HSN DC"))
		persistence = "hsn_civrec_ids_00";
	if(component.equalsIgnoreCase("HSN PC"))
		persistence = "hsn_perscd_ids_00";
//	if(component.equalsIgnoreCase("Population Register"))
	if(component.equalsIgnoreCase("HSN_PR"))
		persistence = "hsn_popreg_total_ids_00";
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence);
	EntityManager em = emf.createEntityManager();
	
	System.out.println("Start loading INDIVIDUAL from " + component);
	print("Reading .");

	
	//Query q = em.createQuery("select a from INDIVIDUAL a where a.id_D < 9000"); 
	//Query q = em.createQuery("select a from INDIVIDUAL a where a.source like '" + component + "%' and  a.id_D like '%00" + lastD + "'"); 
	Query q = em.createQuery("select a from INDIVIDUAL a where a.source like '" + component + "%' and  a.id_D like '%" + lastD + "'"); 
	//Query q = em.createQuery("select a from INDIVIDUAL a where a.source like '" + component + "%'"); 
	//Query q = em.createQuery("select a from INDIVIDUAL a"); 
	setIndividualL(q.getResultList());	
	
	Collections.sort(individualL, new Comparator<INDIVIDUAL>()
			{
		public int compare(INDIVIDUAL i1, INDIVIDUAL i2){

			int idnr1 = new Integer(i1.getId_D());
			int idnr2 = new Integer(i2.getId_D());
			
			if(idnr1 < idnr2)
				return -1;
			if(idnr1 > idnr2)
				return  1;

			if(i1.getId_I() < i2.getId_I())
				return -1;
			if(i1.getId_I() > i2.getId_I())
				return  1;
			
			// Source = "HSN MC B4"
			//           012345678
			
			//String source1 = i1.getSource().substring(0, 8);
			//int    tab1    = new Integer(i1.getSource().substring(8,9));  
			//String source2 = i2.getSource().substring(0, 8);
			//int    tab2    = new Integer(i2.getSource().substring(8,9));  

			
			// sort on sources
			
			//if(Arrays.asList(sources).indexOf(source1) < Arrays.asList(sources).indexOf(source2))
				//return -1;
			//if(Arrays.asList(sources).indexOf(source1) > Arrays.asList(sources).indexOf(source2))
				//return +1;
			
			// sort on tables
			
			//if(tab1 < tab2)
				//return -1;
			//if(tab1 > tab2)
				//return +1;
			
			
			return 0;
		}
			});

	System.out.println("Finished loading INDIVIDUAL from " + component + ", " + q.getResultList().size() + " rows");
	String s = String.format("%8d", q.getResultList().size());
	//print("  INDIVIDUAL     " + s + " rows");

	System.out.println("Start loading INDIV_INDIV from " + component);
	print("Reading ..");

	//q = em.createQuery("select a from INDIV_INDIV a where a.id_D == 1090"); 
	//q = em.createQuery("select a from INDIV_INDIV a where a.source like '" + component + "%' and a.id_D like '%00" + lastD + "'"); 
	q = em.createQuery("select a from INDIV_INDIV a where a.source like '" + component + "%' and a.id_D like '%" + lastD + "'"); 
	//q = em.createQuery("select a from INDIV_INDIV a where a.source like '" + component + "%'");

	//q = em.createQuery("select a from INDIV_INDIV a"); 
	setIndiv_indivL(q.getResultList());	
	
	Collections.sort(indiv_indivL, new Comparator<INDIV_INDIV>()
			{
		public int compare(INDIV_INDIV i1, INDIV_INDIV i2){
			

			int idnr1 = new Integer(i1.getId_D());
			int idnr2 = new Integer(i2.getId_D());

			if(idnr1 < idnr2)
				return -1;
			if(idnr1 > idnr2)
				return  1;
			
			if(i1.getId_I_1() < i2.getId_I_1())
				return -1;
			if(i1.getId_I_1() > i2.getId_I_1())
				return  1;
			if(i1.getId_I_2() < i2.getId_I_2())
				return -1;
			if(i1.getId_I_2() > i2.getId_I_2())
				return  1;
			
			return 0;
		}
			});



	System.out.println("Finished loading INDIV_INDIV from " + component + ", " + q.getResultList().size() + " rows");
	s = String.format("%8d", q.getResultList().size());
	//print("  INDIV_INDIV    " + s + " rows");

	
	System.out.println("Start loading INDIV_CONTEXT from " + component);
	
	print("Reading ...");

	
	//q = em.createQuery("select a from INDIV_CONTEXT a  where a.source like '" + component + "%' and a.id_D like '%00" + lastD + "'"); 
	q = em.createQuery("select a from INDIV_CONTEXT a  where a.source like '" + component + "%' and a.id_D like '%" + lastD + "'"); 
	//q = em.createQuery("select a from INDIV_CONTEXT a  where a.source like '" + component + "%'"); 
	//q = em.createQuery("select a from INDIV_CONTEXT a"); 
	setIndiv_contextL(q.getResultList());	
	
	Collections.sort(indiv_contextL, new Comparator<INDIV_CONTEXT>()
			{
		public int compare(INDIV_CONTEXT ic1, INDIV_CONTEXT ic2){

			int idnr1 = new Integer(ic1.getId_D());
			int idnr2 = new Integer(ic2.getId_D());

			if(idnr1 < idnr2)
				return -1;
			if(idnr1 > idnr2)
				return  1;
			
			if(ic1.getId_I() < ic2.getId_I())
				return -1;
			if(ic1.getId_I() > ic2.getId_I())
				return  1;
			
			return 0;
		}
			});



	System.out.println("Finished loading INDIV_CONTEXT from " + component + ", " + q.getResultList().size() + " rows");
	s = String.format("%8d", q.getResultList().size());
	//print("  INDIV_CONTEXT  " + s + " rows");


	
	
	System.out.println("Finished loading " + component); 

	
}
	
	
	
private static void createIDSTables(){
		
		System.out.println("Start creating or resetting IDS-Tables");
		
		try{
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("hsn_ids");				
			EntityManager em = factory.createEntityManager();	
			
			
			em.getTransaction().begin();

			Query query = em.createNativeQuery(CreateIDSTables.INDIVIDUAL);  
			query.executeUpdate();
			//query = em.createNativeQuery(CreateIDSTables.CONTEXT);  
			//query.executeUpdate();
			//query = em.createNativeQuery(CreateIDSTables.CONTEXT_CONTEXT);  
			//query.executeUpdate();
			query = em.createNativeQuery(CreateIDSTables.INDIV_CONTEXT);  
			query.executeUpdate();
			query = em.createNativeQuery(CreateIDSTables.INDIV_INDIV);  
			query.executeUpdate();
			query = em.createNativeQuery(CreatePersonTable.PERSONS);  
			query.executeUpdate();

			
			em.getTransaction().commit();
            em.clear();
		
			em.getTransaction().begin();

			query = em.createNativeQuery(CreateIDSTables.INDIVIDUAL_TRUNCATE);  
			query.executeUpdate();
			//query = em.createNativeQuery(CreateIDSTables.CONTEXT_DELETE);  
			//query.executeUpdate();
			//query = em.createNativeQuery(CreateIDSTables.CONTEXT_CONTEXT_DELETE);  
			//query.executeUpdate();
			query = em.createNativeQuery(CreateIDSTables.INDIV_CONTEXT_TRUNCATE);  
			query.executeUpdate();
			query = em.createNativeQuery(CreateIDSTables.INDIV_INDIV_TRUNCATE);  
			query.executeUpdate();
			query = em.createNativeQuery(CreatePersonTable.PERSON_TRUNCATE);  
			query.executeUpdate();

			
			em.getTransaction().commit();
            em.clear();
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Finished creating or resetting IDS-Tables");
		
	}




/**
 * 
 * This routine combines the persons from the three components
 * 
 */
private static void integratePersons(){
	
	System.out.println("Integrate Persons");
	
	
	Collections.sort(personL, new Comparator<Person>() // this effectively integrates the three sources
			{
		public int compare(Person p1, Person p2){

			if(p1.getIdnr() < p2.getIdnr())
				return -1;
			if(p1.getIdnr() > p2.getIdnr())
				return  1;
			
			if(p1.getId_I() < p2.getId_I())
				return -1;
			if(p1.getId_I() > p2.getId_I())
				return  1;
			
			return 0;
		}
			});
	
	
	
	int idnrOld = -1;
	ArrayList<Person> family = null; 
	for(int i = 0; i < personL.size(); i++){
		if(personL.get(i).getIdnr() != idnrOld){
			
			if(family != null){
				
				handleRP(family); 
				handleMothers(family); 
				handleFathers(family); 
				handleSpouses(family); 
				handleChildren(family); 
				handleSiblings(family); 
				handleParentsInLaw(family); 
				handleOthers(family); 

			}
			
			idnrOld = personL.get(i).getIdnr();
			family = new ArrayList<Person>(); 
		
		}		
		
		family.add(personL.get(i));
		
	}
	if(family != null){
		
		handleRP(family); 
		handleMothers(family); 
		handleFathers(family);
		handleSpouses(family); 
		handleChildren(family); 
		handleSiblings(family); 
		handleParentsInLaw(family);		
		handleOthers(family); 

	}
	
	
	

}

/**
 * Routine to handle RPs
 * @param family
 * @return
 */
private static void handleRP(ArrayList<Person> family){
	
	ArrayList<Person> group = new ArrayList<Person>();
	for(Person p: family){
		if(p.getRelationRP() != null && p.getRelationRP().equals("RP")){
			String idnr6 = String.format("%06d", p.getIdnr());
			p.setId_I_new("1" +  idnr6 + "001");
			p.setStartCode(2); // preset 
			group.add(p);
		}
		
	}
	
	if(group.size() == 0){
		
//		System.out.println("CRR 1 " + family.get(0).getIdnr());
//		System.out.println();
//		for(Person p: family){
			
			
//			System.out.println("CRR 1 " +  p.getIdnr() + "  " + p.getFamilyName() + "  " + p.getFirstName() + "  " + p.getRelationRP()) ;
			
//		}
		
		//System.exit(9);
		
	}
	
	setStartCode(group);
	family.removeAll(group);
    
	
}
/**
 * Routine to handle mothers
 * 
 * @param family
 * @return
 */
private static void handleMothers(ArrayList<Person> family){
	
	ArrayList<Person> group = new ArrayList<Person>();
	for(Person p: family){
		if(p.getRelationRP() != null && 
				(p.getRelationRP().equalsIgnoreCase("Moeder") || p.getRelationRP().equals("" + ConstRelations2.MOEDER) ||
						p.getRelationRP().equalsIgnoreCase("Stiefmoeder") || p.getRelationRP().equals("" + ConstRelations2.STIEFMOEDER))){
			p.setStartCode(2);  // preset
			group.add(p);
		}
	}
	
	setIDWithinGroup(group, true);  // only Name compare for mothers
	
	
	ArrayList<Person> group2 = new ArrayList<Person>();  
	for(Person p: group)
		if(p.getIdWithinGroup() != 1)
			group2.add(p);	
	
	Person preferredPerson = setStartCode(group); //find preferred source
	
	for(Person p: group){
		String idnr6 = String.format("%06d", p.getIdnr());
		p.setId_I_new("1" + idnr6 + "002");
	}
	
	for(Person p: group2){
		message(new Integer(p.getIdnr()), "9105", p.getFirstName() + " " + p.getFamilyName(), p.getSource(),
				preferredPerson.getFirstName() + " " + preferredPerson.getFamilyName(),preferredPerson.getSource());
		
		//p.;
		//p.setId_I_new("-1");  // this indicates that we know the person is removed, so no messages about her
	}
			
			
	family.removeAll(group);
}
/**
 * Routine to handle fathers
 * 
 * @param family
 * @return
 */
private static void handleFathers(ArrayList<Person> family){
	
	ArrayList<Person> group = new ArrayList<Person>();
	for(Person p: family){
		if(p.getRelationRP() != null && 
				(p.getRelationRP().equalsIgnoreCase("Vader") || p.getRelationRP().equals("" + ConstRelations2.VADER) ||
						p.getRelationRP().equalsIgnoreCase("Stiefvader") || p.getRelationRP().equals("" + ConstRelations2.STIEFVADER))){
			p.setStartCode(2);  // preset
			group.add(p);
		}
	}
	
	setIDWithinGroup(group, true);  // only Name compare for fathers
	
	
	ArrayList<Person> group2 = new ArrayList<Person>();  
	for(Person p: group)
		if(p.getIdWithinGroup() != 1)
			group2.add(p);	
	
	Person preferredPerson = setStartCode(group); //find preferred source
	
	for(Person p: group){
		String idnr6 = String.format("%06d", p.getIdnr());
		p.setId_I_new("1" + idnr6 + "012");
	}
	
	for(Person p: group2){
		//message(new Integer(p.getIdnr()), "9105", p.getFirstName() + " " + p.getFamilyName(), p.getSource(), 
			//	preferredPerson.getFirstName() + " " + preferredPerson.getFamilyName(),preferredPerson.getSource());
		
		//p.setStartCode(0);
		//p.setId_I_new("-1"); // this indicates that we know the person is removed, so no messages about him


	}
			
	family.removeAll(group);
}
/**
 * Routine to handle spouses
 * 
 * @param family
 * @return
 */
private static void handleSpouses(ArrayList<Person> family){
	
	ArrayList<Person> group = new ArrayList<Person>();
	for(Person p: family){
		if(p.getRelationRP() != null && 
				(p.getRelationRP().equalsIgnoreCase("Echtgenoot") || 
					 	 p.getRelationRP().equalsIgnoreCase("Echtgenote") || 
					 	 p.getRelationRP().equals("" + ConstRelations2.ECHTGENOTE_HOOFD) || 
					 	 p.getRelationRP().equals("" + ConstRelations2.ECHTGENOOT_EXPLICIET_HOOFD) ||
					 	 p.getRelationRP().equals("" + ConstRelations2.ECHTGENOOT_MAN_GEEN_HOOFD) ||
					 	 p.getRelationRP().equals("" + ConstRelations2.PARTNER) 
			 	 )){
			//System.out.println("CRR 1 " +  p.getIdnr() + "  " + p.getFamilyName() + "  " + p.getFirstName() + "  " + p.getRelationRP() + "  " + p.getSource()) ;

			p.setStartCode(2);  // preset
			group.add(p);
		}
		
	}
	
	setIDWithinGroup(group, false);     // this gives one or more different spouses
	
	Collections.sort(group, new Comparator<Person>() 
			{
		public int compare(Person p1, Person p2){
			
			if(p1.getIdWithinGroup() < p2.getIdWithinGroup())
				return -1;
			if(p1.getIdWithinGroup() > p2.getIdWithinGroup())
				return  1;
				
			return 0;
		}
			});
	
	
	int id_prev = -1;
	ArrayList<Person> group2 = new ArrayList<Person>();
	
	int spouseCount = 0;
	for(Person p: group){
		if(p.getIdWithinGroup() != id_prev){
			spouseCount++;
			if(spouseCount > 9){
				System.out.println("Too many wifes!!, idnr = " + p.getIdnr() + " , id_i = " + p.getId_I());
				break;
			}
			if(group2.size() > 0){
				setStartCode(group2); //find preferred source
				for(Person p2: group2){

					String idnr6 = String.format("%06d", p2.getIdnr());
					p2.setId_I_new("1" + idnr6 + "02" + p2.getIdWithinGroup());
					System.out.println("CRR 1 " +  p.getIdnr() + "  " + p.getIdWithinGroup() + "  " + p.getId_I_new() + "  " + p.getFamilyName() + "  " + p.getFirstName() + "  " + p.getRelationRP() + "  " + p.getSource() + "  " + p.getIdWithinGroup() + "  " + p.getId_I_new()) ;

				}

				group2.clear();
			}
			id_prev = p.getIdWithinGroup();  	
			
		}
		group2.add(p);
		
	}
	if(group2.size() > 0){
		setStartCode(group2); //find preferred source
		for(Person p2: group2){
			String idnr6 = String.format("%06d", p2.getIdnr());
			p2.setId_I_new("1" + idnr6 + "02" + p2.getIdWithinGroup());
			System.out.println("CRR 1 " +  p2.getIdnr() + "  "  + p2.getIdWithinGroup() + "  " + p2.getId_I_new() + p2.getFamilyName() + "  " + p2.getFirstName() + "  " + p2.getRelationRP() + "  " + p2.getSource() + "  " + p2.getIdWithinGroup() + "  " + p2.getId_I_new()) ;

		}

	}
	
	family.removeAll(group);

}

/**
 * Routine to handle children
 * 
 * @param family
 * @return
 */
private static void handleChildren(ArrayList<Person> family){
	
	ArrayList<Person> group = new ArrayList<Person>();
	for(Person p: family){
		if(p.getRelationRP() != null && 
				(p.getRelationRP().equalsIgnoreCase("Zoon") || p.getRelationRP().equals("" + ConstRelations2.ZOON) ||
						p.getRelationRP().equalsIgnoreCase("Stiefzoon") || p.getRelationRP().equals("" + ConstRelations2.STIEFZOON) ||
						p.getRelationRP().equalsIgnoreCase("Dochter") || p.getRelationRP().equals("" + ConstRelations2.DOCHTER) ||
						p.getRelationRP().equalsIgnoreCase("Kind") || p.getRelationRP().equals("" + ConstRelations2.KIND_PK) ||
						p.getRelationRP().equalsIgnoreCase("Stiefkind") || p.getRelationRP().equals("" + ConstRelations2.STIEFKIND_PK) ||
						p.getRelationRP().equalsIgnoreCase("Stiefdochter") || p.getRelationRP().equals("" + ConstRelations2.STIEFDOCHTER))){
			p.setStartCode(2);  // preset
			group.add(p);
			
		}
	}
	
	setIDWithinGroup(group, false);	
	
	Collections.sort(group, new Comparator<Person>() 
			{
		public int compare(Person p1, Person p2){
			
			if(p1.getIdWithinGroup() < p2.getIdWithinGroup())
				return -1;
			if(p1.getIdWithinGroup() > p2.getIdWithinGroup())
				return  1;
				
			return 0;
		}
			});
	
	
	int id_prev = -1;
	ArrayList<Person> group2 = new ArrayList<Person>();
	
	for(Person p: group){
		if(p.getIdWithinGroup() != id_prev){
			if(group2.size() > 0){
				setStartCode(group2); //find preferred source
				for(Person p2: group2){					
					//p2.setId_I_new("1" + idnrSixCharacters(p2.getIdnr()) + "03" + p2.getIdWithinGroup());
					String n = (30 + p2.getIdWithinGroup()) + "";
					String idnr6 = String.format("%06d", p2.getIdnr());
					p2.setId_I_new("1" + idnr6 + "0" + n);
				}

				group2.clear();
			}
			id_prev = p.getIdWithinGroup();  	
			
		}
		group2.add(p);
		
	}
	if(group2.size() > 0){
		setStartCode(group2); //find preferred source
		for(Person p2: group2){					
			//p2.setId_I_new("1" + idnrSixCharacters(p2.getIdnr()) + "03" + p2.getIdWithinGroup());
			String n = (30 + p2.getIdWithinGroup()) + "";
			String idnr6 = String.format("%06d", p2.getIdnr());
			p2.setId_I_new("1" + idnr6 + "0" + n);

		}

	}
	
	family.removeAll(group);

	
}
/**
 * Routine to handle siblings
 * 
 * @param family
 * @return
 */
private static void handleSiblings(ArrayList<Person> family){
	
	int id = 101;
	ArrayList<Person> group = new ArrayList<Person>();
	for(Person p: family){
		if(p.getRelationRP() != null && 
				(p.getRelationRP().equalsIgnoreCase("Broer") || p.getRelationRP().equals("" + ConstRelations2.BROER) ||
						p.getRelationRP().equalsIgnoreCase("Stiefbroer") || p.getRelationRP().equals("" + ConstRelations2.STIEFBROER) ||
						p.getRelationRP().equalsIgnoreCase("Zuster") || p.getRelationRP().equals("" + ConstRelations2.ZUSTER) ||
						p.getRelationRP().equalsIgnoreCase("Sibling") || p.getRelationRP().equals("" + ConstRelations2.SIBLING) ||
						p.getRelationRP().equalsIgnoreCase("Stiefsibling") || p.getRelationRP().equals("" + ConstRelations2.STIEFSIBLING) ||
						p.getRelationRP().equalsIgnoreCase("Stiefzuster") || p.getRelationRP().equals("" + ConstRelations2.STIEFZUSTER))){
			p.setStartCode(2);  // preset
			group.add(p);
		}
	}
	
	setIDWithinGroup(group, false);
	
	Collections.sort(group, new Comparator<Person>() 
			{
		public int compare(Person p1, Person p2){
			
			if(p1.getIdWithinGroup() < p2.getIdWithinGroup())
				return -1;
			if(p1.getIdWithinGroup() > p2.getIdWithinGroup())
				return  1;
				
			return 0;
		}
			});
	
	
	int id_prev = -1;
	ArrayList<Person> group2 = new ArrayList<Person>();
	
	for(Person p: group){
		if(p.getIdWithinGroup() != id_prev){
			if(group2.size() > 0){
				setStartCode(group2); //find preferred source
				for(Person p2: group2){
					String n = (100 + p2.getIdWithinGroup()) + "";
					String idnr6 = String.format("%06d", p2.getIdnr());
					p2.setId_I_new("1" + idnr6 + n);
				}

				group2.clear();
			}
			id_prev = p.getIdWithinGroup();  	
			
		}
		group2.add(p);
		
	}
	if(group2.size() > 0){
		setStartCode(group2); //find preferred source
		for(Person p2: group2){					
			//p2.setId_I_new("1" + idnrSixCharacters(p2.getIdnr()) + "10" + p2.getIdWithinGroup());
			String n = (100 + p2.getIdWithinGroup()) + "";
			String idnr6 = String.format("%06d", p2.getIdnr());
			p2.setId_I_new("1" + idnr6 + n);

		}

	}
	
	family.removeAll(group);


	}
/**
 * Routine to handle parents in law
 * 
 * @param family
 * @return
 */
private static void handleParentsInLaw(ArrayList<Person> family){
	
	int id = 201;
	ArrayList<Person> group = new ArrayList<Person>();
	for(Person p: family){
		if(p.getRelationRP() != null && 
				(p.getRelationRP().equalsIgnoreCase("Schoonvader") || p.getRelationRP().equals("" + ConstRelations2.SCHOONVADER) ||
						p.getRelationRP().equalsIgnoreCase("Schoonmoeder") || p.getRelationRP().equals("" + ConstRelations2.SCHOONMOEDER))){
			group.add(p);
			p.setStartCode(2);  // preset
		}
	}
	
	setIDWithinGroup(group, false);
	
	Collections.sort(group, new Comparator<Person>() 
			{
		public int compare(Person p1, Person p2){
			
			if(p1.getIdWithinGroup() < p2.getIdWithinGroup())
				return -1;
			if(p1.getIdWithinGroup() > p2.getIdWithinGroup())
				return  1;
				
			return 0;
		}
			});
	
	
	int id_prev = -1;
	ArrayList<Person> group2 = new ArrayList<Person>();
	
	for(Person p: group){
		if(p.getIdWithinGroup() != id_prev){
			if(group2.size() > 0){
				setStartCode(group2); //find preferred source
				for(Person p2: group2){					
					//p2.setId_I_new("1" + idnrSixCharacters(p2.getIdnr()) + "20" + p2.getIdWithinGroup());
					String n = (200 + p2.getIdWithinGroup()) + "";
					String idnr6 = String.format("%06d", p2.getIdnr());
					p2.setId_I_new("1" + idnr6 + n);

				}

				group2.clear();
			}
			id_prev = p.getIdWithinGroup();  	
			
		}
		group2.add(p);
		
	}
	if(group2.size() > 0){
		setStartCode(group2); //find preferred source
		for(Person p2: group2){					
			//p2.setId_I_new("1" + idnrSixCharacters(p2.getIdnr()) + "20" + p2.getIdWithinGroup());
			String n = (200 + p2.getIdWithinGroup()) + "";
			String idnr6 = String.format("%06d", p2.getIdnr());
			p2.setId_I_new("1" + idnr6 + n);

		}

	}
	
	family.removeAll(group);

}

/**
 * Routine to handle everybody else
 * 
 * @param family
 * @return
 */
private static void handleOthers(ArrayList<Person> family){
	
	ArrayList<Person> group = new ArrayList<Person>();
	for(Person p: family){
		if(p.getStartCode() == 0){
			if(p.getRelationRP() == null)
				p.setRelationRP("Onbekend");
			if((p.getSource().substring(0, 6).equals("HSN BC") && (p.getId_I() == 5 || p.getId_I() == 6)) ||
			   (p.getSource().substring(0, 6).equals("HSN MC") && (p.getId_I() % 100 == 31 || p.getId_I() % 100 == 32 || p.getId_I() % 100 == 33 || p.getId_I() % 100 == 34 )))
				p.setFunction("Witness");
					
			if((p.getSource().substring(0, 6).equals("HSN BC") && (p.getId_I() == 4)) ||
			   (p.getSource().substring(0, 6).equals("HSN DC") && (p.getId_I() == 61 || p.getId_I()  == 62)))					
				p.setFunction("Informer");
					
			p.setStartCode(2);  // preset
			group.add(p);
		}
	}
	
	setIDWithinGroup(group, false);
	
	Collections.sort(group, new Comparator<Person>() 
			{
		public int compare(Person p1, Person p2){
			
			if(p1.getIdWithinGroup() < p2.getIdWithinGroup())
				return -1;
			if(p1.getIdWithinGroup() > p2.getIdWithinGroup())
				return  1;
				
			return 0;
		}
			});
	
	
	int id_prev = -1;
	ArrayList<Person> group2 = new ArrayList<Person>();
	
	for(Person p: group){
		if(p.getIdWithinGroup() != id_prev){
			if(group2.size() > 0){
				setStartCode(group2); //find preferred source
				for(Person p2: group2){					
					//p2.setId_I_new("1" + idnrSixCharacters(p2.getIdnr()) + "30" + p2.getIdWithinGroup());
					String n = (300 + p2.getIdWithinGroup()) + "";
					String idnr6 = String.format("%06d", p2.getIdnr());
					p2.setId_I_new("1" + idnr6 + n);

				}

				group2.clear();
			}
			id_prev = p.getIdWithinGroup();  	
			
		}
		group2.add(p);
		
	}
	if(group2.size() > 0){
		setStartCode(group2); //find preferred source
		for(Person p2: group2){					
			//p2.setId_I_new("1" + idnrSixCharacters(p2.getIdnr()) + "30" + p2.getIdWithinGroup());
			String n = (300 + p2.getIdWithinGroup()) + "";
			String idnr6 = String.format("%06d", p2.getIdnr());
			p2.setId_I_new("1" + idnr6 + n);

		}

	}
	
	family.removeAll(group);

	
}

/**
 * This routine sets startcode = 1 for a person in the group
 * It searches for sources in the order:
 * 
 * 	Birth Certificate
 *  Marriage Certificate
 *  PersonalCard
 *  Death Certificate
 *  Population Register
 * 
 * 
 * @param group
 */

private static Person setStartCode(ArrayList<Person> group){
	
	//System.out.println("In setStartCode");
	
 x:	for(String s : sources){
		
		for(Person p: group){
			
			//System.out.println("   In setStartCode A");

			
			if(p.getSource() != null && p.getSource().substring(0,s.length()).equals(s)){
				
				p.setStartCode(1);
				return p;
				
			}
			
		}
		
	}
	
	return null;
}


/**
 * 
 * This routine gives every person in a group a unique personID (starting from 1 up)
 * 
 * 
 * @param group
 */
private static void setIDWithinGroup(ArrayList<Person> group, boolean onlyNames ){

	//System.out.println(group);
	ArrayList<Person> uniquePersons = new ArrayList<Person>();
	int id = 1;  // personID


	for(Person p: group){
		boolean found = false;
		for(Person pu: uniquePersons){			
			if(comparePersons(p, pu, onlyNames) == 0){
				p.setIdWithinGroup(pu.getIdWithinGroup());
				found = true;
				break;
			}
		}


		if(found == false){
			p.setIdWithinGroup(id++);
			uniquePersons.add(p);
		}
	}

	//for(Person p: group)
		//System.out.println("Person " + p.getFamilyName() + "  " + p.getFirstName() + " groupid = " + p.getIdWithinGroup());
}



/**
 * 
 * Routine to make an IDNR exactly 6 characters by prepending one or more '0' characters
 * 
 * @param idnr
 * @return
 */
private static String idnrSixCharacters(String idnr){
	
	 // Make sure the IDNR is always exactly 6 characters
	 
	 if(idnr.length() < 6){
		 for(int j = idnr.length(); j < 6; j++){
			 idnr = "0" + idnr;
		 }
	 }

	 return idnr;
	
	
}
/**
 * 
 * Compares Persons to see if they are the same (= 0) or not
 * 
 * @param p1
 * @param p2
 * @return 0 Same
 *        -1 Different 
 */
private static int comparePersons(Person p1, Person p2, boolean onlyNames){
	
	
	boolean familyNameOK = checkFamilyName(p1.getFamilyName(), p2.getFamilyName());
	boolean firstNameOK = checkFirstName(p1.getFirstName(), p2.getFirstName()); 

	/*
	if(p1.getFamilyName().length() >= 3 && p1.getFamilyName().substring(0,3).equalsIgnoreCase("kru")){
	
		
	System.out.println("");	
	System.out.println(p1.getFamilyName());
	System.out.println(p2.getFamilyName());
	System.out.println(familyNameOK);
	System.out.println(p1.getFirstName());
	System.out.println(p2.getFirstName());

	System.out.println(firstNameOK);
	}
	*/
	
	if(familyNameOK && firstNameOK && onlyNames)
		return 0;
	
	boolean birthDateOK = checkBirthDate(p1, p2); 

	/*
	if(p1.getFamilyName().length() >= 3 && p1.getFamilyName().substring(0,3).equalsIgnoreCase("kru")){
	
		
		if(familyNameOK && firstNameOK && birthDateOK){
			System.out.println("Same!");
		}
		else{
			System.out.println("Different!");
		}
	}
	*/
		
	
	if(familyNameOK && firstNameOK && birthDateOK){
		//System.out.println("Same!");
		return 0;
	}
	else{
		//System.out.println("Different!");
		return -1;
	}
}
/**
 * 
 * Check Family Name as with Standardize Population Register
 * 
 * @param s1
 * @param s2
 * @return
 */
private static boolean checkFamilyName(String s1, String s2){
	
	if(s1 == null || s2 == null) return false;
	if(s1.length() == 0 || s2.length() == 0) return false;
	
	s1 = s1.toLowerCase().trim();
	s2 = s2.toLowerCase().trim();
	
	if(s1.charAt(0) != s2.charAt(0)) return false;
	
	//s1 = s1.replaceAll("y", "ij");
	//s2 = s2.replaceAll("ie", "ij");
	//s1 = s1.replaceAll("y", "ij");
	//s2 = s2.replaceAll("ie", "ij");

	//s1 = s1.replaceAll("egt", "echt");
	//s2 = s2.replaceAll("egt", "echt");

	//s1 = s1.replaceAll("uys", "ist");
	//s2 = s2.replaceAll("uys", "ist");

	int distance = Common1.LevenshteinDistance(s1, s2);
	
	if(distance > 2)  // greater than 2 not allowed
		return false;

	if(distance == 2 && s1.length() <= 5 && s2.length() <= 5)  // distance = 2 is allowed, but not for small strings
		return false;

	
	return true;
	
}

/**
 * 
 * CheckFirstName same as for population register
 * 
 * @param s1
 * @param s2
 * @return
 */
private static boolean checkFirstName(String s1, String s2){
	
	if(s1 == null || s2 == null) return false;
	if(s1.length() == 0 || s2.length() == 0) return false;

	String name1 = s1.split(" ")[0].toLowerCase().trim();
	String name2 = s2.split(" ")[0].toLowerCase().trim();

	String [] suffixes = {"nus", "nis", "nes", "la", "is", "es", "us", "er", "nie", "nij"}; 

	if(name1.length() > 5)
		removeSuffixes(name1, suffixes);	
	
	if(name2.length() > 5)
		removeSuffixes(name2, suffixes);	

	int distance = Common1.LevenshteinDistance(name1, name2);	

	if(distance > 2)  // greater than 2 not allowed
		return false;

	if(distance == 2 && name1.length() <= 5 && name2.length() <= 5)  // distance = 2 is allowed, but not for small strings
		return false;

	
	return true;
	
}

/**
 * 
 * Check birthdate same as for population register
 * 
 * 
 * 
 * @param p1
 * @param p2
 * @return
 */
private static boolean checkBirthDate(Person p1, Person p2){
	
	int day1   = p1.getBirthDay();
	int month1 = p1.getBirthMonth();
	int year1  = p1.getBirthYear();

	int startday1   = p1.getBirthStartDay();
	int startmonth1 = p1.getBirthStartMonth();
	int startyear1  = p1.getBirthStartYear();
	
	int endday1   = p1.getBirthEndDay();
	int endmonth1 = p1.getBirthEndMonth();
	int endyear1  = p1.getBirthEndYear();

	int day2   = p2.getBirthDay();
	int month2 = p2.getBirthMonth();
	int year2  = p2.getBirthYear();

	int startday2  = p2.getBirthStartDay();
	int startmonth2 = p2.getBirthStartMonth();
	int startyear2  = p2.getBirthStartYear();
	
	int endday2   = p2.getBirthEndDay();
	int endmonth2 = p2.getBirthEndMonth();
	int endyear2  = p2.getBirthEndYear();
	
	
	
	
	
	
	//System.out.println("" + day1 + "-" + month1 + "-" + year1);
	//System.out.println("" + startday1 + "-" + startmonth1 + "-" + startyear1);
	//System.out.println("" + endday1 + "-" + endmonth1 + "-" + endyear1);

	//System.out.println();
	
	//System.out.println("" + day2 + "-" + month2 + "-" + year2);
	//System.out.println("" + startday2 + "-" + startmonth2 + "-" + startyear2);
	//System.out.println("" + endday2 + "-" + endmonth2 + "-" + endyear2);
	
	if(year1 != 0 && year2 != 0){  // compare two dates

		if(Math.abs(day1 - day2) > 1) // days differ significantly
			if(Math.abs(month1 - month2) != 0 || Math.abs(year1 - year2) != 0){
				//System.out.println("False 2");				
				return false;	
			}

		if(Math.abs(month1 - month2) != 0) // months differ 
			if(Math.abs(day1 - day2) > 1 || Math.abs(year1 - year2) != 0){
				//System.out.println("False 3");
				return false;
			}

		if(Math.abs(year1 - year2) != 0){ // years differ

			if(Math.abs(day1 - day2) > 1 || Math.abs(month1 - month2) != 0){
				//System.out.println("False 4");
				return false;
			}
			else{
				
			    String date1 = String.format("%02d-%02d-%04d", day1, month1, year1);
			    String date2 = String.format("%02d-%02d-%04d", day2, month2, year2);
				if(date1.substring(6,8).equals(date2.substring(6,8))){ // same century

					if(Math.abs(year1 - year2) <= 2 || 
							(date1.substring(6,7).equals(date2.substring(6,7)) &&
									date1.substring(8,9).equals(date2.substring(8,9)) &&
									date1.substring(9,10).equals(date2.substring(9,10))))
						; // ok
					else{
						//System.out.println("False 5");
						return false;
					}
				}
				else{
					//System.out.println("False 6");
					return false;
				}
			}
		}	
		
		return true;
	}
	else{		
		if(year1 != 0 && year2 == 0 && startyear2 != 0){ // compare date and interval
			
			int dayCount1      = Common1.dayCount(day1, month1, year1);
			int dayCount2Start = Common1.dayCount(startday2, startmonth2, startyear2);
			int dayCount2End   = Common1.dayCount(endday2, endmonth2, endyear2);
			
			if(dayCount2Start <= dayCount1 && dayCount1 <= dayCount2End)
				return true;
			else
				return false;
		}
		else
			if(year1 == 0 && year2 != 0 && startyear1 != 0){ // compare date and interval
				
				int dayCount2      = Common1.dayCount(day2, month2, year2);
				int dayCount1Start = Common1.dayCount(startday1, startmonth1, startyear1);
				int dayCount1End   = Common1.dayCount(endday1, endmonth1, endyear1);
				
				if(dayCount1Start <= dayCount2 && dayCount2 <= dayCount1End)
					return true;
				else
					return false;
			}
			else
				if(year1 == 0 && year2 == 0 && startyear1 != 0 && startyear2 != 0){ // compare date and interval
					
					int dayCount1Start = Common1.dayCount(startday1, startmonth1, startyear1);
					int dayCount1End   = Common1.dayCount(endday1, endmonth1, endyear1);
					int dayCount2Start = Common1.dayCount(startday2, startmonth2, startyear2);
					int dayCount2End   = Common1.dayCount(endday2, endmonth2, endyear2);

					
					if((dayCount1Start <= dayCount2Start && dayCount1End >= dayCount2Start) ||(dayCount1Start >= dayCount2Start && dayCount2End >= dayCount1Start))
						return true;
					else
						return false;
				}
		
		
	}


	return false;
}

public static String removeSuffixes(String t, String[] suffixes){
	
	String s = t;
	
	for(String s0: suffixes){
		
		if(s.length() > s0.length()){
			
			if(s.substring(s.length() - s0.length()).equalsIgnoreCase(s0))
			    s = s.substring(0, s.length() - s0.length());

		}
	}
         	
	return s;
}


public static List<INDIVIDUAL> getIndividualL() {
	return individualL;
}


public static void setIndividualL(List<INDIVIDUAL> individualL) {
	IDS.individualL = individualL;
}


public static List<Person> getPersonL() {
	return personL;
}


public static void setPersonL(List<Person> personL) {
	IDS.personL = personL;
}


public static List<INDIV_INDIV> getIndiv_indivL() {
	return indiv_indivL;
}


public static void setIndiv_indivL(List<INDIV_INDIV> indiv_indivL) {
	IDS.indiv_indivL = indiv_indivL;
}


public static List<INDIV_CONTEXT> getIndiv_contextL() {
	return indiv_contextL;
}


public static void setIndiv_contextL(List<INDIV_CONTEXT> indiv_contextL) {
	IDS.indiv_contextL = indiv_contextL;
}


public static int getIndexPerson() {
	return indexPerson;
}


public static void setIndexPerson(int indexPerson) {
	IDS.indexPerson = indexPerson;
}




public static JTextArea getTextArea() {
	return textArea;
}




public static void setTextArea(JTextArea textArea) {
	IDS.textArea = textArea;
}




public static DataOutputStream getOut() {
	return out;
}




public static void setOut(DataOutputStream out) {
	IDS.out = out;
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



public static String getVersion() {
	return "HSN" + version;
}


public static void setVersion(String version) {
	IDS.version = version;
}


private static void message(int idnr, String number, String... fills) {

    //print("Messagenr: " + number);

    Message m = new Message(number);

    m.setIDNR(idnr);
    m.save(fills);
}

}
