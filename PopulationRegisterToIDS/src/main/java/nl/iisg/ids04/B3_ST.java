package nl.iisg.ids04;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;


@MappedSuperclass
public class B3_ST {
	
	@Id	@Column(name = "B1IDBG")  	private int   		 keyToSourceRegister;
	@Id	@Column(name = "B2DIBG")    private String       entryDateHead;                    
	@Id	@Column(name = "IDNR")      private int          keyToRP;
	
	
	@Id	@Column(name = "B2RNBG")    private int          keyToRegistrationPersons;
	@Id	@Column(name = "B3TYPE")    private int          keyToDistinguishDynamicDataType;             
	@Id	@Column(name = "B3VRNR")    private int          dynamicDataSequenceNumber;
	
	@Column(name = "START_DATE")    private String    startDate;
	@Column(name = "START_FLAG")    private int       startFlag;
	@Column(name = "START_EST")     private int       startEst;

	@Column(name = "END_DATE")      private String    endDate;
	@Column(name = "END_FLAG")      private int       endFlag;
	@Column(name = "END_EST")       private int       endEst;

	//@Column(name = "B3KODE")        private int          contentOfDynamicData;
	@Column(name = "B3RGLN")        private int          valueOfRelatedPerson;
	@Column(name = "B2FCBG")        private int          natureOfPerson;
	@Column(name = "B3MDNR")        private String       dateOfMutation;
	@Column(name = "B3MDFG")        private int          dateOfMutationFlag;

	//@Column(name = "B3GEGEVEN")     private String       dynamicData2;

	@Column(name = "VERSIE")        private String       versionLastTimeOfDataEntry;
	@Column(name = "ONDRZKO")       private String       researchCodeOriginal;
	@Column(name = "VERSIEO")       private String       versionOriginalDataEntry;
	@Column(name = "DATUM")     	private String       date0;
	
	@Transient                      private B2_ST personStandardizedToWhomDynamicDataRefers;
	
	B3_ST(){}

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

	public int getKeyToRegistrationPersons() {
		return keyToRegistrationPersons;
	}

	public void setKeyToRegistrationPersons(int keyToRegistrationPersons) {
		this.keyToRegistrationPersons = keyToRegistrationPersons;
	}

	public int getKeyToDistinguishDynamicDataType() {
		return keyToDistinguishDynamicDataType;
	}

	public void setKeyToDistinguishDynamicDataType(
			int keyToDistinguishDynamicDataType) {
		this.keyToDistinguishDynamicDataType = keyToDistinguishDynamicDataType;
	}

	public int getDynamicDataSequenceNumber() {
		return dynamicDataSequenceNumber;
	}

	public void setDynamicDataSequenceNumber(int dynamicDataSequenceNumber) {
		this.dynamicDataSequenceNumber = dynamicDataSequenceNumber;
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

	public int getStartEst() {
		return startEst;
	}

	public void setStartEst(int startEst) {
		this.startEst = startEst;
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

	public int getEndEst() {
		return endEst;
	}

	public void setEndEst(int endEst) {
		this.endEst = endEst;
	}

	public int getValueOfRelatedPerson() {
		return valueOfRelatedPerson;
	}

	public void setValueOfRelatedPerson(int valueOfRelatedPerson) {
		this.valueOfRelatedPerson = valueOfRelatedPerson;
	}

	public int getNatureOfPerson() {
		return natureOfPerson;
	}

	public void setNatureOfPerson(int natureOfPerson) {
		this.natureOfPerson = natureOfPerson;
	}

	public String getDateOfMutation() {
		return dateOfMutation;
	}

	public void setDateOfMutation(String dateOfMutation) {
		this.dateOfMutation = dateOfMutation;
	}

	public int getDateOfMutationFlag() {
		return dateOfMutationFlag;
	}

	public void setDateOfMutationFlag(int dateOfMutationFlag) {
		this.dateOfMutationFlag = dateOfMutationFlag;
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

	public B2_ST getPersonStandardizedToWhomDynamicDataRefers() {
		return personStandardizedToWhomDynamicDataRefers;
	}

	public void setPersonStandardizedToWhomDynamicDataRefers(
			B2_ST personStandardizedToWhomDynamicDataRefers) {
		this.personStandardizedToWhomDynamicDataRefers = personStandardizedToWhomDynamicDataRefers;
	}

	
	
}
