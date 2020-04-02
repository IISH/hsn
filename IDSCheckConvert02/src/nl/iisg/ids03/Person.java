/*
 * Naam:    Person
 * Version: 0.1
 *  Author: Cor Munnik
 * Copyright
 */

package nl.iisg.ids03;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import jdk.nashorn.internal.objects.annotations.Getter;
import nl.iisg.hsncommon.Common1;
import nl.iisg.hsncommon.ConstRelations2;
import nl.iisg.ref.*;


/**
 * 
 * This class handles the static attributes of a person (name, date of birth etc.)
 *
 */

@Entity
@Table(name="b2")
public class Person {
	
	@Id @Column(name = "B1IDBG")  private int       keyToSourceRegister;
	@Id @Column(name = "B2DIBG")  private int       dayEntryHead;                      
	@Id @Column(name = "B2MIBG")  private int       monthEntryHead;                      
	@Id @Column(name = "B2JIBG")  private int       yearEntryHead;     
	@Id	@Column(name = "IDNR")    private int       keyToRP;   
	@Id @Column(name = "B2RNBG")  private int       keyToRegistrationPersons;

	@Column(name = "B2FCBG")      private int       natureOfPerson;
	@Column(name = "B2RDNR")   	  private int       dayOfRegistration; 
	@Column(name = "B2RMNR")   	  private int       monthOfRegistration; 
	@Column(name = "B2RJNR")   	  private int       yearOfRegistration; 
	@Column(name = "B2RDCR")   	  private int       dayOfRegistrationAfterInterpretation; 
	@Column(name = "B2RMCR")   	  private int       monthOfRegistrationAfterInterpretation; 
	@Column(name = "B2RJCR")   	  private int       yearOfRegistrationAfterInterpretation; 
	@Column(name = "B2ANNR")   	  private String    familyName; 
	@Column(name = "B2VNNR")   	  private String    firstName;
	@Column(name = "B2GSNR")      private String    sex;

	@Column(name = "B2GDNR")      private int       dayOfBirth;
	@Column(name = "B2GMNR")      private int       monthOfBirth;
	@Column(name = "B2GJNR")      private int       yearOfBirth;
	@Column(name = "B2GDCR")      private int       dayOfBirthAfterInterpretation;
	@Column(name = "B2GMCR")      private int       monthOfBirthAfterInterpretation;
	@Column(name = "B2GJCR")      private int       yearOfBirthAfterInterpretation;
	@Column(name = "B2GNNR")      private String    placeOfBirth;

	@Column(name = "B2ODNR")      private int       dayOfDecease;
	@Column(name = "B2OMNR")      private int       monthOfDecease;
	@Column(name = "B2OJNR")      private int       yearOfDecease;
	@Column(name = "B2ODCR")      private int       dayOfDeceaseAfterInterpretation;
	@Column(name = "B2OMCR")      private int       monthOfDeceaseAfterInterpretation;
	@Column(name = "B2OJCR")      private int       yearOfDeceaseAfterInterpretation;
	@Column(name = "B2ONNR")      private String    placeOfDecease;

	@Column(name = "B2NANR")      private String    nationality;  
	@Column(name = "B2WDNR")      private String    legalPlaceOfLiving; 
	@Column(name = "B2VWNR")      private String    legalPlaceOfLivingInCodes;   
	@Column(name = "B2AANR")      private String    remarks; 
	@Column(name = "B2AAN")       private String    remarks2;  

	@Column(name = "OPDRNR")      private String    orderNumber;
	@Column(name = "DATUM")    	  private String    date0;
	@Column(name = "INIT")        private String    initials;
	@Column(name = "VERSIE")      private String    versionLastTimeOfDataEntry;
	@Column(name = "ONDRZKO")     private String    researchCodeOriginal;
	@Column(name = "OPDRNRO")     private String    orderNumberOriginal;
	@Column(name = "DATUMO")      private String    dateOriginal;
	@Column(name = "INITO")       private String    initialOriginal;
	@Column(name = "VERSIEO")     private String    versionOriginalDataEntry;

	@Transient                    private ArrayList<PersonDynamic> dynamicDataOfPerson = new ArrayList<PersonDynamic>();
	@Transient  				  private Registration             registrationPersonAppearsIn;
	@Transient                    private Boolean   isHead = false;
	@Transient                    private Boolean   isHeadFirstSuccessor = false;
	@Transient  PersonStandardized                  standardizedPerson = null;
	
	Person(){}  // no-args constructor
	
/**
 * 
 * This is the constructor for Person objects.
 * The Person object is initialized from a row from the B2 table
 * 
 * @param rowObjects
 */
	Person(Object [] rowObjects, String [] fieldnames, byte[] fieldtypes){
		
		Utils.trimAll(rowObjects);

		setKeyToSourceRegister(                              (Integer)Utils.getColumn("B1IDBG", rowObjects, fieldnames, fieldtypes));
		setDayEntryHead(                                     (Integer)Utils.getColumn("B2DIBG", rowObjects, fieldnames, fieldtypes));
		setMonthEntryHead(                                   (Integer)Utils.getColumn("B2MIBG", rowObjects, fieldnames, fieldtypes));
		setYearEntryHead(                                    (Integer)Utils.getColumn("B2JIBG", rowObjects, fieldnames, fieldtypes));
		setKeyToRP(                                          (Integer)Utils.getColumn("IDNR", rowObjects, fieldnames, fieldtypes));
		setKeyToRegistrationPersons(                         (Integer)Utils.getColumn("B2RNBG", rowObjects, fieldnames, fieldtypes));

		setNatureOfPerson(   		                         (Integer)Utils.getColumn("B2FCBG", rowObjects, fieldnames, fieldtypes));
		setDayOfRegistration(   	                         (Integer)Utils.getColumn("B2RDNR", rowObjects, fieldnames, fieldtypes));
		setMonthOfRegistration(   	                         (Integer)Utils.getColumn("B2RMNR", rowObjects, fieldnames, fieldtypes));
		setYearOfRegistration(   		                     (Integer)Utils.getColumn("B2RJNR", rowObjects, fieldnames, fieldtypes)); 
		setDayOfRegistrationAfterInterpretation(             (Integer)Utils.getColumn("B2RDCR", rowObjects, fieldnames, fieldtypes)); 
		setMonthOfRegistrationAfterInterpretation(           (Integer)Utils.getColumn("B2RMCR", rowObjects, fieldnames, fieldtypes));
		setYearOfRegistrationAfterInterpretation(            (Integer)Utils.getColumn("B2RJCR", rowObjects, fieldnames, fieldtypes));
		setFamilyName(                                        (String)Utils.getColumn("B2ANNR", rowObjects, fieldnames, fieldtypes));
		setFirstName(                                         (String)Utils.getColumn("B2VNNR", rowObjects, fieldnames, fieldtypes));
		setSex(                                               (String)Utils.getColumn("B2GSNR", rowObjects, fieldnames, fieldtypes));

		setDayOfBirth(   				                     (Integer)Utils.getColumn("B2GDNR", rowObjects, fieldnames, fieldtypes));
		setMonthOfBirth(  		                             (Integer)Utils.getColumn("B2GMNR", rowObjects, fieldnames, fieldtypes));
		setYearOfBirth(  				                     (Integer)Utils.getColumn("B2GJNR", rowObjects, fieldnames, fieldtypes));
		setDayOfBirthAfterInterpretation(  	                 (Integer)Utils.getColumn("B2GDCR", rowObjects, fieldnames, fieldtypes));
		setMonthOfBirthAfterInterpretation(  	             (Integer)Utils.getColumn("B2GMCR", rowObjects, fieldnames, fieldtypes));
		setYearOfBirthAfterInterpretation(   	             (Integer)Utils.getColumn("B2GJCR", rowObjects, fieldnames, fieldtypes));
		setPlaceOfBirth(                                      (String)Utils.getColumn("B2GNNR", rowObjects, fieldnames, fieldtypes));
		setDayOfDecease(                                     (Integer)Utils.getColumn("B2ODNR", rowObjects, fieldnames, fieldtypes));
		setMonthOfDecease(                                   (Integer)Utils.getColumn("B2OMNR", rowObjects, fieldnames, fieldtypes));
		setYearOfDecease(                                    (Integer)Utils.getColumn("B2OJNR", rowObjects, fieldnames, fieldtypes));
		setDayOfDeceaseAfterInterpretation(                  (Integer)Utils.getColumn("B2ODCR", rowObjects, fieldnames, fieldtypes));
		setMonthOfDeceaseAfterInterpretation(                (Integer)Utils.getColumn("B2OMCR", rowObjects, fieldnames, fieldtypes));
		setYearOfDeceaseAfterInterpretation(                 (Integer)Utils.getColumn("B2OJCR", rowObjects, fieldnames, fieldtypes));
		setPlaceOfDecease(                                    (String)Utils.getColumn("B2ONNR", rowObjects, fieldnames, fieldtypes));

		setNationality(                                       (String)Utils.getColumn("B2NANR", rowObjects, fieldnames, fieldtypes));
		setLegalPlaceOfLiving(                                (String)Utils.getColumn("B2WDNR", rowObjects, fieldnames, fieldtypes));
		setLegalPlaceOfLivingInCodes(                         (String)Utils.getColumn("B2VWNR", rowObjects, fieldnames, fieldtypes)); 
		setRemarks(                                           (String)Utils.getColumn("B2AANR", rowObjects, fieldnames, fieldtypes));
		setRemarks2(                                          (String)Utils.getColumn("B2AAN", rowObjects, fieldnames, fieldtypes)); 

		setOrderNumber(                                       (String)Utils.getColumn("OPDRNR", rowObjects, fieldnames, fieldtypes));
		setDate0(                                             (String)Utils.getColumn("DATUM", rowObjects, fieldnames, fieldtypes));	
		setInitials(                                          (String)Utils.getColumn("INIT", rowObjects, fieldnames, fieldtypes));
		setVersionLastTimeOfDataEntry(                        (String)Utils.getColumn("VERSIE", rowObjects, fieldnames, fieldtypes));
		setResearchCodeOriginal(                              (String)Utils.getColumn("ONDRZKO", rowObjects, fieldnames, fieldtypes));
		setOrderNumberOriginal(                               (String)Utils.getColumn("OPDRNRO", rowObjects, fieldnames, fieldtypes));
		setDateOriginal(                                      (String)Utils.getColumn("DATUMO", rowObjects, fieldnames, fieldtypes));
		setInitialOriginal(                                   (String)Utils.getColumn("INITO", rowObjects, fieldnames, fieldtypes));
		setVersionOriginalDataEntry(                          (String)Utils.getColumn("VERSIEO", rowObjects, fieldnames, fieldtypes));

		
		// Some adaptations are performed immediately after reading in the row
		
		adaptRegistrationDate();
		adaptDateOfBirth();
		adaptDateOfDecease();
		adaptFirstName();
		adaptFamilyName();
		

	}

