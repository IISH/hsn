package nl.iisg.convertPK;

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


	public int getContentOfDynamicData() {
		return contentOfDynamicData;
	}

	public void setContentOfDynamicData(int contentOfDynamicData) {
		this.contentOfDynamicData = contentOfDynamicData;
	}

	
	

}
