package nl.iisg.ids05;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import nl.iisg.hsncommon.Common1;
import nl.iisg.hsncommon.ConstRelations2;
import nl.iisg.idscontext.ContextElement;
import nl.iisg.idscontext.Contxt;
import nl.iisg.ref.Ref;
import nl.iisg.ref.Ref_Relation_B;

public class Utils {
	
	static int Id_I;

	public static int getId_I() {
		return Id_I++;
	}

	public static void setId_I(int id_I) {
		Id_I = id_I;
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
	 * 
	 * 
	 * 
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
		String [] sa = s.split("[ ]+");
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
		
		String [] sa = s.split("[ ,]+");
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
	
	
	public static void addIndivIndiv(EntityManager em, int IDNR, int id_i_1,  int id_i_2, String source, String relation, 
			String dateType, String estimation, int day, int month, int year){
		
		if(!dateType.equalsIgnoreCase("Missing") && Utils.dateIsValid(day, month, year) != 0){
			System.out.println("1 Source = "+ source + " IDNR = " + IDNR + ", Id_I = " + id_i_1 + "-" + id_i_2 + ",  date Type = " + dateType + " invalid date: " +  day + " " + month+ " "+year);
			return;
			//startDay = startDay/0;
		}

		
		INDIV_INDIV iiUp = new INDIV_INDIV();
		
		iiUp.setId_I_1(id_i_1);
		iiUp.setId_I_2(id_i_2);
		iiUp.setRelation(relation.length() <= 100 ? relation : relation.substring(0, 100));
		iiUp.setId_D((new Integer(IDNR).toString()));
		iiUp.setSource("HSN " + source);

		if(dateType.equalsIgnoreCase("Missing"))
			iiUp.setMissing(estimation);
		else
		{
			iiUp.setDate_type(dateType);
			iiUp.setEstimation(estimation);
			iiUp.setDay(day);
			iiUp.setMonth(month);
			iiUp.setYear(year);			
		}
		
		em.persist(iiUp);
		
		String [] a = relation.split("[ ]+");

		
		// If there is the word " AND " in the relation, it is automatically split and made reciprocal 
		
		int index = -1;
		for(int i = 0; i < a.length; i++){
			if(a[i].equalsIgnoreCase(" AND ")){
				index = i;
				break;
			}
		}
			
		if(index > 0){
			
			INDIV_INDIV iiDown = new INDIV_INDIV();
			iiDown.setId_I_1(id_i_2);
			iiDown.setId_I_2(id_i_1);
			
			String r = "";
			for(int i = index + 1; i < a.length; i++){
				r += a[i] + " ";
			}
			r += "and ";
			for(int i = 0; i < index; i++)
				r += a[i] + " ";
			r = r.trim();

			iiDown.setRelation(r.length() <= 100 ? r : r.substring(0, 100));
			iiDown.setSource("HSN " + source);
			iiDown.setId_D((new Integer(IDNR).toString()));

			if(dateType.equalsIgnoreCase("Missing"))
				iiDown.setMissing(estimation);
			else{
				iiDown.setDate_type(dateType);
				iiDown.setEstimation(estimation);
				iiDown.setDay(day);
				iiDown.setMonth(month);
				iiDown.setYear(year);			
			}
			
			em.persist(iiDown);
			
		}
		
		
		
	}
	
	
	public static void addIndivContextAndContextCertificate(int yearCertificate, int sequenceNumberCertificate, ContextElement ceCertificate, EntityManager em, int IDNR, int Id_I, String source, String source2, String relation, 
			String dateType, String estimation, int day, int month, int year){

		//String [] s = Utils.getLocationHierarchy(ceCertificate);
		//int Id_C = Contxt.addCertificate(s[0], s[1], s[2], source, yearCertificate, sequenceNumberCertificate);
		
		//if(1==1) return; // TRM
		
		//System.out.println("Idnr = " + " source = " + source + " source2 = " + source2 + " relation = " + relation);
		
		if(!dateType.equalsIgnoreCase("Missing") && Utils.dateIsValid(day, month, year) != 0){
			System.out.println("2 Source = "+ source2 + " IDNR = " + IDNR + ", Id_I = " + Id_I + ",  date Type = " + dateType + " invalid date: " +  day + " " + month+ " "+year);
			return;
			//startDay = startDay/0;
		}

		
		ContextElement ce = Contxt.locateCertificate(source, yearCertificate, sequenceNumberCertificate,  ceCertificate,  "Source");
		int Id_C = ce.getId_C();
		INDIV_CONTEXT ic = new INDIV_CONTEXT();
		ic.setId_D((new Integer(IDNR).toString()));
		ic.setId_I(Id_I);
		ic.setId_C(Id_C);
		ic.setSource("HSN " + source2);

		
		ic.setRelation(relation);
		
		ic.setDate_type(dateType);
		ic.setEstimation(estimation);
		ic.setDay(day);
		ic.setMonth(month);
		ic.setYear(year);

		em.persist(ic);
		
	}
	
	
	public static void addIndivContextAndContext(String address, ContextElement ceCertificate, EntityManager em, int IDNR, int Id_I, String source, String relation, 
			String dateType, String estimation, int day, int month, int year){
		
		if(!dateType.equalsIgnoreCase("Missing") && Utils.dateIsValid(day, month, year) != 0){
			System.out.println("3 Source = "+ source + " IDNR = " + IDNR + ", Id_I = " + Id_I + ",  date Type = " + dateType + " invalid date: " +  day + " " + month+ " "+year);
			return;
			//startDay = startDay/0;
		}


		
		//if(1==1) return;
		String [] s = Utils.getLocationHierarchy(ceCertificate);
		String[] t = {null, null, null, null};
		if(address != null)
		    t = Utils.splitAddress(address);
		int Id_C = Contxt.add(s[0], s[1], s[2], null, t[0], t[1], t[2], t[3], null);
		

		
		INDIV_CONTEXT ic = new INDIV_CONTEXT();
		ic.setId_D((new Integer(IDNR).toString()));
		ic.setId_I(Id_I);
		ic.setId_C(Id_C);
		ic.setSource("HSN " + source);

		
		ic.setRelation(relation);

		
		if(dateType.equalsIgnoreCase("Missing"))
			ic.setMissing(estimation);
		else{	
			ic.setDate_type(dateType);
			ic.setEstimation(estimation);
			ic.setDay(day);
			ic.setMonth(month);
			ic.setYear(year);
		}
		em.persist(ic);
		
	}
	
	
	// New
	public static void addIndivContextAndContext(String quarter, String street, String number, String addition, ContextElement ce, EntityManager em, int IDNR, int Id_I, String source, String relation, 
			String dateType, String estimation, int day, int month, int year){
		
		if(!dateType.equalsIgnoreCase("Missing") && Utils.dateIsValid(day, month, year) != 0){
			System.out.println("4 Source = "+ source + " IDNR = " + IDNR + ", Id_I = " + Id_I + ",  date Type = " + dateType + " invalid date: " +  day + " " + month+ " "+year);
			return;
			//startDay = startDay/0;
		}


		if(ce == null) return;

		
		ContextElement context1 = ce;
		ContextElement context2 = null;

		if(quarter != null && quarter.trim().length() > 0)
			context2 = Contxt.locateQuarter(quarter, context1, "Quarter");

		context1 = (context2 !=  null ? context2 : context1);
		
		if(street != null && street.trim().length() > 0)
			context2 = Contxt.locateStreet(street, number, addition, context1, "Address");
		
		context1 = (context2 !=  null ? context2 : context1);
		
		
		//if(context1 == null)
		//	return;
		
		int Id_C = context1.getId_C();
		
		
		INDIV_CONTEXT ic = new INDIV_CONTEXT();
		ic.setId_D((new Integer(IDNR).toString()));
		ic.setId_I(Id_I);
		ic.setId_C(Id_C);
		ic.setSource("HSN " + source);

		
		ic.setRelation(relation);
		
		ic.setDate_type(dateType);
		ic.setEstimation(estimation);
		ic.setDay(day);
		ic.setMonth(month);
		ic.setYear(year);
		
		if(year == 0)
			ic.setMissing("Time_invariant");


		em.persist(ic);
		
	}
	
	// new
	public static void addIndivAndContext(String quarter, String street, String number, String addition, ContextElement ce, EntityManager em, int IDNR, int Id_I, String source, String type, 
			String dateType, String estimation, int day, int month, int year){

		if(!dateType.equalsIgnoreCase("Missing") && Utils.dateIsValid(day, month, year) != 0){
			System.out.println("5 Source = "+ source + " IDNR = " + IDNR + ", Id_I = " + Id_I + ", type = " + type  + ",  date Type = " + dateType + " invalid date: " +  day + " " + month+ " "+year);
			return;
			//startDay = startDay/0;
		}

		//System.out.println("ce = " + ce);
		
		if(ce == null) return;
		
		ContextElement context1 = ce;
		ContextElement context2 = null;

		if(quarter != null && quarter.trim().length() > 0)
			context2 = Contxt.locateQuarter(quarter, context1, "Quarter");

		context1 = (context2 !=  null ? context2 : context1);
		
		if(street != null && street.trim().length() > 0)
			context2 = Contxt.locateStreet(street, number, addition, context1, "Address");
		
		context1 = (context2 !=  null ? context2 : context1);
		
		//if(boat != null && boat.trim().length() > 0)
		//	context2 = Contxt.locateBoat(boat, street, number, addition, context1, "Address");
		
		context1 = (context2 !=  null ? context2 : context1);	
		
		int Id_C = context1.getId_C();
		
		
		
		//System.out.println("addIndivAndContext 3 " + Id_C);

		
		INDIVIDUAL i = new INDIVIDUAL();

		i.setId_I(Id_I);
		i.setId_D((new Integer(IDNR).toString()));
		i.setSource("HSN " + source);

		i.setType(type);
		i.setValue(null);
		
		i.setId_C(Id_C);
		
		i.setDate_type(dateType);
		i.setEstimation(estimation);
		i.setDay(day);
		i.setMonth(month);
		i.setYear(year);

		if(year == 0)
			i.setMissing("Time_invariant");

		
		em.persist(i);

		
	}
	
	
	public static void addIndivAndContext(String address, ContextElement ce, EntityManager em, int IDNR, int Id_I, String source, String type, 
			String dateType, String estimation, int day, int month, int year){
		
		if(!dateType.equalsIgnoreCase("Missing") && Utils.dateIsValid(day, month, year) != 0){
			System.out.println("6 Source = "+ source + " IDNR = " + IDNR + ", Id_I = " + Id_I + ", type = " + type +  ",  date Type = " + dateType + " invalid date: " +  day + " " + month+ " "+year);
			return;
			//startDay = startDay/0;
		}


		//if(1==1) return;

		String [] s = Utils.getLocationHierarchy(ce);
		String[] t = {null, null, null, null};
		if(address != null)
		    t = Utils.splitAddress(address);
		int Id_C = Contxt.add(s[0], s[1], s[2], null, t[0], t[1], t[2], t[3], null);		

		INDIVIDUAL i = new INDIVIDUAL();

		i.setId_I(Id_I);
		i.setId_D((new Integer(IDNR).toString()));
		i.setSource("HSN " + source);

		i.setType(type);
		i.setValue(null);
		
		i.setId_C(Id_C);
		

		if(dateType.equalsIgnoreCase("Missing"))
			i.setMissing(estimation);
		else{

			i.setDate_type(dateType);
			i.setEstimation(estimation);
			i.setDay(day);
			i.setMonth(month);
			i.setYear(year);
		}
		em.persist(i);



		
	}
		
	
	
	
	
	
	
	
	
	
	
	public static void addIndiv(EntityManager em, int IDNR, int Id_I, String source, String type, String value, 
			String dateType, String estimation, int day, int month, int year){
		
		if(!dateType.equalsIgnoreCase("Missing") && Utils.dateIsValid(day, month, year) != 0){
			System.out.println("7 Source = "+ source + " IDNR = " + IDNR + ", Id_I = " + Id_I + ", type = " + type + ", value = " + value + ",  date Type = " + dateType + " invalid date: " +  day + " " + month+ " "+year);
			return;
			//startDay = startDay/0;
		}

		
		int x = 0;
		if(Id_I == 0)
			 x = 1/0;
		
		INDIVIDUAL i = new INDIVIDUAL();
		
		i.setId_I(Id_I);
		i.setId_D((new Integer(IDNR).toString()));
		i.setSource("HSN " + source);

		
		i.setType(type);
		i.setValue(value);
		
		
		if(dateType.equalsIgnoreCase("Missing")){
			i.setMissing(estimation);
			
		}
		else{
			i.setDate_type(dateType);
			i.setEstimation(estimation);
			i.setDay(day);
			i.setMonth(month);
			i.setYear(year);
		}
		
		em.persist(i);
	}

	public static void addIndiv(EntityManager em, int IDNR, int Id_I, String source, String type, String value, 
			String dateType, String estimation, int day, int month, int year, int hour, int minute){
		
		if(!dateType.equalsIgnoreCase("Missing") && Utils.dateIsValid(day, month, year) != 0){
			System.out.println("8 Source = "+ source + " IDNR = " + IDNR + ", Id_I = " + Id_I + ", type = " + type + ", value = " + value + ",  date Type = " + dateType + " invalid date: " +  day + " " + month+ " "+year);
			return;
			//startDay = startDay/0;
		}



		int x = 0;
		if(Id_I == 0)
			 x = 1/0;

		
		INDIVIDUAL i = new INDIVIDUAL();
		
		i.setId_I(Id_I);
		i.setId_D((new Integer(IDNR).toString()));
		i.setSource("HSN " + source);

		
		i.setType(type);
		i.setValue(value);
		
		
		if(dateType.equalsIgnoreCase("Missing")){
			i.setMissing(estimation);
			
		}
		else{
			i.setDate_type(dateType);
			i.setEstimation(estimation);
			i.setHour(hour);
			i.setMinute(minute);
			i.setDay(day);
			i.setMonth(month);
			i.setYear(year);
		}
		
		em.persist(i);
	}

	public static void addIndiv(EntityManager em, int IDNR, int Id_I, String source, String type, String value, 
			String dateType, String estimation, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear){
		
		
		if(!dateType.equalsIgnoreCase("Missing") && Utils.dateIsValid(startDay, startMonth, startYear) != 0){
			System.out.println("9 Source = "+ source + " IDNR = " + IDNR + ", Id_I = " + Id_I + ", type = " + type + ", value = " + value + ",  date Type = " + dateType + " invalid date: " +  startDay + " " + startMonth+ " "+startYear);
			return;
			//startDay = startDay/0;
		}

		//System.out.println("dateType = " + dateType);
		INDIVIDUAL i = new INDIVIDUAL();
		
		i.setId_I(Id_I);
		i.setId_D((new Integer(IDNR).toString()));
		i.setSource("HSN " + source);

		
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
		
		em.persist(i);
		
		
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
			
		//if(!(year  > 1750 ))   return 1;
		if(!(month > 0    && month <= 12))     return 1;
		if(!(day   > 0    && (day  <=  monthLength[month] || (day <= monthLength[2] + 1 && month == 2 && year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))))) return 1;
			
        return 0;		
			
	}
		


