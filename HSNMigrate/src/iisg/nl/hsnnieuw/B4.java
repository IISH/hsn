package iisg.nl.hsnnieuw;

import iisg.nl.hsnimport.Gebkant;
import iisg.nl.hsnimport.Gebknd;
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
@Table(name="b4")
public class B4 {

     @Column(name="IDNR")         private int idnr;
     @Column(name="B4SDCT")       private int b4sdct;
     @Column(name="B4SDCD")       private int b4sdcd;
     @Column(name="B4SDCM")       private int b4sdcm;
     @Column(name="B4SDCY")       private int b4sdcy;
     @Column(name="B4SDMD")       private int b4sdmd;
     @Column(name="B4SDMM")       private int b4sdmm;
     @Column(name="B4SDMY")       private int b4sdmy;
     @Column(name="B4SDML")       private String b4sdml;
     @Column(name="B4FALN")       private String b4faln;
     @Column(name="B4FAPF")       private String b4fapf;
     @Column(name="B4FAFN")       private String b4fafn;
     @Column(name="B4FATT")       private String b4fatt;
     @Column(name="B4FAPA")       private String b4fapa;
     @Column(name="B4SDRD")       private int b4sdrd;
     @Column(name="B4SDRM")       private int b4sdrm;
     @Column(name="B4SDRY")       private int b4sdry;
     @Column(name="B4SDRS")       private int b4sdrs;
     @Column(name="B4SDRN")       private int b4sdrn;
     @Column(name="B4RPLN")       private String b4rpln;
     @Column(name="B4RPFN")       private String b4rpfn;
     @Column(name="B4RPPF")       private String b4rppf;
     @Column(name="B4RPTT")       private String b4rptt;
     @Column(name="B4RPPA")       private String b4rppa;
     @Column(name="B4RPGN")       private String b4rpgn;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")     private int recordID;
     
