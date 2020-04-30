/*
 * Naam:    PersonDynamic
 * Version: 0.1
 *  Author: Cor Munnik
 * Copyright
 */
package nl.iisg.ids03;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;



import nl.iisg.hsncommon.Common1;
import nl.iisg.hsncommon.ConstRelations2;
import nl.iisg.ref.*;


/**
 * 
 * This class handles the dynamic attributes of a person (relation to head, civil status etc.
 *
 */
@Entity
@Table(name="b3")
public class PersonDynamic implements Comparable<PersonDynamic> {

	@Id	@Column(name = "B1IDBG")  	private int   		 keyToSourceRegister;
	@Id	@Column(name = "B2DIBG")    private int          dayEntryHead;                    
	@Id	@Column(name = "B2MIBG")    private int          monthEntryHead;                   
	@Id	@Column(name = "B2JIBG")    private int          yearEntryHead;                   
	@Id	@Column(name = "IDNR")      private int          keyToRP;
	
	@Id	@Column(name = "B2RNBG")    private int          keyToRegistrationPersons;
	@Id	@Column(name = "B3TYPE")    private int          dynamicDataType;             
	@Id	@Column(name = "B3VRNR")    private int          dynamicDataSequenceNumber;

	@Column(name = "B3KODE")        private int          contentOfDynamicData;
	@Column(name = "B3RGLN")        private int          valueOfRelatedPerson;
	@Column(name = "B2FCBG")        private int          natureOfPerson;
	@Column(name = "B3MDNR")        private int          dayOfMutation;
	@Column(name = "B3MMNR")        private int          monthOfMutation;
	@Column(name = "B3MJNR")        private int          yearOfMutation;
	@Column(name = "B3MDCR")        private int          dayOfMutationAfterInterpretation;
	@Column(name = "B3MMCR")        private int          monthOfMutationAfterInterpretation;
	@Column(name = "B3MJCR")        private int          yearOfMutationAfterInterpretation;

	@Column(name = "B3GEGEVEN")     private String       dynamicData2;

	@Column(name = "OPDRNR")        private String       orderNumber;
	@Column(name = "DATUM")         private String       date0;
	@Column(name = "INIT")          private String       initials;
	@Column(name = "VERSIE")        private String       versionLastTimeOfDataEntry;
	@Column(name = "ONDRZKO")       private String       researchCodeOriginal;
	@Column(name = "OPDRNRO")       private String       orderNumberOriginal;
	@Column(name = "DATUMO")        private String       dateOriginal;
	@Column(name = "INITO")         private String       initialOriginal;
	@Column(name = "VERSIEO")       private String       versionOriginalDataEntry;

	@Transient                      private Person                                   personToWhomDynamicDataRefers;
	@Transient                      private ArrayList<PersonDynamicStandardized>     standardizedPersonDynamic = new ArrayList<PersonDynamicStandardized>(); 


	PersonDynamic(){} // no-args constructor
	
	/**
	 * 
	 * @param rowObjects
	 * 
	 * Constructor, initializes object from rowObjects
	 * 
	 */

	PersonDynamic(Object [] rowObjects, String [] fieldnames, byte[] fieldtypes){

		Utils.trimAll(rowObjects);
		
		setKeyToSourceRegister(                                       (Integer)Utils.getColumn("B1IDBG", rowObjects, fieldnames, fieldtypes));
		setDayEntryHead(                                              (Integer)Utils.getColumn("B2DIBG", rowObjects, fieldnames, fieldtypes));
		setMonthEntryHead(                                            (Integer)Utils.getColumn("B2MIBG", rowObjects, fieldnames, fieldtypes));
		setYearEntryHead(                                             (Integer)Utils.getColumn("B2JIBG", rowObjects, fieldnames, fieldtypes));
		setKeyToRP(                                                   (Integer)Utils.getColumn("IDNR", rowObjects, fieldnames, fieldtypes));
		setKeyToRegistrationPersons(                                  (Integer)Utils.getColumn("B2RNBG", rowObjects, fieldnames, fieldtypes));

		setDynamicDataType(                                           (Integer)Utils.getColumn("B3TYPE", rowObjects, fieldnames, fieldtypes));
		setDynamicDataSequenceNumber(                                 (Integer)Utils.getColumn("B3VRNR", rowObjects, fieldnames, fieldtypes));

		setContentOfDynamicData(                                      (Integer)Utils.getColumn("B3KODE", rowObjects, fieldnames, fieldtypes));
		setValueOfRelatedPerson(                                      (Integer)Utils.getColumn("B3RGLN", rowObjects, fieldnames, fieldtypes));
		setNatureOfPerson(                                            (Integer)Utils.getColumn("B2FCBG", rowObjects, fieldnames, fieldtypes));
		setDayOfMutation(                                             (Integer)Utils.getColumn("B3MDNR", rowObjects, fieldnames, fieldtypes));
		setMonthOfMutation(                                           (Integer)Utils.getColumn("B3MMNR", rowObjects, fieldnames, fieldtypes));
		setYearOfMutation(                                            (Integer)Utils.getColumn("B3MJNR", rowObjects, fieldnames, fieldtypes));
		setDayOfMutationAfterInterpretation(                          (Integer)Utils.getColumn("B3MDCR", rowObjects, fieldnames, fieldtypes));
		setMonthOfMutationAfterInterpretation(                        (Integer)Utils.getColumn("B3MMCR", rowObjects, fieldnames, fieldtypes));
		setYearOfMutationAfterInterpretation(                         (Integer)Utils.getColumn("B3MJCR", rowObjects, fieldnames, fieldtypes));
		setDynamicData2(                                              (String)Utils.getColumn("B3GEGEVEN", rowObjects, fieldnames, fieldtypes));

		setOrderNumber(                                 		      (String)Utils.getColumn("OPDRNR", rowObjects, fieldnames, fieldtypes));
		setDate0(                                            		  (String)Utils.getColumn("DATUM", rowObjects, fieldnames, fieldtypes));	
		setInitials(                                          		  (String)Utils.getColumn("INIT", rowObjects, fieldnames, fieldtypes));
		setVersionLastTimeOfDataEntry(                        		  (String)Utils.getColumn("VERSIE", rowObjects, fieldnames, fieldtypes));
		setResearchCodeOriginal(                              		  (String)Utils.getColumn("ONDRZKO", rowObjects, fieldnames, fieldtypes));
		setOrderNumberOriginal(                               		  (String)Utils.getColumn("OPDRNRO", rowObjects, fieldnames, fieldtypes));
		setDateOriginal(                                      		  (String)Utils.getColumn("DATUMO", rowObjects, fieldnames, fieldtypes));
		setInitialOriginal(                                   		  (String)Utils.getColumn("INITO", rowObjects, fieldnames, fieldtypes));
		setVersionOriginalDataEntry(                          		  (String)Utils.getColumn("VERSIEO", rowObjects, fieldnames, fieldtypes));


		// now some immediate changes are performed for interpreted date fields
		
		adaptMutationDate();
	}

