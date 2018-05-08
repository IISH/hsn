package iisg.nl.hsnmigrate;

import iisg.nl.hsnimport.Gebakte;

import iisg.nl.hsnimport.Gebbyz;
import iisg.nl.hsnimport.Gebgtg;
import iisg.nl.hsnimport.Gebkant;
import iisg.nl.hsnimport.Gebknd;
import iisg.nl.hsnimport.Gebvdr;
import iisg.nl.hsnimport.Huwafk;
import iisg.nl.hsnimport.Huwbyz;
import iisg.nl.hsnimport.Huweer;
import iisg.nl.hsnimport.Huwgtg;
import iisg.nl.hsnimport.Huwknd;
import iisg.nl.hsnimport.Huwvrknd;
import iisg.nl.hsnimport.Ovlagv;
import iisg.nl.hsnimport.Ovlbyz;
import iisg.nl.hsnimport.Ovlech;
import iisg.nl.hsnimport.Ovlknd;
import iisg.nl.hsnimport.PkKnd;
import iisg.nl.hsnimport.Stpb;
import iisg.nl.hsnimport.Plaats;
import iisg.nl.hsnimport.Beheer;
import iisg.nl.hsnlog.Log;
import nl.iisg.hsncommon.Common1;
import nl.iisg.ref.*;

import iisg.nl.hsnmigrate.StatView;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;



import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;

public class StandardizeCivilCertificates  implements Runnable {

	static ArrayList<ArrayList> all = new ArrayList<ArrayList>();

	String inputDirectory;
    static DataOutputStream out = null;
    static int count; 


	public StandardizeCivilCertificates(String inputDirectory){

		setInputDirectory(inputDirectory);

	}

    public StandardizeCivilCertificates(DataOutputStream out, String inputDirectory) {

        setInputDirectory(inputDirectory);

        setOut(out);

    }

    public void run() {

		String [] parms = {getInputDirectory()};

		main(parms);
    }

	public static void main(String[] args){
		
		

		File file  = new File("h:/sysout.log");
		if(false){

			try{
				PrintStream printStream = new PrintStream(new FileOutputStream(file));
				System.out.println("Output in h:/sysout.log");
				System.setOut(printStream);

			}
			catch(FileNotFoundException e){}
		}

		//System.out.println("Statistics...");
		//statistics();
		//System.exit(0);
		
        Runtime runtime = Runtime.getRuntime();

		
		String inputFiles =    args[0];

        print("\nCivil Certificates - Standardize      started\n");
        
        
        
        String [] requiredFiles = {"STPB.DBF", "GEBKND.DBF", "GEBGTG.DBF", "GEBVDR.DBF", "GEBAKTE.DBF", "GEBKANT.DBF", "GEBBYZ.DBF",
        		                   "HUWKND.DBF", "HUWVRKND.DBF", "HUWGTG.DBF", "HUWEER.DBF", "HUWAFK.DBF", "HUWBYZ.DBF",
        		                   "OVLKND.DBF", "OVLECH.DBF", "OVLAGV.DBF", "OVLBYZ.DBF",
        		                   "PLAATS.DBF", "BEHEER.DBF", "PKKND.DBF"};
        
        //String missingFile = Common1.nonExisitingFile(inputFiles, requiredFiles);
        //if(missingFile != null){
        //	print("Required file " + missingFile + " missing\n");
        //	return;
        //}
        

		for(int i = 0; i < CreateDBImport.tables.length; i++){
			all.add(new ArrayList());
		}

        print("Creating databases....");

		
	    createDB();
	    
        print("Reading and saving input files...");

	    if(!loadDBFTables(inputFiles)){
        	print("Required input DBF files not found (DBF or accdb) ");
        	 print("\nCivil Certificates - Standardize      finished    \n");
        	return;
	    }
	    	
		
        print("Reading Reference tables...");

		loadRefTables();

		setCount(0);
		
		print("Processing input files...");	
		
		for(int i = 0; i < 10; i++){
			
			loadTables(i);

			sortObjects();

			keyChecks();

			linkObjects();

			convert();
			
			
		}
		
		print("Processed " + getCount() + " IDNR's");

		statistics();

		print("Saving Reference tables...");
		Ref.finalise();
		print("Saving Messages table...");
		Log.finalise();

		all.clear();
		
        print("\nCivil Certificates - Standardize      finished    \n");


	}

