package iisg.nl.hsnimport;
import iisg.nl.hsnmigrate.Utils;
import iisg.nl.hsnnieuw.D2;
import iisg.nl.hsnnieuw.M4;

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
@Table(name="ovlech")
public class Ovlech {

     @Id@Column(name="IDNR")        private int idnr;
     @Column(name="VLGECH")      private int vlgech;
     @Column(name="ANMEOVL")     private String anmeovl;
     @Column(name="TUSEOVL")     private String tuseovl;
     @Column(name="VRN1EOVL")    private String vrn1eovl;
     @Column(name="VRN2EOVL")    private String vrn2eovl;
     @Column(name="VRN3EOVL")    private String vrn3eovl;
     @Column(name="LEVEOVL")     private String leveovl;
     @Column(name="LFTEOVL")     private int lfteovl;
     @Column(name="BRPEOVL")     private String brpeovl;
     @Column(name="ADREOVL")     private String adreovl;
 //    @Column(name="ARCH")        private String arch;
     @Column(name="OPDRNR")      private String opdrnr;
     @Column(name="DATUM")       private String datum;
     @Column(name="INIT")        private String init;
     @Column(name="VERSIE")      private String versie;
     @Column(name="ONDRZKO")     private String ondrzko;
//     @Column(name="ARCHO")       private String archo;
     @Column(name="OPDRNRO")     private String opdrnro;
     @Column(name="DATUMO")      private String datumo;
     @Column(name="INITO")       private String inito;
     @Column(name="VERSIEO")     private String versieo;
     @Column(name="OPDRNRI")     private String orderNumberI;
 //    @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
 //    @Column(name="RecordID") private int recordID;
 
 @Transient                       private Ovlknd ovlknd;
 
public void convert(EntityManager em){
	 
	 //System.out.println("        Ovlech convert");
	 
	 D2 d2 = new D2();
	 d2.transform(this);
	 //EntityManager em = Utils.getEm_nieuw();
	 //em.getTransaction().begin();
	 d2.truncate();
	 em.persist(d2);
	 
	 if(d2.getD2s_lla() != null)
		 em.persist(d2.getD2s_lla());
	 //em.getTransaction().commit();


	 
 }

public void resolveAl(){

	if(getAdreovl() != null &&  Utils.toBeTranslated(getAdreovl().trim())){
		//System.out.println("Resolving Al to: " + getOvlknd().getDeathActPlace());
		setAdreovl(getOvlknd().getDeathActPlace());
	}
}
 
public int getIdnr() {
	return idnr;
}
public void setIdnr(int idnr) {
	this.idnr = idnr;
}
public int getVlgech() {
	return vlgech;
}
public void setVlgech(int vlgech) {
	this.vlgech = vlgech;
}
public String getAnmeovl() {
	return anmeovl;
}
public void setAnmeovl(String anmeovl) {
	this.anmeovl = anmeovl;
}
public String getTuseovl() {
	return tuseovl;
}
public void setTuseovl(String tuseovl) {
	this.tuseovl = tuseovl;
}
public String getVrn1eovl() {
	return vrn1eovl;
}
public void setVrn1eovl(String vrn1eovl) {
	this.vrn1eovl = vrn1eovl;
}
public String getVrn2eovl() {
	return vrn2eovl;
}
public void setVrn2eovl(String vrn2eovl) {
	this.vrn2eovl = vrn2eovl;
}
public String getVrn3eovl() {
	return vrn3eovl;
}
public void setVrn3eovl(String vrn3eovl) {
	this.vrn3eovl = vrn3eovl;
}
public String getLeveovl() {
	return leveovl;
}
public void setLeveovl(String leveovl) {
	this.leveovl = leveovl;
}
public int getLfteovl() {
	return lfteovl;
}
public void setLfteovl(int lfteovl) {
	this.lfteovl = lfteovl;
}
public String getBrpeovl() {
	return brpeovl;
}
public void setBrpeovl(String brpeovl) {
	this.brpeovl = brpeovl;
}
public String getAdreovl() {
	return adreovl;
}
public void setAdreovl(String adreovl) {
	this.adreovl = adreovl;
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

public Ovlknd getOvlknd() {
	return ovlknd;
}
public void setOvlknd(Ovlknd ovlknd) {
	this.ovlknd = ovlknd;
}


}