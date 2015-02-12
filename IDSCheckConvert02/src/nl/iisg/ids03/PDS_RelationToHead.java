/*
 * Naam:    PDS_RelationToHead - PersonDynamicStandardized
 * Version: 0.1
 *  Author: Cor Munnik
 * Copyright
 */
package nl.iisg.ids03;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import nl.iisg.hsncommon.Common1;
import nl.iisg.hsncommon.ConstRelations2;

/**
 * 
 * This class handles the dynamic attributes of a standardized PersonDynamic
 *
 */

@Entity
@Table(name="b311_st")
public class PDS_RelationToHead extends PersonDynamicStandardized {

	@Column(name = "B3KODE")        private int          contentOfDynamicData;
	@Column(name = "B3GEGEVEN")     private String       dynamicData2;
	
	PDS_RelationToHead(){}
	
	@Override
	public ArrayList<PersonDynamicStandardized> transform(PersonDynamic pd){

		super.transform(pd);
		
		setKeyToDistinguishDynamicDataType(11);  
		setContentOfDynamicData(pd.getContentOfDynamicData());
		
		
		// If second explicit head, use date if no mutation date is specified
		
		if(pd.getDynamicData2().indexOf("###$") >= 0){
			setDynamicData2(pd.getDynamicData2()); 
			
			if(Common1.dateIsValid(pd.getDayOfMutation(), pd.getMonthOfMutation(), pd.getYearOfMutation()) != 0){
			
				int i = getDynamicData2().indexOf("###$");
				i = i + 4;
				String t =  getDynamicData2().substring(i).trim();				
				t = t.replaceAll("/", "-"); 
                setDateOfMutation(t); 
                setDateOfMutationFlag(30); 
			
			}
		}
		// See if there is Parent - Child information
		
		PDS_ParentsAndChildren pat = null;
		if((pd.getContentOfDynamicData() == ConstRelations2.KLEINZOON || pd.getContentOfDynamicData() == ConstRelations2.KLEINDOCHTER ||
    	    pd.getContentOfDynamicData() == ConstRelations2.ACHTERKLEINZOON || pd.getContentOfDynamicData() == ConstRelations2.ACHTERKLEINDOCHTER) && pd.getValueOfRelatedPerson() > 0){		
			//pat = new PDS_ParentsAndChildren();
			pat = (PDS_ParentsAndChildren)PDS_Factory(12);
			pat.setKeyToDistinguishDynamicDataType(12);
			pat.setKeyToSourceRegister(getKeyToSourceRegister());
			pat.setEntryDateHead(getEntryDateHead());
			pat.setKeyToRP(getKeyToRP());
			pat.setKeyToRegistrationPersons(getKeyToRegistrationPersons());
			pat.setValueOfRelatedPerson(pd.getValueOfRelatedPerson());
			//System.out.println("Parents and Children, relation: " + pd.getDynamicData2());
			//pd.setDynamicData2("4");
			pat.setOriginalPersonDynamic(pd); 
			pat.transform(pd);
			
		}	
		
		ArrayList<PersonDynamicStandardized> a = new ArrayList();
		a.add(this);
		if(pat != null)
			a.add(pat);
		
		return a;
		

	}

	/**
	 * 
	 * This routine truncates fields that are too long for the corresponding database columns
	 * 
	 */
	

	@Override
	public void truncate(){	
		
		String field = getDynamicData2();
		int allowedSize = ConstTables.XBigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B311_ST", "B3GEGEVEN", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setDynamicData2(field);
		}

	}
	
	@Override
	public void print(){
		
		showFields();
	}
	
	public void showFields(){
		
		String ddt = "RELATIE_TOT_HOOFD = "; 
		if(0 <= getContentOfDynamicData() && getContentOfDynamicData() < ConstRelations2.b3kode1.length){
			ddt += ConstRelations2.b3kode1[getContentOfDynamicData()];			
		}  
		else
			ddt += getContentOfDynamicData();
		
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
		if(getDynamicData2() != null && getDynamicData2().trim().length() != 0)
			dyn = " B3GEGEVEN = " +  getDynamicData2().trim();

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

	public String getDynamicData2() {
		return dynamicData2;
	}

	public void setDynamicData2(String dynamicData2) {
		this.dynamicData2 = dynamicData2;
	}
	
	
	
}
