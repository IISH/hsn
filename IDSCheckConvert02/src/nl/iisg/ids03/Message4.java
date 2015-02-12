package nl.iisg.ids03;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bfout4ft")
public class Message4 {

	@Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="RecordID")    private int recordID; 

	@Column(name = "IDNR")      private int      keyToRP;   

	@Column(name = "B1IDBG")  	private int   	 keyToSourceRegister; 

	@Column(name = "B2DIBG")    private int      dayEntryHead;
	@Column(name = "B2MIBG")    private int      monthEntryHead; 
	@Column(name = "B2JIBG")    private int      yearEntryHead; 

	@Column(name = "B2FDBG")    private int      dayEntryRP;
	@Column(name = "B2FMBG")    private int      monthEntryRP;
	@Column(name = "B2FJBG")    private int      yearEntryRP;

	@Column(name = "B2RNBG")    private int      keyToRegistrationPersons;
	@Column(name = "B2FCBG")    private int      natureOfPerson;

	@Id @Column(name = "FTKODE") 	private	int    errorNumber;
	@Column(name = "FTTYPE")	    private	String errorType;
	@Column(name = "FOUT")          private String errorText;
	
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}
	public int getKeyToRP() {
		return keyToRP;
	}
	public void setKeyToRP(int keyToRP) {
		this.keyToRP = keyToRP;
	}
	public int getKeyToSourceRegister() {
		return keyToSourceRegister;
	}
	public void setKeyToSourceRegister(int keyToSourceRegister) {
		this.keyToSourceRegister = keyToSourceRegister;
	}
	public int getDayEntryHead() {
		return dayEntryHead;
	}
	public void setDayEntryHead(int dayEntryHead) {
		this.dayEntryHead = dayEntryHead;
	}
	public int getMonthEntryHead() {
		return monthEntryHead;
	}
	public void setMonthEntryHead(int monthEntryHead) {
		this.monthEntryHead = monthEntryHead;
	}
	public int getYearEntryHead() {
		return yearEntryHead;
	}
	public void setYearEntryHead(int yearEntryHead) {
		this.yearEntryHead = yearEntryHead;
	}
	public int getDayEntryRP() {
		return dayEntryRP;
	}
	public void setDayEntryRP(int dayEntryRP) {
		this.dayEntryRP = dayEntryRP;
	}
	public int getMonthEntryRP() {
		return monthEntryRP;
	}
	public void setMonthEntryRP(int monthEntryRP) {
		this.monthEntryRP = monthEntryRP;
	}
	public int getYearEntryRP() {
		return yearEntryRP;
	}
	public void setYearEntryRP(int yearEntryRP) {
		this.yearEntryRP = yearEntryRP;
	}
	public int getKeyToRegistrationPersons() {
		return keyToRegistrationPersons;
	}
	public void setKeyToRegistrationPersons(int keyToRegistrationPersons) {
		this.keyToRegistrationPersons = keyToRegistrationPersons;
	}
	public int getNatureOfPerson() {
		return natureOfPerson;
	}
	public void setNatureOfPerson(int natureOfPerson) {
		this.natureOfPerson = natureOfPerson;
	}
	public int getErrorNumber() {
		return errorNumber;
	}
	public void setErrorNumber(int errorNumber) {
		this.errorNumber = errorNumber;
	}
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	public String getErrorText() {
		return errorText;
	}
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	
	


}
