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


import nl.iisg.idscontext.ContextElement;
import nl.iisg.idscontext.Contxt;

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

     @Transient                   private           B1   b1   = null;               
     @Transient                   private ArrayList<D2>  d2L  = new ArrayList<D2>();
     @Transient                   private ArrayList<D3>  d3L  = new ArrayList<D3>();
     @Transient                   private           D4   d4   = null;               

     @Transient                   private A1  d1sdcla;  
     @Transient                   private A1  d1rpbla;  
     @Transient                   private A1  d1rplla;  
     @Transient                   private A1  d1rpdla;  
     @Transient                   private A1  d1falla;  
     @Transient                   private A1  d1molla;
     
     @Transient                   private String DC = null;  



public void convert(EntityManager em){
    	 
    	 // RP
    	 
		 int Id_I_RP = 51; // Dead RP;
		 
    	 ContextElement ceCertificate = null;
  		 if(getD1sdcl() != null && getD1sdcl().trim().length() > 0)
   			 ceCertificate = Contxt.get2(getD1sdcl());

    	 //System.out.println("getD1sdcc = " + getD1sdcc() + "getD1sdcl = " + getD1sdcl() + " ceCertificate = " + ceCertificate);
    	 
    	 
    	 //String death_certificate = "D1ificate";
    	 DC = "Death Certificate";
    	 
		 if(ceCertificate != null){
			 if(getD1sdce() != null && getD1sdce().equalsIgnoreCase("J"))
				 DC =  "DC Extract"; //Extract D1ificate"; 
			 
			 Utils.addIndivContextAndContextCertificate(getD1sdcy(), getD1sdcn(), ceCertificate, em, getIdnr(), Id_I_RP, DC, "DC D1", "Deceased", "Event", "Exact", getD1rpdd(), getD1rpdm(), getD1rpdy());
			 //Utils.addIndivAndContext(getD1rpdl(), ceCertificate, em, getIdnr(), Id_I_RP, "DC D1", "DEATH_LOCATION", "Event", "Exact", getD1rpdd(), getD1rpdm(), getD1rpdy());
			// Utils.addIndivAndContext(null, null, null, null, ceCertificate, em, getIdnr(), Id_I_RP, DC + " D1", "DEATH_LOCATION", "Event", "Exact", getD1rpdd(), getD1rpdm(), getD1rpdy());
		 }

		     	 
		 
		 if(getD1rpln() != null && getD1rpln().trim().length() > 0  && !getD1rpln().trim().equalsIgnoreCase("N"))
			 Utils.addIndiv(em, getIdnr(), Id_I_RP, "DC D1", "LAST_NAME", getD1rpln(), "Missing", "Time_invariant", 0, 0, 0);
		 else 
			 return;
		 
		 
		 
		 
		 Utils.addIndiv(em, getIdnr(), Id_I_RP, "DC D1", "DEATH_DATE", null, "Event", "Exact", getD1rpdd(), getD1rpdm(), getD1rpdy());

		 if(getD1rppf() != null && getD1rppf().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_RP, "DC D1", "PREFIX_LAST_NAME", getD1rppf(), "Missing", "Time_invariant", 0, 0, 0);
		 if(getD1rpfn() != null && getD1rpfn().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_RP, "DC D1", "FIRST_NAME", getD1rpfn(), "Missing", "Time_invariant", 0, 0, 0);
		 if(getD1rpgn() != null && getD1rpgn().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_RP, "DC D1", "SEX", Utils.sex(getD1rpgn()), "Missing", "Time_invariant", 0, 0, 0);
		 if(getD1rpoc() != null && getD1rpoc().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_RP, "DC D1", "OCCUPATION_STANDARD", getD1rpoc(), "Declared", "Exact", getD1rpdd(), getD1rpdm(), getD1rpdy());
		 if(getD1rpcs() != null && getD1rpcs().trim().length() > 0){
			 String cs = "Unknown";
			 if(getD1rpcs().equals("1"))
				 cs = "Unmaried";
			 else
				 if(getD1rpcs().equals("2"))
					 cs = "Widowed";
				 else
					 if(getD1rpcs().equals("3"))
						 cs = "Divorced";
					 else
						 if(getD1rpcs().equals("5"))
							 cs = "Married";


			 Utils.addIndiv(em, getIdnr(), Id_I_RP, "DC D1", "CIVIL_STATUS", cs, "Declared", "Exact", getD1rpdd(), getD1rpdm(), getD1rpdy());
		 }
		 if(getD1rpay() > 0){
			 Utils.addIndiv(em, getIdnr(), Id_I_RP, "DC D1", "AGE_YEARS", (new Integer(getD1rpay())).toString(), "Declared", "Exact", getD1rpdd(), getD1rpdm(), getD1rpdy());
			 if(Utils.dateIsValid(getD1rpdd(), getD1rpdm(), getD1rpdy()) == 0){
				 int[] a = Utils.birthRange(getD1rpay(), getD1rpdd(), getD1rpdm(), getD1rpdy());
				 Utils.addIndiv(em, getIdnr(), Id_I_RP, "DC D1", "BIRTH_DATE", null, "Assigned", "Age_based", a[0], a[1], a[2], a[3], a[4], a[5]);
			 }
		 }
		 else
			 if(getD1rpam() > 0)
				 Utils.addIndiv(em, getIdnr(), Id_I_RP, "DC D1", "AGE_MONTHS", (new Integer(getD1rpam())).toString(), "Declared", "Exact", getD1rpdd(), getD1rpdm(), getD1rpdy());
			 else
				 if(getD1rpaw() > 0)
					 Utils.addIndiv(em, getIdnr(), Id_I_RP, "DC D1", "AGE_WEEKS", (new Integer(getD1rpaw())).toString(), "Declared", "Exact", getD1rpdd(), getD1rpdm(), getD1rpdy());
				 else
					 if(getD1rpad() > 0)
						 Utils.addIndiv(em, getIdnr(), Id_I_RP, "DC D1", "AGE_DAYS", (new Integer(getD1rpad())).toString(), "Declared", "Exact", getD1rpdd(), getD1rpdm(), getD1rpdy());
		 
		 
		 if(getD1rpay() <= 0)				
			Utils.addIndiv(em, getIdnr(), Id_I_RP, "DC D1", "BIRTH_DATE", null, "Assigned", "Estimated [0/100]", 1, 1, getD1sdcy() - 100, 1, 1,  getD1sdcy());

		 
		 Utils.addIndiv(em, getIdnr(), Id_I_RP, "DC D1", "HSN_RESEARCH_PERSON", "HSN RP", "Missing", "Time_invariant", 0, 0, 0);
         Utils.addIndiv(em, getIdnr(), Id_I_RP, "DC D1", "HSN_IDENTIFIER", "" + getIdnr(), "Missing", "Time_invariant", 0, 0, 0);

    	 //if(ceCertificate != null){
   		 //	 Utils.addIndivContextAndContext(getD1rpll(), ceCertificate, em, getIdnr(), Id_I_RP, "DC D1", "", "Declared", "Exact", getD1rpdd(), getD1rpdm(), getD1rpdy());
    	 //}

    	
    	 //if(getD1rpbc() > 0)
    	//	 ceBirthLocation = Contxt.get(getD1rpbc());  // Look up name in Context System
    	 //else
    	//	 if(getD1rpbl() != null && getD1rpbl().trim().length() > 0)
        //		 ceBirthLocation = Contxt.get2(getD1rpbl());  // Look up name in Context System
    			 
    		 
   		 //if(ceBirthLocation != null)
   		//	 Utils.addIndivAndContext(null, ceCertificate, em, getIdnr(), Id_I_RP, "DC D1", "BIRTH_LOCATION", "Missing", "Unavailable", 0, 0, 0);

    		 
		 // RP Birth Location

		 //System.out.println("getD1rpbla() = "+ getD1rpbla());

		 if(getD1rpbla() != null){

			 ContextElement ceBirthLocation = null;

			 if(getD1rpbla().getMunicipality() != null)
				 ceBirthLocation = Contxt.get2(getD1rpbla().getMunicipality());
			 else 
				 ceBirthLocation = ceCertificate;

			 if(ceBirthLocation != null){

				 int startDay1   = (new Integer(getD1rpbla().getStartDate().substring(0,2))).intValue();
				 int startMonth1 = (new Integer(getD1rpbla().getStartDate().substring(3,5))).intValue();
				 int startYear1  = (new Integer(getD1rpbla().getStartDate().substring(6,10))).intValue();

				 Utils.addIndivAndContext(getD1rpbla().getQuarter(), getD1rpbla().getStreet(), getD1rpbla().getNumber(), getD1rpbla().getAddition(),
						 ceBirthLocation, em, getIdnr(), Id_I_RP, "DC D1",  "BIRTH_LOCATION", "Reported", "Exact",  
						 startDay1, startMonth1, startYear1);

			 }

		 }

		 // RP Address

		 if(getD1rplla() != null){

			 ContextElement ceAddress = null;

			 if(getD1rplla().getMunicipality() != null)
				 ceAddress = Contxt.get2(getD1rplla().getMunicipality());
			 else 
				 ceAddress = ceCertificate;

			 if(ceAddress != null){

				 int startDay1   = (new Integer(getD1rplla().getStartDate().substring(0,2))).intValue();
				 int startMonth1 = (new Integer(getD1rplla().getStartDate().substring(3,5))).intValue();
				 int startYear1  = (new Integer(getD1rplla().getStartDate().substring(6,10))).intValue();

				 Utils.addIndivContextAndContext(getD1rplla().getQuarter(), getD1rplla().getStreet(), getD1rplla().getNumber(), getD1rplla().getAddition(),
						 ceAddress, em, getIdnr(), Id_I_RP, "DC D1",  "Address", "Declared", "Exact",  
						 startDay1, startMonth1, startYear1);

			 }

		 }

		 // RP Death Location

		 if(getD1rpdla() != null){

			 ContextElement ceDeathLocation = null;


			 if(getD1rpdla().getMunicipality() != null)
				 ceDeathLocation = Contxt.get2(getD1rpdla().getMunicipality());
			 else 
				 ceDeathLocation = ceCertificate;

			 if(ceDeathLocation != null){

				 int startDay1   = (new Integer(getD1rpdla().getStartDate().substring(0,2))).intValue();
				 int startMonth1 = (new Integer(getD1rpdla().getStartDate().substring(3,5))).intValue();
				 int startYear1  = (new Integer(getD1rpdla().getStartDate().substring(6,10))).intValue();

				 Utils.addIndivAndContext(getD1rpdla().getQuarter(), getD1rpdla().getStreet(), getD1rpdla().getNumber(), getD1rpdla().getAddition(),
						 ceDeathLocation, em, getIdnr(), Id_I_RP, "DC D1",  "DEATH_LOCATION", "Event", "Exact",  
						 startDay1, startMonth1, startYear1);
			 }


		 }


   		 
   		 
   		 
    	 // Father dead RP
    	 
		 int Id_I_FA = 52; // Father Dead RP; 
		 
		 boolean fatherLastname = false;
		 if(getD1faln() != null && getD1faln().trim().length() > 0 && !getD1faln().trim().equalsIgnoreCase("N")){
			 
			 fatherLastname =true;
			 Utils.addIndiv(em, getIdnr(), Id_I_FA, "DC D1", "LAST_NAME", getD1faln(), "Missing", "Time_invariant", 0, 0, 0);

			 if(getD1fapf() != null && getD1fapf().trim().length() > 0){
				 Utils.addIndiv(em, getIdnr(), Id_I_FA, "DC D1", "PREFIX_LAST_NAME", getD1fapf(), "Missing", "Time_invariant", 0, 0, 0);
			 }
			 if(getD1fafn() != null && getD1fafn().trim().length() > 0){
				 Utils.addIndiv(em, getIdnr(), Id_I_FA, "DC D1", "FIRST_NAME", getD1fafn(), "Missing", "Time_invariant", 0, 0, 0);
			 }


			 Utils.addIndiv(em, getIdnr(), Id_I_FA, "DC D1", "SEX", "Male", "Missing", "Time_invariant", 0, 0, 0);


			 if(getD1faca().equalsIgnoreCase("J")){

				 if(getD1faoc() != null)
					 Utils.addIndiv(em, getIdnr(), Id_I_FA, "DC D1", "OCCUPATION_STANDARD", getD1faoc(), "Declared", "Exact", getD1rpdd(), getD1rpdm(), getD1rpdy());
				 if(getD1faay() > 0){
					 Utils.addIndiv(em, getIdnr(), Id_I_FA, "DC D1", "AGE_YEARS", (new Integer(getD1faay())).toString(), "Declared", "Exact", getD1rpdd(), getD1rpdm(), getD1rpdy());
					 if(Utils.dateIsValid(getD1rpdd(), getD1rpdm(), getD1rpdy()) == 0){
						 int[] a = Utils.birthRange(getD1faay(), getD1rpdd(), getD1rpdm(), getD1rpdy());
						 Utils.addIndiv(em, getIdnr(), Id_I_FA, "DC D1", "BIRTH_DATE", null, "Assigned", "Age_based", a[0], a[1], a[2], a[3], a[4], a[5]);
					 }
				 }
				 else
					 Utils.addIndiv(em, getIdnr(), Id_I_FA, "DC D1", "BIRTH_DATE", null, "Assigned", "Estimated [16/100]", 1, 1, getD1rpdy() - 100, 1, 1, getD1rpdy() - 16);

				 if(ceCertificate != null){
					 //Utils.addIndivContextAndContext(getD1fall(), ceCertificate, em, getIdnr(), Id_I_FA, "DC D1", "", "Declared", "Exact", getD1rpdd(), getD1rpdm(), getD1rpdy());
					 Utils.addIndivContextAndContextCertificate(getD1sdcy(), getD1sdcn(), ceCertificate, em, getIdnr(), Id_I_FA, DC, "DC D1", "Father", "Event", "Exact", getD1rpdd(), getD1rpdm(), getD1rpdy());
				 }
			 }
			 else
				 Utils.addIndiv(em, getIdnr(), Id_I_FA, "DC D1", "BIRTH_DATE", null, "Assigned", "Estimated [16/100]", 1, 1, getD1rpdy() - 100, 1, 1, getD1rpdy() - 16);
			 
			 if(getD1infa() != null && getD1infa().trim().equalsIgnoreCase("J")){
				 if(getD1insg() != null && getD1insg().trim().length() > 0)
					 Utils.addIndiv(em, getIdnr(), Id_I_FA, "DC D1", "SIGNATURE", Utils.signature(getD1insg()), "Event", "Exact", getD1rpdd(), getD1rpdm(), getD1rpdy());

			 }
			 Utils.addIndiv(em, getIdnr(), Id_I_FA, "DC D1", "HSN_IDENTIFIER", "" + getIdnr(), "Missing", "Time_invariant", 0, 0, 0);

			 // Father relation to RP 

			 //if(getD1faca().equalsIgnoreCase("J")){

			 Utils.addIndivIndiv(em, getIdnr(), Id_I_FA, Id_I_RP, "DC D1", "Vader", "Missing", "Time_invariant", 0, 0, 0);
			 if(getB1().getB1rpgn().equalsIgnoreCase("M"))
				 Utils.addIndivIndiv(em, getIdnr(), Id_I_RP, Id_I_FA, "DC D1", "Zoon", "Missing", "Time_invariant", 0, 0, 0);
			 else
				 Utils.addIndivIndiv(em, getIdnr(), Id_I_RP, Id_I_FA, "DC D1", "Dochter", "Missing", "Time_invariant", 0, 0, 0);
			 
	    	 // Father Address
	   		 
	   		 if(getD1falla() != null){

	   			 ContextElement ceAddress = null;

	   			 
	   			 if(getD1falla().getMunicipality() != null)
	   				ceAddress = Contxt.get2(getD1falla().getMunicipality());
	   			 else 
	   				ceAddress = ceCertificate;
	   			 
	   			 if(ceAddress != null){
	   			 
					int startDay1   = (new Integer(getD1falla().getStartDate().substring(0,2))).intValue();
					int startMonth1 = (new Integer(getD1falla().getStartDate().substring(3,5))).intValue();
					int startYear1  = (new Integer(getD1falla().getStartDate().substring(6,10))).intValue();

					Utils.addIndivContextAndContext(getD1falla().getQuarter(), getD1falla().getStreet(), getD1falla().getNumber(), getD1falla().getAddition(),
							ceAddress, em, getIdnr(), Id_I_FA, "DC D1",  "Address", "Declared", "Exact",  
							startDay1, startMonth1, startYear1);
	   			 }
	   			 
	   		 }
		 }
		 //}
		 

		 
		 
		 
		 // Mother dead RP

		 int Id_I_MO = 53; // Mother Dead RP; 
		 
		 boolean motherLastname = false;

		 if(getD1moln() != null && getD1moln().trim().length() > 0 && !getD1moln().trim().equalsIgnoreCase("N")){
			 
			 motherLastname = true;
			 Utils.addIndiv(em, getIdnr(), Id_I_MO, "DC D1", "LAST_NAME", getD1moln(), "Missing", "Time_invariant", 0, 0, 0);
			 if(getD1mopf() != null && getD1mopf().trim().length() > 0)
				 Utils.addIndiv(em, getIdnr(), Id_I_MO, "DC D1", "PREFIX_LAST_NAME", getD1mopf(), "Missing", "Time_invariant", 0, 0, 0);
			 if(getD1mofn() != null && getD1mofn().trim().length() > 0)
				 Utils.addIndiv(em, getIdnr(), Id_I_MO, "DC D1", "FIRST_NAME", getD1mofn(), "Missing", "Time_invariant", 0, 0, 0);

			 Utils.addIndiv(em, getIdnr(), Id_I_MO, "DC D1", "SEX", "Female", "Missing", "Time_invariant", 0, 0, 0);


			 if(getD1moca().equalsIgnoreCase("J")){

				 if(getD1mooc() != null && getD1mooc().trim().length() > 0)
					 Utils.addIndiv(em, getIdnr(), Id_I_MO, "DC D1", "OCCUPATION_STANDARD", getD1mooc(), "Declared", "Exact", getD1rpdd(), getD1rpdm(), getD1rpdy());
				 if(getD1moay() > 0){
					 //Utils.addIndiv(em, getIdnr(), Id_I_MO, "DC D1", "AGE_YEARS", (new Integer(getD1moay())).toString(), getD1rpdd(), getD1rpdm(), getD1rpdy());
					 if(Utils.dateIsValid(getD1rpdd(), getD1rpdm(), getD1rpdy()) == 0){
						 int[] a = Utils.birthRange(getD1moay(), getD1rpdd(), getD1rpdm(), getD1rpdy());
						 Utils.addIndiv(em, getIdnr(), Id_I_MO, "DC D1",  "BIRTH_DATE", null, "Assigned", "Age_based", a[0], a[1], a[2], a[3], a[4], a[5]);
					 }

				 }
				 else
					 Utils.addIndiv(em, getIdnr(), Id_I_MO, "DC D1", "BIRTH_DATE", null, "Assigned", "Estimated [15/100]", 1, 1, getD1rpdy() - 100, 1, 1, getD1rpdy() - 15);


				 if(ceCertificate != null){
					 //Utils.addIndivContextAndContext(getD1moll(), ceCertificate, em, getIdnr(), Id_I_MO, "DC  D1", "", "Declared", "Exact", getD1rpdd(), getD1rpdm(), getD1rpdy());
					 Utils.addIndivContextAndContextCertificate(getD1sdcy(), getD1sdcn(), ceCertificate, em, getIdnr(), Id_I_MO, DC, "DC D1", "Mother", "Event", "Exact", getD1rpdd(), getD1rpdm(), getD1rpdy());
				 }
			 }
			 else
				 Utils.addIndiv(em, getIdnr(), Id_I_MO, "DC D1", "BIRTH_DATE", null, "Assigned", "Estimated [15/100]", 1, 1, getD1rpdy() - 100, 1, 1, getD1rpdy() - 15);
			 Utils.addIndiv(em, getIdnr(), Id_I_MO, "DC D1", "HSN_IDENTIFIER", "" + getIdnr(), "Missing", "Time_invariant", 0, 0, 0);

				 
			 // Mother relation to RP 

			 //if(getD1moca().equalsIgnoreCase("J")){

			 Utils.addIndivIndiv(em, getIdnr(), Id_I_MO, Id_I_RP, "DC D1", "Moeder", "Missing", "Time_invariant", 0, 0, 0);
			 if(getB1().getB1rpgn().equalsIgnoreCase("M"))
				 Utils.addIndivIndiv(em, getIdnr(), Id_I_RP, Id_I_MO, "DC D1", "Zoon", "Missing", "Time_invariant", 0, 0, 0);
			 else
				 Utils.addIndivIndiv(em, getIdnr(), Id_I_RP, Id_I_MO, "DC D1", "Dochter", "Missing", "Time_invariant", 0, 0, 0);

			 //}

			 if(fatherLastname == true && motherLastname == true && getD1faca() != null && getD1faca().trim().equalsIgnoreCase("J") && getD1moca() != null && getD1moca().trim().equalsIgnoreCase("J")){
				 Utils.addIndivIndiv(em, getIdnr(), Id_I_FA, Id_I_MO, "DC D1", "Echtgenoot", "Declared", "Exact", getD1rpdd(), getD1rpdm(), getD1rpdy());
				 Utils.addIndivIndiv(em, getIdnr(), Id_I_MO, Id_I_FA, "DC D1", "Echtgenote", "Declared", "Exact", getD1rpdd(), getD1rpdm(), getD1rpdy());
			 }
			 else{
				 if(fatherLastname == true && motherLastname == true){
					 Utils.addIndivIndiv(em, getIdnr(), Id_I_FA, Id_I_MO, "DC D1", "Echtgenoot", "Missing", "Unavailable", 0, 0, 0);
					 Utils.addIndivIndiv(em, getIdnr(), Id_I_MO, Id_I_FA, "DC D1", "Echtgenote", "Missing", "Unavailable", 0, 0, 0);
				 }
			 }
			 
			 
	    	 // Mother address
	   		 
	   		 if(getD1molla() != null){

	   			 ContextElement ceAddress = null;

	   			 
	   			 if(getD1molla().getMunicipality() != null)
	   				ceAddress = Contxt.get2(getD1molla().getMunicipality());
	   			 else 
	   				ceAddress = ceCertificate;
	   			 
	   			 if(ceAddress != null){
	   			 
					int startDay1   = (new Integer(getD1molla().getStartDate().substring(0,2))).intValue();
					int startMonth1 = (new Integer(getD1molla().getStartDate().substring(3,5))).intValue();
					int startYear1  = (new Integer(getD1molla().getStartDate().substring(6,10))).intValue();

					Utils.addIndivContextAndContext(getD1molla().getQuarter(), getD1molla().getStreet(), getD1molla().getNumber(), getD1molla().getAddition(),
							ceAddress, em, getIdnr(), Id_I_MO, "DC D1",  "Address", "Declared", "Exact",  
							startDay1, startMonth1, startYear1);
	   			 }
	   			 
	   		 }
	   		 
		 }

		 // down the tree
		 
		 for(D2 d2: getD2L())
			 d2.convert(em);
		 
		 for(D3 d3: getD3L())
			 d3.convert(em);
		 
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
	public B1 getB1() {
		return b1;
	}
	public void setB1(B1 b1) {
		this.b1 = b1;
	}
	public ArrayList<D2> getD2L() {
		return d2L;
	}
	public void setD2L(ArrayList<D2> d2l) {
		d2L = d2l;
	}
	public ArrayList<D3> getD3L() {
		return d3L;
	}
	public void setD3L(ArrayList<D3> d3l) {
		d3L = d3l;
	}
	public D4 getD4() {
		return d4;
	}
	public void setD4(D4 d4) {
		this.d4 = d4;
	}
	public A1 getD1sdcla() {
		return d1sdcla;
	}
	public void setD1sdcla(A1 d1sdcla) {
		this.d1sdcla = d1sdcla;
	}
	public A1 getD1rpbla() {
		return d1rpbla;
	}
	public void setD1rpbla(A1 d1rpbla) {
		this.d1rpbla = d1rpbla;
	}
	public A1 getD1rplla() {
		return d1rplla;
	}
	public void setD1rplla(A1 d1rplla) {
		this.d1rplla = d1rplla;
	}
	public A1 getD1rpdla() {
		return d1rpdla;
	}
	public void setD1rpdla(A1 d1rpdla) {
		this.d1rpdla = d1rpdla;
	}
	public A1 getD1falla() {
		return d1falla;
	}
	public void setD1falla(A1 d1falla) {
		this.d1falla = d1falla;
	}
	public A1 getD1molla() {
		return d1molla;
	}
	public void setD1molla(A1 d1molla) {
		this.d1molla = d1molla;
	}
	public String getDC() {
		return DC;
	}
	public void setDC(String dC) {
		DC = dC;
	}
     
     
     
}