	PersonDynamic(Object [] rowObjects){

		Utils.trimAll(rowObjects);
		
		setKeyToSourceRegister(                                       Utils.toInt(rowObjects[1]));
		setDayEntryHead(                                              Utils.toInt(rowObjects[2]));
		setMonthEntryHead(                                            Utils.toInt(rowObjects[3]));
		setYearEntryHead(                                             Utils.toInt(rowObjects[4]));
		setKeyToRP(                                                   Utils.toInt(rowObjects[5])); 
		setKeyToRegistrationPersons(                                  Utils.toInt(rowObjects[6]));

		setDynamicDataType(                                           Utils.toInt(rowObjects[7]));
		setDynamicDataSequenceNumber(                                 Utils.toInt(rowObjects[8]));

		setContentOfDynamicData(                                      Utils.toInt(rowObjects[9]));
		setValueOfRelatedPerson(                                      Utils.toInt(rowObjects[10])); 
		setNatureOfPerson(                                            Utils.toInt(rowObjects[11]));
		setDayOfMutation(                                             Utils.toInt(rowObjects[12]));
		setMonthOfMutation(                                           Utils.toInt(rowObjects[13]));
		setYearOfMutation(                                            Utils.toInt(rowObjects[14]));
		setDayOfMutationAfterInterpretation(                          Utils.toInt(rowObjects[15]));
		setMonthOfMutationAfterInterpretation(                        Utils.toInt(rowObjects[16]));
		setYearOfMutationAfterInterpretation(                         Utils.toInt(rowObjects[17]));
		setDynamicData2(                                              (String)rowObjects[18]);

		setOrderNumber(                                              (String)rowObjects[19]);
		setDate0(                                                      Utils.toStr(rowObjects[20]));
		setInitials(                                                 (String)rowObjects[21]);
		setVersionLastTimeOfDataEntry(                               (String)rowObjects[22]);
		setResearchCodeOriginal(                                     (String)rowObjects[23]);
		setOrderNumberOriginal(                                      (String)rowObjects[24]);
		setDateOriginal(                                             Utils.toStr(rowObjects[25]));
		setInitialOriginal(                                          (String)rowObjects[26]);
		setVersionOriginalDataEntry(                                 (String)rowObjects[27]);

		// now some immediate changes are performed for interpreted date fields
		
		adaptMutationDate();
	}

	public void add(Person p){
		setPersonToWhomDynamicDataRefers(p);
	}

	/**
	 * 
	 * @param 
	 * 
	 * This method performs checks on the dynamic data elements of person (PersonDynamic)
	 * The following message numbers can be issued:
	 * 
	 * 1301	  
	 * 1261
	 * 1362
	 * 
	 */
	public void check(){
		
		//System.out.println("Check PersonDynamic In  " + getKeyToRP()); 

		
		//System.out.println("Check PersonDynamic");

		//showFields();

		// Check if person is Head

		if(getDynamicDataType() == ConstRelations2.RELATIE_TOT_HOOFD && getContentOfDynamicData() == ConstRelations2.HOOFD && getDynamicDataSequenceNumber() == 1)
			getPersonToWhomDynamicDataRefers().setIsHead(true);  

		// Check if person is Explicit Head

		if(getDynamicDataType() == ConstRelations2.RELATIE_TOT_HOOFD && getDynamicDataSequenceNumber() > 1)
			getPersonToWhomDynamicDataRefers().setIsHeadFirstSuccessor(true);  


		
		Ref_AINB ainb = Ref.getAINB(getKeyToSourceRegister());
		
		checkMutationDate(ainb); 
		
		// Check if valid value for B6:b3typeregistration - keyToDistinguishDynamicDataType 

		if(!(getDynamicDataType() == ConstRelations2.RELATIE_TOT_HOOFD ||
				getDynamicDataType() == ConstRelations2.BURGELIJKE_STAAT  ||
				getDynamicDataType() == ConstRelations2.GODSDIENST        ||
				getDynamicDataType() == ConstRelations2.BEROEPSTITEL      ||
				getDynamicDataType() == ConstRelations2.AANKOMST          ||
				getDynamicDataType() == ConstRelations2.VERTREK))			 

			message("1301", new Integer(getDynamicDataType()).toString());

		//
		// Check if syntax B3:b3gegeven - dynamicData is correct
		//

		// Check that b3:b3gegeven - dynamicData correct does not have '$' on 4th position if not relation to Head

		if(getDynamicDataType() != ConstRelations2.RELATIE_TOT_HOOFD){

			if(getDynamicData2().length() >= 4 && getDynamicData2().charAt(3) == '$')
				message("1361");				

			// Check that b3:b3gegeven - dynamicData2 correct does not have an explicit head for C-Registers	

			if(getDynamicDataSequenceNumber() == 1 && getDynamicData2().indexOf("$") >= 0 && ainb.getTypeRegister().toUpperCase().equals("C") == true) 
				message("1362");				

		}
		
		// Check that the value of B3RGLN - valueOfRelatedPerson does not refer to the person belonging to this PersonDynamic object
		// Moved to keyChecks
		
		//if(getValueOfRelatedPerson() == getKeyToRegistrationPersons())
			//message("1314");
		
		
		switch(getDynamicDataType()){
		case ConstRelations2.RELATIE_TOT_HOOFD:  checkRelationHead(); break;
		case ConstRelations2.BURGELIJKE_STAAT:   checkCivilStatus (); break;
		case ConstRelations2.GODSDIENST:         checkReligion    (); break;
		case ConstRelations2.BEROEPSTITEL:       checkOccupationalTitle(); break;
		case ConstRelations2.AANKOMST:           checkArrival     (); break;
		case ConstRelations2.VERTREK:            checkDeparture   (); break;
		}		
		
		checkConsistencyWithPerson();
		
		
		//System.out.println("Check PersonDynamic Out " + getKeyToRP()); 


	}

