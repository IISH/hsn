package nl.iisg.ids04;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import nl.iisg.ids04.INDIVIDUAL;
import nl.iisg.ids04.INDIV_CONTEXT;
import nl.iisg.ids04.INDIV_INDIV;
import nl.iisg.idscontext.ContextElement;
import nl.iisg.idscontext.Contxt;

public class Utils {
	
	public static void addIndivIndiv(EntityManager em, int IDNR, int id_i_1,  int id_i_2, String source, String relation, int day, int month, int year){
		
		INDIV_INDIV iiUp = new INDIV_INDIV();
		
		iiUp.setId_I_1(id_i_1);
		iiUp.setId_I_2(id_i_2);
		iiUp.setRelation(relation.length() <= 50 ? relation : relation.substring(0, 50));
		iiUp.setId_D((new Integer(IDNR).toString()));
		iiUp.setSource("HSN PR " + source);

		
		if(year != 0){
			
			iiUp.setDate_type("Event");
			iiUp.setEstimation("Exact");
			iiUp.setDay(day);
			iiUp.setMonth(month);
			iiUp.setYear(year);			
			
		}
		
		em.persist(iiUp);
		
	}
	
	public static void addIndivIndiv(EntityManager em, int IDNR, int id_i_1,  int id_i_2, String source, String relation, int startDay, int startMonth, int startYear, 
			int endDay, int endMonth, int endYear){
		
		INDIV_INDIV iiUp = new INDIV_INDIV();
		
		iiUp.setId_I_1(id_i_1);
		iiUp.setId_I_2(id_i_2);
		iiUp.setRelation(relation.length() <= 50 ? relation : relation.substring(0, 50));
		iiUp.setId_D((new Integer(IDNR).toString()));
		iiUp.setSource("HSN PR " + source);

		
		if(startYear != 0){
			
			iiUp.setDate_type("Event");
			iiUp.setEstimation("Period");
			iiUp.setStart_day(startDay);
			iiUp.setStart_month(startMonth);
			iiUp.setStart_year(startYear);
			iiUp.setEnd_day(endDay);
			iiUp.setEnd_month(endMonth);
			iiUp.setEnd_year(endYear);
			
		}
		
		em.persist(iiUp);
		
		
		
	}
	
	
	public static void addIndivContextAndContext(String address, ContextElement ce, EntityManager em, int IDNR, int Id_I, String source, String relation, int day, int month, int year){

		String [] s = Utils.getLocationHierarchy(ce);
		String [] t = Utils.splitAddress(address);
		int Id_C = Contxt.add(s[0], s[1], s[2], null, t[0], t[1], t[2], t[3], null);
		
		INDIV_CONTEXT ic = new INDIV_CONTEXT();
		ic.setId_D((new Integer(IDNR).toString()));
		ic.setId_I(Id_I);
		ic.setId_C(Id_C);
		ic.setSource("HSN PR " + source);

		
		ic.setRelation(relation);
		
		ic.setDate_type("Event");
		ic.setEstimation("Exact");
		ic.setDay(day);
		ic.setMonth(month);
		ic.setYear(year);

		em.persist(ic);
		
	}
	
	public static void addIndivContextAndContext(String address, ContextElement ce, EntityManager em, int IDNR, int Id_I, String source, String relation, 
			int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear){
		

		String [] s = Utils.getLocationHierarchy(ce);
		String [] t = Utils.splitAddress(address);
		int Id_C = Contxt.add(s[0], s[1], s[2], null, t[0], t[1], t[2], t[3], null);
		
		INDIV_CONTEXT ic = new INDIV_CONTEXT();
		ic.setId_D((new Integer(IDNR).toString()));
		ic.setId_I(Id_I);
		ic.setId_C(Id_C);
		ic.setSource("HSN PR " + source);

		
		ic.setRelation(relation);
		
		ic.setDate_type("Period");
		ic.setEstimation("Exact");
		ic.setStart_day(startDay);
		ic.setStart_month(startMonth);
		ic.setStart_year(startYear);
		ic.setEnd_day(endDay);
		ic.setEnd_month(endMonth);
		ic.setEnd_year(endYear);

		em.persist(ic);
		
	}
	
