package iisg.nl.hsnnieuw;
import iisg.nl.hsnimport.Huwafk;
import iisg.nl.hsnimport.Huweer;
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
     
     
     public void transform(Huweer huweer){
    	 
  	    // copy and/or combine
     	 
    	 setIdnr(huweer.getIdnr());
	     setMar_cd(huweer.getHdag());
	     setMar_cm(huweer.getHmaand());
	     setMar_cy(huweer.getHjaar());
	     setM3sdsq(huweer.getVlgnreh());
	     setM3rpgn(huweer.getHuwer());
	     setM3s_ln(huweer.getAnmeh());
	     setM3s_pf(huweer.getTuseh());
	     setM3s_fn(Utils.combine3FirstNames(huweer.getVrn1eh(), huweer.getVrn2eh(), huweer.getVrn3eh()));
	     setM3s_sr(huweer.getEindeh());

	     
         int result = 0;
         
         if((result = Functions.vlslastname_f(getM3s_ln())) != 0)
           	Utils.message(result + Constants.E_VAM3S_LN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M3", getM3s_ln());

          if((result = Functions.empty_f(getM3s_ln())) != 0)
            	Utils.message(result + Constants.E_LEM3S_LN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M3");


       	 //  get part before "%" and split off prefix and title 
         
         String [] a = Functions.splitField(getM3s_ln());            
         setM3s_ln(a[0]); 
         setM3s_pf(a[1]); 
         setM3s_tt(a[2]); 

         
    	 
         // Check information
         
         if((result = Functions.date_f(getMar_cd(), getMar_cm(), getMar_cy())) != 0)
            	Utils.message(result + Constants.E_DAM3SDMY, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M3", getMar_cd() + "-" + getMar_cm() + "-" + getMar_cy());

         if((result = Functions.gender_f(getM3rpgn())) != 0)
         	Utils.message(result + Constants.E_GLM3RPGN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M3", getM3rpgn());

         if((result = Functions.empty_f(getM3s_fn())) != 0)
            	Utils.message(result + Constants.E_LEM3S_FN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M3");

         if((result = Functions.divorce_f(getM3s_sr())) != 0)
            	Utils.message(result + Constants.E_SRM3S_SR, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M3", getM3s_sr());
         
         // Reference checks
         
         setM3s_fn(Functions.firstname_r(getM3s_fn(), Constants.E_FNM3S_FN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M3"));
         setM3s_pf(Functions.prefix_r(getM3s_pf(), Constants.E_LNM3S_PF, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M3"));
         setM3s_ln(Functions.familyname_r(getM3s_ln(), Constants.E_LNM3S_LN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M3"));
	     
     }
     
    public void truncate(){
       	
       	String field = getM3s_ln();
      	int allowedSize = Const.Bigstring;
      	if(field != null && field.length() > allowedSize){
      		Utils.message(1000000, getIdnr(), getMar_cy(), "HSN_CIVREC_STD",  "M3S_LN",  (new Integer(field.length()).toString()));
      		field = field.substring(0, allowedSize);
      		setM3s_ln(field);
      	}
      	
        field = getM3s_pf();
      	allowedSize = Const.Smallstring;
      	if(field != null && field.length() > allowedSize){
      		Utils.message(1000000, getIdnr(), getMar_cy(), "HSN_CIVREC_STD",  "M3S_PF",  (new Integer(field.length()).toString()));
      		field = field.substring(0, allowedSize);
      		setM3s_pf(field);
      	}
      	
        field = getM3s_fn();
      	allowedSize = Const.Bigstring;
      	if(field != null && field.length() > allowedSize){
      		Utils.message(1000000, getIdnr(), getMar_cy(), "HSN_CIVREC_STD",  "M3S_FN",  (new Integer(field.length()).toString()));
      		field = field.substring(0, allowedSize);
      		setM3s_fn(field);
      	}

        field = getM3s_tt();
      	allowedSize = Const.Smallstring;
      	if(field != null && field.length() > allowedSize){
      		Utils.message(1000000, getIdnr(), getMar_cy(), "HSN_CIVREC_STD",  "M3S_TT",  (new Integer(field.length()).toString()));
      		field = field.substring(0, allowedSize);
      		setM3s_tt(field);
      	}
      	
        field = getM3s_pa();
      	allowedSize = Const.Smallstring;
      	if(field != null && field.length() > allowedSize){
      		Utils.message(1000000, getIdnr(), getMar_cy(), "HSN_CIVREC_STD",  "M3S_PA",  (new Integer(field.length()).toString()));
      		field = field.substring(0, allowedSize);
      		setM3s_pa(field);
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
}