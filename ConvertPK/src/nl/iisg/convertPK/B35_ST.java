package nl.iisg.convertPK;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="b35_st")
public class B35_ST extends B3_ST {
	
	@Column(name = "OCCUPATION_ID")        private int          occupationID;
	@Column(name = "OCCUPATION_ST")        private String       occupationStandardized;
	@Column(name = "OCCUPATION_FG")        private int          occupationFlag;
	
	@Transient                             private B2_ST        person;  

	 
	B35_ST(){}
	
	public void save(EntityManager em){
		
		em.persist(this);
		
	}

	/**
	 * 
	 * This routine truncates fields that are too long for the corresponding database columns
	 * 
	 */
	

	public void truncate(){	
		
		
		//System.out.println("Occupational Title ");
		String field = getOccupationStandardized();
		int allowedSize = TableDefinitions.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B35_ST", "OCCUPATION_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setOccupationStandardized(field);
		}

	}


	public int getOccupationID() {
		return occupationID;
	}

	public void setOccupationID(int occupationID) {
		this.occupationID = occupationID;
	}

	public String getOccupationStandardized() {
		return occupationStandardized;
	}

	public void setOccupationStandardized(String occupationStandardized) {
		this.occupationStandardized = occupationStandardized;
	}

	public int getOccupationFlag() {
		return occupationFlag;
	}

	public void setOccupationFlag(int occupationFlag) {
		this.occupationFlag = occupationFlag;
	}

	public B2_ST getPerson() {
		return person;
	}

	public void setPerson(B2_ST person) {
		this.person = person;
	}
	
	
	

}
