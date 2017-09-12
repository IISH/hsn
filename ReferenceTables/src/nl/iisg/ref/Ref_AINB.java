/*
* Naam:    Ref_AINB
* Version: 0.1
* Author:  Cor Munnik
* Copyright
*/
package nl.iisg.ref;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 
 * This class contains data of one ainb row  
 *
 */ 

@Entity
@Table(name="ref_ainb")
public class Ref_AINB{ 
	@Id @Column(name = "B1IDBG")      private int       keyToSourceRegister;
	    @Column(name = "GEMNAAM")     private String    municipality;
		@Column(name = "B1ABBG")      private String    typeRegister;
		@Column(name = "B1BJBG")      private int       startYearRegister;
		@Column(name = "B1BJBGCR")    private int       startYearRegisterCorrected;
        @Column(name = "B1EJBG")      private int       endYearRegister;
        @Column(name = "B1EJBGCR")    private int       endYearRegisterCorrected;
        @Column(name = "B1GWBG")      private String    b1gwbg;
        @Column(name = "B1IVBG")      private String    b1ivbg;

    
	public int getStartYearRegister() {
		return startYearRegister;
	}

	public void setStartYearRegister(int startYearRegister) {
		this.startYearRegister = startYearRegister;
	}

	public int getEndYearRegister() {
		return endYearRegister;
	}

	public void setEndYearRegister(int endYearRegister) {
		this.endYearRegister = endYearRegister;
	}

	public int getKeyToSourceRegister() {
		return keyToSourceRegister;
	}

	public void setKeyToSourceRegister(int keyToSourceRegister) {
		this.keyToSourceRegister = keyToSourceRegister;
	}

	public String getTypeRegister() {
		return typeRegister;
	}

	public void setTypeRegister(String typeRegister) {
		this.typeRegister = typeRegister;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}



    public String getB1gwbg() {
        return b1gwbg;
    }

    public String getB1ivbg() {
        return b1ivbg;
    }

    public void setB1gwbg(String b1gwbg) {
        this.b1gwbg = b1gwbg;
    }

    public void setB1ivbg(String b1ivbg) {
        this.b1ivbg = b1ivbg;
    }

	public int getStartYearRegisterCorrected() {
		return startYearRegisterCorrected;
	}

	public void setStartYearRegisterCorrected(int startYearRegisterCorrected) {
		this.startYearRegisterCorrected = startYearRegisterCorrected;
	}

	public int getEndYearRegisterCorrected() {
		return endYearRegisterCorrected;
	}

	public void setEndYearRegisterCorrected(int endYearRegisterCorrected) {
		this.endYearRegisterCorrected = endYearRegisterCorrected;
	}

    
    
}
