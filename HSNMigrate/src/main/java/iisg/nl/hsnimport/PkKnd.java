package iisg.nl.hsnimport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="pkknd")
public class PkKnd {
    @Id@Column(name="IDNR")		private int idnr;        
    @Column(name="IDNRP")    	private int idnrp;
    @Column(name="GAKTNRP")  	private String gaktnrp;     
    @Column(name="PKTYPE")   	private int pktype;   
    @Column(name="EINDAGPK") 	private int eindagpk;
    @Column(name="EINMNDPK") 	private int einmndpk;
    @Column(name="EINJARPK") 	private int einjarpk;
    @Column(name="CTRDGP")   	private int ctrdgp; 
    @Column(name="CTRMDP")   	private int ctrmdp;
    @Column(name="CTRJRP")   	private int ctrjrp; 
    @Column(name="CTRPARP")     private String ctrparp;   
    @Column(name="GZNVRMP")     private String gznvrp;   
    @Column(name="ANMPERP")     private String anmperp;   
    @Column(name="TUSPERP")     private String tusperp;   
    @Column(name="VNM1PERP")    private String vnm1perp;   
    @Column(name="VNM2PERP")    private String vnm2perp;   
    @Column(name="VNM3PERP")    private String vnm3perp;   
    @Column(name="GDGPERP")     private int gdgperp;
    @Column(name="GMDPERP")  	private int gdmperp;
    @Column(name="GJRPERP")     private int gdjperp; 
    @Column(name="GDGPERPCR")   private int gdgperpcr;
    @Column(name="GMDPERPCR")   private int gmdperpcr; 
    @Column(name="GJRPERPCR")  	private int gjrperpcr;
    @Column(name="GPLPERP")     private String gplperp;   
    @Column(name="NATPERP")     private String natperp;   
    @Column(name="GDSPERP")     private String gdsperp;   
    @Column(name="GSLPERP")     private String gslperp;   
    @Column(name="ANMVDRP")     private String anmvdrp;   
    @Column(name="TUSVDRP")     private String tusvdrp;   
    @Column(name="VNM1VDRP")    private String vnm1vdrp;   
    @Column(name="VNM2VDRP")    private String vnm2vdrp;   
    @Column(name="VNM3VDRP")    private String vnm3vdrp;   
    @Column(name="GDGVDRP")     private int gdgvdrp;
    @Column(name="GMDVDRP")     private int gmdvdrp;
    @Column(name="GJRVDRP")     private int gjrvdrp;
    @Column(name="GDGVDRPCR")   private int gdgvdrpcr;
    @Column(name="GMDVDRPCR")   private int gdmvdrpcr;
    @Column(name="GJRVDRPCR")   private int gjrvdrpcr;
    @Column(name="GPLVDRP")     private String gplvdrp;   
    @Column(name="ANMMDRP")     private String anmmdrp;   
    @Column(name="TUSMDRP")     private String tusmdrp;   
    @Column(name="VNM1MDRP")    private String vnm1mdrp;   
    @Column(name="VNM2MDRP")    private String vnm2mdrp;   
    @Column(name="VNM3MDRP")    private String vnm3mdrp;    
    @Column(name="GDGMDRP")     private int gdgmdrp;
    @Column(name="GMDMDRP")     private int gmdmdrp;
    @Column(name="GJRMDRP")     private int gjrmdrp;
    @Column(name="GDGMDRPCR")   private int gdgmdrpcr;
    @Column(name="GMDMDRPCR")   private int gmdmdrpcr;
    @Column(name="GJRMDRPCR")   private int gjrmdrpcr; 
    @Column(name="GPLMDRP")     private String gplmdrp;   
    @Column(name="ODGPERP")     private int odgperp;
    @Column(name="OMDPERP")     private int omdperp;
    @Column(name="OJRPERP")     private int ojrperp;
    @Column(name="OPLPERP")     private String oplperp;  
    @Column(name="OAKPERP")     private String oakperp;  
    @Column(name="ODOPERP")     private String odoperp;   
    @Column(name="GEGPERP")     private String gegperp;   
    @Column(name="GEGVDRP")     private String gegvdrp;   
    @Column(name="GEGMDRP")     private String gegmdrp;  
    @Column(name="PROBLMP")     private String problmp;   
    @Column(name="PSBDGP")      private int psbdg;
    @Column(name="PSBMDP")      private int psbmdp;
    @Column(name="PSBJRP")      private int psbjrp;    
    @Column(name="PSBNRP")      private String psbnrp;   
    @Column(name="OPDRNR")      private String opdrnr;   
    @Column(name="DATUM")       private String datum;  
    @Column(name="INIT")        private String init;  
    @Column(name="VERSIE")      private String versie;  
    @Column(name="ONDRZKO")     private String onderzko;  
    @Column(name="OPDRNRO")     private String opdrnro;   
    @Column(name="DATUMO")      private String datumo;   
    @Column(name="INITO")       private String inito;   
    @Column(name="VERSIEO")     private String versieo;  
    @Column(name="OPDRNRI")     private String orderNumberI;
    //@Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
    //@Column(name="RecordID")    private int recordID;
    
    
    
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public int getIdnrp() {
		return idnrp;
	}
	public void setIdnrp(int idnrp) {
		this.idnrp = idnrp;
	}
	public String getGaktnrp() {
		return gaktnrp;
	}
	public void setGaktnrp(String gaktnrp) {
		this.gaktnrp = gaktnrp;
	}
	public int getPktype() {
		return pktype;
	}
	public void setPktype(int pktype) {
		this.pktype = pktype;
	}
	public int getEindagpk() {
		return eindagpk;
	}
	public void setEindagpk(int eindagpk) {
		this.eindagpk = eindagpk;
	}
	public int getEinmndpk() {
		return einmndpk;
	}
	public void setEinmndpk(int einmndpk) {
		this.einmndpk = einmndpk;
	}
	public int getEinjarpk() {
		return einjarpk;
	}
	public void setEinjarpk(int einjarpk) {
		this.einjarpk = einjarpk;
	}
	public int getCtrdgp() {
		return ctrdgp;
	}
	public void setCtrdgp(int ctrdgp) {
		this.ctrdgp = ctrdgp;
	}
	public int getCtrmdp() {
		return ctrmdp;
	}
	public void setCtrmdp(int ctrmdp) {
		this.ctrmdp = ctrmdp;
	}
	public int getCtrjrp() {
		return ctrjrp;
	}
	public void setCtrjrp(int ctrjrp) {
		this.ctrjrp = ctrjrp;
	}
	public String getCtrparp() {
		return ctrparp;
	}
	public void setCtrparp(String ctrparp) {
		this.ctrparp = ctrparp;
	}
	public String getGznvrp() {
		return gznvrp;
	}
	public void setGznvrp(String gznvrp) {
		this.gznvrp = gznvrp;
	}
	public String getAnmperp() {
		return anmperp;
	}
	public void setAnmperp(String anmperp) {
		this.anmperp = anmperp;
	}
	public String getTusperp() {
		return tusperp;
	}
	public void setTusperp(String tusperp) {
		this.tusperp = tusperp;
	}
	public String getVnm1perp() {
		return vnm1perp;
	}
	public void setVnm1perp(String vnm1perp) {
		this.vnm1perp = vnm1perp;
	}
	public String getVnm2perp() {
		return vnm2perp;
	}
	public void setVnm2perp(String vnm2perp) {
		this.vnm2perp = vnm2perp;
	}
	public String getVnm3perp() {
		return vnm3perp;
	}
	public void setVnm3perp(String vnm3perp) {
		this.vnm3perp = vnm3perp;
	}
	public int getGdgperp() {
		return gdgperp;
	}
	public void setGdgperp(int gdgperp) {
		this.gdgperp = gdgperp;
	}
	public int getGdmperp() {
		return gdmperp;
	}
	public void setGdmperp(int gdmperp) {
		this.gdmperp = gdmperp;
	}
	public int getGdjperp() {
		return gdjperp;
	}
	public void setGdjperp(int gdjperp) {
		this.gdjperp = gdjperp;
	}
	public int getGdgperpcr() {
		return gdgperpcr;
	}
	public void setGdgperpcr(int gdgperpcr) {
		this.gdgperpcr = gdgperpcr;
	}
	public int getGmdperpcr() {
		return gmdperpcr;
	}
	public void setGmdperpcr(int gmdperpcr) {
		this.gmdperpcr = gmdperpcr;
	}
	public int getGjrperpcr() {
		return gjrperpcr;
	}
	public void setGjrperpcr(int gjrperpcr) {
		this.gjrperpcr = gjrperpcr;
	}
	public String getGplperp() {
		return gplperp;
	}
	public void setGplperp(String gplperp) {
		this.gplperp = gplperp;
	}
	public String getNatperp() {
		return natperp;
	}
	public void setNatperp(String natperp) {
		this.natperp = natperp;
	}
	public String getGdsperp() {
		return gdsperp;
	}
	public void setGdsperp(String gdsperp) {
		this.gdsperp = gdsperp;
	}
	public String getGslperp() {
		return gslperp;
	}
	public void setGslperp(String gslperp) {
		this.gslperp = gslperp;
	}
	public String getAnmvdrp() {
		return anmvdrp;
	}
	public void setAnmvdrp(String anmvdrp) {
		this.anmvdrp = anmvdrp;
	}
	public String getTusvdrp() {
		return tusvdrp;
	}
	public void setTusvdrp(String tusvdrp) {
		this.tusvdrp = tusvdrp;
	}
	public String getVnm1vdrp() {
		return vnm1vdrp;
	}
	public void setVnm1vdrp(String vnm1vdrp) {
		this.vnm1vdrp = vnm1vdrp;
	}
	public String getVnm2vdrp() {
		return vnm2vdrp;
	}
	public void setVnm2vdrp(String vnm2vdrp) {
		this.vnm2vdrp = vnm2vdrp;
	}
	public String getVnm3vdrp() {
		return vnm3vdrp;
	}
	public void setVnm3vdrp(String vnm3vdrp) {
		this.vnm3vdrp = vnm3vdrp;
	}
	public int getGdgvdrp() {
		return gdgvdrp;
	}
	public void setGdgvdrp(int gdgvdrp) {
		this.gdgvdrp = gdgvdrp;
	}
	public int getGmdvdrp() {
		return gmdvdrp;
	}
	public void setGmdvdrp(int gmdvdrp) {
		this.gmdvdrp = gmdvdrp;
	}
	public int getGjrvdrp() {
		return gjrvdrp;
	}
	public void setGjrvdrp(int gjrvdrp) {
		this.gjrvdrp = gjrvdrp;
	}
	public int getGdgvdrpcr() {
		return gdgvdrpcr;
	}
	public void setGdgvdrpcr(int gdgvdrpcr) {
		this.gdgvdrpcr = gdgvdrpcr;
	}
	public int getGdmvdrpcr() {
		return gdmvdrpcr;
	}
	public void setGdmvdrpcr(int gdmvdrpcr) {
		this.gdmvdrpcr = gdmvdrpcr;
	}
	public int getGjrvdrpcr() {
		return gjrvdrpcr;
	}
	public void setGjrvdrpcr(int gjrvdrpcr) {
		this.gjrvdrpcr = gjrvdrpcr;
	}
	public String getGplvdrp() {
		return gplvdrp;
	}
	public void setGplvdrp(String gplvdrp) {
		this.gplvdrp = gplvdrp;
	}
	public String getAnmmdrp() {
		return anmmdrp;
	}
	public void setAnmmdrp(String anmmdrp) {
		this.anmmdrp = anmmdrp;
	}
	public String getTusmdrp() {
		return tusmdrp;
	}
	public void setTusmdrp(String tusmdrp) {
		this.tusmdrp = tusmdrp;
	}
	public String getVnm1mdrp() {
		return vnm1mdrp;
	}
	public void setVnm1mdrp(String vnm1mdrp) {
		this.vnm1mdrp = vnm1mdrp;
	}
	public String getVnm2mdrp() {
		return vnm2mdrp;
	}
	public void setVnm2mdrp(String vnm2mdrp) {
		this.vnm2mdrp = vnm2mdrp;
	}
	public String getVnm3mdrp() {
		return vnm3mdrp;
	}
	public void setVnm3mdrp(String vnm3mdrp) {
		this.vnm3mdrp = vnm3mdrp;
	}
	public int getGdgmdrp() {
		return gdgmdrp;
	}
	public void setGdgmdrp(int gdgmdrp) {
		this.gdgmdrp = gdgmdrp;
	}
	public int getGmdmdrp() {
		return gmdmdrp;
	}
	public void setGmdmdrp(int gmdmdrp) {
		this.gmdmdrp = gmdmdrp;
	}
	public int getGjrmdrp() {
		return gjrmdrp;
	}
	public void setGjrmdrp(int gjrmdrp) {
		this.gjrmdrp = gjrmdrp;
	}
	public int getGdgmdrpcr() {
		return gdgmdrpcr;
	}
	public void setGdgmdrpcr(int gdgmdrpcr) {
		this.gdgmdrpcr = gdgmdrpcr;
	}
	public int getGmdmdrpcr() {
		return gmdmdrpcr;
	}
	public void setGmdmdrpcr(int gmdmdrpcr) {
		this.gmdmdrpcr = gmdmdrpcr;
	}
	public int getGjrmdrpcr() {
		return gjrmdrpcr;
	}
	public void setGjrmdrpcr(int gjrmdrpcr) {
		this.gjrmdrpcr = gjrmdrpcr;
	}
	public String getGplmdrp() {
		return gplmdrp;
	}
	public void setGplmdrp(String gplmdrp) {
		this.gplmdrp = gplmdrp;
	}
	public int getOdgperp() {
		return odgperp;
	}
	public void setOdgperp(int odgperp) {
		this.odgperp = odgperp;
	}
	public int getOmdperp() {
		return omdperp;
	}
	public void setOmdperp(int omdperp) {
		this.omdperp = omdperp;
	}
	public int getOjrperp() {
		return ojrperp;
	}
	public void setOjrperp(int ojrperp) {
		this.ojrperp = ojrperp;
	}
	public String getOplperp() {
		return oplperp;
	}
	public void setOplperp(String oplperp) {
		this.oplperp = oplperp;
	}
	public String getOakperp() {
		return oakperp;
	}
	public void setOakperp(String oakperp) {
		this.oakperp = oakperp;
	}
	public String getOdoperp() {
		return odoperp;
	}
	public void setOdoperp(String odoperp) {
		this.odoperp = odoperp;
	}
	public String getGegperp() {
		return gegperp;
	}
	public void setGegperp(String gegperp) {
		this.gegperp = gegperp;
	}
	public String getGegvdrp() {
		return gegvdrp;
	}
	public void setGegvdrp(String gegvdrp) {
		this.gegvdrp = gegvdrp;
	}
	public String getGegmdrp() {
		return gegmdrp;
	}
	public void setGegmdrp(String gegmdrp) {
		this.gegmdrp = gegmdrp;
	}
	public String getProblmp() {
		return problmp;
	}
	public void setProblmp(String problmp) {
		this.problmp = problmp;
	}
	public int getPsbdg() {
		return psbdg;
	}
	public void setPsbdg(int psbdg) {
		this.psbdg = psbdg;
	}
	public int getPsbmdp() {
		return psbmdp;
	}
	public void setPsbmdp(int psbmdp) {
		this.psbmdp = psbmdp;
	}
	public int getPsbjrp() {
		return psbjrp;
	}
	public void setPsbjrp(int psbjrp) {
		this.psbjrp = psbjrp;
	}
	public String getPsbnrp() {
		return psbnrp;
	}
	public void setPsbnrp(String psbnrp) {
		this.psbnrp = psbnrp;
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
	public String getOnderzko() {
		return onderzko;
	}
	public void setOnderzko(String onderzko) {
		this.onderzko = onderzko;
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

}
