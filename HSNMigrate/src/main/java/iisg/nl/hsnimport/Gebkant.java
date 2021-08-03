package iisg.nl.hsnimport;

import iisg.nl.hsnmigrate.Utils;
import iisg.nl.hsnnieuw.B2;
import iisg.nl.hsnnieuw.B4;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="gebkant")
public class Gebkant {

   @Id@Column(name="IDNR")       private int idnr;
     @Column(name="KANTTYPE")   private int kanttype;
     @Column(name="KANTDAG")    private int kantdag;
     @Column(name="KANTMND")    private int kantmnd;
     @Column(name="KANTJR")     private int kantjr;
     @Column(name="KHUWDAG")    private int khuwdag;
     @Column(name="KHUWMND")    private int khuwmnd;
     @Column(name="KHUWJR")     private int khuwjr;
     @Column(name="KHUWGEM")    private String khuwgem;
     @Column(name="KHUWANR")    private String khuwanr;
     @Column(name="KANMVAD")    private String kanmvad;
     @Column(name="KTUSVAD")    private String ktusvad;
     @Column(name="KVRN1VAD")   private String kvrn1vad;
     @Column(name="KVRN2VAD")   private String kvrn2vad;
     @Column(name="KVRN3VAD")   private String kvrn3vad;
     @Column(name="KWYZDAG")    private int kwyzdag;
     @Column(name="KWYZMND")    private int kwyzmnd;
     @Column(name="KWYZJR")     private int kwyzjr;
     @Column(name="KWYZKB")     private int kwyzkb;
     @Column(name="KWYZSTBL")   private int kwyzstbl;
     @Column(name="KGMRB")      private String kgmrb;
     @Column(name="KGMERK")     private String kgmerk;
     @Column(name="KWGMMR")     private String kwgmmr;
     @Column(name="KBRPMR")     private String kbrpmr;
     @Column(name="KANMGEB")    private String kanmgeb;
     @Column(name="KVRN1GEB")   private String kvrn1geb;
     @Column(name="KVRN2GEB")   private String kvrn2geb;
     @Column(name="KVRN3GEB")   private String kvrn3geb;
     @Column(name="KTUSGEB")    private String ktusgeb;
     @Column(name="KSEXGEB")    private String ksexgeb;
//     @Column(name="ARCH")       private String arch;
     @Column(name="OPDRNR")     private String opdrnr;
     @Column(name="DATUM")      private String datum;
     @Column(name="INIT")       private String init;
     @Column(name="VERSIE")     private String versie;
     @Column(name="ONDRZKO")    private String ondrzko;
//     @Column(name="ARCHO")      private String archo;
     @Column(name="OPDRNRO")    private String opdrnro;
     @Column(name="DATUMO")     private String datumo;
     @Column(name="INITO")      private String inito;
     @Column(name="VERSIEO")    private String versieo;
     @Column(name="OPDRNRI")    private String orderNumberI;
 //    @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
 //    @Column(name="RecordID") private int recordID;
 
