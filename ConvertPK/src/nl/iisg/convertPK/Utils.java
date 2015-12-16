package nl.iisg.convertPK;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;

import java.lang.reflect.*;

import javax.persistence.Column;
import javax.persistence.Table;

import nl.iisg.ref.Ref;
import nl.iisg.ref.Ref_FamilyName;
import nl.iisg.ref.Ref_FirstName;
import nl.iisg.ref.Ref_Housenumber;
import nl.iisg.ref.Ref_Housenumberaddition;
import nl.iisg.ref.Ref_KG;
import nl.iisg.ref.Ref_Location;
import nl.iisg.ref.Ref_Prefix;
import nl.iisg.ref.Ref_Profession;

public class Utils {
	
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
			else
				dbfName = t.name().toUpperCase();

			// Get declared fields (class/instance variables) of inputClass

			Field [] declaredFieldList = inputClass.getDeclaredFields();  // all fields (class/instance variables) in class 
			int [] columnAnnotatedVariableToDBFField = new int[declaredFieldList.length];  // used to link fields with columns

			// Get .DBF file column information
			
			//System.out.println("Input File = " + inputDirectory + File.separator + dbfName + ".DBF");
			
			InputStream inputStream  = new FileInputStream(inputDirectory + File.separator + dbfName + ".DBF"); 
			DBFReader reader = new DBFReader(inputStream);
			
			//System.out.println("DBF Reader = " + reader);
			
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

						System.out.println("No corresponding column in " + dbfName + ".DBF for Field: " + declaredFieldList[i].getName() + ", annotation: " + columnAnnotatedField.name());
						System.exit(-1);

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
						
						// Adapt the DBF column data to suit our HSN needs (trim strings)
						
						if(fieldTypesDBF[columnAnnotatedVariableToDBFField[index1]] == DBFField.FIELD_TYPE_C)
							if(rowObjects[columnAnnotatedVariableToDBFField[index1]] != null)
								rowObjects[columnAnnotatedVariableToDBFField[index1]] = rowObjects[columnAnnotatedVariableToDBFField[index1]].toString().trim();
								
							
						

						
						// create object to hold value from DBF Column
						
						Object [] e =  new Object[1];
						e[0] = rowObjects[columnAnnotatedVariableToDBFField[index1]];
						//System.out.println("Index = " + index1 + " value = " + rowObjects[columnAnnotatedVariableToDBFField[index1]]);
						// Next statement is equivalent to: setVarx(rowObject[Y]);
						
						Object retObject = null;
						if(e[0] != null)
							 retObject = method.invoke(outputObject, e); // retObject contains the return code from the setter method, it is void
						
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

	public static String standardizeFamilyName(String original){
		
		if(original == null || original.trim().length() == 0)			
			return null;
		
		original = original.trim();

			
		Ref_FamilyName familyName = Ref.getFamilyName(original);      // See if familyName is already in our list
		
		if(familyName != null){                                       // If it is...
			if(familyName.getCode().equalsIgnoreCase("Y") || familyName.getCode().equalsIgnoreCase("U")) // .. See if it has been validated (by HSN Staff)
				return familyName.getName(); 	    				  // Use the standardized value
			else                                                      // It is there, but not validated    
				return original; 							          // Use the original value instead of the standard 
			
		}
		else{
			Ref_FamilyName r = new Ref_FamilyName();                  // familyName not in our list yet, allocate new one
			r.setOriginal(original); 	                              // Original is this familyName
			r.setCode("x");      									  // Indicate it has not yet been validated
			r.setNeedSave(true);                           			  // Indicate that it must be saved											 
			Ref.addFamilyName(r);        							  // Add it to our list
			return original;
		}

	}

	public static String standardizeFirstName(String original){
		
		if(original == null || original.trim().length() == 0){
			return null;                                         
			
		}
		original = original.trim(); 

		//System.out.println("Original = "+ original);
			
		Ref_FirstName firstName = Ref.getFirstName(original);        // See if firstName is already in our list
		if(firstName != null){                                       // If it is...
			if(firstName.getCode().equalsIgnoreCase("Y") || firstName.getCode().equalsIgnoreCase("U")) // .. See if it has been validated (by HSN Staff)
				return firstName.getName();  	 				 // Use the standardized value
			else                                                     // It is there, but not validated    
				return original; 							         // Use the original value instead of the standard 
			
		}
		else{
			Ref_FirstName r = new Ref_FirstName();                   // firstName not in our list yet, allocate new one
			r.setOriginal(original); 	                             // Original is this firstName
			r.setCode("x");      									 // Indicate it has not yet been validated
			r.setNeedSave(true);                           			 // Indicate that it must be saved											 
			Ref.addFirstName(r);        							 // Add it to our list
			return original;
		}

	}

