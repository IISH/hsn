 package nl.iisg.ids06;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;

import nl.iisg.idscontext.ContextElement;
import nl.iisg.idscontext.Contxt;

@Entity
@Table(name="b32_st")
public class B32_ST extends B3_ST{
	

	@Column(name = "B3KODE")        		private int         contentOfDynamicData;
	@Column(name = "CIVIL_STATUS_FG")       private int         civilStatusFlag;
	@Column(name = "CIVIL_LOCALITY_ID")     private int         civilLocalityID;
	@Column(name = "CIVIL_LOCALITY_ST")     private String      civilLocalityStandardized;
	@Column(name = "CIVIL_LOCALITY_FG")     private int         civilLocalityFlag;
	
	B32_ST(){};
	
	public void convert(EntityManager em){
		
		int mutationDay   = 0;
		int mutationMonth = 0;
		int mutationYear  = 0;
		
		int startDay   = 0;
		int startMonth = 0;
		int startYear  = 0;
		
		int endDay   = 0;
		int endMonth = 0;
		int endYear  = 0;		

		if(getDateOfMutation() != null){

			mutationDay   = (new Integer(getDateOfMutation().substring(0,2))).intValue();
			mutationMonth = (new Integer(getDateOfMutation().substring(3,5))).intValue();
			mutationYear  = (new Integer(getDateOfMutation().substring(6,10))).intValue();
		}
		
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



		int rel = getPerson().getRelationsToPKHolder().get(0).getContentOfDynamicData();

		if(getContentOfDynamicData() > 0 && rel != 4 && rel != 5 && rel != 8 && rel != 9){ // not for (step)children
			Utils.addIndiv(em, getKeyToRP(), getPerson().getPersonID(), "B32_ST ", "CIVIL_STATUS", Constants.cscode1[getContentOfDynamicData()], "Reported", "Exact", startDay, startMonth, startYear, endDay, endMonth, endYear);
		}
		
		if(getContentOfDynamicData() == 5){ //  Marriage
			if(mutationYear > 0)
				Utils.addIndiv(em, getKeyToRP(), getPerson().getPersonID(), "B32_ST ", "MARRIAGE_DATE", null, "Reported", "Exact", mutationDay, mutationMonth, mutationYear);
			if(getCivilLocalityID() > 0){
				ContextElement ce = Contxt.get(getCivilLocalityID());
				if(ce != null){
					//Utils.addIndivContextAndContext(null, null, null, null,
					//		ce, em, getKeyToRP(), getPerson().getPersonID(), "B32_ST ", "MARRIAGE_LOCATION", mutationDay, mutationMonth, mutationYear);
					Utils.addIndivAndContext(null, null, null, null, ce, em, getKeyToRP(), getPerson().getPersonID(), "B32_ST",  "MARRIAGE_LOCATION", "Reported", "Exact", mutationDay, mutationMonth, mutationYear);

				}
			}
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
