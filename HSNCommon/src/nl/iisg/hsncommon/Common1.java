package nl.iisg.hsncommon;



import java.io.File;


import nl.iisg.ref.Ref;

public class Common1 {
	
	
	/**
	 * 
	 * @param startDate1
	 * @param EndDate1
	 * @param startDate2
	 * @param EndDate2
	 * @return
	 * 
	 * Calculates the intersection of two intervals, one of which can be null, null
	 * 
	 */
	public static String [] getIntersection(String startDate1, String endDate1, String startDate2, String endDate2){
		
		String [] a = new String[2];
		
		if(startDate1 != null){
			
			if(startDate2 != null){

				int start1 = dayCount(startDate1);
				int end1   = dayCount(endDate1);
				int start2 = dayCount(startDate2);
				int end2   = dayCount(endDate2);
				
				int start = Math.max(start1, start2);		
				int end   = Math.min(end1, end2);
				
				if(start <= end){
					
					a[0] = dateFromDayCount(start);
					a[1] = dateFromDayCount(end);
					
					return a;
				}
				else
					return null;
			}
			else{
				a[0] = startDate1;
				a[1] = endDate1;
				return a;
			}
		}
		else{
			if(startDate2 != null){
				a[0] = startDate2;
				a[1] = endDate2;
				return a;
				
			}
			else
				return null;
			
		}
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
	 * @param count
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

	public static int minimum(int x, int y, int z){
		
		int min = x;
		if(y < min)
			min = y;
		if(z < min)
			min = z;
		
		return(min); 
		
	}

	public static int minimum(int x, int y){
		
		int min = x;
		if(y < min)
			min = y;
		return(min); 
		
	}

	/**
	*
	*  This routine calculates the Levenshtein distance between two strings
	*  The algorithm was adapted from: http://en.wikipedia.org/wiki/Levenshtein_distance
	* 
	*/  
	
	
	
	public static int LevenshteinDistance(String s, String t)
	{
		// for all i and j, d[i,j] will hold the Levenshtein distance between
		// the first i characters of s and the first j characters of t;
		// note that d has (m+1)x(n+1) values, m = length(s), n = length(t)
		//
		// Also allow transpositions
	
		int [][] d = new int[s.length() + 1][t.length() + 1]; 	
	
		int cost = 0;
	
		for(int i = 0; i <= s.length(); i++) 
			d[i][0] = i; // the distance of any first string to an empty second string
	
		for(int j = 0; j <= t.length(); j++)
			d[0][j] = j; // the distance of any second string to an empty first string
	
		for(int i = 1; i <= s.length(); i++){
			for(int j = 1; j <= t.length(); j++){
	
				if(s.charAt(i -1) == t.charAt(j -1))
					cost = 0;
				else 
					cost = 1;
	
	
				d[i][j] = minimum(
						d[i-1][j] + 1,       // deletion
						d[i][j-1] + 1,       // insertion
						d[i-1][j-1] + cost   // substitution
				);
	
	
				if(i > 1 && j > 1 && s.charAt(i-1) == t.charAt(j-2) && s.charAt(i-2) == t.charAt(j-1)){
					d[i][j] = minimum(
							d[i][j],
							d[i-2][j-2] + cost   // transposition
					);
				}
			}
		}
	
		return d[s.length()][t.length()]; 
	}


	public static String nonExisitingFile(String directory, String [] file){
		
		File dir = new File(directory);
		File[] dirFiles = dir.listFiles();
		
		for(String f: file){
			boolean found = false;
			for(File fd: dir.listFiles()){
				if(fd.getName().equals(f)){
					found = true;
					break;
				}
			}
			if(found == false)
				return f;
		}
		
		return null;
		
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
		
		if(!(year  > 1750 && year  <  1941))   return 1;
		if(!(month > 0    && month <= 12))     return 1;
		if(!(day   > 0    && (day  <=  monthLength[month] || (day <= monthLength[2] + 1 && month == 2 && year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))))) return 1;
		
	    return 0;		
		
	}
	
	 /**
     * 
     * This routine determines the relation between two persons based on the relationship of these persons to the Head 
     * 
     * @param relToHeadA
     * @param relToHeadB
     * @return relation A to B
     */
    
    public static int[] getRelation(int relToHeadA, int relToHeadB){
    	
    	// First try if code can be resolved through ref_relation_c;
    	
    	
    	if(Ref.getRelation_C(relToHeadA, relToHeadB) != null){
    		System.out.println("   RelationA = " +  relToHeadA +  "   RelationB = " +  relToHeadB + "   RelationM = " +  Ref.getRelation_C(relToHeadA, relToHeadB));
    		return Ref.getRelation_C(relToHeadA, relToHeadB);
    	}
    	
    	
    	// Some codes do not change1
    	
    	
    	if(relToHeadA == -1 || relToHeadA == -2 || relToHeadA == -3 || relToHeadA == 80 ||
    			(relToHeadA >= 90 && relToHeadA <= 100) ||
    			(relToHeadA >= 41 && relToHeadA <= 49))
    		
    		return new int[]{relToHeadA};
    			
    	
    	if(relToHeadB == -1 || relToHeadB == -2 || relToHeadB == -3 || relToHeadB == 80 ||
    			(relToHeadB >= 90 && relToHeadB <= 100) ||
    			(relToHeadB >= 41 && relToHeadB <= 49))
    		
    		return new int[]{relToHeadB};
    			
    	
    	
    	
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
    		return new int[]{-1};
    	
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
    		return new int[]{-1};
    	
    	// look up code between A and B
    	
    	int [] AB =  new int[]{90};  // preset geen verwantschap
    	
    	if(ConstRelations.transform[code1][code2] != null && ConstRelations.transform[code1][code2].length > 0)
   		  AB = ConstRelations.transform[code1][code2]; 
    	
    	
    	return AB;
    	
    	
    }
    
	

}
