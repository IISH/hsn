package nl.iisg.convertPK;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import nl.iisg.hsncommon.ConstRelations;
import nl.iisg.hsncommon.Common1;
import nl.iisg.hsncommon.ConstRelations2;

@Entity
@Table(name="b4_stpk")
public class B4_ST {
	
	@Id @Column(name = "B1IDBG")	  	private int       keyToSourceRegister;
	@Id @Column(name = "B2DIBG")  		private String    entryDateHead;                      
	@Id @Column(name = "IDNR")   		private int       keyToRP;     

	@Column(name = "B2FDBG")      		private String    entryDateRP;
	
	@Column(name = "IDNR_SPOUSE")  		private int       idnrSpouse;
	@Column(name = "PK_HOLDER")   		private String    pkHolder;   

	@Column(name = "START_PK")		    private String    startDate;
	@Column(name = "START_PK_FG")  		private int       startFlag;

	@Column(name = "END_PK")    		private String    endDate;
	@Column(name = "END_PK_FG")    		private int       endFlag;

	
	@Column(name = "REGISTER_PAGE")     private String    pageNumber;                    
	@Column(name = "REGISTER_LINE")     private int       numberOfHousehold;                  
	@Column(name = "NAME_HEAD_GK")      private String    nameHeadGK;       // GK = GezinsKaart = FamilyCard           
	@Column(name = "SPECIAL_CODE")      private String    specialCode;               
	@Column(name = "REMARKS") 		    private String    remarks;  

	@Column(name = "VERSIE")      		private String    versionLastTimeOfDataEntry;
	@Column(name = "ONDRZKO")     		private String    researchCodeOriginal;
	@Column(name = "VERSIEO")     		private String    versionOriginalDataEntry;
	@Column(name = "DATUM")     		private String    date0;
	
	@Transient                          private ArrayList<B2_ST> persons = new ArrayList<B2_ST>();  
	@Transient                          private ArrayList<B6_ST> addresses = new ArrayList<B6_ST>();  

	B4_ST(){} // No-args constructor for JPA
	
	
	public void save(EntityManager em){
		
		
		
		System.out.println("B4_ST: idnr = " + getKeyToRP());
		
		em.persist(this);
		
		for(B2_ST person: getPersons())
			person.save(em);
		
		for(B6_ST address: getAddresses())
			address.save(em);
		
	}
	
    /**
     * 
     * This routine determines the relation of every person in the registration to every other person
     * in the registration (except to him/herself)
     * 
     */
    
