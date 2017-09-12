/*
 * Naam:    Ref
 * Version: 0.1
 *  Author: Cor Munnik
 * Copyright
 */

package nl.iisg.ref;

//import nl.iisg.ref.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * 
 * This class handles the loading of the reference and standardization tables and getting/adding of standardization and reference data
 * 
 *  Reference Tables (They are not added to by this program):
 *     
 *  Ref_AINB
 *  Ref_GB
 *  Ref_KG
 *  
 *  Standardization tables (They are added to by the program):
 *  
 *  Ref_FamilyName
 *  Ref_FirstName
 *  Ref_Prefix
 *  Ref_Profession
 *  Ref_Location
 *  Ref_Address
 *  Ref_Housenumber
 *  Ref_Housenumberaddition
 * 
 */
public class Ref {

	static    List<Ref_FamilyName>           ref_famName   = null; 
	static    List<Ref_FamilyName>           ref_famName_x = new ArrayList<Ref_FamilyName>(); 
	static    HashMap<String, Ref_FamilyName>        ref_famName21  = null; // entries read from the database
	static    HashMap<String, Ref_FamilyName>        ref_famName22  = null; // new entries  

	static    List<Ref_Prefix>               ref_prefix    = null;
	static    List<Ref_Prefix>               ref_prefix_x  = new ArrayList<Ref_Prefix>();

	static    List<Ref_FirstName>            ref_firstName = null;
	static    List<Ref_FirstName>            ref_firstName_x = new ArrayList<Ref_FirstName>();
	static    HashMap<String, Ref_FirstName>        ref_firstName21  = null; // entries read from the database
	static    HashMap<String, Ref_FirstName>        ref_firstName22  = null; // new entries  


	static    List<Ref_AINB>                 ref_AINB  = new ArrayList<Ref_AINB>();

	static    List<Ref_Location>             ref_location  = null;
	static    List<Ref_Location>             ref_location_x = new ArrayList<Ref_Location>();
	static    HashMap<String, Ref_Location>  ref_location21 = null; // entries read from db 
	static    HashMap<String, Ref_Location>  ref_location22 = null; // new entries  
	
	static    HashMap<Integer, String>       ref_location31 = null; 
	
	
	static    List<Ref_KG>                   ref_KG    = new ArrayList<Ref_KG>();
	static    List<Ref_KG>                   ref_KG_x  = new ArrayList<Ref_KG>();

	static    List<Ref_GB>                   ref_GB   = new ArrayList<Ref_GB>();

	static    List<Ref_Profession>           ref_profession  = null;
	static    List<Ref_Profession>           ref_profession_x = new ArrayList<Ref_Profession>();

	static    List<Ref_Address>              ref_address = null;
	static    List<Ref_Address>              ref_address_x = new ArrayList<Ref_Address>();
	static    HashMap<String, Ref_Address>   ref_address21 = null; // entries read from db 
	static    HashMap<String, Ref_Address>   ref_address22 = null; // new entries  


	static    List<Ref_Housenumber>          ref_housenumber = null;
	static    List<Ref_Housenumber>          ref_housenumber_x = new ArrayList<Ref_Housenumber>();
	static    HashMap<String, Ref_Housenumber>        ref_houseNumber21  = null; // entries read from the database
	static    HashMap<String, Ref_Housenumber>        ref_houseNumber22  = null; // new entries  


	static    List<Ref_Housenumberaddition>          ref_housenumberaddition = null;
	static    List<Ref_Housenumberaddition>          ref_housenumberaddition_x = new ArrayList<Ref_Housenumberaddition>();
	static    HashMap<String, Ref_Housenumberaddition>        ref_houseNumberAddition21  = null; // entries read from the database
	static    HashMap<String, Ref_Housenumberaddition>        ref_houseNumberAddition22  = null; // new entries  


	static    List<Ref_Municipality>                 ref_Municipality  = new ArrayList<Ref_Municipality>();

	static    List<Ref_Relation_B>           ref_relation_b    = null;
	static    List<Ref_Relation_B>           ref_relation_b_x  = new ArrayList<Ref_Relation_B>();
	
	static    List<Ref_Relation_C>           ref_relation_c    = null;
	static    List<Ref_Relation_C>           ref_relation_c_x  = new ArrayList<Ref_Relation_C>();
	

	static EntityManagerFactory              factory_ref   = Persistence.createEntityManagerFactory("ref_tables");
	static EntityManager                     em_ref        = getFactory_ref().createEntityManager(); 
	static EntityManagerFactory              factory_ref_2 = Persistence.createEntityManagerFactory("ref_tables_2");
	static EntityManager                     em_ref_2      = getFactory_ref_2().createEntityManager(); 

	public static final int XBigstring = 256;
	public static final int Bigstring = 80;
	public static final int Smallstring = 15;
	public static final int Mediumstring = 30;
	
	
	
	public static void createRefTables(){
		
		EntityManager em = getNewEm_ref();	
		em.getTransaction().begin();
		
		
		em.getTransaction().commit();
		em.clear();

		em = getEm_ref_2();	
		em.getTransaction().begin();

		Query query = em.createNativeQuery(Ref_CreateTables.Ref_Address); 
		query.executeUpdate();  
		query = em.createNativeQuery(Ref_CreateTables.Ref_Housenumber); 
		query.executeUpdate();  
		query = em.createNativeQuery(Ref_CreateTables.Ref_Housenumberaddition); 
		query.executeUpdate();  
		query = em.createNativeQuery(Ref_CreateTables.Ref_Location); 
		query.executeUpdate();  
		query = em.createNativeQuery(Ref_CreateTables.Ref_Municipality); 
		query.executeUpdate();  

		query = em.createNativeQuery(Ref_CreateTables.Ref_Profession); 
		query.executeUpdate();  
		query = em.createNativeQuery(Ref_CreateTables.Ref_KG); 
		query.executeUpdate();  
		query = em.createNativeQuery(Ref_CreateTables.Ref_FamilyName); 
		query.executeUpdate();  
		query = em.createNativeQuery(Ref_CreateTables.Ref_FirstName); 
		query.executeUpdate();  
		query = em.createNativeQuery(Ref_CreateTables.Ref_Prefix); 
		query.executeUpdate();  
		query = em.createNativeQuery(Ref_CreateTables.Ref_Relation_B); 
		query.executeUpdate();  
		query = em.createNativeQuery(Ref_CreateTables.Ref_Relation_C); 
		query.executeUpdate();  
		
		em.getTransaction().commit();
		em.clear();


	}

	public static void loadAINB(){

		System.out.println("Reading Ref_AINB");

		EntityManager em = getNewEm_ref();	
		Query q = em.createQuery("select a from Ref_AINB a");
		ref_AINB =  q.getResultList();		

		System.out.println("Read   Ref_AINB " + ref_AINB.size() + " rows");

		Collections.sort(ref_AINB, new Comparator<Ref_AINB>()
				{
			public int compare(Ref_AINB ainb1, Ref_AINB ainb2){
				if(ainb1.getKeyToSourceRegister() < ainb2.getKeyToSourceRegister())
					return 1;
				if(ainb1.getKeyToSourceRegister() > ainb2.getKeyToSourceRegister())
					return -1;
				return 0;
			}
				});


	}

	public static void loadGB(){ 

		System.out.println("Reading Ref_GB");

		EntityManager em = getNewEm_ref();	
		Query q = em.createQuery("select g from Ref_GB g");
		ref_GB = q.getResultList();		

		int count = 0;
		for(Ref_GB g: ref_GB){
			count++;
		}
		System.out.println("Read    Ref_GB " + count + " rows");

		Collections.sort(ref_GB, new Comparator<Ref_GB>()
				{
			public int compare(Ref_GB gb1, Ref_GB gb2){
				if(gb1.getKeyToRP() < gb2.getKeyToRP())
					return 1;
				if(gb1.getKeyToRP() > gb2.getKeyToRP())
					return -1;
				return 0;
			}
				});
		

	}

