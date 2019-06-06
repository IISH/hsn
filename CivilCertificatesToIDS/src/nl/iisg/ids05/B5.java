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
     
     @Transient                  private  B1   b1   = null;         
     
     @Transient                   private A1  b5fasla;  
     @Transient                   private A1  b5fadla;
     @Transient                   private A1  b5falla;



     public void convert(EntityManager em){
    	 
    	 // Father
    	 
		 int Id_I_FA = 2; // Utils.getId_I();
    	 
		 if(getB5faln() != null && getB5faln().trim().length() > 0  && !getB5faln().trim().equalsIgnoreCase("N"))
			 Utils.addIndiv(em, getIdnr(), Id_I_FA, "BC B5", "LAST_NAME", getB5faln(), "Missing", "Time_invariant", getB1().getB1sdcd(), getB1().getB1sdcm(), getB1().getB1sdcy());
		 else
			 return;
		 if(getB5fapf() != null && getB5fapf().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_FA, "BC B5", "PREFIX_LAST_NAME", getB5fapf(), "Missing", "Time_invariant", getB1().getB1sdcd(), getB1().getB1sdcm(), getB1().getB1sdcy());
		 if(getB5fafn() != null && getB5fafn().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_FA, "BC B5", "FIRST_NAME", getB5fafn(), "Missing", "Time_invariant", getB1().getB1sdcd(), getB1().getB1sdcm(), getB1().getB1sdcy());
		 if(getB5faay() > 0){
			 Utils.addIndiv(em, getIdnr(), Id_I_FA, "BC B5", "AGE_YEARS", (new Integer(getB5faay())).toString(), "Declared", "Exact", getB1().getB1rpbd(), getB1().getB1rpbm(), getB1().getB1rpby());
			 if(Utils.dateIsValid(getB1().getB1sdcd(), getB1().getB1sdcm(), getB1().getB1sdcy()) == 0){
				 int[] a = Utils.birthRange(getB5faay(), getB1().getB1sdcd(), getB1().getB1sdcm(), getB1().getB1sdcy());
				 Utils.addIndiv(em, getIdnr(), Id_I_FA, "BC B5", "BIRTH_DATE", null, "Assigned", "Age_based", a[0], a[1], a[2], a[3], a[4], a[5]);
			 }
		 }
		 else
				Utils.addIndiv(em, getIdnr(), Id_I_FA, "BC B5", "BIRTH_DATE", null, "Assigned", "Estimated [16/100]", 1, 1, getB1().getB1sdcy() - 100, 1, 1,  getB1().getB1sdcy() - 16);

		 Utils.addIndiv(em, getIdnr(), Id_I_FA, "BC B5", "HSN_RESEARCH_PERSON", "Father RP", "Missing", "Time_invariant", 0, 0, 0);

		 
		 
		 Utils.addIndiv(em, getIdnr(), Id_I_FA, "BC B5", "SEX", "Male", "Missing", "Time_invariant", 0, 0,0); 
		 if(getB5faoc() != null && getB5faoc().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_FA, "BC B5", "OCCUPATION_STANDARD", getB5faoc(), "Declared", "Exact", getB1().getB1sdcd(), getB1().getB1sdcm(), getB1().getB1sdcy());

    	 ContextElement ceCertificate = null;
    	 if(getB1().getB1sdcl() != null && getB1().getB1sdcl().trim().length() > 0)
    		 ceCertificate = Contxt.get2(getB1().getB1sdcl());  // Look up name in Context System
		 
    	 if(ceCertificate != null){    	
    		 //if(getB5fall() != null && getB5fall().trim().length() > 0)
    		 //	 Utils.addIndivContextAndContext(getB5fall(), ceCertificate, em, getIdnr(), Id_I_FA, "BC B5", "", "Event", "Exact", getB1().getB1sdcd(), getB1().getB1sdcm(), getB1().getB1sdcy());

    		 if(getB5fasla() != null){

    			 int startDay1   = (new Integer(getB5fasla().getStartDate().substring(0,2))).intValue();
    			 int startMonth1 = (new Integer(getB5fasla().getStartDate().substring(3,5))).intValue();
    			 int startYear1  = (new Integer(getB5fasla().getStartDate().substring(6,10))).intValue();

    			 Utils.addIndivContextAndContext(getB5fasla().getQuarter(), getB5fasla().getStreet(), getB5fasla().getNumber(), getB5fasla().getAddition(),
    					 ceCertificate, em, getIdnr(), Id_I_FA, "BC B5 ",  "Divorce Location", "Reported", "Exact",  
    					 startDay1, startMonth1, startYear1);
    		 }

    		 if(getB5fadla() != null){

    			 int startDay1   = (new Integer(getB5fadla().getStartDate().substring(0,2))).intValue();
    			 int startMonth1 = (new Integer(getB5fadla().getStartDate().substring(3,5))).intValue();
    			 int startYear1  = (new Integer(getB5fadla().getStartDate().substring(6,10))).intValue();

    			 Utils.addIndivContextAndContext(getB5fadla().getQuarter(), getB5fadla().getStreet(), getB5fadla().getNumber(), getB5fadla().getAddition(),
    					 ceCertificate, em, getIdnr(), Id_I_FA, "BC B5 ",  "Death Location", "Reported", "Exact",  
    					 startDay1, startMonth1, startYear1);
    		 }

    		 if(getB5falla() != null){

    			 int startDay1   = (new Integer(getB5falla().getStartDate().substring(0,2))).intValue();
    			 int startMonth1 = (new Integer(getB5falla().getStartDate().substring(3,5))).intValue();
    			 int startYear1  = (new Integer(getB5falla().getStartDate().substring(6,10))).intValue();

    			 Utils.addIndivContextAndContext(getB5falla().getQuarter(), getB5falla().getStreet(), getB5falla().getNumber(), getB5falla().getAddition(),
    					 ceCertificate, em, getIdnr(), Id_I_FA, "BC B5",  "Address", "Declared", "Exact",  
    					 startDay1, startMonth1, startYear1);
    		 }

    		 Utils.addIndivContextAndContextCertificate(getB1().getB1sdcy(), getB1().getB1sdcn(), ceCertificate, em, getIdnr(), Id_I_FA, 
    				 "Birth Certificate", "BC B5", "Father", "Declared", "Exact", getB1().getB1sdcd(), getB1().getB1sdcm(), getB1().getB1sdcy());
			 

		 }

		 String cs = "Unknown";
		 if(getB1().getB1mocs() != null && getB1().getB1mocs().equals("4"))
				 cs = "Married";
			 else
				 if(getB1().getB1mocs() != null && getB1().getB1mocs().equals("3"))
					 cs = "Divorced";
		 Utils.addIndiv(em, getIdnr(), Id_I_FA, "BC B5", "CIVIL_STATUS", cs, "Declared", "Exact", getB1().getB1sdcd(), getB1().getB1sdcm(), getB1().getB1sdcy());


		 
		 if(getB5fasy() > 0){
			 Utils.addIndiv(em, getIdnr(), Id_I_FA, "BC B5", "DIVORCE_DATE", null, "Reported", "Exact", getB5fasd(),  getB5fasm(), getB5fasy());
			 Utils.addIndiv(em, getIdnr(), 3      , "BC B5", "DIVORCE_DATE", null, "Reported", "Exact", getB5fasd(),  getB5fasm(), getB5fasy());
		 }
		 
    	 //ContextElement ceDivorce = null;
    	 //if(getB5fasl() != null)
    	 //	 ceDivorce = Contxt.get2(getB5fasl());
    	 
    	 
		 //if(ceDivorce != null){
			// Utils.addIndivAndContext(null, ceDivorce, em, getIdnr(), Id_I_FA, "BC B5", "DIVORCE_LOCATION", "Reported", "Exact", getB5fasd(),  getB5fasm(), getB5fasy());
			 //Utils.addIndivAndContext(null, ceDivorce, em, getIdnr(), 3      , "BC B5", "DIVORCE_LOCATION", "Reported", "Exact", getB5fasd(),  getB5fasm(), getB5fasy());
		 //}

		 if(getB5fady() > 0){
			 Utils.addIndiv(em, getIdnr(), Id_I_FA, "BC B5", "DEATH_DATE", null, "Reported", "Exact", getB5fadd(),  getB5fadm(), getB5fady());
		 }
     	 Utils.addIndiv(em, getIdnr(), Id_I_FA, "BC B5", "HSN_IDENTIFIER", "" + getIdnr(), "Missing", "Time_invariant", 0, 0, 0);

		 
		 
    	 //ContextElement ceDeath = null;
    	 //if(getB5fadl() != null)
    		// ceDivorce = Contxt.get2(getB5fadl());
    	 
		 //if(ceDeath != null){
			// Utils.addIndivAndContext(null, ceDeath, em, getIdnr(), Id_I_FA, "BC B5", "DEATH_LOCATION", "Reported", "Exact", getB5fasd(),  getB5fasm(), getB5fasy());
		 //}

		 
		 
		 // Father relations
		 
		 Utils.addIndivIndiv(em, getIdnr(), Id_I_FA, 1, "BC B5", "Vader", "Missing", "Time_invariant", 0, 0,0); 
		 
		String relation = "Kind";
		if(getB1().getB1rpgn().equalsIgnoreCase("M")) relation = "Zoon";
		else
			if(getB1().getB1rpgn().equalsIgnoreCase("V")) relation = "Dochter";


		 Utils.addIndivIndiv(em, getIdnr(), 1, Id_I_FA, "BC B5", relation, "Missing", "Time_invariant", 0, 0,0); 
    	 

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
	public B1 getB1() {
		return b1;
	}
	public void setB1(B1 b1) {
		this.b1 = b1;
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