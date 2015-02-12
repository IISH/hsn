/*
* Naam:    Ref_FamilyName
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
 * This class contains data of one row of family name reference data  
 *
 */

@Entity
@Table(name="ref_familyname")
public class Ref_FamilyName{
	
	@Id @Column(name = "id_familyname")      private int       lastNameID;
	@Column(name = "original")               private String    original;
	@Column(name = "standard")               private String    name;
	@Column(name = "standard_code")          private String    code;
	@Transient                               private Boolean   needSave = false;

	
	static int current_ID = 0;


	public int getLastNameID() {
		return lastNameID;
	}


	public void setLastNameID(int lastNameID) {
		this.lastNameID = lastNameID;
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


	public static int getCurrent_ID() {
		return current_ID++;
	}


	public static void setCurrent_ID(int current_ID) {
		Ref_FamilyName.current_ID = current_ID;
	}
	
		
	
	
}
