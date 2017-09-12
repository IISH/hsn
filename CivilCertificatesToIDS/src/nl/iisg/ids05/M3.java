package nl.iisg.ids05;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import nl.iisg.idscontext.ContextElement;
import nl.iisg.idscontext.Contxt;

@Entity 
@Table(name="m3")
public class M3 {

     @Column(name="IDNR")         private int idnr;
     @Column(name="MAR_CD")       private int mar_cd;
     @Column(name="MAR_CM")       private int mar_cm;
     @Column(name="MAR_CY")       private int mar_cy;
     @Column(name="M3SDSQ")       private int m3sdsq;
     @Column(name="M3RPGN")       private String m3rpgn;
     @Column(name="M3S_LN")       private String m3s_ln;
     @Column(name="M3S_PF")       private String m3s_pf;
     @Column(name="M3S_FN")       private String m3s_fn;
     @Column(name="M3S_TT")       private String m3s_tt;
     @Column(name="M3S_PA")       private String m3s_pa;
     @Column(name="M3S_SR")       private String m3s_sr;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")     private int recordID;
     
     @Transient                   private M1   m1   = null;

     
     public void convert(EntityManager em){   	
    	 
    	 int uniquifier = getMar_cy() * 100 * 1000 + (getMar_cm() * 1000) ; // The Id_I will be like 184906xxx
    	 
    	 // Former spouses
    	 
    	 int Id_I_FS = 20 + getM3sdsq() + uniquifier;
    	 int Id_I_GR = 11 + uniquifier; 
    	 int Id_I_BR = 12 + uniquifier;

    	 if(getM3s_ln() != null && getM3s_ln().trim().length() > 0 && !getM3s_ln().trim().equalsIgnoreCase("N") )
    		 Utils.addIndiv(em, getIdnr(), Id_I_FS, "MC M3", "LAST_NAME", getM3s_ln(), "Missing", "Time_invariant", 0, 0, 0);
    	 else
    		 return;
    	 if(getM3s_pf() != null && getM3s_pf().trim().length() > 0)
    		 Utils.addIndiv(em, getIdnr(), Id_I_FS, "MC M3", "PREFIX_LAST_NAME", getM3s_pf(), "Missing", "Time_invariant", 0, 0, 0);
    	 if(getM3s_fn() != null && getM3s_fn().trim().length() > 0)
    		 Utils.addIndiv(em, getIdnr(), Id_I_FS, "MC M3", "FIRST_NAME", getM3s_fn(), "Missing", "Time_invariant", 0, 0, 0);
		 
    	 // Invert Sex!
    	 
		 if(getM3rpgn().equalsIgnoreCase("M"))
			 Utils.addIndiv(em, getIdnr(), Id_I_FS, "MC M3", "SEX", "Female", "Missing", "Time_invariant", 0, 0, 0);
		 else
			 if(getM3rpgn().equalsIgnoreCase("V"))
				 Utils.addIndiv(em, getIdnr(), Id_I_FS, "MC M3", "SEX", "Male", "Missing", "Time_invariant", 0, 0, 0);

		 // Relation to RP

		 if(getM3rpgn().equalsIgnoreCase("M")){
			 Utils.addIndivIndiv(em, getIdnr(), Id_I_GR, Id_I_FS, "MC M3",  "Echtgenoot", "Missing", "Unavailable",  getMar_cd(), getMar_cm(), getMar_cy());
			 Utils.addIndivIndiv(em, getIdnr(), Id_I_FS, Id_I_GR,  "MC M3",  "Echtgenote", "Missing", "Unavailable",  getMar_cd(), getMar_cm(), getMar_cy());
		 }
		 else{
			 if(getM3rpgn().equalsIgnoreCase("V")){
				 Utils.addIndivIndiv(em, getIdnr(), Id_I_BR, Id_I_FS, "MC M3",  "Echtgenote", "Missing", "Unavailable",  getMar_cd(), getMar_cm(), getMar_cy());
				 Utils.addIndivIndiv(em, getIdnr(), Id_I_FS, Id_I_BR,  "MC M3",  "Echtgenoot", "Missing", "Unavailable",  getMar_cd(), getMar_cm(), getMar_cy());
			 }
		 }
		 
		 // We must give an estimated birthdate for linking purposes
		 
		Utils.addIndiv(em, getIdnr(), Id_I_FS, "MC M3", "BIRTH_DATE", null, "Declared", "Estimated [15/120]", 1, 1, getMar_cy() - 120, 1, 1,   getMar_cy() - 15);

     }


     
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
	public int getM3sdsq() {
		return m3sdsq;
	}
	public void setM3sdsq(int m3sdsq) {
		this.m3sdsq = m3sdsq;
	}
	public String getM3rpgn() {
		return m3rpgn;
	}
	public void setM3rpgn(String m3rpgn) {
		this.m3rpgn = m3rpgn;
	}
	public String getM3s_ln() {
		return m3s_ln;
	}
	public void setM3s_ln(String m3s_ln) {
		this.m3s_ln = m3s_ln;
	}
	public String getM3s_pf() {
		return m3s_pf;
	}
	public void setM3s_pf(String m3s_pf) {
		this.m3s_pf = m3s_pf;
	}
	public String getM3s_fn() {
		return m3s_fn;
	}
	public void setM3s_fn(String m3s_fn) {
		this.m3s_fn = m3s_fn;
	}
	public String getM3s_tt() {
		return m3s_tt;
	}
	public void setM3s_tt(String m3s_tt) {
		this.m3s_tt = m3s_tt;
	}
	public String getM3s_pa() {
		return m3s_pa;
	}
	public void setM3s_pa(String m3s_pa) {
		this.m3s_pa = m3s_pa;
	}
	public String getM3s_sr() {
		return m3s_sr;
	}
	public void setM3s_sr(String m3s_sr) {
		this.m3s_sr = m3s_sr;
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