	/**
	 * 
	 * This method checks the date of a mutation against  range of source, birth and decease date and civil status
	 * The following message numbers can be issued:
	 * 
	 * 1431
	 * 1432
	 * 1433
	 * 1434
	 */

     private void checkMutationDate(Ref_AINB ainb){
    	 
		int day   = getDayOfMutation()   > 0 ? getDayOfMutation()   : 1;
		int month = getMonthOfMutation() > 0 ? getMonthOfMutation() : 1;
		int year  = getYearOfMutation(); 
		
		if(year <= 0) return; 

    	 
    	 if(Common1.dateIsValid(day, month, year) == 0){
    		 
    		 int mutationDate = Utils.dayCount(day, month, year); 
    		 
    		 if(ainb != null){

    			 if(getDynamicDataType() != ConstRelations2.BURGELIJKE_STAAT){ // for civil status there are many outside the range of the register, they are copied from older registers

    				 // Check if year mutation  before begin year register
    				 
   					int startYear = ainb.getStartYearRegisterCorrected() != 0 ? ainb.getStartYearRegisterCorrected() : ainb.getStartYearRegister();  
   					int endYear   = ainb.getEndYearRegisterCorrected()   != 0 ? ainb.getEndYearRegisterCorrected()   : ainb.getEndYearRegister();  

    				 if(getYearOfMutation() <  startYear)
    					 message("1431", "" + day + "-" + month + "-" + getYearOfMutation(), "" + startYear, "" + endYear); 

    				 // Check if year mutation  after end year register

    				 if(getYearOfMutation() >  endYear)
    					 message("1432", "" + day + "-" + month + "-" + getYearOfMutation(), "" + startYear, "" + endYear); 
    			 }
    		 }
    		 
             // Check if mutation date before birth date
    		 
    		 Person p = getPersonToWhomDynamicDataRefers();
    		 if(Common1.dateIsValid(p.getDayOfBirth(), p.getMonthOfBirth(), p.getYearOfBirth()) == 0){
    		 
    			 int birthDate = Utils.dayCount(p.getDayOfBirth(), p.getMonthOfBirth(), p.getYearOfBirth());
    			 
    			 if(mutationDate < birthDate)
    				 message("1433", "" + day + "-" + month + "-" + getYearOfMutation(), 
    						         "" + p.getDayOfBirth()  + "-" + p.getMonthOfBirth()  + "-" + p.getYearOfBirth());
    					 
    		 }
    		 
    		// Check if mutation date after decease date
        		 
    		 if(Common1.dateIsValid(p.getDayOfDecease(), p.getMonthOfDecease(), p.getYearOfDecease()) == 0){

    			 int deceaseDate = Utils.dayCount(p.getDayOfDecease(), p.getMonthOfDecease(), p.getYearOfDecease());

    			 if(mutationDate > deceaseDate)
    				 message("1434", "" + day  + "-" + month  + "-" + getYearOfMutation(), 
    						         "" + p.getDayOfDecease() + "-" + p.getMonthOfDecease() + "-" + p.getYearOfDecease());

        		    		 
    		 }
    	 }
    	 else
			 message("1320", "" + getDayOfMutation() + "-" + getMonthOfMutation() + "-" + getYearOfMutation()); 
    	 
    	 
     }
	
	
	/**
	 * 
	 * @param 
	 * 
	 * This method checks if the relation to Head is valid
	 * The following message numbers can be issued:
	 * 
	 * 1305
	 * 1306
	 * 1307
	 * 1308
	 * 1311
	 * 1350
	 * 1355
	 * 1356
	 * 1357
	 * 1358
	 * 1359
	 * 1351
	 * 1368
	 * 1421
	 * 1422
	 * 1423
	 * 1424
	 * 1425
	 * 1426
	 * 
	 * 
	 */
	
