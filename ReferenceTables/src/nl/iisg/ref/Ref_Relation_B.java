package nl.iisg.ref;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="ref_relation_b")
public class Ref_Relation_B{

	@Id @Column(name = "id")                 private int       relationID;
	@Column(name = "kode")                   private int       kode;
	@Column(name = "nederlands")             private String    nederlands;
	@Column(name = "sex")                    private String    sex;
	@Column(name = "engels")          		 private String    engels;
	@Column(name = "ids")                    private String    ids;
	@Transient                               private Boolean   needSave = false;

	static int current_ID = 0;

	
	
	
	public int getRelationID() {
		return relationID;
	}

	public void setRelationID(int relationID) {
		this.relationID = relationID;
	}


	public int getKode() {
		return kode;
	}

	public void setKode(int kode) {
		this.kode = kode;
	}

	public String getNederlands() {
		return nederlands;
	}

	public void setNederlands(String nederlands) {
		this.nederlands = nederlands;
	}

	public String getEngels() {
		return engels;
	}

	public void setEngels(String engels) {
		this.engels = engels;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Boolean getNeedSave() {
		return needSave;
	}

	public void setNeedSave(Boolean needSave) {
		this.needSave = needSave;
	}

	public static int getCurrent_ID() {
		return current_ID++;
	}

	public static void setCurrent_ID(int current_ID) {
		Ref_Relation_B.current_ID = current_ID;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