    public void relateAllToAll(){
    	
    	//System.out.println("RP = " + getKeyToRP());

    	for(B2_ST psA: getPersons()){
    		

    		int relToHeadA = 0; 
    		for(B313_ST pdsA: psA.getRelationsToPKHolder()){
	        		relToHeadA = pdsA.getContentOfDynamicData();
	        		
	        	if(relToHeadA <= 0)
	        		continue;
	        	
	    		int relToHeadB = 0; 
	        	
	    		
	        	for(B2_ST psB: getPersons()){

	        		if(psB == psA || psB.getPersonID() == psA.getPersonID())
	        			continue;
	        		
	        		// Assume no relation between father and mother PK-Holder 
	        		
	        		if((pdsA.getKeyToRegistrationPersons() == 2 && psB.getKeyToPersons() == 3) || (pdsA.getKeyToRegistrationPersons() == 3 && psB.getKeyToPersons() == 2))
	        			continue;
	        			
	        		
	        		for(B313_ST pdsB: psB.getRelationsToPKHolder()){
	        			relToHeadB = pdsB.getContentOfDynamicData();

	        			if(relToHeadB <= 0)
	        				continue;

	        			int[] relABa = Common1.getRelation(relToHeadA, relToHeadB);
    	        		
    	        		
    	        		int relAB = 90;
    	        		if(relABa != null){
    	        			
    	        			relAB = relABa[0]; // male form
    	        			
    	        			//if(relABa.length == 2 && psA.getSex().equalsIgnoreCase("V")){

    	        	    		//if(psA.getKeyToRP()  == 70269)
    	        	    		//	System.out.println("relAB (m)= " + relAB);
        	        			//relAB = relABa[1];  // female form
        	        			//if(psA.getKeyToRP()  == 70269)
        	        			//	System.out.println("relAB (v) = " + relAB);
    	        			//}
    	        			
    	        			
    	        		}
    	        		
        		    	//String vhhA ="";
        		    	//if(relToHeadA ==1 || relToHeadA == 2 ||  relToHeadA == 147)
        		    	//	vhhA = "";
        		    	//else 
        		    	//	vhhA = " van het Hoofd ";
        		    	//String vhhB ="";
        		    	//if(relToHeadB ==1  || relToHeadB == 2 ||  relToHeadB == 147)
        		    	//	vhhB = "";
        		    	//else 
        		    	//	vhhB = " van het Hoofd ";
        		    	
        		    	//System.out.println("" + ConstRelations2.b3kode1[relToHeadA]   + vhhA + "[" +  pdsA.getPerson().getPersonID() + "]" + " is *" +
        		    		//	ConstRelations2.b3kode1[relAB] + "[" + relAB + "]"  + "* van " + ConstRelations2.b3kode1[relToHeadB] + vhhB  + "["  + pdsB.getPerson().getPersonID() + "]");
        		    	

        				
	        			if(relAB <= 0)
	        				continue;	        			
	        			
	        			
        				if(ConstRelations2.b3kode1_Related[relAB] == null){ // not blood related, so lets try to date
        					if(pdsA.getStartDate() != null || pdsB.getStartDate() != null){
        						String  []  intersection = Common1.getIntersection(pdsA.getStartDate(), pdsA.getEndDate(), pdsB.getStartDate(), pdsB.getEndDate());
        						//System.out.println(pdsA.getStartDate() + "    " + pdsA.getEndDate());
        						//System.out.println(pdsB.getStartDate() + "    " + pdsB.getEndDate());
        						if(intersection != null){


        							//System.out.println();
        							//System.out.println("-----");
        							//System.out.println();
        							
            						//System.out.println(pdsA.getStartDate() + "    " + pdsA.getEndDate());
            						//System.out.println(pdsB.getStartDate() + "    " + pdsB.getEndDate());        							
            						//System.out.println(intersection[0]     + "    " + intersection[1]);

        							//System.out.println();
        							//System.out.println();

            						
		        					B34_ST pds = new B34_ST();
		        					
		        					// Do not date to and from a parents (b2rngb = 2 or 3), we don't know when they died
		        					
		        					if(psA.getKeyToPersons() != 2 && psA.getKeyToPersons() != 3 &&
		        							psB.getKeyToPersons() != 2 && psB.getKeyToPersons() != 3){

		        						pds.setStartDate(intersection[0]);
		        						pds.setEndDate(intersection[1]);
		        					}
		        					
		        					pds.setKeyToSourceRegister(psA.getKeyToSourceRegister());
		        					pds.setEntryDateHead(psA.getEntryDateHead());
		        					pds.setKeyToRP(psA.getKeyToRP());
		        					pds.setKeyToRegistrationPersons(psA.getKeyToPersons());
		        					pds.setDynamicDataType(4);
		        					pds.setDynamicDataSequenceNumber(psA.getRelations().size() + 1); 
		        					pds.setContentOfDynamicData(relAB); 

		        					pds.setValueOfRelatedPerson(psB.getKeyToPersons()); 
		        					pds.setNatureOfPerson(psA.getNatureOfPerson());

		        					pds.setDateOfMutation("00-00-0000");
		        					
		        					// between the PK- Holder and spouses of the PK-Holder we use the Marriage date as start date
		        					
		        					if((pdsA.getContentOfDynamicData() == ConstRelations2.HOOFD)
		        							&& pdsB.getContentOfDynamicData() == ConstRelations2.ECHTGENOTE_HOOFD)
		        						if(pdsB.getDateOfMutation() != null)
		        							pds.setDateOfMutation(pdsB.getDateOfMutation());
		        					
		        					if(pdsA.getContentOfDynamicData() == ConstRelations2.ECHTGENOTE_HOOFD && pdsB.getContentOfDynamicData() == ConstRelations2.HOOFD)
		        						if(pdsA.getDateOfMutation() != null)
		        							pds.setDateOfMutation(pdsA.getDateOfMutation());
		        					
		        					pds.setDateOfMutationFlag(0);

		        					pds.setVersionLastTimeOfDataEntry(psA.getVersionLastTimeOfDataEntry());
		        					pds.setResearchCodeOriginal(psA.getResearchCodeOriginal());
		        					pds.setVersionOriginalDataEntry(psA.getVersionOriginalDataEntry());
		        					pds.setDate0(psA.getDate0());
		        					
		        					//pds.setOriginalPersonDynamic(null);
		        					pds.setPerson(psA);

		        					psA.getRelations().add(pds);

		        				}
	        				}
        					else{ // not blood related but no dates
	        					B34_ST pds = new B34_ST();

	        					pds.setKeyToSourceRegister(psA.getKeyToSourceRegister());
	        					pds.setEntryDateHead(psA.getEntryDateHead());
	        					pds.setKeyToRP(psA.getKeyToRP());
	        					pds.setKeyToRegistrationPersons(psA.getKeyToPersons());
	        					pds.setDynamicDataType(4);
	        					pds.setDynamicDataSequenceNumber(psA.getRelations().size() + 1); 
	        					pds.setContentOfDynamicData(relAB); 

	        					pds.setValueOfRelatedPerson(psB.getKeyToPersons()); 
	        					pds.setNatureOfPerson(psA.getNatureOfPerson());

	        					pds.setDateOfMutation("00-00-0000");
	        					pds.setDateOfMutationFlag(0);

	        					pds.setVersionLastTimeOfDataEntry(psA.getVersionLastTimeOfDataEntry());
	        					pds.setResearchCodeOriginal(psA.getResearchCodeOriginal());
	        					pds.setVersionOriginalDataEntry(psA.getVersionOriginalDataEntry());
	        					pds.setDate0(psA.getDate0());
	        					
	        					//pds.setOriginalPersonDynamic(null);
	        					pds.setPerson(psA);

	        					psA.getRelations().add(pds);
        					}
	        			}
	        			else{ // blood related, do not date

	        				B34_ST pds = new B34_ST();

	        				pds.setKeyToSourceRegister(psA.getKeyToSourceRegister());
	        				pds.setEntryDateHead(psA.getEntryDateHead());
	        				pds.setKeyToRP(psA.getKeyToRP());
	        				pds.setKeyToRegistrationPersons(psA.getKeyToPersons());
	        				pds.setDynamicDataType(4);
	        				pds.setDynamicDataSequenceNumber(psA.getRelations().size() + 1); 
	        				pds.setContentOfDynamicData(relAB); 

	        				pds.setValueOfRelatedPerson(psB.getKeyToPersons()); 
	        				pds.setNatureOfPerson(psA.getNatureOfPerson());

	        				pds.setDateOfMutation("00-00-0000");
	        				pds.setDateOfMutationFlag(0);

	        				pds.setVersionLastTimeOfDataEntry(psA.getVersionLastTimeOfDataEntry());
	        				pds.setResearchCodeOriginal(psA.getResearchCodeOriginal());
	        				pds.setVersionOriginalDataEntry(psA.getVersionOriginalDataEntry());
	        				pds.setDate0(psA.getDate0());

	        				//pds.setOriginalPersonDynamic(null);
	        				pds.setPerson(psA);

	        				psA.getRelations().add(pds);

	        			}
	        		}
	        	}
    		}
    	}
    }

    
    /**
     * 
     * This routine determines the relation between two persons based on the relationship of these persons to the Head 
     * 
     * @param relToHeadA
     * @param relToHeadB
     * @return relation A to B
     */
    