 @Transient                     private Gebknd gebknd;
 
public void convert(EntityManager em){
	
	 
	 //System.out.println("        Gebkant convert");
	 B4 b4 = new B4();
	 b4.transform(this);
	 //EntityManager em = Utils.getEm_nieuw();
	 //em.getTransaction().begin();
	 b4.truncate();
	 em.persist(b4);
	 //em.getTransaction().commit();


	 
	 
 }

public void resolveAl(){
	 
	 if(getKhuwgem() != null &&  Utils.toBeTranslated(getKhuwgem())) 
		 setKhuwgem(getGebknd().getBirthActLocation());
	 
}


public int getIdnr() {
	return idnr;
}

public void setIdnr(int idnr) {
	this.idnr = idnr;
}

public int getKanttype() {
	return kanttype;
}

public void setKanttype(int kanttype) {
	this.kanttype = kanttype;
}

public int getKantdag() {
	return kantdag;
}

public void setKantdag(int kantdag) {
	this.kantdag = kantdag;
}

public int getKantmnd() {
	return kantmnd;
}

public void setKantmnd(int kantmnd) {
	this.kantmnd = kantmnd;
}

public int getKantjr() {
	return kantjr;
}

public void setKantjr(int kantjr) {
	this.kantjr = kantjr;
}

public int getKhuwdag() {
	return khuwdag;
}

public void setKhuwdag(int khuwdag) {
	this.khuwdag = khuwdag;
}

public int getKhuwmnd() {
	return khuwmnd;
}

public void setKhuwmnd(int khuwmnd) {
	this.khuwmnd = khuwmnd;
}

public int getKhuwjr() {
	return khuwjr;
}

public void setKhuwjr(int khuwjr) {
	this.khuwjr = khuwjr;
}

public String getKhuwgem() {
	return khuwgem;
}

public void setKhuwgem(String khuwgem) {
	this.khuwgem = khuwgem;
}

public String getKhuwanr() {
	return khuwanr;
}

public void setKhuwanr(String khuwanr) {
	this.khuwanr = khuwanr;
}

public String getKanmvad() {
	return kanmvad;
}

public void setKanmvad(String kanmvad) {
	this.kanmvad = kanmvad;
}

public String getKtusvad() {
	return ktusvad;
}

public void setKtusvad(String ktusvad) {
	this.ktusvad = ktusvad;
}

public String getKvrn1vad() {
	return kvrn1vad;
}

public void setKvrn1vad(String kvrn1vad) {
	this.kvrn1vad = kvrn1vad;
}

public String getKvrn2vad() {
	return kvrn2vad;
}

public void setKvrn2vad(String kvrn2vad) {
	this.kvrn2vad = kvrn2vad;
}

public String getKvrn3vad() {
	return kvrn3vad;
}

public void setKvrn3vad(String kvrn3vad) {
	this.kvrn3vad = kvrn3vad;
}

public int getKwyzdag() {
	return kwyzdag;
}

public void setKwyzdag(int kwyzdag) {
	this.kwyzdag = kwyzdag;
}

public int getKwyzmnd() {
	return kwyzmnd;
}

public void setKwyzmnd(int kwyzmnd) {
	this.kwyzmnd = kwyzmnd;
}

public int getKwyzjr() {
	return kwyzjr;
}

public void setKwyzjr(int kwyzjr) {
	this.kwyzjr = kwyzjr;
}

public int getKwyzkb() {
	return kwyzkb;
}

public void setKwyzkb(int kwyzkb) {
	this.kwyzkb = kwyzkb;
}

public int getKwyzstbl() {
	return kwyzstbl;
}

public void setKwyzstbl(int kwyzstbl) {
	this.kwyzstbl = kwyzstbl;
}

public String getKgmrb() {
	return kgmrb;
}

public void setKgmrb(String kgmrb) {
	this.kgmrb = kgmrb;
}

public String getKgmerk() {
	return kgmerk;
}

public void setKgmerk(String kgmerk) {
	this.kgmerk = kgmerk;
}

public String getKwgmmr() {
	return kwgmmr;
}

public void setKwgmmr(String kwgmmr) {
	this.kwgmmr = kwgmmr;
}

public String getKbrpmr() {
	return kbrpmr;
}

public void setKbrpmr(String kbrpmr) {
	this.kbrpmr = kbrpmr;
}

public String getKanmgeb() {
	return kanmgeb;
}

public void setKanmgeb(String kanmgeb) {
	this.kanmgeb = kanmgeb;
}

public String getKvrn1geb() {
	return kvrn1geb;
}

public void setKvrn1geb(String kvrn1geb) {
	this.kvrn1geb = kvrn1geb;
}

public String getKvrn2geb() {
	return kvrn2geb;
}

public void setKvrn2geb(String kvrn2geb) {
	this.kvrn2geb = kvrn2geb;
}

public String getKvrn3geb() {
	return kvrn3geb;
}

public void setKvrn3geb(String kvrn3geb) {
	this.kvrn3geb = kvrn3geb;
}

public String getKtusgeb() {
	return ktusgeb;
}

public void setKtusgeb(String ktusgeb) {
	this.ktusgeb = ktusgeb;
}

public String getKsexgeb() {
	return ksexgeb;
}

public void setKsexgeb(String ksexgeb) {
	this.ksexgeb = ksexgeb;
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

public Gebknd getGebknd() {
	return gebknd;
}

public void setGebknd(Gebknd gebknd) {
	this.gebknd = gebknd;
}



}