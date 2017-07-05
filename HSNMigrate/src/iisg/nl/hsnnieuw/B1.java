package iisg.nl.hsnnieuw;
import iisg.nl.hsnimport.Gebknd;
import iisg.nl.hsnimport.Stpb;
import iisg.nl.hsnmigrate.Const;
import iisg.nl.hsnmigrate.Functions;
import iisg.nl.hsnmigrate.Utils;
import iisg.nl.hsnmigrate.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import nl.iisg.ref.Ref;
import nl.iisg.ref.Ref_Municipality;

@Entity
@Table(name="b1")
public class B1 {

     @Column(name="B1SDCC")       private int b1sdcc;
     @Column(name="B1SDCY")       private int b1sdcy;
     @Column(name="B1SDCN")       private int b1sdcn;
     @Column(name="IDNR")         private int idnr;
     @Column(name="B1SDLI")       private String b1sdli;
     @Column(name="B1SDCH")       private int b1sdch;
     @Column(name="B1SDCD")       private int b1sdcd;
     @Column(name="B1SDCM")       private int b1sdcm;
     @Column(name="B1INFA")       private String b1infa;
     @Column(name="B1INLN")       private String b1inln;
     @Column(name="B1INPF")       private String b1inpf;
     @Column(name="B1INFN")       private String b1infn;
     @Column(name="B1INTT")       private String b1intt;
     @Column(name="B1INPA")       private String b1inpa;
     @Column(name="B1INAY")       private int b1inay;
     @Column(name="B1INOC")       private String b1inoc;
     @Column(name="B1INLL")       private String b1inll;
     @Column(name="B1INSG")       private String b1insg;
     @Column(name="B1RPBD")       private int b1rpbd;
     @Column(name="B1RPBM")       private int b1rpbm;
     @Column(name="B1RPBY")       private int b1rpby;
     @Column(name="B1RPBH")       private int b1rpbh;
     @Column(name="B1RPBI")       private int b1rpbi;
     @Column(name="B1RPGN")       private String b1rpgn;
     @Column(name="B1RPLL")       private String b1rpll;
     @Column(name="B1MOLN")       private String b1moln;
     @Column(name="B1MOPF")       private String b1mopf;
     @Column(name="B1MOFN")       private String b1mofn;
     @Column(name="B1MOTT")       private String b1mott;
     @Column(name="B1MOPA")       private String b1mopa;
     @Column(name="B1MOAY")       private int b1moay;
     @Column(name="B1MOCS")       private String b1mocs;
     @Column(name="B1MOOC")       private String b1mooc;
     @Column(name="B1MOLL")       private String b1moll;
     @Column(name="B1RPLN")       private String b1rpln;
     @Column(name="B1RPPF")       private String b1rppf;
     @Column(name="B1RPFN")       private String b1rpfn;
     @Column(name="B1RPTT")       private String b1rptt;
     @Column(name="B1RPPA")       private String b1rppa;
     @Column(name="D_E_P_L")      private String d_e_p_l;
     @Column(name="D_E_P_O")      private String d_e_p_o;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")     private int recordID;
     
     @Transient                   private A1  b1rplla;  
     @Transient                   private A1  b1molla;
     @Transient                   private A1  b1inlla;

     
     
