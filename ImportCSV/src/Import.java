import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Types;

public class Import {

	public static void main(String[] args) {
		// parameters:
		// 
		// userid
		// password
		// database
		// tablename
		// filename
		//

		if(args.length != 5) {
			System.out.println("Parameters: userid, password, tablename,filename ");
			System.exit(-1);
		}

		String userid    = args[0];
		String password  = args[1];
		String database  = args[2];
		String tablename = args[3];
		String filename  = args[4];

		String connectString = "jdbc:mysql://localhost:3306/";

		try{  
			//Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(connectString + database, userid, password);  

			File            file       = new File(filename);    //creates a new file instance  
			FileReader      fr         = new FileReader(file);   //reads the file  
			BufferedReader  br         = new BufferedReader(fr);  //creates a buffering character input stream  
			String line; 
			String insertStmt    = "INSERT INTO " + tablename + " VALUES";
			String selectStmt    = "SELECT * FROM " + tablename;
			int cnt = 0;
			int tot = 0;
			Statement stmt=con.createStatement();
			String   s = insertStmt;
			char     separator = ';';
			
			ResultSetMetaData rsmd = null;
			ResultSet rs = null;
			
			while((line=br.readLine())!=null)  
			{  
				
				tot++;
				if(tot == 1) {
					
					String[] a = line.split(";");
					
					rs = stmt.executeQuery(selectStmt);
					//System.out.println("resultset = " + rs);
					rsmd = rs.getMetaData();
					//System.out.println("rsmd = " + rsmd);
					
					if(a.length != rsmd.getColumnCount()) {
						System.out.println("Table columncount " + rsmd.getColumnCount() + ""
								+ " does not match file columncount " + a.length);
						System.exit(-1);
					}
					
					
					continue;
				}
				
				// remove separator inside fields
				
				boolean insideField = false;
				for(int k = 0; k < line.length(); k++) {
					if(line.charAt(k)== '\"') insideField = !insideField;
					if(insideField && line.charAt(k) == separator) 
						line = line.substring(0, k) + " " + line.substring(k + 1);
					
					
				}

				String[] a = line.split(";");
				s = s + " (";
				int i = 1;
				for(String t: a) {
					//System.out.println(rsmd.getColumnName(i) + " " + rsmd.getColumnType(i) + 
					//		" " + rsmd.getColumnTypeName(i) + " " + t);
					
					//System.out.println(i + "   " + rsmd.getColumnType(i) + "   " +t );
					switch(rsmd.getColumnType(i)) {
					
					case Types.INTEGER:
					case Types.DOUBLE:
						if(t.trim().length() == 0) t = "0";
						break;
						
					case Types.CHAR:
					case Types.VARCHAR:
					case Types.LONGVARCHAR:
						if(t.trim().length() == 0) t = "\"\"";
						if(!t.substring(0,1).equals("\""))             t = "\"" + t;
						if(!t.substring(t.length() - 1).equals("\"")) t =  t + "\"";
					
					}
					
					
					i++;
					
					
					s = s + t + ",";
					//}
				}
				
				// If the line ends in ;;; line.split does not give the last values. We must repair
				
				//System.out.println("BBB " +i + "   " + rsmd.getColumnCount());
				String t = "";
				for(int j = i ; j < rsmd.getColumnCount() + 1; j++) {
					//System.out.println(" Hij Komt hier " + j);
					switch(rsmd.getColumnType(j)) {
					
					case Types.INTEGER:
					case Types.DOUBLE:
						t = "0";
						break;
					case Types.CHAR:
					case Types.VARCHAR:
					case Types.LONGVARCHAR:
						t = "\"\"";
						break;
					}
					if(t.trim().length() == 0) t = "\"\"";
					s = s + t + ",";
				}
				
				
				
				
				s = s.substring(0,s.length() - 1) + "),";
				
				
				cnt++;

				if(cnt == 1000 ) {

					s =  s.substring(0,s.length() - 1) + ";";
					//System.out.println(s);
					stmt.executeUpdate(s); 
					
					s = insertStmt;
					
					cnt = 0;
					System.out.println("Inserted " + (tot - 1) + " rows");

				}
				
			}  
			if(cnt > 0) {
				s =  s.substring(0,s.length() - 1) + ";";
				stmt.executeUpdate(s); 		
				System.out.println("Inserted " + (tot - 1) + " rows");
				System.out.println("Klaar!");
			}


			fr.close();    //closes the stream and release the resources  		 
			con.close();  
		}catch(Exception e){ System.out.println(e);}  
	}  


}

