package nl.iisg.convertPK;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.print.attribute.standard.MediaSize.Other;
import javax.swing.*;

import nl.iisg.hsncommon.Common1;
import nl.iisg.hsncommon.ConstRelations;
import nl.iisg.hsncommon.ConstRelations2;
import nl.iisg.hsncommon.DBFHandler;
import nl.iisg.ref.Ref;


public class StandardizePersonalCards implements Runnable {

    static int personID = 1;
    String inputDirectory;
    static DataOutputStream out = null;

    public StandardizePersonalCards(String inputDirectory) {

        setInputDirectory(inputDirectory);

    }

    public StandardizePersonalCards(DataOutputStream out, String inputDirectory) {

        setInputDirectory(inputDirectory);
        setOut(out);

    }

    public void run() {

        String[] parms = {getInputDirectory()};

        main(parms);
    }


    public static void main(String args[]) {

        if (false) {
            File file = new File("h:/sysout.log");
            try {
                PrintStream printStream = new PrintStream(new FileOutputStream(file));
                print("Output in h:/sysout.log");
                System.setOut(printStream);

            } catch (FileNotFoundException e) {
            }
        }


        Date startTime = new Date();
        print("\nPersonal Cards - Standardize      started\n");

        String inputDirectory = null;

        if (args.length > 0)
            inputDirectory = args[0];
        else {
            print("No input directory specified");
            System.exit(0);
        }

        String [] requiredFiles = {"PKKND.DBF", "PKHUW.DBF", "PKEIGKND.DBF", "PKBRP.DBF", "PKBYZ.DBF", "PKADRES.DBF", "P7.DBF", "P8.DBF"};

        String missingFile = Common1.nonExisitingFile(inputDirectory, requiredFiles);
        if(missingFile != null){
        	print("Required file " + missingFile + " missing\n");
        	return;
        }
        
        print("Creating databases....");
        
        // Create or reset the PK*-tables

        TableDefinitions.createPkTables();

        // Create or reset the B*-tables

        TableDefinitions.createBTables();

        // Create or reset the Messages table

        TableDefinitions.createMTable();

        // Create the reference tables
        
        print("Create reference tables...");
        
        Ref.createRefTables();

        // Create Object Lists from PK*.dbf files
        
        print("Reading input files...");
        
        List<PkKnd> pkkndL = Utils.createObjects("nl.iisg.convertPK.PkKnd", inputDirectory);
        print("Read PKKND.DBF, " + pkkndL.size() + " rows");
        List<PkBrp> pkbrpL = Utils.createObjects("nl.iisg.convertPK.PkBrp", inputDirectory);
        print("Read PKBRP.DBF, " + pkbrpL.size() + " rows");
        List<PkHuw> pkhuwL = Utils.createObjects("nl.iisg.convertPK.PkHuw", inputDirectory);
        print("Read PKHUW.DBF, " + pkhuwL.size() + " rows");
        List<PkEigknd> pkeigkndL = Utils.createObjects("nl.iisg.convertPK.PkEigknd", inputDirectory);
        print("Read PKEIGKND.DBF, " + pkeigkndL.size() + " rows");
        List<PkAdres> pkadresL = Utils.createObjects("nl.iisg.convertPK.PkAdres", inputDirectory);
        print("Read PKADRES.DBF, " + pkadresL.size() + " rows");
        List<PkByz> pkbyzL = Utils.createObjects("nl.iisg.convertPK.PkByz", inputDirectory);
        print("Read PKBYZ.DBF, " + pkbyzL.size() + " rows");
        List<P7> p7L = Utils.createObjects("nl.iisg.convertPK.P7", inputDirectory);
        print("Read P7.DBF, " + p7L.size() + " rows");
        List<P8> p8L = Utils.createObjects("nl.iisg.convertPK.P8", inputDirectory);
        print("Read P8.DBF, " + p8L.size() + " rows");

        // Save the Object Lists

        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("pkimport");
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();

            print("Saving input files...");

            for (PkKnd pkknd1 : pkkndL) {
                em.persist(pkknd1);
            }

            for (PkBrp pkbrp : pkbrpL)
                em.persist(pkbrp);

            for (PkEigknd pkeigknd : pkeigkndL)
                em.persist(pkeigknd);

            for (PkHuw pkhuw : pkhuwL)
                em.persist(pkhuw);

            for (PkAdres pkadres : pkadresL)
                em.persist(pkadres);

            for (PkByz pkbyz : pkbyzL)
                em.persist(pkbyz);

            for (P7 p7 : p7L)
                em.persist(p7);

            for (P8 p8 : p8L)
                em.persist(p8);


            em.getTransaction().commit();
            em.clear();

        } catch (Exception e) {
            if(e.getMessage() != null)
            	print(e.getMessage());
        }


        // Sort the Object Lists

        Collections.sort(pkkndL, new Comparator<PkKnd>() {
            public int compare(PkKnd pkknd1, PkKnd pkknd2) {
                if (pkknd1.getIdnr() > pkknd2.getIdnr()) return 1;
                else if (pkknd1.getIdnr() < pkknd2.getIdnr()) return -1;
                return 0;
            }
        });

        Collections.sort(pkbrpL, new Comparator<PkBrp>() {
            public int compare(PkBrp pkbrp1, PkBrp pkbrp2) {
                if (pkbrp1.getIdnr() > pkbrp2.getIdnr()) return 1;
                else if (pkbrp1.getIdnr() < pkbrp2.getIdnr()) return -1;
                if (pkbrp1.getVgnrbrp() > pkbrp2.getVgnrbrp()) return 1;
                else if (pkbrp1.getVgnrbrp() < pkbrp2.getVgnrbrp()) return -1;
                return 0;
            }
        });

        Collections.sort(pkhuwL, new Comparator<PkHuw>() {
            public int compare(PkHuw pkhuw1, PkHuw pkhuw2) {
                if (pkhuw1.getIdnr() > pkhuw2.getIdnr()) return 1;
                else if (pkhuw1.getIdnr() < pkhuw2.getIdnr()) return -1;
                if (pkhuw1.getVnrhuwp() > pkhuw2.getVnrhuwp()) return 1;
                else if (pkhuw1.getVnrhuwp() < pkhuw2.getVnrhuwp()) return -1;
                return 0;
            }
        });

        Collections.sort(pkeigkndL, new Comparator<PkEigknd>() {
            public int compare(PkEigknd pkeigknd1, PkEigknd pkeigknd2) {
                if (pkeigknd1.getIdnr() > pkeigknd2.getIdnr()) return 1;
                else if (pkeigknd1.getIdnr() < pkeigknd2.getIdnr()) return -1;
                if (pkeigknd1.getVgnrkdp() > pkeigknd2.getVgnrkdp()) return 1;
                else if (pkeigknd1.getVgnrkdp() < pkeigknd2.getVgnrkdp()) return -1;
                return 0;
            }
        });

        Collections.sort(pkbyzL, new Comparator<PkByz>() {
            public int compare(PkByz pkbyz1, PkByz pkbyz2) {
                if (pkbyz1.getIdnr() > pkbyz2.getIdnr()) return 1;
                else if (pkbyz1.getIdnr() < pkbyz2.getIdnr()) return -1;
                if (pkbyz1.getByznr() > pkbyz2.getByznr()) return 1;
                else if (pkbyz1.getByznr() < pkbyz2.getByznr()) return -1;
                return 0;
            }
        });

        Collections.sort(pkadresL, new Comparator<PkAdres>() {
            public int compare(PkAdres pkadres1, PkAdres pkadres2) {
                if (pkadres1.getIdnr() > pkadres2.getIdnr()) return 1;
                else if (pkadres1.getIdnr() < pkadres2.getIdnr()) return -1;
                if (pkadres1.getVgnradp() > pkadres2.getVgnradp()) return 1;
                else if (pkadres1.getVgnradp() < pkadres2.getVgnradp()) return -1;
                return 0;
            }
        });


        Collections.sort(p7L, new Comparator<P7>() {
            public int compare(P7 p71, P7 p72) {
                if (p71.getIdnr() > p72.getIdnr()) return 1;
                else if (p71.getIdnr() < p72.getIdnr()) return -1;
                return 0;
            }
        });

        Collections.sort(p8L, new Comparator<P8>() {
            public int compare(P8 p81, P8 p82) {
                if (p81.getIdnr() > p82.getIdnr()) return 1;
                else if (p81.getIdnr() < p82.getIdnr()) return -1;
                if (p81.getP8tpnr() > p82.getP8tpnr()) return 1;
                else if (p81.getP8tpnr() < p82.getP8tpnr()) return -1;
                return 0;
            }
        });

        
        
        
        //if(1==1) System.exit(0);;
        
        if(!keyCheck(pkkndL, pkbrpL, pkhuwL, pkeigkndL, pkadresL, pkbyzL, p7L, p8L)){
        	
        	print("Saving Message Table...");
            Message.finalise(); // write messages to disk

        	print("Fatal error has occurred. Check bfout7t and repair");
        	print("\nPersonal Cards - Standardize      finished\n");
        	//System.out.println("\nPopulation Register - Standardize      finished\n");
        	return;
        }
        // Link the PK* objects

        int i_br = 0;
        int i_ad = 0;
        int i_ek = 0;
        int i_hw = 0;
        int i_bz = 0;
        int i_p7 = 0;
        int i_p8 = 0;

        for (PkKnd pkknd : pkkndL) {
        	
            while (i_br < pkbrpL.size() && pkbrpL.get(i_br).getIdnr() == pkknd.getIdnr()) {

            	//System.out.println("    i_br =  " + i_br + " idnr = " + pkbrpL.get(i_br).getIdnr());

            	
                pkknd.getProfessions().add(pkbrpL.get(i_br));  // add profession to ArrayList professions in PkKnd
                pkbrpL.get(i_br).setPkHolder(pkknd);           // set PK-Holder object in Profession Object 
                i_br++;
            }

            while (i_ad < pkadresL.size() && pkadresL.get(i_ad).getIdnr() == pkknd.getIdnr()) {

                pkknd.getAddresses().add(pkadresL.get(i_ad));  // add address to ArrayList addresses in PkKnd
                pkadresL.get(i_ad).setPkHolder(pkknd);         // set PK-Holder object in Address Object 
                i_ad++;
            }

            while (i_ek < pkeigkndL.size() && pkeigkndL.get(i_ek).getIdnr() == pkknd.getIdnr()) {

            	//System.out.println("    i_ek =  " + i_ek + " idnr = " + pkeigkndL.get(i_ek).getIdnr());
            	
                pkknd.getChildren().add(pkeigkndL.get(i_ek));  // add child to ArrayList children in PkKnd
                pkeigkndL.get(i_ek).setPkHolder(pkknd);        // set PK-Holder object in Child Object 
                i_ek++;
            }

            while (i_hw < pkhuwL.size() && pkhuwL.get(i_hw).getIdnr() == pkknd.getIdnr()) {
            	
            	//System.out.println("    i_hw =  " + i_hw + " idnr = " + pkhuwL.get(i_hw).getIdnr());

                pkknd.getMarriages().add(pkhuwL.get(i_hw));   // add marriage to ArrayList marriages in PkKnd
                pkhuwL.get(i_hw).setPkHolder(pkknd);          // set PK-Holder object in Marriage Object 
                i_hw++;
            }

            while (i_bz < pkbyzL.size() && pkbyzL.get(i_bz).getIdnr() == pkknd.getIdnr()) {

                pkknd.getRemarks().add(pkbyzL.get(i_bz));     // add remark to ArrayList remarks in PkKnd
                pkbyzL.get(i_bz).setPkHolder(pkknd);          // set PK-Holder object in Remark Object 
                i_bz++;
            }

            while (i_p7 < p7L.size() && p7L.get(i_p7).getIdnr() == pkknd.getIdnr()) {

                pkknd.setP7(p7L.get(i_p7));                   // P7 to P7 in PkKnd
                p7L.get(i_p7).setPkHolder(pkknd);             // set PK-Holder object in P7 Object 
                i_p7++;
            }

            while (i_p8 < p8L.size() && p8L.get(i_p8).getIdnr() == pkknd.getIdnr()) {

                pkknd.getP8().add(p8L.get(i_p8));             // add P8 to ArrayList remarks in PkKnd
                p8L.get(i_p8).setPkHolder(pkknd);             // set PK-Holder object in P8 Object 
                i_p8++;
            }
            
            //System.out.println(pkknd.getIdnr());
        }

