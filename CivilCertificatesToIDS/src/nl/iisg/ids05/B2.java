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
@Table(name="b2")
public class B2 { 

     @Column(name="IDNR")         private int idnr;
     @Column(name="B2W_SQ")       private int b2w_sq;
     @Column(name="B2W_LN")       private String b2w_ln;
     @Column(name="B2W_PF")       private String b2w_pf;
     @Column(name="B2W_FN")       private String b2w_fn;
     @Column(name="B2W_TT")       private String b2w_tt;
     @Column(name="B2W_PA")       private String b2w_pa;
     @Column(name="B2W_AY")       private int b2w_ay;
     @Column(name="B2W_OC")       private String b2w_oc;
     @Column(name="B2W_LL")       private String b2w_ll;
     @Column(name="B2W_SG")       private String b2w_sg;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")    private int recordID;
     
     @Transient                  private   B1   b1   = null;               

     @Transient                   private A1  b2w_lla;
     
     public void convert(EntityManager em){
    	 
    	 // Witness
    	 
		 int Id_I_WN = 4 + getB2w_sq(); //Utils.getId_I();
		
		 if(getB2w_ln() != null && getB2w_ln().trim().length() > 0 && !getB2w_ln().trim().equalsIgnoreCase("N"))
			 Utils.addIndiv(em, getIdnr(), Id_I_WN, "BC B2", "LAST_NAME", getB2w_ln(), "Missing", "Time_invariant", getB1().getB1sdcd(), getB1().getB1sdcm(), getB1().getB1sdcy());
		 else
			 return;
		 if(getB2w_pf() != null && getB2w_pf().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_WN, "BC B2", "PREFIX_LAST_NAME", getB2w_pf(), "Missing", "Time_invariant", getB1().getB1sdcd(), getB1().getB1sdcm(), getB1().getB1sdcy());
		 if(getB2w_fn() != null && getB2w_fn().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_WN, "BC B2", "FIRST_NAME", getB2w_fn(), "Missing", "Time_invariant", getB1().getB1sdcd(), getB1().getB1sdcm(), getB1().getB1sdcy());

    	 ContextElement ceCertificate = null;
    	 if(getB1().getB1sdcl() != null && getB1().getB1sdcl().trim().length() > 0)
    		 ceCertificate = Contxt.get2(getB1().getB1sdcl());  // Look up name in Context System
    		 
    	 //System.out.println("CECertificate = " + ceCertificate); // XYZ

    	 if(ceCertificate != null){    		 
    		 Utils.addIndivContextAndContextCertificate(getB1().getB1sdcy(), getB1().getB1sdcn(), ceCertificate, em, getIdnr(), Id_I_WN, 
    				 "Birth Certificate", "BC B2", "Witness", "Event", "Exact", getB1().getB1sdcd(), getB1().getB1sdcm(), getB1().getB1sdcy());
    		 //System.out.println("---> " +getB2w_lla());
    		 //Utils.addIndivContextAndContext(getB2w_ll(), ceCertificate, em, getIdnr(), Id_I_WN, "BC B2", "", "Event", "Exact", getB1().getB1sdcd(), getB1().getB1sdcm(), getB1().getB1sdcy());
    		 if(getB2w_lla() != null){

    	    	 

    			 int startDay1   = (new Integer(getB2w_lla().getStartDate().substring(0,2))).intValue();
    			 int startMonth1 = (new Integer(getB2w_lla().getStartDate().substring(3,5))).intValue();
    			 int startYear1  = (new Integer(getB2w_lla().getStartDate().substring(6,10))).intValue();

    			 //System.out.println("Adding CECertificate = " + ceCertificate); // XYZ
    			 
    			 //Utils.addIndivContextAndContext(getB2w_lla().getQuarter(), getB2w_lla().getStreet(), getB2w_lla().getNumber(), getB2w_lla().getAddition(),
    			 //		 ceCertificate, em, getIdnr(), Id_I_WN, "BC B2",  "Address", "Declared", "Exact",  
    			 //		 startDay1, startMonth1, startYear1);
    			 
				Utils.addIndivAndContext(getB2w_lla().getQuarter(), getB2w_lla().getStreet(), getB2w_lla().getNumber(), getB2w_lla().getAddition(),
						ceCertificate, em, getIdnr(), Id_I_WN, "BC B2",  "RESIDENCE_LOCATION", "Event", "Exact",  
								startDay1, startMonth1, startYear1);

    		 }
    	 }
		 
		 if(getB2w_ay() > 0){
			 Utils.addIndiv(em, getIdnr(), Id_I_WN, "BC B2", "AGE_YEARS", (new Integer(getB2w_ay())).toString(), "Declared", "Exact", getB1().getB1sdcd(), getB1().getB1sdcm(), getB1().getB1sdcy());
			 if(Utils.dateIsValid(getB1().getB1sdcd(), getB1().getB1sdcm(), getB1().getB1sdcy()) == 0){
				 int[] a = Utils.birthRange(getB2w_ay(), getB1().getB1sdcd(), getB1().getB1sdcm(), getB1().getB1sdcy());
				 Utils.addIndiv(em, getIdnr(), Id_I_WN, "BC B2", "BIRTH_DATE", null, "Assigned", "Age_based",  a[0], a[1], a[2], a[3], a[4], a[5]);
			 }
		 }
		 else
			Utils.addIndiv(em, getIdnr(), Id_I_WN, "BC B2", "BIRTH_DATE", null, "Assigned", "Estimated [16/100]", 1, 1, getB1().getB1sdcy() - 100, 1, 1, getB1(). getB1sdcy() - 16);

		 if(getB2w_oc() != null && getB2w_oc().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_WN, "BC B2", "OCCUPATION_STANDARD", getB2w_oc(), "Declared", "Exact", getB1().getB1sdcd(), getB1().getB1sdcm(), getB1().getB1sdcy());
		 if(getB2w_sg() != null && getB2w_sg().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_WN, "BC B2", "SIGNATURE", Utils.signature(getB2w_sg()), "Event", "Exact", getB1().getB1sdcd(), getB1().getB1sdcm(), getB1().getB1sdcy());
         Utils.addIndiv(em, getIdnr(), Id_I_WN, "BC B2", "HSN_IDENTIFIER", "" + getIdnr(), "Missing", "Time_invariant", 0, 0, 0);


		 // Witness relations
		 
		 Utils.addIndivIndiv(em, getIdnr(), Id_I_WN, 1, "BC B2", "Onbekend", "Event", "Exact", getB1().getB1rpbd(), getB1().getB1rpbm(), getB1().getB1rpby()); // Witness and newly-born RP
		 Utils.addIndivIndiv(em, getIdnr(), 1, Id_I_WN, "BC B2", "Onbekend", "Event", "Exact", getB1().getB1rpbd(), getB1().getB1rpbm(), getB1().getB1rpby()); // Witness and newly-born RP

     }
     
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public int getB2w_sq() {
		return b2w_sq;
	}
	public void setB2w_sq(int b2w_sq) {
		this.b2w_sq = b2w_sq;
	}
	public String getB2w_ln() {
		return b2w_ln;
	}
	public void setB2w_ln(String b2w_ln) {
		this.b2w_ln = b2w_ln;
	}
	public String getB2w_pf() {
		return b2w_pf;
	}
	public void setB2w_pf(String b2w_pf) {
		this.b2w_pf = b2w_pf;
	}
	public String getB2w_fn() {
		return b2w_fn;
	}
	public void setB2w_fn(String b2w_fn) {
		this.b2w_fn = b2w_fn;
	}
	public String getB2w_tt() {
		return b2w_tt;
	}
	public void setB2w_tt(String b2w_tt) {
		this.b2w_tt = b2w_tt;
	}
	public String getB2w_pa() {
		return b2w_pa;
	}
	public void setB2w_pa(String b2w_pa) {
		this.b2w_pa = b2w_pa;
	}
	public int getB2w_ay() {
		return b2w_ay;
	}
	public void setB2w_ay(int b2w_ay) {
		this.b2w_ay = b2w_ay;
	}
	public String getB2w_oc() {
		return b2w_oc;
	}
	public void setB2w_oc(String b2w_oc) {
		this.b2w_oc = b2w_oc;
	}
	public String getB2w_ll() {
		return b2w_ll;
	}
	public void setB2w_ll(String b2w_ll) {
		this.b2w_ll = b2w_ll;
	}
	public String getB2w_sg() {
		return b2w_sg;
	}
	public void setB2w_sg(String b2w_sg) {
		this.b2w_sg = b2w_sg;
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

	public A1 getB2w_lla() {
		return b2w_lla;
	}

	public void setB2w_lla(A1 b2w_lla) {
		this.b2w_lla = b2w_lla;
	}
     
     
     
     
     
     
}