     public void transform(Gebkant gebkant){
    	 
    	 //System.out.println("!!!!!!!!!!!!!!!");
  	    // copy and/or combine
    	 
    	 
 	    setIdnr(gebkant.getIdnr());
 	    setB4sdct(gebkant.getKanttype());
 	    setB4sdcd(gebkant.getKantdag());
 	    setB4sdcm(gebkant.getKantmnd());
 	    setB4sdcy(gebkant.getKantjr());
 	    setB4sdmd(gebkant.getKhuwdag());
 	    setB4sdmm(gebkant.getKhuwmnd());
 	    setB4sdmy(gebkant.getKhuwjr());
 	    setB4sdml(gebkant.getKhuwgem());
 	    setB4faln(gebkant.getKanmvad());
 	    setB4fapf(gebkant.getKtusvad());
 	    setB4fafn(Utils.combine3FirstNames(gebkant.getKvrn1vad(), gebkant.getKvrn2vad(), gebkant.getKvrn3vad()));
 	    setB4sdrd(gebkant.getKwyzdag());
 	    setB4sdrm(gebkant.getKwyzmnd());
 	    setB4sdry(gebkant.getKwyzjr());
 	    setB4sdrs(gebkant.getKwyzkb());
 	    setB4sdrn(gebkant.getKwyzstbl());
 	    setB4rpln(gebkant.getKanmgeb());
 	    setB4rpfn(Utils.combine3FirstNames(gebkant.getKvrn1geb(), gebkant.getKvrn2geb(), gebkant.getKvrn3geb()));
 	    setB4rppf(gebkant.getKtusgeb());
 	    setB4rpgn(gebkant.getKsexgeb());
         
 	    
 	    //  get part before "%" and split off prefix and title 
        
 	   int result = 0;
 	   
       if((result = Functions.vlslastname_f(getB4faln())) != 0)
          	Utils.message(result + Constants.E_VAB4FALN, getIdnr(), 0, "HSN_CIVREC_STD", "B4", getB4faln());
         
        if((result = Functions.empty_f(getB4faln())) != 0)
          	Utils.message(result + Constants.E_LEB4FALN, getIdnr(), 0, "HSN_CIVREC_STD", "B4");
        
        if((result = Functions.lastname_valid_f(getB4faln())) != 0)
        	Utils.message(result + Constants.E_ONB4FALN, getIdnr(), 0, "HSN_CIVREC_STD", "B4", getB4faln());

         
        String [] a = Functions.splitField(getB4faln());            
        setB4faln(a[0]); 
        setB4fapf(a[1]); 
        setB4fatt(a[2]); 

        if((result = Functions.vlslastname_f(getB4rpln())) != 0)
           	Utils.message(result + Constants.E_VAB4RPLN, getIdnr(), 0, "HSN_CIVREC_STD", "B4", getB4rpln());
          
         if((result = Functions.empty_f(getB4rpln())) != 0)
         	Utils.message(result + Constants.E_LEB4RPLN, getIdnr(), 0, "HSN_CIVREC_STD", "B4");

         if((result = Functions.lastname_valid_f(getB4rpln())) != 0)
         	Utils.message(result + Constants.E_ONB4RPLN, getIdnr(), 0, "HSN_CIVREC_STD", "B4", getB4rpln());

         
        a = Functions.splitField(getB4rpln());            
        setB4rpln(a[0]); 
        setB4rppf(a[1]); 
        setB4rptt(a[2]); 
         
         
         // Check information
         
         if((result = Functions.certiftype_f(getB4sdct())) != 0)
          	Utils.message(result + Constants.E_DAB1SDCY, getIdnr(), 0, "HSN_CIVREC_STD", "B4", "" + getB4sdct());
          
         if((result = Functions.date_f(getB4sdcd(), getB4sdcm(), getB4sdcy())) != 0)
           	Utils.message(result + Constants.E_DAB4SDCY, getIdnr(), 0, "HSN_CIVREC_STD", "B4", getB4sdcd() + "-" + getB4sdcm() + "-" + getB4sdcy());

         if((result = Functions.date_f(getB4sdmd(), getB4sdmm(), getB4sdmy())) != 0)
           	Utils.message(result + Constants.E_DAB4SDMY, getIdnr(), 0, "HSN_CIVREC_STD", "B4", getB4sdmd() + "-" + getB4sdmm() + "-" + getB4sdmy());
         
         if((result = Functions.vlslocation_f(getB4sdml())) != 0)
           	Utils.message(result + Constants.E_VLB4SDML, getIdnr(), 0, "HSN_CIVREC_STD", "B4", getB4faln());
          
         if((result = Functions.empty_f(getB4fafn())) != 0)
           	Utils.message(result + Constants.E_LEB4FAFN, getIdnr(), 0, "HSN_CIVREC_STD", "B4");
          
         if((result = Functions.date_f(getB4sdrd(), getB4sdrm(), getB4sdry())) != 0)
            	Utils.message(result + Constants.E_DAB4SDRY, getIdnr(), 0, "HSN_CIVREC_STD", "B4", getB4sdrd() + "-" + getB4sdrm() + "-" + getB4sdry());

         if((result = Functions.empty_f(getB4rpfn())) != 0)
          	Utils.message(result + Constants.E_LEB4RPFN, getIdnr(), 0, "HSN_CIVREC_STD", "B4");
         
         if((result = Functions.gender_f(getB4rpgn())) != 0)
          	Utils.message(result + Constants.E_GNB4RPGN, getIdnr(), 0, "HSN_CIVREC_STD", "B4", getB4rpgn());
         
         // Reference checks
         
         setB4fafn(Functions.firstname_r(getB4fafn(), Constants.E_FNB4FAFN, getIdnr(), 0, "HSN_CIVREC_STD", "B4"));
         setB4rpfn(Functions.firstname_r(getB4rpfn(), Constants.E_FNB4RPFN, getIdnr(), 0, "HSN_CIVREC_STD", "B4"));
         setB4fapf(Functions.prefix_r(getB4fapf(), Constants.E_LNB4FAPF, getIdnr(), 0, "HSN_CIVREC_STD", "B4"));
         setB4rppf(Functions.prefix_r(getB4rppf(), Constants.E_LNB4RPPF, getIdnr(), 0, "HSN_CIVREC_STD", "B4"));
         setB4faln(Functions.familyname_r(getB4faln(), Constants.E_LNB4FALN, getIdnr(), 0, "HSN_CIVREC_STD", "B4"));
         setB4rpln(Functions.familyname_r(getB4rpln(), Constants.E_LNB4RPLN, getIdnr(), 0, "HSN_CIVREC_STD", "B4"));
         setB4sdml(Functions.location_r(getB4sdml(), Constants.E_LOB4SDML, getIdnr(), 0, "HSN_CIVREC_STD", "B4"));

    	 
     }
     
     
    public void truncate(){
     	
     	String field = getB4sdml();
     	int allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B4SDML",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setB4sdml(field);
     	}
     	