        //if(1==1) System.exit(0);
        
        //for (PkKnd pkknd1 : pkkndL) {
        	
        //	pkknd1.print();
        	
       // }
        // Load Reference Tables
        
        print("Reading reference tables... ");
        
        Ref.loadKG();
        Ref.loadFamName();
        Ref.loadFirstName();
        Ref.loadPrefix();
        Ref.loadLocation();
        Ref.loadProfession();
        Ref.loadAddress();
        Ref.loadHousenumber();
        Ref.loadHousenumberaddition();
        Ref.loadRelation_C();

        //new Message("1111").save(); // just an example

        // Convert PK*-Objects to B*-Objects

        print("Processing input files...");


        int idnr = -1;
        String B2dibg = null;  // this is secondary key for to distinguish PkKnd rows with equal IDNR
        for (PkKnd pkknd1 : pkkndL) {
        	if (pkknd1.getIdnr() == idnr) {
        		B2dibg = Common1.dateFromDayCount(Common1.dayCount(B2dibg) + 1);
        	} else
        		B2dibg = "01-01-1940";
        	idnr = pkknd1.getIdnr();
        	pkknd1.convert(B2dibg);
        }

        // Give persons unique person number
        // But compare only within idnr 
        
        // We create a second list of Pkknd objects, sorted on idnrP 
        
        List<PkKnd> pkkndL2 = new ArrayList(); 
        
        pkkndL2.addAll(pkkndL);        
        
        Collections.sort(pkkndL2, new Comparator<PkKnd>() {
            public int compare(PkKnd pkknd1, PkKnd pkknd2) {
                if (pkknd1.getIdnrp() > pkknd2.getIdnrp()) return 1;
                else if (pkknd1.getIdnrp() < pkknd2.getIdnrp()) return -1;
                return 0;   
            }
        });
        
        ArrayList<B2_ST> uniquePersons = new ArrayList<B2_ST>();
        
        int idnrPrev = 0;
        for (PkKnd pkknd1 : pkkndL2) {
        	if(pkknd1.getB4().getIdnrSpouse() != idnrPrev){
        		uniquePersons.clear(); // This assures we link in the context of an IDNR, not 'globally'
        		idnrPrev = pkknd1.getB4().getKeyToRP();
        	}
        	for (B2_ST b2 : pkknd1.getB4().getPersons()) {
        		boolean found = false;
        		for (B2_ST b2unique : uniquePersons) {

        			if (comparePersons(b2, b2unique) == 0) {
        				b2.setPersonID(b2unique.getPersonID());
        				found = true;
        				break;
        			}
        		}
        		if (found == false) {
        			b2.setPersonID(getPersonID());
        			uniquePersons.add(b2);
        		}
        	}

        }
        
               
        // Set PersonID_FA and PersonID_MO for children and OP
        
        for (PkKnd pkknd1 : pkkndL) {
        	for (B2_ST b2 : pkknd1.getB4().getPersons()) {
        		
        		if(b2.getRelationsToPKHolder().get(0).getContentOfDynamicData() == 3 || b2.getRelationsToPKHolder().get(0).getContentOfDynamicData() == 4 ||
       			   b2.getRelationsToPKHolder().get(0).getContentOfDynamicData() == 8 || b2.getRelationsToPKHolder().get(0).getContentOfDynamicData() == 9){
        			
        			
        			//System.out.println(b2.getFirstName() + "  " + b2.getFamilyName());
        			
        			// Set Parent 1 PersonID
        			
        			if(b2.getRegistration().getPersons().get(0).getSex().equalsIgnoreCase("M"))		
        				b2.setPersonID_FA(b2.getRegistration().getPersons().get(0).getPersonID());
        			else
        				b2.setPersonID_MO(b2.getRegistration().getPersons().get(0).getPersonID());
        			
        			//System.out.println(b2.getFirstName() + "  " + b2.getFamilyName());

        			
        			// Set parent 2 PersonID
        			
        			for(B2_ST b2_temp: b2.getRegistration().getPersons()){
        				
        				if(b2_temp.getRelationsToPKHolder().get(0).getContentOfDynamicData() == 2  || b2_temp.getRelationsToPKHolder().get(0).getContentOfDynamicData() == 145){  // Spouse


        					//System.out.println("...");
        					//System.out.println("..." + b2_temp.getFirstName() + "  " + b2_temp.getFamilyName());


        					int marriageStartDays = Common1.dayCount(b2_temp.getRelationsToPKHolder().get(0).getStartDate());
        					int marriageEndDays   = b2_temp.getRelationsToPKHolder().get(0).getEndDate() != null ?  
        							Common1.dayCount(b2_temp.getRelationsToPKHolder().get(0).getEndDate()) : 0;

        							if(marriageStartDays < Common1.dayCount(b2.getDateOfBirth()) &&	
        									(marriageEndDays == 0 || marriageEndDays > Common1.dayCount(b2.getDateOfBirth()))){

        						if(b2.getPersonID_FA() == 0)
        							b2.setPersonID_FA(b2_temp.getPersonID());
        						else
        							b2.setPersonID_MO(b2_temp.getPersonID());
        						
        						break;
        						
        	                }
        				}
        			}
        		}
        		else
        			if(b2.getRelationsToPKHolder().get(0).getContentOfDynamicData() == 1){ // OP
        				
        				b2.setPersonID_FA(pkknd1.getB4().getPersons().get(1).getPersonID()); // Father is 2nd
        				b2.setPersonID_MO(pkknd1.getB4().getPersons().get(2).getPersonID()); // Mother is 3rd
        				
        			}
        		
        	}
        
        }
        
        
        // Integrate cards
        
        for (PkKnd pkknd1 : pkkndL) { // the Wives are selected from this list

        	// locate partner registration

        	int i = 1;       // number of husbands
        	

        	for (PkKnd pkknd2 : pkkndL2) { // The husbands are selected from this list

        		if(pkknd1 != pkknd2 && pkknd2.getIdnrp() == pkknd1.getIdnr()){  // Wife-pkknd1 was married to husband-pkknd2

        			copyPartnerInfo(pkknd2.getB4(), pkknd1.getB4());
        			
        			for (B2_ST b2L : pkknd2.getB4().getPersons()) 
        				for(B2_ST b2R :  pkknd2.getB4().getPersons())
        					handleB34_new(b2L, b2R);
        			

        			// Now we must change the key of pkknd2.getB4():
        			// It must be registered under the IDNR of the wife
        			// Also for the B2 and B3 elements under it
        			// In order to get a unique key, we must use b2dibg also


        			// Determine b2dibg

        			String b2dibg = String.format("%02d-%02d-%04d", ++i, 1, 1940);
        			//System.out.println("i = " + i);

        			//System.out.println("b2dibg = " + b2dibg);

        			pkknd2.getB4().setKeyToRP(pkknd1.getIdnr());
        			pkknd2.getB4().setEntryDateHead(b2dibg);
        			pkknd2.getB4().setIdnrSpouse(pkknd2.getIdnr());

        			for(B2_ST p: pkknd2.getB4().getPersons()){
        				p.setKeyToRP(pkknd1.getIdnr());
        				p.setEntryDateHead(b2dibg);

        				for(B3_ST d: p.getRelationsToPKHolder()){
        					d.setKeyToRP(pkknd1.getIdnr());
        					d.setEntryDateHead(b2dibg);
        				}

        				for(B3_ST d: p.getCivilStatus()){
        					d.setKeyToRP(pkknd1.getIdnr());
        					d.setEntryDateHead(b2dibg);
        				}

        				for(B3_ST d: p.getReligions()){
        					d.setKeyToRP(pkknd1.getIdnr());
        					d.setEntryDateHead(b2dibg);
        				}

        				for(B3_ST d: p.getRelations()){
        					d.setKeyToRP(pkknd1.getIdnr());
        					d.setEntryDateHead(b2dibg);
        				}

        				for(B3_ST d: p.getProfessions()){
        					d.setKeyToRP(pkknd1.getIdnr());
        					d.setEntryDateHead(b2dibg);
        				}

        				for(B3_ST d: p.getOrigins()){
        					d.setKeyToRP(pkknd1.getIdnr());
        					d.setEntryDateHead(b2dibg);
        				}

        				for(B3_ST d: p.getDestinations()){
        					d.setKeyToRP(pkknd1.getIdnr());
        					d.setEntryDateHead(b2dibg);
        				}
        			}

        			for(B6_ST a: pkknd2.getB4().getAddresses()){
        				a.setKeyToRP(pkknd1.getIdnr());
        				a.setDateEntryHeadOfHousehold(b2dibg);

        			}

        			//System.out.println("3 pkknd1.getB4().getPersons.size() = " + pkknd1.getB4().getPersons().size());

        		}
        		
        		
        	}
        	
        	
        	if(pkknd1.getIdnr() > 500000) break; // because wives are below 500000
        	
			//   Set relations in Wife's family 
        	
			
			for (B2_ST b2L : pkknd1.getB4().getPersons()) 
				for(B2_ST b2R :  pkknd1.getB4().getPersons())
					handleB34(b2L, b2R);
					//System.out.println("XXXX " + b2L.getKeyToPersons() + " XXXX " + b2R.getKeyToPersons() );

        	
        	
        	
        }


        // Relate All to All

        //for (PkKnd pkknd1 : pkkndL) 
        	//	pkknd1.getB4().relateAllToAll(); 
        	
        


        for (PkKnd pkknd1 : pkkndL)
        		pkknd1.getB4().truncate();



        print("Processed " + pkkndL.size() + " IDNRs");
        print("Saving output data tables...");


        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("pkdata");
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();

            for (PkKnd pkknd1 : pkkndL)
            		pkknd1.getB4().save(em);


