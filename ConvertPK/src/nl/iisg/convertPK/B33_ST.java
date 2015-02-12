package nl.iisg.convertPK;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;

@Entity
@Table(name="b33_st")
public class B33_ST  extends B3_ST{
	
	@Column(name = "RELIGION_ID")     private int         religionID;
	@Column(name = "RELIGION_ST")     private String      religionStandardized;
	@Column(name = "RELIGION_FG")     private int         religionFlag;
	
	B33_ST(){}

	
	/**
	 * 
	 * This routine truncates fields that are too long for the corresponding database columns
	 * 
	 */
	

	
	public void truncate(){	
		
		String field = getReligionStandardized();
		int allowedSize = TableDefinitions.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B33_ST", "RELIGION_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setReligionStandardized(field);
		}

	}
	


	
	public void save(EntityManager em){
		
		em.persist(this);
		
	}


	public int getReligionID() {
		return religionID;
	}


	public void setReligionID(int religionID) {
		this.religionID = religionID;
	}


	public String getReligionStandardized() {
		return religionStandardized;
	}


	public void setReligionStandardized(String religionStandardized) {
		this.religionStandardized = religionStandardized;
	}


	public int getReligionFlag() {
		return religionFlag;
	}


	public void setReligionFlag(int religionFlag) {
		this.religionFlag = religionFlag;
	}

	

}
