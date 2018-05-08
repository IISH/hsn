package iisg.nl.hsnmigrate;

import iisg.nl.hsnlog.Log;
import nl.iisg.ref.Ref;
import nl.iisg.ref.Ref_Address;
import nl.iisg.ref.Ref_Housenumber;
import nl.iisg.ref.Ref_Housenumberaddition;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;

public class Utils {

	static EntityManagerFactory factory_nieuw  = Persistence.createEntityManagerFactory("nieuw");
	static EntityManager             em_nieuw  = factory_nieuw.createEntityManager(); 

	static EntityManagerFactory factory_import  = Persistence.createEntityManagerFactory("import");
	static EntityManager             em_import  = factory_import.createEntityManager(); 


	
	
	public static void message(int errcode, int idnr, int year, String... fills){
		
		
		if(errcode ==   200255)
			errcode /= 0;
		Log log = new Log(errcode);
		log.save(idnr, year, fills);
		//log.save1();
		
	}
	
	public static String combine3FirstNames(String name1, String name2, String name3){
		
		String name = "";
		
		if(name1 != null) name = name1.split("%")[0];
		name += " ";

		if(name2 != null) name += name2.split("%")[0];
		name += " ";

		if(name3 != null) name += name3.split("%")[0];

		
		return name.trim();
		
		
	}
	
	public static Connection getConnection(String database, String userid, String passwrd) throws Exception {
		
		
		//String h = null;
		
		//if(database.equals("jdbc:mysql://localhost:3306"))
			//h = h.substring(1,2);
		
		String driver = "org.gjt.mm.mysql.Driver";
		String url =  database;
		String username = userid;
		String password = passwrd;
		
		//System.out.println("URL = " + url);

		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}


	public static void message(String database, String table, int idnr, int errcode, String messageText){
		
		
	}

	 public static int dayCount(int day1, int month1, int year1){

		 //System.out.println(" " + day1+ month1 + year1);

		 int [] monthLength = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

		 int day = 1;
		 int month = 1;
		 int year = 1600;

		 int dayCount = 0;

		 if(year1 <= 0 || month1 <= 0 || day1 <= 0) return -1;
			
         if(!(year1  > 1750 && year1  <  1941))   return -1;
			if(!(month1 > 0    && month1 <= 12))     return -1;
			if(!(day1   > 0    && (day1  <=  monthLength[month1] || (day1 <= monthLength[2] + 1 && month1 == 2 && year1 % 4 == 0 && (year1 % 100 != 0 || year1 % 400 == 0))))) return -1;		 
		 

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
				 GeneratedValue generatedValue = declaredFieldList[i].getAnnotation(GeneratedValue.class);				

				 if(columnAnnotatedField != null && generatedValue == null){ // It is one of our "COLUMN=XXXX" annotated fields

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

					 //if(found == false){
					 //
					 //	System.out.println("No corresponding column in " + dbfName + ".DBF for Field: " + declaredFieldList[i].getName() + ", annotation: " + columnAnnotatedField.name());
					 //	System.exit(-1);
					 //
					 //}
				 }
			 }

			 // Read the DBF rows, instanciate objects and call their setters with data from DBF rows

			 System.out.println("Reading " + dbfName + ".DBF");			


			 int count = 0;
			 Object [] rowObjects;
			 List a = new ArrayList();
			 Object [] e =  new Object[1];
			 Class[] parameterTypes = new Class[1];

