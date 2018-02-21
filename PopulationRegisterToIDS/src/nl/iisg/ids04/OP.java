package nl.iisg.ids04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import javax.persistence.EntityManager;

import nl.iisg.idscontext.ContextElement;
import nl.iisg.idscontext.Contxt;


public class OP {
    private int                                     keyToRP;
    private ArrayList<B4_ST>                        RegistrationsStandardizedOfOP = new ArrayList<B4_ST>();

    OP(){}
    
    OP(int key){
    	
    	setKeyToRP(key);
    	
    }

    
    public void convert(EntityManager em){
    	
    	// Create list of unique persons
    	
    	
    	//System.out.println("---> RP = " + getKeyToRP());
    	
    	HashSet<Integer> h = new HashSet();

    	int OPBirthYear = 0;
    	
    	for(B4_ST b4: getRegistrationsStandardizedOfOP()){
    		for(B2_ST b2: b4.getPersonsStandardizedInRegistration()){    			
    			h.add(b2.getPersonID());    	
    			if(b2.getNatureOfPerson() == 1)
    				OPBirthYear = new Integer(b2.getDateOfBirth().substring(6, 10)).intValue();
    		}
    	}
    	
    	//System.out.println("Persons: " + h.size());
    	
    	
    	// For each personID, collect the list of identifying properties
    	


    	String[] a = null;
    	
    	for(Integer pId: h){
    		
    		//System.out.println();
    		//System.out.println("Person: " + pId);

        	ArrayList<String>  firstNames        = new ArrayList<String>(); 
        	ArrayList<String>  lastNames         = new ArrayList<String>(); 
        	ArrayList<String>  prefixes          = new ArrayList<String>(); 
        	ArrayList<String>  sexes             = new ArrayList<String>(); 
        	
        	ArrayList<String>  birthDates        = new ArrayList<String>();
        	ArrayList<String>  birthDatesFlag    = new ArrayList<String>();
        	ArrayList<String>  deathDates        = new ArrayList<String>(); 
        	ArrayList<String>  deathDatesFlag    = new ArrayList<String>(); 
        	ArrayList<String>  birthPlaces       = new ArrayList<String>(); 
        	ArrayList<String>  birthPlacesFlag   = new ArrayList<String>(); 
        	ArrayList<String>  deathPlaces       = new ArrayList<String>(); 
        	ArrayList<String>  deathPlacesFlag   = new ArrayList<String>();

        	class observationPeriod{
        		
        		String  startDate;        
        		int     startFlag;        
        		int     startEst;
        		int     startDays;

        		String  endDate;        
        		int     endFlag;        
        		int     endEst;   
        		int     endDays;

        	}
        	
        	ArrayList<observationPeriod> observationPeriods = new  ArrayList<observationPeriod>();
        	
        	boolean isRP = false; 

    		
        	for(B4_ST b4: getRegistrationsStandardizedOfOP()){
        		for(B2_ST b2: b4.getPersonsStandardizedInRegistration()){
        			if(b2.getPersonID() == pId){

          				if(b2.getFirstName() != null)
        					firstNames.add(b2.getFirstName().trim());
        				if(b2.getFamilyName() != null)
        					lastNames.add(b2.getFamilyName().trim());
        				if(b2.getPrefixLastName() != null)
        					prefixes.add(b2.getPrefixLastName().trim());
        				if(b2.getSex() != null)
        					sexes.add(b2.getSex().trim());
        				
        				if(b2.getDateOfBirth() != null && !b2.getDateOfBirth().equals("00-00-0000")){
        					birthDates.add(b2.getDateOfBirth().trim());
        					birthDatesFlag.add("" + b2.getDateOfBirthFlag());
        				}
        				if(b2.getDateOfDecease() != null && !b2.getDateOfDecease().equals("00-00-0000")){
        					deathDates.add(b2.getDateOfDecease().trim());
        					deathDatesFlag.add("" + b2.getDateOfDeceaseFlag());
        				}
        				if(b2.getPlaceOfBirthStandardized() != null){
        					birthPlaces.add(b2.getPlaceOfBirthStandardized().trim());
        					birthPlacesFlag.add("" + b2.getPlaceOfBirthFlag());
        				}
        				if(b2.getPlaceOfDeceaseStandardized() != null){
        					deathPlaces.add(b2.getPlaceOfDeceaseStandardized().trim());
        					deathPlacesFlag.add("" + b2.getPlaceOfDeceaseFlag());
        				}
        				
        				
        				if(b2.getNatureOfPerson() == 1 || b2.getNatureOfPerson() == 5)
        					isRP = true;
        				
        				if(!b2.getStartDate().equals("00-00-0000")  && !b2.getEndDate().equals("00-00-0000")){
        					observationPeriod p = new observationPeriod();
        					p.startDate = b2.getStartDate();
        					p.startFlag = b2.getStartFlag();
        					p.startEst = b2.getStartEst();

        					p.endDate = b2.getEndDate();
        					p.endFlag = b2.getEndFlag();
        					p.endEst = b2.getEndEst();
        					observationPeriods.add(p);
        				}
        				
        			}
        		}
        	}
        	
    		Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "HSN_IDENTIFIER", "" + getKeyToRP());
    		
    		
        	
        
        	Integer x = null;
        	Integer z = null;
        	Integer y = null;
        	
        	int day = 0;
        	int month = 0;
        	int year = 0;

        	a = Utils.findLastMostCommonValue(birthDates, birthDatesFlag);
            
        	if(a[0] != null && a[0].length() != 0){
        		
        		day   = (new Integer(a[0].substring(0,2))).intValue();
        		month = (new Integer(a[0].substring(3,5))).intValue();
        		year  = (new Integer(a[0].substring(6,10))).intValue();

        
        		Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "BIRTH_DATE", null, day, month, year);
        		
        		x = new Integer(a[1]);
        		y = new Integer(a[2]);
        		z = 100 * y + x;		

        		Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "BIRTH_DATE_FLAG", "" + z, day, month, year);
        		
        	}
        	else{
        		
        		Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "BIRTH_DATE", null,  "Estimation [-100, 100]", 1, 1, OPBirthYear - 100, 1, 1, OPBirthYear + 100);
      		       //  addIndiv(EntityManager em, int IDNR, int Id_I, String source, String type, String value, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear){

        		
        		
        	}
        		
        	a = Utils.findLastMostCommonValue(birthPlaces, birthPlacesFlag);
        	
        	if(a[0] != null && a[0].length() != 0){       		

        		ContextElement ce = Contxt.get2(a[0]);		
        		if(ce != null){
        			Utils.addIndivAndContext(null, ce, em, getKeyToRP(), pId, "B2_ST",  "BIRTH_LOCATION", day, month, year);



        			//Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "BIRTH_PLACE", a[0], day, month, year);

        			x = new Integer(a[1]);
        			y = new Integer(a[2]);
        			z = 100 * y + x;		
        			Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "BIRTH_LOCATION_FLAG", "" + z, day, month, year);
        		}
        	}


        	a = Utils.findLastMostCommonValue(firstNames, null);
        	
        	if(a[0] != null && a[0].length() != 0){

        		//Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "FIRST_NAME", a[0], day, month, year);
        		Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "FIRST_NAME", a[0]);

        		//Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "FIRST_NAME_FLAG", a[1], day, month, year);
        	}

        	a = Utils.findLastMostCommonValue(lastNames, null);
        	if(a[0] != null && a[0].length() != 0){

        		Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "LAST_NAME", a[0]);
        		//Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "LASTN_AME_FLAG", a[1], day, month, year);
        	}
        	
        	a = Utils.findLastMostCommonValue(prefixes, null);
        	if(a[0] != null && a[0].length() != 0){

        		Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "PREFIX_LAST_NAME", a[0]);
        		//Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "PREFIX_LASTNAME_FLAG", a[1], day, month, year);
        	}
        	
        	a = Utils.findLastMostCommonValue(sexes, null);
        	if(a[0] != null && a[0].length() != 0){

        		String sex = a[0];
        		if(sex.equalsIgnoreCase("M"))
        			sex = "Male";
        		else
            		if(sex.equalsIgnoreCase("V"))
            			sex = "Female";
            		else
            			sex = "Unknown";
        		
        		Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "SEX", sex);
        		//Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "SEX_FLAG", a[1], day, month, year);
        	}
        	

        	day = 0;
        	month = 0;
        	year = 0;

        	
        	a = Utils.findLastMostCommonValue(deathDates, deathDatesFlag);
        	if(a[0] != null && a[0].length() != 0){

        		day   = (new Integer(a[0].substring(0,2))).intValue();
        		month = (new Integer(a[0].substring(3,5))).intValue();
        		year  = (new Integer(a[0].substring(6,10))).intValue();
        		
        		Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "DEATH_DATE", null, day, month, year);

        		x = new Integer(a[1]);
        		y = new Integer(a[2]);
        		z = 100 * y + x;		

        		Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "DEATH_DATE_FLAG", "" + z, day, month, year);

        	}
        	
        	
        	a = Utils.findLastMostCommonValue(deathPlaces, deathPlacesFlag);
        	if(a[0] != null && a[0].length() != 0){

        		//System.out.println("Deathplace = " + a[0] + "size = " + a[0].length());   		
        		

        		ContextElement ce = Contxt.get2(a[0]);		
        		if(ce != null){
        			Utils.addIndivAndContext(null, ce, em, getKeyToRP(), pId, "B2_ST",  "DEATH_LOCATION", day, month, year);

        			//Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "DEATH_PLACE", a[0], day, month, year);
        			x = new Integer(a[1]);
        			y = new Integer(a[2]);
        			z = 100 * y + x;		


        			Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "DEATH_LOCATION_FLAG", "" + z, day, month, year);

        			
        		}
        	}
        	
        	// We must sort the Start - End Periods on Start date
        	// Then we must write "OBSERVATION_START" and OBSERVATION_END records
        	
        	
        	Collections.sort(observationPeriods, new Comparator<observationPeriod>()
        			{
        		public int compare(observationPeriod p1, observationPeriod p2){

        			int i1 = Utils.dayCount(p1.startDate);
        			int i2 = Utils.dayCount(p2.startDate);

        			if(i1 < i2)
        				return -1;
        			if(i1 > i2)
        				return  1;

        			return 0;
        		}
        			});
        	
        	int c = 1;
        	observationPeriod pp = null;     // reference to previous observation period
        	
        	
        	int day_1   = 0;
        	int month_1 = 0;
        	int year_1  = 0;

        	
        	for(observationPeriod p: observationPeriods){
        		if(c == 1){
        			
            		day_1   = (new Integer(p.startDate.substring(0,2))).intValue();
            		month_1 = (new Integer(p.startDate.substring(3,5))).intValue();
            		year_1  = (new Integer(p.startDate.substring(6,10))).intValue();
            		
            		String reason = "Start Source";
            		if(p.startFlag == 3)
            			reason = "Birth";
            		else
            			if(p.startFlag == 4)
            				reason = "Arrival"; 
            			
            		
            		
            		//Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "OBSERVATION_START", reason, day_1, month_1, year_1);
        		}
        	
        		if(pp != null &&  Math.abs(Utils.dayCount(pp.endDate) - Utils.dayCount(p.startDate)) > 10){
        			
            		day   = (new Integer(pp.endDate.substring(0,2))).intValue();
            		month = (new Integer(pp.endDate.substring(3,5))).intValue();
            		year  = (new Integer(pp.endDate.substring(6,10))).intValue();
            		
            		String reason = "End Source";
            		if(pp.endFlag == 51)
            			reason = "Death";
            		else
            			if(pp.endFlag == 52)
            				reason = "Departure"; 
            			

            		
            		//Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "OBSERVATION_END", reason, day, month, year);
            		Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "OBSERVATION", null, null, day_1, month_1, year_1, day, month, year);


            		
            		day   = (new Integer(p.startDate.substring(0,2))).intValue();
            		month = (new Integer(p.startDate.substring(3,5))).intValue();
            		year  = (new Integer(p.startDate.substring(6,10))).intValue();
            		
            		reason = "Start Source";
            		if(p.startFlag == 3)
            			reason = "Birth";
            		else
            			if(p.startFlag == 4)
            				reason = "Arrival"; 
            			

            		//Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "OBSERVATION_START", reason, day, month, year);
        		}
        		
        		if(c == observationPeriods.size()){
        			
            		day   = (new Integer(p.endDate.substring(0,2))).intValue();
            		month = (new Integer(p.endDate.substring(3,5))).intValue();
            		year  = (new Integer(p.endDate.substring(6,10))).intValue();
            		
            		String reason = "End Source";
            		if(p.endFlag == 51)
            			reason = "Death";
            		else
            			if(p.endFlag == 52)
            				reason = "Departure"; 
            			

            		
            		//Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "OBSERVATION_END", reason, day, month, year);
            		Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "OBSERVATION", null, null, day_1, month_1, year_1, day, month, year);
        			
        			
        		}
        		c++;
        		pp = p;  // keep previous period
        	}
        	
        	
        	
        	if(isRP)
        		Utils.addIndiv(em, getKeyToRP(), pId, "B2_ST", "HSN_RESEARCH_PERSON", "HSN RP", day, month, year);

    	}
    }

    public int getKeyToRP() {
		return keyToRP;
	}

	public void setKeyToRP(int keyToRP) {
		this.keyToRP = keyToRP;
	}

	public ArrayList<B4_ST> getRegistrationsStandardizedOfOP() {
		return RegistrationsStandardizedOfOP;
	}

	public void setRegistrationsStandardizedOfOP(
			ArrayList<B4_ST> registrationsStandardizedOfOP) {
		RegistrationsStandardizedOfOP = registrationsStandardizedOfOP;
	}
    
    

}