	public static String standardizePrefix(String original){
		
		if(original == null || original.trim().length() == 0)			
			return null;
		
		original = original.trim();

		Ref_Prefix prefix = Ref.getPrefix(original);        		 // See if prefix is already in our list
		if(prefix != null){                                       	 // If it is...
			if(prefix.getCode().equalsIgnoreCase("Y") || prefix.getCode().equalsIgnoreCase("U"))  	 // .. See if it has been validated (by HSN Staff)
				return prefix.getPrefix(); 	    				 	 // Use the standardized value
			else                                                     // It is there, but not validated    
				return original; 							         // Use the original value instead of the standard 
		 }
		else{
			Ref_Prefix r = new Ref_Prefix();                   		 // prefix not in our list yet, allocate new one
			r.setOriginal(original); 	                             // Original is this prefix
			r.setCode("x");      									 // Indicate it has not yet been validated
			r.setNeedSave(true);                           			 // Indicate that it must be saved											 
			Ref.addPrefix(r);        								 // Add it to our list
			return original;
		}

	}

	public static ArrayList standardizeLocation(String original){
		
		ArrayList a = new ArrayList();
		
		if(original == null || original.trim().length() == 0){
			a.add(null);
			a.add(0);
			return a;
			
		}
		
		original = original.trim();
		
		Ref_Location location = Ref.getLocation(original);           // See if Location is already in our list
		if(location != null){                                        // If it is...
			if(location.getStandardCode().equalsIgnoreCase("Y") || location.getStandardCode().equalsIgnoreCase("U")){    // .. See if it has been validated (by HSN Staff)
				a.add(location.getLocation()); 	    				 // Use the standardized value
				a.add(location.getLocationID());
				//System.out.println("1");
			}
			else{                                                    // It is there, but not validated    
				a.add(original); 	 			    				 // Use the original value
				a.add(-1);                                           // No standard
				//System.out.println("2");
			}
		 }
		else{
			Ref_Location r = new Ref_Location();                   	 // Location not in our list yet, allocate new one
			r.setOriginal(original); 	                             // Original is this Location
			r.setStandardCode("x");      						     // Indicate it has not yet been validated
			r.setNeedSave(true);                           			 // Indicate that it must be saved											 
			Ref.addLocation(r);        								 // Add it to our list
			a.add(original);  				    				     // Use the original value
			a.add(-1);                                               // No standard
			//System.out.println("3");
			//System.out.println(original + "               " + a.get(0));

		}
		
		//System.out.println(original + "               " + a.get(0));
		
		return a;

	}

	public static ArrayList standardizeProfession(String original){
		
		ArrayList a = new ArrayList();
		
		if(original == null || original.trim().length() == 0){
			a.add(null);
			a.add(0);
			return a;
			
		}
		
		original = original.trim();
		
		Ref_Profession profession = Ref.getProfession(original);     // See if Profession is already in our list
		if(profession != null){                                      // If it is...
			if(profession.getCode().equalsIgnoreCase("Y") || profession.getCode().equalsIgnoreCase("U")){  // .. See if it has been validated (by HSN Staff)
				a.add(profession.getProfession()); 	    			 // Use the standardized value
				a.add(profession.getProfessionID());
			}
			else{                                                    // It is there, but not validated    
				a.add(original); 	 			    				 // Use the original value
				a.add(-1);                                           // No standard 
			}
		 }
		else{
			Ref_Profession r = new Ref_Profession();                 // Profession not in our list yet, allocate new one
			r.setOriginal(original); 	                             // Original is this Profession
			r.setCode("x");      									 // Indicate it has not yet been validated
			r.setNeedSave(true);                           			 // Indicate that it must be saved											 
			Ref.addProfession(r);        							 // Add it to our list
			a.add(original);  				    				     // Use the original value
			a.add(-1);                                               // No standard 
		}
		
		return a;

	}


	public static String standardizeHousenumber(String original){
		
		
		if(original == null || original.trim().length() == 0)
			return null;
		
		original = original.trim(); 

		Ref_Housenumber housenumber = Ref.getHousenumber(original);  // See if housenumber is already in our list
		if(housenumber != null){                                     // If it is...
			if(housenumber.getCode().equalsIgnoreCase("Y") || housenumber.getCode().equalsIgnoreCase("U"))	// .. See if it has been validated (by HSN Staff)
				return housenumber.getHousenumber(); 	    		 // Use the standardized value
			else                                                     // It is there, but not validated    
				return original; 							         // Use the original value instead of the standard 
		 }
		else{
			Ref_Housenumber r = new Ref_Housenumber();               // housenumber not in our list yet, allocate new one
			r.setOriginal(original); 	                             // Original is this housenumber
			r.setCode("x");      									 // Indicate it has not yet been validated
			r.setNeedSave(true);                           			 // Indicate that it must be saved											 
			Ref.addHousenumber(r);        							 // Add it to our list
			return original;
		}

	}