	Person(Object [] rowObjects){
		
		Utils.trimAll(rowObjects);

		setKeyToSourceRegister(                                       Utils.toInt(rowObjects[1]));
		setDayEntryHead(                                              Utils.toInt(rowObjects[2]));
		setMonthEntryHead(                                            Utils.toInt(rowObjects[3]));
		setYearEntryHead(                                             Utils.toInt(rowObjects[4]));
		setKeyToRP(                                                   Utils.toInt(rowObjects[5])); 
		setKeyToRegistrationPersons(                                  Utils.toInt(rowObjects[6]));

		setNatureOfPerson(   		                                  Utils.toInt(rowObjects[7]));
		setDayOfRegistration(   	                                  Utils.toInt(rowObjects[8])); 
		setMonthOfRegistration(   	                                  Utils.toInt(rowObjects[9])); 
		setYearOfRegistration(   		                              Utils.toInt(rowObjects[10])); 
		setDayOfRegistrationAfterInterpretation(                      Utils.toInt(rowObjects[11])); 
		setMonthOfRegistrationAfterInterpretation(                    Utils.toInt(rowObjects[12])); 
		setYearOfRegistrationAfterInterpretation(                     Utils.toInt(rowObjects[13])); 
		setFamilyName(                                               (String)rowObjects[14]); 
		setFirstName(                                                (String)rowObjects[15]);
		setSex(                                                      (String)rowObjects[16]);

		setDayOfBirth(   				                              Utils.toInt(rowObjects[17]));
		setMonthOfBirth(  		                                      Utils.toInt(rowObjects[18]));
		setYearOfBirth(  				                              Utils.toInt(rowObjects[19]));
		setDayOfBirthAfterInterpretation(  	                          Utils.toInt(rowObjects[20]));
		setMonthOfBirthAfterInterpretation(  	                      Utils.toInt(rowObjects[21]));
		setYearOfBirthAfterInterpretation(   	                      Utils.toInt(rowObjects[22]));
		setPlaceOfBirth(                                             (String)rowObjects[23]);
		setDayOfDecease(                                              Utils.toInt(rowObjects[24]));
		setMonthOfDecease(                                            Utils.toInt(rowObjects[25]));
		setYearOfDecease(                                             Utils.toInt(rowObjects[26]));
		setDayOfDeceaseAfterInterpretation(                           Utils.toInt(rowObjects[27]));
		setMonthOfDeceaseAfterInterpretation(                         Utils.toInt(rowObjects[28]));
		setYearOfDeceaseAfterInterpretation(                          Utils.toInt(rowObjects[29]));
		setPlaceOfDecease(                                           (String)rowObjects[30]);

		setNationality(                                               (String)rowObjects[31]);  
		setLegalPlaceOfLiving(                                        (String)rowObjects[32]); 
		setLegalPlaceOfLivingInCodes(                                 (String)rowObjects[33]);   
		setRemarks(                                                   (String)rowObjects[34]); 
		setRemarks(                                                   (String)rowObjects[35]);  

		setOrderNumber(                                               (String)rowObjects[36]);
		setDate0(                                                      Utils.toStr(rowObjects[37]));		
		setInitials(                                                  (String)rowObjects[38]);
		setVersionLastTimeOfDataEntry(                                (String)rowObjects[39]);
		setResearchCodeOriginal(                                      (String)rowObjects[40]);
		setOrderNumberOriginal(                                       (String)rowObjects[41]);
		setDateOriginal(                                               Utils.toStr(rowObjects[42]));
		setInitialOriginal(                                           (String)rowObjects[43]);
		setVersionOriginalDataEntry(                                  (String)rowObjects[44]);

		
		// Some adaptations are performed immediately after reading in the row
		
		adaptRegistrationDate();
		adaptDateOfBirth();
		adaptDateOfDecease();
		adaptFirstName();
		adaptFamilyName();
		

	}


	public boolean contains(PersonDynamic pd){

		if(getKeyToSourceRegister()           == pd.getKeyToSourceRegister() &&    
				getDayEntryHead()             == pd.getDayEntryHead() &&		
				getMonthEntryHead()           == pd.getMonthEntryHead() &&
				getYearEntryHead()            == pd.getYearEntryHead() &&
				getKeyToRP()                  == pd.getKeyToRP() &&
				getKeyToRegistrationPersons() == pd.getKeyToRegistrationPersons()){

			return true;
		}	
		else{
			return false;
		}	


	}
	
	public boolean higher(PersonDynamic pd){

		if(getKeyToRP()              > pd.getKeyToRP())                  return true;
		if(getKeyToRP()              < pd.getKeyToRP())                  return false;
		if(getYearEntryHead()        > pd.getYearEntryHead())            return true;
		if(getYearEntryHead()        < pd.getYearEntryHead())            return false;
		if(getMonthEntryHead()       > pd.getMonthEntryHead())           return true;
		if(getMonthEntryHead()       < pd.getMonthEntryHead())           return false;
		if(getDayEntryHead()         > pd.getDayEntryHead())             return true;
		if(getDayEntryHead()         < pd.getDayEntryHead())             return false;
		if(getKeyToSourceRegister()  > pd.getKeyToSourceRegister())      return true;
		if(getKeyToSourceRegister()  < pd.getKeyToSourceRegister())      return false;
		if(getKeyToRegistrationPersons() > pd.getKeyToRegistrationPersons()) return true;
		if(getKeyToRegistrationPersons() < pd.getKeyToRegistrationPersons()) return false;
		

		
		return false;
		
	}

	public boolean check(){

		boolean returnCode = true;
		//System.out.println("Check Person");

		//showFields();
		

		// Find ainb row for person

		Ref_AINB ainb = Ref.getAINB(getKeyToSourceRegister());

		checkDates(ainb); 
		checkNames(ainb);

		//
		// Check all PersonDynamic objects for person
		//

		for(PersonDynamic pd : getDynamicDataOfPerson()){
			pd.check();
		}

		//
		// Now check consistency of PersonDynamic
		//

		checkConsistency(ainb);

		if(getNatureOfPerson() == ConstRelations2.FIRST_APPEARANCE_OF_OP ||
				getNatureOfPerson() == ConstRelations2.FURTHER_APPEARANCE_OF_OP ){
			returnCode = checkOP(ainb);
			//System.out.println("checkOP rc = " + returnCode); // ASDZ

		}
		
		if(getIsHead() && !getIsHeadFirstSuccessor())
			checkHead(ainb);
		
		if(getIsHeadFirstSuccessor() == true)
			checkExplicitHead(ainb);
		
		//System.out.println("        Person.check, rc =  " + returnCode);


		return returnCode;
	}

	/**
	 * 
	 * @param ainb
	 * 
	 * This method performs consistency check on the dynamic data elements of person (PersonDynamic)
	 * The following message numbers can be issued:
	 * 
	 * 1302
	 * 1303
	 * 1304
	 */

	public void checkConsistency(Ref_AINB ainb){


		int religion = 0;
		int civilStatus = 0;
		int relationToHead = 0;

		for(PersonDynamic pd: getDynamicDataOfPerson()){
			switch(pd.getDynamicDataType()){
			case ConstRelations2.RELATIE_TOT_HOOFD: relationToHead++;   break;
			case ConstRelations2.BURGELIJKE_STAAT:  civilStatus++;      break;
			case ConstRelations2.GODSDIENST:        religion++;         break;
			}		
		}
        // Check that there is a relation to head 
		if(relationToHead == 0)
			message("1302");

		// Check that there is a civil status
		if(civilStatus == 0)
			message("1303");

		// Check that there is a religion
		if(religion == 0)
			message("1304");
		
		// Check consistency arrival/departure dates
		
		checkArrivalDepartures();
		
        		
		

		
		

	}


	/**
	 * 
	 * @param ainb
	 * 
	 * This method performs date checks on Person.
	 * The following message numbers can be issued:
     *	
     *	1116
     *	1117
	 *  1195
	 *  1196
	 *  1197
	 *  1201
	 *  1202
	 *  1204
	 *  1205
	 *  1206
	 *  1207
	 *  1208
	 *  1209
	 *  1210
	 *  1211
	 *  1212
	 *  1213
	 *  1218
	 *  1219
	 *  1220
	 * 
	 */

