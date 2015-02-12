package nl.iisg.ref;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ref_plaats")
public class Ref_Municipality {
	@Id @Column(name = "GEMNR")      private int       codeMunicipality;
    @Column(name = "PROVNR")     	 private int       codeProvince;
    @Column(name = "REGNR")     	 private int       codeRegion;
    @Column(name = "REGIO")     	 private String    region;
    @Column(name = "VOLGNR")     	 private int       sequenceNumber;
    @Column(name = "GEMNAAM")        private String    municipalityName;
    
	public int getCodeMunicipality() {
		return codeMunicipality;
	}
	public void setCodeMunicipality(int codeMunicipality) {
		this.codeMunicipality = codeMunicipality;
	}
	public int getCodeProvince() {
		return codeProvince;
	}
	public void setCodeProvince(int codeProvince) {
		this.codeProvince = codeProvince;
	}
	public int getCodeRegion() {
		return codeRegion;
	}
	public void setCodeRegion(int codeRegion) {
		this.codeRegion = codeRegion;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getMunicipalityName() {
		return municipalityName;
	}
	public void setMunicipalityName(String municipalityName) {
		this.municipalityName = municipalityName;
	}

	
	

}
