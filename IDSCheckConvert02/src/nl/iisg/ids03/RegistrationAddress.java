/*
 * Naam:    RegistrationAddress
 * Version: 0.1
 *  Author: Cor Munnik
 * Copyright
 */
package nl.iisg.ids03;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import nl.iisg.hsncommon.Common1;
import nl.iisg.ref.*;



/**
 * 
 * This class handles the address attributes of a registration 
 *
 */
@Entity
@Table(name="b6")
public class RegistrationAddress {	

	@Id	@Column(name = "B1IDBG")  	private int   		 keyToSourceRegister;      
	@Id	@Column(name = "B2DIBG")    private int          dayEntryHead; 
	@Id	@Column(name = "B2MIBG")    private int          monthEntryHead; 
	@Id	@Column(name = "B2JIBG")    private int          yearEntryHead;  
	@Id	@Column(name = "IDNR")      private int          keyToRP;    

	@Id	@Column(name = "B2RNBG")    private int          keyToRegistrationPersons;
	@Id	@Column(name = "B6VRNR")    private int          sequenceNumberToAddresses; 

	@Column(name = "B6MDNR")        private int          dayOfAddress; 
	@Column(name = "B6MMNR")        private int          monthOfAddress; 
	@Column(name = "B6MJNR")        private int          yearOfAddress; 
	@Column(name = "B6MDCR")        private int          dayOfAddressAfterInterpretation; 
	@Column(name = "B6MMCR")        private int          monthOfAddressAfterInterpretation; 
	@Column(name = "B6MJCR")        private int          yearOfAddressAfterInterpretation; 

	@Column(name = "B6TPNR")        private String       addressType;
	@Column(name = "B6SINR")        private int          synchroneNumber;
	@Column(name = "B6STNR")        private String       nameOfStreet;
	@Column(name = "B6NRNR")        private String       number;
	@Column(name = "B6TVNR")        private String       additionToNumber;

	@Column(name = "OPDRNR")        private String		 orderNumber;
	@Column(name = "DATUM")     	private String   	 date0;
	@Column(name = "INIT")      	private String   	 initials;
	@Column(name = "VERSIE")    	private String   	 versionLastTimeOfDataEntry;
	@Column(name = "ONDRZKO")   	private String   	 researchCodeOriginal;
	@Column(name = "OPDRNRO")   	private String   	 orderNumberOriginal;
	@Column(name = "DATUMO")    	private String   	 dateOriginal;
	@Column(name = "INITO")     	private String   	 initialOriginal;
	@Column(name = "VERSIEO")   	private String   	 versionOriginalDataEntry;

	@Transient                      private Registration registrationToWhichAddressRefers;
	@Transient 						private RegistrationAddressStandardized standardizedRegistrationAddress = null;


	RegistrationAddress(){}
	

	/**
	 * 
	 * @param rowObjects
	 * 
	 * Constructor, initializes object from rowObjects
	 * 
	 */

