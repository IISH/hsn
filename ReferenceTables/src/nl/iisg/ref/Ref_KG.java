/*
* Naam:    Ref_KG
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
 * This class contains data of one KG (=KerkGenootschap, Religious Denomination) reference data  
 *
 */ 
@Entity
@Table(name="ref_religion")
//@SQLInsert(sql = "INSERT INTO ref_religion (id_religion, standard_code, original, standard) VALUES (?, ?, ?, ?)");
public class Ref_KG {
	
  @Id@Column(name = "id_religion")   private int       id_religion;
     @Column(name = "original")      private String    denomination;
   	 @Column(name = "standard")      private String    standard;
	 @Column(name = "standard_code") private String    code;
	 @Column(name = "standard_source") private String  source;
     @Transient                      private Boolean   needSave = false;
 


		
     static int current_ID = 0;
		
public Ref_KG(){}  // default constructor for JPA



public String getDenomination() {
	return denomination;
}



public void setDenomination(String denomination) {
	this.denomination = denomination;
}



public String getStandard() {
	return standard;
}



public void setStandard(String standard) {
	this.standard = standard;
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



public Boolean getNeedSave() {
	return needSave;
}



public void setNeedSave(Boolean needSave) {
	this.needSave = needSave;
}



public int getId_religion() {
	return id_religion;
}



public void setId_religion(int id_religion) {
	this.id_religion = id_religion;
}



public static int getCurrent_ID() {
	return current_ID++;
}



public static void setCurrent_ID(int current_ID) {
	Ref_KG.current_ID = current_ID;
}




	    					
   	

		
		

}