	public static void loadKG(){ 

		System.out.println("Reading Ref_Religion");

		EntityManager em = getEm_ref_2();	
		Query q = em.createQuery("select k from Ref_KG k");
		ref_KG = q.getResultList();		

		int count = 0;
		int highest_ID = 0;
		for(Ref_KG g: ref_KG){
			if(g.getId_religion() > highest_ID)
				highest_ID = g.getId_religion();
			count++;
		}

		Ref_KG.setCurrent_ID(highest_ID + 1);
		System.out.println("Read    Ref_Religion " + count + " rows");

		Collections.sort(ref_KG, new Comparator<Ref_KG>()
				{
			public int compare(Ref_KG kg1, Ref_KG kg2){
				String kg01 = kg1.getDenomination() != null ?  kg1.getDenomination() : "" ;
				String kg02 = kg2.getDenomination() != null ?  kg2.getDenomination() : "" ;
				return (kg01.compareToIgnoreCase(kg02));
			}
				});
		


	}

	public static void loadFamNameOld(){

		System.out.println("Reading Ref_FamilyName");

		EntityManager em = getEm_ref_2();	
		Query q = em.createQuery("select a from Ref_FamilyName a");
		ref_famName  = q.getResultList();		

		int highest_ID = 0;
		int count = 0;

		for(Ref_FamilyName f: ref_famName){
			
			if(f.getLastNameID() > highest_ID)
				highest_ID = f.getLastNameID();
			count++;
		}

		Ref_FamilyName.setCurrent_ID(highest_ID + 1);
		System.out.println("Read    Ref_FamilyName " + count + " rows");

		Collections.sort(ref_famName, new Comparator<Ref_FamilyName>()
				{
			public int compare(Ref_FamilyName f1, Ref_FamilyName f2){
				
				String f01 = f1.getOriginal() != null ? f1.getOriginal() : "";
				String f02 = f2.getOriginal() != null ? f2.getOriginal() : "";
				return (f01.compareToIgnoreCase(f02));
			}
				});
		

	}

	public static void loadFamName(){

		System.out.println("Reading Ref_FamilyName");

		EntityManager em = getEm_ref_2();	
		Query q = em.createQuery("select a from Ref_FamilyName a");
		List<Ref_FamilyName> ref_famName  = q.getResultList();		

		int highest_ID = 0;
		int count = 0;

		ref_famName21 = new HashMap<String, Ref_FamilyName>();
		ref_famName22 = new HashMap<String, Ref_FamilyName>();
		
		for(Ref_FamilyName f: ref_famName){

			if(f.getOriginal() != null){
				ref_famName21.put(f.getOriginal().trim().toLowerCase(), f);

				if(f.getLastNameID() > highest_ID)
					highest_ID = f.getLastNameID();
				count++;

			}

		}
			
		Ref_FamilyName.setCurrent_ID(highest_ID + 1);
		
		
		System.out.println("Read    Ref_FamilyName " + count + " rows");

	}

	
	public static void loadFirstNameOld(){

		System.out.println("Reading Ref_FirstName");

		EntityManager em = getEm_ref_2();	
		Query q = em.createQuery("select a from Ref_FirstName a");
		ref_firstName  = q.getResultList();
		
		int highest_ID = 0;
		int count = 0;
		for(Ref_FirstName f: ref_firstName){
			if(f.getFirstNameID() > highest_ID)
				highest_ID = f.getFirstNameID();

			count++;
		}	

		Ref_FirstName.setCurrent_ID(highest_ID + 1); 
		System.out.println("Read    Ref_FirstName " + count + " rows");

		Collections.sort(ref_firstName, new Comparator<Ref_FirstName>()
				{
			public int compare(Ref_FirstName f1, Ref_FirstName f2){
				String f01 = f1.getOriginal() != null ? f1.getOriginal() : "";
				String f02 = f2.getOriginal() != null ? f2.getOriginal() : "";
				return (f01.compareToIgnoreCase(f02));
			}
				});
		

	}

	public static void loadFirstName(){

		System.out.println("Reading Ref_FirstName");

		EntityManager em = getEm_ref_2();	
		Query q = em.createQuery("select a from Ref_FirstName a");
		List<Ref_FirstName> ref_firstName  = q.getResultList();		

		int highest_ID = 0;
		int count = 0;

		ref_firstName21 = new HashMap<String, Ref_FirstName>();
		ref_firstName22 = new HashMap<String, Ref_FirstName>();
		
		for(Ref_FirstName f: ref_firstName){

			if(f.getOriginal() != null){
				ref_firstName21.put(f.getOriginal().trim().toLowerCase(), f);

				if(f.getFirstNameID() > highest_ID)
					highest_ID = f.getFirstNameID();
				count++;

			}

		}
			
		Ref_FirstName.setCurrent_ID(highest_ID + 1);
		
		
		System.out.println("Read    Ref_FirstName " + count + " rows");

	}


	public static void loadPrefix(){

		System.out.println("Reading Ref_Prefix");
		EntityManager em = getEm_ref_2();	
		Query q = em.createQuery("select a from Ref_Prefix a");
		ref_prefix  = q.getResultList();

		int highest_ID = 0;
		int count = 0;
		for(Ref_Prefix p: ref_prefix){
			if(p.getPrefixID() > highest_ID)
				highest_ID = p.getPrefixID();

			count++;
		}	

		Ref_Prefix.setCurrent_ID(highest_ID + 1); 
		System.out.println("Read    Ref_Prefix " + count + " rows");

		Collections.sort(ref_prefix, new Comparator<Ref_Prefix>()
				{
			public int compare(Ref_Prefix f1, Ref_Prefix f2){
				String f01 = f1.getOriginal() != null ? f1.getOriginal() : "";
				String f02 = f2.getOriginal() != null ? f2.getOriginal() : "";
				return (f01.compareToIgnoreCase(f02));
			}
				});
		


	}


	public static void loadLocationOld(){

		System.out.println("Reading Ref_Location");

		EntityManager em = getEm_ref_2();	
		Query q = em.createQuery("select a from Ref_Location a");
		ref_location  = q.getResultList();		

		int count = 0;
		int highest_ID = 0;
		for(Ref_Location f: ref_location){
			if(f.getLocationID() > highest_ID)
				highest_ID = f.getLocationID();

			count++; 
		}

		Ref_Location.setCurrent_ID(highest_ID + 1); 
		System.out.println("Read    Ref_Location " + count + " rows");

		Collections.sort(ref_location, new Comparator<Ref_Location>()
				{
			public int compare(Ref_Location l1, Ref_Location l2){
				String loc1 = l1.getOriginal() != null ? l1.getOriginal() : "";
				String loc2 = l2.getOriginal() != null ? l2.getOriginal() : "";
				return (loc1.compareToIgnoreCase(loc2));
			}
				});
		
	}

	public static void loadLocation(){

		System.out.println("Reading Ref_Location");

		EntityManager em = getEm_ref_2();	
		Query q = em.createQuery("select a from Ref_Location a");
		ref_location  = q.getResultList();		

		ref_location21 = new HashMap<String, Ref_Location>();
		ref_location22 = new HashMap<String, Ref_Location>();
		ref_location31 = new HashMap<Integer, String>();

		int highest_ID = 0;
		int count = 0;

		for(Ref_Location f: ref_location){
			
			if(f.getOriginal() != null){
			
				ref_location21.put(f.getOriginal().trim().toLowerCase(), f);
				
				
				if(f.getLocationNo() > 0){
					
					if(f.getLocation() != null && f.getLocation().trim().length() > 0)
						ref_location31.put(f.getLocationNo(), f.getLocation().trim().toLowerCase());	
					else
						if(f.getMunicipality() != null && f.getMunicipality().trim().length() > 0)
							ref_location31.put(f.getLocationNo(), f.getMunicipality().trim().toLowerCase());	
					
				}
			
				if(f.getLocationID() > highest_ID)
					highest_ID = f.getLocationID();
				count++;
			}
			
		}

		Ref_Location.setCurrent_ID(highest_ID + 1);
		
		
		System.out.println("Read    Ref_Location " + count + " rows");
		
	}

	public static void loadProfession(){

		System.out.println("Reading Ref_Profession");

		EntityManager em = getEm_ref_2();	
		Query q = em.createQuery("select a from Ref_Profession a");
		ref_profession  = q.getResultList();	

		int highest_ID = 0;
		int count = 0;
		for(Ref_Profession p: ref_profession){
			if(p.getProfessionID() > highest_ID)
				highest_ID = p.getProfessionID();


			count++;
		}
		Ref_Profession.setCurrent_ID(highest_ID + 1); 
		System.out.println("Read    Ref_Profession " + count + " rows");

		Collections.sort(ref_profession, new Comparator<Ref_Profession>()
				{
			public int compare(Ref_Profession f1, Ref_Profession f2){
				String f01 = f1.getOriginal() != null ? f1.getOriginal() : "";
				String f02 = f2.getOriginal() != null ? f2.getOriginal() : "";
				return (f01.compareToIgnoreCase(f02));
			}
				});
		

	}