	RegistrationAddress(Object [] rowObjects, String [] fieldnames, byte[] fieldtypes){

		System.out.println("In constructor");
		
		Utils.trimAll(rowObjects);
		
		
		
		setKeyToSourceRegister(                              (Integer)Utils.getColumn("B1IDBG", rowObjects, fieldnames, fieldtypes));
		setDayEntryHead(                                     (Integer)Utils.getColumn("B2DIBG", rowObjects, fieldnames, fieldtypes));
		setMonthEntryHead(                                   (Integer)Utils.getColumn("B2MIBG", rowObjects, fieldnames, fieldtypes));
		setYearEntryHead(                                    (Integer)Utils.getColumn("B2JIBG", rowObjects, fieldnames, fieldtypes));
		setKeyToRP(                                          (Integer)Utils.getColumn("IDNR", rowObjects, fieldnames, fieldtypes));

		
		
		setKeyToRegistrationPersons(                         (Integer)Utils.getColumn("B2RNBG", rowObjects, fieldnames, fieldtypes));
		System.out.println("Constructor " + (Integer)Utils.getColumn("B2RNBG", rowObjects, fieldnames, fieldtypes));
		
		setSequenceNumberToAddresses(                        (Integer)Utils.getColumn("B6VRNR", rowObjects, fieldnames, fieldtypes));

		setDayOfAddress(                                     (Integer)Utils.getColumn("B6MDNR", rowObjects, fieldnames, fieldtypes));
		setMonthOfAddress(                                   (Integer)Utils.getColumn("B6MMNR", rowObjects, fieldnames, fieldtypes));
		setYearOfAddress(                                    (Integer)Utils.getColumn("B6MJNR", rowObjects, fieldnames, fieldtypes));
		setDayOfAddressAfterInterpretation(                  (Integer)Utils.getColumn("B6MDCR", rowObjects, fieldnames, fieldtypes));
		setMonthOfAddressAfterInterpretation(                (Integer)Utils.getColumn("B6MDCR", rowObjects, fieldnames, fieldtypes));
		setYearOfAddressAfterInterpretation(                 (Integer)Utils.getColumn("B6MJCR", rowObjects, fieldnames, fieldtypes));

		setAddressType(                                      (String)Utils.getColumn("B6TPNR", rowObjects, fieldnames, fieldtypes));
		setSynchroneNumber(                                   (Integer)Utils.getColumn("B6SINR", rowObjects, fieldnames, fieldtypes));
		setNameOfStreet(                                     (String)Utils.getColumn("B6STNR", rowObjects, fieldnames, fieldtypes));
		setNumber(                                           (String)Utils.getColumn("B6NRNR", rowObjects, fieldnames, fieldtypes));
		setAdditionToNumber(                                 (String)Utils.getColumn("B6TVNR", rowObjects, fieldnames, fieldtypes));
		
		setOrderNumber(                                 	 (String)Utils.getColumn("OPDRNR", rowObjects, fieldnames, fieldtypes));
		setDate0(                                            (String)Utils.getColumn("DATUM", rowObjects, fieldnames, fieldtypes));	
		setInitials(                                         (String)Utils.getColumn("INIT", rowObjects, fieldnames, fieldtypes));
		setVersionLastTimeOfDataEntry(                       (String)Utils.getColumn("VERSIE", rowObjects, fieldnames, fieldtypes));
		setResearchCodeOriginal(                             (String)Utils.getColumn("ONDRZKO", rowObjects, fieldnames, fieldtypes));
		setOrderNumberOriginal(                              (String)Utils.getColumn("OPDRNRO", rowObjects, fieldnames, fieldtypes));
		setDateOriginal(                                     (String)Utils.getColumn("DATUMO", rowObjects, fieldnames, fieldtypes));
		setInitialOriginal(                                  (String)Utils.getColumn("INITO", rowObjects, fieldnames, fieldtypes));
		setVersionOriginalDataEntry(                         (String)Utils.getColumn("VERSIEO", rowObjects, fieldnames, fieldtypes));

		
        // now some immediate changes are performed for interpreted date fields
		
		adaptAddressDate();
	}

	RegistrationAddress(Object [] rowObjects){

		Utils.trimAll(rowObjects);
		
		setKeyToSourceRegister(                                       Utils.toInt(rowObjects[1]));
		setDayEntryHead(                        			          Utils.toInt(rowObjects[2]));
		setMonthEntryHead(                                 			  Utils.toInt(rowObjects[3]));
		setYearEntryHead(                                 			  Utils.toInt(rowObjects[4]));
		setKeyToRP(                                                   Utils.toInt(rowObjects[5])); 

		setKeyToRegistrationPersons(                                  Utils.toInt(rowObjects[6]));
		setSequenceNumberToAddresses(                                 Utils.toInt(rowObjects[7]));

		setDayOfAddress(                                              Utils.toInt(rowObjects[8]));
		setMonthOfAddress(                                            Utils.toInt(rowObjects[9])); 
		setYearOfAddress(                                             Utils.toInt(rowObjects[10]));
		setDayOfAddressAfterInterpretation(                           Utils.toInt(rowObjects[11]));
		setMonthOfAddressAfterInterpretation(                         Utils.toInt(rowObjects[12])); 
		setYearOfAddressAfterInterpretation(                          Utils.toInt(rowObjects[13]));

		setAddressType(                                              (String)rowObjects[14]);
		setSynchroneNumber(                                            Utils.toInt(rowObjects[15]));
		setNameOfStreet(                                             (String)rowObjects[16]);
		setNumber(                                                   (String)rowObjects[17]);
		setAdditionToNumber(                                         (String)rowObjects[18]);

		setOrderNumber(                                              (String)rowObjects[19]);
		setDate0(                                                      Utils.toStr(rowObjects[20]));
		setInitials(                                                 (String)rowObjects[21]);
		setVersionLastTimeOfDataEntry(                               (String)rowObjects[22]);
		setResearchCodeOriginal(                                     (String)rowObjects[23]);
		setOrderNumberOriginal(                                      (String)rowObjects[24]);
		setDateOriginal(                                             Utils.toStr(rowObjects[25]));
		setInitialOriginal(                                          (String)rowObjects[26]);
		setVersionOriginalDataEntry(                                 (String)rowObjects[27]);
		
        // now some immediate changes are performed for interpreted date fields
		
		adaptAddressDate();
	}