			 while( (rowObjects = reader.nextRecord()) != null) {

				 Object outputObject = inputClass.newInstance();  // equivalent to "Object outputObject = new inputClass();"

				 int index1 = 0;
				 for(int i = 0; i < declaredFieldList.length; i++){

					 Column columnAnnotatedField = declaredFieldList[i].getAnnotation(Column.class);
					 GeneratedValue generatedValue = declaredFieldList[i].getAnnotation(GeneratedValue.class);				

					 if(columnAnnotatedField != null && generatedValue == null){ // It is one of our "COLUMN=XXXX"-annotated fields

						 // Make ready to invoke the setter of the annotated Field's variable

						 // Set parameter list, only one parameter for setter, with datatype depending on DBF Field's type



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

						 // Adapt the DBF column data to suit our HSN needs: Trim strings

						 if(fieldTypesDBF[columnAnnotatedVariableToDBFField[index1]] == DBFField.FIELD_TYPE_C){
							 if(rowObjects[columnAnnotatedVariableToDBFField[index1]] != null)
								 rowObjects[columnAnnotatedVariableToDBFField[index1]] = ((String) rowObjects[columnAnnotatedVariableToDBFField[index1]]).trim();
						 }


						 // create object to hold value from DBF Column




						 e[0] = rowObjects[columnAnnotatedVariableToDBFField[index1]];

						 // Next statement is equivalent to: setVarx(rowObject[Y]);

						 Object retObject = null;
						 if(e[0] != null)
							 retObject = method.invoke(outputObject,(Object []) e); // retObject contains the return code from the setter method, it is void

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

		 catch(EOFException e){
			 // Do nothing, the program runs on
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



	 /**
	  * 
	  * Name: CreateObjects2
	  * 
	  * Purpose: Read MSAccess table and return List with initiated objects
	  * 
	  * Remark: This routine uses Reflection (java.lang.reflect) to inspect the input Class
	  *         It also re-uses JPA-annotations   
	  * 
	  * This routine performs the following:
	  * 
	  * Read Table annotation from class specified by className (Gives MSAccess table name)
	  * Open MSAccess table from inputDirectory
	  * Get MSAccess table Column Information
	  * Get Column Annotations from class specified by className
	  * Check that every Column Annotation has corresponding MSAccess table column
	  * Read MSAccess table rows
	  * For every MSAccess table row:
	  *     Allocate new Object from class specified by className
	  *     For every Column Annotation:
	  *       
	  *       Take corresponding MSAccess table column data type 
	  *       Take corresponding MSAccess table column data from  row 
	  *       Transform data depending on data type (to suit our specific needs for HSN) 
	  *       Find Annotated Field's setter method (based on Field's name, CamelCase) 
	  *       Invoke annotated Field's setter method with data and datatype
	  *        
	  *     Add Object to output arrayList
	  * 
	  * 
	  * @param className
	  * @param inputDirectory
	  * @return List of Objects of type indicated by className. Objects are initialized with the data from the  File rows.
	  * 
	  */

	 public static List createObjects2(String className, String inputDirectory){ 

		// System.out.println("In create Objects2");

		 try{

			 Class<?> inputClass   = Class.forName(className); 

			 // Get name of MSAccess table name from annotation

			 Table t = (Table) inputClass.getAnnotation(Table.class);


			 String tabName = null;
			 if(t == null){
				 //tableName = t.name();
				 System.out.println("No MSAccess table name specified for " + className);
				 System.exit(-1);
			 }
			 else {
				 tabName = t.name().toUpperCase();
			 }

			 // Get declared fields (class/instance variables) of inputClass
			 // Accessing these fields must be replaced by accessing the public methods
			 // However, in that case we should also change our annotation, i.e. annotate the setters instead of the fields themselves


			 Field [] declaredFieldList = inputClass.getDeclaredFields();  // all fields (class/instance variables) in class
			 int [] columnAnnotatedVariableToMSAField = new int[declaredFieldList.length];  // used to link fields with columns
			 int numberOfMSAFields = 0;

			 // Get MSAccess table information

			 //String connURL = "jdbc:ucanaccess://" + inputDirectory + File.separator + "PK.accdb;memory=false";
			 //String connURL = "jdbc:ucanaccess://" + inputDirectory + File.separator + "PK.accdb";
			 String connURL = null;
			 Connection conn = null;
			 String [] fieldNamesMSA = null;
			 String [] fieldTypesMSA = null; 

			 ResultSet rs = null;
			 ResultSetMetaData rsmd = null;
			 Statement s = null;


			 
			 final FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Access Database", "accdb");			 
			 final File file = new File(inputDirectory);
			 
			 String file1 = "";
			 for (final File child : file.listFiles()) {
				 if(extensionFilter.accept(child)) {
					 //connURL = "jdbc:ucanaccess://" + inputDirectory + File.separator + "PK.accdb";
					 
					 connURL = "jdbc:ucanaccess://" + inputDirectory + File.separator + child.getName();
					 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");  // Not needed on Windows
					 conn = DriverManager.getConnection(connURL, "", "");
					 
					 
					 s = conn.createStatement();
					 //String selTable = "SELECT * FROM " + tabName + " WHERE IDNR < 15000";  // test
					 String selTable = "SELECT * FROM " + tabName ; 

					 try {
						 s.execute(selTable);
						 file1 = child.getName();
					 } catch (SQLException e1) {
						 // TODO Auto-generated catch block
						 continue;
					 }

					 rs = s.getResultSet();
					 rsmd = rs.getMetaData();

					 numberOfMSAFields = rsmd.getColumnCount();

					 fieldNamesMSA = new String[numberOfMSAFields];
					 fieldTypesMSA = new String[numberOfMSAFields]; 

					 for(int i=0; i<numberOfMSAFields; i++){
						 fieldNamesMSA[i] = rsmd.getColumnName(i + 1);
						 fieldTypesMSA[i] = rsmd.getColumnTypeName(i + 1);
					 }
				 }
			 }

			 if(numberOfMSAFields == 0) return null;


			 // Check that all annotated fields have a corresponding column in the MSA table
			 int index = 0; // used to map fields to columns 
			 for (int i = 0; i < declaredFieldList.length; i++) { // Note: this will give *all* fields, not just the annotated ones

				 Column columnAnnotatedField = declaredFieldList[i].getAnnotation(Column.class);				
				 GeneratedValue generatedValue = declaredFieldList[i].getAnnotation(GeneratedValue.class);				

				 if(columnAnnotatedField != null && generatedValue == null){ // It is one of our "COLUMN=XXXX" annotated fields

					 String columnAnnotatedFieldType = null;
					 String[] a = declaredFieldList[i].getType().toString().split(" "); // Must do this because annotatedFieldList[i].getType().toString() returns "class java.lang.String"
					 if(a.length > 1)
						 columnAnnotatedFieldType = a[1];					
					 else
						 columnAnnotatedFieldType = a[0];					

					 boolean found = false;

					 // Let's find the corresponding  column 

					 for(int j = 0; j < numberOfMSAFields; j++){

						 if(fieldNamesMSA[j].equalsIgnoreCase(columnAnnotatedField.name())){

							 found = true;
							 columnAnnotatedVariableToMSAField[index++] = j; // Links our "COLUMN=XXXX" annotated variable to MSA Column "XXXX" 

							 // Data type of class variable and  column must match: "int" <-> "N" and "java.lang.String" <-> "C" (or "D" - date)



							 if(fieldTypesMSA[j].equalsIgnoreCase("VARCHAR")){
								 if(!columnAnnotatedFieldType.equalsIgnoreCase("java.lang.String")){

									 System.out.println("Field  with annotation: @Column(name=\"" + columnAnnotatedField.name().trim() + "\" has datatype \"" + columnAnnotatedFieldType +
											 "\" but Column " + fieldNamesMSA[j] + " of table " + tabName + " has datatype \"" + fieldTypesMSA[j] + "\""); 
									 System.exit(-1);


								 }
							 }

							 if(fieldTypesMSA[j].equalsIgnoreCase("DOUBLE")){
								 if(!columnAnnotatedFieldType.equalsIgnoreCase("int")){

									 System.out.println("Field  with annotation: @Column(name=\"" + columnAnnotatedField.name().trim() + "\" has datatype \"" + columnAnnotatedFieldType +
											 "\" but Column " + fieldNamesMSA[j] + " of table " + tabName + " has datatype \"" + fieldTypesMSA[j] + "\""); 
									 System.exit(-1);


								 }
							 }


						 }
					 }

					 if(found == false){

						 System.out.println("No corresponding column in " + tabName + ". for Field: " + declaredFieldList[i].getName() + ", annotation: " + columnAnnotatedField.name());
						 System.exit(-1);

					 }
				 }
			 }

			 // Go through rows, instantiate objects and call their setters with data from table

			 Class[] parameterTypes = new Class[1];
			 Object [] rowObjects;
			 Object [] e =  new Object[1];
			 List a = new ArrayList();
			 int count = 0;

			 while((rs!=null) && (rs.next())){

				 Object outputObject = inputClass.newInstance();  // equivalent to "Object outputObject = new inputClass();"

				 int index1 = 0;
				 for(int i = 0; i < declaredFieldList.length; i++){


					 Column columnAnnotatedField = declaredFieldList[i].getAnnotation(Column.class);
					 GeneratedValue generatedValue = declaredFieldList[i].getAnnotation(GeneratedValue.class);				

					 if(columnAnnotatedField != null && generatedValue == null){ // It is one of our "COLUMN=XXXX"-annotated fields

						 // Make ready to invoke the setter of the annotated Field's variable

						 // Set parameter list, only one parameter for setter, with datatype depending on  Field's type

						 if(count == 0){
							 System.out.println("index1 = " + index1);
							 System.out.println("columnAnnotatedVariableToMSAField[index1] = " + columnAnnotatedVariableToMSAField[index1]);
							 System.out.println("fieldNamesMSA[columnAnnotatedVariableToMSAField[index1]] =  " + fieldNamesMSA[columnAnnotatedVariableToMSAField[index1]]);
							 System.out.println("fieldTypesMSA[columnAnnotatedVariableToMSAField[index1]] =  " + fieldTypesMSA[columnAnnotatedVariableToMSAField[index1]]);
						 }

						 if(fieldTypesMSA[columnAnnotatedVariableToMSAField[index1]].equalsIgnoreCase("DOUBLE") ||
								 fieldTypesMSA[columnAnnotatedVariableToMSAField[index1]].equalsIgnoreCase("INTEGER")  ) 
							 parameterTypes[0] = Integer.TYPE;
						 else
							 parameterTypes[0] = String.class;


						 // Create the name of the setter method for this variable ("abcde" -> "setAbcde")

						 String methodName = "set";
						 methodName += declaredFieldList[i].getName().substring(0,1).toUpperCase();
						 methodName += declaredFieldList[i].getName().substring(1);

						 // Get the method from inputClass by it's name and signature (number of parameters and their types)
						 //System.out.println("parameterTypes[0] =  " + parameterTypes[0]);

						 Method  method = inputClass.getDeclaredMethod(methodName, parameterTypes);


						 // create object to hold value from MSA Column		

						 e[0] = null;

						 if(fieldTypesMSA[columnAnnotatedVariableToMSAField[index1]].equalsIgnoreCase("DOUBLE") || 
								 fieldTypesMSA[columnAnnotatedVariableToMSAField[index1]].equalsIgnoreCase("INTEGER")) 
							 e[0] = rs.getInt(columnAnnotatedVariableToMSAField[index1] + 1);

						 if(fieldTypesMSA[columnAnnotatedVariableToMSAField[index1]].equalsIgnoreCase("VARCHAR")) {
							 e[0] = rs.getString(columnAnnotatedVariableToMSAField[index1] + 1);
							
							 //if(e[0] == null) e[0] = ""; // We do this because the old DBF reader did it, and the code above relies on it
						 }

						 if(fieldTypesMSA[columnAnnotatedVariableToMSAField[index1]].equalsIgnoreCase("TIMESTAMP")){

							 Date d = rs.getDate(columnAnnotatedVariableToMSAField[index1] + 1);
							 if(d != null){
								 String ss = d.toString();							
								 String u =  ss.substring(8, 10) + "-" + ss.substring(5, 7) + "-" + ss.substring(0, 4);

								 e[0] = u;
							 }
						 }

						 // Next statement is equivalent to: setVarx(rowObject[Y]);

						 Object retObject = null;
						 if(e[0] != null)
							 retObject = method.invoke(outputObject,(Object []) e); // retObject contains the return code from the setter method, it is void

						 index1++; // next ColumnAnnotated field
					 }
				 }

				 a.add(outputObject);
				 count++;  // next MSA row

				 //break;
			 }

			 // close and cleanup

			 System.out.println("Read    " + tabName + " from " + inputDirectory + "\\" + file1 +": " + count + " rows");	

			 s.close();
			 conn.close();

			 return a;
		 }
		 catch (NoSuchMethodException e) {
			 System.out.println(e.toString());
			 System.exit(-1);
		 } catch (IllegalAccessException e1) {
			 // TODO Auto-generated catch block
			 e1.printStackTrace();
		 } catch (SQLException e1) {
			 // TODO Auto-generated catch block
			 e1.printStackTrace();

		 } catch (IllegalArgumentException e1) {
			 // TODO Auto-generated catch block
			 e1.printStackTrace();
		 } catch (InvocationTargetException e1) {
			 // TODO Auto-generated catch block
			 e1.printStackTrace();
		 } catch (InstantiationException e1) {
			 // TODO Auto-generated catch block
			 e1.printStackTrace();
		 } catch (ClassNotFoundException e1) {
			 // TODO Auto-generated catch block
			 e1.printStackTrace();
		 }

		 return null;


	 }		

		public static String standardizeHousenumber(String original){
			
			
			if(original == null || original.trim().length() == 0)
				return null;
			
			//System.out.println("Standardizing number : " + original);
			
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
		
		public static Ref_Address standardizeAddress(String original){
			
			if(original == null || original.trim().length() == 0)
				return null;

			original = original.trim(); 
		
				
			Ref_Address address = Ref.getAddress2(original);     		 // See if address is in our list
			if(address != null)                                          // If it is...
				return address;
			else
				return null;
			

		}
	 
	public static EntityManagerFactory getFactory_nieuw() {
		return factory_nieuw;
	}



	public static void setFactory_nieuw(EntityManagerFactory factory_nieuw) {
		Utils.factory_nieuw = factory_nieuw;
	}



	public static EntityManager getEm_nieuw() {
		return em_nieuw;
	}



	public static void setEm_nieuw(EntityManager em_nieuw) {
		Utils.em_nieuw = em_nieuw;
	}


	public static EntityManagerFactory getFactory_import() {
		return factory_import;
	}


	public static void setFactory_import(EntityManagerFactory factory_import) {
		Utils.factory_import = factory_import;
	}


	public static EntityManager getEm_import() {
		return em_import;
	}


	public static void setEm_import(EntityManager em_import) {
		Utils.em_import = em_import;
	}

	
	
	
}
