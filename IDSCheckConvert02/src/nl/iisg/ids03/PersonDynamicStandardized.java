/*
 * Naam:    PersonDynamicStandardized
 * Version: 0.1
 *  Author: Cor Munnik
 * Copyright
 */
package nl.iisg.ids03;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import nl.iisg.hsncommon.ConstRelations2;

import java.util.ArrayList;

/**
 * 
 * This class handles the (dynamic) attributes of a standardized PersonDynamic
 *
 */


@MappedSuperclass
public class PersonDynamicStandardized {
		
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
	
	@Transient                      private PersonDynamic      originalPersonDynamic;
	@Transient                      private PersonStandardized personStandardizedToWhomDynamicDataRefers;
	
	PersonDynamicStandardized(){} // No-args constructor for JPA
	
	/**
	 * 
	 * This routine transforms data from a PersonDynamic object to a PersonDynamicStandardized object
	 * 
	 * 
	 * @param pd
	 * @return
	 */
	
	public ArrayList<PersonDynamicStandardized> transform(PersonDynamic pd){

		setKeyToSourceRegister(pd.getKeyToSourceRegister());
		setKeyToRP(pd.getKeyToRP());
		setKeyToRegistrationPersons(pd.getKeyToRegistrationPersons());
		setKeyToDistinguishDynamicDataType(pd.getDynamicDataType());
		setDynamicDataSequenceNumber(pd.getDynamicDataSequenceNumber()); 
		//setContentOfDynamicData(pd.getContentOfDynamicData()); 
		setValueOfRelatedPerson(pd.getValueOfRelatedPerson()); 
		setNatureOfPerson(pd.getNatureOfPerson()); 
		//setDynamicData2(pd.getDynamicData2()); 
		setVersionLastTimeOfDataEntry(pd.getVersionLastTimeOfDataEntry());
		setResearchCodeOriginal(pd.getResearchCodeOriginal());
		setVersionOriginalDataEntry(pd.getVersionOriginalDataEntry());
        setDate0(pd.getDate0());
		
		transformHeadOfHouseholdDate(pd);
		transformMutationDate(pd);
		
		ArrayList<PersonDynamicStandardized> a = new ArrayList<PersonDynamicStandardized>();
		a.add(this);
		
		return a;
		
	}

	/**
	 * 
	 * This routine transform the Head of Household date to the new format
	 * 
	 * @param pd
	 */
	public void transformHeadOfHouseholdDate(PersonDynamic pd){
		
		String temp = String.format("%02d-%02d-%04d", pd.getDayEntryHead(), pd.getMonthEntryHead(), pd.getYearEntryHead());
		setEntryDateHead(temp);		

	}
	/**
	 * 
	 * This routine transform the mutation date to the new format
	 * 
	 * @param pd
	 */
	
	public void transformMutationDate(PersonDynamic pd){

		int[] result = 	Utils2.transformDateFields(pd.getDayOfMutation(),                    pd.getMonthOfMutation(),                     pd.getYearOfMutation(), 
				                                   pd.getDayOfMutationAfterInterpretation(), pd.getMonthOfMutationAfterInterpretation(),  pd.getYearOfMutationAfterInterpretation()); 

		setDateOfMutation(String.format("%02d-%02d-%04d", result[0], result[1], result[2]));
		setDateOfMutationFlag(result[3]);


	}
	
