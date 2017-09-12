package nl.iisg.convertPK;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import nl.iisg.ref.Ref;
import nl.iisg.ref.Ref_Profession;
 
@Entity
@Table(name="pkbrp")
public class PkBrp {
	
@Id  @Column(name="IDNR")       private int     idnr; 		  // ID Number
	 @Column(name="VGNRBRP")    private int     vgnrbrp;      // Sequence number for different profession of person
	 @Column(name="BRPPOSP")    private String  brpposp;      // Position in profession herarchy
	 @Column(name="BEROEPP")    private String  beroepp;      // Profession
	 @Column(name="DHBRPP")     private String  dhbrppp;      // Profession is crosssed over (doorgehaald)
	 
	 @Column(name="OPDRNR")     private String  opdrnr;  	  // order number
	 @Column(name="DATUM")      private String  datum;        // date
	 @Column(name="INIT")       private String  init;         // initials 
	 @Column(name="VERSIE")     private String  versie;       // version
	 @Column(name="ONDRZKO")    private String  onderzko;     // original research
	 @Column(name="OPDRNRO")    private String  opdrnro;      // original order number  
	 @Column(name="DATUMO")     private String  datumo;       // original date   
	 @Column(name="INITO")      private String  inito;        // original initials    
	 @Column(name="VERSIEO")    private String  versieo;      // original version

    // Next come object fields that are not saved. 
    // They are marked "Transient"
    
    @Transient                 private PkKnd    pkHolder;      // PK-Holder
    
// No-arguments constructor is necessary
    
    public PkBrp(){
    	
    }
    
    
    public boolean convert(B35_ST b35){
    	
    	if(getBeroepp() == null || getBeroepp().trim().length() == 0)
    		return false;
    	
    	String profession = getBeroepp();
    	
    	boolean ex = false;
    	if(profession.indexOf("*ex") >= 0){
    		ex = true;
    		profession.replaceAll("*ex", "");
    	}
    	
    	ArrayList a = Utils.standardizeProfession(profession);
    	
    	b35.setOccupationStandardized((String)a.get(0));
    	b35.setOccupationID((Integer)a.get(1));
    	
    	String position = getBrpposp();
    	
    	int posFlag = 0;
    	if(position == null || position.equalsIgnoreCase("n")){
    		if(ex == true)
    			posFlag = 13;
    		else
    			posFlag = 3;
    	}
    	else{
    		if(position != null && position.equalsIgnoreCase("o")){
    			if(ex == true)
    				posFlag = 12;
    			else
    				posFlag = 2;
    		}
    		else
    			if(position != null && position.equalsIgnoreCase("h")){
    				if(ex == true)
    					posFlag = 11;
    				else
    					posFlag = 1;
    			}
    	}
    	
    	b35.setOccupationFlag(posFlag);
    	return true;
    	
    	
    }

   


	public int getIdnr() {
		return idnr;
	}


	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}


	public int getVgnrbrp() {
		return vgnrbrp;
	}


	public void setVgnrbrp(int vgnrbrp) {
		this.vgnrbrp = vgnrbrp;
	}


	public String getBrpposp() {
		return brpposp;
	}


	public void setBrpposp(String brpposp) {
		this.brpposp = brpposp;
	}


	public String getBeroepp() {
		return beroepp;
	}


	public void setBeroepp(String beroepp) {
		this.beroepp = beroepp;
	}


	public String getDhbrppp() {
		return dhbrppp;
	}


	public void setDhbrppp(String dhbrppp) {
		this.dhbrppp = dhbrppp;
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
