package nl.iisg.ids04;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="b312_st")
public class B312_ST extends B3_ST{
	@Column(name = "B3KODE")        private int          contentOfDynamicData;
	@Column(name = "RELATION_FG")   private int          relation;
	
	B312_ST(){}

	public int getContentOfDynamicData() {
		return contentOfDynamicData;
	}

	public void setContentOfDynamicData(int contentOfDynamicData) {
		this.contentOfDynamicData = contentOfDynamicData;
	}

	public int getRelation() {
		return relation;
	}

	public void setRelation(int relation) {
		this.relation = relation;
	}



}