	/**
	 * 
	 * This routine decides which subclass of PersonDynamicStandardized must be allocated
	 * 
	 * @param pd
	 * @return
	 */
	
//    public static PersonDynamicStandardized PDS_Factory(PersonDynamic pd){
      public static PersonDynamicStandardized PDS_Factory(int dynamicDataType){
		
		
		
		switch(dynamicDataType){

		case ConstRelations2.RELATIE_TOT_HOOFD_ST:
		case ConstRelations2.RELATIE_TOT_HOOFD:

			PDS_RelationToHead rth = new PDS_RelationToHead();
			return rth;

		case ConstRelations2.BURGELIJKE_STAAT:

			PDS_CivilStatus cs = new PDS_CivilStatus();
			return cs;

		case ConstRelations2.OUDER_KIND:

			PDS_ParentsAndChildren pat = new PDS_ParentsAndChildren();
			return pat;

		case ConstRelations2.GODSDIENST:

			PDS_Religion r = new PDS_Religion();
			return r;

		case ConstRelations2.BEROEPSTITEL:

			PDS_OccupationalTitle ot = new PDS_OccupationalTitle();
			return ot;

		case ConstRelations2.AANKOMST:

			PDS_PlaceOfOrigin poo = new PDS_PlaceOfOrigin();
			return poo;

		case ConstRelations2.VERTREK:

			PDS_PlaceOfDestination pod = new PDS_PlaceOfDestination();
			return pod;

		}		
		
		return null;
		
	}
	
    /**
     * 
     * The PersonDynamicStandardized objects must be renumbered per subclass (RelationToHead, ParentsAndChildren etc.)
     * 
     * @param dynamicDataOfPerson
     */
    
      public static void renumber(ArrayList<PersonDynamicStandardized>  dynamicDataOfPerson){


    	  int rth = 1;
    	  int pat = 1;
    	  int cs  = 1;
    	  int r   = 1;
    	  int ot  = 1; 
    	  int poo = 1;
    	  int pod = 1;



    	  for(PersonDynamicStandardized pds: dynamicDataOfPerson){

    		  if(pds instanceof nl.iisg.ids03.PDS_RelationToHead){
    			  pds.setDynamicDataSequenceNumber(rth);
    			  rth++;
    		  }

    		  if(pds instanceof nl.iisg.ids03.PDS_ParentsAndChildren){
    			  pds.setDynamicDataSequenceNumber(pat);
    			  pat++;
    		  }

    		  if(pds instanceof nl.iisg.ids03.PDS_CivilStatus){
    			  pds.setDynamicDataSequenceNumber(cs);
    			  cs++;
    		  }

    		  if(pds instanceof nl.iisg.ids03.PDS_Religion){
    			  pds.setDynamicDataSequenceNumber(r);
    			  r++;
    		  }

    		  if(pds instanceof nl.iisg.ids03.PDS_OccupationalTitle){
    			  pds.setDynamicDataSequenceNumber(ot);
    			  ot++;
    		  }

    		  if(pds instanceof nl.iisg.ids03.PDS_PlaceOfOrigin){
    			  pds.setDynamicDataSequenceNumber(poo);
    			  poo++;
    		  }

    		  if(pds instanceof nl.iisg.ids03.PDS_PlaceOfDestination){
    			  pds.setDynamicDataSequenceNumber(pod);
    			  pod++;
    		  }
    	  }
      }
	
	public void print(){
		
    	//System.out.println("PersonDynamicStandardized");
    }
	
	/**
	 * 
	 * This routine writes the standardized PersonDynamic object to the database
	 * 
	 */

	
	public void write(){

		Utils.persist(this);
	}



