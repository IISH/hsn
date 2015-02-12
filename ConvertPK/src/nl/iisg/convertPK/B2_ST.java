 package nl.iisg.convertPK;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="b2_st")
public class B2_ST {

	/**
	 * 
	 * This class handles the static attributes of a standardized person (name, date of birth etc.)
	 * Objects of this class are initialized from a Person object
	 *
	 */


	@Id @Column(name = "B1IDBG")  				private int       keyToSourceRegister;
	@Id @Column(name = "B2DIBG")  				private String    entryDateHead;                      
	@Id	@Column(name = "IDNR")    				private int       keyToRP;   
	@Id @Column(name = "B2RNBG")  				private int       keyToPersons;

	@Column(name = "B2FCBG")      				private int       natureOfPerson;

	@Column(name = "B2RDNR")   	  				private String    dateOfRegistration;
	@Column(name = "B2RDFG")   	  				private int       dateOfRegistrationFlag;

	@Column(name = "PERSON_ID")  				private int       personID;
	@Column(name = "PERSON_ID_FG")          	private int       personIDFlag;

	@Column(name = "PERSON_ID_FA")  		    private int       personID_FA;
	@Column(name = "PERSON_ID_FA_FG")          	private int       personID_FA_FG;

	@Column(name = "PERSON_ID_MO")  		    private int       personID_MO;
	@Column(name = "PERSON_ID_MO_FG")          	private int       personID_MO_FG;

	@Column(name = "START_DATE")             	private String    startDate;
	@Column(name = "START_FLAG")             	private int       startFlag;
	@Column(name = "START_EST")             	private int       startEst;

	@Column(name = "END_DATE")              	private String    endDate;
	@Column(name = "END_FLAG")              	private int       endFlag;
	@Column(name = "END_EST")               	private int       endEst;

	@Column(name = "FAMILYNAME")          		private String    familyName;
	@Column(name = "PREFIX_FAMILYNAME")   		private String    prefixLastName;
	@Column(name = "TITLE_NOBLE")         		private String    titleNoble; 
	@Column(name = "TITLE_ELSE")          		private String    titleElse; 
	@Column(name = "FAMILYNAME_FG")     		private int       familyNameInterpreted;

	@Column(name = "FIRSTNAME")  				private String    firstName;
	@Column(name = "FIRSTNAME_FG")	        	private int       firstNameFlag;
	@Column(name = "SEX")        				private String    sex;

	@Column(name = "B2GDNR")      				private String    dateOfBirth;
	@Column(name = "B2GDFG")      				private int       dateOfBirthFlag;
	@Column(name = "BIRTH_LOCALITY_ID")      	private int       placeOfBirthID;
	@Column(name = "BIRTH_LOCALITY_ST")      	private String    placeOfBirthStandardized;
	@Column(name = "BIRTH_LOCALITY_FG")      	private int       placeOfBirthFlag;

	@Column(name = "B2ODNR")     				private String    dateOfDecease;
	@Column(name = "B2ODFG")      				private int       dateOfDeceaseFlag;
	@Column(name = "DEATH_LOCALITY_ID")     	private int       placeOfDeceaseID;
	@Column(name = "DEATH_LOCALITY_ST")      	private String    placeOfDeceaseStandardized;
	@Column(name = "DEATH_LOCALITY_FG")      	private int       placeOfDeceaseFlag;

	@Column(name = "NATIONALITY")               private String    nationality;  

	@Column(name = "LEGAL_LIVPL_ID")     		private int       legalPlaceOfLivingID;
	@Column(name = "LEGAL_LIVPL_ST")      		private String    legalPlaceOfLivingStandardized;
	@Column(name = "LEGAL_LIVPL_FG")      		private int       legalPlaceOfLivingFlag;	
	@Column(name = "LEGAL_LIVPL_CODE")          private String    legalPlaceOfLivingCode;

	@Column(name = "REMARKS")    				private String    remarks; 
	@Column(name = "ADDITION")    				private String    addition;

	@Column(name = "VERSIE")      				private String    versionLastTimeOfDataEntry;
	@Column(name = "ONDRZKO")    				private String    researchCodeOriginal;
	@Column(name = "VERSIEO")     				private String    versionOriginalDataEntry;
	@Column(name = "DATUM")     				private String    date0;

	@Transient                                  private B4_ST     registration;
	
