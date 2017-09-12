package nl.iisg.ids05;

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
     
     @Transient                  private           B1   b1   = null;               

     public void convert(EntityManager em){
    	 
    	 // Get earliest date
    	 
    	 int day = getB4sdcd();
    	 int month = getB4sdcm();
    	 int year = getB4sdcy();

    	 if(getB4sdct() == 1){
    		 day = getB4sdmd();
    		 month = getB4sdmm();
    		 year = getB4sdmy();
    	 }
    	 else{
        	 if(getB4sdct() == 2){
        		 day = getB4sdrd();
        		 month = getB4sdrm();
        		 year = getB4sdry();
        	 }
    	 }
    	 
    	 // Child RP
    	 
		 int Id_I_RP = 1; //Utils.getId_I();
		
		 if(getB4rpln() != null && getB4rpln().trim().length() > 0 && !getB4rpln().trim().equalsIgnoreCase("N"))
			 Utils.addIndiv(em, getIdnr(), Id_I_RP, "BC B4", "LAST_NAME", getB4rpln(), "Reported", "Exact", day, month, year);
		 else
			 return;
		 if(getB4rppf() != null && getB4rppf().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_RP, "BC B4", "PREFIX_LAST_NAME", getB4rppf(), "Reported", "Exact", day, month, year);
		 if(getB4rpfn() != null && getB4rpfn().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_RP, "BC B4", "FIRST_NAME", getB4rpfn(), "Reported", "Exact", day, month, year);
		 if(getB4rpgn() != null && getB4rpgn().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_RP, "BC B4", "SEX", Utils.sex(getB4rpgn()), "Reported", "Exact", day, month, year);
		 
		 // New Father
		 
		 int Id_I_FA = 9;
		 
		 if(getB4faln() != null && getB4faln().trim().length() > 0 && !getB4faln().trim().equalsIgnoreCase("N")){
			 Utils.addIndiv(em, getIdnr(), Id_I_FA, "BC B4", "LAST_NAME", getB4faln(), "Missing", "Time_invariant", day, month, year);
		 }
		 else
			 return;
		 if(getB4fapf() != null && getB4fapf().trim().length() > 0){
			 Utils.addIndiv(em, getIdnr(), Id_I_FA, "BC B4", "PREFIX_LAST_NAME",getB4fapf(), "Missing", "Time_invariant", day, month, year);
		 }
		 if(getB4fafn() != null && getB4fafn().trim().length() > 0){	 
			 Utils.addIndiv(em, getIdnr(), Id_I_FA, "BC B4", "FIRST_NAME", getB4fafn(), "Missing", "Time_invariant", day, month, year);
		 }
		 Utils.addIndiv(em, getIdnr(), Id_I_FA, "BC B4", "SEX", "Male", "Missing", "Time_invariant", 0, 0, 0);
		 
		 // We must give an estimated birthdate for linking purposes
		 
		 Utils.addIndiv(em, getIdnr(), Id_I_FA, "BC B4", "BIRTH_DATE", null, "Declared", "Estimated [15/100]", 1, 1, year - 100, 1, 1,   year - 15);


		 
		 Utils.addIndiv(em, getIdnr(), Id_I_FA, "BC B4", "MARRIAGE_DATE", null, "Reported", "Exact", day, month, year);
		 Utils.addIndiv(em, getIdnr(), 3      , "BC B4", "MARRIAGE_DATE", null, "Reported", "Exact", day, month, year); // also for mother

    	 // Get context element for marriage location
    	 
    	 ContextElement ceMarriage = null;
    	 if(getB4sdml() != null)
    		 ceMarriage = Contxt.get2(getB4sdml());
    	 
		 if(ceMarriage != null){
			 Utils.addIndivAndContext(null, ceMarriage, em, getIdnr(), Id_I_FA, "BC B4", "MARRIAGE_LOCATION", "Reported", "Exact", day, month, year);
			 Utils.addIndivAndContext(null, ceMarriage, em, getIdnr(), 3      , "BC B4", "MARRIAGE_LOCATION", "Reported", "Exact", day, month, year); // also for mother
		 }



		 // Father-Child relations

		 Utils.addIndivIndiv(em, getIdnr(), Id_I_FA, Id_I_RP, "BC B4", "Vader", "Missing", "Time_invariant", 0, 0, 0); 
		 
		String relation = "Kind";
		if(getB1().getB1rpgn().equalsIgnoreCase("M")) relation = "Zoon";
		else
			if(getB1().getB1rpgn().equalsIgnoreCase("V")) relation = "Dochter";

		 Utils.addIndivIndiv(em, getIdnr(), Id_I_RP, Id_I_FA, "BC B4", relation, "Missing", "Time_invariant", 0, 0, 0); 

		 // Spouses

		 Utils.addIndivIndiv(em, getIdnr(), Id_I_FA, 3, "BC B4", "Echtgenoot", "Reported", "Exact", day, month, year);
		 Utils.addIndivIndiv(em, getIdnr(), 3, Id_I_FA, "BC B4", "Echtgenote", "Reported", "Exact", day, month, year);

		 // Bind father to source context of marriage
		 
    	 ContextElement ceCertificate = null;
    	 if(getB1().getB1sdcc() > 0)
    		 ceCertificate = Contxt.get(getB1().getB1sdcc());  // Look up name in Context System

		 
		 if(ceCertificate != null){
			 Utils.addIndivContextAndContextCertificate(getB1().getB1sdcy(), getB1().getB1sdcn(), ceCertificate, em, getIdnr(), Id_I_FA, "BC B4", "Father", "Event", "Exact",
					 getB1().getB1sdcd(),  getB1().getB1sdcm(), getB1().getB1sdcy());
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
	public B1 getB1() {
		return b1;
	}
	public void setB1(B1 b1) {
		this.b1 = b1;
	}
     
     
     
     
}
