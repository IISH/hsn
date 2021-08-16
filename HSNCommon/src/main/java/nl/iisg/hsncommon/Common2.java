package nl.iisg.hsncommon;

import java.io.File;
import java.io.FileInputStream;
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
import javax.persistence.GeneratedValue;
import javax.persistence.Table;


public class Common2 {

	
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
		
        System.out.println("In create Objects");

	
			
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

			
			// Get MSAccess table information
			
            String connURL = "jdbc:ucanaccess://" + inputDirectory + File.separator + "PK.accdb";
			 
            Connection conn = DriverManager.getConnection(connURL, "", "");
	        Statement s = conn.createStatement();

	        // Fetch table
	        
	        String selTable = "SELECT * FROM " + tabName;
	        s.execute(selTable);
	        ResultSet rs = s.getResultSet();
	        ResultSetMetaData rsmd = rs.getMetaData();
	        
	        int numberOfMSAFields = rsmd.getColumnCount();
	        
			String [] fieldNamesMSA = new String[numberOfMSAFields];
			String [] fieldTypesMSA = new String[numberOfMSAFields]; 
			
			for(int i=0; i<numberOfMSAFields; i++){
				fieldNamesMSA[i] = rsmd.getColumnName(i + 1);
				fieldTypesMSA[i] = rsmd.getColumnTypeName(i + 1);
			}
			

	         

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

						
						//System.out.println("index1 = " + index1);
						//System.out.println("columnAnnotatedVariableToMSAField[index1] = " + columnAnnotatedVariableToMSAField[index1]);
		                //System.out.println("fieldNamesMSA[columnAnnotatedVariableToMSAField[index1]] =  " + fieldNamesMSA[columnAnnotatedVariableToMSAField[index1]]);
		                //System.out.println("fieldTypesMSA[columnAnnotatedVariableToMSAField[index1]] =  " + fieldTypesMSA[columnAnnotatedVariableToMSAField[index1]]);

					
						if(fieldTypesMSA[columnAnnotatedVariableToMSAField[index1]].equalsIgnoreCase("DOUBLE")) 
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
					
						if(fieldTypesMSA[columnAnnotatedVariableToMSAField[index1]].equalsIgnoreCase("DOUBLE")) 
							e[0] = rs.getInt(columnAnnotatedVariableToMSAField[index1] + 1);
						
						if(fieldTypesMSA[columnAnnotatedVariableToMSAField[index1]].equalsIgnoreCase("VARCHAR")) 
							e[0] = rs.getString(columnAnnotatedVariableToMSAField[index1] + 1);
						
						if(fieldTypesMSA[columnAnnotatedVariableToMSAField[index1]].equalsIgnoreCase("TIMESTAMP")){
							
							Date d = rs.getDate(columnAnnotatedVariableToMSAField[index1] + 1);
							String ss = d.toString();							
							String u =  ss.substring(8, 10) + "-" + ss.substring(5, 7) + "-" + ss.substring(0, 4);

							e[0] = u;
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
	        
			System.out.println("Read    " + tabName + " from " + inputDirectory + "\\PK.accdb: " + count + " rows");	

	        s.close();
	        conn.close();
	        
	        return a;
			}
			catch (NoSuchMethodException e) {
				System.out.println(e.toString());
				System.exit(-1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
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

	        
	}}	
			
	
	
