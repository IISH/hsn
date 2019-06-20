package iisg.nl.hsnimport;

import iisg.nl.hsnmigrate.Constants;
import iisg.nl.hsnmigrate.Utils;
import iisg.nl.hsnnieuw.D1;
import iisg.nl.hsnnieuw.D4;
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

import nl.iisg.ref.Ref;
import nl.iisg.ref.Ref_Municipality;

@Entity
@Table(name="ovlknd")
public class Ovlknd {

     @Column(name="IDNR")           private int idnr;
     @Column(name="OACGEMNR")       private int oacgemnr;
     @Column(name="OACGEMNM")       private String oacgemnm;
     @Column(name="OAKTENR")        private int oaktenr;
     @Column(name="OAKTEUUR")       private int oakteuur;
     @Column(name="OAKTEMIN")       private int oaktemin;
     @Column(name="OAKTEDAG")       private int oaktedag;
     @Column(name="OAKTEMND")       private int oaktemnd;
     @Column(name="OAKTEJR")        private int oaktejr;
     @Column(name="LENGEB")         private int lengeb;
     @Column(name="AGVVADR")        private String agvvadr;
     @Column(name="NAGVR")          private int nagvr;
     @Column(name="HNDTKV")         private String hndtkv;
     @Column(name="GEGOVL")         private String gegovl;
     @Column(name="GEGVAD")         private String gegvad;
     @Column(name="GEGMOE")         private String gegmoe;
     @Column(name="ANMOVL")         private String anmovl;
     @Column(name="TUSOVL")         private String tusovl;
     @Column(name="VRN1OVL")        private String vrn1ovl;
     @Column(name="VRN2OVL")        private String vrn2ovl;
     @Column(name="VRN3OVL")        private String vrn3ovl;
     @Column(name="LAAUG")          private int laaug;
     @Column(name="BRPOVL")         private String brpovl;
     @Column(name="GBPOVL")         private int gbpovl;
     @Column(name="GGMOVL")         private String ggmovl;
     @Column(name="ADROVL")         private String adrovl;
     @Column(name="BRGOVL")         private String brgovl;
     @Column(name="OVLSEX")         private String ovlsex;
     @Column(name="OVLDAG")         private int ovldag;
     @Column(name="OVLMND")         private int ovlmnd;
     @Column(name="OVLJR")          private int ovljr;
     @Column(name="OVLUUR")         private int ovluur;
     @Column(name="OVLMIN")         private int ovlmin;
     @Column(name="PLOOVL")         private String ploovl;
     @Column(name="LFTUOVL")        private int lftuovl;
     @Column(name="LFTDOVL")        private int lftdovl;
     @Column(name="LFTWOVL")        private int lftwovl;
     @Column(name="LFTMOVL")        private int lftmovl;
     @Column(name="LFTJOVL")        private int lftjovl;
     @Column(name="MREOVL")         private int mreovl;
     @Column(name="ANMVOVL")        private String anmvovl;
     @Column(name="TUSVOVL")        private String tusvovl;
     @Column(name="VRN1VOVL")       private String vrn1vovl;
     @Column(name="VRN2VOVL")       private String vrn2vovl;
     @Column(name="VRN3VOVL")       private String vrn3vovl;
     @Column(name="LEVVOVL")        private String levvovl;
     @Column(name="BRPVOVL")        private String brpvovl;
     @Column(name="LFVROVL")        private int lfvrovl;
     @Column(name="ADRVOVL")        private String adrvovl;
     @Column(name="ANMMOVL")        private String anmmovl;
     @Column(name="TUSMOVL")        private String tusmovl;
     @Column(name="VRN1MOVL")       private String vrn1movl;
     @Column(name="VRN2MOVL")       private String vrn2movl;
     @Column(name="VRN3MOVL")       private String vrn3movl;
     @Column(name="LEVMOVL")        private String levmovl;
     @Column(name="BRPMOVL")        private String brpmovl;
     @Column(name="LFMROVL")        private int lfmrovl;
     @Column(name="ADRMOVL")        private String adrmovl;
     @Column(name="EXTRACT")        private String extract;
     @Column(name="PROBLM")         private String problm;
     @Column(name="ARCH")           private String arch;
     @Column(name="OPDRNR")         private String opdrnr;
     @Column(name="DATUM")          private String datum;
     @Column(name="INIT")           private String init;
     @Column(name="VERSIE")         private String versie;
     @Column(name="ONDRZKO")        private String ondrzko;
     @Column(name="ARCHO")          private String archo;
     @Column(name="OPDRNRO")        private String opdrnro;
     @Column(name="DATUMO")         private String datumo;
     @Column(name="INITO")          private String inito;
     @Column(name="VERSIEO")        private String versieo;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID") private int recordID;
 
