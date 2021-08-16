package nl.iisg.convertPK;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;

@Entity
@Table(name="b32_st")
public class B32_ST extends B3_ST{ 
	

	@Column(name = "B3KODE")        		private int         contentOfDynamicData;
	@Column(name = "CIVIL_STATUS_FG")       private int         civilStatusFlag;
	@Column(name = "CIVIL_LOCALITY_ID")     private int         civilLocalityID;
	@Column(name = "CIVIL_LOCALITY_ST")     private String      civilLocalityStandardized;
	@Column(name = "CIVIL_LOCALITY_FG")     private int         civilLocalityFlag;
	
	B32_ST(){};
	

	public void save(EntityManager em){
		
		em.persist(this);
			
	}

	/**
	 * 
	 * This routine truncates fields that are too long for the corresponding database columns
	 * 
	 */
	


	public void truncate(){	
		
		String field = getCivilLocalityStandardized();
		int allowedSize = TableDefinitions.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B32_ST", "CIVIL_LOCALITY_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setCivilLocalityStandardized(field);
		}
	}


	public int getContentOfDynamicData() {
		return contentOfDynamicData;
	}


	public void setContentOfDynamicData(int contentOfDynamicData) {
		this.contentOfDynamicData = contentOfDynamicData;
	}


	public int getCivilStatusFlag() {
		return civilStatusFlag;
	}


	public void setCivilStatusFlag(int civilStatusFlag) {
		this.civilStatusFlag = civilStatusFlag;
	}


	public int getCivilLocalityID() {
		return civilLocalityID;
	}


	public void setCivilLocalityID(int civilLocalityID) {
		this.civilLocalityID = civilLocalityID;
	}


	public String getCivilLocalityStandardized() {
		return civilLocalityStandardized;
	}


	public void setCivilLocalityStandardized(String civilLocalityStandardized) {
		this.civilLocalityStandardized = civilLocalityStandardized;
	}


	public int getCivilLocalityFlag() {
		return civilLocalityFlag;
	}


	public void setCivilLocalityFlag(int civilLocalityFlag) {
		this.civilLocalityFlag = civilLocalityFlag;
	}
	
	
		
}
