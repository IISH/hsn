package iisg.nl.hsnnieuw;

import iisg.nl.hsnimport.Huweer;
import iisg.nl.hsnimport.Huwgtg;
import iisg.nl.hsnmigrate.Const;
import iisg.nl.hsnmigrate.Constants;
import iisg.nl.hsnmigrate.Functions;
import iisg.nl.hsnmigrate.Utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
     
     public void transform(Huwgtg huwgtg){
    	 
   	    // copy and/or combine
     
     setIdnr(huwgtg.getIdnr());
     setMar_cd(huwgtg.getHdag());
     setMar_cm(huwgtg.getHmaand());
     setMar_cy(huwgtg.getHjaar());
     setM4sdsq(huwgtg.getVlgnrgt());
     setM4w_ln(huwgtg.getAnmgt());
     setM4w_pf(huwgtg.getTusgt());
     setM4w_fn(Utils.combine3FirstNames(huwgtg.getVrn1gt(), huwgtg.getVrn2gt(), huwgtg.getVrn3gt()));
     setM4w_ay(huwgtg.getLftjgt());
     setM4w_oc(huwgtg.getBrpgt());
     setM4w_ll(huwgtg.getAdrgt());
     setM4w_sg(huwgtg.getHndgt());
     setM4w_lr(huwgtg.getRelwie());
     setM4w_ls(huwgtg.getRelgt());

     int result = 0;
     
     if((result = Functions.vlslastname_f(getM4w_ln())) != 0)
       	Utils.message(result + Constants.E_VAM4W_LN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M4", getM4w_ln());

      if((result = Functions.empty_f(getM4w_ln())) != 0)
        	Utils.message(result + Constants.E_LEM4W_LN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M4");
      
      if((result = Functions.lastname_valid_f(getM4w_ln())) != 0)
       	Utils.message(result + Constants.E_ONM4W_LN, getIdnr(), 0, "HSN_CIVREC_STD", "M4", getM4w_ln());
       


   	 //  get part before "%" and split off prefix and title 
     
     String [] a = Functions.splitField(getM4w_ln());            
     setM4w_ln(a[0]); 
     setM4w_pf(a[1]); 
     setM4w_tt(a[2]); 
     
     // Check information
     
     if((result = Functions.date_f(getMar_cd(), getMar_cm(), getMar_cy())) != 0)
     	Utils.message(result + Constants.E_DAM4SDMY, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M4", getMar_cd() + "-" + getMar_cm() + "-" + getMar_cy());

     if((result = Functions.empty_f(getM4w_fn())) != 0)
        	Utils.message(result + Constants.E_LEM4W_FN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M4");

     if((result = Functions.age_f(getM4w_ay())) != 0)
     	Utils.message(result + Constants.E_AGM4W_AY, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M4", "" + getM4w_ay());

     if((result = Functions.empty_f(getM4w_oc())) != 0)
      	Utils.message(result + Constants.E_LEM4W_OC, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M4");

     if((result = Functions.empty_f(getM4w_ll())) != 0)
       	Utils.message(result + Constants.E_LEM4W_LL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M4");

     if((result = Functions.vlslocation_f(getM4w_ll())) != 0)
        	Utils.message(result + Constants.E_VLM4W_LL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M4", getM4w_ll());

     if((result = Functions.idem_f(getM4w_ll())) != 0)
     	Utils.message(result + Constants.E_IMM4W_LL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M4", getM4w_ll());

     if((result = Functions.threedouble_f(getM4w_ll())) != 0)
      	Utils.message(result + Constants.E_X3M4W_LL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M4", getM4w_ll());

     if((result = Functions.signature_f(getM4w_sg())) != 0)
       	Utils.message(result + Constants.E_SGM4W_SG, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M4", getM4w_sg());

     if((result = Functions.relation_f(getM4w_lr())) != 0)
       	Utils.message(result + Constants.E_LRM4W_LR, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M4", getM4w_lr());

     // Reference checks
     
     setM4w_ln(Functions.familyname_r(getM4w_ln(), Constants.E_LNM4W_LN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M4"));
     setM4w_pf(Functions.prefix_r(getM4w_pf(), Constants.E_LNM4W_PF, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M4"));
     setM4w_fn(Functions.firstname_r(getM4w_fn(), Constants.E_FNM4W_FN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M4"));
     setM4w_oc(Functions.profession_r(getM4w_oc(), Constants.E_OCM4W_OC, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M4"));

     
     }
     
     public void truncate(){
        	
        String field = getM4w_ln();
       	int allowedSize = Const.Bigstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), getMar_cy(), "HSN_CIVREC_STD",  "M4W_LN",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setM4w_ln(field);
       	}
       	
        field = getM4w_pf();
       	allowedSize = Const.Smallstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), getMar_cy(), "HSN_CIVREC_STD",  "M4W_PF",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setM4w_pf(field);
       	}
       	
        field = getM4w_fn();
       	allowedSize = Const.Bigstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), getMar_cy(), "HSN_CIVREC_STD",  "M4W_FN",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setM4w_fn(field);
       	}

        field = getM4w_tt();
       	allowedSize = Const.Smallstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), getMar_cy(), "HSN_CIVREC_STD",  "M4W_TT",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setM4w_tt(field);
       	}
       	
        field = getM4w_pa();
       	allowedSize = Const.Smallstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), getMar_cy(), "HSN_CIVREC_STD",  "M4W_PA",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setM4w_pa(field);
       	}
       	
        field = getM4w_oc();
       	allowedSize = Const.Bigstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), getMar_cy(), "HSN_CIVREC_STD",  "M4W_OC",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setM4w_oc(field);
       	}

        field = getM4w_ll();
       	allowedSize = Const.Bigstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), getMar_cy(), "HSN_CIVREC_STD",  "M4W_LL",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setM4w_ll(field);
       	}

        field = getM4w_ls();
       	allowedSize = Const.Bigstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), getMar_cy(), "HSN_CIVREC_STD",  "M4W_LS",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setM4w_ls(field);
       	}


       	
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
}