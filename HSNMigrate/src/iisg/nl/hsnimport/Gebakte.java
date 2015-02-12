package iisg.nl.hsnimport;

import iisg.nl.hsnmigrate.Utils;
import iisg.nl.hsnnieuw.B4;
import iisg.nl.hsnnieuw.B5;
import iisg.nl.hsnnieuw.B6;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="gebakte")
public class Gebakte {

     @Column(name="GEMNR")       private int gemnr;
     @Column(name="JAAR")        private int jaar;
     @Column(name="AKTENR")      private int aktenr;
     @Column(name="IDNR")        private int idnr;
     @Column(name="GEBKODE")     private int gebkode;
     @Column(name="OVERSAMP")    private String oversamp;
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

 @Transient                      private Stpb stpb;
 
 
public void convert(EntityManager em){
	 
	 //System.out.println("    Gebakte convert");
	 
	 B6 b6 = new B6();
	 b6.transform(this);
	 //EntityManager em = Utils.getEm_nieuw();
	 //em.getTransaction().begin();
	 em.persist(b6);
	 //em.getTransaction().commit();


	 
 }



public int getGemnr() {
	return gemnr;
}

public void setGemnr(int gemnr) {
	this.gemnr = gemnr;
}

public int getJaar() {
	return jaar;
}

public void setJaar(int jaar) {
	this.jaar = jaar;
}

public int getAktenr() {
	return aktenr;
}

public void setAktenr(int aktenr) {
	this.aktenr = aktenr;
}

public int getIdnr() {
	return idnr;
}

public void setIdnr(int idnr) {
	this.idnr = idnr;
}

public int getGebkode() {
	return gebkode;
}

public void setGebkode(int gebkode) {
	this.gebkode = gebkode;
}

public String getOversamp() {
	return oversamp;
}

public void setOversamp(String oversamp) {
	this.oversamp = oversamp;
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


public Stpb getStpb() {
	return stpb;
}

public void setStpb(Stpb stpb) {
	this.stpb = stpb;
}



public int getRecordID() {
	return recordID;
}



public void setRecordID(int recordID) {
	this.recordID = recordID;
}
 
 

}
