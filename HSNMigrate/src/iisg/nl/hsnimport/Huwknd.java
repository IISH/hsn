package iisg.nl.hsnimport;

import iisg.nl.hsnmigrate.Constants;
import iisg.nl.hsnmigrate.Functions;
import iisg.nl.hsnmigrate.Utils;
import iisg.nl.hsnnieuw.B1;
import iisg.nl.hsnnieuw.B3;
import iisg.nl.hsnnieuw.M1;
import iisg.nl.hsnnieuw.M6;

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
@Table(name="huwknd")
public class Huwknd  {

     @Column(name="IDNR")       private int idnr;
     @Column(name="HVLGNR")     private int hvlgnr;
     @Column(name="HGEMNR")     private int hgemnr;
     @Column(name="HAKTENR")    private int haktenr;
     @Column(name="HPLTS")      private String hplts;
     @Column(name="HUUR")       private int huur;
     @Column(name="HDAG")       private int hdag;
     @Column(name="HMAAND")     private int hmaand;
     @Column(name="HJAAR")      private int hjaar;
     @Column(name="SCHEIDNG")   private String scheidng;
     @Column(name="SDAG")       private int sdag;
     @Column(name="SMAAND")     private int smaand;
     @Column(name="SJAAR")      private int sjaar;
     @Column(name="SPLTS")      private String splts;
     @Column(name="IDAG")       private int idag;
     @Column(name="IMAAND")     private int imaand;
     @Column(name="IJAAR")      private int ijaar;
     @Column(name="IPLTS")      private String iplts;
     @Column(name="LFTJHM")     private int lftjhm;
     @Column(name="LFTJHV")     private int lftjhv;
     @Column(name="GEBSEX")     private String gebsex;
     @Column(name="ANMHM")      private String anmhm;
     @Column(name="TUSHM")      private String tushm;
     @Column(name="VRN1HM")     private String vrn1hm;
     @Column(name="VRN2HM")     private String vrn2hm;
     @Column(name="VRN3HM")     private String vrn3hm;
     @Column(name="BRPHM")      private String brphm;
     @Column(name="GEBPLNHM")   private int gebplnhm;
     @Column(name="GEBPLHM")    private String gebplhm;
     @Column(name="ADRHM")      private String adrhm;
     @Column(name="OADRHM")     private String oadrhm;
     @Column(name="OADREHM")    private String oadrehm;
     @Column(name="BSTHM")      private String bsthm;
     @Column(name="HNDHM")      private String hndhm;
     @Column(name="ANMHV")      private String anmhv;
     @Column(name="TUSHV")      private String tushv;
     @Column(name="VRN1HV")     private String vrn1hv;
     @Column(name="VRN2HV")     private String vrn2hv;
     @Column(name="VRN3HV")     private String vrn3hv;
     @Column(name="BRPHV")      private String brphv;
     @Column(name="GEBPLNHV")   private int    gebplnhv;
     @Column(name="GEBPLHV")    private String gebplhv;
     @Column(name="ADRHV")      private String adrhv;
     @Column(name="OADRHV")     private String oadrhv;
     @Column(name="OADREHV")    private String oadrehv;
     @Column(name="BSTHV")      private String bsthv;
     @Column(name="HNDHV")      private String hndhv;
     @Column(name="LEVVRHM")    private String levvrhm;
     @Column(name="TOEVRHM")    private String toevrhm;
     @Column(name="ANMVRHM")    private String anmvrhm;
     @Column(name="TUSVRHM")    private String tusvrhm;
     @Column(name="VRN1VRHM")   private String vrn1vrhm;
     @Column(name="VRN2VRHM")   private String vrn2vrhm;
     @Column(name="VRN3VRHM")   private String vrn3vrhm;
     @Column(name="BRPVRHM")    private String brpvrhm;
     @Column(name="ADRVRHM")    private String adrvrhm;
     @Column(name="PLOVVRHM")   private String plovvrhm;
     @Column(name="HNDVRHM")    private String hndvrhm;
     @Column(name="LFTJVRHM")   private int lftjvrhm;
     @Column(name="LEVMRHM")    private String levmrhm;
     @Column(name="TOEMRHM")    private String toemrhm;
     @Column(name="ANMMRHM")    private String anmmrhm;
     @Column(name="TUSMRHM")    private String tusmrhm;
     @Column(name="VRN1MRHM")   private String vrn1mrhm;
     @Column(name="VRN2MRHM")   private String vrn2mrhm;
     @Column(name="VRN3MRHM")   private String vrn3mrhm;
     @Column(name="BRPMRHM")    private String brpmrhm;
     @Column(name="ADRMRHM")    private String adrmrhm;
     @Column(name="PLOVMRHM")   private String plovmrhm;
     @Column(name="HNDMRHM")    private String hndmrhm;
     @Column(name="LFTJMRHM")   private int lftjmrhm;
     @Column(name="LEVVRHV")    private String levvrhv;
     @Column(name="TOEVRHV")    private String toevrhv;
     @Column(name="ANMVRHV")    private String anmvrhv;
     @Column(name="TUSVRHV")    private String tusvrhv;
     @Column(name="VRN1VRHV")   private String vrn1vrhv;
     @Column(name="VRN2VRHV")   private String vrn2vrhv;
     @Column(name="VRN3VRHV")   private String vrn3vrhv;
     @Column(name="BRPVRHV")    private String brpvrhv;
     @Column(name="ADRVRHV")    private String adrvrhv;
     @Column(name="PLOVVRHV")   private String plovvrhv;
     @Column(name="HNDVRHV")    private String hndvrhv;
     @Column(name="LFTJVRHV")   private int lftjvrhv;
     @Column(name="LEVMRHV")    private String levmrhv;
     @Column(name="TOEMRHV")    private String toemrhv;
     @Column(name="ANMMRHV")    private String anmmrhv;
     @Column(name="TUSMRHV")    private String tusmrhv;
     @Column(name="VRN1MRHV")   private String vrn1mrhv;
     @Column(name="VRN2MRHV")   private String vrn2mrhv;
     @Column(name="VRN3MRHV")   private String vrn3mrhv;
     @Column(name="BRPMRHV")    private String brpmrhv;
     @Column(name="ADRMRHV")    private String adrmrhv;
     @Column(name="PLOVMRHV")   private String plovmrhv;
     @Column(name="HNDMRHV")    private String hndmrhv;
     @Column(name="LFTJMRHV")   private int lftjmrhv;
     @Column(name="UGEBHUW")    private String ugebhuw;
     @Column(name="UOVLOUD")    private String uovloud;
     @Column(name="UOVLECH")    private String uovlech;
     @Column(name="CERTNATM")   private String certnatm;
     @Column(name="TOESTNOT")   private String toestnot;
     @Column(name="AKTEBEK")    private String aktebek;
     @Column(name="ONVERMGN")   private String onvermgn;
     @Column(name="COMMAND")    private String command;
     @Column(name="TOESTVGD")   private String toestvgd;
     @Column(name="GEGHUW")     private String geghuw;
     @Column(name="GEGVR")      private String gegvr;
     @Column(name="GEGMR")      private String gegmr;
     @Column(name="PROBLM")     private String problm;
     @Column(name="NGTG")       private int	   ngtg;
     @Column(name="ERKEN")      private String erken;
     @Column(name="ARCH")       private String arch;
     @Column(name="OPDRNR")     private String opdrnr;
     @Column(name="DATUM")      private String datum;
     @Column(name="INIT")       private String init;
     @Column(name="VERSIE")     private String versie;
     @Column(name="ONDRZKO")    private String ondrzko;
     @Column(name="ARCHO")      private String archo;
     @Column(name="OPDRNRO")    private String opdrnro;
     @Column(name="DATUMO")     private String datumo;
     @Column(name="INITO")      private String inito;
     @Column(name="VERSIEO")    private String versieo;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID") private int recordID;
 
