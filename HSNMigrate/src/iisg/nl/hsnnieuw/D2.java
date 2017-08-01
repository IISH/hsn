package iisg.nl.hsnnieuw;

import iisg.nl.hsnimport.Ovlech;
import iisg.nl.hsnimport.Ovlknd;
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
import javax.persistence.Transient;

@Entity
@Table(name="d2")
public class D2 {

     @Column(name="IDNR")         private int idnr;
     @Column(name="D2S_SQ")       private int d2s_sq;
     @Column(name="D2S_LN")       private String d2s_ln;
     @Column(name="D2S_PF")       private String d2s_pf;
     @Column(name="D2S_FN")       private String d2s_fn;
     @Column(name="D2S_TT")       private String d2s_tt;
     @Column(name="D2S_PA")       private String d2s_pa;
     @Column(name="D2S_CA")       private String d2s_ca;
     @Column(name="D2S_AY")       private int d2s_ay;
     @Column(name="D2S_OC")       private String d2s_oc;
     @Column(name="D2S_LL")       private String d2s_ll;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")     private int recordID;
     
     @Transient                   private A1  d2s_lla;  

     
     public void transform(Ovlech ovlech){
     
     setIdnr(ovlech.getIdnr());

     setD2s_sq(ovlech.getVlgech());
     setD2s_ln(ovlech.getAnmeovl());
     setD2s_pf(ovlech.getTuseovl());
     setD2s_fn(Utils.combine3FirstNames(ovlech.getVrn1eovl(), ovlech.getVrn2eovl(), ovlech.getVrn3eovl()));
     setD2s_ca(ovlech.getLeveovl());
     setD2s_ay(ovlech.getLfteovl());
     setD2s_oc(ovlech.getBrpeovl());
     setD2s_ll(ovlech.getAdreovl());
     
   	 //  get part before "%" and split off prefix and title 

     int result = 0;

     if((result = Functions.lastname_valid_f(getD2s_ln())) != 0)
      	Utils.message(result + Constants.E_OND2S_LN, getIdnr(), 0, "HSN_CIVREC_STD", "D2", getD2s_ln());
      
     if((result = Functions.vlslastname_f(getD2s_ln())) != 0)
       	Utils.message(result + Constants.E_VAD2S_LN, getIdnr(), 0, "HSN_CIVREC_STD", "D2", getD2s_ln());

     if((result = Functions.empty_f(getD2s_ln())) != 0)
     	Utils.message(result + Constants.E_LED2S_LN, getIdnr(), 0, "HSN_CIVREC_STD", "D2");

     String [] a = Functions.splitField(getD2s_ln());            
     setD2s_ln(a[0]); 
     setD2s_pf(a[1]); 
     setD2s_tt(a[2]); 

     //System.out.println("D2s_pf = " +d2s_pf );
     
     // Check information
     

     if((result = Functions.empty_f(getD2s_fn())) != 0)
     	Utils.message(result + Constants.E_LED2S_FN, getIdnr(), 0, "HSN_CIVREC_STD", "D2");

     if((result = Functions.yesnoO_f(getD2s_ca())) != 0)
      	Utils.message(result + Constants.E_JND2S_CA, getIdnr(), 0, "HSN_CIVREC_STD", "D2", getD2s_ca());

     if((result = Functions.age_f(getD2s_ay())) != 0)
       	Utils.message(result + Constants.E_AGD2S_AY, getIdnr(), 0, "HSN_CIVREC_STD", "D2", "" + getD2s_ay());

     if((result = Functions.empty_f(getD2s_oc())) != 0)
        	Utils.message(result + Constants.E_LED2S_OC, getIdnr(), 0, "HSN_CIVREC_STD", "D2");

     if((result = Functions.idem_f(getD2s_ll())) != 0)
     	Utils.message(result + Constants.E_IMD2S_LL, getIdnr(), 0, "HSN_CIVREC_STD", "D2", getD2s_ll());

     if((result = Functions.threedouble_f(getD2s_ll())) != 0)
      	Utils.message(result + Constants.E_X3D2S_LL, getIdnr(), 0, "HSN_CIVREC_STD", "D2", getD2s_ll());

     if((result = Functions.vlslocation_f(getD2s_ll())) != 0)
      	Utils.message(result + Constants.E_VLD2S_LL, getIdnr(), 0, "HSN_CIVREC_STD", "D2", getD2s_ll());

     // Reference checks
     
     setD2s_oc(Functions.profession_r(getD2s_oc(), Constants.E_OCD2S_OC, getIdnr(), 0, "HSN_CIVREC_STD", "D2"));
     setD2s_pf(Functions.prefix_r(getD2s_pf(), Constants.E_LND2S_PX, getIdnr(), 0, "HSN_CIVREC_STD", "D2"));
     setD2s_ln(Functions.familyname_r(getD2s_ln(), Constants.E_LND2S_LN, getIdnr(), 0, "HSN_CIVREC_STD", "D2"));
     setD2s_fn(Functions.firstname_r(getD2s_fn(), Constants.E_FND2S_FN, getIdnr(), 0, "HSN_CIVREC_STD", "D2"));
     //setD2s_ll(Functions.location_r(getD2s_ll(), Constants.E_LOD2S_LL, getIdnr(), 0, "HSN_CIVREC_STD", "D2"));

     setD2s_lla(Functions.location_r2(getD2s_ll(), Constants.E_LOD2S_LL, getIdnr(), 0, "HSN_CIVREC_STD", "D2"));
     if(getD2s_lla() != null){
    	 getD2s_lla().setRole(10);
     	
    	 getD2s_lla().setStartDate(String.format("%02d-%02d-%02d", ovlech.getOvlknd().getOaktedag(), ovlech.getOvlknd().getOaktemnd(), ovlech.getOvlknd().getOaktejr()));
    	 getD2s_lla().setEndDate(String.format("%02d-%02d-%02d",  ovlech.getOvlknd().getOaktedag(), ovlech.getOvlknd().getOaktemnd(), ovlech.getOvlknd().getOaktejr()));
     	
     	if(getD2s_lla().getLocationNumber() == null){
     		getD2s_lla().setLocationNumber(ovlech.getOvlknd().getOacgemnr() + "");
     		getD2s_lla().setMunicipality(ovlech.getOvlknd().getDeathActPlace());
     		
     	}
     }
     

     
     }
     
