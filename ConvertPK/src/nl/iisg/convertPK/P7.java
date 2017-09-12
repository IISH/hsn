package nl.iisg.convertPK;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="p7") 
public class P7 {
	
	 @Id  @Column(name="IDNR")      private  int      idnr; 		    // ID Number
     @Column(name="IDNRP")          private  int      idnrp;  	        // IDNR partner
     @Column(name="P7IDBG")         private  int      p7idbg;  		    // Original identifier for the Personal List from CBG itself (only kept for checks on consistency)
	 @Column(name="P7OPOG")         private  String   p7opog;           // Place of death on PL-lists (municipality and country distinguished)
	 @Column(name="P7OPOL")         private  String   p7opol;           // Place of death on PL-lists (municipality and country distinguished)
	 @Column(name="P7OPOR")         private  String   p7opor;           // Municipality of certificate; rarely used only in case of foreign places of death
	 @Column(name="P7OPOB")         private  String   p7opob;           // Administrative code of the death certificate or other indication; comparable with field oakperp (to be checked); only in combination with p7opor
	 @Column(name="P7OPIO")         private  String   p7opio;           // indicatie onjuist; Not clear what this variable means (only sporadically content)
	 @Column(name="P7OPPG")         private  String   p7oppg;           // Municipality where the Personal List was put up 
	 @Column(name="P7OPPC")         private  String   p7oppc;           // Affirmation of the Personal Card was converted
	 @Column(name="OPDRNR")         private  String   opdrnr;           // order number
	 @Column(name="DATUM")          private  String   datum;            // date
	 @Column(name="INIT")           private  String   init;             // initials 
	 @Column(name="VERSIE")         private  String   versie;           // version
	 @Column(name="ONDRZKO")        private  String   onderzko;         // original research
	 @Column(name="OPDRNRO")        private  String   opdrnro;          // original order number  
	 @Column(name="DATUMO")         private  String   datumo;           // original date   
	 @Column(name="INITO")          private  String   inito;            // original initials    
	 @Column(name="VERSIEO")        private  String   versieo;          // original version
	 
     @Transient 	                private  PkKnd    pkHolder;         // PK-Holder

	 
	// No-arguments constructor is necessary
	    
	public P7(){}
	
	public void convert(B6_ST b6){
		
		
		
		
		
	}
	 
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public int getIdnrp() {
		return idnrp;
	}
	public void setIdnrp(int idnrp) {
		this.idnrp = idnrp;
	}
	public int getP7idbg() {
		return p7idbg;
	}
	public void setP7idbg(int p7idbg) {
		this.p7idbg = p7idbg;
	}
	public String getP7opog() {
		return p7opog;
	}
	public void setP7opog(String p7opog) {
		this.p7opog = p7opog;
	}
	public String getP7opol() {
		return p7opol;
	}
	public void setP7opol(String p7opol) {
		this.p7opol = p7opol;
	}
	public String getP7opor() {
		return p7opor;
	}
	public void setP7opor(String p7opor) {
		this.p7opor = p7opor;
	}
	public String getP7opob() {
		return p7opob;
	}
	public void setP7opob(String p7opob) {
		this.p7opob = p7opob;
	}
	public String getP7opio() {
		return p7opio;
	}
	public void setP7opio(String p7opio) {
		this.p7opio = p7opio;
	}
	public String getP7oppg() {
		return p7oppg;
	}
	public void setP7oppg(String p7oppg) {
		this.p7oppg = p7oppg;
	}
	public String getP7oppc() {
		return p7oppc;
	}
	public void setP7oppc(String p7oppc) {
		this.p7oppc = p7oppc;
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