	public static void loadAddress(){

		System.out.println("Reading Ref_Address");
		//EntityManager em = getNewEm_ref();	
		EntityManager em = getEm_ref_2();
		Query q = em.createQuery("select a from Ref_Address a");
		ref_address  = q.getResultList();
		
		ref_address21 = new HashMap<String, Ref_Address>();
		ref_address22 = new HashMap<String, Ref_Address>();

		
		int highest_ID = 0;
		int count = 0;
		for(Ref_Address a: ref_address){
			
			if(a.getOriginal() != null && a.getOriginal().trim().length() > 0){
				
				ref_address21.put(a.getOriginal().trim(), a);
				
				if(a.getAddressID() > highest_ID)
					highest_ID = a.getAddressID();
				count++;
			}
		}

		Ref_Address.setCurrent_ID(highest_ID + 1); 
		System.out.println("Read    Ref_Address " + count + " rows");


	}



	public static void loadHousenumberOld(){

		System.out.println("Reading Ref_Housenumber");

		EntityManager em = getEm_ref_2();	

		Query q = em.createQuery("select a from Ref_Housenumber a");
		ref_housenumber  = q.getResultList();		

		int highest_ID = 0;
		int count = 0;

		if(ref_housenumber != null){
			for(Ref_Housenumber h: ref_housenumber){
				if(h.getHousenumberID() > highest_ID)
					highest_ID = h.getHousenumberID();
					count++;
			}
		}
		Ref_Housenumber.setCurrent_ID(highest_ID + 1); 
		System.out.println("Read    Ref_Housenumber " + count + " rows");

		Collections.sort(ref_housenumber, new Comparator<Ref_Housenumber>()
				{
			public int compare(Ref_Housenumber f1, Ref_Housenumber f2){
				String f01 = f1.getOriginal() != null ? f1.getOriginal() : "";
				String f02 = f2.getOriginal() != null ? f2.getOriginal() : "";
				return (f01.compareToIgnoreCase(f02));
			}
				});


	}

	public static void loadHousenumberadditionOld(){

		System.out.println("Reading Ref_Housenumberaddition");

		EntityManager em = getEm_ref_2();	
	
		Query q = em.createQuery("select a from Ref_Housenumberaddition a");
		ref_housenumberaddition  = q.getResultList();		

		int highest_ID = 0;
		int count = 0;
		if(ref_housenumberaddition != null){
			for(Ref_Housenumberaddition a: ref_housenumberaddition){
				if(a.getHousenumberadditionID() > highest_ID)
					highest_ID = a.getHousenumberadditionID();
				count++;
			}
		}
		Ref_Housenumberaddition.setCurrent_ID(highest_ID + 1); 
		System.out.println("Read    Ref_Housenumberaddition " + count + " rows");

		Collections.sort(ref_housenumberaddition, new Comparator<Ref_Housenumberaddition>()
				{
			public int compare(Ref_Housenumberaddition f1, Ref_Housenumberaddition f2){
				String f01 = f1.getOriginal() != null ? f1.getOriginal() : "";
				String f02 = f2.getOriginal() != null ? f2.getOriginal() : "";
				return (f01.compareToIgnoreCase(f02));
			}
				});
		


	}
	
	public static void loadHousenumberaddition(){

		System.out.println("Reading Ref_Housenumberaddition");

		EntityManager em = getEm_ref_2();	
		Query q = em.createQuery("select a from Ref_Housenumberaddition a");
		List<Ref_Housenumberaddition> ref_housenumberaddition  = q.getResultList();		

		int highest_ID = 0;
		int count = 0;

		ref_houseNumberAddition21 = new HashMap<String, Ref_Housenumberaddition>();
		ref_houseNumberAddition22 = new HashMap<String, Ref_Housenumberaddition>();
		
		for(Ref_Housenumberaddition f: ref_housenumberaddition){

			if(f.getOriginal() != null){
				ref_houseNumberAddition21.put(f.getOriginal().trim().toLowerCase(), f);

				if(f.getCurrent_ID() > highest_ID)
					highest_ID = f.getCurrent_ID();
				count++;

			}

		}
			
		Ref_Housenumberaddition.setCurrent_ID(highest_ID + 1);
		
		
		System.out.println("Read    Ref_Housenumberaddition " + count + " rows");

	}
	
	

	public static void loadHousenumber(){

		System.out.println("Reading Ref_Housenumber");

		EntityManager em = getEm_ref_2();	
		Query q = em.createQuery("select a from Ref_Housenumber a");
		List<Ref_Housenumber> ref_housenumber  = q.getResultList();		

		int highest_ID = 0;
		int count = 0;

		ref_houseNumber21 = new HashMap<String, Ref_Housenumber>();
		ref_houseNumber22 = new HashMap<String, Ref_Housenumber>();
		
		for(Ref_Housenumber f: ref_housenumber){

			if(f.getOriginal() != null){
				ref_houseNumber21.put(f.getOriginal().trim().toLowerCase(), f);

				if(f.getCurrent_ID() > highest_ID)
					highest_ID = f.getCurrent_ID();
				count++;

			}

		}
			
		Ref_Housenumber.setCurrent_ID(highest_ID + 1);
		
		
		System.out.println("Read    Ref_Housenumber " + count + " rows");

	}


	public static void loadMunicipality(){

		System.out.println("Reading Ref_Municipality");

		EntityManager em = getEm_ref_2();	
		Query q = em.createQuery("select a from Ref_Municipality a");
		ref_Municipality =  q.getResultList();		

		System.out.println("Read   ref_plaats " + ref_Municipality.size() + " rows");

		Collections.sort(ref_Municipality, new Comparator<Ref_Municipality>()
				{
			public int compare(Ref_Municipality municipality1, Ref_Municipality municipality2){
				if(municipality1.getCodeMunicipality() < municipality2.getCodeMunicipality())
					return -1;
				if(municipality1.getCodeMunicipality() > municipality2.getCodeMunicipality())
					return 1;
				return 0;
			}
				});


	}


	public static void loadRelation_B(){

		System.out.println("Reading ref_relation_b");
		EntityManager em = getEm_ref_2();	
		Query q = em.createQuery("select a from Ref_Relation_B a");
		ref_relation_b  = q.getResultList();

		int highest_ID = 0;
		int count = 0;
		for(Ref_Relation_B p: ref_relation_b){
			if(p.getRelationID() > highest_ID)
				highest_ID = p.getRelationID();
			count++;
		}	

		Ref_Relation_B.setCurrent_ID(highest_ID + 1); 
		System.out.println("Read    ref_relation_b " + count + " rows");

		Collections.sort(ref_relation_b, new Comparator<Ref_Relation_B>()
				{
			public int compare(Ref_Relation_B f1, Ref_Relation_B f2){
				String f01 = f1.getNederlands() != null ? f1.getNederlands() : "";
				String f02 = f2.getNederlands() != null ? f2.getNederlands() : "";
				return (f01.compareToIgnoreCase(f02));
			}
				});
		


	}

	public static void loadRelation_C(){

		System.out.println("Reading ref_relation_c");
		EntityManager em = getEm_ref_2();	
		Query q = em.createQuery("select a from Ref_Relation_C a where a.relation_A IS NOT NULL AND a.relation_B IS NOT NULL");
		ref_relation_c  = q.getResultList();

		int highest_ID = 0;
		int count = 0;
		for(Ref_Relation_C p: ref_relation_c){
			if(p.getRelationID() > highest_ID)
				highest_ID = p.getRelationID();
			count++;
		}	

		Ref_Relation_C.setCurrent_ID(highest_ID + 1); 
		System.out.println("Read    ref_relation_c " + count + " rows");
		Collections.sort(ref_relation_c, new Comparator<Ref_Relation_C>()
				{
			public int compare(Ref_Relation_C p1, Ref_Relation_C p2){
				Integer rel1A = new Integer(p1.getRelation_A());
				Integer rel1B = new Integer(p1.getRelation_B());
				Integer rel2A = new Integer(p2.getRelation_A());
				Integer rel2B = new Integer(p2.getRelation_B());

				if(rel1A < rel2A) return -1;
				if(rel1A > rel2A) return  1;
				if(rel1B < rel2B) return -1;
				if(rel1B > rel2B) return  1;
				
				return 0;
			}
				});


	}