	public void checkRelationHead(){
		
		Ref_AINB ainb = Ref.getAINB(getKeyToSourceRegister());
		
		if(getContentOfDynamicData() >= 0){
			
			

			if(getContentOfDynamicData() < ConstRelations2.getB3kode1().length){

				// Check that content type is valid
				
				if(ConstRelations2.getB3kode1()[getContentOfDynamicData()] == null){
					message("1311", (new Integer(getContentOfDynamicData()).toString())); 
				}
			}
			else
				message("1311", (new Integer(getContentOfDynamicData()).toString()));

			
			// Check for b3kode - ContentOfDynamicData = 99, no allowed

			if(getContentOfDynamicData() == 99)
				message("1350");

		}
		else{
			if(getContentOfDynamicData() < -3)
				message("1311", new Integer(getContentOfDynamicData()).toString()); 

		}
		
		
		// For C Registers, Check that first person is head
		
		
		if(ainb != null && ainb.getTypeRegister().toUpperCase().equals("C") == true){
			
			// Check that no second relation to head can be given
			
			// Corona Thuis
			if(getDynamicDataSequenceNumber() > 1){
				message("1307");
				
			}
			
			if(getKeyToRegistrationPersons() == 1){
			    if(getContentOfDynamicData() != ConstRelations2.HOOFD)
			    	message("1305");
			}
			
			// Check that second and further persons have relation code to Head = -3
			else
				if(getContentOfDynamicData() != -3)
					message("1306");

		}
		
		
		// Check that the first relation to the head must have mutation date -3/-3/-3
		
		if(getDynamicDataSequenceNumber() == 1)
			if(getDayOfMutation() != -3 || getMonthOfMutation() != -3 || getYearOfMutation() != -3)
				message("1308"); 

		// Check head
		
		if (getContentOfDynamicData() == ConstRelations2.HOOFD){

			if(getKeyToRegistrationPersons() == 1)  // The regular head of household
				
			// Check that the sequence number of the dynamic data == 1
			
			if(getDynamicDataSequenceNumber() > 1)
				message("1355");
		}

		
		// Check wife
		
		if (getContentOfDynamicData() == ConstRelations2.ECHTGENOTE_HOOFD){
			
			if (getValueOfRelatedPerson() > 0){
				
				Person p = getPersonToWhomDynamicDataRefers();
				Registration r = p.getRegistrationPersonAppearsIn();
				Person p2 = null;
				
				for(Person p1: r.getPersonsInRegistration()){
					
					if(p.getKeyToRegistrationPersons() == getValueOfRelatedPerson()){
						
						p2 = p1;						
						break;						
					}
				}
				
				if(p2 == null)
					message("1424", new Integer(getValueOfRelatedPerson()).toString());
				
			}
		}
		

		// Check grand-children 

		if (getContentOfDynamicData() == ConstRelations2.KLEINZOON       ||
				getContentOfDynamicData() == ConstRelations2.KLEINDOCHTER){

			// Check if reference to grand-children correct

			int code = 0;
			if(getDynamicData2() != null && getDynamicData2().trim().length() > 0){

				if(Character.isDigit(getDynamicData2().trim().charAt(0))){

					String s =  "" + getDynamicData2().trim().charAt(0); // There is a code

					code = (new Integer(s));

					if (getValueOfRelatedPerson() < 1) {  // invalid line number				

						if(code != 9){
							message("1356");
						}

						// Check if grand-children have incorrect relation to mother

						if (code == 1 || code == 2 || code == 3)
							message("1357");

						// Check if grand-children have incorrect relation to father
						else
							if (code == 4 || code == 5 || code == 6)
								message("1358");
							else
								if(code == 9 && getValueOfRelatedPerson() != -2 && getValueOfRelatedPerson() != -3)
									message("1359");
					}
					else{ // There is a valid line number

						// Check if correct relation to father or mother specified

						// Bij 1 tm 6 moet een valid regelnummer zijn
						// bij 9 moet er -2 of -3 staan

						if(code < 1 || code > 6){
							message("1368");

						}
					}



				}		
				else // no code
					if(getValueOfRelatedPerson() != 0) // but there is a related person 
						message("1369");


				// Check that value of related person exists in B2 - Person

				if(getValueOfRelatedPerson() > 0){

					Person p = getPersonToWhomDynamicDataRefers();
					Registration r = p.getRegistrationPersonAppearsIn();
					Person p2 = null;

					for(Person p1: r.getPersonsInRegistration()){

						if(p1.getKeyToRegistrationPersons() == getValueOfRelatedPerson()){
							p2 = p1;						
							break;						
						}
					}

					if(p2 == null){

						// incorrect relation to mother

						if (code == 1 || code == 2 || code == 3)
							message("1421", new Integer(getValueOfRelatedPerson()).toString());

						// Check if grand-children have incorrect relation to father

						else
							if (code == 4 || code == 5 || code == 6)
								message("1422", new Integer(getValueOfRelatedPerson()).toString());
							else
								message("1423", new Integer(getValueOfRelatedPerson()).toString());
					}

					else{

						if (code == 1 || code == 2 || code == 3)
							if(p2.getSex().toUpperCase().equals("V") != true)
								message("1425", new Integer(getValueOfRelatedPerson()).toString());

						if (code == 4 || code == 5 || code == 6)
							if(p2.getSex().toUpperCase().equals("M") != true)
								message("1426", new Integer(getValueOfRelatedPerson()).toString());

					}
				}


				// Check relation to Head versus sex of person

				if(getPersonToWhomDynamicDataRefers().getSex().equalsIgnoreCase("v"))
					if(getContentOfDynamicData() > 0 && getContentOfDynamicData() < ConstRelations2.b3kode1_Male.length && ConstRelations2.b3kode1_Male[getContentOfDynamicData()] != null)
						message("1401", new Integer(getKeyToRegistrationPersons()).toString());			

				if(getPersonToWhomDynamicDataRefers().getSex().equalsIgnoreCase("m"))
					if(getContentOfDynamicData() > 0 && getContentOfDynamicData() < ConstRelations2.b3kode1_Female.length && ConstRelations2.b3kode1_Female[getContentOfDynamicData()] != null)
						message("1400", new Integer(getKeyToRegistrationPersons()).toString());			

			}
			else
				message("1369");
		}



	}

