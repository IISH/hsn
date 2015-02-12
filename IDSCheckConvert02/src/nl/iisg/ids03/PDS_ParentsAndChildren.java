
/*
 * Naam:    PDS_ParentsAndChildren - PersonDynamicStandardized
 * Version: 0.1
 *  Author: Cor Munnik
 * Copyright
 */
package nl.iisg.ids03;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * This class handles the dynamic attributes of a standardized PersonDynamic
 *
 */

@Entity
@Table(name="b312_st")
public class PDS_ParentsAndChildren extends PersonDynamicStandardized {

	@Column(name = "B3KODE")        private int          contentOfDynamicData;
	@Column(name = "RELATION_FG")   private int          relation;

	
	PDS_ParentsAndChildren(){}
	
	@Override
	public ArrayList<PersonDynamicStandardized> transform(PersonDynamic pd){

		super.transform(pd);
		
		setKeyToDistinguishDynamicDataType(12);
		
		String x = pd.getDynamicData2().trim();
		
		if(x != null && x.length() == 1 && "123456".indexOf(x) >=0){
			
			int xi = (new Integer(x)).intValue();
			
			switch(xi){
			case 1:
				setContentOfDynamicData(12);
				setRelation(31);
				break;
			case 2:
				setContentOfDynamicData(12);
				setRelation(32);
				break;
			case 3:
				setContentOfDynamicData(12);
				setRelation(33);
				break;
			case 4:
				setContentOfDynamicData(11);
				setRelation(31);
				break;
			case 5:
				setContentOfDynamicData(11);
				setRelation(32);
				break;
			case 6:
				setContentOfDynamicData(11);
				setRelation(33);
				break;
				
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
		
		String ddt = "OUDER_KIND_INFO   = " + getContentOfDynamicData() + " - " + getRelation();
		String EntryDateHead = getEntryDateHead();
		String ddc = getDateOfMutation2();
		String rp = "";
		if(getValueOfRelatedPerson() > 0)
			rp = "      Related Person = " + getValueOfRelatedPerson();
		
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
	
	public int getRelation() {
		return relation;
	}

	public void setRelation(int relation) {
		this.relation = relation;
	}
	
	
}
