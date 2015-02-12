package nl.iisg.ids04;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;

@Entity
@Table(name="b311_st")
public class B311_ST extends B3_ST {
	
	@Column(name = "B3KODE")        private int          contentOfDynamicData;
	@Column(name = "B3GEGEVEN")     private String       dynamicData2;
	
	B311_ST(){}
	
	public void convert(EntityManager em){
		
		if(1==1) 
			return;  // because b311 specifies the relation to the head, not to the RP

		if(getContentOfDynamicData() > 1){
			int r = getContentOfDynamicData();
			//if(r < Constants.b3kode1.length && Constants.b3kode1[r] != null){

				//String relation = Constants.b3kode1[r];
			
			    String relation = "" + r; // We will convert to text in the final IDS 

				int id_i          = getPersonStandardizedToWhomDynamicDataRefers().getPersonID();  
				int id_i_pkholder = getPersonStandardizedToWhomDynamicDataRefers().getRegistrationStandardizedPersonAppearsIn().getPersonsStandardizedInRegistration().get(0).getPersonID(); // B311 -> B2 (non-PK-Holder) -> B4 -> B2 (PK-Holder)

				Utils.addIndivIndiv(em, getKeyToRP(), id_i,  id_i_pkholder, "B311", relation, 0, 0, 0);
			//}
		}	
	}

	public int getContentOfDynamicData() {
		return contentOfDynamicData;
	}
	public void setContentOfDynamicData(int contentOfDynamicData) {
		this.contentOfDynamicData = contentOfDynamicData;
	}
	public String getDynamicData2() {
		return dynamicData2;
	}
	public void setDynamicData2(String dynamicData2) {
		this.dynamicData2 = dynamicData2;
	}
	
	


}