	/**
	 * 
	 * @param ainb
	 * 
	 * This method checks if the civil status of the person is valid
	 * The following message numbers can be issued:
	 * 
	 * 1313
	 * 1316
	 * 1320
	 * 1321
	 * 1435
	 * 
	 * 
	 */
	public void checkCivilStatus(){

		// Check that civil status is valid

		if(getContentOfDynamicData() >= 0){

			if(getContentOfDynamicData() < ConstRelations2.getB3kode2().length){

				if(ConstRelations2.getB3kode2()[getContentOfDynamicData()] == null){
					message("1313", (new Integer(getContentOfDynamicData()).toString())); 
				}
			}
			else
				message("1313", (new Integer(getContentOfDynamicData()).toString()));
		}
		else{
			if(getContentOfDynamicData() < -3)
				message("1313", (new Integer(getContentOfDynamicData()).toString())); 
		}

		// Check if correct reference to partner

		if (getContentOfDynamicData() == ConstRelations2.WEDUWNAAR_WEDUWE ||
				getContentOfDynamicData() == ConstRelations2.GESCHEIDEN       || 
				getContentOfDynamicData() == ConstRelations2.GEHUWD           || 
				getContentOfDynamicData() == ConstRelations2.ONBEKEND_OP_LATER_ONBEKEND_MOMENT_GEHUWD)


			if(getValueOfRelatedPerson() < 1 && getValueOfRelatedPerson() != -1 && getValueOfRelatedPerson() != -2 && getValueOfRelatedPerson() != -3
			&& getValueOfRelatedPerson() != 99)
				message("1316", (new Integer(getContentOfDynamicData()).toString()));


		// Check if valid date mutation

		
		
		if(getYearOfMutation() > 0){
			
			//System.out.println("year = " + getYearOfMutation());

			int day   = getDayOfMutation()   > 0 ? getDayOfMutation()   : 1;
			int month = getMonthOfMutation() > 0 ? getMonthOfMutation() : 1;
			/*
			if(month < 0 || day < 0){

				int [] a = Utils.createPartialDate(day, month);

				if(day < 0){
					if(getContentOfDynamicData() != ConstRelations2.AANKOMST)
						day =1;
					else
						day = a[0]; 	
				}

				if(month < 0){
					if(getContentOfDynamicData() != ConstRelations2.AANKOMST)
						month =1;
					else
						month = a[1]; 	
				}
			}	

		    */ 
			
			if(Common1.dateIsValid(day, month, getYearOfMutation()) != 0)
				message("1320", "" + getDayOfMutation() + " - " + getMonthOfMutation() + " - " + getYearOfMutation()); 
			else
			{
				Person p = getPersonToWhomDynamicDataRefers();
				if(Common1.dateIsValid(p.getDayOfBirth(), p.getMonthOfBirth(), p.getYearOfBirth())  == 0){
					
					// Check if mutation before 15th year of age
					
					if(p.getYearOfBirth() + 15 > getYearOfMutation())
	    				 message("1435", "" + getDayOfMutation() + "-" + getMonthOfMutation() + "-" + getYearOfMutation(), 
                				         "" + p.getDayOfBirth()  + "-" + p.getMonthOfBirth()  + "-" + p.getYearOfBirth());
				}
			}

			if(getDynamicDataSequenceNumber() > 1 && getDynamicDataType() == ConstRelations2.BURGELIJKE_STAAT){

				if(Common1.dateIsValid(getDayEntryHead(), getMonthEntryHead(),getYearEntryHead()) == 0 && 
						Common1.dateIsValid(getDayOfMutation(), getMonthOfMutation(), getYearOfMutation()) == 0){
					int i1 = Utils.dayCount(getDayEntryHead(), getMonthEntryHead(),getYearEntryHead());
					int i2 = Utils.dayCount(getDayOfMutation(),           getMonthOfMutation(),          getYearOfMutation());

					if(i2 < i1)
						message("1321", "" + getDayOfMutation() + " - " + getMonthOfMutation() + " - " + getYearOfMutation());
				}
			}	
		}
		//else
		//message("1320", "" + getDayOfMutation() + " - " + getMonthOfMutation() + " - " + getYearOfMutation()); 


	}
	/**
	 * 
	 * @param 
	 * 
	 * This method checks if the religion the person is valid
	 * The following message numbers can be issued:
	 * 
	 * 1345
	 * 1346
	 * 
	 * 
	 */

	public void checkReligion(){

		// Check if church denomination for person is in KG reference file

		
		
		if(getDynamicData2() == null || getDynamicData2().trim().length() == 0)			
			return;
		
		Ref_KG kg1 = Ref.getKG(getDynamicData2());

		if(kg1 == null && getDynamicData2().toUpperCase().indexOf("ID") < 0   && 
                			getDynamicData2().toUpperCase().indexOf("IDEM") < 0  &&
                			getDynamicData2().toUpperCase().indexOf("\"") < 0)
			message("1345", getDynamicData2().trim());	

		// Check that Godsdienst is not Id or Idem etc. for first person in registration

		
		if(getKeyToRegistrationPersons() == 1 && (getDynamicData2().toUpperCase().indexOf("ID") >= 0   || 
				                                  getDynamicData2().toUpperCase().indexOf("IDEM") >= 0  ||
				                                  getDynamicData2().toUpperCase().indexOf("\"") >= 0))

			message("1346");	


	}
	/**
	 * 
	 * @param 
	 * 
	 * This method checks if the occupational title of the person is valid
	 * The following message numbers can be issued:
	 * 
	 * 1371
	 */

	public void checkOccupationalTitle(){
		
		// Check if "@" in b3gegeven - dynamicData2

		if(getDynamicData2() != null && getDynamicData2().length() > 0 && getDynamicData2().indexOf("@") >= 0)
			message("1371");

	}
	
	/**
	 * 
	 * @param 
	 * 
	 * This method checks if the arrivals of the person are valid
	 * The following message numbers can be issued:
	 * 
	 * 1126
	 * 1347
	 * 1372
	 * 1373
	 * 1375
	 * 1376
	 * 
	 */


