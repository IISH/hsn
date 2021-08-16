package iisg.nl.hsnnieuw;

import iisg.nl.hsnimport.Huwafk;
import iisg.nl.hsnimport.Huwknd;
import iisg.nl.hsnmigrate.Constants;
import iisg.nl.hsnmigrate.Functions;
import iisg.nl.hsnmigrate.Utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import nl.iisg.ref.Ref;
import nl.iisg.ref.Ref_Municipality;

@Entity
@Table(name="m2")
public class M2 {

     @Column(name="IDNR")         private int idnr;
     @Column(name="MAR_CD")       private int mar_cd;
     @Column(name="MAR_CM")       private int mar_cm;
     @Column(name="MAR_CY")       private int mar_cy;
     @Column(name="M2SDSQ")       private int m2sdsq;
     @Column(name="M2U_UM")       private int m2u_um;
     @Column(name="M2U_UD")       private int m2u_ud;
     @Column(name="M2U_UY")       private int m2u_uy;
     @Column(name="M2U_UC")       private int m2u_uc;
     @Column(name="M2U_UL")       private String m2u_ul;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")     private int recordID;
     
     
     public void transform(Huwafk huwafk){
    	 
    	 setIdnr(huwafk.getIdnr());
    	 setMar_cd(huwafk.getHdag());
    	 setMar_cm(huwafk.getHmaand());
    	 setMar_cy(huwafk.getHjaar());
    	 setM2sdsq(huwafk.getHwaknr());
    	 setM2u_um(huwafk.getHwakmd());
    	 setM2u_ud(huwafk.getHwakdg());
    	 setM2u_uy(huwafk.getHwakjr());
    	 setM2u_uc(huwafk.getHwakgr());
    	 setM2u_ul(huwafk.getHwakpl());

    	 
         // Check information
         
         int result = 0;
         
         if((result = Functions.date_f(getMar_cd(), getMar_cm(), getMar_cy())) != 0){
             //System.out.println("M2 issuing error ");

           	Utils.message(result + Constants.E_DAM2SDMY, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M2", getMar_cd() + "-" + getMar_cm() + "-" + getMar_cy());
         }

         if((result = Functions.date_f(getM2u_ud(), getM2u_um(), getM2u_uy())) != 0)
            	Utils.message(result + Constants.E_DAM2SAMY, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M2", getM2u_ud() + "-" + getM2u_um() + "-" + getM2u_uy());

         
         // Code and name of municipality of birth must be handled together
         
         if(getM2u_uc() > 0){
        	 if((result = Functions.codeconversion_f(getM2u_uc())) != 0){

        		 int code = 0;
        		 switch(getM2sdsq()){

        		 case 1: code = Constants.E_CCM2SD1C; break;
        		 case 2: code = Constants.E_CCM2SD2C; break;
        		 case 3: code = Constants.E_CCM2SD3C; break;
        		 case 4: code = Constants.E_CCM2SD4C; break;

        		 default: code = Constants.E_CCM2SD4C; break;

        		 }

        		 Utils.message(result + code, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M2", "" + getM2u_uc());
        	 }
        	 else{
        		 Ref_Municipality r = Ref.getMunicipality(getM2u_uc());
        		 setM2u_ul(r.getMunicipalityName());
        	 }
         }
         else
        	 if((result = Functions.empty_f(getM2u_ul())) != 0)
        		 Utils.message(result + Constants.E_LEM2U_UL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M2");
         

         if((result = Functions.vlslocation_f(getM2u_ul())) != 0)
          	Utils.message(result + Constants.E_VLM2U_UL, getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M2");

    	 
         // Reference checks
         
         setM2u_ul(Functions.location_r(getM2u_ul(), Constants.E_LOM2U_UL,getIdnr(), getMar_cy(), "HSN_CIVREC_STD", "M2"));
         
         
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
	public int getM2sdsq() {
		return m2sdsq;
	}
	public void setM2sdsq(int m2sdsq) {
		this.m2sdsq = m2sdsq;
	}
	public int getM2u_um() {
		return m2u_um;
	}
	public void setM2u_um(int m2u_um) {
		this.m2u_um = m2u_um;
	}
	public int getM2u_ud() {
		return m2u_ud;
	}
	public void setM2u_ud(int m2u_ud) {
		this.m2u_ud = m2u_ud;
	}
	public int getM2u_uy() {
		return m2u_uy;
	}
	public void setM2u_uy(int m2u_uy) {
		this.m2u_uy = m2u_uy;
	}
	public int getM2u_uc() {
		return m2u_uc;
	}
	public void setM2u_uc(int m2u_uc) {
		this.m2u_uc = m2u_uc;
	}
	public String getM2u_ul() {
		return m2u_ul;
	}
	public void setM2u_ul(String m2u_ul) {
		this.m2u_ul = m2u_ul;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}

}