    public void truncate(){
        	
    	String field = getD2s_ln();
    	int allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D2S_LN",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setD2s_ln(field);
    	}
    	
    	field = getD2s_pf();
    	allowedSize = Const.Smallstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D2S_PF",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setD2s_pf(field);
    	}
    	
    	field = getD2s_fn();
    	allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D2S_FN",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setD2s_fn(field);
    	}
    	
    	field = getD2s_tt();
    	allowedSize = Const.Smallstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D2S_TT",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setD2s_tt(field);
    	}
    	
    	field = getD2s_pa();
    	allowedSize = Const.Smallstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D2S_PA",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setD2s_pa(field);
    	}
    	
    	field = getD2s_oc();
    	allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D2S_OC",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setD2s_oc(field);
    	}
    	
    	field = getD2s_ll();
    	allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D2S_LL",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setD2s_ll(field);
    	}
    	
    }

     
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public int getD2s_sq() {
		return d2s_sq;
	}
	public void setD2s_sq(int d2s_sq) {
		this.d2s_sq = d2s_sq;
	}
	public String getD2s_ln() {
		return d2s_ln;
	}
	public void setD2s_ln(String d2s_ln) {
		this.d2s_ln = d2s_ln;
	}
	public String getD2s_pf() {
		return d2s_pf;
	}
	public void setD2s_pf(String d2s_pf) {
		this.d2s_pf = d2s_pf;
	}
	public String getD2s_fn() {
		return d2s_fn;
	}
	public void setD2s_fn(String d2s_fn) {
		this.d2s_fn = d2s_fn;
	}
	public String getD2s_tt() {
		return d2s_tt;
	}
	public void setD2s_tt(String d2s_tt) {
		this.d2s_tt = d2s_tt;
	}
	public String getD2s_pa() {
		return d2s_pa;
	}
	public void setD2s_pa(String d2s_pa) {
		this.d2s_pa = d2s_pa;
	}
	public String getD2s_ca() {
		return d2s_ca;
	}
	public void setD2s_ca(String d2s_ca) {
		this.d2s_ca = d2s_ca;
	}
	public int getD2s_ay() {
		return d2s_ay;
	}
	public void setD2s_ay(int d2s_ay) {
		this.d2s_ay = d2s_ay;
	}
	public String getD2s_oc() {
		return d2s_oc;
	}
	public void setD2s_oc(String d2s_oc) {
		this.d2s_oc = d2s_oc;
	}
	public String getD2s_ll() {
		return d2s_ll;
	}
	public void setD2s_ll(String d2s_ll) {
		this.d2s_ll = d2s_ll;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}

	public A1 getD2s_lla() {
		return d2s_lla;
	}

	public void setD2s_lla(A1 d2s_lla) {
		this.d2s_lla = d2s_lla;
	}

     
}
