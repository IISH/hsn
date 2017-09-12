/*
* Naam:    Ref_FirstName
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
 * This class contains data of one row of first name reference data  
 *
 */
 
@Entity
@Table(name="ref_firstname")
public class Ref_FirstName{
	
	@Id @Column(name = "id_firstname")        private int       firstNameID;
	@Column(name = "original")                private String    original;
	@Column(name = "standard")                private String    name;
	@Column(name = "standard_code")           private String    code;
	@Column(name = "standard_source")         private String    source;
	@Transient                                private Boolean   needSave = false;

	
	static int current_ID = 0;


	public int getFirstNameID() {
		return firstNameID;
	}


	public void setFirstNameID(int firstNameID) {
		this.firstNameID = firstNameID;
	}


	public String getOriginal() {
		return original;
	}


	public void setOriginal(String original) {
		this.original = original;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public Boolean getNeedSave() {
		return needSave;
	}


	public void setNeedSave(Boolean needSave) {
		this.needSave = needSave;
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
		Ref_FirstName.current_ID = current_ID;
	}
	
	
		
	
}