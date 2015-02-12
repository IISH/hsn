package iisg.nl.hsnnieuw;

import iisg.nl.hsnimport.Stpb;
import iisg.nl.hsnmigrate.Const;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="b0")
public class B0 {
	
	@Column(name="B0SUHG")    private int b0suhg;
	@Column(name="B0SUHC")    private int b0suhc;
	@Column(name="B0SUHY")    private int b0suhy;
	@Column(name="B0SUHN")    private int b0suhn;
	@Column(name="B0SUHD")    private int b0suhd;
	@Column(name="IDNR")      private int idnr; 
	@Column(name="B0SUHP")	  private int b0suhp; 
	@Column(name="B0SUHR")    private int b0suhr; 
	@Column(name="B0SUHE")    private int b0suhe;
    @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
    @Column(name="RecordID")  private int recordID;

public void transform(Stpb stpb){	
	
	setB0suhg(stpb.getDoelnr());
	setB0suhc(stpb.getGemnr());
	setB0suhy(stpb.getJaar());
	setB0suhn(stpb.getAktenr());
	setB0suhd(stpb.getCohortnr());
	setIdnr(stpb.getIdnr());
	setB0suhp(stpb.getProvnr());
	setB0suhr(stpb.getRegnr());
	setB0suhe(stpb.getSubcohnr());
	
	
}

public void truncate(){
	
	
}

public int getB0suhg() {
	return b0suhg;
}
public void setB0suhg(int b0suhg) {
	this.b0suhg = b0suhg;
}
public int getB0suhc() {
	return b0suhc;
}
public void setB0suhc(int b0suhc) {
	this.b0suhc = b0suhc;
}
public int getB0suhy() {
	return b0suhy;
}
public void setB0suhy(int b0suhy) {
	this.b0suhy = b0suhy;
}
public int getB0suhn() {
	return b0suhn;
}
public void setB0suhn(int b0suhn) {
	this.b0suhn = b0suhn;
}
public int getB0suhd() {
	return b0suhd;
}
public void setB0suhd(int b0suhd) {
	this.b0suhd = b0suhd;
}
public int getIdnr() {
	return idnr;
}
public void setIdnr(int idnr) {
	this.idnr = idnr;
}
public int getB0suhp() {
	return b0suhp;
}
public void setB0suhp(int b0suhp) {
	this.b0suhp = b0suhp;
}
public int getB0suhr() {
	return b0suhr;
}
public void setB0suhr(int b0suhr) {
	this.b0suhr = b0suhr;
}
public int getB0suhe() {
	return b0suhe;
}
public void setB0suhe(int b0suhe) {
	this.b0suhe = b0suhe;
}
public int getRecordID() {
	return recordID;
}
public void setRecordID(int recordID) {
	this.recordID = recordID;
}

	
	

}

