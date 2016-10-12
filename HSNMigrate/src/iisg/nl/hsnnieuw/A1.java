package iisg.nl.hsnnieuw;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="a1")
public class A1 {

     @Column(name="IDNR")         private int idnr;
     @Column(name="ADR_CD")       private int adr_cd;
     @Column(name="ADR_CM")       private int adr_cm;
     @Column(name="ADR_CY")       private int adr_cy;
     @Column(name="MUNICIPALITY") private String municipality;
     @Column(name="STREET")       private String street;
     @Column(name="QUARTER")      private String quarter;
     @Column(name="NUMBER") private String number;
     @Column(name="NUMBER_ADDITION") private String numberAddition;
     
     
     
     public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public int getAdr_cd() {
		return adr_cd;
	}
	public void setAdr_cd(int adr_cd) {
		this.adr_cd = adr_cd;
	}
	public int getAdr_cm() {
		return adr_cm;
	}
	public void setAdr_cm(int adr_cm) {
		this.adr_cm = adr_cm;
	}
	public int getAdr_cy() {
		return adr_cy;
	}
	public void setAdr_cy(int adr_cy) {
		this.adr_cy = adr_cy;
	}
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getNumberAddition() {
		return numberAddition;
	}
	public void setNumberAddition(String numberAddition) {
		this.numberAddition = numberAddition;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")     private int recordID;
     
}
