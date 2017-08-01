package iisg.nl.hsnimport;

import iisg.nl.hsnmigrate.Utils;
import iisg.nl.hsnnieuw.D2;
import iisg.nl.hsnnieuw.D3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ovlagv")
public class Ovlagv {

     @Column(name="IDNR")         private int idnr;
     @Column(name="VLGNRAG")      private int vlgnrag;
     @Column(name="ANMAGV")       private String anmagv;
     @Column(name="TUSAGV")       private String tusagv;
     @Column(name="VRN1AGV")      private String vrn1agv;
     @Column(name="VRN2AGV")      private String vrn2agv;
     @Column(name="VRN3AGV")      private String vrn3agv;
     @Column(name="RAGVOVL")      private String ragvovl;
     @Column(name="LFTAGV")       private int lftagv;
     @Column(name="BRPAGV")       private String brpagv;
     @Column(name="ADRAGV")       private String adragv;
     @Column(name="HNDAGV")       private String hndagv;
     @Column(name="ARCH")         private String arch;
     @Column(name="OPDRNR")       private String opdrnr;
     @Column(name="DATUM")        private String datum;
     @Column(name="INIT")         private String init;
     @Column(name="VERSIE")       private String versie;
     @Column(name="ONDRZKO")      private String ondrzko;
     @Column(name="ARCHO")        private String archo;
     @Column(name="OPDRNRO")      private String opdrnro;
     @Column(name="DATUMO")       private String datumo;
     @Column(name="INITO")        private String inito;
     @Column(name="VERSIEO")      private String versieo;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID") private int recordID;
 
 @Transient                       private Ovlknd ovlknd;
 
public void convert(EntityManager em){
	 
	 //System.out.println("        Ovlagv convert");
	 
	 D3 d3 = new D3();
	 d3.transform(this);
	 //EntityManager em = Utils.getEm_nieuw();
	 //em.getTransaction().begin();
	 d3.truncate();
	 em.persist(d3);
	 //em.getTransaction().commit();
	 if(d3.getD3i_lla() != null)
		 em.persist(d3.getD3i_lla());
	 
 }

public void resolveAl(){

	if(getAdragv() != null && getAdragv().trim().equalsIgnoreCase("AL"))
		setAdragv(getOvlknd().getDeathActPlace());
}


 
 
public int getIdnr() {
	return idnr;
}
public void setIdnr(int idnr) {
	this.idnr = idnr;
}
public int getVlgnrag() {
	return vlgnrag;
}
public void setVlgnrag(int vlgnrag) {
	this.vlgnrag = vlgnrag;
}
public String getAnmagv() {
	return anmagv;
}
public void setAnmagv(String anmagv) {
	this.anmagv = anmagv;
}
public String getTusagv() {
	return tusagv;
}
public void setTusagv(String tusagv) {
	this.tusagv = tusagv;
}
public String getVrn1agv() {
	return vrn1agv;
}
public void setVrn1agv(String vrn1agv) {
	this.vrn1agv = vrn1agv;
}
public String getVrn2agv() {
	return vrn2agv;
}
public void setVrn2agv(String vrn2agv) {
	this.vrn2agv = vrn2agv;
}
public String getVrn3agv() {
	return vrn3agv;
}
public void setVrn3agv(String vrn3agv) {
	this.vrn3agv = vrn3agv;
}
public String getRagvovl() {
	return ragvovl;
}
public void setRagvovl(String ragvovl) {
	this.ragvovl = ragvovl;
}
public int getLftagv() {
	return lftagv;
}
public void setLftagv(int lftagv) {
	this.lftagv = lftagv;
}
public String getBrpagv() {
	return brpagv;
}
public void setBrpagv(String brpagv) {
	this.brpagv = brpagv;
}
public String getAdragv() {
	return adragv;
}
public void setAdragv(String adragv) {
	this.adragv = adragv;
}
public String getHndagv() {
	return hndagv;
}
public void setHndagv(String hndagv) {
	this.hndagv = hndagv;
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
public Ovlknd getOvlknd() {
	return ovlknd;
}
public void setOvlknd(Ovlknd ovlknd) {
	this.ovlknd = ovlknd;
}





public int getRecordID() {
	return recordID;
}





public void setRecordID(int recordID) {
	this.recordID = recordID;
}
 
 
 
 
}
