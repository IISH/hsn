/*
  `* Naam:    Utils
* Version: 0.1
* Author: Cor Munnik
* Copyright
*/
package nl.iisg.ids03;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.FlushModeType;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;

import nl.iisg.hsncommon.Common1;
import nl.iisg.ref.*;


/**
 * 
 * This class contains routines that are used throughout the application
 *  
 */
public class Utils {

	private static final String PACKAGE_NAME = "nl.iisg.ids03";
	
	//static EntityManagerFactory factory_ref = Persistence.createEntityManagerFactory("ref_tables");
	//static EntityManager em_ref = getFactory_ref().createEntityManager();
	//static int em_ref_Count = 0;

	static EntityManagerFactory factory_tabs = Persistence.createEntityManagerFactory("bdated_tables");
	static EntityManager em_tabs = getFactory_tabs().createEntityManager();
	static int em_btabs_Count = 0;

	static EntityManagerFactory factory_original_tabs = Persistence.createEntityManagerFactory("b_tables");
	static EntityManager em_original_tabs = getFactory_original_tabs().createEntityManager();
	static int em_original_btabs_Count = 0;

	static EntityManagerFactory factory_original_tabs2 = Persistence.createEntityManagerFactory("b_tables_input");
	static EntityManager em_original_tabs2 = getFactory_original_tabs2().createEntityManager();
	static int em_original_btabs2_Count = 0;

	static EntityManagerFactory factory_log = Persistence.createEntityManagerFactory("popreg_log");
	static EntityManager em_log = getFactory_log().createEntityManager(); 
	static int em_log_Count = 0;

    static EntityManagerFactory factory_mysql_opslag = Persistence.createEntityManagerFactory("MySQL_opslag");
	static EntityManager em_mysql_opslag = getFactory_mysql_opslag().createEntityManager();
	static int em_mysql_opslag_Count = 0;

    static EntityManagerFactory factory_mysql_opslag_org = Persistence.createEntityManagerFactory("MySQL_opslag_org");
	static EntityManager em_mysql_opslag_org = getFactory_mysql_opslag_org().createEntityManager();
	static int em_mysql_opslag_org_Count = 0;



	static int c = 0;
	
	//static{
	//	getEm_ref().getTransaction().begin(); 
	//	getEm_tabs().getTransaction().begin(); 
	//	getEm_log().getTransaction().begin(); 
	//}
	
	/**
	 * 
	 * Converts object to int
	 * 
	 * @param o
	 * @return
	 */
	public static int toInt(Object o){
		
		String s = o.toString();		
		String a[] = s.split("\\.");		
		Integer i = new Integer(a[0]);
		return i.intValue(); 
		
	}
	
	/**
	 * 
	 * Converts object to string
	 * 
	 * @param date0
	 * @return
	 */
	
	public static String toStr(Object date0){
	
		String [] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		
		// Original Format is: Thu Dec 15 00:00:00 CET 2005
		// New      Format is: 15-12-2005
		
		String s = date0.toString();

		String[] a = s.split(" ");
		
		String month = a[1];
		int month_i = 0; 
		
		for(int i = 0; i < months.length ; i++){
			
			if(months[i].equalsIgnoreCase(month) == true){
				month_i = i + 1;
				break;
			}
		}
		
		int day_i =  (new Integer(a[2])).intValue();
		int year_i = (new Integer(a[5])).intValue();
		
		String t = String.format("%02d-%02d-%4d", day_i, month_i, year_i);
		return t;
		
	}

