package nl.iisg.ids04;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;

import nl.iisg.idscontext.ContextElement;
import nl.iisg.idscontext.Contxt;

@Entity
@Table(name="b33_st")
public class B33_ST extends B3_ST{

	@Column(name = "RELIGION_ID")     private int         religionID;
	@Column(name = "RELIGION_ST")     private String      religionStandardized;
	@Column(name = "RELIGION_FG")     private int         religionFlag;
	
	B33_ST(){}

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

		if(getReligionStandardized() != null)
			Utils.addIndiv(em, getKeyToRP(), getPersonStandardizedToWhomDynamicDataRefers().getPersonID(), "B33", "RELIGION_STANDARD", getReligionStandardized(), null, startDay, startMonth, startYear, endDay, endMonth, endYear);

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