	public void checkArrival(){

		// Check if "@" in b3gegeven - dynamicData2

		if(getDynamicData2() != null && getDynamicData2().length() > 0 && getDynamicData2().indexOf("@") >= 0)
			message("1372");


		// Check if "#" in b3gegeven - dynamicData2

		if(getDynamicData2() != null  && getDynamicData2().length() > 0  && getDynamicData2().indexOf("#") >= 0)
			message("1375");


		/* Te Rotterdam komt het verschijnsel voor, dat men bij de GK's de 
		 * originele eerste herkomstdatum aan blijft houden als iemand van een andere
		 * Gk komt, bv '& GK Lena Groenevelt * Delft. De datum staat dan voor de eerste
		 * aankomst uit Delft. Deze constructie herkenbaar gemaakt om vervolgens
		 * de herkomstdatum over te slaan bij de vaststelling van aankomstdatum, indien
		 * deze botst met de vorige datum
		 */

		if(getDynamicData2() != null && getDynamicData2().length() > 0  && getDynamicData2().indexOf("&") >= 0 && getDynamicData2().indexOf("*") >= 0 && getYearOfMutation() > 0)
			message("1347", "" + getDayOfMutation() + "-" +  getMonthOfMutation() + "-" + getYearOfMutation());


		// Check if "*" (or "@" if versieo - versionOriginalDataEntry before 3.40) in first position b3gegeven - dynamicData2

		if(getDynamicData2() != null && getDynamicData2().length() > 0  && (getDynamicData2().charAt(0) == '*' || (getDynamicData2().charAt(0) == '@' &&
				(getVersionOriginalDataEntry().indexOf("3.1") >= 0  ||
						getVersionOriginalDataEntry().indexOf("3.2") >= 0  ||
						getVersionOriginalDataEntry().indexOf("3.3") >= 0))))

			message("1363");

		// If person is head, perform extra checks

		
		if(getDynamicDataSequenceNumber() == 1 && getPersonToWhomDynamicDataRefers().getIsHead() == true && getPersonToWhomDynamicDataRefers().getIsHeadFirstSuccessor() == false){

			if(Common1.dateIsValid(getDayEntryHead(), getMonthEntryHead(),getYearEntryHead()) == 0){
				if(Common1.dateIsValid(getDayOfMutation(), getMonthOfMutation(),getYearOfMutation()) == 0){

					int x = Utils.dayCount(getDayOfMutation(), getMonthOfMutation(),getYearOfMutation());
					int y = Utils.dayCount(getDayEntryHead(), getMonthEntryHead(),getYearEntryHead());

					// Check if headdate before arrival date of head

					if(y < x)
						message("1126", "" + getDayEntryHead()  + "-" + getMonthEntryHead()  + "-" + getYearEntryHead(), 
								        "" + getDayOfMutation() + "-" + getMonthOfMutation() + "-" + getYearOfMutation());
				}
			}	
		}
	}
	
	/**
	 * 
	 * @param 
	 * 
	 * This method checks if the departures of the person are valid
	 * The following message numbers can be issued:
	 * 
	 * 1127
	 * 1364
	 * 1373
	 * 1376
	 * 
	 */

	public void checkDeparture(){

		// Check if "@" in b3gegeven - dynamicData2

		if(getDynamicData2() != null  && getDynamicData2().indexOf("@") >= 0)
			message("1373");

		// Check if "#" in b3gegeven - dynamicData2

		if(getDynamicData2() != null && getDynamicData2().indexOf("#") >= 0)
			message("1376");


		// Check if "*" (or "@" if versieo - versionOriginalDataEntry before 3.40) in first position b3gegeven - dynamicData2

		if(getDynamicData2() != null  && getDynamicData2().length() > 0  && (getDynamicData2().charAt(0) == '*' || (getDynamicData2().charAt(0) == '@' &&
				(getVersionOriginalDataEntry().indexOf("3.1") >= 0  ||
						getVersionOriginalDataEntry().indexOf("3.2") >= 0  ||
						getVersionOriginalDataEntry().indexOf("3.3") >= 0))))

			message("1364");

		// If person is head, perform extra checks
		
		if(getPersonToWhomDynamicDataRefers().getIsHead() == true && getPersonToWhomDynamicDataRefers().getIsHeadFirstSuccessor() == false &&
				 getDynamicDataSequenceNumber() == 1){

			if(Common1.dateIsValid(getDayEntryHead(), getMonthEntryHead(),getYearEntryHead()) == 0){
				if(Common1.dateIsValid(getDayOfMutation(), getMonthOfMutation(),getYearOfMutation()) == 0){

					int x = Utils.dayCount(getDayOfMutation(), getMonthOfMutation(),getYearOfMutation());
					int y = Utils.dayCount(getDayEntryHead(), getMonthEntryHead(),getYearEntryHead());

					// Check if headdate after departure date of head

					if(y > x)
						message("1127", "" + getDayEntryHead()  + "-" + getMonthEntryHead()   + "-" + getYearEntryHead(), 
								        "" + getDayOfMutation() + "-" +  getMonthOfMutation() + "-" + getYearOfMutation());
				}
			}
			
		}
	}

	/**
	 * 
	 * @param 
	 * 
	 * This method checks if the PersonDynamic object is consistent with the Person object that the PersonDynamic object belongs to 
	 * The following message numbers can be issued:
	 * 
	 * 
	 * 1461 -> 1161 
	 * 
	 * 
	 */
	
	private void checkConsistencyWithPerson(){

		// Check if b2fcbg - natureOfPerson has the same value as the corresponding Person object
        // Corona
		
		if(getNatureOfPerson() != getPersonToWhomDynamicDataRefers().getNatureOfPerson())
			message("1161", new Integer(getKeyToRegistrationPersons()).toString());
		
		
	}
	
		
	/**
	 * 
	 * If a valid (>0) correction day/month/year is given, it is copied to the original date
	 * If an original day/month/year == -3, it is set to -1
	 * 
	 */

	private void adaptMutationDate(){
	    
    	setDayOfMutation(Utils.convertDateElement(getDayOfMutation(), getDayOfMutationAfterInterpretation()));
    	setMonthOfMutation(Utils.convertDateElement(getMonthOfMutation(), getMonthOfMutationAfterInterpretation()));
    	setYearOfMutation(Utils.convertDateElement(getYearOfMutation(), getYearOfMutationAfterInterpretation()));


		
		
	}
	
