package nl.iisg.ids05;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="m2")
public class M2 {

     @Column(name="IDNR")         private int idnr;
     @Column(name="MAR_CD")       private int mar_cd;
     @Column(name="MAR_CM")       private int mar_cm;
     @Column(name="MAR_CY")       private int mar_cy;
     @Column(name="M2SDSQ")       private int m2sdsq;
     @Column(name="M2U_UM")       private int m2u_um;
     @Column(name="M2U_UD")       private int m2u_ud;
     @Column(name="M2U_UY")       private int m2u_uy;
     @Column(name="M2U_UC")       private int m2u_uc;
     @Column(name="M2U_UL")       private String m2u_ul;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")     private int recordID;
     
     @Transient                   private M1  m1   = null;

     
      
     
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public int getMar_cd() {
		return mar_cd;
	}
	public void setMar_cd(int mar_cd) {
		this.mar_cd = mar_cd;
	}
	public int getMar_cm() {
		return mar_cm;
	}
	public void setMar_cm(int mar_cm) {
		this.mar_cm = mar_cm;
	}
	public int getMar_cy() {
		return mar_cy;
	}
	public void setMar_cy(int mar_cy) {
		this.mar_cy = mar_cy;
	}
	public int getM2sdsq() {
		return m2sdsq;
	}
	public void setM2sdsq(int m2sdsq) {
		this.m2sdsq = m2sdsq;
	}
	public int getM2u_um() {
		return m2u_um;
	}
	public void setM2u_um(int m2u_um) {
		this.m2u_um = m2u_um;
	}
	public int getM2u_ud() {
		return m2u_ud;
	}
	public void setM2u_ud(int m2u_ud) {
		this.m2u_ud = m2u_ud;
	}
	public int getM2u_uy() {
		return m2u_uy;
	}
	public void setM2u_uy(int m2u_uy) {
		this.m2u_uy = m2u_uy;
	}
	public int getM2u_uc() {
		return m2u_uc;
	}
	public void setM2u_uc(int m2u_uc) {
		this.m2u_uc = m2u_uc;
	}
	public String getM2u_ul() {
		return m2u_ul;
	}
	public void setM2u_ul(String m2u_ul) {
		this.m2u_ul = m2u_ul;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}
	public M1 getM1() {
		return m1;
	}
	public void setM1(M1 m1) {
		this.m1 = m1;
	}

	
	
}
