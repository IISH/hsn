package nl.iisg.linksinput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;

import nl.iisg.dts.Convert;

public class LinkInputAndMapping {
	
	public static void main(String[] args){
		
		if (false) {
			File file = new File("h:/sysout.log");
			try {
				PrintStream printStream = new PrintStream(new FileOutputStream(file));	               
				System.setOut(printStream);

			} catch (FileNotFoundException e) {
			}
		}
		
		System.out.println("Start Processing\n");
		
		checkArgs(args);   
		
		ArrayList<String>[] a = parseInputFile(args[0]);
		

		// Read files, create tables, insert rows 
		
		for(String s: a[0])
			if(s.split("\\.").length > 1 && s.split("\\.")[1].equalsIgnoreCase("DBF")){
				String [] b = {s, args[1], args[2], args[3]};  // pass filename plus last three arguments
				System.out.println("\nReading file " + s + "\n");
				Convert.main(b);
			}
			
		
		// Execute queries 

		Statement stmt = null;
		try{
			Connection conn = getConnection(args[1], args[2], args[3]);
			stmt = conn.createStatement();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.exit(999);
		}
		
		for(String s: a[1]){
			System.out.println("\nRunning query: \n");
			System.out.println(s);
			try{
				stmt.executeUpdate(s);
				SQLWarning SQLWarning = stmt.getWarnings();
				while(SQLWarning != null){				
					if(SQLWarning.getMessage() != null)
						System.out.println(SQLWarning.getMessage());
					SQLWarning = SQLWarning.getNextWarning();
				}
				System.out.println("\nReturn Code = 0");
			}
			catch(SQLException e) {
				if(e.getErrorCode() != 1050){ // Table already exists
					System.out.println(e.getMessage());
					System.exit(999);
				}
				System.out.println("\nReturn Code = 1050: Table already exists");
				
			}
			catch(Exception e) {
				
				System.out.println(e.getMessage());
				System.out.println("Program terminated");
				System.exit(999);
			}

			
		}

		
		System.out.println("\nFinished Processing");

		
	}
	
	
	
	private static void checkArgs(String[] args){
		
		if(args.length == 0){
			System.out.println("No input mapping file specified");
			System.exit(10);
		}
		
		File mappingFile = new File(args[0]);
		
		if(!mappingFile.exists()){			
			System.out.println("Input mapping file " + args[0] + " does not exist");
			System.exit(20);
		}

		if(!mappingFile.isFile()){			
			System.out.println("Input mapping file " + args[0] + " is not a file");
			System.exit(20);
		}
		
		if(args.length == 1){			
			System.out.println("No target database specified");
			System.exit(210);
		}
		
		if(args.length == 2){			
			System.out.println("No userid for database specified");
			System.exit(210);
		}
		
		if(args.length == 3){			
			System.out.println("No password for database specified");
			System.exit(210);
		}
		
		if(args.length > 4){			
			System.out.println("Too many arguments specified");
			System.exit(210);
		}
		
		

		
	}
	
	
	/**
	 * @param inputFile
	 * @return array of arrayLists;
	 * 
	 * 	First  arrayList contains files   to read
	 *  Second arrayList contains queries to run
	 */
	