	@Transient                                  private ArrayList<B313_ST> relationsToPKHolder  = new ArrayList<B313_ST>();  
	@Transient                                  private ArrayList<B32_ST> civilStatus           = new ArrayList<B32_ST>();  
	@Transient                                  private ArrayList<B33_ST> religions             = new ArrayList<B33_ST>();  
	@Transient                                  private ArrayList<B34_ST> relations             = new ArrayList<B34_ST>();  
	@Transient                                  private ArrayList<B35_ST> professions           = new ArrayList<B35_ST>();  
	@Transient                                  private ArrayList<B36_ST> origins               = new ArrayList<B36_ST>();  
	@Transient                                  private ArrayList<B37_ST> destinations          = new ArrayList<B37_ST>();  
	@Transient                                  private ArrayList<B6_ST>  addressess            = new ArrayList<B6_ST>();  


	B2_ST(){} // No-arguments Constructor


	public void save(EntityManager em){
		
		em.persist(this);
		
		for(B313_ST b313: getRelationsToPKHolder())
			b313.save(em);
			
		for(B32_ST b32: getCivilStatus())
			b32.save(em);
			
		for(B33_ST b33: getReligions())
			b33.save(em);
			
		for(B34_ST b34: getRelations())
			b34.save(em);
			
		for(B35_ST b35: getProfessions())
			b35.save(em);
			
		for(B36_ST b36: getOrigins())
			b36.save(em);
			
		for(B37_ST b37: getDestinations())
			b37.save(em);
			
		for(B6_ST b6: getAddressess())
			b6.save(em);
			
		
		
		
	}
	
	/**
	 * 
	 * This routine truncates fields that are too long for the corresponding database columns
	 * 
	 */
	
	
	public void truncate(){	
		
		String field = getFamilyName();
		int allowedSize = TableDefinitions.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B2_ST", "FAMILYNAME", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setFamilyName(field);
		}
			
