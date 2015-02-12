
/*
 * Naam:    PDS_Religion - PersonDynamicStandardized
 * Version: 0.1
 *  Author: Cor Munnik
 * Copyright
 */
package nl.iisg.ids03;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import nl.iisg.hsncommon.ConstRelations2;
import nl.iisg.ref.*;


/**
 * 
 * This class handles the dynamic attributes of a standardized PersonDynamic
 */

@Entity
@Table(name="b33_st")
public class PDS_Religion extends PersonDynamicStandardized {

	@Column(name = "RELIGION_ID")     private int         religionID;
	@Column(name = "RELIGION_ST")     private String      religionStandardized;
	@Column(name = "RELIGION_FG")     private int         religionFlag;
	
	PDS_Religion(){}
	
	@Override
	public ArrayList<PersonDynamicStandardized> transform(PersonDynamic pd){

        super.transform(pd);
        

        if(pd.getDynamicData2() != null && pd.getDynamicData2().trim().length() > 0){
    		Ref_KG kg = Ref.getKG(pd.getDynamicData2().trim());
    		if(kg != null  && kg.getStandard() != null && kg.getStandard().trim().length() > 0 &&
    				kg.getCode() != null && (kg.getCode().trim().equalsIgnoreCase("y"))){ 
    			// Special processing for Standard value "Ïdem"
    			if(kg.getStandard().trim().equalsIgnoreCase("Idem")){
    				
    				String religion = null;
    				//System.out.println(getPersonStandardizedToWhomDynamicDataRefers());
    				for(PersonStandardized ps: getPersonStandardizedToWhomDynamicDataRefers().getRegistrationStandardizedPersonAppearsIn().getPersonsStandardizedInRegistration()){
    				
    					if(ps == getPersonStandardizedToWhomDynamicDataRefers())
    						break;
    					
    					for(PersonDynamicStandardized pds: ps.getDynamicDataOfPersonStandardized()){

    						if(pds.getKeyToDistinguishDynamicDataType() == ConstRelations2.GODSDIENST)
    							if(((PDS_Religion)pds).getReligionStandardized() != null  && !((PDS_Religion)pds).getReligionStandardized().trim().equalsIgnoreCase("Idem"))
    								religion = ((PDS_Religion)pds).getReligionStandardized().trim();
    					}
    				}
    				System.out.println("religion = "+ religion);
    				if(religion != null){
    	    			setReligionStandardized(religion);
    	    			setReligionFlag(1);
    				}
    				else{
    					System.out.println( pd.getDynamicData2().trim() + " =====> " + kg.getStandard());
    	    			setReligionStandardized(kg.getStandard().trim());
    	    			setReligionFlag(0);
    				}
    			}
    			else{
    				System.out.println( pd.getDynamicData2().trim() + " =====+> " + kg.getStandard());
    				setReligionStandardized(kg.getStandard().trim());
    				setReligionFlag(0);
    			}
    		}
    		else{
    			
    			if(kg == null){
    				Ref_KG k = new Ref_KG();
    				k.setDenomination(pd.getDynamicData2().trim());
    				k.setCode("x");
    				Ref.addKG(k);
    				message("21", "" + pd.getDynamicData2());
    			}
    			else{
    				if(kg.getCode() != null && kg.getCode().trim().length() > 0){
    					if(kg.getCode().equalsIgnoreCase("x"))
    						message("21", "" + pd.getDynamicData2());
    					else{
    						if(kg.getCode().equalsIgnoreCase("n"))
    							message("23",  "" + pd.getDynamicData2());
    						else{
    							if(kg.getCode().equalsIgnoreCase("u"))
    								message("25",  "" + pd.getDynamicData2());
    							else
    								message("29", "" + kg.getCode());
    						}
    					}
    				}
    				else
						message("29", "null");

    			}
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
		
		String ddt = "GODSDIENST        = "; 
		if(getReligionStandardized() != null){
			ddt += getReligionStandardized();			
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
		
		String field = getReligionStandardized();
		int allowedSize = ConstTables.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B33_ST", "RELIGION_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setReligionStandardized(field);
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


	public int getReligionID() {
		return religionID;
	}

	public void setReligionID(int religionID) {
		this.religionID = religionID;
	}

	public String getReligionStandardized() {
		return religionStandardized;
	}

	public void setReligionStandardized(String religionStandardized) {
		this.religionStandardized = religionStandardized;
	}

	public int getReligionFlag() {
		return religionFlag;
	}

	public void setReligionFlag(int religionFlag) {
		this.religionFlag = religionFlag;
	}
	
	
	
	
	
}