	public void add(Registration ra){
		setRegistrationToWhichAddressRefers(ra);
	}


	/**
	 * 
	 * @param ainb
	 * 
	 * This method performs checks on the address data of a registration
	 * The following message numbers can be issued:
	 * 
	 * 1085
	 * 1086
	 * 1089
	 * 1090
	 * 1091
	 * 1092
	 * 1093
	 * 1094
	 * 1095
	 * 1098
	 * 1131
	 * 1132
	 * 1134
	 * 1135
	 * 
	 */
	public void check(){
		
		//System.out.println("Check Address");

		//showFields();

		// Check address date not too far before or after range register
		//System.out.println("Check address date not too far before or after range register");

		int yearAddress  = getYearOfAddress();
		int monthAddress = getMonthOfAddress();
		int dayAddress   = getDayOfAddress();

		if(getYearOfAddressAfterInterpretation() > 0){
			yearAddress  = getYearOfAddressAfterInterpretation();
			monthAddress = getMonthOfAddressAfterInterpretation();
			dayAddress   = getDayOfAddressAfterInterpretation();
		}	
		
		int earliestDayOfAddress   = dayAddress   > 0 ? dayAddress : 1;
		int earliestMonthOfAddress = monthAddress > 0 ? monthAddress : 1;
		
		int latestDayOfAddress   = dayAddress   > 0 ? dayAddress : 28;
		int latestMonthOfAddress = monthAddress > 0 ? monthAddress : 12;

		if(yearAddress > 0){

			Ref_AINB ainb = Ref.getAINB(getKeyToSourceRegister());
			if(ainb != null){

				int registerStartYear = ainb.getStartYearRegisterCorrected() > 0 ? ainb.getStartYearRegisterCorrected() : ainb.getStartYearRegister();
				int registerEndYear   = ainb.getEndYearRegisterCorrected()   > 0 ? ainb.getEndYearRegisterCorrected()   : ainb.getEndYearRegister();
				if(registerStartYear > 0){
					if(yearAddress < registerStartYear - 1){
						message("1131", "" + yearAddress, "" + registerStartYear); 

					}
				}

				if(registerEndYear > 0){
					if(yearAddress > registerEndYear + 1){
						message("1132",  "" + yearAddress, "" + registerEndYear); 

					}
				}
			}

			// Check that (latest) date first address is no more than 5 days before date entry Head of Household			
						
			if(Common1.dateIsValid(latestDayOfAddress, latestMonthOfAddress, yearAddress) == 0 && getSequenceNumberToAddresses() == 1){

				int nl = Utils.dayCount(latestDayOfAddress, latestMonthOfAddress, yearAddress);				
				int n2 = Utils.dayCount(getDayEntryHead(), getMonthEntryHead(), getYearEntryHead());

				if(nl  < n2 - 5)	
					message("1134", "" + dayAddress + "-" +  monthAddress + "-" + yearAddress);

			}

			// Check that (earliest)  date first address is no more than 30 days after date entry Head of Household
			
			if(Common1.dateIsValid(earliestDayOfAddress, earliestMonthOfAddress, yearAddress) == 0 && getSequenceNumberToAddresses() == 1){

				int ne = Utils.dayCount(earliestDayOfAddress, earliestMonthOfAddress, yearAddress);				
				int n2 = Utils.dayCount(getDayEntryHead(), getMonthEntryHead(), getYearEntryHead());

				if(ne > n2 + 30)	
					message("1135", "" + dayAddress + "-" +  monthAddress + "-" + yearAddress);
			}

		}

		if(getNameOfStreet() != null){

			// Check that street name does not contain (meldcode) '@'
			
			if(getNameOfStreet().indexOf("@") >= 0){
				message("1085"); 
			}

			// Check that street name does not contain (meldcode) '#'
			
			if(getNameOfStreet().indexOf("#") >= 0){
				message("1086"); 
			}

			// Check that street name does not contain (meldcode) '*' more than once
			
			if(getNameOfStreet().indexOf("*") >= 0){
				String temp = getNameOfStreet();
				temp = temp.substring(temp.indexOf("*") + 1); 

				if(temp.indexOf("*") >= 0) 
					message("1089", getNameOfStreet()); 
			}

			// Check that street name does not contain (meldcode) '/' more than once
			
			if(getNameOfStreet().indexOf("/") >= 0){
				String temp = getNameOfStreet();
				temp = temp.substring(temp.indexOf("/") + 1); 

				if(temp.indexOf("/") >= 0) 
					message("1090", getNameOfStreet()); 
			}

			// Check if street name contains "{}" characters. If so, the character before the "}" must be numeric
			
			if(getNameOfStreet().indexOf("}") >= 0){
				String temp = getNameOfStreet();
				int index = temp.indexOf("}");
				if(index > 0) {
					char c = temp.charAt(index-1); 
					if(Character.isDigit(c) != true){
						message("1092", getNameOfStreet()); 
					}
				}
				else  // field starts with "}", also not allowed
					message("1092", getNameOfStreet());

			}

			// Check if street name contains "/" characters. If so, the characters before and after the "/" must not be both numeric
			
			if(getNameOfStreet().indexOf("/") >= 0){
				String temp = getNameOfStreet();
				int index = temp.indexOf("/");
				if(index > 0){
					char c = temp.charAt(index-1);
					if(index + 1 < temp.length()){
						char d =  temp.charAt(index+1);
						if(Character.isDigit(c) == true  && Character.isDigit(d) == true)
							message("1094",  getNameOfStreet()); 
					}
				}

			}

			// Check if street name contains "/" characters. If so, the characters before it must not be a letter (must be numeric)
			// Only for [b6tpnr] = 'OA'
			
			if(getAddressType().equalsIgnoreCase("OA")) {
				if(getNameOfStreet().indexOf("/") >= 0){
					String temp = getNameOfStreet();
					int index = temp.indexOf("/");
					if(index > 0){

						char c = temp.charAt(index-1);
						if(Character.isDigit(c) != true){
							message("1093",  getNameOfStreet()); 
						}
					}
				}
			}
		}

		// Check that address type is filled in and valid
		
		if(getAddressType() != null){
			String type = getAddressType();
			if(type.equalsIgnoreCase("OA") != true &&
					type.equalsIgnoreCase("ST") != true && 
					type.equalsIgnoreCase("WK") != true && 
					type.equalsIgnoreCase("PL") != true && 
					type.equalsIgnoreCase("BO") != true && 
					type.equalsIgnoreCase("LP") != true && 
					type.equalsIgnoreCase("GS") != true && 
					type.equalsIgnoreCase("KB") != true && 
					type.equalsIgnoreCase("AN") != true && 
					type.equalsIgnoreCase("WG") != true){

				message("1095", getAddressType());   	

			}
		}
		else
			message("1095", "");


		// Check that address type is not in old format
		
		if(getAddressType() != null && getAddressType().equalsIgnoreCase("OA")){
			message("1091");   	
		}

		// Check that house number does not contain character. But '-' is OK. But '0' is not
		
		if(getNumber() != null){
			String number = getNumber().trim();  // Number is String ;-)
			if(number.equals("0")) message("1098", getNumber());
			else {
				for(int i = 0; i < number.length(); i++){
					if(Character.isDigit(number.charAt(i)) != true && number.charAt(i) != '-'){
						message("1098", getNumber());
						break;
					}
				}
			}
		}



	}
	/**
	 * 
	 * If a valid (>0) Interpretation day/month/year is given, it is copied to the original date
	 * If an original day/month/year == -3, it is set to -1
	 * 
	 */

