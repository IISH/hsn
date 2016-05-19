package nl.iisg.ids05;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import nl.iisg.hsncommon.Common1;
import nl.iisg.hsncommon.ConstRelations2;
import nl.iisg.idscontext.ContextElement;
import nl.iisg.idscontext.Contxt;

@Entity
@Table(name="m4")
public class M4 {

     @Column(name="IDNR")         private int idnr;
     @Column(name="MAR_CD")       private int mar_cd;
     @Column(name="MAR_CM")       private int mar_cm;
     @Column(name="MAR_CY")       private int mar_cy;
     @Column(name="M4SDSQ")       private int m4sdsq;
     @Column(name="M4W_LN")       private String m4w_ln;
     @Column(name="M4W_PF")       private String m4w_pf;
     @Column(name="M4W_FN")       private String m4w_fn;
     @Column(name="M4W_TT")       private String m4w_tt;
     @Column(name="M4W_PA")       private String m4w_pa;
     @Column(name="M4W_AY")       private int m4w_ay;
     @Column(name="M4W_OC")       private String m4w_oc;
     @Column(name="M4W_LL")       private String m4w_ll;
     @Column(name="M4W_SG")       private String m4w_sg;
     @Column(name="M4W_LR")       private String m4w_lr;
     @Column(name="M4W_LS")       private String m4w_ls;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")     private int recordID;
     
     @Transient                   private M1   m1   = null;

