/*
 * Naam:    PDS_PlaceOfDestination - PersonDynamicStandardized
 * Version: 0.1
 *  Author: Cor Munnik
 * Copyright
 */

package nl.iisg.ids03;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

import java.util.StringTokenizer; 

import nl.iisg.ref.*;



/**
 * 
 * This class handles the dynamic attributes of a standardized PersonDynamic
 *
 */

@Entity
@Table(name="b37_st")
public class PDS_PlaceOfDestination extends PersonDynamicStandardized {


	@Column(name = "DESTINATION_ID")  private int         destinationID;
	@Column(name = "DESTINATION_ST")  private String      destinationStandardized;
	@Column(name = "DESTINATION_FG")  private int         destinationFlag;
	
	@Column(name = "DESTINATION_EQUAL")  private int      destinationGroup;


	@Column(name = "ADDRESS")       private String      address;
	@Column(name = "REGISTER")      private String      register;
	@Column(name = "CENSUS")        private String      census;
	
	
	PDS_PlaceOfDestination(){}

	@Override
	public ArrayList<PersonDynamicStandardized> transform(PersonDynamic pd){
		
		super.transform(pd);

		String destination = null;

		StringTokenizer stk = new StringTokenizer(pd.getDynamicData2(), "/&", true); 
		while(stk.hasMoreTokens()) {

			String data = stk.nextToken(); 

			if(data.equals("/") == true)

				if(stk.hasMoreTokens())
					setAddress(stk.nextToken());

			if(data.equals("&") == true){

				String temp = null; 

				if(stk.hasMoreTokens())
					temp = stk.nextToken();

				if(temp != null && temp.length() >= 2 && temp.substring(0,2).equalsIgnoreCase("VT") == true)
					setCensus(temp.substring(2));

				if(temp != null && (temp.length() <= 1 || ((temp.length() >= 2  && temp.substring(0,2).equalsIgnoreCase("VT") != true && temp.substring(0,2).equalsIgnoreCase("O ") != true))))				
					setRegister(temp);
			}
			
			if(data.equals("/") != true && data.equals("&") != true)
				destination = data;

		}	
			
		
		
		
		
			
		if(destination != null){
			
			String[] b = Utils.transformPlace(destination, null, null);
			
			
			if(b[0] != null){
				setDestinationStandardized(b[0]);
				setDestinationID(new Integer(b[1]).intValue());
				setDestinationFlag(1); 
			}
			else{
				setDestinationStandardized(destination);
				setDestinationID(0);
				setDestinationFlag(-1); 
				
				if(b[1] != null)
					message(b[1], destination);
			}
			
		}
		else{
			setDestinationStandardized(pd.getDynamicData2());
			setDestinationID(0);
			setDestinationFlag(-1); 
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
		
		// 225349  01-01-1910  165560
		// 01-01-2000 xxx 01-01-2001 
		
		String ddt = "VERTREK           = "; 
		if(getDestinationStandardized() != null){
			ddt += getDestinationStandardized();			
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
		
		String field = getDestinationStandardized();
		int allowedSize = ConstTables.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B37_ST", "DESTINATION_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setDestinationStandardized(field);
		}

		field = getAddress();
		allowedSize = ConstTables.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B37_ST", "ADDRESS", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setAddress(field);
		}

		field = getRegister();
		allowedSize = ConstTables.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B37_ST", "REGISTER", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setRegister(field);
		}

		field = getCensus();
		allowedSize = ConstTables.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B37_ST", "CENSUS", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setCensus(field);
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


	
	public int getDestinationID() {
		return destinationID;
	}

	public void setDestinationID(int destinationID) {
		this.destinationID = destinationID;
	}

	public String getDestinationStandardized() {
		return destinationStandardized;
	}

	public void setDestinationStandardized(String destinationStandardized) {
		this.destinationStandardized = destinationStandardized;
	}

	public int getDestinationFlag() {
		return destinationFlag;
	}

	public void setDestinationFlag(int destinationFlag) {
		this.destinationFlag = destinationFlag;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getCensus() {
		return census;
	}

	public void setCensus(String census) {
		this.census = census;
	}

	public int getDestinationGroup() {
		return destinationGroup;
	}

	public void setDestinationGroup(int destinationGroup) {
		this.destinationGroup = destinationGroup;
	}

	
	

}	
	