     public void transform(Gebknd gebknd){	
    		
    	 
    	    // copy and/or combine
    	 
    	 
    	    setIdnr(gebknd.getIdnr());
    		setB1sdcc(gebknd.getGemnr());
            setB1sdcy(gebknd.getJaar());    		
            setB1sdcn(gebknd.getAktenr());    		
            setIdnr(gebknd.getIdnr());    		
            setB1sdli(gebknd.getInvbeper());    		
            setB1sdch(gebknd.getAkteuur());    		
            setB1sdcd(gebknd.getAktedag());    		
            setB1sdcm(gebknd.getAktemnd());    		
            setB1inln(gebknd.getAnmag());    		
            setB1inpf(gebknd.getTusag());    		
            setB1infn(Utils.combine3FirstNames(gebknd.getVrn1ag(), gebknd.getVrn2ag(), gebknd.getVrn3ag()));    		
            setB1inay(gebknd.getLftag());    		
            setB1inoc(gebknd.getBrpag());    		
            setB1inll(gebknd.getAdrag());    		
            setB1insg(gebknd.getHndag());    		
            setB1rpbd(gebknd.getGebdag());    		
            setB1rpbm(gebknd.getGebmnd());    		
            setB1rpby(gebknd.getGebjr());    		
            setB1rpbh(gebknd.getGebuur());    		
            setB1rpbi(gebknd.getGebmin());    		
            setB1rpgn(gebknd.getGebsex());    		
            setB1rpll(gebknd.getGebadr());    		
            setB1moln(gebknd.getAnmmr());    		
            setB1mopf(gebknd.getTusmr());    		
            setB1mofn(Utils.combine3FirstNames(gebknd.getVrn1mr(), gebknd.getVrn2mr(), gebknd.getVrn3mr()));    		
            setB1moay(gebknd.getLftmr());    		
            setB1mocs(gebknd.getBrgstmr());    		
            setB1mooc(gebknd.getBrpmr());    		
            setB1rpln(gebknd.getAnmgeb());    		
            setB1rppf(gebknd.getTusgeb());    		
            setB1rpfn(Utils.combine3FirstNames(gebknd.getVrn1geb(), gebknd.getVrn2geb(), gebknd.getVrn3geb()));    		
            setD_e_p_l(gebknd.getVersie());    		
            setD_e_p_o(gebknd.getVersieo());    
            setB1moll(gebknd.getAdrmr());
            
            if(gebknd.getGebvdr() == null)
            	setB1infa("J");
            else
            	setB1infa("N");
    		
            
            //  get part before "%" and split off prefix and title 

            int result = 0;
            
            if((result = Functions.vlslastname_f(getB1inln())) != 0)
            	Utils.message(result + Constants.E_VAB1INLN, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getB1inln());

            if((result = Functions.lastname_valid_f(getB1inln())) != 0)
            	Utils.message(result + Constants.E_ONB1INLN, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getB1inln());

            String [] a = Functions.splitField(getB1inln());
            setB1inln(a[0]); 
            setB1inpf(a[1]);
            setB1intt(a[2]);

            
            if((result = Functions.vlslastname_f(getB1moln())) != 0)
            	Utils.message(result + Constants.E_VAB1MOLN, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getB1moln());

            if((result = Functions.lastname_valid_f(getB1moln())) != 0)
            	Utils.message(result + Constants.E_ONB1MOLN, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getB1moln());

            a = Functions.splitField(getB1moln());

            setB1moln(a[0]); 
            setB1mopf(a[1]);
            setB1mott(a[2]);
            
            if((result = Functions.vlslastname_f(getB1rpln())) != 0)
            	Utils.message(result + Constants.E_VAB1RPLN, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getB1rpln());

            if((result = Functions.lastname_valid_f(getB1moln())) != 0)
            	Utils.message(result + Constants.E_ONB1RPLN, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getB1moln());


            a = Functions.splitField(getB1rpln());            
            setB1rpln(a[0]); 
            setB1rppf(a[1]);
            setB1rptt(a[2]);
            
            
            // Check information
            
            
            if((result = Functions.codeconversion_f(getB1sdcc())) != 0)
            	Utils.message(result + Constants.E_CCB1SDCC, getIdnr(), 0, "HSN_CIVREC_STD", "B1", "" + getB1sdcc());
                                                 
            if((result = Functions.date_f(getB1sdcd(), getB1sdcm(), getB1sdcy())) != 0)
            	Utils.message(result + Constants.E_DAB1SDCY, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getB1sdcd() + "-" + getB1sdcm() + "-" + getB1sdcy());
            
            if((result = Functions.hour_f(getB1sdch())) != 0)
            	Utils.message(result + Constants.E_HRB1SDCH, getIdnr(), 0, "HSN_CIVREC_STD", "B1", (new Integer(getB1sdch())).toString());
            else
            	if(getB1sdch() >= 0 && getB1sdch() < 8)
                	Utils.message(100900 + Constants.E_HRB1SDC2, getIdnr(), 0, "HSN_CIVREC_STD", "B1", (new Integer(getB1sdch())).toString());
            	
            if((result = Functions.empty_f(getB1inln())) != 0)
            	Utils.message(result + Constants.E_LEB1INLN, getIdnr(), 0, "HSN_CIVREC_STD", "B1");
            
            if((result = Functions.empty_f(getB1infn())) != 0)
            	Utils.message(result + Constants.E_LEB1INFN, getIdnr(), 0, "HSN_CIVREC_STD", "B1");
            
            if((result = Functions.age_f(getB1inay())) != 0)
            	Utils.message(result + Constants.E_AGB1INAY, getIdnr(), 0, "HSN_CIVREC_STD", "B1", (new Integer(getB1inay())).toString());
            
            if((result = Functions.empty_f(getB1inoc())) != 0)
            	Utils.message(result + Constants.E_LEB1INOC, getIdnr(), 0, "HSN_CIVREC_STD", "B1");
            
            if((result = Functions.idem_f(getB1inll())) != 0)
            	Utils.message(result + Constants.E_IMB1INLL, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getB1inll());
            
            if((result = Functions.threedouble_f(getB1inll())) != 0)
            	Utils.message(result + Constants.E_X3B1INLL, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getB1inll());
            
            if((result = Functions.vlslocation_f(getB1inll())) != 0)
            	Utils.message(result + Constants.E_VLB1INLL, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getB1inll());
            
            if((result = Functions.signature_f(getB1insg())) != 0)
            	Utils.message(result + Constants.E_SGB1INSG, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getB1insg());
            
            if((result = Functions.date_f(getB1rpbd(), getB1rpbm(), getB1rpby())) != 0)
            	Utils.message(result + Constants.E_DAB1RPBY, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getB1rpbd() + "-" + getB1rpbm() + "-" + getB1rpby());

            if((result = Functions.hour_f(getB1rpbh())) != 0)
            	Utils.message(result + Constants.E_HRB1RPBH, getIdnr(), 0, "HSN_CIVREC_STD", "B1", "" + getB1rpbh());
            
            
            if((result = Functions.minute_f(getB1rpbi())) != 0)
            	Utils.message(result + Constants.E_MIB1RPBI, getIdnr(), 0, "HSN_CIVREC_STD", "B1", "" + getB1rpbi());
            
            if((result = Functions.gender_f(getB1rpgn())) != 0)
            	Utils.message(result + Constants.E_GNB1RPGN, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getB1rpgn());
            
            if((result = Functions.idem_f(getB1rpll())) != 0)
            	Utils.message(result + Constants.E_IMB1RPLL, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getB1rpll());
            
            if((result = Functions.threedouble_f(getB1rpll())) != 0)
            	Utils.message(result + Constants.E_X3B1RPLL, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getB1rpll());

            if((result = Functions.vlslocation_f(getB1rpll())) != 0)
            	Utils.message(result + Constants.E_VLB1RPLL, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getB1rpll());
            
            if((result = Functions.age_f(getB1moay())) != 0)
            	Utils.message(result + Constants.E_AGB1MOAY, getIdnr(), 0, "HSN_CIVREC_STD", "B1", "" + getB1moay());
            
            if((result = Functions.civilstatusB_f(getB1mocs())) != 0)
            	Utils.message(result + Constants.E_CSB1MOCS, getIdnr(), 0, "HSN_CIVREC_STD", "B1", "" + getB1mocs());
            
            if((result = Functions.empty_f(getB1mooc())) != 0)
            	Utils.message(result + Constants.E_LEB1MOOC, getIdnr(), 0, "HSN_CIVREC_STD", "B1");
            
            if((result = Functions.empty_f(getB1mofn())) != 0)
            	Utils.message(result + Constants.E_LEB1MOFN, getIdnr(), 0, "HSN_CIVREC_STD", "B1");
            
            if((result = Functions.empty_f(getB1moln())) != 0)
            	Utils.message(result + Constants.E_LEB1MOLN, getIdnr(), 0, "HSN_CIVREC_STD", "B1");
            
            if((result = Functions.vlslocation_f(getB1moll())) != 0)
            	Utils.message(result + Constants.E_VLB1MOLL, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getB1moll());

            if((result = Functions.idem_f(getB1moll())) != 0)
            	Utils.message(result + Constants.E_IMB1MOLL, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getB1moll());

            if((result = Functions.threedouble_f(getB1moll())) != 0)
            	Utils.message(result + Constants.E_X3B1MOLL, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getB1moll());            

            if((result = Functions.empty_f(getB1rpln())) != 0)
            	Utils.message(result + Constants.E_LEB1RPLN, getIdnr(), 0, "HSN_CIVREC_STD", "B1");

            if((result = Functions.empty_f(getB1rpfn())) != 0)
            	Utils.message(result + Constants.E_LEB1RPFN, getIdnr(), 0, "HSN_CIVREC_STD", "B1");
            

            // Reference checks
            
            setB1infn(Functions.firstname_r(getB1infn(), Constants.E_FNB1INFN, getIdnr(), 0, "HSN_CIVREC_STD", "B1"));
            setB1mofn(Functions.firstname_r(getB1mofn(), Constants.E_FNB1MOFN, getIdnr(), 0, "HSN_CIVREC_STD", "B1"));
            setB1rpfn(Functions.firstname_r(getB1rpfn(), Constants.E_FNB1RPFN, getIdnr(), 0, "HSN_CIVREC_STD", "B1"));
            
            setB1inpf(Functions.prefix_r(getB1inpf(), Constants.E_LNB1INPF, getIdnr(), 0, "HSN_CIVREC_STD", "B1"));
            setB1mopf(Functions.prefix_r(getB1mopf(), Constants.E_LNB1MOPF, getIdnr(), 0, "HSN_CIVREC_STD", "B1"));
            setB1rppf(Functions.prefix_r(getB1rppf(), Constants.E_LNB1RPPF, getIdnr(), 0, "HSN_CIVREC_STD", "B1"));
            
            setB1inln(Functions.familyname_r(getB1inln(), Constants.E_LNB1INLN, getIdnr(), 0, "HSN_CIVREC_STD", "B1"));
            setB1moln(Functions.familyname_r(getB1moln(), Constants.E_LNB1MOLN, getIdnr(), 0, "HSN_CIVREC_STD", "B1"));
            setB1rpln(Functions.familyname_r(getB1rpln(), Constants.E_LNB1RPLN, getIdnr(), 0, "HSN_CIVREC_STD", "B1"));
            
            setB1inoc(Functions.profession_r(getB1inoc(), Constants.E_OCB1INOC, getIdnr(), 0, "HSN_CIVREC_STD", "B1"));
            setB1mooc(Functions.profession_r(getB1mooc(), Constants.E_OCB1MOOC, getIdnr(), 0, "HSN_CIVREC_STD", "B1"));
            
            

            //setB1inll(Functions.location_r(getB1inll(), Constants.E_LOB1INLL, getIdnr(), 0, "HSN_CIVREC_STD", "B1"));
            //setB1moll(Functions.location_r(getB1moll(), Constants.E_LOB1MOLL, getIdnr(), 0, "HSN_CIVREC_STD", "B1"));
            //setB1rpll(Functions.location_r(getB1rpll(), Constants.E_LOB1RPLL, getIdnr(), 0, "HSN_CIVREC_STD", "B1"));
            
            setB1rplla(Functions.location_r2(getB1rpll(), Constants.E_LOB1RPLL, getIdnr(), 0, "HSN_CIVREC_STD", "B1"));
            if(getB1rplla() != null){
            	getB1rplla().setRole(1);
            	if(getB1rplla().getMunicipalityNumber() == null){

            		Ref_Municipality r = Ref.getMunicipality(getB1sdcc());
            		if(r!= null && r.getMunicipalityName() != null && r.getMunicipalityName().trim().length() > 0){ 
            			getB1rplla().setMunicipality(r.getMunicipalityName().trim());
            			getB1rplla().setMunicipalityNumber(getB1sdcc() + "");
            			
            		}

            	}
            }
            
            setB1molla(Functions.location_r2(getB1moll(), Constants.E_LOB1MOLL, getIdnr(), 0, "HSN_CIVREC_STD", "B1"));
            if(getB1molla() != null){
            	getB1molla().setRole(3);
            	if(getB1molla().getMunicipalityNumber() == null){

            		Ref_Municipality r = Ref.getMunicipality(getB1sdcc());
            		if(r!= null && r.getMunicipalityName() != null && r.getMunicipalityName().trim().length() > 0){ 
            			getB1molla().setMunicipality(r.getMunicipalityName().trim());
            			getB1molla().setMunicipalityNumber(getB1sdcc() + "");
            			
            		}

            	}
            }
            
            setB1inlla(Functions.location_r2(getB1inll(), Constants.E_LOB1INLL, getIdnr(), 0, "HSN_CIVREC_STD", "B1"));
            if(getB1inlla() != null){
            	getB1inlla().setRole(4);
            	if(getB1inlla().getMunicipalityNumber() == null){

            		Ref_Municipality r = Ref.getMunicipality(getB1sdcc());
            		if(r!= null && r.getMunicipalityName() != null && r.getMunicipalityName().trim().length() > 0){ 
            			getB1inlla().setMunicipality(r.getMunicipalityName().trim());
            			getB1inlla().setMunicipalityNumber(getB1sdcc() + "");
            			
            		}

            	}
            }
            

            // logic checks
            
            if(Utils.dayCount(getB1sdcd(), getB1sdcm(), getB1sdcy()) - Utils.dayCount(getB1rpbd(), getB1rpbm(), getB1rpby()) > 5)
            	Utils.message(300500, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getB1rpbd() + "-" + getB1rpbm() + "-" + getB1rpby() + " and " + getB1sdcd() + "-" + getB1sdcm() + "-" + getB1sdcy());
            
            if(getB1infa().equalsIgnoreCase("J")){
            	//System.out.println("informer = " + getB1inln() + " RP = " + getB1rpln());
            	if(getB1inln() != null && getB1rpln() != null && !getB1inln().equalsIgnoreCase(getB1rpln())){
                    	Utils.message(300600 + Constants.E_IFB1RPLN, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getB1inln() + " and " + getB1rpln());
            	}
            }
    	}

     
    public void truncate(){
    	
     	String field = getB1inln();
    	int allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B1INLN",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB1inln(field);
    	}
    	