    private void adaptAddressDate(){
    
    	setDayOfAddress(Utils.convertDateElement(getDayOfAddress(), getDayOfAddressAfterInterpretation()));
    	setMonthOfAddress(Utils.convertDateElement(getMonthOfAddress(), getMonthOfAddressAfterInterpretation()));
    	setYearOfAddress(Utils.convertDateElement(getYearOfAddress(), getYearOfAddressAfterInterpretation()));
    }

    
    /**
     * 
     * This routine prints out essential fields of the object
     * 
     */

    public void print(){
    	
    	showFields();
    }
    /**
     * 
     * This routine prints out essential fields of the object
     * 
     */
    
    public void showFields(){
    	

		String EntryDateHead = String.format("%02d-%02d-%04d", getDayEntryHead(), getMonthEntryHead(), getYearEntryHead());
        String addressDate   = String.format("%02d-%02d-%04d", getDayOfAddress(), getMonthOfAddress(), getYearOfAddress()); 
		
    	
		  System.out.println("" +
		  		"" + getKeyToRP() +
		  		"  " + EntryDateHead +		  		
		  		"  " + getKeyToSourceRegister() + 
		  		"  " + getSequenceNumberToAddresses() +
		  		"  " + addressDate +
		  		"  " + getKeyToRegistrationPersons() +  
		  		"  " + getAddressType() +
		  		"  " + getSynchroneNumber() +
		  		"  " + getNameOfStreet().trim() +
		  		"  " + getNumber().trim() +
		  		"  " + getAdditionToNumber().trim()
		  		);

    }

