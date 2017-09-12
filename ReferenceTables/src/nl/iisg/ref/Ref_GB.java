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
 * This class contains data of one GB (Geboorteregister = birth register) row of reference data  
 *
 */ 
@Entity
@Table(name="ref_gbh")
public class Ref_GB {

	@Column(name = "GEMNR")     	private int          numberMunicipality;
	@Column(name = "GGMOP")     	private String       nameMunicipality;
	@Column(name = "COHORTNR")     	private int          cohortNumber;
	@Id     @Column(name = "IDNR")  private int          keyToRP;

	@Column(name = "ANMVR")     	private String       lastNameFather;
	@Column(name = "TUSVR")     	private String       prefixFather;
	@Column(name = "VRN1VR")     	private String       firstName1Father;
	@Column(name = "VRN2VR")     	private String       firstName2Father;
	@Column(name = "VRN3VR")     	private String       firstName3Father;
	
	@Column(name = "GEBDAG")        private int          dayOfBirth;
	@Column(name = "GEBMND")     	private int          monthOfBirth;
	@Column(name = "GEBJR")  	    private int          yearOfBirth;
	@Column(name = "GEBSEX")   		private String       sex;


	@Column(name = "ANMMR")    	    private String       lastNameMother;
	@Column(name = "TUSMR")         private String       prefixMother;
	@Column(name = "VRN1MR")        private String       firstName1Mother;
	@Column(name = "VRN2MR")        private String       firstName2Mother;
	@Column(name = "VRN3MR")        private String       firstName3Mother;

	@Column(name = "ANMGEB")        private String       lastName;
	@Column(name = "TUSGEB")        private String       prefixName;
	@Column(name = "VRN1GEB")       private String       firstName1;
	@Column(name = "VRN2GEB")       private String       firstName2;
	@Column(name = "VRN3GEB")       private String       firstName3;

   	@Transient        		        private Boolean      needSave = false;

		
		
            
Ref_GB(){} // default constructor for JPA




public int getNumberMunicipality() {
	return numberMunicipality;
}




public void setNumberMunicipality(int numberMunicipality) {
	this.numberMunicipality = numberMunicipality;
}




public String getNameMunicipality() {
	return nameMunicipality;
}




public void setNameMunicipality(String nameMunicipality) {
	this.nameMunicipality = nameMunicipality;
}




public int getCohortNumber() {
	return cohortNumber;
}




public void setCohortNumber(int cohortNumber) {
	this.cohortNumber = cohortNumber;
}




public int getKeyToRP() {
	return keyToRP;
}




public void setKeyToRP(int keyToRP) {
	this.keyToRP = keyToRP;
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




public String getFirstName1Father() {
	return firstName1Father;
}




public void setFirstName1Father(String firstName1Father) {
	this.firstName1Father = firstName1Father;
}




public String getFirstName2Father() {
	return firstName2Father;
}




public void setFirstName2Father(String firstName2Father) {
	this.firstName2Father = firstName2Father;
}




public String getFirstName3Father() {
	return firstName3Father;
}




public void setFirstName3Father(String firstName3Father) {
	this.firstName3Father = firstName3Father;
}




public int getDayOfBirth() {
	return dayOfBirth;
}




public void setDayOfBirth(int dayOfBirth) {
	this.dayOfBirth = dayOfBirth;
}




public int getMonthOfBirth() {
	return monthOfBirth;
}




public void setMonthOfBirth(int monthOfBirth) {
	this.monthOfBirth = monthOfBirth;
}




public int getYearOfBirth() {
	return yearOfBirth;
}




public void setYearOfBirth(int yearOfBirth) {
	this.yearOfBirth = yearOfBirth;
}




public String getSex() {
	return sex;
}




public void setSex(String sex) {
	this.sex = sex;
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




public String getFirstName1Mother() {
	return firstName1Mother;
}




public void setFirstName1Mother(String firstName1Mother) {
	this.firstName1Mother = firstName1Mother;
}




public String getFirstName2Mother() {
	return firstName2Mother;
}




public void setFirstName2Mother(String firstName2Mother) {
	this.firstName2Mother = firstName2Mother;
}




public String getFirstName3Mother() {
	return firstName3Mother;
}




public void setFirstName3Mother(String firstName3Mother) {
	this.firstName3Mother = firstName3Mother;
}




public String getLastName() {
	return lastName;
}




public void setLastName(String lastName) {
	this.lastName = lastName;
}




public String getPrefixName() {
	return prefixName;
}




public void setPrefixName(String prefixName) {
	this.prefixName = prefixName;
}




public String getFirstName1() {
	return firstName1;
}




public void setFirstName1(String firstName1) {
	this.firstName1 = firstName1;
}




public String getFirstName2() {
	return firstName2;
}




public void setFirstName2(String firstName2) {
	this.firstName2 = firstName2;
}




public String getFirstName3() {
	return firstName3;
}




public void setFirstName3(String firstName3) {
	this.firstName3 = firstName3;
}




public Boolean getNeedSave() {
	return needSave;
}




public void setNeedSave(Boolean needSave) {
	this.needSave = needSave;
}

		



}



