 package nl.iisg.ids04;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;

import nl.iisg.idscontext.ContextElement;
import nl.iisg.idscontext.Contxt;

@Entity
@Table(name="b32_st")
public class B32_ST extends B3_ST {
	
	@Column(name = "B3KODE")        		private int         contentOfDynamicData;
	@Column(name = "CIVIL_STATUS_FG")       private int         civilStatusFlag;
	@Column(name = "CIVIL_LOCALITY_ID")     private int         civilLocalityID;
	@Column(name = "CIVIL_LOCALITY_ST")     private String      civilLocalityStandardized;
	@Column(name = "CIVIL_LOCALITY_FG")     private int         civilLocalityFlag;

	B32_ST(){}
	
	
	public void convert(EntityManager em){
		
		//System.out.println("Enter b3st");

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

		//System.out.println("b3st 1");

		if(getEndDate() != null){

			endDay   = (new Integer(getEndDate().substring(0,2))).intValue();
			endMonth = (new Integer(getEndDate().substring(3,5))).intValue();
			endYear  = (new Integer(getEndDate().substring(6,10))).intValue();
		}


		//System.out.println("b3st 2");

		if(getContentOfDynamicData() > 0){
				Utils.addIndiv(em, getKeyToRP(), getPersonStandardizedToWhomDynamicDataRefers().getPersonID(), "B32", "CIVIL_STATUS " ,  
					Constants.cscode1[getContentOfDynamicData()], null, startDay, startMonth, startYear, endDay, endMonth, endYear);
		}

		//System.out.println("b3st 3");

		
		if(getContentOfDynamicData() == 5){ //  Marriage
			Utils.addIndiv(em, getKeyToRP(), getPersonStandardizedToWhomDynamicDataRefers().getPersonID(), "B32", "MARRIAGE_DATE", null, startDay, startMonth, startYear);
			if(getCivilLocalityStandardized() != null && getCivilLocalityStandardized().trim().length() > 0){
				ContextElement ce = Contxt.get2(getCivilLocalityStandardized());		
				if(ce != null){
					Utils.addIndivContextAndContext(null, ce, em, getKeyToRP(), getPersonStandardizedToWhomDynamicDataRefers().getPersonID(), "B32", "MARRIAGE LOCATION", startDay, startMonth, startYear, endDay, endMonth, endYear);
				}
			}
		}
		//System.out.println("Exit b3st");

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
