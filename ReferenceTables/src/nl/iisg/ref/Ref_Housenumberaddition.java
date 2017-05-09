/*
* Naam:    Ref_Housenumberaddition
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
@Table(name="ref_addition")
public class Ref_Housenumberaddition{
	
	@Id @Column(name = "id_huisnummertoevoeging")   	private int       housenumberadditionID;
	@Column(name = "origineel")              			private String    original;
	@Column(name = "toevoeging")               			private String    addition;
	@Column(name = "nieuwcode")              			private String    code;
	@Transient                                          private Boolean   needSave = false;

	
	static int current_ID = 0;

	

	public int getHousenumberadditionID() {
		return housenumberadditionID;
	}

	public void setHousenumberadditionID(int housenumberadditionID) {
		this.housenumberadditionID = housenumberadditionID;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getAddition() {
		return addition;
	}

	public void setAddition(String addition) {
		this.addition = addition;
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
		Ref_Housenumberaddition.current_ID = current_ID;
	}

	public Boolean getNeedSave() {
		return needSave;
	}

	public void setNeedSave(Boolean needSave) {
		this.needSave = needSave;
	}
	
	
	
	
}
	
