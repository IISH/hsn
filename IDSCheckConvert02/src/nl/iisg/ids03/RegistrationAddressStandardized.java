  package nl.iisg.ids03;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import nl.iisg.ref.*;

/**
 * 
 * This class handles a standardized Registrationaddress
 *
 */

@Entity
@Table(name="b6_st")
public class RegistrationAddressStandardized {

	@Id @Column(name = "B1IDBG")  private int       keyToSourceRegister;
	@Id @Column(name = "B2DIBG")  private String    dateEntryHeadOfHousehold;                      
	@Id @Column(name = "IDNR")    private int       keyToRP;
	@Id	@Column(name = "B2RNBG")  private int       keyToRegistrationPersons;
	
	@Id @Column(name = "B6VRNR")  private int       sequenceNumberToAddresses;
	
	@Column(name = "B6MDNR")      private String    dateOfAddress;
	@Column(name = "B6MDFG")      private int       dateOfAddressFlag;
	
	@Column(name = "START_DATE")  private String    startDate;
	@Column(name = "START_FLAG")  private int       startFlag;
	@Column(name = "START_EST")   private int       startEst;

	@Column(name = "END_DATE")    private String    endDate;
	@Column(name = "END_FLAG")    private int       endFlag;
	@Column(name = "END_EST")     private int       endEst;
	
	@Column(name = "MUNICIPALITY_ST")  private String  municipality;
	
	@Column(name = "ADDRESS_ID")  private int       addressID;
	
	@Column(name = "STREET_ST")   private String    street;
	@Column(name = "QUARTER_ST")  private String    quarter;
	@Column(name = "PLACE_ST")    private String    place;
	@Column(name = "BOAT_ST")     private String    boat;
	@Column(name = "BERTH_ST")    private String    berth;
	@Column(name = "INSTIT_ST")   private String    institution;
	@Column(name = "LANDLORD_ST") private String    landlord;
	@Column(name = "OTHER_ST")    private String    other;
	@Column(name = "ADDRESS_FG")  private int       addressFlag;
	
	@Column(name = "NUMBER_ST")   private String    number;
	@Column(name = "ADDITION_ST") private String    addition;
	
	@Column(name = "VERSIE")      private String    versionLastTimeOfDataEntry;
	@Column(name = "ONDRZKO")     private String    researchCodeOriginal;
	@Column(name = "VERSIEO")     private String    versionOriginalDataEntry;
	@Column(name = "DATUM")       private String    date0;
	
	@Transient                    private RegistrationAddress originalAddress;
	@Transient  				  private boolean			 oldAddressTotallyProcessed = false;


	/**
	 * 
	 * No args constructor used by JPA
	 */
	
	RegistrationAddressStandardized(){}

	/**
	 * Constructor: RegistrationAddressStandardized is created out of a RegistrationAddress
	 * 
	 * @param ra
	 */

	RegistrationAddressStandardized(RegistrationAddress ra){

		
		setKeyToSourceRegister(ra.getKeyToSourceRegister());
		setKeyToRP(ra.getKeyToRP());
		setSequenceNumberToAddresses(ra.getSequenceNumberToAddresses());
		setKeyToRegistrationPersons(ra.getKeyToRegistrationPersons());

        setDate0(ra.getDate0());  
		setVersionLastTimeOfDataEntry(ra.getVersionLastTimeOfDataEntry());
		setResearchCodeOriginal(ra.getResearchCodeOriginal());
		setVersionOriginalDataEntry(ra.getVersionOriginalDataEntry());

	}

	/**
	 * This routine transforms some fields into a new format 
	 * 
	 * @param ra
	 */
	public void transform(RegistrationAddress ra){

		transformHeadOfHouseholdDate(ra);
		transformDateOfAddress(ra);
	}



    public void transformHeadOfHouseholdDate(RegistrationAddress ra){
		
		String temp = String.format("%02d-%02d-%04d", ra.getDayEntryHead(), ra.getMonthEntryHead(), ra.getYearEntryHead());
		setDateEntryHeadOfHousehold(temp);		
	}

	public void transformDateOfAddress(RegistrationAddress ra){
		
		
		int[] result = 	Utils2.transformDateFields(ra.getDayOfAddress(),                    ra.getMonthOfAddress(),                    ra.getYearOfAddress(),
				                                   ra.getDayOfAddressAfterInterpretation(), ra.getMonthOfAddressAfterInterpretation(), ra.getYearOfAddressAfterInterpretation());
		
		setDateOfAddress(String.format("%02d-%02d-%04d", result[0], result[1], result[2]));
		setDateOfAddressFlag(result[3]);
		
	}

	

    /**
	 * 
	 * 
	 * This method converts the RegistrationAddress Object to the new format
	 * 
	 */
	
