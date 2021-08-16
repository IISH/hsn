package nl.iisg.ids04;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;

import nl.iisg.hsncommon.ConstRelations2;

@Entity
@Table(name="b34_st")
public class B34_ST extends B3_ST{
	@Column(name = "B3KODE")        private int          contentOfDynamicData;

	B34_ST(){}

	public int getContentOfDynamicData() {
		return contentOfDynamicData;
	}

	public void setContentOfDynamicData(int contentOfDynamicData) {
		this.contentOfDynamicData = contentOfDynamicData;
	}
	
	public void convert(EntityManager em){
		
		//  This routine is used to set the relation of this person to the RP
		
		int startDay = 0;
		int startMonth = 0;
		int startYear = 0;
		int endDay = 0;
		int endMonth = 0;
		int endYear = 0;
		
		if(getStartDate() != null){
			startDay   = new Integer(getStartDate().substring(0,2));
			startMonth = new Integer(getStartDate().substring(3,5));
			startYear  = new Integer(getStartDate().substring(6,10));
			endDay     = new Integer(getEndDate().substring(0,2));
			endMonth   = new Integer(getEndDate().substring(3,5));
			endYear    = new Integer(getEndDate().substring(6,10));
		}

		//System.out.println("" + getKeyToSourceRegister() + " " + getEntryDateHead() + " " + getKeyToRP() + " " + getKeyToRegistrationPersons() + " " + getValueOfRelatedPerson() + " " + getContentOfDynamicData()); 
		
		int id_i_1        = getPersonStandardizedToWhomDynamicDataRefers().getPersonID();  
		int id_i_2        = getPersonStandardizedToWhomDynamicDataRefers().getRegistrationStandardizedPersonAppearsIn().
							getPersonsStandardizedInRegistration().get(getValueOfRelatedPerson() - 1).getPersonID(); // B34 -> B2 -> B4 -> B2 (RP)
		
		//System.out.println("id_i_1 = " + id_i_1 + ", id_i_2 = " + id_i_2 + " relation: " + getContentOfDynamicData());
		String missing = null;
		if(startYear == 0 && ConstRelations2.b3kode1_Related[getContentOfDynamicData()] != null)
			missing = "Time invariant";

		Utils.addIndivIndiv(em, getKeyToRP(), id_i_1,  id_i_2, "B34", "" + getContentOfDynamicData(), startDay, startMonth, startYear, endDay, endMonth, endYear, missing);

	}
}
