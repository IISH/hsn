package nl.iisg.ids04;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="b4_st")
public class B4_ST {
	
	@Id @Column(name = "B1IDBG")	  	private int       keyToSourceRegister;
	@Id @Column(name = "B2DIBG")  		private String    entryDateHead;                      
	@Id @Column(name = "IDNR")   		private int       keyToRP;     

	@Column(name = "B2FDBG")      		private String    entryDateRP;

	@Column(name = "REGISTER_PAGE")     private String    pageNumber;                    
	@Column(name = "REGISTER_LINE")     private int       numberOfHousehold;                  
	@Column(name = "NAME_HEAD_GK")      private String    nameHeadGK;       // GK = GezinsKaart = FamilyCard           
	@Column(name = "SPECIAL_CODE")      private String    specialCode;               
	@Column(name = "SPECIAL_REMARKS")   private String    remarks;  

	@Column(name = "VERSIE")      		private String    versionLastTimeOfDataEntry;
	@Column(name = "ONDRZKO")     		private String    researchCodeOriginal;
	@Column(name = "VERSIEO")     		private String    versionOriginalDataEntry;
	@Column(name = "DATUM")     		private String    date0;

	@Transient                          private B4_ST     originalRegistration;
	@Transient                          private ArrayList<B2_ST> personsStandardizedInRegistration   = new ArrayList<B2_ST>();
	@Transient  						private ArrayList<B6_ST>   addressesStandardizedOfRegistration = new ArrayList<B6_ST>();
	
	@Transient  				        private OP        op = null;


	
	B4_ST(){} // No-args constructor for JPA


	public boolean contains(B2_ST p){

		if(getKeyToSourceRegister()  == p.getKeyToSourceRegister() &&
				getEntryDateHead().equals(p.getEntryDateHead()) &&
				getKeyToRP()     == p.getKeyToRP())

			return true;
		else
			return false;


	}

	public boolean contains(B6_ST ra){

		if(getKeyToSourceRegister()        == ra.getKeyToSourceRegister() &&    
				getEntryDateHead().equals(ra.getDateEntryHeadOfHousehold()) &&
				getKeyToRP()     == ra.getKeyToRP())

			return true;
		else
			return false;


	}

	public int getKeyToSourceRegister() {
		return keyToSourceRegister;
	}



	public void setKeyToSourceRegister(int keyToSourceRegister) {
		this.keyToSourceRegister = keyToSourceRegister;
	}



	public String getEntryDateHead() {
		return entryDateHead;
	}



	public void setEntryDateHead(String entryDateHead) {
		this.entryDateHead = entryDateHead;
	}



	public int getKeyToRP() {
		return keyToRP;
	}



	public void setKeyToRP(int keyToRP) {
		this.keyToRP = keyToRP;
	}



	public String getEntryDateRP() {
		return entryDateRP;
	}



	public void setEntryDateRP(String entryDateRP) {
		this.entryDateRP = entryDateRP;
	}



	public String getPageNumber() {
		return pageNumber;
	}



	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}



	public int getNumberOfHousehold() {
		return numberOfHousehold;
	}



	public void setNumberOfHousehold(int numberOfHousehold) {
		this.numberOfHousehold = numberOfHousehold;
	}



	public String getNameHeadGK() {
		return nameHeadGK;
	}



	public void setNameHeadGK(String nameHeadGK) {
		this.nameHeadGK = nameHeadGK;
	}



	public String getSpecialCode() {
		return specialCode;
	}



	public void setSpecialCode(String specialCode) {
		this.specialCode = specialCode;
	}



	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	public String getVersionLastTimeOfDataEntry() {
		return versionLastTimeOfDataEntry;
	}



	public void setVersionLastTimeOfDataEntry(String versionLastTimeOfDataEntry) {
		this.versionLastTimeOfDataEntry = versionLastTimeOfDataEntry;
	}



	public String getResearchCodeOriginal() {
		return researchCodeOriginal;
	}



	public void setResearchCodeOriginal(String researchCodeOriginal) {
		this.researchCodeOriginal = researchCodeOriginal;
	}



	public String getVersionOriginalDataEntry() {
		return versionOriginalDataEntry;
	}



	public void setVersionOriginalDataEntry(String versionOriginalDataEntry) {
		this.versionOriginalDataEntry = versionOriginalDataEntry;
	}



	public String getDate0() {
		return date0;
	}



	public void setDate0(String date0) {
		this.date0 = date0;
	}



	public B4_ST getOriginalRegistration() {
		return originalRegistration;
	}



	public void setOriginalRegistration(B4_ST originalRegistration) {
		this.originalRegistration = originalRegistration;
	}



	public ArrayList<B2_ST> getPersonsStandardizedInRegistration() {
		return personsStandardizedInRegistration;
	}



	public void setPersonsStandardizedInRegistration(
			ArrayList<B2_ST> personsStandardizedInRegistration) {
		this.personsStandardizedInRegistration = personsStandardizedInRegistration;
	}



	public ArrayList<B6_ST> getAddressesStandardizedOfRegistration() {
		return addressesStandardizedOfRegistration;
	}



	public void setAddressesStandardizedOfRegistration(
			ArrayList<B6_ST> addressesStandardizedOfRegistration) {
		this.addressesStandardizedOfRegistration = addressesStandardizedOfRegistration;
	}



	public OP getOp() {
		return op;
	}



	public void setOp(OP op) {
		this.op = op;
	}


	
}