	public void convertAddress(){

			
		Ref_AINB ainb = Ref.getAINB(getKeyToSourceRegister());	
		
		setMunicipality(ainb.getMunicipality());
		
		if(getLandlord() != null && getLandlord().equalsIgnoreCase("inw$"))
			setLandlord("Living in");
				
		Ref_Address  refAdd = Ref.getAddress(getStreet(), getQuarter(),	getPlace(), getBoat(), getBerth(), getInstitution(), getLandlord(), getOther());
		if(refAdd != null && refAdd.getCode() != null && (refAdd.getCode().equalsIgnoreCase("y") == true)){
			
			setStreet(refAdd.getStreet());
			setQuarter(refAdd.getQuarter());
			setPlace(refAdd.getPlace());
			setBoat(refAdd.getBoat());
			setBerth(refAdd.getBerth());
			setInstitution(refAdd.getInstitution());
			setLandlord(refAdd.getLandlord());
			setOther(refAdd.getOther());
			setAddressID(refAdd.getAddressID());
			//setAddressFlag(refAdd.); // new address type
			
		}
		else{
			
			setAddressID(-1);  // indicate that we have no reference values (but the original values)
			
			refAdd = new Ref_Address();

			if(getOriginalAddress().getAddressType().equals("OA")){
				if(isOldAddressTotallyProcessed())
					refAdd.setOldAddressOriginal("Old Address");
				else
					refAdd.setOldAddressOriginal(getOriginalAddress().getNameOfStreet());
			}
 
			refAdd.setStreetOriginal(getStreet());
			refAdd.setQuarterOriginal(getQuarter());
			refAdd.setPlaceOriginal(getPlace());
			refAdd.setBoatOriginal(getBoat());
			refAdd.setBerthOriginal(getBerth());
			refAdd.setInstitutionOriginal(getInstitution());
			refAdd.setLandlordOriginal(getLandlord());
			refAdd.setOtherOriginal(getOther());		
			
			refAdd.setCode("x");
			
			Ref.addAddress(refAdd);
			
			//setAddressID(refAdd.getAddressID());
			
		}
		
		if(getNumber() != null && getNumber().trim().length() != 0){
			Ref_Housenumber  refHousenumber = Ref.getHousenumber(getNumber());
			if(refHousenumber != null && refHousenumber.getCode() != null && (refHousenumber.getCode().equalsIgnoreCase("y") == true)){
				setNumber(refHousenumber.getHousenumber());
			}
			else{

				refHousenumber = new Ref_Housenumber();
				refHousenumber.setOriginal(getNumber());

				refHousenumber.setCode("x");

				Ref.addHousenumber(refHousenumber);

				//setAddressID(refAdd.getAddressID());

			}
		}

		if(getAddition() != null && getAddition().trim().length() != 0){
			Ref_Housenumberaddition  refHousenumberaddition = Ref.getHousenumberaddition(getAddition());
			if(refHousenumberaddition != null && refHousenumberaddition.getCode() != null && (refHousenumberaddition.getCode().equalsIgnoreCase("y") == true)){
				setAddition(refHousenumberaddition.getAddition());
			}
			else{

				refHousenumberaddition = new Ref_Housenumberaddition();
				refHousenumberaddition.setOriginal(getAddition());

				refHousenumberaddition.setCode("x");

				Ref.addHousenumberaddition(refHousenumberaddition);

				//setAddressID(refAdd.getAddressID());

			}
		}

		
		
	}

	/**
	 * 
	 * This routine truncates fields that are too long for the corresponding database columns
	 * 
	 */
	
	
	public void truncate(){	
		
		String field = getStreet();
		int allowedSize = ConstTables.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B6_ST", "STREET_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setStreet(field);
		}
			