	public void checkDates(Ref_AINB ainb){
		
		
		int headDateValid = Common1.dateIsValid(getDayEntryHead(), getMonthEntryHead(),getYearEntryHead());

		
		if(headDateValid == -1)
			message("1117", "" + getDayEntryHead() + "-" + getMonthEntryHead() + "-" + getYearEntryHead());
		else 
			if(headDateValid == 1)
				message("1116", "" + getDayEntryHead() + "-" + getMonthEntryHead() + "-" + getYearEntryHead());
			


		if(ainb != null){
			
		
			int day   = getDayOfRegistration()   > 0 ? getDayOfRegistration()   : 1;
			int month = getMonthOfRegistration() > 0 ? getMonthOfRegistration() : 1;
			int year  = getYearOfRegistration(); 
			
			// Check if valid registration date for non-C-register 
			
			// For compatibility with old program
			// Check that registration date != -3/-3/-3 for non C-Register 


			if(ainb.getTypeRegister().toUpperCase().equals("C") != true &&
					(getDayOfRegistration() == -3 && getMonthOfRegistration() == -3 && getYearOfRegistration() == -3))
				message("1195", "" + getDayOfRegistration() + "-" + getMonthOfRegistration() + "-" + getYearOfRegistration());

			// Check that registration date = -3/-3/-3 for C-Register 
			
			if(ainb.getTypeRegister().toUpperCase().equals("C") == true &&
					!(getDayOfRegistration() == -3 && getMonthOfRegistration() == -3 && getYearOfRegistration() == -3))
				message("1218");

			// Check that registration date for non C-Register is not -3/-3/-3

			if(ainb.getTypeRegister().toUpperCase().equals("C") != true &&
					(getDayOfRegistration() == -3 && getMonthOfRegistration() == -3 && getYearOfRegistration() == -3))
				message("1219");


			// Check if date of decease not after range register
			
			int startYear = ainb.getStartYearRegisterCorrected() != 0 ? ainb.getStartYearRegisterCorrected() : ainb.getStartYearRegister();  
			int endYear   = ainb.getEndYearRegisterCorrected()   != 0 ? ainb.getEndYearRegisterCorrected()   : ainb.getEndYearRegister();  

			if(getYearOfDecease() >  endYear){

				if(1940 < getYearOfDecease() && getYearOfDecease() < 1970)
					message("1220", "" + getDayOfDecease()            + "-" + getMonthOfDecease()      + "-" + getYearOfDecease(),
							"" +  startYear + "-" + endYear); 
				else
					message("1209", "" + getDayOfDecease()            + "-" + getMonthOfDecease()      + "-" + getYearOfDecease(),
							"" +  startYear + "-" + endYear); 
			}

			// Check if date of decease not before range register

			if(getYearOfDecease() > 0 && getYearOfDecease() < startYear)		
				message("1210", "" + getDayOfDecease()            + "-" + getMonthOfDecease()      + "-" + getYearOfDecease(),
						"" +  startYear + "-" + endYear); 

			// Check if date of registration not after range register

			if(getYearOfRegistration() >  endYear)		
				message("1211", "" +      getDayOfRegistration() + "-" + getMonthOfRegistration() + "-" + getYearOfRegistration(), 
						"" +  startYear + "-" + endYear); 

			// Check if date of registration not before range register

			if(getYearOfRegistration() >  0 && getYearOfRegistration() < startYear)		
				message("1212", "" +      getDayOfRegistration() + "-" + getMonthOfRegistration() + "-" + getYearOfRegistration(), 
						"" +  startYear + "-" + endYear); 

		}	
		
		// Check if valid birth date 

		if(Common1.dateIsValid(getDayOfBirth(), getMonthOfBirth(), getYearOfBirth()) == 1)
			message("1196", "" + getDayOfBirth() + "-" + getMonthOfBirth() + "-" + getYearOfBirth());

		// Check if valid birth date (Does not contain -1 or -2) 

		if(Common1.dateIsValid(getDayOfBirth(), getMonthOfBirth(), getYearOfBirth()) == -1)
			message("1197", "" + getDayOfBirth() + "-" + getMonthOfBirth() + "-" + getYearOfBirth());


		// Check if valid decease date  
		
		if(getYearOfDecease() != 0){

			if( Common1.dateIsValid(getDayOfDecease(), getMonthOfDecease(), getYearOfDecease()) == 0){
				if(getYearOfDecease() > 1940)
					message("1201");
			}	
			else
				if(getYearOfDecease() < 0)
					message("1213", "" + getDayOfDecease() + "-" + getMonthOfDecease() + "-" + getYearOfDecease());
				else
					message("1202");
		}

		// Check that registration date is later than birth date

		int day   = getDayOfRegistration()   > 0 ? getDayOfRegistration()   : 28;
		int month = getMonthOfRegistration() > 0 ? getMonthOfRegistration() : 12;
		int year  = getYearOfRegistration(); 

		
		int i1 = Utils.dayCount(getDayOfBirth(),        getMonthOfBirth(),        getYearOfBirth());
		int i2 = Utils.dayCount(day, month, year);

		if(i1 > 0 && i2 > 0 && i1 > i2)
			message("1204", "" + day                       + "-" + month                    + "-" + year, 
				        	"" + getDayOfBirth()           + "-" + getMonthOfBirth()        + "-" + getYearOfBirth());


		// Check that registration date is earlier than decease date
		
		day   = getDayOfRegistration()   > 0 ? getDayOfRegistration()   : 1;
		month = getMonthOfRegistration() > 0 ? getMonthOfRegistration() : 1;
		year  = getYearOfRegistration();

		i2 = Utils.dayCount(day, month, year);
		i1 = Utils.dayCount(getDayOfDecease(),      getMonthOfDecease(),      getYearOfDecease());

		if( i1 > 0 && i2 > 0 && i2 > i1)
			message("1205", "" + day                       + "-" + month                    + "-" + year, 
				        	"" + getDayOfDecease()      + "-" + getMonthOfDecease()      + "-" + getYearOfDecease());

		// Check that birth date not later than decease date

		i1 = Utils.dayCount(getDayOfBirth(),        getMonthOfBirth(),        getYearOfBirth());
		i2 = Utils.dayCount(getDayOfDecease(),      getMonthOfDecease(),      getYearOfDecease());


		if( i1 > 0 && i2 > 0 && i1 > i2)
			message("1206", "" + getDayOfBirth()        + "-" + getMonthOfBirth()        + "-" + getYearOfBirth(), 
					        "" + getDayOfDecease()      + "-" + getMonthOfDecease()      + "-" + getYearOfDecease());

		// Check if birth date note more than 90 year before decease date 	    

		if( i1 > 0 && i2 > 0 && i2 > i1 + 90 * 365 + 23)
			message("1207", "" + getDayOfBirth()        + "-" + getMonthOfBirth()        + "-" + getYearOfBirth(), 
				        	"" + getDayOfDecease()      + "-" + getMonthOfDecease()      + "-" + getYearOfDecease());

		// Check if birth date note more than 100 year before decease date 	    

		if( i1 > 0 && i2 > 0 && i2 > i1 + 100 * 365 + 25)
			message("1208", "" + getDayOfBirth()        + "-" + getMonthOfBirth()        + "-" + getYearOfBirth(), 
				        	"" + getDayOfDecease()      + "-" + getMonthOfDecease()      + "-" + getYearOfDecease());

	}

	/**
	 * 
	 * @param ainb
	 * 
	 * This method performs name checks on Person.
	 * The following message numbers can be issued:
	 * 
	 * 1221
	 * 1222
	 * 1223
	 * 1224
	 * 1225
	 * 1226
	 * 1230
	 * 1238
	 * 1239
	 * 1241
	 * 1242
	 * 1245
	 * 1247
	 * 1251
	 * 1252
	 * 1253
	 * 1254
	 * 1255
	 * 1256
	 *  
	 *  
	 */

	public void checkNames(Ref_AINB ainb){
		
		if(getFamilyName().trim().equalsIgnoreCase("GEEN OP"))
			return;

		// Check that family name is specified

		if(getFamilyName() != null && getFamilyName().trim().equals("") == true)
			message("1221", "" + getKeyToRegistrationPersons());

		// Check that first name is specified

		if(getFirstName() != null && getFirstName().trim().equals("") == true)
			message("1222", "" + getKeyToRegistrationPersons());


		// Check that family name does not contain (meldcode) '@'

		if(getFamilyName() != null && getFamilyName().indexOf("@") >= 0){
			message("1223"); 
		}

		// Check that family name does not contain (meldcode) '#'

		if(getFamilyName() != null && getFamilyName().indexOf("#") >= 0){
			message("1224"); 
		}

		// Check that first name does not contain (meldcode) '@'

		if(getFirstName() != null && getFirstName().indexOf("@") >= 0){
			message("1225"); 
		}

		// Check that first name does not contain (meldcode) '#'

		if(getFirstName() != null && getFirstName().indexOf("#") >= 0){
			message("1226"); 
		}

		// Check that first name does not contain (meldcode) '$'

		if(getFirstName() != null && getFirstName().indexOf("$") >= 0){
			message("1230"); 
		}

		// Check that family name does not contain substring 'Geb'

		if(getFamilyName() != null && getFamilyName().toUpperCase().indexOf("GEB") >= 0  && getFamilyName().indexOf("%") < 0){
			message("1238", getFamilyName()); 
		}

		// Check that family name does not contain substring 'Wed'

		if(getFamilyName() != null && getFamilyName().toUpperCase().indexOf("WED") >= 0  && getFamilyName().indexOf("%") < 0){
			message("1239", getFamilyName()); 
		}

		// Check that sex is correct

		if(getSex() != null && getSex().toUpperCase().equals("M") != true  && getSex().toUpperCase().equals("V") != true && getSex().toUpperCase().equals("O") != true)
			message("1241");

		// Check that sex is not O(nbekend)

		if(getSex() != null && getSex().toUpperCase().equals("O") == true)
			message("1242");

		// Check that birth place is not 'N'

		if(getPlaceOfBirth() != null && getPlaceOfBirth().trim().toUpperCase().equals("N"))
			message("1245");

		// Check that decease place is not 'N'

		if(getPlaceOfDecease() != null && getPlaceOfDecease().trim().toUpperCase().equals("N"))
			message("1247");

		// Check for C - registers that legalPlaceOfLivingInCodes - B2VWNR cannot have certain values  

		if(ainb != null && ainb.getTypeRegister().toUpperCase().equals("C") && getLegalPlaceOfLivingInCodes() != null && 
				(getLegalPlaceOfLivingInCodes().toUpperCase().equals("V ") == true ||			                      
						getLegalPlaceOfLivingInCodes().toUpperCase().equals(" V") == true ||			                      
						getLegalPlaceOfLivingInCodes().toUpperCase().equals("W ") == true ||			                      
						getLegalPlaceOfLivingInCodes().toUpperCase().equals(" W") == true ||			                      
						getLegalPlaceOfLivingInCodes().toUpperCase().equals("VW") == true ||			                      
						getLegalPlaceOfLivingInCodes().toUpperCase().equals("WV") == true))			                      

			message("1251");

		// Check that legalPlaceOfLiving does not contain '@'

		if(getLegalPlaceOfLiving() != null && getLegalPlaceOfLiving().indexOf("@") >= 0)
			message("1252");

		// Check that legalPlaceOfLiving does not contain '#'

		if(getLegalPlaceOfLiving() != null && getLegalPlaceOfLiving().indexOf("#") >= 0)
			message("1254");

		// Check that remarks does not contain '@'

		if(getRemarks() != null && getRemarks().indexOf("@") >= 0)
			message("1253");

		// Check that remarks2 does not contain '@'

		if(getRemarks2() != null && getRemarks2().indexOf("@") >= 0)
			message("1253");

		// Check that remarks does not contain '#'

		if(getRemarks() != null && getRemarks().indexOf("#") >= 0)
			message("1255");

		// See if parts of the remarks field must be edited/moved
		
		if(getRemarks() != null && getRemarks().trim().length() != 0 && !getRemarks().trim().equalsIgnoreCase("N") && getRemarks().indexOf("!!!") < 0){
			message("1256",  getRemarks());
		}

	}

