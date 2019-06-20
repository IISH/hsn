package iisg.nl.hsnimport;

import iisg.nl.hsnmigrate.Utils;
import iisg.nl.hsnnieuw.M1;
import iisg.nl.hsnnieuw.M2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="huwafk")
public class Huwafk  {

     @Column(name="IDNR")       private int idnr;
     @Column(name="HDAG")       private int hdag;
     @Column(name="HMAAND")     private int hmaand;
     @Column(name="HJAAR")      private int hjaar;
     @Column(name="HVLGNR")     private int hvlgnr;
     @Column(name="HWAKNR")     private int hwaknr;
     @Column(name="HWAKDG")     private int hwakdg;
     @Column(name="HWAKMD")     private int hwakmd;
     @Column(name="HWAKJR")     private int hwakjr;
     @Column(name="HWAKGR")     private int hwakgr;
     @Column(name="HWAKPL")     private String hwakpl;
     @Column(name="ARCH")       private String arch;
     @Column(name="OPDRNR")     private String opdrnr;
     @Column(name="DATUM")      private String datum;
     @Column(name="INIT")       private String init;
     @Column(name="VERSIE")     private String versie;
     @Column(name="ONDRZKO")    private String ondrzko;
     @Column(name="ARCHO")      private String archo;
     @Column(name="OPDRNRO")    private String opdrnro;
     @Column(name="DATUMO")     private String datumo;
     @Column(name="INITO")      private String inito;
     @Column(name="VERSIEO")    private String versieo;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID") private int recordID;
 
 @Transient                     private Huwknd huwknd;
 
public void convert(EntityManager em){
	 
	 //System.out.println("        Huwafk convert");
	 
	 M2 m2 = new M2();
	 m2.transform(this);
	 //EntityManager em = Utils.getEm_nieuw();
	 //em.getTransaction().begin();
	 em.persist(m2);
	 //em.getTransaction().commit();

	 
 }

public void resolveAl(){
	
		
	
		if(getHwakpl() != null &&  Utils.toBeTranslated(getHwakpl()))
			setHwakpl(getHuwknd().getMarriageActPlace());

}

public int getIdnr() {
	return idnr;
}

public void setIdnr(int idnr) {
	this.idnr = idnr;
}

public int getHdag() {
	return hdag;
}

public void setHdag(int hdag) {
	this.hdag = hdag;
}

public int getHmaand() {
	return hmaand;
}

public void setHmaand(int hmaand) {
	this.hmaand = hmaand;
}

public int getHjaar() {
	return hjaar;
}

public void setHjaar(int hjaar) {
	this.hjaar = hjaar;
}

public int getHvlgnr() {
	return hvlgnr;
}

public void setHvlgnr(int hvlgnr) {
	this.hvlgnr = hvlgnr;
}

public int getHwaknr() {
	return hwaknr;
}

public void setHwaknr(int hwaknr) {
	this.hwaknr = hwaknr;
}

public int getHwakdg() {
	return hwakdg;
}

public void setHwakdg(int hwakdg) {
	this.hwakdg = hwakdg;
}

public int getHwakmd() {
	return hwakmd;
}

public void setHwakmd(int hwakmd) {
	this.hwakmd = hwakmd;
}

public int getHwakjr() {
	return hwakjr;
}

public void setHwakjr(int hwakjr) {
	this.hwakjr = hwakjr;
}

public String getHwakpl() {
	return hwakpl;
}

public void setHwakpl(String hwakpl) {
	this.hwakpl = hwakpl;
}

public String getArch() {
	return arch;
}

public void setArch(String arch) {
	this.arch = arch;
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

public String getOndrzko() {
	return ondrzko;
}

public void setOndrzko(String ondrzko) {
	this.ondrzko = ondrzko;
}

public String getArcho() {
	return archo;
}

public void setArcho(String archo) {
	this.archo = archo;
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


public Huwknd getHuwknd() {
	return huwknd;
}

public void setHuwknd(Huwknd huwknd) {
	this.huwknd = huwknd;
}


public int getHwakgr() {
	return hwakgr;
}


public void setHwakgr(int hwakgr) {
	this.hwakgr = hwakgr;
}


public int getRecordID() {
	return recordID;
}


public void setRecordID(int recordID) {
	this.recordID = recordID;
}
 
 
 
 
}
