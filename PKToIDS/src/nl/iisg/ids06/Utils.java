package nl.iisg.ids06;

import javax.persistence.EntityManager;

import nl.iisg.idscontext.ContextElement;
import nl.iisg.idscontext.Contxt;

public class Utils {
	
	public static void addIndivIndiv(EntityManager em, int IDNR, int id_i_1,  int id_i_2, String source, String relation, 
			String dateType, String estimation, int day, int month, int year){
		
		INDIV_INDIV iiUp = new INDIV_INDIV();
		
		iiUp.setId_I_1(id_i_1);
		iiUp.setId_I_2(id_i_2);
		iiUp.setRelation(relation.length() <= 50 ? relation : relation.substring(0, 50));
		iiUp.setId_D((new Integer(IDNR).toString()));
		iiUp.setSource("HSN PC " + source);

		
		if(year != 0){
			
			iiUp.setDate_type(dateType);
			iiUp.setEstimation(estimation);
			iiUp.setDay(day);
			iiUp.setMonth(month);
			iiUp.setYear(year);			
			
		}
		
		else
			iiUp.setMissing("Time Invariant");
		
		em.persist(iiUp);
		
		
		
	}
	
	public static void addIndivIndiv(EntityManager em, int IDNR, int id_i_1,  int id_i_2, String source, String relation, 
			String dateType, String estimation, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear){
		
		INDIV_INDIV iiUp = new INDIV_INDIV();
		
		iiUp.setId_I_1(id_i_1);
		iiUp.setId_I_2(id_i_2);
		iiUp.setRelation(relation.length() <= 50 ? relation : relation.substring(0, 50));
		iiUp.setId_D((new Integer(IDNR).toString()));
		iiUp.setSource("HSN PC " + source);

		
		if(startYear != 0){
			
			iiUp.setDate_type(dateType);
			iiUp.setEstimation(estimation);
			iiUp.setStart_day(startDay);
			iiUp.setStart_month(startMonth);
			iiUp.setStart_year(startYear);
			iiUp.setEnd_day(endDay);
			iiUp.setEnd_month(endMonth);
			iiUp.setEnd_year(endYear);
			
		}
		else
			iiUp.setMissing("Time Invariant");
		
		em.persist(iiUp);
		
		
		
	}
	
	public static void addIndivContextAndContext(String boat, String quarter, String street, String number, String addition, ContextElement ce, EntityManager em, int IDNR, int Id_I, String source, String relation, 
			String dateType, String estimation, int day, int month, int year){

		
		ContextElement context1 = null;

		
		if(boat != null && boat.trim().length() > 0)
			context1 = Contxt.locateBoat(boat, street, number, addition, ce, "Address");
		else
			if(street != null && street.trim().length() > 0)
				context1 = Contxt.locateAddress(street, number, addition, ce, "Address");
			else
				if(quarter != null && quarter.trim().length() > 0)
					context1 = Contxt.locateQuarter(quarter, number, addition, ce, "Address");

		
		if(context1 == null)
			return;
		
		int Id_C = context1.getId_C();
		
		
		INDIV_CONTEXT ic = new INDIV_CONTEXT();
		ic.setId_D((new Integer(IDNR).toString()));
		ic.setId_I(Id_I);
		ic.setId_C(Id_C);
		ic.setSource("HSN PC " + source);

		
		ic.setRelation(relation);
		
		ic.setDate_type(dateType);
		ic.setEstimation(estimation);
		ic.setDay(day);
		ic.setMonth(month);
		ic.setYear(year);
		
		if(year == 0)
			ic.setMissing("Time Invariant");


		em.persist(ic);
		
	}
	
	public static void addIndivContextAndContext(String boat, String quarter, String street, String number, String addition, ContextElement ce, EntityManager em, int IDNR, int Id_I, String source, String relation, 
			String dateType, String estimation, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear){
		
		//System.out.println("In addIndivContextAndContext");
		
		ContextElement context1 = null;

		//String [] s = Utils.getLocationHierarchy(ce);
		if(boat != null && boat.trim().length() > 0)
			context1 = Contxt.locateBoat(boat, street, number, addition, ce, "Address");
		else
			if(street != null && street.trim().length() > 0)
				context1 = Contxt.locateAddress(street, number, addition, ce, "Address");
			else
				if(quarter != null && quarter.trim().length() > 0)
					context1 = Contxt.locateQuarter(quarter, number, addition, ce, "Address");

		
		if(context1 == null)
			return;
		
		int Id_C = context1.getId_C();
		

		
		INDIV_CONTEXT ic = new INDIV_CONTEXT();
		ic.setId_D((new Integer(IDNR).toString()));
		ic.setId_I(Id_I);
		ic.setId_C(Id_C);
		ic.setSource("HSN PC " + source);

		
		ic.setRelation(relation);
		
		ic.setDate_type(dateType);
		ic.setEstimation(estimation);
		ic.setStart_day(startDay);
		ic.setStart_month(startMonth);
		ic.setStart_year(startYear);
		ic.setEnd_day(endDay);
		ic.setEnd_month(endMonth);
		ic.setEnd_year(endYear);

		if(startYear == 0)
			ic.setMissing("Time Invariant");

		
		em.persist(ic);
		
	}
	