	/**
	 * 
	 *  This routine checks the arrival and departure dates for consistency
	 *  The following messages can be issued:
	 *  
	 *    1324
	 *    1325
	 *    1326
	 *    1327
	 *    1328
	 *    1329
	 * 
	 * 
	 * 
	 * 
	 */
	
	private void checkArrivalDepartures(){

		int arrivalI = 0;
		int departureI = 0;

		int[] arrivalDays     = new int[2];
		int[] departureDays   = new int[2];


		for(PersonDynamic pd: getDynamicDataOfPerson()){

			if(pd.getDynamicDataType() == ConstRelations2.AANKOMST){
				if(arrivalI < 2){
					if(Common1.dateIsValid(pd.getDayOfMutation(), pd.getMonthOfMutation(),pd.getYearOfMutation()) == 0)
						arrivalDays[arrivalI] = Utils.dayCount(pd.getDayOfMutation(), pd.getMonthOfMutation(),pd.getYearOfMutation());
					else
						arrivalDays[arrivalI] = -1;
				}	
				arrivalI++;
			}

			if(pd.getDynamicDataType() == ConstRelations2.VERTREK){
				if(departureI < 2){
					if(Common1.dateIsValid(pd.getDayOfMutation(), pd.getMonthOfMutation(),pd.getYearOfMutation()) == 0)
						departureDays[departureI]   = Utils.dayCount(pd.getDayOfMutation(), pd.getMonthOfMutation(),pd.getYearOfMutation());
					else
						departureDays[departureI] = -1;
				}
				departureI++;
			}
		}
		
		// Check that mo more than 2 arrivals or departures
		
		if(arrivalI > 2)
			message("1325");
		if(departureI > 2)
			message("1326");

		// Check that the arrivals are chronological
		
		if(arrivalDays[0] > 0 && arrivalDays[1] > 0 && arrivalDays[0]  >= arrivalDays[1])
			message("1323");

		// Check that the departures  are chronological

		if(departureDays[0] > 0 && departureDays[1] > 0 && departureDays[0]  >= departureDays[1])
			message("1324");
		
		
		// Check for 2 arrivals and no valid first departure
		
		if(arrivalI >= 2 && departureDays[0]  <= 0)
			message("1322");
		
		// Check that the date of second arrival is valid
		
		if(arrivalI >= 2 && arrivalDays[1] <= 0)
			message("1327"); 
		
		// Check that the date of first departure is valid (with 2 departures !)
		
		if(departureI >= 2 && departureDays[0] <= 0)
			message("1328");

		// Check that the first arrival earlier than second departure
		
		if(arrivalI >= 1 && departureI >= 2 && departureDays[1] > 0 && arrivalDays[0] >= departureDays[1]){
			message("1329");
		}
			
		

		//if(1 == 1)
		//	return;
		
		if(arrivalDays[0] == 0 && departureDays[0] == 0){

			return;
		}


		if(arrivalDays[1] == 0 && departureDays[1] == 0){

			// 1    Maximal 1 arrival and maximal 1 departure
			//
			// 1A   1st arrival          -                     OK
			// 1B       -              1st departure           OK
			// 1C   1st arrival   <    1st departure           OK
			// 1D   1st arrival   >    1st departure           OK, will be repaired later

			
			return;
		}


		if(arrivalDays[1] == 0 && departureDays[1] != 0){

			// 2   Maximal 1 arrival and 2 departures
			//
			// 2A  1st arrival        <     1st departure
			//        -               <     2nd departure      Error 1327: Second arrival date invalid/missing
			// 2B  1st arrival = -1         1st departure
			//        -                     2nd departure      Error 1327: Second arrival date invalid/missing
			// 2C  1st arrival        <     1st departure
			//        -               >     2nd departure      Error 1324: Date first departure later/equal second departure + Error 1327
			// 2D  1st arrival        >     1st departure
			//        -               <     2nd departure      Error 1327
			// 2E  1st arrival        >     1st departure
			//        -               >     2nd departure      Error 1329: Date first arrival later/equal date second departure + Error 1327
			// 2F     -                     1st departure
			//        -                     2nd departure      Error 1327


			if(arrivalDays[0] > 0){
				if(departureDays[1] != -1){
					if(arrivalDays[0] < departureDays[0] && arrivalDays[0] < departureDays[1])  // 2A
						message("1327");
					if(arrivalDays[0] < departureDays[0] && arrivalDays[0] > departureDays[1]){  // 2C
						message("1324");
						message("1327");

					}	
					if(arrivalDays[0] > departureDays[0] && arrivalDays[0] < departureDays[1])  // 2D
						message("1327");
					if(arrivalDays[0] > departureDays[0] && arrivalDays[0] > departureDays[1]){  // 2E
						message("1329");
						message("1327");
					}	
				}
			}
			else  // 2B + 2F
				message("1327");
			
			return;
		}

		if(arrivalDays[1] != 0 && departureDays[1] == 0){

			// 3   Maximal 1 departure and 2 arrivals
			//
			// 3A  1st arrival        <     1st departure
			//     2nd arrival        <          -               OK will be repaired later
			
			// 3B  1st arrival              1st departure = -1
			//     2nd arrival                   -               Error 1322: 2 arrivals, no valid departure 
			
			// 3C  1st arrival        >     1st departure 
			//     2nd arrival        <                          Error 1323: Date second arrival earlier/equal first arrival + Error 1327 Second arrival date invalid/missing
			
			// 3D  1st arrival        <     1st departure
			//     2nd arrival        >                          OK will be repaired later
			
			// 3E  1st arrival        >     1st departure        
			//     2nd arrival        >                          OK will be repaired later
			
			// 3F  1st arrival                   -       
			//     2nd arrival                   -               Error 1322: 2 arrivals, no valid departure
			
			// 3G  1st arrival              1st departure
			//     2nd arrival = -1                              Error 1327: Second arrival date invalid/missing 


			if(departureDays[0] > 0){
				if(arrivalDays[1] != -1){
					if(arrivalDays[0] < departureDays[0] && arrivalDays[1] < departureDays[0])  // 3A
						; // OK
					if(arrivalDays[0] > departureDays[0] && arrivalDays[1] < departureDays[0]){ // 3C

						message("1323");
						message("1327");
					}
					if(arrivalDays[0] < departureDays[0] && arrivalDays[1] > departureDays[0])  // 3D
						; // OK
					if(arrivalDays[0] > departureDays[0] && arrivalDays[1] > departureDays[0]){ // 3E
						; // OK
					}
				}
				else
					message("1327"); // 3G
			}
			else{
				message("1322");// 3B + 3F
				

			}
			
			return;
			
		}

		// 2 arrivals and 2 departures

		// Only check situations with one or more -1 values
		if(arrivalDays[0] != -1 && departureDays[0] != -1  && arrivalDays[1] != -1 && departureDays[1] != -1){
			; // 4 All OK 
			return;

		}

		if((arrivalDays[0] == -1 || departureDays[0] == -1)  && arrivalDays[1] != -1 && departureDays[1] != -1){ 	// 1 or 2 times -1 in first arrival/departure				

			// 5  2 departures and 2 arrivals, first one can be -1
			//
			// 5A  1st arrival              1st departure = -1
			//     2nd arrival        >     2nd departure        Error 1322: 2 arrivals, no valid departure + Error 1328: Date first departure invalid/missing
			// 5B  1st arrival              1st departure = -1
			//     2nd arrival        <     2nd departure        Error 1322: 2 arrivals, no valid departure + Error 1328: Date first departure invalid/missing
			// 5C  1st arrival = -1         1st departure
			//     2nd arrival        <     2nd departure        OK
			// 5D  1st arrival = -1         1st departure
			//     2nd arrival        >     2nd departure        OK
			// 5E  1st arrival = -1         1st departure = -1   
			//     2nd arrival        <     2nd departure        Error 1322: 2 arrivals, no valid departure + Error 1328: Date first departure invalid/missing
			// 5F  1st arrival = -1         1st departure = -1
			//     2nd arrival        >     2nd departure        Error 1322: 2 arrivals, no valid departure + Error 1328: Date first departure invalid/missing

			if(departureDays[0] == -1){
				message("1322");
				message("1328");
				
			}
			
			return;
		}

		if((arrivalDays[1] == -1 || departureDays[1] == -1)  && arrivalDays[0] != -1 && departureDays[0] != -1){  // 1 or 2 times -1 in second arrival/departure	

			// 6  2 departures and 2 arrivals, second one can be -1
			//
			// 6A  1st arrival        >     1st departure    
			//     2nd arrival              2nd departure = -1   OK
			// 6B  1st arrival        <     1st departure 
			//     2nd arrival              2nd departure = -1   OK
			// 6C  1st arrival        >     1st departure
			//     2nd arrival = -1         2nd departure        Error 1327: Second arrival date invalid/missing
			// 6D  1st arrival        <     1st departure
			//     2nd arrival = -1         2nd departure        Error 1327: Second arrival date invalid/missing
			// 6E  1st arrival        >     1st departure        
			//     2nd arrival = -1         2nd departure = -1   Error 1327: Second arrival date invalid/missing
			// 6F  1st arrival        <     1st departure     
			//     2nd arrival = -1         2nd departure = -1   Error 1327: Second arrival date invalid/missing

			if(arrivalDays[1] == -1)
				message("1327");
			
			return;
		}

		// 3 or more values -1

		// 7  2 departures and 2 arrivals, 3 or more times -1
		//
		// 7A  1st arrival = -1         1st departure = -1
		//     2nd arrival              2nd departure = -1   Error 1322: 2 arrivals, no valid departure + Error 1328: Date first departure invalid/missing
		// 7B  1st arrival              1st departure = -1
		//     2nd arrival = -1         2nd departure = -1   Error 1327: Second arrival date invalid/missing + Error 1328: Date first departure invalid/missing
		// 7C  1st arrival = -1         1st departure = -1
		//     2nd arrival = -1         2nd departure        Error 1327: Second arrival date invalid/missing + Error 1328: Date first departure invalid/missing
		// 7D  1st arrival = -1         1st departure
		//     2nd arrival = -1         2nd departure = -1   Error 1327: Second arrival date invalid/missing
		// 7E  1st arrival = -1         1st departure = -1        
		//     2nd arrival = -1         2nd departure = -1   Error 1327: Second arrival date invalid/missing + Error 1328: Date first departure invalid/missing


		if((arrivalDays[0] == -1 && arrivalDays[1] == -1 && departureDays[0] == -1) ||
				(arrivalDays[0] == -1 && arrivalDays[1] == -1 && departureDays[1] == -1) ||
				(arrivalDays[0] == -1 && departureDays[0] == -1 && departureDays[1] == -1) ||
				(arrivalDays[1] == -1 && departureDays[0] == -1 && departureDays[1] == -1)){

			if(arrivalDays[1] != 0){
				message("1322");
				message("1328");
			}
			else
				if(arrivalDays[0] != 0){
					message("1327");
					message("1328");
				}
				else
					if(departureDays[1] != 0){
						message("1327");
						message("1328");
					}
					else
						if(departureDays[0] != 0){
							message("1327");
						}
						else{
							message("1327");
							message("1328");
						}

			return;
		}

	}

	
	/**
	 * 
	 * @param 
	 * 
	 * This method performs checks on OP Person.
	 * The following message numbers can be issued:
	 * 
	 *  1002
	 *  1003
	 *  1004
	 *  1005
	 *  1153
	 *  1154
	 *  1155
	 *  1156
	 *  1157
	 *  1158
	 *  1159
	 *  1166
	 *  1180
	 *  1181
	 *  1182
	 *  1183
	 *  1184
	 *  1185
	 *  1186
	 *  1187
	 *  
	 */

