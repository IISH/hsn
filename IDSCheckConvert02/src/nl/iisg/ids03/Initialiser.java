/*
* Naam:    Initialiser
* Version: 0.1
* Author:  Cor Munnik
* Copyright
*/
package nl.iisg.ids03;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import nl.iisg.hsncommon.DBFHandler;
import nl.iisg.ref.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
/**
 * 
 * This class handles the initialization of the objects used in this application
 * 
 *
 */
public class Initialiser {
	
	/**
	 * This routine creates the following tables (if they do not exist)
	 * 
	 * b2
	 * b3
	 * b4
	 * b6
	 * 
	 * b2_st
	 * b311_st
	 * b312_st
	 * b32_st
	 * b33_st
	 * b34_st
	 * b35_st
	 * b36_st
	 * b37_st
	 * b4_st
	 * b6_st
	 * 
	 * Ref_Address  
	 * Ref_FamilyName  
	 * Ref_FirstName  
	 * Ref_GB  
	 * Ref_Housenumber  
	 * Ref_Housenumberaddition  
	 * Ref_KG  
	 * Ref_Location  
	 * Ref_Prefix  
	 * Ref_Profession  
	 *
	 * 
	 * This routine loads the following tables:
	 * 
	 * B*-tables:     Person objects               - for all B2 rows
	 *                PersonDynamic  objects       - for all B3 rows    
	 *                Registration objects         - for all B4 rows
	 *                RegistrationAddress objects  - for all B6 rows
	 *                
	 * Reference:     Ref_AINB                     - from ainb file (gegevens over registers)
	 *                Ref_GB                       - from GB file (geboortebewijzen)  
	 *                Ref_KG                       - from KG file (godsdienst)
	 *                Ref_FamilyName               - from ref_familienaam file (gestandaardiseerde achternamen)
	 *                Ref_FirstName                  
	 *                Ref_Prefix
	 *                Ref_Location
     *                Ref_Profession
     *                Ref_Address   	
     *                Ref_Huisnummer
     *                Ref_Huisnummertoevoeging
	 * 
	 * @param inputDirectory
	 * @param ops
	 * @param persons
	 * @param personsDynamic
	 * @param registrations
	 * @param registrationAddresses
	 */

