
/*
 * Naam:    PDS_CivilStatus - PersonDynamicStandardized
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
 *
 */

@Entity
@Table(name="b32_st")
public class PDS_CivilStatus extends PersonDynamicStandardized {

	@Column(name = "B3KODE")        		private int         contentOfDynamicData;
	@Column(name = "CIVIL_STATUS_FG")       private int         civilStatusFlag;
	@Column(name = "CIVIL_LOCALITY_ID")     private int         civilLocalityID;
	@Column(name = "CIVIL_LOCALITY_ST")     private String      civilLocalityStandardized;
	@Column(name = "CIVIL_LOCALITY_FG")     private int         civilLocalityFlag;
	
	PDS_CivilStatus(){}
	
	@Override
	public ArrayList<PersonDynamicStandardized> transform(PersonDynamic pd){

		super.transform(pd);
		
		PDS_CivilStatus cs = null;
		
		setContentOfDynamicData((pd.getContentOfDynamicData()));
		
		switch(pd.getContentOfDynamicData()){
		case 9:
			setContentOfDynamicData(1); // unmarried
			setCivilStatusFlag(9);
			setValueOfRelatedPerson(0); 
			pd.setContentOfDynamicData(-1); // trick, will make cs.transform(pd) go to case -1
	        //cs = new PDS_CivilStatus();
	        //cs = (PDS_CivilStatus)PDS_Factory(ConstRelations2.BURGELIJKE_STAAT);
			cs = (PDS_CivilStatus)copyPersonDynamicStandardized();
	        cs.transform(pd);
	        break; // this record is finished now, it cannot have location info or related person info because it is an unmarried record
		
		case -1: // trick, transforming the new, married record will go here
			
			setContentOfDynamicData(5);
			setDateOfMutation("00-00-0000");
			setCivilStatusFlag(9);
			pd.setContentOfDynamicData(9); // trick over, reset to original value
	        
	     
	    default:
	    	
			String s = pd.getDynamicData2();
			
			if(s != null && s.equals("") != true){
				
				String[] a = Utils.transformPlace(pd.getDynamicData2(), Ref.getAINB(getKeyToSourceRegister()), null);

				if(a[0] != null){
					setCivilLocalityStandardized(a[0]);
					setCivilLocalityID(new Integer(a[1]).intValue()); 
					setCivilLocalityFlag(new Integer(a[2]).intValue());
				}
			}
			
		}
		
		ArrayList<PersonDynamicStandardized> a = new ArrayList<PersonDynamicStandardized>();
		a.add(this);
		if(cs != null)
			a.add(cs);
		return a;
		

	}
	
	@Override
	public void print(){
		
		showFields();
	}
	
	public void showFields(){
		
		String ddt = "BURGERLIJKE STAAT = "; 
		if(0 <= getContentOfDynamicData() && getContentOfDynamicData() < ConstRelations2.b3kode2.length){
			ddt += ConstRelations2.b3kode2[getContentOfDynamicData()];			
		}    
		
		String startDate = getStartDate() != null ? getStartDate() : "00-00-0000";
		String startFlag = String.format("%02d", getStartFlag());
		String startEst  = String.format("%03d", getStartEst());

		String endDate = getEndDate() != null ? getEndDate() : "00-00-0000";
		String endFlag = String.format("%02d", getEndFlag());
		String endEst  = String.format("%03d", getEndEst());

		String keyToPersons = String.format("%2d", getKeyToRegistrationPersons());
		String ddSeqNr = String.format("%2d", getDynamicDataSequenceNumber());

		
		String ddc = getDateOfMutation2();
		String dyn = "";
		if(getCivilLocalityStandardized() != null && getCivilLocalityStandardized().trim().length() != 0)
			dyn = getCivilLocalityStandardized().trim();

		String rp = "";
		if(getValueOfRelatedPerson() > 0)
			rp = "      Related Person = " + getValueOfRelatedPerson();
			
		
		System.out.println(startDate +
				" " + startFlag +			
				" " + startEst +			
				" " + endDate +
				" " + endFlag +			
				" " + endEst +	
		  		"   " + keyToPersons +
         		"        " + ddc + 
		  		"  " + ddSeqNr +
		        "  " + ddt +
		        "  " + dyn 
		        + rp
		        );
		

	}
	
	/**
	 * 
	 * This routine truncates fields that are too long for the corresponding database columns
	 * 
	 */
	

	@Override
	public void truncate(){	
		
		String field = getCivilLocalityStandardized();
		int allowedSize = ConstTables.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B32_ST", "CIVIL_LOCALITY_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setCivilLocalityStandardized(field);
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

	
	
	public int getContentOfDynamicData() {
		return contentOfDynamicData;
	}

	public void setContentOfDynamicData(int contentOfDynamicData) {
		this.contentOfDynamicData = contentOfDynamicData;
	}

	public int getCivilStatusFlag() {
		return civilStatusFlag;
	}

	public void setCivilStatusFlag(int civilStatusFlag) {
		this.civilStatusFlag = civilStatusFlag;
	}

	public int getCivilLocalityID() {
		return civilLocalityID;
	}

	public void setCivilLocalityID(int civilLocalityID) {
		this.civilLocalityID = civilLocalityID;
	}

	public String getCivilLocalityStandardized() {
		return civilLocalityStandardized;
	}

	public void setCivilLocalityStandardized(String civilLocalityStandardized) {
		this.civilLocalityStandardized = civilLocalityStandardized;
	}

	public int getCivilLocalityFlag() {
		return civilLocalityFlag;
	}

	public void setCivilLocalityFlag(int civilLocalityFlag) {
		this.civilLocalityFlag = civilLocalityFlag;
	}
	
	
	
}