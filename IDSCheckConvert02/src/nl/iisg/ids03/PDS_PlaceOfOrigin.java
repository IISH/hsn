
/*
 * Naam:    PDS_PlaceOfOrigin - PersonDynamicStandardized
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
@Table(name="b36_st")
public class PDS_PlaceOfOrigin extends PersonDynamicStandardized {


	@Column(name = "ORIGIN_ID")     private int         originID;
	@Column(name = "ORIGIN_ST")     private String      originStandardized;
	@Column(name = "ORIGIN_FG")     private int         originFlag;
	
	@Column(name = "ORIGIN_EQUAL")  private int         originGroup;


	@Column(name = "ADDRESS")       private String      address;
	@Column(name = "REGISTER")      private String      register;
	@Column(name = "CENSUS")        private String      census;
	
	
	
	/**
	 * 
	 * No arg constructor for JPA
	 * 
	 */
	PDS_PlaceOfOrigin(){}
	
	
	/**
	 * 
	 * This routine transforms location information into various field 
	 */

	@Override
	public ArrayList<PersonDynamicStandardized> transform(PersonDynamic pd){
		
		super.transform(pd);

		String origin = null;
		boolean originalOrigin = false;
		
		StringTokenizer stk = new StringTokenizer(pd.getDynamicData2(), "/&*", true); // true means keywords as tokens, so the "/&*" are also returned
		while(stk.hasMoreTokens()) {

			String data = stk.nextToken(); 

			if(data.equals("/") == true)

				if(stk.hasMoreTokens())
					setAddress(stk.nextToken());

			if(data.equals("&") == true){

				String temp = null; 

				if(stk.hasMoreTokens())
					temp = stk.nextToken();

				if(temp != null && temp.length() >= 2 && temp.substring(0,2).equalsIgnoreCase("O ") == true){

					origin = temp.substring(2);
					originalOrigin = true; 
				}

				if(temp != null && temp.length() >= 2 && temp.substring(0,2).equalsIgnoreCase("VT") == true)
					setCensus(temp.substring(2));

				if(temp != null && temp.length() <= 1 || (temp.length() >= 2  && temp.substring(0,2).equalsIgnoreCase("VT") != true && temp.substring(0,2).equalsIgnoreCase("O ") != true))				
					setRegister(temp);
			}


			if(data.equals("*") == true){
				if(stk.hasMoreTokens()){
					origin = stk.nextToken();
					originalOrigin = true; 
				}
			}	
			
			if(data.equals("/") != true && data.equals("&") != true && data.equals("*") != true)  // This is first time
				origin = data;

		}	
			
		
		if(origin != null){
			
			String[] b = Utils.transformPlace(origin, null, null);
			
			System.out.println(origin + " " + b[0] +   "  " + b[1]);
			
			if(b[0] != null){
				setOriginStandardized(b[0]);
				setOriginID(new Integer(b[1]).intValue());
				setOriginFlag(1); 
			}
			else{
				setOriginStandardized(origin);
				setOriginID(0);
				setOriginFlag(-1); 

				if(b[1] != null)
					message(b[1], origin);
			}
			
		}
		else{
			setOriginStandardized(pd.getDynamicData2());
			setOriginID(0);
			setOriginFlag(-1); 
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

		String ddt = "AANKOMST          = "; 
		if(getOriginStandardized() != null){
			ddt += getOriginStandardized();			
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
		        "  " + ddt +
		        " Group = " + getOriginGroup()
		        );
		

	}
	
	/**
	 * 
	 * This routine truncates fields that are too long for the corresponding database columns
	 * 
	 */
	

	@Override
	public void truncate(){	
		
		String field = getOriginStandardized();
		int allowedSize = ConstTables.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B36_ST", "ORIGIN_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setOriginStandardized(field);
		}

		field = getAddress();
		allowedSize = ConstTables.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B36_ST", "ADDRESS", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setAddress(field);
		}

		field = getRegister();
		allowedSize = ConstTables.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B36_ST", "REGISTER", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setRegister(field);
		}

		field = getCensus();
		allowedSize = ConstTables.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B36_ST", "CENSUS", "" + allowedSize);
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


	
	public int getOriginID() {
		return originID;
	}

	public void setOriginID(int originID) {
		this.originID = originID;
	}

	public String getOriginStandardized() {
		return originStandardized;
	}

	public void setOriginStandardized(String originStandardized) {
		this.originStandardized = originStandardized;
	}

	public int getOriginFlag() {
		return originFlag;
	}

	public void setOriginFlag(int originFlag) {
		this.originFlag = originFlag;
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


	public int getOriginGroup() {
		return originGroup;
	}


	public void setOriginGroup(int originGroup) {
		this.originGroup = originGroup;
	}

	
	
}