	/**
	 * 
	 * This routine prints out the essential fields of this object
	 * 
	 * 
	 */

	public void print(){

		showFields();
		
	}
	
	public void showFields(){
		
		//System.out.println("            Check PersonDynamic   " +
		//		" IDNR = " + getkeyToRP() +
		//		" B1IDBG = " + getKeyToSourceRegister() + 
		//		" B2DIBG* = " + getDayEntryHead() + " " + getMonthEntryHead() + " " + getYearEntryHead() +
		//		" B2RNBG = " + getKeyToRegistrationPersons() +
		//		" B3TYPE = " + getKeyToDistinguishDynamicDataType() +
		//		" B3VRNR = " + getSequenceNumberToDistinguishDynamicData());
		
		
		
		String ddt = null;
		switch(getDynamicDataType()){
		case 1: ddt = "RELATIE_TOT_HOOFD = "; 
		if(0 <= getContentOfDynamicData() && getContentOfDynamicData() < ConstRelations2.b3kode1.length){
			ddt += ConstRelations2.b3kode1[getContentOfDynamicData()]; 
			ddt += " " ;
			ddt += getDynamicData2().trim();
		}    
		break;


		case 2: ddt = "BURGELIJKE_STAAT  = ";

		if(0 <= getContentOfDynamicData() && getContentOfDynamicData() < ConstRelations2.b3kode2.length){
			ddt += ConstRelations2.b3kode2[getContentOfDynamicData()];
			ddt += " " ;
			ddt += getDynamicData2().trim();
		}
		break;

		case 3: ddt = "GODSDIENST        = " + getDynamicData2().trim();		 break;
		case 5: ddt = "BEROEPSTITEL      = " + getDynamicData2().trim();		 break;
		case 6: ddt = "AANKOMST            " + getDynamicData2().trim();		 break;
		case 7: ddt = "VERTREK             " + getDynamicData2().trim();		 break;
		
		}

		String EntryDateHead = String.format("%02d-%02d-%04d", getDayEntryHead(), getMonthEntryHead(), getYearEntryHead());

		
		String ddc = String.format("%02d-%02d-%04d", getDayOfMutation                   (), getMonthOfMutation                   (), getYearOfMutation                   ());
		

		String rp = "";
		if(getValueOfRelatedPerson() > 0)
			rp = "      Related Person = " + getValueOfRelatedPerson();
			
		
		  System.out.println("" +
		  		"" + getKeyToRP() +
		  		"  " + EntryDateHead +
		  		"  " + getKeyToSourceRegister() + 
		  		"  " + getKeyToRegistrationPersons() +
         		"        " + ddc + 
		  		"  " + getDynamicDataSequenceNumber() +
		        "  " + ddt
		        + rp		      
		        );
		

	}
	
	/**
	 * 
	 * 
	 * This method converts the PersonDynamic Object to the new format
	 * 
	 * 
	 */
	
	public void Convert(){
		

		//showFields();
		PersonDynamicStandardized pds = PersonDynamicStandardized.PDS_Factory(this.getDynamicDataType()); // create the standardized dynamic data object
		if(pds != null){
			pds.setOriginalPersonDynamic(this);		
			pds.setPersonStandardizedToWhomDynamicDataRefers(getPersonToWhomDynamicDataRefers().getStandardizedPerson());
			//getPersonToWhomDynamicDataRefers().getStandardizedPerson().getDynamicDataOfPersonStandardized().add(pds);


			setStandardizedPersonDynamic(pds.transform(this));


			ArrayList<PersonDynamicStandardized> pdsa = getStandardizedPersonDynamic();


			// Next instructions link PersonDynamicStandardized <-> PersonStandardized

			for(PersonDynamicStandardized pds1: pdsa){
				getPersonToWhomDynamicDataRefers().getStandardizedPerson().getDynamicDataOfPersonStandardized().add(pds1);
				pds1.setPersonStandardizedToWhomDynamicDataRefers(getPersonToWhomDynamicDataRefers().getStandardizedPerson());

			}
		}

	}

	/**
	 * 
	 * This routine allocates a Message object and fills it with the parameters.
	 * Additional information is added to identify the B*-row(s) to which the message applies
	 * 
	 * @param number
	 * @param fills
	 */

	
	private void message(String number, String... fills){
		
		Message m = new Message(number);
		
		m.setKeyToRP(getKeyToRP());
		
		m.setKeyToSourceRegister(getKeyToSourceRegister());
		
		m.setDayEntryHead(getDayEntryHead());
		m.setMonthEntryHead(getMonthEntryHead());
		m.setYearEntryHead(getYearEntryHead());
		
		m.setDayEntryRP  (getPersonToWhomDynamicDataRefers().getRegistrationPersonAppearsIn().getDayEntryRP());
		m.setMonthEntryRP(getPersonToWhomDynamicDataRefers().getRegistrationPersonAppearsIn().getMonthEntryRP());
		m.setYearEntryRP (getPersonToWhomDynamicDataRefers().getRegistrationPersonAppearsIn().getYearEntryRP());
		
		
		m.setKeyToRegistrationPersons(getKeyToRegistrationPersons());
		m.setNatureOfPerson(getNatureOfPerson());
		
		m.save(fills); 
		
		
	}
	

	
	public int getKeyToSourceRegister() {
		return keyToSourceRegister;
	}

	public void setKeyToSourceRegister(int keyToSourceRegister) {
		this.keyToSourceRegister = keyToSourceRegister;
	}

	public int getDayEntryHead() {
		return dayEntryHead;
	}

	public void setDayEntryHead(int dayEntryHead) {
		this.dayEntryHead = dayEntryHead;
	}

	public int getMonthEntryHead() {
		return monthEntryHead;
	}

	public void setMonthEntryHead(int monthEntryHead) {
		this.monthEntryHead = monthEntryHead;
	}