	public static Ref_AINB getAINB(int key){


		Ref_AINB ainb = new Ref_AINB();
		ainb.setKeyToSourceRegister(key);


		int i = Collections.binarySearch(ref_AINB, ainb, new Comparator<Ref_AINB>()
				{
			public int compare(Ref_AINB ainb1, Ref_AINB ainb2){
				if(ainb1.getKeyToSourceRegister() < ainb2.getKeyToSourceRegister())
					return 1;
				if(ainb1.getKeyToSourceRegister() > ainb2.getKeyToSourceRegister())
					return -1;
				return 0;
			}
				});



		if(i >= 0){
			return(ref_AINB.get(i));
		}
		else
			return null;


	}

	public static Ref_KG getKG(String religion){
		
		Ref_KG kg = new Ref_KG();
		kg.setDenomination(religion.trim());


		int i = Collections.binarySearch(ref_KG, kg, new Comparator<Ref_KG>()
				{
			public int compare(Ref_KG kg1, Ref_KG kg2){
				return (kg1.getDenomination().compareToIgnoreCase(kg2.getDenomination()));
			}
				});

		if(i >= 0){
			return(ref_KG.get(i));

		}
		for(Ref_KG f0: ref_KG_x){
			if(f0.getDenomination().equalsIgnoreCase(religion))
				return f0;
		}
		return null;
	}

	public static void addKG(Ref_KG kg){

		//System.out.println("In addPrefix, origineel = " + prefix.getOriginal());

		
		if(kg == null || kg.getDenomination() == null || kg.getDenomination().trim().length() == 0)
			return; 

		//System.out.println("In addPrefix, origineel not null");

		
		if(getKG(kg.getDenomination()) != null)
			return;
		//System.out.println("In addPrefix, getPrefix = null");

		
		kg.setDenomination(kg.getDenomination().trim());
		//kg.setId_religion(Ref_KG.getCurrent_ID());
		kg.setNeedSave(true);
		kg.setSource("HSN");
	

		ref_KG_x.add(kg);

		if(ref_KG_x.size() >= 1000){
			
			 ref_KG.addAll(ref_KG_x);
			 ref_KG_x.clear();

			 Collections.sort(ref_KG, new Comparator<Ref_KG>()
			 {
			  	public int compare(Ref_KG p1, Ref_KG p2){
				return (p1.getDenomination().compareToIgnoreCase(p2.getDenomination()));
			 }});
			

		}

	}