     	field = getB1inpf();
    	allowedSize = Const.Smallstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",   "B1INPF",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB1inpf(field);
    	}
    	
     	field = getB1infn();
    	allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",   "B1INFN",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB1infn(field);
    	}
    	
     	field = getB1intt();
    	allowedSize = Const.Smallstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B1INTT",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB1intt(field);
    	}
    	
     	field = getB1inpa();
    	allowedSize = Const.Smallstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B1INPA",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB1inpa(field);
    	}
    	
     	field = getB1inoc();
    	allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B1INOC",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB1inoc(field);
    	}
    	
     	field = getB1inll();
    	allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B1INLL",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB1inll(field);
    	}
    	
     	field = getB1rpll();
    	allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B1RPLL",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB1rpll(field);
    	}
    	
     	field = getB1moln();
    	allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B1MOLN",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB1moln(field);
    	}
    	
     	field = getB1mopf();
    	allowedSize = Const.Smallstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B1MOPF",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB1mopf(field);
    	}
    	
     	field = getB1mofn();
    	allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B1MOFN",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB1mofn(field);
    	}
    	
     	field = getB1mott();
    	allowedSize = Const.Smallstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B1MOTT",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB1mott(field);
    	}
    	
     	field = getB1mopa();
    	allowedSize = Const.Smallstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B1MOPA",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB1mopa(field);
    	}
    	
     	field = getB1mooc();
    	allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B1MOOC",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB1mooc(field);
    	}
    	
     	field = getB1moll();
    	allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B1MOLL",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB1moll(field);
    	}
    	
     	field = getB1rpln();
    	allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B1RPLN",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB1rpln(field);
    	}
    	
     	field = getB1rppf();
    	allowedSize = Const.Smallstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B1RPPF",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB1rppf(field);
    	}
    	
     	field = getB1rpfn();
    	allowedSize = Const.Bigstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B1RPFN",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB1rpfn(field);
    	}

     	field = getB1rptt();
    	allowedSize = Const.Smallstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B1RPTT",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB1rptt(field);
    	}
    	
     	field = getB1rppa();
    	allowedSize = Const.Smallstring;
    	if(field != null && field.length() > allowedSize){
    		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "B1RPPA",  (new Integer(field.length()).toString()));
    		field = field.substring(0, allowedSize);
    		setB1rppa(field);
    	}
    	


	
	
	
	
	
    }
     
	public int getB1sdcc() {
		return b1sdcc;
	}
	public void setB1sdcc(int b1sdcc) {
		this.b1sdcc = b1sdcc;
	}
	public int getB1sdcy() {
		return b1sdcy;
	}
	public void setB1sdcy(int b1sdcy) {
		this.b1sdcy = b1sdcy;
	}
	public int getB1sdcn() {
		return b1sdcn;
	}
	public void setB1sdcn(int b1sdcn) {
		this.b1sdcn = b1sdcn;
	}
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public String getB1sdli() {
		return b1sdli;
	}
	public void setB1sdli(String b1sdli) {
		this.b1sdli = b1sdli;
	}
	public int getB1sdch() {
		return b1sdch;
	}
	public void setB1sdch(int b1sdch) {
		this.b1sdch = b1sdch;
	}
	public int getB1sdcd() {
		return b1sdcd;
	}
	public void setB1sdcd(int b1sdcd) {
		this.b1sdcd = b1sdcd;
	}
	public int getB1sdcm() {
		return b1sdcm;
	}
	public void setB1sdcm(int b1sdcm) {
		this.b1sdcm = b1sdcm;
	}
	public String getB1infa() {
		return b1infa;
	}
	public void setB1infa(String b1infa) {
		this.b1infa = b1infa;
	}
	public String getB1inln() {
		return b1inln;
	}
	public void setB1inln(String b1inln) {
		this.b1inln = b1inln;
	}
	public String getB1inpf() {
		return b1inpf;
	}
	public void setB1inpf(String b1inpf) {
		this.b1inpf = b1inpf;
	}
	public String getB1infn() {
		return b1infn;
	}
	public void setB1infn(String b1infn) {
		this.b1infn = b1infn;
	}
	public String getB1intt() {
		return b1intt;
	}
	public void setB1intt(String b1intt) {
		this.b1intt = b1intt;
	}
	public String getB1inpa() {
		return b1inpa;
	}
	public void setB1inpa(String b1inpa) {
		this.b1inpa = b1inpa;
	}
	public int getB1inay() {
		return b1inay;
	}
	public void setB1inay(int b1inay) {
		this.b1inay = b1inay;
	}
	public String getB1inoc() {
		return b1inoc;
	}
	public void setB1inoc(String b1inoc) {
		this.b1inoc = b1inoc;
	}
	public String getB1inll() {
		return b1inll;
	}
	public void setB1inll(String b1inll) {
		this.b1inll = b1inll;
	}
	public String getB1insg() {
		return b1insg;
	}
	public void setB1insg(String b1insg) {
		this.b1insg = b1insg;
	}
	public int getB1rpbd() {
		return b1rpbd;
	}
	public void setB1rpbd(int b1rpbd) {
		this.b1rpbd = b1rpbd;
	}
	public int getB1rpbm() {
		return b1rpbm;
	}
	public void setB1rpbm(int b1rpbm) {
		this.b1rpbm = b1rpbm;
	}
	public int getB1rpby() {
		return b1rpby;
	}
	public void setB1rpby(int b1rpby) {
		this.b1rpby = b1rpby;
	}
	public int getB1rpbh() {
		return b1rpbh;
	}
	public void setB1rpbh(int b1rpbh) {
		this.b1rpbh = b1rpbh;
	}
	public int getB1rpbi() {
		return b1rpbi;
	}
	public void setB1rpbi(int b1rpbi) {
		this.b1rpbi = b1rpbi;
	}
	public String getB1rpgn() {
		return b1rpgn;
	}
	public void setB1rpgn(String b1rpgn) {
		this.b1rpgn = b1rpgn;
	}
	public String getB1rpll() {
		return b1rpll;
	}
	public void setB1rpll(String b1rpll) {
		this.b1rpll = b1rpll;
	}
	public String getB1moln() {
		return b1moln;
	}
	public void setB1moln(String b1moln) {
		this.b1moln = b1moln;
	}
	public String getB1mopf() {
		return b1mopf;
	}
	public void setB1mopf(String b1mopf) {
		this.b1mopf = b1mopf;
	}
	public String getB1mofn() {
		return b1mofn;
	}
	public void setB1mofn(String b1mofn) {
		this.b1mofn = b1mofn;
	}
	public String getB1mott() {
		return b1mott;
	}
	public void setB1mott(String b1mott) {
		this.b1mott = b1mott;
	}
	public String getB1mopa() {
		return b1mopa;
	}
	public void setB1mopa(String b1mopa) {
		this.b1mopa = b1mopa;
	}
	public int getB1moay() {
		return b1moay;
	}
	public void setB1moay(int b1moay) {
		this.b1moay = b1moay;
	}
	public String getB1mocs() {
		return b1mocs;
	}
	public void setB1mocs(String b1mocs) {
		this.b1mocs = b1mocs;
	}
	public String getB1mooc() {
		return b1mooc;
	}
	public void setB1mooc(String b1mooc) {
		this.b1mooc = b1mooc;
	}
	public String getB1moll() {
		return b1moll;
	}
	public void setB1moll(String b1moll) {
		this.b1moll = b1moll;
	}
	public String getB1rpln() {
		return b1rpln;
	}
	public void setB1rpln(String b1rpln) {
		this.b1rpln = b1rpln;
	}
	public String getB1rppf() {
		return b1rppf;
	}
	public void setB1rppf(String b1rppf) {
		this.b1rppf = b1rppf;
	}
	public String getB1rpfn() {
		return b1rpfn;
	}
	public void setB1rpfn(String b1rpfn) {
		this.b1rpfn = b1rpfn;
	}
	public String getB1rptt() {
		return b1rptt;
	}
	public void setB1rptt(String b1rptt) {
		this.b1rptt = b1rptt;
	}
	public String getB1rppa() {
		return b1rppa;
	}
	public void setB1rppa(String b1rppa) {
		this.b1rppa = b1rppa;
	}
	public String getD_e_p_l() {
		return d_e_p_l;
	}
	public void setD_e_p_l(String d_e_p_l) {
		this.d_e_p_l = d_e_p_l;
	}
	public String getD_e_p_o() {
		return d_e_p_o;
	}
	public void setD_e_p_o(String d_e_p_o) {
		this.d_e_p_o = d_e_p_o;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}


	public A1 getB1rplla() {
		return b1rplla;
	}


	public void setB1rplla(A1 b1rplla) {
		this.b1rplla = b1rplla;
	}


	public A1 getB1molla() {
		return b1molla;
	}


	public void setB1molla(A1 b1molla) {
		this.b1molla = b1molla;
	}


	public A1 getB1inlla() {
		return b1inlla;
	}


	public void setB1inlla(A1 b1inlla) {
		this.b1inlla = b1inlla;
	}
     
     
     
     
     
     
     
}



