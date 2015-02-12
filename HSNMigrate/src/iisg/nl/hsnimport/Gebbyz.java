package iisg.nl.hsnimport;

import iisg.nl.hsnmigrate.Utils;
import iisg.nl.hsnnieuw.B2;
import iisg.nl.hsnnieuw.B3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="gebbyz")
public class Gebbyz {

	 @Column(name="IDNR")       private int idnr;
     @Column(name="BYZNR")      private int byznr;
     @Column(name="BYZ")        private String byz;
     @Column(name="SCHERM")     private String scherm;
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
 
 @Transient                     private Gebknd gebknd;
 
 


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
