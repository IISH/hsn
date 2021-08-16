package iisg.nl.hsnmigrate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="statview")
public class StatView {
	
	@Id @Column(name="PROVNR") private int 		   provnr;
	@Id @Column(name="3GS")    private String      threeGS;
	@Id @Column(name="COHORT") private String      cohort;
	@Column(name="PK500") 	   private int         pk500;
	
	public int getProvnr() {
		return provnr;
	}
	public void setProvnr(int provnr) {
		this.provnr = provnr;
	}
	public String getThreeGS() {
		return threeGS;
	}
	public void setThreeGS(String threeGS) {
		this.threeGS = threeGS;
	}
	public String getCohort() {
		return cohort;
	}
	public void setCohort(String cohort) {
		this.cohort = cohort;
	}
	public int getPk500() {
		return pk500;
	}
	public void setPk500(int pk500) {
		this.pk500 = pk500;
	}
	
	

}