	private boolean checkOP(Ref_AINB ainb){
		
		boolean returnCode = true;
		
		if(getFamilyName().trim().equalsIgnoreCase("GEEN OP")){
			System.out.println("GEEN OP!!!");
			return true;
		}

		Registration r = getRegistrationPersonAppearsIn();

		// Check that the OP date is valid 

		if(Common1.dateIsValid(r.getDayEntryRP(), r.getMonthEntryRP(), r.getYearEntryRP() ) < 0)
			message("1003", "" + r.getDayEntryRP() + "-" + r.getMonthEntryRP() + "-" + r.getYearEntryRP());
		else
			if(Common1.dateIsValid(r.getDayEntryRP(), r.getMonthEntryRP(), r.getYearEntryRP() ) > 0)
				message("1002", "" + r.getDayEntryRP() + "-" + r.getMonthEntryRP() + "-" + r.getYearEntryRP());
			else{

				int opdate =   Utils.dayCount(r.getDayEntryRP(), r.getMonthEntryRP(), r.getYearEntryRP());

				if(Common1.dateIsValid(getDayEntryHead(), getMonthEntryHead(),getYearEntryHead()) == 0){

					// Check if OP date before date Head


					int headdate = Utils.dayCount(getDayEntryHead(), getMonthEntryHead(),getYearEntryHead());

					if(opdate < headdate)
						message("1004", "" + r.getDayEntryRP() + "-" + r.getMonthEntryRP() + "-" + r.getYearEntryRP(), 
								"" + getDayEntryHead() + "-" + getMonthEntryHead() + "-" + getYearEntryHead());  


					// If OP is Head, OP-date and Headdate must be equal

					if(getIsHead() == true)
						if(opdate != headdate){
							message("1005", "" + getDayEntryHead() + "-" + getMonthEntryHead() + "-" + getYearEntryHead(),  
									"" + r.getDayEntryRP() + "-" + r.getMonthEntryRP() + "-" + r.getYearEntryRP());
							returnCode = false;
						}
				}


				// Check that OP date in range Bevolkingsregister


				int startYear = ainb.getStartYearRegisterCorrected() != 0 ? ainb.getStartYearRegisterCorrected() : ainb.getStartYearRegister();  
				int endYear   = ainb.getEndYearRegisterCorrected()   != 0 ? ainb.getEndYearRegisterCorrected()   : ainb.getEndYearRegister();  


				if(ainb != null){
					if(startYear > 0){
						if(r.getYearEntryRP() < startYear -1){
							message("1153", "" + r.getDayEntryRP() + "-" + r.getMonthEntryRP() + "-" + r.getYearEntryRP(), (new Integer(startYear)).toString(), (new Integer(endYear)).toString()); 
						}
					}

					if(endYear > 0){
						if(r.getYearEntryRP() > endYear + 1){
							message("1154", "" + r.getDayEntryRP() + "-" + r.getMonthEntryRP() + "-" + r.getYearEntryRP(), (new Integer(startYear)).toString(), (new Integer(endYear)).toString()); 
						}
					}
				}

				// Check if OP registration date (if given) more than 20 day before OP date
				
				int year  = getYearOfRegistration();
				int month = getMonthOfRegistration() > 0 ? getMonthOfRegistration() : 1;
				int day   = getDayOfRegistration()   > 0 ? getDayOfRegistration()   : 1;

				if(getNatureOfPerson() == ConstRelations2.FIRST_APPEARANCE_OF_OP) {
					
					if(Common1.dateIsValid(day, month, year) == 0){

						int registrationdate = Utils.dayCount(day, month, year);
						if(opdate + 20 < registrationdate)
							message("1155", "" + r.getDayEntryRP() + "-" + r.getMonthEntryRP() + "-" + r.getYearEntryRP(), 
									"" + day               + "-" + month               + "-" + year);  
					}
				}

				// Check that OP date not before birth date OP (if given)

				year  = getYearOfBirth();
				month = getMonthOfBirth() > 0 ? getMonthOfBirth() : 1;
				day   = getDayOfBirth()   > 0 ? getDayOfBirth()   : 1;

				if(Common1.dateIsValid(day, month, year) == 0){

					int birthdate = Utils.dayCount(day, month, year);
					if(opdate  < birthdate)
						message("1156", "" + r.getDayEntryRP() + "-" + r.getMonthEntryRP() + "-" + r.getYearEntryRP(), 
								"" + day               + "-" + month               + "-" + year);  
				}

				// Check that OP date not after decease date OP (if given)

				year  = getYearOfDecease();
				month = getMonthOfDecease() > 0 ? getMonthOfDecease() : 12;
				day   = getDayOfDecease()   > 0 ? getDayOfDecease()   : 28;

				if(Common1.dateIsValid(day, month, year) == 0){

					int deceasedate = Utils.dayCount(day, month, year);
					if(opdate  > deceasedate)
						message("1157", "" + r.getDayEntryRP() + "-" + r.getMonthEntryRP() + "-" + r.getYearEntryRP(), 
								"" + day               + "-" + month               + "-" + year);  


				}

				// Check that the OP date is not before arrival date of OP
				
				PersonDynamic pd1 = null;
				if(getNatureOfPerson() == ConstRelations2.FIRST_APPEARANCE_OF_OP){


					for(PersonDynamic pd : getDynamicDataOfPerson()){
						if(pd.getDynamicDataType() == ConstRelations2.AANKOMST){
							pd1 = pd;
							break;
						}
					}


					if(pd1 != null){

						year  = pd1.getYearOfMutation();
						month = pd1.getMonthOfMutation() > 0 ? pd1.getMonthOfMutation() : 1;
						day   = pd1.getDayOfMutation()   > 0 ? pd1.getDayOfMutation()   : 1;

						if(Common1.dateIsValid(day, month, year) == 0){
							int arrivaldate = Utils.dayCount(day, month, year); 
							if(opdate < arrivaldate)
								message("1158", "" + r.getDayEntryRP() + "-" + r.getMonthEntryRP() + "-" + r.getYearEntryRP(), 
										"" + day               + "-" + month               + "-" + year);  
						}	
					}
				}
				// Check that the OP date is not after departure date of OP

				pd1 = null;
				for(PersonDynamic pd : getDynamicDataOfPerson()){
					if(pd.getDynamicDataType() == ConstRelations2.VERTREK){
						pd1 = pd;
						break;
					}
				}

				if(pd1 != null){

					year  = pd1.getYearOfMutation();
					month = pd1.getMonthOfMutation() > 0 ? pd1.getMonthOfMutation() : 12;
					day   = pd1.getDayOfMutation()   > 0 ? pd1.getDayOfMutation()   : 28;

					if(Common1.dateIsValid(day, month, year) == 0){
						int departuredate = Utils.dayCount(day, month, year); 
						if(opdate > departuredate)
							message("1159", "" + r.getDayEntryRP() + "-" + r.getMonthEntryRP() + "-" + r.getYearEntryRP(), 
									"" + day               + "-" + month               + "-" + year);  
					}	


				}

				// (For KeyToRegistrationPersons - b2rnbg = 2) Check if marriage date later than OP date

				if(getKeyToRegistrationPersons() == 2){

					pd1 = null;
					for(PersonDynamic pd : getDynamicDataOfPerson()){
						if(pd.getDynamicDataType() == ConstRelations2.BURGELIJKE_STAAT && pd.getContentOfDynamicData() == ConstRelations2.GEHUWD){
							pd1 = pd;
							break;
						}
					}

					if(pd1 != null){

						year  = pd1.getYearOfMutation();
						month = pd1.getMonthOfMutation() > 0 ? pd1.getMonthOfMutation() : 12;
						day   = pd1.getDayOfMutation()   > 0 ? pd1.getDayOfMutation()   : 28;

						if(Common1.dateIsValid(day, month, year) == 0){
							int marriagedate = Utils.dayCount(day, month, year); 
							if(opdate < marriagedate)
								message("1166", "" + r.getDayEntryRP() + "-" + r.getMonthEntryRP() + "-" + r.getYearEntryRP(), 
										"" + day               + "-" + month               + "-" + year);  
						}	
					}
				}

				Ref_RP rp1 = Ref.getRP(getKeyToRP());

				if(rp1 != null){

					// Check that sex OP in B2 is same as in HSNRP

					if(rp1.getSexRP().equalsIgnoreCase(getSex()) != true)
						message("1185", getSex(), rp1.getSexRP());

					// Check that birth year OP in B2 is more or less same as in HSNRP


					if(getYearOfBirth() > rp1.getYearOfBirthRP() + 1 || getYearOfBirth() < rp1.getYearOfBirthRP() - 1 )
						message("1186", new Integer(getYearOfBirth()).toString(), new Integer(rp1.getYearOfBirthRP()).toString());

				}

				// Check if sex of OP is determined

				if(getSex() != null && getSex().toUpperCase().equals("M") != true  && getSex().toUpperCase().equals("V") != true)
					message("1187");			

				// Check if second appearance of OP. 

				if(getNatureOfPerson() == ConstRelations2.FURTHER_APPEARANCE_OF_OP){

					Person firstOP = null;
					for(Person p: getRegistrationPersonAppearsIn().getPersonsInRegistration()){

						if(p.getNatureOfPerson() == ConstRelations2.FIRST_APPEARANCE_OF_OP){
							firstOP = p;

							break;
						}
					}

					if(firstOP != null){

						// Check if the family names match

						if(!getFamilyName().equalsIgnoreCase(firstOP.getFamilyName()))
							message("1180");

						// Check if the first names match

						if(!getFirstName().equalsIgnoreCase(firstOP.getFirstName()))
							message("1181");

						// Check if the birth dates match

						if(getDayOfBirth() != firstOP.getDayOfBirth() || 
								getMonthOfBirth() != firstOP.getMonthOfBirth() || 
								getYearOfBirth() != firstOP.getYearOfBirth()) 
							message("1182");

						// Check if the birth place matches

						// XCV
						//System.out.println("XCV" + firstOP.getPlaceOfBirth() + "  " + getPlaceOfBirth());

						if(!getPlaceOfBirth().equalsIgnoreCase(firstOP.getPlaceOfBirth()))
							message("1183");

						// Check if the sex matches

						if(!getSex().equalsIgnoreCase(firstOP.getSex()))
							message("1184");	

					}
				}
			}
			return returnCode;
	}

	
	/**
	 * 
	 * This routine performs various checks on the Head of Household
	 * The following message numbers can be issued  
	 * 
	 * 1120
	 * 1121
	 * 1122
	 * 1123
	 * 1124
	 * 1125
	 * 1144
	 * 1145
	 * 1146
	 * 1147
	 * 
	 * 
	 */
	