	private static void createDB(){

		try{

			EntityManagerFactory factory1 = Persistence.createEntityManagerFactory("import");
			EntityManager em = factory1.createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNativeQuery(CreateDBImport.stpb);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.gebakte);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.gebbyz);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.gebgtg);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.gebkant);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.gebknd);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.gebvdr);
			query.executeUpdate();


			query = em.createNativeQuery(CreateDBImport.ovlagv);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.ovlbyz);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.ovlech);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.ovlknd);
			query.executeUpdate();

			query = em.createNativeQuery(CreateDBImport.huwknd);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.huwafk);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.huweer);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.huwgtg);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.huwvrknd);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.huwbyz);
			query.executeUpdate();

			query = em.createNativeQuery(CreateDBImport.plaats);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.beheer);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.pkknd);
			query.executeUpdate();

			em.getTransaction().commit();
            em.clear();

			//if(1==1) return;
			em.getTransaction().begin();
			
			query = em.createNativeQuery(CreateDBImport.stpb_truncate);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.gebakte_truncate);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.gebbyz_truncate);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.gebgtg_truncate);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.gebkant_truncate);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.gebknd_truncate);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.gebvdr_truncate);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.ovlagv_truncate);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.ovlbyz_truncate);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.ovlech_truncate);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.ovlknd_truncate);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.huwknd_truncate);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.huwafk_truncate);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.huweer_truncate);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.huwgtg_truncate);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.huwvrknd_truncate);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.huwbyz_truncate);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.plaats_truncate);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.beheer_truncate);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBImport.pkknd_truncate);
			query.executeUpdate();

			em.getTransaction().commit();
            em.clear();

		}
		catch(Exception e) {
			print(e.getMessage());
		}


		try{
			EntityManagerFactory factory1 = Persistence.createEntityManagerFactory("nieuw");
			EntityManager em = factory1.createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNativeQuery(CreateDBNieuw.B0);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.B1);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.B2);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.B3);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.B4);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.B5);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.B6);
			query.executeUpdate();

			query = em.createNativeQuery(CreateDBNieuw.D1);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.D2);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.D3);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.D4);
			query.executeUpdate();

			query = em.createNativeQuery(CreateDBNieuw.M0);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.M1);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.M2);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.M3);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.M4);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.M5);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.M6);
			query.executeUpdate();
			
			query = em.createNativeQuery(CreateDBNieuw.A1);
			query.executeUpdate();



			query = em.createNativeQuery(CreateDBNieuw.A1_TRUNCATE);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.B0_TRUNCATE);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.B1_TRUNCATE);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.B2_TRUNCATE);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.B3_TRUNCATE);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.B4_TRUNCATE);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.B5_TRUNCATE);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.B6_TRUNCATE);
			query.executeUpdate();

			query = em.createNativeQuery(CreateDBNieuw.D1_TRUNCATE);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.D2_TRUNCATE);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.D3_TRUNCATE);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.D4_TRUNCATE);
			query.executeUpdate();

			query = em.createNativeQuery(CreateDBNieuw.M0_TRUNCATE);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.M1_TRUNCATE);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.M2_TRUNCATE);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.M3_TRUNCATE);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.M4_TRUNCATE);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.M5_TRUNCATE);
			query.executeUpdate();
			query = em.createNativeQuery(CreateDBNieuw.M6_TRUNCATE);
			query.executeUpdate();
			
			query = em.createNativeQuery(CreateDBNieuw.A1_TRUNCATE);
			query.executeUpdate();


			em.getTransaction().commit();
            em.clear();

		}
		catch(Exception e) {
			print(e.getMessage());
		}

		
		try{

			EntityManagerFactory factory1 = Persistence.createEntityManagerFactory("log_create");
			EntityManager em1 = factory1.createEntityManager();
			em1.getTransaction().begin();

			Query query = em1.createNativeQuery(CreateDBLog.log);
			query.executeUpdate();
			query = em1.createNativeQuery(CreateDBLog.logSummary);
			query.executeUpdate();


			query = em1.createNativeQuery(CreateDBLog.log_truncate);
			query.executeUpdate();
			query = em1.createNativeQuery(CreateDBLog.logSummary_truncate);
			query.executeUpdate();

			query = em1.createNativeQuery(Statistics.statView);
			query.executeUpdate();


			em1.getTransaction().commit();
            em1.clear();

			em1.close();


		}
		catch(Exception e) {
			print(e.getMessage());
		}




		
		// Also create the reference tables

		Ref.createRefTables();


	}

	private static boolean loadDBFTables(String inputFiles){
		
		
	
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("import");
		EntityManager em = emf.createEntityManager();
		
		
		em.getTransaction().begin();
		List<Stpb> stpbL = Utils.createObjects("iisg.nl.hsnimport.Stpb", inputFiles);
		if(stpbL == null) stpbL = Utils.createObjects2("iisg.nl.hsnimport.Stpb", inputFiles);
		if(stpbL == null) return false;
		
		for(Stpb g: stpbL){
			em.persist(g);
		}
		em.getTransaction().commit();
		em.clear();
		print("Read STPB.DBF, " + stpbL.size() + " rows");
		stpbL = null; // because we are going to read again from the SQL tables, in smaller portions

		//
		
		em = emf.createEntityManager();
		em.getTransaction().begin();		
		List<Gebakte> gebakteL = Utils.createObjects("iisg.nl.hsnimport.Gebakte", inputFiles);
		if(gebakteL == null) gebakteL = Utils.createObjects2("iisg.nl.hsnimport.Gebakte", inputFiles);
		if(gebakteL == null) return false;
		
		for(Gebakte g: gebakteL){
			em.persist(g);
		}
		em.getTransaction().commit();
		em.clear();
		print("Read GEBAKTE.DBF, " + gebakteL.size() + " rows");
		gebakteL = null;
		
		//

		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Gebbyz>   gebbyzL = Utils.createObjects("iisg.nl.hsnimport.Gebbyz", inputFiles);
		if(gebbyzL == null) gebbyzL = Utils.createObjects2("iisg.nl.hsnimport.Gebbyz", inputFiles);
		if(gebbyzL == null) return false;
		
		for(Gebbyz g: gebbyzL){
			em.persist(g);
		}
		em.getTransaction().commit();
		em.clear();
		print("Read GEBBYZ.DBF, " + gebbyzL.size() + " rows");
		gebbyzL = null;
		
		//
		
		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Gebgtg>   gebgtgL = Utils.createObjects("iisg.nl.hsnimport.Gebgtg", inputFiles);
		if(gebgtgL == null) gebgtgL = Utils.createObjects2("iisg.nl.hsnimport.Gebgtg", inputFiles);
		if(gebgtgL == null) return false;
		
		for(Gebgtg g: gebgtgL){
			em.persist(g);
		}
		em.getTransaction().commit();
		em.clear();
		print("Read GEBGTG.DBF, " + gebgtgL.size() + " rows");
		gebgtgL = null;
		
		//

		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Gebkant>   gebkantL = Utils.createObjects("iisg.nl.hsnimport.Gebkant", inputFiles);
		if(gebkantL == null) gebkantL = Utils.createObjects2("iisg.nl.hsnimport.Gebkant", inputFiles);
		if(gebkantL == null) return false;
		
		for(Gebkant g: gebkantL){
			em.persist(g);
		}
		em.getTransaction().commit();
		em.clear();
		print("Read GEBKANT.DBF, " + gebkantL.size() + " rows");
		gebkantL = null;
		
		//

		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Gebknd>   gebkndL = Utils.createObjects("iisg.nl.hsnimport.Gebknd", inputFiles);
		if(gebkndL == null) gebkndL = Utils.createObjects2("iisg.nl.hsnimport.Gebknd", inputFiles);
		if(gebkndL == null) return false;
		
		for(Gebknd g: gebkndL){
			em.persist(g);
		}
		em.getTransaction().commit();
		em.clear();
		print("Read GEBKND.DBF, " + gebkndL.size() + " rows");
		gebkndL = null;
		
		//

		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.clear();
		List<Gebvdr>   gebvdrL = Utils.createObjects("iisg.nl.hsnimport.Gebvdr", inputFiles);
		if(gebvdrL == null) gebvdrL = Utils.createObjects2("iisg.nl.hsnimport.Gebvdr", inputFiles);
		if(gebvdrL == null) return false;
		
		for(Gebvdr g: gebvdrL){
			em.persist(g);
		}
		em.getTransaction().commit();
		em.clear();
		print("Read GEBVDR.DBF, " + gebvdrL.size() + " rows");
		gebvdrL = null;
		
		//
		
		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Ovlagv>   ovlagvL = Utils.createObjects("iisg.nl.hsnimport.Ovlagv", inputFiles);
		if(ovlagvL == null) ovlagvL = Utils.createObjects2("iisg.nl.hsnimport.Ovlagv", inputFiles);
		if(ovlagvL == null) return false;
		
		for(Ovlagv g: ovlagvL){
			em.persist(g);
		}
		em.getTransaction().commit();
		em.clear();
		print("Read OVLAGV.DBF, " + ovlagvL.size() + " rows");
		ovlagvL = null;
		
		//

		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Ovlbyz>   ovlbyzL = Utils.createObjects("iisg.nl.hsnimport.Ovlbyz", inputFiles);
		if(ovlbyzL == null) ovlbyzL = Utils.createObjects2("iisg.nl.hsnimport.Ovlbyz", inputFiles);
		if(ovlbyzL == null) return false;
		
		for(Ovlbyz g: ovlbyzL){
			em.persist(g);
		}
		em.getTransaction().commit();
		em.clear();
		print("Read OVLBYZ.DBF, " + ovlbyzL.size() + " rows");
		ovlbyzL = null;

		//
		
		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Ovlech>   ovlechL = Utils.createObjects("iisg.nl.hsnimport.Ovlech", inputFiles);
		if(ovlechL == null) ovlechL = Utils.createObjects2("iisg.nl.hsnimport.Ovlech", inputFiles);
		if(ovlechL == null) return false;
		
		for(Ovlech g: ovlechL){
			em.persist(g);
		}
		em.getTransaction().commit();
		em.clear();
		print("Read OVLECH.DBF, " + ovlechL.size() + " rows");
		ovlechL = null;

		//
		
		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Ovlknd>   ovlkndL = Utils.createObjects("iisg.nl.hsnimport.Ovlknd", inputFiles);
		if(ovlkndL == null) ovlkndL = Utils.createObjects2("iisg.nl.hsnimport.Ovlknd", inputFiles);
		if(ovlkndL == null) return false;
		
		for(Ovlknd g: ovlkndL){
			em.persist(g);
		}
		em.getTransaction().commit();
		em.clear();
		print("Read OVLKND.DBF, " + ovlkndL.size() + " rows");
		ovlkndL = null;
		
		//

		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Huwknd>   huwkndL = Utils.createObjects("iisg.nl.hsnimport.Huwknd", inputFiles);
		if(huwkndL == null) huwkndL = Utils.createObjects2("iisg.nl.hsnimport.Huwknd", inputFiles);
		if(huwkndL == null) return false;
		
		for(Huwknd g: huwkndL){
			em.persist(g);
		}
		em.getTransaction().commit();
		em.clear();
		print("Read HUWKND.DBF, " + huwkndL.size() + " rows");
		huwkndL = null;

		//
		
		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Huwafk>   huwafkL = Utils.createObjects("iisg.nl.hsnimport.Huwafk", inputFiles);
		if(huwafkL == null) huwafkL = Utils.createObjects2("iisg.nl.hsnimport.Huwafk", inputFiles);
		if(huwafkL == null) return false;
		
		for(Huwafk g: huwafkL){
			em.persist(g);
		}
		em.getTransaction().commit();
		em.clear();
		print("Read HUWAFK.DBF, " + huwafkL.size() + " rows");
		huwafkL = null;
		
		//

		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Huweer>   huweerL = Utils.createObjects("iisg.nl.hsnimport.Huweer", inputFiles);
		if(huweerL == null) huweerL  = Utils.createObjects2("iisg.nl.hsnimport.Huweer", inputFiles);
		if(huweerL == null) return false;
		
		for(Huweer g: huweerL){
			em.persist(g);
		}
		em.getTransaction().commit();
		em.clear();
		print("Read HUWEER.DBF, " + huweerL.size() + " rows");
		huweerL = null;
		
		//

		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Huwgtg>   huwgtgL = Utils.createObjects("iisg.nl.hsnimport.Huwgtg", inputFiles);
		if(huwgtgL == null) huwgtgL = Utils.createObjects2("iisg.nl.hsnimport.Huwgtg", inputFiles);
		if(huwgtgL == null) return false;
		
		for(Huwgtg g: huwgtgL){
			em.persist(g);
		}
		em.getTransaction().commit();
		em.clear();
		print("Read HUWGTG.DBF, " + huwgtgL.size() + " rows");
		huwgtgL = null;
		
		//

		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Huwvrknd>   huwvrkndL = Utils.createObjects("iisg.nl.hsnimport.Huwvrknd", inputFiles);
		if(huwvrkndL == null) huwvrkndL = Utils.createObjects2("iisg.nl.hsnimport.Huwvrknd", inputFiles);
		if(huwvrkndL == null) return false;
		
		for(Huwvrknd g: huwvrkndL){
			em.persist(g);
		}
		em.getTransaction().commit();
		em.clear();
		print("Read HUWVRKND.DBF, " + huwvrkndL.size() + " rows");
		huwvrkndL = null;
		
		//

		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Huwbyz>   huwbyzL = Utils.createObjects("iisg.nl.hsnimport.Huwbyz", inputFiles);
		if(huwbyzL == null) huwbyzL = Utils.createObjects2("iisg.nl.hsnimport.Huwbyz", inputFiles);
		if(huwbyzL == null) return false;
		
		for(Huwbyz g: huwbyzL){
			em.persist(g);
		}
		em.getTransaction().commit();
		em.clear();
		print("Read HUWBYZ.DBF, " + huwbyzL.size() + " rows");
		huwbyzL = null;

		// Load 3 tables only used by statistics
		
		//if(1==1) return;

		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<PkKnd>   pkkndL = Utils.createObjects("iisg.nl.hsnimport.PkKnd", inputFiles);
		if(pkkndL == null)   pkkndL = Utils.createObjects2("iisg.nl.hsnimport.PkKnd", inputFiles);
		if(pkkndL == null) return false;
		
		for(PkKnd g: pkkndL){
			em.persist(g);
		}
		em.getTransaction().commit();
		em.clear();
		print("Read PKKND.DBF, " + pkkndL.size() + " rows");
		pkkndL = null;
		
		//

		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Plaats>   plaatsL = Utils.createObjects("iisg.nl.hsnimport.Plaats", inputFiles);
		if(plaatsL == null) plaatsL = Utils.createObjects2("iisg.nl.hsnimport.Plaats", inputFiles);
		if(plaatsL == null) return false;
		
		for(Plaats g: plaatsL){
			em.persist(g);
		}
		em.getTransaction().commit();
		em.clear();
		print("Read PLAATS.DBF, " + plaatsL.size() + " rows");
		plaatsL = null;

		//
		
		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Beheer>   beheerL = Utils.createObjects("iisg.nl.hsnimport.Beheer", inputFiles);
		if(beheerL == null)  beheerL = Utils.createObjects2("iisg.nl.hsnimport.Beheer", inputFiles);
		if(beheerL == null) return false;
		
		for(Beheer g: beheerL){
			em.persist(g);
		}
		em.getTransaction().commit();
		em.clear();
		print("Read BEHEER.DBF, " + beheerL.size() + " rows");
		beheerL = null;
		
		return true;

	}
	

	private static void loadTables(int i){
		

		for(ArrayList a: all) // remove references to previous data batch
			a.clear();
		
		int count = 0;
		EntityManager emi = Utils.getEm_import();

		for(String table1: CreateDBImport.tables1){
			//Query q = emi.createQuery("select a from " + table1 + " a");
			Query q = emi.createQuery("select a from " + table1 + " a where a.idnr like '%" + i +"'");

			ArrayList s = all.get(count);
			s.addAll(q.getResultList());
			count++;
			//if(1==1) System.exit(9);
		}

		
	}


	private static void loadRefTables(){
		
		Ref.createRefTables();

		Ref.loadFamName();
		Ref.loadFirstName();
		Ref.loadPrefix();
		Ref.loadLocation();
		Ref.loadProfession();
		Ref.loadHousenumberaddition();
		Ref.loadHousenumber();
		Ref.loadMunicipality();
		Ref.loadAddress();

	}

	private static void sortObjects(){

		ArrayList<Stpb>    stpbL    = all.get(0);
		ArrayList<Gebakte> gebakteL = all.get(1);
		ArrayList<Gebbyz>  gebbyzL  = all.get(2);
		ArrayList<Gebgtg>  gebgtgL  = all.get(3);
		ArrayList<Gebkant> gebkantL = all.get(4);
		ArrayList<Gebknd>  gebkndL  = all.get(5);
		ArrayList<Gebvdr>  gebvdrL  = all.get(6);

		ArrayList<Ovlagv>  ovlagvL  = all.get(7);
		ArrayList<Ovlbyz>  ovlbyzL  = all.get(8);
		ArrayList<Ovlech>  ovlechL  = all.get(9);
		ArrayList<Ovlknd>  ovlkndL  = all.get(10);

		ArrayList<Huwknd>  huwkndL  = all.get(11);
		ArrayList<Huwafk>  huwafkL  = all.get(12);
		ArrayList<Huweer>  huweerL  = all.get(13);
		ArrayList<Huwgtg>  huwgtgL  = all.get(14);
		ArrayList<Huwvrknd> huwvrkndL  = all.get(15);
		ArrayList<Huwbyz>  huwbyzL  = all.get(16);




		Collections.sort(stpbL, new Comparator<Stpb>()
				{public int compare(Stpb stpb1, Stpb stpb2){
						if     (stpb1.getIdnr() >  stpb2.getIdnr()) return  1;
						else if(stpb1.getIdnr() <  stpb2.getIdnr()) return -1;
						return 0;}});

		Collections.sort(gebkndL, new Comparator<Gebknd>()
				{public int compare(Gebknd gebknd1, Gebknd gebknd2){
						if     (gebknd1.getIdnr() >  gebknd2.getIdnr()) return  1;
						else if(gebknd1.getIdnr() <  gebknd2.getIdnr()) return -1;
						return 0;}});

		Collections.sort(gebgtgL, new Comparator<Gebgtg>()
				{public int compare(Gebgtg gebgtg1, Gebgtg gebgtg2){
						if     (gebgtg1.getIdnr() >  gebgtg2.getIdnr()) return  1;
						else if(gebgtg1.getIdnr() <  gebgtg2.getIdnr()) return -1;
						if     (gebgtg1.getVlgnrgt() > gebgtg2.getVlgnrgt()) return 1; 
						else if(gebgtg1.getVlgnrgt() < gebgtg2.getVlgnrgt()) return -1; 
						return 0;}});

		Collections.sort(gebbyzL, new Comparator<Gebbyz>()
				{public int compare(Gebbyz gebbyz1, Gebbyz gebbyz2){
						if     (gebbyz1.getIdnr() >  gebbyz2.getIdnr()) return  1;
						else if(gebbyz1.getIdnr() <  gebbyz2.getIdnr()) return -1;
						if     (gebbyz1.getByznr() > gebbyz2.getByznr()) return 1; 
						else if(gebbyz1.getByznr() < gebbyz2.getByznr()) return -1; 

						return 0;}});

		Collections.sort(gebkantL, new Comparator<Gebkant>()
				{public int compare(Gebkant gebkant1, Gebkant gebkant2){
						if     (gebkant1.getIdnr() >  gebkant2.getIdnr()) return  1;
						else if(gebkant1.getIdnr() <  gebkant2.getIdnr()) return -1;
						if     (gebkant1.getKanttype() >  gebkant2.getKanttype()) return  1;
						else if(gebkant1.getKanttype() <  gebkant2.getKanttype()) return -1;
						return 0;}});

		Collections.sort(gebvdrL, new Comparator<Gebvdr>()
				{public int compare(Gebvdr gebvdr1, Gebvdr gebvdr2){
						if     (gebvdr1.getIdnr() >  gebvdr2.getIdnr()) return  1;
						else if(gebvdr1.getIdnr() <  gebvdr2.getIdnr()) return -1;
						return 0;}});

		Collections.sort(gebakteL, new Comparator<Gebakte>()
				{public int compare(Gebakte gebakte1, Gebakte gebakte2){
						if     (gebakte1.getIdnr() >  gebakte2.getIdnr()) return  1;
						else if(gebakte1.getIdnr() <  gebakte2.getIdnr()) return -1;
						return 0;}});

		Collections.sort(ovlagvL, new Comparator<Ovlagv>()
				{public int compare(Ovlagv ovlagv1, Ovlagv ovlagv2){
						if     (ovlagv1.getIdnr() >  ovlagv2.getIdnr()) return  1;
						else if(ovlagv1.getIdnr() <  ovlagv2.getIdnr()) return -1;
						if     (ovlagv1.getVlgnrag() > ovlagv2.getVlgnrag()) return 1;
						else if(ovlagv1.getVlgnrag() < ovlagv2.getVlgnrag()) return -1;
						return 0;}});

		Collections.sort(ovlbyzL, new Comparator<Ovlbyz>()
				{public int compare(Ovlbyz ovlbyz1, Ovlbyz ovlbyz2){
						if     (ovlbyz1.getIdnr() >  ovlbyz2.getIdnr()) return  1;
						else if(ovlbyz1.getIdnr() <  ovlbyz2.getIdnr()) return -1;
						if     (ovlbyz1.getByznr() > ovlbyz2.getByznr()) return 1; 
						else if(ovlbyz1.getByznr() < ovlbyz2.getByznr()) return -1;
						return 0;}});

		Collections.sort(ovlechL, new Comparator<Ovlech>()
				{public int compare(Ovlech ovlech1, Ovlech ovlech2){
						if     (ovlech1.getIdnr() >  ovlech2.getIdnr()) return  1;
						else if(ovlech1.getIdnr() <  ovlech2.getIdnr()) return -1;
						if     (ovlech1.getVlgech() > ovlech2.getVlgech()) return 1;
						else if(ovlech1.getVlgech() < ovlech2.getVlgech()) return -1;
						return 0;}});

		Collections.sort(ovlkndL, new Comparator<Ovlknd>()
				{public int compare(Ovlknd ovlknd1, Ovlknd ovlknd2){
						if     (ovlknd1.getIdnr() >  ovlknd2.getIdnr()) return  1;
						else if(ovlknd1.getIdnr() <  ovlknd2.getIdnr()) return -1;
						return 0;}});

		Collections.sort(huwkndL, new Comparator<Huwknd>()
				{public int compare(Huwknd huwknd1, Huwknd huwknd2){
						if     (huwknd1.getIdnr() >  huwknd2.getIdnr()) return  1;
						else if(huwknd1.getIdnr() <  huwknd2.getIdnr()) return -1;
						if     (huwknd1.getHjaar() >  huwknd2.getHjaar()) return  1;
						else if(huwknd1.getHjaar() <  huwknd2.getHjaar()) return -1;
						if     (huwknd1.getHmaand() >  huwknd2.getHmaand()) return  1;
						else if(huwknd1.getHmaand() <  huwknd2.getHmaand()) return -1;
						if     (huwknd1.getHdag()   >  huwknd2.getHdag()) return  1;
						else if(huwknd1.getHdag() <  huwknd2.getHdag()) return -1;

						return 0;}});

		Collections.sort(huwafkL, new Comparator<Huwafk>()
				{public int compare(Huwafk huwafk1, Huwafk huwafk2){
						if     (huwafk1.getIdnr() >  huwafk2.getIdnr()) return  1;
						else if(huwafk1.getIdnr() <  huwafk2.getIdnr()) return -1;
						if     (huwafk1.getHjaar() >  huwafk2.getHjaar()) return  1;
						else if(huwafk1.getHjaar() <  huwafk2.getHjaar()) return -1;
						if     (huwafk1.getHmaand() >  huwafk2.getHmaand()) return  1;
						else if(huwafk1.getHmaand() <  huwafk2.getHmaand()) return -1;
						if     (huwafk1.getHdag()   >  huwafk2.getHdag()) return  1;
						else if(huwafk1.getHdag() <  huwafk2.getHdag()) return -1;
						if     (huwafk1.getHwaknr()   >  huwafk2.getHwaknr()) return  1;
						else if(huwafk1.getHwaknr() <  huwafk2.getHwaknr()) return -1;

						return 0;}});

		Collections.sort(huweerL, new Comparator<Huweer>()
				{public int compare(Huweer huweer1, Huweer huweer2){
						if     (huweer1.getIdnr() >  huweer2.getIdnr()) return  1;
						else if(huweer1.getIdnr() <  huweer2.getIdnr()) return -1;
						if     (huweer1.getHjaar() >  huweer2.getHjaar()) return  1;
						else if(huweer1.getHjaar() <  huweer2.getHjaar()) return -1;
						if     (huweer1.getHmaand() >  huweer2.getHmaand()) return  1;
						else if(huweer1.getHmaand() <  huweer2.getHmaand()) return -1;
						if     (huweer1.getHdag()   >  huweer2.getHdag()) return  1;
						else if(huweer1.getHdag() <  huweer2.getHdag()) return -1;
						if     (huweer1.getVlgnreh()   >  huweer2.getVlgnreh()) return  1;
						else if(huweer1.getVlgnreh() <  huweer2.getVlgnreh()) return -1;

						return 0;}});

		Collections.sort(huwgtgL, new Comparator<Huwgtg>()
				{public int compare(Huwgtg huwgtg1, Huwgtg huwgtg2){
						if     (huwgtg1.getIdnr() >  huwgtg2.getIdnr()) return  1;
						else if(huwgtg1.getIdnr() <  huwgtg2.getIdnr()) return -1;
						if     (huwgtg1.getHjaar() >  huwgtg2.getHjaar()) return  1;
						else if(huwgtg1.getHjaar() <  huwgtg2.getHjaar()) return -1;
						if     (huwgtg1.getHmaand() >  huwgtg2.getHmaand()) return  1;
						else if(huwgtg1.getHmaand() <  huwgtg2.getHmaand()) return -1;
						if     (huwgtg1.getHdag()   >  huwgtg2.getHdag()) return  1;
						else if(huwgtg1.getHdag() <  huwgtg2.getHdag()) return -1;
						if     (huwgtg1.getVlgnrgt()   >  huwgtg2.getVlgnrgt()) return  1;
						else if(huwgtg1.getVlgnrgt() <  huwgtg2.getVlgnrgt()) return -1;

						return 0;}});

		Collections.sort(huwvrkndL, new Comparator<Huwvrknd>()
				{public int compare(Huwvrknd huwvrknd1, Huwvrknd huwvrknd2){
						if     (huwvrknd1.getIdnr() >  huwvrknd2.getIdnr()) return  1;
						else if(huwvrknd1.getIdnr() <  huwvrknd2.getIdnr()) return -1;
						if     (huwvrknd1.getHjaar() >  huwvrknd2.getHjaar()) return  1;
						else if(huwvrknd1.getHjaar() <  huwvrknd2.getHjaar()) return -1;
						if     (huwvrknd1.getHmaand() >  huwvrknd2.getHmaand()) return  1;
						else if(huwvrknd1.getHmaand() <  huwvrknd2.getHmaand()) return -1;
						if     (huwvrknd1.getHdag()   >  huwvrknd2.getHdag()) return  1;
						else if(huwvrknd1.getHdag() <  huwvrknd2.getHdag()) return -1;
						if     (huwvrknd1.getVlgnrvk()   >  huwvrknd2.getVlgnrvk()) return  1;
						else if(huwvrknd1.getVlgnrvk() <  huwvrknd2.getVlgnrvk()) return -1;

						return 0;}});

		Collections.sort(huwbyzL, new Comparator<Huwbyz>()
				{public int compare(Huwbyz huwbyz1, Huwbyz huwbyz2){
						if     (huwbyz1.getIdnr() >  huwbyz2.getIdnr()) return  1;
						else if(huwbyz1.getIdnr() <  huwbyz2.getIdnr()) return -1;
						if     (huwbyz1.getHjaar() >  huwbyz2.getHjaar()) return  1;
						else if(huwbyz1.getHjaar() <  huwbyz2.getHjaar()) return -1;
						if     (huwbyz1.getHmaand() >  huwbyz2.getHmaand()) return  1;
						else if(huwbyz1.getHmaand() <  huwbyz2.getHmaand()) return -1;
						if     (huwbyz1.getHdag() >  huwbyz2.getHdag()) return  1;
						else if(huwbyz1.getHdag() <  huwbyz2.getHdag()) return -1;
						if     (huwbyz1.getByznr() >  huwbyz2.getByznr()) return  1;
						else if(huwbyz1.getByznr() <  huwbyz2.getByznr()) return -1;
						return 0;}});



	}
	private static void linkObjects(){

		ArrayList<Stpb>    stpbL    = all.get(0);
		ArrayList<Gebakte> gebakteL = all.get(1);
		ArrayList<Gebbyz>  gebbyzL  = all.get(2);
		ArrayList<Gebgtg>  gebgtgL  = all.get(3);
		ArrayList<Gebkant> gebkantL = all.get(4);
		ArrayList<Gebknd>  gebkndL  = all.get(5);
		ArrayList<Gebvdr>  gebvdrL  = all.get(6);

		ArrayList<Ovlagv>  ovlagvL  = all.get(7);
		ArrayList<Ovlbyz>  ovlbyzL  = all.get(8);
		ArrayList<Ovlech>  ovlechL  = all.get(9);
		ArrayList<Ovlknd>  ovlkndL  = all.get(10);

		ArrayList<Huwknd>  huwkndL  = all.get(11);
		ArrayList<Huwafk>  huwafkL  = all.get(12);
		ArrayList<Huweer>  huweerL  = all.get(13);
		ArrayList<Huwgtg>  huwgtgL  = all.get(14);
		ArrayList<Huwvrknd> huwvrkndL  = all.get(15);
		ArrayList<Huwbyz>  huwbyzL  = all.get(16);



		int i_gebakte = 0;
		int i_gebknd  = 0;
		int i_gebgtg  = 0;
		int i_gebbyz  = 0;
		int i_gebkant = 0;
		int i_gebvdr  = 0;

		int i_ovlknd = 0;
		int i_ovlech = 0;
		int i_ovlagv = 0;
		int i_ovlbyz = 0;

		int i_huwknd = 0;
		int i_huwafk = 0;
		int i_huweer = 0;
		int i_huwgtg = 0;
		int i_huwvrknd = 0;
		int i_huwbyz = 0;

		boolean g = false;
		boolean o = false;
		boolean h = false;

		int c = 0;
		int ig =0;
		int d = 0;

		int igo = 0;
		int igh = 0;
		int igho = 0;
		int iga = 0;
		int i_skip = 0;

		for(Stpb stpb: stpbL){

			g = false;
			o = false;
			h = false;
			c++;

			// Birth records

			while(i_gebknd < gebkndL.size() && gebkndL.get(i_gebknd).getIdnr() < stpb.getIdnr()){
				//Utils.message(300100, gebkndL.get(i_gebknd).getIdnr(), "HSN_CIVREC_ORG", "Gebknd");
				i_gebknd++;
				i_skip++;

			}


			while(i_gebknd < gebkndL.size() && gebkndL.get(i_gebknd).getIdnr() == stpb.getIdnr()){
				g = true;
				d++;
				stpb.getGebkndL().add(gebkndL.get(i_gebknd));
				gebkndL.get(i_gebknd).setStpb(stpb);

				// Gebgtg

				while(i_gebgtg < gebgtgL.size() && gebgtgL.get(i_gebgtg).getIdnr() < stpb.getIdnr())
					i_gebgtg++;

				while(i_gebgtg < gebgtgL.size() && gebgtgL.get(i_gebgtg).getIdnr() == stpb.getIdnr()){
					gebkndL.get(i_gebknd).getGebgtgL().add(gebgtgL.get(i_gebgtg));
					gebgtgL.get(i_gebgtg).setGebknd(gebkndL.get(i_gebknd));

					i_gebgtg++;

				}

				// Gebbyz
				while(i_gebbyz < gebbyzL.size() && gebbyzL.get(i_gebbyz).getIdnr() < stpb.getIdnr())
					i_gebbyz++;


				while(i_gebbyz < gebbyzL.size() && gebbyzL.get(i_gebbyz).getIdnr() == stpb.getIdnr()){
					gebkndL.get(i_gebknd).getGebbyzL().add(gebbyzL.get(i_gebbyz));
					gebbyzL.get(i_gebbyz).setGebknd(gebkndL.get(i_gebknd));
					//print("          " + gebbyzL.get(i_gebbyz));

					i_gebbyz++;
				}

				// Gebkant

				while(i_gebkant < gebkantL.size() && gebkantL.get(i_gebkant).getIdnr() < stpb.getIdnr())
					i_gebkant++;

				while(i_gebkant < gebkantL.size() && gebkantL.get(i_gebkant).getIdnr() == stpb.getIdnr()){
					gebkndL.get(i_gebknd).getGebkantL().add(gebkantL.get(i_gebkant));
					gebkantL.get(i_gebkant).setGebknd(gebkndL.get(i_gebknd));

					i_gebkant++;
				}

				// Gebvdr

				while(i_gebvdr < gebvdrL.size() && gebvdrL.get(i_gebvdr).getIdnr() < stpb.getIdnr())
					i_gebvdr++;

				while(i_gebvdr < gebvdrL.size() && gebvdrL.get(i_gebvdr).getIdnr() == stpb.getIdnr()){
					gebkndL.get(i_gebknd).setGebvdr(gebvdrL.get(i_gebvdr));
					gebvdrL.get(i_gebvdr).setGebknd(gebkndL.get(i_gebknd));

					i_gebvdr++;
				}

				// Marriage records


				// Huwknd

				while(i_huwknd < huwkndL.size() && huwkndL.get(i_huwknd).getIdnr() < stpb.getIdnr()){
					Utils.message(300105, huwkndL.get(i_huwknd).getIdnr(), 0, "HSN_CIVREC_ORG", "Huwknd");
					i_huwknd++;
				}

				while(i_huwknd < huwkndL.size() && huwkndL.get(i_huwknd).getIdnr() == stpb.getIdnr()){
					
					h = true;
					gebkndL.get(i_gebknd).getHuwkndL().add(huwkndL.get(i_huwknd));
					huwkndL.get(i_huwknd).setGebknd(gebkndL.get(i_gebknd));
					
					// Huwafk

					while(i_huwafk < huwafkL.size() && huwafkL.get(i_huwafk).getIdnr() < stpb.getIdnr()){
						//System.out.println("Huwafk skipping, idnr = " + huwafkL.get(i_huwafk).getIdnr());
						i_huwafk++;
					}

					while(i_huwafk < huwafkL.size() && huwafkL.get(i_huwafk).getIdnr() == stpb.getIdnr() &&
							huwafkL.get(i_huwafk).getHjaar() == huwkndL.get(i_huwknd).getHjaar() &&
							huwafkL.get(i_huwafk).getHmaand() == huwkndL.get(i_huwknd).getHmaand() &&
							huwafkL.get(i_huwafk).getHdag() == huwkndL.get(i_huwknd).getHdag()){

						huwkndL.get(i_huwknd).getHuwafkL().add(huwafkL.get(i_huwafk));
						huwafkL.get(i_huwafk).setHuwknd(huwkndL.get(i_huwknd));

						i_huwafk++;

					}

					// Huweer

					while(i_huweer < huweerL.size() && huweerL.get(i_huweer).getIdnr() < stpb.getIdnr())
						i_huweer++;

					while(i_huweer < huweerL.size() && huweerL.get(i_huweer).getIdnr() == stpb.getIdnr() &&
							huweerL.get(i_huweer).getHjaar() == huwkndL.get(i_huwknd).getHjaar() &&
							huweerL.get(i_huweer).getHmaand() == huwkndL.get(i_huwknd).getHmaand() &&
							huweerL.get(i_huweer).getHdag() == huwkndL.get(i_huwknd).getHdag()){

						huwkndL.get(i_huwknd).getHuweerL().add(huweerL.get(i_huweer));
						huweerL.get(i_huweer).setHuwknd(huwkndL.get(i_huwknd));

						i_huweer++;

					}

					// Huwgtg

					while(i_huwgtg < huwgtgL.size() && huwgtgL.get(i_huwgtg).getIdnr() < stpb.getIdnr())
						i_huwgtg++;

					while(i_huwgtg < huwgtgL.size() && huwgtgL.get(i_huwgtg).getIdnr() == stpb.getIdnr() &&
							huwgtgL.get(i_huwgtg).getHjaar() == huwkndL.get(i_huwknd).getHjaar() &&
							huwgtgL.get(i_huwgtg).getHmaand() == huwkndL.get(i_huwknd).getHmaand() &&
							huwgtgL.get(i_huwgtg).getHdag() == huwkndL.get(i_huwknd).getHdag()){

						huwkndL.get(i_huwknd).getHuwgtgL().add(huwgtgL.get(i_huwgtg));
						huwgtgL.get(i_huwgtg).setHuwknd(huwkndL.get(i_huwknd));

						i_huwgtg++;

					}

					// Huwvrknd

					while(i_huwvrknd < huwvrkndL.size() && huwvrkndL.get(i_huwvrknd).getIdnr() < stpb.getIdnr())
						i_huwvrknd++;


					while(i_huwvrknd < huwvrkndL.size() && huwvrkndL.get(i_huwvrknd).getIdnr() == stpb.getIdnr() &&
							huwvrkndL.get(i_huwvrknd).getHjaar() == huwkndL.get(i_huwknd).getHjaar() &&
							huwvrkndL.get(i_huwvrknd).getHmaand() == huwkndL.get(i_huwknd).getHmaand() &&
							huwvrkndL.get(i_huwvrknd).getHdag() == huwkndL.get(i_huwknd).getHdag()){

						huwkndL.get(i_huwknd).getHuwvrkndL().add(huwvrkndL.get(i_huwvrknd));
						huwvrkndL.get(i_huwvrknd).setHuwknd(huwkndL.get(i_huwknd));

						i_huwvrknd++;

					}

					// Huwbyz

					while(i_huwbyz < huwbyzL.size() && huwbyzL.get(i_huwbyz).getIdnr() < stpb.getIdnr())
						i_huwbyz++;


					while(i_huwbyz < huwbyzL.size() && huwbyzL.get(i_huwbyz).getIdnr() == stpb.getIdnr() &&
							huwbyzL.get(i_huwbyz).getHjaar() == huwkndL.get(i_huwknd).getHjaar() &&
							huwbyzL.get(i_huwbyz).getHmaand() == huwkndL.get(i_huwknd).getHmaand() &&
							huwbyzL.get(i_huwbyz).getHdag() == huwkndL.get(i_huwknd).getHdag()){

						huwkndL.get(i_huwknd).getHuwbyzL().add(huwbyzL.get(i_huwbyz));
						huwbyzL.get(i_huwbyz).setHuwknd(huwkndL.get(i_huwknd));

						i_huwbyz++;

					}



					i_huwknd++;

				}

				// decease records

				// Ovlknd

				while(i_ovlknd < ovlkndL.size() && ovlkndL.get(i_ovlknd).getIdnr() < stpb.getIdnr()){
					Utils.message(300104, ovlkndL.get(i_ovlknd).getIdnr(), 0, "HSN_CIVREC_ORG", "Ovlknd");
					i_ovlknd++;
				}

				while(i_ovlknd < ovlkndL.size() && ovlkndL.get(i_ovlknd).getIdnr() == stpb.getIdnr()){
					o = true;
					gebkndL.get(i_gebknd).setOvlknd(ovlkndL.get(i_ovlknd));
					ovlkndL.get(i_ovlknd).setGebknd(gebkndL.get(i_gebknd));


					// Ovlech

					while(i_ovlech < ovlechL.size() && ovlechL.get(i_ovlech).getIdnr() < stpb.getIdnr())
						i_ovlech++;


					while(i_ovlech < ovlechL.size() && ovlechL.get(i_ovlech).getIdnr() == stpb.getIdnr()){
						ovlkndL.get(i_ovlknd).getOvlechL().add(ovlechL.get(i_ovlech));
						ovlechL.get(i_ovlech).setOvlknd(ovlkndL.get(i_ovlknd));

						i_ovlech++;
					}


					// Ovlagv

					while(i_ovlagv < ovlagvL.size() && ovlagvL.get(i_ovlagv).getIdnr() < stpb.getIdnr())
						i_ovlagv++;

					while(i_ovlagv < ovlagvL.size() && ovlagvL.get(i_ovlagv).getIdnr() == stpb.getIdnr()){
						ovlkndL.get(i_ovlknd).getOvlagvL().add(ovlagvL.get(i_ovlagv));
						ovlagvL.get(i_ovlagv).setOvlknd(ovlkndL.get(i_ovlknd));

						i_ovlagv++;
					}


					// Ovlbyz

					while(i_ovlbyz < ovlbyzL.size() && ovlbyzL.get(i_ovlbyz).getIdnr() < stpb.getIdnr())
						i_ovlbyz++;

					while(i_ovlbyz < ovlbyzL.size() && ovlbyzL.get(i_ovlbyz).getIdnr() == stpb.getIdnr()){
						ovlkndL.get(i_ovlknd).getOvlbyzL().add(ovlbyzL.get(i_ovlbyz));
						ovlbyzL.get(i_ovlbyz).setOvlknd(ovlkndL.get(i_ovlknd));

						i_ovlbyz++;
					}


					i_ovlknd++;
				}

				i_gebknd++;

			}


			if(g == true && h == true && o == true){
				igho++;
			}
			if(g == true && h == true && o == false){
				igh++;
			}
			if(g == true && h == false && o == true){
				igo++;
			}
			if(g == true && h == false && o == false){
				ig++;
			}


			// Gebakte

			while(i_gebakte < gebakteL.size() && gebakteL.get(i_gebakte).getIdnr() < stpb.getIdnr())
				i_gebakte++;

			while(i_gebakte < gebakteL.size() && gebakteL.get(i_gebakte).getIdnr() == stpb.getIdnr()){
				iga++;
				stpb.getGebakteL().add(gebakteL.get(i_gebakte));
				gebakteL.get(i_gebakte).setStpb(stpb);
				//print("     " + gebakteL.get(i_gebakte));

				i_gebakte++;
			}
		}

		//print("Birth certificates: " + d);
		//print("Only Birth certificates: " + ig);

		//print("Birth + Marriage certificates: " + igh);
		//print("Birth + Decease certificates: " + igo);
		//print("Birth + Marriage + Decease certificates: " + igho);
		//print("Birth + Gebakte: " + iga);
		//if(i_skip > 0)
		//	print("Skipped Birth Certificates: " + i_skip);
		//print("#stpb = " + c);
		//print("i+gebknd = " + i_gebknd);
		//print("i+huwknd = " + i_huwknd);
		//print("i+ovlknd = " + i_ovlknd);

		/*

		ArrayList<Huwafk>  huwafkL1  = all.get(12);
    	for(Huwafk huwafk: huwafkL1)
        	System.out.println("      Huwafk: " + huwafk + " IDNR: " + huwafk.getIdnr());

        
		for(Stpb stpb: stpbL){

        	System.out.println();
        	System.out.println("Sptb: " + stpb + " IDNR: " + stpb.getIdnr());
        	for(Gebknd gebknd: stpb.getGebkndL()){
            	System.out.println("  Gebknd: " + gebknd + " IDNR: " + gebknd.getIdnr());

        		for(Huwknd huwknd: gebknd.getHuwkndL()){
                	System.out.println("    Huwknd: " + huwknd + " IDNR: " + huwknd.getIdnr());
                	
                	for(Huwafk huwafk: huwknd.getHuwafkL())
                    	System.out.println("      Huwafk: " + huwafk + " IDNR: " + huwafk.getIdnr());

        		}
        	}
        }
        
        */
		


	}

	private static void convert(){

		ArrayList<Stpb>    stpbL    = all.get(0);
		int c = getCount();

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("nieuw");
		EntityManager        em  = emf.createEntityManager();



		em.getTransaction().begin();

        Query query=em.createNativeQuery("set @@GLOBAL.sql_mode = NO_BACKSLASH_ESCAPES");
        query.executeUpdate();

		for(Stpb stpb: stpbL){
			
			stpb.convert(em);
			if(++c%1000 == 0){
				print("Processed " + c + " IDNR's");
				em.getTransaction().commit();
				em.clear();
				
				em = emf.createEntityManager();
				em.getTransaction().begin();
				//Log.finalise();
				//if(1==1)break;
			}
		}

		setCount(c);
		//print("Start saving data tables");

		em.getTransaction().commit();
        em.clear();

		em.close();


	}

	
	private static void keyChecks(){

		ArrayList<Stpb>    stpbL    = all.get(0);
		ArrayList<Gebakte> gebakteL = all.get(1);
		ArrayList<Gebbyz>  gebbyzL  = all.get(2);
		ArrayList<Gebgtg>  gebgtgL  = all.get(3);
		ArrayList<Gebkant> gebkantL = all.get(4);
		ArrayList<Gebknd>  gebkndL  = all.get(5);
		ArrayList<Gebvdr>  gebvdrL  = all.get(6);

		Gebknd gebknd = new Gebknd();

		Comparator<Gebknd> comparatorGebknd = new Comparator<Gebknd>()
		{
			public int compare(Gebknd gebknd1, Gebknd gebknd2){
					if     (gebknd1.getIdnr() < gebknd2.getIdnr()) return -1;
					else if(gebknd1.getIdnr() > gebknd2.getIdnr()) return  1;
					return 0;
			}
		};



		int errorBase = 300400;

       // B2 - Gebgtg --> B1 - Gebknd

		for(Gebgtg gebgtg: gebgtgL){
			gebknd.setIdnr(gebgtg.getIdnr());
			int i = Collections.binarySearch(gebkndL, gebknd, comparatorGebknd);
			if(i < 0)
				Utils.message(errorBase + Constants.E_IDB2IDNR, gebgtg.getIdnr(), 0, "HSN_CIVREC_STD", "B2");
		}

	    // B3 - Gebbyz --> B1 - Gebknd

		for(Gebbyz gebbyz: gebbyzL){
			gebknd.setIdnr(gebbyz.getIdnr());
			int i = Collections.binarySearch(gebkndL, gebknd, comparatorGebknd);
			if(i < 0)
				Utils.message(errorBase + Constants.E_IDB3IDNR, gebbyz.getIdnr(), 0, "HSN_CIVREC_STD", "B3");
		}

	    // B4 - Gebkant --> B1 - Gebknd

		for(Gebkant gebkant: gebkantL){
			gebknd.setIdnr(gebkant.getIdnr());
			int i = Collections.binarySearch(gebkndL, gebknd, comparatorGebknd);
			if(i < 0)
				Utils.message(errorBase + Constants.E_IDB4IDNR, gebkant.getIdnr(), 0, "HSN_CIVREC_STD", "B4");

		}

	    // B5 - Gebvdr --> B1 - Gebknd

		for(Gebvdr gebvdr: gebvdrL){
			gebknd.setIdnr(gebvdr.getIdnr());
			int i = Collections.binarySearch(gebkndL, gebknd, comparatorGebknd);
			if(i < 0)
				Utils.message(errorBase + Constants.E_IDB5IDNR, gebvdr.getIdnr(), 0, "HSN_CIVREC_STD", "B5");

		}



		ArrayList<Ovlagv>  ovlagvL  = all.get(7);
		ArrayList<Ovlbyz>  ovlbyzL  = all.get(8);
		ArrayList<Ovlech>  ovlechL  = all.get(9);
		ArrayList<Ovlknd>  ovlkndL  = all.get(10);


		Ovlknd ovlknd = new Ovlknd();

		Comparator<Ovlknd> comparatorOvlknd = new Comparator<Ovlknd>()
		{
			public int compare(Ovlknd ovlknd1, Ovlknd ovlknd2){
					if     (ovlknd1.getIdnr() < ovlknd2.getIdnr()) return -1;
					else if(ovlknd1.getIdnr() > ovlknd2.getIdnr()) return  1;
					return 0;
			}
		};

       // D2 - Ovlech --> D1 - Ovlknd

		for(Ovlech ovlech: ovlechL){
			ovlknd.setIdnr(ovlech.getIdnr());
			int i = Collections.binarySearch(ovlkndL, ovlknd, comparatorOvlknd);
			if(i < 0)
				Utils.message(errorBase + Constants.E_IDD2IDNR, ovlech.getIdnr(), 0, "HSN_CIVREC_STD", "D2");
		}

       // D3 - Ovlagv --> D1 - Ovlknd

		for(Ovlagv ovlagv: ovlagvL){
			ovlknd.setIdnr(ovlagv.getIdnr());
			int i = Collections.binarySearch(ovlkndL, ovlknd, comparatorOvlknd);
			if(i < 0)
				Utils.message(errorBase + Constants.E_IDD3IDNR, ovlagv.getIdnr(), 0, "HSN_CIVREC_STD", "D3");
		}

       // D4 - Ovlbyz --> D1 - Ovlknd

		for(Ovlbyz ovlbyz: ovlbyzL){
			ovlknd.setIdnr(ovlbyz.getIdnr());
			int i = Collections.binarySearch(ovlkndL, ovlknd, comparatorOvlknd);
			if(i < 0)
				Utils.message(errorBase + Constants.E_IDD4IDNR, ovlbyz.getIdnr(), 0, "HSN_CIVREC_STD", "D4");
		}



		ArrayList<Huwknd>  huwkndL  = all.get(11);
		ArrayList<Huwafk>  huwafkL  = all.get(12);
		ArrayList<Huweer>  huweerL  = all.get(13);
		ArrayList<Huwgtg>  huwgtgL  = all.get(14);
		ArrayList<Huwvrknd> huwvrkndL  = all.get(15);
		ArrayList<Huwbyz>  huwbyzL  = all.get(16);


		Huwknd huwknd = new Huwknd();

		Comparator<Huwknd> comparatorHuwknd = new Comparator<Huwknd>()
		{
			public int compare(Huwknd huwknd1, Huwknd huwknd2){
					if     (huwknd1.getIdnr() < huwknd2.getIdnr()) return -1;
					else if(huwknd1.getIdnr() > huwknd2.getIdnr()) return  1;
					return 0;
			}
		};

       // M2 - Huwafk --> M1 - Huwknd

		for(Huwafk huwafk: huwafkL){
			huwknd.setIdnr(huwafk.getIdnr());
			int i = Collections.binarySearch(huwkndL, huwknd, comparatorHuwknd);
			if(i < 0)
				Utils.message(errorBase + Constants.E_IDM2IDNR, huwafk.getIdnr(), 0, "HSN_CIVREC_STD", "M2");
		}

       // M3 - Huweer --> M1 - Huwknd

		for(Huweer huweer: huweerL){
			huwknd.setIdnr(huweer.getIdnr());
			int i = Collections.binarySearch(huwkndL, huwknd, comparatorHuwknd);
			if(i < 0)
				Utils.message(errorBase + Constants.E_IDM3IDNR, huweer.getIdnr(), 0, "HSN_CIVREC_STD", "M3");
		}

       // M4 - Huwgtg --> M1 - Huwknd

		for(Huwgtg huwgtg: huwgtgL){
			huwknd.setIdnr(huwgtg.getIdnr());
			int i = Collections.binarySearch(huwkndL, huwknd, comparatorHuwknd);
			if(i < 0)
				Utils.message(errorBase + Constants.E_IDM4IDNR, huwgtg.getIdnr(), 0, "HSN_CIVREC_STD", "M4");
		}

       // M5 - Huwvrknd --> M1 - Huwknd

		for(Huwvrknd huwvrknd: huwvrkndL){
			huwknd.setIdnr(huwvrknd.getIdnr());
			int i = Collections.binarySearch(huwkndL, huwknd, comparatorHuwknd);
			if(i < 0)
				Utils.message(errorBase + Constants.E_IDM5IDNR, huwvrknd.getIdnr(), 0, "HSN_CIVREC_STD", "M5");
		}

       // M6 - Huwbyz --> M1 - Huwknd

		for(Huwbyz huwbyz: huwbyzL){
			huwknd.setIdnr(huwbyz.getIdnr());
			int i = Collections.binarySearch(huwkndL, huwknd, comparatorHuwknd);
			if(i < 0)
				Utils.message(errorBase + Constants.E_IDM6IDNR, huwbyz.getIdnr(), 0, "HSN_CIVREC_STD", "M6");
		}

		// B1 - Gebknd --> B2 - Gebgtg
		
		Gebgtg gebgtg = new Gebgtg();
		
		for(Gebknd gebknd1: gebkndL){
		
			gebgtg.setIdnr(gebknd1.getIdnr());
			int i = Collections.binarySearch(gebgtgL, gebgtg, new Comparator<Gebgtg>(){
				public int compare(Gebgtg gebgtg1, Gebgtg gebgtg2){
						if     (gebgtg1.getIdnr() < gebgtg2.getIdnr()) return -1;
						else if(gebgtg1.getIdnr() > gebgtg2.getIdnr()) return  1;
						return 0;
				}});
			if(i < 0)
				Utils.message(errorBase + Constants.E_IDB12IDN, gebknd1.getIdnr(), 0, "HSN_CIVREC_STD", "B2");
			
		}
		
		// B1 - Gebknd --> B5 - Gebvdr
		
		Gebvdr gebvdr = new Gebvdr();
		
		for(Gebknd gebknd1: gebkndL){
		
			gebvdr.setIdnr(gebknd1.getIdnr());
			int i = Collections.binarySearch(gebvdrL, gebvdr, new Comparator<Gebvdr>(){
				public int compare(Gebvdr gebvdr1, Gebvdr gebvdr2){
						if     (gebvdr1.getIdnr() < gebvdr2.getIdnr()) return -1;
						else if(gebvdr1.getIdnr() > gebvdr2.getIdnr()) return  1;
						return 0;
				}});
			if(i < 0)
				Utils.message(errorBase + Constants.E_IDB15IDN, gebknd1.getIdnr(), 0, "HSN_CIVREC_STD", "B5");
			
		}
		
		// D1 - Ovlknd --> D3 - Ovlagv
		
		Ovlagv ovlagv = new Ovlagv();
		
		for(Ovlknd ovlknd1: ovlkndL){
		
			ovlagv.setIdnr(ovlknd1.getIdnr());
			int i = Collections.binarySearch(ovlagvL, ovlagv, new Comparator<Ovlagv>(){
				public int compare(Ovlagv ovlagv1, Ovlagv ovlagv2){
						if     (ovlagv1.getIdnr() < ovlagv2.getIdnr()) return -1;
						else if(ovlagv1.getIdnr() > ovlagv2.getIdnr()) return  1;
						return 0;
				}});
			if(i < 0)
				Utils.message(errorBase + Constants.E_IDD13IDN, ovlknd1.getIdnr(), 0, "HSN_CIVREC_STD", "D3");
			
		}
		
		// M1 - Huwknd --> M2 - Huwafk
		
		Huwafk huwafk = new Huwafk();
		
		for(Huwknd huwknd1: huwkndL){
		
			huwafk.setIdnr(huwknd1.getIdnr());
			huwafk.setHjaar(huwknd1.getHjaar());
			huwafk.setHmaand(huwknd1.getHmaand());
			huwafk.setHdag(huwknd1.getHdag());
			
			int i = Collections.binarySearch(huwafkL, huwafk, new Comparator<Huwafk>(){
				public int compare(Huwafk huwafk1, Huwafk huwafk2){
						if     (huwafk1.getIdnr() < huwafk2.getIdnr()) return -1;
						else if(huwafk1.getIdnr() > huwafk2.getIdnr()) return  1;
						if     (huwafk1.getHjaar() >  huwafk2.getHjaar()) return  1;
						else if(huwafk1.getHjaar() <  huwafk2.getHjaar()) return -1;
						if     (huwafk1.getHmaand() >  huwafk2.getHmaand()) return  1;
						else if(huwafk1.getHmaand() <  huwafk2.getHmaand()) return -1;
						if     (huwafk1.getHdag()   >  huwafk2.getHdag()) return  1;
						else if(huwafk1.getHdag() <  huwafk2.getHdag()) return -1;
						return 0;
				}});
			if(i < 0)
				Utils.message(errorBase + Constants.E_IDM12IDN, huwknd1.getIdnr(), 0, "HSN_CIVREC_STD", "M2");
			
		}
		
		// M1 - Huwknd --> M4 - Huwgtg
		
		Huwgtg huwgtg = new Huwgtg();
		
		for(Huwknd huwknd1: huwkndL){
		
			huwgtg.setIdnr(huwknd1.getIdnr());
			huwgtg.setHjaar(huwknd1.getHjaar());
			huwgtg.setHmaand(huwknd1.getHmaand());
			huwgtg.setHdag(huwknd1.getHdag());
			
			int i = Collections.binarySearch(huwgtgL, huwgtg, new Comparator<Huwgtg>(){
				public int compare(Huwgtg huwgtg1, Huwgtg huwgtg2){
						if     (huwgtg1.getIdnr() < huwgtg2.getIdnr()) return -1;
						else if(huwgtg1.getIdnr() > huwgtg2.getIdnr()) return  1;
						if     (huwgtg1.getHjaar() >  huwgtg2.getHjaar()) return  1;
						else if(huwgtg1.getHjaar() <  huwgtg2.getHjaar()) return -1;
						if     (huwgtg1.getHmaand() >  huwgtg2.getHmaand()) return  1;
						else if(huwgtg1.getHmaand() <  huwgtg2.getHmaand()) return -1;
						if     (huwgtg1.getHdag()   >  huwgtg2.getHdag()) return  1;
						else if(huwgtg1.getHdag() <  huwgtg2.getHdag()) return -1;
						return 0;
				}});
			if(i < 0)
				Utils.message(errorBase + Constants.E_IDM14IDN, huwknd1.getIdnr(), 0, "HSN_CIVREC_STD", "M4");
			
		}
		

		// B0 --> B1 of B6

		errorBase = 300100;

		Comparator<Gebakte> comparatorGebakte = new Comparator<Gebakte>()
		{
			public int compare(Gebakte gebakte1, Gebakte gebakte2){
					if     (gebakte1.getIdnr() < gebakte2.getIdnr()) return -1;
					else if(gebakte1.getIdnr() > gebakte2.getIdnr()) return  1;
					return 0;
			}
		};

		Gebakte gebakte = new Gebakte();

		for(Stpb stpb: stpbL){

			gebakte.setIdnr(stpb.getIdnr());
			int i = Collections.binarySearch(gebakteL, gebakte, comparatorGebakte);
			if(i < 0){

				gebknd.setIdnr(stpb.getIdnr());
				int j = Collections.binarySearch(gebkndL, gebknd, comparatorGebknd);

				if(j < 0)
					Utils.message(errorBase + Constants.E_SPB1SDCC, stpb.getIdnr(), 0, "HSN_CIVREC_STD", "B0");

			}
		}


		// B1 --> B0
		// B6 --> B0



		Comparator<Stpb> comparatorStpb = new Comparator<Stpb>()
		{
			public int compare(Stpb stpb1, Stpb stpb2){
					if     (stpb1.getIdnr() < stpb2.getIdnr()) return -1;
					else if(stpb1.getIdnr() > stpb2.getIdnr()) return  1;
					return 0;
			}
		};

		Stpb stpb = new Stpb();

		for(Gebknd gebknd1 : gebkndL){

			stpb.setIdnr(gebknd1.getIdnr());
			int i = Collections.binarySearch(stpbL, stpb, comparatorStpb);
			if(i < 0)
				Utils.message(errorBase + Constants.E_SPB1SDCY, gebknd1.getIdnr(), 0, "HSN_CIVREC_STD", "B1");

		}

		for(Gebakte gebakte1 : gebakteL){

			stpb.setIdnr(gebakte1.getIdnr());
			int i = Collections.binarySearch(stpbL, stpb, comparatorStpb);
			if(i < 0)
				Utils.message(errorBase + Constants.E_SPB1SDCN, gebakte1.getIdnr(), 0, "HSN_CIVREC_STD", "B6");

		}

		// IDNR must occur only once in B1, B6, D1

		errorBase = 300200;


		int idnr = -1;
		for(Gebknd gebknd1: gebkndL){
			if(gebknd1.getIdnr() == idnr)
				Utils.message(errorBase + Constants.E_DUB1IDNR, gebknd1.getIdnr(), 0, "HSN_CIVREC_STD", "B1");
			idnr = gebknd1.getIdnr();
		}

		idnr = -1;
		for(Gebakte gebakte1: gebakteL){
			if(gebakte1.getIdnr() == idnr)
				Utils.message(errorBase + Constants.E_DUB6IDNR, gebakte1.getIdnr(), 0, "HSN_CIVREC_STD", "B6");
			idnr = gebakte1.getIdnr();
		}

		idnr = -1;
		for(Ovlknd ovlknd1: ovlkndL){
			if(ovlknd1.getIdnr() == idnr)
				Utils.message(errorBase + Constants.E_DUD1IDNR, ovlknd1.getIdnr(), 0, "HSN_CIVREC_STD", "D1");
			idnr = ovlknd1.getIdnr();
		}

		// IDNR + Vlgnrgt must be unique in B2 - Gebgtg
		
		idnr = -1;
		int seqno = -1;
		for(Gebgtg gebgtg1: gebgtgL){
			if(gebgtg1.getIdnr() == idnr && gebgtg1.getVlgnrgt() == seqno)
				Utils.message(errorBase + Constants.E_DUB2IDVL, idnr, 0, "HSN_CIVREC_STD", "B2", "" + seqno);
			idnr = gebgtg1.getIdnr();
			seqno = gebgtg1.getVlgnrgt();
		}
		
		// IDNR + Byznr   must be unique in B3 - Gebbyz
		
		idnr = -1;
		seqno = -1;
		for(Gebbyz gebbyz1: gebbyzL){
			if(gebbyz1.getIdnr() == idnr && gebbyz1.getByznr() == seqno)
				Utils.message(errorBase + Constants.E_DUB3IDBY, idnr, 0, "HSN_CIVREC_STD", "B3",  "" + seqno);
			idnr = gebbyz1.getIdnr();
			seqno = gebbyz1.getByznr();
		}		
		
		// IDNR + Kanttype must be unique in B4 - Gebkant
		
		idnr = -1;
		seqno = -1;
		for(Gebkant gebkant1: gebkantL){
			if(gebkant1.getIdnr() == idnr && gebkant1.getKanttype() == seqno)
				Utils.message(errorBase + Constants.E_DUB4IDKA, idnr, 0,  "HSN_CIVREC_STD", "B4", "" + seqno);
			idnr = gebkant1.getIdnr();
			seqno = gebkant1.getKanttype();
		}			
		
		// IDNR must be unique in B5 - Gebvdr
		
		idnr = -1;
		for(Gebvdr gebvdr1: gebvdrL){
			if(gebvdr1.getIdnr() == idnr)
				Utils.message(errorBase + Constants.E_DUB5IDNR, idnr, 0, "HSN_CIVREC_STD", "B5", "");
			idnr = gebvdr1.getIdnr();
		}			
		
		// IDNR + Hjaar + Hmaand must be unique in M1 - huwknd
		
		idnr = -1;
		int marriage_year  = -1;
		int marriage_month = - 1;
		for(Huwknd huwknd1: huwkndL){
			if(huwknd1.getIdnr() == idnr && huwknd1.getHjaar() == marriage_year && huwknd1.getHmaand() == marriage_month)
				Utils.message(errorBase + Constants.E_DUM1KEY, idnr, 0, "HSN_CIVREC_STD", "M1", "" + marriage_year + "  " + marriage_month);
			idnr = huwknd1.getIdnr();
			marriage_year = huwknd1.getHjaar();
			marriage_month = huwknd1.getHmaand();
		}
		
		// IDNR + Hjaar + Hmaand + Hwaknr must be unique in M2 - Huwafk
		
		idnr = -1;
		marriage_year  = -1;
		marriage_month = - 1;
		seqno = -1;

		for(Huwafk huwafk1: huwafkL){
			if(huwafk1.getIdnr() == idnr && huwafk1.getHjaar() == marriage_year && huwafk1.getHmaand() == marriage_month && huwafk1.getHwaknr() == seqno)
				Utils.message(errorBase + Constants.E_DUM2KEY, idnr, 0, "HSN_CIVREC_STD", "M2",  "" + marriage_year + "  " + marriage_month + "   " + seqno);
			idnr = huwafk1.getIdnr();
			marriage_year = huwafk1.getHjaar();
			marriage_month = huwafk1.getHmaand();
			seqno = huwafk1.getHwaknr();
		}
		
		
		// IDNR + Hjaar + Hmaand + Huwer + Vlgnreh must be unique in M3 - Huweer
		
		idnr = -1;
		marriage_year  = -1;
		marriage_month = - 1;
		seqno = -1;
		String sex = "";

		for(Huweer huweer1: huweerL){
			//System.out.println("idnr = " + huweer1.getIdnr() + " year = " + huweer1.getHjaar() + " month = " + huweer1.getHmaand() + " sex = " + huweer1.getHuwer() + "seqno = " + huweer1.getVlgnreh());
			if(huweer1.getIdnr() == idnr && huweer1.getHjaar() == marriage_year && huweer1.getHmaand() == marriage_month && /* huweer1.getHuwer().equalsIgnoreCase(sex) && */ huweer1.getVlgnreh() == seqno)
				Utils.message(errorBase + Constants.E_DUM3KEY, idnr, 0, "HSN_CIVREC_STD", "M3",  "" + marriage_year + "  " + marriage_month + "   " + sex + "  " + seqno);
			idnr = huweer1.getIdnr();
			marriage_year = huweer1.getHjaar();
			marriage_month = huweer1.getHmaand();
			sex = huweer1.getHuwer();
			seqno = huweer1.getVlgnreh();
		}		
		
		// IDNR + Hjaar + Hmaand + Vlgnrgt must be unique in M4 - Huwgtg
		
		idnr = -1;
		marriage_year  = -1;
		marriage_month = - 1;
		seqno = -1;

		for(Huwgtg huwgtg1: huwgtgL){
			if(huwgtg1.getIdnr() == idnr && huwgtg1.getHjaar() == marriage_year && huwgtg1.getHmaand() == marriage_month && huwgtg1.getVlgnrgt() == seqno)
				Utils.message(errorBase + Constants.E_DUM4KEY, idnr, 0, "HSN_CIVREC_STD", "M4",  "" + marriage_year + "  " + marriage_month + "   " + seqno);
			idnr = huwgtg1.getIdnr();
			marriage_year = huwgtg1.getHjaar();
			marriage_month = huwgtg1.getHmaand();
			seqno = huwgtg1.getVlgnrgt();
		}
		
		
		// IDNR + Hjaar + Hmaand + Vlgnrvk must be unique in M5 - Huwvrknd
		
		idnr = -1;
		marriage_year  = -1;
		marriage_month = - 1;
		seqno = -1;

		for(Huwvrknd huwvrknd1: huwvrkndL){
			if(huwvrknd1.getIdnr() == idnr && huwvrknd1.getHjaar() == marriage_year && huwvrknd1.getHmaand() == marriage_month && huwvrknd1.getVlgnrvk() == seqno)
				Utils.message(errorBase + Constants.E_DUM5KEY, idnr, 0, "HSN_CIVREC_STD", "M5",  "" + marriage_year + "  " + marriage_month + "   " + seqno);
			idnr = huwvrknd1.getIdnr();
			marriage_year = huwvrknd1.getHjaar();
			marriage_month = huwvrknd1.getHmaand();
			seqno = huwvrknd1.getVlgnrvk();
		}		
		
		// IDNR + Hjaar + Hmaand + Byznr must be unique in M6 - Huwbyz
		
		idnr = -1;
		marriage_year  = -1;
		marriage_month = - 1;
		seqno = -1;

		for(Huwbyz huwbyz1: huwbyzL){
			if(huwbyz1.getIdnr() == idnr && huwbyz1.getHjaar() == marriage_year && huwbyz1.getHmaand() == marriage_month && huwbyz1.getByznr() == seqno)
				Utils.message(errorBase + Constants.E_DUM6KEY, idnr, 0, "HSN_CIVREC_STD", "M6",  "" + marriage_year + "  " + marriage_month + "   " + seqno);
			idnr = huwbyz1.getIdnr();
			marriage_year = huwbyz1.getHjaar();
			marriage_month = huwbyz1.getHmaand();
			seqno = huwbyz1.getByznr();
		}		
		
		
		// IDNR + Vlgech must be unique in D2 - Ovlech
		
		idnr = -1;
		seqno = -1;
		for(Ovlech ovlech1: ovlechL){
			if(ovlech1.getIdnr() == idnr && ovlech1.getVlgech() == seqno)
				Utils.message(errorBase + Constants.E_DUD2IDVL, idnr, 0, "HSN_CIVREC_STD", "D2",  "" + seqno);
			idnr = ovlech1.getIdnr();
			seqno = ovlech1.getVlgech();
		}		
		
		// IDNR + Vlgnrag must be unique in D3 - Ovlagv
		
		idnr = -1;
		seqno = -1;
		for(Ovlagv ovlagv1: ovlagvL){
			if(ovlagv1.getIdnr() == idnr && ovlagv1.getVlgnrag() == seqno)
				Utils.message(errorBase + Constants.E_DUD3IDVL, idnr, 0, "HSN_CIVREC_STD", "D3",  "" + seqno);
			idnr = ovlagv1.getIdnr();
			seqno = ovlagv1.getVlgnrag();
		}		
		
		
		
		// IDNR + Byznr must be unique in D4 - Ovlbyz
		
		idnr = -1;
		seqno = -1;
		for(Ovlbyz ovlbyz1: ovlbyzL){
			if(ovlbyz1.getIdnr() == idnr && ovlbyz1.getByznr() == seqno)
				Utils.message(errorBase + Constants.E_DUD4IDBY, idnr, 0, "HSN_CIVREC_STD", "D4",  "" + seqno);
			idnr = ovlbyz1.getIdnr();
			seqno = ovlbyz1.getByznr();
		}		
		

		errorBase = 300300;


		// B1 --> B6  (B1 and B6 must be disjunct)

		for(Gebknd gebknd1: gebkndL){

			gebakte.setIdnr(gebknd1.getIdnr());
			int i = Collections.binarySearch(gebakteL, gebakte, comparatorGebakte);
			if(i >= 0)
				Utils.message(errorBase + Constants.E_AKB1IDNR, gebknd1.getIdnr(), 0, "HSN_CIVREC_STD", "B1");
		}

		// B6 --> B1  (B1 and B6 must be disjunct)


		for(Gebakte gebakte1: gebakteL){

			gebknd.setIdnr(gebakte1.getIdnr());
			int i = Collections.binarySearch(gebkndL, gebknd, comparatorGebknd);
			if(i >= 0){
				Utils.message(errorBase + Constants.E_AKB1IDNR, gebakte1.getIdnr(), 0, "HSN_CIVREC_STD", "B6");
			}
		}

		// B6 --> B3 (If B6SUHZ == 9, there must be a corresponding record in B3

		errorBase = 101900;
		Comparator<Gebbyz> comparatorGebbyz = new Comparator<Gebbyz>()
		{
			public int compare(Gebbyz gebbyz1, Gebbyz gebbyz2){
					if     (gebbyz1.getIdnr() < gebbyz1.getIdnr()) return -1;
					else if(gebbyz1.getIdnr() > gebbyz1.getIdnr()) return  1;
					return 0;
			}
		};

		Gebbyz gebbyz = new Gebbyz();
		for(Gebakte gebakte1: gebakteL){
			if(gebakte1.getGemnr() == 9){
				gebbyz.setIdnr(gebakte1.getIdnr());
				int i = Collections.binarySearch(gebbyzL, gebbyz, comparatorGebbyz);
				if(i < 0)
					Utils.message(errorBase + Constants.E_HZB6SULZ, gebakte1.getIdnr(), 0, "HSN_CIVREC_STD", "B6");
			}
		}

		

	}

	private static void statistics(){

		print("Calculating statistics...");

		try{

			EntityManagerFactory factory1 = Persistence.createEntityManagerFactory("nieuw");
			EntityManager em = factory1.createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNativeQuery(Statistics.truncateM0);
			System.out.println();
			System.out.println(Statistics.truncateM0);

			query.executeUpdate();
			query = em.createNativeQuery(Statistics.insertM0);
			System.out.println();
			System.out.println(Statistics.insertM0);
			query.executeUpdate();

			em.getTransaction().commit();
            em.clear();

			EntityManagerFactory factory2 = Persistence.createEntityManagerFactory("log_create");
			EntityManager em2 = factory2.createEntityManager();

			em2.getTransaction().begin();

			query = em2.createNativeQuery(Statistics.truncateLog);
			System.out.println();
			System.out.println(Statistics.truncateLog);

			query.executeUpdate();
			query = em2.createNativeQuery(Statistics.insertLog);
			System.out.println();
			System.out.println(Statistics.insertLog);

			query.executeUpdate();
			query = em2.createNativeQuery(Statistics.updateLog1);
			System.out.println();
			System.out.println(Statistics.updateLog1);

			query.executeUpdate();



			query = em2.createQuery("select a from StatView a");
			List<StatView> l  = query.getResultList();


			//query = em.createNativeQuery(Statistics.selectLog);
			//query.executeUpdate();


			ArrayList<String> statements = new ArrayList<String>();

			for(StatView sv: l){

			//	String PK500  = new Integer(rs.getInt("pk500")).toString();
			//	String PROVNR = new Integer(rs.getInt("PROVNR")).toString();
			//	String THREEGS = rs.getString("3GS");
			//	String COHORT = rs.getString("COHORT");

				
				//System.out.println();
				//System.out.println("getPk500 = " + sv.getPk500());
				//System.out.println("getProvnr = " + sv.getProvnr());
				//System.out.println("getThreeGS = " + sv.getThreeGS());
				//System.out.println("getCohort = " + sv.getCohort());

			    String	statement = Statistics.updateLog2;
				//System.out.println();
				//System.out.println(Statistics.updateLog2);

				statement = statement.replace("%PK500", "" + sv.getPk500());
				statement = statement.replace("%PROVNR","" + sv.getProvnr());
				statement = statement.replace("%3GS", sv.getThreeGS());
				statement = statement.replace("%COHORT", sv.getCohort());

				//print(statement);
				//System.out.println();
				//System.out.println(statement);
				statements.add(statement);

			}

			for(String s: statements){
				query = em2.createNativeQuery(s);
				query.executeUpdate();
			}


			query = em2.createNativeQuery(Statistics.updateLog3);
			query.executeUpdate();
			//System.out.println();
			//System.out.println(Statistics.updateLog3);

			
			em2.getTransaction().commit();
            em2.clear();


		}
		catch(Exception e) {
			print(e.getMessage());
		}


	}
	

    public void setOut(DataOutputStream out) {
        StandardizeCivilCertificates.out = out;
    }

	public String getInputDirectory() {
		return inputDirectory;
	}

	public void setInputDirectory(String inputDirectory) {
		this.inputDirectory = inputDirectory;
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


	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		StandardizeCivilCertificates.count = count;
	}
}
