package nl.iisg.ref;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 
 * This class contains data of one row of address reference data  
 *
 */
 
@Entity
@Table(name="ref_address")
public class Ref_Address {

	@Id @Column(name = "ADDRESS_ID")  private int       addressID;
	
	@Column(name = "ORIGINAL")   private String     original;
	
	@Column(name = "STREET_OR")   private String    streetOriginal;
	@Column(name = "STREET_ST")   private String    street;
	
	@Column(name = "QUARTER_OR")  private String    quarterOriginal;
	@Column(name = "QUARTER_ST")  private String    quarter;
	
	@Column(name = "PLACE_OR")    private String    placeOriginal;
	@Column(name = "PLACE_ST")    private String    place;
	
	@Column(name = "BOAT_OR")     private String    boatOriginal;
	@Column(name = "BOAT_ST")     private String    boat;
	
	@Column(name = "BERTH_OR")    private String    berthOriginal;
	@Column(name = "BERTH_ST")    private String    berth;
	
	@Column(name = "INSTIT_OR")   private String    institutionOriginal;
	@Column(name = "INSTIT_ST")   private String    institution;
	
	@Column(name = "LANDLORD_OR") private String    landlordOriginal;
	@Column(name = "LANDLORD_ST") private String    landlord;
	
	@Column(name = "OTHER_OR")    private String    otherOriginal;
	@Column(name = "OTHER_ST")    private String    other;

	@Column(name = "OA_OR")       private String    OldAddressOriginal;

	
	@Column(name = "NIEUWCODE")   private String    code;
	@Column(name = "STANDARD_SOURCE")   private String    source;
	@Transient                    private Boolean   needSave = false;

	
	static int current_ID = 0;
	
	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

		
	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getStreetOriginal() {
		return streetOriginal;
	}

	public void setStreetOriginal(String streetOriginal) {
		this.streetOriginal = streetOriginal;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getQuarterOriginal() {
		return quarterOriginal;
	}

	public void setQuarterOriginal(String quarterOriginal) {
		this.quarterOriginal = quarterOriginal;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getPlaceOriginal() {
		return placeOriginal;
	}

	public void setPlaceOriginal(String placeOriginal) {
		this.placeOriginal = placeOriginal;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getBoatOriginal() {
		return boatOriginal;
	}

	public void setBoatOriginal(String boatOriginal) {
		this.boatOriginal = boatOriginal;
	}

	public String getBoat() {
		return boat;
	}

	public void setBoat(String boat) {
		this.boat = boat;
	}

	public String getBerthOriginal() {
		return berthOriginal;
	}

	public void setBerthOriginal(String berthOriginal) {
		this.berthOriginal = berthOriginal;
	}

	public String getBerth() {
		return berth;
	}

	public void setBerth(String berth) {
		this.berth = berth;
	}

	public String getInstitutionOriginal() {
		return institutionOriginal;
	}

	public void setInstitutionOriginal(String institutionOriginal) {
		this.institutionOriginal = institutionOriginal;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getLandlordOriginal() {
		return landlordOriginal;
	}

	public void setLandlordOriginal(String landlordOriginal) {
		this.landlordOriginal = landlordOriginal;
	}

	public String getLandlord() {
		return landlord;
	}

	public void setLandlord(String landlord) {
		this.landlord = landlord;
	}

	public String getOtherOriginal() {
		return otherOriginal;
	}

	public void setOtherOriginal(String otherOriginal) {
		this.otherOriginal = otherOriginal;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public static int getCurrent_ID() {
		return current_ID++;
	}

	public static void setCurrent_ID(int current_ID) {
		Ref_Address.current_ID = current_ID;
	}

	public String getOldAddressOriginal() {
		return OldAddressOriginal;
	}

	public void setOldAddressOriginal(String oldAddressOriginal) {
		OldAddressOriginal = oldAddressOriginal;
	}

	public Boolean getNeedSave() {
		return needSave;
	}

	public void setNeedSave(Boolean needSave) {
		this.needSave = needSave;
	}

	
	
}
