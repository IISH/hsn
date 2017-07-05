package iisg.nl.hsnimport;

import iisg.nl.hsnmigrate.Utils;
import iisg.nl.hsnnieuw.M3;
import iisg.nl.hsnnieuw.M5;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="huwvrknd")
public class Huwvrknd  {

     @Column(name="IDNR")       private int idnr;
     @Column(name="HDAG")       private int hdag;
     @Column(name="HMAAND")     private int hmaand;
     @Column(name="HJAAR")      private int hjaar;
     @Column(name="HVLGNR")     private int hvlgnr;
     @Column(name="VLGNRVK")    private int vlgnrvk;
     @Column(name="ANMVK")      private String anmvk;
     @Column(name="TUSVK")      private String tusvk;
     @Column(name="VRN1VK")     private String vrn1vk;
     @Column(name="VRN2VK")     private String vrn2vk;
     @Column(name="VRN3VK")     private String vrn3vk;
     @Column(name="GBDGVK")     private int gbdgvk;
     @Column(name="GBMDVK")     private int gbmdvk;
     @Column(name="GBJRVK")     private int gbjrvk;
     @Column(name="GESLVK")     private String geslvk;
     @Column(name="GBPLVK")     private String gbplvk;
     @Column(name="ERVK")       private String ervk;
     @Column(name="ERVKWIE")    private String ervkwie;
     @Column(name="MEKDGVK")    private int mekdgvk;
     @Column(name="MEKMDVK")    private int mekmdvk;
     @Column(name="MEKJRVK")    private int mekjrvk;
     @Column(name="MEKPLVK")    private String mekplvk;
     @Column(name="VEKDGVK")    private int vekdgvk;
     @Column(name="VEKMDVK")    private int vekmdvk;
     @Column(name="VEKJRVK")    private int vekjrvk;
     @Column(name="VEKPLVK")    private String vekplvk;
     @Column(name="ARCH")       private String arch;
     @Column(name="OPDRNR")     private String opdrnr;
     @Column(name="DATUM")      private String datum;
     @Column(name="INIT")       private String init;
     @Column(name="VERSIE")     private String versie;
     @Column(name="OPDRNRO")    private String opdrnro;
     @Column(name="ARCHO")      private String archo;
     @Column(name="ONDRZKO")    private String ondrzko;
     @Column(name="DATUMO")     private String datumo;
     @Column(name="INITO")      private String inito;
     @Column(name="VERSIEO")    private String versieo;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID") private int recordID;
 
 @Transient                     private Huwknd huwknd;
 
 
public void convert(EntityManager em){
	 
	 //System.out.println("        Huwvrknd convert");
	 
	 M5 m5 = new M5();
	 m5.transform(this);
	 //EntityManager em = Utils.getEm_nieuw();
	 //em.getTransaction().begin();
	 m5.truncate();
	 em.persist(m5);
	 //em.getTransaction().commit();


	 
 }

public void resolveAl(){
	
	
	
	if(getGbplvk() != null && getGbplvk().equalsIgnoreCase("AL"))
		setGbplvk(getHuwknd().getMarriageActPlace());

	if(getMekplvk() != null && getMekplvk().equalsIgnoreCase("AL"))
		setMekplvk(getHuwknd().getMarriageActPlace());

	if(getVekplvk() != null && getVekplvk().equalsIgnoreCase("AL"))
		setVekplvk(getHuwknd().getMarriageActPlace());

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

public int getVlgnrvk() {
	return vlgnrvk;
}

public void setVlgnrvk(int vlgnrvk) {
	this.vlgnrvk = vlgnrvk;
}

public String getAnmvk() {
	return anmvk;
}

public void setAnmvk(String anmvk) {
	this.anmvk = anmvk;
}

public String getTusvk() {
	return tusvk;
}

public void setTusvk(String tusvk) {
	this.tusvk = tusvk;
}

public String getVrn1vk() {
	return vrn1vk;
}

public void setVrn1vk(String vrn1vk) {
	this.vrn1vk = vrn1vk;
}

public String getVrn2vk() {
	return vrn2vk;
}

public void setVrn2vk(String vrn2vk) {
	this.vrn2vk = vrn2vk;
}

public String getVrn3vk() {
	return vrn3vk;
}

public void setVrn3vk(String vrn3vk) {
	this.vrn3vk = vrn3vk;
}

public int getGbdgvk() {
	return gbdgvk;
}

public void setGbdgvk(int gbdgvk) {
	this.gbdgvk = gbdgvk;
}

public int getGbmdvk() {
	return gbmdvk;
}

public void setGbmdvk(int gbmdvk) {
	this.gbmdvk = gbmdvk;
}

public int getGbjrvk() {
	return gbjrvk;
}

public void setGbjrvk(int gbjrvk) {
	this.gbjrvk = gbjrvk;
}

public String getGeslvk() {
	return geslvk;
}

public void setGeslvk(String geslvk) {
	this.geslvk = geslvk;
}

public String getGbplvk() {
	return gbplvk;
}

public void setGbplvk(String gbplvk) {
	this.gbplvk = gbplvk;
}

public String getErvk() {
	return ervk;
}

public void setErvk(String ervk) {
	this.ervk = ervk;
}

public String getErvkwie() {
	return ervkwie;
}

public void setErvkwie(String ervkwie) {
	this.ervkwie = ervkwie;
}

public int getMekdgvk() {
	return mekdgvk;
}

public void setMekdgvk(int mekdgvk) {
	this.mekdgvk = mekdgvk;
}

public int getMekmdvk() {
	return mekmdvk;
}

public void setMekmdvk(int mekmdvk) {
	this.mekmdvk = mekmdvk;
}

public int getMekjrvk() {
	return mekjrvk;
}

public void setMekjrvk(int mekjrvk) {
	this.mekjrvk = mekjrvk;
}

public String getMekplvk() {
	return mekplvk;
}

public void setMekplvk(String mekplvk) {
	this.mekplvk = mekplvk;
}

public int getVekdgvk() {
	return vekdgvk;
}

public void setVekdgvk(int vekdgvk) {
	this.vekdgvk = vekdgvk;
}

public int getVekmdvk() {
	return vekmdvk;
}

public void setVekmdvk(int vekmdvk) {
	this.vekmdvk = vekmdvk;
}

public int getVekjrvk() {
	return vekjrvk;
}

public void setVekjrvk(int vekjrvk) {
	this.vekjrvk = vekjrvk;
}

public String getVekplvk() {
	return vekplvk;
}

public void setVekplvk(String vekplvk) {
	this.vekplvk = vekplvk;
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

public String getOpdrnro() {
	return opdrnro;
}

public void setOpdrnro(String opdrnro) {
	this.opdrnro = opdrnro;
}

public String getArcho() {
	return archo;
}

public void setArcho(String archo) {
	this.archo = archo;
}

public String getOndrzko() {
	return ondrzko;
}

public void setOndrzko(String ondrzko) {
	this.ondrzko = ondrzko;
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


public int getRecordID() {
	return recordID;
}


public void setRecordID(int recordID) {
	this.recordID = recordID;
}

 
 
 
 
 
}
