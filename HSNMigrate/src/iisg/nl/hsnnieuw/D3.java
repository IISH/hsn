package iisg.nl.hsnnieuw;

import iisg.nl.hsnimport.Ovlagv;
import iisg.nl.hsnimport.Ovlech;
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
@Table(name="d3")
public class D3 {

     @Column(name="IDNR")         private int idnr;
     @Column(name="D3I_SQ")       private int d3i_sq;
     @Column(name="D3I_LN")       private String d3i_ln;
     @Column(name="D3I_PF")       private String d3i_pf;
     @Column(name="D3I_FN")       private String d3i_fn;
     @Column(name="D3I_TT")       private String d3i_tt;
     @Column(name="D3I_PA")       private String d3i_pa;
     @Column(name="D3I_LS")       private String d3i_ls;
     @Column(name="D3I_AY")       private int d3i_ay;
     @Column(name="D3I_OC")       private String d3i_oc;
     @Column(name="D3I_LL")       private String d3i_ll;
     @Column(name="D3I_SG")       private String d3i_sg;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")     private int recordID;
     
     
     public void transform(Ovlagv ovlagv){
         
         setIdnr(ovlagv.getIdnr());

         setD3i_sq(ovlagv.getVlgnrag());
         setD3i_ln(ovlagv.getAnmagv());
         setD3i_pf(ovlagv.getTusagv());
         setD3i_fn(Utils.combine3FirstNames(ovlagv.getVrn1agv(), ovlagv.getVrn2agv(), ovlagv.getVrn3agv()));
         if(getD3i_fn().length() > 50)
        	 setD3i_fn(getD3i_fn().substring(0,50)); // hack
         setD3i_ls(ovlagv.getRagvovl());
         setD3i_ay(ovlagv.getLftagv());
         setD3i_oc(ovlagv.getBrpagv());
         setD3i_ll(ovlagv.getAdragv());
         setD3i_sg(ovlagv.getHndagv());
         
       	 //  get part before "%" and split off prefix and title 

         int result = 0;
         
         if((result = Functions.lastname_valid_f(getD3i_ln())) != 0)
           	Utils.message(result + Constants.E_OND3I_LN, getIdnr(), 0, "HSN_CIVREC_STD", "D3", getD3i_ln());

         if((result = Functions.vlslastname_f(getD3i_ln())) != 0)
            	Utils.message(result + Constants.E_VAD3I_LN, getIdnr(), 0, "HSN_CIVREC_STD", "D3", getD3i_ln());
          
         if((result = Functions.empty_f(getD3i_ln())) != 0)
         	Utils.message(result + Constants.E_LED3I_LN, getIdnr(), 0, "HSN_CIVREC_STD", "D3");
       
         String [] a = Functions.splitField(getD3i_ln());            
         setD3i_ln(a[0]); 
         setD3i_pf(a[1]); 
         setD3i_tt(a[2]); 

         // Check information
         
         if((result = Functions.empty_f(getD3i_fn())) != 0)
          	Utils.message(result + Constants.E_LED3I_FN, getIdnr(), 0, "HSN_CIVREC_STD", "D3");
        
         if((result = Functions.age_f(getD3i_ay())) != 0)
           	Utils.message(result + Constants.E_AGD3I_AY, getIdnr(), 0, "HSN_CIVREC_STD", "D3", "" + getD3i_ay());
         
         if((result = Functions.empty_f(getD3i_oc())) != 0)
            	Utils.message(result + Constants.E_LED3I_OC, getIdnr(), 0, "HSN_CIVREC_STD", "D3");
          
         if((result = Functions.idem_f(getD3i_ll())) != 0)
         	Utils.message(result + Constants.E_IMD3I_LL, getIdnr(), 0, "HSN_CIVREC_STD", "D3", getD3i_ll());
       
         if((result = Functions.threedouble_f(getD3i_ll())) != 0)
          	Utils.message(result + Constants.E_X3D3I_LL, getIdnr(), 0, "HSN_CIVREC_STD", "D3", getD3i_ll());
        
         if((result = Functions.vlslocation_f(getD3i_ll())) != 0)
           	Utils.message(result + Constants.E_VLD3I_LL, getIdnr(), 0, "HSN_CIVREC_STD", "D3", getD3i_ll());
         
         if((result = Functions.signature_f(getD3i_sg())) != 0)
           	Utils.message(result + Constants.E_SGD3I_SG, getIdnr(), 0, "HSN_CIVREC_STD", "D3", getD3i_sg());
         
         // Reference checks
         
         setD3i_oc(Functions.profession_r(getD3i_oc(), Constants.E_OCD3I_OC, getIdnr(), 0, "HSN_CIVREC_STD", "D3"));
         
         setD3i_pf(Functions.prefix_r(getD3i_pf(), Constants.E_LND3I_PF, getIdnr(), 0, "HSN_CIVREC_STD", "D3"));
         setD3i_ln(Functions.familyname_r(getD3i_ln(), Constants.E_LND3I_LN, getIdnr(), 0, "HSN_CIVREC_STD", "D3"));
         setD3i_fn(Functions.firstname_r(getD3i_fn(), Constants.E_FND3I_FN, getIdnr(), 0, "HSN_CIVREC_STD", "D3"));

         
     }
     
