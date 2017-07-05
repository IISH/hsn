package iisg.nl.hsnimport;

import iisg.nl.hsnmigrate.Utils;
import iisg.nl.hsnnieuw.B2;
import iisg.nl.hsnnieuw.B5;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="gebvdr")
public class Gebvdr {

     @Column(name="IDNR")        private int idnr;
     @Column(name="GEGVR")       private String gegvr;
     @Column(name="ANMVR")       private String anmvr;
     @Column(name="TUSVR")       private String tusvr;
     @Column(name="VRN1VR")      private String vrn1vr;
     @Column(name="VRN2VR")      private String vrn2vr;
     @Column(name="VRN3VR")      private String vrn3vr;
     @Column(name="LFTVR")       private int lftvr;
     @Column(name="BRPVR")       private String brpvr;
     @Column(name="ADRVR")       private String adrvr;
     @Column(name="G5OOSD")      private int g5oosd;
     @Column(name="G5OOSM")      private int g5oosm;
     @Column(name="G5OOSJ")      private int g5oosj;
     @Column(name="G5OOGS")      private String g5oogs;
     @Column(name="G5VOOD")      private int g5vood;
     @Column(name="G5VOOM")      private int g5voom;
     @Column(name="G5VOOJ")      private int g5vooj;
     @Column(name="G5VOGO")      private String g5vogo;
     @Column(name="ARCH")        private String arch;
     @Column(name="OPDRNR")      private String opdrnr;
     @Column(name="DATUM")       private String datum;
     @Column(name="INIT")        private String init;
     @Column(name="VERSIE")      private String versie;
     @Column(name="ONDRZKO")     private String ondrzko;
     @Column(name="ARCHO")       private String archo;
     @Column(name="OPDRNRO")     private String opdrnro;
     @Column(name="DATUMO")      private String datumo;
     @Column(name="INITO")       private String inito;
     @Column(name="VERSIEO")     private String versieo;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID") private int recordID;
 
 @Transient                      private Gebknd gebknd;
 
public void convert(EntityManager em){
	 
	 B5 b5 = new B5();
	 b5.transform(this);
	 //EntityManager em = Utils.getEm_nieuw();
	 //em.getTransaction().begin();
	 b5.truncate();
	 em.persist(b5);
	 //em.getTransaction().commit();


	 
 }
	 
public void resolveAl(){
	 
	 if(getAdrvr() != null && getAdrvr().trim().equalsIgnoreCase("AL")) 
		 setAdrvr(getGebknd().getBirthActLocation());
	 
	 if(getG5oogs() != null && getG5oogs().trim().equalsIgnoreCase("AL")) 
		 setG5oogs(getGebknd().getBirthActLocation());
	 
	 if(getG5vogo() != null && getG5vogo().trim().equalsIgnoreCase("AL")) 
		 setG5vogo(getGebknd().getBirthActLocation());
	 
}


 

public int getIdnr() {
	return idnr;
}

public void setIdnr(int idnr) {
	this.idnr = idnr;
}

public String getGegvr() {
	return gegvr;
}

public void setGegvr(String gegvr) {
	this.gegvr = gegvr;
}

public String getAnmvr() {
	return anmvr;
}

public void setAnmvr(String anmvr) {
	this.anmvr = anmvr;
}

public String getTusvr() {
	return tusvr;
}

public void setTusvr(String tusvr) {
	this.tusvr = tusvr;
}

public String getVrn1vr() {
	return vrn1vr;
}

public void setVrn1vr(String vrn1vr) {
	this.vrn1vr = vrn1vr;
}

public String getVrn2vr() {
	return vrn2vr;
}

public void setVrn2vr(String vrn2vr) {
	this.vrn2vr = vrn2vr;
}

public String getVrn3vr() {
	return vrn3vr;
}

public void setVrn3vr(String vrn3vr) {
	this.vrn3vr = vrn3vr;
}

public int getLftvr() {
	return lftvr;
}

public void setLftvr(int lftvr) {
	this.lftvr = lftvr;
}

public String getBrpvr() {
	return brpvr;
}

public void setBrpvr(String brpvr) {
	this.brpvr = brpvr;
}

public String getAdrvr() {
	return adrvr;
}

public void setAdrvr(String adrvr) {
	this.adrvr = adrvr;
}

public int getG5oosd() {
	return g5oosd;
}

public void setG5oosd(int g5oosd) {
	this.g5oosd = g5oosd;
}

public int getG5oosm() {
	return g5oosm;
}

public void setG5oosm(int g5oosm) {
	this.g5oosm = g5oosm;
}

public int getG5oosj() {
	return g5oosj;
}

public void setG5oosj(int g5oosj) {
	this.g5oosj = g5oosj;
}

public String getG5oogs() {
	return g5oogs;
}

public void setG5oogs(String g5oogs) {
	this.g5oogs = g5oogs;
}

public int getG5vood() {
	return g5vood;
}

public void setG5vood(int g5vood) {
	this.g5vood = g5vood;
}

public int getG5voom() {
	return g5voom;
}

public void setG5voom(int g5voom) {
	this.g5voom = g5voom;
}

public int getG5vooj() {
	return g5vooj;
}

public void setG5vooj(int g5vooj) {
	this.g5vooj = g5vooj;
}

public String getG5vogo() {
	return g5vogo;
}

public void setG5vogo(String g5vogo) {
	this.g5vogo = g5vogo;
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
