package iisg.nl.hsnnieuw;

import iisg.nl.hsnimport.Huwgtg;
import iisg.nl.hsnimport.Huwvrknd;
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
@Table(name="m5")
public class M5 {

     @Column(name="IDNR")         private int idnr;
     @Column(name="MAR_CD")       private int mar_cd;
     @Column(name="MAR_CM")       private int mar_cm;
     @Column(name="MAR_CY")       private int mar_cy;
     @Column(name="M5C_SQ")       private int m5c_sq;
     @Column(name="M5C_LN")       private String m5c_ln;
     @Column(name="M5C_PF")       private String m5c_pf;
     @Column(name="M5C_FN")       private String m5c_fn;
     @Column(name="M5C_TT")       private String m5c_tt;
     @Column(name="M5C_PA")       private String m5c_pa;
     @Column(name="M5C_BD")       private int m5c_bd;
     @Column(name="M5C_BM")       private int m5c_bm;
     @Column(name="M5C_BY")       private int m5c_by;
     @Column(name="M5C_GN")       private String m5c_gn;
     @Column(name="M5C_BL")       private String m5c_bl;
     @Column(name="M5C_RR")       private String m5c_rr;
     @Column(name="M5C_RW")       private String m5c_rw;
     @Column(name="M5C_FD")       private int m5c_fd;
     @Column(name="M5C_FM")       private int m5c_fm;
     @Column(name="M5C_FY")       private int m5c_fy;
     @Column(name="M5C_FL")       private String m5c_fl;
     @Column(name="M5C_OD")       private int m5c_od;
     @Column(name="M5C_OM")       private int m5c_om;
     @Column(name="M5C_OY")       private int m5c_oy;
     @Column(name="M5C_OL")       private String m5c_ol;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")     private int recordID;
 