 @Transient                     private ArrayList<Ovlech>   ovlechL = new ArrayList<Ovlech>();
 @Transient                     private ArrayList<Ovlbyz>   ovlbyzL = new ArrayList<Ovlbyz>();
 @Transient                     private ArrayList<Ovlagv>   ovlagvL = new ArrayList<Ovlagv>();

 @Transient                     private           Gebknd    gebknd;
 @Transient						private String    deathActPlace = null;

 
public void convert(EntityManager em){
	 
	 //System.out.println("    Ovlknd convert");
	 
	 D1 d1 = new D1();
	 d1.transform(this);
	 //EntityManager em = Utils.getEm_nieuw();
	 //em.getTransaction().begin();
	 d1.truncate();
	 em.persist(d1);
	 
	 // Save address objects
	 
	 if(d1.getD1sdcla() != null) em.persist(d1.getD1sdcla());
	 if(d1.getD1rpbla() != null) em.persist(d1.getD1rpbla());
	 if(d1.getD1rplla() != null) em.persist(d1.getD1rplla());
	 if(d1.getD1rpdla() != null) em.persist(d1.getD1rpdla());
	 if(d1.getD1falla() != null) em.persist(d1.getD1falla());
	 if(d1.getD1molla() != null) em.persist(d1.getD1molla());

	 //em.getTransaction().commit();

	 
	 int seqNr = 1;
	 for(Ovlech ovlech: ovlechL){
		 if(ovlech.getVlgech() != seqNr++)
			 Utils.message(101700 + Constants.E_SQD2S_SQ, getIdnr(), 0, "HSN_CIVREC_STD", "D2");  // 101700 is errorBase for sequence function
		
		 ovlech.convert(em);
	 }

	 if(getAgvvadr() != null &&  getAgvvadr().equalsIgnoreCase("J"))
		 seqNr = 2;
	 else
		 seqNr = 1;

	 for(Ovlagv ovlagv: ovlagvL){
		 if(ovlagv.getVlgnrag() != seqNr++){
			 Utils.message(101700 + Constants.E_SQD3I_SQ, getIdnr(), 0, "HSN_CIVREC_STD", "D3");  // 101700 is errorBase for sequence function
		 }

		 ovlagv.convert(em);
	 }

	 D4 d4 = null;
	 if(ovlbyzL.size() > 0){
		 d4 = new D4();
		 d4.setIdnr(getIdnr());

		 String byz = "";
		 seqNr = 1;
		 for(Ovlbyz ovlbyz: ovlbyzL){
			 if(ovlbyz.getByznr() != seqNr++)
				 Utils.message(101700 + Constants.E_SQM6SDSQ, getIdnr(), 0, "HSN_CIVREC_STD", "D4");  // 101700 is errorBase for sequence function
			 byz = byz +ovlbyz.getByz() + " ";		 
		 }
		 d4.setD4sdmi(byz.trim());
		 em.persist(d4);
	 }
	 
 }

public void resolveAl(){
	
	 if(getOacgemnr() > 0){
		 
		 Ref_Municipality r = Ref.getMunicipality(getOacgemnr());
		 if(r != null && r.getMunicipalityName() != null && r.getMunicipalityName().trim().length() >0) 
			 setDeathActPlace(r.getMunicipalityName().trim());
	 }
	 else
		 setDeathActPlace(getOacgemnm());

	
	
	if(getDeathActPlace() != null){
		
	
		if(getAdrovl() != null &&  Utils.toBeTranslated(getAdrovl().trim()))
			setAdrovl(getDeathActPlace());
		
		if(getPloovl() != null &&  Utils.toBeTranslated(getPloovl()))
			setPloovl(getDeathActPlace());

		if(getAdrvovl() != null &&  Utils.toBeTranslated(getAdrvovl().trim()))
			setAdrvovl(getDeathActPlace());
		
		if(getAdrmovl() != null &&  Utils.toBeTranslated(getAdrmovl().trim()))
			setAdrmovl(getDeathActPlace());
		
		for(Ovlech ovlech: ovlechL)
			ovlech.resolveAl();

		for(Ovlagv ovlagv: ovlagvL)
			ovlagv.resolveAl();

		
	}
	
	 
}

 
public int getIdnr() {
	return idnr;
}
public void setIdnr(int idnr) {
	this.idnr = idnr;
}
public String getOacgemnm() {
	return oacgemnm;
}
public void setOacgemnm(String oacgemnm) {
	this.oacgemnm = oacgemnm;
}
public int getOaktenr() {
	return oaktenr;
}
public void setOaktenr(int oaktenr) {
	this.oaktenr = oaktenr;
}
public int getOakteuur() {
	return oakteuur;
}
public void setOakteuur(int oakteuur) {
	this.oakteuur = oakteuur;
}
public int getOaktemin() {
	return oaktemin;
}
public void setOaktemin(int oaktemin) {
	this.oaktemin = oaktemin;
}
public int getOaktedag() {
	return oaktedag;
}
public void setOaktedag(int oaktedag) {
	this.oaktedag = oaktedag;
}
public int getOaktemnd() {
	return oaktemnd;
}
public void setOaktemnd(int oaktemnd) {
	this.oaktemnd = oaktemnd;
}
public int getOaktejr() {
	return oaktejr;
}
public void setOaktejr(int oaktejr) {
	this.oaktejr = oaktejr;
}

public String getAgvvadr() {
	return agvvadr;
}
public void setAgvvadr(String agvvadr) {
	this.agvvadr = agvvadr;
}
public int getNagvr() {
	return nagvr;
}
public void setNagvr(int nagvr) {
	this.nagvr = nagvr;
}
public String getHndtkv() {
	return hndtkv;
}
public void setHndtkv(String hndtkv) {
	this.hndtkv = hndtkv;
}
public String getGegovl() {
	return gegovl;
}
public void setGegovl(String gegovl) {
	this.gegovl = gegovl;
}
public String getGegvad() {
	return gegvad;
}
public void setGegvad(String gegvad) {
	this.gegvad = gegvad;
}
public String getGegmoe() {
	return gegmoe;
}
public void setGegmoe(String gegmoe) {
	this.gegmoe = gegmoe;
}
public String getAnmovl() {
	return anmovl;
}
public void setAnmovl(String anmovl) {
	this.anmovl = anmovl;
}
public String getTusovl() {
	return tusovl;
}
public void setTusovl(String tusovl) {
	this.tusovl = tusovl;
}
public String getVrn1ovl() {
	return vrn1ovl;
}
public void setVrn1ovl(String vrn1ovl) {
	this.vrn1ovl = vrn1ovl;
}
public String getVrn2ovl() {
	return vrn2ovl;
}
public void setVrn2ovl(String vrn2ovl) {
	this.vrn2ovl = vrn2ovl;
}
public String getVrn3ovl() {
	return vrn3ovl;
}
public void setVrn3ovl(String vrn3ovl) {
	this.vrn3ovl = vrn3ovl;
}
public int getLaaug() {
	return laaug;
}
public void setLaaug(int laaug) {
	this.laaug = laaug;
}
public String getBrpovl() {
	return brpovl;
}
public void setBrpovl(String brpovl) {
	this.brpovl = brpovl;
}
public int getGbpovl() {
	return gbpovl;
}
public void setGbpovl(int gbpovl) {
	this.gbpovl = gbpovl;
}
public String getGgmovl() {
	return ggmovl;
}
public void setGgmovl(String ggmovl) {
	this.ggmovl = ggmovl;
}
public String getAdrovl() {
	return adrovl;
}
public void setAdrovl(String adrovl) {
	this.adrovl = adrovl;
}
public String getBrgovl() {
	return brgovl;
}
public void setBrgovl(String brgovl) {
	this.brgovl = brgovl;
}
public String getOvlsex() {
	return ovlsex;
}
public void setOvlsex(String ovlsex) {
	this.ovlsex = ovlsex;
}
public int getOvldag() {
	return ovldag;
}
public void setOvldag(int ovldag) {
	this.ovldag = ovldag;
}
public int getOvlmnd() {
	return ovlmnd;
}
public void setOvlmnd(int ovlmnd) {
	this.ovlmnd = ovlmnd;
}
public int getOvljr() {
	return ovljr;
}
public void setOvljr(int ovljr) {
	this.ovljr = ovljr;
}
public int getOvluur() {
	return ovluur;
}
public void setOvluur(int ovluur) {
	this.ovluur = ovluur;
}
public int getOvlmin() {
	return ovlmin;
}
public void setOvlmin(int ovlmin) {
	this.ovlmin = ovlmin;
}
public String getPloovl() {
	return ploovl;
}
public void setPloovl(String ploovl) {
	this.ploovl = ploovl;
}
public int getLftuovl() {
	return lftuovl;
}
public void setLftuovl(int lftuovl) {
	this.lftuovl = lftuovl;
}
public int getLftdovl() {
	return lftdovl;
}
public void setLftdovl(int lftdovl) {
	this.lftdovl = lftdovl;
}
public int getLftwovl() {
	return lftwovl;
}
public void setLftwovl(int lftwovl) {
	this.lftwovl = lftwovl;
}
public int getLftmovl() {
	return lftmovl;
}
public void setLftmovl(int lftmovl) {
	this.lftmovl = lftmovl;
}
public int getLftjovl() {
	return lftjovl;
}
public void setLftjovl(int lftjovl) {
	this.lftjovl = lftjovl;
}
public int getMreovl() {
	return mreovl;
}
public void setMreovl(int mreovl) {
	this.mreovl = mreovl;
}
public String getAnmvovl() {
	return anmvovl;
}
public void setAnmvovl(String anmvovl) {
	this.anmvovl = anmvovl;
}
public String getTusvovl() {
	return tusvovl;
}
public void setTusvovl(String tusvovl) {
	this.tusvovl = tusvovl;
}
public String getVrn1vovl() {
	return vrn1vovl;
}
public void setVrn1vovl(String vrn1vovl) {
	this.vrn1vovl = vrn1vovl;
}
public String getVrn2vovl() {
	return vrn2vovl;
}
public void setVrn2vovl(String vrn2vovl) {
	this.vrn2vovl = vrn2vovl;
}
public String getVrn3vovl() {
	return vrn3vovl;
}
public void setVrn3vovl(String vrn3vovl) {
	this.vrn3vovl = vrn3vovl;
}
public String getLevvovl() {
	return levvovl;
}
public void setLevvovl(String levvovl) {
	this.levvovl = levvovl;
}
public String getBrpvovl() {
	return brpvovl;
}
public void setBrpvovl(String brpvovl) {
	this.brpvovl = brpvovl;
}
public int getLfvrovl() {
	return lfvrovl;
}
public void setLfvrovl(int lfvrovl) {
	this.lfvrovl = lfvrovl;
}
public String getAdrvovl() {
	return adrvovl;
}
public void setAdrvovl(String adrvovl) {
	this.adrvovl = adrvovl;
}
public String getAnmmovl() {
	return anmmovl;
}
public void setAnmmovl(String anmmovl) {
	this.anmmovl = anmmovl;
}
public String getTusmovl() {
	return tusmovl;
}
public void setTusmovl(String tusmovl) {
	this.tusmovl = tusmovl;
}
public String getVrn1movl() {
	return vrn1movl;
}
public void setVrn1movl(String vrn1movl) {
	this.vrn1movl = vrn1movl;
}
public String getVrn2movl() {
	return vrn2movl;
}
public void setVrn2movl(String vrn2movl) {
	this.vrn2movl = vrn2movl;
}
public String getVrn3movl() {
	return vrn3movl;
}
public void setVrn3movl(String vrn3movl) {
	this.vrn3movl = vrn3movl;
}
public String getLevmovl() {
	return levmovl;
}
public void setLevmovl(String levmovl) {
	this.levmovl = levmovl;
}
public String getBrpmovl() {
	return brpmovl;
}
public void setBrpmovl(String brpmovl) {
	this.brpmovl = brpmovl;
}
public int getLfmrovl() {
	return lfmrovl;
}
public void setLfmrovl(int lfmrovl) {
	this.lfmrovl = lfmrovl;
}
public String getAdrmovl() {
	return adrmovl;
}
public void setAdrmovl(String adrmovl) {
	this.adrmovl = adrmovl;
}
public String getExtract() {
	return extract;
}
public void setExtract(String extract) {
	this.extract = extract;
}
public String getProblm() {
	return problm;
}
public void setProblm(String problm) {
	this.problm = problm;
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
public ArrayList<Ovlech> getOvlechL() {
	return ovlechL;
}
public void setOvlechL(ArrayList<Ovlech> ovlech) {
	this.ovlechL = ovlech;
}
public Gebknd getGebknd() {
	return gebknd;
}
public void setGebknd(Gebknd gebknd) {
	this.gebknd = gebknd;
}
public ArrayList<Ovlbyz> getOvlbyzL() {
	return ovlbyzL;
}
public void setOvlbyzL(ArrayList<Ovlbyz> ovlbyz) {
	this.ovlbyzL = ovlbyz;
}
public ArrayList<Ovlagv> getOvlagvL() {
	return ovlagvL;
}
public void setOvlagvL(ArrayList<Ovlagv> ovlagv) {
	this.ovlagvL = ovlagv;
}


public int getOacgemnr() {
	return oacgemnr;
}


public void setOacgemnr(int oacgemnr) {
	this.oacgemnr = oacgemnr;
}


public int getLengeb() {
	return lengeb;
}


public void setLengeb(int lengeb) {
	this.lengeb = lengeb;
}


public int getRecordID() {
	return recordID;
}


public void setRecordID(int recordID) {
	this.recordID = recordID;
}

public String getDeathActPlace() {
	return deathActPlace;
}

public void setDeathActPlace(String deathActPlace) {
	this.deathActPlace = deathActPlace;
}
 


}