	public static void addIndivAndContext(String address, ContextElement ce, EntityManager em, int IDNR, int Id_I, String source, String type, int day, int month, int year){

		String [] s = Utils.getLocationHierarchy(ce);
		String [] t = Utils.splitAddress(address);
		int Id_C = Contxt.add(s[0], s[1], s[2], null, t[0], t[1], t[2], t[3], null);

		INDIVIDUAL i = new INDIVIDUAL();

		i.setId_I(Id_I);
		i.setId_D((new Integer(IDNR).toString()));
		i.setSource("HSN PR " + source);

		i.setType(type);
		i.setValue(null);
		
		i.setId_C(Id_C);
		
		i.setDate_type("Event");
		i.setEstimation("Exact");
		i.setDay(day);
		i.setMonth(month);
		i.setYear(year);

		em.persist(i);



		
	}
		
	
	
	public static void addIndiv(EntityManager em, int IDNR, int Id_I, String source, String type, String value){
		
		INDIVIDUAL i = new INDIVIDUAL();
		
		i.setId_I(Id_I);
		i.setId_D((new Integer(IDNR).toString()));
		i.setSource("HSN PR " + source);

		
		i.setType(type);
		i.setValue(value);
		
		i.setDate_type("Reported");
		i.setEstimation("Exact");
		i.setMissing("Time_invariant");
		
		em.persist(i);
		
		
	}

	public static void addIndiv(EntityManager em, int IDNR, int Id_I, String source, String type, String value, int day, int month, int year){
		
		INDIVIDUAL i = new INDIVIDUAL();
		
		i.setId_I(Id_I);
		i.setId_D((new Integer(IDNR).toString()));
		i.setSource("HSN PR " + source);

		
		i.setType(type);
		i.setValue(value);
		
		i.setDate_type("Event");
		i.setEstimation("Exact");
		i.setDay(day);
		i.setMonth(month);
		i.setYear(year);
		
		em.persist(i);
		
		
	}

	public static void addIndiv(EntityManager em, int IDNR, int Id_I, String source, String type, String value, String estimation, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear){
		
		INDIVIDUAL i = new INDIVIDUAL();
		
		i.setId_I(Id_I);
		i.setId_D((new Integer(IDNR).toString()));
		i.setSource("HSN PR " + source);

		
		i.setType(type);
		i.setValue(value);
		
		i.setDate_type("Period");
		if(estimation != null)
			i.setEstimation(estimation);
		else	
			i.setEstimation("Exact");
		i.setStart_day(startDay);
		i.setStart_month(startMonth);
		i.setStart_year(startYear);
		i.setEnd_day(endDay);
		i.setEnd_month(endMonth);
		i.setEnd_year(endYear);
		
		em.persist(i);
		
		
	}

	/**
	 * 
	 * This routine gets the context element of a Municipality 
	 * It returns an array (length = 3) with either:
	 *   
	 *   Country - Province - Municipality or
	 *   Country - null     - Municipality 
	 * 
	 * 
	 * 
	 * @param ce
	 * @return
	 */

	public static String [] getLocationHierarchy(ContextElement ce){
		
		 String[] s = new String[3];
		 int j = 0;
		 while(ce != null){
			 for(int i = 0; i < ce.getTypes().size(); i++)
				 if(ce.getTypes().get(i).equals("NAME")){    				 
					 s[2-j++] = ce.getValues().get(i);
					 break;
				 }
			 ce = ce.getParent();
		 }
		 
		 if(s[0] == null){ // this means that there was only 1 level above ce (Country), not 2 (Country and Province), so we correct
			 s[0] = s[1];
			 s[1] = null;
		 }

		 return s;
		
	}
	
	/*
	 *  Input: address  
	 * 
	 *  Output: String array:
	 *  				Quarter
	 *                  Street
	 *                  Number
	 *                  Addition
	 * 
	 */
	
