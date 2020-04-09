/*
 * Naam:    Registration
 * Version: 0.1
 *  Author: Cor Munnik
 * Copyright
 */
package nl.iisg.ids03;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import nl.iisg.hsncommon.Common1;
import nl.iisg.hsncommon.ConstRelations2;
import nl.iisg.ref.*;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * 
 * This class handles the static attributes of a registration 
 *
 */
@Entity
@Table(name="b4")
public class Registration implements Comparable<Registration>{

	@Id	@Column(name = "B1IDBG")  	private int   	 keyToSourceRegister;      
	@Id	@Column(name = "B2DIBG")    private int      dayEntryHead;
	@Id	@Column(name = "B2MIBG")    private int      monthEntryHead; 
	@Id	@Column(name = "B2JIBG")    private int      yearEntryHead; 
	@Id	@Column(name = "IDNR")      private int      keyToRP;   

	@Column(name = "B2FDBG")    private int      dayEntryRP;
	@Column(name = "B2FMBG")    private int      monthEntryRP;
	@Column(name = "B2FJBG")    private int      yearEntryRP;

	@Column(name = "B2PGBG")    private String   pageNumberOfSource; 
	@Column(name = "B2VHBG")    private int      numberOfHousehold; 
	@Column(name = "B4GKBG")    private String   infoFamilyCardsSystem; 
	@Column(name = "B4SPBG")    private String   specialDataEntryCodes; 
	@Column(name = "B4AAN")     private String   remarks;  

	@Column(name = "OPDRNR")    private String   orderNumber;
	@Column(name = "DATUM")     private String   date0;
	@Column(name = "INIT")      private String   initials;
	@Column(name = "VERSIE")    private String   versionLastTimeOfDataEntry;
	@Column(name = "ONDRZKO")   private String   researchCodeOriginal;
	@Column(name = "OPDRNRO")   private String   orderNumberOriginal;
	@Column(name = "DATUMO")    private String   dateOriginal;
	@Column(name = "INITO")     private String   initialOriginal;
	@Column(name = "VERSIEO")   private String   versionOriginalDataEntry;

	@Transient  				private ArrayList<Person>                personsInRegistration   = new ArrayList<Person>();
	@Transient  				private ArrayList<RegistrationAddress>   addressesOfRegistration = new ArrayList<RegistrationAddress>();
	@Transient  				private OP                               op = null;
	@Transient  				private RegistrationStandardized         standardizedRegistration = null;

	 
	static boolean trace = false;
	
	Registration(){} // no-args constructor
	
	/**
	 * 
	 * @param rowObjects
	 * 
	 * Constructor, initializes object from rowObjects
	 * 
	 */


	Registration(Object [] rowObjects, String [] fieldnames, byte[] fieldtypes){
		
		Utils.trimAll(rowObjects);
		
		setKeyToSourceRegister(                              (Integer)Utils.getColumn("B1IDBG", rowObjects, fieldnames, fieldtypes));
		setDayEntryHead(                                     (Integer)Utils.getColumn("B2DIBG", rowObjects, fieldnames, fieldtypes));
		setMonthEntryHead(                                   (Integer)Utils.getColumn("B2MIBG", rowObjects, fieldnames, fieldtypes));
		setYearEntryHead(                                    (Integer)Utils.getColumn("B2JIBG", rowObjects, fieldnames, fieldtypes));
		setKeyToRP(                                          (Integer)Utils.getColumn("IDNR", rowObjects, fieldnames, fieldtypes));

		setDayEntryRP(									     (Integer)Utils.getColumn("B2FDBG", rowObjects, fieldnames, fieldtypes));
		setMonthEntryRP(                                     (Integer)Utils.getColumn("B2FMBG", rowObjects, fieldnames, fieldtypes));                          
		setYearEntryRP(                                      (Integer)Utils.getColumn("B2FJBG", rowObjects, fieldnames, fieldtypes));

		setPageNumberOfSource(                               (String)Utils.getColumn("B2PGBG", rowObjects, fieldnames, fieldtypes));
		setNumberOfHousehold(                                (Integer)Utils.getColumn("B2VHBG", rowObjects, fieldnames, fieldtypes));
		setInfoFamilyCardsSystem(                            (String)Utils.getColumn("B4GKBG", rowObjects, fieldnames, fieldtypes));
		setSpecialDataEntryCodes(                            (String)Utils.getColumn("B4SPBG", rowObjects, fieldnames, fieldtypes));
		setRemarks(                                          (String)Utils.getColumn("B4AAN", rowObjects, fieldnames, fieldtypes));  

		setOrderNumber(                                 	 (String)Utils.getColumn("OPDRNR", rowObjects, fieldnames, fieldtypes));
		setDate0(                                            (String)Utils.getColumn("DATUM", rowObjects, fieldnames, fieldtypes));	
		setInitials(                                         (String)Utils.getColumn("INIT", rowObjects, fieldnames, fieldtypes));
		setVersionLastTimeOfDataEntry(                       (String)Utils.getColumn("VERSIE", rowObjects, fieldnames, fieldtypes));
		setResearchCodeOriginal(                             (String)Utils.getColumn("ONDRZKO", rowObjects, fieldnames, fieldtypes));
		setOrderNumberOriginal(                              (String)Utils.getColumn("OPDRNRO", rowObjects, fieldnames, fieldtypes));
		setDateOriginal(                                     (String)Utils.getColumn("DATUMO", rowObjects, fieldnames, fieldtypes));
		setInitialOriginal(                                  (String)Utils.getColumn("INITO", rowObjects, fieldnames, fieldtypes));
		setVersionOriginalDataEntry(                         (String)Utils.getColumn("VERSIEO", rowObjects, fieldnames, fieldtypes));

	}

	Registration(Object [] rowObjects){
		
		Utils.trimAll(rowObjects);
		
		setKeyToSourceRegister(                                       Utils.toInt(rowObjects[1]));
		setDayEntryHead(                                              Utils.toInt(rowObjects[2]));
		setMonthEntryHead(                                            Utils.toInt(rowObjects[3]));
		setYearEntryHead(                                             Utils.toInt(rowObjects[4]));
		setKeyToRP(                                                   Utils.toInt(rowObjects[5])); 

		setDayEntryRP(									              Utils.toInt(rowObjects[6]));	
		setMonthEntryRP(                                              Utils.toInt(rowObjects[7]));                           
		setYearEntryRP(                                               Utils.toInt(rowObjects[8]));

		setPageNumberOfSource(                                       (String)rowObjects[9]);
		setNumberOfHousehold(                                         Utils.toInt(rowObjects[10]));
		setInfoFamilyCardsSystem(                                    (String)rowObjects[11]);  
		setSpecialDataEntryCodes(                                    (String)rowObjects[12]);
		setRemarks(                                                  (String)rowObjects[13]);    

		setOrderNumber(                                              (String)rowObjects[14]);
		setDate0(                                                      Utils.toStr(rowObjects[15]));
		setInitials(                                                 (String)rowObjects[16]);
		setVersionLastTimeOfDataEntry(                               (String)rowObjects[17]);
		setResearchCodeOriginal(                                     (String)rowObjects[18]);
		setOrderNumberOriginal(                                      (String)rowObjects[19]);
		setDateOriginal(                                             Utils.toStr(rowObjects[20]));
		setInitialOriginal(                                          (String)rowObjects[21]);
		setVersionOriginalDataEntry(                                 (String)rowObjects[22]);


	}

	public boolean contains(Person p){

		if(getKeyToSourceRegister()        == p.getKeyToSourceRegister() &&    
				getDayEntryHead()    == p.getDayEntryHead() &&		
				getMonthEntryHead()  == p.getMonthEntryHead() &&		
				getYearEntryHead()   == p.getYearEntryHead() &&
				getKeyToRP()     == p.getKeyToRP())

			return true;
		else
			return false;


	}
	
	public boolean higher(Person p){
		
		//System.out.println("In higher");
		
		//System.out.println("   Registration " + getKeyToRP() + " " + getKeyToSourceRegister() + "  " + getDayEntryHead() 
		//+ "-" + getMonthEntryHead() + "-" + getYearEntryHead());
		
		//System.out.println("      Person    " + p.getKeyToRP() + " " + p.getKeyToSourceRegister() + "  " + p.getDayEntryHead() 
		//+ "-" + p.getMonthEntryHead() + "-" + p.getYearEntryHead() + "  "  + p.getKeyToRegistrationPersons());


		if(getKeyToRP()              > p.getKeyToRP())                  return true;
		if(getKeyToRP()              < p.getKeyToRP())                  return false;
		if(getYearEntryHead()        > p.getYearEntryHead())            return true;
		if(getYearEntryHead()        < p.getYearEntryHead())            return false;
		if(getMonthEntryHead()       > p.getMonthEntryHead())           return true;
		if(getMonthEntryHead()       < p.getMonthEntryHead())           return false;
		if(getDayEntryHead()         > p.getDayEntryHead())             return true;
		if(getDayEntryHead()         < p.getDayEntryHead())             return false;
		if(getKeyToSourceRegister()  > p.getKeyToSourceRegister())      return true;
		if(getKeyToSourceRegister()  < p.getKeyToSourceRegister())      return false;
		
		return false;
		
	}

	public boolean contains(PersonDynamic pd){

		if(getKeyToSourceRegister()        == pd.getKeyToSourceRegister() &&    
				getDayEntryHead()    == pd.getDayEntryHead() &&		
				getMonthEntryHead()  == pd.getMonthEntryHead() &&		
				getYearEntryHead()   == pd.getYearEntryHead() &&
				getKeyToRP()     == pd.getKeyToRP())

			return true;
		else
			return false;


	}

