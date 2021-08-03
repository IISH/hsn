package nl.iisg.ids04;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="b6_st")
public class B6_ST {
	
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
	
	@Transient B4_ST registration;
	
	B6_ST(){}
	
	
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


	public B4_ST getRegistration() {
		return registration;
	}


	public void setRegistration(B4_ST registration) {
		this.registration = registration;
	}


	public String getMunicipality() {
		return municipality;
	}


	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}



}
