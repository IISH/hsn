package iisg.nl.hsnimport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="plaats")
public class Plaats {
	
    @Id@Column(name="GEMNR")	private int gemnr;        
    @Column(name="PROVNR")		private int provnr;        
    @Column(name="REGNR")		private int regnr;        
    @Column(name="REGIO")		private String regio;        
    //@Column(name="VOLGNR")		private int volgnr;        
    @Column(name="GEMNAAM")		private String gemnaam;  
    @Column(name="OPDRNRI")     private String orderNumberI;
    //@Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
    //@Column(name="RecordID")    private int recordID;
    
	public int getGemnr() {
		return gemnr;
	}
	public void setGemnr(int gemnr) {
		this.gemnr = gemnr;
	}
	public int getProvnr() {
		return provnr;
	}
	public void setProvnr(int provnr) {
		this.provnr = provnr;
	}
	public int getRegnr() {
		return regnr;
	}
	public void setRegnr(int regnr) {
		this.regnr = regnr;
	}
	public String getRegio() {
		return regio;
	}
	public void setRegio(String regio) {
		this.regio = regio;
	}
	
	public String getGemnaam() {
		return gemnaam;
	}
	public void setGemnaam(String gemnaam) {
		this.gemnaam = gemnaam;
	}
	public String getOrderNumberI() {
		return orderNumberI;
	}
	public void setOrderNumberI(String orderNumberI) {
		this.orderNumberI = orderNumberI;
	}

    
}