	public boolean contains(RegistrationAddress ra){

		if(getKeyToSourceRegister()        == ra.getKeyToSourceRegister() &&    
				getDayEntryHead()    == ra.getDayEntryHead() &&		
				getMonthEntryHead()  == ra.getMonthEntryHead() &&		
				getYearEntryHead()   == ra.getYearEntryHead() &&
				getKeyToRP()     == ra.getKeyToRP())

			return true;
		else
			return false;


	}
	
	public boolean higher(RegistrationAddress ra){

		if(getKeyToRP()              > ra.getKeyToRP())                  return true;
		if(getKeyToRP()              < ra.getKeyToRP())                  return false;
		if(getYearEntryHead()        > ra.getYearEntryHead())            return true;
		if(getYearEntryHead()        < ra.getYearEntryHead())            return false;
		if(getMonthEntryHead()       > ra.getMonthEntryHead())           return true;
		if(getMonthEntryHead()       < ra.getMonthEntryHead())           return false;
		if(getDayEntryHead()         > ra.getDayEntryHead())             return true;
		if(getDayEntryHead()         < ra.getDayEntryHead())             return false;
		if(getKeyToSourceRegister()  > ra.getKeyToSourceRegister())      return true;
		if(getKeyToSourceRegister()  < ra.getKeyToSourceRegister())      return false;
		
		return false;
		
	}



	/**
	 * 
	 * For use in HashSet 
	 * 
	 */
	
	@Override public boolean equals(Object o){
		
		if(o instanceof Registration) {
			Registration r = (Registration) o;
			if(r.getPageNumberOfSource() == getPageNumberOfSource() && r.getNumberOfHousehold() == getNumberOfHousehold())
				return true;
			else
				return false; 
		}
		else
			return false;
	}		    
	
	/**
	 * 
	 * For use in HashSet 
	 * 
	 */
	
	@Override public int hashCode(){
		
        return getPageNumberOfSource().hashCode() + getPageNumberOfSource().hashCode(); 
    } 
	
	/**
	 * 
	 * @param 
	 * 
	 * This method performs checks on the data of a registration
	 * The following message numbers can be issued:
	 * 
	 */

	public boolean check(){
		
		boolean returnCode = true;
		//System.out.println("Check Registration"); 

		//showFields();
		
        // Find ainb row
		
		Ref_AINB ainb = Ref.getAINB(getKeyToSourceRegister());		
		checkRegistration(ainb);

		//
		// Check Persons in registration
		//


		for(Person p: personsInRegistration){
			boolean rc = p.check();
			if(rc == false) 
				returnCode = false;
		}

		//
		// Check Consistency Persons
		//

		checkConsistencyPersons(ainb);


		
		// Next the registration addresses
		
		//System.out.println(); // for better looking output
		
		for(RegistrationAddress ra: addressesOfRegistration){
			ra.check();
		}
		
		checkConsistencyAddresses();
		
		//System.out.println("    Registration.check, rc =  " + returnCode);
		
		return returnCode;

	}
	
	
	
	
	/**
	 * 
	 * 
	 * This routine performs checks on the registration
	 * The following messages can be issued
	 * 
	 * 1112
	 * 1113
	 * 1114
	 * 1115
	 * 1118
	 * 
	 */
	
	private void checkRegistration(Ref_AINB ainb){
		
		if(ainb != null){

			// Check that page and line number of register ARE filled in for register type A, B, C, D

			if(ainb.getTypeRegister().toUpperCase().equals("A") == true ||
					ainb.getTypeRegister().toUpperCase().equals("B") == true ||
					ainb.getTypeRegister().toUpperCase().equals("C") == true ||
					ainb.getTypeRegister().toUpperCase().equals("D") == true)
				if(getPageNumberOfSource().trim().length() == 0 || getNumberOfHousehold() == 0)
					message("1112");

			// Check that page number and line number NOT filled in for register type G or I

			if(ainb.getTypeRegister().toUpperCase().equals("G") == true ||
					ainb.getTypeRegister().toUpperCase().equals("I") == true)
				if(getPageNumberOfSource().trim().length() != 0 || getNumberOfHousehold() != 0)
					message("1113");

			// Check if infoFamilyCardsSystem - B4GKBG IS filled in for register type  G or I

			if(ainb.getTypeRegister().toUpperCase().equals("G") == true ||
					ainb.getTypeRegister().toUpperCase().equals("I") == true)
				if(getInfoFamilyCardsSystem().trim().length() == 0)
					message("1114");

			// Check if infoFamilyCardsSystem - B4GKBG is NOT filled in for register type A, B, C, D

			if(ainb.getTypeRegister().toUpperCase().equals("A") == true ||
					ainb.getTypeRegister().toUpperCase().equals("B") == true ||
					ainb.getTypeRegister().toUpperCase().equals("C") == true ||
					ainb.getTypeRegister().toUpperCase().equals("D") == true)
				if(getInfoFamilyCardsSystem().trim().length() != 0)
					message("1115");

			// Check that only 1 person in registration for register type A or I

			if(ainb.getTypeRegister().toUpperCase().equals("A") == true ||
					ainb.getTypeRegister().toUpperCase().equals("I") == true)
				if(getPersonsInRegistration().size() > 1)
					message("1118");


		}

	}
	
	/**
	 * 
	 * @param 
	 * 
	 * This method performs checks on the consistency of the persons in a registration
	 * The following message numbers can be issued:
	 * 
	 * 1055
	 * 1056
	 * 1068
	 * 1162
	 * 1163
	 * 1164
	 * 1165
	 * 1214
	 * 1215
	 * 1216
	 * 1317
	 * 1360
	 * 1402
	 * 1403
	 * 1404
	 * 1405
	 * 1406
	 * 1407
	 * 1408
	 * 1409
	 * 1410
	 * 1442
	 * 1443
	 * 1444
	 * 1445
	 * 1462
	 * 1463
	 * 1464
	 * 
	 */
	