     public void transform(Huwvrknd huwvrknd){
    	 
     // copy and/or combine
    	 
     setIdnr(huwvrknd.getIdnr());
     setMar_cd(huwvrknd.getHdag());
     setMar_cm(huwvrknd.getHmaand());
     setMar_cy(huwvrknd.getHjaar());
     setM5c_sq(huwvrknd.getVlgnrvk());
     setM5c_ln(huwvrknd.getAnmvk());
     setM5c_pf(huwvrknd.getTusvk());
     setM5c_fn(huwvrknd.getVrn1vk().split("%")[0] + " " + huwvrknd.getVrn2vk().split("%")[0] + " " + huwvrknd.getVrn3vk().split("%")[0]);
     setM5c_bd(huwvrknd.getGbdgvk());
     setM5c_bm(huwvrknd.getGbmdvk());
     setM5c_by(huwvrknd.getGbjrvk());
     setM5c_gn(huwvrknd.getGeslvk());
     setM5c_bl(huwvrknd.getGbplvk());
     setM5c_rr(huwvrknd.getErvk());
     setM5c_rw(huwvrknd.getErvkwie());
     setM5c_od(huwvrknd.getMekdgvk());
     setM5c_om(huwvrknd.getMekmdvk());
     setM5c_oy(huwvrknd.getMekjrvk());
     setM5c_ol(huwvrknd.getMekplvk());
     setM5c_fd(huwvrknd.getVekdgvk());
     setM5c_fm(huwvrknd.getVekmdvk());
     setM5c_fy(huwvrknd.getVekjrvk());
     setM5c_fl(huwvrknd.getVekplvk());
     
     int result = 0;
     
     if((result = Functions.vlslastname_f(getM5c_ln())) != 0)
       	Utils.message(result + Constants.E_VAM5C_LN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5", getM5c_ln());

      if((result = Functions.empty_f(getM5c_ln())) != 0)
        	Utils.message(result + Constants.E_LEM5C_LN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5");
      
      if((result = Functions.lastname_valid_f(getM5c_ln())) != 0)
         	Utils.message(result + Constants.E_ONM5C_LN, getIdnr(), 0, "HSN_CIVREC_STD", "M5", getM5c_ln());
         
   	 //  get part before "%" and split off prefix and title 
     
     String [] a = Functions.splitField(getM5c_ln());            
     setM5c_ln(a[0]); 
     setM5c_pf(a[1]); 
     setM5c_tt(a[2]); 
     
     // Check information
     
     if((result = Functions.date_f(getMar_cd(), getMar_cm(), getMar_cy())) != 0)
      	Utils.message(result + Constants.E_DAM5SDMY, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5", getMar_cd() + "-" + getMar_cm() + "-" + getMar_cy());

     if((result = Functions.date_f(getM5c_bd(), getM5c_bm(), getM5c_by())) != 0)
       	Utils.message(result + Constants.E_DAM5C_BY, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5", getM5c_bd() + "-" + getM5c_bm() + "-" + getM5c_by());

     if((result = Functions.date_f(getM5c_od(), getM5c_om(), getM5c_oy())) != 0)
        	Utils.message(result + Constants.E_DAM5C_OY, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5", getM5c_od() + "-" + getM5c_om() + "-" + getM5c_oy());

     if((result = Functions.date_f(getM5c_fd(), getM5c_fm(), getM5c_fy())) != 0)
     	Utils.message(result + Constants.E_DAM5C_FY, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5", getM5c_fd() + "-" + getM5c_fm() + "-" + getM5c_fy());

     if((result = Functions.empty_f(getM5c_fn())) != 0)
        	Utils.message(result + Constants.E_LEM5C_FN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5");

     if((result = Functions.gender_f(getM5c_gn())) != 0)
     	Utils.message(result + Constants.E_GNM5C_GN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5", getM5c_gn());

     if((result = Functions.empty_f(getM5c_bl())) != 0)
       	Utils.message(result + Constants.E_LEM5C_BL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5");

     if((result = Functions.vlslocation_f(getM5c_bl())) != 0)
       	Utils.message(result + Constants.E_VLM5C_BL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5");

     if((result = Functions.yesno_f(getM5c_rr())) != 0)
       	Utils.message(result + Constants.E_JNM5C_RR, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5", getM5c_rr());

     if((result = Functions.recognition_f(getM5c_rw())) != 0)
        	Utils.message(result + Constants.E_RWM5C_RW, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5", getM5c_rw());

     
     if(getM5c_rr().equalsIgnoreCase("J")){

    	 if((result = Functions.empty_f(getM5c_ol())) != 0)
    		 Utils.message(result + Constants.E_LEM5C_OL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5");

    	 if((result = Functions.empty_f(getM5c_fl())) != 0)
    		 Utils.message(result + Constants.E_LEM5C_FL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5");
    	 
         if((result = Functions.vlslocation_f(getM5c_ol())) != 0)
          	Utils.message(result + Constants.E_VLM5C_OL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5");

          if((result = Functions.vlslocation_f(getM5c_fl())) != 0)
          	Utils.message(result + Constants.E_VLM5C_FL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5");

     }
     
     // Reference checks
     
     setM5c_ln(Functions.familyname_r(getM5c_ln(), Constants.E_LNM5C_LN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5"));
     setM5c_pf(Functions.prefix_r(getM5c_pf(), Constants.E_LNM5C_PF, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5"));
     setM5c_fn(Functions.firstname_r(getM5c_fn(), Constants.E_FNM5C_FN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5"));
     setM5c_bl(Functions.location_r(getM5c_bl(), Constants.E_LOM5C_BL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5"));
     setM5c_ol(Functions.location_r(getM5c_ol(), Constants.E_LOM5C_OL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5"));
     setM5c_fl(Functions.location_r(getM5c_fl(), Constants.E_LOM5C_FL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5"));

     // logic checks
     
     int dayCountMarriage   = Utils.dayCount(getMar_cd(), getMar_cm(), getMar_cy());
     int dayCountBirth      = Utils.dayCount(getM5c_bd(), getM5c_bm(), getM5c_by());
     int dayCountRecFather  = Utils.dayCount(getM5c_fd(), getM5c_fm(), getM5c_fy());    
     int dayCountRecMother  = Utils.dayCount(getM5c_od(), getM5c_om(), getM5c_oy());
     
     if(dayCountBirth > 0 &&  dayCountMarriage > 0 && dayCountBirth >= dayCountMarriage)
       	Utils.message(300500 + Constants.E_DLM5M5BM, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5", getM5c_bd() + "-" + getM5c_bm() + "-" + getM5c_by() + " and " +
       			getMar_cd() + "-" + getMar_cm() + "-" + getMar_cy());
     
     if(dayCountRecFather > 0 && dayCountMarriage > 0 && dayCountRecFather >= dayCountMarriage)
     	Utils.message(300500 + Constants.E_DLM5M5FM, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5", getM5c_fd() + "-" + getM5c_fm() + "-" + getM5c_fy() + " and " +
       			getMar_cd() + "-" + getMar_cm() + "-" + getMar_cy());
     			
     if(dayCountRecMother > 0 && dayCountMarriage > 0 && dayCountRecMother >= dayCountMarriage)
     	Utils.message(300500 + Constants.E_DLM5M5OM, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5", getM5c_od() + "-" + getM5c_om() + "-" + getM5c_oy() + " and " +
       			getMar_cd() + "-" + getMar_cm() + "-" + getMar_cy());
     			
     if(dayCountRecFather > 0 && dayCountBirth > 0 && dayCountRecFather <= dayCountBirth)
    	 Utils.message(300500 + Constants.E_DLM5M5BF, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5", getM5c_bd() + "-" + getM5c_bm() + "-" + getM5c_by() + " and " +
    			 getM5c_fd() + "-" + getM5c_fm() + "-" + getM5c_fy());

     if(dayCountRecMother > 0 && dayCountBirth > 0 && dayCountRecMother <= dayCountBirth)
    	 Utils.message(300500 + Constants.E_DLM5M5BO, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M5", getM5c_bd() + "-" + getM5c_bm() + "-" + getM5c_by() + " and " +
    			 getM5c_od() + "-" + getM5c_om() + "-" + getM5c_oy());
     
}
     
     public void truncate(){
     	
         String field = getM5c_ln();
        	int allowedSize = Const.Bigstring;
        	if(field != null && field.length() > allowedSize){
        		Utils.message(1000000, getIdnr(), getMar_cy(), "HSN_CIVREC_STD",  "M5C_LN",  (new Integer(field.length()).toString()));
        		field = field.substring(0, allowedSize);
        		setM5c_ln(field);
        	}
        	
         field = getM5c_pf();
        	allowedSize = Const.Smallstring;
        	if(field != null && field.length() > allowedSize){
        		Utils.message(1000000, getIdnr(), getMar_cy(), "HSN_CIVREC_STD",  "M5C_PF",  (new Integer(field.length()).toString()));
        		field = field.substring(0, allowedSize);
        		setM5c_pf(field);
        	}
        	
         field = getM5c_fn();
        	allowedSize = Const.Bigstring;
        	if(field != null && field.length() > allowedSize){
        		Utils.message(1000000, getIdnr(), getMar_cy(), "HSN_CIVREC_STD",  "M5C_FN",  (new Integer(field.length()).toString()));
        		field = field.substring(0, allowedSize);
        		setM5c_fn(field);
        	}

         field = getM5c_tt();
        	allowedSize = Const.Smallstring;
        	if(field != null && field.length() > allowedSize){
        		Utils.message(1000000, getIdnr(), getMar_cy(), "HSN_CIVREC_STD",  "M5C_TT",  (new Integer(field.length()).toString()));
        		field = field.substring(0, allowedSize);
        		setM5c_tt(field);
        	}
        	
         field = getM5c_pa();
        	allowedSize = Const.Smallstring;
        	if(field != null && field.length() > allowedSize){
        		Utils.message(1000000, getIdnr(), getMar_cy(), "HSN_CIVREC_STD",  "M5C_PA",  (new Integer(field.length()).toString()));
        		field = field.substring(0, allowedSize);
        		setM5c_pa(field);
        	}
        	
         field = getM5c_bl();
        	allowedSize = Const.Bigstring;
        	if(field != null && field.length() > allowedSize){
        		Utils.message(1000000, getIdnr(), getMar_cy(), "HSN_CIVREC_STD",  "M5C_BL",  (new Integer(field.length()).toString()));
        		field = field.substring(0, allowedSize);
        		setM5c_bl(field);
        	}

         field = getM5c_fl();
        	allowedSize = Const.Bigstring;
        	if(field != null && field.length() > allowedSize){
        		Utils.message(1000000, getIdnr(), getMar_cy(), "HSN_CIVREC_STD",  "M5C_FL",  (new Integer(field.length()).toString()));
        		field = field.substring(0, allowedSize);
        		setM5c_fl(field);
        	}

         field = getM5c_ol();
        	allowedSize = Const.Bigstring;
        	if(field != null && field.length() > allowedSize){
        		Utils.message(1000000, getIdnr(), getMar_cy(), "HSN_CIVREC_STD",  "M5C_OL",  (new Integer(field.length()).toString()));
        		field = field.substring(0, allowedSize);
        		setM5c_ol(field);
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
	public int getM5c_sq() {
		return m5c_sq;
	}
	public void setM5c_sq(int m5c_sq) {
		this.m5c_sq = m5c_sq;
	}
	public String getM5c_ln() {
		return m5c_ln;
	}
	public void setM5c_ln(String m5c_ln) {
		this.m5c_ln = m5c_ln;
	}
	public String getM5c_pf() {
		return m5c_pf;
	}
	public void setM5c_pf(String m5c_pf) {
		this.m5c_pf = m5c_pf;
	}
	public String getM5c_fn() {
		return m5c_fn;
	}
	public void setM5c_fn(String m5c_fn) {
		this.m5c_fn = m5c_fn;
	}
	public String getM5c_tt() {
		return m5c_tt;
	}
	public void setM5c_tt(String m5c_tt) {
		this.m5c_tt = m5c_tt;
	}
	public String getM5c_pa() {
		return m5c_pa;
	}
	public void setM5c_pa(String m5c_pa) {
		this.m5c_pa = m5c_pa;
	}
	public int getM5c_bd() {
		return m5c_bd;
	}
	public void setM5c_bd(int m5c_bd) {
		this.m5c_bd = m5c_bd;
	}
	public int getM5c_bm() {
		return m5c_bm;
	}
	public void setM5c_bm(int m5c_bm) {
		this.m5c_bm = m5c_bm;
	}
	public int getM5c_by() {
		return m5c_by;
	}
	public void setM5c_by(int m5c_by) {
		this.m5c_by = m5c_by;
	}
	public String getM5c_gn() {
		return m5c_gn;
	}
	public void setM5c_gn(String m5c_gn) {
		this.m5c_gn = m5c_gn;
	}
	public String getM5c_bl() {
		return m5c_bl;
	}
	public void setM5c_bl(String m5c_bl) {
		this.m5c_bl = m5c_bl;
	}
	public String getM5c_rr() {
		return m5c_rr;
	}
	public void setM5c_rr(String m5c_rr) {
		this.m5c_rr = m5c_rr;
	}
	public String getM5c_rw() {
		return m5c_rw;
	}
	public void setM5c_rw(String m5c_rw) {
		this.m5c_rw = m5c_rw;
	}
	public int getM5c_fd() {
		return m5c_fd;
	}
	public void setM5c_fd(int m5c_fd) {
		this.m5c_fd = m5c_fd;
	}
	public int getM5c_fm() {
		return m5c_fm;
	}
	public void setM5c_fm(int m5c_fm) {
		this.m5c_fm = m5c_fm;
	}
	public int getM5c_fy() {
		return m5c_fy;
	}
	public void setM5c_fy(int m5c_fy) {
		this.m5c_fy = m5c_fy;
	}
	public String getM5c_fl() {
		return m5c_fl;
	}
	public void setM5c_fl(String m5c_fl) {
		this.m5c_fl = m5c_fl;
	}
	public int getM5c_od() {
		return m5c_od;
	}
	public void setM5c_od(int m5c_od) {
		this.m5c_od = m5c_od;
	}
	public int getM5c_om() {
		return m5c_om;
	}
	public void setM5c_om(int m5c_om) {
		this.m5c_om = m5c_om;
	}
	public int getM5c_oy() {
		return m5c_oy;
	}
	public void setM5c_oy(int m5c_oy) {
		this.m5c_oy = m5c_oy;
	}
	public String getM5c_ol() {
		return m5c_ol;
	}
	public void setM5c_ol(String m5c_ol) {
		this.m5c_ol = m5c_ol;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}

}
