package nl.iisg.ids05;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="b3")
public class B3 {

     @Column(name="IDNR")         private int idnr;
     @Column(name="B3SDMI")       private String b3sdmi;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")     private int recordID;
     
     @Transient                   private B1   b1   = null;               

      
     
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
	public B1 getB1() {
		return b1;
	}
	public void setB1(B1 b1) {
		this.b1 = b1;
	}
     
     
     
     
}
     