	private static ArrayList[] parseInputFile(String inputFile){
		
		System.out.println("Using input file " + inputFile);

		File file = new File(inputFile);
		String inputMapping = null;
		
		try{
			FileInputStream fin = new FileInputStream(file);
			byte[] b = new byte[((int)file.length())];
			fin.read(b);
			String s = new String(b);
			inputMapping = s;
		}
		catch(IOException ioe)
		{
			System.out.println("IOException reading mapping file " + inputFile);
			System.exit(30);
		}
		
		

		inputMapping = inputMapping.replaceAll("\r\n", " \r\n"); // Add blank before end of line  
		
		// Check that input file sections are specified and in correct order
		
		int i1 = inputMapping.indexOf("[INFO]");
		if(i1 < 0){
			System.out.println("No [INFO] section in mapping file " + inputFile);
			System.exit(40);
		}
		
		int i2 = inputMapping.indexOf("[GLOBAL]");
		if(i2 < 0){
			System.out.println("No [GLOBAL] section in mapping file " + inputFile);
			System.exit(50);
		}
		
		if(i1 > i2){
			System.out.println("[INFO] section must come before [GLOBAL] section in mapping file " + inputFile);
			System.exit(60);
		}
		
		i1 = i2;

		i2 = inputMapping.indexOf("[READ]");
		if(i2 < 0){
			System.out.println("No [READ] section in mapping file " + inputFile);
			System.exit(70);
		}

		if(i1 > i2){
			System.out.println("[GLOBAL] section must come before [READ] section in mapping file " + inputFile);
			System.exit(80);
		}
		
		i1 = i2;
		
		i2 = inputMapping.indexOf("[RUN]");
		if(i2 < 0){
			System.out.println("No [RUN] section in mapping file " + inputFile);
			System.exit(90);
		}

		if(i1 > i2){
			System.out.println("[READ] section must come before [RUN] section in mapping file " + inputFile);
			System.exit(100);
		}

		i1 = i2;
		
		i2 = inputMapping.indexOf("[QUERIES]");
		if(i2 < 0){
			System.out.println("No [QUERIES] section in mapping file " + inputFile);
			System.exit(110);
		}

		if(i1 > i2){
			System.out.println("[RUN] section must come before [QUERIES] section in mapping file " + inputFile);
			System.exit(120);
		}
		
		//
		
		String infoSection   = inputMapping.split("\\[INFO\\]")[1].split("\\[GLOBAL\\]")[0];
		String globalSection = inputMapping.split("\\[INFO\\]")[1].split("\\[GLOBAL\\]")[1].split("\\[READ\\]")[0];
		String readSection   = inputMapping.split("\\[INFO\\]")[1].split("\\[GLOBAL\\]")[1].split("\\[READ\\]")[1].split("\\[RUN\\]")[0];
		String runSection    = inputMapping.split("\\[INFO\\]")[1].split("\\[GLOBAL\\]")[1].split("\\[READ\\]")[1].split("\\[RUN\\]")[1].split("\\[QUERIES\\]")[0];
		String querySection  = inputMapping.split("\\[INFO\\]")[1].split("\\[GLOBAL\\]")[1].split("\\[READ\\]")[1].split("\\[RUN\\]")[1].split("\\[QUERIES\\]")[1];
		
		// parse Global section
		
		ArrayList<String> globalVars = new ArrayList();
		ArrayList<String> globalVals = new ArrayList();

		String [] globalStatements = globalSection.split(";");
		
		for(int i = 0; i < globalStatements.length; i++){
			if(globalStatements[i].trim().length() > 0){
				String[] a = globalStatements[i].split("=");
				if(a.length != 2){
					System.out.println("Invalid entry in [GLOBAL] section: " + globalStatements[i]);
					System.exit(130);
				}
				globalVars.add(a[0].trim());
				globalVals.add(a[1].trim());
			}
		}
		
		// Parse the Read section
		
		ArrayList<String> filesToRead = new ArrayList();
		
		String [] files = readSection.split(";");
		for(int j = 0; j < files.length; j++){
			if(files[j].trim().length() > 0){
				File f = new File(files[j].trim());
				if(!f.exists()){			
					System.out.println("File " + files[j].trim() + " does not exist");
					System.exit(200);
				}
				else{
					filesToRead.add(files[j].trim());
				}
			}
		}

		
		// Parse the Query section
		
		ArrayList<String> queryVars  = new ArrayList();
		ArrayList<String> queryVals  = new ArrayList();
		
		String [] queryStatements = querySection.split("#");


		for(int i = 0; i < queryStatements.length; i++){

			//System.out.println("Statement " + queryStatements[i]);

			if(queryStatements[i].trim().length() > 0){

				String firstWord = null;
				String rest = null;
				
				int index = queryStatements[i].indexOf(' ');
				if(index == -1){
					System.out.println("Invalid entry in [QUERIES] section 1: " + queryStatements[i]);
					System.exit(140);
				}
				else{
					firstWord = queryStatements[i].substring(0, index);
					rest = queryStatements[i].substring(index).trim();
					if(rest == null || rest.trim().length() ==0){
						System.out.println("Invalid entry in [QUERIES] section 2: " + queryStatements[i]);
						System.exit(150);
					}
					
					//System.out.println("Firstword " + firstWord);
					//System.out.println("Rest      " + rest);

					queryVars.add(firstWord.trim());
					queryVals.add(rest.trim());
				}
			} 

		}

		
		// Parse the Run section
		
		ArrayList<String> queriesToRun = new ArrayList();
		
		String [] runStatements = runSection.split("#");
		
		String query = null;
		for(int i = 0; i < runStatements.length; i++){
			
			int queryIndex = -1;

			if(runStatements[i].trim().length() > 0){

				String firstWord = null;
				String rest = null;
				int colonIndex =  runStatements[i].indexOf(':');
				if(colonIndex == -1){
					// Look for query without :
					for(int j = 0; j < queryVars.size(); j++){
						if(queryVars.get(j).equalsIgnoreCase(runStatements[i].trim())){
							queryIndex = j;
							break;
						}
					}	
					if(queryIndex == -1){
						System.out.println("Invalid entry in [RUN] section (no such query 1): " + runStatements[i]);
						System.exit(170);
					}
					query = queryVals.get(queryIndex).trim();
				}
				else{
					firstWord = runStatements[i].substring(0, colonIndex);
					
					// Look for query
					
					for(int j = 0; j < queryVars.size(); j++){
						if(queryVars.get(j).equalsIgnoreCase(firstWord)){
							queryIndex = j;
							break;
						}
					}	
					if(queryIndex == -1){
						System.out.println("Invalid entry in [RUN] section (no such query 2): " + firstWord);
						System.exit(170);
					}
					
					// parse rest
					
					rest = runStatements[i].substring(colonIndex + 1).trim();
					String [] parameters = rest.split(";");
					
					if(parameters.length == 0){
						System.out.println("Invalid entry in [RUN] section (no valid parameters): " + rest);
						System.exit(180);
					}

					ArrayList<String> localVars = new ArrayList();
					ArrayList<String> localVals = new ArrayList();

					for(int j = 0; j < parameters.length; j++){
						
						if(parameters[j].length() > 0){
							
							String [] a = parameters[j].split("=");
							
							if(a.length != 2){
								System.out.println("Invalid entry in [RUN] section 3: ");
								//System.out.println("Invalid entry in [RUN] section 3: " + parameters[j]);
								System.exit(190);
							}
							localVars.add(a[0].trim());
							localVals.add(a[1].trim());
							
						}
					}
					
					// Apply local variables to query
					
					query = queryVals.get(queryIndex);
					
					for(int j = 0; j < localVars.size(); j++)						
						query = query.replaceAll("\\{" + localVars.get(j) + "\\}", localVals.get(j));
						
				}
				
				// Apply global variables to query
				
				for(int j = 0; j < globalVars.size(); j++)						
					query = query.replaceAll("\\{" + globalVars.get(j) + "\\}", globalVals.get(j));
				
				queriesToRun.add(query);
				
			}

		}
		  
		
		// Set return information
		
		ArrayList[] a = new ArrayList[2];
		
		a[0] = filesToRead;
		a[1] = queriesToRun;
		
		return a;		
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