	 public static String permission(String c){
		
		 if(c.equalsIgnoreCase("s"))
			 return "Written";
		 if(c.equalsIgnoreCase("m"))
			 return "Verbal";
		 if(c.equalsIgnoreCase("k"))
			 return "Notarial";
		 if(c.equalsIgnoreCase("a"))
			 return "Otherwise";
		 return "Not specified";
		 
		 
	 }

	 public static String signature(String c){
		 
		 if(c.equalsIgnoreCase("a"))
			 return "No";
		 if(c.equalsIgnoreCase("b"))
			 return "Sabbath";
		 if(c.equalsIgnoreCase("c"))
			 return "Unable";
		 if(c.equalsIgnoreCase("d"))
			 return "Unable";
		 if(c.equalsIgnoreCase("e"))
			 return "Unable";
		 if(c.equalsIgnoreCase("h"))
			 return "Yes";
		 if(c.equalsIgnoreCase("j"))
			 return "Yes";
		 if(c.equalsIgnoreCase("n"))
			 return "No";
		 if(c.equalsIgnoreCase("o"))
			 return "Unknown";
		 
		 return "No";
		 
	 }

	 public static String civilStatus(String c){

		 if(c.equalsIgnoreCase("1"))
			 return "Unmarried";
		 if(c.equalsIgnoreCase("2"))
			 return "Widowed";
		 if(c.equalsIgnoreCase("3"))
			 return "Divorced";
		 if(c.equalsIgnoreCase("4"))
			 return "Married";
		 if(c.equalsIgnoreCase("5"))
			 return "Married";
		 if(c.equalsIgnoreCase("6"))
			 return "Unknown";
		 if(c.equalsIgnoreCase("7"))
			 return "Unknown";
		 if(c.equalsIgnoreCase("8"))
			 return "Unmarried";
		 
		 return "Unknown";

		 
	 }
	 
