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
@Table(name="d3")
public class D3 {

     @Column(name="IDNR")         private int idnr;
     @Column(name="D3I_SQ")       private int d3i_sq;
     @Column(name="D3I_LN")       private String d3i_ln;
     @Column(name="D3I_PF")       private String d3i_pf;
     @Column(name="D3I_FN")       private String d3i_fn; 
     @Column(name="D3I_TT")       private String d3i_tt;
     @Column(name="D3I_PA")       private String d3i_pa;
     @Column(name="D3I_LS")       private String d3i_ls;
     @Column(name="D3I_AY")       private int d3i_ay;
     @Column(name="D3I_OC")       private String d3i_oc;
     @Column(name="D3I_LL")       private String d3i_ll;
     @Column(name="D3I_SG")       private String d3i_sg;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")     private int recordID;
     
     @Transient                   private D1   d1  = null;  
     @Transient                   private A1  d3i_lla;  

     public void convert(EntityManager em){
    	 
    	 // Informer
    	 
		 int Id_I_IN = 60 + getD3i_sq(); // Informer
		 
		 if(getD3i_ln() != null && getD3i_ln().trim().length() > 0 && !getD3i_ln().trim().equalsIgnoreCase("N"))
			 Utils.addIndiv(em, getIdnr(), Id_I_IN, "DC D3", "LAST_NAME", getD3i_ln(), "Missing", "Time_invariant", 0, 0, 0);
		 else
			 return;
		 if(getD3i_pf() != null && getD3i_pf().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_IN, "DC D3", "PREFIX_LAST_NAME", getD3i_pf(), "Missing", "Time_invariant", 0, 0, 0);
		 if(getD3i_fn() != null && getD3i_fn().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_IN, "DC D3", "FIRST_NAME", getD3i_fn(), "Missing", "Time_invariant", 0, 0, 0);
		 if(getD3i_oc() != null && getD3i_oc().trim().length() > 0)
			 Utils.addIndiv(em, getIdnr(), Id_I_IN, "DC D3", "OCCUPATION_STANDARD", getD3i_oc(), "Declared", "Exact", getD1().getD1rpdd(), getD1().getD1rpdm(), getD1().getD1rpdy());
		 if(getD3i_ay() > 0 && getD3i_ay() < 120){  // because there is a strange value age = 558 in D3
			 Utils.addIndiv(em, getIdnr(), Id_I_IN, "DC D3", "AGE_YEARS", (new Integer(getD3i_ay())).toString(), "Declared", "Exact", getD1().getD1rpdd(), getD1().getD1rpdm(), getD1().getD1rpdy());
			 if(Utils.dateIsValid(getD1().getD1rpdd(), getD1().getD1rpdm(), getD1().getD1rpdy()) == 0){
				 int[] a = Utils.birthRange(getD3i_ay(), getD1().getD1rpdd(), getD1().getD1rpdm(), getD1().getD1rpdy());
				 Utils.addIndiv(em, getIdnr(), Id_I_IN, "DC D3", "BIRTH_DATE", null, "Declared", "Age_based", a[0], a[1], a[2], a[3], a[4], a[5]);			 
			 }
		 }
		 else
			Utils.addIndiv(em, getIdnr(), Id_I_IN, "DC D3", "BIRTH_DATE", null, "Declared", "Estimated [18/100]", 1, 1, getD1().getD1sdcy() - 100, 1, 1,  getD1().getD1sdcy() - 18);

		 Utils.addIndiv(em, getIdnr(), Id_I_IN, "DC D3", "SIGNATURE", Utils.signature(getD3i_sg()), "Event", "Exact", getD1().getD1rpdd(), getD1().getD1rpdm(), getD1().getD1rpdy());
		 
         Utils.addIndiv(em, getIdnr(), Id_I_IN, "DC D3", "HSN_IDENTIFIER", "" + getIdnr(), "Missing", "Time_invariant", 0, 0, 0);

		 
    	 ContextElement ceCertificate = null;
    	 if(getD1().getD1sdcc() > 0)
    		 ceCertificate = Contxt.get(getD1().getD1sdcc());  // Look up name in Context System 
    	 else
    		 if(getD1().getD1sdcl() != null && getD1().getD1sdcl().trim().length() > 0)
    			 ceCertificate = Contxt.get2(getD1().getD1sdcl());

		 if(ceCertificate != null){
			 
	    	 //String death_certificate = "Death Certificate";
	    	 String death_certificate = "DC D3";
			 if(getD1().getD1sdce() != null && getD1().getD1sdce().equalsIgnoreCase("J"))
				 death_certificate = "DC D3"; //"Extract Death Certificate"; 
			 
			 //Utils.addIndivContextAndContext(getD3i_ll(), ceCertificate, em, getIdnr(), Id_I_IN, "DC D3", "", "Declared", "Exact", getD1().getD1rpdd(), getD1().getD1rpdm(), getD1().getD1rpdy());
			 Utils.addIndivContextAndContextCertificate(getD1().getD1sdcy(), getD1().getD1sdcn(), ceCertificate, em, getIdnr(), Id_I_IN, death_certificate, 
					 "Unknown", "Event", "Exact", getD1().getD1rpdd(), getD1().getD1rpdm(), getD1().getD1rpdy());
			 
			 // Address
			 
			 if(getD3i_lla() != null){

				 ContextElement ceAddress = null;

				 if(getD3i_lla().getMunicipality() != null)
					 ceAddress = Contxt.get2(getD3i_lla().getMunicipality());
				 else 
					 ceAddress = ceCertificate;

				 if(ceAddress != null){

					 int startDay1   = (new Integer(getD3i_lla().getStartDate().substring(0,2))).intValue();
					 int startMonth1 = (new Integer(getD3i_lla().getStartDate().substring(3,5))).intValue();
					 int startYear1  = (new Integer(getD3i_lla().getStartDate().substring(6,10))).intValue();

					 Utils.addIndivContextAndContext(getD3i_lla().getQuarter(), getD3i_lla().getStreet(), getD3i_lla().getNumber(), getD3i_lla().getAddition(),
							 ceAddress, em, getIdnr(), Id_I_IN, "DC D3",  "Address", "Reported", "Exact",  
							 startDay1, startMonth1, startYear1);

				 }

			 }
		 }

		 // Relation to RP 
    	 
    	 String informer          = "Onbekend";
    	 String reciproceInformer = "Onbekend";
    	 
    	 if(getD3i_ls() != null && getD3i_ls().trim().length() > 0 && !getD3i_ls().trim().equalsIgnoreCase("N")){
    		 informer = getD3i_ls().trim();
    		 reciproceInformer = Utils.findReciproke(informer, getD1().getD1rpgn());
    	 }
		 Utils.addIndivIndiv(em, getIdnr(), Id_I_IN, 51, "DC D3",  informer , "Event", "Exact", getD1().getD1rpdd(), getD1().getD1rpdm(), getD1().getD1rpdy()); // Deceased RP and Informer
		 Utils.addIndivIndiv(em, getIdnr(), 51, Id_I_IN, "DC D3",  reciproceInformer, "Event", "Exact", getD1().getD1rpdd(), getD1().getD1rpdm(), getD1().getD1rpdy()); // Deceased RP and Informer
		 
     }
     

     
     
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public int getD3i_sq() {
		return d3i_sq;
	}
	public void setD3i_sq(int d3i_sq) {
		this.d3i_sq = d3i_sq;
	}
	public String getD3i_ln() {
		return d3i_ln;
	}
	public void setD3i_ln(String d3i_ln) {
		this.d3i_ln = d3i_ln;
	}
	public String getD3i_pf() {
		return d3i_pf;
	}
	public void setD3i_pf(String d3i_pf) {
		this.d3i_pf = d3i_pf;
	}
	public String getD3i_fn() {
		return d3i_fn;
	}
	public void setD3i_fn(String d3i_fn) {
		this.d3i_fn = d3i_fn;
	}
	public String getD3i_tt() {
		return d3i_tt;
	}
	public void setD3i_tt(String d3i_tt) {
		this.d3i_tt = d3i_tt;
	}
	public String getD3i_pa() {
		return d3i_pa;
	}
	public void setD3i_pa(String d3i_pa) {
		this.d3i_pa = d3i_pa;
	}
	public String getD3i_ls() {
		return d3i_ls;
	}
	public void setD3i_ls(String d3i_ls) {
		this.d3i_ls = d3i_ls;
	}
	public int getD3i_ay() {
		return d3i_ay;
	}
	public void setD3i_ay(int d3i_ay) {
		this.d3i_ay = d3i_ay;
	}
	public String getD3i_oc() {
		return d3i_oc;
	}
	public void setD3i_oc(String d3i_oc) {
		this.d3i_oc = d3i_oc;
	}
	public String getD3i_ll() {
		return d3i_ll;
	}
	public void setD3i_ll(String d3i_ll) {
		this.d3i_ll = d3i_ll;
	}
	public String getD3i_sg() {
		return d3i_sg;
	}
	public void setD3i_sg(String d3i_sg) {
		this.d3i_sg = d3i_sg;
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




	public A1 getD3i_lla() {
		return d3i_lla;
	}




	public void setD3i_lla(A1 d3i_lla) {
		this.d3i_lla = d3i_lla;
	}
     
     
     
     
     
}