 @Transient                     private ArrayList<Huwafk>  huwafkL = new ArrayList<Huwafk>();
 @Transient                     private ArrayList<Huweer>  huweerL = new ArrayList<Huweer>();
 @Transient                     private ArrayList<Huwgtg>  huwgtgL = new ArrayList<Huwgtg>();
 @Transient                     private ArrayList<Huwvrknd>  huwvrkndL = new ArrayList<Huwvrknd>();
 @Transient                     private ArrayList<Huwbyz>  huwbyzL = new ArrayList<Huwbyz>();
 
 @Transient                     private Gebknd   gebknd;
 @Transient						private String   marriageActPlace = null;
 
public void convert(EntityManager em){
	 
	 M1 m1 = new M1();
	 m1.transform(this);
	 //System.out.println("M1:  " + m1.getM1bfpf());
	 //EntityManager em = Utils.getEm_nieuw();
	 //em.getTransaction().begin();
	 m1.truncate();
	 em.persist(m1);
	 //em.getTransaction().commit();
	 
	 
	 
	 int dayCountMarriage = Utils.dayCount(getHdag(), getHmaand(), getHjaar());
	 int seqNr = 1;
	 
	 for(Huwafk huwafk: huwafkL){
		 
		 if(huwafk.getHwaknr() != seqNr++)
			 Utils.message(101700 + Constants.E_SQM2SDSQ, getIdnr(), 0, "HSN_CIVREC_STD", "M2");  // 101700 is errorBase for sequence function
		
		 int dayCountAnnouncement = Utils.dayCount(huwafk.getHwakdg(), huwafk.getHwakmd(), huwafk.getHwakjr());
		 if(dayCountMarriage > 0 && dayCountAnnouncement > 0 && dayCountAnnouncement > dayCountMarriage)
			 Utils.message(300500 + Constants.E_DLM2M2AM, getIdnr(), 0, "HSN_CIVREC_STD", "M2", huwafk.getHwakdg() + "-" + huwafk.getHwakmd() + "-" + huwafk.getHwakjr() + " and " +
					 getHdag() + "-" + getHmaand() + "-" + getHjaar());  
		 
		 huwafk.convert(em);
	 }

	 
	 // Vlgnreh is consecutive for Huwer = 'm' and for Huwer = 'v'
	 int seqNrMan = 1;
	 int seqNrWom = 1;
	 for(Huweer huweer: huweerL){
		 if(huweer.getHuwer() != null)
			 if((huweer.getHuwer().equalsIgnoreCase("M") && huweer.getVlgnreh() != seqNrMan++) || (huweer.getHuwer().equalsIgnoreCase("V") && huweer.getVlgnreh() != seqNrWom++))
				 Utils.message(101700 + Constants.E_SQM3SDSQ, getIdnr(), 0, "HSN_CIVREC_STD", "M3");  // 101700 is errorBase for sequence function
		
		 huweer.convert(em);
	 }

	 seqNr = 1;
	 for(Huwgtg huwgtg: huwgtgL){
		 if(huwgtg.getVlgnrgt() != seqNr++)
			 Utils.message(101700 + Constants.E_SQM4SDSQ, getIdnr(), 0, "HSN_CIVREC_STD", "M4");  // 101700 is errorBase for sequence function		

		 huwgtg.convert(em);
	 }
	 if(seqNr > 5)  // only 4 witnesses at marriage
		 Utils.message(102000 + Constants.E_WIM4IDNR, getIdnr(), 0, "HSN_CIVREC_STD", "M4");  // 101700 is errorBase for sequence function

	 seqNr = 1;
	 for(Huwvrknd huwvrknd: huwvrkndL){
	 	 if(huwvrknd.getVlgnrvk() != seqNr++)
	 		 Utils.message(101700 + Constants.E_SQM5C_SQ, getIdnr(), 0, "HSN_CIVREC_STD", "M5");  // 101700 is errorBase for sequence function
		
		 huwvrknd.convert(em);
	 }

	 
	 M6 m6 = null;
	 if(huwbyzL.size() > 0){
		 m6 = new M6();
		 m6.setIdnr(getIdnr());

		 String byz = "";
		 seqNr = 1;
		 //System.out.println("Idnr = " + getIdnr() + " seqnr = " + seqNr);
		 
		 for(Huwbyz huwbyz: huwbyzL){
			 if(huwbyz.getByznr() != seqNr++)
				 Utils.message(101700 + Constants.E_SQM6SDSQ, getIdnr(), 0, "HSN_CIVREC_STD", "M6");  // 101700 is errorBase for sequence function
			 
	         int result = 0;
	         
	         if((result = Functions.date_f(huwbyz.getHdag(), getHmaand(), getHjaar())) != 0)
	          	Utils.message(result + Constants.E_DAM6SDMY, getIdnr(), 0, "HSN_CIVREC_STD", "M6");

			 
			 
			 
			 byz = byz + huwbyz.getByz() + " ";		 
		 }
		 m6.setM6sdmi(byz.trim());
		 em.persist(m6);
	 }

	 
	 
	 
	 
 }

public void resolveAl(){
	
	setMarriageActPlace(getHplts());
	
	if(getMarriageActPlace() != null){
		
	
		if(getIplts() != null &&  Utils.toBeTranslated(getIplts().trim()))
			setIplts(getMarriageActPlace());
		
		if(getOadrhm() != null &&  Utils.toBeTranslated(getOadrhm().trim()))
			setOadrhm(getMarriageActPlace());
		
		if(getOadrehm() != null &&  Utils.toBeTranslated(getOadrehm().trim()))
			setOadrehm(getMarriageActPlace());
		
		
		
		if(getGebplhv() != null &&  Utils.toBeTranslated(getGebplhv().trim()))
			setGebplhv(getMarriageActPlace());
		
		if(getAdrhv() != null &&  Utils.toBeTranslated(getAdrhv()))
			setAdrhv(getMarriageActPlace());
		
		if(getOadrhv() != null &&  Utils.toBeTranslated(getOadrhv().trim()))
			setOadrhv(getMarriageActPlace());
		
		if(getOadrehv() != null &&  Utils.toBeTranslated(getOadrehv()))
			setOadrehv(getMarriageActPlace());
		

		
		if(getGebplhm() != null &&  Utils.toBeTranslated(getGebplhm().trim()))
			setGebplhm(getMarriageActPlace());
		
		if(getAdrhm() != null &&  Utils.toBeTranslated(getAdrhm().trim()))
			setAdrhm(getMarriageActPlace());
		
		if(getOadrhm() != null &&  Utils.toBeTranslated(getOadrhm().trim()))
			setOadrhm(getMarriageActPlace());
		
		if(getOadrehm() != null &&  Utils.toBeTranslated(getOadrehm().trim()))
			setOadrehm(getMarriageActPlace());
				
		
		
		
		if(getAdrvrhm() != null &&  Utils.toBeTranslated(getAdrvrhm().trim()))
			setAdrvrhm(getMarriageActPlace());
		
		if(getPlovvrhm() != null &&  Utils.toBeTranslated(getPlovvrhm().trim()))
			setPlovvrhm(getMarriageActPlace());
		
		if(getAdrmrhm() != null &&  Utils.toBeTranslated(getAdrmrhm().trim()))
			setAdrmrhm(getMarriageActPlace());
		
		if(getPlovmrhm() != null &&  Utils.toBeTranslated(getPlovmrhm().trim()))
			setPlovmrhm(getMarriageActPlace());
		
		if(getAdrvrhv() != null &&  Utils.toBeTranslated(getAdrvrhv().trim()))
			setAdrvrhv(getMarriageActPlace());
		
		if(getPlovvrhv() != null &&  Utils.toBeTranslated(getPlovvrhv().trim()))
			setPlovvrhv(getMarriageActPlace());
		
		if(getAdrmrhv() != null &&  Utils.toBeTranslated(getAdrmrhv().trim()))
			setAdrmrhv(getMarriageActPlace());
		
		if(getPlovmrhv() != null &&  Utils.toBeTranslated(getPlovmrhv().trim()))
			setPlovmrhv(getMarriageActPlace());
		
		for(Huwafk huwafk: huwafkL)
			huwafk.resolveAl();

		 for(Huwgtg huwgtg: huwgtgL)
			 huwgtg.resolveAl();
		
	}
	
	 
}



public int getIdnr() {
	return idnr;
}

public void setIdnr(int idnr) {
	this.idnr = idnr;
}

public int getHvlgnr() {
	return hvlgnr;
}

public void setHvlgnr(int hvlgnr) {
	this.hvlgnr = hvlgnr;
}

public int getHgemnr() {
	return hgemnr;
}

public void setHgemnr(int hgemnr) {
	this.hgemnr = hgemnr;
}

public int getHaktenr() {
	return haktenr;
}

public void setHaktenr(int haktenr) {
	this.haktenr = haktenr;
}

public String getHplts() {
	return hplts;
}

public void setHplts(String hplts) {
	this.hplts = hplts;
}

public int getHuur() {
	return huur;
}

public void setHuur(int huur) {
	this.huur = huur;
}

public int getHdag() {
	return hdag;
}

public void setHdag(int hdag) {
	this.hdag = hdag;
}

public int getHmaand() {
	return hmaand;
}

public void setHmaand(int hmaand) {
	this.hmaand = hmaand;
}

public int getHjaar() {
	return hjaar;
}

public void setHjaar(int hjaar) {
	this.hjaar = hjaar;
}

public String getScheidng() {
	return scheidng;
}

public void setScheidng(String scheidng) {
	this.scheidng = scheidng;
}

public int getSdag() {
	return sdag;
}

public void setSdag(int sdag) {
	this.sdag = sdag;
}

public int getSmaand() {
	return smaand;
}

public void setSmaand(int smaand) {
	this.smaand = smaand;
}

public int getSjaar() {
	return sjaar;
}

public void setSjaar(int sjaar) {
	this.sjaar = sjaar;
}

public String getSplts() {
	return splts;
}

public void setSplts(String splts) {
	this.splts = splts;
}

public int getIdag() {
	return idag;
}

public void setIdag(int idag) {
	this.idag = idag;
}

public int getImaand() {
	return imaand;
}

public void setImaand(int imaand) {
	this.imaand = imaand;
}

public int getIjaar() {
	return ijaar;
}

public void setIjaar(int ijaar) {
	this.ijaar = ijaar;
}

public String getIplts() {
	return iplts;
}

public void setIplts(String iplts) {
	this.iplts = iplts;
}

public int getLftjhm() {
	return lftjhm;
}

public void setLftjhm(int lftjhm) {
	this.lftjhm = lftjhm;
}

public int getLftjhv() {
	return lftjhv;
}

public void setLftjhv(int lftjhv) {
	this.lftjhv = lftjhv;
}

public String getGebsex() {
	return gebsex;
}

public void setGebsex(String gebsex) {
	this.gebsex = gebsex;
}

public String getAnmhm() {
	return anmhm;
}

public void setAnmhm(String anmhm) {
	this.anmhm = anmhm;
}

public String getTushm() {
	return tushm;
}

public void setTushm(String tushm) {
	this.tushm = tushm;
}

public String getVrn1hm() {
	return vrn1hm;
}

public void setVrn1hm(String vrn1hm) {
	this.vrn1hm = vrn1hm;
}

public String getVrn2hm() {
	return vrn2hm;
}

public void setVrn2hm(String vrn2hm) {
	this.vrn2hm = vrn2hm;
}

public String getVrn3hm() {
	return vrn3hm;
}

public void setVrn3hm(String vrn3hm) {
	this.vrn3hm = vrn3hm;
}

public String getBrphm() {
	return brphm;
}

public void setBrphm(String brphm) {
	this.brphm = brphm;
}

public int getGebplnhm() {
	return gebplnhm;
}

public void setGebplnhm(int gebplnhm) {
	this.gebplnhm = gebplnhm;
}

public String getGebplhm() {
	return gebplhm;
}

public void setGebplhm(String gebplhm) {
	this.gebplhm = gebplhm;
}

public String getAdrhm() {
	return adrhm;
}

public void setAdrhm(String adrhm) {
	this.adrhm = adrhm;
}

public String getOadrhm() {
	return oadrhm;
}

public void setOadrhm(String oadrhm) {
	this.oadrhm = oadrhm;
}

public String getOadrehm() {
	return oadrehm;
}

public void setOadrehm(String oadrehm) {
	this.oadrehm = oadrehm;
}

public String getBsthm() {
	return bsthm;
}

public void setBsthm(String bsthm) {
	this.bsthm = bsthm;
}

public String getHndhm() {
	return hndhm;
}

public void setHndhm(String hndhm) {
	this.hndhm = hndhm;
}

public String getAnmhv() {
	return anmhv;
}

public void setAnmhv(String anmhv) {
	this.anmhv = anmhv;
}

public String getTushv() {
	return tushv;
}

public void setTushv(String tushv) {
	this.tushv = tushv;
}

public String getVrn1hv() {
	return vrn1hv;
}

public void setVrn1hv(String vrn1hv) {
	this.vrn1hv = vrn1hv;
}

public String getVrn2hv() {
	return vrn2hv;
}

public void setVrn2hv(String vrn2hv) {
	this.vrn2hv = vrn2hv;
}

public String getVrn3hv() {
	return vrn3hv;
}

public void setVrn3hv(String vrn3hv) {
	this.vrn3hv = vrn3hv;
}

public String getBrphv() {
	return brphv;
}

public void setBrphv(String brphv) {
	this.brphv = brphv;
}


public String getGebplhv() {
	return gebplhv;
}

public void setGebplhv(String gebplhv) {
	this.gebplhv = gebplhv;
}

public String getAdrhv() {
	return adrhv;
}

public void setAdrhv(String adrhv) {
	this.adrhv = adrhv;
}

public String getOadrhv() {
	return oadrhv;
}

public void setOadrhv(String oadrhv) {
	this.oadrhv = oadrhv;
}

public String getOadrehv() {
	return oadrehv;
}

public void setOadrehv(String oadrehv) {
	this.oadrehv = oadrehv;
}

public String getBsthv() {
	return bsthv;
}

public void setBsthv(String bsthv) {
	this.bsthv = bsthv;
}

public String getHndhv() {
	return hndhv;
}

public void setHndhv(String hndhv) {
	this.hndhv = hndhv;
}

public String getLevvrhm() {
	return levvrhm;
}

public void setLevvrhm(String levvrhm) {
	this.levvrhm = levvrhm;
}

public String getToevrhm() {
	return toevrhm;
}

public void setToevrhm(String toevrhm) {
	this.toevrhm = toevrhm;
}

public String getAnmvrhm() {
	return anmvrhm;
}

public void setAnmvrhm(String anmvrhm) {
	this.anmvrhm = anmvrhm;
}

public String getTusvrhm() {
	return tusvrhm;
}

public void setTusvrhm(String tusvrhm) {
	this.tusvrhm = tusvrhm;
}

public String getVrn1vrhm() {
	return vrn1vrhm;
}

public void setVrn1vrhm(String vrn1vrhm) {
	this.vrn1vrhm = vrn1vrhm;
}

public String getVrn2vrhm() {
	return vrn2vrhm;
}

public void setVrn2vrhm(String vrn2vrhm) {
	this.vrn2vrhm = vrn2vrhm;
}

public String getVrn3vrhm() {
	return vrn3vrhm;
}

public void setVrn3vrhm(String vrn3vrhm) {
	this.vrn3vrhm = vrn3vrhm;
}

public String getBrpvrhm() {
	return brpvrhm;
}

public void setBrpvrhm(String brpvrhm) {
	this.brpvrhm = brpvrhm;
}

public String getAdrvrhm() {
	return adrvrhm;
}

public void setAdrvrhm(String adrvrhm) {
	this.adrvrhm = adrvrhm;
}

public String getPlovvrhm() {
	return plovvrhm;
}

public void setPlovvrhm(String plovvrhm) {
	this.plovvrhm = plovvrhm;
}

public String getHndvrhm() {
	return hndvrhm;
}

public void setHndvrhm(String hndvrhm) {
	this.hndvrhm = hndvrhm;
}

public int getLftjvrhm() {
	return lftjvrhm;
}

public void setLftjvrhm(int lftjvrhm) {
	this.lftjvrhm = lftjvrhm;
}

public String getLevmrhm() {
	return levmrhm;
}

public void setLevmrhm(String levmrhm) {
	this.levmrhm = levmrhm;
}

public String getToemrhm() {
	return toemrhm;
}

public void setToemrhm(String toemrhm) {
	this.toemrhm = toemrhm;
}

public String getAnmmrhm() {
	return anmmrhm;
}

public void setAnmmrhm(String anmmrhm) {
	this.anmmrhm = anmmrhm;
}

public String getTusmrhm() {
	return tusmrhm;
}

public void setTusmrhm(String tusmrhm) {
	this.tusmrhm = tusmrhm;
}

public String getVrn1mrhm() {
	return vrn1mrhm;
}

public void setVrn1mrhm(String vrn1mrhm) {
	this.vrn1mrhm = vrn1mrhm;
}

public String getVrn2mrhm() {
	return vrn2mrhm;
}

public void setVrn2mrhm(String vrn2mrhm) {
	this.vrn2mrhm = vrn2mrhm;
}

public String getVrn3mrhm() {
	return vrn3mrhm;
}

public void setVrn3mrhm(String vrn3mrhm) {
	this.vrn3mrhm = vrn3mrhm;
}

public String getBrpmrhm() {
	return brpmrhm;
}

public void setBrpmrhm(String brpmrhm) {
	this.brpmrhm = brpmrhm;
}

public String getAdrmrhm() {
	return adrmrhm;
}

public void setAdrmrhm(String adrmrhm) {
	this.adrmrhm = adrmrhm;
}

public String getPlovmrhm() {
	return plovmrhm;
}

public void setPlovmrhm(String plovmrhm) {
	this.plovmrhm = plovmrhm;
}

public String getHndmrhm() {
	return hndmrhm;
}

public void setHndmrhm(String hndmrhm) {
	this.hndmrhm = hndmrhm;
}

public int getLftjmrhm() {
	return lftjmrhm;
}

public void setLftjmrhm(int lftjmrhm) {
	this.lftjmrhm = lftjmrhm;
}

public String getLevvrhv() {
	return levvrhv;
}

public void setLevvrhv(String levvrhv) {
	this.levvrhv = levvrhv;
}

public String getToevrhv() {
	return toevrhv;
}

public void setToevrhv(String toevrhv) {
	this.toevrhv = toevrhv;
}

public String getAnmvrhv() {
	return anmvrhv;
}

public void setAnmvrhv(String anmvrhv) {
	this.anmvrhv = anmvrhv;
}

public String getTusvrhv() {
	return tusvrhv;
}

public void setTusvrhv(String tusvrhv) {
	this.tusvrhv = tusvrhv;
}

public String getVrn1vrhv() {
	return vrn1vrhv;
}

public void setVrn1vrhv(String vrn1vrhv) {
	this.vrn1vrhv = vrn1vrhv;
}

public String getVrn2vrhv() {
	return vrn2vrhv;
}

public void setVrn2vrhv(String vrn2vrhv) {
	this.vrn2vrhv = vrn2vrhv;
}

public String getVrn3vrhv() {
	return vrn3vrhv;
}

public void setVrn3vrhv(String vrn3vrhv) {
	this.vrn3vrhv = vrn3vrhv;
}

public String getBrpvrhv() {
	return brpvrhv;
}

public void setBrpvrhv(String brpvrhv) {
	this.brpvrhv = brpvrhv;
}

public String getAdrvrhv() {
	return adrvrhv;
}

public void setAdrvrhv(String adrvrhv) {
	this.adrvrhv = adrvrhv;
}

public String getPlovvrhv() {
	return plovvrhv;
}

public void setPlovvrhv(String plovvrhv) {
	this.plovvrhv = plovvrhv;
}

public String getHndvrhv() {
	return hndvrhv;
}

public void setHndvrhv(String hndvrhv) {
	this.hndvrhv = hndvrhv;
}

public int getLftjvrhv() {
	return lftjvrhv;
}

public void setLftjvrhv(int lftjvrhv) {
	this.lftjvrhv = lftjvrhv;
}

public String getLevmrhv() {
	return levmrhv;
}

public void setLevmrhv(String levmrhv) {
	this.levmrhv = levmrhv;
}

public String getToemrhv() {
	return toemrhv;
}

public void setToemrhv(String toemrhv) {
	this.toemrhv = toemrhv;
}

public String getAnmmrhv() {
	return anmmrhv;
}

public void setAnmmrhv(String anmmrhv) {
	this.anmmrhv = anmmrhv;
}

public String getTusmrhv() {
	return tusmrhv;
}

public void setTusmrhv(String tusmrhv) {
	this.tusmrhv = tusmrhv;
}

public String getVrn1mrhv() {
	return vrn1mrhv;
}

public void setVrn1mrhv(String vrn1mrhv) {
	this.vrn1mrhv = vrn1mrhv;
}

public String getVrn2mrhv() {
	return vrn2mrhv;
}

public void setVrn2mrhv(String vrn2mrhv) {
	this.vrn2mrhv = vrn2mrhv;
}

public String getVrn3mrhv() {
	return vrn3mrhv;
}

public void setVrn3mrhv(String vrn3mrhv) {
	this.vrn3mrhv = vrn3mrhv;
}

public String getBrpmrhv() {
	return brpmrhv;
}

public void setBrpmrhv(String brpmrhv) {
	this.brpmrhv = brpmrhv;
}

public String getAdrmrhv() {
	return adrmrhv;
}

public void setAdrmrhv(String adrmrhv) {
	this.adrmrhv = adrmrhv;
}

public String getPlovmrhv() {
	return plovmrhv;
}

public void setPlovmrhv(String plovmrhv) {
	this.plovmrhv = plovmrhv;
}

public String getHndmrhv() {
	return hndmrhv;
}

public void setHndmrhv(String hndmrhv) {
	this.hndmrhv = hndmrhv;
}

public int getLftjmrhv() {
	return lftjmrhv;
}

public void setLftjmrhv(int lftjmrhv) {
	this.lftjmrhv = lftjmrhv;
}

public String getUgebhuw() {
	return ugebhuw;
}

public void setUgebhuw(String ugebhuw) {
	this.ugebhuw = ugebhuw;
}

public String getUovloud() {
	return uovloud;
}

public void setUovloud(String uovloud) {
	this.uovloud = uovloud;
}

public String getUovlech() {
	return uovlech;
}

public void setUovlech(String uovlech) {
	this.uovlech = uovlech;
}

public String getCertnatm() {
	return certnatm;
}

public void setCertnatm(String certnatm) {
	this.certnatm = certnatm;
}

public String getToestnot() {
	return toestnot;
}

public void setToestnot(String toestnot) {
	this.toestnot = toestnot;
}

public String getAktebek() {
	return aktebek;
}

public void setAktebek(String aktebek) {
	this.aktebek = aktebek;
}

public String getOnvermgn() {
	return onvermgn;
}

public void setOnvermgn(String onvermgn) {
	this.onvermgn = onvermgn;
}

public String getCommand() {
	return command;
}

public void setCommand(String command) {
	this.command = command;
}

public String getToestvgd() {
	return toestvgd;
}

public void setToestvgd(String toestvgd) {
	this.toestvgd = toestvgd;
}

public String getGeghuw() {
	return geghuw;
}

public void setGeghuw(String geghuw) {
	this.geghuw = geghuw;
}

public String getGegvr() {
	return gegvr;
}

public void setGegvr(String gegvr) {
	this.gegvr = gegvr;
}

public String getGegmr() {
	return gegmr;
}

public void setGegmr(String gegmr) {
	this.gegmr = gegmr;
}

public String getProblm() {
	return problm;
}

public void setProblm(String problm) {
	this.problm = problm;
}


public String getErken() {
	return erken;
}

public void setErken(String erken) {
	this.erken = erken;
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

public ArrayList<Huwafk> getHuwafkL() {
	return huwafkL;
}

public void setHuwafkL(ArrayList<Huwafk> huwafk) {
	this.huwafkL = huwafk;
}

public ArrayList<Huweer> getHuweerL() {
	return huweerL;
}

public void setHuweerL(ArrayList<Huweer> huweer) {
	this.huweerL = huweer;
}

public ArrayList<Huwgtg> getHuwgtgL() {
	return huwgtgL;
}

public void setHuwgtgL(ArrayList<Huwgtg> huwgtg) {
	this.huwgtgL = huwgtg;
}

public ArrayList<Huwvrknd> getHuwvrkndL() {
	return huwvrkndL;
}

public void setHuwvrkndk(ArrayList<Huwvrknd> huwvrknd) {
	this.huwvrkndL = huwvrknd;
}

public ArrayList<Huwbyz> getHuwbyzL() {
	return huwbyzL;
}

public void setHuwbyzL(ArrayList<Huwbyz> huwbyz) {
	this.huwbyzL = huwbyz;
}

public Gebknd getGebknd() {
	return gebknd;
}

public void setGebknd(Gebknd gebknd) {
	this.gebknd = gebknd;
}

public int getGebplnhv() {
	return gebplnhv;
}

public void setGebplnhv(int gebplnhv) {
	this.gebplnhv = gebplnhv;
}

public void setHuwvrkndL(ArrayList<Huwvrknd> huwvrkndL) {
	this.huwvrkndL = huwvrkndL;
}

public int getNgtg() {
	return ngtg;
}

public void setNgtg(int ngtg) {
	this.ngtg = ngtg;
}

public int getRecordID() {
	return recordID;
}

public void setRecordID(int recordID) {
	this.recordID = recordID;
}

public String getMarriageActPlace() {
	return marriageActPlace;
}

public void setMarriageActPlace(String marriageActPlace) {
	this.marriageActPlace = marriageActPlace;
}
 
 
 
 

 
}