	 /**
	  * 
	  * @param age
	  * @param day
	  * @param month
	  * @param year
	  * @return
	  * 
	  * We know the age at a certain date. We must calculate a range of possible birthdates
	  * 
	  */
	 public static int[] birthRange(int age, int day, int month, int year){

		 int[] a =  {0, 0, 0, 0, 0, 0};


		 if(Utils.dateIsValid(day, month, year) != 0)
			 return a;


		 int [] monthDays = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

		 // Earliest birthday: tomorrow - (age + 1)

		 int tomorrowDay = 0;
		 int tomorrowMonth = 0;
		 int tomorrowYear = 0;

		 if(day < monthDays[month] || (day == 29 && month == 2 && year % 4 == 0)){
			 tomorrowDay =  day + 1;
			 tomorrowMonth = month;
			 tomorrowYear = year;
		 }
		 else{
			 if(month < 12){
				 tomorrowDay = 1;
				 tomorrowMonth = month + 1;
				 tomorrowYear = year;
			 }
			 else{
				 tomorrowDay = 1;
				 tomorrowMonth = 1;
				 tomorrowYear = year + 1;
			 }
		 }

		 if(Utils.dateIsValid(tomorrowDay, tomorrowMonth, tomorrowYear) != 0){
			 tomorrowDay = tomorrowDay - 1; 
			 if(Utils.dateIsValid(tomorrowDay, tomorrowMonth, tomorrowYear) != 0)
				 return a;

		 }

		 a[0] = tomorrowDay;
		 a[1] = tomorrowMonth;
		 a[2] = tomorrowYear  - (age + 1);

		 if(Utils.dateIsValid(a[0], a[1], a[2]) != 0)
			 a[0] = a[0] - 1; // can only be 29-3 in a non-leap year so subtract 1


		 // Latest birthday: today - age

		 a[3] = day;
		 a[4] = month;
		 a[5] = year - age;

		 if(Utils.dateIsValid(a[3], a[4], a[5]) != 0)
			 a[3] = a[3] - 1; // can only be 29-3 in a non-leap year so subtract 1

		 return a;

	 }