     public void convert(EntityManager em){   	
    	 
    	 int uniquifier = getMar_cy() * 100 * 1000 + (getMar_cm() * 1000) ; // The Id_I will be like 184906xxx
    	 
    	 // Witnesses
    	
    	 int Id_I_WT = 30 + getM4sdsq() + uniquifier;
    	 int Id_I_GR = 11 + uniquifier; 
    	 int Id_I_BR = 12 + uniquifier;


    	 if(getM4w_ln() != null && getM4w_ln().trim().length() > 0 && !getM4w_ln().trim().equalsIgnoreCase("N"))
    		 Utils.addIndiv(em, getIdnr(), Id_I_WT, "MC M4", "LAST_NAME", getM4w_ln(), "Missing", "Time_invariant", 0, 0, 0);
    	 else
    		 return;
    	 if(getM4w_pf() != null && getM4w_pf().trim().length() > 0)
    		 Utils.addIndiv(em, getIdnr(), Id_I_WT, "MC M4", "PREFIX_LAST_NAME", getM4w_pf(), "Missing", "Time_invariant", 0, 0, 0);
    	 if(getM4w_fn() != null && getM4w_fn().trim().length() > 0)
    		 Utils.addIndiv(em, getIdnr(), Id_I_WT, "MC M4", "FIRST_NAME", getM4w_fn(), "Missing", "Time_invariant", 0, 0, 0);	 
		 if(getM4w_ay() > 0){
			 Utils.addIndiv(em, getIdnr(), Id_I_WT, "MC M4", "AGE_YEARS", (new Integer(getM4w_ay())).toString(), "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 if(Utils.dateIsValid(getMar_cd(), getMar_cm(), getMar_cy()) == 0){
				 int[] a = Utils.birthRange(getM4w_ay(), getMar_cd(), getMar_cm(), getMar_cy());
				 Utils.addIndiv(em, getIdnr(), Id_I_WT, "MC M4", "BIRTH_DATE", null, "Declared", "Age_based", a[0], a[1], a[2], a[3], a[4], a[5]);
			 }
		 }
		 else
			Utils.addIndiv(em, getIdnr(), Id_I_WT, "MC M4", "BIRTH_DATE", null, "Declared", "Estimated [18/100]", 1, 1, getMar_cy() - 100, 1, 1,   getMar_cy() - 18);

		 if(getM4w_oc() != null && getM4w_oc().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_WT, "MC M4", "OCCUPATION_STANDARD", getM4w_oc(), "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
		 if(getM4w_sg() != null && getM4w_sg().trim().length() > 0)			 
			 Utils.addIndiv(em, getIdnr(), Id_I_WT, "MC M4", "SIGNATURE", Utils.signature(getM4w_sg()), "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());

    	 if(getM4w_ll() != null && getM4w_ll().trim().length() > 0){
    		 ContextElement ce = Contxt.get2(getM4w_ll());
    		 if(ce != null)
    			 Utils.addIndivContextAndContext(null, ce, em, getIdnr(), Id_I_WT, "MC M4", "", "Reported", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
    	 }
    	 
    	 ContextElement ceMarriage = null;
    	 if(getM1().getM1sdml() != null)
    		 ceMarriage = Contxt.get2(getM1().getM1sdml());

    	 if(ceMarriage != null)
    		 Utils.addIndivContextAndContextCertificate(getMar_cy(), getM1().getM1sdcn(), ceMarriage, em, getIdnr(), Id_I_WT, "Marriage Certificate", "Witness", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());

		 
		 // Relation to RP
		 
    	 // System.out.println("M4 IDNR = " + getIdnr() + " m4w_lr = " + getM4w_lr() + " m4w_ls = " + getM4w_ls());
    	 
    	 boolean GR_WT = false;
    	 boolean BR_WT = false;
    	 
		 if(getM4w_lr() != null){
			 if(getM4w_lr().trim().equalsIgnoreCase("M") || getM4w_lr().trim().equalsIgnoreCase("B"))
				 if(getM4w_ls() != null && getM4w_ls().trim().length() > 0){
					 Utils.addIndivIndiv(em, getIdnr(), Id_I_WT, Id_I_GR, "MC M4",  getM4w_ls().trim(), "Event", "Exact",  getMar_cd(), getMar_cm(), getMar_cy());					 
					 Utils.addIndivIndiv(em, getIdnr(), Id_I_GR, Id_I_WT, "MC M4",  findReciproke(getM4w_ls().trim(), "M"), "Event", "Exact",  getMar_cd(), getMar_cm(), getMar_cy());
					 GR_WT = true;
					 Utils.addIndivIndiv(em, getIdnr(), Id_I_WT, Id_I_BR, "MC M4",  "Onbekend", "Event", "Exact",  getMar_cd(), getMar_cm(), getMar_cy());
				 }
				 else{
					 Utils.addIndivIndiv(em, getIdnr(), Id_I_WT, Id_I_GR, "MC M4",  "Onbekend", "Event", "Exact",  getMar_cd(), getMar_cm(), getMar_cy());
					 Utils.addIndivIndiv(em, getIdnr(), Id_I_WT, Id_I_BR, "MC M4",  "Onbekend", "Event", "Exact",  getMar_cd(), getMar_cm(), getMar_cy());
				 }
			 else
				 if(getM4w_lr().trim().equalsIgnoreCase("V") || getM4w_lr().trim().equalsIgnoreCase("B")){
					 if(getM4w_ls() != null && getM4w_ls().trim().length() > 0){
						 Utils.addIndivIndiv(em, getIdnr(), Id_I_WT, Id_I_BR, "MC M4",  getM4w_ls().trim(), "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
						 Utils.addIndivIndiv(em, getIdnr(), Id_I_BR, Id_I_WT, "MC M4",  findReciproke(getM4w_ls().trim(), "V"), "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
						 BR_WT = true;
						 Utils.addIndivIndiv(em, getIdnr(), Id_I_WT, Id_I_GR, "MC M4",  "Onbekend", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
					 }
					 else{
					     Utils.addIndivIndiv(em, getIdnr(), Id_I_WT, Id_I_BR, "MC M4",  "Onbekend", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
					     Utils.addIndivIndiv(em, getIdnr(), Id_I_WT, Id_I_GR, "MC M4",  "Onbekend", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
					 }
				 }
				 else{
				     Utils.addIndivIndiv(em, getIdnr(), Id_I_WT, Id_I_BR, "MC M4",  "Onbekend", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
				     Utils.addIndivIndiv(em, getIdnr(), Id_I_WT, Id_I_GR, "MC M4",  "Onbekend", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
				 }
		 }
		 else{
		     Utils.addIndivIndiv(em, getIdnr(), Id_I_WT, Id_I_BR, "MC M4",  "Onbekend", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
		     Utils.addIndivIndiv(em, getIdnr(), Id_I_WT, Id_I_GR, "MC M4",  "Onbekend", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
		 }
		 if(!GR_WT)
			 Utils.addIndivIndiv(em, getIdnr(), Id_I_GR, Id_I_WT, "MC M4",  "Onbekend", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
		 if(!BR_WT)
			 Utils.addIndivIndiv(em, getIdnr(), Id_I_BR, Id_I_WT, "MC M4",  "Onbekend", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());

     }


     
    private String findReciproke(String relation, String sex){
    	
    	    	
    	System.out.println("xxx1 " + relation);
    	
    	if(relation == null) return("Onbekend");
    	 
    	int code = -1;
    	for(int j= 1; j < ConstRelations2.b3kode1.length; j++){
    		if(relation.equalsIgnoreCase(ConstRelations2.b3kode1[j])){
    			code = j;
    			break;
    		}
    		
    	}
    	System.out.println("xxx2 " + code);

    	
    	if(code > 0){
    		
    		int reciprokeCode = Common1.getRelation(1, code)[0];
        	System.out.println("xxx3 " + reciprokeCode);
        	//System.out.println("xxx4 " + ConstRelations2.b3kode1[reciprokeCode]);

    		if(reciprokeCode > 0 && reciprokeCode < ConstRelations2.b3kode1.length){
    			
    	    	// Adapt for sex

    	    	if(sex.equalsIgnoreCase("V") && ConstRelations2.b3kode1_Female[reciprokeCode] == null)
    	    		if(ConstRelations2.mToF[reciprokeCode] != 0)
    	    			reciprokeCode = ConstRelations2.mToF[reciprokeCode];

    	    	if(sex.equalsIgnoreCase("M") && ConstRelations2.b3kode1_Male[reciprokeCode] == null)
    	    		if(ConstRelations2.fToM[reciprokeCode] != 0)
    	    			reciprokeCode = ConstRelations2.fToM[reciprokeCode];

    	    	
    			
    			return (ConstRelations2.b3kode1[reciprokeCode]);
    		}
    		
    		
    	}
    	
    	
    	return("Onbekend");
    	 
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


	public int getM4sdsq() {
		return m4sdsq;
	}


	public void setM4sdsq(int m4sdsq) {
		this.m4sdsq = m4sdsq;
	}


	public String getM4w_ln() {
		return m4w_ln;
	}


	public void setM4w_ln(String m4w_ln) {
		this.m4w_ln = m4w_ln;
	}


	public String getM4w_pf() {
		return m4w_pf;
	}


	public void setM4w_pf(String m4w_pf) {
		this.m4w_pf = m4w_pf;
	}


	public String getM4w_fn() {
		return m4w_fn;
	}


	public void setM4w_fn(String m4w_fn) {
		this.m4w_fn = m4w_fn;
	}


	public String getM4w_tt() {
		return m4w_tt;
	}


	public void setM4w_tt(String m4w_tt) {
		this.m4w_tt = m4w_tt;
	}


	public String getM4w_pa() {
		return m4w_pa;
	}


	public void setM4w_pa(String m4w_pa) {
		this.m4w_pa = m4w_pa;
	}


	public int getM4w_ay() {
		return m4w_ay;
	}


	public void setM4w_ay(int m4w_ay) {
		this.m4w_ay = m4w_ay;
	}


	public String getM4w_oc() {
		return m4w_oc;
	}


	public void setM4w_oc(String m4w_oc) {
		this.m4w_oc = m4w_oc;
	}


	public String getM4w_ll() {
		return m4w_ll;
	}


	public void setM4w_ll(String m4w_ll) {
		this.m4w_ll = m4w_ll;
	}


	public String getM4w_sg() {
		return m4w_sg;
	}


	public void setM4w_sg(String m4w_sg) {
		this.m4w_sg = m4w_sg;
	}


	public String getM4w_lr() {
		return m4w_lr;
	}


	public void setM4w_lr(String m4w_lr) {
		this.m4w_lr = m4w_lr;
	}


	public String getM4w_ls() {
		return m4w_ls;
	}


	public void setM4w_ls(String m4w_ls) {
		this.m4w_ls = m4w_ls;
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