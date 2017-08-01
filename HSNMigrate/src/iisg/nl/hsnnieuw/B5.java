package iisg.nl.hsnnieuw;

import iisg.nl.hsnimport.Gebkant;
import iisg.nl.hsnimport.Gebvdr;
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
@Table(name="b5")
public class B5 {

     @Column(name="IDNR")         private int idnr;
     @Column(name="B5SDCF")       private String b5sdcf;
     @Column(name="B5FALN")       private String b5faln;
     @Column(name="B5FAPF")       private String b5fapf;
     @Column(name="B5FAFN")       private String b5fafn;
     @Column(name="B5FATT")       private String b5fatt;
     @Column(name="B5FAPA")       private String b5fapa;
     @Column(name="B5FAAY")       private int b5faay;
     @Column(name="B5FAOC")       private String b5faoc;
     @Column(name="B5FALL")       private String b5fall;
     @Column(name="B5FASD")       private int b5fasd;
     @Column(name="B5FASM")       private int b5fasm;
     @Column(name="B5FASY")       private int b5fasy;
     @Column(name="B5FASL")       private String b5fasl;
     @Column(name="B5FADD")       private int b5fadd;
     @Column(name="B5FADM")       private int b5fadm;
     @Column(name="B5FADY")       private int b5fady;
     @Column(name="B5FADL")       private String b5fadl;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")     private int recordID;
     
     @Transient                   private A1  b5fasla;  
     @Transient                   private A1  b5fadla;
     @Transient                   private A1  b5falla;

     
     