    private int getRelation(int relToHeadA, int relToHeadB){
    	
    	// Some codes do not change
    	
    	
    	if(relToHeadA == -1 || relToHeadA == -2 || relToHeadA == -3 || relToHeadA == 80 ||
    			(relToHeadA >= 90 && relToHeadA <= 100) ||
    			(relToHeadA >= 41 && relToHeadA <= 49))
    		
    		return relToHeadA;
    			
    	
    	
    	
    	// look up first code
    	
    	int code1 = -1; 
    	
    	outer1:
    	for(int i = 0; i < ConstRelations.relCode1.length; i++){
        	for(int j = 0; j < ConstRelations.relCode1[i].length; j++){
        		if( ConstRelations.relCode1[i][j] == relToHeadA){
        			code1 = i;
        			break outer1;
        		}
        	}
    	}
    	if(code1 < 0)
    		return -1;
    	
    	// look up second code
    	
    	int code2 = -1; 
    	
    	outer2:
    	for(int i = 0; i < ConstRelations.relCode2.length; i++){
        	for(int j = 0; j < ConstRelations.relCode2[i].length; j++){
        		if( ConstRelations.relCode2[i][j] == relToHeadB){
        			code2 = i;
        			break outer2;
        		}
        	}
    	}
    	if(code2 < 0)
    		return -1;
    	
    	// look up code between A and B
    	
    	int AB = 90;  // preset no relation
    	
    	if(ConstRelations.transform[code1][code2] != null && ConstRelations.transform[code1][code2].length > 0)
    		AB = ConstRelations.transform[code1][code2][ConstRelations.transform[code1][code2].length -1];
    	    	   	
    	return AB;
    	
    	
    }
    