	private void checkHead(Ref_AINB ainb){
		
		// Check if Head of Household date is valid
		
		int headDateValid = Common1.dateIsValid(getDayEntryHead(), getMonthEntryHead(),getYearEntryHead());
		
			
		// Next tests only if valid head date
		
		if(headDateValid == 0){
			
			// Check if headdate before range bevolkingsregister
			
			if(ainb != null){

				int startYear = ainb.getStartYearRegisterCorrected() != 0 ? ainb.getStartYearRegisterCorrected() : ainb.getStartYearRegister();  
				int endYear   = ainb.getEndYearRegisterCorrected()   != 0 ? ainb.getEndYearRegisterCorrected()   : ainb.getEndYearRegister();  

				if(startYear > 0){
					if(getYearEntryHead() <= startYear - 1){
						int difference = startYear - getYearEntryHead();
						message("1120", "" + getDayEntryHead() + "-" + getMonthEntryHead() + "-" + getYearEntryHead(), (new Integer(difference)).toString(),
								"" + startYear, "" + endYear); 

					}
				}

				// Check if headdate after range bevolkingsregister

				if(endYear > 0){
					if(getYearEntryHead() >= endYear + 1){
						int difference = getYearEntryHead() - endYear;
						message("1121", "" + getDayEntryHead() + "-" + getMonthEntryHead() + "-" + getYearEntryHead(), (new Integer(difference)).toString(),
								"" + startYear, "" + endYear); 

					}
				}

			}
			// Next tests only if valid birth date
			
			if(Common1.dateIsValid(getDayOfBirth(), getMonthOfBirth(),getYearOfBirth()) == 0){
				
				int x = Utils.dayCount(getDayOfBirth(), getMonthOfBirth(),getYearOfBirth());
				int y = Utils.dayCount(getDayEntryHead(), getMonthEntryHead(),getYearEntryHead());
				
							
				// Check if Headdate  before birth date head
				
				if(y < x)
					message("1124", "" + getDayEntryHead() + "-" + getMonthEntryHead() + "-" + getYearEntryHead(),
							        "" + getDayOfBirth() + "-" + getMonthOfBirth() + "-" + getYearOfBirth());

				// Check if Headdate  before 12th birthday head
				
				else
					if(y < x + (12 * 365) + 3)				
						message("1145", "" + getDayEntryHead() + "-" + getMonthEntryHead() + "-" + getYearEntryHead(), 
			        			"" + getDayOfBirth()   + "-" + getMonthOfBirth()   + "-" + getYearOfBirth());
				
				// Check if Headdate  before 18th birthday head
				// Only for B, G, C and D registers 
					else
						if(ainb.getTypeRegister().equalsIgnoreCase("B") || 
								ainb.getTypeRegister().equalsIgnoreCase("C") ||
								ainb.getTypeRegister().equalsIgnoreCase("D") ||
								ainb.getTypeRegister().equalsIgnoreCase("G"))
							if(y < x + (18 * 365) + 4)
								message("1144", "" + getDayEntryHead() + "-" + getMonthEntryHead() + "-" + getYearEntryHead(), 
							         			"" + getDayOfBirth()   + "-" + getMonthOfBirth()   + "-" + getYearOfBirth());

				
			}
			
			// Check if headdate after date of decease of head
			
			if(Common1.dateIsValid(getDayOfDecease(), getMonthOfDecease(),getYearOfDecease()) == 0){
				
				int x = Utils.dayCount(getDayOfDecease(), getMonthOfDecease(),getYearOfDecease());
				int y = Utils.dayCount(getDayEntryHead(), getMonthEntryHead(),getYearEntryHead());
				
				if(y > x)
					message("1125", "" + getDayEntryHead() + "-" + getMonthEntryHead() + "-" + getYearEntryHead(), 
							        "" + getDayOfDecease() + "-" + getMonthOfDecease() + "-" + getYearOfDecease());
				
				
			}
			
			
			// Check if registration date of head earlier than arrival date head or later than departure date of head
			
			int registerDate = 0;
			int registerDay = getDayOfRegistration();
			int registerMonth = getMonthOfRegistration();
			int registerYear = getYearOfRegistration();
			
			if(Common1.dateIsValid(registerDay, registerMonth, registerYear) == 0)
				registerDate = Utils.dayCount(registerDay, registerMonth, registerYear);			
			
			
			for(PersonDynamic pd: getDynamicDataOfPerson()){
				
				switch(pd.getDynamicDataType()){

				case ConstRelations2.AANKOMST:

					if(Common1.dateIsValid(pd.getDayOfMutation(), pd.getMonthOfMutation(),pd.getYearOfMutation()) == 0){
						int arrivalDate = Utils.dayCount(pd.getDayOfMutation(), pd.getMonthOfMutation(),pd.getYearOfMutation());

						if(registerDate != 0){

							if(arrivalDate > registerDate)
								message("1146", "" + registerDay           + "-" + registerMonth           + "-" + registerYear,
									            "" + pd.getDayOfMutation() + "-" + pd.getMonthOfMutation() + "-" + pd.getYearOfMutation());  

						}

					}
					break;
					
				case ConstRelations2.VERTREK:

					if(Common1.dateIsValid(pd.getDayOfMutation(), pd.getMonthOfMutation(),pd.getYearOfMutation()) == 0){
						int departureDate = Utils.dayCount(pd.getDayOfMutation(), pd.getMonthOfMutation(),pd.getYearOfMutation());

						if(registerDate != 0){

							if(departureDate < registerDate)
								message("1147", "" + registerDay           + "-" + registerMonth           + "-" + registerYear,
									            "" + pd.getDayOfMutation() + "-" + pd.getMonthOfMutation() + "-" + pd.getYearOfMutation());  

						}

					}
					break;
					
					default:
						break;
					
				}
			}
			
			// Check if Headdate (more than 10 days) before registration date of head
			
			int year  = getYearOfRegistration();
			int month = getMonthOfRegistration() > 0 ? getMonthOfRegistration() : 1;
			int day   = getDayOfRegistration()   > 0 ? getDayOfRegistration()   : 1;
			
			
			if(Common1.dateIsValid(day, month, year) == 0){

				int x = Utils.dayCount(day, month, year);
				int y = Utils.dayCount(getDayEntryHead(), getMonthEntryHead(),getYearEntryHead());


				// Check if headdate more than 10 days before date of Registration of head

				if(y + 10 < x)
					message("1122", "" + getDayEntryHead() + "-" +  getMonthEntryHead() + "-" + getYearEntryHead(), 
							        "" + day               + "-" +  month               + "-" + year);

				// Check if headdate before date of Registration of head

				else{
					if(y < x){
						message("1123", "" + getDayEntryHead() + "-" +  getMonthEntryHead() + "-" + getYearEntryHead(), 
										"" + day               + "-" +  month               + "-" + year);
					}
				}
			}
		
			
		} // If valid headdate


			
		
		
		
	}
	
	/**
	 * 
	 * This routine performs various checks on a second explicit head (the first/normal head does not have isExplictHead = true)
	 * The following messages can be issued:
	 * 
	 * 1331
	 * 1333
	 * 1334
	 * 1335
	 * 1336
	 * 1337
	 * 1338
	 * 1339
	 * 1340
	 * 1341
	 * 1342
	 * 
	 */
	
