package iisg.nl.hsnimport;

import iisg.nl.hsnmigrate.Utils;
import iisg.nl.hsnnieuw.B1;
import iisg.nl.hsnnieuw.B2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="gebgtg")
public class Gebgtg {

     @Column(name="IDNR")       private int idnr;
     @Column(name="VLGNRGT")    private int vlgnrgt;
     @Column(name="ANMGT")      private String anmgt;
     @Column(name="TUSGT")      private String tusgt;
     @Column(name="VRN1GT")     private String vrn1gt;
     @Column(name="VRN2GT")     private String vrn2gt;
     @Column(name="VRN3GT")     private String vrn3gt;
     @Column(name="LFTGT")      private int lftgt;
     @Column(name="BRPGT")      private String brpgt;
     @Column(name="ADRGT")      private String adrgt;
     @Column(name="HNDGT")      private String hndgt;
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
     @Transient                 private Gebknd gebknd;
 
 
 public void convert(EntityManager em){
	 
	 //System.out.println("        Gebgtg convert");
	 
	 B2 b2 = new B2();
	 b2.transform(this);
	 //EntityManager em = Utils.getEm_nieuw();
	 //em.getTransaction().begin();
	 b2.truncate();
	 em.persist(b2);
	 //em.getTransaction().commit();


	 
 }
 
 
 public void resolveAl(){
	 
	 if(getAdrgt() != null && getAdrgt().trim().equalsIgnoreCase("AL")) 
		 setAdrgt(getGebknd().getBirthActLocation());

	 
 }

public int getIdnr() {
	return idnr;
}

public void setIdnr(int idnr) {
	this.idnr = idnr;
}

public int getVlgnrgt() {
	return vlgnrgt;
}

public void setVlgnrgt(int vlgnrgt) {
	this.vlgnrgt = vlgnrgt;
}

public String getAnmgt() {
	return anmgt;
}

public void setAnmgt(String anmgt) {
	this.anmgt = anmgt;
}

public String getTusgt() {
	return tusgt;
}

public void setTusgt(String tusgt) {
	this.tusgt = tusgt;
}

public String getVrn1gt() {
	return vrn1gt;
}

public void setVrn1gt(String vrn1gt) {
	this.vrn1gt = vrn1gt;
}

public String getVrn2gt() {
	return vrn2gt;
}

public void setVrn2gt(String vrn2gt) {
	this.vrn2gt = vrn2gt;
}

public String getVrn3gt() {
	return vrn3gt;
}

public void setVrn3gt(String vrn3gt) {
	this.vrn3gt = vrn3gt;
}

public int getLftgt() {
	return lftgt;
}

public void setLftgt(int lftgt) {
	this.lftgt = lftgt;
}

public String getBrpgt() {
	return brpgt;
}

public void setBrpgt(String brpgt) {
	this.brpgt = brpgt;
}

public String getAdrgt() {
	return adrgt;
}

public void setAdrgt(String adrgt) {
	this.adrgt = adrgt;
}

public String getHndgt() {
	return hndgt;
}

public void setHndgt(String hndgt) {
	this.hndgt = hndgt;
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


public Gebknd getGebknd() {
	return gebknd;
}

public void setGebknd(Gebknd gebknd) {
	this.gebknd = gebknd;
}

public int getRecordID() {
	return recordID;
}

public void setRecordID(int recordID) {
	this.recordID = recordID;
}


 
 
}
