package iisg.nl.hsnnieuw;

import iisg.nl.hsnimport.Ovlagv;
import iisg.nl.hsnimport.Ovlbyz;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="d4")
public class D4 {

     @Column(name="IDNR")         private int idnr;
     @Column(name="D4SDMI")       private String d4sdmi;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")     private int recordID;
     
 public void transform(Ovlbyz ovlbyz){
         
         setIdnr(ovlbyz.getIdnr());
         

 }     
     
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public String getD4sdmi() {
		return d4sdmi;
	}
	public void setD4sdmi(String d4sdmi) {
		this.d4sdmi = d4sdmi;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}
     
     
     
}