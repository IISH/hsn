package nl.iisg.convertPK;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;

@Entity
@Table(name="b313_st")
public class B313_ST extends B3_ST {


	@Column(name = "B3KODE")        private int          contentOfDynamicData;
	
	B313_ST(){};
	

	public void save(EntityManager em){
		
		em.persist(this);
			
	}

	public void truncate(){	
		

	}


	public int getContentOfDynamicData() {
		return contentOfDynamicData;
	}


	public void setContentOfDynamicData(int contentOfDynamicData) {
		this.contentOfDynamicData = contentOfDynamicData;
	}

	
}