	public static void addIndivAndContext(String quarter, String street, String number, String addition, ContextElement ce, EntityManager em, int IDNR, int Id_I, String source, String type, 
			String dateType, String estimation, int day, int month, int year){

		
		//System.out.println("addIndivAndContext 1 " + quarter + "  "+ street);
		
		//String [] s = Utils.getLocationHierarchy(ce);
		//int Id_C = Contxt.add(s[0], s[1], s[2], null, quarter, street, number, addition, null);
		
		ContextElement context1 = null;
		if(street != null && street.trim().length() > 0)
			context1 = Contxt.locateAddress(street, number, addition, ce, "Address");
		else
			if(quarter != null && quarter.trim().length() > 0)
				context1 = Contxt.locateQuarter(quarter, number, addition, ce, "Quarter");

		//System.out.println("addIndivAndContext 2 " + context1 );

		int Id_C = 0;
		
		if(context1 != null)
			Id_C = context1.getId_C();
		else
			Id_C = ce.getId_C();
		
		
		
		//System.out.println("addIndivAndContext 3 " + Id_C);

		
		INDIVIDUAL i = new INDIVIDUAL();

		i.setId_I(Id_I);
		i.setId_D((new Integer(IDNR).toString()));
		i.setSource("HSN PC " + source);

		i.setType(type);
		i.setValue(null);
		
		i.setId_C(Id_C);
		
		i.setDate_type(dateType);
		i.setEstimation(estimation);
		i.setDay(day);
		i.setMonth(month);
		i.setYear(year);

		if(year == 0)
			i.setMissing("Time Invariant");

		
		em.persist(i);

		
	}
		
	
	
	public static void addIndiv(EntityManager em, int IDNR, int Id_I, String source, String type, String value, 
			String dateType, String estimation, int day, int month, int year){
		
		//System.out.println("Cr add individual");
		INDIVIDUAL i = new INDIVIDUAL();
		
		i.setId_I(Id_I);
		i.setId_D((new Integer(IDNR).toString()));
		i.setSource("HSN PC " + source);

		
		i.setType(type);
		i.setValue(value);
		
		i.setDate_type(dateType);
		i.setEstimation(estimation);
		i.setDay(day);
		i.setMonth(month);
		i.setYear(year);

		if(year == 0)
			i.setMissing("Time Invariant");
		
		em.persist(i);
		
		
	}

	public static void addIndiv(EntityManager em, int IDNR, int Id_I, String source, String type, String value, 
			String dateType, String estimation, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear){
		
		//System.out.println("Cr add individual");

		INDIVIDUAL i = new INDIVIDUAL();
		
		i.setId_I(Id_I);
		i.setId_D((new Integer(IDNR).toString()));
		i.setSource("HSN PC " + source);

		
		i.setType(type);
		i.setValue(value);
		
		i.setDate_type(dateType);
		i.setEstimation(estimation);
		i.setStart_day(startDay);
		i.setStart_month(startMonth);
		i.setStart_year(startYear);
		i.setEnd_day(endDay);
		i.setEnd_month(endMonth);
		i.setEnd_year(endYear);

		if(startYear == 0)
			i.setMissing("Time Invariant");

		
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
		
		//System.out.println("In getLocationHierarchy, ce = " + ce);
		
		 String[] s = new String[3];
		 int j = 0;
		 while(ce != null){
			 for(int i = 0; i < ce.getTypes().size(); i++)
				 if(ce.getTypes().get(i).equals("NAME")){
					 //System.out.println("B");
					 s[2-j++] = ce.getValues().get(i);
					 //System.out.println(ce.getValues().get(i));
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
		
		//System.out.println("Address = " + address + " --->  Quarter = " + r[0] + " Street = " + s[0] + " Number = " + s[1] + " Addition = " + s[2]);
		
		return r;
	}
	
	
	public static  String[] getQuarter(String s){
		
		//System.out.println("s = " + s);
		String [] sa = s.split(" ");
		String [] r =  new String[2]; // return array
		
		for(int i = 0; i < sa.length; i++){
			sa[i] = sa[i].trim();
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
			
			sa[i] = sa[i].trim();
			
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
				
				//System.out.println("Case 4: " + sa[i]);

				if(Character.isDigit(sa[i].charAt(0))){
					if(Character.isDigit(sa[i].charAt(1))){
						if(Character.isDigit(sa[i].charAt(2))){
							if(Character.isDigit(sa[i].charAt(3))){
								nr = sa[i];
							}

							else{
								nr = sa[i].substring(0,3);
								addition = sa[i].substring(3,4);
							}
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
				 dayCount = 127835;
				 year = 1950;
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

		 //System.out.println("Daycount = " + dayCount);
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
	 * Checks if a date is a valid date
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @return -1: One ore more elements are <= 0
	 *          0: Valid date
	 *          1: Invalid date 
	 * 
	 * 
	 */
	
	public static int dateIsValid(int day, int month, int year){
		
		int [] monthLength = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		if(year <= 0 || month <= 0 || day <= 0) return -1;
		
		if(!(year  > 1750))   return 1;
		if(!(month > 0    && month <= 12))     return 1;
		if(!(day   > 0    && (day  <=  monthLength[month] || (day <= monthLength[2] + 1 && month == 2 && year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))))) return 1;
		
        return 0;		
		
	}
	




}