	private void checkConsistencyPersons(Ref_AINB ainb){

		// Check that there is a Head of Household
		
		boolean headFound = false;
		for(Person p: personsInRegistration){
			if(p.getIsHead() == true)
				headFound = true;
		}
		
		if(headFound == false)
			message("1068");
			
		
		// Check that maximal 2 heads of household, including first head

		int heads = 0;
		for(Person p: getPersonsInRegistration()){
			if(p.getIsHead() == true || p.getIsHeadFirstSuccessor() == true)
				heads++;
		}	

		if(heads > 2)
			message("1360");		
		

		// Check that exactly one OP in registration

		boolean op_found = false;
		for(Person p: personsInRegistration){
			if(p.getNatureOfPerson()  == ConstRelations2.FIRST_APPEARANCE_OF_OP ){
				if(op_found == true){
				   message("1163");
				   break;
				}
				else
					op_found = true;
			}	
		}

		if(op_found == false)
			message("1162");
		
		
		// Check that the first appearance of OP has b2fcbg - natureOfPerson = 1 (1 = first appearance of OP, 5 = further appearance OP)
		
		boolean seenFirst = false;
		boolean seenFurther = false;
		
		for(Person p: personsInRegistration){
			if(p.getNatureOfPerson()  == ConstRelations2.FIRST_APPEARANCE_OF_OP){
				seenFirst = true;
			}
			if(p.getNatureOfPerson()  == ConstRelations2.FURTHER_APPEARANCE_OF_OP){
				if(seenFirst == false){
					message("1165", p.getKeyToRegistrationPersons());
				}
			}
		}
		
		// Check if natureOfPerson - b2fcbg is valid

		for(Person p: personsInRegistration){

			if(p.getNatureOfPerson() != ConstRelations2.FIRST_APPEARANCE_OF_OP && 
					p.getNatureOfPerson() != ConstRelations2.FURTHER_APPEARANCE_OF_OP &&
					p.getNatureOfPerson() != ConstRelations2.OTHER_PERSON_THAN_OP)
				message("1164", "" + p.getNatureOfPerson()); 
		}

		// Check that the registration dates are chronological
		
		int days = 0;
		boolean estimatedDates = false;
		boolean chronological = true;
		int firstNonChronologicalNr = 0;
		String firstNonChronologicalDate = null;
		boolean lastPersonNotChronological = false;
		
		for(Person p: personsInRegistration){
			
		    int x = 0;
			if(Common1.dateIsValid(p.getDayOfRegistration(), p.getMonthOfRegistration(), p.getYearOfRegistration()) == 0){				
				x = Utils.dayCount(p.getDayOfRegistration(), p.getMonthOfRegistration(), p.getYearOfRegistration());
				
			}
			else{
				if(p.getYearOfRegistration() > 1750 && p.getYearOfRegistration()  <  1941){  // year valid
					int day = p.getDayOfRegistration() > 0 ?  p.getDayOfRegistration() : 1;
					int month = p.getMonthOfRegistration() > 0 ?  p.getMonthOfRegistration() : 1;
					x = Utils.dayCount(day, month, p.getYearOfRegistration());
					estimatedDates = true;
				}
			}
			
			
			if(x > 0 && x < days){
				chronological = false;
				firstNonChronologicalNr = p.getKeyToRegistrationPersons();
				firstNonChronologicalDate = p.getDayOfRegistration() + "-" + p.getMonthOfRegistration() + "-" + p.getYearOfRegistration();
				if(p.getKeyToRegistrationPersons() == personsInRegistration.size())
					lastPersonNotChronological = true;
				
				break;
			}
			
			if(x > 0)
				days = x;
			
		}

		
		if(chronological == false){
			if(estimatedDates == true)				
				message("1214", "" + firstNonChronologicalNr, firstNonChronologicalDate);
			else
				message("1215", "" + firstNonChronologicalNr, firstNonChronologicalDate);
			
			if(lastPersonNotChronological == true)
				message("1216");
				
		}
		
		
		
		// Check compare head and his/her (grant)children for age and names
		
		
		if(personsInRegistration.size() > 0){

			String headSex = personsInRegistration.get(0).getSex();
			String wifeName = null;
			String headName = personsInRegistration.get(0).getFamilyName();
			int    headBirthYear = personsInRegistration.get(0).getYearOfBirth();

			// Get wife name

			outer:
				for(Person p: personsInRegistration){
					for(PersonDynamic pd: p.getDynamicDataOfPerson()){
						if(pd.getDynamicDataType() == ConstRelations2.RELATIE_TOT_HOOFD){
							if(pd.getContentOfDynamicData() == ConstRelations2.ECHTGENOTE_HOOFD){
								wifeName = p.getFamilyName();
								if(wifeName != null && wifeName.trim().length() != 0 && wifeName.equalsIgnoreCase(headName)){
									message("1462", pd.getKeyToRegistrationPersons());
									break outer;
								}
							}
						}	
					}
				}



			for(Person p: personsInRegistration){

				for(PersonDynamic pd: p.getDynamicDataOfPerson()){

					if(pd.getDynamicDataType() == ConstRelations2.RELATIE_TOT_HOOFD){

						if(pd.getContentOfDynamicData() == ConstRelations2.ZOON || pd.getContentOfDynamicData() == ConstRelations2.DOCHTER){

							if(p.getYearOfBirth() > 0 && headBirthYear > 0){

								if(headSex.equalsIgnoreCase("M")){

									// Check if age difference less than 16 years

									if(headBirthYear > p.getYearOfBirth() - 16)
										if(heads == 1)
											message("1402", pd.getKeyToRegistrationPersons(), new Integer(p.getYearOfBirth()).toString(), new Integer(headBirthYear).toString());
										else
											message("1411", pd.getKeyToRegistrationPersons(), new Integer(p.getYearOfBirth()).toString(), new Integer(headBirthYear).toString());

									// Check if age difference more than 80 years

									if(headBirthYear < p.getYearOfBirth() - 80)
										if(heads == 1)
											message("1404", pd.getKeyToRegistrationPersons(), new Integer(p.getYearOfBirth()).toString(), new Integer(headBirthYear).toString());
										else
											message("1413", pd.getKeyToRegistrationPersons(), new Integer(p.getYearOfBirth()).toString(), new Integer(headBirthYear).toString());

									// Check if age difference more than 100 years

									if(headBirthYear < p.getYearOfBirth() - 100)
										if(heads == 1)
											message("1403", pd.getKeyToRegistrationPersons(), new Integer(p.getYearOfBirth()).toString(), new Integer(headBirthYear).toString());
										else
											message("1412", pd.getKeyToRegistrationPersons(), new Integer(p.getYearOfBirth()).toString(), new Integer(headBirthYear).toString());


								}

								if(headSex.equalsIgnoreCase("V")){

									// Check if age difference less than 15 years

									if(headBirthYear > p.getYearOfBirth() - 15)
										message("1405", pd.getKeyToRegistrationPersons(), new Integer(p.getYearOfBirth()).toString(), new Integer(headBirthYear).toString());

									// Check if age difference more than 45 years

									if(headBirthYear < p.getYearOfBirth() - 45)
										message("1407", pd.getKeyToRegistrationPersons(), new Integer(p.getYearOfBirth()).toString(), new Integer(headBirthYear).toString());

									// Check if age difference more than 50 years

									if(headBirthYear < p.getYearOfBirth() - 50)
										message("1406", pd.getKeyToRegistrationPersons(), new Integer(p.getYearOfBirth()).toString(), new Integer(headBirthYear).toString());


								}
							}

							// Check if child has family name of wife of head (very rare)

							if(wifeName != null && p.getFamilyName() != null && wifeName.equalsIgnoreCase(p.getFamilyName()))
								message("1463", pd.getKeyToRegistrationPersons());

						}


						if(pd.getContentOfDynamicData() == ConstRelations2.KLEINZOON || pd.getContentOfDynamicData() == ConstRelations2.KLEINDOCHTER){

							if(p.getYearOfBirth() > 0){

								if(headSex.equalsIgnoreCase("M")){

									// Check if age difference less than 37 years

									if(headBirthYear > p.getYearOfBirth() - 37)
										message("1442", pd.getKeyToRegistrationPersons(), new Integer(p.getYearOfBirth()).toString(), new Integer(headBirthYear).toString());

									// Check if age difference more than 80 years

									if(headBirthYear < p.getYearOfBirth() - 80)
										message("1443", pd.getKeyToRegistrationPersons(), new Integer(p.getYearOfBirth()).toString(), new Integer(headBirthYear).toString());

									// Check if age difference more than 100 years

									if(headBirthYear < p.getYearOfBirth() - 100)
										message("1444", pd.getKeyToRegistrationPersons(), new Integer(p.getYearOfBirth()).toString(), new Integer(headBirthYear).toString());


								}

								if(headSex.equalsIgnoreCase("V")){

									// Check if age difference less than 35 years

									if(headBirthYear > p.getYearOfBirth() - 35)
										message("1445", pd.getKeyToRegistrationPersons(), new Integer(p.getYearOfBirth()).toString(), new Integer(headBirthYear).toString());


								}
							}
						}

						// Check if last name non blood related person is the same as last name of head

						if(pd.getContentOfDynamicData() <= 0 || (pd.getContentOfDynamicData() != ConstRelations2.ECHTGENOTE_HOOFD 
								&& pd.getContentOfDynamicData() != ConstRelations2.HOOFD 
								&& ConstRelations2.b3kode1_Related[pd.getContentOfDynamicData()] == null)){
							if(p.getFamilyName() != null && headName != null)
								if(p.getFamilyName().equalsIgnoreCase(headName)){
									message("1464", pd.getKeyToRegistrationPersons());
								} 
						}
					}
				}
			}
		}
		
        // For C Registers some checks when Head dies/leaves and OP still there
		
		int headLastLeaveDays = 0;
		int headDeceaseDays = 0;
		int OPLastLeaveDays = 0;
		int OPDeceaseDays = 0;
		int sourceEndDays = 0; 
		
		if(ainb != null && ainb.getTypeRegister().toUpperCase().equals("C") == true){
			
			sourceEndDays = Utils.dayCount(31, 12, ainb.getEndYearRegister()); 
			Person head = getPersonsInRegistration().get(0); // must be head for C register
			
			for(PersonDynamic pd : head.getDynamicDataOfPerson())
				if(pd.getDynamicDataType() == ConstRelations2.VERTREK)
					if(Common1.dateIsValid(pd.getDayOfMutation(), pd.getMonthOfMutation(), pd.getYearOfMutation()) == 0){
						headLastLeaveDays = Utils.dayCount(pd.getDayOfMutation(), pd.getMonthOfMutation(),pd.getYearOfMutation());
			}
			
			
			if(Common1.dateIsValid(head.getDayOfDecease(), head.getMonthOfDecease(), head.getYearOfDecease()) == 0)
				 headDeceaseDays = Utils.dayCount(head.getDayOfDecease(), head.getMonthOfDecease(), head.getYearOfDecease());

			
			Person OP = null;
			for(Person p : getPersonsInRegistration())
				if(p.getNatureOfPerson() == ConstRelations2.FIRST_APPEARANCE_OF_OP || p.getNatureOfPerson() == ConstRelations2.FURTHER_APPEARANCE_OF_OP)
					OP = p;
			
			
			for(PersonDynamic pd : OP.getDynamicDataOfPerson())
				if(pd.getDynamicDataType() == ConstRelations2.VERTREK)
					if(Common1.dateIsValid(pd.getDayOfMutation(), pd.getMonthOfMutation(), pd.getYearOfMutation()) == 0){
						OPLastLeaveDays = Utils.dayCount(pd.getDayOfMutation(), pd.getMonthOfMutation(),pd.getYearOfMutation());
			}
				
			if(Common1.dateIsValid(OP.getDayOfDecease(), OP.getMonthOfDecease(), OP.getYearOfDecease()) == 0)
				 headDeceaseDays = Utils.dayCount(OP.getDayOfDecease(), OP.getMonthOfDecease(), OP.getYearOfDecease());
			
			
			
			
			
			// Check if head deceases and OP still present (splitting of registration necessary)
			
			
			if(headDeceaseDays > 0 && headDeceaseDays < sourceEndDays && (headDeceaseDays < OPLastLeaveDays || (headDeceaseDays < OPDeceaseDays && OPDeceaseDays < sourceEndDays)))
					message("1408");
			
			
			// Check if head leaves and OP still present (splitting of registration necessary)
			
			if(headLastLeaveDays >0 && ((OPDeceaseDays > headLastLeaveDays && OPDeceaseDays < sourceEndDays && OPLastLeaveDays != headLastLeaveDays) || OPLastLeaveDays > headLastLeaveDays))
				message("1409");
				
			// Check if head leaves or dies and OP still present
			
			if(OPLastLeaveDays == 0 && (OPDeceaseDays == 0 || OPDeceaseDays > sourceEndDays) && (headLastLeaveDays > 0 || headDeceaseDays > 0))
				message("1410");
			
			
			
			
		}	
		
		// Check if marriage dates head and wife of head match 
		
		ArrayList<Integer>  headDay     = new ArrayList<Integer>();
		ArrayList<Integer>  headMonth   = new ArrayList<Integer>();
		ArrayList<Integer>  headYear    = new ArrayList<Integer>();
		
		int headLineNumber = 0;
		
		int wifeDay = 0;
		int wifeMonth = 0;
		int wifeYear = 0;
		
		int wifeLineNumber = 0;
		
		for(Person p: getPersonsInRegistration()){
			
			boolean head = false;
			boolean wife = false; 
			
			for(PersonDynamic pd: p.getDynamicDataOfPerson()){

				if(pd.getDynamicDataType() == ConstRelations2.RELATIE_TOT_HOOFD){
					if(pd.getContentOfDynamicData() == ConstRelations2.HOOFD)
						head = true;
					else
						if(pd.getContentOfDynamicData() == ConstRelations2.ECHTGENOTE_HOOFD)
							wife = true;
				}
				
				if(pd.getDynamicDataType() == ConstRelations2.BURGELIJKE_STAAT && pd.getContentOfDynamicData() == ConstRelations2.GEHUWD){
					
					if(head == true){
						headDay.add(pd.getDayOfMutation());
						headMonth.add(pd.getMonthOfMutation());
						headYear.add(pd.getYearOfMutation());
						headLineNumber = p.getKeyToRegistrationPersons();
						//break; // use first date
					}
					else{
						if(wife == true){
							wifeDay = pd.getDayOfMutation();
							wifeMonth = pd.getMonthOfMutation();
							wifeYear = pd.getYearOfMutation();
							wifeLineNumber = p.getKeyToRegistrationPersons();
                            break; 
		    			}
					}
				}	
			}
		}

		if(wifeDay < 1)   wifeDay = 1;
		if(wifeMonth < 1) wifeMonth = 1;
		
		if(Common1.dateIsValid(wifeDay, wifeMonth, wifeYear)== 0) {

			boolean match = false;
			for(int i = 0; i < headDay.size(); i++) {

				int headDay_   = headDay.get(i);
				int headMonth_ = headMonth.get(i);
				int headYear_  = headYear.get(i);

				if(headDay_ < 1)   headDay_ = 1;
				if(headMonth_ < 1) headMonth_ = 1;

				if(Common1.dateIsValid(headDay_, headMonth_, headYear_) == 0)
					if(headDay_ == wifeDay || headMonth_ == wifeMonth || headYear_ == wifeYear)
						match = true;


			}

			if(!match)
				message("1317", "" + headLineNumber, "" + wifeLineNumber);
		}
		
		// Next case
		
		CheckIncest();
		
		
		
	}
	
	
	
	
	
