/*
* Naam:    CheckKeys
* Version: 0.1
* Author:  Cor Munnik
* Copyright
*/
package nl.iisg.ids03;

/**
 * 
 * This class handles the validation of relations between the Person, PersonDynamic, Registration and RegistrationAddress objects
 * It also performs some checks on completeness of administrative fields in the objects
 *
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.Id;

import nl.iisg.ref.*;


/**
 * 
 * This routine performs consistency checks on the B* tables
 * 
 *
 */
public class KeyChecks {

	public boolean CheckKeys(List<Person> persons, List<PersonDynamic> personsDynamic, List<Registration> registrations, List<RegistrationAddress> registrationAddresses){

		boolean keysOK = true;
		boolean found = false;
		int     count = 0;
		int   	keyToSourceRegister       = 0;      
		int     dayEntryHeadOfHousehold   = 0;
		int     monthEntryHeadOfHousehold = 0; 
		int     yearEntryHeadOfHousehold  = 0; 
		int     keyToHSNResearchPerson    = 0;   

		// Check that B2 keys are in B4

		System.out.println("Checking Keys...");
		System.out.println("Check that B2 keys are in B4");
		
		for(Person p: persons){
			
			Registration r = new Registration();
			r.setKeyToSourceRegister(p.getKeyToSourceRegister());
			r.setDayEntryHead(p.getDayEntryHead());
			r.setMonthEntryHead(p.getMonthEntryHead());
			r.setYearEntryHead(p.getYearEntryHead());
			r.setKeyToRP(p.getKeyToRP());
			
		    int i = Collections.binarySearch(registrations, r, new ComparatorRegistration());
			
			if(i < 0){
				Message m = new Message("1021");				// '1021', 'FS', 'Sleutel B2 niet in B4'
				m.setKeyToRP(p.getKeyToRP());
				m.setKeyToSourceRegister(p.getKeyToSourceRegister());
				m.setDayEntryHead(p.getDayEntryHead());
				m.setMonthEntryHead(p.getMonthEntryHead());
				m.setYearEntryHead(p.getYearEntryHead());			
				m.setKeyToRegistrationPersons(p.getKeyToRegistrationPersons());
				m.save();		
				keysOK = false;
			}

		}
		
		// Check that B3 keys are in B4
		System.out.println("Check that B3 keys are in B4");
		
		for(PersonDynamic pd: personsDynamic){
			
			Registration r = new Registration();
			r.setKeyToSourceRegister(pd.getKeyToSourceRegister());
			r.setDayEntryHead(pd.getDayEntryHead());
			r.setMonthEntryHead(pd.getMonthEntryHead());
			r.setYearEntryHead(pd.getYearEntryHead());
			r.setKeyToRP(pd.getKeyToRP());
			
		    int i = Collections.binarySearch(registrations, r, new ComparatorRegistration());
			if(i < 0){
				Message m = new Message("1022"); // '1022', 'FS', 'Sleutel B3 niet in B4'
				m.setKeyToRP(pd.getKeyToRP());
				m.setKeyToSourceRegister(pd.getKeyToSourceRegister());
				m.setDayEntryHead(pd.getDayEntryHead());
				m.setMonthEntryHead(pd.getMonthEntryHead());
				m.setYearEntryHead(pd.getYearEntryHead());
				m.setKeyToRegistrationPersons(pd.getKeyToRegistrationPersons());
				m.save();
				keysOK = false;
				
			}
		}

		// Check that B6 keys are in B4
		System.out.println("Check that B6 keys are in B4");
		
		for(RegistrationAddress ra: registrationAddresses){
			
			Registration r = new Registration();
			r.setKeyToSourceRegister(ra.getKeyToSourceRegister());
			r.setDayEntryHead(ra.getDayEntryHead());
			r.setMonthEntryHead(ra.getMonthEntryHead());
			r.setYearEntryHead(ra.getYearEntryHead());
			r.setKeyToRP(ra.getKeyToRP());
			
		    int i = Collections.binarySearch(registrations, r, new ComparatorRegistration());
			if(i < 0){				
				Message m = new Message("1023");  // '1023', 'FS', 'Sleutel B6 niet in B4'
				m.setKeyToRP(ra.getKeyToRP());
				m.setKeyToSourceRegister(ra.getKeyToSourceRegister());
				m.setDayEntryHead(ra.getDayEntryHead());
				m.setMonthEntryHead(ra.getMonthEntryHead());
				m.setYearEntryHead(ra.getYearEntryHead());
				m.setKeyToRegistrationPersons(ra.getKeyToRegistrationPersons());
				m.save();
				keysOK = false;
				
				//break;
			}	
		}
		
		// Check that B4 does not have double key
		System.out.println("Check that B4 has no double key");
		
		keyToSourceRegister       = 0;      
		dayEntryHeadOfHousehold   = 0;
		monthEntryHeadOfHousehold = 0; 
		yearEntryHeadOfHousehold  = 0;
		keyToHSNResearchPerson    = 0;   
 
		for(Registration r: registrations){
			
			if(keyToSourceRegister == r.getKeyToSourceRegister()       &&
           dayEntryHeadOfHousehold == r.getDayEntryHead()   &&
	     monthEntryHeadOfHousehold == r.getMonthEntryHead() && 
		  yearEntryHeadOfHousehold == r.getYearEntryHead()  && 
			keyToHSNResearchPerson == r.getKeyToRP()){
			
				Message m = new Message("1028");  // '1028', 'FS', 'Dubbele sleutel in B4'
				m.setKeyToRP(keyToHSNResearchPerson);
				m.setKeyToSourceRegister(keyToSourceRegister);
				m.setDayEntryHead(dayEntryHeadOfHousehold);
				m.setMonthEntryHead(monthEntryHeadOfHousehold);
				m.setYearEntryHead(yearEntryHeadOfHousehold);
				m.save();
				keysOK = false;
				break;
			}
			else{
				keyToSourceRegister       = r.getKeyToSourceRegister();
				dayEntryHeadOfHousehold   = r.getDayEntryHead();
				monthEntryHeadOfHousehold = r.getMonthEntryHead(); 
			    yearEntryHeadOfHousehold  = r.getYearEntryHead(); 
				keyToHSNResearchPerson    = r.getKeyToRP();
			}
		}
		
		// Check that B4 keys are in B2
		System.out.println("Check that B4 keys are in B2");
		
		for(Registration r: registrations){
			
			Person p = new Person();
			p.setKeyToSourceRegister(r.getKeyToSourceRegister());
			p.setDayEntryHead(r.getDayEntryHead());
			p.setMonthEntryHead(r.getMonthEntryHead());
			p.setYearEntryHead(r.getYearEntryHead());
			p.setKeyToRP(r.getKeyToRP());
			p.setKeyToRegistrationPersons(1); // because numbering starts with 1
			
		    int i = Collections.binarySearch(persons, p, new ComparatorPerson());
			if(i < 0){
				Message m = new Message("1020");  // '1020', 'FS', 'Sleutel B4 niet in B2'
				m.setKeyToRP(r.getKeyToRP());
				m.setKeyToSourceRegister(r.getKeyToSourceRegister());
				m.setDayEntryHead(r.getDayEntryHead());
				m.setMonthEntryHead(r.getMonthEntryHead());
				m.setYearEntryHead(r.getYearEntryHead());
				m.save();
				keysOK = false;
				//break;
			}	
		}
		
		// Check that administrative fields in B2 are filled in
		System.out.println("Check that administrative fields in B2 are filled in");
		
		count = 0;
		for(Person p: persons){
			if(p.getOrderNumberOriginal() == null || p.getOrderNumberOriginal().trim().length() == 0              ||
			          p.getDateOriginal() == null || p.getDateOriginal().trim().length() == 0                     ||
			       p.getInitialOriginal() == null || p.getInitialOriginal().trim().length() == 0                  ||
		  p.getVersionOriginalDataEntry() == null || p.getVersionOriginalDataEntry().trim().length() == 0         || 
		      p.getResearchCodeOriginal() == null || p.getResearchCodeOriginal().trim().length() == 0){
				
				if(count++ < 5){
					Message m = new Message("1024"); // '1024', 'FS', 'B2 administratieve velden niet volledig ingevuld (alleen 1ste 5 gevallen)'
					m.setKeyToRP(p.getKeyToRP());
					m.setKeyToSourceRegister(p.getKeyToSourceRegister());
					m.setDayEntryHead(p.getDayEntryHead());
					m.setMonthEntryHead(p.getMonthEntryHead());
					m.setYearEntryHead(p.getYearEntryHead());
					m.setKeyToRegistrationPersons(p.getKeyToRegistrationPersons());
					m.save();
				}
			}
		}
		
		// Check that administrative fields in B3 are filled in
		System.out.println("Check that administrative fields in B3 are filled in");
		
		count = 0;
		for(PersonDynamic pd: personsDynamic){
			if(pd.getOrderNumberOriginal() == null || pd.getOrderNumberOriginal().trim().length() == 0              ||
			          pd.getDateOriginal() == null || pd.getDateOriginal().trim().length() == 0                     ||
			       pd.getInitialOriginal() == null || pd.getInitialOriginal().trim().length() == 0                  ||
		  pd.getVersionOriginalDataEntry() == null || pd.getVersionOriginalDataEntry().trim().length() == 0         || 
		      pd.getResearchCodeOriginal() == null || pd.getResearchCodeOriginal().trim().length() == 0){
				
				if(count++ < 5){
					Message m = new Message("1025"); // '1025', 'FS', 'B3 administratieve velden niet volledig ingevuld (alleen 1ste 5 gevallen)'
					m.setKeyToRP(pd.getKeyToRP());
					m.setKeyToSourceRegister(pd.getKeyToSourceRegister());
					m.setDayEntryHead(pd.getDayEntryHead());
					m.setMonthEntryHead(pd.getMonthEntryHead());
					m.setYearEntryHead(pd.getYearEntryHead());
					m.setKeyToRegistrationPersons(pd.getKeyToRegistrationPersons());
					m.save();
				}

			}
		}
		// Check that administrative fields in B4 are filled in
		System.out.println("Check that administrative fields in B4 are filled in");
		
		count = 0;
		for(Registration r: registrations){
			if(r.getOrderNumberOriginal() == null || r.getOrderNumberOriginal().trim().length() == 0              ||
			          r.getDateOriginal() == null || r.getDateOriginal().trim().length() == 0                     ||
			       r.getInitialOriginal() == null || r.getInitialOriginal().trim().length() == 0                  ||
		  r.getVersionOriginalDataEntry() == null || r.getVersionOriginalDataEntry().trim().length() == 0         || 
		      r.getResearchCodeOriginal() == null || r.getResearchCodeOriginal().trim().length() == 0){
				
				if(count++ < 5){
					Message m = new Message("1026");    // '1026', 'FS', 'B4 administratieve velden niet volledig ingevuld (alleen 1ste 5 gevallen)'
					m.setKeyToRP(r.getKeyToRP());
					m.setKeyToSourceRegister(r.getKeyToSourceRegister());
					m.setDayEntryHead(r.getDayEntryHead());
					m.setMonthEntryHead(r.getMonthEntryHead());
					m.setYearEntryHead(r.getYearEntryHead());
					m.save();
				}

			}
		}
		
		// Check that administrative fields in B6 are filled in
		System.out.println("Check that administrative fields in B6 are filled in");
		
		count = 0;
		for(RegistrationAddress ra: registrationAddresses){
			if(ra.getOrderNumberOriginal() == null || ra.getOrderNumberOriginal().trim().length() == 0              ||
			          ra.getDateOriginal() == null || ra.getDateOriginal().trim().length() == 0                     ||
			       ra.getInitialOriginal() == null || ra.getInitialOriginal().trim().length() == 0                  ||
		  ra.getVersionOriginalDataEntry() == null || ra.getVersionOriginalDataEntry().trim().length() == 0         || 
		      ra.getResearchCodeOriginal() == null || ra.getResearchCodeOriginal().trim().length() == 0){
				
				if(count++ < 5){
					Message m = new Message("1027"); // '1027', 'FS', 'B6 administratieve velden niet volledig ingevuld (alleen 1ste 5 gevallen)'
					m.setKeyToRP(ra.getKeyToRP());
					m.setKeyToSourceRegister(ra.getKeyToSourceRegister());
					m.setDayEntryHead(ra.getDayEntryHead());
					m.setMonthEntryHead(ra.getMonthEntryHead());
					m.setYearEntryHead(ra.getYearEntryHead());
					m.setKeyToRegistrationPersons(ra.getKeyToRegistrationPersons());
					m.save();
				}

			}
		}
		
		// Check that B2 - B2RNBG starts with 1b
		System.out.println("Check that B2:B2RNBG - keyToRegistrationPersons starts with 1");
		
		for(Registration r: registrations){
			for(Person p: r.getPersonsInRegistration()){

				if(p.getKeyToRegistrationPersons() != 1){
					Message m = new Message("1055"); // '1055', 'FS', HSN-volgnummering is in B2 niet goed (begint niet met volgnummer '1')
					m.setKeyToRP(p.getKeyToRP());
					m.setKeyToSourceRegister(p.getKeyToSourceRegister());
					m.setDayEntryHead(p.getDayEntryHead());
					m.setMonthEntryHead(p.getMonthEntryHead());
					m.setYearEntryHead(p.getYearEntryHead());
					m.setKeyToRegistrationPersons(p.getKeyToRegistrationPersons());
					m.save();
					keysOK = false;
					//break;
				}
				break;
			}
		}

		// Check that B2 - B2RNBG is consecutive
		System.out.println("Check that B2:B2RNBG - keyToRegistrationPersons is consecutive");
		
		for(Registration r: registrations){
			
			int sn = 0;
			for(Person p: r.getPersonsInRegistration()){

				if(p.getKeyToRegistrationPersons() != ++sn){
					Message m = new Message("1056");         // '1056', 'FS', HSN-volgnummering is niet goed (geconstateerd in B2)
					m.setKeyToRP(p.getKeyToRP());
					m.setKeyToSourceRegister(p.getKeyToSourceRegister());
					m.setDayEntryHead(p.getDayEntryHead());
					m.setMonthEntryHead(p.getMonthEntryHead());
					m.setYearEntryHead(p.getYearEntryHead());
					m.setKeyToRegistrationPersons(p.getKeyToRegistrationPersons());
					m.save();
					keysOK = false;
					break;
				}
				
			}
		}

		
		// Check that B2 - B1IDBG - keyToSourceRegister is in ainb
		System.out.println("Check that B2:B1IDBG - keyToSourceRegister is in ainb");
		
		for(Person p: persons){
			
			Ref_AINB ainb = Ref.getAINB(p.getKeyToSourceRegister());
			
			if(ainb == null){
				Message m = new Message("1053"); // '1053', 'RF', 'Identificatienummer van de bron <bronnummer> niet in AINB-bestand aanwezig'
				m.setKeyToRP(p.getKeyToRP());
				m.setKeyToSourceRegister(p.getKeyToSourceRegister());
				m.setDayEntryHead(p.getDayEntryHead());
				m.setMonthEntryHead(p.getMonthEntryHead());
				m.setYearEntryHead(p.getYearEntryHead());
				m.setKeyToRegistrationPersons(p.getKeyToRegistrationPersons());
				m.save(new Integer(p.getKeyToSourceRegister()).toString());
				keysOK = false;
				//break;
			}
		}
		
		// Check that B2 - IDNR - keyToHSNResearchPerson is in HSNRP (geboorteakten)
		System.out.println("Check that B2:IDNR - keyToHSNResearchPerson is in HSNRP (geboorteakten)");
		
		for(Person p: persons){
			
			//Ref_GB gb = Ref.getGB(p.getKeyToRP());
			Ref_RP rp = Ref.getRP(p.getKeyToRP());
			
			if(rp == null){
				Message m = new Message("1054"); // '1054', 'RF', 'Identificatienummer Onderzoekspersoon NIET in HSNRP bestand  aanwezig! <idnr>'
				m.setKeyToRP(p.getKeyToRP());
				m.setKeyToSourceRegister(p.getKeyToSourceRegister());
				m.setDayEntryHead(p.getDayEntryHead());
				m.setMonthEntryHead(p.getMonthEntryHead());
				m.setYearEntryHead(p.getYearEntryHead());	
				m.setKeyToRegistrationPersons(p.getKeyToRegistrationPersons());
				m.save(new Integer(p.getKeyToRP()).toString());
				keysOK = false;

				//break;
			}
		}
		
        
		// Check that key B2 is in B3 at least 3 times (for b3type = 1,2,3)
		System.out.println("Check that key B2 is in B3 at least 3 times (for b3type = 1,2,3)");
		
		int i_pd = 0;
		for(Person p: persons){
			
			PersonDynamic pd = new PersonDynamic();
			pd.setKeyToSourceRegister(p.getKeyToSourceRegister());
			pd.setDayEntryHead(p.getDayEntryHead());
			pd.setMonthEntryHead(p.getMonthEntryHead());
			pd.setYearEntryHead(p.getYearEntryHead());
			pd.setKeyToRP(p.getKeyToRP());
			pd.setKeyToRegistrationPersons(p.getKeyToRegistrationPersons()); 
			pd.setDynamicDataSequenceNumber(1); // because we look at the first dynamic data element for different values of dynamicDataType 
			
			pd.setDynamicDataType(1);
		    int i = Collections.binarySearch(personsDynamic, pd, new ComparatorPersonDynamic());
		    
			if(i < 0){
				
				Message m = new Message("1058");  // '1058', 'FS', 'Sleutel van B2 komt niet voor in B3-bestand, (typenummer 1)'
				m.setKeyToRP(p.getKeyToRP());
				m.setKeyToSourceRegister(p.getKeyToSourceRegister());
				m.setDayEntryHead(p.getDayEntryHead());
				m.setMonthEntryHead(p.getMonthEntryHead());
				m.setYearEntryHead(p.getYearEntryHead());
				m.setKeyToRegistrationPersons(p.getKeyToRegistrationPersons());
                m.save();
				keysOK = false;

			}
			
			pd.setDynamicDataType(2);
		    i = Collections.binarySearch(personsDynamic, pd, new ComparatorPersonDynamic());
			
			if(i < 0){

				Message m = new Message("1059");  // '1059', 'FS', 'Sleutel van B2 komt niet voor in B3-bestand, (typenummer 2)'
				m.setKeyToRP(p.getKeyToRP());
				m.setKeyToSourceRegister(p.getKeyToSourceRegister());
				m.setDayEntryHead(p.getDayEntryHead());
				m.setMonthEntryHead(p.getMonthEntryHead());
				m.setYearEntryHead(p.getYearEntryHead());
				m.setKeyToRegistrationPersons(p.getKeyToRegistrationPersons());
                m.save();
				keysOK = false;

			}
			
			pd.setDynamicDataType(3);
		    i = Collections.binarySearch(personsDynamic, pd, new ComparatorPersonDynamic());

			if(i < 0){
				Message m = new Message("1060");         // '1060', 'FS', 'Sleutel van B2 komt niet voor in B3-bestand, (typenummer 3)'
				m.setKeyToRP(p.getKeyToRP());
				m.setKeyToSourceRegister(p.getKeyToSourceRegister());
				m.setDayEntryHead(p.getDayEntryHead());
				m.setMonthEntryHead(p.getMonthEntryHead());
				m.setYearEntryHead(p.getYearEntryHead());
				m.setKeyToRegistrationPersons(p.getKeyToRegistrationPersons());
                m.save();
				keysOK = false;

			}

		}	
		
		// Check that key B3 is in B2 
		System.out.println("Check that key B3 is in B2 ");
		
		for(PersonDynamic pd: personsDynamic){
			
			Person p = new Person();
			p.setKeyToSourceRegister(pd.getKeyToSourceRegister());
			p.setDayEntryHead(pd.getDayEntryHead());
			p.setMonthEntryHead(pd.getMonthEntryHead());
			p.setYearEntryHead(pd.getYearEntryHead());
			p.setKeyToRP(pd.getKeyToRP());
			p.setKeyToRegistrationPersons(pd.getKeyToRegistrationPersons()); 
						
			int i = Collections.binarySearch(persons, p, new ComparatorPerson());
			if(i < 0){
				
				
				Message m = new Message("1061");  // '1061', 'FS', 'Sleutel van B3  komt niet voor in B2-bestand'
				m.setKeyToRP(pd.getKeyToRP());
				m.setKeyToSourceRegister(pd.getKeyToSourceRegister());
				m.setDayEntryHead(pd.getDayEntryHead());
				m.setMonthEntryHead(pd.getMonthEntryHead());
				m.setYearEntryHead(pd.getYearEntryHead());
				m.setKeyToRegistrationPersons(pd.getKeyToRegistrationPersons());
                m.save();
				keysOK = false;
			}	
		}

		// Check that key B3 key is ok (per dynamicDataType the dynamicDataSequenceNumber must start with 1 and be contiguous (1, 2, 3, 4......)
		System.out.println("Check that key B3 key OK");
		
		int prevType = -1;
		int prevSeqNo = -1;		
		
		
		for(PersonDynamic pd: personsDynamic){
			
			if(prevType != pd.getDynamicDataType()){
				prevType = pd.getDynamicDataType();
				if(pd.getDynamicDataSequenceNumber() != 1){
					Message m = new Message("1062");  // '1062', 'FS', 1062,  Sleutel van B3 klopt niet (doornummering regels fout bij <typenummer>)
					m.setKeyToRP(pd.getKeyToRP());
					m.setKeyToSourceRegister(pd.getKeyToSourceRegister());
					m.setDayEntryHead(pd.getDayEntryHead());
					m.setMonthEntryHead(pd.getMonthEntryHead());
					m.setYearEntryHead(pd.getYearEntryHead());
					m.setKeyToRegistrationPersons(pd.getKeyToRegistrationPersons());
					String [] fills = {"" + pd.getDynamicDataType()};
	                m.save(fills);
					keysOK = false;

	                continue;
				}
				else
					prevSeqNo = 1;
			}
			else{
				if(pd.getDynamicDataSequenceNumber() != ++prevSeqNo){
					Message m = new Message("1062");  // '1062', 'FS', 1062,  Sleutel van B3 klopt niet (doornummering regels fout bij <typenummer>)
					m.setKeyToRP(pd.getKeyToRP());
					m.setKeyToSourceRegister(pd.getKeyToSourceRegister());
					m.setDayEntryHead(pd.getDayEntryHead());
					m.setMonthEntryHead(pd.getMonthEntryHead());
					m.setYearEntryHead(pd.getYearEntryHead());
					m.setKeyToRegistrationPersons(pd.getKeyToRegistrationPersons());
					String [] fills = {"" + pd.getDynamicDataType()};
	                m.save(fills);
					keysOK = false;
	                
	                continue;
					
				}
			}
			
		}
		
		
		
		// Check that partial key B6 is in B2 
		System.out.println("Check that partial key B6 is in B2 ");
		
		for(RegistrationAddress ra: registrationAddresses){
			
			Person p = new Person();
			p.setKeyToSourceRegister(ra.getKeyToSourceRegister());
			p.setDayEntryHead(ra.getDayEntryHead());
			p.setMonthEntryHead(ra.getMonthEntryHead());
			p.setYearEntryHead(ra.getYearEntryHead());
			p.setKeyToRP(ra.getKeyToRP());
			if(ra.getKeyToRegistrationPersons() == 0)
				p.setKeyToRegistrationPersons(1); // household bound address, look for first person
			else
				p.setKeyToRegistrationPersons(ra.getKeyToRegistrationPersons()); // address for specific person
						
			int i = Collections.binarySearch(persons, p, new ComparatorPerson());
			if(i < 0){
				//System.out.println("XXXXX " + 3);
				Message m = new Message("1063");  // '1063', 'FS', 'Sleutel van B6 komt niet voor in B2-bestand'
				
				m.setKeyToRP(ra.getKeyToRP());
				m.setKeyToSourceRegister(ra.getKeyToSourceRegister());
				m.setDayEntryHead(ra.getDayEntryHead());
				m.setMonthEntryHead(ra.getMonthEntryHead());
				m.setYearEntryHead(ra.getYearEntryHead());
                m.save();
				keysOK = false;
			}	
		}

		// Check that B3:B2RNBG != B3:B3RGLN. This means the related person is the person itself. This is always incorrect 
		
		System.out.println("Check that B3 valueOfRelatedPerson (B3RGLN) is not equal to B3 keyToRegistrationPersons (B2RNGB) ");
		
		for(PersonDynamic pd: personsDynamic){
		
			if(pd.getValueOfRelatedPerson() == pd.getKeyToRegistrationPersons()){
				
				Message m = new Message("1314");  //1314, FT, Regelnummer verwijst voor regelnummer van de relatie naar de eigen regel.
				m.setKeyToRP(pd.getKeyToRP());
				m.setKeyToSourceRegister(pd.getKeyToSourceRegister());
				m.setDayEntryHead(pd.getDayEntryHead());
				m.setMonthEntryHead(pd.getMonthEntryHead());
				m.setYearEntryHead(pd.getYearEntryHead());
				m.setKeyToRegistrationPersons(pd.getKeyToRegistrationPersons());
                m.save();
				keysOK = false;

			}
		}
		return(keysOK);
	}
}
