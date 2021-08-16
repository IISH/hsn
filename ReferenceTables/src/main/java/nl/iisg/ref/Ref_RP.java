/*
* Naam:    Ref_GB
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
 * This class contains data of one RP row of reference data  
 *
 */ 
@Entity
@Table(name="HSNRP")
public class Ref_RP {

			
   	@Id@Column(name = "Id")   		private int          id;
 	@Column(name = "IDNR")       	private int          keyToRP;
 	@Column(name = "ID_origin")     private int          originId;
 	@Column(name = "Project")       private String       project;
 	@Column(name = "Gemnr")       	private int          gemnr;
 	@Column(name = "Valid_day")     private int          validDay;
 	@Column(name = "Valid_month")   private int          validMonth;
 	@Column(name = "Valid_year")    private int          validYear;
 	@Column(name = "RP_family")     private String       lastNameRP;
 	@Column(name = "RP_prefix")     private String       prefixRP;
 	@Column(name = "RP_firstname")  private String       firstNameRP;
 	@Column(name = "RP_B_DAY")      private int          dayOfBirthRP;
 	@Column(name = "RP_B_MONTH")    private int          monthOfBirthRP;
	@Column(name = "RP_B_YEAR")     private int          yearOfBirthRP;
	@Column(name = "RP_B_SEX")      private String       sexRP; 
	@Column(name = "RP_B_PLACE")    private String       birthPlaceRP;
	@Column(name = "RP_B_PROV")     private int          birthProvinceRP;
	@Column(name = "RP_B_COH")      private int          birthCohortRP; 
	@Column(name = "MO_family")     private String       lastNameMother;
    @Column(name = "MO_prefix")     private String       prefixMother;
 	@Column(name = "MO_firstname")  private String       firstNameMother;
 	@Column(name = "FA_family")     private String       lastNameFather; 
 	@Column(name = "FA_prefix")     private String       prefixFather;
 	@Column(name = "FA_firstname")  private String       firstNameFather; 
		
	//@Transient        		        private Boolean      needSave = false;
            
Ref_RP(){} // default constructor for JPA

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getKeyToRP() {
	return keyToRP;
}

public void setKeyToRP(int keyToRP) {
	this.keyToRP = keyToRP;
}

public int getOriginId() {
	return originId;
}

public void setOriginId(int originId) {
	this.originId = originId;
}

public String getProject() {
	return project;
}

public void setProject(String project) {
	this.project = project;
}

public int getGemnr() {
	return gemnr;
}

public void setGemnr(int gemnr) {
	this.gemnr = gemnr;
}

public int getValidDay() {
	return validDay;
}

public void setValidDay(int validDay) {
	this.validDay = validDay;
}

public int getValidMonth() {
	return validMonth;
}

public void setValidMonth(int validMonth) {
	this.validMonth = validMonth;
}

public int getValidYear() {
	return validYear;
}

public void setValidYear(int validYear) {
	this.validYear = validYear;
}

public String getLastNameRP() {
	return lastNameRP;
}

public void setLastNameRP(String lastNameRP) {
	this.lastNameRP = lastNameRP;
}

public String getPrefixRP() {
	return prefixRP;
}

public void setPrefixRP(String prefixRP) {
	this.prefixRP = prefixRP;
}

public String getFirstNameRP() {
	return firstNameRP;
}

public void setFirstNameRP(String firstNameRP) {
	this.firstNameRP = firstNameRP;
}

public int getDayOfBirthRP() {
	return dayOfBirthRP;
}

public void setDayOfBirthRP(int dayOfBirthRP) {
	this.dayOfBirthRP = dayOfBirthRP;
}

public int getMonthOfBirthRP() {
	return monthOfBirthRP;
}

public void setMonthOfBirthRP(int monthOfBirthRP) {
	this.monthOfBirthRP = monthOfBirthRP;
}

public int getYearOfBirthRP() {
	return yearOfBirthRP;
}

public void setYearOfBirthRP(int yearOfBirthRP) {
	this.yearOfBirthRP = yearOfBirthRP;
}

public String getSexRP() {
	return sexRP;
}

public void setSexRP(String sexRP) {
	this.sexRP = sexRP;
}

public String getBirthPlaceRP() {
	return birthPlaceRP;
}

public void setBirthPlaceRP(String birthPlaceRP) {
	this.birthPlaceRP = birthPlaceRP;
}

public int getBirthProvinceRP() {
	return birthProvinceRP;
}

public void setBirthProvinceRP(int birthProvinceRP) {
	this.birthProvinceRP = birthProvinceRP;
}

public int getBirthCohortRP() {
	return birthCohortRP;
}

public void setBirthCohortRP(int birthCohortRP) {
	this.birthCohortRP = birthCohortRP;
}

public String getLastNameMother() {
	return lastNameMother;
}

public void setLastNameMother(String lastNameMother) {
	this.lastNameMother = lastNameMother;
}

public String getPrefixMother() {
	return prefixMother;
}

public void setPrefixMother(String prefixMother) {
	this.prefixMother = prefixMother;
}

public String getFirstNameMother() {
	return firstNameMother;
}

public void setFirstNameMother(String firstNameMother) {
	this.firstNameMother = firstNameMother;
}

public String getLastNameFather() {
	return lastNameFather;
}

public void setLastNameFather(String lastNameFather) {
	this.lastNameFather = lastNameFather;
}

public String getPrefixFather() {
	return prefixFather;
}

public void setPrefixFather(String prefixFather) {
	this.prefixFather = prefixFather;
}

public String getFirstNameFather() {
	return firstNameFather;
}

public void setFirstNameFather(String firstNameFather) {
	this.firstNameFather = firstNameFather;
}


}
