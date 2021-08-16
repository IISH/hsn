package iisg.nl.hsnnieuw;

import iisg.nl.hsnimport.Gebknd;
import iisg.nl.hsnimport.Huwknd;
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
@Table(name="m1")
public class M1 {
     @Column(name="IDNR")         private int idnr;
     @Column(name="M1SDCN")       private int m1sdcn;
     @Column(name="M1SDML")       private String m1sdml;
     @Column(name="M1SDMH")       private int m1sdmh;
     @Column(name="MAR_CD")       private int mar_cd;
     @Column(name="MAR_CM")       private int mar_cm;
     @Column(name="MAR_CY")       private int mar_cy;
     @Column(name="M1SDSE")       private String m1sdse;
     @Column(name="M1SDSD")       private int m1sdsd;
     @Column(name="M1SDSM")       private int m1sdsm;
     @Column(name="M1SDSY")       private int m1sdsy;
     @Column(name="M1SDSL")       private String m1sdsl;
     @Column(name="M1SDED")       private int m1sded;
     @Column(name="M1SDEM")       private int m1sdem;
     @Column(name="M1SDEY")       private int m1sdey;
     @Column(name="M1SDEL")       private String m1sdel;
     @Column(name="M1GRAY")       private int m1gray;
     @Column(name="M1BRAY")       private int m1bray;
     @Column(name="M1RPGN")       private String m1rpgn;
     @Column(name="M1GRLN")       private String m1grln;
     @Column(name="M1GRPF")       private String m1grpf;
     @Column(name="M1GRFN")       private String m1grfn;
     @Column(name="M1GRTT")       private String m1grtt;
     @Column(name="M1GRPA")       private String m1grpa;
     @Column(name="M1GROC")       private String m1groc;
     @Column(name="M1GRBL")       private String m1grbl;
     @Column(name="M1GRLL")       private String m1grll;
     @Column(name="M1GRLF")       private String m1grlf;
     @Column(name="M1GRLO")       private String m1grlo;
     @Column(name="M1GRPL")       private String m1grpl;
     @Column(name="M1GRQL")       private String m1grql;
     @Column(name="M1GRCS")       private String m1grcs;
     @Column(name="M1GRSG")       private String m1grsg;
     @Column(name="M1BRLN")       private String m1brln;
     @Column(name="M1BRPF")       private String m1brpf;
     @Column(name="M1BRFN")       private String m1brfn;
     @Column(name="M1BRTT")       private String m1brtt;
     @Column(name="M1BRPA")       private String m1brpa;
     @Column(name="M1BROC")       private String m1broc;
     @Column(name="M1BRBL")       private String m1brbl;
     @Column(name="M1BRLL")       private String m1brll;
     @Column(name="M1BRLF")       private String m1brlf;
     @Column(name="M1BRLO")       private String m1brlo;
     @Column(name="M1BRPL")       private String m1brpl;
     @Column(name="M1BRQL")       private String m1brql;
     @Column(name="M1BRCS")       private String m1brcs;
     @Column(name="M1BRSG")       private String m1brsg;
     @Column(name="M1GFCA")       private String m1gfca;
     @Column(name="M1GFMP")       private String m1gfmp;
     @Column(name="M1GFLN")       private String m1gfln;
     @Column(name="M1GFPF")       private String m1gfpf;
     @Column(name="M1GFFN")       private String m1gffn;
     @Column(name="M1GFTT")       private String m1gftt;
     @Column(name="M1GFPA")       private String m1gfpa;
     @Column(name="M1GFOC")       private String m1gfoc;
     @Column(name="M1GFLL")       private String m1gfll;
     @Column(name="M1GFDL")       private String m1gfdl;
     @Column(name="M1GFSG")       private String m1gfsg;
     @Column(name="M1GFAY")       private int m1gfay;
     @Column(name="M1GMCA")       private String m1gmca;
     @Column(name="M1GMMP")       private String m1gmmp;
     @Column(name="M1GMLN")       private String m1gmln;
     @Column(name="M1GMPF")       private String m1gmpf;
     @Column(name="M1GMFN")       private String m1gmfn;
     @Column(name="M1GMTT")       private String m1gmtt;
     @Column(name="M1GMPA")       private String m1gmpa;
     @Column(name="M1GMOC")       private String m1gmoc;
     @Column(name="M1GMLL")       private String m1gmll;
     @Column(name="M1GMDL")       private String m1gmdl;
     @Column(name="M1GMSG")       private String m1gmsg;
     @Column(name="M1GMAY")       private int m1gmay;
     @Column(name="M1BFCA")       private String m1bfca;
     @Column(name="M1BFMP")       private String m1bfmp;
     @Column(name="M1BFLN")       private String m1bfln;
     @Column(name="M1BFPF")       private String m1bfpf;
     @Column(name="M1BFFN")       private String m1bffn;
     @Column(name="M1BFTT")       private String m1bftt;
     @Column(name="M1BFPA")       private String m1bfpa;
     @Column(name="M1BFOC")       private String m1bfoc;
     @Column(name="M1BFLL")       private String m1bfll;
     @Column(name="M1BFDL")       private String m1bfdl;
     @Column(name="M1BFSG")       private String m1bfsg;
     @Column(name="M1BFAY")       private int m1bfay;
     @Column(name="M1BMCA")       private String m1bmca;
     @Column(name="M1BMMP")       private String m1bmmp;
     @Column(name="M1BMLN")       private String m1bmln;
     @Column(name="M1BMPF")       private String m1bmpf;
     @Column(name="M1BMFN")       private String m1bmfn;
     @Column(name="M1BMTT")       private String m1bmtt;
     @Column(name="M1BMPA")       private String m1bmpa;
     @Column(name="M1BMOC")       private String m1bmoc;
     @Column(name="M1BMLL")       private String m1bmll;
     @Column(name="M1BMDL")       private String m1bmdl;
     @Column(name="M1BMSG")       private String m1bmsg;
     @Column(name="M1BMAY")       private int m1bmay;
     @Column(name="M1SDBB")       private String m1sdbb;
     @Column(name="M1SDDP")       private String m1sddp;
     @Column(name="M1SDDE")       private String m1sdde;
     @Column(name="M1SDNM")       private String m1sdnm;
     @Column(name="M1SDPN")       private String m1sdpn;
     @Column(name="M1SDAF")       private String m1sdaf;
     @Column(name="M1SDAP")       private String m1sdap;
     @Column(name="M1SDPC")       private String m1sdpc;
     @Column(name="M1SDPG")       private String m1sdpg;
     @Column(name="D_E_P_L")      private String d_e_p_l;
     @Column(name="D_E_P_O")      private String d_e_p_o;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")    private int recordID;
     
