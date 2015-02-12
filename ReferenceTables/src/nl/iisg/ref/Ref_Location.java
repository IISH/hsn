/*
* Naam:    Ref_Location
* Version: 0.1
* Author:  Cor Munnik
* Copyright
*/
package nl.iisg.ref;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * This class contains data of one row of location reference data  
 *
 */

@Entity
@Table(name="ref_location")
public class Ref_Location{
	
	@Id @Column(name = "id_location")        private int       locationID;
	@Column(name = "original")               private String    original;
	@Column(name = "location_no")            private int       locationNo;
	@Column(name = "ambiguity")              private int       ambiguity;
	@Column(name = "location")               private String    location;
	@Column(name = "municipality")           private String    municipality;
	@Column(name = "province")               private String    province;
	@Column(name = "region")                 private String    region;
	@Column(name = "country")                private String    country;
	@Column(name = "standard_code")          private String    standardCode;
	@Column(name = "standard_source")        private String    standard_source;
	@Column(name = "standard_method")        private String    standard_method;
	@Transient                               private Boolean   needSave = false;

	
	static int current_ID = 0;


	public int getLocationID() {
		return locationID;
	}


	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}


	public String getOriginal() {
		return original;
	}


	public void setOriginal(String original) {
		this.original = original;
	}


	public int getLocationNo() {
		return locationNo;
	}


	public void setLocationNo(int locationNo) {
		this.locationNo = locationNo;
	}


	public int getAmbiguity() {
		return ambiguity;
	}


	public void setAmbiguity(int ambiguity) {
		this.ambiguity = ambiguity;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getMunicipality() {
		return municipality;
	}


	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getStandardCode() {
		return standardCode;
	}


	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}


	public String getStandard_source() {
		return standard_source;
	}


	public void setStandard_source(String standard_source) {
		this.standard_source = standard_source;
	}


	public String getStandard_method() {
		return standard_method;
	}


	public void setStandard_method(String standard_method) {
		this.standard_method = standard_method;
	}


	public Boolean getNeedSave() {
		return needSave;
	}


	public void setNeedSave(Boolean needSave) {
		this.needSave = needSave;
	}


	public static int getCurrent_ID() {
		return current_ID++;
	}


	public static void setCurrent_ID(int current_ID) {
		Ref_Location.current_ID = current_ID;
	}
	
	
	
	
	
	
}