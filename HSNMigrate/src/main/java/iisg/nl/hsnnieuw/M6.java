package iisg.nl.hsnnieuw;

import iisg.nl.hsnimport.Huwbyz;
import iisg.nl.hsnimport.Huwvrknd;
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
@Table(name="m6")
public class M6 {

     @Column(name="IDNR")         private int idnr;
     @Column(name="MAR_CD")       private int mar_cd;
     @Column(name="MAR_CM")       private int mar_cm;
     @Column(name="MAR_CY")       private int mar_cy;
     @Column(name="M6SDMI")       private String m6sdmi;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")     private int recordID;
     
     
     public void transform(Huwbyz huwbyz){
    	 
         // copy and/or combine
        	 
         setIdnr(huwbyz.getIdnr());
         setMar_cd(huwbyz.getHdag());
         setMar_cm(huwbyz.getHmaand());
         setMar_cy(huwbyz.getHjaar());
         setM6sdmi(huwbyz.getByz());
         
         // Check information
         
         int result = 0;
         
         if((result = Functions.date_f(getMar_cd(), getMar_cm(), getMar_cy())) != 0)
          	Utils.message(result + Constants.E_DAM6SDMY, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M6", getMar_cd() + "-" + getMar_cm() + "-" + getMar_cy());
         
     }
     
     
     
     
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public int getMar_cd() {
		return mar_cd;
	}
	public void setMar_cd(int mar_cd) {
		this.mar_cd = mar_cd;
	}
	public int getMar_cm() {
		return mar_cm;
	}
	public void setMar_cm(int mar_cm) {
		this.mar_cm = mar_cm;
	}
	public int getMar_cy() {
		return mar_cy;
	}
	public void setMar_cy(int mar_cy) {
		this.mar_cy = mar_cy;
	}
	public String getM6sdmi() {
		return m6sdmi;
	}
	public void setM6sdmi(String m6sdmi) {
		this.m6sdmi = m6sdmi;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
}