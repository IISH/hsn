package nl.iisg.ref;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="ref_relation_c")
public class Ref_Relation_C{

	@Id @Column(name = "id")                 private int       relationID;
	@Column(name = "relation_a")             private String    relation_A;
	@Column(name = "relation_b")             private String    relation_B;
	@Column(name = "relation_m")             private String    relation_M;
	@Transient                               private Boolean   needSave = false;

	static int current_ID = 0;

	 
	
	
	public static int getCurrent_ID() {
		return current_ID++;
	}

	public static void setCurrent_ID(int current_ID) {
		Ref_Relation_B.current_ID = current_ID;
	}

	public int getRelationID() {
		return relationID;
	}

	public void setRelationID(int relationID) {
		this.relationID = relationID;
	}

	public String getRelation_A() {
		return relation_A;
	}

	public void setRelation_A(String relation_A) {
		this.relation_A = relation_A;
	}

	public String getRelation_B() {
		return relation_B;
	}

	public void setRelation_B(String relation_B) {
		this.relation_B = relation_B;
	}

	public String getRelation_M() {
		return relation_M;
	}

	public void setRelation_M(String relation_M) {
		this.relation_M = relation_M;
	}


	public Boolean getNeedSave() {
		return needSave;
	}

	public void setNeedSave(Boolean needSave) {
		this.needSave = needSave;
	}


	
	
}
