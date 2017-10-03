package nl.iisg.ids05;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import nl.iisg.hsncommon.Common1;
import nl.iisg.idscontext.ContextElement;
import nl.iisg.idscontext.Contxt;

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

     
     
     @Transient                  private           B1   b1   = null;
     @Transient                  private ArrayList<M2>  m2L  = new ArrayList<M2>();
     @Transient                  private ArrayList<M3>  m3L  = new ArrayList<M3>();
     @Transient                  private ArrayList<M4>  m4L  = new ArrayList<M4>();
     @Transient                  private ArrayList<M5>  m5L  = new ArrayList<M5>();
     @Transient                  private           M6   m6   = null;
     
     
     
     public void convert(EntityManager em){   	
    	 
    	 int uniquifier = getMar_cy() * 100 * 1000 + (getMar_cm() * 1000) ; // The Id_I will be like 184906xxx
    	 
    	 int Id_I_GR = 11 + uniquifier; 
    	 int Id_I_BR = 12 + uniquifier;
    	 
    	 
    	 // Get context element for marriage location
    	 
    	 ContextElement ceMarriage = null;
    	 if(getM1sdml() != null)
    		 ceMarriage = Contxt.get2(getM1sdml());
    	 
    	 // Groom

		 Utils.addIndiv(em, getIdnr(), Id_I_GR, "MC M1", "SEX", "Male", "Missing", "Time_invariant", 0, 0, 0);
		 Utils.addIndiv(em, getIdnr(), Id_I_GR, "MC M1", "MARRIAGE_DATE", null, "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy(), getM1sdmh(), 0);
		 

		 if(ceMarriage != null){
			 Utils.addIndivAndContext(null, ceMarriage, em, getIdnr(), Id_I_GR, "MC M1", "MARRIAGE_LOCATION", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 Utils.addIndivContextAndContextCertificate(getMar_cy(), getM1sdcn(), ceMarriage, em, getIdnr(), Id_I_GR, "MC M1", "Groom", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
		 }

		 if(getM1grln() != null && getM1grln().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_GR, "MC M1", "LAST_NAME", getM1grln(), "Missing", "Time_invariant", 0, 0, 0);
		 if(getM1grpf() != null && getM1grpf().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_GR, "MC M1", "PREFIX_LAST_NAME", getM1grpf(), "Missing", "Time_invariant", 0, 0, 0);
		 if(getM1grfn() != null && getM1grfn().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_GR, "MC M1", "FIRST_NAME", getM1grfn(), "Missing", "Time_invariant", 0, 0, 0);
		 if(getM1groc() != null && getM1groc().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_GR, "MC M1", "OCCUPATION_STANDARD", getM1groc(), "Declared", "", getMar_cd(), getMar_cm(), getMar_cy());
		 if(getM1grsg() != null && getM1grsg().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_GR, "MC M1", "SIGNATURE", Utils.signature(getM1grsg()), "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
		 if(getM1gray() > 0){
			 Utils.addIndiv(em, getIdnr(), Id_I_GR, "MC M1", "AGE_YEARS", (new Integer(getM1gray())).toString(), "Reported", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 if(Utils.dateIsValid(getMar_cd(), getMar_cm(), getMar_cy()) == 0){
				 int[] a = Utils.birthRange(getM1gray(), getMar_cd(), getMar_cm(), getMar_cy());
				 Utils.addIndiv(em, getIdnr(), Id_I_GR, "MC M1", "BIRTH_DATE", null, "Declared", "Age_based", a[0], a[1], a[2], a[3], a[4], a[5]);
			 }
		 }
		 else
			Utils.addIndiv(em, getIdnr(), Id_I_GR, "MC M1", "BIRTH_DATE", null, "Declared", "Age_based", 1, 1, getMar_cy() - 100, 1, 1,   getMar_cy() - 15);

		 
		 if(getM1rpgn().equalsIgnoreCase("M"))
			 Utils.addIndiv(em, getIdnr(), Id_I_GR, "MC M1", "HSN_RESEARCH_PERSON", "HSN RP", "Missing", "Time_invariant", 0, 0, 0);

		 
    	 ContextElement ceBirth = null;
    	 if(getM1grbl() != null)
    		 ceBirth = Contxt.get2(getM1grbl());
    	 if(ceBirth != null)	 
		     Utils.addIndivAndContext(null, ceBirth, em, getIdnr(), Id_I_GR, "MC M1", "BIRTH_LOCATION", "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());

		 
		 
		 if(getM1grcs() != null && getM1grcs().trim().length() > 0){
			 
			 String cs = "Unknown";
			 if(getM1grcs().equalsIgnoreCase("1"))
				 cs = "Unmarried";
			 else
				 if(getM1grcs().equalsIgnoreCase("2"))
					 cs = "Widowed";
				 else
					 if(getM1grcs().equalsIgnoreCase("3"))
						 cs = "Divorced";

			 
			 String date = Common1.dateFromDayCount(Common1.dayCount(getMar_cd(), getMar_cm(), getMar_cy()) - 1);
			 
			 Utils.addIndiv(em, getIdnr(), Id_I_GR, "MC M1", "CIVIL_STATUS", cs, "Declared", "Exact",
						new Integer(date.substring(0, 2)), new Integer(date.substring(3, 5)), new Integer(date.substring(6, 10)));
			 //Utils.addIndiv(em, getIdnr(), Id_I_GR, "MC M1", "CIVIL_STATUS", cs, "Declared", "Exact",
			 //			0, 0, 0, new Integer(date.substring(0, 2)), new Integer(date.substring(3, 5)), new Integer(date.substring(6, 10)));
			 //Utils.addIndiv(em, getIdnr(), Id_I_GR, "MC M1", "CIVIL_STATUS", "Married", "Declared", "Exact",
			 //		 getMar_cd(), getMar_cm(), getMar_cy(), 0, 0, 0);

		 }
		 

		 
		 if(getM1sdse().equalsIgnoreCase("J")){
			 
			 if(getM1sdsy() > 0){
				 Utils.addIndiv(em, getIdnr(), Id_I_GR, "MC M1", "DIVORCE_DATE", null, "Reported", "Exact", getM1sdsd(),  getM1sdsm(), getM1sdsy());
			 }
			 
	    	 ContextElement ceDivorce = null;
	    	 if(getM1sdsl() != null)
	    		 ceDivorce = Contxt.get2(getM1sdsl());
	    	 
			 if(ceDivorce != null){
				 Utils.addIndivAndContext(null, ceDivorce, em, getIdnr(), Id_I_GR, "MC M1", "DIVORCE_LOCATION", "Reported", "Exact", getM1sdsd(),  getM1sdsm(), getM1sdsy());
			 }
		 }
		 
		 ContextElement ce = null;
    	 if(getM1grll() != null){
    		 ce = Contxt.get2(getM1grll());
    		 if(ce != null)
    			 Utils.addIndivContextAndContext(null, ce, em, getIdnr(), Id_I_GR, "MC M1", "", "Event", "Exact",  getMar_cd(), getMar_cm(), getMar_cy());
    	 }
    	 
    	 // Bride
    	 
		 Utils.addIndiv(em, getIdnr(), Id_I_BR, "MC M1", "SEX", "Female", "Missing", "Time_invariant", 0, 0, 0);
		 Utils.addIndiv(em, getIdnr(), Id_I_BR, "MC M1", "MARRIAGE_DATE", null, "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());

		 if(ceMarriage != null){
			 Utils.addIndivAndContext(null, ceMarriage, em, getIdnr(), Id_I_BR, "MC M1", "MARRIAGE_LOCATION", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 Utils.addIndivContextAndContextCertificate(getMar_cy(), getM1sdcn(), ceMarriage, em, getIdnr(), Id_I_BR, "MC M1", "Bride", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
		 }
		 

		 if(getM1brln() != null && getM1brln().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_BR, "MC M1", "LAST_NAME", getM1brln(), "Missing", "Time_invariant", 0, 0, 0);
		 if(getM1brpf() != null && getM1brpf().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_BR, "MC M1", "PREFIX_LAST_NAME", getM1brpf(), "Missing", "Time_invariant", 0, 0, 0);
		 if(getM1brfn() != null && getM1brfn().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_BR, "MC M1", "FIRST_NAME", getM1brfn(), "Missing", "Time_invariant", 0, 0, 0);
		 if(getM1broc() != null && getM1broc().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_BR, "MC M1", "OCCUPATION_STANDARD", getM1broc(), "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
		 if(getM1brsg() != null && getM1brsg().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_BR, "MC M1", "SIGNATURE", Utils.signature(getM1brsg()), "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
		 if(getM1bray() > 0){
			 Utils.addIndiv(em, getIdnr(), Id_I_BR, "MC M1", "AGE_YEARS", (new Integer(getM1bray())).toString(), "Reported", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 if(Utils.dateIsValid(getMar_cd(), getMar_cm(), getMar_cy()) == 0){
				 int[] a = Utils.birthRange(getM1bray(), getMar_cd(), getMar_cm(), getMar_cy());
				 Utils.addIndiv(em, getIdnr(), Id_I_BR, "MC M1", "BIRTH_DATE", null, "Declared", "Age_based", a[0], a[1], a[2], a[3], a[4], a[5]);
			 }
		 }
		 else
			Utils.addIndiv(em, getIdnr(), Id_I_BR, "MC M1", "BIRTH_DATE", null, "Declared", "Age_based", 1, 1, getMar_cy() - 100, 1, 1,   getMar_cy() - 15);

		 
		 if(getM1rpgn().equalsIgnoreCase("V"))
			 Utils.addIndiv(em, getIdnr(), Id_I_BR, "MC M1", "HSN_RESEARCH_PERSON", "HSN RP", "Missing", "Time_invariant", 0, 0, 0);

		 
		 if(getM1brcs() != null && getM1brcs().trim().length() > 0){
			 
			 String cs = "Unknown";
			 if(getM1brcs().equalsIgnoreCase("1"))
				 cs = "Unmarried";
			 else
				 if(getM1brcs().equalsIgnoreCase("2"))
					 cs = "Widowed";
				 else
					 if(getM1brcs().equalsIgnoreCase("3"))
						 cs = "Divorced";

			 
			 String date = Common1.dateFromDayCount(Common1.dayCount(getMar_cd(), getMar_cm(), getMar_cy()) - 1);
			 Utils.addIndiv(em, getIdnr(), Id_I_BR, "MC M1", "CIVIL_STATUS", cs, "Declared", "Exact", 
						new Integer(date.substring(0, 2)), new Integer(date.substring(3, 5)), new Integer(date.substring(6, 10)));
			 //Utils.addIndiv(em, getIdnr(), Id_I_BR, "MC M1", "CIVIL_STATUS", cs, "Declared", "Exact", 
			 //			0, 0, 0, new Integer(date.substring(0, 2)), new Integer(date.substring(3, 5)), new Integer(date.substring(6, 10)));
			 //Utils.addIndiv(em, getIdnr(), Id_I_BR, "MC M1", "CIVIL_STATUS", "Married", "Declared", "Exact",
			 //		 getMar_cd(), getMar_cm(), getMar_cy(),0, 0, 0);
					 
		 }
		 
    	 if(getM1brll() != null && getM1brll().trim().length() > 0){
    		 ce = Contxt.get2(getM1brll());
    		 if(ce != null)
    			 Utils.addIndivContextAndContext(null, ce, em, getIdnr(), Id_I_BR, "MC M1", "", "Reported", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
    	 }

    	 ceBirth = null;
    	 if(getM1brbl() != null)
    		 ceBirth = Contxt.get2(getM1brbl());
    	 if(ceBirth != null)	 
		     Utils.addIndivAndContext(null, ceBirth, em, getIdnr(), Id_I_BR, "MC M1", "BIRTH_LOCATION", "Reported", "Exact", getMar_cd(), getMar_cm(), getMar_cy());

    	 
		 if(getM1sdse().equalsIgnoreCase("J")){
			 
			 if(getM1sdsy() > 0){
				 Utils.addIndiv(em, getIdnr(), Id_I_BR, "MC M1", "DIVORCE_DATE", null, "Reported", "Exact", getM1sdsd(),  getM1sdsm(), getM1sdsy());
			 }
			 
	    	 ContextElement ceDivorce = null;
	    	 if(getM1sdsl() != null)
	    		 ceDivorce = Contxt.get2(getM1sdsl());
	    	 
			 if(ceDivorce != null){
				 Utils.addIndivAndContext(null, ceDivorce, em, getIdnr(), Id_I_BR, "MC M1", "DIVORCE_LOCATION", "Reported", "Exact", getM1sdsd(),  getM1sdsm(), getM1sdsy());
			 }
		 }
		 

    	 // Relation to RP
    	 
		 Utils.addIndivIndiv(em, getIdnr(), Id_I_GR, Id_I_BR, "MC M1", "Echtgenoot", "Event", "Exact",  getMar_cd(), getMar_cm(), getMar_cy());
		 Utils.addIndivIndiv(em, getIdnr(), Id_I_BR, Id_I_GR, "MC M1", "Echtgenote", "Event", "Exact",  getMar_cd(), getMar_cm(), getMar_cy());
		 
		 // Father Groom
    	 
		 int Id_I_GF = 13 + uniquifier; // Utils.getId_I(); // Father Groom is new person

		 if(getM1gfln() != null && getM1gfln().trim().length() > 0 && !getM1gfln().trim().equalsIgnoreCase("N")){
			 Utils.addIndiv(em, getIdnr(), Id_I_GF, "MC M1", "LAST_NAME", getM1gfln(), "Missing", "Time_invariant", 0, 0, 0);
			 Utils.addIndiv(em, getIdnr(), Id_I_GF, "MC M1", "SEX", "Male", "Missing", "Time_invariant", 0, 0, 0);
			 if(getM1gfpf() != null && getM1gfpf().trim().length() > 0)
				 Utils.addIndiv(em, getIdnr(), Id_I_GF, "MC M1", "PREFIX_LAST_NAME", getM1gfpf(), "Missing", "Time_invariant", 0, 0, 0);
			 if(getM1gffn() != null && getM1gffn().trim().length() > 0)
				 Utils.addIndiv(em, getIdnr(), Id_I_GF, "MC M1", "FIRST_NAME", getM1gffn(), "Missing", "Time_invariant", 0, 0, 0);
			 if(getM1gfoc() != null && getM1gfoc().trim().length() > 0)
				 Utils.addIndiv(em, getIdnr(), Id_I_GF, "MC M1", "OCCUPATION_STANDARD", getM1gfoc(), "Declared", "", getMar_cd(), getMar_cm(), getMar_cy());
			 if(getM1gfsg() != null && getM1gfsg().trim().length() > 0)
				 Utils.addIndiv(em, getIdnr(), Id_I_GF, "MC M1", "SIGNATURE", Utils.signature(getM1gfsg()), "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 if(getM1gfay() > 0){
				 Utils.addIndiv(em, getIdnr(), Id_I_GF, "MC M1", "AGE_YEARS", (new Integer(getM1gfay())).toString(), "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
				 if(Utils.dateIsValid(getMar_cd(), getMar_cm(), getMar_cy()) == 0){
					 int[] a = Utils.birthRange(getM1gfay(), getMar_cd(), getMar_cm(), getMar_cy());
					 Utils.addIndiv(em, getIdnr(), Id_I_GF, "MC M1", "BIRTH_DATE", null, "Declared", "Age_based", a[0], a[1], a[2], a[3], a[4], a[5]);
				 }
			 }
			 else
				Utils.addIndiv(em, getIdnr(), Id_I_GF, "MC M1", "BIRTH_DATE", null, "Declared", "Estimated [30/100]", 1, 1, getMar_cy() - 100, 1, 1,   getMar_cy() - 30);


			 if(getM1gray() >= 23 || getM1gfca().equalsIgnoreCase("N"))
				 Utils.addIndiv(em, getIdnr(), Id_I_GF, "MC M1", "MARRIAGE_PERMISSION", "Not necessary", "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 else
				 if(getM1gfmp() != null && getM1gfmp().trim().length() > 0)
					 Utils.addIndiv(em, getIdnr(), Id_I_GF, "MC M1", "MARRIAGE_PERMISSION", Utils.permission(getM1gfmp()), "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());


			 if(getM1gfca().equalsIgnoreCase("J")){
				 if(getM1gfll() != null){
					 ce = Contxt.get2(getM1gfll());
					 if(ce != null)
						 Utils.addIndivContextAndContext(null, ce, em, getIdnr(), Id_I_GF, "MC M1", "", "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
				 }
			 }

			 if(getM1gfca().equalsIgnoreCase("J"))
				 Utils.addIndiv(em, getIdnr(), Id_I_GF, "MC M1", "ALIVE", "Yes", "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 else
				 Utils.addIndiv(em, getIdnr(), Id_I_GF, "MC M1", "ALIVE", "No", "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());

			 if(ceMarriage != null){
				 Utils.addIndivContextAndContextCertificate(getMar_cy(), getM1sdcn(), ceMarriage, em, getIdnr(), Id_I_GF, "MC M1", "Father Groom", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 }


			 if(getM1gfdl() != null && getM1gfdl().trim().length() > 0){
				 ContextElement ce1 = Contxt.get2(getM1gfdl());
				 Utils.addIndivAndContext(null, ce1, em, getIdnr(), Id_I_GF, "MC M1", "DEATH_LOCATION", "Missing", "Unavailable", 0, 0, 0);
			 }

			 // Father Groom relations 

			 Utils.addIndivIndiv(em, getIdnr(), Id_I_GF, Id_I_GR, "MC M1", "Vader", "Missing", "Time_invariant", 0, 0, 0);
			 Utils.addIndivIndiv(em, getIdnr(), Id_I_GR, Id_I_GF, "MC M1", "Zoon", "Missing", "Time_invariant", 0, 0, 0);
			 Utils.addIndivIndiv(em, getIdnr(), Id_I_GF, Id_I_BR, "MC M1", "Schoonvader",  "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 Utils.addIndivIndiv(em, getIdnr(), Id_I_BR, Id_I_GF, "MC M1", "Schoondochter",  "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy()); 
		 }  


		 // Mother Groom
    	 
		 int Id_I_GM = 14 + uniquifier; // Utils.getId_I(); // Mother Groom is new person

		 if(getM1gmln() != null && getM1gmln().trim().length() > 0 && !getM1gmln().trim().equalsIgnoreCase("N")){
			 Utils.addIndiv(em, getIdnr(), Id_I_GM, "MC M1", "LAST_NAME", getM1gmln(), "Missing", "Time_invariant", 0, 0, 0);
			 Utils.addIndiv(em, getIdnr(), Id_I_GM, "MC M1", "SEX", "Female", "Missing", "Time_invariant", 0, 0, 0);

			 if(getM1gmpf() != null && getM1gmpf().trim().length() > 0)
				 Utils.addIndiv(em, getIdnr(), Id_I_GM, "MC M1", "PREFIX_LAST_NAME", getM1gmpf(), "Missing", "Time_invariant", 0, 0, 0);
			 if(getM1gmfn() != null && getM1gmfn().trim().length() > 0)
				 Utils.addIndiv(em, getIdnr(), Id_I_GM, "MC M1", "FIRST_NAME", getM1gmfn(), "Missing", "Time_invariant", 0, 0, 0);
			 if(getM1gmoc() != null && getM1gmoc().trim().length() > 0)
				 Utils.addIndiv(em, getIdnr(), Id_I_GM, "MC M1", "OCCUPATION_STANDARD", getM1gmoc(), "Declared", "", getMar_cd(), getMar_cm(), getMar_cy());
			 if(getM1gmsg() != null && getM1gmsg().trim().length() > 0)
				 Utils.addIndiv(em, getIdnr(), Id_I_GM, "MC M1", "SIGNATURE", Utils.signature(getM1gmsg()), "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 if(getM1gmay() > 0){
				 Utils.addIndiv(em, getIdnr(), Id_I_GM, "MC M1", "AGE_YEARS", (new Integer(getM1gmay())).toString(),  "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
				 if(Utils.dateIsValid(getMar_cd(), getMar_cm(), getMar_cy()) == 0){
					 int[] a = Utils.birthRange(getM1gmay(), getMar_cd(), getMar_cm(), getMar_cy());
					 Utils.addIndiv(em, getIdnr(), Id_I_GM, "MC M1", "BIRTH_DATE", null, "Declared", "Age_based", a[0], a[1], a[2], a[3], a[4], a[5]);
				 }
			 }
			 else
				 Utils.addIndiv(em, getIdnr(), Id_I_GM, "MC M1", "BIRTH_DATE", null, "Declared", "Estimated [30/100]", 1, 1, getMar_cy() - 100, 1, 1,   getMar_cy() - 30);

			 if(getM1gray() >= 23 || getM1gmca().equalsIgnoreCase("N"))
				 Utils.addIndiv(em, getIdnr(), Id_I_GM, "MC M1", "MARRIAGE_PERMISSION", "Not necessary", "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 else
				 if(getM1gmmp() != null && getM1gmmp().trim().length() > 0)
					 Utils.addIndiv(em, getIdnr(), Id_I_GM, "MC M1", "MARRIAGE_PERMISSION", Utils.permission(getM1gmmp()), "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());


			 if(getM1gmca().equalsIgnoreCase("J")){
				 if(getM1gmll() != null){
					 ce = Contxt.get2(getM1gfll());
					 if(ce != null)
						 Utils.addIndivContextAndContext(null, ce, em, getIdnr(), Id_I_GM, "MC M1", "", "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
				 }
			 }

			 if(getM1gmca().equalsIgnoreCase("J"))
				 Utils.addIndiv(em, getIdnr(), Id_I_GM, "MC M1", "ALIVE", "Yes", "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 else
				 Utils.addIndiv(em, getIdnr(), Id_I_GM, "MC M1", "ALIVE", "No", "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());

			 if(ceMarriage != null){
				 Utils.addIndivContextAndContextCertificate(getMar_cy(), getM1sdcn(), ceMarriage, em, getIdnr(), Id_I_GM, "MC M1", "Mother Groom", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 }

			 if(getM1gmdl() != null && getM1gmdl().trim().length() > 0){
				 ContextElement ce1 = Contxt.get2(getM1gmdl());
				 Utils.addIndivAndContext(null, ce1, em, getIdnr(), Id_I_GM, "MC M1", "DEATH_LOCATION",  "Missing", "Unavailable", 0, 0, 0);
			 }



			 // Mother Groom relation to IP

			 Utils.addIndivIndiv(em, getIdnr(), Id_I_GM, Id_I_GR, "MC M1", "Moeder", "Missing", "Time_invariant", 0, 0, 0);
			 Utils.addIndivIndiv(em, getIdnr(), Id_I_GR, Id_I_GM, "MC M1", "Zoon", "Missing", "Time_invariant", 0, 0, 0);
			 Utils.addIndivIndiv(em, getIdnr(), Id_I_GM, Id_I_BR, "MC M1", "Schoonmoeder", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 Utils.addIndivIndiv(em, getIdnr(), Id_I_BR, Id_I_GM, "MC M1", "Schoondochter", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());

		 }
		 // Father Groom and Mother Groom get an undate Husband Wife relation (if both are alive)
		 
		 if(getM1gfca().equalsIgnoreCase("J") &&  getM1gmca().equalsIgnoreCase("J")){
			 Utils.addIndivIndiv(em, getIdnr(), Id_I_GM, Id_I_GF, "MC M1", "Echtgenote", "Missing", "Unavailable", 0, 0, 0); 
			 Utils.addIndivIndiv(em, getIdnr(), Id_I_GF, Id_I_GM, "MC M1", "Echtgenoot", "Missing", "Unavailable" , 0, 0, 0); 
		 }
			 
		 
		 
		 // Father Bride
    	 
		 int Id_I_BF = 15 + uniquifier; // Utils.getId_I(); // Father Bride is new person

		 if(getM1bfln() != null && getM1bfln().trim().length() > 0 && !getM1bfln().trim().equalsIgnoreCase("N")){
			 Utils.addIndiv(em, getIdnr(), Id_I_BF, "MC M1", "LAST_NAME", getM1bfln(), "Missing", "Time_invariant", 0, 0, 0);
			 Utils.addIndiv(em, getIdnr(), Id_I_BF, "MC M1", "SEX", "Male", "Missing", "Time_invariant", 0, 0, 0);

			 if(getM1bfpf() != null && getM1bfpf().trim().length() > 0)
				 Utils.addIndiv(em, getIdnr(), Id_I_BF, "MC M1", "PREFIX_LAST_NAME", getM1bfpf(), "Missing", "Time_invariant", 0, 0, 0);
			 if(getM1bffn() != null && getM1bffn().trim().length() > 0)
				 Utils.addIndiv(em, getIdnr(), Id_I_BF, "MC M1", "FIRST_NAME", getM1bffn(), "Missing", "Time_invariant", 0, 0, 0);
			 if(getM1bfoc() != null && getM1bfoc().trim().length() > 0)
				 Utils.addIndiv(em, getIdnr(), Id_I_BF, "MC M1", "OCCUPATION_STANDARD", getM1bfoc(), "Declared", "", getMar_cd(), getMar_cm(), getMar_cy());
			 if(getM1bfsg() != null && getM1bfsg().trim().length() > 0)
				 Utils.addIndiv(em, getIdnr(), Id_I_BF, "MC M1", "SIGNATURE", Utils.signature(getM1bfsg()), "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 if(getM1bfay() > 0){
				 Utils.addIndiv(em, getIdnr(), Id_I_BF, "MC M1", "AGE_YEARS", (new Integer(getM1bfay())).toString(),  "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
				 if(Utils.dateIsValid(getMar_cd(), getMar_cm(), getMar_cy()) == 0){
					 int[] a = Utils.birthRange(getM1bfay(), getMar_cd(), getMar_cm(), getMar_cy());
					 Utils.addIndiv(em, getIdnr(), Id_I_BF, "MC M1", "BIRTH_DATE", null, "Declared", "Age_based", a[0], a[1], a[2], a[3], a[4], a[5]);
				 }
			 }
			 else
				Utils.addIndiv(em, getIdnr(), Id_I_BF, "MC M1", "BIRTH_DATE", null, "Declared", "Estimated [30/100]", 1, 1, getMar_cy() - 100, 1, 1,   getMar_cy() - 30);


			 if(getM1bray() >= 23 || getM1bfca().equalsIgnoreCase("N"))
				 Utils.addIndiv(em, getIdnr(), Id_I_BF, "MC M1", "MARRIAGE_PERMISSION", "Not necessary", "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 else	 
				 if(getM1bfmp() != null && getM1bfmp().trim().length() > 0)
					 Utils.addIndiv(em, getIdnr(), Id_I_BF, "MC M1", "MARRIAGE_PERMISSION", Utils.permission(getM1bfmp()), "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());


			 if(getM1bfca().equalsIgnoreCase("J")){
				 if(getM1bfll() != null){
					 ce = Contxt.get2(getM1bfll());
					 if(ce != null)
						 Utils.addIndivContextAndContext(null, ce, em, getIdnr(), Id_I_BF, "MC M1", "", "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
				 }
			 }

			 if(getM1bfca().equalsIgnoreCase("J"))
				 Utils.addIndiv(em, getIdnr(), Id_I_BF, "MC M1", "ALIVE", "Yes", "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 else
				 Utils.addIndiv(em, getIdnr(), Id_I_BF, "MC M1", "ALIVE", "No", "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());

			 if(ceMarriage != null){
				 Utils.addIndivContextAndContextCertificate(getMar_cy(), getM1sdcn(), ceMarriage, em, getIdnr(), Id_I_BF, "MC M1", "Father Bride", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 }

			 if(getM1bfdl() != null && getM1bfdl().trim().length() > 0){
				 ContextElement ce1 = Contxt.get2(getM1bfdl());
				 Utils.addIndivAndContext(null, ce1, em, getIdnr(), Id_I_BF, "MC M1", "DEATH_LOCATION", "Missing", "Unavailable", 0, 0, 0);
			 }



			 // Father Bride relation to IP

			 Utils.addIndivIndiv(em, getIdnr(), Id_I_BF, Id_I_BR, "MC M1", "Vader" , "Missing", "Time_invariant", 0, 0, 0);
			 Utils.addIndivIndiv(em, getIdnr(), Id_I_BR, Id_I_BF, "MC M1", "Dochter" , "Missing", "Time_invariant", 0, 0, 0);
			 Utils.addIndivIndiv(em, getIdnr(), Id_I_BF, Id_I_GR, "MC M1", "Schoonvader" , "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 Utils.addIndivIndiv(em, getIdnr(), Id_I_GR, Id_I_BF, "MC M1", "Schoonzoon" , "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());

		 }

		 // Mother Bride

		 int Id_I_BM = 16 + uniquifier; // Utils.getId_I(); // Mother Bride is new person

		 if(getM1bmln() != null && getM1bmln().trim().length() > 0 && !getM1bmln().trim().equalsIgnoreCase("N")){
			 Utils.addIndiv(em, getIdnr(), Id_I_BM, "MC M1", "LAST_NAME", getM1bmln(), "Missing", "Time_invariant", 0, 0, 0);
			 Utils.addIndiv(em, getIdnr(), Id_I_BM, "MC M1", "SEX", "Female", "Missing", "Time_invariant", 0, 0, 0);

			 if(getM1bmpf() != null && getM1bmpf().trim().length() > 0)
				 Utils.addIndiv(em, getIdnr(), Id_I_BM, "MC M1", "PREFIX_LAST_NAME", getM1bmpf(), "Missing", "Time_invariant", 0, 0, 0);
			 if(getM1bmfn() != null && getM1bmfn().trim().length() > 0)
				 Utils.addIndiv(em, getIdnr(), Id_I_BM, "MC M1", "FIRST_NAME", getM1bmfn(), "Missing", "Time_invariant", 0, 0, 0);
			 if(getM1bmoc() != null && getM1bmoc().trim().length() > 0)
				 Utils.addIndiv(em, getIdnr(), Id_I_BM, "MC M1", "OCCUPATION_STANDARD", getM1bmoc(), "Declared", "", getMar_cd(), getMar_cm(), getMar_cy());
			 if(getM1bmsg() != null && getM1bmsg().trim().length() > 0)
				 Utils.addIndiv(em, getIdnr(), Id_I_BM, "MC M1", "SIGNATURE", Utils.signature(getM1bmsg()), "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 if(getM1bmay() > 0){
				 Utils.addIndiv(em, getIdnr(), Id_I_BM, "MC M1", "AGE_YEARS", (new Integer(getM1bmay())).toString(),  "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
				 if(Utils.dateIsValid(getMar_cd(), getMar_cm(), getMar_cy()) == 0){
					 int[] a = Utils.birthRange(getM1bmay(), getMar_cd(), getMar_cm(), getMar_cy());
					 Utils.addIndiv(em, getIdnr(), Id_I_BM, "MC M1", "BIRTH_DATE", null, "Declared", "Age_based", a[0], a[1], a[2], a[3], a[4], a[5]);
				 }
			 }
			 else
				Utils.addIndiv(em, getIdnr(), Id_I_BM, "MC M1", "BIRTH_DATE", null, "Declared", "Estimated [30/100]", 1, 1, getMar_cy() - 100, 1, 1,   getMar_cy() - 30);

			 
			 if(getM1bray() >= 23 || getM1bmca().equalsIgnoreCase("N"))
				 Utils.addIndiv(em, getIdnr(), Id_I_BM, "MC M1", "MARRIAGE_PERMISSION", "Not necessary", "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 else
				 if(getM1bmmp() != null && getM1bmmp().trim().length() > 0)
					 Utils.addIndiv(em, getIdnr(), Id_I_BM, "MC M1", "MARRIAGE_PERMISSION", Utils.permission(getM1bmmp()), "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());


			 if(getM1bmca().equalsIgnoreCase("J")){
				 if(getM1bfll() != null){
					 ce = Contxt.get2(getM1bfll());
					 if(ce != null)
						 Utils.addIndivContextAndContext(null, ce, em, getIdnr(), Id_I_BM, "MC M1", "", "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
				 }
			 }

			 if(getM1bmca().equalsIgnoreCase("J"))
				 Utils.addIndiv(em, getIdnr(), Id_I_BM, "MC M1", "ALIVE", "Yes", "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 else
				 Utils.addIndiv(em, getIdnr(), Id_I_BM, "MC M1", "ALIVE", "No", "Declared", "Exact", getMar_cd(), getMar_cm(), getMar_cy());

			 if(ceMarriage != null){
				 Utils.addIndivContextAndContextCertificate(getMar_cy(), getM1sdcn(), ceMarriage, em, getIdnr(), Id_I_BM, "MC M1", "Mother Bride", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 }

			 if(getM1bmdl() != null && getM1bmdl().trim().length() > 0){
				 ContextElement ce1 = Contxt.get2(getM1bmdl());
				 Utils.addIndivAndContext(null, ce1, em, getIdnr(), Id_I_BM, "MC M1", "DEATH_LOCATION", "Missing", "Unavailable", 0, 0, 0);
			 }



			 // Mother Bride relation to IP

			 Utils.addIndivIndiv(em, getIdnr(), Id_I_BM, Id_I_BR, "MC M1", "Moeder", "Missing", "Time_invariant", 0, 0, 0);
			 Utils.addIndivIndiv(em, getIdnr(), Id_I_BR, Id_I_BM, "MC M1", "Dochter", "Missing", "Time_invariant", 0, 0, 0);
			 Utils.addIndivIndiv(em, getIdnr(), Id_I_BM, Id_I_GR, "MC M1", "Schoonmoeder", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());
			 Utils.addIndivIndiv(em, getIdnr(), Id_I_GR, Id_I_BM, "MC M1", "Schoonzoon", "Event", "Exact", getMar_cd(), getMar_cm(), getMar_cy());

		 }
		 // Father Bride and Mother Bride get an undated Husband Wife relation (if both are alive)
		 
		 if(getM1bfca().equalsIgnoreCase("J") &&  getM1bmca().equalsIgnoreCase("J")){
			 Utils.addIndivIndiv(em, getIdnr(), Id_I_BM, Id_I_BF, "MC M1", "Echtgenote", "Missing", "Unavailable", 0, 0, 0); 
			 Utils.addIndivIndiv(em, getIdnr(), Id_I_BF, Id_I_BM, "MC M1", "Echtgenoot", "Missing", "Unavailable" , 0, 0, 0); 
		 }
			 

		 
		 // Down the tree
		 
		 for(M3 m3: getM3L())
			 m3.convert(em);

		 for(M4 m4: getM4L())
			 m4.convert(em);

		 for(M5 m5: getM5L())
			 m5.convert(em);

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
	public B1 getB1() {
		return b1;
	}
	public void setB1(B1 b1) {
		this.b1 = b1;
	}
	public ArrayList<M2> getM2L() {
		return m2L;
	}
	public void setM2L(ArrayList<M2> m2l) {
		m2L = m2l;
	}
	public ArrayList<M3> getM3L() {
		return m3L;
	}
	public void setM3L(ArrayList<M3> m3l) {
		m3L = m3l;
	}
	public ArrayList<M4> getM4L() {
		return m4L;
	}
	public void setM4L(ArrayList<M4> m4l) {
		m4L = m4l;
	}
	public ArrayList<M5> getM5L() {
		return m5L;
	}
	public void setM5L(ArrayList<M5> m5l) {
		m5L = m5l;
	}
	public M6 getM6() {
		return m6;
	}
	public void setM6(M6 m6) {
		this.m6 = m6;
	}
     
     
}