		field = getPrefixLastName();
		allowedSize = TableDefinitions.Smallstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B2_ST", "PREFIX_FAMILYNAME", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setPrefixLastName(field);
		}
			
		field = getTitleNoble();
		allowedSize = TableDefinitions.Smallstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B2_ST", "TITLE_NOBLE", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setTitleNoble(field);
		}
			
		field = getTitleElse();
		allowedSize = TableDefinitions.Smallstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B2_ST", "TITLE_ELSE", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setTitleElse(field);
		}
			
		field = getFirstName();
		allowedSize = TableDefinitions.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B2_ST", "FIRSTNAME", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setFirstName(field);
		}
			
		field = getPlaceOfBirthStandardized();
		allowedSize = TableDefinitions.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B2_ST", "BIRTH_LOCALITY_ST", "" +allowedSize);
			field = field.substring(0, allowedSize);
			setPlaceOfBirthStandardized(field);
		}
			
		field = getPlaceOfDeceaseStandardized();
		allowedSize = TableDefinitions.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B2_ST", "DEATH_LOCALITY_ST", "" +allowedSize);
			field = field.substring(0, allowedSize);
			setPlaceOfDeceaseStandardized(field);
		}
			
		field = getNationality();
		allowedSize = TableDefinitions.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B2_ST", "NATIONALITY", "" +allowedSize);
			field = field.substring(0, allowedSize);
			setNationality(field);
		}
			
		field = getLegalPlaceOfLivingStandardized();
		allowedSize = TableDefinitions.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B2_ST", "LEGAL_LIVPL_ST", "" +allowedSize);
			field = field.substring(0, allowedSize);
			setLegalPlaceOfLivingStandardized(field);
		}
			
		field = getRemarks();
		allowedSize = TableDefinitions.XBigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B2_ST", "REMARKS", "" +allowedSize);
			field = field.substring(0, allowedSize);
			setRemarks(field);
		}
			
		field = getAddition();
		allowedSize = TableDefinitions.XBigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B2_ST", "ADDITION", "" +allowedSize);
			field = field.substring(0, allowedSize);
			setAddition(field);
		}
			
		for(B313_ST b313: getRelationsToPKHolder())
			b313.truncate();
			
		for(B32_ST b32: getCivilStatus())
			b32.truncate();
			
		for(B33_ST b33: getReligions())
			b33.truncate();
			
		for(B35_ST b35: getProfessions())
			b35.truncate();
			
		for(B36_ST b36: getOrigins())
			b36.truncate();
			
		for(B37_ST b37: getDestinations())
			b37.truncate();
			


	}

	private void message(String number, String... fills){
		
		Message m = new Message(number);
		
		m.setKeyToRP(getKeyToRP());
		
		m.setKeyToSourceRegister(getKeyToSourceRegister());
		
		m.setDayEntryHead((new Integer(getRegistration().getEntryDateHead().substring(0,2)).intValue()));
		m.setMonthEntryHead((new Integer(getRegistration().getEntryDateHead().substring(3,5)).intValue()));
		m.setYearEntryHead((new Integer(getRegistration().getEntryDateHead().substring(6,10)).intValue()));
		
		
		m.setDayEntryRP((new Integer(getRegistration().getEntryDateRP().substring(0,2)).intValue()));
		m.setMonthEntryRP((new Integer(getRegistration().getEntryDateRP().substring(3,5)).intValue()));
		m.setYearEntryRP((new Integer(getRegistration().getEntryDateRP().substring(6,10)).intValue()));
		
		m.setKeyToRegistrationPersons(getKeyToPersons());
		m.setNatureOfPerson(getNatureOfPerson());
		
		m.save(fills); 
		
		
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


	public int getKeyToPersons() {
		return keyToPersons;
	}


	public void setKeyToPersons(int keyToPersons) {
		this.keyToPersons = keyToPersons;
	}


	public int getNatureOfPerson() {
		return natureOfPerson;
	}


	public void setNatureOfPerson(int natureOfPerson) {
		this.natureOfPerson = natureOfPerson;
	}


	public String getDateOfRegistration() {
		return dateOfRegistration;
	}


	public void setDateOfRegistration(String dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}


	public int getDateOfRegistrationFlag() {
		return dateOfRegistrationFlag;
	}


	public void setDateOfRegistrationFlag(int dateOfRegistrationFlag) {
		this.dateOfRegistrationFlag = dateOfRegistrationFlag;
	}


	public int getPersonID() {
		return personID;
	}


	public void setPersonID(int personID) {
		this.personID = personID;
	}


	public int getPersonIDFlag() {
		return personIDFlag;
	}


	public void setPersonIDFlag(int personIDFlag) {
		this.personIDFlag = personIDFlag;
	}


	public int getPersonID_FA() {
		return personID_FA;
	}


	public void setPersonID_FA(int personID_FA) {
		this.personID_FA = personID_FA;
	}


	public int getPersonID_FA_FG() {
		return personID_FA_FG;
	}


	public void setPersonID_FA_FG(int personID_FA_FG) {
		this.personID_FA_FG = personID_FA_FG;
	}


	public int getPersonID_MO() {
		return personID_MO;
	}


	public void setPersonID_MO(int personID_MO) {
		this.personID_MO = personID_MO;
	}


	public int getPersonID_MO_FG() {
		return personID_MO_FG;
	}


	public void setPersonID_MO_FG(int personID_MO_FG) {
		this.personID_MO_FG = personID_MO_FG;
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


	public String getFamilyName() {
		return familyName;
	}


	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}


	public String getPrefixLastName() {
		return prefixLastName;
	}


	public void setPrefixLastName(String prefixLastName) {
		this.prefixLastName = prefixLastName;
	}


	public String getTitleNoble() {
		return titleNoble;
	}


	public void setTitleNoble(String titleNoble) {
		this.titleNoble = titleNoble;
	}


	public String getTitleElse() {
		return titleElse;
	}


	public void setTitleElse(String titleElse) {
		this.titleElse = titleElse;
	}


	public int getFamilyNameInterpreted() {
		return familyNameInterpreted;
	}


	public void setFamilyNameInterpreted(int familyNameInterpreted) {
		this.familyNameInterpreted = familyNameInterpreted;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public int getFirstNameFlag() {
		return firstNameFlag;
	}


	public void setFirstNameFlag(int firstNameFlag) {
		this.firstNameFlag = firstNameFlag;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public int getDateOfBirthFlag() {
		return dateOfBirthFlag;
	}


	public void setDateOfBirthFlag(int dateOfBirthFlag) {
		this.dateOfBirthFlag = dateOfBirthFlag;
	}


	public int getPlaceOfBirthID() {
		return placeOfBirthID;
	}


	public void setPlaceOfBirthID(int placeOfBirthID) {
		this.placeOfBirthID = placeOfBirthID;
	}


	public String getPlaceOfBirthStandardized() {
		return placeOfBirthStandardized;
	}


	public void setPlaceOfBirthStandardized(String placeOfBirthStandardized) {
		this.placeOfBirthStandardized = placeOfBirthStandardized;
	}


	public int getPlaceOfBirthFlag() {
		return placeOfBirthFlag;
	}


	public void setPlaceOfBirthFlag(int placeOfBirthFlag) {
		this.placeOfBirthFlag = placeOfBirthFlag;
	}


	public String getDateOfDecease() {
		return dateOfDecease;
	}


	public void setDateOfDecease(String dateOfDecease) {
		this.dateOfDecease = dateOfDecease;
	}


	public int getDateOfDeceaseFlag() {
		return dateOfDeceaseFlag;
	}


	public void setDateOfDeceaseFlag(int dateOfDeceaseFlag) {
		this.dateOfDeceaseFlag = dateOfDeceaseFlag;
	}


	public int getPlaceOfDeceaseID() {
		return placeOfDeceaseID;
	}


	public void setPlaceOfDeceaseID(int placeOfDeceaseID) {
		this.placeOfDeceaseID = placeOfDeceaseID;
	}


	public String getPlaceOfDeceaseStandardized() {
		return placeOfDeceaseStandardized;
	}


	public void setPlaceOfDeceaseStandardized(String placeOfDeceaseStandardized) {
		this.placeOfDeceaseStandardized = placeOfDeceaseStandardized;
	}


	public int getPlaceOfDeceaseFlag() {
		return placeOfDeceaseFlag;
	}


	public void setPlaceOfDeceaseFlag(int placeOfDeceaseFlag) {
		this.placeOfDeceaseFlag = placeOfDeceaseFlag;
	}


	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	public int getLegalPlaceOfLivingID() {
		return legalPlaceOfLivingID;
	}


	public void setLegalPlaceOfLivingID(int legalPlaceOfLivingID) {
		this.legalPlaceOfLivingID = legalPlaceOfLivingID;
	}


	public String getLegalPlaceOfLivingStandardized() {
		return legalPlaceOfLivingStandardized;
	}


	public void setLegalPlaceOfLivingStandardized(
			String legalPlaceOfLivingStandardized) {
		this.legalPlaceOfLivingStandardized = legalPlaceOfLivingStandardized;
	}


	public int getLegalPlaceOfLivingFlag() {
		return legalPlaceOfLivingFlag;
	}


	public void setLegalPlaceOfLivingFlag(int legalPlaceOfLivingFlag) {
		this.legalPlaceOfLivingFlag = legalPlaceOfLivingFlag;
	}


	public String getLegalPlaceOfLivingCode() {
		return legalPlaceOfLivingCode;
	}


	public void setLegalPlaceOfLivingCode(String legalPlaceOfLivingCode) {
		this.legalPlaceOfLivingCode = legalPlaceOfLivingCode;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getAddition() {
		return addition;
	}


	public void setAddition(String addition) {
		this.addition = addition;
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


	public B4_ST getRegistration() {
		return registration;
	}


	public void setRegistration(B4_ST registration) {
		this.registration = registration;
	}


	public ArrayList<B313_ST> getRelationsToPKHolder() {
		return relationsToPKHolder;
	}


	public void setRelationsToPKHolder(ArrayList<B313_ST> relationsToPKHolder) {
		this.relationsToPKHolder = relationsToPKHolder;
	}


	public ArrayList<B32_ST> getCivilStatus() {
		return civilStatus;
	}


	public void setCivilStatus(ArrayList<B32_ST> civilStatus) {
		this.civilStatus = civilStatus;
	}


	public ArrayList<B33_ST> getReligions() {
		return religions;
	}


	public void setReligions(ArrayList<B33_ST> religions) {
		this.religions = religions;
	}


	public ArrayList<B35_ST> getProfessions() {
		return professions;
	}


	public void setProfessions(ArrayList<B35_ST> professions) {
		this.professions = professions;
	}


	public ArrayList<B36_ST> getOrigins() {
		return origins;
	}


	public void setOrigins(ArrayList<B36_ST> origins) {
		this.origins = origins;
	}


	public ArrayList<B37_ST> getDestinations() {
		return destinations;
	}


	public void setDestinations(ArrayList<B37_ST> destinations) {
		this.destinations = destinations;
	}


	public ArrayList<B6_ST> getAddressess() {
		return addressess;
	}


	public void setAddressess(ArrayList<B6_ST> addressess) {
		this.addressess = addressess;
	}


	public ArrayList<B34_ST> getRelations() {
		return relations;
	}


	public void setRelations(ArrayList<B34_ST> relations) {
		this.relations = relations;
	}


	
}