	/**
	 * 
	 * This routine truncates fields that are too long for the corresponding database columns
	 * 
	 */
	
	
	public void truncate(){	
		
		String field = getPageNumber();
		int allowedSize = TableDefinitions.Smallstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B4_ST", "REGISTER_PAGE", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setPageNumber(field);
		}
			
		field = getNameHeadGK();
		allowedSize = TableDefinitions.XBigstring;

		if(field != null && field.length() > allowedSize){
			message("1500", "B4_ST", "NAME_HEAD_GK", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setNameHeadGK(field);
		}
			
		field = getSpecialCode();
		allowedSize = TableDefinitions.XBigstring;

		if(field != null && field.length() > allowedSize){
			message("1500", "B4_ST", "SPECIAL_CODE", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setSpecialCode(field);
		}
			
		field = getRemarks();
		allowedSize = TableDefinitions.XBigstring;

		if(field != null && field.length() > allowedSize){
			message("1500", "B4_ST", "REMARKS", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setRemarks(field);
		}
			
		
		for(B2_ST p: persons){
			p.truncate();
		}
		
		for(B6_ST ras: addresses){
			ras.truncate();
		}
		
	}
	
	/**
	 * 
	 * This routine allocates a Message object and fills it with the parameters.
	 * Additional information is added to identify the B*-row(s) to which the message applies
	 * 
	 * @param number
	 * @param fills
	 */

	private void message(String number, String... fills){
		
		Message m = new Message(number);
		
		m.setKeyToRP(getKeyToRP());
		
		m.setKeyToSourceRegister(getKeyToSourceRegister());
		
		m.setDayEntryHead((new Integer(getEntryDateHead().substring(0,2)).intValue()));
		m.setMonthEntryHead((new Integer(getEntryDateHead().substring(3,5)).intValue()));
		m.setYearEntryHead((new Integer(getEntryDateHead().substring(6,10)).intValue()));
		
		m.setKeyToRegistrationPersons(0);
		m.setNatureOfPerson(0);
		
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

	

}