	public static String[] splitAddress(String address){
		
		String [] r =  new String[4];  // return array
		
		if(address == null){
			r[0] = null;
			r[1] = null;
			r[2] = null;
			r[3] = null;
			
			return r;
		}
			
		
		String [] t = getQuarter(address);
		r[0] = t[0];  // copy Quarter
		String [] s = getNumber(t[1]);
		r[1] = s[0]; // Street
		r[2] = s[1]; // Number
		r[3] = s[2]; // Addition
		
		return r;
	}
	
	
	public static  String[] getQuarter(String s){
		
		//System.out.println("s = " + s);
		String [] sa = s.split(" ");
		String [] r =  new String[2]; // return arry
		
		for(int i = 0; i < sa.length -1; i++){
			if(sa[i].equalsIgnoreCase("WIJK") || sa[i].equalsIgnoreCase("WK")){
				
				r[0] = sa[i + 1]; // Quarter
				
				String t = "";
				
				for(int j = 0; j < sa.length; j++){
					if(j != i && j != i + 1)
						t = t + " " + sa[j].trim();
					
				}
				
				r[1] = t;
				return r;
				
				
			}
			else
				if(sa[i].length() <= 2 && sa[i].length() > 0 && Character.isUpperCase(sa[i].charAt(0))){
					
					r[0] = sa[i];

					String t = "";				
					for(int j = 0; j < sa.length; j++){
						if(j != i)
							t = t + " " + sa[j].trim();
						
					}
					
					r[1] = t;
					return r;

				}
		}
		
		r[0] = null;
		r[1] = s;

		return r;
		
	}
	

	public static  String[] getNumber(String s){
	
		//System.out.println("s = " + s);
		
		String [] sa = s.split("[ ,]");
		sa[sa.length-1].trim();
		
		String nr = null;
		String addition = null;
		
		
		for(int i = 0; i < sa.length; i++){
			
			//System.out.println(sa[i] + "  " + sa[i].length());
			
			switch(sa[i].length()){
			
			case 1:
				
				if(Character.isDigit(sa[i].charAt(0)))
					nr = sa[i];

				break;
				
			case 2:
				
				if(Character.isDigit(sa[i].charAt(0))){
					if(Character.isDigit(sa[i].charAt(1))){
						nr = sa[i];					

					}
					else{
						nr = sa[i].substring(0,1);
						addition = sa[i].substring(1,2);
						
					}
				}
				break;

			case 3:
				
				if(Character.isDigit(sa[i].charAt(0))){
					if(Character.isDigit(sa[i].charAt(1))){
						if(Character.isDigit(sa[i].charAt(2))){
							nr = sa[i];
						}
						else{
							nr = sa[i].substring(0,2);
							addition = sa[i].substring(2,3);
						}
					}
				}
				break;
				
			case 4:
				
				//System.out.println(sa[i]);

				if(Character.isDigit(sa[i].charAt(0))){
					if(Character.isDigit(sa[i].charAt(1))){
						if(Character.isDigit(sa[i].charAt(2))){
							if(Character.isDigit(sa[i].charAt(3))){
							nr = sa[i];
							}
						}
						else{
							nr = sa[i].substring(0,3);
							addition = sa[i].substring(3,4);
						}
					}
				}
				break;	
			
			
			}
			

			if(nr != null){

				String t = "";

				if(i > 0 && (sa[i - 1].equalsIgnoreCase("NR") || sa[i - 1].equalsIgnoreCase("NO"))){
					for(int j = 0; j < sa.length; j++){
						if(j != i && j != i - 1)
							t = t + " " + sa[j].trim();
							
					}
				}
				else{
					for(int j = 0; j < sa.length; j++){
						if(j != i)
							t = t + " " + sa[j].trim();
							
					}
					
					
				}
				
				String [] r =  new String[3];
				
				r[0] = t;
				r[1] = nr;
				r[2] = addition;
				
				return r;
				
				
			}
		}

		String [] r =  new String[3];
		
		r[0] = s;
		r[1] = null;
		r[2] = null;
		
		return r;

		
	}
	