	public PersonDynamicStandardized copyPersonDynamicStandardized(){
		
		PersonDynamicStandardized pds =  PDS_Factory(getKeyToDistinguishDynamicDataType());
		

		pds.setKeyToSourceRegister(getKeyToSourceRegister());
		pds.setEntryDateHead(getEntryDateHead());
		pds.setKeyToRP(getKeyToRP());
		pds.setKeyToRegistrationPersons(getKeyToRegistrationPersons());
		pds.setKeyToDistinguishDynamicDataType(getKeyToDistinguishDynamicDataType());
		pds.setDynamicDataSequenceNumber(getDynamicDataSequenceNumber()); 
		
		pds.setValueOfRelatedPerson(getValueOfRelatedPerson()); 
		pds.setNatureOfPerson(getNatureOfPerson());

		pds.setDateOfMutation(getDateOfMutation());
		pds.setDateOfMutationFlag(getDateOfMutationFlag());
		
		pds.setVersionLastTimeOfDataEntry(getVersionLastTimeOfDataEntry());
		pds.setResearchCodeOriginal(getResearchCodeOriginal());
		pds.setVersionOriginalDataEntry(getVersionOriginalDataEntry());
		pds.setDate0(getDate0());
		
		pds.setOriginalPersonDynamic(getOriginalPersonDynamic());
		pds.setPersonStandardizedToWhomDynamicDataRefers(getPersonStandardizedToWhomDynamicDataRefers());
		
		PersonDynamicStandardized pds2 = this;
		
		switch(pds.getKeyToDistinguishDynamicDataType()){

		case ConstRelations2.RELATIE_TOT_HOOFD_ST:

			((PDS_RelationToHead)pds).setContentOfDynamicData(((PDS_RelationToHead)this).getContentOfDynamicData());
			((PDS_RelationToHead)pds).setDynamicData2(((PDS_RelationToHead)this).getDynamicData2());
			
			
			break;
			
		case ConstRelations2.OUDER_KIND:

			((PDS_ParentsAndChildren)pds).setContentOfDynamicData(((PDS_ParentsAndChildren)this).getContentOfDynamicData());
			((PDS_ParentsAndChildren)pds).setRelation(((PDS_ParentsAndChildren)this).getRelation());
			break;
			

		case ConstRelations2.BURGELIJKE_STAAT:			

			((PDS_CivilStatus)pds).setContentOfDynamicData(((PDS_CivilStatus)pds2).getContentOfDynamicData());
			
			((PDS_CivilStatus)pds).setCivilStatusFlag(((PDS_CivilStatus)pds2).getCivilStatusFlag());
			((PDS_CivilStatus)pds).setCivilLocalityID(((PDS_CivilStatus)pds2).getCivilLocalityID());
			((PDS_CivilStatus)pds).setCivilLocalityStandardized(((PDS_CivilStatus)pds2).getCivilLocalityStandardized());
			((PDS_CivilStatus)pds).setCivilLocalityFlag(((PDS_CivilStatus)pds2).getCivilLocalityFlag());
			
			break;

		case ConstRelations2.GODSDIENST:

			((PDS_Religion)pds).setReligionID(((PDS_Religion)this).getReligionID());
			((PDS_Religion)pds).setReligionStandardized(((PDS_Religion)this).getReligionStandardized());
			((PDS_Religion)pds).setReligionFlag(((PDS_Religion)this).getReligionFlag());
			break;

		case ConstRelations2.BEROEPSTITEL:

			((PDS_OccupationalTitle)pds).setOccupationID(((PDS_OccupationalTitle)this).getOccupationID());
			((PDS_OccupationalTitle)pds).setOccupationStandardized(((PDS_OccupationalTitle)this).getOccupationStandardized());
			((PDS_OccupationalTitle)pds).setOccupationFlag(((PDS_OccupationalTitle)this).getOccupationFlag());
			break;

		case ConstRelations2.AANKOMST:

			((PDS_PlaceOfOrigin)pds).setOriginID(((PDS_PlaceOfOrigin)this).getOriginID());
			((PDS_PlaceOfOrigin)pds).setOriginStandardized(((PDS_PlaceOfOrigin)this).getOriginStandardized());
			((PDS_PlaceOfOrigin)pds).setOriginFlag(((PDS_PlaceOfOrigin)this).getOriginFlag());
			
			((PDS_PlaceOfOrigin)pds).setOriginGroup(((PDS_PlaceOfOrigin)this).getOriginGroup());
			
			((PDS_PlaceOfOrigin)pds).setAddress(((PDS_PlaceOfOrigin)this).getAddress());
			((PDS_PlaceOfOrigin)pds).setRegister(((PDS_PlaceOfOrigin)this).getRegister());
			((PDS_PlaceOfOrigin)pds).setCensus(((PDS_PlaceOfOrigin)this).getCensus());


			break;

		case ConstRelations2.VERTREK:

			((PDS_PlaceOfDestination)pds).setDestinationID(((PDS_PlaceOfDestination)this).getDestinationID());
			((PDS_PlaceOfDestination)pds).setDestinationStandardized(((PDS_PlaceOfDestination)this).getDestinationStandardized());
			((PDS_PlaceOfDestination)pds).setDestinationFlag(((PDS_PlaceOfDestination)this).getDestinationFlag());
			
			((PDS_PlaceOfDestination)pds).setDestinationGroup(((PDS_PlaceOfDestination)this).getDestinationGroup());
			
			((PDS_PlaceOfDestination)pds).setAddress(((PDS_PlaceOfDestination)this).getAddress());
			((PDS_PlaceOfDestination)pds).setRegister(((PDS_PlaceOfDestination)this).getRegister());
			((PDS_PlaceOfDestination)pds).setCensus(((PDS_PlaceOfDestination)this).getCensus());


			break;

		}		
		
		
		
		return pds;
	}
	

