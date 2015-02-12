 package nl.iisg.ids06;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;

@Entity
@Table(name="b313_st")
public class B313_ST extends B3_ST {


	@Column(name = "B3KODE")        private int          contentOfDynamicData;

	B313_ST(){};


	public void convert(EntityManager em){
		
		if(1==1)
			return;  // we moved this to b34_st to make reciprocal


		if(getContentOfDynamicData() > 1){
			int r = getContentOfDynamicData();
			//if(r < Constants.b3kode1.length && Constants.b3kode1[r] != null){

			//String relation = Constants.b3kode1[r];
			String relation = "" + r;

				int id_i          = getPerson().getPersonID();  
				int id_i_pkholder = getPerson().getRegistration().getPersons().get(0).getPersonID(); // B311 -> B2 (non-PK-Holder) -> B4 -> B2 (PK-Holder)

				Utils.addIndivIndiv(em, getKeyToRP(), id_i,  id_i_pkholder, "B313_ST", relation, "Reported", "Exact", 0, 0, 0);
			//}
		}	
	}


	public int getContentOfDynamicData() {
		return contentOfDynamicData;
	}


	public void setContentOfDynamicData(int contentOfDynamicData) {
		this.contentOfDynamicData = contentOfDynamicData;
	}

	
}
