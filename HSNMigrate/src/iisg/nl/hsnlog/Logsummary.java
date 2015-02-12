package iisg.nl.hsnlog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="logsummary")
public class Logsummary {

     @Column(name="PROV")       private int prov;
     @Column(name="3GS")        private String threegs; // Cor
     @Column(name="COH")        private String coh;
     @Column(name="GEB")        private int geb;
     @Column(name="OVL")        private int ovl;
     @Column(name="OVL16")      private int ovl16;
     @Column(name="OVL16_")     private String ovl16_;
     @Column(name="PK")         private int pk;
     @Column(name="OVLPK")      private int ovlpk;
     @Column(name="OVLITV_")    private String ovlitv_;
     @Column(name="OVLD")       private int ovld;
     @Column(name="OVLD_")      private String ovld_;
     @Column(name="VOLW")       private int volw;
     @Column(name="HUW")        private int huw;
     @Column(name="HUW1")       private int huw1;
     @Column(name="HUW2_")      private String huw2_;
     @Column(name="HUWITV")     private int huwitv;
     @Column(name="HUWITV_")    private String huwitv_;
     @Column(name="TOT")        private int tot;
     @Column(name="TOTD")       private int totd;
     @Column(name="PK500KPLUS") private int pk500kplus;
     @Column(name="Primary")    private String primary;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")   private int recordID;
 
public int getProv() {
	return prov;
}
public void setProv(int prov) {
	this.prov = prov;
}
public String getThreegs() {
	return threegs;
}
public void setThreegs(String threegs) {
	this.threegs = threegs;
}
public String getCoh() {
	return coh;
}
public void setCoh(String coh) {
	this.coh = coh;
}
public int getGeb() {
	return geb;
}
public void setGeb(int geb) {
	this.geb = geb;
}
public int getOvl() {
	return ovl;
}
public void setOvl(int ovl) {
	this.ovl = ovl;
}
public int getOvl16() {
	return ovl16;
}
public void setOvl16(int ovl16) {
	this.ovl16 = ovl16;
}
public String getOvl16_() {
	return ovl16_;
}
public void setOvl16_(String ovl16_) {
	this.ovl16_ = ovl16_;
}
public int getPk() {
	return pk;
}
public void setPk(int pk) {
	this.pk = pk;
}
public int getOvlpk() {
	return ovlpk;
}
public void setOvlpk(int ovlpk) {
	this.ovlpk = ovlpk;
}
public String getOvlitv_() {
	return ovlitv_;
}
public void setOvlitv_(String ovlitv_) {
	this.ovlitv_ = ovlitv_;
}
public int getOvld() {
	return ovld;
}
public void setOvld(int ovld) {
	this.ovld = ovld;
}
public String getOvld_() {
	return ovld_;
}
public void setOvld_(String ovld_) {
	this.ovld_ = ovld_;
}
public int getVolw() {
	return volw;
}
public void setVolw(int volw) {
	this.volw = volw;
}
public int getHuw() {
	return huw;
}
public void setHuw(int huw) {
	this.huw = huw;
}
public int getHuw1() {
	return huw1;
}
public void setHuw1(int huw1) {
	this.huw1 = huw1;
}
public String getHuw2_() {
	return huw2_;
}
public void setHuw2_(String huw2_) {
	this.huw2_ = huw2_;
}
public int getHuwitv() {
	return huwitv;
}
public void setHuwitv(int huwitv) {
	this.huwitv = huwitv;
}
public String getHuwitv_() {
	return huwitv_;
}
public void setHuwitv_(String huwitv_) {
	this.huwitv_ = huwitv_;
}
public int getTot() {
	return tot;
}
public void setTot(int tot) {
	this.tot = tot;
}
public int getTotd() {
	return totd;
}
public void setTotd(int totd) {
	this.totd = totd;
}
public int getPk500kplus() {
	return pk500kplus;
}
public void setPk500kplus(int pk500kplus) {
	this.pk500kplus = pk500kplus;
}
public String getPrimary() {
	return primary;
}
public void setPrimary(String primary) {
	this.primary = primary;
}
public int getRecordID() {
	return recordID;
}
public void setRecordID(int recordID) {
	this.recordID = recordID;
}
 
 
}