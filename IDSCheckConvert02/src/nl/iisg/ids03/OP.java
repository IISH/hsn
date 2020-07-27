/*
* Naam:    OP (Ondezoekspersoon)
* Version: 0.1
* Author:  Cor Munnik
* Copyright
*/



package nl.iisg.ids03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import nl.iisg.hsncommon.Common1;
import nl.iisg.hsncommon.ConstRelations2;

/**
 * 
 * This class is used for OP
 *
 */
public class OP {
	      private int                                     keyToRP;
	      private ArrayList<Registration>                 RegistrationsOfOP             = new ArrayList<Registration>();
	  	  private ArrayList<RegistrationStandardized>     RegistrationsStandardizedOfOP = new ArrayList<RegistrationStandardized>();
	  	  
	  	  static int PersonNumber = 1;
	  	  
/**
* 
* @param 
* 
* This method performs checks on OP. 
* The following message numbers can be issued:
* 
* 1006
* 1057
* 1111
*  
*/	  	  
	  	  
public boolean check(){
	
		
	System.out.println("OP idnr = "+getKeyToRP());
	
	// Following give IDNRs terminating errors
	//if(getKeyToRP() == 76081 || getKeyToRP() == 76059 || getKeyToRP() == 70796) return true;
	
	boolean returnCode = true;
    
	
	for(Registration r: getRegistrationsOfOP()){
		
		
		boolean rc = r.check();
		//System.out.println("  Op.check, re = " + rc);
		if(rc == false)
			returnCode = false;
	}
	
	// Check if the same OP date is not used more than once (in different households) New implementation
	
	HashSet<Integer> UniqueDateCounts = new HashSet<Integer>();

	for(Registration r : getRegistrationsOfOP()){
		if(Common1.dateIsValid(r.getDayEntryRP(), r.getMonthEntryRP(), r.getYearEntryRP() ) == 0){		
			if(UniqueDateCounts.add(Utils.dayCount(r.getDayEntryRP(), r.getMonthEntryRP(), r.getYearEntryRP())) == false){
				message("1006", "" + r.getDayEntryRP() + "-" + r.getMonthEntryRP() + "-" + r.getYearEntryRP());
				returnCode = false;
			}
		}
	}




	
	
	/*
	// Check if the same OP date is not used more than once (in different households)
	
	int [] t = new int[100];  // never more than 100 registrations for 1 OP
	int index = 0;
	
	
	// put dates (in days since 01-01-1600) in array t
	
	for(Registration r : getRegistrationsOfOP()){
		
		if(Utils.dateIsValid(r.getDayEntryRP(), r.getMonthEntryRP(), r.getYearEntryRP()) == 0){
			t[index] = Utils.dayCount(r.getDayEntryRP(), r.getMonthEntryRP(), r.getYearEntryRP());
			index++;
		}
	}
	
	
	if(index > 1){

		int [] tt = new int[index];
		for(int i = 0; i < tt.length; i++)
			tt[i] = t[i];

		Arrays.sort(tt);  // sort array 

		// look for duplicate value 

		int j = 0;
		boolean dub = false;

		for(int i = 0; i < tt.length; i++){

			if(tt[i] == j){  // duplicate
				
				dub = true;
				break; 
			}
			else 
				j = tt[i];

		}


		// find the date

		if(dub == true){
			for(Registration r : getRegistrationsOfOP()){
				if(Utils.dateIsValid(r.getDayEntryRP(), r.getMonthEntryRP(), r.getYearEntryRP() ) == 0){		
					if(Utils.dayCount(r.getDayEntryRP(), r.getMonthEntryRP(), r.getYearEntryRP()) == j){
						message("1006", "" + r.getDayEntryRP() + "-" + r.getMonthEntryRP() + "-" + r.getYearEntryRP());
						returnCode = false;
						break;
					}
				}
			}
		}
	}
	
	*/
	
	
	// Check if date head of household used more than once for this OP in different registrations

	UniqueDateCounts.clear();	
	/*
	for(Registration r: getRegistrationsOfOP()){
		for(Person p: r.getPersonsInRegistration()){
			if(p.getIsHead() == true){
				if(Common1.dateIsValid(r.getDayEntryHead(), r.getMonthEntryHead(), r.getYearEntryHead() ) == 0){	
					if(UniqueDateCounts.add(Utils.dayCount(r.getDayEntryHead(), r.getMonthEntryHead(), r.getYearEntryHead())) == false){					
						message("1057", "" + p.getDayOfBirth() + "-" + p.getMonthOfBirth() + "-" +p.getYearOfBirth());
					}
				}
				break;  // because we want to check for *different* registrations
			}
		}
	}
	*/
	
	// Corona Thuis Test op B4 ipv B2 en gebruik de Hoofddatum in de message
	for(Registration r: getRegistrationsOfOP()){
		
		if(Common1.dateIsValid(r.getDayEntryHead(), r.getMonthEntryHead(), r.getYearEntryHead() ) == 0){	
			if(UniqueDateCounts.add(Utils.dayCount(r.getDayEntryHead(), r.getMonthEntryHead(), r.getYearEntryHead())) == false){					
				message("1057", "" + r.getDayEntryHead() + "-" + r.getMonthEntryHead() + "-" +r.getYearEntryHead());
			}
		}
	}
	
	
	// Check if page number and line number registration used more than once
	
	//HashSet<Registration> hashSetRegistrations = new HashSet<Registration>();
	HashSet<Integer> hashSetRegistrations = new HashSet<Integer>();
	
	for(Registration r: getRegistrationsOfOP()) {
		try {
			int i = Integer.valueOf(r.getPageNumberOfSource().trim());
			i = (10000 * i) + r.getNumberOfHousehold();
			//System.out.println( "AABB + adding: " + i);
			if(hashSetRegistrations.add(i) != true) 
				message("1111", r.getPageNumberOfSource(), "" + r.getNumberOfHousehold()); 
		}
		catch (Exception e) {
			//System.out.println( "AABB + exception: ");
		}
	}


	//System.out.println("  Op.check, returnCode = " + returnCode);
	return returnCode;
	
}
	  	

public void convert(){
	
	
	// Following give IDNRs terminating errors in Check
	//if(getKeyToRP() == 76081 || getKeyToRP() == 76059 || getKeyToRP() == 70796) return;
	
	for(Registration r : getRegistrationsOfOP()){
		//showFields();
		r.Convert();
	}
}


/**
 * 
 * 
 *  This routine loops through all persons that appear in the registrations for this OP
 *  Persons that are thought to be the same are given the same person number
 * 
 * 
 */



public void identify(){
	
	ArrayList<PersonStandardized> uniquePersons = new ArrayList<PersonStandardized>();
	setPersonNumber(1);  // restart for each OP
	
	for(RegistrationStandardized r : getRegistrationsStandardizedOfOP()){
		
		for(PersonStandardized p: r.getPersonsStandardizedInRegistration()){
			
			boolean found = false;
			for(PersonStandardized pu: uniquePersons){
				
				if(comparePersons(p, pu) == 0){
					p.setPersonID(pu.getPersonID());
					found = true;
					break;
				}
			}
			if(found == false){
				p.setPersonID(getPersonNumber());
				uniquePersons.add(p);
			}
		}
	}
}

/**
 * 
 *  This routine gives a start and an end date (including flags) 
 *  to all persons (thus indicating the period(s) of validity) in this OP's set of registrations
 *  Also for Dynamic Data Elements
 * 
 */

public void giveDate(){
	
	for(RegistrationStandardized rs : getRegistrationsStandardizedOfOP()){

		rs.CreateEqualArrivalGroups();
		rs.CreateEqualDepartureGroups();
		
		ArrayList<PersonStandardized> a = new ArrayList<PersonStandardized>();
		
		for(PersonStandardized ps: rs.getPersonsStandardizedInRegistration()){
			ArrayList<PersonStandardized> b = ps.giveStartDate1();
			if(b != null)
				a.addAll(b);
		}
		
		rs.getPersonsStandardizedInRegistration().addAll(a);
		
		handleArrivalGroups(rs);
		
		rs.MoveLaterDatesAfterEarlierDates();
		rs. renumber();
	}
		
	for(RegistrationStandardized rs : getRegistrationsStandardizedOfOP()){
		
		for(PersonStandardized ps: rs.getPersonsStandardizedInRegistration())
			ps.giveEndDate1();
	}
	
	for(RegistrationStandardized rs : getRegistrationsStandardizedOfOP())
		rs.setEndOfRegisterDate();
	for(RegistrationStandardized rs : getRegistrationsStandardizedOfOP())
		rs.handleUndatedDeparture();

	for(RegistrationStandardized rs : getRegistrationsStandardizedOfOP()){

		for(PersonStandardized ps: rs.getPersonsStandardizedInRegistration())
			ps.giveStartDate2();
			
		
        rs.minMax();
        
        
		for(PersonStandardized ps: rs.getPersonsStandardizedInRegistration())
			ps.giveEndDate2();
		
	}
	
	// date the dynamic data elements
	
	for(RegistrationStandardized rs : getRegistrationsStandardizedOfOP())
		rs.dateDynamicElements();
			
	// build marriage structure
	
	for(RegistrationStandardized rs : getRegistrationsStandardizedOfOP())
		rs.buildMarriageStructure();			

	// find father and mother
	
	for(RegistrationStandardized rs : getRegistrationsStandardizedOfOP())
		rs.findFatherAndMother();	
	
	
	// E propagate parent information
	
	for(RegistrationStandardized rs : getRegistrationsStandardizedOfOP()){
		for(PersonStandardized ps: rs.getPersonsStandardizedInRegistration()){
			if(ps.getPersonID_FA() == 0){
				loop1:
				for(RegistrationStandardized rs1 : getRegistrationsStandardizedOfOP()){
					for(PersonStandardized ps1: rs1.getPersonsStandardizedInRegistration()){
						if(ps.getPersonID() == ps1.getPersonID() && ps1.getPersonID_FA() != 0){
							ps.setPersonID_FA(ps1.getPersonID_FA());
							ps.setPersonID_FA_FG(45);
							break loop1;
						}
					}
				}
			}
			if(ps.getPersonID_MO() == 0){
				loop2:
				for(RegistrationStandardized rs1 : getRegistrationsStandardizedOfOP()){
					for(PersonStandardized ps1: rs1.getPersonsStandardizedInRegistration()){
						if(ps.getPersonID() == ps1.getPersonID() && ps1.getPersonID_MO() != 0){
							ps.setPersonID_MO(ps1.getPersonID_MO());
							ps.setPersonID_MO_FG(45);
							break loop2;
						}
					}
				}
			}
		}
	}
	
	// find father and mother (2)
	
	for(RegistrationStandardized rs : getRegistrationsStandardizedOfOP())
		rs.findFatherAndMother2();	
	
	// H Check inconsistencies by way of identifiers (One person has different PersonIDs for father or mother in different registrations)
	
	HashSet<Integer> h = new HashSet<Integer>();	
	
	// Get all personnumbers in all registrations for OP
	
	for(RegistrationStandardized r: getRegistrationsStandardizedOfOP()){
		for(PersonStandardized p: r.getPersonsStandardizedInRegistration()){
			h.add(p.getPersonID());
		}
	}
	
	for(Integer id: h){
		
		int id_fa = -1;
		int id_mo = -1;
		for(RegistrationStandardized r: getRegistrationsStandardizedOfOP()){
			for(PersonStandardized p: r.getPersonsStandardizedInRegistration()){
				if(p.getPersonID() == id){
					if(id_fa == -1)
						id_fa = p.getPersonID_FA();
					else
						if(id_fa != p.getPersonID_FA()){
							message("4154", p.getDateOfBirth());					
						}
					if(id_mo == -1)
						id_mo = p.getPersonID_MO();
					else
						if(id_mo != p.getPersonID_MO()){
							message("4155", p.getDateOfBirth());
						}
				}
			}
		}
	}
	
	// Construct implicit Heads

	for(RegistrationStandardized rs : getRegistrationsStandardizedOfOP())
		rs.implicitHeads();	

	// renumber
	
	for(RegistrationStandardized rs : getRegistrationsStandardizedOfOP()){

		rs. renumber();
		
	}

	
}

/**
 * 
 * This routine inserts a PersonStandardized object at the chronologically right place (according to startDate) in
 * RegistrationStandardized.personsStandardizedInRegistration
 * 
 * @param ps
 */

private void insertPersonStandardized(PersonStandardized ps, ArrayList<PersonStandardized> personsStandardizedInRegistration){
	
	int dayCount = Utils.dayCount(ps.getStartDate());
	int index = 0;
	int insertIndex = 0;
	
	for(PersonStandardized ps1: personsStandardizedInRegistration){
		
		int dayCount1 = Utils.dayCount(ps1.getStartDate());
		if(dayCount <= dayCount1)
			insertIndex = index;

		index++;
		
	}
	
	personsStandardizedInRegistration.add(insertIndex, ps);
	
}


/**
 * 
 * This routine looks at all arrival groups
 * For each group it:
 *           gets the start date of the first person in the group
 *           gives this start date to all other group members that
 *           still have startFlag = 1 
 * 
 * 
 * @param rs
 */

public void handleArrivalGroups(RegistrationStandardized rs){

	for(int i = 1; i <= rs.getPersonsStandardizedInRegistration().size(); i++){  // there can never be more than this number of groups

		boolean first = true;
		String startDate = null;
		int startEst = 0;

		for(PersonStandardized ps: rs.getPersonsStandardizedInRegistration()){

			for(PersonDynamicStandardized pds: ps.getDynamicDataOfPersonStandardized()){
				if(pds.getKeyToDistinguishDynamicDataType() == ConstRelations2.AANKOMST){
					PDS_PlaceOfOrigin pda = (PDS_PlaceOfOrigin) pds;
					if(pda.getOriginGroup() == i){					
						if(first == true){
							first = false;
							if(ps.getStartFlag() > 1 || (ps.getStartFlag() == 1 && ps.getKeyToPersons() == 1)){
								startDate = ps.getStartDate();
							    startEst = ps.getStartEst();
							}
							else
								break;
						}
						else{
							if(ps.getStartFlag() == 1 && startDate != null)
								ps.setStartDate(startDate);
							    ps.setStartFlag(9);
							    ps.setStartEst(startEst);
						}
					}
				}
			}
		}
	}
}




public void relateAllToAll(){
	
	for(RegistrationStandardized r : getRegistrationsStandardizedOfOP()){
		r.relateAllToAll();
	}
}




public void truncate(){
	
	for(RegistrationStandardized r : getRegistrationsStandardizedOfOP()){
		r.truncate();
	}
}

public void write(){
	
	for(RegistrationStandardized r : getRegistrationsStandardizedOfOP()){
		r.write();
	}
}

public void print(){

	for(Registration r : getRegistrationsOfOP()){
		r.print();
	}

	System.out.println("====================STANDARDIZED==========================================================================");

	
	for(RegistrationStandardized rs: getRegistrationsStandardizedOfOP()){
		
		rs.print();
		
		System.out.println("====================MARRIAGE TABLE==========================================================================");
		
		for(Marriages m : rs.getMarriagesHead())
			m.print(); 
		
	}
	
	
	
}

OP(int RP){
	setKeyToRP(RP);
}
	  	  
public boolean contains(Registration r){
	if(r.getKeyToRP() == getKeyToRP())
		return true;
	else
		return false;
}

private void showFields(){
	
//	System.out.println("Check OP                          " +
//	           " IDNR = " + keyToRP());
	
	System.out.println();
	System.out.println("" + getKeyToRP());


}

/**
 * 
 * This routine compares two persons to see if they are in fact the same person
 * 
 * @return
 */

private int comparePersons(PersonStandardized ps, PersonStandardized pus){

	//
	// Test if different family name
	//
	boolean familyNameOK =	CheckFamilyName(ps, pus); 
	
	//
	// Test if different first firstname
	//
	
	boolean firstNameOK = CheckFirstName(ps, pus); 
	
	//
	// Test if different birth dates 
	//
	boolean birthDateOK = CheckBirthDate(ps, pus); 
		
	//
	// Test if different sex
	//
	
	boolean sexOK = true;
	if((ps.getSex().equals("m") == true && pus.getSex().equals("v") == true) || (ps.getSex().equals("v") == true && pus.getSex().equals("m") == true))
		sexOK = false;
	
	// If one test not ok, return false
	
	
	if(familyNameOK != true || firstNameOK != true || birthDateOK != true || sexOK != true)
		return -1;
	
	return 0;
}
/**
 * 
 * This routine checks if the birth dates match
 * The following message numbers can be issued:
 *  
 * 4106
 * 4107
 * 4108
 * 
 * @param ps
 * @param pus
 * @return
 */
private boolean CheckBirthDate(PersonStandardized ps, PersonStandardized pus){
	

	String date1 = ps.getDateOfBirth();
	int day1   = (new Integer(date1.split("-")[0])).intValue();
	int month1 = (new Integer(date1.split("-")[1])).intValue();
	int year1  = (new Integer(date1.split("-")[2])).intValue();
	
	String date2 = pus.getDateOfBirth();
	int day2   = (new Integer(date2.split("-")[0])).intValue();
	int month2 = (new Integer(date2.split("-")[1])).intValue();
	int year2  = (new Integer(date2.split("-")[2])).intValue();
	

	if(date1.equals("00-00-0000") == true || date2.equals("00-00-0000") == true) // invalid dates
		return false;

	if(year1 == 0 || year2 == 0)
		return false;

	if(day1 != 0 && month1 != 0 && day2 != 0 && month2 != 0){

		if(Math.abs(day1 - day2) > 1) // days differ significantly
			if(Math.abs(month1 - month2) != 0 || Math.abs(year1 - year2) != 0)
				return false;	

		if(Math.abs(month1 - month2) != 0) // months differ 
			if(Math.abs(day1 - day2) > 1 || Math.abs(year1 - year2) != 0)
				return false;

		if(Math.abs(year1 - year2) != 0){ // years differ

			if(Math.abs(day1 - day2) > 1 || Math.abs(month1 - month2) != 0)
				return false;
			else{
				if(date1.substring(6,8).equals(date2.substring(6,8))){ // same century

					if(Math.abs(year1 - year2) <= 2 || 
							(date1.substring(6,7).equals(date2.substring(6,7)) &&
									date1.substring(8,9).equals(date2.substring(8,9)) &&
									date1.substring(9,10).equals(date2.substring(9,10))))
						; // ok
					else
						return false;
				}
				else
					return false;
			}
		}	
	}
	
	if(day1 != day2)
		 message("4006", (new Integer(day1).toString()), (new Integer(day2).toString())); 
	if(month1 != month2)
		 message("4007", (new Integer(month1).toString()), (new Integer(month2).toString())); 
	if(year1 != year2)
		 message("4008", (new Integer(year1).toString()), (new Integer(year2).toString())); 
	
	return true;
}


/**
 * 
 * This routine checks if the family names match
 * The following message numbers can be issued:
 *  
 * 3101
 * 
 * @param ps
 * @param pus
 * @return
 */
private boolean CheckFamilyName(PersonStandardized ps, PersonStandardized pus){
	
	if(ps.getFamilyName() == null || pus.getFamilyName() == null)
		return false;

	String name1 = ps.getFamilyName().toLowerCase().trim(); 
	String name2 = pus.getFamilyName().toLowerCase().trim(); 


	if(name1 != null && name2 != null){

		if(name1.length() > 0 && name2.length() > 0 && name1.charAt(0) != name2.charAt(0))  // first character different		
			return false;

		// replacements are applied before comparing the names

		/*
		name1 = name1.replaceAll("y", "ij");
		name2 = name2.replaceAll("y", "ij");
		
		name1 = name1.replaceAll("ie", "ij");
		name2 = name2.replaceAll("ie", "ij");

		name1 = name1.replaceAll("egt", "echt");
		name2 = name2.replaceAll("egt", "echt");

		name1 = name1.replaceAll("uys", "ist");
		name2 = name2.replaceAll("uys", "ist");

		 */
		
		int distance = Common1.LevenshteinDistance(name1, name2);

		if(distance > 2)  // greater than 2 not allowed
			return false;

		if(distance == 2 && name1.length() <= 5 && name2.length() <= 5)  // distance = 2 is allowed, but not for small strings
			return false;

	}
	else
		return false;
	
	
	if(!name1.equals(name2)){
		 message("3101", ps.getFamilyName(),pus.getFamilyName());
	}
		
	
	return true;

}

/**
 * 
 * This routine checks if the first names match
 * The following message numbers can be issued:
 *  
 * 3103
 * 
 * @param ps
 * @param pus
 * @return
 */
private boolean CheckFirstName(PersonStandardized ps, PersonStandardized pus){
	
	if(ps.getFirstName() == null || pus.getFirstName() == null)
		return false;
	
	String name1 = ps.getFirstName().split(" ")[0].toLowerCase().trim();
	String name2 = pus.getFirstName().split(" ")[0].toLowerCase().trim();

	if(name1 != null && name2 != null){
		if(name1.length() > 0 && name2.length() > 0 && name1.charAt(0) != name2.charAt(0))  // first character different		
			return false;

		// remove some suffixes

		String [] suffixes = {"nus", "nis", "nes", "la", "is", "es", "us", "er", "nie", "nij"}; 

		if(name1.length() > 5)
			Utils.removeSuffixes(name1, suffixes);	

		if(name2.length() > 5)
			Utils.removeSuffixes(name2, suffixes);	

		int distance = Common1.LevenshteinDistance(name1, name2);	

		if(distance > 2)  // greater than 2 not allowed
			return false;

		if(distance == 2 && name1.length() <= 5 && name2.length() <= 5)  // distance = 2 is allowed, but not for small strings
			return false;
	}
	else
		return false;
	
	if(!name1.equals(name2))
		 message("3103", ps.getFirstName(),pus.getFirstName()); 

	
	return true;
}

private void message(String number, String... fills){
	
	Message m = new Message(number);
	
	m.setKeyToRP(getKeyToRP());
	m.save(fills); 
}




public void add(Registration r){
	RegistrationsOfOP.add(r); 
}
	  	  

public int getKeyToRP() {
	return keyToRP;
}


public void setKeyToRP(int keyToRP) {
	this.keyToRP = keyToRP;
}


public ArrayList<Registration> getRegistrationsOfOP() {
	return RegistrationsOfOP;
}
public void setRegistrationsOfOP(ArrayList<Registration> registrationsOfOP) {
	RegistrationsOfOP = registrationsOfOP;
}


public static int getPersonNumber() {
	return PersonNumber++;
}


public static void setPersonNumber(int personNumber) {
	PersonNumber = personNumber;
}


public ArrayList<RegistrationStandardized> getRegistrationsStandardizedOfOP() {
	return RegistrationsStandardizedOfOP;
}


public void setRegistrationsStandardizedOfOP(
		ArrayList<RegistrationStandardized> registrationsStandardizedOfOP) {
	RegistrationsStandardizedOfOP = registrationsStandardizedOfOP;
}

	  	  
	  	  
}