	public static void truncateKG(Ref_KG p){

		String field = p.getDenomination();
		int allowedSize = Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "Ref_Religion", "original", "" + allowedSize);
			field = field.substring(0, allowedSize);
			p.setDenomination(field);
		}

	}


	
	
	public static Ref_GB getGB(int OPNumber){

		Ref_GB gb = new Ref_GB();
		gb.setKeyToRP(OPNumber);


		int i = Collections.binarySearch(ref_GB, gb, new Comparator<Ref_GB>()
				{
			public int compare(Ref_GB gb1, Ref_GB gb2){
				if(gb1.getKeyToRP() < gb2.getKeyToRP())
					return 1;
				if(gb1.getKeyToRP() > gb2.getKeyToRP())
					return -1;
				return 0;
			}
				});



		if(i >= 0){
			return(ref_GB.get(i));
		}
		else
			return null;
	}

	public static Ref_Municipality getMunicipality(int key){

		//System.out.println("Resolving 2");

		Ref_Municipality municipality = new Ref_Municipality();
		municipality.setCodeMunicipality(key);

		int i = Collections.binarySearch(ref_Municipality, municipality, new Comparator<Ref_Municipality>()
				{
			public int compare(Ref_Municipality municipality1, Ref_Municipality municipality2){
				if(municipality1.getCodeMunicipality() < municipality2.getCodeMunicipality())
					return -1;
				if(municipality1.getCodeMunicipality() > municipality2.getCodeMunicipality())
					return 1;
				return 0;
			}
				});

		//System.out.println("Resolving 2 , i = "+ i );

		
		
		if(i >= 0){
			return(ref_Municipality.get(i));
		}
		else
			return null;


	}

	public static Ref_FamilyName getFamilyName(String name){
		

		Ref_FamilyName nameStandardized = ref_famName21.get(name.trim().toLowerCase());

		if(nameStandardized != null){
			return nameStandardized;
		}
		else{	
			nameStandardized = ref_famName22.get(name.trim().toLowerCase());

			if(nameStandardized != null){
				return nameStandardized;
			}

		}

		return null;
		
		
	}
	
	public static Ref_FamilyName getFamilyNameOld(String name){

		if(name == null || name.trim().length() == 0)
			return null;
		
		if(ref_famName != null){

			Ref_FamilyName f = new Ref_FamilyName();
			f.setOriginal(name.trim());

			int i = Collections.binarySearch(ref_famName, f, new Comparator<Ref_FamilyName>()
					{
				public int compare(Ref_FamilyName f1, Ref_FamilyName f2){
					return (f1.getOriginal().compareToIgnoreCase(f2.getOriginal()));
				}
					});

			if(i >= 0)
				return(ref_famName.get(i));
		}
		for(Ref_FamilyName f0: ref_famName_x){
			if(f0.getOriginal().equalsIgnoreCase(name))
				return f0;
		}
		return null;
	}

	
	public static void addFamilyName(Ref_FamilyName f){
		
		
		String name = f.getOriginal().trim().toLowerCase();
		f.setOriginal(f.getOriginal().trim().toLowerCase());
		
		if(getFamilyName(name) == null){
			ref_famName22.put(name, f);
		}
		
		
		
	}

	public static void addFamilyNameOld(Ref_FamilyName f){

		if(f == null || f.getOriginal() == null || f.getOriginal().trim().length() == 0)
			return;
		
		if(getFamilyName(f.getOriginal()) != null)
			return;

		f.setOriginal(f.getOriginal().trim());

		//f.setLastNameID(Ref_FamilyName.getCurrent_ID());
		f.setNeedSave(true);

		ref_famName_x.add(f);

		if(ref_famName_x.size() >= 1000){
			
			ref_famName.addAll(ref_famName_x);
			ref_famName_x.clear();

			Collections.sort(ref_famName, new Comparator<Ref_FamilyName>()
					{
				public int compare(Ref_FamilyName f1, Ref_FamilyName f2){
					
					String original1 = f1.getOriginal() != null ? f1.getOriginal() : "";
					String original2 = f2.getOriginal() != null ? f2.getOriginal() : "";
					return (original1.compareToIgnoreCase(original2));
				}});
		}
	}

	public static void truncateFamilyName(Ref_FamilyName f){

		String field = f.getOriginal();
		int allowedSize = Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "REF_FAMILIENAAM", "ORIGINEEL" , "" + allowedSize);
			field = field.substring(0, allowedSize);
			f.setOriginal(field);
		}

	}



	public static Ref_FirstName getFirstNameOld(String name){

		if(name == null || name.trim().length() == 0)
			return null;
		
		if(ref_firstName != null){

			Ref_FirstName f = new Ref_FirstName();
			f.setOriginal(name);

			int i = Collections.binarySearch(ref_firstName, f, new Comparator<Ref_FirstName>()
					{
				public int compare(Ref_FirstName f1, Ref_FirstName f2){
					return (f1.getOriginal().compareToIgnoreCase(f2.getOriginal()));
				}
					});

			if(i >= 0)
				return(ref_firstName.get(i));
		}
		for(Ref_FirstName f0: ref_firstName_x){
			if(f0.getOriginal().equalsIgnoreCase(name))
				return f0;
		}
		return null;


	}

	public static Ref_FirstName getFirstName(String name){
		

		Ref_FirstName nameStandardized = ref_firstName21.get(name.trim().toLowerCase());

		if(nameStandardized != null){
			return nameStandardized;
		}
		else{	
			nameStandardized = ref_firstName22.get(name.trim().toLowerCase());

			if(nameStandardized != null){
				return nameStandardized;
			}

		}

		return null;
		
		
	}

	public static void addFirstNameOld(Ref_FirstName f){

		
		if(f == null || f.getOriginal() == null || f.getOriginal().trim().length() == 0)
			return;
		
		if(getFirstName(f.getOriginal()) != null)
			return;

		int x = Ref_FirstName.getCurrent_ID();

		f.setOriginal(f.getOriginal().trim());

		//f.setFirstNameID(x);
		f.setNeedSave(true);

		ref_firstName_x.add(f);

		if(ref_firstName_x.size() >= 1000){

			ref_firstName.addAll(ref_firstName_x);
			ref_firstName_x.clear();

			Collections.sort(ref_firstName, new Comparator<Ref_FirstName>()
					{
				public int compare(Ref_FirstName f1, Ref_FirstName f2){
					String original1 = f1.getOriginal() != null ? f1.getOriginal() : "";
					String original2 = f2.getOriginal() != null ? f2.getOriginal() : "";
					return (original1.compareToIgnoreCase(original2));

				}
					});
		}

	}
	
	public static void addFirstName(Ref_FirstName f){
		
		
		String name = f.getOriginal().trim().toLowerCase();
		f.setOriginal(f.getOriginal().trim().toLowerCase());
		
		if(getFirstName(name) == null){
			ref_firstName22.put(name, f);
		}
		
		
		
	}


	public static void truncateFirstName(Ref_FirstName f){

		String field = f.getOriginal();
		int allowedSize = Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "REF_VOORNAAM", "ORIGINEEL", "" + allowedSize);
			field = field.substring(0, allowedSize);
			f.setOriginal(field);
		}

	}




	public static Ref_Location getLocationOld(String location){

		if(location == null || location.trim().length() == 0)
			return null;

		location = location.trim();
		
		if(ref_location != null){
			Ref_Location r = new Ref_Location();
			r.setOriginal(location);

			int i = Collections.binarySearch(ref_location, r, new Comparator<Ref_Location>()
					{
				public int compare(Ref_Location l1, Ref_Location l2){
					return (l1.getOriginal().compareToIgnoreCase(l2.getOriginal()));
				}
					});

			if(i >= 0)
				return(ref_location.get(i));
		}
		for(Ref_Location l: ref_location_x){
			if(l.getOriginal().equalsIgnoreCase(location))
				return l;
		}
		return null;		
	}
	
	public static Ref_Location getLocation(String location){
		
		Ref_Location locationStandardized = ref_location21.get(location.trim().toLowerCase());

		if(locationStandardized != null){
			return locationStandardized;
		}
		else{	
			locationStandardized = ref_location22.get(location.trim().toLowerCase());

			if(locationStandardized != null){
				return locationStandardized;
			}

		}

		return null;


	}

	public static String getLocation(int locationNo){
		
		String locationStandardized = ref_location31.get(locationNo);
		return locationStandardized;


	}

	public static List<Ref_Location>  getLocations(){
		return ref_location;
	}


	public static void addLocationOld(Ref_Location l){
		
		
		//System.out.println("add location " + l.getOriginal());
		
		if(l == null || l.getOriginal() == null || l.getOriginal().trim().length() == 0)
			return;

		if(getLocation(l.getOriginal()) != null)
			return;

		l.setOriginal(l.getOriginal().trim());
		l.setLocationID(Ref_Location.getCurrent_ID());
		l.setNeedSave(true);
		ref_location_x.add(l);

		if(ref_location_x.size() >= 1000){

			ref_location.addAll(ref_location_x);
			ref_location_x.clear();

			Collections.sort(ref_location, new Comparator<Ref_Location>()
					{
				public int compare(Ref_Location l1, Ref_Location l2){
					String original1 = l1.getOriginal() != null ? l1.getOriginal() : "";
					String original2 = l2.getOriginal() != null ? l2.getOriginal() : "";
					return (original1.compareToIgnoreCase(original2));
				}});
		}
	}

	public static void addLocation(Ref_Location f){
		
		
		String name = f.getOriginal().trim().toLowerCase();
		f.setOriginal(f.getOriginal().trim().toLowerCase());
		
		if(getFamilyName(name) == null)
			ref_location22.put(name, f);
		
		
		
	}



	public static void truncateLocation(Ref_Location l){

		String field = l.getOriginal();
		int allowedSize = Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "REF_LOCATIE", "ORIGINEEL", "" + allowedSize);
			field = field.substring(0, allowedSize);
			l.setOriginal(field);
		}

	}

	public static Ref_Prefix getPrefix(String prefix){

		if(prefix == null || prefix.trim().length() == 0)
			return null;
		
		prefix = prefix.trim();

		//System.out.println("In getPrefix, prefix = " + prefix);
		//System.out.println("In getPrefix, list = ");
		
		//for(Ref_Prefix p1: ref_prefix_x)
			//System.out.println("        " + p1.getOriginal());
		
		
		if(ref_prefix != null){

			Ref_Prefix p = new Ref_Prefix();
			p.setOriginal(prefix);

			int i = Collections.binarySearch(ref_prefix, p, new Comparator<Ref_Prefix>()
					{
				public int compare(Ref_Prefix p1, Ref_Prefix p2){
					return (p1.getOriginal().compareToIgnoreCase(p2.getOriginal()));
				}
					});

			//System.out.println("In getPrefix, i = " + i);
			
			if(i >= 0)
				return(ref_prefix.get(i));
		}
		for(Ref_Prefix p: ref_prefix_x){
			//System.out.println(">>>>>>>>" + p.getOriginal() + ">>>>" + prefix);
			if(p.getOriginal().equalsIgnoreCase(prefix))
				return p;
		}
		return null;		

	}


	public static void addPrefix(Ref_Prefix prefix){

		//System.out.println("In addPrefix, origineel = " + prefix.getOriginal());

		
		if(prefix == null || prefix.getOriginal() == null || prefix.getOriginal().trim().length() == 0)
			return; 

		//System.out.println("In addPrefix, origineel not null");

		
		if(getPrefix(prefix.getOriginal()) != null)
			return;
		//System.out.println("In addPrefix, getPrefix = null");

		
		prefix.setOriginal(prefix.getOriginal().trim());
		//prefix.setPrefixID(Ref_Prefix.getCurrent_ID());
		prefix.setNeedSave(true);

		ref_prefix_x.add(prefix);

		if(ref_prefix_x.size() >= 1000){
			
			 ref_prefix.addAll(ref_prefix_x);
			 ref_prefix_x.clear();

			 Collections.sort(ref_prefix, new Comparator<Ref_Prefix>()
			 {
			  	public int compare(Ref_Prefix p1, Ref_Prefix p2){
					String original1 = p1.getOriginal() != null ? p1.getOriginal() : "";
					String original2 = p2.getOriginal() != null ? p2.getOriginal() : "";
					return (original1.compareToIgnoreCase(original2));

			 }});
			

		}

	}

	public static void truncatePrefix(Ref_Prefix p){

		String field = p.getOriginal();
		int allowedSize = Mediumstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "REF_PREFIX", "ORIGINEEL", "" + allowedSize);
			field = field.substring(0, allowedSize);
			p.setOriginal(field);
		}

	}



	public static Ref_Profession getProfession(String profession){

		if(profession == null || profession.trim().length() == 0)
			return null;

		profession = profession.trim();


		if(ref_profession != null){

			Ref_Profession r = new Ref_Profession();
			r.setOriginal(profession);

			int i = Collections.binarySearch(ref_profession, r, new Comparator<Ref_Profession>()
					{
				public int compare(Ref_Profession r1, Ref_Profession r2){        		
					return (r1.getOriginal().compareToIgnoreCase(r2.getOriginal()));
				}
					}); 

			if(i >= 0)
				return(ref_profession.get(i));
		}
		for(Ref_Profession p: ref_profession_x){
			if(p.getOriginal().equalsIgnoreCase(profession)){
				return p;
			}
		}
		return null;		
	}

	public static void addProfession(Ref_Profession profession){
		
		if(profession == null || profession.getOriginal() == null || profession.getOriginal().trim().length() == 0)
			return; 
		

		if(getProfession(profession.getOriginal()) != null){
			return;
		}
		
		profession.setOriginal(profession.getOriginal().trim());
		//profession.setProfessionID(Ref_Profession.getCurrent_ID());
		profession.setNeedSave(true);

		ref_profession_x.add(profession);
		
		if(ref_profession_x.size() >= 1000){
			 ref_profession.addAll(ref_profession_x);
			 ref_profession_x.clear();

			 Collections.sort(ref_profession, new Comparator<Ref_Profession>()
			 {
			  	public int compare(Ref_Profession p1, Ref_Profession p2){
					String original1 = p1.getOriginal() != null ? p1.getOriginal() : "";
					String original2 = p2.getOriginal() != null ? p2.getOriginal() : "";
					return (original1.compareToIgnoreCase(original2));

			 }});
		}
	}
	

	public static void truncateProfession(Ref_Profession p){

		String field = p.getOriginal();
		int allowedSize = XBigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "Ref_occupation", "original", "" + allowedSize);
			field = field.substring(0, allowedSize);
			p.setOriginal(field);
		}

	}




	public static Ref_Housenumber getHousenumberOld(String housenumber){

		if(housenumber == null || housenumber.trim().length() == 0)
			return null;

		housenumber = housenumber.trim();

		if(ref_housenumber != null){
			
			Ref_Housenumber h = new Ref_Housenumber();
			h.setOriginal(housenumber);

			int i = Collections.binarySearch(ref_housenumber, h, new Comparator<Ref_Housenumber>()
					{
				public int compare(Ref_Housenumber r1, Ref_Housenumber r2){        		
					return (r1.getOriginal().compareToIgnoreCase(r2.getOriginal()));
				}
					}); 


			if(i >= 0)
				return(ref_housenumber.get(i));
		}
		for(Ref_Housenumber p: ref_housenumber_x){

			if(p.getOriginal().equalsIgnoreCase(housenumber))
				return p;
		}
		return null;		
	}
	
	public static Ref_Housenumber getHousenumber(String name){
		

		Ref_Housenumber numberStandardized = ref_houseNumber21.get(name.trim().toLowerCase());

		if(numberStandardized != null){
			return numberStandardized;
		}
		else{	
			numberStandardized = ref_houseNumber22.get(name.trim().toLowerCase());

			if(numberStandardized != null){
				return numberStandardized;
			}

		}

		return null;
		
		
	}


	public static void addHousenumberOld(Ref_Housenumber housenumber){

		if(housenumber == null || housenumber.getOriginal() == null || housenumber.getOriginal().trim().length() == 0)
			return;

		
		if(getHousenumber(housenumber.getOriginal()) != null)
				return;
		
		housenumber.setHousenumberID(Ref_Housenumber.getCurrent_ID());
		housenumber.setOriginal(housenumber.getOriginal().trim());

		housenumber.setNeedSave(true);

		ref_housenumber_x.add(housenumber);
		
		if(ref_housenumber_x.size() >= 1000){
			
			ref_housenumber.addAll(ref_housenumber_x);
			ref_housenumber_x.clear();
			
			Collections.sort(ref_housenumber, new Comparator<Ref_Housenumber>()
					{
				public int compare(Ref_Housenumber h1, Ref_Housenumber h2){
					return (h1.getOriginal().compareToIgnoreCase(h2.getOriginal()));
				}
					});
			
		}
	}
	
	public static void addHousenumber(Ref_Housenumber housenumber){
		
		if(housenumber == null) return;		
		
		String name = housenumber.getOriginal().trim().toLowerCase();
		housenumber.setOriginal(housenumber.getOriginal().trim().toLowerCase());
		
		if(getHousenumber(name) == null){
			ref_houseNumber22.put(name, housenumber);
		}
		
	}


	public static void truncateHousenumber(Ref_Housenumber h){

		String field = h.getOriginal();
		int allowedSize = Smallstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "REF_HUISNUMMER", "ORIGINEEL", "" + allowedSize);
			field = field.substring(0, allowedSize);
			h.setOriginal(field);
		}

	}



	public static void addHousenumberadditionOld(Ref_Housenumberaddition housenumberaddition){

		if(housenumberaddition == null || housenumberaddition.getOriginal() == null || housenumberaddition.getOriginal().trim().length() == 0)
			return;

		if(getHousenumberaddition(housenumberaddition.getOriginal()) != null)
			return;

		housenumberaddition.setHousenumberadditionID(Ref_Housenumberaddition.getCurrent_ID());
		housenumberaddition.setNeedSave(true);

		ref_housenumberaddition_x.add(housenumberaddition);
		
		if(ref_housenumberaddition_x.size() >= 1000){
			
			ref_housenumberaddition.addAll(ref_housenumberaddition_x);
			ref_housenumberaddition_x.clear();
			
			Collections.sort(ref_housenumberaddition, new Comparator<Ref_Housenumberaddition>()
					{
				public int compare(Ref_Housenumberaddition h1, Ref_Housenumberaddition h2){
					return (h1.getOriginal().compareToIgnoreCase(h2.getOriginal()));
				}
					});
			
		}

		
	}

	public static void addHousenumberaddition(Ref_Housenumberaddition housenumberaddition){
		
		if(housenumberaddition == null) return;		
		
		String name = housenumberaddition.getOriginal().trim().toLowerCase();
		housenumberaddition.setOriginal(housenumberaddition.getOriginal().trim().toLowerCase());
		
		if(getHousenumberaddition(name) == null){
			ref_houseNumberAddition22.put(name, housenumberaddition);
		}
		
		
		
	}


	public static Ref_Housenumberaddition getHousenumberadditionOld(String housenumberaddition){

		if(housenumberaddition == null || housenumberaddition.trim().length() == 0)
			return null;

		housenumberaddition = housenumberaddition.trim();

		if(ref_housenumberaddition != null){
			
			Ref_Housenumberaddition h = new Ref_Housenumberaddition();
			h.setOriginal(housenumberaddition);

			int i = Collections.binarySearch(ref_housenumberaddition, h, new Comparator<Ref_Housenumberaddition>()
					{
				public int compare(Ref_Housenumberaddition r1, Ref_Housenumberaddition r2){        		
					return (r1.getOriginal().compareToIgnoreCase(r2.getOriginal()));
				}
					}); 


			if(i >= 0)
				return(ref_housenumberaddition.get(i));
		}
		for(Ref_Housenumberaddition p: ref_housenumberaddition_x){

			//System.out.println("" + p.getOriginal() + "   " + housenumberaddition + "  " + p.getOriginal().length() + "  " + housenumberaddition.length());
			if(p.getOriginal().equalsIgnoreCase(housenumberaddition))
				return p;
		}
		return null;		
	}
	
	public static Ref_Housenumberaddition getHousenumberaddition(String name){
		

		Ref_Housenumberaddition additionStandardized = ref_houseNumberAddition21.get(name.trim().toLowerCase());

		if(additionStandardized != null){
			return additionStandardized;
		}
		else{	
			additionStandardized = ref_houseNumberAddition22.get(name.trim().toLowerCase());

			if(additionStandardized != null){
				return additionStandardized;
			}

		}

		return null;
		
		
	}
	


	public static void truncateHousenumberaddition(Ref_Housenumberaddition a){

		
		System.out.println("---->"+ a.getOriginal());

		String field = a.getOriginal();
		int allowedSize = Smallstring;
		if(field != null && field.length() > allowedSize){
			//System.out.println("Too big!!");
			message("1500", "REF_HOUSENUMBERADDITION", "ORIGINEEL", "" + allowedSize);
			field = field.substring(0, allowedSize);
			a.setOriginal(field);
		}

	}

	public static Ref_Relation_B getRelation_B(String relation){

		if(relation == null || relation.trim().length() == 0)
			return null;
		
		relation = relation.trim();

		if(ref_relation_b != null){

			Ref_Relation_B r = new Ref_Relation_B();
			r.setNederlands(relation);

			int i = Collections.binarySearch(ref_relation_b, r, new Comparator<Ref_Relation_B>()
					{
				public int compare(Ref_Relation_B r1, Ref_Relation_B r2){
					return (r1.getNederlands().compareToIgnoreCase(r2.getNederlands()));
				}
					});

			//System.out.println("In getPrefix, i = " + i);
			
			if(i >= 0)
				return(ref_relation_b.get(i));
		}
		for(Ref_Relation_B r: ref_relation_b_x){
			//System.out.println(">>>>>>>>" + p.getOriginal() + ">>>>" + prefix);
			if(r.getNederlands().equalsIgnoreCase(relation))
				return r;
		}
		return null;		

	}

	public static Ref_Relation_B getRelation_B(int relation){

		for(Ref_Relation_B r: ref_relation_b){
			if(r.getKode() == relation)
				return r; 
		}

		return null;		

	}


	public static void addRelation_B(Ref_Relation_B relation){

		if(relation == null || relation.getNederlands() == null || relation.getNederlands().trim().length() == 0)
			return; 

		if(getRelation_B(relation.getNederlands()) != null)
			return;
		
		relation.setNederlands(relation.getNederlands().trim());
		relation.setNeedSave(true);

		ref_relation_b_x.add(relation);

		if(ref_relation_b_x.size() >= 1000){
			
			 ref_relation_b.addAll(ref_relation_b_x);
			 ref_relation_b_x.clear();

			 Collections.sort(ref_relation_b, new Comparator<Ref_Relation_B>()
			 {
			  	public int compare(Ref_Relation_B r1, Ref_Relation_B r2){
				return (r1.getNederlands().compareToIgnoreCase(r2.getNederlands()));
			 }});
			

		}

	}

	public static int[] getRelation_C(int relationA, int relationB){

		String rA = "" + relationA;
		String rB = "" + relationB;
		
		for(Ref_Relation_C r: ref_relation_c){
			if(r.getRelation_A().trim().equalsIgnoreCase(rA) && r.getRelation_B().trim().equalsIgnoreCase(rB)){
				String [] a = r.getRelation_M().split("/");
				int[] b = new int[a.length];
				for(int i = 0; i < a.length; i++)
					b[i] = new Integer(a[i]); 
				return b;
			}
		}

		return null;		

	}

	
	
	public static void truncateRelationB(Ref_Relation_B p){

		String field = p.getNederlands();
		int allowedSize = Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "REF_RELATION_B", "NEDERLANDS", "" + allowedSize);
			field = field.substring(0, allowedSize);
			p.setNederlands(field);
		}

	}


	public static Ref_Address getAddress2(String address){
		

		Ref_Address addressStandardized = ref_address21.get(address.trim());

		if(addressStandardized != null){
			return addressStandardized;
		}
		else{	
			addressStandardized = ref_address22.get(address.trim());

			if(addressStandardized != null){
				return addressStandardized;
			}

		}

		return null;
		
		
	}
	
	public static void addAddress2(Ref_Address a){
		
		//System.out.println("Add address2, count = " + ref_address22.size());
		
		a.setOriginal(a.getOriginal().trim());
		
		if(getAddress2(a.getOriginal()) == null)
			ref_address22.put(a.getOriginal(), a);
		
		
		
	}


	

	public static Ref_Address getAddress(String street, String quarter, String place, String boat, String berth, String institution, String landlord, String other){

		//System.out.println("Get Address");

		//System.out.println("Getting address  ");
		//System.out.println(street);
		//System.out.println(quarter);
		//System.out.println(place);
		//System.out.println(boat);
		//System.out.println(berth);
		//System.out.println(landlord);
		//System.out.println(institution);
		//System.out.println(other);

		
		
		for(Ref_Address ra: ref_address){

			String streetR =      ra.getStreetOriginal();
			String quarterR =     ra.getQuarterOriginal();
			String placeR =       ra.getPlaceOriginal();
			String boatR =        ra.getBoatOriginal();
			String berthR =       ra.getBerthOriginal();
			String institutionR = ra.getInstitutionOriginal(); 
			String landlordR =    ra.getLandlordOriginal();  
			String otherR =       ra.getOtherOriginal();


			if(compareElements(street, streetR)  		  != true) continue;				
			if(compareElements(quarter, quarterR) 		  != true) continue; 
			if(compareElements(place, placeR)     		  != true) continue; 
			if(compareElements(boat, boatR)       		  != true) continue; 
			if(compareElements(berth, berthR)     	      != true) continue;
			if(compareElements(institution, institutionR) != true) continue; 
			if(compareElements(landlord, landlordR)       != true) continue; 
			if(compareElements(other, otherR)             != true) continue; 


			return ra;

		}

		for(Ref_Address ra: ref_address_x){

			String streetR =      ra.getStreetOriginal();
			String quarterR =     ra.getQuarterOriginal();
			String placeR =       ra.getPlaceOriginal();
			String boatR =        ra.getBoatOriginal();
			String berthR =       ra.getBerthOriginal();
			String institutionR = ra.getInstitutionOriginal(); 
			String landlordR =    ra.getLandlordOriginal();  
			String otherR =       ra.getOtherOriginal();


			if(compareElements(street, streetR)  		  != true) continue;				
			if(compareElements(quarter, quarterR) 		  != true) continue; 
			if(compareElements(place, placeR)     		  != true) continue; 
			if(compareElements(boat, boatR)       		  != true) continue; 
			if(compareElements(berth, berthR)     	      != true) continue;
			if(compareElements(institution, institutionR) != true) continue; 
			if(compareElements(landlord, landlordR)       != true) continue; 
			if(compareElements(other, otherR)             != true) continue; 


			return ra;

		}

		return null;
	}

	private static boolean compareElements(String s1, String s2){
		
		//System.out.println("s1 = " + s1 + " s2 = " + s2);

		if(s1 == null && s2 == null)
			return true;
		

		if(s1 != null && s2 != null && s1.trim().equalsIgnoreCase(s2.trim()) == true)
			return true;

		return false; 

	}
	public static void addAddress(Ref_Address a){

		if(getAddress(a.getStreetOriginal(), a.getQuarterOriginal(), a.getPlaceOriginal(), a.getBoatOriginal(), a.getBerthOriginal(),
				a.getInstitutionOriginal(), a.getLandlordOriginal(), a.getOtherOriginal()) != null)
			return;

		//System.out.println("adding address  ");
		//System.out.println(a.getStreetOriginal());
		//System.out.println(a.getQuarterOriginal());
		//System.out.println(a.getPlaceOriginal());
		//System.out.println(a.getBoatOriginal());
		//System.out.println(a.getBerthOriginal());
		//System.out.println(a.getLandlordOriginal());
		//System.out.println(a.getInstitutionOriginal());
		//System.out.println(a.getOtherOriginal());
		
		//int x = 3/0;
		
		
		a.setNeedSave(true);
		a.setAddressID(Ref_Address.getCurrent_ID());
		ref_address_x.add(a);
	}

	public static void truncateAddress(Ref_Address a){

		String field = a.getOriginal();
		int allowedSize = Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "REF_ADRES", "STREET_OR", "" + allowedSize);
			field = field.substring(0, allowedSize);
			a.setStreetOriginal(field);
		}

		field = a.getStreetOriginal();
		allowedSize = Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "REF_ADRES", "STREET_OR", "" + allowedSize);
			field = field.substring(0, allowedSize);
			a.setStreetOriginal(field);
		}

		field = a.getQuarterOriginal();
		allowedSize = Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "REF_ADRES", "QUARTER_OR", "" + allowedSize);
			field = field.substring(0, allowedSize);
			a.setQuarterOriginal(field);
		}

		field = a.getPlaceOriginal();
		allowedSize = Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "REF_ADRES", "PLACE_OR", "" + allowedSize);
			field = field.substring(0, allowedSize);
			a.setPlaceOriginal(field);
		}

		field = a.getBoatOriginal();
		allowedSize = Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "REF_ADRES", "BOAT_OR", "" + allowedSize);
			field = field.substring(0, allowedSize);
			a.setBoatOriginal(field);
		}

		field = a.getBerthOriginal();
		allowedSize = Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "REF_ADRES", "BERTH_OR", "" + allowedSize);
			field = field.substring(0, allowedSize);
			a.setBerthOriginal(field);
		}

		field = a.getInstitutionOriginal();
		allowedSize = Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "REF_ADRES", "INSTIT_OR", "" + allowedSize);
			field = field.substring(0, allowedSize);
			a.setInstitutionOriginal(field);
		}

		field = a.getLandlordOriginal();
		allowedSize = Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "REF_ADRES", "LANDLORD_OR", "" + allowedSize);
			field = field.substring(0, allowedSize);
			a.setLandlordOriginal(field);
		}

		field = a.getOtherOriginal();
		allowedSize = Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "REF_ADRES", "OTHER_OR", "" + allowedSize);
			field = field.substring(0, allowedSize);
			a.setOtherOriginal(field);
		}

		field = a.getOldAddressOriginal();
		allowedSize = Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "REF_ADRES", "OA_OR", "" + allowedSize);
			field = field.substring(0, allowedSize);
			a.setOldAddressOriginal(field);
		}

	}


	public static void truncate(){

		
		if(ref_famName22 != null)
			for(Ref_FamilyName x: ref_famName22.values())
				Ref.truncateFamilyName(x);
					
		
        if(ref_firstName != null)
        	for(Ref_FirstName x: ref_firstName)			
        		Ref.truncateFirstName(x);
		for(Ref_FirstName x: ref_firstName_x)			
			Ref.truncateFirstName(x);

		if(ref_prefix != null)
			for(Ref_Prefix x: ref_prefix)			
				Ref.truncatePrefix(x);
		for(Ref_Prefix x: ref_prefix_x)			
			Ref.truncatePrefix(x);

		if(ref_location21 != null)
			for(Ref_Location x: ref_location21.values())
				Ref.truncateLocation(x);
	//	if(ref_location22 != null)
			for(Ref_Location x: ref_location22.values())
				Ref.truncateLocation(x);

		if(ref_profession != null)
			for(Ref_Profession x: ref_profession)
				Ref.truncateProfession(x);
		for(Ref_Profession x: ref_profession_x)
			Ref.truncateProfession(x);

		if(ref_address22 != null)
			for(Ref_Address x: ref_address22.values())
				Ref.truncateAddress(x);

		
		if(ref_houseNumber22 != null)
			for(Ref_Housenumber x: ref_houseNumber22.values())
				Ref.truncateHousenumber(x);

		
		if(ref_houseNumberAddition22 != null)
			for(Ref_Housenumberaddition x: ref_houseNumberAddition22.values())
				Ref.truncateHousenumberaddition(x);

		if(ref_KG != null)
			for(Ref_KG x: ref_KG)
				Ref.truncateKG(x);
		for(Ref_KG x: ref_KG_x)
			Ref.truncateKG(x);

		if(ref_relation_b != null)
			for(Ref_Relation_B x: ref_relation_b)
				Ref.truncateRelationB(x);
		for(Ref_Relation_B x: ref_relation_b_x)
			Ref.truncateRelationB(x);

	}

	public static void finalise(){

		System.out.println("Started saving reference tables");
		EntityManager em = getEm_ref_2();	
		
		em.getTransaction().begin();   
		
		
		truncate();
		
		int count = 0;
		

		// Address
		
		count = 0;
		//for(Ref_Address x: ref_address)
		//	if(x.getNeedSave() == true){
		//		em.persist(x);
		//		count++;
		//	}
		
		//for(Ref_Address x: ref_address_x){
		//	if(x.getNeedSave() == true){
		//		em.persist(x);
		//		count++;
		//	}
		//}
		
		
		//System.out.println("ref_address22 count = "+ ref_address22.size());
		
		if(ref_address22 != null){
			for (Ref_Address ref_address : ref_address22.values()) {
				count++;
				em.persist(ref_address);
				//System.out.println(ref_address.getOriginal());

			}
			
			ref_address22.clear();
			
		}
		

		System.out.println("Saved " + count + " rows to Ref_Address");

		
		// Housenumber
		
		
		count = 0;
		
		if(ref_houseNumber22 != null){
			for (Ref_Housenumber ref_hn : ref_houseNumber22.values()) {
				count++;
				em.persist(ref_hn);

			}
			ref_houseNumber22.clear();
		}
		System.out.println("Saved " + count + " rows to ref_housenumber");


		



		// Housenumberaddition
		
		count = 0;
		
		if(ref_houseNumberAddition22 != null){
			for (Ref_Housenumberaddition ref_hna : ref_houseNumberAddition22.values()) {
				count++;
				em.persist(ref_hna);

			}
			ref_houseNumberAddition22.clear();
		}
		System.out.println("Saved " + count + " rows to ref_housenumberaddition");

		
        // FamilyName
		
		
		
		/* new start */
		
		count = 0;
		
		
		if(ref_famName22 != null){
			for (Ref_FamilyName ref_famname : ref_famName22.values()) {
				count++;
				em.persist(ref_famname);

			}
			ref_famName22.clear();
		}
		System.out.println("Saved " + count + " rows to Ref_FamilyName");
		
		/* new end */
		
		
		/*
		if(ref_famName != null)
			for(Ref_FamilyName x: ref_famName)
				if(x.getNeedSave() == true){
					em.persist(x); 
					count++;
				}
		
		for(Ref_FamilyName x: ref_famName_x){
			if(x.getNeedSave() == true){
				em.persist(x); 
				count++;
			}
		}
		ref_famName_x.clear();
		System.out.println("Saved " + count + " rows to Ref_FamilyName");

		*/
		
		
		
		// FirstName

		if(ref_firstName22 != null){
			for (Ref_FirstName ref_firstname : ref_firstName22.values()) {
				count++;
				em.persist(ref_firstname);

			}
			ref_firstName22.clear();
		}
		System.out.println("Saved " + count + " rows to Ref_FirstName");

		
		// Prefix

		count = 0;
		if(ref_prefix != null)
			for(Ref_Prefix x: ref_prefix)
				if(x.getNeedSave() == true){
					em.persist(x); 
					count++;
				}
		
		for(Ref_Prefix x: ref_prefix_x){	
			if(x.getNeedSave() == true){
				em.persist(x);
				count++;
			}
		}
		ref_prefix_x.clear();
		System.out.println("Saved " + count + " rows to Ref_Prefix");


		
		// Profession
		
		count = 0;
		if(ref_profession != null)
			for(Ref_Profession x: ref_profession)
				if(x.getNeedSave() == true){
					em.persist(x);
					count++;
				}
		
		for(Ref_Profession x: ref_profession_x){
			if(x.getNeedSave() == true){
				em.persist(x);
				count++;
			}
		}
		ref_profession_x.clear();
		System.out.println("Saved " + count + " rows to Ref_occupation");


		// Religion
		
		count = 0;
		if(ref_KG != null)
			for(Ref_KG x: ref_KG)
				if(x.getNeedSave() == true){
					em.persist(x);
					count++;
				}
		
		for(Ref_KG x: ref_KG_x){
			if(x.getNeedSave() == true){
				em.persist(x);
				count++;
			}
		}
		ref_KG_x.clear();
		System.out.println("Saved " + count + " rows to Ref_religion");

		// Relation_B
		
		count = 0;
		if(ref_relation_b != null)
			for(Ref_Relation_B x: ref_relation_b)
				if(x.getNeedSave() == true){
					System.out.println(x.getNederlands() + "         " + x.getEngels());
					em.persist(x);
					count++;
				}
		
		for(Ref_Relation_B x: ref_relation_b_x){
			if(x.getNeedSave() == true){
				System.out.println(x.getNederlands() + "         " + x.getEngels());
				em.persist(x);
				count++;
			}
		}
		ref_relation_b_x.clear();
		System.out.println("Saved " + count + " rows to Ref_relation_b");

		// Location

		//count = 0;
		//if(ref_location != null)
		//	for(Ref_Location x: ref_location)
		//		if(x.getNeedSave() == true){
		//			em.persist(x); 
		//			count++;
		//		}
		//
		//for(Ref_Location x: ref_location_x){
		//	if(x.getNeedSave() == true){
		//		em.persist(x); 
		//		count++;
		//	}
		//}
		//ref_location_x.clear();
		//
		//System.out.println("Saved " + count + " rows to Ref_Location");

		/* new start */
		
		count = 0;
		
		
		if(ref_location22 != null){
			for (Ref_Location ref_location : ref_location22.values()) {
				count++;
				em.persist(ref_location);

			}
			ref_location22.clear();
		}
		System.out.println("Saved " + count + " rows to Ref_Location");
		
		/* new end */
		



		
		em.getTransaction().commit();
		em.clear();
		
		
		
		
		System.out.println("Finished saving reference tables");


	}

	private static void message(String number, String... fills){

		//	Message m = new Message(number);	
		//	m.setKeyToRP(-1);

		//	m.save(fills); 


	}

	public static EntityManager getNewEm_ref(){

		EntityManager em = getFactory_ref().createEntityManager();    	
		return(em);

	}

	public static EntityManager getEm_ref() {
		return em_ref;
	}

	public static void setEm_ref(EntityManager em_ref) {
		Ref.em_ref = em_ref;
	}

	public static EntityManagerFactory getFactory_ref() {
		return factory_ref;
	}

	public static void setFactory_ref(EntityManagerFactory factory_ref) {
		Ref.factory_ref = factory_ref;
	}

	public static EntityManagerFactory getFactory_ref_2() {
		return factory_ref_2;
	}

	public static void setFactory_ref_2(EntityManagerFactory factory_ref_2) {
		Ref.factory_ref_2 = factory_ref_2;
	}

	public static EntityManager getEm_ref_2() {
		return em_ref_2;
	}

	public static void setEm_ref_2(EntityManager em_ref_2) {
		Ref.em_ref_2 = em_ref_2;
	}


}
