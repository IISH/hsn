package iisg.nl.hsnnieuw;

import iisg.nl.hsnimport.Gebgtg;
import iisg.nl.hsnimport.Gebknd;
import iisg.nl.hsnmigrate.Const;
import iisg.nl.hsnmigrate.Constants;
import iisg.nl.hsnmigrate.Functions;
import iisg.nl.hsnmigrate.Utils;
import nl.iisg.ref.Ref;
import nl.iisg.ref.Ref_Municipality;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="b2")
public class B2 {

     @Column(name="IDNR")         private int idnr;
     @Column(name="B2W_SQ")       private int b2w_sq;
     @Column(name="B2W_LN")       private String b2w_ln;
     @Column(name="B2W_PF")       private String b2w_pf;
     @Column(name="B2W_FN")       private String b2w_fn;
     @Column(name="B2W_TT")       private String b2w_tt;
     @Column(name="B2W_PA")       private String b2w_pa;
     @Column(name="B2W_AY")       private int b2w_ay;
     @Column(name="B2W_OC")       private String b2w_oc;
     @Column(name="B2W_LL")       private String b2w_ll;
     @Column(name="B2W_SG")       private String b2w_sg;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")    private int recordID;
     
     @Transient                   private A1  b2w_lla;

     
     
     public void transform(Gebgtg gebgtg){	
     
    	// copy and/or combine
    	 
    	 setIdnr(gebgtg.getIdnr());
    	 setB2w_ln(gebgtg.getAnmgt());
    	 setB2w_pf(gebgtg.getTusgt());
    	 setB2w_fn(Utils.combine3FirstNames(gebgtg.getVrn1gt(), gebgtg.getVrn2gt(), gebgtg.getVrn3gt()));
    	 setB2w_ay(gebgtg.getLftgt());
    	 setB2w_oc(gebgtg.getBrpgt());
    	 setB2w_ll(gebgtg.getAdrgt());
    	 setB2w_sg(gebgtg.getHndgt());
    	 setB2w_sq(gebgtg.getVlgnrgt());

         int result = 0;
         
         if((result = Functions.vlslastname_f(getB2w_ln())) != 0)
           	Utils.message(result + Constants.E_VAB2W_LN, getIdnr(), 0, "HSN_CIVREC_STD", "B2", getB2w_ln());
       
    	 //  get part before "%" and split off prefix and title 
         
         String [] a = Functions.splitField(getB2w_ln());            
         setB2w_ln(a[0]); 
         setB2w_pf(a[1]);
         setB2w_tt(a[2]);
         
         
      // Check information
         
         if((result = Functions.empty_f(getB2w_ln())) != 0)
           	Utils.message(result + Constants.E_LEB2W_LN, getIdnr(), 0, "HSN_CIVREC_STD", "B2");
       
         if((result = Functions.empty_f(getB2w_fn())) != 0)
         	Utils.message(result + Constants.E_LEB2W_FN, getIdnr(), 0, "HSN_CIVREC_STD", "B2");
     
         if((result = Functions.empty_f(getB2w_oc())) != 0)
         	Utils.message(result + Constants.E_LEB2W_OC, getIdnr(), 0, "HSN_CIVREC_STD", "B2");
     
         if((result = Functions.age_f(getB2w_ay())) != 0)
         	Utils.message(result + Constants.E_AGB2W_AY, getIdnr(), 0, "HSN_CIVREC_STD", "B2", "" + getB2w_ay());
     
         if((result = Functions.vlslocation_f(getB2w_ll())) != 0)
          	Utils.message(result + Constants.E_VLB2W_LL, getIdnr(), 0, "HSN_CIVREC_STD", "B2", getB2w_ll());
      
         if((result = Functions.idem_f(getB2w_ll())) != 0)
           	Utils.message(result + Constants.E_IMB2W_LL, getIdnr(), 0, "HSN_CIVREC_STD", "B2", getB2w_ll());
       
         if((result = Functions.threedouble_f(getB2w_ll())) != 0)
         	Utils.message(result + Constants.E_X3B2W_LL, getIdnr(), 0, "HSN_CIVREC_STD", "B2", getB2w_ll());
      
         if((result = Functions.lastname_valid_f(getB2w_ll())) != 0)
         	Utils.message(result + Constants.E_ONB2W_LN, getIdnr(), 0, "HSN_CIVREC_STD", "B2", getB2w_ll());
      
         
        
         if((result = Functions.signature_f(getB2w_sg())) != 0)
            	Utils.message(result + Constants.E_SGB2W_SG, getIdnr(), 0, "HSN_CIVREC_STD", "B2", getB2w_sg());
         
         
         
         // Reference checks
         
         setB2w_fn(Functions.firstname_r(getB2w_fn(), Constants.E_FNB2W_FN, getIdnr(), 0, "HSN_CIVREC_STD", "B2"));
         setB2w_pf(Functions.prefix_r(getB2w_pf(), Constants.E_LNB2W_PF, getIdnr(), 0, "HSN_CIVREC_STD", "B2"));
         setB2w_ln(Functions.familyname_r(getB2w_ln(), Constants.E_LNB2W_LN, getIdnr(), 0, "HSN_CIVREC_STD", "B2"));
         //setB2w_ll(Functions.location_r(getB2w_ll(), Constants.E_LOB2W_LL, getIdnr(), 0, "HSN_CIVREC_STD", "B2"));
         setB2w_oc(Functions.profession_r(getB2w_oc(), Constants.E_OCM4W_OC, getIdnr(), 0, "HSN_CIVREC_STD", "B2"));
         
         setB2w_lla(Functions.location_r2(getB2w_ll(), Constants.E_LOB2W_LL, getIdnr(), 0, "HSN_CIVREC_STD", "B2"));
         if(getB2w_lla() != null){
        	getB2w_lla().setRole(4 + getB2w_sq());  // 5 or 6
        	
        	getB2w_lla().setStartDate(String.format("%02d-%02d-%02d", gebgtg.getGebknd().getGebdag(), gebgtg.getGebknd().getGebmnd(), gebgtg.getGebknd().getGebjr()));
        	getB2w_lla().setEndDate(String.format("%02d-%02d-%02d", gebgtg.getGebknd().getGebdag(), gebgtg.getGebknd().getGebmnd(), gebgtg.getGebknd().getGebjr()));

         	if(getB2w_lla().getLocationNumber() == null){
       			getB2w_lla().setMunicipality(gebgtg.getGebknd().getBirthActLocation());
       			getB2w_lla().setLocationNumber(gebgtg.getGebknd().getBirthActLocationNo());
         	}
         }
     }
     
     
    public void truncate(){
    	
     	String field = getB2w_ln();
    	int allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B2W_LN",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB2w_ln(field);
    	}

