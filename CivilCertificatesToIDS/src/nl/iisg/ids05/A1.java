package nl.iisg.ids05;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="a1")
public class A1 {
	
	@Id @Column(name = "IDNR")    private int       idnr;
	@Column(name = "ROLE")        private int       role;
	
	
	@Column(name = "START_DATE")  private String    startDate;
	@Column(name = "START_FLAG")  private int       startFlag;
	@Column(name = "START_EST")   private int       startEst;

	@Column(name = "END_DATE")    private String    endDate;
	@Column(name = "END_FLAG")    private int       endFlag;
	@Column(name = "END_EST")     private int       endEst;
	
	@Column(name = "MUNICIPALITY_ST")  private String  municipality;
	
	@Column(name = "LOCATION_NO")  private String  locationNumber;
	
	@Column(name = "ADDRESS_ID")  private int       addressID;
	
	@Column(name = "STREET_ST")   private String    street;
	@Column(name = "QUARTER_ST")  private String    quarter;
	@Column(name = "PLACE_ST")    private String    place;
	@Column(name = "BOAT_ST")     private String    boat;
	@Column(name = "BERTH_ST")    private String    berth;
	@Column(name = "INSTIT_ST")   private String    institution;//
	@Column(name = "LANDLORD_ST") private String    landlord;
	@Column(name = "OTHER_ST")    private String    other;
	@Column(name = "ADDRESS_FG")  private int       addressFlag;
	
	@Column(name = "NUMBER_ST")   private String    number;
	@Column(name = "ADDITION_ST") private String    addition;
	
    @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
    @Column(name="RecordID")     private int recordID;
    
    public A1(){} // because it must be accessed from a different package

	
	
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
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

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	
	/*

	public String getMunicipalityNumber() {
		return locationNumber;
	}

	public void setMunicipalityNumber(String municipalityNumber) {
		this.locationNumber = municipalityNumber;
	}
	
	*/

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

	public int getRecordID() {
		return recordID;
	}

	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}

	public String getLocationNumber() {
		return locationNumber;
	}

	public void setLocationNumber(String locationNumber) {
		this.locationNumber = locationNumber;
	}



	public int getIdnr() {
		return idnr;
	}



	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}

    
	
  }
