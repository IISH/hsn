package nl.iisg.dts;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import com.linuxense.javadbf.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class Convert{
/**
 * 
 * This program takes a .dbf file as input.
 * It creates a similar table in MySQL 
 * Then, it copies all rows from the .dbf file to
 * the newly created table.
 * 
 * Restrictions: Numerical data is always truncated to integers
 * 
 * 
 * @param  
 * 
 * Inputfile
 * Userid
 * Password
 * Database
 * [New table name]
 * [Column specification]
 * 
 */
	
		public static void main( String args[]) {
		
		
		String inputFile =     args[0];
		String database =      args[1];
		String userid =        args[2];
		String password =      args[3];
		
		

		String[] months = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};	
		try {

			// create a DBFReader object
			//
			InputStream inputStream  = new FileInputStream(inputFile); // take dbf file as program argument
			DBFReader reader = new DBFReader(inputStream); 

			// Start create statement
			
			

			String createTable = "CREATE TABLE IF NOT EXISTS ";

			String[] a =  inputFile.split("[.\\\\]");

			createTable += a[a.length - 2];
			String tableName = a[a.length - 2];

			System.out.println(tableName);
			createTable += " (";

			int numberOfFields = reader.getFieldCount();

			ArrayList<String>  type  = new ArrayList();
			ArrayList<String>  name  = new ArrayList();

			for( int i=0; i<numberOfFields; i++) {

				DBFField field = reader.getField( i);

				createTable += field.getName();
				//System.out.println(field.getName());
				createTable += " ";
				name.add(field.getName());

				if(field.getDataType() == DBFField.FIELD_TYPE_C){
					createTable += "VARCHAR(" +(new Integer(field.getFieldLength()).toString() + "), ");
					type.add("C");
				}
				else
					if(field.getDataType() == DBFField.FIELD_TYPE_N || field.getDataType() == DBFField.FIELD_TYPE_F){
						createTable += "INT, ";	  
						type.add("N");
					}
					else
						if(field.getDataType() == DBFField.FIELD_TYPE_D){
							createTable += "VARCHAR(10), ";
							type.add("D");
						}

			}

			createTable = createTable.substring(0, createTable.length()-2) + ");";

			Connection conn = getConnection(database, userid, password);
			Statement stmt = conn.createStatement();

			String query = createTable;
			stmt.executeUpdate(query);	  

			// Truncate table
			
			stmt.executeUpdate("TRUNCATE TABLE " + tableName);	
			
			
			// Now, let us start reading the rows

			Object []rowObjects;

			String insertStmt = "INSERT INTO " + tableName + " values"; 
			String values = null;
			int count = 0;
			while( (rowObjects = reader.nextRecord()) != null) {

				values = " (";
				for( int i=0; i<rowObjects.length; i++) {

					if(rowObjects[i] != null){

						if(type.get(i).equals("N")){

							String s = rowObjects[i].toString();
							int j = s.indexOf(".");
							if(j >= 0)
								s = s.substring(0, j);
							values += "\"";
							values += s;
							values += "\", ";

						}

						if(type.get(i).equals("C")){

							values += "\"";
							String x = (((String)rowObjects[i]).trim()).replaceAll("\\\\", "\\\\\\\\"); // Change '\' to '\\' This assumes no escape character in input
							values += x.replaceAll("\"", "\\\\\"");                                     // Change '"' to '\"'
							//values += ((String)rowObjects[i]).trim(); 

							values += "\", ";
						}
						if(type.get(i).equals("D")){
							
							// Example: Fri Nov 12 00:00:00 CET 2005 (this becomes 12-11-2005)

							String s = rowObjects[i].toString();

							String [] b = s.split("[ ]");
							int month = 0;

							for(int k = 1; k <= 12; k++){

								if(months[k].equals(b[1])){

									month = k;

									break;
								}
							}

							String t = String.format("%02d-%02d-%04d", (new Integer(b[2])).intValue(),(new Integer(month)).intValue(), (new Integer(b[5])).intValue());


							values += "\"";
							values += t;
							values += "\", ";


						}

					}
					else
						if(type.get(i).equals("D"))
							values += "0000/00/00,";							
						else
							if(type.get(i).equals("N"))
								values += "0,";
							else
								values += "\"\", ";
				}
				values = values.substring(0, values.length()-2) + "),";

				insertStmt += values;

				count++;
				if(count % 1000 == 0){
					insertStmt = insertStmt.substring(0, insertStmt.length()-1) + ";";	
					//System.out.println(insertStmt);
					stmt.executeUpdate(insertStmt);
					System.out.println("Processed " + count + " lines");
					insertStmt = "INSERT INTO " + tableName + " values";					
					values = null;
					
				}

			}
			
			if(values != null){
				insertStmt = insertStmt.substring(0, insertStmt.length()-1) + ";";
				stmt.executeUpdate(insertStmt);
				
				//System.out.println("Processed " + count + " lines");
			}
			
			System.out.println("Processed " + count + " lines");

			inputStream.close();
			conn.close();
		}
		catch( DBFException e) {

			System.out.println( e.getMessage());
			System.exit(999);
		}
		catch( IOException e) {

			System.out.println( e.getMessage());
			System.exit(999);

		}
		catch( SQLException e) {
			

			System.out.println(e.getMessage());
			System.exit(999);

		}
		catch( Exception e) {

			System.out.println( e.getMessage());
			System.exit(999);

		}

	}  
	public static Connection getConnection(String database, String userid, String passwrd) throws Exception {
		String driver = "org.gjt.mm.mysql.Driver";
		//String url = "jdbc:mysql://localhost/" + database;
		String url = database;
		String username = userid;
		String password = passwrd;
			Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

}