     	field = getB4faln();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B4FALN",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setB4faln(field);
     	}
     	
     	field = getB4fapf();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B4FAPF",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setB4fapf(field);
     	}
     	
     	field = getB4fafn();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B4FAFN",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setB4fafn(field);
     	}
     	
     	field = getB4fatt();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B4FATT",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setB4fatt(field);
     	}
     	
     	field = getB4fapa();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B4FAPA",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setB4fapa(field);
     	}
     	
     	field = getB4rpln();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B4RPLN",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setB4rpln(field);
     	}
     	
     	field = getB4rpfn();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B4RPFN",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setB4rpfn(field);
     	}
     	
     	field = getB4rppf();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B4RPPF",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setB4rppf(field);
     	}
     	
     	field = getB4rptt();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B4RPTT",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setB4rptt(field);
     	}
     	
     	field = getB4rppa();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B4RPPA",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setB4rppa(field);
     	}
     	
  	}
 
     
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public int getB4sdct() {
		return b4sdct;
	}
	public void setB4sdct(int b4sdct) {
		this.b4sdct = b4sdct;
	}
	public int getB4sdcd() {
		return b4sdcd;
	}
	public void setB4sdcd(int b4sdcd) {
		this.b4sdcd = b4sdcd;
	}
	public int getB4sdcm() {
		return b4sdcm;
	}
	public void setB4sdcm(int b4sdcm) {
		this.b4sdcm = b4sdcm;
	}
	public int getB4sdcy() {
		return b4sdcy;
	}
	public void setB4sdcy(int b4sdcy) {
		this.b4sdcy = b4sdcy;
	}
	public int getB4sdmd() {
		return b4sdmd;
	}
	public void setB4sdmd(int b4sdmd) {
		this.b4sdmd = b4sdmd;
	}
	public int getB4sdmm() {
		return b4sdmm;
	}
	public void setB4sdmm(int b4sdmm) {
		this.b4sdmm = b4sdmm;
	}
	public int getB4sdmy() {
		return b4sdmy;
	}
	public void setB4sdmy(int b4sdmy) {
		this.b4sdmy = b4sdmy;
	}
	public String getB4sdml() {
		return b4sdml;
	}
	public void setB4sdml(String b4sdml) {
		this.b4sdml = b4sdml;
	}
	public String getB4faln() {
		return b4faln;
	}
	public void setB4faln(String b4faln) {
		this.b4faln = b4faln;
	}
	public String getB4fapf() {
		return b4fapf;
	}
	public void setB4fapf(String b4fapf) {
		this.b4fapf = b4fapf;
	}
	public String getB4fafn() {
		return b4fafn;
	}
	public void setB4fafn(String b4fafn) {
		this.b4fafn = b4fafn;
	}
	public String getB4fatt() {
		return b4fatt;
	}
	public void setB4fatt(String b4fatt) {
		this.b4fatt = b4fatt;
	}
	public String getB4fapa() {
		return b4fapa;
	}
	public void setB4fapa(String b4fapa) {
		this.b4fapa = b4fapa;
	}
	public int getB4sdrd() {
		return b4sdrd;
	}
	public void setB4sdrd(int b4sdrd) {
		this.b4sdrd = b4sdrd;
	}
	public int getB4sdrm() {
		return b4sdrm;
	}
	public void setB4sdrm(int b4sdrm) {
		this.b4sdrm = b4sdrm;
	}
	public int getB4sdry() {
		return b4sdry;
	}
	public void setB4sdry(int b4sdry) {
		this.b4sdry = b4sdry;
	}
	public int getB4sdrs() {
		return b4sdrs;
	}
	public void setB4sdrs(int b4sdrs) {
		this.b4sdrs = b4sdrs;
	}
	public int getB4sdrn() {
		return b4sdrn;
	}
	public void setB4sdrn(int b4sdrn) {
		this.b4sdrn = b4sdrn;
	}
	public String getB4rpln() {
		return b4rpln;
	}
	public void setB4rpln(String b4rpln) {
		this.b4rpln = b4rpln;
	}
	public String getB4rpfn() {
		return b4rpfn;
	}
	public void setB4rpfn(String b4rpfn) {
		this.b4rpfn = b4rpfn;
	}
	public String getB4rppf() {
		return b4rppf;
	}
	public void setB4rppf(String b4rppf) {
		this.b4rppf = b4rppf;
	}
	public String getB4rptt() {
		return b4rptt;
	}
	public void setB4rptt(String b4rptt) {
		this.b4rptt = b4rptt;
	}
	public String getB4rppa() {
		return b4rppa;
	}
	public void setB4rppa(String b4rppa) {
		this.b4rppa = b4rppa;
	}
	public String getB4rpgn() {
		return b4rpgn;
	}
	public void setB4rpgn(String b4rpgn) {
		this.b4rpgn = b4rpgn;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}
     
     
     
     
}