     public void transform(Huwknd huwknd){	
 		
    	 
 	    // copy and/or combine
 	 
    	 setIdnr(huwknd.getIdnr());
    	 setM1sdcn(huwknd.getHaktenr());
    	 setM1sdml(huwknd.getHplts());
    	 setM1sdmh(huwknd.getHuur());
    	 setMar_cd(huwknd.getHdag()); 
    	 setMar_cm(huwknd.getHmaand());
    	 setMar_cy(huwknd.getHjaar());
    	 setM1sdse(huwknd.getScheidng());
    	 setM1sdsd(huwknd.getSdag());
    	 setM1sdsm(huwknd.getSmaand());
    	 setM1sdsy(huwknd.getSjaar());
    	 setM1sdsl(huwknd.getSplts());
    	 setM1sded(huwknd.getIdag());
    	 setM1sdem(huwknd.getImaand());
    	 setM1sdey(huwknd.getIjaar());
    	 setM1sdel(huwknd.getIplts());
    	 setM1gray(huwknd.getLftjhm());
    	 setM1bray(huwknd.getLftjhv());
    	 setM1rpgn(huwknd.getGebsex());
    	 setM1grln(huwknd.getAnmhm());
    	 setM1grpf(huwknd.getTushm());
    	 setM1grfn(Utils.combine3FirstNames(huwknd.getVrn1hm(), huwknd.getVrn2hm(), huwknd.getVrn3hm()));
    	 setM1groc(huwknd.getBrphm());
    	 setM1grbl(huwknd.getGebplhm());
    	 setM1grll(huwknd.getAdrhm());
    	 setM1grlf(huwknd.getOadrhm());
    	 setM1grlo(huwknd.getOadrehm());
    	 setM1grcs(huwknd.getBsthm());
    	 setM1grsg(huwknd.getHndhm());
    	 setM1brln(huwknd.getAnmhv());
    	 setM1brpf(huwknd.getTushv());
    	 setM1brfn(Utils.combine3FirstNames(huwknd.getVrn1hv(), huwknd.getVrn2hv(), huwknd.getVrn3hv()));
    	 setM1broc(huwknd.getBrphv());
    	 setM1brbl(huwknd.getGebplhv());
    	 setM1brll(huwknd.getAdrhv());
    	 setM1brlf(huwknd.getOadrhv());
    	 setM1brlo(huwknd.getOadrehv());
    	 setM1brcs(huwknd.getBsthv());
    	 setM1brsg(huwknd.getHndhv());
    	 setM1gfca(huwknd.getLevvrhm());
    	 setM1gfmp(huwknd.getToevrhm());
    	 setM1gfln(huwknd.getAnmvrhm());
    	 setM1gfpf(huwknd.getTusvrhm());
    	 setM1gffn(Utils.combine3FirstNames(huwknd.getVrn1vrhm(), huwknd.getVrn2vrhm(), huwknd.getVrn3vrhm()));
    	 setM1gfoc(huwknd.getBrpvrhm());
    	 setM1gfll(huwknd.getAdrvrhm());
    	 setM1gfdl(huwknd.getPlovvrhm());
    	 setM1gfsg(huwknd.getHndvrhm());
    	 setM1gfay(huwknd.getLftjvrhm());
    	 setM1gmca(huwknd.getLevvrhm());
    	 setM1gmmp(huwknd.getToemrhm());
    	 setM1gmln(huwknd.getAnmmrhm());
    	 setM1gmpf(huwknd.getTusmrhm());
    	 setM1gmfn(Utils.combine3FirstNames(huwknd.getVrn1mrhm(), huwknd.getVrn2mrhm(), huwknd.getVrn3mrhm()));
    	 setM1gmoc(huwknd.getBrpmrhm());
    	 setM1gmll(huwknd.getAdrmrhm());
    	 setM1gmdl(huwknd.getPlovmrhm());
    	 setM1gmsg(huwknd.getHndmrhm());
    	 setM1gmay(huwknd.getLftjmrhm());
    	 setM1bfca(huwknd.getLevvrhv());
    	 setM1bfmp(huwknd.getToevrhv());
    	 setM1bfln(huwknd.getAnmvrhv());
    	 setM1bfpf(huwknd.getTusvrhv());
    	 setM1bffn(Utils.combine3FirstNames(huwknd.getVrn1vrhv(), huwknd.getVrn2vrhv(), huwknd.getVrn3vrhv()));
    	 setM1bfoc(huwknd.getBrpvrhv());
    	 setM1bfll(huwknd.getAdrvrhv());
    	 setM1bfdl(huwknd.getPlovvrhv());
    	 setM1bfsg(huwknd.getHndvrhv());
    	 setM1bfay(huwknd.getLftjvrhv());
    	 setM1bmca(huwknd.getLevvrhv());
    	 setM1bmmp(huwknd.getToemrhv());
    	 setM1bmln(huwknd.getAnmmrhv());
    	 setM1bmpf(huwknd.getTusmrhv());
    	 setM1bmfn(Utils.combine3FirstNames(huwknd.getVrn1mrhv(), huwknd.getVrn2mrhv(), huwknd.getVrn3mrhv()));
    	 setM1bmoc(huwknd.getBrpmrhv());
    	 setM1bmll(huwknd.getAdrmrhv());
    	 setM1bmdl(huwknd.getPlovmrhv());
    	 setM1bmsg(huwknd.getHndmrhv());
    	 setM1bmay(huwknd.getLftjmrhv());
    	 setM1sdbb(huwknd.getUgebhuw());
    	 setM1sddp(huwknd.getUovloud());
    	 setM1sdde(huwknd.getUovlech());
    	 setM1sdnm(huwknd.getCertnatm());
    	 setM1sdpn(huwknd.getToestnot());
    	 setM1sdaf(huwknd.getAktebek());
    	 setM1sdap(huwknd.getOnvermgn());
    	 setM1sdpc(huwknd.getCommand());
    	 setM1sdpg(huwknd.getToestvgd());
         setD_e_p_l(huwknd.getVersie());    		
         setD_e_p_o(huwknd.getVersieo());    		

    	     		
 		
         
       	 //  get part before "%" and split off prefix and title 

         int result = 0;
         
         if((result = Functions.vlslastname_f(getM1grln())) != 0)
           	Utils.message(result + Constants.E_VAM1GRLN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1grln());
           
         if((result = Functions.empty_f(getM1grln())) != 0)
            	Utils.message(result + Constants.E_LEM1GRLN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1");
         
         if((result = Functions.lastname_valid_f(getM1grln())) != 0)
         	Utils.message(result + Constants.E_ONM1GRLN, getIdnr(), 0, "HSN_CIVREC_STD", "M1", getM1grln());

         
         String [] a = Functions.splitField(getM1grln());            
         setM1grln(a[0]); 
         setM1grpf(a[1]); 
         setM1grtt(a[2]); 
         
         if((result = Functions.vlslastname_f(getM1brln())) != 0)
            	Utils.message(result + Constants.E_VAM1BRLN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1brln());
         
         if((result = Functions.empty_f(getM1brln())) != 0)
         	Utils.message(result + Constants.E_LEM1BRLN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1");
         
         if((result = Functions.lastname_valid_f(getM1brln())) != 0)
          	Utils.message(result + Constants.E_ONM1BRLN, getIdnr(), 0, "HSN_CIVREC_STD", "M1", getM1brln());

         
         a = Functions.splitField(getM1brln());            
         setM1brln(a[0]); 
         setM1brpf(a[1]); 
         setM1brtt(a[2]); 
         
         if((result = Functions.vlslastname_f(getM1gfln())) != 0)
          	Utils.message(result + Constants.E_VAM1GFLN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1gfln());
          
         if((result = Functions.empty_f(getM1gfln())) != 0)
           	Utils.message(result + Constants.E_LEM1GFLN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1");
         
         if((result = Functions.lastname_valid_f(getM1gfln())) != 0)
           	Utils.message(result + Constants.E_ONM1GFLN, getIdnr(), 0, "HSN_CIVREC_STD", "M1", getM1gfln());

         a = Functions.splitField(getM1gfln());            
         setM1gfln(a[0]); 
         setM1gfpf(a[1]); 
         setM1gftt(a[2]);
         
         if((result = Functions.vlslastname_f(getM1gmln())) != 0)
           	Utils.message(result + Constants.E_VAM1GMLN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1gmln());
           
         if((result = Functions.empty_f(getM1gmln())) != 0)
           	Utils.message(result + Constants.E_LEM1GMLN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1");
         
         if((result = Functions.lastname_valid_f(getM1gmln())) != 0)
            	Utils.message(result + Constants.E_ONM1GMLN, getIdnr(), 0, "HSN_CIVREC_STD", "M1", getM1gmln());

         a = Functions.splitField(getM1gmln());            
         setM1gmln(a[0]); 
         setM1gmpf(a[1]); 
         setM1gmtt(a[2]); 
         
         if((result = Functions.vlslastname_f(getM1bmln())) != 0)
            	Utils.message(result + Constants.E_VAM1BMLN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1bmln());

         if((result = Functions.empty_f(getM1bmln())) != 0)
            	Utils.message(result + Constants.E_LEM1BMLN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1");
         
         if((result = Functions.lastname_valid_f(getM1bmln())) != 0)
         	Utils.message(result + Constants.E_ONM1BFLN, getIdnr(), 0, "HSN_CIVREC_STD", "M1", getM1bmln());


         a = Functions.splitField(getM1bmln());
         
         setM1bmln(a[0]); 
         setM1bmpf(a[1]); 
         setM1bmtt(a[2]);
         
         if((result = Functions.vlslastname_f(getM1bfln())) != 0)
         	Utils.message(result + Constants.E_VAM1BFLN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1bfln());

         if((result = Functions.empty_f(getM1bfln())) != 0)
        	 Utils.message(result + Constants.E_LEM1BFLN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1");

         if((result = Functions.lastname_valid_f(getM1bfln())) != 0)
          	Utils.message(result + Constants.E_ONM1BMLN, getIdnr(), 0, "HSN_CIVREC_STD", "M1", getM1bfln());

         
         a = Functions.splitField(getM1bfln());
         
         setM1bfln(a[0]); 
         setM1bfpf(a[1]); 
         setM1bftt(a[2]); 

         
         // Check information
         
         if((result = Functions.empty_f(getM1sdml())) != 0)
           	Utils.message(result + Constants.E_LEM1SDML, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1");
           
         if((result = Functions.vlslocation_f(getM1sdml())) != 0)
           	Utils.message(result + Constants.E_VLM1SDML, getIdnr(), getMar_cy(),"HSN_CIVREC_STD", "M1");
           
         if((result = Functions.hour_f(getM1sdmh())) != 0)
           	Utils.message(result + Constants.E_HRM1SDM2, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", "" + getM1sdmh());
         else
        	 if(getM1sdmh() >= 0 && getM1sdmh() < 8)
                	Utils.message(100900 + Constants.E_HRM1SDMH, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", "" + getM1sdmh());

        		 
         if((result = Functions.yesno_f(getM1sdse())) != 0)
            	Utils.message(result + Constants.E_JNM1SDSE, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1sdse());
            
         if((result = Functions.date_f(getMar_cd(), getMar_cm(), getMar_cy())) != 0)
         	Utils.message(result + Constants.E_DAM1SDMY, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getMar_cd() + "-" + getMar_cm() + "-" + getMar_cy());
         
         // Check divorce date
         
         if(getM1sdse().equalsIgnoreCase("J")){
        	 if((result = Functions.date_f(getM1sdsd(), getM1sdsm(), getM1sdsy())) != 0)
        		 Utils.message(result + Constants.E_DAM1SDSY, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1sdsd() + "-" + getM1sdsm() + "-" + getM1sdsy());

        	 if((result = Functions.date_f(getM1sded(), getM1sdem(), getM1sdey())) != 0)
        		 Utils.message(result + Constants.E_DAM1SDEY, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1sded() + "-" + getM1sdem() + "-" + getM1sdey());
        	 
             if((result = Functions.vlslocation_f(getM1sdsl())) != 0)
                	Utils.message(result + Constants.E_VLM1SDSL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1");
        	 
        	 
         }

         if((result = Functions.age_f(getM1gray())) != 0)
            	Utils.message(result + Constants.E_AGM1GRAY, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", "" + getM1gray());
            
         if((result = Functions.age_f(getM1bray())) != 0)
         	Utils.message(result + Constants.E_AGM1BRAY, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", "" + getM1bray());
         
         if((result = Functions.gender_f(getM1rpgn())) != 0)
          	Utils.message(result + Constants.E_GNM1RPGN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1rpgn());
          

         // =============================================================================
         
         if((result = Functions.empty_f(getM1grfn())) != 0)
           	Utils.message(result + Constants.E_LEM1GRFN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1");
           
         if((result = Functions.empty_f(getM1groc())) != 0)
           	Utils.message(result + Constants.E_LEM1GROC, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1");
           
         if((result = Functions.empty_f(getM1grbl())) != 0)
            	Utils.message(result + Constants.E_LEM1GRBL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1");
            
         if((result = Functions.vlslocation_f(getM1grbl())) != 0)
            	Utils.message(result + Constants.E_VLM1GRBL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1grbl());
            
         if((result = Functions.empty_f(getM1grll())) != 0)
            	Utils.message(result + Constants.E_LEM1GRLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1");
            
         if((result = Functions.vlslocation_f(getM1grll())) != 0)
         	Utils.message(result + Constants.E_VLM1GRLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1grll());
         
         if((result = Functions.idem_f(getM1grll())) != 0)
         	Utils.message(result + Constants.E_IMM1GRLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1grll());
         
         if((result = Functions.threedouble_f(getM1grll())) != 0)
           	Utils.message(result + Constants.E_X3M1GRLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1grll());
           
         if((result = Functions.vlslocation_f(getM1grlf())) != 0)
            	Utils.message(result + Constants.E_VLM1GRLF, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1grlf());
            
         if((result = Functions.idem_f(getM1grlf())) != 0)
            	Utils.message(result + Constants.E_IMM1GRLF, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1grlf());
            
         // =============================================================================
         
         if((result = Functions.threedouble_f(getM1grlf())) != 0)
          	Utils.message(result + Constants.E_X3M1GRLF, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1grlf());
          
         if((result = Functions.vlslocation_f(getM1grlo())) != 0)
           	Utils.message(result + Constants.E_VLM1GRLO, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1grlo());
           
         if((result = Functions.idem_f(getM1grlo())) != 0)
            	Utils.message(result + Constants.E_IMM1GRLO, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1grlo());
            
         if((result = Functions.threedouble_f(getM1grlo())) != 0)
         	Utils.message(result + Constants.E_X3M1GRLO, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1grlo());
         
         if((result = Functions.civilstatusM_f(getM1grcs())) != 0)
          	Utils.message(result + Constants.E_CSM1GRCS, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1grcs());
          
         if((result = Functions.signature_f(getM1grsg())) != 0)
           	Utils.message(result + Constants.E_SGM1GRSG, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1grsg());
           
         if((result = Functions.empty_f(getM1broc())) != 0)
            	Utils.message(result + Constants.E_LEM1BROC, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1");
            
         if((result = Functions.empty_f(getM1brfn())) != 0)
            	Utils.message(result + Constants.E_LEM1BRFN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1");
            
         // =================================================================================
         
         if((result = Functions.empty_f(getM1brbl())) != 0)
           	Utils.message(result + Constants.E_LEM1BRBL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1");
           
         if((result = Functions.vlslocation_f(getM1brbl())) != 0)
           	Utils.message(result + Constants.E_VLM1BRBL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1");
           
         if((result = Functions.empty_f(getM1brll())) != 0)
           	Utils.message(result + Constants.E_LEM1BRLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1");
           
         if((result = Functions.vlslocation_f(getM1brll())) != 0)
            	Utils.message(result + Constants.E_VLM1BRLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1brll());
            
         if((result = Functions.idem_f(getM1brll())) != 0)
         	Utils.message(result + Constants.E_IMM1BRLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1brll());
         
         if((result = Functions.threedouble_f(getM1brll())) != 0)
          	Utils.message(result + Constants.E_X3M1BRLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1brll());
          
         if((result = Functions.vlslocation_f(getM1brlf())) != 0)
           	Utils.message(result + Constants.E_VLM1BRLF, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1brlf());
           
         if((result = Functions.idem_f(getM1brlf())) != 0)
            	Utils.message(result + Constants.E_IMM1BRLF, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1brlf());
            
         if((result = Functions.threedouble_f(getM1brlf())) != 0)
         	Utils.message(result + Constants.E_X3M1BRLF, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1brlf());
         
         if((result = Functions.vlslocation_f(getM1brlo())) != 0)
          	Utils.message(result + Constants.E_VLM1BRLO, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1brlo());
          
         if((result = Functions.idem_f(getM1brlo())) != 0)
          	Utils.message(result + Constants.E_IMM1BRLO, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1brlo());
          
         
         //===================================================================================

         
         if((result = Functions.threedouble_f(getM1brlo())) != 0)
            	Utils.message(result + Constants.E_X3M1BRLO, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1brlo());
            
         if((result = Functions.civilstatusM_f(getM1brcs())) != 0)
         	Utils.message(result + Constants.E_CSM1BRCS, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1brcs());
         
         if((result = Functions.signature_f(getM1brsg())) != 0)
          	Utils.message(result + Constants.E_SGM1BRSG, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1brsg());
          
         if((result = Functions.yesnoO_f(getM1gfca())) != 0)
           	Utils.message(result + Constants.E_JNM1GFCA, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1gfca());
           
         if((result = Functions.permission_f(getM1gfmp())) != 0)
            	Utils.message(result + Constants.E_JNM1GFMP, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1gfmp());
            
         if((result = Functions.empty_f(getM1gffn())) != 0)
           	Utils.message(result + Constants.E_LEM1GFFN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1");
           
         if((result = Functions.vlslocation_f(getM1gfll())) != 0)
            	Utils.message(result + Constants.E_VLM1GFLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1gfll());
            
         if((result = Functions.idem_f(getM1gfll())) != 0)
            	Utils.message(result + Constants.E_IMM1GFLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1gfll());
            
         //====================================================================================

         if((result = Functions.threedouble_f(getM1gfll())) != 0)
          	Utils.message(result + Constants.E_X3M1GFLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1gfll());
          
         if((result = Functions.vlslocation_f(getM1gfdl())) != 0)
           	Utils.message(result + Constants.E_VLM1GFDL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1gfll());
           
         if((result = Functions.idem_f(getM1gfdl())) != 0)
            	Utils.message(result + Constants.E_IMM1GFDL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1gfdl());
            
         if((result = Functions.threedouble_f(getM1gfdl())) != 0)
         	Utils.message(result + Constants.E_X3M1GFDL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1gfdl());
         
         if((result = Functions.signature_f(getM1gfsg())) != 0)
          	Utils.message(result + Constants.E_SGM1GFSG, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1gfsg());
          
         if((result = Functions.age_f(getM1gfay())) != 0)
           	Utils.message(result + Constants.E_AGM1GFAY, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", "" + getM1gfay());
           
         if((result = Functions.yesnoO_f(getM1gmca())) != 0)
            	Utils.message(result + Constants.E_JNM1GMCA, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1gmca());
            
         if((result = Functions.permission_f(getM1gmmp())) != 0)
         	Utils.message(result + Constants.E_JNM1GMMP, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1gmmp());
         
         //====================================================================================

         if((result = Functions.empty_f(getM1gmfn())) != 0)
           	Utils.message(result + Constants.E_LEM1GMFN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1");

         if((result = Functions.vlslocation_f(getM1gmll())) != 0)
            	Utils.message(result + Constants.E_VLM1GMLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1gmll());

         if((result = Functions.idem_f(getM1gmll())) != 0)
         	Utils.message(result + Constants.E_IMM1GMLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1gmll());

         if((result = Functions.threedouble_f(getM1gmll())) != 0)
          	Utils.message(result + Constants.E_X3M1GMLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1gmll());

         if((result = Functions.vlslocation_f(getM1gmdl())) != 0)
           	Utils.message(result + Constants.E_VLM1GMDL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1gmdl());

         if((result = Functions.idem_f(getM1gmdl())) != 0)
            	Utils.message(result + Constants.E_IMM1GMDL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1gmdl());

         if((result = Functions.threedouble_f(getM1gmdl())) != 0)
         	Utils.message(result + Constants.E_X3M1GMDL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1gmdl());

         if((result = Functions.signature_f(getM1gmsg())) != 0)
          	Utils.message(result + Constants.E_SGM1GMSG, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1gmsg());

         if((result = Functions.age_f(getM1gmay())) != 0)
           	Utils.message(result + Constants.E_AGM1GMAY, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", "" + getM1gmay());

         if((result = Functions.yesnoO_f(getM1bfca())) != 0)
           	Utils.message(result + Constants.E_JNM1BFCA, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1bfca());

         //====================================================================================

         if((result = Functions.empty_f(getM1bffn())) != 0)
          	Utils.message(result + Constants.E_LEM1BFFN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1");

         if((result = Functions.vlslocation_f(getM1bfll())) != 0)
           	Utils.message(result + Constants.E_VLM1BFLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1bfll());

         if((result = Functions.idem_f(getM1bfll())) != 0)
            	Utils.message(result + Constants.E_IMM1BFLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1bfll());

         if((result = Functions.threedouble_f(getM1bfll())) != 0)
         	Utils.message(result + Constants.E_X3M1BFLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1bfll());

         if((result = Functions.vlslocation_f(getM1bfdl())) != 0)
          	Utils.message(result + Constants.E_VLM1BFDL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1bfll());

         if((result = Functions.idem_f(getM1bfdl())) != 0)
           	Utils.message(result + Constants.E_IMM1BFDL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1bfdl());

         if((result = Functions.threedouble_f(getM1bfdl())) != 0)
            	Utils.message(result + Constants.E_X3M1BFDL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1bfdl());

         if((result = Functions.signature_f(getM1bfsg())) != 0)
            	Utils.message(result + Constants.E_SGM1BFSG, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1bfsg());

         
         //====================================================================================

         if((result = Functions.age_f(getM1bfay())) != 0)
         	Utils.message(result + Constants.E_AGM1BFAY, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", "" + getM1bfay());
         
         if((result = Functions.permission_f(getM1bfmp())) != 0)
          	Utils.message(result + Constants.E_JNM1BFMP, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1bfmp());


         if((result = Functions.yesnoO_f(getM1bmca())) != 0)
          	Utils.message(result + Constants.E_JNM1BMCA, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1bmca());

         if((result = Functions.empty_f(getM1bmfn())) != 0)
         	Utils.message(result + Constants.E_LEM1BMFN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1");

         if((result = Functions.vlslocation_f(getM1bmll())) != 0)
          	Utils.message(result + Constants.E_VLM1BMLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1bmll());

         if((result = Functions.idem_f(getM1bmll())) != 0)
           	Utils.message(result + Constants.E_IMM1BMLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1bmll());

         if((result = Functions.threedouble_f(getM1bmll())) != 0)
            	Utils.message(result + Constants.E_X3M1BMLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1bmll());

         if((result = Functions.vlslocation_f(getM1bmdl())) != 0)
         	Utils.message(result + Constants.E_VLM1BMDL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1bmdl());

         if((result = Functions.idem_f(getM1bmdl())) != 0)
          	Utils.message(result + Constants.E_IMM1BMDL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1bmdl());

         if((result = Functions.threedouble_f(getM1bmdl())) != 0)
          	Utils.message(result + Constants.E_X3M1BMDL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1bmdl());


         //====================================================================================

         if((result = Functions.threedouble_f(getM1bfdl())) != 0)
          	Utils.message(result + Constants.E_X3M1BFDL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1bfdl());

         if((result = Functions.signature_f(getM1bmsg())) != 0)
           	Utils.message(result + Constants.E_SGM1BMSG, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1bmsg());

         if((result = Functions.age_f(getM1bmay())) != 0)
            	Utils.message(result + Constants.E_AGM1BMAY, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", "" + getM1bmay());
         
         if((result = Functions.permission_f(getM1bmmp())) != 0)
          	Utils.message(result + Constants.E_JNM1BMMP, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1bmmp());

         if((result = Functions.yesno_f(getM1sdbb())) != 0)
         	Utils.message(result + Constants.E_JNM1SDBB, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1sdbb());

         if((result = Functions.yesno_f(getM1sddp())) != 0)
          	Utils.message(result + Constants.E_JNM1SDDP, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1sddp());

         if((result = Functions.yesno_f(getM1sdde())) != 0)
           	Utils.message(result + Constants.E_JNM1SDDE, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1sdde());

         if((result = Functions.yesno_f(getM1sdnm())) != 0)
            	Utils.message(result + Constants.E_JNM1SDNM, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1sdnm());

         if((result = Functions.yesno_f(getM1sdpn())) != 0)
         	Utils.message(result + Constants.E_JNM1SDPN, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1sdpn());

         if((result = Functions.yesno_f(getM1sdaf())) != 0)
          	Utils.message(result + Constants.E_JNM1SDAF, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1sdaf());

         if((result = Functions.yesno_f(getM1sdap())) != 0)
          	Utils.message(result + Constants.E_JNM1SDAP, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1sdap());

         //====================================================================================

         if((result = Functions.yesno_f(getM1sdpc())) != 0)
            	Utils.message(result + Constants.E_JNM1SDPC, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1sdpc());
           
         if((result = Functions.yesno_f(getM1sdpg())) != 0)
            	Utils.message(result + Constants.E_JNM1SDPG, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1sdpg());
          
         
         // Reference checks
         
         setM1sdml(Functions.location_r(getM1sdml(), Constants.E_LOM1SDML, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         setM1sdsl(Functions.location_r(getM1sdsl(), Constants.E_LOM1SDSL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         setM1sdel(Functions.location_r(getM1sdel(), Constants.E_LOM1SDEL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         
         setM1grbl(Functions.location_r(getM1grbl(), Constants.E_LOM1GRBL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         setM1grll(Functions.location_r(getM1grll(), Constants.E_LOM1GRLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         setM1grlo(Functions.location_r(getM1grlo(), Constants.E_LOM1GRLO, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         setM1grlf(Functions.location_r(getM1grlf(), Constants.E_LOM1GRLF, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         
         setM1brbl(Functions.location_r(getM1brbl(), Constants.E_LOM1BRBL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         setM1brll(Functions.location_r(getM1brll(), Constants.E_LOM1BRLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         setM1brlo(Functions.location_r(getM1brlo(), Constants.E_LOM1BRLO, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         setM1brlf(Functions.location_r(getM1brlf(), Constants.E_LOM1BRLF, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         
         setM1gfll(Functions.location_r(getM1gfll(), Constants.E_LOM1GFLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         setM1gfdl(Functions.location_r(getM1gfdl(), Constants.E_LOM1GFDL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         setM1bfll(Functions.location_r(getM1bfll(), Constants.E_LOM1BFLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         setM1bfdl(Functions.location_r(getM1bfdl(), Constants.E_LOM1BFDL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         
         setM1gmll(Functions.location_r(getM1gmll(), Constants.E_LOM1GMLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         setM1gmdl(Functions.location_r(getM1gmdl(), Constants.E_LOM1GMDL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         setM1bmll(Functions.location_r(getM1bmll(), Constants.E_LOM1BMLL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         setM1bmdl(Functions.location_r(getM1bmdl(), Constants.E_LOM1BMDL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         
         
         
         
         setM1groc(Functions.profession_r(getM1groc(), Constants.E_OCM1GROC, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         setM1broc(Functions.profession_r(getM1broc(), Constants.E_OCM1BROC, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         setM1gfoc(Functions.profession_r(getM1gfoc(), Constants.E_OCM1GFOC, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         setM1gmoc(Functions.profession_r(getM1gmoc(), Constants.E_OCM1GMOC, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         setM1bfoc(Functions.profession_r(getM1bfoc(), Constants.E_OCM1BFOC, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         setM1bmoc(Functions.profession_r(getM1bmoc(), Constants.E_OCM1BMOC, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1"));
         
         setM1grln(Functions.familyname_r(getM1grln(), Constants.E_LNM1GRLN, getIdnr(), 0, "HSN_CIVREC_STD", "M1"));
         setM1brln(Functions.familyname_r(getM1brln(), Constants.E_LNM1BRLN, getIdnr(), 0, "HSN_CIVREC_STD", "M1"));
         setM1gfln(Functions.familyname_r(getM1gfln(), Constants.E_LNM1GFLN, getIdnr(), 0, "HSN_CIVREC_STD", "M1"));
         setM1gmln(Functions.familyname_r(getM1gmln(), Constants.E_LNM1GMLN, getIdnr(), 0, "HSN_CIVREC_STD", "M1"));
         setM1bfln(Functions.familyname_r(getM1bfln(), Constants.E_LNM1BFLN, getIdnr(), 0, "HSN_CIVREC_STD", "M1"));
         setM1bmln(Functions.familyname_r(getM1bmln(), Constants.E_LNM1BMLN, getIdnr(), 0, "HSN_CIVREC_STD", "M1"));

         setM1grpf(Functions.familyname_r(getM1grpf(), Constants.E_LNM1GRPF, getIdnr(), 0, "HSN_CIVREC_STD", "M1"));
         setM1brpf(Functions.familyname_r(getM1brpf(), Constants.E_LNM1BRPF, getIdnr(), 0, "HSN_CIVREC_STD", "M1"));
         setM1gfpf(Functions.familyname_r(getM1gfpf(), Constants.E_LNM1GFPF, getIdnr(), 0, "HSN_CIVREC_STD", "M1"));
         setM1gmpf(Functions.familyname_r(getM1gmpf(), Constants.E_LNM1GMPF, getIdnr(), 0, "HSN_CIVREC_STD", "M1"));
         setM1bfpf(Functions.familyname_r(getM1bfpf(), Constants.E_LNM1BFPF, getIdnr(), 0, "HSN_CIVREC_STD", "M1"));
         setM1bmpf(Functions.familyname_r(getM1bmpf(), Constants.E_LNM1BMPF, getIdnr(), 0, "HSN_CIVREC_STD", "M1")); 

         setM1grfn(Functions.firstname_r(getM1grfn(), Constants.E_FNM1GRFN, getIdnr(), 0, "HSN_CIVREC_STD", "M1"));
         setM1brfn(Functions.firstname_r(getM1brfn(), Constants.E_FNM1BRFN, getIdnr(), 0, "HSN_CIVREC_STD", "M1"));
         setM1gffn(Functions.firstname_r(getM1gffn(), Constants.E_FNM1GFFN, getIdnr(), 0, "HSN_CIVREC_STD", "M1"));
         setM1gmfn(Functions.firstname_r(getM1gmfn(), Constants.E_FNM1GMFN, getIdnr(), 0, "HSN_CIVREC_STD", "M1"));
         setM1bffn(Functions.firstname_r(getM1bffn(), Constants.E_FNM1BFFN, getIdnr(), 0, "HSN_CIVREC_STD", "M1"));
         setM1bmfn(Functions.firstname_r(getM1bmfn(), Constants.E_FNM1BMFN, getIdnr(), 0, "HSN_CIVREC_STD", "M1"));

         
         // logic checks
         
         
         int marCount = Utils.dayCount(getMar_cd(), getMar_cm(), getMar_cy());
         if(getM1sdse().equalsIgnoreCase("J")){

            
             int devCount = Utils.dayCount(getM1sdsd(), getM1sdsm(), getM1sdsy()); 
             int desCount = Utils.dayCount(getM1sded(), getM1sdem(), getM1sdey());
        	 
             if(marCount > 0 && devCount > 0 && devCount < marCount)
            	 Utils.message(300500 + Constants.E_DLM1M1MS, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getMar_cd() + "-" +  getMar_cm() + "-" + getMar_cy() + " and " +
           			getM1sdsd() + "-" + getM1sdsm() + "-" + getM1sdsy());

             if(devCount > 0 && desCount > 0 && desCount < devCount)
            	 Utils.message(300500 + Constants.E_DLM1M1SE, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M1", getM1sdsd() + "-" + getM1sdsm() + "-" + getM1sdsy() + " and " +
            			 getM1sded() + "-" + getM1sdem() + "-" + getM1sdey());

         }
         

         
         int birthDayCount = Utils.dayCount(huwknd.getGebknd().getGebdag(), huwknd.getGebknd().getGebmnd(), huwknd.getGebknd().getGebjr());
         
         if(marCount > 0 && birthDayCount > 0){

        	 int dif = Utils.dayCount(getMar_cd(), getMar_cm(),getMar_cy()) - Utils.dayCount(huwknd.getGebknd().getGebdag(), huwknd.getGebknd().getGebmnd(), huwknd.getGebknd().getGebjr());
        	 dif = dif / 365;

        	 if(getM1rpgn() != null){

        		 int age  = getM1rpgn().equalsIgnoreCase("M") ? getM1gray() : getM1bray();
        		 int agep = getM1rpgn().equalsIgnoreCase("M") ? getM1bray() : getM1gray();


        		 String partnerAge ="";
        		 if(Math.abs(dif - agep) < 1)
        			 partnerAge = "(leeftijd partner: " + agep + ")";

        		 if(Math.abs(dif - age) > 1){

        			 Utils.message(300700 + Constants.E_ALM1RPAY, getIdnr(), getMar_cy(),"HSN_CIVREC_STD", "M1", huwknd.getGebknd().getGebdag() + "-" + huwknd.getGebknd().getGebmnd() + "-" + huwknd.getGebknd().getGebjr() + 
        					 "  " + getMar_cd() + "-" + getMar_cm() + "-" + getMar_cy() + "  " + age + " " + partnerAge);
        		 }
        		 
                 if(!getM1rpgn().equalsIgnoreCase(huwknd.getGebknd().getGebsex()))
          	      	Utils.message(300800 + Constants.E_GLM1RPGN, getIdnr(), getMar_cy(),"HSN_CIVREC_STD", "M1", huwknd.getGebknd().getGebsex());
               
        	 }
         }


         if(marCount > 0 && birthDayCount > 0 && marCount < birthDayCount)
           	 Utils.message(300500 + Constants.E_DLB1M1BM, getIdnr(), getMar_cy(),"HSN_CIVREC_STD", "M1", huwknd.getGebknd().getGebdag() + "-" + huwknd.getGebknd().getGebmnd() + "-" + huwknd.getGebknd().getGebjr() + " and " +
           			getMar_cd() + "-" + getMar_cm() + "-" + getMar_cy());
	 
         
 	}
  
     public void truncate(){
      	
      	String field = getM1sdml();
     	int allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1SDML",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1sdml(field);
     	}
     	
     	field = getM1sdsl();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1SDSL",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1sdsl(field);
     	}
     	
     	field = getM1sdel();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1SDEL",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1sdel(field);
     	}
     	
     	
     	// Groom
     	
     	field = getM1grln();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GRLN",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1grln(field);
     	}
     	
     	field = getM1grpf();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GRPF",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1grpf(field);
     	}
     	
     	field = getM1grfn();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GRFN",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1grfn(field);
     	}
     	
     	field = getM1grtt();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GRTT",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1grtt(field);
     	}
     	
     	field = getM1grpa();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GRPA",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1grpa(field);
     	}
     	
     	field = getM1groc();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GROC",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1groc(field);
     	}
     	
     	field = getM1grbl();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GRBL",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1grbl(field);
     	}
     	
     	field = getM1grll();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GRLL",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1grll(field);
     	}
     	
     	field = getM1grlf();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GRLF",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1grlf(field);
     	}
     	
     	field = getM1grlo();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GRLO",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1grlo(field);
     	}
     	
     	field = getM1grpl();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GRPL",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1grpl(field);
     	}
     	
     	field = getM1grql();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GRQL",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1grql(field);
     	}
     	
     	// Bride
     	
     	field = getM1brln();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BRLN",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1brln(field);
     	}
     	
     	field = getM1brpf();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BRPF",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1brpf(field);
     	}
     	
     	field = getM1brfn();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BRFN",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1brfn(field);
     	}
     	
     	field = getM1brtt();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BRTT",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1brtt(field);
     	}
     	
     	field = getM1brpa();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BRPA",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1brpa(field);
     	}
     	
     	field = getM1broc();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BROC",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1broc(field);
     	}
     	
     	field = getM1brbl();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BRBL",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1brbl(field);
     	}
     	
     	field = getM1brll();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BRLL",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1brll(field);
     	}
     	
     	field = getM1brlf();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BRLF",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1brlf(field);
     	}
     	
     	field = getM1brlo();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BRLO",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1brlo(field);
     	}
     	
     	field = getM1brpl();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BRPL",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1brpl(field);
     	}
     	
     	field = getM1brql();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BRQL",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1brql(field);
     	}
     	

     	// Groom Father
     	
     	field = getM1gfln();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GFLN",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1gfln(field);
     	}
     	
     	field = getM1gfpf();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GFPF",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1gfpf(field);
     	}
     	
     	field = getM1gffn();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GFFN",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1gffn(field);
     	}
     	
     	field = getM1gftt();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GFTT",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1gftt(field);
     	}
     	
     	field = getM1gfpa();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GFPA",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1gfpa(field);
     	}
     	
     	field = getM1gfoc();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GFOC",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1gfoc(field);
     	}
     	
     	
     	field = getM1gfll();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GFLL",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1gfll(field);
     	}
     	
     	field = getM1gfdl();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GFDL",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1gfdl(field);
     	}
     	
     	
     	// Groom Mother
     	
     	field = getM1gmln();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GMLN",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1gmln(field);
     	}
     	
     	field = getM1gmpf();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GMPF",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1gmpf(field);
     	}
     	
     	field = getM1gmfn();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GMFN",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1gmfn(field);
     	}
     	
     	field = getM1gmtt();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GMTT",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1gmtt(field);
     	}
     	
     	field = getM1gmpa();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GMPA",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1gmpa(field);
     	}
     	
     	field = getM1gmoc();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GMOC",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1gmoc(field);
     	}
     	
     	
     	field = getM1gmll();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GMLL",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1gmll(field);
     	}
     	
     	field = getM1gmdl();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1GMDL",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1gmdl(field);
     	}
     	
     	// Bride Father
     	
     	field = getM1bfln();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BFLN",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1bfln(field);
     	}
     	
     	field = getM1bfpf();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BFPF",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1bfpf(field);
     	}
     	
     	field = getM1bffn();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BFFN",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1bffn(field);
     	}
     	
     	field = getM1bftt();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BFTT",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1bftt(field);
     	}
     	
     	field = getM1bfpa();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BFPA",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1bfpa(field);
     	}
     	
     	field = getM1bfoc();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BFOC",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1bfoc(field);
     	}
     	
     	
     	field = getM1bfll();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BFLL",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1bfll(field);
     	}
     	
     	field = getM1bfdl();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BFDL",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1bfdl(field);
     	}
     	
     	
     	// Bride Mother
     	
     	field = getM1bmln();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BMLN",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1bmln(field);
     	}
     	
     	field = getM1bmpf();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BMPF",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1bmpf(field);
     	}
     	
     	field = getM1bmfn();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BMFN",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1bmfn(field);
     	}
     	
     	field = getM1bmtt();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BMTT",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1bmtt(field);
     	}
     	
     	field = getM1bmpa();
     	allowedSize = Const.Smallstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BMPA",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1bmpa(field);
     	}
     	
     	field = getM1bmoc();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BMOC",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1bmoc(field);
     	}
     	
     	
     	field = getM1bmll();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BMLL",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1bmll(field);
     	}
     	
     	field = getM1bmdl();
     	allowedSize = Const.Bigstring;
     	if(field != null && field.length() > allowedSize){
     		Utils.message(1000000, getIdnr(), getMar_cy(),"HSN_CIVREC_STD",  "M1BMDL",  (new Integer(field.length()).toString()));
     		field = field.substring(0, allowedSize);
     		setM1bmdl(field);
     	}
     	
     	
     	

     	
     	
     	
     	
     	
     	
     	}

     
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public int getM1sdcn() {
		return m1sdcn;
	}
	public void setM1sdcn(int m1sdcn) {
		this.m1sdcn = m1sdcn;
	}
	public String getM1sdml() {
		return m1sdml;
	}
	public void setM1sdml(String m1sdml) {
		this.m1sdml = m1sdml;
	}
	public int getM1sdmh() {
		return m1sdmh;
	}
	public void setM1sdmh(int m1sdmh) {
		this.m1sdmh = m1sdmh;
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
	public String getM1sdse() {
		return m1sdse;
	}
	public void setM1sdse(String m1sdse) {
		this.m1sdse = m1sdse;
	}
	public int getM1sdsd() {
		return m1sdsd;
	}
	public void setM1sdsd(int m1sdsd) {
		this.m1sdsd = m1sdsd;
	}
	public int getM1sdsm() {
		return m1sdsm;
	}
	public void setM1sdsm(int m1sdsm) {
		this.m1sdsm = m1sdsm;
	}
	public int getM1sdsy() {
		return m1sdsy;
	}
	public void setM1sdsy(int m1sdsy) {
		this.m1sdsy = m1sdsy;
	}
	public String getM1sdsl() {
		return m1sdsl;
	}
	public void setM1sdsl(String m1sdsl) {
		this.m1sdsl = m1sdsl;
	}
	public int getM1sded() {
		return m1sded;
	}
	public void setM1sded(int m1sded) {
		this.m1sded = m1sded;
	}
	public int getM1sdem() {
		return m1sdem;
	}
	public void setM1sdem(int m1sdem) {
		this.m1sdem = m1sdem;
	}
	public int getM1sdey() {
		return m1sdey;
	}
	public void setM1sdey(int m1sdey) {
		this.m1sdey = m1sdey;
	}
	public String getM1sdel() {
		return m1sdel;
	}
	public void setM1sdel(String m1sdel) {
		this.m1sdel = m1sdel;
	}
	public int getM1gray() {
		return m1gray;
	}
	public void setM1gray(int m1gray) {
		this.m1gray = m1gray;
	}
	public int getM1bray() {
		return m1bray;
	}
	public void setM1bray(int m1bray) {
		this.m1bray = m1bray;
	}
	public String getM1rpgn() {
		return m1rpgn;
	}
	public void setM1rpgn(String m1rpgn) {
		this.m1rpgn = m1rpgn;
	}
	public String getM1grln() {
		return m1grln;
	}
	public void setM1grln(String m1grln) {
		this.m1grln = m1grln;
	}
	public String getM1grpf() {
		return m1grpf;
	}
	public void setM1grpf(String m1grpf) {
		this.m1grpf = m1grpf;
	}
	public String getM1grfn() {
		return m1grfn;
	}
	public void setM1grfn(String m1grfn) {
		this.m1grfn = m1grfn;
	}
	public String getM1grtt() {
		return m1grtt;
	}
	public void setM1grtt(String m1grtt) {
		this.m1grtt = m1grtt;
	}
	public String getM1grpa() {
		return m1grpa;
	}
	public void setM1grpa(String m1grpa) {
		this.m1grpa = m1grpa;
	}
	public String getM1groc() {
		return m1groc;
	}
	public void setM1groc(String m1groc) {
		this.m1groc = m1groc;
	}
	public String getM1grbl() {
		return m1grbl;
	}
	public void setM1grbl(String m1grbl) {
		this.m1grbl = m1grbl;
	}
	public String getM1grll() {
		return m1grll;
	}
	public void setM1grll(String m1grll) {
		this.m1grll = m1grll;
	}
	public String getM1grpl() {
		return m1grpl;
	}
	public void setM1grpl(String m1grpl) {
		this.m1grpl = m1grpl;
	}
	public String getM1grql() {
		return m1grql;
	}
	public void setM1grql(String m1grql) {
		this.m1grql = m1grql;
	}
	public String getM1grcs() {
		return m1grcs;
	}
	public void setM1grcs(String m1grcs) {
		this.m1grcs = m1grcs;
	}
	public String getM1grsg() {
		return m1grsg;
	}
	public void setM1grsg(String m1grsg) {
		this.m1grsg = m1grsg;
	}
	public String getM1brln() {
		return m1brln;
	}
	public void setM1brln(String m1brln) {
		this.m1brln = m1brln;
	}
	public String getM1brpf() {
		return m1brpf;
	}
	public void setM1brpf(String m1brpf) {
		this.m1brpf = m1brpf;
	}
	public String getM1brfn() {
		return m1brfn;
	}
	public void setM1brfn(String m1brfn) {
		this.m1brfn = m1brfn;
	}
	public String getM1brtt() {
		return m1brtt;
	}
	public void setM1brtt(String m1brtt) {
		this.m1brtt = m1brtt;
	}
	public String getM1brpa() {
		return m1brpa;
	}
	public void setM1brpa(String m1brpa) {
		this.m1brpa = m1brpa;
	}
	public String getM1broc() {
		return m1broc;
	}
	public void setM1broc(String m1broc) {
		this.m1broc = m1broc;
	}
	public String getM1brbl() {
		return m1brbl;
	}
	public void setM1brbl(String m1brbl) {
		this.m1brbl = m1brbl;
	}
	public String getM1brll() {
		return m1brll;
	}
	public void setM1brll(String m1brll) {
		this.m1brll = m1brll;
	}
	public String getM1brpl() {
		return m1brpl;
	}
	public void setM1brpl(String m1brpl) {
		this.m1brpl = m1brpl;
	}
	public String getM1brql() {
		return m1brql;
	}
	public void setM1brql(String m1brql) {
		this.m1brql = m1brql;
	}
	public String getM1brcs() {
		return m1brcs;
	}
	public void setM1brcs(String m1brcs) {
		this.m1brcs = m1brcs;
	}
	public String getM1brsg() {
		return m1brsg;
	}
	public void setM1brsg(String m1brsg) {
		this.m1brsg = m1brsg;
	}
	public String getM1gfca() {
		return m1gfca;
	}
	public void setM1gfca(String m1gfca) {
		this.m1gfca = m1gfca;
	}
	public String getM1gfmp() {
		return m1gfmp;
	}
	public void setM1gfmp(String m1gfmp) {
		this.m1gfmp = m1gfmp;
	}
	public String getM1gfln() {
		return m1gfln;
	}
	public void setM1gfln(String m1gfln) {
		this.m1gfln = m1gfln;
	}
	public String getM1gfpf() {
		return m1gfpf;
	}
	public void setM1gfpf(String m1gfpf) {
		this.m1gfpf = m1gfpf;
	}
	public String getM1gffn() {
		return m1gffn;
	}
	public void setM1gffn(String m1gffn) {
		this.m1gffn = m1gffn;
	}
	public String getM1gftt() {
		return m1gftt;
	}
	public void setM1gftt(String m1gftt) {
		this.m1gftt = m1gftt;
	}
	public String getM1gfpa() {
		return m1gfpa;
	}
	public void setM1gfpa(String m1gfpa) {
		this.m1gfpa = m1gfpa;
	}
	public String getM1gfoc() {
		return m1gfoc;
	}
	public void setM1gfoc(String m1gfoc) {
		this.m1gfoc = m1gfoc;
	}
	public String getM1gfll() {
		return m1gfll;
	}
	public void setM1gfll(String m1gfll) {
		this.m1gfll = m1gfll;
	}
	public String getM1gfdl() {
		return m1gfdl;
	}
	public void setM1gfdl(String m1gfdl) {
		this.m1gfdl = m1gfdl;
	}
	public String getM1gfsg() {
		return m1gfsg;
	}
	public void setM1gfsg(String m1gfsg) {
		this.m1gfsg = m1gfsg;
	}
	public int getM1gfay() {
		return m1gfay;
	}
	public void setM1gfay(int m1gfay) {
		this.m1gfay = m1gfay;
	}
	public String getM1gmca() {
		return m1gmca;
	}
	public void setM1gmca(String m1gmca) {
		this.m1gmca = m1gmca;
	}
	public String getM1gmmp() {
		return m1gmmp;
	}
	public void setM1gmmp(String m1gmmp) {
		this.m1gmmp = m1gmmp;
	}
	public String getM1gmln() {
		return m1gmln;
	}
	public void setM1gmln(String m1gmln) {
		this.m1gmln = m1gmln;
	}
	public String getM1gmpf() {
		return m1gmpf;
	}
	public void setM1gmpf(String m1gmpf) {
		this.m1gmpf = m1gmpf;
	}
	public String getM1gmfn() {
		return m1gmfn;
	}
	public void setM1gmfn(String m1gmfn) {
		this.m1gmfn = m1gmfn;
	}
	public String getM1gmtt() {
		return m1gmtt;
	}
	public void setM1gmtt(String m1gmtt) {
		this.m1gmtt = m1gmtt;
	}
	public String getM1gmpa() {
		return m1gmpa;
	}
	public void setM1gmpa(String m1gmpa) {
		this.m1gmpa = m1gmpa;
	}
	public String getM1gmoc() {
		return m1gmoc;
	}
	public void setM1gmoc(String m1gmoc) {
		this.m1gmoc = m1gmoc;
	}
	public String getM1gmll() {
		return m1gmll;
	}
	public void setM1gmll(String m1gmll) {
		this.m1gmll = m1gmll;
	}
	public String getM1gmdl() {
		return m1gmdl;
	}
	public void setM1gmdl(String m1gmdl) {
		this.m1gmdl = m1gmdl;
	}
	public String getM1gmsg() {
		return m1gmsg;
	}
	public void setM1gmsg(String m1gmsg) {
		this.m1gmsg = m1gmsg;
	}
	public int getM1gmay() {
		return m1gmay;
	}
	public void setM1gmay(int m1gmay) {
		this.m1gmay = m1gmay;
	}
	public String getM1bfca() {
		return m1bfca;
	}
	public void setM1bfca(String m1bfca) {
		this.m1bfca = m1bfca;
	}
	public String getM1bfmp() {
		return m1bfmp;
	}
	public void setM1bfmp(String m1bfmp) {
		this.m1bfmp = m1bfmp;
	}
	public String getM1bfln() {
		return m1bfln;
	}
	public void setM1bfln(String m1bfln) {
		this.m1bfln = m1bfln;
	}
	public String getM1bfpf() {
		return m1bfpf;
	}
	public void setM1bfpf(String m1bfpf) {
		this.m1bfpf = m1bfpf;
	}
	public String getM1bffn() {
		return m1bffn;
	}
	public void setM1bffn(String m1bffn) {
		this.m1bffn = m1bffn;
	}
	public String getM1bftt() {
		return m1bftt;
	}
	public void setM1bftt(String m1bftt) {
		this.m1bftt = m1bftt;
	}
	public String getM1bfpa() {
		return m1bfpa;
	}
	public void setM1bfpa(String m1bfpa) {
		this.m1bfpa = m1bfpa;
	}
	public String getM1bfoc() {
		return m1bfoc;
	}
	public void setM1bfoc(String m1bfoc) {
		this.m1bfoc = m1bfoc;
	}
	public String getM1bfll() {
		return m1bfll;
	}
	public void setM1bfll(String m1bfll) {
		this.m1bfll = m1bfll;
	}
	public String getM1bfdl() {
		return m1bfdl;
	}
	public void setM1bfdl(String m1bfdl) {
		this.m1bfdl = m1bfdl;
	}
	public String getM1bfsg() {
		return m1bfsg;
	}
	public void setM1bfsg(String m1bfsg) {
		this.m1bfsg = m1bfsg;
	}
	public int getM1bfay() {
		return m1bfay;
	}
	public void setM1bfay(int m1bfay) {
		this.m1bfay = m1bfay;
	}
	public String getM1bmca() {
		return m1bmca;
	}
	public void setM1bmca(String m1bmca) {
		this.m1bmca = m1bmca;
	}
	public String getM1bmmp() {
		return m1bmmp;
	}
	public void setM1bmmp(String m1bmmp) {
		this.m1bmmp = m1bmmp;
	}
	public String getM1bmln() {
		return m1bmln;
	}
	public void setM1bmln(String m1bmln) {
		this.m1bmln = m1bmln;
	}
	public String getM1bmpf() {
		return m1bmpf;
	}
	public void setM1bmpf(String m1bmpf) {
		this.m1bmpf = m1bmpf;
	}
	public String getM1bmfn() {
		return m1bmfn;
	}
	public void setM1bmfn(String m1bmfn) {
		this.m1bmfn = m1bmfn;
	}
	public String getM1bmtt() {
		return m1bmtt;
	}
	public void setM1bmtt(String m1bmtt) {
		this.m1bmtt = m1bmtt;
	}
	public String getM1bmpa() {
		return m1bmpa;
	}
	public void setM1bmpa(String m1bmpa) {
		this.m1bmpa = m1bmpa;
	}
	public String getM1bmoc() {
		return m1bmoc;
	}
	public void setM1bmoc(String m1bmoc) {
		this.m1bmoc = m1bmoc;
	}
	public String getM1bmll() {
		return m1bmll;
	}
	public void setM1bmll(String m1bmll) {
		this.m1bmll = m1bmll;
	}
	public String getM1bmdl() {
		return m1bmdl;
	}
	public void setM1bmdl(String m1bmdl) {
		this.m1bmdl = m1bmdl;
	}
	public String getM1bmsg() {
		return m1bmsg;
	}
	public void setM1bmsg(String m1bmsg) {
		this.m1bmsg = m1bmsg;
	}
	public int getM1bmay() {
		return m1bmay;
	}
	public void setM1bmay(int m1bmay) {
		this.m1bmay = m1bmay;
	}
	public String getM1sdbb() {
		return m1sdbb;
	}
	public void setM1sdbb(String m1sdbb) {
		this.m1sdbb = m1sdbb;
	}
	public String getM1sddp() {
		return m1sddp;
	}
	public void setM1sddp(String m1sddp) {
		this.m1sddp = m1sddp;
	}
	public String getM1sdde() {
		return m1sdde;
	}
	public void setM1sdde(String m1sdde) {
		this.m1sdde = m1sdde;
	}
	public String getM1sdnm() {
		return m1sdnm;
	}
	public void setM1sdnm(String m1sdnm) {
		this.m1sdnm = m1sdnm;
	}
	public String getM1sdpn() {
		return m1sdpn;
	}
	public void setM1sdpn(String m1sdpn) {
		this.m1sdpn = m1sdpn;
	}
	public String getM1sdaf() {
		return m1sdaf;
	}
	public void setM1sdaf(String m1sdaf) {
		this.m1sdaf = m1sdaf;
	}
	public String getM1sdap() {
		return m1sdap;
	}
	public void setM1sdap(String m1sdap) {
		this.m1sdap = m1sdap;
	}
	public String getM1sdpc() {
		return m1sdpc;
	}
	public void setM1sdpc(String m1sdpc) {
		this.m1sdpc = m1sdpc;
	}
	public String getM1sdpg() {
		return m1sdpg;
	}
	public void setM1sdpg(String m1sdpg) {
		this.m1sdpg = m1sdpg;
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


	public String getM1grlf() {
		return m1grlf;
	}


	public void setM1grlf(String m1grlf) {
		this.m1grlf = m1grlf;
	}


	public String getM1grlo() {
		return m1grlo;
	}


	public void setM1grlo(String m1grlo) {
		this.m1grlo = m1grlo;
	}


	public String getM1brlf() {
		return m1brlf;
	}


	public void setM1brlf(String m1brlf) {
		this.m1brlf = m1brlf;
	}


	public String getM1brlo() {
		return m1brlo;
	}


	public void setM1brlo(String m1brlo) {
		this.m1brlo = m1brlo;
	}
     
     
}