	public int getYearEntryHead() {
		return yearEntryHead;
	}

	public void setYearEntryHead(int yearEntryHead) {
		this.yearEntryHead = yearEntryHead;
	}

	public int getKeyToRP() {
		return keyToRP;
	}

	public void setKeyToRP(int keyToRP) {
		this.keyToRP = keyToRP;
	}

	public int getKeyToRegistrationPersons() {
		return keyToRegistrationPersons;
	}

	
	public void setKeyToRegistrationPersons(
			int keyToRegistrationPersons) {
		this.keyToRegistrationPersons = keyToRegistrationPersons;
	}

	public int getDynamicDataType() {
		return dynamicDataType;
	}

	public void setDynamicDataType(
			int keyToDistinguishDynamicDataType) {
		this.dynamicDataType = keyToDistinguishDynamicDataType;
	}

	public int getDynamicDataSequenceNumber() {
		return dynamicDataSequenceNumber;
	}

	public void setDynamicDataSequenceNumber(
			int sequenceNumberToDistinguishDynamicData) {
		this.dynamicDataSequenceNumber = sequenceNumberToDistinguishDynamicData;
	}

	public int getContentOfDynamicData() {
		return contentOfDynamicData;
	}

	public void setContentOfDynamicData(int ContentOfDynamicData) {
		this.contentOfDynamicData = ContentOfDynamicData;
	}

	public int getValueOfRelatedPerson() {
		return valueOfRelatedPerson;
	}

	public void setValueOfRelatedPerson(int valueOfRelatedPerson) {
		this.valueOfRelatedPerson = valueOfRelatedPerson;
	}

	public int getNatureOfPerson() {
		return natureOfPerson;
	}

	public void setNatureOfPerson(int natureOfPerson) {
		this.natureOfPerson = natureOfPerson;
	}

	public int getDayOfMutation() {
		return dayOfMutation;
	}

	public void setDayOfMutation(int dayOfMutation) {
		this.dayOfMutation = dayOfMutation;
	}

	public int getMonthOfMutation() {
		return monthOfMutation;
	}

	public void setMonthOfMutation(int monthOfMutation) {
		this.monthOfMutation = monthOfMutation;
	}

	public int getYearOfMutation() {
		return yearOfMutation;
	}

	public void setYearOfMutation(int yearOfMutation) {
		this.yearOfMutation = yearOfMutation;
	}

	public int getDayOfMutationAfterInterpretation() {
		return dayOfMutationAfterInterpretation;
	}

	public void setDayOfMutationAfterInterpretation(int dayOfMutationAfterInterpretation) {
		this.dayOfMutationAfterInterpretation = dayOfMutationAfterInterpretation;
	}

	public int getMonthOfMutationAfterInterpretation() {
		return monthOfMutationAfterInterpretation;
	}

	public void setMonthOfMutationAfterInterpretation(int monthOfMutationAfterInterpretation) {
		this.monthOfMutationAfterInterpretation = monthOfMutationAfterInterpretation;
	}

	public int getYearOfMutationAfterInterpretation() {
		return yearOfMutationAfterInterpretation;
	}

	public void setYearOfMutationAfterInterpretation(int yearOfMutationAfterInterpretation) {
		this.yearOfMutationAfterInterpretation = yearOfMutationAfterInterpretation;
	}

	public String getDynamicData2() {
		return dynamicData2;
	}

	public void setDynamicData2(String dynamicData2) {
		this.dynamicData2 = dynamicData2;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getDate0() {
		return date0;
	}

	public void setDate0(String date) {
		this.date0 = date;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getVersionLastTimeOfDataEntry() {
		return versionLastTimeOfDataEntry;
	}

	public void setVersionLastTimeOfDataEntry(String versionLastTimeOfDataEntry) {
		this.versionLastTimeOfDataEntry = versionLastTimeOfDataEntry;
	}

	public String getResearchCodeOriginal() {
		return researchCodeOriginal;
	}

	public void setResearchCodeOriginal(String researchCodeOriginal) {
		this.researchCodeOriginal = researchCodeOriginal;
	}

	public String getOrderNumberOriginal() {
		return orderNumberOriginal;
	}

	public void setOrderNumberOriginal(String orderNumberOriginal) {
		this.orderNumberOriginal = orderNumberOriginal;
	}

	public String getDateOriginal() {
		return dateOriginal;
	}

	public void setDateOriginal(String dateOriginal) {
		this.dateOriginal = dateOriginal;
	}

	public String getInitialOriginal() {
		return initialOriginal;
	}

	public void setInitialOriginal(String initialOriginal) {
		this.initialOriginal = initialOriginal;
	}

	public String getVersionOriginalDataEntry() {
		return versionOriginalDataEntry;
	}

	public void setVersionOriginalDataEntry(String versionOriginalDataEntry) {
		this.versionOriginalDataEntry = versionOriginalDataEntry;
	}

	public Person getPersonToWhomDynamicDataRefers() {
		return personToWhomDynamicDataRefers;
	}

	public void setPersonToWhomDynamicDataRefers(
			Person personToWhomDynamicDataRefers) {
		this.personToWhomDynamicDataRefers = personToWhomDynamicDataRefers;
	}

	public ArrayList<PersonDynamicStandardized> getStandardizedPersonDynamic() {
		return standardizedPersonDynamic;
	}

	public void setStandardizedPersonDynamic(
			ArrayList<PersonDynamicStandardized> standardizedPersonDynamic) {
		this.standardizedPersonDynamic = standardizedPersonDynamic;
	}

	
    public int compareTo(PersonDynamic r) {
        if(dynamicDataSequenceNumber < r.getDynamicDataSequenceNumber()){
            return -1;
        } else if (dynamicDataSequenceNumber > r.getDynamicDataSequenceNumber()) {
            return 1;
        } else {       // keyToRP == r.getKeyToRP()
            return 0;
        }
    }


}