
/*
 * Naam:    PDS_OccupationalTitle - PersonDynamicStandardized
 * Version: 0.1
 *  Author: Cor Munnik
 * Copyright
 */
package nl.iisg.ids03;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

import nl.iisg.ref.*;


/**
 * 
 * This class handles the dynamic attributes of a standardized PersonDynamic
 *
 */

@Entity
@Table(name="b35_st")
public class PDS_OccupationalTitle extends PersonDynamicStandardized {

	@Column(name = "OCCUPATION_ID")        private int          occupationID;
	@Column(name = "OCCUPATION_ST")        private String       occupationStandardized;
	@Column(name = "OCCUPATION_FG")        private int          occupationFlag;
	
	PDS_OccupationalTitle(){}

	@Override
	public ArrayList<PersonDynamicStandardized> transform(PersonDynamic pd){

        super.transform(pd);

        boolean ex = false;
        
        String profession = pd.getDynamicData2();
        
        if(profession != null && profession.trim().length() > 0) {


        	if (profession.indexOf("*ex") >= 0) {
        		profession.replace("*ex", "");
        		ex = true;
        	}

        	Ref_Profession rp = Ref.getProfession(profession);


        	if(rp != null){

        		//System.out.println(profession + " " + rp.getProfession() + " " + rp.getCode());
        		if(rp.getCode() != null){



        			if(rp.getCode().equalsIgnoreCase("y")){
        				if(rp.getProfession() != null){
        					setOccupationStandardized(rp.getProfession());
        					setOccupationID(rp.getProfessionID()); 
        					int flag = pd.getContentOfDynamicData();
        					if(ex == true)
        						flag = flag + 10;
        					setOccupationFlag(flag);							
        				}
        			}
        			else{
        				if(rp.getCode().equalsIgnoreCase("x"))
        					message("41", "" + profession);
        				else{
        					if(rp.getCode().equalsIgnoreCase("n"))
        						message("43",  "" + profession);
        					else{
        						if(rp.getCode().equalsIgnoreCase("u"))
        							message("45",  "" + profession);
        						else
        							message("49", "" + rp.getCode());
        					}
        				}
        			}
        		}
        		else
        			message("49",  "null");
        	}
        	else{
        		Ref_Profession rp1 = new Ref_Profession();
        		rp1.setOriginal(profession);
        		rp1.setProfession(profession);
        		rp1.setCode("x");
        		Ref.addProfession(rp1);

        		message("41",  "" +   profession);
        	}


        }


        
        ArrayList<PersonDynamicStandardized> a = new ArrayList();
		a.add(this);
		return a;
	}
	
	@Override
	public void print(){
		
		showFields();
	}
	
	public void showFields(){
		String ddt = "BEROEPSTITEL      = "; 
		if(getOccupationStandardized() != null){
			ddt += getOccupationStandardized();			
		}    
		
		String EntryDateHead = getEntryDateHead();

		
		String ddc = getDateOfMutation2();
		
		String startDate = getStartDate() != null ? getStartDate() : "00-00-0000";
		String startFlag = String.format("%02d", getStartFlag());
		String startEst  = String.format("%03d", getStartEst());

		String endDate = getEndDate() != null ? getEndDate() : "00-00-0000";
		String endFlag = String.format("%02d", getEndFlag());
		String endEst  = String.format("%03d", getEndEst());
		
		String keyToPersons = String.format("%2d", getKeyToRegistrationPersons());
		String ddSeqNr = String.format("%2d", getDynamicDataSequenceNumber());


			
		System.out.println(startDate +
				" " + startFlag +			
				" " + startEst +			
				" " + endDate +
				" " + endFlag +			
				" " + endEst +	
		  		"   " + keyToPersons +
         		"        " + ddc + 
		  		"  " + ddSeqNr +
		        "  " + ddt
		        );
		

	}
	
	/**
	 * 
	 * This routine truncates fields that are too long for the corresponding database columns
	 * 
	 */
	

	@Override
	public void truncate(){	
		
		
		//System.out.println("Occupational Title ");
		String field = getOccupationStandardized();
		int allowedSize = ConstTables.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B35_ST", "OCCUPATION_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setOccupationStandardized(field);
		}

	}

	private void message(String number, String... fills){
		
		Message m = new Message(number);
		
		m.setKeyToRP(getKeyToRP());
		
		m.setKeyToSourceRegister(getKeyToSourceRegister());
		
		m.setDayEntryHead((new Integer(getEntryDateHead().substring(0,2)).intValue()));
		m.setMonthEntryHead((new Integer(getEntryDateHead().substring(3,5)).intValue()));
		m.setYearEntryHead((new Integer(getEntryDateHead().substring(6,10)).intValue()));
		
		m.setDayEntryRP((new Integer(getPersonStandardizedToWhomDynamicDataRefers().getRegistrationStandardizedPersonAppearsIn().getEntryDateRP().substring(0,2)).intValue()));
		m.setMonthEntryRP((new Integer(getPersonStandardizedToWhomDynamicDataRefers().getRegistrationStandardizedPersonAppearsIn().getEntryDateRP().substring(3,5)).intValue()));
		m.setYearEntryRP((new Integer(getPersonStandardizedToWhomDynamicDataRefers().getRegistrationStandardizedPersonAppearsIn().getEntryDateRP().substring(6,10)).intValue()));
		
		m.setKeyToRegistrationPersons(getKeyToRegistrationPersons());
		m.setNatureOfPerson(getNatureOfPerson());
		
		m.save(fills); 
		
		
	}


	
	public int getOccupationID() {
		return occupationID;
	}

	public void setOccupationID(int occupationID) {
		this.occupationID = occupationID;
	}

	public String getOccupationStandardized() {
		return occupationStandardized;
	}

	public void setOccupationStandardized(String occupationStandardized) {
		this.occupationStandardized = occupationStandardized;
	}

	public int getOccupationFlag() {
		return occupationFlag;
	}

	public void setOccupationFlag(int occupationFlag) {
		this.occupationFlag = occupationFlag;
	}

	
	
	
	
}