    public void truncate(){
     	
    	String field = getD3i_ln();
    	int allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD", "HSN_CIVREC_STD", "D3I_LN",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setD3i_ln(field);
    	}
    	
    	field = getD3i_pf();
    	allowedSize = Const.Smallstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD", "HSN_CIVREC_STD",   "D3I_PF",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setD3i_pf(field);
    	}
    	
    	field = getD3i_fn();
    	allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD", "HSN_CIVREC_STD",   "D3I_FN",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setD3i_fn(field);
    	}
    	
    	field = getD3i_tt();
    	allowedSize = Const.Smallstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD", "HSN_CIVREC_STD",   "D3I_TT",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setD3i_tt(field);
    	}
    	
    	field = getD3i_pa();
    	allowedSize = Const.Smallstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD", "HSN_CIVREC_STD",  "D3I_PA",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setD3i_pa(field);
    	}
    	
    	field = getD3i_ls();
    	allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD", "HSN_CIVREC_STD",   "D3I_LS",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setD3i_ls(field);
    	}
    	
    	field = getD3i_oc();
    	allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD", "HSN_CIVREC_STD",  "D3I_OC",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setD3i_oc(field);
    	}
    	
    	field = getD3i_ll();
    	allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD", "HSN_CIVREC_STD",  "D3I_LL",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setD3i_ll(field);
    	}
    	
    	
    }
     
     
     
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public int getD3i_sq() {
		return d3i_sq;
	}
	public void setD3i_sq(int d3i_sq) {
		this.d3i_sq = d3i_sq;
	}
	public String getD3i_ln() {
		return d3i_ln;
	}
	public void setD3i_ln(String d3i_ln) {
		this.d3i_ln = d3i_ln;
	}
	public String getD3i_pf() {
		return d3i_pf;
	}
	public void setD3i_pf(String d3i_pf) {
		this.d3i_pf = d3i_pf;
	}
	public String getD3i_fn() {
		return d3i_fn;
	}
	public void setD3i_fn(String d3i_fn) {
		this.d3i_fn = d3i_fn;
	}
	public String getD3i_tt() {
		return d3i_tt;
	}
	public void setD3i_tt(String d3i_tt) {
		this.d3i_tt = d3i_tt;
	}
	public String getD3i_pa() {
		return d3i_pa;
	}
	public void setD3i_pa(String d3i_pa) {
		this.d3i_pa = d3i_pa;
	}
	public String getD3i_ls() {
		return d3i_ls;
	}
	public void setD3i_ls(String d3i_ls) {
		this.d3i_ls = d3i_ls;
	}
	public int getD3i_ay() {
		return d3i_ay;
	}
	public void setD3i_ay(int d3i_ay) {
		this.d3i_ay = d3i_ay;
	}
	public String getD3i_oc() {
		return d3i_oc;
	}
	public void setD3i_oc(String d3i_oc) {
		this.d3i_oc = d3i_oc;
	}
	public String getD3i_ll() {
		return d3i_ll;
	}
	public void setD3i_ll(String d3i_ll) {
		this.d3i_ll = d3i_ll;
	}
	public String getD3i_sg() {
		return d3i_sg;
	}
	public void setD3i_sg(String d3i_sg) {
		this.d3i_sg = d3i_sg;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}
     
     
     
     
     
}
