package iisg.nl.hsnnieuw;

import iisg.nl.hsnimport.Huwafk;
import iisg.nl.hsnimport.Huwknd;
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

import nl.iisg.ref.Ref;
import nl.iisg.ref.Ref_Municipality;

@Entity
@Table(name="d1")
public class D1 {

     @Column(name="IDNR")         private int idnr;
     @Column(name="D1SDCC")       private int d1sdcc;
     @Column(name="D1SDCL")       private String d1sdcl;
     @Column(name="D1SDCN")       private int d1sdcn;
     @Column(name="D1SDCH")       private int d1sdch;
     @Column(name="D1SDCI")       private int d1sdci;
     @Column(name="D1SDCD")       private int d1sdcd;
     @Column(name="D1SDCM")       private int d1sdcm;
     @Column(name="D1SDCY")       private int d1sdcy;
     @Column(name="D1INFA")       private String d1infa;
     @Column(name="D1INSQ")       private int d1insq;
     @Column(name="D1INSG")       private String d1insg;
     @Column(name="D1RPLN")       private String d1rpln;
     @Column(name="D1RPPF")       private String d1rppf;
     @Column(name="D1RPFN")       private String d1rpfn;
     @Column(name="D1RPTT")       private String d1rptt;
     @Column(name="D1RPPA")       private String d1rppa;
     @Column(name="D1RPOC")       private String d1rpoc;
     @Column(name="D1RPBC")       private int d1rpbc;
     @Column(name="D1RPBL")       private String d1rpbl;
     @Column(name="D1RPLL")       private String d1rpll;
     @Column(name="D1RPCS")       private String d1rpcs;
     @Column(name="D1RPGN")       private String d1rpgn;
     @Column(name="D1RPDD")       private int d1rpdd;
     @Column(name="D1RPDM")       private int d1rpdm;
     @Column(name="D1RPDY")       private int d1rpdy;
     @Column(name="D1RPDH")       private int d1rpdh;
     @Column(name="D1RPDI")       private int d1rpdi;
     @Column(name="D1RPDL")       private String d1rpdl;
     @Column(name="D1RPAD")       private int d1rpad;
     @Column(name="D1RPAW")       private int d1rpaw;
     @Column(name="D1RPAM")       private int d1rpam;
     @Column(name="D1RPAY")       private int d1rpay;
     @Column(name="D1FALN")       private String d1faln;
     @Column(name="D1FAPF")       private String d1fapf;
     @Column(name="D1FAFN")       private String d1fafn;
     @Column(name="D1FATT")       private String d1fatt;
     @Column(name="D1FAPA")       private String d1fapa;
     @Column(name="D1FACA")       private String d1faca;
     @Column(name="D1FAOC")       private String d1faoc;
     @Column(name="D1FAAY")       private int d1faay;
     @Column(name="D1FALL")       private String d1fall;
     @Column(name="D1MOLN")       private String d1moln;
     @Column(name="D1MOPF")       private String d1mopf;
     @Column(name="D1MOFN")       private String d1mofn;
     @Column(name="D1MOTT")       private String d1mott;
     @Column(name="D1MOPA")       private String d1mopa;
     @Column(name="D1MOCA")       private String d1moca;
     @Column(name="D1MOOC")       private String d1mooc;
     @Column(name="D1MOAY")       private int d1moay;
     @Column(name="D1MOLL")       private String d1moll;
     @Column(name="D1SDCE")       private String d1sdce;
     @Column(name="D_E_P_L")      private String d_e_p_l;
     @Column(name="D_E_P_O")      private String d_e_p_o;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")     private int recordID;
     
     
     public void transform(Ovlknd ovlknd){
     
     setIdnr(ovlknd.getIdnr());
     setD1sdcc(ovlknd.getOacgemnr());
     setD1sdcl(ovlknd.getOacgemnm());
     setD1sdcn(ovlknd.getOaktenr());
     setD1sdch(ovlknd.getOakteuur());
     setD1sdci(ovlknd.getOaktemin());
     setD1sdcd(ovlknd.getOaktedag());
     setD1sdcm(ovlknd.getOaktemnd());
     setD1sdcy(ovlknd.getOaktejr());
     setD1infa(ovlknd.getAgvvadr());
     setD1insq(ovlknd.getNagvr());
     setD1insg(ovlknd.getHndtkv());
     setD1rpln(ovlknd.getAnmovl());
     setD1rppf(ovlknd.getTusovl());
     setD1rpfn(ovlknd.getVrn1ovl().split("%")[0] + " " + ovlknd.getVrn2ovl().split("%")[0] + " " + ovlknd.getVrn3ovl().split("%")[0]);
     setD1rpoc(ovlknd.getBrpovl());
     setD1rpbc(ovlknd.getGbpovl());
     setD1rpbl(ovlknd.getGgmovl());
     setD1rpll(ovlknd.getAdrovl());
     setD1rpcs(ovlknd.getBrgovl());
     setD1rpgn(ovlknd.getOvlsex());
     setD1rpdd(ovlknd.getOvldag());
     setD1rpdm(ovlknd.getOvlmnd());
     setD1rpdy(ovlknd.getOvljr());
     setD1rpdh(ovlknd.getOvluur());
     setD1rpdi(ovlknd.getOvlmin());
     setD1rpdl(ovlknd.getPloovl());
     setD1rpad(ovlknd.getLftdovl());
     setD1rpaw(ovlknd.getLftwovl());
     setD1rpam(ovlknd.getLftmovl());
     setD1rpay(ovlknd.getLftjovl());
     setD1faln(ovlknd.getAnmvovl());
     setD1fapf(ovlknd.getTusvovl());
     setD1fafn(ovlknd.getVrn1vovl().split("%")[0] + " " + ovlknd.getVrn2vovl().split("%")[0] + " " + ovlknd.getVrn3vovl().split("%")[0]);
     setD1faca(ovlknd.getLevvovl());
     setD1faoc(ovlknd.getBrpvovl());
     setD1faay(ovlknd.getLfvrovl());
     setD1fall(ovlknd.getAdrvovl());
     setD1moln(ovlknd.getAnmmovl());
     setD1mopf(ovlknd.getTusmovl());
     setD1mofn(ovlknd.getVrn1movl().split("%")[0] + " " + ovlknd.getVrn2movl().split("%")[0] + " " + ovlknd.getVrn3movl().split("%")[0]);
     setD1moca(ovlknd.getLevmovl());
     setD1mooc(ovlknd.getBrpmovl());
     setD1moay(ovlknd.getLfmrovl());
     setD1moll(ovlknd.getAdrmovl());
     setD1sdce(ovlknd.getExtract());
     setD_e_p_l(ovlknd.getVersie());    		
     setD_e_p_o(ovlknd.getVersieo());    	
     
     
   	 //  get part before "%" and split off prefix and title 

     int result = 0;
     
     if((result = Functions.lastname_valid_f(getD1rpln())) != 0)
     	Utils.message(result + Constants.E_OND1RPLN, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1rpln());
     
     if((result = Functions.vlslastname_f(getD1rpln())) != 0)
       	Utils.message(result + Constants.E_VAD1RPLN, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1rpln());

     if((result = Functions.empty_f(getD1rpln())) != 0)
      	Utils.message(result + Constants.E_LED1RPLN, getIdnr(), 0, "HSN_CIVREC_STD", "D1");

     String [] a = Functions.splitField(getD1rpln());            
     setD1rpln(a[0]); 
     setD1rppf(a[1]); 
     setD1rptt(a[2]); 

     if((result = Functions.lastname_valid_f(getD1faln())) != 0)
     	Utils.message(result + Constants.E_OND1FALN, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getD1faln());
     
     if((result = Functions.vlslastname_f(getD1faln())) != 0)
        	Utils.message(result + Constants.E_VAD1FALN, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1faln());
     
     if((result = Functions.empty_f(getD1faln())) != 0)
       	Utils.message(result + Constants.E_LED1FALN, getIdnr(), 0, "HSN_CIVREC_STD", "D1");

     a = Functions.splitField(getD1faln());            
     setD1faln(a[0]); 
     setD1fapf(a[1]); 
     setD1fatt(a[2]);
     
     if((result = Functions.lastname_valid_f(getD1moln())) != 0)
     	Utils.message(result + Constants.E_OND1MOLN, getIdnr(), 0, "HSN_CIVREC_STD", "B1", getD1moln());

     if((result = Functions.vlslastname_f(getD1moln())) != 0)
        	Utils.message(result + Constants.E_VAD1MOLN, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1moln());

     if((result = Functions.empty_f(getD1moln())) != 0)
       	Utils.message(result + Constants.E_LED1MOLN, getIdnr(), 0, "HSN_CIVREC_STD", "D1");

     a = Functions.splitField(getD1moln());            
     setD1moln(a[0]); 
     setD1mopf(a[1]); 
     setD1mott(a[2]); 

     // Check information
     
     
     // Code and name of municipality of Certificate must be handled together
     
     if(getD1sdcc() > 0){     
    	 if((result = Functions.codeconversion_f(getD1sdcc())) != 0)
    		 Utils.message(result + Constants.E_CCD1SDCC, getIdnr(), 0, "HSN_CIVREC_STD", "D1", "" + getD1sdcc());   
    	 else{
    		 Ref_Municipality r = Ref.getMunicipality(getD1sdcc());
    		 setD1sdcl(r.getMunicipalityName());
    	 }
     }
     else
    	 if((result = Functions.empty_f(getD1sdcl())) != 0)
    		 Utils.message(result + Constants.E_LED1SDCC, getIdnr(), 0, "HSN_CIVREC_STD", "D1");
     
    	 
   	 if((result = Functions.vlslocation_f(getD1sdcl())) != 0)
   		 Utils.message(result + Constants.E_VLD1SDCL, getIdnr(), 0, "HSN_CIVREC_STD", "D1");

     
     if((result = Functions.date_f(getD1sdcd(), getD1sdcm(), getD1sdcy())) != 0)
       	Utils.message(result + Constants.E_DAD1SDCY, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1sdcd() + "-" + getD1sdcm() + "-" + getD1sdcy());

     if((result = Functions.hour_f(getD1sdch())) != 0)
        	Utils.message(result + Constants.E_HRD1SDCH, getIdnr(), 0, "HSN_CIVREC_STD", "D1", "" + getD1sdch());
     else
    	 if(getD1sdch() >= 0 && getD1sdch() < 8)
         	Utils.message(100900 + Constants.E_HRD1SDC2, getIdnr(), 0, "HSN_CIVREC_STD", "D1", "" + getD1sdch());

     if((result = Functions.minute_f(getD1sdci())) != 0)
     	Utils.message(result + Constants.E_MID1SDCI, getIdnr(), 0, "HSN_CIVREC_STD", "D1", "" + getD1sdci());

     if((result = Functions.yesno_f(getD1infa())) != 0)
       	Utils.message(result + Constants.E_JND1INFA, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1infa());

     if((result = Functions.signature_f(getD1insg())) != 0)
        	Utils.message(result + Constants.E_SGD1INSG, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1insg());

     if((result = Functions.empty_f(getD1rpfn())) != 0)
       	Utils.message(result + Constants.E_LED1RPFN, getIdnr(), 0, "HSN_CIVREC_STD", "D1");

     if((result = Functions.empty_f(getD1rpoc())) != 0)
        	Utils.message(result + Constants.E_LED1RPOC, getIdnr(), 0, "HSN_CIVREC_STD", "D1");

     
     // Code and name of municipality of birth must be handled together
     
     if(getD1rpbc() > 0){
    	 if((result = Functions.codeconversion_f(getD1rpbc())) != 0)
    		 Utils.message(result + Constants.E_CCD1RPBC, getIdnr(), 0, "HSN_CIVREC_STD", "D1", "" + getD1rpbc());
    	 else{
    		 Ref_Municipality r = Ref.getMunicipality(getD1sdcc());
    		 if(r != null)
    			 setD1rpbl(r.getMunicipalityName());
    	 }
     }
     else
    	 if((result = Functions.empty_f(getD1rpbl())) != 0)
    		 Utils.message(result + Constants.E_LED1RPCC, getIdnr(), 0, "HSN_CIVREC_STD", "D1");
     
     
   	 if((result = Functions.vlslocation_f(getD1rpbl())) != 0)
   		 Utils.message(result + Constants.E_VLD1RPBL, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1rpbl());
 

     if((result = Functions.idem_f(getD1rpll())) != 0)
       	Utils.message(result + Constants.E_IMD1RPLL, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1rpll());

     if((result = Functions.threedouble_f(getD1rpll())) != 0)
        	Utils.message(result + Constants.E_X3D1RPLL, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1rpll());

     if((result = Functions.vlslocation_f(getD1rpll())) != 0)
     	Utils.message(result + Constants.E_VLD1RPLL, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1rpll());

     if((result = Functions.civilstatusD_f(getD1rpcs())) != 0)
      	Utils.message(result + Constants.E_CSD1RPCS, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1rpcs());

     if((result = Functions.date_f(getD1rpdd(), getD1rpdm(), getD1rpdy())) != 0)
       	Utils.message(result + Constants.E_DAD1RPDY, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1rpdd() + "-" + getD1rpdm() + "-" + getD1rpdy());

     if((result = Functions.hour_f(getD1rpdh())) != 0)
        	Utils.message(result + Constants.E_HRD1RPDH, getIdnr(), 0, "HSN_CIVREC_STD", "D1", "" + getD1rpdh());

     if((result = Functions.minute_f(getD1rpdi())) != 0)
     	Utils.message(result + Constants.E_MID1RPDI, getIdnr(), 0, "HSN_CIVREC_STD", "D1", "" + getD1rpdi());

     if((result = Functions.threedouble_f(getD1rpdl())) != 0)
      	Utils.message(result + Constants.E_X3D1RPDL, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1rpdl());

     if((result = Functions.idem_f(getD1rpdl())) != 0)
       	Utils.message(result + Constants.E_IMD1RPDL, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1rpdl());

     if((result = Functions.vlslocation_f(getD1rpdl())) != 0)
        	Utils.message(result + Constants.E_VLD1RPDL, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1rpdl());

     if((result = Functions.age_f(getD1rpay())) != 0)
     	Utils.message(result + Constants.E_AGD1RPAY, getIdnr(), 0, "HSN_CIVREC_STD", "D1", "" + getD1rpay());

     if((result = Functions.empty_f(getD1fafn())) != 0)
        	Utils.message(result + Constants.E_LED1FAFN, getIdnr(), 0, "HSN_CIVREC_STD", "D1");

     if((result = Functions.yesnoO_f(getD1faca())) != 0)
     	Utils.message(result + Constants.E_JND1FACA, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1faca());

     if((result = Functions.age_f(getD1faay())) != 0)
      	Utils.message(result + Constants.E_AGD1FAAY, getIdnr(), 0, "HSN_CIVREC_STD", "D1", "" + getD1faay());

     if((result = Functions.idem_f(getD1fall())) != 0)
       	Utils.message(result + Constants.E_IMD1FALL, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1fall());

     if((result = Functions.threedouble_f(getD1fall())) != 0)
        	Utils.message(result + Constants.E_X3D1FALL, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1fall());

     if((result = Functions.vlslocation_f(getD1fall())) != 0)
     	Utils.message(result + Constants.E_VLD1FALL, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1fall());

     if((result = Functions.empty_f(getD1mofn())) != 0)
        	Utils.message(result + Constants.E_LED1MOFN, getIdnr(), 0, "HSN_CIVREC_STD", "D1");

     if((result = Functions.yesnoO_f(getD1moca())) != 0)
     	Utils.message(result + Constants.E_JND1MOCA, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1moca());

     if((result = Functions.age_f(getD1moay())) != 0)
      	Utils.message(result + Constants.E_AGD1MOAY, getIdnr(), 0, "HSN_CIVREC_STD", "D1", "" + getD1moay());

     if((result = Functions.idem_f(getD1moll())) != 0)
       	Utils.message(result + Constants.E_IMD1MOLL, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1moll());

     if((result = Functions.threedouble_f(getD1moll())) != 0)
        	Utils.message(result + Constants.E_X3D1MOLL, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1moll());
     
     if((result = Functions.vlslocation_f(getD1moll())) != 0)
       	Utils.message(result + Constants.E_VLD1MOLL, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1moll());
     
     if((result = Functions.yesno_f(getD1sdce())) != 0)
      	Utils.message(result + Constants.E_JND1SDCE, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1sdce());

     if((result = Functions.gender_f(getD1rpgn())) != 0)
      	Utils.message(result + Constants.E_GND1RPGN, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1rpgn());

     // Reference checks
     
     setD1rpoc(Functions.profession_r(getD1rpoc(), Constants.E_OCD1RPOC, getIdnr(), 0, "HSN_CIVREC_STD", "D1"));
     setD1faoc(Functions.profession_r(getD1faoc(), Constants.E_OCD1FAOC, getIdnr(), 0, "HSN_CIVREC_STD", "D1"));
     setD1mooc(Functions.profession_r(getD1mooc(), Constants.E_OCD1MOOC, getIdnr(), 0, "HSN_CIVREC_STD", "D1"));
     setD1sdcl(Functions.location_r(getD1sdcl(), Constants.E_LOD1SDCL, getIdnr(), 0, "HSN_CIVREC_STD", "D1"));
     setD1rpbl(Functions.location_r(getD1rpbl(), Constants.E_LOD1RPBL, getIdnr(), 0, "HSN_CIVREC_STD", "D1"));
     setD1rpdl(Functions.location_r(getD1rpdl(), Constants.E_LOD1RPDL, getIdnr(), 0, "HSN_CIVREC_STD", "D1"));
     setD1rpln(Functions.familyname_r(getD1rpln(), Constants.E_LND1RPLN, getIdnr(), 0, "HSN_CIVREC_STD", "D1"));
     setD1rppf(Functions.prefix_r(getD1rppf(), Constants.E_LND1RPPF, getIdnr(), 0, "HSN_CIVREC_STD", "D1"));
     setD1rpfn(Functions.firstname_r(getD1rpfn(), Constants.E_FND1RPFN, getIdnr(), 0, "HSN_CIVREC_STD", "D1"));

     setD1faln(Functions.familyname_r(getD1faln(), Constants.E_LND1FALN, getIdnr(), 0, "HSN_CIVREC_STD", "D1"));
     setD1fapf(Functions.prefix_r(getD1fapf(), Constants.E_LND1FAPF, getIdnr(), 0, "HSN_CIVREC_STD", "D1"));
     setD1fafn(Functions.firstname_r(getD1fafn(), Constants.E_FND1FAFN, getIdnr(), 0, "HSN_CIVREC_STD", "D1"));
     
     setD1moln(Functions.familyname_r(getD1moln(), Constants.E_LND1MOLN, getIdnr(), 0, "HSN_CIVREC_STD", "D1"));
     setD1mopf(Functions.prefix_r(getD1mopf(), Constants.E_LND1MOPF, getIdnr(), 0, "HSN_CIVREC_STD", "D1"));
     setD1mofn(Functions.firstname_r(getD1mofn(), Constants.E_FND1MOFN, getIdnr(), 0, "HSN_CIVREC_STD", "D1"));
     
     // logic checks    
     
     if(Utils.dayCount(getD1sdcd(), getD1sdcm(), getD1sdcy()) > 0 &&  Utils.dayCount(getD1rpdd(), getD1rpdm(), getD1rpdy()) > 0)
         if(Utils.dayCount(getD1sdcd(), getD1sdcm(), getD1sdcy()) - Utils.dayCount(getD1rpdd(), getD1rpdm(), getD1rpdy()) > 5)
        	 Utils.message(300500 + Constants.E_DLD1D1DC, getIdnr(), 0, "HSN_CIVREC_STD", "D1", getD1rpdd() + "-" + getD1rpdm() + "-" + getD1rpdy() + " " + 
        			 getD1sdcd() + "-" + getD1sdcm() + "-" + getD1sdcy());
        			 
     for(Huwknd huwknd: ovlknd.getGebknd().getHuwkndL())
    	 if(Utils.dayCount(huwknd.getHdag(), huwknd.getHmaand(), huwknd.getHjaar()) > 0 && Utils.dayCount(getD1rpdd(), getD1rpdm(),getD1rpdy()) > 0)
        	 if(Utils.dayCount(huwknd.getHdag(), huwknd.getHmaand(), huwknd.getHjaar()) > Utils.dayCount(getD1rpdd(), getD1rpdm(),getD1rpdy()))
    	      	Utils.message(300500 + Constants.E_DLM1D1MD, getIdnr(), 0, "HSN_CIVREC_STD", "D1", huwknd.getHdag() + "-" + huwknd.getHmaand() + "-" + huwknd.getHjaar() + " " + 
    	      	     getD1rpdd() + "-" + getD1rpdm() + "-" + getD1rpdy());
     
     if(Utils.dayCount(getD1rpdd(), getD1rpdm(),getD1rpdy()) > 0 && Utils.dayCount(ovlknd.getGebknd().getGebdag(), ovlknd.getGebknd().getGebmnd(), ovlknd.getGebknd().getGebjr()) > 0){
    	 int dif = Utils.dayCount(getD1rpdd(), getD1rpdm(),getD1rpdy()) - Utils.dayCount(ovlknd.getGebknd().getGebdag(), ovlknd.getGebknd().getGebmnd(), ovlknd.getGebknd().getGebjr());
    	 dif = dif / 365;
    	 
    	 if(dif < 0)
    		 Utils.message(300500 + Constants.E_DLB1D1BD, getIdnr(), 0, "HSN_CIVREC_STD", "D1", ovlknd.getGebknd().getGebdag() + "-" + ovlknd.getGebknd().getGebmnd() + "-" + ovlknd.getGebknd().getGebjr() +
     				"  " + getD1rpdd() + "-" + getD1rpdm() + "-" + getD1rpdy() + " " + getD1rpay());

    	 
    	 if(getD1rpay() > 0 && Math.abs(dif - getD1rpay()) > 1){
    		 
    		 //System.out.println();
    		 //System.out.println("Birth Day " + ovlknd.getGebknd().getGebdag() + "-" + ovlknd.getGebknd().getGebmnd() + "-" + ovlknd.getGebknd().getGebjr());
    		 //System.out.println("Day count birthday " + Utils.dayCount(ovlknd.getGebknd().getGebdag(), ovlknd.getGebknd().getGebmnd(), ovlknd.getGebknd().getGebjr()));
    		 //System.out.println("Death day " + getD1rpdd() + "-" + getD1rpdm() + "-" + getD1rpdy() + " " + getD1rpay());
    		 //System.out.println("Day count deathday " + Utils.dayCount(getD1rpdd(), getD1rpdm(),getD1rpdy()));
    		 //System.out.println("Age " + getD1rpay());
    		 //System.out.println("diff " + dif);
    		 
    		 Utils.message(300700 + Constants.E_ALD1RPAY, getIdnr(), 0, "HSN_CIVREC_STD", "D1", ovlknd.getGebknd().getGebdag() + "-" + ovlknd.getGebknd().getGebmnd() + "-" + ovlknd.getGebknd().getGebjr() +
    				"  " + getD1rpdd() + "-" + getD1rpdm() + "-" + getD1rpdy() + " " + getD1rpay());
    	 }
     }
    	 
     if(!getD1rpgn().equalsIgnoreCase(ovlknd.getGebknd().getGebsex()))
	      	Utils.message(300800 + Constants.E_GLD1RPGN, getIdnr(), 0, "HSN_CIVREC_STD", "D1");
     
     
     }
    	 
     
    public void truncate(){
       	
       	String field = getD1rpln();
       	int allowedSize = Const.Bigstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1RPLN",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1rpln(field);
       	}
       	