	/**
	 * 
	 * Normalizes the first date by means of the second
	 * 
	 */
	 public static int[] normalize(int day1, int month1, int year1, int day2, int month2, int year2){
		 
		 int estimate = 100; // presume exact

		 if(day1 < 1){
			 if(year1 == year2){				
				 day1 = day2;
				 estimate = 134;
			 }
			 else{
				 day1 = 1;
				 estimate = 131;
			 }
		 }
		 if(month1 < 1){	
			 if(year1 == year2){
				 day1 = day2;
				 month1 = month2;
				 estimate = 144;

			 }
			 else{
				 month1 = 1;
				 estimate = 141;
			 }
		 }
		 
		 int [] a = new int[4];
		 
		 a[0] = day1;
		 a[1] = month1;
		 a[2] = year1;
		 a[3] = estimate;
		 
		 return a;
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
			 
		 // Correct for 29-02-xxxx
		 
		 if(month1 == 2 && day1 > 28 && !(year1 % 4 == 0 && (year1 % 100 != 0 || year1 % 400 == 0))){
			 day1 = day1 - 28;
			 month1 = 3;
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

		 int count = 0;

		 while(day != day1 || month != month1 || year != year1){
			 
			 if(++count > 1000 * 365) {
				 System.out.println("dayCount input = " + day1 + "-" + month1 + "-" + year1);
				 count = count/0; 
			 }


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
    	
    	if(count <= 0) {
    		count = 1/0; // force exception;
    	}
    	
    	
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
	 * Check if a date is valid 
	
	 * 
	 */

    
	public static int dateIsValid(String date){
		
		if(date == null) return -1;
		
		int day = (new Integer(date.substring(0,2))).intValue();
		int month = (new Integer(date.substring(3,5))).intValue();
		int year = (new Integer(date.substring(6,10))).intValue();
		
		return(Common1.dateIsValid(day, month, year));		
	}
	
	public static int [] createPartialDate(int day, int month){
		
		if(day < 0){
			day = 31;
			if(month == 2)
				day = 28;
			if(month == 4 || month == 6 || month == 9 || month == 11)
				day = 30;
		}
		if(month < 0)
			month = 12;

		int [] a = new int[2];
		a[0] = day;
		a[1] = month;
		return(a); 
	}
	
	/**
	*
	*  If a valid (y>0) correction day/month/year is given, it is copied to the original date (x)
	*  If an original day/month/year == -3, it is set to -1
	* 
	*/  


	public static int convertDateElement(int original, int interpreted){

		if(interpreted > 0)
			return(interpreted); 
		else
			if(interpreted == -3)
				return(-1);
			else
				return(original);


	}
	
	private static String ColumnName(String className, String attributeName){

    	String columnName = null;
    	String cname = PACKAGE_NAME + "." + className; 
    	
    	
    	try{
    	   Class<?> cls = Class.forName(cname); 
    	   Field f = cls.getDeclaredField(attributeName);
           if (f != null){
    	      Column c = f.getAnnotation(Column.class);
        	  if (c != null) 
        		  columnName = c.name();
    	   }
    	}
    	catch(Exception e){
    		System.out.println("Error occurred getting Column Name for " + className + ":" + attributeName);
    	}
    	
    	return(columnName != null ? columnName : "");
    }
    /*
     * The annotations to the class are read and the associated table name is returned  
     */	
    	
    private static String TableName(String className){
    	
    	String tableName = null;
    	String cname = PACKAGE_NAME + "." +  className; 
    	
    	try{
    		Class<?> cls = Class.forName(cname);
    		Table t = (Table) cls.getAnnotation(Table.class);
    		if(t != null)
    			tableName = t.name();

    	}
    	catch(Exception e){
    		System.out.println("Error occurred getting Table Name");
    	}
    	
    	return(tableName != null ? tableName : "");
    }

    
    public static void persist(Object o){


    	if(o == null) return;

    	EntityManager em = null;

    	if(o instanceof nl.iisg.ids03.PersonDynamicStandardized ||
    			o instanceof nl.iisg.ids03.PersonStandardized ||
    			o instanceof nl.iisg.ids03.RegistrationStandardized ||
    			o instanceof nl.iisg.ids03.RegistrationAddressStandardized) {    		

    		em = getEm_tabs();

    		em.persist(o);
    	}
    }
    
    
    public static void commit(){
    	
    	EntityManager em = getEm_tabs();

    	if(em != null){

    		em.getTransaction().commit();
    		em.clear();
    		em.close();

    	}
    }
    /**
     * 
     * @param place
     * @param ainb, rp
     * @return
     */
    public static String[] transformPlace(String place, Ref_AINB ainb, Ref_RP rp){
    	
    	String a[] = new String[3];
    	String place1 = place;
    	
    	if(place1 == null || place1.trim().length() == 0){
    		
    		if(ainb != null && ainb.getMunicipality() != null && ainb.getMunicipality().length() > 0 ){
    			a[0] = ainb.getMunicipality();
    			a[1] = "0"; 
    			a[2] ="2"; // No value, chosen for the municipality of the register
    		}
    		else{
    			if(rp != null && rp.getBirthPlaceRP() != null){

    				a[0] = rp.getBirthPlaceRP();
    				a[1] = "0"; 
    				a[2] ="4"; // From birth certificate

    			}

    		}
    	}

    	else{
    		Ref_Location l = Ref.getLocation(place1);
    		if(place1.equalsIgnoreCase("Langenholte"))
    			System.out.println("AAAS3 " + l);

    		//System.out.println("AAAS1 " + place1 + " " + l);
    		if(l != null  && l.getStandardCode() != null && l.getStandardCode().equalsIgnoreCase("y") == true){
    		    if(l.getLocation() != null && l.getLocation().length() > 0){
    		   	    a[0] = l.getLocation();
        		    a[1] = new Integer(l.getLocationID()).toString();
        			a[2] = "1"; // Only one standard value possible for original  
    		    }
    		}
    		else{
    			if(l == null){
    				//System.out.println("AAAS2 " + place1 + " " + l);
    				Ref_Location l1 = new Ref_Location();
    				l1.setOriginal(place1);
    				l1.setStandardCode("x");
    				Ref.addLocation(l1);    				
    				a[1] ="91"; 
    			}
    			else{
    				if(l.getStandardCode() != null){
    					if(l.getStandardCode().equalsIgnoreCase("x"))
    						a[1] = "91";
    					else{
    						if(l.getStandardCode().equalsIgnoreCase("n"))
    							a[1] = "93";
    						else{
    							if(l.getStandardCode().equalsIgnoreCase("u"))
    								a[1] = "95";
    							else
    								a[1] = "99";
    						}
    					}
    				}
					else
						a[1] = "99";
    			}
    		}	
    	}	
    	
    	return a;
    	
    }
    
    public static EntityManager getNewEm_tabs(){
    	
    	EntityManager em = getFactory_tabs().createEntityManager();    	
    	return(em);
    }
    
    
    public static void trimAll(Object [] rowObjects){
    	
    	for(int i = 0; i < rowObjects.length; i++){
    		
    		if(rowObjects[i] instanceof java.lang.String)
    			((String)rowObjects[i]).trim();
    		
    	}
    
    }
    
    public static String removeSuffixes(String t, String[] suffixes){
    	
    	String s = t;
    	
    	for(String s0: suffixes){
    		
    		if(s.length() > s0.length()){
    			
    			if(s.substring(s.length() - s0.length()).equalsIgnoreCase(s0))
    			    s = s.substring(0, s.length() - s0.length());

    		}
    	}
             	
    	return s;
    }
 
    public static Object  getColumn(String columnName, Object [] rowObjects, String [] fieldnames, byte[] fieldtypes){
    	
    	String [] months = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    	
    	int index = -1;
    	for(int i = 0; i < fieldnames.length; i++){
    		if(fieldnames[i].equalsIgnoreCase(columnName)){
    			index = i;
    			break;
    		}
    	}
    	
    	if(index == -1){
    		System.out.println("ColumnName = " + columnName);
    		index = index / 0; // force exception
    	}
    	
    	if(fieldtypes[index] == DBFField.FIELD_TYPE_C)    
    		if(rowObjects[index] != null){
    		return ((String)rowObjects[index]).trim();
    		}
    		else
    			return null;
    	else{
			if(fieldtypes[index] == DBFField.FIELD_TYPE_N || fieldtypes[index] == DBFField.FIELD_TYPE_F){


				if(rowObjects[index] != null){
					String s = rowObjects[index].toString();
					int j = s.indexOf(".");
					if(j >= 0)
						s = s.substring(0, j);				
					return (new Integer(s)).intValue();
				}
				else 
					return 0;

			}
			else{
				if(fieldtypes[index] == DBFField.FIELD_TYPE_D){
					
					// Example: Fri Nov 12 00:00:00 CET 2005 (this becomes 12-11-2005)
					
					if(rowObjects[index] != null){
						String s = rowObjects[index].toString();
						String [] b = s.split("[ ]");
						int month = 0;
						for(int k = 1; k <= 12; k++){
							if(months[k].equals(b[1])){
								month = k;
								break;
							}
						}
						String t = String.format("%02d-%02d-%04d", (new Integer(b[2])).intValue(),(new Integer(month)).intValue(), (new Integer(b[5])).intValue());
						return t;
					}
					else
						return null;
				}
				
			}
    	}
    	
    	return null;
    }
    
    public static void executeNativeStatement(String statement){
    	
    	
    	System.out.println(statement);
            EntityManager em = getNewEm_tabs();

            em.getTransaction().begin();

            em.createNativeQuery(statement).executeUpdate();

            em.getTransaction().commit();
            em.clear();

            em.close();

    	
    }
    
	/**
	 * 
	 * Name: CreateObjects
	 * 
	 * Purpose: Read DBF File and return List with initiated objects
	 * 
	 * Remark: This routine uses Reflection (java.lang.reflect) to inspect the input Class
	 *         It also re-uses JPA-annotations   
	 * 
	 * This routine performs the following:
	 * 
	 * Read Table annotation from class specified by className (Gives DBF File name)
	 * Open DBF File from inputDirectory
	 * Get DBF Column Information
	 * Get Column Annotations from class specified by className
	 * Check that every Column Annotation has corresponding DBF column
	 * Read DBF rows
	 * For every DBF row:
	 *     Allocate new Object from class specified by className
	 *     For every Column Annotation:
	 *       
	 *       Take corresponding DBF column data type 
	 *       Take corresponding DBF column data from DBF row 
	 *       Transform data depending on data type (to suit our specific needs for HSN) 
	 *       Find Annotated Field's setter method (based on Field's name, CamelCase) 
	 *       Invoke annotated Field's setter method with data and datatype
	 *        
	 *     Add Object to output arrayList
	 * 
	 * 
	 * @param className
	 * @param inputDirectory
	 * @return List of Objects of type indicated by className. Objects are initialized with the data from the DBF File rows.
	 * 
	 */
	
	public static List createObjects(String className, String inputDirectory){ 
		String [] months = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};  // needed to transform dates
	
		try{
			Class<?> inputClass   = Class.forName(className); 
			
			// Get name of .DBF file from annotation
	
			Table t = (Table) inputClass.getAnnotation(Table.class);
	
	
	        String dbfName = null;
	        if(t == null){
	            //tableName = t.name();
	            System.out.println("No .DBF file specified for " + className);
	            System.exit(-1);
	        }
			else {
	            dbfName = t.name().toUpperCase();
	        }
	
	        // Get declared fields (class/instance variables) of inputClass
	
	        Field [] declaredFieldList = inputClass.getDeclaredFields();  // all fields (class/instance variables) in class
			int [] columnAnnotatedVariableToDBFField = new int[declaredFieldList.length];  // used to link fields with columns
	
			// Get .DBF file column information
	
			InputStream inputStream  = new FileInputStream(inputDirectory + File.separator + dbfName + ".DBF");
			//InputStreamReader isr = new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1);
	        System.out.println("Opening: " + inputDirectory + File.separator + dbfName + ".DBF");
			DBFReader reader = new DBFReader(inputStream);
			
			int numberOfDBFFields = reader.getFieldCount();
	
			String [] fieldNamesDBF = new String[numberOfDBFFields];
			byte   [] fieldTypesDBF = new byte[numberOfDBFFields]; 
	
			for(int i=0; i<numberOfDBFFields; i++){
				fieldNamesDBF[i] = reader.getField(i).getName();
				fieldTypesDBF[i] = reader.getField(i).getDataType();
			}
	
			// Check that all annotated fields have a corresponding column in the .DBF File
	        
			int index = 0; // used to map fields to columns 
			for (int i = 0; i < declaredFieldList.length; i++) { // Note: this will give *all* fields, not just the annotated ones
	
				Column columnAnnotatedField = declaredFieldList[i].getAnnotation(Column.class);				
	
				if(columnAnnotatedField != null){ // It is one of our "COLUMN=XXXX" annotated fields
	
					String columnAnnotatedFieldType = null;
					String[] a = declaredFieldList[i].getType().toString().split(" "); // Must do this because annotatedFieldList[i].getType().toString() returns "class java.lang.String"
					if(a.length > 1)
						columnAnnotatedFieldType = a[1];					
					else
						columnAnnotatedFieldType = a[0];					
	
					boolean found = false;
					
					// Let's find the corresponding DBF column 
					
					for(int j = 0; j < numberOfDBFFields; j++){
	
						if(fieldNamesDBF[j].equalsIgnoreCase(columnAnnotatedField.name())){
	
							found = true;
							columnAnnotatedVariableToDBFField[index++] = j; // Links our "COLUMN=XXXX" annotated variable to DBF Column "XXXX" 
	
							// Data type of class variable and DBF column must match: "int" <-> "N" and "java.lang.String" <-> "C" (or "D" - date)
							
							switch(fieldTypesDBF[j]){
	
							case(DBFField.FIELD_TYPE_N):
	
								if(!columnAnnotatedFieldType.equalsIgnoreCase("int")){
	
									System.out.println("Field  with annotation: @Column(name=\"" + columnAnnotatedField.name().trim() + "\" has datatype \"" + columnAnnotatedFieldType +
											"\" but Column " + fieldNamesDBF[j] + " of File " + dbfName + ".DBF has datatype \"" + (char)fieldTypesDBF[j] + "\""); 
									System.exit(-1);
	
								}
							break;
	
							case(DBFField.FIELD_TYPE_C):
							case(DBFField.FIELD_TYPE_D):
	
								if(!columnAnnotatedFieldType.equalsIgnoreCase("java.lang.String")){
	
									System.out.println("Field with annotation: @Column(name=\"" + columnAnnotatedField.name().trim() + "\" has datatype \"" + columnAnnotatedFieldType +
											"\" but Column " + fieldNamesDBF[j] + " of File " + dbfName + ".DBF has datatype \"" + (char)fieldTypesDBF[j] + "\""); 
									System.exit(-1);
	
								}
							break;
	
							default:
	
								System.out.println("Field with annotation: @Column(name=\"" + columnAnnotatedField.name().trim() + "\" has datatype \"" + columnAnnotatedFieldType +
										"\" but Column " + fieldNamesDBF[j] + " of File " + dbfName + ".DBF has *unsupported* datatype \"" + (char)fieldTypesDBF[j] + "\""); 
								System.exit(-1);
	
							}
						}
					}
					
					
	
					if(found == false){
	
						columnAnnotatedVariableToDBFField[index++] = -1; //  Our "COLUMN=XXXX" annotated variable has no DBF Column 
						//System.out.println("No corresponding column in " + dbfName + ".DBF for Field: " + declaredFieldList[i].getName() + ", annotation: " + columnAnnotatedField.name());
						//System.exit(-1);
	
					}
					
					
				}
			}
			
			// Read the DBF rows, instanciate objects and call their setters with data from DBF rows
			
			System.out.println("Reading " + dbfName + ".DBF");
			
	        int count = 0;
	        Object [] rowObjects;
	        List a = new ArrayList();
	        
			while( (rowObjects = reader.nextRecord()) != null) {
				
				Object outputObject = inputClass.newInstance();  // equivalent to "Object outputObject = new inputClass();"
				
				int index1 = 0;
				for(int i = 0; i < declaredFieldList.length; i++){
					
					

					Column columnAnnotatedField = declaredFieldList[i].getAnnotation(Column.class);

					if(columnAnnotatedField != null){ // It is one of our "COLUMN=XXXX"-annotated fields

						if(columnAnnotatedVariableToDBFField[index1] != -1) {

							// Make ready to invoke the setter of the annotated Field's variable

							// Set parameter list, only one parameter for setter, with datatype depending on DBF Field's type

							Class[] parameterTypes = new Class[1];


							if(fieldTypesDBF[columnAnnotatedVariableToDBFField[index1]] == DBFField.FIELD_TYPE_N)
								parameterTypes[0] = Integer.TYPE;
							else
								parameterTypes[0] = String.class;


							// Create the name of the setter method for this variable ("abcde" -> "setAbcde")

							String methodName = "set";
							methodName += declaredFieldList[i].getName().substring(0,1).toUpperCase();
							methodName += declaredFieldList[i].getName().substring(1);



							// Get the method from inputClass by it's name and signature (number of parameters and their types)

							Method  method = inputClass.getDeclaredMethod(methodName, parameterTypes);

							// Adapt the DBF column data to suit our HSN needs (tranform "1.0" to "1" (integer))

							if(fieldTypesDBF[columnAnnotatedVariableToDBFField[index1]] == DBFField.FIELD_TYPE_N){

								if(rowObjects[columnAnnotatedVariableToDBFField[index1]] != null){
									String s = rowObjects[columnAnnotatedVariableToDBFField[index1]].toString();

									String [] b = s.split("[.]"); 
									Integer k = new Integer(b[0]);
									rowObjects[columnAnnotatedVariableToDBFField[index1]] = k;
								}

							}

						    // Adapt the DBF column data to suit our HSN needs (tranform "Wed Jul 19 00:00:00 CEST 2000" to "19-07-2000")

							if(fieldTypesDBF[columnAnnotatedVariableToDBFField[index1]] == DBFField.FIELD_TYPE_D){

								// Format  Wed Jul 19 00:00:00 CEST 2000
								//         0   1   2  3        4    5  

								if(rowObjects[columnAnnotatedVariableToDBFField[index1]] != null){
									String s = rowObjects[columnAnnotatedVariableToDBFField[index1]].toString();

									String[] d = s.trim().split("[ ]"); 
									for(int ii = 0; ii < months.length; ii++){

										if(months[ii].equalsIgnoreCase(d[1])){
											String u = String.format("%02d-%02d-%04d", new Integer(d[2]), new Integer(ii), new Integer(d[5]));
											rowObjects[columnAnnotatedVariableToDBFField[index1]] = u;
											break;
										}
									}
								}
							}

						    // Adapt the DBF column data to suit our HSN needs (transform ISO-8859-1 to UTF-8)
							// This does not work, rowObjects[columnAnnotatedVariableToDBFField[index1]] already is a String
							// but judging by the output, it has not been created using ISO_8859_1, so it contains invalid characters

							
							 
							if(fieldTypesDBF[columnAnnotatedVariableToDBFField[index1]] == DBFField.FIELD_TYPE_C){

								if(rowObjects[columnAnnotatedVariableToDBFField[index1]] != null){
									
									//System.out.println("ddda " + System.getProperty("file.encoding"));
									
									//byte[] b = ((String)rowObjects[columnAnnotatedVariableToDBFField[index1]]).getBytes("UTF-8");
									//String s = new String(b, StandardCharsets.ISO_8859_1);
									
									//System.out.println("ggg " + rowObjects[columnAnnotatedVariableToDBFField[index1]].getClass());
									//new String((byte[]) rowObjects[columnAnnotatedVariableToDBFField[index1]], StandardCharsets.ISO_8859_1);
									rowObjects[columnAnnotatedVariableToDBFField[index1]] = rowObjects[columnAnnotatedVariableToDBFField[index1]];
									
								}
							}
							
							

							// create object to hold value from DBF Column



							Object [] e =  new Object[1];
							e[0] = rowObjects[columnAnnotatedVariableToDBFField[index1]];



							// Next statement is equivalent to: setVarx(rowObject[Y]);

							Object retObject = null;
							if(e[0] != null)
								retObject = method.invoke(outputObject,(Object []) e); // retObject contains the return code from the setter method, it is void

							//if(e[0] != null) {
							//	try {
							//		retObject = method.invoke(outputObject,(Object []) e); // retObject contains the return code from the setter method, it is void

							//	} finally{
							//		System.out.println("AAS " + methodName);
							//		System.out.println("AAS " + e[0]);
							//	}
							//}

						}
						index1++; // next ColumnAnnotated field
					}
				}

				a.add(outputObject);
				count++;  // next DBF row

			}	
					
			System.out.println("Read    " + dbfName + ".DBF " + count + " rows");	
			inputStream.close();
	
			return a;
			
		}
	
		catch(ClassNotFoundException e){
			System.out.println(e.toString());
			System.exit(-1);
		}
		catch(InstantiationException e){
			System.out.println(e.toString());
			System.exit(-1);
		}
		catch(IllegalAccessException  e){
			System.out.println(e.toString());
			System.exit(-1);
		}
		catch (DBFException e) {
			System.out.println(e.toString());
			System.exit(-1);
		} 
		catch (NoSuchMethodException e) {
			System.out.println(e.toString());
			System.exit(-1);
		}
		catch (InvocationTargetException e) {
			System.out.println(e.toString());
			System.exit(-1);
		}
		catch (FileNotFoundException e) {
		}
		catch (IOException e) {
		}
	
		return null;
	}

    
    
	public static EntityManagerFactory getFactory_tabs() {
		return factory_tabs;
	}

	public static void setFactory_tabs(EntityManagerFactory factory_tabs) {
		Utils.factory_tabs = factory_tabs;
	}

	public static EntityManager getEm_tabs() {
		return em_tabs;
	}

    public static EntityManager getEm_mysql_opslag_org() {
        return em_mysql_opslag_org;
    }

	public static void setEm_tabs(EntityManager em_tabs) {
		Utils.em_tabs = em_tabs;
	}

	public static int getEm_btabs_Count() {
		return em_btabs_Count;
	}

	public static void setEm_btabs_Count(int em_btabs_Count) {
		Utils.em_btabs_Count = em_btabs_Count;
	}

	public static String getPackageName() {
		return PACKAGE_NAME;
	}

	public static EntityManagerFactory getFactory_log() {
		return factory_log;
	}

	public static void setFactory_log(EntityManagerFactory factory_log) {
		Utils.factory_log = factory_log;
	}

	public static EntityManager getEm_log() {
		return em_log;
	}

	public static void setEm_log(EntityManager em_log) {
		Utils.em_log = em_log;
	}

	public static int getEm_log_Count() {
		return em_log_Count;
	}

	public static void setEm_log_Count(int em_log_Count) {
		Utils.em_log_Count = em_log_Count;
	}


    /* Added by CRO */
    public static EntityManagerFactory getFactory_mysql_opslag(){
        return factory_mysql_opslag;
    }

    public static void setFactory_mysql_opslag(EntityManagerFactory factory_mysql_opslag){
        Utils.factory_mysql_opslag = factory_mysql_opslag;
    }

    public static EntityManager getEm_mysql_opslag() {
		return em_mysql_opslag;
	}

    public static EntityManagerFactory getFactory_mysql_opslag_org() {
        return factory_mysql_opslag_org;
    }

	public static EntityManagerFactory getFactory_original_tabs() {
		return factory_original_tabs;
	}

	public static void setFactory_original_tabs(
			EntityManagerFactory factory_original_tabs) {
		Utils.factory_original_tabs = factory_original_tabs;
	}

	public static EntityManager getEm_original_tabs() {
		return em_original_tabs;
	}

	public static void setEm_original_tabs(EntityManager em_original_tabs) {
		Utils.em_original_tabs = em_original_tabs;
	}
	
	
	public static EntityManagerFactory getFactory_original_tabs2() {
		return factory_original_tabs2;
	}
    
	public static EntityManager getEm_original_tabs2() {
		return em_original_tabs2;
	}

	public static void setEm_original_tabs2(EntityManager em_original_tabs2) {
		Utils.em_original_tabs2 = em_original_tabs2;
	}

	public static void setFactory_original_tabs2(EntityManagerFactory factory_original_tabs2) {
		Utils.factory_original_tabs2 = factory_original_tabs2;
	}

	public static int getEm_original_btabs_Count() {
		return em_original_btabs_Count;
	}

	public static void setEm_original_btabs_Count(int em_original_btabs_Count) {
		Utils.em_original_btabs_Count = em_original_btabs_Count;
	}

	public static int getEm_mysql_opslag_Count() {
		return em_mysql_opslag_Count;
	}

	public static void setEm_mysql_opslag_Count(int em_mysql_opslag_Count) {
		Utils.em_mysql_opslag_Count = em_mysql_opslag_Count;
	}

	public static void setEm_mysql_opslag(EntityManager em_mysql_opslag) {
		Utils.em_mysql_opslag = em_mysql_opslag;
	}


}