	private void checkExplicitHead(Ref_AINB ainb){
		
		int headDay = 0;
		int headMonth = 0;
		int headYear = 0;
		
		for(PersonDynamic pd: getDynamicDataOfPerson()){
			if(pd.getDynamicDataType() == ConstRelations2.RELATIE_TOT_HOOFD){
				if(pd.getDynamicData2().indexOf("###$") >= 0){
					if(pd.getDynamicData2().length() >= "###$dd/mm/jjjj".length()){

						headDay = new Integer(pd.getDynamicData2().substring(4,6)).intValue();
						headMonth = new Integer(pd.getDynamicData2().substring(7,9)).intValue();
						headYear =  new Integer(pd.getDynamicData2().substring(10,14)).intValue();


					}
				}
			}	
		}	
	
		
		if(Common1.dateIsValid(headDay, headMonth, headYear) == 0){

			if(ainb != null){
				// Check if headdate before range bevolkingsregister 
				
				int startYear = ainb.getStartYearRegisterCorrected() != 0 ? ainb.getStartYearRegisterCorrected() : ainb.getStartYearRegister();  
				int endYear   = ainb.getEndYearRegisterCorrected()   != 0 ? ainb.getEndYearRegisterCorrected()   : ainb.getEndYearRegister();  

				if(startYear > 0){
					if(headYear <= startYear - 1){
						message("1333", "" + headYear, "" + startYear, "" + endYear); 

					}
				}

				// Check if headdate after range bevolkingsregister

				if(endYear > 0){
					if(headYear >= endYear + 1){
						message("1334", "" + headYear, "" + startYear, "" + endYear); 

					}
				}
			}
			
			// Check that date second explicit head is later than original head date
			
			if(getYearEntryHead() >  headYear || (getYearEntryHead()  == headYear  && getMonthEntryHead() > headMonth) ||
			  (getYearEntryHead() == headYear &&  getMonthEntryHead() == headMonth && getDayEntryHead()   > headDay)) 
				message("1335", "" + headDay           + "-" + headMonth           + "-" + headYear, 
						        "" + getDayEntryHead() + "-" + getMonthEntryHead() + "-" + getYearEntryHead());
			
			// Check that date second explicit head is not equal to original head date
			
			if(getYearEntryHead() ==  headYear && getMonthEntryHead() == headMonth && getDayEntryHead() == headDay) 
				message("1336", "" + headDay           + "-" + headMonth           + "-" + headYear, 
				                "" + getDayEntryHead() + "-" + getMonthEntryHead() + "-" + getYearEntryHead());
			
			
			// Check that date of second explicit head not after decease date of second explicit head
			
			if( Common1.dateIsValid(getDayOfDecease(), getMonthOfDecease(), getYearOfDecease()) == 0){
				
				if(getYearOfDecease() <  headYear || (getYearOfDecease()  == headYear  && getMonthOfDecease() < headMonth) ||
				  (getYearOfDecease() == headYear &&  getMonthOfDecease() == headMonth && getDayOfDecease()   < headDay)) 
							message("1337", "" + headDay + "-" + headMonth + "-" + headYear);
				
			}
			
			// Check that date of second explicit head not before birth date of second explicit head
			
			if( Common1.dateIsValid(getDayOfBirth(), getMonthOfBirth(), getYearOfBirth()) == 0){
				
				if(getYearOfBirth() >  headYear || (getYearOfBirth()  == headYear  && getMonthOfBirth() > headMonth) ||
				  (getYearOfBirth() == headYear &&  getMonthOfBirth() == headMonth && getDayOfBirth()   > headDay)) 
							message("1338", "" + headDay + "-" + headMonth + "-" + headYear);
				
			}
				
			
		}
		// No (valid) date for second head
		else 
			message("1331");
		

		// Compare date second explicit head with arrival/departure dates (only first 2)
		
		int arrivals = 0;
		int departures = 0;
		for(PersonDynamic pd: getDynamicDataOfPerson()){
			
			if(pd.getDynamicDataType() == ConstRelations2.AANKOMST){
				if(Common1.dateIsValid(pd.getDayOfMutation(), pd.getMonthOfMutation(),pd.getYearOfMutation()) == 0){
				    if(arrivals < 2){
				    	
	                    // Check that date second explicit head not before first/second arrival
				    	
						if(pd.getYearOfMutation() >  headYear || (pd.getYearOfMutation()  == headYear  && pd.getMonthOfMutation() > headMonth) ||
						  (pd.getYearOfMutation() == headYear &&  pd.getMonthOfMutation() == headMonth && pd.getDayOfMutation()   > headDay))
							if(arrivals++ == 0)
								message("1339", "" + headDay +               "-" + headMonth +               "-" + headYear, 
										        "" + pd.getDayOfMutation() + "-" + pd.getMonthOfMutation() + "-" + pd.getYearOfMutation());
							else
								message("1340", "" + headDay +               "-" + headMonth +               "-" + headYear, 
						                        "" + pd.getDayOfMutation() + "-" + pd.getMonthOfMutation() + "-" + pd.getYearOfMutation());
				    }
				}   
			}
			
			if(pd.getDynamicDataType() == ConstRelations2.VERTREK){
				if(Common1.dateIsValid(pd.getDayOfMutation(), pd.getMonthOfMutation(),pd.getYearOfMutation()) == 0){
				    if(departures < 2){
				    	
	                    // Check that date second explicit head not after first/second departure

						if(pd.getYearOfMutation() <  headYear || (pd.getYearOfMutation()  == headYear  && pd.getMonthOfMutation() < headMonth) ||
						  (pd.getYearOfMutation() == headYear &&  pd.getMonthOfMutation() == headMonth && pd.getDayOfMutation()   < headDay))
							if(departures++ == 0)
								message("1341", "" + headDay +               "-" + headMonth +               "-" + headYear, 
										        "" + pd.getDayOfMutation() + "-" + pd.getMonthOfMutation() + "-" + pd.getYearOfMutation());
							else
								message("1342", "" + headDay +               "-" + headMonth +               "-" + headYear, 
					                	        "" + pd.getDayOfMutation() + "-" + pd.getMonthOfMutation() + "-" + pd.getYearOfMutation());
				    }
				}
			}
		}
		
		
	}
	

	/**
	 * 
	 * If a valid (>0) correction day/month/year is given, it is copied to the original date
	 * 
	 */

    private void adaptRegistrationDate(){
    
    	setDayOfRegistration(Utils.convertDateElement(getDayOfRegistration(), getDayOfRegistrationAfterInterpretation()));
    	setMonthOfRegistration(Utils.convertDateElement(getMonthOfRegistration(), getMonthOfRegistrationAfterInterpretation()));
    	setYearOfRegistration(Utils.convertDateElement(getYearOfRegistration(), getYearOfRegistrationAfterInterpretation()));
    }

	/**
	*
	*  If a valid (>0) correction day/month/year is given, it is copied to the original date
	* 
	*/  

	private void adaptDateOfBirth(){

    	setDayOfBirth(Utils.convertDateElement(getDayOfBirth(), getDayOfBirthAfterInterpretation()));
    	setMonthOfBirth(Utils.convertDateElement(getMonthOfBirth(), getMonthOfBirthAfterInterpretation()));
    	setYearOfBirth(Utils.convertDateElement(getYearOfBirth(), getYearOfBirthAfterInterpretation()));
	}

	/**
	*
	*  If a valid (>0) correction day/month/year is given, it is copied to the original date
	* 
	*/  

	void adaptDateOfDecease(){

    	setDayOfDecease(Utils.convertDateElement(getDayOfDecease(), getDayOfDeceaseAfterInterpretation()));
    	setMonthOfDecease(Utils.convertDateElement(getMonthOfDecease(), getMonthOfDeceaseAfterInterpretation()));
    	setYearOfDecease(Utils.convertDateElement(getYearOfDecease(), getYearOfDeceaseAfterInterpretation()));
	}

	/**
	 *
	 * Family name is only used up to a '$', "!", or "%"
	 * 
	 */
	
	private void adaptFamilyName(){
		
		String name = getFamilyName();
		
		int i = name.indexOf("$");  
		if(i >= 0) name = name.substring(0, i-1);
		
		i = name.indexOf("!");  
		if(i >= 0) name = name.substring(0, i-1);
		
		i = name.indexOf("%");  
		if(i >= 0) name = name.substring(0, i-1);
		
		setFamilyName(name); 
		
	}
	
	/**
	* 
	* First name is standardized in a form without comma's and only 1 space between the names
	* 
	*/
	
	private void adaptFirstName(){
		
		String name = getFirstName();

		String [] names = name.split("[ ,]"); // split on space or comma, this gets rid of the comma's
		String name2 = "";
		
		for(int l = 0; l < names.length; l++){
			name2 = name2 + names[l] + " ";
		}
		if(name2 != null)
		    setFirstName(name2.trim()); 
	}

	public void add(Registration ra){
		setRegistrationPersonAppearsIn(ra);
	}


	public void add(PersonDynamic pd){
		getDynamicDataOfPerson().add(pd);
	}

	
	/**
	 * 
	 * This routine prints out the essential fields of this object
	 * 
	 * 
	 */
	
	public void print(){
		
		 showFields();
		 for(PersonDynamic pd : getDynamicDataOfPerson()){
				pd.print();
			}
		
	}
	
