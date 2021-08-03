package iisg.nl.hsnimport;

import iisg.nl.hsnmigrate.Utils;
import iisg.nl.hsnnieuw.M1;
import iisg.nl.hsnnieuw.M3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="huweer")
public class Huweer  {

     @Id@Column(name="IDNR")       private int idnr;
     @Column(name="HDAG")       private int hdag;
     @Column(name="HMAAND")     private int hmaand;
     @Column(name="HJAAR")      private int hjaar;
     @Column(name="VLGNREH")    private int vlgnreh;
     @Column(name="HUWER")      private String huwer;
     @Column(name="ANMEH")      private String anmeh;
     @Column(name="TUSEH")      private String tuseh;
     @Column(name="VRN1EH")     private String vrn1eh;
     @Column(name="VRN2EH")     private String vrn2eh;
     @Column(name="VRN3EH")     private String vrn3eh;
     @Column(name="EINDEH")     private String eindeh;
 //    @Column(name="ARCH")       private String arch;
     @Column(name="OPDRNR")     private String opdrnr;
     @Column(name="DATUM")      private String datum;
     @Column(name="INIT")       private String init;
     @Column(name="VERSIE")     private String versie;
     @Column(name="ONDRZKO")    private String ondrzko;
 //    @Column(name="ARCHO")      private String archo;
     @Column(name="OPDRNRO")    private String opdrnro;
     @Column(name="DATUMO")     private String datumo;
     @Column(name="INITO")      private String inito;
     @Column(name="VERSIEO")    private String versieo;
     @Column(name="OPDRNRI")    private String orderNumberI;

 
 @Transient                     private Huwknd huwknd;
 
 
public void convert(EntityManager em){
	 
	 //System.out.println("        Huweer convert");
	 
	 M3 m3 = new M3();
	 m3.transform(this);
	 //EntityManager em = Utils.getEm_nieuw();
	 //em.getTransaction().begin();
	 m3.truncate();
	 em.persist(m3);
	 //em.getTransaction().commit();

	 
 }


public int getIdnr() {
	return idnr;
}

public void setIdnr(int idnr) {
	this.idnr = idnr;
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

public int getVlgnreh() {
	return vlgnreh;
}

public void setVlgnreh(int vlgnreh) {
	this.vlgnreh = vlgnreh;
}

public String getHuwer() {
	return huwer;
}

public void setHuwer(String huwer) {
	this.huwer = huwer;
}

public String getAnmeh() {
	return anmeh;
}

public void setAnmeh(String anmeh) {
	this.anmeh = anmeh;
}

public String getTuseh() {
	return tuseh;
}

public void setTuseh(String tuseh) {
	this.tuseh = tuseh;
}

public String getVrn1eh() {
	return vrn1eh;
}

public void setVrn1eh(String vrn1eh) {
	this.vrn1eh = vrn1eh;
}

public String getVrn2eh() {
	return vrn2eh;
}

public void setVrn2eh(String vrn2eh) {
	this.vrn2eh = vrn2eh;
}

public String getVrn3eh() {
	return vrn3eh;
}

public void setVrn3eh(String vrn3eh) {
	this.vrn3eh = vrn3eh;
}

public String getEindeh() {
	return eindeh;
}

public void setEindeh(String eindeh) {
	this.eindeh = eindeh;
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


public Huwknd getHuwknd() {
	return huwknd;
}

public void setHuwknd(Huwknd huwknd) {
	this.huwknd = huwknd;
}


 
}
