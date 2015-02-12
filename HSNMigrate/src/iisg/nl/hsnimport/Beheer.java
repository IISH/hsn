package iisg.nl.hsnimport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="beheer")
public class Beheer {
	
    @Column(name="IDNR")		private int idnr;        
    @Column(name="OVLDAG")    	private int ovldag;
    @Column(name="OVLMND")    	private int ovlmnd;
    @Column(name="OVLJAAR")    	private int ovljaar;
    @Column(name="FASE_A")    	private int fase_A;
    @Column(name="FASE_B")    	private int fase_B;
    @Column(name="FASE_C_D")   	private int fase_C_D;
    @Column(name="OVLPLAATS")  	private String ovlplaats;
    @Column(name="INVOERSTAT") 	private String invoerstat;
    @Column(name="RANDOMGETA") 	private int randomgeta;
    @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
    @Column(name="RecordID")    private int recordID;
    
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
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
	public int getOvljaar() {
		return ovljaar;
	}
	public void setOvljaar(int ovljaar) {
		this.ovljaar = ovljaar;
	}
	public int getFase_A() {
		return fase_A;
	}
	public void setFase_A(int fase_A) {
		this.fase_A = fase_A;
	}
	public int getFase_B() {
		return fase_B;
	}
	public void setFase_B(int fase_B) {
		this.fase_B = fase_B;
	}
	public int getFase_C_D() {
		return fase_C_D;
	}
	public void setFase_C_D(int fase_C_D) {
		this.fase_C_D = fase_C_D;
	}
	public String getInvoerstat() {
		return invoerstat;
	}
	public void setInvoerstat(String invoerstat) {
		this.invoerstat = invoerstat;
	}
	public int getRandomgeta() {
		return randomgeta;
	}
	public void setRandomgeta(int randomgeta) {
		this.randomgeta = randomgeta;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}
	public String getOvlplaats() {
		return ovlplaats;
	}
	public void setOvlplaats(String ovlplaats) {
		this.ovlplaats = ovlplaats;
	}
    
    

    

}