	public void showFields(){
		
		//System.out.println("        Check Person              " +
		//	" IDNR = " + getkeyToRP() +
		//	" B1IDBG = " + getKeyToSourceRegister() + 
		//	" B2DIBG* = " + getDayEntryHead() + " " + getMonthEntryHead() + " " + getYearEntryHead() +
		//	" B2RNBG = " + getKeyToRegistrationPersons());

		String Header = "";
		String Trailer = "";
		
		if(getNatureOfPerson() == ConstRelations2.FIRST_APPEARANCE_OF_OP)
			Trailer = "  <== OP";
		else
			if(getNatureOfPerson() == ConstRelations2.FURTHER_APPEARANCE_OF_OP)
				Trailer = "  <==  OP verder";
		
		String birth   = String.format("%02d-%02d-%04d", getDayOfBirth   (), getMonthOfBirth    (), getYearOfBirth  ());
		String decease = String.format("%02d-%02d-%04d", getDayOfDecease (), getMonthOfDecease  (), getYearOfDecease  ());

		
		//String birthPlace   = (getPlaceOfBirth()   == null || getPlaceOfBirth().trim().length() == 0) ? "?? " : getPlaceOfBirth().trim() + " " ;
		//String deceasePlace = (getPlaceOfDecease() == null || getPlaceOfBirth().trim().length() == 0) ? "?? " : getPlaceOfDecease().trim() + " " ;
		
		String birthPlace = getPlaceOfBirth();
		if(birthPlace != null){
			birthPlace = birthPlace.trim();
		    if(birthPlace.length() == 0)
		    	birthPlace = "?? ";
		}
		else     
			birthPlace = "?? ";
		
		String deceasePlace = getPlaceOfDecease();
		if(deceasePlace != null){
			deceasePlace = deceasePlace.trim();
		    if(deceasePlace.length() == 0)
		    	deceasePlace = "?? ";
		}
		else     
			deceasePlace = "?? ";
		
		
		
		String life = "(" + birthPlace + " " +  birth + " - " + deceasePlace + decease + ")"; 

		String EntryDateHead = String.format("%02d-%02d-%04d", getDayEntryHead(), getMonthEntryHead(), getYearEntryHead());
		String RegistrationDate = String.format("%02d-%02d-%04d", getDayOfRegistration(), getMonthOfRegistration(), getYearOfRegistration());
		
		//String personNumber = String.format("%04d", getStandardizedPerson().getPersonID());
				
		//System.out.println();
		System.out.println(Header +
			"" + getKeyToRP() +
			"  " + EntryDateHead +
			"  " + getKeyToSourceRegister() +			
			"  " + getKeyToRegistrationPersons() +
		//	"  " + personNumber +
			"  " + RegistrationDate +
		    "  " + getFamilyName().trim() + 
		    "  " + getFirstName().trim() +
		    "  " + life +
		    "  " + Trailer);

	
	}

	/**
	 * 
	 * 
	 * This method converts the Person Object to the new format
	 * It also calls the convert method 
	 * for the PersonDynamic objects belonging to this Person object
	 * 
	 * 
	 */
	public void Convert(){
		
	    //showFields();
		
		standardizedPerson = new PersonStandardized();
		setStandardizedPerson(standardizedPerson); 
		standardizedPerson.setOriginalPerson(this);
		
		// Next instructions link PersonStandardized <-> RegistrationStandardized
		
		getRegistrationPersonAppearsIn().getStandardizedRegistration().getPersonsStandardizedInRegistration().add(standardizedPerson);
		standardizedPerson.setRegistrationStandardizedPersonAppearsIn(getRegistrationPersonAppearsIn().getStandardizedRegistration());
		
		
		
		standardizedPerson.transform(this);
		
		for(PersonDynamic pd: getDynamicDataOfPerson()){
			pd.Convert();
		}

		PersonDynamicStandardized.renumber(standardizedPerson.getDynamicDataOfPersonStandardized());
	

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
		
		m.setDayEntryRP(getRegistrationPersonAppearsIn().getDayEntryRP());
		m.setMonthEntryRP(getRegistrationPersonAppearsIn().getMonthEntryRP());
		m.setYearEntryRP(getRegistrationPersonAppearsIn().getYearEntryRP());
		
		

		
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


	public int getNatureOfPerson() {
		return natureOfPerson;
	}


	public void setNatureOfPerson(int natureOfPerson) {
		this.natureOfPerson = natureOfPerson;
	}


	public int getDayOfRegistration() {
		return dayOfRegistration;
	}


	public void setDayOfRegistration(int dayOfRegistration) {
		this.dayOfRegistration = dayOfRegistration;
	}


	public int getMonthOfRegistration() {
		return monthOfRegistration;
	}


	public void setMonthOfRegistration(int monthOfRegistration) {
		this.monthOfRegistration = monthOfRegistration;
	}


	public int getYearOfRegistration() {
		return yearOfRegistration;
	}


	public void setYearOfRegistration(int yearOfRegistration) {
		this.yearOfRegistration = yearOfRegistration;
	}


	public int getDayOfRegistrationAfterInterpretation() {
		return dayOfRegistrationAfterInterpretation;
	}


	public void setDayOfRegistrationAfterInterpretation(
			int dayOfRegistrationAfterInterpretation) {
		this.dayOfRegistrationAfterInterpretation = dayOfRegistrationAfterInterpretation;
	}


	public int getMonthOfRegistrationAfterInterpretation() {
		return monthOfRegistrationAfterInterpretation;
	}


	public void setMonthOfRegistrationAfterInterpretation(
			int monthOfRegistrationAfterInterpretation) {
		this.monthOfRegistrationAfterInterpretation = monthOfRegistrationAfterInterpretation;
	}


	public int getYearOfRegistrationAfterInterpretation() {
		return yearOfRegistrationAfterInterpretation;
	}


	public void setYearOfRegistrationAfterInterpretation(
			int yearOfRegistrationAfterInterpretation) {
		this.yearOfRegistrationAfterInterpretation = yearOfRegistrationAfterInterpretation;
	}


	public String getFamilyName() {
		return familyName;
	}


	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public int getDayOfBirth() {
		return dayOfBirth;
	}


	public void setDayOfBirth(int dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}


	public int getMonthOfBirth() {
		return monthOfBirth;
	}


	public void setMonthOfBirth(int monthOfBirth) {
		this.monthOfBirth = monthOfBirth;
	}


	public int getYearOfBirth() {
		return yearOfBirth;
	}


	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}


	public int getDayOfBirthAfterInterpretation() {
		return dayOfBirthAfterInterpretation;
	}


	public void setDayOfBirthAfterInterpretation(int dayOfBirthAfterInterpretation) {
		this.dayOfBirthAfterInterpretation = dayOfBirthAfterInterpretation;
	}


	public int getMonthOfBirthAfterInterpretation() {
		return monthOfBirthAfterInterpretation;
	}


	public void setMonthOfBirthAfterInterpretation(
			int monthOfBirthAfterInterpretation) {
		this.monthOfBirthAfterInterpretation = monthOfBirthAfterInterpretation;
	}


	public int getYearOfBirthAfterInterpretation() {
		return yearOfBirthAfterInterpretation;
	}


	public void setYearOfBirthAfterInterpretation(int yearOfBirthAfterInterpretation) {
		this.yearOfBirthAfterInterpretation = yearOfBirthAfterInterpretation;
	}


	public String getPlaceOfBirth() {
		return placeOfBirth;
	}


	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}


	public int getDayOfDecease() {
		return dayOfDecease;
	}


	public void setDayOfDecease(int dayOfDecease) {
		this.dayOfDecease = dayOfDecease;
	}


	public int getMonthOfDecease() {
		return monthOfDecease;
	}


	public void setMonthOfDecease(int monthOfDecease) {
		this.monthOfDecease = monthOfDecease;
	}


	public int getYearOfDecease() {
		return yearOfDecease;
	}


	public void setYearOfDecease(int yearOfDecease) {
		this.yearOfDecease = yearOfDecease;
	}


	public int getDayOfDeceaseAfterInterpretation() {
		return dayOfDeceaseAfterInterpretation;
	}


	public void setDayOfDeceaseAfterInterpretation(
			int dayOfDeceaseAfterInterpretation) {
		this.dayOfDeceaseAfterInterpretation = dayOfDeceaseAfterInterpretation;
	}


	public int getMonthOfDeceaseAfterInterpretation() {
		return monthOfDeceaseAfterInterpretation;
	}


	public void setMonthOfDeceaseAfterInterpretation(
			int monthOfDeceaseAfterInterpretation) {
		this.monthOfDeceaseAfterInterpretation = monthOfDeceaseAfterInterpretation;
	}


	public int getYearOfDeceaseAfterInterpretation() {
		return yearOfDeceaseAfterInterpretation;
	}


	public void setYearOfDeceaseAfterInterpretation(
			int yearOfDeceaseAfterInterpretation) {
		this.yearOfDeceaseAfterInterpretation = yearOfDeceaseAfterInterpretation;
	}


	public String getPlaceOfDecease() {
		return placeOfDecease;
	}


	public void setPlaceOfDecease(String placeOfDecease) {
		this.placeOfDecease = placeOfDecease;
	}


	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	public String getLegalPlaceOfLiving() {
		return legalPlaceOfLiving;
	}


	public void setLegalPlaceOfLiving(String legalPlaceOfLiving) {
		this.legalPlaceOfLiving = legalPlaceOfLiving;
	}


	public String getLegalPlaceOfLivingInCodes() {
		return legalPlaceOfLivingInCodes;
	}


	public void setLegalPlaceOfLivingInCodes(String legalPlaceOfLivingInCodes) {
		this.legalPlaceOfLivingInCodes = legalPlaceOfLivingInCodes;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getRemarks2() {
		return remarks2;
	}


	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
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


	public ArrayList<PersonDynamic> getDynamicDataOfPerson() {
		return dynamicDataOfPerson;
	}


	public void setDynamicDataOfPerson(ArrayList<PersonDynamic> dynamicDataOfPerson) {
		this.dynamicDataOfPerson = dynamicDataOfPerson;
	}


	public Registration getRegistrationPersonAppearsIn() {
		return registrationPersonAppearsIn;
	}


	public void setRegistrationPersonAppearsIn(
			Registration registrationPersonAppearsIn) {
		this.registrationPersonAppearsIn = registrationPersonAppearsIn;
	}

	public Boolean getIsHead() {
		return isHead;
	}

	public void setIsHead(Boolean isHead) {
		this.isHead = isHead;
	}


	public Boolean getIsHeadFirstSuccessor() {
		return isHeadFirstSuccessor;
	}


	public void setIsHeadFirstSuccessor(Boolean isExplicitHead) {
		this.isHeadFirstSuccessor = isExplicitHead;
	}


	public PersonStandardized getStandardizedPerson() {
		return standardizedPerson;
	}


	public void setStandardizedPerson(PersonStandardized standardizedPerson) {
		this.standardizedPerson = standardizedPerson;
	}



}	