	public void truncate(){	
		
		//System.out.println("In PersonDynamicStandardized.truncate()");
		
		switch(getKeyToDistinguishDynamicDataType()){

		case ConstRelations2.RELATIE_TOT_HOOFD_ST:

			((PDS_RelationToHead)this).truncate();
			break;
			
		case ConstRelations2.OUDER_KIND:
			break;
			
		case ConstRelations2.BURGELIJKE_STAAT:			

			((PDS_CivilStatus)this).truncate();
			break;

		case ConstRelations2.ALLTOALL:

			((PDS_AllToAll)this).truncate();
			break;

		case ConstRelations2.GODSDIENST:

			((PDS_Religion)this).truncate();
			break;

		case ConstRelations2.BEROEPSTITEL:

			((PDS_OccupationalTitle)this).truncate();
			break;

		case ConstRelations2.AANKOMST:

			((PDS_PlaceOfOrigin)this).truncate();

			break;

		case ConstRelations2.VERTREK:

			((PDS_PlaceOfDestination)this).truncate();
			break;

		}		
		
		

		
	}
	
	/*
	 * This function is used throughout the program instead of getDateOfMutation
	 * It returns a date of "00-00-0000" if the date is like "03-04-0000"
	 * 
	 */
	
	public String getDateOfMutation2() {
		
		if(getDateOfMutation() != null && getDateOfMutation().substring(6, 10).equals("0000"))
			return("00-00-0000");
		
		return dateOfMutation;
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
	public void setEntryDateHead(String datentryHeadOfHousehold) {
		this.entryDateHead = datentryHeadOfHousehold;
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
	public void setDynamicDataSequenceNumber(
			int sequenceNumberToDistinguishDynamicData) {
		this.dynamicDataSequenceNumber = sequenceNumberToDistinguishDynamicData;
	}
	//public int getContentOfDynamicData() {
	//	return contentOfDynamicData;
	//}
	//public void setContentOfDynamicData(int contentOfDynamicData) {
	//	this.contentOfDynamicData = contentOfDynamicData;
	//}
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
	//public String getDynamicData2() {
	//	return dynamicData2;
	//}
	//public void setDynamicData2(String dynamicData2) {
	//	this.dynamicData2 = dynamicData2;
	//}
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

	public void setDate0(String date) {
		this.date0 = date;
	}

	public PersonDynamic getOriginalPersonDynamic() {
		return originalPersonDynamic;
	}

	public void setOriginalPersonDynamic(PersonDynamic originalPersonDynamic) {
		this.originalPersonDynamic = originalPersonDynamic;
	}

	public PersonStandardized getPersonStandardizedToWhomDynamicDataRefers() {
		return personStandardizedToWhomDynamicDataRefers;
	}

	public void setPersonStandardizedToWhomDynamicDataRefers(
			PersonStandardized personStandardizedToWhomDynamicDataRefers) {
		this.personStandardizedToWhomDynamicDataRefers = personStandardizedToWhomDynamicDataRefers;
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




	
	
}
