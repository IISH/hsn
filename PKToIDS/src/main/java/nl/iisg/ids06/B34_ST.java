package nl.iisg.ids06;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;

@Entity
@Table(name="b34_st") 

public class B34_ST extends B3_ST {
	
	@Column(name = "B3KODE")        private int          contentOfDynamicData;

	
	
	public void save(EntityManager em){
		
		em.persist(this);
		
	}

	public void convert(EntityManager em){
		
		//  This routine is used to set the relation of this person to the RP or from the RP to every person (by consecutive invocations)

		int startDay = 0;
		int startMonth = 0;
		int startYear = 0;
		int endDay = 0;
		int endMonth = 0;
		int endYear = 0;
		
		int mutationDay = 0;
		int mutationMonth = 0;
		int mutationYear = 0;
		
		if(getStartDate() != null){
			startDay   = new Integer(getStartDate().substring(0,2));
			startMonth = new Integer(getStartDate().substring(3,5));
			startYear  = new Integer(getStartDate().substring(6,10));
		}
		
		if(getEndDate() != null){
			endDay     = new Integer(getEndDate().substring(0,2));
			endMonth   = new Integer(getEndDate().substring(3,5));
			endYear    = new Integer(getEndDate().substring(6,10));
		}

		if(getDateOfMutation() != null){
			mutationDay   = new Integer(getDateOfMutation().substring(0,2));
			mutationMonth = new Integer(getDateOfMutation().substring(3,5));
			mutationYear  = new Integer(getDateOfMutation().substring(6,10));
		}
		
		// If mutation year earlier, we use it. This gives the 'full' range between the PK-Holder and his parents on the one hand and spouses of the PK-Holder on the other
		
		if(mutationYear  != 0  && ((mutationYear < startYear) || (mutationYear == startYear && mutationMonth < startMonth) ||
				(mutationYear == startYear && mutationMonth == startMonth && mutationDay < startDay))){
			startDay   = mutationDay;
			startMonth = mutationMonth;
			startYear  = mutationYear;
			
		}

		int id_i_1 = getPerson().getPersonID();
		int id_i_2 = getPerson().getRegistration().getPersons().get(getValueOfRelatedPerson() - 1).getPersonID(); // B34 -> B2 -> B4 -> B2 (RP)
		
		
		
		String m = "";
		
		if(getEndDate() == null){
			if(getStartFlag() == 21)
				m = "Marriage_Related";
			else{
				m = "Time_Invariant";
				startDay = 0;
				startMonth = 0;
				startYear = 0;
			}
		}
			
		
		Utils.addIndivIndiv(em, getKeyToRP(), id_i_1,  id_i_2, "B34_ST", "" + getContentOfDynamicData(), "Reported", "Exact", m, startDay, startMonth, startYear, endDay, endMonth, endYear);
		
	}
	
	public int getContentOfDynamicData() {
		return contentOfDynamicData;
	}

	public void setContentOfDynamicData(int contentOfDynamicData) {
		this.contentOfDynamicData = contentOfDynamicData;
	}

	
	

}