	 public static int[] range(int minYears, int maxYears, int day, int month, int year){

		 int[] a =  {0, 0, 0, 0, 0, 0};
		 
		 int minYear  = year - minYears;
		 int minDay   = day;
		 int minMonth = month; 
		 
		// Correct for 29-02-xxxx
		 
		 if(month == 2 && day == 29 && !(minYear % 4 == 0 && (minYear % 100 != 0 || minYear % 400 == 0))){
			 minDay   = 1;
			 minMonth = 3;
		 }

		 a[0] = minDay;
		 a[1] = minMonth;
		 a[2] = minYear;
		 
		 int maxYear  = year - maxYears;
		 int maxDay   = day;
		 int maxMonth = month; 
		 
		// Correct for 29-02-xxxx
		 
		 if(month == 2 && day == 29 && !(maxYear % 4 == 0 && (maxYear % 100 != 0 || maxYear % 400 == 0))){
			 maxDay   = 1;
			 maxMonth = 3;
		 }

		 a[3] = maxDay;
		 a[4] = maxMonth;
		 a[5] = maxYear;
		 
		 return a;
	 }
	 
	 /**
	  * Only 1 can be > 0!
	  * @param ageDays
	  * @param ageWeeks
	  * @param ageMonths
	  * @param ageYears
	  * 
	  * 
	  * @param day
	  * @param month
	  * @param year
	  * @return
	  * 
	  * We know the age at a certain date. We must calculate a range of possible birthdates
	  * 
	  */
	 public static int[] birthRange(int ageDays, int ageWeeks, int ageMonths, int ageYears, int day, int month, int year){
		
		 System.out.println("---------------------");
		 System.out.println("ageDays = " + ageDays + ", ageWeeks = " + ageWeeks + ", ageMonths = "+ ageMonths + ", ageYears = " + ageYears);
		 System.out.println("Date = "+  day + "-" + month + "-" + year);
		 
		 if(Utils.dateIsValid(day, month, year) != 0)
			 return null;
		 
		 // We have to normalize the input, because sometimes 0 is used instead of -1 
		 
		 
		 if(ageYears == 0 && ageMonths == 0 && ageWeeks == 0 && ageDays == 0){
			 ageYears = -1;
			 ageMonths = -1; 
			 ageWeeks = -1;
			 ageDays = -1;			 
		 }
		 else{
			 if(ageMonths == 0 && ageWeeks == 0 && ageDays == 0){
				 ageMonths = -1; 
				 ageWeeks = -1;
				 ageDays = -1;			 
			 }
			 else{
				 if(ageWeeks == 0 && ageDays == 0){
					 ageWeeks = -1;
					 ageDays = -1;			 
				 }
				 else{
					 if(ageDays == 0){
						 ageDays = -1;
					 }
				 }
			 }
		 }
		 
		 if(ageYears < 0 && (ageMonths > 0 || ageWeeks > 0 ||  ageDays > 0)) ageYears = 0;
		 if(ageMonths < 0 && (ageWeeks > 0 ||  ageDays > 0)) ageMonths = 0;
		 if(ageWeeks < 0 && ageDays > 0) ageWeeks = 0;

		 // End Normalize
		 
		 System.out.println("ageDays = " + ageDays + ", ageWeeks = " + ageWeeks + ", ageMonths = "+ ageMonths + ", ageYears = " + ageYears);

		 LocalDate lowestDate = LocalDate.of(year, month, day);
		 LocalDate highestDate = LocalDate.of(year, month, day);
		 

		 LocalDate lowestDate1  = null;
		 LocalDate highestDate1 = null;
		 
		 LocalDate lowestDate2  = null;
		 LocalDate highestDate2 = null;
		 
		 LocalDate lowestDate3  = null;
		 LocalDate highestDate3 = null;
		 
		 LocalDate lowestDate4  = null;
		 LocalDate highestDate4 = null;
		 

		 
		 if(ageYears >= 0){			 
			 lowestDate1 = lowestDate.minusYears(ageYears);
			 highestDate1 = highestDate.minusYears(ageYears);
			 
			 if(ageMonths >= 0){				 
				 lowestDate2 = lowestDate1.minusMonths(ageMonths);
				 highestDate2 = highestDate1.minusMonths(ageMonths);
				 
				 if(ageWeeks >= 0){					 
					 lowestDate3 = lowestDate2.minusDays(7 * ageWeeks);
					 highestDate3 = highestDate2.minusDays(7 * ageWeeks);
					 
					 if(ageDays >= 0){						 
						 lowestDate4 = lowestDate3.minusDays(ageDays);
						 highestDate4 = highestDate3.minusDays(ageDays);
					 }
					 else{
						 lowestDate4 = lowestDate3.minusDays(6);
						 highestDate4 = highestDate3;
					 }
				 }
				 else{
					 //System.out.println("Else 1");
					 lowestDate3 = lowestDate2.minusMonths(1);
					 lowestDate4 = lowestDate3.plusDays(1);
					 highestDate4 = highestDate2;
				 }
			 }
			 else{
				 lowestDate3 = lowestDate1.minusYears(1);
				 lowestDate4 = lowestDate3.plusDays(1);
				 highestDate4 = highestDate1;
			 }
			 
			 
		 }
		 else{
			 System.out.println("Returning null");
			 return null;
		 }
		 
		 int[] a =  {lowestDate4.getDayOfMonth(),  lowestDate4.getMonthValue(),  lowestDate4.getYear(),
				     highestDate4.getDayOfMonth(), highestDate4.getMonthValue(), highestDate4.getYear()}; 
		 if(a != null)
			 System.out.println(a[0] + "-"+a[1] + "-"+ a[2] + "----" + a[3] + "-" +a[4]+ "-"+ a[5]);
		 
		 return a;
	 }

	 
    public static String sex(String sex){
    	
    	if(sex == null) return "Unknown";
	    	
    	if(sex.trim().toUpperCase().equals("M"))
    		return "Male";
    	if(sex.trim().toUpperCase().equals("V"))
    		return "Female";
   		return "Unknown";
	    	
    }

