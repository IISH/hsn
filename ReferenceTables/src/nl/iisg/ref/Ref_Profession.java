/*
* Naam:    Ref_Profession
* * Version: 0.1
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
 * This class contains data of one row of profession reference data  
 *
 */

@Entity
@Table(name="ref_occupation")
public class Ref_Profession{
	
	@Id @Column(name = "id_occupation")      private int       professionID;
	@Column(name = "original")               private String    original;
	@Column(name = "standard")               private String    profession;
	@Column(name = "standard_code")          private String    code;
	@Transient                               private Boolean   needSave = false;

	
	static int current_ID = 0;
	
	public int getProfessionID() {
		return professionID;
	}
	public void setProfessionID(int professionID) {
		this.professionID = professionID;
	}
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}


	public static int getCurrent_ID() {
		return current_ID++;
	}


	public static void setCurrent_ID(int current_ID) {
		Ref_Profession.current_ID = current_ID;
	}
	public Boolean getNeedSave() {
		return needSave;
	}
	public void setNeedSave(Boolean needSave) {
		this.needSave = needSave;
	}

	
	
	
	
}
	
	
	