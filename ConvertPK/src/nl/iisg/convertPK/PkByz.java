package nl.iisg.convertPK;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
 
@Entity
@Table(name="pkbyz")
public class PkByz {
	
	 @Id  @Column(name="IDNR")      private int     idnr; 	      // ID Number
	 @Column(name="BYZNR")          private int     byznr;  	  // sequence number
	 @Column(name="BYZ")            private String  byz;     	  // details
	 @Column(name="SCHERM")         private String  scherm;       // screen identifier in data input application	 
	 @Column(name="OPDRNR")         private String  opdrnr;  	  // order number
	 @Column(name="DATUM")          private String  datum;        // date
	 @Column(name="INIT")           private String  init;         // initials 
	 @Column(name="VERSIE")         private String  versie;       // version
	 @Column(name="ONDRZKO")        private String  onderzko;     // original research
	 @Column(name="OPDRNRO")        private String  opdrnro;      // original order number  
	 @Column(name="DATUMO")         private String  datumo;       // original date   
	 @Column(name="INITO")          private String  inito;        // original initials    
	 @Column(name="VERSIEO")        private String  versieo;      // original version
	 
     @Transient 	                private PkKnd   pkHolder;      // PK-Holder

	 
	// No-arguments constructor is necessary
	    
    public PkByz(){
	    	
    }
	 
	 
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public int getByznr() {
		return byznr;
	}
	public void setByznr(int byznr) {
		this.byznr = byznr;
	}
	public String getByz() {
		return byz;
	}
	public void setByz(String byz) {
		this.byz = byz;
	}
	public String getScherm() {
		return scherm;
	}
	public void setScherm(String scherm) {
		this.scherm = scherm;
	}
	public String getOpdrnr() {
		return opdrnr;
	}
	public void setOpdrnr(String opdrnr) {
		this.opdrnr = opdrnr;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getInit() {
		return init;
	}
	public void setInit(String init) {
		this.init = init;
	}
	public String getVersie() {
		return versie;
	}
	public void setVersie(String versie) {
		this.versie = versie;
	}
	public String getOnderzko() {
		return onderzko;
	}
	public void setOnderzko(String onderzko) {
		this.onderzko = onderzko;
	}
	public String getOpdrnro() {
		return opdrnro;
	}
	public void setOpdrnro(String opdrnro) {
		this.opdrnro = opdrnro;
	}
	public String getDatumo() {
		return datumo;
	}
	public void setDatumo(String datumo) {
		this.datumo = datumo;
	}
	public String getInito() {
		return inito;
	}
	public void setInito(String inito) {
		this.inito = inito;
	}
	public String getVersieo() {
		return versieo;
	}
	public void setVersieo(String versieo) {
		this.versieo = versieo;
	}


	public PkKnd getPkHolder() {
		return pkHolder;
	}


	public void setPkHolder(PkKnd pkHolder) {
		this.pkHolder = pkHolder;
	}
	 
	 


}
