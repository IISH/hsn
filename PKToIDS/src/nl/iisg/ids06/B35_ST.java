 package nl.iisg.ids06;

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
	
	public void convert(EntityManager em){
		
		int startDay   = 0;
		int startMonth = 0;
		int startYear  = 0;
		
		int endDay   = 0;
		int endMonth = 0;
		int endYear  = 0;		

		if(getStartDate() != null){

			startDay   = (new Integer(getStartDate().substring(0,2))).intValue();
			startMonth = (new Integer(getStartDate().substring(3,5))).intValue();
			startYear  = (new Integer(getStartDate().substring(6,10))).intValue();
		}

		if(getEndDate() != null){

			endDay   = (new Integer(getEndDate().substring(0,2))).intValue();
			endMonth = (new Integer(getEndDate().substring(3,5))).intValue();
			endYear  = (new Integer(getEndDate().substring(6,10))).intValue();
		}

		if(getOccupationStandardized() != null)
			Utils.addIndiv(em, getKeyToRP(), getPerson().getPersonID(), "B35_ST",  "OCCUPATION_STANDARD", "" + getOccupationStandardized(),
					 "Declared", "Exact", startDay, startMonth, startYear, endDay, endMonth, endYear);
		
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