     public void transform(Gebvdr gebvdr){
    	 
    	// System.out.println("XXXXXXXXXXXXXX");
  	    // copy and/or combine
    	 
    	 
 	    setIdnr(gebvdr.getIdnr());
 	    setB5sdcf(gebvdr.getGegvr());
 	    
 	    //if(gebvdr.getGegvr().equalsIgnoreCase("N"))
 	    	//return;
 	    
 	    setB5faln(gebvdr.getAnmvr());
 	    setB5fapf(gebvdr.getTusvr());
 	    setB5fafn(Utils.combine3FirstNames(gebvdr.getVrn1vr(),  gebvdr.getVrn2vr(), gebvdr.getVrn3vr()));
 	    setB5faay(gebvdr.getLftvr());
 	    setB5faoc(gebvdr.getBrpvr());
 	    setB5fall(gebvdr.getAdrvr());
 	    setB5fasd(gebvdr.getG5oosd());
 	    setB5fasm(gebvdr.getG5oosm());
 	    setB5fasy(gebvdr.getG5oosj());
 	    setB5fasl(gebvdr.getG5oogs());
 	    setB5fadd(gebvdr.getG5vood());
 	    setB5fadm(gebvdr.getG5voom());
 	    setB5fady(gebvdr.getG5vooj());
 	    setB5fadl(gebvdr.getG5vogo());

 	    
        int result = 0;
        
        if((result = Functions.vlslastname_f(getB5faln())) != 0)
          	Utils.message(result + Constants.E_VAB5FALN, getIdnr(), 0, "HSN_CIVREC_STD", "B5", getB5faln());

        if((result = Functions.empty_f(getB5faln())) != 0)
          	Utils.message(result + Constants.E_LEB5FALN, getIdnr(), 0, "HSN_CIVREC_STD", "B5");
        
        if((result = Functions.lastname_valid_f(getB5faln())) != 0)
         	Utils.message(result + Constants.E_ONB5FALN, getIdnr(), 0, "HSN_CIVREC_STD", "B5", getB5faln());

          
 	    
   	 //  get part before "%" and split off prefix and title 
        
        
        String [] a = Functions.splitField(getB5faln());            
        setB5faln(a[0]); 
        setB5fapf(a[1]);
        setB5fatt(a[2]);

         
         // Check information
         
         if((result = Functions.empty_f(getB5fafn())) != 0)
           	Utils.message(result + Constants.E_LEB5FAFN, getIdnr(), 0, "HSN_CIVREC_STD", "B5");
           
         if((result = Functions.age_f(getB5faay())) != 0)
           	Utils.message(result + Constants.E_AGB5FAAY, getIdnr(), 0, "HSN_CIVREC_STD", "B5", "" + getB5faay());
           
         if((result = Functions.empty_f(getB5faoc())) != 0)
           	Utils.message(result + Constants.E_LEB5FAOC, getIdnr(), 0, "HSN_CIVREC_STD", "B5");

         if((result = Functions.vlslocation_f(getB5fall())) != 0){
           	Utils.message(result + Constants.E_VLB5FALL, getIdnr(), 0, "HSN_CIVREC_STD", "B5", getB5fall());
         }
                                             
         if((result = Functions.idem_f(getB5fall())) != 0)
           	Utils.message(result + Constants.E_IMB5FALL, getIdnr(), 0, "HSN_CIVREC_STD", "B5", getB5fall());
           
         if((result = Functions.threedouble_f(getB5fall())) != 0)
           	Utils.message(result + Constants.E_X3B5FALL, getIdnr(), 0, "HSN_CIVREC_STD", "B5", getB5fall());
           
         if((result = Functions.date_f(getB5fasd(), getB5fasm(), getB5fasy())) != 0)
           	Utils.message(result + Constants.E_DAB5FASY, getIdnr(), 0, "HSN_CIVREC_STD", "B5",  getB5fasd() + "-" + getB5fasm() + "-" + getB5fasy());
           
         if((result = Functions.vlslocation_f(getB5fasl())) != 0)
         	Utils.message(result + Constants.E_VLB5FASL, getIdnr(), 0, "HSN_CIVREC_STD", "B5", getB5fasl());
      
         if((result = Functions.idem_f(getB5fasl())) != 0)
         	Utils.message(result + Constants.E_IMB5FASL, getIdnr(), 0, "HSN_CIVREC_STD", "B5", getB5fasl());
      
         if((result = Functions.threedouble_f(getB5fasl())) != 0){
         	Utils.message(result + Constants.E_X3B5FASL, getIdnr(), 0, "HSN_CIVREC_STD", "B5", getB5fasl());
         	
         }
         
         if((result = Functions.date_f(getB5fadd(), getB5fadm(), getB5fady())) != 0)
            	Utils.message(result + Constants.E_DAB5FADY, getIdnr(), 0, "HSN_CIVREC_STD", "B5",getB5fadd() + "-" + getB5fadm() + "-" + getB5fady());
          
         if((result = Functions.vlslocation_f(getB5fadl())) != 0)
         	Utils.message(result + Constants.E_VLB5FADL, getIdnr(), 0, "HSN_CIVREC_STD", "B5", getB5fadl());

         if((result = Functions.idem_f(getB5fadl())) != 0)
         	Utils.message(result + Constants.E_IMB5FADL, getIdnr(), 0, "HSN_CIVREC_STD", "B5", getB5fadl());

         if((result = Functions.threedouble_f(getB5fadl())) != 0)
         	Utils.message(result + Constants.E_X3B5FADL, getIdnr(), 0, "HSN_CIVREC_STD", "B5", getB5fadl());

         // Reference checks
         
         setB5fafn(Functions.firstname_r(getB5fafn(), Constants.E_FNB5FAFN, getIdnr(), 0, "HSN_CIVREC_STD", "B5"));
         setB5fapf(Functions.prefix_r(getB5fapf(), Constants.E_LNB5FAPF, getIdnr(), 0, "HSN_CIVREC_STD", "B5"));
         setB5faln(Functions.familyname_r(getB5faln(), Constants.E_LNB5FALN, getIdnr(), 0, "HSN_CIVREC_STD", "B5"));
         setB5faoc(Functions.profession_r(getB5faoc(), Constants.E_OCB5FAOC, getIdnr(), 0, "HSN_CIVREC_STD", "B5"));
         //setB5fasl(Functions.location_r(getB5fasl(), Constants.E_LOB5FASL, getIdnr(), 0, "HSN_CIVREC_STD", "B5"));
         //setB5fadl(Functions.location_r(getB5fadl(), Constants.E_LOB5FADL, getIdnr(), 0, "HSN_CIVREC_STD", "B5"));
         //setB5fadl(Functions.location_r(getB5fall(), Constants.E_LOB5FALL, getIdnr(), 0, "HSN_CIVREC_STD", "B5"));

         setB5fasla(Functions.location_r2(getB5fasl(), Constants.E_LOB5FASL, getIdnr(), 0, "HSN_CIVREC_STD", "B5"));
         if(getB5fasla() != null){
         	getB5fasla().setRole(4);
         	
        	getB5fasla().setStartDate(String.format("%02d-%02d-%02d", gebvdr.getGebknd().getGebdag(), gebvdr.getGebknd().getGebmnd(), gebvdr.getGebknd().getGebjr()));
        	getB5fasla().setEndDate(String.format("%02d-%02d-%02d", gebvdr.getGebknd().getGebdag(), gebvdr.getGebknd().getGebmnd(), gebvdr.getGebknd().getGebjr()));

         	if(getB5fasla().getLocationNumber() == null){
         		getB5fasla().setLocationNumber(gebvdr.getGebknd().getBirthActLocationNo());
         		getB5fasla().setMunicipality(gebvdr.getGebknd().getBirthActLocation());
         		
         	}
         }

         setB5fadla(Functions.location_r2(getB5fadl(), Constants.E_LOB5FADL, getIdnr(), 0, "HSN_CIVREC_STD", "B5"));
         if(getB5fadla() != null){
         	getB5fadla().setRole(4);
         	
        	getB5fadla().setStartDate(String.format("%02d-%02d-%02d", gebvdr.getGebknd().getGebdag(), gebvdr.getGebknd().getGebmnd(), gebvdr.getGebknd().getGebjr()));
        	getB5fadla().setEndDate(String.format("%02d-%02d-%02d", gebvdr.getGebknd().getGebdag(), gebvdr.getGebknd().getGebmnd(), gebvdr.getGebknd().getGebjr()));

         	if(getB5fadla().getLocationNumber() == null){
         		getB5fadla().setLocationNumber(gebvdr.getGebknd().getBirthActLocationNo());
         		getB5fadla().setMunicipality(gebvdr.getGebknd().getBirthActLocation());
         		
         	}
         }

         setB5falla(Functions.location_r2(getB5fall(), Constants.E_LOB5FALL, getIdnr(), 0, "HSN_CIVREC_STD", "B5"));
         if(getB5falla() != null){
         	getB5falla().setRole(4);
         	
        	getB5falla().setStartDate(String.format("%02d-%02d-%02d", gebvdr.getGebknd().getGebdag(), gebvdr.getGebknd().getGebmnd(), gebvdr.getGebknd().getGebjr()));
        	getB5falla().setEndDate(String.format("%02d-%02d-%02d", gebvdr.getGebknd().getGebdag(), gebvdr.getGebknd().getGebmnd(), gebvdr.getGebknd().getGebjr()));

         	
         	if(getB5falla().getLocationNumber() == null){
         		getB5falla().setLocationNumber(gebvdr.getGebknd().getBirthActLocationNo());
         		getB5falla().setMunicipality(gebvdr.getGebknd().getBirthActLocation());
         		
         	}
         }

         
    	 
     }
     
     
    public void truncate(){
      	
      	String field = getB5faln();
      	int allowedSize = Const.Bigstring;
      	if(field != null && field.length() > allowedSize){
      		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B5FALN",  (new Integer(field.length()).toString()));
      		field = field.substring(0, allowedSize);
      		setB5faln(field);
      	}
      	
      	field = getB5fapf();
      	allowedSize = Const.Smallstring;
      	if(field != null && field.length() > allowedSize){
      		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B5FAPF",  (new Integer(field.length()).toString()));
      		field = field.substring(0, allowedSize);
      		setB5fapf(field);
      	}
      	
      	field = getB5fafn();
      	allowedSize = Const.Bigstring;
      	if(field != null && field.length() > allowedSize){
      		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B5FAFN",  (new Integer(field.length()).toString()));
      		field = field.substring(0, allowedSize);
      		setB5fafn(field);
      	}

      	field = getB5fatt();
      	allowedSize = Const.Smallstring;
      	if(field != null && field.length() > allowedSize){
      		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B5FATT",  (new Integer(field.length()).toString()));
      		field = field.substring(0, allowedSize);
      		setB5fatt(field);
      	}
      	
      	field = getB5fapa();
      	allowedSize = Const.Smallstring;
      	if(field != null && field.length() > allowedSize){
      		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B5FAPA",  (new Integer(field.length()).toString()));
      		field = field.substring(0, allowedSize);
      		setB5fapa(field);
      	}
      	
      	field = getB5faoc();
      	allowedSize = Const.Bigstring;
      	if(field != null && field.length() > allowedSize){
      		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B5FAOC",  (new Integer(field.length()).toString()));
      		field = field.substring(0, allowedSize);
      		setB5faoc(field);
      	}

      	field = getB5fall();
      	allowedSize = Const.Bigstring;
      	if(field != null && field.length() > allowedSize){
      		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B5FALL",  (new Integer(field.length()).toString()));
      		field = field.substring(0, allowedSize);
      		setB5fall(field);
      	}

      	field = getB5fasl();
      	allowedSize = Const.Bigstring;
      	if(field != null && field.length() > allowedSize){
      		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B5FASL",  (new Integer(field.length()).toString()));
      		field = field.substring(0, allowedSize);
      		setB5fasl(field);
      	}

      	field = getB5fadl();
      	allowedSize = Const.Bigstring;
      	if(field != null && field.length() > allowedSize){
      		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B5FADL",  (new Integer(field.length()).toString()));
      		field = field.substring(0, allowedSize);
      		setB5fadl(field);
      	}


      	
      	
   	}

     
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public String getB5sdcf() {
		return b5sdcf;
	}
	public void setB5sdcf(String b5sdcf) {
		this.b5sdcf = b5sdcf;
	}
	public String getB5faln() {
		return b5faln;
	}
	public void setB5faln(String b5faln) {
		this.b5faln = b5faln;
	}
	public String getB5fapf() {
		return b5fapf;
	}
	public void setB5fapf(String b5fapf) {
		this.b5fapf = b5fapf;
	}
	public String getB5fafn() {
		return b5fafn;
	}
	public void setB5fafn(String b5fafn) {
		this.b5fafn = b5fafn;
	}
	public String getB5fatt() {
		return b5fatt;
	}
	public void setB5fatt(String b5fatt) {
		this.b5fatt = b5fatt;
	}
	public String getB5fapa() {
		return b5fapa;
	}
	public void setB5fapa(String b5fapa) {
		this.b5fapa = b5fapa;
	}
	public int getB5faay() {
		return b5faay;
	}
	public void setB5faay(int b5faay) {
		this.b5faay = b5faay;
	}
	public String getB5faoc() {
		return b5faoc;
	}
	public void setB5faoc(String b5faoc) {
		this.b5faoc = b5faoc;
	}
	public String getB5fall() {
		return b5fall;
	}
	public void setB5fall(String b5fall) {
		this.b5fall = b5fall;
	}
	public int getB5fasd() {
		return b5fasd;
	}
	public void setB5fasd(int b5fasd) {
		this.b5fasd = b5fasd;
	}
	public int getB5fasm() {
		return b5fasm;
	}
	public void setB5fasm(int b5fasm) {
		this.b5fasm = b5fasm;
	}
	public int getB5fasy() {
		return b5fasy;
	}
	public void setB5fasy(int b5fasy) {
		this.b5fasy = b5fasy;
	}
	public String getB5fasl() {
		return b5fasl;
	}
	public void setB5fasl(String b5fasl) {
		this.b5fasl = b5fasl;
	}
	public int getB5fadd() {
		return b5fadd;
	}
	public void setB5fadd(int b5fadd) {
		this.b5fadd = b5fadd;
	}
	public int getB5fadm() {
		return b5fadm;
	}
	public void setB5fadm(int b5fadm) {
		this.b5fadm = b5fadm;
	}
	public int getB5fady() {
		return b5fady;
	}
	public void setB5fady(int b5fady) {
		this.b5fady = b5fady;
	}
	public String getB5fadl() {
		return b5fadl;
	}
	public void setB5fadl(String b5fadl) {
		this.b5fadl = b5fadl;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}


	public A1 getB5fasla() {
		return b5fasla;
	}


	public void setB5fasla(A1 b5fasla) {
		this.b5fasla = b5fasla;
	}


	public A1 getB5fadla() {
		return b5fadla;
	}


	public void setB5fadla(A1 b5fadla) {
		this.b5fadla = b5fadla;
	}


	public A1 getB5falla() {
		return b5falla;
	}


	public void setB5falla(A1 b5falla) {
		this.b5falla = b5falla;
	}
     
     
     
     
     
     
     
     
     
     
     
     
}