       	field = getD1rppf();
       	allowedSize = Const.Smallstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1RPPF",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1rppf(field);
       	}

       	field = getD1rpfn();
       	allowedSize = Const.Bigstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1RPFN",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1rpfn(field);
       	}
       	
       	field = getD1rptt();
       	allowedSize = Const.Smallstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1RPTT",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1rptt(field);
       	}
       	
       	field = getD1rppa();
       	allowedSize = Const.Smallstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1RPPA",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1rppa(field);
       	}
       	
       	field = getD1rpoc();
       	allowedSize = Const.Bigstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1RPOC",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1rpoc(field);
       	}
       	
       	field = getD1rpbl();
       	allowedSize = Const.Bigstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1RPBL",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1rpbl(field);
       	}
       	
       	field = getD1rpll();
       	allowedSize = Const.Bigstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1RPLL",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1rpll(field);
       	}
       	
       	field = getD1rpdl();
       	allowedSize = Const.Bigstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1RPDL",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1rpdl(field);
       	}
       	
       	field = getD1faln();
       	allowedSize = Const.Bigstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1FALN",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1faln(field);
       	}
       	
       	field = getD1fapf();
       	allowedSize = Const.Smallstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1FAPF",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1fapf(field);
       	}
       	
       	field = getD1fafn();
       	allowedSize = Const.Bigstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1FAFN",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1fafn(field);
       	}
       	
       	field = getD1fatt();
       	allowedSize = Const.Smallstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1FATT",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1fatt(field);
       	}
       	
       	field = getD1fapa();
       	allowedSize = Const.Smallstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1FAPA",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1fapa(field);
       	}
       	
       	field = getD1faoc();
       	allowedSize = Const.Bigstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1FAOC",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1faoc(field);
       	}
       	
       	field = getD1fall();
       	allowedSize = Const.Bigstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1FALL",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1fall(field);
       	}
       	
       	field = getD1moln();
       	allowedSize = Const.Bigstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1MOLN",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1moln(field);
       	}
       	
       	field = getD1mopf();
       	allowedSize = Const.Smallstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1MOPF",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1mopf(field);
       	}
       	
       	field = getD1mofn();
       	allowedSize = Const.Bigstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1MOFN",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1mofn(field);
       	}
       	
       	field = getD1mott();
       	allowedSize = Const.Smallstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1MOTT",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1mott(field);
       	}
       	
       	field = getD1mopa();
       	allowedSize = Const.Smallstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1MOPA",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1mopa(field);
       	}
       	
       	field = getD1moll();
       	allowedSize = Const.Bigstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1MOLL",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1moll(field);
       	}
       	
       	field = getD1mooc();
       	allowedSize = Const.Bigstring;
       	if(field != null && field.length() > allowedSize){
       		Utils.message(1000000, getIdnr(), 0, "HSN_CIVREC_STD",  "D1MOOC",  (new Integer(field.length()).toString()));
       		field = field.substring(0, allowedSize);
       		setD1mooc(field);
       	}
       	

   	}

     
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public int getD1sdcc() {
		return d1sdcc;
	}
	public void setD1sdcc(int d1sdcc) {
		this.d1sdcc = d1sdcc;
	}
	public String getD1sdcl() {
		return d1sdcl;
	}
	public void setD1sdcl(String d1sdcl) {
		this.d1sdcl = d1sdcl;
	}
	public int getD1sdcn() {
		return d1sdcn;
	}
	public void setD1sdcn(int d1sdcn) {
		this.d1sdcn = d1sdcn;
	}
	public int getD1sdch() {
		return d1sdch;
	}
	public void setD1sdch(int d1sdch) {
		this.d1sdch = d1sdch;
	}
	public int getD1sdci() {
		return d1sdci;
	}
	public void setD1sdci(int d1sdci) {
		this.d1sdci = d1sdci;
	}
	public int getD1sdcd() {
		return d1sdcd;
	}
	public void setD1sdcd(int d1sdcd) {
		this.d1sdcd = d1sdcd;
	}
	public int getD1sdcm() {
		return d1sdcm;
	}
	public void setD1sdcm(int d1sdcm) {
		this.d1sdcm = d1sdcm;
	}
	public int getD1sdcy() {
		return d1sdcy;
	}
	public void setD1sdcy(int d1sdcy) {
		this.d1sdcy = d1sdcy;
	}
	public String getD1infa() {
		return d1infa;
	}
	public void setD1infa(String d1infa) {
		this.d1infa = d1infa;
	}
	public int getD1insq() {
		return d1insq;
	}
	public void setD1insq(int d1insq) {
		this.d1insq = d1insq;
	}
	public String getD1insg() {
		return d1insg;
	}
	public void setD1insg(String d1insg) {
		this.d1insg = d1insg;
	}
	public String getD1rpln() {
		return d1rpln;
	}
	public void setD1rpln(String d1rpln) {
		this.d1rpln = d1rpln;
	}
	public String getD1rppf() {
		return d1rppf;
	}
	public void setD1rppf(String d1rppf) {
		this.d1rppf = d1rppf;
	}
	public String getD1rpfn() {
		return d1rpfn;
	}
	public void setD1rpfn(String d1rpfn) {
		this.d1rpfn = d1rpfn;
	}
	public String getD1rptt() {
		return d1rptt;
	}
	public void setD1rptt(String d1rptt) {
		this.d1rptt = d1rptt;
	}
	public String getD1rppa() {
		return d1rppa;
	}
	public void setD1rppa(String d1rppa) {
		this.d1rppa = d1rppa;
	}
	public String getD1rpoc() {
		return d1rpoc;
	}
	public void setD1rpoc(String d1rpoc) {
		this.d1rpoc = d1rpoc;
	}
	public int getD1rpbc() {
		return d1rpbc;
	}
	public void setD1rpbc(int d1rpbc) {
		this.d1rpbc = d1rpbc;
	}
	public String getD1rpbl() {
		return d1rpbl;
	}
	public void setD1rpbl(String d1rpbl) {
		this.d1rpbl = d1rpbl;
	}
	public String getD1rpll() {
		return d1rpll;
	}
	public void setD1rpll(String d1rpll) {
		this.d1rpll = d1rpll;
	}
	public String getD1rpcs() {
		return d1rpcs;
	}
	public void setD1rpcs(String d1rpcs) {
		this.d1rpcs = d1rpcs;
	}
	public String getD1rpgn() {
		return d1rpgn;
	}
	public void setD1rpgn(String d1rpgn) {
		this.d1rpgn = d1rpgn;
	}
	public int getD1rpdd() {
		return d1rpdd;
	}
	public void setD1rpdd(int d1rpdd) {
		this.d1rpdd = d1rpdd;
	}
	public int getD1rpdm() {
		return d1rpdm;
	}
	public void setD1rpdm(int d1rpdm) {
		this.d1rpdm = d1rpdm;
	}
	public int getD1rpdy() {
		return d1rpdy;
	}
	public void setD1rpdy(int d1rpdy) {
		this.d1rpdy = d1rpdy;
	}
	public int getD1rpdh() {
		return d1rpdh;
	}
	public void setD1rpdh(int d1rpdh) {
		this.d1rpdh = d1rpdh;
	}
	public int getD1rpdi() {
		return d1rpdi;
	}
	public void setD1rpdi(int d1rpdi) {
		this.d1rpdi = d1rpdi;
	}
	public String getD1rpdl() {
		return d1rpdl;
	}
	public void setD1rpdl(String d1rpdl) {
		this.d1rpdl = d1rpdl;
	}
	public int getD1rpad() {
		return d1rpad;
	}
	public void setD1rpad(int d1rpad) {
		this.d1rpad = d1rpad;
	}
	public int getD1rpaw() {
		return d1rpaw;
	}
	public void setD1rpaw(int d1rpaw) {
		this.d1rpaw = d1rpaw;
	}
	public int getD1rpam() {
		return d1rpam;
	}
	public void setD1rpam(int d1rpam) {
		this.d1rpam = d1rpam;
	}
	public int getD1rpay() {
		return d1rpay;
	}
	public void setD1rpay(int d1rpay) {
		this.d1rpay = d1rpay;
	}
	public String getD1faln() {
		return d1faln;
	}
	public void setD1faln(String d1faln) {
		this.d1faln = d1faln;
	}
	public String getD1fapf() {
		return d1fapf;
	}
	public void setD1fapf(String d1fapf) {
		this.d1fapf = d1fapf;
	}
	public String getD1fafn() {
		return d1fafn;
	}
	public void setD1fafn(String d1fafn) {
		this.d1fafn = d1fafn;
	}
	public String getD1fatt() {
		return d1fatt;
	}
	public void setD1fatt(String d1fatt) {
		this.d1fatt = d1fatt;
	}
	public String getD1fapa() {
		return d1fapa;
	}
	public void setD1fapa(String d1fapa) {
		this.d1fapa = d1fapa;
	}
	public String getD1faca() {
		return d1faca;
	}
	public void setD1faca(String d1faca) {
		this.d1faca = d1faca;
	}
	public String getD1faoc() {
		return d1faoc;
	}
	public void setD1faoc(String d1faoc) {
		this.d1faoc = d1faoc;
	}
	public int getD1faay() {
		return d1faay;
	}
	public void setD1faay(int d1faay) {
		this.d1faay = d1faay;
	}
	public String getD1fall() {
		return d1fall;
	}
	public void setD1fall(String d1fall) {
		this.d1fall = d1fall;
	}
	public String getD1moln() {
		return d1moln;
	}
	public void setD1moln(String d1moln) {
		this.d1moln = d1moln;
	}
	public String getD1mopf() {
		return d1mopf;
	}
	public void setD1mopf(String d1mopf) {
		this.d1mopf = d1mopf;
	}
	public String getD1mofn() {
		return d1mofn;
	}
	public void setD1mofn(String d1mofn) {
		this.d1mofn = d1mofn;
	}
	public String getD1mott() {
		return d1mott;
	}
	public void setD1mott(String d1mott) {
		this.d1mott = d1mott;
	}
	public String getD1mopa() {
		return d1mopa;
	}
	public void setD1mopa(String d1mopa) {
		this.d1mopa = d1mopa;
	}
	public String getD1moca() {
		return d1moca;
	}
	public void setD1moca(String d1moca) {
		this.d1moca = d1moca;
	}
	public String getD1mooc() {
		return d1mooc;
	}
	public void setD1mooc(String d1mooc) {
		this.d1mooc = d1mooc;
	}
	public int getD1moay() {
		return d1moay;
	}
	public void setD1moay(int d1moay) {
		this.d1moay = d1moay;
	}
	public String getD1moll() {
		return d1moll;
	}
	public void setD1moll(String d1moll) {
		this.d1moll = d1moll;
	}
	public String getD1sdce() {
		return d1sdce;
	}
	public void setD1sdce(String d1sdce) {
		this.d1sdce = d1sdce;
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
     
     
     
}