	/**
	 * 
	 * 
	 * This method checks for incest relations within a household
	 * The following messages can be issued:
	 * 
	 * 1381
	 * 1382
	 * 1383
	 * 1384
	 * 1385
	 * 
	 * 
	 */

	private void CheckIncest(){

		// First find marriage

		for(Person p1: getPersonsInRegistration()){
			for(PersonDynamic pd: p1.getDynamicDataOfPerson()){		
				if(pd.getDynamicDataType() == ConstRelations2.BURGELIJKE_STAAT && pd.getContentOfDynamicData() == ConstRelations2.GEHUWD){					
					if(pd.getValueOfRelatedPerson() > 0){
						Person p2 = null; // this is the "related" person, the partner
						for(Person pp: getPersonsInRegistration()){
							if(pp.getKeyToRegistrationPersons() == pd.getValueOfRelatedPerson()){
								p2 = pp;
								break;
							}

						}

						// Compare p1 and p2

						if(p2 != null){

							// find the relation to the head of both persons

							int p1RelToHead = 0;						    	
							for(PersonDynamic pd1: p1.getDynamicDataOfPerson()){
								if(pd1.getDynamicDataType() == ConstRelations2.RELATIE_TOT_HOOFD){
									p1RelToHead =  pd1.getContentOfDynamicData();
									break;
								}

							}

							int p2RelToHead = 0;						    	
							for(PersonDynamic pd2: p2.getDynamicDataOfPerson()){
								if(pd2.getDynamicDataType() == ConstRelations2.RELATIE_TOT_HOOFD){
									p2RelToHead =  pd2.getContentOfDynamicData();
									break;
								}

							}

							if(p1RelToHead >= 0 && p2RelToHead >= 0){

								// Check if head married to own son/daughter 


								if(p1RelToHead == ConstRelations2.HOOFD && p1.getIsHeadFirstSuccessor() == false){
									if(p2RelToHead == ConstRelations2.ZOON || p2RelToHead == ConstRelations2.DOCHTER)
										message("1382", "" + p1.getKeyToRegistrationPersons(), "" + p2.getKeyToRegistrationPersons());

									// Check if head married to non allowed blood relatives
									else
										if(ConstRelations2.b3kode1_Marriable[p2RelToHead] == null)
											message("1383", "" + p1.getKeyToRegistrationPersons(), "" + p2.getKeyToRegistrationPersons());

								}
								else{

									// Check if a son and a daughter of the head are married
									if((p1RelToHead == ConstRelations2.ZOON  && p2RelToHead == ConstRelations2.DOCHTER) || (p2RelToHead == ConstRelations2.ZOON  && p1RelToHead == ConstRelations2.DOCHTER))
										message("1381", "" + p1.getKeyToRegistrationPersons(), "" + p2.getKeyToRegistrationPersons());

									else{

										// Check other relations

										if(p1RelToHead >= 0 && ConstRelations2.b3kode1_No_Marriage_Allowed[p1RelToHead] != null){
											for(int i = 0; i < ConstRelations2.b3kode1_No_Marriage_Allowed[p1RelToHead].length; i++){
												if(ConstRelations2.b3kode1_No_Marriage_Allowed[p1RelToHead][i] == p2RelToHead){
													if(p1.getSex().toUpperCase().equals("M")){
														message("1384");
														break;
													}
													else{
														message("1385");
														break;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}	
	}

	
	/**
	 * 
	 * @param 
	 * 
	 * This method performs checks on the addresses of a registration
	 * The following message numbers can be issued:
	 * 
	 * 1084
	 * 1087
	 * 1088
	 * 1128
	 * 1130
	 * 1133
	 * 
	 */
	
	private void checkConsistencyAddresses(){
		
		// Check if there are addresses
		
		if(getAddressesOfRegistration() == null || getAddressesOfRegistration().size() == 0){			
			message("1084");
			return;
		}	
			
		
		// 1087 replacement begin

		// 1072  FT  Er is geen adresdatum en [b6sinr]  is gelijk aan 0
		//
		// But: [undated] [B6SINR = 0] ST
		//      [undated] [B6SINR = 0] ST
		//      [undated] [B6SINR = 0] ST is OK
        //
		//      [undated] [B6SINR = 0] ST
		//      [undated] [B6SINR = 0] ST
		//      [undated] [B6SINR = 0] WK is no OK
        //
		
		// 1073  FT Bij dezelfde adresdatum zijn verschillende [b6sinr] 

		// 1074  FT  Volgnummer [b6vrnr] loopt niet gelijk op met adresdatum


		 
		HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();
		Integer b6sinr = null;
		int prevDayCount = 0;
		boolean chronological = true;
		String undatedSequenceType = null;
		
		for(RegistrationAddress ra: getAddressesOfRegistration()){
			
			// Test that b6sinr != 0 if no address date and mixed address types
			
			if(ra.getYearOfAddress() <= 0 && ra.getSynchroneNumber() == 0){
				if(undatedSequenceType != null){
					if(!undatedSequenceType.equalsIgnoreCase(ra.getAddressType()))
							message("1072");	
							
				}
				else
					undatedSequenceType = ra.getAddressType();
					
			}
			else
				undatedSequenceType = null;
				
			if(Common1.dateIsValid(ra.getDayOfAddress(), ra.getMonthOfAddress(), ra.getYearOfAddress()) == 0){
				
				if(Utils.dayCount(ra.getDayOfAddress(), ra.getMonthOfAddress(), ra.getYearOfAddress()) < prevDayCount){
					message("1074");
					chronological = false;
				}
				else
					prevDayCount = Utils.dayCount(ra.getDayOfAddress(), ra.getMonthOfAddress(), ra.getYearOfAddress());
				
				b6sinr = h.get(Utils.dayCount(ra.getDayOfAddress(), ra.getMonthOfAddress(), ra.getYearOfAddress()));
				if(b6sinr == null)
					h.put(Utils.dayCount(ra.getDayOfAddress(), ra.getMonthOfAddress(), ra.getYearOfAddress()), ra.getSynchroneNumber());
				else
					if(b6sinr != ra.getSynchroneNumber())
						message("1073");

				// Check if address is bound to the household

				if(ra.getKeyToRegistrationPersons() != 0)
						message("1088");

			}
		}
		
		
		// 1087 replacement end
		
		
		// Check that the B6 key is OK address SequenceNumber must start with 1 and be contiguous (1, 2, 3, 4......)
		
		int seqNo = 1;
		int persNo = -1;
		for(RegistrationAddress ra: getAddressesOfRegistration()){
			
			if(ra.getKeyToRegistrationPersons() != persNo){
				persNo = ra.getKeyToRegistrationPersons();
				seqNo = 1;
				
			}
			//System.out.println("seqNo = " + seqNo + ", getSequenceNumberToAddresses = " + ra.getSequenceNumberToAddresses());
			if(ra.getSequenceNumberToAddresses() != seqNo){
				//System.out.println("Message 1064");
				message("1064", "" + ra.getSequenceNumberToAddresses()); // 1064, FS, Sleutel van B6 klopt niet (doornummering regels fout)
				chronological = false;				
				break;
			}
			else
				seqNo++;
		}
			
		// Check that the B6SINR starts with 1 and is contiguous (1,1,1,2,2,2, 3, 4,4......)
		// But... It can always drop back to 0, so 1,1,2,2,0,3,3 is OK
		
		int curSinr  = 0;
		for(RegistrationAddress ra: getAddressesOfRegistration()){
			if(ra.getSynchroneNumber() != 0 && ra.getSynchroneNumber() != curSinr && ra.getSynchroneNumber() != curSinr + 1){
				message("1071");  // 1071, FS, B6SINR klopt niet (doornummering regels fout)
				break;
			}
			else
				if(ra.getSynchroneNumber() != 0)
					curSinr = ra.getSynchroneNumber();
		}

		
		// Check that if B6SINR > 0, there are at least 2 entries with that value
		
		int curSynch = -1;
		int curSynchOccurrences = 0;;
		for(RegistrationAddress ra: getAddressesOfRegistration()){
			if(ra.getSynchroneNumber() > 0){
				if(ra.getSynchroneNumber() == curSynch)
					curSynchOccurrences++;
				else{
					if(curSynchOccurrences == 1)
						message("1075", "" + curSynch);
					curSynch = ra.getSynchroneNumber();
					curSynchOccurrences = 1;
				}
			}
		}
		if(curSynchOccurrences == 1)
			message("1075", "" + curSynch);

		
		// Check that B6SINR == 0 for entries that have a date

		for(RegistrationAddress ra: getAddressesOfRegistration())
			if(ra.getYearOfAddress() > 0 && ra.getSynchroneNumber() != 0)
				message("1076");

		
		
		// Next tests only if chronological addresses
		
		if(chronological == true){
			
			int headDate = 0;
			int firstAddressDateEarliest = 0;
			int firstAddressDateLatest   = 0;
			
			int firstAddressDay =   getAddressesOfRegistration().get(0).getDayOfAddress();
			int firstAddressMonth = getAddressesOfRegistration().get(0).getMonthOfAddress();
			int firstAddressYear =  getAddressesOfRegistration().get(0).getYearOfAddress();
			
			int faDayEarliest      = firstAddressDay   > 0 ? firstAddressDay   : 1;
			int faMonthEarliest    = firstAddressMonth > 0 ? firstAddressMonth : 1;

			int faDayLatest        = firstAddressDay   > 0 ? firstAddressDay   : 28;
			int faMonthLatest      = firstAddressMonth > 0 ? firstAddressMonth : 12;


			
			if(Common1.dateIsValid(getDayEntryHead(), getMonthEntryHead(), getYearEntryHead()) == 0)				
				headDate = Utils.dayCount(getDayEntryHead(), getMonthEntryHead(), getYearEntryHead());
			
			if(Common1.dateIsValid(faDayEarliest, faMonthEarliest, firstAddressYear) == 0)				
				firstAddressDateEarliest = Utils.dayCount(faDayEarliest, faMonthEarliest, firstAddressYear);
				
			if(Common1.dateIsValid(faDayLatest, faMonthLatest, firstAddressYear) == 0)				
				firstAddressDateLatest = Utils.dayCount(faDayLatest, faMonthLatest, firstAddressYear);
				
			// Check if the head date is before the first address date 

			if(headDate > 0 && firstAddressDateEarliest > 0 && headDate + 20 < firstAddressDateEarliest)
				message("1128", "" + getDayEntryHead() + "-" + getMonthEntryHead()  + "-" + getYearEntryHead(), 
						        "" + firstAddressDay   + "-" + firstAddressMonth    + "-" + firstAddressYear);

			// Check if latest date first address more than 5 days before earliest registration date head

			if(getPersonsInRegistration().size() > 0){

				int headRegistrationDateEarliest = 0;
				int headRegistrationDateLatest   = 0;
				
				int headRegistrationDay =   getPersonsInRegistration().get(0).getDayOfRegistration(); 
				int headRegistrationMonth = getPersonsInRegistration().get(0).getMonthOfRegistration(); 
				int headRegistrationYear =  getPersonsInRegistration().get(0).getYearOfRegistration(); 
				
				int hrd  = headRegistrationDay   > 0 ? headRegistrationDay   : 28;
				int hrm  = headRegistrationMonth > 0 ? headRegistrationMonth : 12;
				
				if(Common1.dateIsValid(hrd, hrm, headRegistrationYear) == 0)				
					headRegistrationDateLatest = Utils.dayCount(hrd, hrm, headRegistrationYear);
				
				hrd  = headRegistrationDay   > 0 ? headRegistrationDay   : 1;
				hrm  = headRegistrationMonth > 0 ? headRegistrationMonth : 1;
				
				if(Common1.dateIsValid(hrd, hrm, headRegistrationYear) == 0)				
					headRegistrationDateEarliest = Utils.dayCount(hrd, hrm, headRegistrationYear);
				
				

				if(headRegistrationDateEarliest > 0 && firstAddressDateLatest > 0 && firstAddressDateLatest + 5 < headRegistrationDateEarliest)
					message("1130", "" + firstAddressDay     + "-" + firstAddressMonth     + "-" + firstAddressYear,
						        	"" + headRegistrationDay + "-" + headRegistrationMonth + "-" + headRegistrationYear);

				// Check if date first address more than 30 days after registration date head

				if(headRegistrationDateLatest > 0 && firstAddressDateEarliest > 0 && firstAddressDateEarliest  > headRegistrationDateLatest + 30)
					message("1133", "" + firstAddressDay     + "-" + firstAddressMonth     + "-" + firstAddressYear,
						        	"" + headRegistrationDay + "-" + headRegistrationMonth + "-" + headRegistrationYear);
			}
		}
	}

	
	/**
	 * 
	 * 
	 * This method converts the Registration Object to the new format
	 * It also calls the convert method 
	 * for the Person and RegistrationAddress objects belonging to this Registration object
	 * 
	 * 
	 */
	public void Convert(){
		
		
        //showFields();
		
		standardizedRegistration = new RegistrationStandardized(this); 
		setStandardizedRegistration(standardizedRegistration);
		standardizedRegistration.setOriginalRegistration(this);
		
		// Next instructions link OP <-> RegistrationStandardized
		
		getOp().getRegistrationsStandardizedOfOP().add(standardizedRegistration);
		standardizedRegistration.setOp(getOp());
		
		standardizedRegistration.transform(this);
		
		for(Person p: personsInRegistration){
			p.Convert();

		}

		//fixReligion();
		
		standardizedRegistration.improveReciprocity();
		standardizedRegistration.improveReciprocity2();
		
		ConvertAddresses();
		

	}
	
	
	/**
	 * 
	 * This method loops through the addresses of a registration
	 * Addresses are aggregated 
	 * and consolidated in a RegistrationAddressStandardized object. 
	 * 
	 */
	
	
	private void ConvertAddresses(){


		int currentSequenceNumber = -1;
		int currentDay = 0;
		int currentMonth = 0;
		int currentYear = 0;

		RegistrationAddress rao = null;
		RegistrationAddressStandardized ras = null;
		String streetName = null;

        int sequenceNumber = 1;

		for(RegistrationAddress ra: addressesOfRegistration){
			
			String old_data = String.format("%d %d %d-%d-%d %s ST: %s NR: %s AD: %s",
                    ra.getSequenceNumberToAddresses(),
                    ra.getSynchroneNumber(),
                    ra.getDayOfAddress(), 
                    ra.getMonthOfAddress(),
                    ra.getYearOfAddress(),
                    ra.getAddressType(),
                    ra.getNameOfStreet().trim(),
                    ra.getNumber().trim(),
                    ra.getAdditionToNumber().trim());


			//if(ra.getKeyToRP() == 6083) System.out.println("-----> " + old_data);
			
			if(ra.getAddressType().equalsIgnoreCase("OA") == true){ 

				if((ra.getSynchroneNumber() == 0 || ra.getSynchroneNumber() != currentSequenceNumber ) &&
						(Common1.dateIsValid(ra.getDayOfAddress(), ra.getMonthOfAddress(), ra.getYearOfAddress()) != 0 ||
								ra.getDayOfAddress() != currentDay || ra.getMonthOfAddress() != currentMonth || ra.getYearOfAddress() != currentYear)){  // new set of addresses		

					
					currentSequenceNumber = ra.getSynchroneNumber();
					currentDay = ra.getDayOfAddress();
					currentMonth = ra.getMonthOfAddress();
					currentYear = ra.getYearOfAddress();

					if(rao != null){ // saved object, must be processed first
						ras = rao.getStandardizedRegistrationAddress();  // restore ras
						ras.convertAddress();  // Convert further
						rao = null;
					}


					ras = new RegistrationAddressStandardized(ra);
					ras.setOriginalAddress(ra); 
					ra.setStandardizedRegistrationAddress(ras);
					ra.getRegistrationToWhichAddressRefers().getStandardizedRegistration().getAddressesStandardizedOfRegistration().add(ras);
					ras.setAddressFlag(1);

					ras.setLandlord(old_data); 	// Copy original data XYZ
					
					ras.transform(ra);  // transforms dates
					ras.setSequenceNumberToAddresses(sequenceNumber);
					sequenceNumber++;
					
					if(convertOldAddressLine(ra, streetName, ras) == null)
						ras.setOldAddressTotallyProcessed(true);
					
					//ras.convertAddress(); // look in reference tables 


					streetName = ras.getStreet(); // Need this later if next record has a "*"
					rao = ra;  // save this RegistrationAddress
				}
				else{

					ras.setOther(old_data); 	// Copy original data  XYZ
					convertOldAddressLine(ra, streetName, ras);
				}
			}
			else{

				if((ra.getSynchroneNumber() == 0 || ra.getSynchroneNumber() != currentSequenceNumber ) &&
						(Common1.dateIsValid(ra.getDayOfAddress(), ra.getMonthOfAddress(), ra.getYearOfAddress()) != 0 ||
								ra.getDayOfAddress() != currentDay || ra.getMonthOfAddress() != currentMonth || ra.getYearOfAddress() != currentYear)){  // new set of addresses		

					currentSequenceNumber = ra.getSynchroneNumber();
					currentDay = ra.getDayOfAddress();
					currentMonth = ra.getMonthOfAddress();
					currentYear = ra.getYearOfAddress();

					if(rao != null){ // saved object, must be processed first
						ras = rao.getStandardizedRegistrationAddress();  // restore ras
						ras.convertAddress();  // Convert further
						rao = null;
					}


					ras = new RegistrationAddressStandardized(ra); // new ras
					ras.setOriginalAddress(ra); 
					ra.setStandardizedRegistrationAddress(ras);
					ra.getRegistrationToWhichAddressRefers().getStandardizedRegistration().getAddressesStandardizedOfRegistration().add(ras);
					ras.setAddressFlag(2);
					
					ras.setLandlord(old_data); 	// Copy original data


					ras.transform(ra); 
					ras.setSequenceNumberToAddresses(sequenceNumber);
					sequenceNumber++;
					convertNewAddressLine(ra, ras);
					rao = ra;  // save this RegistrationAddress


				}
				else{ // We are in a set of addresses that belong together
					ras.setOther(old_data); 	// Copy original data
					convertNewAddressLine(ra, ras);

				}
				
			}

		}
		if(rao != null){  // If there is an old ra, it must be processed
			ras = rao.getStandardizedRegistrationAddress();  // restore ras
	    	ras.convertAddress();
		}
		

	}
	
	/**
	 * 
	 * @param ra
	 * @param ras
	 * 
	 * @returns Name of a street or quarter if one if found
	 * 
	 * This routine aggregates a RegistrationAddresses (4.01 or lower) into a RegistrationAddresStandardized
	 * 
	 * Returns: Part of the address line that was not processed
	 * 
	 *  Examples: Tuinstraat 41/dl.15/bl.4126/wijk II	                 
     *            Weg naar Vleuten 52 
     *            1e Hugo de Grstr.
	 *            G 289 E
	 *            * 93>Italiaander
	 *            D 3 / Hoek van St. Marie 3
	 *            Jan v/d.Heijdenstraat 215
	 *            * 17/dl.35/fo.49/2 hoog
	 *            349
	 *            %bijz%
	 *            $Geen adres$>Baars+A.J.
	 */
	
	private String convertOldAddressLine(RegistrationAddress ra, String streetName,  RegistrationAddressStandardized ras){

		//if(ra.getKeyToRP() == 4005) System.out.println(streetName + "  ras Landlord " + ras.getLandlord() + "ras Other" + ras.getOther());

		
		if(ra.getNameOfStreet().trim().equalsIgnoreCase("$Geen Adres$")){
			ras.setAddressFlag(3);  // indicator of 'no address'
			return "";
		}


		String [] ret = null;
		boolean processedAll = false; 
		
		// A1: See if we have a "*" in the record, replace it by the street/quarter name of the previous record

		String address = "";
		int index = ra.getNameOfStreet().indexOf("*");		
		if(index >= 0){

			if(streetName != null){

				address = ra.getNameOfStreet().substring(0, index);
				address += " ";
				address += (streetName != null ? streetName : ""); 
				address += " ";
				if(ra.getNameOfStreet().length() > index +1)
					address += ra.getNameOfStreet().substring(index +1);
			}

		}	
		else
			address = ra.getNameOfStreet();



		// A2: See B
		
		
		// A3: See if there is landlord info		
		
		index = address.indexOf(">");
		if(index >= 0){			
			ras.setLandlord(address.substring(index + 1).trim());
			address = address.substring(0,index); 
			
		}

		// B: Check for "/"
		
		index = address.indexOf("/");
		
		if(index < 0){
			
			// B1: No "/"
			
			// C1: Try to find Quarter information
			// C2: Try to find Street information 
			
			
			
			ret = Common1.tryQuarterInfo(address);
			if(ret != null){
				ras.setQuarter(ret[0]);
				address = ret[1];
				if(address != null)
					address = tryStreetInfo(address, ras);
			}
		}

		else{
			
			// Found "/"
			
			if(index > 0){
			
				String address1 = address.substring(0, index).trim();
				
				ret = Common1.tryQuarterInfo(address1);
				
				
				if(index < address.length() -1){
					String address2 = address.substring(index + 1).trim();
					tryStreetInfo(address2, ras);
				}
				
				/*
				if(ret != null){
					ras.setQuarter(ret[0]);
					address = ret[1];
					if(address != null)
						address = tryStreetInfo(address, ras);
				}
				*/

				/*
				if(address != null && index + 1 < address.length() - 1){
				
					String address2 = address.substring(index + 1).trim();
					
					ret = Common1.tryQuarterInfo(address2);
					if(ret != null){
						ras.setQuarter(ret[0]);
						address2 = ret[1];
						if(address2 != null)
							address2 = tryStreetInfo(address2, ras);
					}

				
				}
				*/
			}
			else // index == 0
				address = tryStreetInfo(address, ras);
			
		}
		
		return address;
		


	}
	
	
	
	/**
	 * 
	 * This routine tries to find Quarter (=Wijk) information from the string address
	 * If it finds it, it sets the appropriate fields in the ras
	 * 
	 * Example: Wijk 15 no.375 Binneweg
	 * 
	 */
	
	
	private String tryQuarterInfoOld(String address, RegistrationAddressStandardized ras){
		
		
		String rt = null; 
		String [] a = address.split(" ");
		
		switch(a.length){
		
		case 0: break;
		case 1:
			
			if(a[0].length() <= 2){
				ras.setQuarter(a[0]);
				rt = null;
			}

			break;		


		case 2:
			
			if(a[0].toUpperCase().equals("WIJK")){
				ras.setQuarter(a[1]);
				
				rt= null;
			}
			else{

				if(a[0].length() <= 2){
					ras.setQuarter(a[0]);
					rt = a[1];
				}
				else
					rt = address;

			}
			
			break;
				
		default:

			if(a[0].toUpperCase().equals("WIJK")){
				ras.setQuarter(a[1]);
				
				rt= null;
			}
			else{

				if(a[0].length() <= 2){
					ras.setQuarter(a[0]);
					
					rt = "";
					
					for(int i = 2; i < a.length; i++){
						
						rt += a[i];
						rt += " ";
					}
					rt = rt.trim();
						
				}
				else
					rt = address;

			}
			
			break;

		}

		return rt;
	}
	
	private static String tryQuarterInfo(String address, RegistrationAddressStandardized ras){

    	if(address == null  || address.trim().length() == 0) return "";


		String [] a = address.split("[ ]+");


		
		
		if(a != null && a.length > 0){
			for(int i = 0; i < a.length; i++){
				if(a[i].equalsIgnoreCase("Wijk") || 
				   a[i].equalsIgnoreCase("Wk") ||
				   a[i].equalsIgnoreCase("Wk.")){
					if(i + 1 < a.length){
						ras.setQuarter(a[i + 1]);
						address = "";
						for (int ii = 0; ii < a.length; ii++)
							if(ii != i && ii != i + 1)
							    address = address + a[ii] + " ";
					}
					return address.trim();
				}
			}
		}


		if((a[0].length() == 1 &&   Character.isUpperCase(a[0].charAt(0)) == true) || (a[0].length() == 2 &&   Character.isUpperCase(a[0].charAt(0)) == true  && Character.isUpperCase(a[0].charAt(1)) == true)){
			
			
			if(!(a.length > 1 && Character.isAlphabetic(a[1].charAt(0)))){ // Not followed by text, eg Q van Uffelenstraat
			
				ras.setQuarter(a[0]);

				address = "";
				for (int i = 1; i < a.length; i++)
					address = address + a[i] + " ";


				return address.trim();
			}


		}

		return address;
	}
	/**
	 * 
	 * This routine tries to find Street (=Straat) information from the string address
	 * If it finds it, it sets the appropriate fields in the ras
	 * 
	 * 
	 */
	
	
	private String tryStreetInfo(String address, RegistrationAddressStandardized ras){
		
		String rt  = null; 
		String[] rt2  = null; 

		String [] a = address.split("[ ]+");
		/*
		if(ras.getKeyToRP() == 4005){
			System.out.println(ras.getKeyToRP() + "  " + address);
			for(int i = 0; i < a.length; i++)			
				System.out.println(i + " " + a[i]); // XYZ
		}
		*/
		switch(a.length){

		case 0: break;
		case 1:


			if(a[a.length - 1].length() > 0){
				if(Character.isDigit(a[a.length - 1].charAt(0)) == true && a[a.length - 1].length() <= 4){
					
					rt2 = Common1.tryNumberInfo(a[a.length - 1]);
					
					if(rt2[0].length() > 0) ras.setNumber(rt2[0]);
					if(rt2[1].length() > 0) ras.setAddition(rt2[1]);
					
					rt = null;				
				}
				else{
					ras.setStreet(a[a.length - 1]);
					rt = null;
				}
			}

			break;

		case 2:

			if(a[a.length - 1].length() > 0){
				if(Character.isDigit(a[a.length - 1].charAt(0)) == true && a[a.length - 1].length() <= 4){
					
					rt2 = Common1.tryNumberInfo(a[a.length - 1]);
					
					if(rt2[0].length() > 0) ras.setNumber(rt2[0]);
					if(rt2[1].length() > 0) ras.setAddition(rt2[1]);
					

					String street = "";
					for(int i = 0; i < a.length - 1; i++){
						street += a[i]; 
						street += " " ;
					}		
					ras.setStreet(street.trim());

					rt = null;

				}
			}	
			else{
				if(a[a.length - 2].length() > 0){
					if(Character.isDigit(a[a.length - 2].charAt(0)) == true && a[a.length - 2].length() <= 4){

						rt2 = Common1.tryNumberInfo(a[a.length - 1]);

						if(rt2[0].length() > 0) ras.setNumber(rt2[0]);
						
						ras.setAddition(rt2[1] + a[a.length - 1]);
						rt = null;

					}
				}	
			}

			break;

			

		default:
			
			/*if(ras.getKeyToRP() == 4005){
				System.out.println(ras.getKeyToRP() + "  " + address);
				for(int i = 0; i < a.length; i++)			
					System.out.println("default "+ i + " " + a[i]); // XYZ
			}

			if(ras.getKeyToRP() == 4005){
				System.out.println("a[a.length - 1] = " + a[a.length - 1]);
				System.out.println("a[a.length - 2] = " + a[a.length - 2]);
				System.out.println(".charAt(0))" + a[a.length - 1].charAt(0));
				System.out.println("Character.isDigit(a[a.length - 1].charAt(0))" + Character.isDigit(a[a.length - 1].charAt(0)));
				System.out.println(".charAt(0))" + a[a.length - 2].charAt(0));
				System.out.println("Character.isDigit(a[a.length - 2].charAt(0))" + Character.isDigit(a[a.length - 2].charAt(0)));
			}

*/
			if(Character.isDigit(a[a.length - 1].charAt(0)) == true && a[a.length - 1].length() <= 4){

				rt2 = Common1.tryNumberInfo(a[a.length - 1]);

				if(rt2[0].length() > 0) ras.setNumber(rt2[0]);
				if(rt2[1].length() > 0) ras.setAddition(rt2[1]);

				/*
				if(ras.getKeyToRP() == 4005){
					System.out.println(ras.getKeyToRP() + "  " + address);
					for(int i = 0; i < a.length; i++)			
						System.out.println("default 1"+ i + " " + a[i]); // XYZ
				}
*/

				String street = "";
				for(int i = 0; i < a.length - 1; i++){
					street += a[i]; 
					street += " " ;
				}		
				if(street != null)
					ras.setStreet(street.trim());

				rt = null;

			}

			else{
				/*
				if(ras.getKeyToRP() == 4005){
					System.out.println("X a[a.length - 2] = " + a[a.length - 2]);
					System.out.println("X .charAt(0))" + a[a.length - 2].charAt(0));
					System.out.println("X Character.isDigit(a[a.length - 2].charAt(0))" + Character.isDigit(a[a.length - 2].charAt(0)));
				}
*/
				if(Character.isDigit(a[a.length - 2].charAt(0)) == true && a[a.length - 2].length() <= 4){

					rt2 = Common1.tryNumberInfo(a[a.length - 2]);

					if(rt2[0].length() > 0) ras.setNumber(rt2[0]);
					ras.setAddition(rt2[1] + a[a.length - 1]);
					/*
					if(ras.getKeyToRP() == 4005){
						System.out.println(ras.getKeyToRP() + "  " + address);
						for(int i = 0; i < a.length; i++)			
							System.out.println("default 2"+ i + " " + a[i]); // XYZ
					}
*/

					String street = "";
					for(int i = 0; i < a.length - 2; i++){
						street += a[i]; 
						street += " " ;
					}		
					ras.setStreet(street.trim());

					rt = null;

				}



			}

		}				

		return rt;

	}
	
	
	
	private void convertOldAddressLine2(RegistrationAddress ra, String streetName,  RegistrationAddressStandardized ras){

		//System.out.println("convertOldAddressLine! ");

		String prefix = null;
		int index = 0;
		String [] a = null;

		String address = ra.getNameOfStreet();
		// See if we have a * in the record, replace it by the street name of the previous record
		
		index = ra.getNameOfStreet().indexOf("*");
		if(index >= 0){
			
			address = ra.getNameOfStreet().substring(0, index);
			address += " ";
			address += streetName; 
			address += " ";
			if(ra.getNameOfStreet().length() > index +1)
				address += ra.getNameOfStreet().substring(index +1);
		}	
		else
			address = ra.getNameOfStreet();
		
		// See if there is landlord info		
		
		index = address.indexOf(">");
		if(index >= 0){			
			ras.setLandlord(address.substring(index + 1).trim());
			address = address.substring(0,index); 
			
		}
		
		// See if there is quarter information
		
		index = address.indexOf("/");
		if(index >= 0)
			prefix = address.substring(0, index);
		else
			prefix = address;
		

		a = prefix.split(" ");

		switch(a.length){
		case 1:
			boolean numeric = true;
			for (int i = 0; i < a[0].length(); i++) {         
				if (!Character.isDigit(a[0].charAt(i))) 
					numeric = false;;        

			}
			if(numeric == true)
				ras.setNumber(a[0]); 

			else{
				if(a[0].length() < 2)
					ras.setQuarter(a[0]); 
			}
			break;		

		case 2:
			if(a[0].toUpperCase().equals("WIJK"))
				ras.setQuarter(a[1]);

			else{
				if(a[0].length() < 2){
					ras.setQuarter(a[0]);
					ras.setNumber(a[1]);
				}	
			}
			break;					

		case 3:
			if(a[0].toUpperCase().equals("WIJK")){
				ras.setQuarter(a[1]);
				ras.setNumber(a[2]);
			}

			else{
				if(a[0].length() < 2){
					ras.setQuarter(a[0]);
					ras.setNumber(a[1]); 
				}
			}
			break;		

		case 4:
			if(a[0].toUpperCase().equals("WIJK")){
				ras.setQuarter(a[1]);
				ras.setNumber(a[3]);
			}

			else{
				if(a[0].length() < 2){
					ras.setQuarter(a[0]);
					ras.setNumber(a[1]); 
				}
			}
			break;
			
        default:				

		}


		// If we found something, remove prefix from address 

		if(ras.getQuarter() != null || ras.getNumber() != null){

			if(index >= 0)
				if(address.length() > index + 1)
					address = address.substring(index + 1); 


		}

		// Try to find street information

		a = address.split(" ");

		switch(a.length){
		
		case 0: break;
		case 1:
			
			if(Character.isDigit(a[a.length - 1].charAt(0)) == true && a[a.length - 1].length() <= 4)
				ras.setNumber(a[a.length - 1]);
			else
				ras.setStreet(a[a.length - 1]);
		
			break;
		
		default:
		
			if(Character.isDigit(a[a.length - 1].charAt(0)) == true && a[a.length - 1].length() <= 4){
				ras.setNumber(a[a.length - 1]);
				
				String street = "";
				for(int i = 0; i < a.length - 1; i++){
					street += a[i]; 
					street += " " ;
				}		
				if(street != null)
					ras.setStreet(street.trim());
			}
			else{
				if(Character.isDigit(a[a.length - 2].charAt(0)) == true && a[a.length - 2].length() <= 4){
					ras.setNumber(a[a.length - 2]);
					ras.setAddition(a[a.length - 1]);
					
					if(a.length > 2){
						String street = "";;
						for(int i = 0; i < a.length - 2; i++){
							street += a[i];
							street += " " ;
						}				
						if(street != null)
							ras.setStreet(street.trim());
					}
				}
				else
					ras.setStreet(address);
			}
		}
	}

	/**
	 * 
	 * @param ra
	 * @param ras
	 * 
	 * This routine aggregates RegistrationAddresses (4.02 or higher) into a RegistrationAddresStandardized
	 * Depending on the type of RegistrationAddress (e.g. ST - Street, WK - Quarter etc.) the correct field in
	 * RegistrationAddresStandardized is filled.   
	 * 
	 * 
	 */
	
	private void convertNewAddressLine(RegistrationAddress ra, RegistrationAddressStandardized ras){
		
		if(ra.getNameOfStreet().trim().equalsIgnoreCase("$Geen Adres$")){
			ras.setAddressFlag(3);  // indicator of 'no address'
			return;
		}

		
		//System.out.println("convertNewAddressLine! " + ra.getAddressType());
		
		if(ra.getAddressType().equalsIgnoreCase("ST") == true){
			
			if(ra.getNameOfStreet() != null && ra.getNameOfStreet().length() >= 7 && ra.getNameOfStreet().substring(0,7).equals("IJsselv")){
				//System.out.println("---> " + ra.getNameOfStreet());
				trace = true;
			}	
			
			ras.setStreet(ra.getNameOfStreet());
			ras.setNumber(ra.getNumber()); 
			ras.setAddition(ra.getAdditionToNumber()); 
			return;
			
		}
			
		if(ra.getAddressType().equalsIgnoreCase("WK") == true){
			
			ras.setQuarter(ra.getNameOfStreet());

			if(ras.getNumber() == null){
				ras.setNumber(ra.getNumber()); 
				ras.setAddition(ra.getAdditionToNumber()); 
			}
			return;
			
		}
			
		if(ra.getAddressType().equalsIgnoreCase("PL") == true){
			
			ras.setPlace(ra.getNameOfStreet());
			return;
			
		}
			
		if(ra.getAddressType().equalsIgnoreCase("BO") == true){
			
			ras.setBoat(ra.getNameOfStreet());
			return;
			
		}
			
		if(ra.getAddressType().equalsIgnoreCase("LP") == true){
			
			ras.setBerth(ra.getNameOfStreet());
			return;
			
		}
			
		if(ra.getAddressType().equalsIgnoreCase("GS") == true){
			
			ras.setInstitution(ra.getNameOfStreet());
			return;
			
		}
			
		if(ra.getAddressType().equalsIgnoreCase("KB") == true){
			
			ras.setLandlord(ra.getNameOfStreet());			
			return;
			
		}
			
		if(ra.getAddressType().equalsIgnoreCase("AN") == true){
			
			ras.setOther(ra.getNameOfStreet());			
			return;
			
		}
			
		
		
		
	}
	
	
	
	/**
	 * 
	 * This routine fixes the use of "idem" for religion
	 * Codes 2 and 3 not implemented yet
	 * 
	 */
	public void fixReligion(){

		String firstReligion = null;
		String secondReligion = null;
		int firstReligionDays = -1;
		int secondReligionDays = -1;
		int mutationDays = -1;

		for (Person p : getPersonsInRegistration()) {

			firstReligion = null;
			secondReligion = null;
			firstReligionDays = -1;
			secondReligionDays = -1;
			mutationDays = -1;

			for (PersonDynamic pd : p.getDynamicDataOfPerson()) {

				for (PersonDynamicStandardized pds : pd
						.getStandardizedPersonDynamic()) {

					if (pds instanceof nl.iisg.ids03.PDS_Religion) {

						PDS_Religion r = (PDS_Religion) pds;
						
						if (r.getDateOfMutationFlag() == 10
								|| r.getDateOfMutationFlag() == 20
								|| r.getDateOfMutationFlag() == 21
								|| r.getDateOfMutationFlag() == 22
								|| r.getDateOfMutationFlag() == 23)
							mutationDays = Utils.dayCount(r.getDateOfMutation2());
						
						if (r.getReligionStandardized() != null) {
							
							if (r.getReligionStandardized().equalsIgnoreCase("Idem") != true) {

								r.setReligionFlag(0);
								
								if (firstReligion == null) {
									firstReligion = r.getReligionStandardized();
									firstReligionDays = mutationDays;
								} 
								else {
									if (secondReligion == null) {
										secondReligion = r.getReligionStandardized();
										secondReligionDays = mutationDays;
									}
								}

							}
							else{
							
								if(firstReligion != null){									
									r.setReligionStandardized(firstReligion);
									if(secondReligion == null)
										r.setReligionFlag(1);
									else
										r.setReligionFlag(4);
									
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void add(Person p){
		personsInRegistration.add(p); 

	}
	public void add(RegistrationAddress ra){
		addressesOfRegistration.add(ra); 
	}
	
    /**
     * 
     * This routine prints out essential fields of the object
     * 
     */
	
	
	public void print(){
		
		showFields();
		System.out.println();
		
		for(Person p: personsInRegistration){
			p.print();
		}
		
		//System.out.println();
		//getStandardizedRegistration().print();
		
		for(RegistrationAddress ra: addressesOfRegistration)
			ra.print();
		
	}
	
	private void showFields(){
		
        // System.out.println("    Check Registration            " +
		//			" IDNR = " + getkeyToRP() +
		//			" B1IDBG = " + getKeyToSourceRegister() + 
		//			" B2DIBG* = " + getDayEntryHead() + " " + getMonthEntryHead() + " " + getYearEntryHead());

		String EntryDateHead = String.format("%02d-%02d-%04d", getDayEntryHead(), getMonthEntryHead(), getYearEntryHead());
		String EntryDateOP   = String.format("%02d-%02d-%04d", getDayEntryRP(), getMonthEntryRP(), getYearEntryRP());
		
		Ref_AINB ainb = Ref.getAINB(getKeyToSourceRegister());	
		String municipality = ainb.getMunicipality();
		int startYear = ainb.getStartYearRegister();
		int endYear = ainb.getEndYearRegister();
		String registerInfo = String.format("%s  %d-%d", municipality, startYear, endYear);
		
		
		System.out.println();
		System.out.println("" +
		  			"" + getKeyToRP() +
		  			"  " + EntryDateHead +
		  			"  " + getKeyToSourceRegister() +
		  			"  OP date: " + EntryDateOP +
		  			"  Register:  " + registerInfo +
		  			"  Page: " + getPageNumberOfSource().trim() +
		  			"  Line: " + getNumberOfHousehold() 		  			
		  			);
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
		
		m.setDayEntryRP(getDayEntryRP());
		m.setMonthEntryRP(getMonthEntryRP());
		m.setYearEntryRP(getYearEntryRP());
		
		
		m.save(fills); 
		
		
	}
	
	/**
	 * 
	 * This routine allocates a Message object and fills it with the parameters.
	 * Additional information is added to identify the B*-row(s) to which the message applies
	 * This routine accepts also a person number
	 * 
	 * @param number
	 * @param fills
	 */

	private void message(String number, int personNumber, String... fills){
		
		Message m = new Message(number);
		
		m.setKeyToRP(getKeyToRP());
		
		m.setKeyToSourceRegister(getKeyToSourceRegister());
		
		m.setDayEntryHead(getDayEntryHead());
		m.setMonthEntryHead(getMonthEntryHead());
		m.setYearEntryHead(getYearEntryHead());
		
		m.setDayEntryRP(getDayEntryRP());
		m.setMonthEntryRP(getMonthEntryRP());
		m.setYearEntryRP(getYearEntryRP());
		
		m.setKeyToRegistrationPersons(personNumber); 
		
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

	public int getDayEntryRP() {
		return dayEntryRP;
	}

	public void setDayEntryRP(int dayEntryRP) {
		this.dayEntryRP = dayEntryRP;
	}

	public int getMonthEntryRP() {
		return monthEntryRP;
	}

	public void setMonthEntryRP(int monthEntryRP) {
		this.monthEntryRP = monthEntryRP;
	}

	public int getYearEntryRP() {
		return yearEntryRP;
	}

	public void setYearEntryRP(int yearEntryRP) {
		this.yearEntryRP = yearEntryRP;
	}

	public String getPageNumberOfSource() {
		return pageNumberOfSource;
	}

	public void setPageNumberOfSource(String pageNumberOfSource) {
		this.pageNumberOfSource = pageNumberOfSource;
	}

	public int getNumberOfHousehold() {
		return numberOfHousehold;
	}

	public void setNumberOfHousehold(int numberOfHousehold) {
		this.numberOfHousehold = numberOfHousehold;
	}

	public String getInfoFamilyCardsSystem() {
		return infoFamilyCardsSystem;
	}

	public void setInfoFamilyCardsSystem(String infoFamilyCardsSystem) {
		this.infoFamilyCardsSystem = infoFamilyCardsSystem;
	}

	public String getSpecialDataEntryCodes() {
		return specialDataEntryCodes;
	}

	public void setSpecialDataEntryCodes(String specialDataEntryCodes) {
		this.specialDataEntryCodes = specialDataEntryCodes;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public ArrayList<Person> getPersonsInRegistration() {
		return personsInRegistration;
	}

	public void setPersonsInRegistration(ArrayList<Person> personsInRegistration) {
		this.personsInRegistration = personsInRegistration;
	}

	public ArrayList<RegistrationAddress> getAddressesOfRegistration() {
		return addressesOfRegistration;
	}

	public void setAddressesOfRegistration(ArrayList<RegistrationAddress> addressesOfRegistration) {
		this.addressesOfRegistration = addressesOfRegistration;
	}

	public OP getOp() {
		return op;
	}

	public void setOp(OP op) {
		this.op = op;
	}

	public RegistrationStandardized getStandardizedRegistration() {
		return standardizedRegistration;
	}

	public void setStandardizedRegistration(
			RegistrationStandardized standardizedRegistration) {
		this.standardizedRegistration = standardizedRegistration;
	}


    private int dateCompare(Registration lhs, Registration rhs){
        if(lhs.getYearEntryRP() < rhs.getYearEntryRP()){
            return -1;
        } else if (lhs.getYearEntryRP() > rhs.getYearEntryRP() ) {
            return 1;
        } else {     // ==
            if( lhs.getMonthEntryRP() < rhs.getMonthEntryRP() ){
                return -1;
            } else if (lhs.getMonthEntryRP() > rhs.getMonthEntryRP()){
                return 1;
            } else {
                if ( lhs.getDayEntryRP() < rhs.getDayEntryRP() ){
                    return -1;
                } else if ( lhs.getDayEntryRP() > rhs.getDayEntryRP() ){
                    return 1;
                } else {
                    return 0;
                }

            }
        }

    }

    // sorteert op Idnr. todo: sorteren op OPdatum als Idnr gelijk is.
    public int compareTo(Registration r) {
        if(keyToRP < r.getKeyToRP()){
            return -1;
        } else if (keyToRP > r.getKeyToRP()) {
            return 1;
        } else {       // keyToRP == r.getKeyToRP()
            //return 0;
            return dateCompare( this, r);
        }
    }
}