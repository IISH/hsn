 package nl.iisg.ids06;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="b4_stpk")
public class B4_ST { 
	
	@Id @Column(name = "B1IDBG")	  	private int       keyToSourceRegister;
	@Id @Column(name = "B2DIBG")  		private String    entryDateHead;                      
	@Id @Column(name = "IDNR")   		private int       keyToRP;     

	//@Column(name = "B2FDBG")      		private String    entryDateRP;
	
	@Column(name = "IDNR_SPOUSE")  		private int       idnrSpouse;
	@Column(name = "PK_HOLDER")   		private String    pkHolder;   

	@Column(name = "START_PK")		    private String    startDate;
	@Column(name = "START_PK_FG")  		private int       startFlag;

	@Column(name = "END_PK")    		private String    endDate;
	@Column(name = "END_PK_FG")    		private int       endFlag;

	
//	@Column(name = "REGISTER_PAGE")     private String    pageNumber;                    
//	@Column(name = "REGISTER_LINE")     private int       numberOfHousehold;                  
//	@Column(name = "NAME_HEAD_GK")      private String    nameHeadGK;       // GK = GezinsKaart = FamilyCard           
//	@Column(name = "SPECIAL_CODE")      private String    specialCode;               
	@Column(name = "REMARKS") 		    private String    remarks;  

	@Column(name = "VERSIE")      		private String    versionLastTimeOfDataEntry;
	@Column(name = "ONDRZKO")     		private String    researchCodeOriginal;
	@Column(name = "VERSIEO")     		private String    versionOriginalDataEntry;
	@Column(name = "DATUM")     		private String    date0;
	
	@Transient                          private ArrayList<B2_ST> persons = new ArrayList<B2_ST>();  
	@Transient                          private ArrayList<B6_ST> addresses = new ArrayList<B6_ST>(); 
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

	public ArrayList<B2_ST> getPersons() {
		return persons;
	}

	public void setPersons(ArrayList<B2_ST> persons) {
		this.persons = persons;
	}

	public ArrayList<B6_ST> getAddresses() {
		return addresses;
	}

	public void setAddresses(ArrayList<B6_ST> addresses) {
		this.addresses = addresses;
	}


	public int getIdnrSpouse() {
		return idnrSpouse;
	}


	public void setIdnrSpouse(int idnrSpouse) {
		this.idnrSpouse = idnrSpouse;
	}


	public String getPkHolder() {
		return pkHolder;
	}


	public void setPkHolder(String pkHolder) {
		this.pkHolder = pkHolder;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public int getStartFlag() {
		return startFlag;
	}


	public void setStartFlag(int startFlag) {
		this.startFlag = startFlag;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public int getEndFlag() {
		return endFlag;
	}


	public void setEndFlag(int endFlag) {
		this.endFlag = endFlag;
	}




	public OP getOp() {
		return op;
	}




	public void setOp(OP op) {
		this.op = op;
	}

	

}