	/**
	 * 
	 * Calculates the number of days since 01-01-1600
	 * 
	 * @param day1
	 * @param month1
	 * @param year1
	 * @return
	 */
	 public static int dayCount(int day1, int month1, int year1){

		 //System.out.println("" + day1+ month1 + year1);

		 int [] monthLength = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

		 int day = 1;
		 int month = 1;
		 int year = 1600;

		 int dayCount = 0;

		 if(year1 <= 1600)
			 return -1;
		 else{
			 if(month1 <= 0)
				 month1 = 1;
			 if(day1 <= 0)
				 day1 = 1;
		 }
			 

		 if(year1 > 1800){

			 int decade = (year1 - 1800)/10;

			 
			 // to speed things up
			 switch(decade){				
			 case 0:
				 dayCount = 73049;
				 year = 1800;
				 break;				    
			 case 1:
				 dayCount = 76701;
				 year = 1810;
				 break;				    
			 case 2:
				 dayCount = 80353;
				 year = 1820;
				 break;				    
			 case 3:
				 dayCount = 84006;
				 year = 1830;
				 break;				    
			 case 4:
				 dayCount = 87658;
				 year = 1840;
				 break;				    
			 case 5:
				 dayCount = 91311;
				 year = 1850;
				 break;				    
			 case 6:
				 dayCount = 94963;
				 year = 1860;
				 break;				    
			 case 7:
				 dayCount = 98616;
				 year = 1870;
				 break;				    
			 case 8:
				 dayCount = 102268;
				 year = 1880;
				 break;				    
			 case 9:
				 dayCount = 105921;
				 year = 1890;
				 break;				    
			 case 10:
				 dayCount = 109573;
				 year = 1900;
				 break;				    
			 case 11:
				 dayCount = 113225;
				 year = 1910;
				 break;				    
			 case 12:
				 dayCount = 116877;
				 year = 1920;
				 break;			    
			 case 13:
				 dayCount = 120530;
				 year = 1930;
				 break;				    
			 case 14:
				 dayCount = 124182;
				 year = 1940;
				 break;				    
			 case 15:
				 dayCount = 127835;
				 year = 1950;
				 break;				    
			 default:

			 }
		 }


		 while(day != day1 || month != month1 || year != year1){

			 //System.out.println("" + day + " " + month + " " + year);
			 if(day < monthLength[month])
				 day++;
			 else{
				 if(month == 2 && day == monthLength[2] && year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))
					 day++;		  
				 else{
					 day = 1;
					 if(month < 12){
						 month++;

					 }
					 else{
						 month = 1;
						 year++;
					 }
				 }
			 }

			 dayCount++;

		 }

		 return(dayCount);
	 }

	/**
	 * 
	 * Calculates the number of day since 01-01-1600
	 * 
	 * @param date0: dd-mm-yyyy
	 * @return
	 */
	
	public static int dayCount(String date0){
		
		//System.out.println(date0);
			
		int d = (new Integer(date0.substring(0,2))).intValue();
		int m = (new Integer(date0.substring(3,5))).intValue();
		int y = (new Integer(date0.substring(6,10))).intValue();
		
		if(d > 0 && m > 0 && y > 0)
			return(dayCount(d, m, y));
		else
		    return(-1);		
		
		
	}

	/**
	 * 
	 * This routine calculates the date if count contains the number of days 
	 * since 01-01-1600.
	 * It is the inverse of dayCount
	 * 
	 * 
	 * @param count1
	 * @return
	 */
	
    public static String dateFromDayCount(int count1){
    	
    	int [] monthLength = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    	
    	int day = 1;
    	int month = 1;
    	int year = 1600;
    	
    	int count = count1;
    	
    	if(count <= 0)
    		count = 1/0; // force exception;
    	
    	
    	while(count > 0){
    		
    		if(day < monthLength[month])
				day++;
			else{
			  if(month == 2 && day == monthLength[2] && year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))
			      day++;		  
			  else{
				  day = 1;
				  if(month < 12){
					  month++;
					  
				  }
				  else{
					  month = 1;
					  year++;
				  }
			  }
			}
			count--;
    	}
    	//System.out.println(String.format("%02d-%02d-%04d    %d", day, month, year, count1));
        return(String.format("%02d-%02d-%04d", day, month, year));  
    	
    }
	
    public static String sex(String sex){
    	
    	if(sex.trim().toUpperCase().equals("M"))
    		return "Male";
    	if(sex.trim().toUpperCase().equals("V"))
    		return "Female";
   		return "Unknown";
    	
    }

    
    
    
