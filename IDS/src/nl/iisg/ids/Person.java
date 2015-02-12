package nl.iisg.ids;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="persons")
public class Person {
	
@Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
@Column(name = "Id")  				private int       id; 		// Primary Key
@Column(name = "IDNR")  				private String    idnr;   	// IDNR RP
@Column(name = "Id_D")  				private String    id_D;   	// CC/PC/PR
@Column(name = "Id_I")  				private int		  id_I;  	// Id_I from CC/PC/PR
@Column(name = "Id_I_new")  			private String	  id_I_new;  // to be constructed
@Column(name = "Start_code")  			private int       startCode;  // first source found
@Column(name = "Familyname")  			private String    familyName;  
@Column(name = "Prefix_Familyname")  	private String    prefix;  
@Column(name = "Firstname")  			private String    firstName;  
@Column(name = "Sex")     	 			private String    sex;  
@Column(name = "Birthday")  			private int 	  birthDay;  
@Column(name = "Birthmonth")  			private int 	  birthMonth;  
@Column(name = "Birthyear")  			private int 	  birthYear;  
@Column(name = "Birthstartday")  		private int 	  birthStartDay;  
@Column(name = "Birthstartmonth")  		private int 	  birthStartMonth;  
@Column(name = "Birthstartyear")  		private int 	  birthStartYear;  
@Column(name = "Birthendday")  			private int 	  birthEndDay;  
@Column(name = "Birthendmonth")  		private int 	  birthEndMonth;  
@Column(name = "Birthendyear")  		private int 	  birthEndYear;  
@Column(name = "Id_B_C")  				private String 	  id_BC; 	// contextID Birth place   
@Column(name = "Deathday")  			private int 	  deathDay;  
@Column(name = "Deathmonth")  			private int 	  deathMonth;  
@Column(name = "Deathyear")  			private int 	  deathYear;  
@Column(name = "Id_D_C")  				private String 	  id_DC; 	// contextID Decease place   
@Column(name = "Relation_RP")  			private String 	  relationRP; // relation with RP
@Column(name = "Original_Relation_RP")  private String 	  originalRelationRP;   // relation with RP
@Column(name = "Function")  			private String 	  function; // witness or informer

@Transient                              private int       idWithinGroup; // PersonID within group (RP's, mother's, etc,..)
@Transient                              private ArrayList<INDIVIDUAL>    individual    = new ArrayList<INDIVIDUAL>();
@Transient                              private ArrayList<INDIV_INDIV>   indiv_indiv   = new ArrayList<INDIV_INDIV>();
@Transient                              private ArrayList<INDIV_CONTEXT> indiv_context = new ArrayList<INDIV_CONTEXT>();

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public String getId_D() {
	return id_D;
}
public void setId_D(String id_D) {
	this.id_D = id_D;
}
public int getId_I() {
	return id_I;
}
public void setId_I(int id_I) {
	this.id_I = id_I;
}


public String getFamilyName() {
	return familyName;
}
public void setFamilyName(String familyName) {
	this.familyName = familyName;
}
public String getPrefix() {
	return prefix;
}
public void setPrefix(String prefix) {
	this.prefix = prefix;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public int getBirthDay() {
	return birthDay;
}
public void setBirthDay(int birthDay) {
	this.birthDay = birthDay;
}
public int getBirthMonth() {
	return birthMonth;
}
public void setBirthMonth(int birthMonth) {
	this.birthMonth = birthMonth;
}
public int getBirthYear() {
	return birthYear;
}
public void setBirthYear(int birthYear) {
	this.birthYear = birthYear;
}
public int getBirthStartDay() {
	return birthStartDay;
}
public void setBirthStartDay(int birthStartDay) {
	this.birthStartDay = birthStartDay;
}
public int getBirthStartMonth() {
	return birthStartMonth;
}
public void setBirthStartMonth(int birthStartMonth) {
	this.birthStartMonth = birthStartMonth;
}
public int getBirthStartYear() {
	return birthStartYear;
}
public void setBirthStartYear(int birthStartYear) {
	this.birthStartYear = birthStartYear;
}
public int getBirthEndDay() {
	return birthEndDay;
}
public void setBirthEndDay(int birthEndDay) {
	this.birthEndDay = birthEndDay;
}
public int getBirthEndMonth() {
	return birthEndMonth;
}
public void setBirthEndMonth(int birthEndMonth) {
	this.birthEndMonth = birthEndMonth;
}
public int getBirthEndYear() {
	return birthEndYear;
}
public void setBirthEndYear(int birthEndYear) {
	this.birthEndYear = birthEndYear;
}
public int getDeathDay() {
	return deathDay;
}
public void setDeathDay(int deathDay) {
	this.deathDay = deathDay;
}
public int getDeathMonth() {
	return deathMonth;
}
public void setDeathMonth(int deathMonth) {
	this.deathMonth = deathMonth;
}
public int getDeathYear() {
	return deathYear;
}
public void setDeathYear(int deathYear) {
	this.deathYear = deathYear;
}

public String getIdnr() {
	return idnr;
}
public void setIdnr(String idnr) {
	this.idnr = idnr;
}
public String getId_BC() {
	return id_BC;
}
public void setId_BC(String id_BC) {
	this.id_BC = id_BC;
}
public String getId_DC() {
	return id_DC;
}
public void setId_DC(String id_DC) {
	this.id_DC = id_DC;
}
public ArrayList<INDIVIDUAL> getIndividual() {
	return individual;
}
public void setIndividual(ArrayList<INDIVIDUAL> individual) {
	this.individual = individual;
}
public ArrayList<INDIV_INDIV> getIndiv_indiv() {
	return indiv_indiv;
}
public void setIndiv_indiv(ArrayList<INDIV_INDIV> indiv_indiv) {
	this.indiv_indiv = indiv_indiv;
}
public ArrayList<INDIV_CONTEXT> getIndiv_context() {
	return indiv_context;
}
public void setIndiv_context(ArrayList<INDIV_CONTEXT> indiv_context) {
	this.indiv_context = indiv_context;
}
public String getRelationRP() {
	return relationRP;
}
public void setRelationRP(String relationRP) {
	this.relationRP = relationRP;
}
public int getStartCode() {
	return startCode;
}
public void setStartCode(int startCode) {
	this.startCode = startCode;
}
public String getId_I_new() {
	return id_I_new;
}
public void setId_I_new(String id_I_new) {
	this.id_I_new = id_I_new;
}
public int getIdWithinGroup() {
	return idWithinGroup;
}
public void setIdWithinGroup(int idWithinGroup) {
	this.idWithinGroup = idWithinGroup;
}
public String getFunction() {
	return function;
}
public void setFunction(String function) {
	this.function = function;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getOriginalRelationRP() {
	return originalRelationRP;
}
public void setOriginalRelationRP(String originalRelationRP) {
	this.originalRelationRP = originalRelationRP;
}


}