	/**
	 * 
	 * This routine allocates a Message object and fills it with the parameters.
	 * Additional information is added to identify the B*-row(s) to which the message applies
	 * 
	 * @param number
	 * @param fills
	 */


	private void message(String number, String... fills){
		
		Message m = new Message(number);
		
		m.setKeyToRP(getKeyToRP());
		
		m.setKeyToSourceRegister(getKeyToSourceRegister());
		
		m.setDayEntryHead(getDayEntryHead());
		m.setMonthEntryHead(getMonthEntryHead());
		m.setYearEntryHead(getYearEntryHead());
		
		m.setDayEntryRP(getRegistrationToWhichAddressRefers().getDayEntryRP());
		m.setMonthEntryRP(getRegistrationToWhichAddressRefers().getMonthEntryRP());
		m.setYearEntryRP(getRegistrationToWhichAddressRefers().getYearEntryRP());	
		
		m.save(fills); 
		
		
	}


	public int getKeyToSourceRegister() {
		return keyToSourceRegister;
	}


	public void setKeyToSourceRegister(int keyToSourceRegister) {
		this.keyToSourceRegister = keyToSourceRegister;
	}


	public int getDayEntryHead() {
		return dayEntryHead;
	}


	public void setDayEntryHead(int dayEntryHead) {
		this.dayEntryHead = dayEntryHead;
	}


	public int getMonthEntryHead() {
		return monthEntryHead;
	}


	public void setMonthEntryHead(int monthEntryHead) {
		this.monthEntryHead = monthEntryHead;
	}


	public int getYearEntryHead() {
		return yearEntryHead;
	}


	public void setYearEntryHead(int yearEntryHead) {
		this.yearEntryHead = yearEntryHead;
	}


	public int getKeyToRP() {
		return keyToRP;
	}


	public void setKeyToRP(int keyToRP) {
		this.keyToRP = keyToRP;
	}


	public int getKeyToRegistrationPersons() {
		return keyToRegistrationPersons;
	}


	public void setKeyToRegistrationPersons(int keyToRegistrationPersons) {
		this.keyToRegistrationPersons = keyToRegistrationPersons;
	}


	public int getSequenceNumberToAddresses() {
		return sequenceNumberToAddresses;
	}


	public void setSequenceNumberToAddresses(int sequenceNumberToAddresses) {
		this.sequenceNumberToAddresses = sequenceNumberToAddresses;
	}


	public int getDayOfAddress() {
		return dayOfAddress;
	}


	public void setDayOfAddress(int dayOfAddress) {
		this.dayOfAddress = dayOfAddress;
	}