    public static String findReciproke(String relation, String sex){
    	
    	
    	//System.out.println("xxx1 " + relation);
    	
    	if(relation == null){
    		//System.out.println("yyy0 " + relation);
    		return("Onbekend");
    	}
    	
    	Ref_Relation_B r =  Ref.getRelation_B(relation);
    	
    	if(r == null  || r.getKode() == 0){
    		//System.out.println("yyy1 " + relation);
    		return("Onbekend");
    	}
    	 
    	int code = r.getKode();
    	
    	/*
    	for(int j= 1; j < ConstRelations2.b3kode1.length; j++){
    		if(relation.equalsIgnoreCase(ConstRelations2.b3kode1[j])){
    			code = j;
    			break;
    		}
    		
    	}
    	*/ 
    	
    	//System.out.println("xxx2 " + code);

    	
    		
    	int reciprokeCode = Common1.getRelation(1, code)[0];
    	//System.out.println("xxx3 " + reciprokeCode);
    	//System.out.println("xxx4 " + ConstRelations2.b3kode1[reciprokeCode]);

    	if(reciprokeCode > 0 && reciprokeCode < ConstRelations2.b3kode1.length){

    		// Adapt for sex

    		if(sex.equalsIgnoreCase("V") && ConstRelations2.b3kode1_Female[reciprokeCode] == null)
    			if(ConstRelations2.mToF[reciprokeCode] != 0)
    				reciprokeCode = ConstRelations2.mToF[reciprokeCode];

    		if(sex.equalsIgnoreCase("M") && ConstRelations2.b3kode1_Male[reciprokeCode] == null)
    			if(ConstRelations2.fToM[reciprokeCode] != 0)
    				reciprokeCode = ConstRelations2.fToM[reciprokeCode];

    		//System.out.println("xxx4 " + ConstRelations2.b3kode1[reciprokeCode]);


    		return (ConstRelations2.b3kode1[reciprokeCode]);
    	}
    	else
    		;
    		//System.out.println("yyy4 " + relation + " " + reciprokeCode);
    		
    	
    	
    	return("Onbekend");
    	 
    }


	 
}
