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
@Table(name="d2")
public class D2 {

     @Column(name="IDNR")         private int idnr;
     @Column(name="D2S_SQ")       private int d2s_sq;
     @Column(name="D2S_LN")       private String d2s_ln;
     @Column(name="D2S_PF")       private String d2s_pf;
     @Column(name="D2S_FN")       private String d2s_fn;
     @Column(name="D2S_TT")       private String d2s_tt;
     @Column(name="D2S_PA")       private String d2s_pa;
     @Column(name="D2S_CA")       private String d2s_ca;
     @Column(name="D2S_AY")       private int d2s_ay;
     @Column(name="D2S_OC")       private String d2s_oc;
     @Column(name="D2S_LL")       private String d2s_ll;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY)  
     @Column(name="RecordID")     private int recordID;
     
     @Transient                   private D1   d1  = null;               
     @Transient                   private A1  d2s_lla;  
     
     public void convert(EntityManager em){
    	 
    	 // Former Spouse
    	 
		 int Id_I_FS = 53 + getD2s_sq(); // Former Spouse
		 
		  
		 
		 if(getD2s_ln() != null && getD2s_ln().trim().length() > 0 && !getD2s_ln().trim().equalsIgnoreCase("N")){
			 Utils.addIndiv(em, getIdnr(), Id_I_FS,  "DC D2", "LAST_NAME", getD2s_ln(), "Missing", "Time_invariant", 0, 0, 0);
		 }
		 else
			 return; 
		 
		 if(getD2s_pf() != null && getD2s_pf().trim().length() > 0){
			 Utils.addIndiv(em, getIdnr(), Id_I_FS,  "DC D2", "PREFIX_LAST_NAME", getD2s_pf(), "Missing", "Time_invariant", 0, 0, 0);
		 }
		 if(getD2s_fn() != null && getD2s_fn().trim().length() > 0){
			 Utils.addIndiv(em, getIdnr(), Id_I_FS,  "DC D2", "FIRST_NAME", getD2s_fn(), "Missing", "Time_invariant", 0, 0, 0);
		 }

		 if(getD1().getD1rpgn() != null && getD1().getD1rpgn().trim().equalsIgnoreCase("M"))
			 Utils.addIndiv(em, getIdnr(), Id_I_FS,  "DC D2", "SEX", "Female", "Missing", "Time_invariant", 0, 0, 0); // invert sex!
		 else
			 Utils.addIndiv(em, getIdnr(), Id_I_FS,  "DC D2", "SEX", "Male", "Missing", "Time_invariant", 0, 0, 0);
			 
		 
		 if(getD2s_ca() != null && getD2s_ca().equalsIgnoreCase("J")){

			 if(getD2s_oc() != null && getD2s_oc().trim().length() > 0)
				 Utils.addIndiv(em, getIdnr(), Id_I_FS,  "DC D2", "OCCUPATION_STANDARD", getD2s_oc(), "Declared", "Exact", getD1().getD1rpdd(), getD1().getD1rpdm(), getD1().getD1rpdy());
			 if(getD2s_ay() > 0){
				 Utils.addIndiv(em, getIdnr(), Id_I_FS,  "DC D2", "AGE_YEARS", (new Integer(getD2s_ay())).toString(), "Declared", "Exact", getD1().getD1rpdd(), getD1().getD1rpdm(), getD1().getD1rpdy());
				 if(Utils.dateIsValid(getD1().getD1rpdd(), getD1().getD1rpdm(), getD1().getD1rpdy()) == 0){
					 int[] a = Utils.birthRange(getD2s_ay(), getD1().getD1rpdd(), getD1().getD1rpdm(), getD1().getD1rpdy());
					 Utils.addIndiv(em, getIdnr(), Id_I_FS,  "DC D2", "BIRTH_DATE", null, "Assigned", "Age_based", a[0], a[1], a[2], a[3], a[4], a[5]);			 
				 }
			 }
			 else
				Utils.addIndiv(em, getIdnr(), Id_I_FS,  "DC D2", "BIRTH_DATE", null, "Assigned", "Estimated [15/100]", 1, 1, getD1().getD1sdcy() - 100, 1, 1,  getD1().getD1sdcy() - 15);


			 ContextElement ceCertificate = null;
			 if(getD1().getD1sdcc() > 0)
				 ceCertificate = Contxt.get(getD1().getD1sdcc());  // Look up name in Context System 
			 else
				 if(getD1().getD1sdcl() != null && getD1().getD1sdcl().trim().length() > 0)
					 ceCertificate = Contxt.get2(getD1().getD1sdcl());


			 //if(ceCertificate != null)
			//	 Utils.addIndivContextAndContext(getD2s_ll(), ceCertificate, em, getIdnr(), Id_I_FS, "DC D2", "", "Event", "Exact",
			//			 getD1().getD1rpdd(), getD1().getD1rpdm(), getD1().getD1rpdy());
			 
			 
			 // Address 
			 
			 
			 if(getD2s_lla() != null){

				 ContextElement ceAddress = null;

				 if(getD2s_lla().getMunicipality() != null)
					 ceAddress = Contxt.get2(getD2s_lla().getMunicipality());
				 else 
					 ceAddress = ceCertificate;

				 if(ceAddress != null){

					 int startDay1   = (new Integer(getD2s_lla().getStartDate().substring(0,2))).intValue();
					 int startMonth1 = (new Integer(getD2s_lla().getStartDate().substring(3,5))).intValue();
					 int startYear1  = (new Integer(getD2s_lla().getStartDate().substring(6,10))).intValue();

					 Utils.addIndivContextAndContext(getD2s_lla().getQuarter(), getD2s_lla().getStreet(), getD2s_lla().getNumber(), getD2s_lla().getAddition(),
							 ceAddress, em, getIdnr(), Id_I_FS,  "DC D2",  "Member", "Declared", "Exact",  
							 startDay1, startMonth1, startYear1);

				 }

			 }
		 }
		 else
			Utils.addIndiv(em, getIdnr(), Id_I_FS,  "DC D2", "BIRTH_DATE", null, "Assigned", "Estimated [15/100]", 1, 1, getD1().getD1sdcy() - 100, 1, 1,  getD1().getD1sdcy() - 15);

		 Utils.addIndiv(em, getIdnr(), Id_I_FS,  "DC D2", "HSN_IDENTIFIER", "" + getIdnr(), "Missing", "Time_invariant", 0, 0, 0);


		 // Relation to RP Is former spouse RP

		 if(getD2s_ca() != null && getD2s_ca().trim().equalsIgnoreCase("J")){
			 if(getD1().getB1().getB1rpgn().equalsIgnoreCase("M")){
				 Utils.addIndivIndiv(em, getIdnr(), 51, Id_I_FS,  "DC D2", "Echtgenoot", "Declared", "Exact", getD1().getD1rpdd(), getD1().getD1rpdm(), getD1().getD1rpdy());
				 Utils.addIndivIndiv(em, getIdnr(), Id_I_FS, 51,  "DC D2", "Echtgenote", "Declared", "Exact", getD1().getD1rpdd(), getD1().getD1rpdm(), getD1().getD1rpdy());
				 
				 Utils.addIndiv(em, getIdnr(), Id_I_FS, "DC D2", "HSN_RESEARCH_PERSON", "Wife RP", "Declared", "Exact", getD1().getD1rpdd(), getD1().getD1rpdm(), getD1().getD1rpdy());

			 }
			 else{
				 Utils.addIndivIndiv(em, getIdnr(), 51, Id_I_FS,  "DC D2", "Echtgenote", "Declared", "Exact", getD1().getD1rpdd(), getD1().getD1rpdm(), getD1().getD1rpdy());
				 Utils.addIndivIndiv(em, getIdnr(), Id_I_FS, 51,  "DC D2", "Echtgenoot", "Declared", "Exact", getD1().getD1rpdd(), getD1().getD1rpdm(), getD1().getD1rpdy());
				 
				 Utils.addIndiv(em, getIdnr(), Id_I_FS, "DC D2", "HSN_RESEARCH_PERSON", "Husband RP", "Declared", "Exact", getD1().getD1rpdd(), getD1().getD1rpdm(), getD1().getD1rpdy());

			 }
		 }
		 else{
			 if(getD1().getB1().getB1rpgn().equalsIgnoreCase("M")){
				 Utils.addIndivIndiv(em, getIdnr(), 51, Id_I_FS,  "DC D2", "Echtgenoot", "Missing", "Unavailable", 0, 0,0 );
				 Utils.addIndivIndiv(em, getIdnr(), Id_I_FS, 51,  "DC D2", "Echtgenote", "Missing", "Unavailable", 0, 0,0 );
				 
				 Utils.addIndiv(em, getIdnr(), Id_I_FS, "DC D2", "HSN_RESEARCH_PERSON", "Wife RP", "Missing", "Time_invariant", 0, 0, 0);

			 }
			 else{
				 Utils.addIndivIndiv(em, getIdnr(), 51, Id_I_FS,  "DC D2", "Echtgenote", "Missing", "Unavailable", 0, 0,0 );
				 Utils.addIndivIndiv(em, getIdnr(), Id_I_FS, 51,  "DC D2", "Echtgenoot", "Missing", "Unavailable", 0, 0,0 );
				 
				 Utils.addIndiv(em, getIdnr(), Id_I_FS, "DC D2", "HSN_RESEARCH_PERSON", "Husband RP", "Missing", "Time_invariant", 0, 0, 0);

			 }
		 }
     }

     
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public int getD2s_sq() {
		return d2s_sq;
	}
	public void setD2s_sq(int d2s_sq) {
		this.d2s_sq = d2s_sq;
	}
	public String getD2s_ln() {
		return d2s_ln;
	}
	public void setD2s_ln(String d2s_ln) {
		this.d2s_ln = d2s_ln;
	}
	public String getD2s_pf() {
		return d2s_pf;
	}
	public void setD2s_pf(String d2s_pf) {
		this.d2s_pf = d2s_pf;
	}
	public String getD2s_fn() {
		return d2s_fn;
	}
	public void setD2s_fn(String d2s_fn) {
		this.d2s_fn = d2s_fn;
	}
	public String getD2s_tt() {
		return d2s_tt;
	}
	public void setD2s_tt(String d2s_tt) {
		this.d2s_tt = d2s_tt;
	}
	public String getD2s_pa() {
		return d2s_pa;
	}
	public void setD2s_pa(String d2s_pa) {
		this.d2s_pa = d2s_pa;
	}
	public String getD2s_ca() {
		return d2s_ca;
	}
	public void setD2s_ca(String d2s_ca) {
		this.d2s_ca = d2s_ca;
	}
	public int getD2s_ay() {
		return d2s_ay;
	}
	public void setD2s_ay(int d2s_ay) {
		this.d2s_ay = d2s_ay;
	}
	public String getD2s_oc() {
		return d2s_oc;
	}
	public void setD2s_oc(String d2s_oc) {
		this.d2s_oc = d2s_oc;
	}
	public String getD2s_ll() {
		return d2s_ll;
	}
	public void setD2s_ll(String d2s_ll) {
		this.d2s_ll = d2s_ll;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}
	public D1 getD1() {
		return d1;
	}
	public void setD1(D1 d1) {
		this.d1 = d1;
	}


	public A1 getD2s_lla() {
		return d2s_lla;
	}


	public void setD2s_lla(A1 d2s_lla) {
		this.d2s_lla = d2s_lla;
	}

	
     
}
