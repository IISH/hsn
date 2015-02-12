package iisg.nl.hsnimport;

import iisg.nl.hsnmigrate.Utils;
import iisg.nl.hsnnieuw.B0;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="stpb")
public class Stpb {
	
	@Column(name="DOELNR")   private int doelnr; 
	@Column(name="GEMEENTE") private String gemeente;	
	@Column(name="GEMNR")    private int gemnr;
    @Column(name="JAAR")	 private int jaar; 
	@Column(name="AKTENR")   private int aktenr; 
	@Column(name="COHORTNR") private int cohortnr;
    @Column(name="IDNR")     private int idnr;	
	@Column(name="PROVNR")   private int provnr;
	@Column(name="REGNR")    private int regnr;
	@Column(name="SUBCOHNR") private int subcohnr;
    @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
    @Column(name="RecordID") private int recordID;

@Transient               private ArrayList<Gebknd>  gebkndL  = new ArrayList<Gebknd>();
@Transient               private ArrayList<Gebakte> gebakteL = new ArrayList<Gebakte>();


public void convert(EntityManager em){
	
	
	//System.out.println("Stpb convert");
	//System.out.println("IDNR = " + getIdnr());
	
	B0 b0 = new B0();
	b0.transform(this);
	//EntityManager em = Utils.getEm_nieuw();
	
	
	em.persist(b0);
	
	
	for(Gebknd gebknd: gebkndL)
		gebknd.convert(em);
	
	
	
	for(Gebakte gebakte: gebakteL)
		gebakte.convert(em);
	
	
	
	//em.getTransaction().commit();
	//System.out.println();
}



public int getDoelnr() {
	return doelnr;
}
public void setDoelnr(int doelnr) {
	this.doelnr = doelnr;
}
public String getGemeente() {
	return gemeente;
}
public void setGemeente(String gemeente) {
	this.gemeente = gemeente;
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
public int getCohortnr() {
	return cohortnr;
}
public void setCohortnr(int cohortnr) {
	this.cohortnr = cohortnr;
}
public int getIdnr() {
	return idnr;
}
public void setIdnr(int idnr) {
	this.idnr = idnr;
}
public int getProvnr() {
	return provnr;
}
public void setProvnr(int provnr) {
	this.provnr = provnr;
}
public int getRegnr() {
	return regnr;
}
public void setRegnr(int regnr) {
	this.regnr = regnr;
}
public int getSubcohnr() {
	return subcohnr;
}
public void setSubcohnr(int subcohnr) {
	this.subcohnr = subcohnr;
}
public ArrayList<Gebknd> getGebkndL() {
	return gebkndL;
}
public void setGebkndL(ArrayList<Gebknd> gebknd) {
	this.gebkndL = gebknd;
}
public ArrayList<Gebakte> getGebakteL() {
	return gebakteL;
}
public void setGebakteL(ArrayList<Gebakte> gebakte) {
	this.gebakteL = gebakte;
}


public int getRecordID() {
	return recordID;
}


public void setRecordID(int recordID) {
	this.recordID = recordID;
}
	
	
	
	

}