	public static String standardizeHousenumberaddition(String original){

		if(original == null || original.trim().length() == 0)
			return null;
			
		
		original = original.trim(); 

		Ref_Housenumberaddition housenumberaddition = Ref.getHousenumberaddition(original);  // See if housenumberaddition is already in our list
		if(housenumberaddition != null){       					                             // If it is...
			if(housenumberaddition.getCode().equalsIgnoreCase("Y") || housenumberaddition.getCode().equalsIgnoreCase("U"))	  // .. See if it has been validated (by HSN Staff)
				return housenumberaddition.getAddition(); 	    		 					 // Use the standardized value
			else                                                     						 // It is there, but not validated    
				return original; 							         						 // Use the original value instead of the standard 
		 }
		else{
			Ref_Housenumberaddition r = new Ref_Housenumberaddition();   	                 // housenumberaddition not in our list yet, allocate new one
			r.setOriginal(original); 	                            						 // Original is this housenumberaddition
			r.setCode("x");      									 						 // Indicate it has not yet been validated
			r.setNeedSave(true);                           									 // Indicate that it must be saved											 
			Ref.addHousenumberaddition(r);        											 // Add it to our list
			return original;
		}

	}
	
	public static String standardizeReligion(String original){
		
		if(original == null || original.trim().length() == 0)
			return null;

		original = original.trim(); 
	
			
		Ref_KG religion = Ref.getKG(original);     				      // See if denomination is in our list
		if(religion != null){                                         // If it is...
			if(religion.getStandard() != null)			              // .. See if it has a standard value (by HSN Staff)
				return religion.getStandard(); 	    				  // Use the standardized value
			else                                                      // It is there, but not validated    
				return original; 							          // Use the original value instead of the standard 
			
		}
		else{
			Ref_KG  k = new Ref_KG(); 
			k.setDenomination(original);
			k.setCode("x");
			k.setNeedSave(true);
			Ref.addKG(k);
			return original; 								          // Use the original value instead of the standard
		}

	}


	
	public static int[] transformDateFields(int day, int month, int year, int dayCorrected, int monthCorrected, int yearCorrected){

		int[] orgValue = new int[3];
		int[] corValue = new int[3];
		int[] outValue = new int[4];

		orgValue[0] = day;
		orgValue[1] = month;
		orgValue[2] = year;

		corValue[0] = dayCorrected;
		corValue[1] = monthCorrected;
		corValue[2] = yearCorrected;


		int hflag = 0;
		int flag = 0; 

		for(int i = 0; i < 3; i++){
			if(orgValue[i] > 0){
				if(corValue[i] > 0){
					outValue[i] = corValue[i];
					if(flag <= 10) 
						flag = 20;
				}
				else{
					outValue[i] = orgValue[i];
					if(flag == 0)
						flag = 10;
				}
			}	
			else{
				switch(orgValue[i]){
				case -1: hflag = 11; break;
				case -2: hflag = 12; break;
				case -3: hflag = 13; break;
				default: hflag = 13;
				}				
				if(corValue[i] > 0){
					outValue[i] = corValue[i];
					flag = hflag + 10; 
				}
				else{
					outValue[i] = 0;
					flag = hflag;
				}
			}
		}
		
		if(outValue[2] > 0){
			if(outValue[1] == 0){
				outValue[1] = 7;
				outValue[0] = 1;
				flag = 41;
			}
			else
				if(outValue[0] == 0){
					outValue[0] = 1;
					flag = 42;
				}
		}

		outValue[3] = flag;
		return(outValue);
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


    public static int minimum(int x, int y){
    	
    	int min = x;
    	if(y < min)
    		min = y;
    	return(min); 
    	
    }
    
    public static int minimum(int x, int y, int z){
    	
    	int min = x;
    	if(y < min)
    		min = y;
    	if(z < min)
    		min = z;
    	
    	return(min); 
    	
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

	static void checkKeyFields(int IDNR, String fileName, String...strings){
		
		//System.out.println("strings has dimension " + strings.length);
		
		if( strings.length > 0 && (strings[0] == null || strings[0].trim().length() == 0)) message(IDNR, "4127", fileName);
		if( strings.length > 1 && (strings[1] == null || strings[1].trim().length() == 0)) message(IDNR, "4128", fileName);
		if( strings.length > 2 && (strings[2] == null || strings[2].trim().length() == 0)) message(IDNR, "4129", fileName);
		
		
	}
	
    private static void message(int idnr, String number, String... fills) {

        //print("Messagenr: " + number);

        Message m = new Message(number);

        m.setKeyToRP(idnr);
        m.save(fills);
    }


    
}



