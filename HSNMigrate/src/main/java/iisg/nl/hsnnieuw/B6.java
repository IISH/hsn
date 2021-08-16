package iisg.nl.hsnnieuw;

import iisg.nl.hsnimport.Gebakte;
import iisg.nl.hsnimport.Gebvdr;
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
@Table(name="b6")
public class B6 {

     @Column(name="IDNR")         private int idnr;
     @Column(name="B6SUHC")       private int b6suhc;
     @Column(name="B6SUHY")       private int b6suhy;
     @Column(name="B6SUHN")       private int b6suhn;
     @Column(name="B6SUHZ")       private int b6suhz;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")     private int recordID;
     
     
     public void transform(Gebakte gebakte){
    	 
    	 //System.out.println("YYYYYYYYYYYYYX");
    	 
  	    // copy and/or combine
    	 
    	 
 	    setIdnr(gebakte.getIdnr());
 	    
 	    setB6suhc(gebakte.getGemnr());
 	    setB6suhy(gebakte.getJaar());
 	    setB6suhn(gebakte.getAktenr());
 	    setB6suhz(gebakte.getGebkode());
         
         // Check information
         
         int result = 0;
         
         if((result = Functions.birthcode_f(getB6suhz())) != 0)
            	Utils.message(result + Constants.E_HZB6SUHZ, getIdnr(), 0, "HSN_CIVREC_STD", "B6", "" + getB6suhz());

         
     }
     
     
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public int getB6suhc() {
		return b6suhc;
	}
	public void setB6suhc(int b6suhc) {
		this.b6suhc = b6suhc;
	}
	public int getB6suhy() {
		return b6suhy;
	}
	public void setB6suhy(int b6suhy) {
		this.b6suhy = b6suhy;
	}
	public int getB6suhn() {
		return b6suhn;
	}
	public void setB6suhn(int b6suhn) {
		this.b6suhn = b6suhn;
	}
	public int getB6suhz() {
		return b6suhz;
	}
	public void setB6suhz(int b6suhz) {
		this.b6suhz = b6suhz;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}
     
     
     
     
}
