package nl.iisg.hsncommon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;


public class DBFHandler {

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
						
						// create object to hold value from DBF Column
						
						
						
						Object [] e =  new Object[1];
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
	        e.printStackTrace();
		}
		catch (IOException e) {
	        e.printStackTrace();
		}
	
		return null;
	}

}