            em.getTransaction().commit();
            em.clear();
        } catch (Exception e) {
        	e.printStackTrace(System.out);
        }


		print("Saving Reference tables...");
        Ref.finalise();     // write reference files to disk
        
		print("Saving Message Table...");
        Message.finalise(); // write messages to disk

        print("\nPersonal Cards - Standardize      finished \n");

        
    }

    /**
     * 
     * This routine synchronizes the information between spouses
     * The Wife has been chosen as an RP. But her children are on the PK of the Husband
     * So we copy the Husband information (including his parents who become her parents-in-law)
     * and his children to the Registration of his wife. 
     * 
     * @param b4Husband
     * @param b4Wife
     * 
     */
    private static void copyPartnerInfo(B4_ST b4Husband, B4_ST b4Wife){
    	
    	
    	//System.out.println("Husband = " + b4Husband + "wife = "+ b4Wife);
    	
    	//System.out.println("Copy Partner Info"); 
    	
    	//if(1==1) return; 
    	
    	B2_ST b2Partner = null;
    	
    A:	for(B2_ST b2H: b4Husband.getPersons()){
    		
    		switch(b2H.getRelationsToPKHolder().get(0).getContentOfDynamicData()){
    		
    		case 1: // The husband. Find him as partner of his wife
    			
    			for(B2_ST b2W: b4Wife.getPersons()){
    				
    				if(b2H.getPersonID() == b2W.getPersonID()){
    					
    					b2Partner = b2W;
    					break;
    				}
    			}
    							
    			if(b2Partner != null){				

    				b2Partner.setDateOfDecease(b2H.getDateOfDecease());
    				b2Partner.setPlaceOfDeceaseStandardized(b2H.getPlaceOfDeceaseStandardized());
    				b2Partner.setNationality(b2H.getNationality());
    				
    				b2Partner.setPersonID_FA(b2H.getPersonID_FA());
    				b2Partner.setPersonID_FA_FG(b2H.getPersonID_FA_FG());
    				b2Partner.setPersonID_MO(b2H.getPersonID_MO());
    				b2Partner.setPersonID_MO_FG(b2H.getPersonID_MO_FG());

    				for(B33_ST b33H: b2H.getReligions()){

    					B33_ST b33W = allocateB33(b2Partner, b33H);
    					b33W.setDynamicDataSequenceNumber(b2Partner.getReligions().size() + 1);
    					b2Partner.getReligions().add(b33W);

    				}

    				for(B35_ST b35H: b2H.getProfessions()){
    					
    					B35_ST b35W = allocateB35(b2Partner, b35H);
    					b35W.setDynamicDataSequenceNumber(b2Partner.getProfessions().size() + 1);
    					b2Partner.getProfessions().add(b35W);

    				}
    			}
    			break;



    		
    		
    		case 2:  // One of the wives, maybe the RP No action, we know everything about her already from her own card
    			break;
    		
    		default:  // Husband's parents and children

    			
    			if(b2Partner != null){
        			
    				
    				B2_ST b2 = null;
    			
    				// See if PersonID already exists in Wife Registration
        			for(B2_ST b2W: b4Wife.getPersons()){
        				if(b2W.getPersonID() == b2H.getPersonID()){
        					b2 = b2W;
        					break;
        				}
        			}
        			
    				
        			
        			if(b2 == null){
        				
        				//System.out.println("allocating person: b4Wife = " + b4Wife + "b4H = " + b2H);

        				b2 = allocateB2(b4Wife, b2H);  // Allocate new person
        				b2.setKeyToPersons(b4Wife.getPersons().size() + 1);  // keep together    				
        				b4Wife.getPersons().add(b2);                         // keep together

        			}
    				
    				B313_ST b313 = allocateB313(b4Wife, b2Partner); // relation new person to PK-Holder Wife
    				b313.setPerson(b2);
    				b313.setKeyToRegistrationPersons(b2.getKeyToPersons());
    				b313.setDynamicDataSequenceNumber(1);
    				
    				b2.getRelationsToPKHolder().add(b313); 
    				
    				switch(b2H.getRelationsToPKHolder().get(0).getContentOfDynamicData()){ // relation to PK-Holder husband
    				
    				case 11:
    					
    					b313.setContentOfDynamicData(61); // Father (11) -> Father in Law (61)
    					//setRelations(b2);
    					break;
    					
    				case 21:
    					b313.setContentOfDynamicData(71); // Mother (21) -> Mother in Law (71)
    					//setRelations(b2);
    					break; 
    					
    				case 3: // Son of the husband
    				case 4: // Daughter of the husband    					
    					
    					if(b2H.getPersonID_MO() == b4Wife.getPersons().get(0).getPersonID())
    						b313.setContentOfDynamicData(b2H.getRelationsToPKHolder().get(0).getContentOfDynamicData()); // also wife's child
    					else{
    						b313.setContentOfDynamicData(b2H.getRelationsToPKHolder().get(0).getContentOfDynamicData() + 5); // make them wife's stepchild
    						b313.setStartDate(b2H.getStartDate());
    						b313.setStartFlag(b2H.getStartFlag());
    						b313.setEndDate(b4Wife.getPersons().get(0).getEndDate());  // For step-children, we assume the relation lasts until endPK of Wife 
    						b313.setEndFlag(b4Wife.getPersons().get(0).getEndFlag());
    					}
    					
    					 //setRelations(b2);
    					
    					break;
    					
    				case 8: // Stepson of the husband
    				case 9: // Stepdaughter of the husband
	
    					if(b2H.getPersonID_MO() == b4Wife.getPersons().get(0).getPersonID())
    						b313.setContentOfDynamicData(b2H.getRelationsToPKHolder().get(0).getContentOfDynamicData() - 5); // wife's child
    					else{
    						b313.setContentOfDynamicData(b2H.getRelationsToPKHolder().get(0).getContentOfDynamicData()); // wife's stepchild
    						b313.setStartDate(b2H.getStartDate());
    						b313.setStartFlag(b2H.getStartFlag());
    						b313.setEndDate(b4Wife.getPersons().get(0).getEndDate());  // For step-children, we assume the relation lasts until endPK of Wife 
    						b313.setEndFlag(b4Wife.getPersons().get(0).getEndFlag());

    					}
    					
    					//setRelations(b2);
    					

    				default:
    				
    				}
    			}
    			break;
    		}
    	}
    }

    /**
     * 
     * Allocate an B2_ST with keys copied from b4 and the rest copied from b2
     * 
     * @param b2
     * @param b33
     * @return
     */
    
    private static B2_ST allocateB2(B4_ST b4, B2_ST b2I){
    	
    	
    	B2_ST b2 = new B2_ST();

    	// Keys from b4
    	
    	b2.setKeyToSourceRegister(b4.getKeyToSourceRegister());
        b2.setEntryDateHead(b4.getEntryDateHead());
        b2.setKeyToRP(b4.getKeyToRP());
        
        // Rest from b2I

        //b2.setDateOfRegistration(b2I.getDateOfRegistration());
        //b2.setDateOfRegistrationFlag(b2I.getDateOfRegistrationFlag());

        b2.setPersonID(b2I.getPersonID());
        b2.setPersonIDFlag(b2I.getPersonIDFlag());
        b2.setPersonID_FA(b2I.getPersonID_FA());
        b2.setPersonID_FA_FG(b2I.getPersonID_FA_FG());
        b2.setPersonID_MO(b2I.getPersonID_MO());
        b2.setPersonID_MO_FG(b2I.getPersonID_MO_FG());

        
        b2.setStartDate(b2I.getStartDate());
        b2.setStartFlag(b2I.getStartFlag());
        b2.setStartEst(b2I.getStartEst());
        b2.setEndDate(b2I.getEndDate());
        b2.setEndFlag(b2I.getEndFlag());
        b2.setEndEst(b2I.getEndEst());

    	b2.setFamilyName(b2I.getFamilyName());
    	b2.setPrefixLastName((b2I.getPrefixLastName()));
    	b2.setTitleNoble(b2I.getTitleNoble());
    	b2.setTitleElse(b2I.getTitleElse());
    	b2.setFamilyNameInterpreted(b2I.getFamilyNameInterpreted());

    	
    	b2.setFirstName(b2I.getFirstName());
    	b2.setFirstNameFlag(b2I.getFirstNameFlag());
    	b2.setSex(b2I.getSex());
    	
    	b2.setDateOfBirth(b2I.getDateOfBirth());
    	b2.setDateOfBirthFlag(b2I.getDateOfBirthFlag());
    	b2.setPlaceOfBirthID(b2I.getPlaceOfBirthID());
    	b2.setPlaceOfBirthStandardized(b2I.getPlaceOfBirthStandardized());
    	b2.setPlaceOfBirthFlag(b2I.getPlaceOfBirthFlag());
    	
    	b2.setDateOfDecease(b2I.getDateOfDecease());
    	b2.setDateOfDeceaseFlag(b2I.getDateOfDeceaseFlag());
    	b2.setPlaceOfDeceaseID(b2I.getPlaceOfDeceaseID());
    	b2.setPlaceOfDeceaseStandardized(b2I.getPlaceOfDeceaseStandardized());
    	b2.setPlaceOfDeceaseFlag(b2I.getPlaceOfDeceaseFlag());

    	b2.setNationality(b2I.getNationality());
    	
    	b2.setLegalPlaceOfLivingCode(b2.getLegalPlaceOfLivingCode());
    	b2.setLegalPlaceOfLivingStandardized(b2.getLegalPlaceOfLivingStandardized());
    	b2.setLegalPlaceOfLivingFlag(b2.getLegalPlaceOfLivingFlag());
    	b2.setLegalPlaceOfLivingID(b2.getLegalPlaceOfLivingID());
    	
    	b2.setRemarks(b2I.getRemarks());
    	b2.setAddition(b2I.getAddition());
    	
		b2.setVersionLastTimeOfDataEntry(b2I.getVersionLastTimeOfDataEntry());
		b2.setResearchCodeOriginal(b2I.getResearchCodeOriginal());
		b2.setVersionOriginalDataEntry(b2I.getVersionOriginalDataEntry());
		b2.setDate0(b2I.getDate0());
		
		// Registration
		
		b2.setRegistration(b4);

        // Dynamic data No!
		
        
    	return b2;
    	
    }
    
    
    /**
     * 
     * Allocate an B313_ST with keys copied from b4 and the rest copied from b2I
     * 
     * @param b2
     * @param b2I  
     * @return
     */
    
    private static B313_ST allocateB313(B4_ST b4, B2_ST b2I){
    	
    	B313_ST b313 = new B313_ST();
    	
    	// keys from b4
    	
    	b313.setDynamicDataType(13);
        b313.setKeyToRP(b4.getKeyToRP());
        b313.setEntryDateHead(b4.getEntryDateHead());
        b313.setKeyToSourceRegister(b4.getKeyToSourceRegister());
        
        // rest from b2I
        
        //b313.setStartDate(b2I.getStartDate());
        //b313.setStartFlag(b2I.getStartFlag());
        //b313.setStartEst(b2I.getStartEst());
        //b313.setEndDate(b2I.getEndDate());
        //b313.setEndFlag(b2I.getEndFlag());
        //b313.setEndEst(b2I.getEndEst());

		b313.setVersionLastTimeOfDataEntry(b2I.getVersionLastTimeOfDataEntry());
		b313.setResearchCodeOriginal(b2I.getResearchCodeOriginal());
		b313.setVersionOriginalDataEntry(b2I.getVersionOriginalDataEntry());
		b313.setDate0(b2I.getDate0());
		
        
    	return b313;
    	
    }
    
    /**
     * 
     * Allocate an B33_ST with keys copied from b2 and the rest copied from b33
     * 
     * @param b2
     * @param b33
     * @return
     */
    
    private static B33_ST allocateB33(B2_ST b2, B33_ST b33I){
    	
    	B33_ST b33 = new B33_ST(); 
    	
    	// Keys from b2
    	
    	b33.setDynamicDataType(3);
        b33.setKeyToRP(b2.getKeyToRP());
        b33.setEntryDateHead(b2.getEntryDateHead());
        b33.setKeyToSourceRegister(b2.getKeyToSourceRegister());
        b33.setKeyToRegistrationPersons(b2.getKeyToPersons());
        
        // rest from b33I
        
        b33.setStartDate(b33I.getStartDate());
        b33.setStartFlag(b33I.getStartFlag());
        b33.setStartEst(b33I.getStartEst());
        b33.setEndDate(b33I.getEndDate());
        b33.setEndFlag(b33I.getEndFlag());
        b33.setEndEst(b33I.getEndEst());
        
        b33.setReligionFlag(b33I.getReligionFlag());
        b33.setReligionStandardized(b33I.getReligionStandardized());
        b33.setReligionID(b33I.getReligionID());
        
		b33.setVersionLastTimeOfDataEntry(b33I.getVersionLastTimeOfDataEntry());
		b33.setResearchCodeOriginal(b33I.getResearchCodeOriginal());
		b33.setVersionOriginalDataEntry(b33I.getVersionOriginalDataEntry());
		b33.setDate0(b33I.getDate0());
		
		// Person from b2
		
		b33.setPerson(b2);

    	
    	return b33;
    	
    }
    
    
    /**
     * 
     * Allocate an B35_ST with keys copied from b2 and the rest copied from b35
     * 
     * @param b2
     * @param b35
     * @return
     */
    
    private static B35_ST allocateB35(B2_ST b2, B35_ST b35I){
    	
    	B35_ST b35 = new B35_ST(); 
    	
    	// Keys from b2
    	b35.setDynamicDataType(5);

        b35.setKeyToRP(b2.getKeyToRP());
        b35.setEntryDateHead(b2.getEntryDateHead());
        b35.setKeyToSourceRegister(b2.getKeyToSourceRegister());
        b35.setKeyToRegistrationPersons(b2.getKeyToPersons());
        
        // Rest from b35I
        
        b35.setStartDate(b35I.getStartDate());
        b35.setStartFlag(b35I.getStartFlag());
        b35.setStartEst(b35I.getStartEst());
        b35.setEndDate(b35I.getEndDate());
        b35.setEndFlag(b35I.getEndFlag());
        b35.setEndEst(b35I.getEndEst());
        
        
        b35.setOccupationFlag(b35I.getOccupationFlag());
        b35.setOccupationStandardized(b35I.getOccupationStandardized());
        b35.setOccupationID(b35I.getOccupationID());

		b35.setVersionLastTimeOfDataEntry(b35I.getVersionLastTimeOfDataEntry());
		b35.setResearchCodeOriginal(b35I.getResearchCodeOriginal());
		b35.setVersionOriginalDataEntry(b35I.getVersionOriginalDataEntry());
		b35.setDate0(b35I.getDate0());
		
		// Person from b2
		
		b35.setPerson(b2);
    	return b35;
    	
    }
    
    private static void handleB34_new(B2_ST b2L, B2_ST b2R){

    	// The constant ECHTGENOOT_MAN_GEEN_HOOFD             is used here as ECHTGENOOT only, HOOFD is irrelevant
    	// The constant ECHTGENOTE_VROUW_GEEN_VROUW_VAN_HOOFD is used here as ECHTGENOTE only, GEEN_VROUW_VAN_HOOFD is irrelevant


    	if(b2R.getKeyToPersons() == b2L.getKeyToPersons()) return;

    	int relL = b2L.getRelationsToPKHolder().get(0).getContentOfDynamicData();

    	int rel = 0;

    	switch(relL){
    	
    	
    	case ConstRelations2.HOOFD:
    		rel = handleHOOFD(b2L, b2R); break;

    		
    	case ConstRelations2.OUDER:
    	case ConstRelations2.VADER:
    	case ConstRelations2.MOEDER:
    		rel = handleOUDER(b2L, b2R); break;
    		
    	case ConstRelations2.ECHTGENOTE_HOOFD:
    	case ConstRelations2.ECHTGENOOT_VROUWELIJKE_OP:
    	case ConstRelations2.ECHTGENOOT_MAN_GEEN_HOOFD:    // Is this equal to previous?
    	case ConstRelations2.PARTNER:
    		rel = handlePARTNER(b2L, b2R); break;
    		
    	case ConstRelations2.KIND_PK:
    	case ConstRelations2.ZOON:
    	case ConstRelations2.DOCHTER:
    	case ConstRelations2.STIEFKIND_PK:
    	case ConstRelations2.STIEFZOON:
    	case ConstRelations2.STIEFDOCHTER:
    		rel = handleKIND(b2L, b2R); break;
    		
    	case ConstRelations2.SCHOONOUDER:
    	case ConstRelations2.SCHOONVADER:
    	case ConstRelations2.SCHOONMOEDER:
    		rel = handleSCHOONOUDER(b2L, b2R); break;
    		    	
    	}
    	
    	boolean ti = false; 
    	switch(rel){

    	case ConstRelations2.ZOON:            		    
    	case ConstRelations2.DOCHTER:          			 
    	case ConstRelations2.KLEINZOON:            		    
    	case ConstRelations2.KLEINDOCHTER:          			 
    	case ConstRelations2.KLEINKIND:          			 
    	case ConstRelations2.GROOTVADER:
    	case ConstRelations2.VADER:
    	case ConstRelations2.MOEDER:
    	case ConstRelations2.GROOTMOEDER:
    	case ConstRelations2.GROOTOUDER:
    	case ConstRelations2.BROER:
    	case ConstRelations2.HALFBROER:
    	case ConstRelations2.HALFZUSTER:
    	case ConstRelations2.ZUSTER:
    	case ConstRelations2.SIBLING:
    	case ConstRelations2.HALFSIBLING:
    	case ConstRelations2.OUDER:
    	case ConstRelations2.KIND_PK:


    		ti = true;
    		break;

    	default: 
    		ti = false;
				
    	}
    	
    	
    	if(rel != 0){ 	
    		B34_ST b34 = allocateB34(b2L.getRelationsToPKHolder().get(0), b2R.getRelationsToPKHolder().get(0), rel,  ti);
    		if(b34 != null)
    			b2L.getRelations().add(b34);

    	}
    	else{
    		System.out.println("No relation " + b2L.getKeyToRP() + "  " + b2L.getEntryDateHead() + "   " + b2L.getKeyToPersons() + "  " + b2R.getKeyToPersons());
    	}



    }
    
    private static int handleHOOFD(B2_ST b2L, B2_ST b2R){
    	
    	int relR = b2R.getRelationsToPKHolder().get(0).getContentOfDynamicData();
    	int rel = 0;

    	
		switch(relR){

		case ConstRelations2.ECHTGENOTE_HOOFD:                      rel = ConstRelations2.ECHTGENOOT_MAN_GEEN_HOOFD;			  break;  // Spouse female        		
		case ConstRelations2.ECHTGENOOT_MAN_GEEN_HOOFD:             rel = ConstRelations2.ECHTGENOTE_HOOFD;                       break;  // Spouse male         
		case ConstRelations2.PARTNER:                               rel = ConstRelations2.PARTNER;                                break;  // Spouse        

		case ConstRelations2.ZOON:
		case ConstRelations2.DOCHTER:
		case ConstRelations2.KIND_PK:

			if(b2L.getSex().equalsIgnoreCase("M"))   			
				rel = ConstRelations2.VADER;
			else
				if(b2L.getSex().equalsIgnoreCase("V"))
					rel = ConstRelations2.MOEDER;
				else
					rel = ConstRelations2.OUDER;

			break;  

		case ConstRelations2.STIEFZOON:
		case ConstRelations2.STIEFDOCHTER:
		case ConstRelations2.STIEFKIND_PK:

			if(b2L.getSex().equalsIgnoreCase("M"))   			
				rel = ConstRelations2.STIEFVADER;
			else
				if(b2L.getSex().equalsIgnoreCase("V"))
					rel = ConstRelations2.STIEFMOEDER;
				else
					rel = ConstRelations2.STIEFOUDER;

			break;  

		case ConstRelations2.VADER:           
		case ConstRelations2.MOEDER:           
		case ConstRelations2.OUDER:           

			if(b2L.getSex().equalsIgnoreCase("M"))   			
				rel = ConstRelations2.ZOON;
			else
				if(b2L.getSex().equalsIgnoreCase("V"))
					rel = ConstRelations2.DOCHTER;
				else
					rel = ConstRelations2.KIND_PK;

			break;  	

		case ConstRelations2.SCHOONOUDER:           
		case ConstRelations2.SCHOONVADER:           
		case ConstRelations2.SCHOONMOEDER:           

			if(b2L.getSex().equalsIgnoreCase("M"))   			
				rel = ConstRelations2.SCHOONZOON;
			else
				if(b2L.getSex().equalsIgnoreCase("V"))
					rel = ConstRelations2.SCHOONDOCHTER;
				else
					rel = ConstRelations2.SCHOONKIND;

			break;  	



		}
    	
    	return rel;
    	
    }
    
    private static int handleOUDER(B2_ST b2L, B2_ST b2R){
    	
    	int relL = b2L.getRelationsToPKHolder().get(0).getContentOfDynamicData();
    	int relR = b2R.getRelationsToPKHolder().get(0).getContentOfDynamicData();
    	int rel = 0;

    	
		switch(relR){

		case ConstRelations2.HOOFD:                                 rel = relR;                                                   break;  
		
		case ConstRelations2.ECHTGENOTE_HOOFD:                             		
		case ConstRelations2.ECHTGENOOT_MAN_GEEN_HOOFD:                    
		case ConstRelations2.PARTNER:                 

			if(b2L.getSex().equalsIgnoreCase("M"))   			
				rel = ConstRelations2.SCHOONVADER;
			else
				if(b2L.getSex().equalsIgnoreCase("V"))
					rel = ConstRelations2.SCHOONMOEDER;
				else
					rel = ConstRelations2.SCHOONOUDER;

			break;  

		case ConstRelations2.ZOON:
		case ConstRelations2.DOCHTER:
		case ConstRelations2.KIND_PK:
		case ConstRelations2.STIEFZOON:
		case ConstRelations2.STIEFDOCHTER:
		case ConstRelations2.STIEFKIND_PK:

			if(testBlood(b2R, b2L)){
				if(b2L.getSex().equalsIgnoreCase("M"))   			
					rel = ConstRelations2.GROOTVADER;
				else{
					if(b2L.getSex().equalsIgnoreCase("V"))
						rel = ConstRelations2.GROOTMOEDER;
					else
						rel = ConstRelations2.GROOTOUDER;
				}
			}
			else{
				if(b2L.getSex().equalsIgnoreCase("M"))   			
					rel = ConstRelations2.STIEFGROOTVADER;
				else
					if(b2L.getSex().equalsIgnoreCase("V"))
						rel = ConstRelations2.STIEFGROOTMOEDER;
					else
						rel = ConstRelations2.STIEFGROOTOUDER;

			}

			break;  


		}

    	
    	return rel;
    	

    	
    }
    
    private static int handlePARTNER(B2_ST b2L, B2_ST b2R){
    	
    	int relR = b2R.getRelationsToPKHolder().get(0).getContentOfDynamicData();
    	int rel = 0;

   	
		switch(relR){

		case ConstRelations2.HOOFD:                                 rel = relR;                                                   break;  

		case ConstRelations2.ZOON: 
		case ConstRelations2.STIEFZOON:
		case ConstRelations2.DOCHTER: 
		case ConstRelations2.STIEFDOCHTER: 
		case ConstRelations2.STIEFKIND_PK: 
		case ConstRelations2.KIND_PK: 

			if(b2L.getSex().equalsIgnoreCase("M")){
				if(b2L.getPersonID() == b2R.getPersonID_FA()){
					rel= ConstRelations2.VADER;    				
				}
				else{
					rel= ConstRelations2.STIEFVADER;    				
				}
			}
			else{
				if(b2L.getSex().equalsIgnoreCase("V")){

					if(b2L.getPersonID() == b2R.getPersonID_MO()){
						rel= ConstRelations2.MOEDER;    				
					}
					else{
						rel= ConstRelations2.STIEFMOEDER;    				
					}
				}
				else{

					if(b2L.getPersonID() == b2R.getPersonID_FA() || b2L.getPersonID() == b2R.getPersonID_MO())
						rel = ConstRelations2.OUDER;
					else
						rel = ConstRelations2.STIEFOUDER;
				}

			}

			break;


		case ConstRelations2.VADER:
		case ConstRelations2.MOEDER:            
		case ConstRelations2.OUDER:            

			if(b2L.getSex().equalsIgnoreCase("M"))
				rel = ConstRelations2.SCHOONZOON;
			else
				if(b2L.getSex().equalsIgnoreCase("V"))
					rel = ConstRelations2.SCHOONDOCHTER;
				else
					rel = ConstRelations2.SCHOONKIND;

			break;

		case ConstRelations2.SCHOONVADER:

			if(b2L.getPersonID_FA() == b2R.getPersonID()){
				if(b2L.getSex().equalsIgnoreCase("M"))
					rel = ConstRelations2.ZOON;
				else
					if(b2L.getSex().equalsIgnoreCase("V"))
						rel = ConstRelations2.DOCHTER;
					else
						rel= ConstRelations2.SCHOONKIND;

			}

			break;

		case ConstRelations2.SCHOONMOEDER:            

			if(b2L.getPersonID_MO() == b2R.getPersonID()){
				if(b2L.getSex().equalsIgnoreCase("M"))
					rel = ConstRelations2.ZOON;
				else
					if(b2L.getSex().equalsIgnoreCase("V"))
						rel = ConstRelations2.DOCHTER;
					else
						rel= ConstRelations2.SCHOONKIND;

			}
			break;

		case ConstRelations2.SCHOONOUDER:            

			if(b2L.getPersonID_MO() == b2R.getPersonID() || b2L.getPersonID_FA() == b2R.getPersonID()){

				if(b2L.getSex().equalsIgnoreCase("M"))
					rel = ConstRelations2.ZOON;
				else
					if(b2L.getSex().equalsIgnoreCase("V"))
						rel = ConstRelations2.DOCHTER;
					else
						rel= ConstRelations2.SCHOONKIND;

			}

			break;



		}

		return rel;

    	
    }
    
    private static int handleKIND(B2_ST b2L, B2_ST b2R){
    	
    	int relR = b2R.getRelationsToPKHolder().get(0).getContentOfDynamicData();
    	int rel = 0;
	
		switch(relR){

		case ConstRelations2.HOOFD:                                 rel = relR;                                                   break;  

		case ConstRelations2.ECHTGENOTE_HOOFD: 
		case ConstRelations2.ECHTGENOOT_MAN_GEEN_HOOFD: 
		case ConstRelations2.PARTNER:                 
			
			int ksex = 0;
			if(b2L.getSex().equalsIgnoreCase("M"))				
				ksex = 1;
			else
				if(b2L.getSex().equalsIgnoreCase("V"))
					ksex = 2;
			
			boolean bloodRelated = false;
			if(b2L.getPersonID_MO() == b2R.getPersonID() || b2L.getPersonID_FA() == b2R.getPersonID())
				bloodRelated = true;
			
			
			switch(ksex){
			
			case 0:
				
				switch(ksex){
				
				case 0: rel = (bloodRelated == true) ? ConstRelations2.KIND_PK : ConstRelations2.STIEFKIND_PK; break; 
				case 1: rel = (bloodRelated == true) ? ConstRelations2.ZOON    : ConstRelations2.STIEFZOON;    break;
				case 2: rel = (bloodRelated == true) ? ConstRelations2.DOCHTER : ConstRelations2.STIEFDOCHTER; break;
				}
			}

			break;


		case ConstRelations2.ZOON:
		case ConstRelations2.STIEFZOON:     
		case ConstRelations2.DOCHTER:
		case ConstRelations2.STIEFDOCHTER:     
		case ConstRelations2.KIND_PK:     
		case ConstRelations2.STIEFKIND_PK:     

			int lsex = 0;
			if(b2L.getSex().equalsIgnoreCase("M"))				
				lsex = 1;
			else
				if(b2L.getSex().equalsIgnoreCase("V"))
					lsex = 2;

			
			int r = 0;

			if(b2L.getPersonID_FA() == b2R.getPersonID_FA()) r++;
			if(b2L.getPersonID_MO() == b2R.getPersonID_MO()) r++;


			switch(lsex){
			
			case 0: 
				
				switch(r){
				
				case 0: rel = ConstRelations2.STIEFSIBLING;    break;
				case 1: rel = ConstRelations2.HALFSIBLING;     break;
				case 2: rel = ConstRelations2.SIBLING;         break;
				
				}
				break;
				
			case 1:
				
				switch(r){
				
				case 0: rel = ConstRelations2.STIEFBROER;      break;
				case 1: rel = ConstRelations2.HALFBROER;       break;
				case 2: rel = ConstRelations2.BROER;           break;
				
				}
				break;
				
			case 2:
				
				switch(r){
				
				case 0: rel = ConstRelations2.STIEFZUSTER;    break;
				case 1: rel = ConstRelations2.HALFZUSTER;     break;
				case 2: rel = ConstRelations2.ZUSTER;         break;
				
				}
				break;
				
				
			}
			
			break;

		case ConstRelations2.VADER:            
		case ConstRelations2.MOEDER:           
		case ConstRelations2.SCHOONVADER:            
		case ConstRelations2.SCHOONMOEDER:        
			
			lsex = 0;
			if(b2L.getSex().equalsIgnoreCase("M"))				
				lsex = 1;
			else
				if(b2L.getSex().equalsIgnoreCase("V"))
					lsex = 2;

			
			bloodRelated = testBlood(b2L, b2R);
			
			
			switch(lsex){
				
			case 0: rel = (bloodRelated == true) ? ConstRelations2.KLEINKIND : ConstRelations2.STIEFKLEINKIND; break;
			case 1: rel = (bloodRelated == true) ? ConstRelations2.KLEINZOON : ConstRelations2.STIEFKLEINZOON; break;
			case 2: rel = (bloodRelated == true) ? ConstRelations2.KLEINDOCHTER : ConstRelations2.STIEFKLEINDOCHTER; break;
			
			}
			

			break;
		}

		return rel;

    	
    }

    private static int handleSCHOONOUDER(B2_ST b2L, B2_ST b2R){
    	
    	int relR = b2R.getRelationsToPKHolder().get(0).getContentOfDynamicData();
    	int rel = 0;
    	
		int lsex = 0;
		if(b2L.getSex().equalsIgnoreCase("M"))				
			lsex = 1;
		else
			if(b2L.getSex().equalsIgnoreCase("V"))
				lsex = 2;
		
		boolean bloodRelated = false;
		if(b2L.getPersonID_MO() == b2R.getPersonID() || b2L.getPersonID_FA() == b2R.getPersonID())
			bloodRelated = true;
		else
			if(testBlood(b2R, b2L))
				bloodRelated = true;

		switch(relR){


		case ConstRelations2.HOOFD:                                 rel = relR;                                                   break;  

		case ConstRelations2.ECHTGENOTE_HOOFD:			
		case ConstRelations2.ECHTGENOOT_MAN_GEEN_HOOFD: 
		case ConstRelations2.PARTNER: 
			
			if(bloodRelated){

				switch(lsex){

				case 0: rel = ConstRelations2.OUDER; break;
				case 1: rel = ConstRelations2.VADER; break;
				case 2: rel = ConstRelations2.MOEDER; break;
				}
			}
			break;

		case ConstRelations2.ZOON:            		    
		case ConstRelations2.DOCHTER:          			 
		case ConstRelations2.STIEFZOON:        			
		case ConstRelations2.STIEFDOCHTER:     	
		case ConstRelations2.KIND_PK:     			
		case ConstRelations2.STIEFKIND_PK:     			

			if(bloodRelated){
				
				switch(lsex){
				case 0: rel = ConstRelations2.GROOTOUDER; break;
				case 1: rel = ConstRelations2.GROOTVADER; break;
				case 2: rel = ConstRelations2.GROOTMOEDER; break;
				}
			}
			else{
				switch(lsex){
				case 0: rel = ConstRelations2.STIEFGROOTOUDER; break;
				case 1: rel = ConstRelations2.STIEFGROOTVADER; break;
				case 2: rel = ConstRelations2.STIEFGROOTMOEDER; break;
				}
				
			}


			break;

		}

		

    	return rel;
    	
    }

    
    private static void handleB34(B2_ST b2L, B2_ST b2R){

    	// The constant ECHTGENOOT_MAN_GEEN_HOOFD             is used here as ECHTGENOOT only, HOOFD is irrelevant
    	// The constant ECHTGENOTE_VROUW_GEEN_VROUW_VAN_HOOFD is used here as ECHTGENOTE only, GEEN_VROUW_VAN_HOOFD is irrelevant


    	if(b2R.getKeyToPersons() == b2L.getKeyToPersons()) return;

    	int relL = b2L.getRelationsToPKHolder().get(0).getContentOfDynamicData();
    	int relR = b2R.getRelationsToPKHolder().get(0).getContentOfDynamicData();

    	int rel = 0;

    	switch(relL){

    	case ConstRelations2.HOOFD:                                 // Card Holder    	

    		switch(relR){

    		case ConstRelations2.ECHTGENOTE_HOOFD:                      rel = ConstRelations2.ECHTGENOOT_MAN_GEEN_HOOFD;			  break;  // Spouse female        		
    		case ConstRelations2.ECHTGENOOT_MAN_GEEN_HOOFD:             rel = ConstRelations2.ECHTGENOTE_VROUW_GEEN_VROUW_VAN_HOOFD;  break;  // Spouse male         

    		case ConstRelations2.ZOON:
    		case ConstRelations2.DOCHTER:
    		case ConstRelations2.KIND_PK:

    			if(b2L.getSex().equalsIgnoreCase("M"))   			
    				rel = ConstRelations2.VADER;
    			else
    				if(b2L.getSex().equalsIgnoreCase("V"))
    					rel = ConstRelations2.MOEDER;
    				else
    					rel = ConstRelations2.OUDER;

    			break;  

    		case ConstRelations2.STIEFZOON:
    		case ConstRelations2.STIEFDOCHTER:
    		case ConstRelations2.STIEFKIND_PK:

    			if(b2L.getSex().equalsIgnoreCase("M"))   			
    				rel = ConstRelations2.STIEFVADER;
    			else
    				if(b2L.getSex().equalsIgnoreCase("V"))
    					rel = ConstRelations2.STIEFMOEDER;
    				else
    					rel = ConstRelations2.STIEFOUDER;

    			break;  

    		case ConstRelations2.VADER:           
    		case ConstRelations2.MOEDER:           

    			if(b2L.getSex().equalsIgnoreCase("M"))   			
    				rel = ConstRelations2.ZOON;
    			else
    				if(b2L.getSex().equalsIgnoreCase("V"))
    					rel = ConstRelations2.DOCHTER;
    				else
    					rel = ConstRelations2.KIND_PK;

    			break;  	

    		case ConstRelations2.SCHOONVADER:           
    		case ConstRelations2.SCHOONMOEDER:           

    			if(b2L.getSex().equalsIgnoreCase("M"))   			
    				rel = ConstRelations2.SCHOONZOON;
    			else
    				if(b2L.getSex().equalsIgnoreCase("V"))
    					rel = ConstRelations2.SCHOONDOCHTER;
    				else
    					rel = ConstRelations2.SCHOONKIND;

    			break;  	



    		}
    		break;

    	case ConstRelations2.ECHTGENOTE_HOOFD:						// Spouse female
    	case ConstRelations2.ECHTGENOOT_MAN_GEEN_HOOFD:             // Spouse male

    		switch(relR){

    		case ConstRelations2.HOOFD: 

    			if(b2L.getSex().equalsIgnoreCase("M"))    			
    				rel = ConstRelations2.ECHTGENOOT_MAN_GEEN_HOOFD;
    			else
    				if(b2L.getSex().equalsIgnoreCase("V"))  
    					rel = ConstRelations2.ECHTGENOTE_VROUW_GEEN_VROUW_VAN_HOOFD;
    				else
    					rel = ConstRelations2.PARTNER;


    			break;                

    		case ConstRelations2.ZOON: 
    		case ConstRelations2.STIEFZOON:
    		case ConstRelations2.DOCHTER: 
    		case ConstRelations2.STIEFDOCHTER: 
    		case ConstRelations2.STIEFKIND_PK: 
    		case ConstRelations2.KIND_PK: 

    			if(b2L.getSex().equalsIgnoreCase("M")){
    				if(b2L.getPersonID() == b2R.getPersonID_FA()){
    					rel= ConstRelations2.VADER;    				
    				}
    				else{
    					rel= ConstRelations2.STIEFVADER;    				
    				}
    			}
    			else{
    				if(b2L.getSex().equalsIgnoreCase("V")){

    					if(b2L.getPersonID() == b2R.getPersonID_MO()){
    						rel= ConstRelations2.MOEDER;    				
    					}
    					else{
    						rel= ConstRelations2.STIEFMOEDER;    				
    					}
    				}
    				else{

    					if(b2L.getPersonID() == b2R.getPersonID_FA() || b2L.getPersonID() == b2R.getPersonID_MO())
    						rel = ConstRelations2.OUDER;
    					else
    						rel = ConstRelations2.STIEFOUDER;
    				}

    			}

    			break;


    		case ConstRelations2.VADER:
    		case ConstRelations2.MOEDER:            
    		case ConstRelations2.OUDER:            

    			if(b2L.getSex().equalsIgnoreCase("M"))
    				rel = ConstRelations2.SCHOONZOON;
    			else
    				if(b2L.getSex().equalsIgnoreCase("V"))
    					rel = ConstRelations2.SCHOONDOCHTER;
    				else
    					rel = ConstRelations2.SCHOONKIND;

    			break;

    		case ConstRelations2.SCHOONVADER:

    			if(b2L.getPersonID_FA() == b2R.getPersonID()){

    				if(b2L.getSex().equalsIgnoreCase("M"))
    					rel = ConstRelations2.ZOON;
    				else
    					if(b2L.getSex().equalsIgnoreCase("V"))
    						rel = ConstRelations2.DOCHTER;
    					else
    						rel= ConstRelations2.SCHOONKIND;

    			}

    			break;

    		case ConstRelations2.SCHOONMOEDER:            

    			if(b2L.getPersonID_MO() == b2R.getPersonID()){

    				if(b2L.getSex().equalsIgnoreCase("M"))
    					rel = ConstRelations2.ZOON;
    				else
    					if(b2L.getSex().equalsIgnoreCase("V"))
    						rel = ConstRelations2.DOCHTER;
    					else
    						rel= ConstRelations2.SCHOONKIND;

    			}

    			break;



    		}

    		break;

    	case ConstRelations2.STIEFZOON:                                  // Son
    	case ConstRelations2.ZOON:                                 

    		switch(relR){

    		case ConstRelations2.HOOFD:    


    			rel = (relL == ConstRelations2.ZOON) ?  ConstRelations2.ZOON :  ConstRelations2.STIEFZOON;
    			break;



    		case ConstRelations2.ECHTGENOTE_HOOFD: 
    		case ConstRelations2.ECHTGENOOT_MAN_GEEN_HOOFD: 

    			if(b2R.getSex().equalsIgnoreCase("M")){
    				if(b2L.getPersonID_FA() == b2R.getPersonID())
    					rel = ConstRelations2.ZOON;
    				else
    					rel = ConstRelations2.STIEFZOON;
    			}
    			else{
    				if(b2R.getSex().equalsIgnoreCase("V")){
    					if(b2L.getPersonID_MO() == b2R.getPersonID())
    						rel = ConstRelations2.ZOON;
    					else
    						rel = ConstRelations2.STIEFZOON;
    				}
    				else{
    					if(b2L.getPersonID_MO() == b2R.getPersonID() || b2L.getPersonID_FA() == b2R.getPersonID())
    						rel = ConstRelations2.ZOON;
    					else
    						rel = ConstRelations2.STIEFZOON;

    				}
    			}	

    			break;


    		case ConstRelations2.ZOON:
    		case ConstRelations2.STIEFZOON:     
    		case ConstRelations2.DOCHTER:
    		case ConstRelations2.STIEFDOCHTER:     
    		case ConstRelations2.KIND_PK:     
    		case ConstRelations2.STIEFKIND_PK:     

    			int r = 0;

    			if(b2L.getPersonID_FA() == b2R.getPersonID_FA()) r++;
    			if(b2L.getPersonID_MO() == b2R.getPersonID_MO()) r++;


    			if(r == 0){        		
    				rel = ConstRelations2.STIEFBROER;
    			}
    			else
    				if(r ==1){
    					rel = ConstRelations2.HALFBROER;
    				}
    				else{
    					rel = ConstRelations2.BROER;
    				}



    			break;

    		case ConstRelations2.VADER:            
    		case ConstRelations2.MOEDER:           
    		case ConstRelations2.SCHOONVADER:            
    		case ConstRelations2.SCHOONMOEDER:        

    			if(testBlood(b2L, b2R)){
    				rel = ConstRelations2.KLEINZOON;
    			}
    			else 
    				rel = ConstRelations2.STIEFKLEINZOON;

    			break;
    		}

    		break;

    	case ConstRelations2.DOCHTER:                               // Daughter
    	case ConstRelations2.STIEFDOCHTER:                          // Stepdaughter	


    		switch(relR){

    		case ConstRelations2.HOOFD:    


    			rel = (relL == ConstRelations2.DOCHTER) ?  ConstRelations2.DOCHTER :  ConstRelations2.STIEFDOCHTER;
    			break;



    		case ConstRelations2.ECHTGENOTE_HOOFD: 
    		case ConstRelations2.ECHTGENOOT_MAN_GEEN_HOOFD: 

    			if(b2R.getSex().equalsIgnoreCase("M")){
    				if(b2L.getPersonID_FA() == b2R.getPersonID())
    					rel = ConstRelations2.DOCHTER;
    				else
    					rel = ConstRelations2.STIEFDOCHTER;
    			}
    			else{
    				if(b2R.getSex().equalsIgnoreCase("V")){
    					if(b2L.getPersonID_MO() == b2R.getPersonID())
    						rel = ConstRelations2.DOCHTER;
    					else
    						rel = ConstRelations2.STIEFDOCHTER;
    				}
    				else{
    					if(b2L.getPersonID_MO() == b2R.getPersonID() || b2L.getPersonID_FA() == b2R.getPersonID())
    						rel = ConstRelations2.DOCHTER;
    					else
    						rel = ConstRelations2.STIEFDOCHTER;

    				}
    			}	

    			break;


    		case ConstRelations2.ZOON:
    		case ConstRelations2.DOCHTER:
    		case ConstRelations2.STIEFZOON:     
    		case ConstRelations2.STIEFDOCHTER:     
    		case ConstRelations2.STIEFSIBLING:     
    		case ConstRelations2.SIBLING:     

    			int r = 0;

    			if(b2L.getPersonID_FA() == b2R.getPersonID_FA()) r++;
    			if(b2L.getPersonID_MO() == b2R.getPersonID_MO()) r++;


    			if(r == 0){        		
    				rel = ConstRelations2.STIEFZUSTER;
    			}
    			else
    				if(r ==1){
    					rel = ConstRelations2.HALFZUSTER;
    				}
    				else{
    					rel = ConstRelations2.ZUSTER;
    				}

    			break;

    		case ConstRelations2.VADER:            
    		case ConstRelations2.MOEDER:           
    		case ConstRelations2.SCHOONVADER:            
    		case ConstRelations2.SCHOONMOEDER:           

    			if(testBlood(b2L, b2R)){
    				rel = ConstRelations2.KLEINDOCHTER;
    			}
    			else 
    				rel = ConstRelations2.STIEFKLEINDOCHTER;

    			break;

    		}

    		break;

    	case ConstRelations2.KIND_PK:
    	case ConstRelations2.STIEFKIND_PK:                                 

    		switch(relR){

    		case ConstRelations2.HOOFD:    


    			rel = (relL == ConstRelations2.KIND_PK) ?  ConstRelations2.KIND_PK :  ConstRelations2.STIEFKIND_PK;
    			break;



    		case ConstRelations2.ECHTGENOTE_HOOFD: 
    		case ConstRelations2.ECHTGENOOT_MAN_GEEN_HOOFD: 

    			if(b2R.getSex().equalsIgnoreCase("M")){
    				if(b2L.getPersonID_FA() == b2R.getPersonID())
    					rel = ConstRelations2.KIND_PK;
    				else
    					rel = ConstRelations2.STIEFKIND_PK;
    			}
    			else{
    				if(b2R.getSex().equalsIgnoreCase("V")){
    					if(b2L.getPersonID_MO() == b2R.getPersonID())
    						rel = ConstRelations2.KIND_PK;
    					else
    						rel = ConstRelations2.STIEFKIND_PK;
    				}
    				else{
    					if(b2L.getPersonID_MO() == b2R.getPersonID() || b2L.getPersonID_FA() == b2R.getPersonID())
    						rel = ConstRelations2.KIND_PK;
    					else
    						rel = ConstRelations2.STIEFKIND_PK;

    				}
    			}	

    			break;


    		case ConstRelations2.ZOON:
    		case ConstRelations2.STIEFZOON:     
    		case ConstRelations2.DOCHTER:
    		case ConstRelations2.STIEFDOCHTER:     
    		case ConstRelations2.KIND_PK:     
    		case ConstRelations2.STIEFKIND_PK:     

    			int r = 0;

    			if(b2L.getPersonID_FA() == b2R.getPersonID_FA()) r++;
    			if(b2L.getPersonID_MO() == b2R.getPersonID_MO()) r++;


    			if(r == 0){        		
    				rel = ConstRelations2.STIEFSIBLING;
    			}
    			else
    				if(r ==1){
    					rel = ConstRelations2.HALFSIBLING;
    				}
    				else{
    					rel = ConstRelations2.SIBLING;
    				}



    			break;

    		case ConstRelations2.VADER:            
    		case ConstRelations2.MOEDER:           
    		case ConstRelations2.SCHOONVADER:            
    		case ConstRelations2.SCHOONMOEDER:        

    			if(testBlood(b2L, b2R)){
    				rel = ConstRelations2.KLEINKIND;
    			}
    			else 
    				rel = ConstRelations2.STIEFKLEINKIND;

    			break;
    		}

    		break;



    	case ConstRelations2.VADER:                                 // Father	

    		switch(relR){


    		case ConstRelations2.HOOFD:            			rel = ConstRelations2.VADER;                               break;                                       		
    		case ConstRelations2.ECHTGENOTE_HOOFD: 			rel = ConstRelations2.SCHOONVADER;                         break;                                        		
    		case ConstRelations2.ECHTGENOOT_MAN_GEEN_HOOFD: rel = ConstRelations2.SCHOONVADER;                         break;  


    		case ConstRelations2.ZOON:             			 
    		case ConstRelations2.DOCHTER:          			
    		case ConstRelations2.STIEFZOON:        			  
    		case ConstRelations2.STIEFDOCHTER:     			
    		case ConstRelations2.KIND_PK:     			
    		case ConstRelations2.STIEFKIND_PK:     			

    			if(testBlood(b2R, b2L)){

    				rel = ConstRelations2.GROOTVADER;
    			}
    			else 
    				rel = ConstRelations2.STIEFGROOTVADER;

    			break;


    		}

    		break;


    	case ConstRelations2.MOEDER:                                // Mother

    		switch(relR){


    		case ConstRelations2.HOOFD:			            rel = ConstRelations2.MOEDER;               			    break;                                       		
    		case ConstRelations2.ECHTGENOTE_HOOFD:			rel = ConstRelations2.SCHOONMOEDER;                         break;
    		case ConstRelations2.ECHTGENOOT_MAN_GEEN_HOOFD: rel = ConstRelations2.SCHOONVADER;                          break;

    		case ConstRelations2.ZOON:            		    
    		case ConstRelations2.DOCHTER:          			
    		case ConstRelations2.STIEFZOON:        			
    		case ConstRelations2.STIEFDOCHTER:     
    		case ConstRelations2.KIND_PK:     			
    		case ConstRelations2.STIEFKIND_PK:     			


    			if(testBlood(b2R, b2L)){

    				rel = ConstRelations2.GROOTMOEDER;
    			}
    			else 
    				rel = ConstRelations2.STIEFGROOTMOEDER;

    			break;

    		}

    		break;

    	case ConstRelations2.SCHOONMOEDER:                                

    		switch(relR){


    		case ConstRelations2.HOOFD:			            rel = ConstRelations2.SCHOONMOEDER;                         break;

    		case ConstRelations2.ECHTGENOTE_HOOFD:			
    		case ConstRelations2.ECHTGENOOT_MAN_GEEN_HOOFD: 

    			if(b2L.getPersonID() == b2R.getPersonID_MO()){
    				rel = ConstRelations2.MOEDER;
    			}
    			break;

    		case ConstRelations2.ZOON:            		   
    		case ConstRelations2.DOCHTER:          			
    		case ConstRelations2.STIEFZOON:        			
    		case ConstRelations2.STIEFDOCHTER:     		
    		case ConstRelations2.KIND_PK:     			
    		case ConstRelations2.STIEFKIND_PK:     			


    			if(testBlood(b2R, b2L)){

    				rel = ConstRelations2.GROOTMOEDER;
    			}
    			else 
    				rel = ConstRelations2.STIEFGROOTMOEDER;

    			break;




    		}

    		break;

    	case ConstRelations2.SCHOONVADER:                                

    		switch(relR){


    		case ConstRelations2.HOOFD:			            rel = ConstRelations2.SCHOONVADER;                          break;

    		case ConstRelations2.ECHTGENOTE_HOOFD:			
    		case ConstRelations2.ECHTGENOOT_MAN_GEEN_HOOFD: 

    			if(b2L.getPersonID() == b2R.getPersonID_FA()){
    				rel = ConstRelations2.VADER;
    			}
    			break;

    		case ConstRelations2.ZOON:            		    
    		case ConstRelations2.DOCHTER:          			 
    		case ConstRelations2.STIEFZOON:        			
    		case ConstRelations2.STIEFDOCHTER:     	
    		case ConstRelations2.KIND_PK:     			
    		case ConstRelations2.STIEFKIND_PK:     			


    			if(testBlood(b2R, b2L)){    				
    				rel = ConstRelations2.GROOTVADER;
    			}
    			else 
    				rel = ConstRelations2.STIEFGROOTVADER;

    			break;

    		}

    		break;

    	}


    	boolean ti = false; 
    	switch(rel){

    	case ConstRelations2.ZOON:            		    
    	case ConstRelations2.DOCHTER:          			 
    	case ConstRelations2.KLEINZOON:            		    
    	case ConstRelations2.KLEINDOCHTER:          			 
    	case ConstRelations2.KLEINKIND:          			 
    	case ConstRelations2.GROOTVADER:
    	case ConstRelations2.VADER:
    	case ConstRelations2.MOEDER:
    	case ConstRelations2.GROOTMOEDER:
    	case ConstRelations2.BROER:
    	case ConstRelations2.HALFBROER:
    	case ConstRelations2.HALFZUSTER:
    	case ConstRelations2.ZUSTER:
    	case ConstRelations2.SIBLING:
    	case ConstRelations2.OUDER:
    	case ConstRelations2.KIND_PK:


    		ti = true;
    		break;

    	default: 
    		ti = false;
				
    	}
    	
    	
    	if(rel != 0){ 	
    		B34_ST b34 = allocateB34(b2L.getRelationsToPKHolder().get(0), b2R.getRelationsToPKHolder().get(0), rel,  ti);
    		if(b34 != null)
    			b2L.getRelations().add(b34);

    	}
    	else{
    		;//System.out.println("No relation " + b2L.getKeyToRP() + "  " + b2L.getEntryDateHead() + "   " + b2L.getKeyToPersons() + "  " + b2R.getKeyToPersons());
    	}


    }

    /*
     * 
     *  Check if a (stepgrandchild or grandchild) actually is grandchild or stepgrandchild
     * 
     */

    
    static boolean testBlood(B2_ST b2lower, B2_ST b2_higher){
    	
    	// Try to find parent B2_ST of lower
    	
    	B2_ST b2Father = null;
    	B2_ST b2Mother = null;
    	
    	for(B2_ST b2: b2lower.getRegistration().getPersons()){
    		
    		if(b2.getPersonID() == b2lower.getPersonID_FA())
    			b2Father = b2;
    		if(b2.getPersonID() == b2lower.getPersonID_MO())
    			b2Mother = b2;
    		
    		
    	}
    	
    	if(b2Mother != null && b2Mother.getPersonID_MO() == b2_higher.getPersonID()) return true;
    	if(b2Mother != null && b2Mother.getPersonID_FA() == b2_higher.getPersonID()) return true;
    	if(b2Father != null && b2Father.getPersonID_MO() == b2_higher.getPersonID()) return true;
    	if(b2Father != null && b2Father.getPersonID_FA() == b2_higher.getPersonID()) return true;
    		
    	return false;
    	
    	}
    	
    	
    	
    	
    

    /**
     * 
     * Allocate an B34_ST with keys copied from b4 and the rest copied from b311
     * 
     * @param b2
     * @param b2I  
     * @return
     */
    
    private static B34_ST allocateB34(B313_ST b313L, B313_ST b313R, int rel, boolean time_invariant){
    	
    	B34_ST b34 = new B34_ST(); 
    	
    	// Keys from b313L 
    	b34.setDynamicDataType(4);
    	
    	b34.setVersionLastTimeOfDataEntry(b313L.getVersionLastTimeOfDataEntry());
    	b34.setResearchCodeOriginal(b313L.getResearchCodeOriginal());
    	b34.setVersionOriginalDataEntry(b313L.getVersionOriginalDataEntry());
    	b34.setDate0(b313L.getDate0());


        b34.setKeyToRP(b313L.getKeyToRP());
        b34.setEntryDateHead(b313L.getEntryDateHead());
        b34.setKeyToSourceRegister(b313L.getKeyToSourceRegister());
        b34.setKeyToRegistrationPersons(b313L.getKeyToRegistrationPersons());
        b34.setDynamicDataSequenceNumber(b313L.getPerson().getRelations().size() + 1);
        
        b34.setContentOfDynamicData(rel);
        b34.setValueOfRelatedPerson(b313R.getKeyToRegistrationPersons());
        
        
        
        if(time_invariant == false) {
        	int [] a = findCommonTime(b313L.getStartDate(), b313L.getEndDate(), b313R.getStartDate(), b313R.getEndDate());

        	if(a != null){

        		b34.setStartDate(Common1.dateFromDayCount(a[0]));
        		b34.setEndDate(Common1.dateFromDayCount(a[1]));
        	}
        	//else return null;
        }
        return b34;
    }
    

    private static int[] findCommonTime(String b2LL, String b2LR, String b2RL, String b2RR){
    	

       	
       	if(b2LL == null || b2LR == null || b2RL == null || b2RR == null) return null;
       	
       	String b = "----[" +  b2LL + ", " + b2LR + "] [" +  b2RL + ", " + b2RR + "]";
       	
       	// Common1.dateFromDayCount(Common1.dayCount(B2dibg) + 1);
       	
       	int l = Common1.dayCount(b2LL);
       	if(Common1.dayCount(b2RL) > l) l = Common1.dayCount(b2RL);
       	
       	int r = Common1.dayCount(b2LR);
       	if(Common1.dayCount(b2RR) < r) r = Common1.dayCount(b2RR);

       	if(l < r){
       		
       		int[ ] a = new int[2];
       		a[0] = l;
       		a[1] = r;
       		
       		//System.out.println("l = " + l +  ", r = "+ r);
       		
       		//System.out.println(b + "   [" +  Common1.dateFromDayCount(l) + ", " +Common1.dateFromDayCount(r) + "]");
       		
       		return a;
       		
       	}
       	
       	//System.out.println(b + "   No common time");
       	
    	return null;
    
    	
    }
    
    
    /*
      * 
      * This routine compares two persons to see if they are in fact the same person
      * 
      * @return
      */

    private static int comparePersons(B2_ST p, B2_ST pu) {
    	
    	// First Check if the persons are from different generations
    	
    	int [] Generation0 = {ConstRelations2.VADER,       ConstRelations2.MOEDER,           ConstRelations2.OUDER,
    			              ConstRelations2.SCHOONVADER, ConstRelations2.SCHOONMOEDER,     ConstRelations2.SCHOONOUDER};
    	
    	int [] Generation1 = {ConstRelations2.HOOFD,       ConstRelations2.ECHTGENOTE_HOOFD, ConstRelations2.ECHTGENOOT_MAN_GEEN_HOOFD};
    	
    	int [] Generation2 = {ConstRelations2.ZOON,        ConstRelations2.DOCHTER,          ConstRelations2.KIND_PK,
    			              ConstRelations2.STIEFZOON,   ConstRelations2.STIEFDOCHTER,     ConstRelations2.STIEFKIND_PK}; 
    	
    	
    	int p_gen = -1;
    	int pu_gen = -1;
    	
    	for(int i = 0; i < Generation0.length; i++){    		
    		if(p.getRelationsToPKHolder().get(0).getContentOfDynamicData()  == Generation0[i]) p_gen = 0;
    		if(pu.getRelationsToPKHolder().get(0).getContentOfDynamicData() == Generation0[i]) pu_gen = 0;  		
    	}
    	
    	for(int i = 0; i < Generation1.length; i++){    		
    		if(p.getRelationsToPKHolder().get(0).getContentOfDynamicData()  == Generation1[i]) p_gen = 1;
    		if(pu.getRelationsToPKHolder().get(0).getContentOfDynamicData() == Generation1[i]) pu_gen = 1;  		
    	}
    	
    	for(int i = 0; i < Generation2.length; i++){    		
    		if(p.getRelationsToPKHolder().get(0).getContentOfDynamicData()  == Generation2[i]) p_gen = 2;
    		if(pu.getRelationsToPKHolder().get(0).getContentOfDynamicData() == Generation2[i]) pu_gen = 2;  		
    	}

    	
    	// If persons are from different generations, they cannot be the same person
    	
    	if(p_gen != pu_gen)
    		return -1;
    	

        //
        // Test if different family name
        //
        boolean familyNameOK = CheckFamilyName(p, pu);

        //
        // Test if different first firstname
        //

        boolean firstNameOK = CheckFirstName(p, pu);

        //
        // Test if different birth dates 
        //
        boolean birthDateOK = CheckBirthDate(p, pu);

    	//
    	// Test if different sex
    	//
    	
    	boolean sexOK = true;
    	if((p.getSex().equals("m") == true && pu.getSex().equals("v") == true) || (p.getSex().equals("v") == true && pu.getSex().equals("m") == true))
    		sexOK = false;


        // Extra checks


        if (familyNameOK && firstNameOK  && birthDateOK && sexOK) {
        	


            // Check if all first names are equal

            String[] names1 = p.getFirstName().split(" ");
            String[] names2 = pu.getFirstName().split(" ");

            if (!names1[0].equalsIgnoreCase(names2[0])){
                message(p.getKeyToRP(), "3101", names1[0], names2[0]);
            }
          
            if (names1.length >= 2 && names2.length >= 2) 
                if (!names1[1].equalsIgnoreCase(names2[1])) 
                    message(p.getKeyToRP(), "4119", names1[1], names2[1]);

            if (names1.length >= 3 && names2.length >= 3) 
                if (!names1[2].equalsIgnoreCase(names2[2])) 
                    message(p.getKeyToRP(), "4120", names1[2], names2[2]);

            return 0;

        }

        // Perform some checks 

        if (familyNameOK != true && firstNameOK == true && birthDateOK == true) {
            message(p.getKeyToRP(), "4121", p.getFamilyName(), pu.getFamilyName());
            return -1;
        }

        if (familyNameOK == true && firstNameOK == true && birthDateOK == true && sexOK != true) {
           message(p.getKeyToRP(), "4122", new Integer(pu.getKeyToPersons()).toString());
            return -1;
        }

        if (familyNameOK == true && firstNameOK == true && birthDateOK != true) {
            message(p.getKeyToRP(), "4126", p.getFamilyName(), p.getFirstName(), p.getDateOfBirth(), pu.getDateOfBirth());
            return -1;
        }


        return -1;
    }

    /**
     * This routine checks if the birth dates match
     * The following message numbers can be issued:
     * <p/>
     * 4106
     * 4107
     * 4108
     * 4110
     *
     * @param p
     * @param pu
     * @return
     */
    private static boolean CheckBirthDate(B2_ST p, B2_ST pu) {


        String date1 = p.getDateOfBirth();
        int day1 = (new Integer(date1.split("-")[0])).intValue();
        int month1 = (new Integer(date1.split("-")[1])).intValue();
        int year1 = (new Integer(date1.split("-")[2])).intValue();

        String date2 = pu.getDateOfBirth();
        int day2 = (new Integer(date2.split("-")[0])).intValue();
        int month2 = (new Integer(date2.split("-")[1])).intValue();
        int year2 = (new Integer(date2.split("-")[2])).intValue();


        if (date1.equals("00-00-0000") == true || date2.equals("00-00-0000") == true) // invalid dates
            return false;

        if (year1 == 0 || year2 == 0)
            return false;

        if (Utils.dateIsValid(day1, month1, year1) != 0 || Utils.dateIsValid(day2, month2, year2) != 0) {
            if (year1 > 1700 && year2 > 1700) {
                if (year1 == year2) {
                    message(p.getKeyToRP(), "4125");
                    return true;

                } else
                    return false;
            } else
                return false;
        }

        if (day1 != 0 && month1 != 0 && day2 != 0 && month2 != 0) {

            if (Math.abs(day1 - day2) > 1) // days differ significantly
                if (Math.abs(month1 - month2) != 0 || Math.abs(year1 - year2) != 0)
                    return false;

            if (Math.abs(month1 - month2) != 0) // months differ 
                if (Math.abs(day1 - day2) > 1 || Math.abs(year1 - year2) != 0)
                    return false;

            if (Math.abs(year1 - year2) != 0) { // years differ

                if (Math.abs(day1 - day2) > 1 || Math.abs(month1 - month2) != 0)
                    return false;
                else {
                    if (date1.substring(6, 8).equals(date2.substring(6, 8))) { // same century

                        if (Math.abs(year1 - year2) <= 2 || date1.substring(9, 10).equals(date2.substring(9, 10)))
                            ; // ok
                        else
                            return false;
                    } else
                        return false;
                }
            }
        }

        if (day1 != day2)
            message(p.getKeyToRP(), "4006", (new Integer(day1).toString()), (new Integer(day2).toString()));
        if (month1 != month2)
            message(p.getKeyToRP(), "4007", (new Integer(month1).toString()), (new Integer(month2).toString()));
        if (year1 != year2) {

            if (Math.abs(year1 - year2) <= 2)
                message(p.getKeyToRP(), "4008", (new Integer(year1).toString()), (new Integer(year2).toString()));
            else
                message(p.getKeyToRP(), "4010", (new Integer(year1).toString()), (new Integer(year2).toString()));

        }

        return true;
    }


    /**
     * This routine checks if the family names match
     * The following message numbers can be issued:
     * <p/>
     * 3101
     *
     * @param p
     * @param pu
     * @return
     */
    private static boolean CheckFamilyName(B2_ST p, B2_ST pu) {

        if (p.getFamilyName() == null || pu.getFamilyName() == null)
            return false;

        String name1 = p.getFamilyName().toLowerCase().trim();
        String name2 = pu.getFamilyName().toLowerCase().trim();


        if (name1 != null && name2 != null) {

            if (name1.length() > 0 && name2.length() > 0 && name1.charAt(0) != name2.charAt(0))  // first character different		
                return false;

            // replacements are applied before comparing the names

            name1 = name1.replaceAll("y", "ij");
            name2 = name2.replaceAll("ie", "ij");
            name1 = name1.replaceAll("y", "ij");
            name2 = name2.replaceAll("ie", "ij");

            name1 = name1.replaceAll("egt", "echt");
            name2 = name2.replaceAll("egt", "echt");

            name1 = name1.replaceAll("uys", "ist");
            name2 = name2.replaceAll("uys", "ist");

            int distance = Utils.LevenshteinDistance(name1, name2);

            if (distance > 2)  // greater than 2 not allowed
                return false;

            if (distance == 2 && name1.length() <= 5 && name2.length() <= 5)  // distance = 2 is allowed, but not for small strings
                return false;

        } else
            return false;



        return true;

    }

    /**
     * This routine checks if the first names match
     * The following message numbers can be issued:
     * <p/>
     * 3103
     *
     * @param p
     * @param pu
     * @param m
     * @return
     */
    private static boolean CheckFirstName(B2_ST p, B2_ST pu) {

        if (p.getFirstName() == null || pu.getFirstName() == null)
            return false;

        String name1 = p.getFirstName().split(" ")[0].toLowerCase().trim();
        String name2 = pu.getFirstName().split(" ")[0].toLowerCase().trim();


        if (name1 != null && name2 != null) {

            if (name1.length() > 0 && name2.length() > 0 && name1.charAt(0) != name2.charAt(0))  // first character different		
                return false;

            // replacements are applied before comparing the names

            name1 = name1.replaceAll("y", "ij");
            name2 = name2.replaceAll("ie", "ij");
            name1 = name1.replaceAll("y", "ij");
            name2 = name2.replaceAll("ie", "ij");

            name1 = name1.replaceAll("egt", "echt");
            name2 = name2.replaceAll("egt", "echt");

            name1 = name1.replaceAll("uys", "ist");
            name2 = name2.replaceAll("uys", "ist");

            int distance = Utils.LevenshteinDistance(name1, name2);

            if (distance > 2)  // greater than 2 not allowed
                return false;

            if (distance == 2 && name1.length() <= 5 && name2.length() <= 5)  // distance = 2 is allowed, but not for small strings
                return false;

        } else
            return false;

        return true;

    }
    
    
    private static boolean keyCheck( List<PkKnd> pkkndL,
    		List<PkBrp> pkBrpL,
    		List<PkHuw> pkhuwL,
    		List<PkEigknd> pkeigkndL,
    		List<PkAdres> pkadresL,
    		List<PkByz> pkbyzL,
    		List<P7> p7L,
    		List<P8> p8L
    		){
    	
    	Comparator<PkKnd> cpk = new Comparator<PkKnd>() {
			public int compare(PkKnd pkknd1, PkKnd pkknd2) {
				if (pkknd1.getIdnr() > pkknd2.getIdnr())
					return 1;
				else if (pkknd1.getIdnr() < pkknd2.getIdnr())
					return -1;
				return 0;
			}
		}; 
    	
		for (PkHuw b : pkhuwL){
			PkKnd pkn = new PkKnd();
			pkn.setIdnr(b.getIdnr());
			if (Collections.binarySearch(pkkndL, pkn, cpk) < 0) {
				message(b.getIdnr(), "7110", "" + b.getIdnr());
				return false;
			}
			;
		}
		
		for (PkEigknd b : pkeigkndL) {
			PkKnd pkn = new PkKnd();
			pkn.setIdnr(b.getIdnr());
			if (Collections.binarySearch(pkkndL, pkn, cpk) < 0) {
				message(b.getIdnr(), "7111", "" + b.getIdnr());
				return false;
			}
			;
		}
		
		for (PkByz b : pkbyzL) {
			PkKnd pkn = new PkKnd();
			pkn.setIdnr(b.getIdnr());
			if (Collections.binarySearch(pkkndL, pkn, cpk) < 0) {
				message(b.getIdnr(), "7112", "" + b.getIdnr());
				return false;
			}
			;
		}

		for (PkBrp b : pkBrpL) {
			PkKnd pkn = new PkKnd();
			pkn.setIdnr(b.getIdnr());
			if (Collections.binarySearch(pkkndL, pkn, cpk) < 0) {
				message(b.getIdnr(), "7113", "" + b.getIdnr());
				return false;
			}
			;
		}
		
		for (PkAdres b : pkadresL) {
			PkKnd pkn = new PkKnd();
			pkn.setIdnr(b.getIdnr());
			if (Collections.binarySearch(pkkndL, pkn, cpk) < 0) {
				message(b.getIdnr(), "7114", "" + b.getIdnr());
				return false;
			}
			;
		}
		
		for (P7 b : p7L) {
			PkKnd pkn = new PkKnd();
			pkn.setIdnr(b.getIdnr());
			if (Collections.binarySearch(pkkndL, pkn, cpk) < 0) {
				message(b.getIdnr(), "7115", "" + b.getIdnr());
				return false;
			}
			;
		}
		
		for (P8 b : p8L) {
			PkKnd pkn = new PkKnd();
			pkn.setIdnr(b.getIdnr());
			if (Collections.binarySearch(pkkndL, pkn, cpk) < 0) {
				message(b.getIdnr(), "7116", "" + b.getIdnr());
				return false;
			}
			;
		}
		

		
		
		
		
		
		
    	
    	/*
    	 * List<PkKnd> pkkndL = Utils.createObjects("nl.iisg.convertPK.PkKnd", inputDirectory);
        print("Read PKKND.DBF, " + pkkndL.size() + " rows");
        List<PkBrp> pkbrpL = Utils.createObjects("nl.iisg.convertPK.PkBrp", inputDirectory);
        print("Read PKBRP.DBF, " + pkbrpL.size() + " rows");
        List<PkHuw> pkhuwL = Utils.createObjects("nl.iisg.convertPK.PkHuw", inputDirectory);
        print("Read PKHUW.DBF, " + pkhuwL.size() + " rows");
        List<PkEigknd> pkeigkndL = Utils.createObjects("nl.iisg.convertPK.PkEigknd", inputDirectory);
        print("Read PKEIGKND.DBF, " + pkeigkndL.size() + " rows");
        List<PkAdres> pkadresL = Utils.createObjects("nl.iisg.convertPK.PkAdres", inputDirectory);
        print("Read PKADRES.DBF, " + pkadresL.size() + " rows");
        List<PkByz> pkbyzL = Utils.createObjects("nl.iisg.convertPK.PkByz", inputDirectory);
        print("Read PKBYZ.DBF, " + pkbyzL.size() + " rows");
        List<P7> p7L = Utils.createObjects("nl.iisg.convertPK.P7", inputDirectory);
        print("Read P7.DBF, " + p7L.size() + " rows");
        List<P8> p8L = Utils.createObjects("nl.iisg.convertPK.P8", inputDirectory);
        print("Read P8.DBF, " + p8L.size() + " rows");
    	 * 
    	 */
    	
    	return true;
    }
    
    
    
    private static void message(int idnr, String number, String... fills) {

        //print("Messagenr: " + number);

        Message m = new Message(number);

        m.setKeyToRP(idnr);
        m.save(fills);
    }


    public static int getPersonID() {
        return personID++;
    }

    public static void setPersonID(int personID) {
        StandardizePersonalCards.personID = personID;
    }


    public String getInputDirectory() {
        return inputDirectory;
    }


    public void setInputDirectory(String inputDirectory) {
        this.inputDirectory = inputDirectory;
    }

    public void setOut(DataOutputStream out) {
        StandardizePersonalCards.out = out;
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
