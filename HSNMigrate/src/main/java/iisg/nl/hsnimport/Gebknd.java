  package iisg.nl.hsnimport;
import iisg.nl.hsnmigrate.Constants;
import iisg.nl.hsnmigrate.Utils;
import iisg.nl.hsnnieuw.B0;
import iisg.nl.hsnnieuw.B1;
import iisg.nl.hsnnieuw.B3;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import nl.iisg.ref.Ref;
import nl.iisg.ref.Ref_Location;
import nl.iisg.ref.Ref_Municipality;

@Entity
@Table(name="gebknd")
public class Gebknd {

     @Column(name="GEMNR")       private int gemnr;
     @Column(name="JAAR")        private int jaar;
     @Column(name="AKTENR")      private int aktenr;
     @Column(name="COHORTNR")    private int cohortnr;
  @Id@Column(name="IDNR")        private int idnr;
     @Column(name="OVERSAMP")    private String oversamp;
     @Column(name="INVBEPER")    private String invbeper;
     @Column(name="AKTEUUR")     private int akteuur;
     @Column(name="AKTEDAG")     private int aktedag;
     @Column(name="AKTEMND")     private int aktemnd;
     @Column(name="LENGEB")      private int lengeb;
     @Column(name="ANMAG")       private String anmag;
     @Column(name="TUSAG")       private String tusag;
     @Column(name="VRN1AG")      private String vrn1ag;
     @Column(name="VRN2AG")      private String vrn2ag;
     @Column(name="VRN3AG")      private String vrn3ag;
     @Column(name="LFTAG")       private int lftag;
     @Column(name="BRPAG")       private String brpag;
     @Column(name="ADRAG")       private String adrag;
     @Column(name="HNDAG")       private String hndag;
     @Column(name="GEBDAG")      private int gebdag;
     @Column(name="GEBMND")      private int gebmnd;
     @Column(name="GEBJR")       private int gebjr;
     @Column(name="GEBUUR")      private int gebuur;
     @Column(name="GEBMIN")      private int gebmin;
     @Column(name="GEBSEX")      private String gebsex;
     @Column(name="GEBADR")      private String gebadr;
     @Column(name="ANMMR")       private String anmmr;
     @Column(name="TUSMR")       private String tusmr;
     @Column(name="VRN1MR")      private String vrn1mr;
     @Column(name="VRN2MR")      private String vrn2mr;
     @Column(name="VRN3MR")      private String vrn3mr;
     @Column(name="LFTMR")       private int lftmr;
     @Column(name="BRGSTMR")     private String brgstmr;
     @Column(name="BRPMR")       private String brpmr;
     @Column(name="ADRMR")       private String adrmr;
     @Column(name="ANMGEB")      private String anmgeb;
     @Column(name="TUSGEB")      private String tusgeb;
     @Column(name="VRN1GEB")     private String vrn1geb;
     @Column(name="VRN2GEB")     private String vrn2geb;
     @Column(name="VRN3GEB")     private String vrn3geb;
     @Column(name="KANT")        private String kant;
     @Column(name="PROBLM")      private String problm;
  //   @Column(name="ARCH")        private String arch;
     @Column(name="OPDRNR")      private String opdrnr;
     @Column(name="DATUM")       private String datum;
     @Column(name="INIT")        private String init;
     @Column(name="VERSIE")      private String versie;
     @Column(name="ONDRZKO")     private String ondrzko;
  //   @Column(name="ARCHO")       private String archo;
     @Column(name="OPDRNRO")     private String opdrnro;
     @Column(name="DATUMO")      private String datumo;
     @Column(name="INITO")       private String inito;
     @Column(name="VERSIEO")     private String versieo;
     @Column(name="OPDRNRI")    private String orderNumberI;
//     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
//     @Column(name="RecordID") private int recordID;
 