     	field = getB2w_pf();
    	allowedSize = Const.Smallstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B2W_PF",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB2w_pf(field);
    	}

     	field = getB2w_fn();
    	allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B2W_FN",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB2w_fn(field);
    	}

     	field = getB2w_tt();
    	allowedSize = Const.Smallstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B2W_TT",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB2w_tt(field);
    	}

     	field = getB2w_pa();
    	allowedSize = Const.Smallstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B2W_PA",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB2w_pa(field);
    	}

     	field = getB2w_oc();
    	allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B2W_OC",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB2w_oc(field);
    	}

     	field = getB2w_ll();
    	allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B2W_LL",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB2w_ll(field);
    	}

    	
    	 
    }
     
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public int getB2w_sq() {
		return b2w_sq;
	}
	public void setB2w_sq(int b2w_sq) {
		this.b2w_sq = b2w_sq;
	}
	public String getB2w_ln() {
		return b2w_ln;
	}
	public void setB2w_ln(String b2w_ln) {
		this.b2w_ln = b2w_ln;
	}
	public String getB2w_pf() {
		return b2w_pf;
	}
	public void setB2w_pf(String b2w_pf) {
		this.b2w_pf = b2w_pf;
	}
	public String getB2w_fn() {
		return b2w_fn;
	}
	public void setB2w_fn(String b2w_fn) {
		this.b2w_fn = b2w_fn;
	}
	public String getB2w_tt() {
		return b2w_tt;
	}
	public void setB2w_tt(String b2w_tt) {
		this.b2w_tt = b2w_tt;
	}
	public String getB2w_pa() {
		return b2w_pa;
	}
	public void setB2w_pa(String b2w_pa) {
		this.b2w_pa = b2w_pa;
	}
	public int getB2w_ay() {
		return b2w_ay;
	}
	public void setB2w_ay(int b2w_ay) {
		this.b2w_ay = b2w_ay;
	}
	public String getB2w_oc() {
		return b2w_oc;
	}
	public void setB2w_oc(String b2w_oc) {
		this.b2w_oc = b2w_oc;
	}
	public String getB2w_ll() {
		return b2w_ll;
	}
	public void setB2w_ll(String b2w_ll) {
		this.b2w_ll = b2w_ll;
	}
	public String getB2w_sg() {
		return b2w_sg;
	}
	public void setB2w_sg(String b2w_sg) {
		this.b2w_sg = b2w_sg;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}


	public A1 getB2w_lla() {
		return b2w_lla;
	}


	public void setB2w_lla(A1 b2w_lla) {
		this.b2w_lla = b2w_lla;
	}
     
     
     
     
     
     
}