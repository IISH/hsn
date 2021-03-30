/*
* Naam:    OP (Ondezoekspersoon)
* Version: 0.1
* Author:  Cor Munnik
* Copyright
*/



package nl.iisg.ids03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nl.iisg.hsncommon.Common1;
import nl.iisg.hsncommon.ConstRelations2;
//import nl.iisg.ids05.Utils;
import nl.iisg.ref.Ref;
import nl.iisg.ref.Ref_AINB;

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
	
		
	//System.out.println("OP idnr = "+getKeyToRP());
	
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
	// vervallen 02-02-2021
	/*
	for(Registration r: getRegistrationsOfOP()){
		
		if(Common1.dateIsValid(r.getDayEntryHead(), r.getMonthEntryHead(), r.getYearEntryHead() ) == 0){	
			if(UniqueDateCounts.add(Utils.dayCount(r.getDayEntryHead(), r.getMonthEntryHead(), r.getYearEntryHead())) == false){					
				message("1057", "" + r.getDayEntryHead() + "-" + r.getMonthEntryHead() + "-" +r.getYearEntryHead());
			}
		}
	}
	*/
	
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

	// 1347 Aankomstdatum <datum> wellicht uit eerdere inschrijving?
	
	HashSet<Integer> allRegistrations    = new HashSet<Integer>();
	HashSet<Integer> currentRegistration = new HashSet<Integer>();
		
	for(Registration r: getRegistrationsOfOP()){
		currentRegistration.clear();
		for(Person p: r.getPersonsInRegistration()){
			for(PersonDynamic pd: p.getDynamicDataOfPerson()) {
				if(pd.getDynamicDataType() == ConstRelations2.AANKOMST) {
					
					int mutationDay   = pd.getDayOfMutationAfterInterpretation()   > 0 ? pd.getDayOfMutationAfterInterpretation()   : pd.getDayOfMutation();
					int mutationMonth = pd.getMonthOfMutationAfterInterpretation() > 0 ? pd.getMonthOfMutationAfterInterpretation() : pd.getMonthOfMutation();
					int mutationYear  = pd.getYearOfMutationAfterInterpretation()  > 0 ? pd.getYearOfMutationAfterInterpretation()  : pd.getYearOfMutation();
					
					if(Common1.dateIsValid(mutationDay, mutationMonth, mutationYear) == 0) {
						if(allRegistrations.add(Common1.dayCount(mutationDay, mutationMonth, mutationYear))) 
							currentRegistration.add(Common1.dayCount(mutationDay, mutationMonth, mutationYear)); // date is added in current registration
						else {                                                                                   // date cannot be added 
							if(!currentRegistration.contains(Common1.dayCount(mutationDay, mutationMonth, mutationYear)))  // if not added in current registration
								message("1347", pd.getKeyToSourceRegister(), pd.getDayEntryHead(),pd.getMonthEntryHead(), pd.getYearEntryHead(), pd.getKeyToRegistrationPersons(),
										mutationDay + "-" + mutationMonth + "-" + mutationYear);
						}
					}
				}
			}
		}
	}
	
	// Check 'GEEN OP' situatie 

	int cnt1 = 0;
	int cnt2 = 0;

	for(Registration r: getRegistrationsOfOP()){
		for(Person p: r.getPersonsInRegistration()) {	
			if(p.getNatureOfPerson() == ConstRelations2.FIRST_APPEARANCE_OF_OP)
				cnt2++;
			if(p.getFamilyName().trim().equalsIgnoreCase("GEEN OP")) {
				cnt1++;

				//System.out.println("SDDDS " + p.getFamilyName() + " " + p.getFamilyName().length());
				//break;		
			}
		}	
	}

	if(cnt1 > 0) { 
		message("4172", "" + cnt1);		
		if(Ref.getRP(getKeyToRP()) == null)
			message("4174");
		if(cnt2 > cnt1)
			message("4173");
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
 *  Bij identificeren    01-02-1900 en 01-03-1900   -> gelijk
                         01-02-1900 en 03-02-1900   -> gelijk
 *  maar                 01-03-1900 en 03-02-1900   -> niet direct gelijk, maar via vorige twee indirect gelijk  
 * 
 * 
 */



public void identify(){
	
	ArrayList<PersonStandardized> testedPersons = new ArrayList<PersonStandardized>();
	setPersonNumber(1);  // restart for each OP
	Map<Integer, Set<PersonStandardized>> m = new HashMap<Integer, Set<PersonStandardized>>();
	boolean m4135 = false;
	
	for(RegistrationStandardized r : getRegistrationsStandardizedOfOP()){
		for(PersonStandardized p: r.getPersonsStandardizedInRegistration()){
			for(PersonStandardized pu: testedPersons){
				
				if(p != pu && comparePersons(p, pu)){
					if(p.getPersonID() == 0) {
						p.setPersonID(pu.getPersonID());					
						m.get(pu.getPersonID()).add(p);
											
					}
					else {						
						if(pu.getKeyToPersons() != p.getPersonID())
							for(PersonStandardized px: m.get(pu.getPersonID())) {
								px.setPersonID(p.getPersonID());
								m.get(p.getPersonID()).add(px);
							}
					}
				}
			}
			if(p.getPersonID() == 0){
				Integer i = new Integer(getPersonNumber());
				p.setPersonID(i);
				Set<PersonStandardized> s = new HashSet<PersonStandardized>();
				m.put(i, s);
				s.add(p);
			}
			testedPersons.add(p);
			String name = p.getFamilyName();
			if(name.indexOf("@A") >= 0 || name.indexOf("@B") >= 0 || name.indexOf("#A") >= 0 || name.indexOf("#B") >= 0) {
				if(!m4135) {
					message("4135");
					m4135 = true;
					
				}
			}

		}
	}
	
	// Check that all b2fcbg = 1 and b2fcbg = 5 have the same person_ID (These are the OPs)
	
	int establishedPerson_ID = 0;
	for(RegistrationStandardized r : getRegistrationsStandardizedOfOP()){
		for(PersonStandardized p: r.getPersonsStandardizedInRegistration()){
			if((p.getNatureOfPerson() == ConstRelations2.FIRST_APPEARANCE_OF_OP)   ||
		       (p.getNatureOfPerson() == ConstRelations2.FURTHER_APPEARANCE_OF_OP)) {
				if(establishedPerson_ID == 0) establishedPerson_ID = p.getPersonID();
				else if(p.getPersonID() != establishedPerson_ID) message("4131");
					
			}
		}
	}
	
	// Dit betreft een melding die optreedt als -na identificatie- verschillende hoofden van een huishouden
	// sterk op elkaar lijken (drie elementen van de vijf hetzelfde zijn: gebdag, gebmnd, gebjaar, achternaam 
	// en eerste voornaam EN het geboortejaar niet meer dan 20 jaar van elkaar verschilt)
	
	
	ArrayList<String> birthDay = new ArrayList<String>();
	ArrayList<String> birthMonth = new ArrayList<String>();
	ArrayList<String> birthYear = new ArrayList<String>();
	ArrayList<String> firstName = new ArrayList<String>();
	ArrayList<String> lastName = new ArrayList<String>();
	ArrayList<Integer> personID = new ArrayList<Integer>();
	
	ArrayList[] a = new ArrayList[5];
	
	a[0] = birthDay;
	a[1] = birthMonth;
	a[2] = birthYear;
	a[3] = firstName;
	a[4] = lastName;
	
	for(RegistrationStandardized r : getRegistrationsStandardizedOfOP()){
		for(PersonStandardized p: r.getPersonsStandardizedInRegistration()){
			for(PersonDynamicStandardized pd: p.getDynamicDataOfPersonStandardized()) {
				if(pd.getKeyToDistinguishDynamicDataType() == ConstRelations2.RELATIE_TOT_HOOFD_ST){
					if(((PDS_RelationToHead)pd).getContentOfDynamicData() == ConstRelations2.HOOFD ||
				       (((PDS_RelationToHead)pd).getContentOfDynamicData() >= ConstRelations2.EXPLICIET_HOOFD_ALLENSTAAND ||
                        ((PDS_RelationToHead)pd).getContentOfDynamicData() <= ConstRelations2.IMPLICIET_HOOFD_GEEN_VERWANT)) {

						birthYear.add(p.getDateOfBirth().substring(6,10));
						birthMonth.add(p.getDateOfBirth().substring(3,5));
						birthDay.add(p.getDateOfBirth().substring(0,2));
						firstName.add(p.getFirstName().split(" ")[0]);
						lastName.add(p.getFamilyName());		
						
						personID.add(p.getPersonID());
						break;
					}
				}
			}
		}
	}
	
	Set<Integer> aa = new HashSet<Integer>();
	
	for(int i = 0; i < birthDay.size(); i++) {
		for(int j = i; j < birthDay.size(); j++) {
			
			int equals = 0;
			
			if(birthDay.get(i).equals(birthDay.get(j))) equals++;
			if(birthMonth.get(i).equals(birthMonth.get(j))) equals++;
			if(birthYear.get(i).equals(birthYear.get(j))) equals++;
			
			if((lastName.get(i).length() <= 5 && Common1.LevenshteinDistance(lastName.get(i), lastName.get(j)) <= 1) ||
		       (lastName.get(i).length() >  5 && Common1.LevenshteinDistance(lastName.get(i), lastName.get(j)) <= 2)) equals++; 

			if((firstName.get(i).length() <= 5 && Common1.LevenshteinDistance(firstName.get(i), firstName.get(j)) <= 1) ||
		       (firstName.get(i).length() >  5 && Common1.LevenshteinDistance(firstName.get(i), firstName.get(j)) <= 2)) equals++;
		
			if(equals >= 3 && Math.abs(Integer.parseInt(birthYear.get(i)) - Integer.parseInt(birthYear.get(j))) <= 20 &&
					personID.get(i) != personID.get(j))
				
				if(!aa.contains(personID.get(i) + 1000 * personID.get(j)) && !aa.contains(personID.get(j) + 1000 * personID.get(i))) {

					message("4151",
							firstName.get(i), lastName.get(i), birthDay.get(i) + "-" + birthMonth.get(i) +  "-" + birthYear.get(i),
							firstName.get(j), lastName.get(j), birthDay.get(j) + "-" + birthMonth.get(j) +  "-" + birthYear.get(j));

					aa.add(personID.get(i) + 1000 * personID.get(j));
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
	
	// G Check inconsistencies by way of birth dates
	
	HashSet<Integer> h0 = new HashSet<Integer>();
	
	for(RegistrationStandardized r: getRegistrationsStandardizedOfOP()){
		for(PersonStandardized ps: r.getPersonsStandardizedInRegistration()){
			if(h0.contains(ps.getPersonID())) continue;
			if(ps.getPersonID_MO() != 0){
			x:	for(RegistrationStandardized r1: getRegistrationsStandardizedOfOP()){
					for(PersonStandardized ps1: r1.getPersonsStandardizedInRegistration()){
						if(ps1.getPersonID() == ps.getPersonID_MO()){
							if(Utils.dateIsValid(ps.getDateOfBirth()) == 0  && Utils.dateIsValid(ps1.getDateOfDecease()) == 0) {
								if(Utils.dayCount(ps.getDateOfBirth()) - Utils.dayCount(ps1.getDateOfDecease()) > 9 * 30) {
									message("4153", "" + (Utils.dayCount(ps.getDateOfBirth()) - Utils.dayCount(ps1.getDateOfDecease())));
									h0.add(ps.getPersonID());
									break x;
								}
							}
						}
					}
				}
			}
		}
	}
	
	// H Check inconsistencies by way of identifiers (One person has different PersonIDs for father or mother in different registrations)
	
	HashSet<Integer> h = new HashSet<Integer>();	
	HashSet<Integer> f = new HashSet<Integer>(); // fathers
	HashSet<Integer> m = new HashSet<Integer>(); // mothers
	
	// Get all personnumbers in all registrations for OP
	
	for(RegistrationStandardized r: getRegistrationsStandardizedOfOP()){
		for(PersonStandardized p: r.getPersonsStandardizedInRegistration()){
			h.add(p.getPersonID());
		}
	}
	
	for(Integer id: h){
		
		f.clear();
		m.clear();
		String birthdate = "";
		for(RegistrationStandardized r: getRegistrationsStandardizedOfOP()){
			for(PersonStandardized p: r.getPersonsStandardizedInRegistration()){
				if(p.getPersonID() == id){
					birthdate = p.getDateOfBirth();
					m.add(p.getPersonID_MO());
					f.add(p.getPersonID_FA());
				}
			}
		}
		if(f.size() > 1) message("4154", birthdate);
		if(m.size() > 1) message("4155", birthdate);
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
	
	// Check for inconsistencies in relation to OP (over all registrations)
	
	class PersonStandardizedExt extends PersonStandardized {
		
		int relationToOP;

		public int getRelationToOP() {
			return relationToOP;
		}

		public void setRelationToOP(int relationToOP) {
			this.relationToOP = relationToOP;
		}

	}
	
	ArrayList<PersonStandardizedExt> ps = new ArrayList<PersonStandardizedExt>();
	
	for(RegistrationStandardized r : getRegistrationsStandardizedOfOP()) {
		
		int OPKey = 0;
		
		for(PersonStandardized p : r.getPersonsStandardizedInRegistration())
			if(p.getNatureOfPerson() == ConstRelations2.FIRST_APPEARANCE_OF_OP)
				OPKey = p.getKeyToPersons();
		
		for(PersonStandardized p : r.getPersonsStandardizedInRegistration()) {	
		
			PersonStandardizedExt pse = new PersonStandardizedExt();
			pse.setDateOfBirth(p.getDateOfBirth());
			pse.setPersonID(p.getPersonID());
			pse.setDynamicDataOfPersonStandardized(p.getDynamicDataOfPersonStandardized());
			
			for(PersonDynamicStandardized pds : p.getToAll()) {				
				if(((PDS_AllToAll)pds).getValueOfRelatedPerson() == OPKey) {
					pse.setRelationToOP(((PDS_AllToAll)pds).getContentOfDynamicData());
					
				}
			}
			
			if(p.getNatureOfPerson() == ConstRelations2.FIRST_APPEARANCE_OF_OP)
				pse.setRelationToOP(0); // this is a pseudo-relation, used to distinguish the RP
			
			ps.add(pse);
			
		}
			
	}
	
	//System.out.println("FFR " + "ps.size()  = " + ps.size());
	
	Collections.sort(ps ,new Comparator<PersonStandardizedExt>() {
		 public int compare(PersonStandardizedExt p1, PersonStandardizedExt p2) {
			 Integer p1id = Integer.valueOf((p1.getPersonID()));
			 Integer p2id = Integer.valueOf((p2.getPersonID()));
		  return p1id.compareTo(p2id);
		 }
	}); 
	
	Set<Integer> hh = new HashSet<Integer>();
	Set<Integer> ho = new HashSet<Integer>();
	
	PersonStandardized previousP = null; 
	
	for(PersonStandardizedExt p: ps) {
		if(previousP != null && p.getPersonID() != previousP.getPersonID()) {
			if(ho.size() > 1) {
				int prev = -1;
				for(Integer i: ho) { 
					if(prev >= 0 && i >= 0)
						message("4161", previousP.getDateOfBirth(),
								prev == 0 ? "OP" : ConstRelations2.b3kode1[prev], 
								i    == 0 ? "OP" : ConstRelations2.b3kode1[i]);
					prev = i;
				}
			}
			ho.clear();
			
			
  		}			
		ho.add(p.getRelationToOP());
		previousP = p;
		
		//for(PersonDynamicStandardized pds : p.getDynamicDataOfPersonStandardized())
		//	if(pds.getKeyToDistinguishDynamicDataType() == ConstRelations2.RELATIE_TOT_HOOFD_ST)
		//		hh.add(((PDS_RelationToHead)pds).getContentOfDynamicData());
		
		
		
		//System.out.println("FFR " + "hh.size() == " + hh.size());
	 }
	
	// Check for inconsistencies in relation to Head (per Registration)
	
   for(RegistrationStandardized r : getRegistrationsStandardizedOfOP()) {
	   
	   previousP = null;
		for(PersonStandardized p : r.getPersonsStandardizedInRegistration()) {
			
			if(previousP != null && p != previousP) {  
				
				if(hh.size() > 1) {
					int prev = -1;
					for(Integer i: hh) { 
						if(prev >= 0 && i >= 0)
							message("4162", previousP.getDateOfBirth(),
									 ConstRelations2.b3kode1[prev], 
									 ConstRelations2.b3kode1[i]);
						prev = i;
					}
				}
				hh.clear();				
			}
			
			for(PersonDynamicStandardized pds : p.getDynamicDataOfPersonStandardized())
					if(pds.getKeyToDistinguishDynamicDataType() == ConstRelations2.RELATIE_TOT_HOOFD_ST)
						hh.add(((PDS_RelationToHead)pds).getContentOfDynamicData());
			
			previousP = p;
		}
   }
	
	//Map m = new HashMap(PersonStandardized)
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

private boolean comparePersons(PersonStandardized ps, PersonStandardized pus){

	//
	// Test if different family name
	//
	boolean familyNameOK =	CheckFamilyName(ps, pus, false); // no message (yet)
	
	
	//
	// Test if different first firstname
	//
	
	boolean firstNameOK = CheckFirstName(ps, pus, false); // no message (yet)
	
	
	//
	// Test if different birth dates 
	//
	boolean birthDateOK = CheckBirthDate(ps, pus, false); // no message (yet)
	
	//
	// Test if different sex
	//
	
	boolean sexOK = (ps.getSex().equalsIgnoreCase("m") &&  pus.getSex().equalsIgnoreCase("m")) ||
			      (ps.getSex().equalsIgnoreCase("v") &&  pus.getSex().equalsIgnoreCase("v"));
	
	// If every test is OK, we do the again to get the messages
	// This way we only get messages when we know the persons wil be linked (are the same)
	
	if(familyNameOK && firstNameOK && birthDateOK  && sexOK) {
		CheckFamilyName(ps, pus, true);
		CheckFirstName(ps, pus, true);
		CheckBirthDate(ps, pus, true);

	}

	if (!familyNameOK && firstNameOK  && birthDateOK  && sexOK ) {
        message("4121", ps.getFamilyName(), pus.getFamilyName());
        return false;
    }

    if (familyNameOK  && firstNameOK  && birthDateOK == true && !sexOK ) {
       message("4122", new Integer(pus.getKeyToPersons()).toString());
        return false;
    }
    
	if (familyNameOK && !firstNameOK  && birthDateOK  && sexOK  ) {
        message("4123", ps.getFirstName(), pus.getFirstName());
        return false;
    }

    if (familyNameOK && firstNameOK && !birthDateOK  && sexOK ) {
        message("4126", ps.getFamilyName(), ps.getFirstName(), ps.getDateOfBirth(), pus.getDateOfBirth());
        return false;
    }

    if (!familyNameOK && !firstNameOK && birthDateOK  && sexOK ) {
        message("4134", ps.getFamilyName(), ps.getFirstName(), pus.getFamilyName(), pus.getFirstName(),ps.getDateOfBirth());
        return false;
    }


    
    if (familyNameOK && firstNameOK && birthDateOK  && sexOK ) {
    	
    	boolean psIsOP = (ps.getNatureOfPerson() == ConstRelations2.FIRST_APPEARANCE_OF_OP)   || (ps.getNatureOfPerson() == ConstRelations2.FURTHER_APPEARANCE_OF_OP);
    	boolean pusIsOP = (pus.getNatureOfPerson() == ConstRelations2.FIRST_APPEARANCE_OF_OP) || (pus.getNatureOfPerson() == ConstRelations2.FURTHER_APPEARANCE_OF_OP);


    	if((psIsOP && !pusIsOP) || (!psIsOP && pusIsOP))
    		message("4132");

    }

    
	return (familyNameOK && firstNameOK && birthDateOK  && sexOK );
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
private boolean CheckBirthDate(PersonStandardized ps, PersonStandardized pus, boolean giveMessage){
	

	String date1 = ps.getDateOfBirth();
	int day1   = (new Integer(date1.split("-")[0])).intValue();
	int month1 = (new Integer(date1.split("-")[1])).intValue();
	int year1  = (new Integer(date1.split("-")[2])).intValue();
	
	String date2 = pus.getDateOfBirth();
	int day2   = (new Integer(date2.split("-")[0])).intValue();
	int month2 = (new Integer(date2.split("-")[1])).intValue();
	int year2  = (new Integer(date2.split("-")[2])).intValue();
	
	String id1 = ps.getKeyToRP() + "-" + ps.getKeyToSourceRegister() + "-" + ps.getEntryDateHead() +  "-" + ps.getKeyToPersons();
	String id2 = pus.getKeyToRP() + "-" + pus.getKeyToSourceRegister() + "-" + pus.getEntryDateHead() +  "-" + pus.getKeyToPersons();
	
	id1 = id1 + " " + ps.getFirstName() + " " + ps.getFamilyName() + " (" + ps.getDateOfBirth() + ")";
	id2 = id2 + " " + pus.getFirstName() + " " + pus.getFamilyName() + " (" + pus.getDateOfBirth() + ")";
	
	String id = " " + id1 + " vs " + id2;

	
	if(date1.equals("00-00-0000") == true || date2.equals("00-00-0000") == true) // invalid dates
		return false;

	if(year1 == 0 || year2 == 0)
		return false;

	if(day1 > 0 && month1 > 0 && day2 > 0 && month2 > 0){

		if(Math.abs(day1 - day2) > 1) { // days differ significantly
			if(Math.abs(month1 - month2) != 0 || Math.abs(year1 - year2) != 0)
				return false;	
		}

		if(Math.abs(month1 - month2) != 0) { // months differ 
			if(Math.abs(day1 - day2) > 1 || Math.abs(year1 - year2) != 0)
				return false;
		}
		
		if(Math.abs(year1 - year2) != 0) {// years differ
			
			if((Math.abs(year1 - year2)) % 10 == 0) {

				if(Math.abs(day1 - day2) > 1 || Math.abs(month1 - month2) != 0) {
					return false;
				}
			}
			else {
				
				if(date1.substring(0, date1.length() - 1).equals(date2.substring(0, date2.length() - 1))) // only last digits differ
					if(giveMessage)
						message("4009", (new Integer(year1).toString()), (new Integer(year2).toString()), id);

				return false;
			}
		}
		
	}
	else
		if(year1 > 1700 && year1 == year2) {
			if(giveMessage)
				message("4125", (new Integer(year1).toString()), (new Integer(year2).toString()), id); 
			 return true;			
		}
		else return false;

	if(day1 != day2)
		if(giveMessage)
			message("4006", (new Integer(day1).toString()), (new Integer(day2).toString()), id); 
	if(month1 != month2)
		if(giveMessage)
			message("4007", (new Integer(month1).toString()), (new Integer(month2).toString()), id); 
	if(year1 != year2) {		
		if(Math.abs(year1 - year2) % 100 == 0) {
			if(giveMessage)
				message("4012", (new Integer(year1).toString()), (new Integer(year2).toString()), id); 
		}
		else {
			if(Math.abs(year1 - year2) % 10 == 0)
				if(giveMessage)
					message("4010", (new Integer(year1).toString()), (new Integer(year2).toString()), id); 
				else {		
					if(giveMessage)
						message("4008", (new Integer(year1).toString()), (new Integer(year2).toString()), id); 
				}
		}
	}
	
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
private boolean CheckFamilyName(PersonStandardized ps, PersonStandardized pus, boolean giveMessage){
	
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
		if(giveMessage)
			message("4001", ps.getFamilyName(),pus.getFamilyName());
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
private boolean CheckFirstName(PersonStandardized ps, PersonStandardized pus, boolean giveMessage){
	
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
		if(giveMessage)
			message("4003", ps.getFirstName(),pus.getFirstName()); 
	
	// We willen een melding als een Dirk Jan wordt gelinkt aan een Dirk, of een ‘Delphina Magdalena” gelijkgesteld wordt aan ‘Delphina Magdalena Johanna’, of Kees Jan aan Kees Dirk
	// Maar niet als Henk Jan gelijk wordt gesteld aan Henk Jan, ook al is alleen de eerste naam gebruikt voor linking
	
	String[] n1 = {"","","","","","",""};
	String[] n2 = {"","","","","","",""};
	
	//System.out.println("AAASX " + ps.getFirstName());
	
	for(int i = 0; i < ps.getFirstName().split(" ").length; i++)
		n1[i] = ps.getFirstName().split(" ")[i];
	
	for(int i = 0; i < pus.getFirstName().split(" ").length; i++)
		n2[i] = pus.getFirstName().split(" ")[i];
	
	boolean b4005 = false;
	for(int i = 1; i < n1.length; i++)
		if(!n1[i].equalsIgnoreCase(n2[i]))
			b4005 = true;

	if(b4005 && giveMessage)
		//message("4005");  // only 1 firstname used
	    message("4005", ps.getFirstName() + " versus " + pus.getFirstName());  // only 1 firstname used

	return true;
}

private void message(String number, String... fills){
	
	Message m = new Message(number); 
	
	m.setKeyToRP(getKeyToRP());
	m.save(fills); 
}

private void message(String number, int b1idbg, int b2dibg, int b2mibg, int b2jibg, int b2rnbg, String... fills){
	
	Message m = new Message(number);
	
	m.setKeyToRP(getKeyToRP());
	m.setKeyToSourceRegister(b1idbg);
	m.setDayEntryHead(b2dibg);
	m.setMonthEntryHead(b2mibg);
	m.setYearEntryHead(b2jibg);
	m.setKeyToRegistrationPersons(b2rnbg);
	m.save(fills); 
}

private void message(String number, int b1idbg, String b2dibg, int b2rnbg, String... fills){
	
	Message m = new Message(number);
	
	m.setKeyToRP(getKeyToRP());
	m.setKeyToSourceRegister(b1idbg);
	m.setDayEntryHead(Integer.parseInt(b2dibg.substring(0,2)));
	m.setMonthEntryHead(Integer.parseInt(b2dibg.substring(3,5)));
	m.setYearEntryHead(Integer.parseInt(b2dibg.substring(6,10)));
	m.setKeyToRegistrationPersons(b2rnbg);
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
