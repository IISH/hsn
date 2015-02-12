package iisg.nl.hsnnieuw;

import iisg.nl.hsnimport.Gebgtg;
import iisg.nl.hsnmigrate.Constants;
import iisg.nl.hsnmigrate.Functions;
import iisg.nl.hsnmigrate.Utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="b3")
public class B3 {

     @Column(name="IDNR")         private int idnr;
     @Column(name="B3SDMI")       private String b3sdmi;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")     private int recordID;
     
     
     
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public String getB3sdmi() {
		return b3sdmi;
	}
	public void setB3sdmi(String b3sdmi) {
		this.b3sdmi = b3sdmi;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}
     
     
     
     
}
     