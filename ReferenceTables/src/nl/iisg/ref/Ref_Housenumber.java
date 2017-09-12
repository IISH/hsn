/*
* Naam:    Ref_Huisnummer
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
@Table(name="ref_housenumber")
public class Ref_Housenumber{
	
	@Id @Column(name = "id_huisnummer")      private int       housenumberID;
	@Column(name = "origineel")              private String    original;
	@Column(name = "nummer")                 private String    housenumber;
	@Column(name = "nieuwcode")              private String    code;
	@Transient                               private Boolean   needSave = false;

	
	static int current_ID = 0;
	

	public int getHousenumberID() {
		return housenumberID;
	}

	public void setHousenumberID(int housenumberID) {
		this.housenumberID = housenumberID;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getHousenumber() {
		return housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
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
		Ref_Housenumber.current_ID = current_ID;
	}

	public Boolean getNeedSave() {
		return needSave;
	}

	public void setNeedSave(Boolean needSave) {
		this.needSave = needSave;
	}

	
	
	
	
	
}