/**
 * 
 * This routine inspects an array of candidates and
 * returns the last most frequently occurring one.
 * It also returns a flag indicating:
 * 
 *  1 - All candidates identical (Identical value is returned)
 *  2 - Unique most frequent variant returned
 *  3 - Last most frequent variant returned (because there were more than 1) 
 *  
 *  Last, if there is an array of cadidateFlags specified, the flag corresponding to the
 *  most frequently cadidate is returned as a 3rd parameter
 * 
 * @param cadidates
 * @return
 */
    public static String [] findLastMostCommonValue(ArrayList<String> candidates, ArrayList<String> candidatesFlags){
    	
    	//System.out.println("Candidates: " + candidates);
    	
    	String [] ret = new String[3];
    	
    	if(candidates == null || candidates.size() == 0)
    		return ret;
    	
    	ret[0] = candidates.get(0); // preset all identical hence equal to first one
    	ret[1] = "1";               // preset all identical
    	
    	ArrayList<String>  uniqueCandidates         = new ArrayList<String>();
    	ArrayList<String>  uniqueCandidatesFlags    = new ArrayList<String>();
    	ArrayList<Integer> uniqueCandidateFrequency = new ArrayList<Integer>();
    	
    	// create list of unique candidates and their frequency
    	
    	for(int j = 0; j <  candidates.size(); j++){
    		
    		boolean found = false;
    		
    		int index = -1;
    		for(int i = 0; i < uniqueCandidates.size(); i++){
    			if(candidates.get(j).equalsIgnoreCase(uniqueCandidates.get(i))){
    				index = i;
    				break;
    			}
    		}
    		
    		if(index >= 0)    			
    			uniqueCandidateFrequency.set(index, uniqueCandidateFrequency.get(index) + 1);
    		else{
    			uniqueCandidates.add(candidates.get(j));
    			if(candidatesFlags != null)
    				uniqueCandidatesFlags.add(candidatesFlags.get(j)); 
    			uniqueCandidateFrequency.add(1);
    			
    		}
    	}
    	
    	if(uniqueCandidates.size() == 1){
    		ret[0] = uniqueCandidates.get(0);
    		ret[1] = "1";
    		if(candidatesFlags != null)
    			ret[2] = candidatesFlags.get(0);
    		return ret;
    	}
    	
    	
    	
    	// find the unique candidate with the highest (unique) frequency
    	
    	int highest = -1;
    	boolean unique = true;
    	String retValue = "";
    	String retValue2 = "";
    	for(int i = 0; i <  uniqueCandidates.size(); i++){
    		if(uniqueCandidateFrequency.get(i) >= highest){
        		if(uniqueCandidateFrequency.get(i) > highest)
        			unique = true;
        		else
        			unique = false;
    			retValue = uniqueCandidates.get(i);
    			if(candidatesFlags != null)
    				retValue2 = uniqueCandidatesFlags.get(i);
    			highest = uniqueCandidateFrequency.get(i);
    		}
    	}
    	
    	ret[0] = retValue;
    	if(unique == true)
    		ret[1] = "2";
    	else
    		ret[1] = "3";

		if(candidatesFlags != null)
			ret[2] = retValue2;

    	
    	return ret;
    	
    }

}