	public int getMonthOfAddress() {
		return monthOfAddress;
	}


	public void setMonthOfAddress(int monthOfAddress) {
		this.monthOfAddress = monthOfAddress;
	}


	public int getYearOfAddress() {
		return yearOfAddress;
	}


	public void setYearOfAddress(int yearOfAddress) {
		this.yearOfAddress = yearOfAddress;
	}


	public int getDayOfAddressAfterInterpretation() {
		return dayOfAddressAfterInterpretation;
	}


	public void setDayOfAddressAfterInterpretation(
			int dayOfAddressAfterInterpretation) {
		this.dayOfAddressAfterInterpretation = dayOfAddressAfterInterpretation;
	}


	public int getMonthOfAddressAfterInterpretation() {
		return monthOfAddressAfterInterpretation;
	}


	public void setMonthOfAddressAfterInterpretation(
			int monthOfAddressAfterInterpretation) {
		this.monthOfAddressAfterInterpretation = monthOfAddressAfterInterpretation;
	}


	public int getYearOfAddressAfterInterpretation() {
		return yearOfAddressAfterInterpretation;
	}


	public void setYearOfAddressAfterInterpretation(
			int yearOfAddressAfterInterpretation) {
		this.yearOfAddressAfterInterpretation = yearOfAddressAfterInterpretation;
	}


	public String getAddressType() {
		return addressType;
	}


	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}


	public int getSynchroneNumber() {
		return synchroneNumber;
	}


	public void setSynchroneNumber(int sequenceNumber) {
		this.synchroneNumber = sequenceNumber;
	}


	public String getNameOfStreet() {
		return nameOfStreet;
	}


	public void setNameOfStreet(String nameOfStreet) {
		this.nameOfStreet = nameOfStreet;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getAdditionToNumber() {
		return additionToNumber;
	}


	public void setAdditionToNumber(String additionToNumber) {
		this.additionToNumber = additionToNumber;
	}


	public String getOrderNumber() {
		return orderNumber;
	}


	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}


	public String getDate0() {
		return date0;
	}


	public void setDate0(String date0) {
		this.date0 = date0;
	}


	public String getInitials() {
		return initials;
	}


	public void setInitials(String initials) {
		this.initials = initials;
	}


	public String getVersionLastTimeOfDataEntry() {
		return versionLastTimeOfDataEntry;
	}


	public void setVersionLastTimeOfDataEntry(String versionLastTimeOfDataEntry) {
		this.versionLastTimeOfDataEntry = versionLastTimeOfDataEntry;
	}


	public String getResearchCodeOriginal() {
		return researchCodeOriginal;
	}


	public void setResearchCodeOriginal(String researchCodeOriginal) {
		this.researchCodeOriginal = researchCodeOriginal;
	}


	public String getOrderNumberOriginal() {
		return orderNumberOriginal;
	}


	public void setOrderNumberOriginal(String orderNumberOriginal) {
		this.orderNumberOriginal = orderNumberOriginal;
	}


	public String getDateOriginal() {
		return dateOriginal;
	}


	public void setDateOriginal(String dateOriginal) {
		this.dateOriginal = dateOriginal;
	}


	public String getInitialOriginal() {
		return initialOriginal;
	}


	public void setInitialOriginal(String initialOriginal) {
		this.initialOriginal = initialOriginal;
	}


	public String getVersionOriginalDataEntry() {
		return versionOriginalDataEntry;
	}


	public void setVersionOriginalDataEntry(String versionOriginalDataEntry) {
		this.versionOriginalDataEntry = versionOriginalDataEntry;
	}


	public Registration getRegistrationToWhichAddressRefers() {
		return registrationToWhichAddressRefers;
	}


	public void setRegistrationToWhichAddressRefers(
			Registration registrationToWhichAddressRefers) {
		this.registrationToWhichAddressRefers = registrationToWhichAddressRefers;
	}


	public RegistrationAddressStandardized getStandardizedRegistrationAddress() {
		return standardizedRegistrationAddress;
	}


	public void setStandardizedRegistrationAddress(
			RegistrationAddressStandardized standardizedRegistrationAddress) {
		this.standardizedRegistrationAddress = standardizedRegistrationAddress;
	}
	
	
}