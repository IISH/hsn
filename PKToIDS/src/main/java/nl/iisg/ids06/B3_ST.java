 package nl.iisg.ids06;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Transient;

 
@MappedSuperclass
public class B3_ST {
	
	@Id	@Column(name = "B1IDBG")  	private int   		 keyToSourceRegister;
	@Id	@Column(name = "B2DIBG")    private String       entryDateHead;      
	@Id	@Column(name = "IDNR")      private int          keyToRP;
	
	@Id	@Column(name = "B2RNBG")    private int          keyToRegistrationPersons;
	@Id	@Column(name = "B3TYPE")    private int          dynamicDataType;             
	@Id	@Column(name = "B3VRNR")    private int          dynamicDataSequenceNumber;

	@Column(name = "START_DATE")    private String    startDate;
	@Column(name = "START_FLAG")    private int       startFlag;
	@Column(name = "START_EST")     private int       startEst;

	@Column(name = "END_DATE")      private String    endDate;
	@Column(name = "END_FLAG")      private int       endFlag;
	@Column(name = "END_EST")       private int       endEst;

	
	@Column(name = "B3RGLN")        private int          valueOfRelatedPerson;
//	@Column(name = "B2FCBG")        private int          natureOfPerson;
	@Column(name = "B3MDNR")        private String       dateOfMutation;
	@Column(name = "B3MDFG")        private int          dateOfMutationFlag;


	@Column(name = "DATUM")         private String       date0;
	@Column(name = "VERSIE")        private String       versionLastTimeOfDataEntry;
	@Column(name = "ONDRZKO")       private String       researchCodeOriginal;
	@Column(name = "VERSIEO")       private String       versionOriginalDataEntry;

	@Transient                      private B2_ST        person;


	
	B3_ST(){} // no-args constructor
	

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

	public int getDynamicDataType() {
		return dynamicDataType;
	}

	public void setDynamicDataType(int dynamicDataType) {
		this.dynamicDataType = dynamicDataType;
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

	public String getDate0() {
		return date0;
	}

	public void setDate0(String date0) {
		this.date0 = date0;
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

	public B2_ST getPerson() {
		return person;
	}

	public void setPerson(B2_ST person) {
		this.person = person;
	}


}
