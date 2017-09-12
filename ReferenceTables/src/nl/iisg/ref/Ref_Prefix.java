package nl.iisg.ref;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 
 * This class contains data of one row of prefix reference data  
 *
 */
@Entity
@Table(name="ref_prepiece")
public class Ref_Prefix {
	@Id @Column(name = "id_prepiece")      private int       prefixID;
	@Column(name = "original")             private String    original;    
	@Column(name = "title_noble")          private String    titleNoble;
	@Column(name = "title_other")          private String    titleOther;
	@Column(name = "prefix")               private String    prefix;
	@Column(name = "standard_code")        private String    code;
	@Transient                             private Boolean   needSave = false;

	static int current_ID = 0;

	public int getPrefixID() { 
		return prefixID;
	}

	public void setPrefixID(int prefixID) {
		this.prefixID = prefixID;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getTitleNoble() {
		return titleNoble;
	}

	public void setTitleNoble(String titleNoble) {
		this.titleNoble = titleNoble;
	}

	public String getTitleOther() {
		return titleOther;
	}

	public void setTitleOther(String titleOther) {
		this.titleOther = titleOther;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
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
		Ref_Prefix.current_ID = current_ID;
	}
	
	
	
}