 @Transient                  private ArrayList<Gebgtg>  gebgtgL  = new ArrayList<Gebgtg>();
 @Transient                  private ArrayList<Gebbyz>  gebbyzL  = new ArrayList<Gebbyz>();
 @Transient                  private ArrayList<Gebkant> gebkantL = new ArrayList<Gebkant>();
 @Transient                  private           Gebvdr   gebvdr;

 @Transient                  private           Ovlknd   ovlknd;
 @Transient                  private ArrayList<Huwknd>  huwkndL = new ArrayList<Huwknd>();

 
 
 @Transient                  private Stpb               stpb;

 @Transient					 private String				birthActLocation = null;
 @Transient					 private String				birthActLocationNo = null;
 
 public void convert(EntityManager em){
	 
	 
	 // Before doing anything else, we must determine the BirthAct location
	 // Next, in all fields that contain address information "Al" (=Alhier), this string must be replaced 
	 // by the BirthAct Location
	 
	 if(getGemnr() > 0){
		 
		 Ref_Municipality r = Ref.getMunicipality(getGemnr());
		 if(r!= null && r.getMunicipalityName() != null && r.getMunicipalityName().trim().length() >0){ 
 			Ref_Location l = Ref.getLocation(r.getMunicipalityName().trim());
			if(l != null  && l.getStandardCode() != null && (l.getStandardCode().equalsIgnoreCase("y"))){ 
				setBirthActLocation(l.getMunicipality());
				setBirthActLocationNo(l.getLocationNo() + "");
			}    			
		 }
	 }
	 
	 
	 if(getBirthActLocation() != null){
		 
		 //System.out.println("Resolving 1");
		 
		 if(getAdrag() != null && Utils.toBeTranslated(getAdrag().trim()))
			 setAdrag(getBirthActLocation());
		 
		 if(getGebadr() != null &&  Utils.toBeTranslated(getGebadr().trim()))
			 setGebadr(getBirthActLocation());
		 
		 if(getAdrmr() != null &&  Utils.toBeTranslated(getAdrmr().trim()))
			 setAdrmr(getBirthActLocation());
		 
		 
		 for(Gebgtg gebgtg: gebgtgL)
			 gebgtg.resolveAl();

		 for(Gebkant gebkant: gebkantL)
			 gebkant.resolveAl();
		 
		 if(gebvdr != null)
			 gebvdr.resolveAl();
		 
		 for(Huwknd huwknd: huwkndL)
			 huwknd.resolveAl();

		 if(ovlknd != null)
			 ovlknd.resolveAl();

	 }
	 
	 int seqNr = 1;
	 
	 B1 b1 = new B1();
	 b1.transform(this);
	 b1.truncate();
	 em.persist(b1);
	 
	 // Save address objects
	 
	 if(b1.getB1rplla() != null) em.persist(b1.getB1rplla());
	 if(b1.getB1molla() != null) em.persist(b1.getB1molla());
	 if(b1.getB1inlla() != null) em.persist(b1.getB1inlla());
	 

	 seqNr = 1;
	 for(Gebgtg gebgtg: gebgtgL){
		 if(gebgtg.getVlgnrgt() != seqNr++)
			 Utils.message(101700 + Constants.E_SQB2W_SQ, getIdnr(), 0, "HSN_CIVREC_STD", "B2");  // 101700 is errorBase for sequence function
		
		 gebgtg.convert(em);
	 }
	 if(seqNr != 3) // only two witnesses for birth
		 Utils.message(102000 + Constants.E_WIB2IDNR, getIdnr(), 0, "HSN_CIVREC_STD", "B2");  // 101700 is errorBase for sequence function
		 
		 

	 B3 b3 = null;
	 if(gebbyzL.size() > 0){
		 b3 = new B3();
		 b3.setIdnr(getIdnr());

		 String byz = "";
		 seqNr = 1;
		 for(Gebbyz gebbyz: gebbyzL){
			 if(gebbyz.getByznr() != seqNr++)
				 Utils.message(101700 + Constants.E_SQB3SDSQ, getIdnr(), 0, "HSN_CIVREC_STD", "B3");  // 101700 is errorBase for sequence function
			 if(gebbyz.getByz() != null)
				 byz = byz + gebbyz.getByz().trim() + " ";	

			 //if(byz.indexOf("\u00A2") >= 0){
			 //	 System.out.println("idnr = " + getIdnr() + " index = " + byz.indexOf("\u00A2") + byz);
			 //	 System.out.println(gebbyz.getByz());
			 //seqNr = 2/0;
			 //}


		 }

		 b3.setB3sdmi(byz.trim());
		 em.persist(b3);

	 }

	 for(Gebkant gebkant: gebkantL)
		 gebkant.convert(em);

	 
	 if(gebvdr != null){
		 gebvdr.convert(em);
	 }

	 for(Huwknd huwknd: huwkndL)
		 huwknd.convert(em);

	 if(ovlknd != null)
		 ovlknd.convert(em);
	 
	 

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

public String getOversamp() {
	return oversamp;
}

public void setOversamp(String oversamp) {
	this.oversamp = oversamp;
}

public String getInvbeper() {
	return invbeper;
}

public void setInvbeper(String invbeper) {
	this.invbeper = invbeper;
}

public int getAkteuur() {
	return akteuur;
}

public void setAkteuur(int akteuur) {
	this.akteuur = akteuur;
}

public int getAktedag() {
	return aktedag;
}

public void setAktedag(int aktedag) {
	this.aktedag = aktedag;
}

public int getAktemnd() {
	return aktemnd;
}

public void setAktemnd(int aktemnd) {
	this.aktemnd = aktemnd;
}

public int getLengeb() {
	return lengeb;
}

public void setLengeb(int lengeb) {
	this.lengeb = lengeb;
}

public String getAnmag() {
	return anmag;
}

public void setAnmag(String anmag) {
	this.anmag = anmag;
}

public String getTusag() {
	return tusag;
}

public void setTusag(String tusag) {
	this.tusag = tusag;
}

public String getVrn1ag() {
	return vrn1ag;
}

public void setVrn1ag(String vrn1ag) {
	this.vrn1ag = vrn1ag;
}

public String getVrn2ag() {
	return vrn2ag;
}

public void setVrn2ag(String vrn2ag) {
	this.vrn2ag = vrn2ag;
}

public String getVrn3ag() {
	return vrn3ag;
}

public void setVrn3ag(String vrn3ag) {
	this.vrn3ag = vrn3ag;
}

public int getLftag() {
	return lftag;
}

public void setLftag(int lftag) {
	this.lftag = lftag;
}

public String getBrpag() {
	return brpag;
}

public void setBrpag(String brpag) {
	this.brpag = brpag;
}

public String getAdrag() {
	return adrag;
}

public void setAdrag(String adrag) {
	this.adrag = adrag;
}

public String getHndag() {
	return hndag;
}

public void setHndag(String hndag) {
	this.hndag = hndag;
}

public int getGebdag() {
	return gebdag;
}

public void setGebdag(int gebdag) {
	this.gebdag = gebdag;
}

public int getGebmnd() {
	return gebmnd;
}

public void setGebmnd(int gebmnd) {
	this.gebmnd = gebmnd;
}

public int getGebjr() {
	return gebjr;
}

public void setGebjr(int gebjr) {
	this.gebjr = gebjr;
}

public int getGebuur() {
	return gebuur;
}

public void setGebuur(int gebuur) {
	this.gebuur = gebuur;
}

public int getGebmin() {
	return gebmin;
}

public void setGebmin(int gebmin) {
	this.gebmin = gebmin;
}

public String getGebsex() {
	return gebsex;
}

public void setGebsex(String gebsex) {
	this.gebsex = gebsex;
}

public String getGebadr() {
	return gebadr;
}

public void setGebadr(String gebadr) {
	this.gebadr = gebadr;
}

public String getAnmmr() {
	return anmmr;
}

public void setAnmmr(String anmmr) {
	this.anmmr = anmmr;
}

public String getTusmr() {
	return tusmr;
}

public void setTusmr(String tusmr) {
	this.tusmr = tusmr;
}

public String getVrn1mr() {
	return vrn1mr;
}

public void setVrn1mr(String vrn1mr) {
	this.vrn1mr = vrn1mr;
}

public String getVrn2mr() {
	return vrn2mr;
}

public void setVrn2mr(String vrn2mr) {
	this.vrn2mr = vrn2mr;
}

public String getVrn3mr() {
	return vrn3mr;
}

public void setVrn3mr(String vrn3mr) {
	this.vrn3mr = vrn3mr;
}

public int getLftmr() {
	return lftmr;
}

public void setLftmr(int lftmr) {
	this.lftmr = lftmr;
}

public String getBrgstmr() {
	return brgstmr;
}

public void setBrgstmr(String brgstmr) {
	this.brgstmr = brgstmr;
}

public String getBrpmr() {
	return brpmr;
}

public void setBrpmr(String brpmr) {
	this.brpmr = brpmr;
}

public String getAdrmr() {
	return adrmr;
}

public void setAdrmr(String adrmr) {
	this.adrmr = adrmr;
}

public String getAnmgeb() {
	return anmgeb;
}

public void setAnmgeb(String anmgeb) {
	this.anmgeb = anmgeb;
}

public String getTusgeb() {
	return tusgeb;
}

public void setTusgeb(String tusgeb) {
	this.tusgeb = tusgeb;
}

public String getVrn1geb() {
	return vrn1geb;
}

public void setVrn1geb(String vrn1geb) {
	this.vrn1geb = vrn1geb;
}

public String getVrn2geb() {
	return vrn2geb;
}

public void setVrn2geb(String vrn2geb) {
	this.vrn2geb = vrn2geb;
}

public String getVrn3geb() {
	return vrn3geb;
}

public void setVrn3geb(String vrn3geb) {
	this.vrn3geb = vrn3geb;
}

public String getKant() {
	return kant;
}

public void setKant(String kant) {
	this.kant = kant;
}

public String getProblm() {
	return problm;
}

public void setProblm(String problm) {
	this.problm = problm;
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


public String getOrderNumberI() {
	return orderNumberI;
}

public void setOrderNumberI(String orderNumberI) {
	this.orderNumberI = orderNumberI;
}

public ArrayList<Gebgtg> getGebgtgL() {
	return gebgtgL;
}

public void setGebgtgL(ArrayList<Gebgtg> gebgtg) {
	this.gebgtgL = gebgtg;
}

public ArrayList<Gebbyz> getGebbyzL() {
	return gebbyzL;
}

public void setGebbyzL(ArrayList<Gebbyz> gebbyz) {
	this.gebbyzL = gebbyz;
}

public ArrayList<Gebkant> getGebkantL() {
	return gebkantL;
}

public void setGebkantL(ArrayList<Gebkant> gebkant) {
	this.gebkantL = gebkant;
}

public Gebvdr getGebvdr() {
	return gebvdr;
}

public void setGebvdr(Gebvdr gebvdr) {
	this.gebvdr = gebvdr;
}

public Stpb getStpb() {
	return stpb;
}

public void setStpb(Stpb stpb) {
	this.stpb = stpb;
}

public Ovlknd getOvlknd() {
	return ovlknd;
}

public void setOvlknd(Ovlknd ovlknd) {
	this.ovlknd = ovlknd;
}

public ArrayList<Huwknd> getHuwkndL() {
	return huwkndL;
}

public void setHuwkndL(ArrayList<Huwknd> huwknd) {
	this.huwkndL = huwknd;
}

public String getBirthActLocation() {
	return birthActLocation;
}

public void setBirthActLocation(String birthActLocation) {
	this.birthActLocation = birthActLocation;
}

public String getBirthActLocationNo() {
	return birthActLocationNo;
}


public void setBirthActLocationNo(String birthActLocationNo) {
	this.birthActLocationNo = birthActLocationNo;
}

 
}