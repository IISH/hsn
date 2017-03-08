package nl.iisg.convertPK;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import nl.iisg.ref.Ref;
import nl.iisg.ref.Ref_AINB;
import nl.iisg.ref.Ref_Address;
import nl.iisg.ref.Ref_Housenumber;
import nl.iisg.ref.Ref_Housenumberaddition;

@Entity
@Table(name="b6_st")
public class B6_ST {
	
	@Id @Column(name = "B1IDBG")  private int       keyToSourceRegister;
	@Id @Column(name = "B2DIBG")  private String    dateEntryHeadOfHousehold;                      
	@Id @Column(name = "IDNR")    private int       keyToRP;
	@Id	@Column(name = "B2RNBG")  private int       keyToRegistrationPersons;
	
	@Column(name = "B6VRNR")      private int       sequenceNumberToAddresses;
	
	@Column(name = "B6MDNR")      private String    dateOfAddress;
	@Column(name = "B6MDFG")      private int       dateOfAddressFlag;
	
	@Column(name = "START_DATE")  private String    startDate;
	@Column(name = "START_FLAG")  private int       startFlag;
	@Column(name = "START_EST")   private int       startEst;

	@Column(name = "END_DATE")    private String    endDate;
	@Column(name = "END_FLAG")    private int       endFlag;
	@Column(name = "END_EST")     private int       endEst;
	
	@Column(name = "MUNICIPALITY_ST")  private String  municipality;
	@Column(name = "MUNICIPALITY_NO")  private int  municipalityNumber;
	@Column(name = "POSTAL_CODE") private String     zipCode;
	@Column(name = "RENUMBERING") private int	     renumbering;
	
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
	
	@Transient                    private B2_ST     person;

	
	B6_ST(){};
	
	public void save(EntityManager em){
		
		em.persist(this);
		
	}
	
	
	public void convertAddress(){

				
		Ref_Address  refAdd = Ref.getAddress(getStreet(), getQuarter(),	getPlace(), getBoat(), getBerth(), getInstitution(), getLandlord(), getOther());
		if(refAdd != null && refAdd.getCode() != null && (refAdd.getCode().equalsIgnoreCase("y") == true || refAdd.getCode().equalsIgnoreCase("u") == true)){
			
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
			if(refHousenumber != null && refHousenumber.getCode() != null && (refHousenumber.getCode().equalsIgnoreCase("y") || refHousenumber.getCode().equalsIgnoreCase("u"))){
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
			if(refHousenumberaddition != null && refHousenumberaddition.getCode() != null && (refHousenumberaddition.getCode().equalsIgnoreCase("y") ||refHousenumberaddition.getCode().equalsIgnoreCase("u"))){
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
		int allowedSize = TableDefinitions.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B6_ST", "STREET_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setStreet(field);
		}
			
		field = getQuarter();
		allowedSize = TableDefinitions.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B6_ST", "QUARTER_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setQuarter(field);
		}
			
		field = getPlace();
		allowedSize = TableDefinitions.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B6_ST", "PLACE_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setPlace(field);
		}
			
		field = getBoat();
		allowedSize = TableDefinitions.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B6_ST", "BOAT_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setBoat(field);
		}
			
		field = getBerth();
		allowedSize = TableDefinitions.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B6_ST", "BERTH_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setBerth(field);
		}
			
		field = getLandlord();
		allowedSize = TableDefinitions.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B6_ST", "LANDLORD_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setLandlord(field);
		}
			
		field = getInstitution();
		allowedSize = TableDefinitions.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B6_ST", "INSTIT_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setInstitution(field);
		}
			
		field = getOther();
		allowedSize = TableDefinitions.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B6_ST", "OTHER_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setOther(field);
		}
			
		field = getNumber();
		allowedSize = TableDefinitions.Smallstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B6_ST", "NUMBER_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setNumber(field);
		}
			
		field = getAddition();
		allowedSize = TableDefinitions.Smallstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B6_ST", "ADDITION_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setAddition(field);
		}
			
		
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

	public B2_ST getPerson() {
		return person;
	}

	public void setPerson(B2_ST person) {
		this.person = person;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public int getRenumbering() {
		return renumbering;
	}

	public void setRenumbering(int renumbering) {
		this.renumbering = renumbering;
	}

	public int getMunicipalityNumber() {
		return municipalityNumber;
	}

	public void setMunicipalityNumber(int municipalityNumber) {
		this.municipalityNumber = municipalityNumber;
	}

	
	
}