	public void createOriginalBTables(){
		
		System.out.println("Start creating or resetting B-Tables");
		
		try{
			EntityManager em = Utils.getEm_original_tabs();
			em.getTransaction().begin();
			
			Query query = em.createNativeQuery(CreateOriginalBTables.B2);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateOriginalBTables.B3);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateOriginalBTables.B4);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateOriginalBTables.B6);  
			query.executeUpdate();  
			
			em.getTransaction().commit();
            em.clear();

			em.getTransaction().begin();
			
			query = em.createNativeQuery(CreateOriginalBTables.B2_TRUNCATE);
			query.executeUpdate();  
			query = em.createNativeQuery(CreateOriginalBTables.B3_TRUNCATE);
			query.executeUpdate();  
			query = em.createNativeQuery(CreateOriginalBTables.B4_TRUNCATE);
			query.executeUpdate();  
			query = em.createNativeQuery(CreateOriginalBTables.B6_TRUNCATE);
			query.executeUpdate();  

			
			em.getTransaction().commit();
            em.clear();

			
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}

		System.out.println("Finished creating or resetting B-Tables");

		}
		

	
	public void createBTables(){
		
		System.out.println("Start creating or resetting B_ST-Tables");
		
		try{
			EntityManager em = Utils.getEm_tabs();
			em.getTransaction().begin();
			
			Query query = em.createNativeQuery(CreateBTables.B2_ST);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateBTables.B311_ST);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateBTables.B312_ST);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateBTables.B32_ST);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateBTables.B33_ST);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateBTables.B34_ST);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateBTables.B35_ST);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateBTables.B36_ST);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateBTables.B37_ST);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateBTables.B4_ST);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateBTables.B6_ST);  
			query.executeUpdate();  
			
			em.getTransaction().commit();
            em.clear();

			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

		
		resetBTables();
		
		
		System.out.println("Finished creating or resetting B_ST-Tables");

		}
	private void resetBTables(){
		
		
		try{
			
			EntityManager em = Utils.getEm_tabs();
			em.getTransaction().begin();

			Query query = em.createNativeQuery(CreateBTables.B2_ST_TRUNCATE);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateBTables.B311_ST_TRUNCATE);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateBTables.B312_ST_TRUNCATE);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateBTables.B32_ST_TRUNCATE);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateBTables.B33_ST_TRUNCATE);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateBTables.B34_ST_TRUNCATE);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateBTables.B35_ST_TRUNCATE);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateBTables.B36_ST_TRUNCATE);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateBTables.B37_ST_TRUNCATE);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateBTables.B4_ST_TRUNCATE);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateBTables.B6_ST_TRUNCATE);  
			query.executeUpdate();
			
			em.getTransaction().commit();
            em.clear();

			
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}


		}
		
	public void createMTables(){
		
		System.out.println("Start creating or resetting Message Tables");
		
		try{
			
			EntityManager em = Utils.getEm_log();
			em.getTransaction().begin();

			Query query = em.createNativeQuery(CreateMTables.BFOUT1FT); 
			query.executeUpdate();
			query = em.createNativeQuery(CreateMTables.BFOUT4FT); 
			query.executeUpdate();

			em.getTransaction().commit();
            em.clear();

			

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

		
		resetMTables();
		
		System.out.println("Finished creating or resetting Message Tables");

		}
		
	private void resetMTables(){
		
		
		try{
			EntityManager em = Utils.getEm_log();
			em.getTransaction().begin();

			Query query = Utils.getEm_log().createNativeQuery(CreateMTables.BFOUT1FT_TRUNCATE); 
			query.executeUpdate();
			query = Utils.getEm_log().createNativeQuery(CreateMTables.BFOUT4FT_TRUNCATE); 
			query.executeUpdate();
			
			em.getTransaction().commit();
            em.clear();

			

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}


		}
		
	public void createRTables(){
		
		System.out.println("Start creating Reference Tables");
		
		Ref.createRefTables();

		System.out.println("Finished creating Reference Tables");

		}
		
	
	public void loadTables(String inputDirectory, List<Person> persons, List<PersonDynamic> personsDynamic, List<Registration> registrations, List<RegistrationAddress> registrationAddresses){
		
				List<Person> p = Utils.createObjects("nl.iisg.ids03.Person",  inputDirectory);
				persons.addAll(p);
				Collections.sort(persons, new ComparatorPerson()); 
				
				
				List<PersonDynamic> pd = Utils.createObjects("nl.iisg.ids03.PersonDynamic",  inputDirectory);
				personsDynamic.addAll(pd);
				Collections.sort(personsDynamic, new ComparatorPersonDynamic()); 
				
				List<Registration> r = Utils.createObjects("nl.iisg.ids03.Registration",  inputDirectory);
				registrations.addAll(r);
				Collections.sort(registrations, new ComparatorRegistration()); 
				
				List<RegistrationAddress> ra = Utils.createObjects("nl.iisg.ids03.RegistrationAddress",  inputDirectory);
				registrationAddresses.addAll(ra);
				Collections.sort(registrationAddresses, new ComparatorRegistrationAddress()); 
				
				// Load the reference tables

				System.out.println("Load reference tables"); 
				
				Ref.loadAINB();
				Ref.loadGB();
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
				
				
	       }
	
	public void loadTables2(String inputDirectory, 
			List<OP> ops, List<Person> persons, List<PersonDynamic> personsDynamic, List<Registration> registrations, List<RegistrationAddress> registrationAddresses){

		try {                                                                                

			// Load B2, B3, B4, B6	

			InputStream inputStream  = new FileInputStream(inputDirectory + "/b2.dbf");
			DBFReader reader = new DBFReader( inputStream); 
			int numberOfFields = reader.getFieldCount();
			
			String [] fieldnames = new String[numberOfFields];
			byte   [] fieldtypes = new byte[numberOfFields]; 
			
			for(int i=0; i<numberOfFields; i++){
		        fieldnames[i] = reader.getField(i).getName();
		        fieldtypes[i] = reader.getField(i).getDataType();
			}
          

			Object [] rowObjects;

			int count = 0;
			
			System.out.println("Reading " + inputDirectory + "\\B2.DBF");			
			while( (rowObjects = reader.nextRecord()) != null) {
				Person p = new Person(rowObjects, fieldnames, fieldtypes);       
				persons.add(p);
				count++;
			}
			System.out.println("Read    " + inputDirectory + "/B2.DBF, " + count + " rows");

			Collections.sort(persons, new ComparatorPerson()); 


			inputStream  = new FileInputStream(inputDirectory + "\\b3.dbf");
			reader = new DBFReader( inputStream);
			
			numberOfFields = reader.getFieldCount();
			
			fieldnames = new String[numberOfFields];
			fieldtypes = new byte[numberOfFields]; 
			
			for(int i=0; i<numberOfFields; i++){
		        fieldnames[i] = reader.getField(i).getName();
		        fieldtypes[i] = reader.getField(i).getDataType();
			}
          
			
			System.out.println("Reading " + inputDirectory + "/B3.DBF");
            count = 0;
			while( (rowObjects = reader.nextRecord()) != null) {
				PersonDynamic pd = new PersonDynamic(rowObjects, fieldnames, fieldtypes);
				personsDynamic.add(pd);
				count++;

			}
			System.out.println("Read    " + inputDirectory + "\\B3.DBF, " + count + " rows");			
			Collections.sort(personsDynamic, new ComparatorPersonDynamic()); 

			inputStream  = new FileInputStream(inputDirectory + "/b4.dbf");
			reader = new DBFReader( inputStream);
			
			numberOfFields = reader.getFieldCount();
			
			fieldnames = new String[numberOfFields];
			fieldtypes = new byte[numberOfFields]; 
			
			for(int i=0; i<numberOfFields; i++){
		        fieldnames[i] = reader.getField(i).getName();
		        fieldtypes[i] = reader.getField(i).getDataType();
			}
			
			
			System.out.println("Reading " + inputDirectory + "\\B4.DBF");			
            count = 0;

			while( (rowObjects = reader.nextRecord()) != null) {
				Registration r = new Registration(rowObjects, fieldnames, fieldtypes);
				registrations.add(r);
				count++;
			}
			System.out.println("Read    " + inputDirectory + "\\B4.DBF, " + count + " rows");			
			Collections.sort(registrations, new ComparatorRegistration()); 


			inputStream  = new FileInputStream(inputDirectory + "/b6.dbf");
			reader = new DBFReader( inputStream);
			
			numberOfFields = reader.getFieldCount();
			
			fieldnames = new String[numberOfFields];
			fieldtypes = new byte[numberOfFields]; 
			
			for(int i=0; i<numberOfFields; i++){
		        fieldnames[i] = reader.getField(i).getName();
		        System.out.println(fieldnames[i]);
		        fieldtypes[i] = reader.getField(i).getDataType();
			}
			
			System.out.println("Reading " + inputDirectory + "\\B6.DBF");			

            count = 0;

			while( (rowObjects = reader.nextRecord()) != null) {
				if(!rowObjects[6].toString().equals("0.0")){
					System.out.println(rowObjects[6]);
     				RegistrationAddress ra = new RegistrationAddress(rowObjects, fieldnames, fieldtypes);
				}
				//registrationAddresses.add(ra);
				//if(ra.getKeyToRegistrationPersons() != 0)
					//System.out.println("B2RNBG = " + ra.getKeyToRegistrationPersons());
				count++;
			}
			System.out.println("Read    " + inputDirectory + "\\B6.DBF, " + count + " rows");			
			Collections.sort(registrationAddresses, new ComparatorRegistrationAddress()); 


			inputStream.close();
		}
		catch( DBFException e) {

			System.out.println( e.getMessage());
		}
		catch( IOException e) {

			System.out.println( e.getMessage());
		}
		
		// create output tables Not yet
		
		//if(!createOutputTables())
		//	System.exit(0);
		
		// Load the reference tables

		System.out.println("Load reference tables"); 
		
		Ref.loadAINB();
		Ref.loadGB();
		Ref.loadKG();

		Ref.loadFamName();
		Ref.loadFirstName();
		Ref.loadPrefix();
		Ref.loadLocation();
		Ref.loadProfession();
		Ref.loadAddress();
		Ref.loadHousenumber();
		Ref.loadHousenumberaddition(); 
		
	}
	    
	/**
	 * 
	 * This routine saves the B*-tables in MySQL tables
	 * 
	 * @param persons
	 * @param personsDynamic
	 * @param registrations
	 * @param registrationAddresses
	 */
	
	public void saveBTables(List<Person> persons, List<PersonDynamic> personsDynamic, List<Registration> registrations, List<RegistrationAddress> registrationAddresses){
		
		System.out.println("Start saving B-Tables");
		
		try{
			EntityManager em = Utils.getEm_original_tabs();
			em.getTransaction().begin();
		
			
			for(Person p: persons)
				em.persist(p);
				
			for(PersonDynamic pd: personsDynamic)
				em.persist(pd);
				
			for(Registration r: registrations)
				em.persist(r);
				
			for(RegistrationAddress ra: registrationAddresses){
				
				//System.out.println(ra.getKeyToSourceRegister() + "-" + ra.getYearEntryHead() + "-" + ra.getMonthEntryHead() + "-" +
					//ra.getDayEntryHead() + "-" + ra.getKeyToRP() + "-" + ra.getKeyToRegistrationPersons() + "-" + ra.getSequenceNumberToAddresses()
				//);
				
				em.persist(ra);
			}
				
			em.getTransaction().commit();
            em.clear();

			//em.close(); // don't need this em anymore

		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}

		System.out.println("Finished saving B-Tables");

		
		
		
		
		
	}

	
	/**
	 * This routine sorts the Person, PersonDynamic, Registration and RegistrationAddress objects
	 * Then, it creates the OP list and links OP to Registrations, Registrations to RegistrationAddresses and Persons 
	 * and Persons to PersonDynamics.
	 * 
	 * 
	 * @param ops
	 * @param persons
	 * @param personsDynamic
	 * @param registrations
	 * @param registrationAddresses
	 * 
	 */
	public void linkObjects(List<OP> ops, List<Person> persons, List<PersonDynamic> personsDynamic, List<Registration> registrations, List<RegistrationAddress> registrationAddresses){

		// Link Objects:
		//  OP <-> Registration
		//  Registration <-> Person
		//  Registration <-> RegistrationAddress
		//  Person <-> PersonDynamic       


		int i_p = 0;
		int i_ra = 0;
		int i_pd = 0;
		int i_r = 0;
		int i_o = 1;
		
		// create OP list from registrations
		
		
		for(Registration r: registrations){
			boolean found = false;
			
			for(OP op: ops){
				if(r.getKeyToRP() == op.getKeyToRP()){
					found = true;
					op.add(r);
					r.setOp(op);
					break;
				}
			}
			
			if(found == false){
				OP op1 = new OP(r.getKeyToRP());				
				ops.add(op1);
				op1.add(r);
				r.setOp(op1); 
				
			}
		}
		
		System.out.println("Set Object Links");

		
		for(OP op: ops){
			
			for(Registration r: op.getRegistrationsOfOP()){

				while(i_ra < registrationAddresses.size() && r.contains(registrationAddresses.get(i_ra))){  
					registrationAddresses.get(i_ra).add(r);
					r.add(registrationAddresses.get(i_ra));
					i_ra++;
				}

				while(i_p < persons.size() && r.contains(persons.get(i_p))){  
					persons.get(i_p).add(r);	
					r.add(persons.get(i_p));

					while(i_pd < personsDynamic.size() && persons.get(i_p).contains(personsDynamic.get(i_pd))  ){
						personsDynamic.get(i_pd).add(persons.get(i_p));
						persons.get(i_p).add(personsDynamic.get(i_pd));
						i_pd++;
					}

					i_p++;
				}

				i_r++;
			}
		}
		
		
		System.out.println("Sort Objects");
		
		for(OP op: ops){
			
			Collections.sort(op.getRegistrationsOfOP(), new Comparator<Registration>()
			{
				public int compare(Registration r1, Registration r2){
					
					if     (r1.getYearEntryRP() >  r2.getYearEntryRP()) return  1;
					else if(r1.getYearEntryRP() <  r2.getYearEntryRP()) return -1;

					if     (r1.getMonthEntryRP() >  r2.getMonthEntryRP()) return  1;
					else if(r1.getMonthEntryRP() <  r2.getMonthEntryRP()) return -1;

					if     (r1.getDayEntryRP() >  r2.getDayEntryRP()) return  1;
					else if(r1.getDayEntryRP() <  r2.getDayEntryRP()) return -1;
					
					return 0;
				}
			}); 
		}
	}  
	
	public static boolean createOutputTables(){

		boolean retvalue = true;

		try{
			Scanner scanner = new Scanner(new FileReader("..\\createB2_st"));  // werkt niet
			scanner.useDelimiter(";");
			while (scanner.hasNextLine()){
				Utils.executeNativeStatement(scanner.nextLine());
		    }
		}
		catch (IOException e) {
			retvalue = false;

		}

		System.out.println("=====> " + retvalue);
		return retvalue;
		
	}
	
	
}
	
	
	
	
	
	
	