		field = getQuarter();
		allowedSize = ConstTables.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B6_ST", "QUARTER_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setQuarter(field);
		}
			
		field = getPlace();
		allowedSize = ConstTables.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B6_ST", "PLACE_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setPlace(field);
		}
			
		field = getBoat();
		allowedSize = ConstTables.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B6_ST", "BOAT_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setBoat(field);
		}
			
		field = getBerth();
		allowedSize = ConstTables.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B6_ST", "BERTH_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setBerth(field);
		}
			
		field = getLandlord();
		allowedSize = ConstTables.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B6_ST", "LANDLORD_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setLandlord(field);
		}
			
		field = getInstitution();
		allowedSize = ConstTables.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B6_ST", "INSTIT_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setInstitution(field);
		}
			
		field = getOther();
		allowedSize = ConstTables.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B6_ST", "OTHER_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setOther(field);
		}
			
		field = getNumber();
		allowedSize = ConstTables.Smallstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B6_ST", "NUMBER_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setNumber(field);
		}
			
		field = getAddition();
		allowedSize = ConstTables.Smallstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B6_ST", "ADDITION_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setAddition(field);
		}
			
		
	}
	




    /**
     * 
     * This routine writes the Standardized registration address object (if there is one) to the database
     * 
     */

	public void write(){

		Utils.persist(this);

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
    
    private void showFields(){
    	
		String startDate = getStartDate() != null ? getStartDate() : "00-00-0000";
		String startFlag = String.format("%02d", getStartFlag());
		String startEst  = String.format("%03d", getStartEst());

		String endDate = getEndDate() != null ? getEndDate() : "00-00-0000";
		String endFlag = String.format("%02d", getEndFlag());
		String endEst  = String.format("%03d", getEndEst());

		String keyToPersons = String.format("%2d", getKeyToRegistrationPersons());
		String addressID    = String.format("%4d", getAddressID());

		
		System.out.println(startDate +
				" " + startFlag +			
				" " + startEst +			
				" " + endDate +
				" " + endFlag +			
				" " + endEst +			
		  		"   " + keyToPersons + 
		  		"        " + getDateOfAddress() + 
		  		"   " + addressID +
		  		"  " + (getStreet()     != null ? getStreet() : "") +
		  		"  " + (getQuarter()    != null ? getQuarter() : "") +
		  		"  " + (getPlace()      != null ? getPlace() : "") +
		  		"  " + (getBoat()       != null ? getBoat() : "") +
		  		"  " + (getBerth()      != null ? getBerth() : "") +
		  		"  " + (getInstitution()!= null ? getInstitution() : "") +
		  		"  " + (getLandlord()   != null ? getLandlord() : "") +
		  		"  " + (getNumber()     != null ? getNumber().trim() : "") +
		  		"  " + (getAddition()   != null ? getAddition().trim() : "")
		  		);
		

    }

	private void message(String number, String... fills){
		
		Message m = new Message(number);
		
		m.setKeyToRP(getKeyToRP());
		
		m.setKeyToSourceRegister(getKeyToSourceRegister());
		
		m.setDayEntryHead((new Integer(getDateEntryHeadOfHousehold().substring(0,2)).intValue()));
		m.setMonthEntryHead((new Integer(getDateEntryHeadOfHousehold().substring(3,5)).intValue()));
		m.setYearEntryHead((new Integer(getDateEntryHeadOfHousehold().substring(6,10)).intValue()));
		
		
		m.setKeyToRegistrationPersons(getKeyToRegistrationPersons());
		
		m.save(fills); 
		
		
	}


	

	
	
	public int getKeyToSourceRegister() {
		return keyToSourceRegister;
	}


	public void setKeyToSourceRegister(int keyToSourceRegister) {
		this.keyToSourceRegister = keyToSourceRegister;
	}


	public String getDateEntryHeadOfHousehold() {
		return dateEntryHeadOfHousehold;
	}


	public void setDateEntryHeadOfHousehold(String dateEntryHeadOfHousehold) {
		this.dateEntryHeadOfHousehold = dateEntryHeadOfHousehold;
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


	public String getDateOfAddress() {
		return dateOfAddress;
	}


	public void setDateOfAddress(String dateOfAddress) {
		this.dateOfAddress = dateOfAddress;
	}


	public int getDateOfAddressFlag() {
		return dateOfAddressFlag;
	}


	public void setDateOfAddressFlag(int dateOfAddressFlag) {
		this.dateOfAddressFlag = dateOfAddressFlag;
	}


	public int getAddressID() {
		return addressID;
	}


	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getQuarter() {
		return quarter;
	}


	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public String getBoat() {
		return boat;
	}


	public void setBoat(String boat) {
		this.boat = boat;
	}


	public String getBerth() {
		return berth;
	}


	public void setBerth(String berth) {
		this.berth = berth;
	}


	public String getInstitution() {
		return institution;
	}


	public void setInstitution(String institution) {
		this.institution = institution;
	}


	public String getLandlord() {
		return landlord;
	}


	public void setLandlord(String landlord) {
		this.landlord = landlord;
	}


	public String getOther() {
		return other;
	}


	public void setOther(String other) {
		this.other = other;
	}


	public int getAddressFlag() {
		return addressFlag;
	}


	public void setAddressFlag(int addressFlag) {
		this.addressFlag = addressFlag;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getAddition() {
		return addition;
	}


	public void setAddition(String addition) {
		this.addition = addition;
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


	public String getVersionOriginalDataEntry() {
		return versionOriginalDataEntry;
	}


	public void setVersionOriginalDataEntry(String versionOriginalDataEntry) {
		this.versionOriginalDataEntry = versionOriginalDataEntry;
	}


	public String getDate0() {
		return date0;
	}


	public void setDate0(String date0) {
		this.date0 = date0;
	}

	public RegistrationAddress getOriginalAddress() {
		return originalAddress;
	}

	public void setOriginalAddress(RegistrationAddress originalAddress) {
		this.originalAddress = originalAddress;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public int getStartFlag() {
		return startFlag;
	}

	public void setStartFlag(int startFlag) {
		this.startFlag = startFlag;
	}

	public int getStartEst() {
		return startEst;
	}

	public void setStartEst(int startEst) {
		this.startEst = startEst;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getEndFlag() {
		return endFlag;
	}

	public void setEndFlag(int endFlag) {
		this.endFlag = endFlag;
	}

	public int getEndEst() {
		return endEst;
	}

	public void setEndEst(int endEst) {
		this.endEst = endEst;
	}

	public boolean isOldAddressTotallyProcessed() {
		return oldAddressTotallyProcessed;
	}

	public void setOldAddressTotallyProcessed(boolean oldAddressTotallyProcessed) {
		this.oldAddressTotallyProcessed = oldAddressTotallyProcessed;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}


}
