package nl.iisg.hsnlinks;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;




import java.util.HashMap;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Utils {
	
	
	static ArrayList<INDIVIDUAL>           iL = new ArrayList<INDIVIDUAL>();
	static ArrayList<CONTEXT>             iCL = new ArrayList<CONTEXT>();
	static ArrayList<CONTEXT_CONTEXT>    iCCL = new ArrayList<CONTEXT_CONTEXT>();
	
	static ArrayList<String>               mesTxt  = new ArrayList<String>(); 
	static ArrayList<String>               mesType = new ArrayList<String>(); 
	static ArrayList<Integer>               mesNr = new ArrayList<Integer>(); 
	
	static HashMap<Integer, Integer>      issuedMesNrs = new HashMap<Integer, Integer>();

	
	static int Id_C;
	static int Old_id_C;

	
	
	public static void addContext(Connection connection, CONTEXT context){
		
		iCL.add(context);
		if(iCL.size() >= 1000){
			writeCList(connection);
			iCL.clear();
		}
		
	}

	public static void addContextContext(Connection connection, CONTEXT_CONTEXT cc){
		
		iCCL.add(cc);
		if(iCCL.size() >= 1000){
			writeCCList(connection);
			iCCL.clear();
		}
		
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

	private static void writeCList(Connection connection){
		
		String insertStatement =
				"insert into links_ids.context (Id_D, Id_C, Source, Type, Value, date_type, estimation, day, month, year) values(\"";
		
		for(CONTEXT c: iCL){
			
			// Front part
			
			insertStatement += c.getId_D();
			insertStatement += "\", \"";
			
			insertStatement += c.getId_C();
			insertStatement += "\", \"";
			
			insertStatement += c.getSource();
			insertStatement += "\", \"";
			
			insertStatement += c.getType();
			insertStatement += "\", \"";
			
			insertStatement += c.getValue();
			insertStatement += "\", \"";			
						
			
			// Timestamp part

			insertStatement += c.getDate_type();
			insertStatement += "\", \"";
			
			insertStatement += c.getEstimation();
			insertStatement += "\", \"";
			
			insertStatement += c.getDay();
			insertStatement += "\", \"";
			
			insertStatement += c.getMonth();
			insertStatement += "\", \"";
			
			insertStatement += c.getYear();
			insertStatement += "\"), (\"";
			
		}
		
		insertStatement = insertStatement.substring(0, insertStatement.length() - 4);
		
		//Utils.executeQ(connection, insertStatement);
		
		try {
			Statement s = (Statement) connection.createStatement ();
			System.out.println(insertStatement.substring(0,250));
			System.out.println(" Inserted number of lines: " + s.executeUpdate(insertStatement));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Utils.closeConnection(connection);
			e.printStackTrace();
		}
		
	}
	
	private static void writeCCList(Connection connection){
		
		String insertStatement =
				"insert into links_ids.context_context (Id_D, Id_C_1, Id_C_2, Source, relation, date_type, estimation, day, month, year) values(\"";
		
		for(CONTEXT_CONTEXT cc: iCCL){
			
			// Front part
			
			insertStatement += cc.getId_D();
			insertStatement += "\", \"";
			
			insertStatement += cc.getId_C_1();
			insertStatement += "\", \"";
			
			insertStatement += cc.getId_C_2();
			insertStatement += "\", \"";
			
			insertStatement += cc.getSource();
			insertStatement += "\", \"";
			
			insertStatement += cc.getRelation();
			insertStatement += "\", \"";
			
			
			// Timestamp part

			insertStatement += cc.getDate_type();
			insertStatement += "\", \"";
			
			insertStatement += cc.getEstimation();
			insertStatement += "\", \"";
			
			insertStatement += cc.getDay();
			insertStatement += "\", \"";
			
			insertStatement += cc.getMonth();
			insertStatement += "\", \"";
			
			insertStatement += cc.getYear();
			insertStatement += "\"), (\"";
			
		}
		
		insertStatement = insertStatement.substring(0, insertStatement.length() - 4);
		
		try {
			Statement s = (Statement) connection.createStatement ();
			System.out.println(insertStatement);
			s.execute(insertStatement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Utils.closeConnection(connection);
			e.printStackTrace();
		}
		
	}
	
	
	
	public static Connection connect(String dataBase){
		
		Connection c = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection("jdbc:mysql:" + dataBase);
			//c.setAutoCommit(false); 

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		
		}
		 catch (SQLException e) {
				e.printStackTrace();
				System.exit(-1);
		}
			
		return c;
	}
	
	public static void closeConnection(Connection connection){
		
		try {
			connection.close();
		}
		 catch (SQLException e) {
				e.printStackTrace();
				System.exit(-1);
		}
	}
	
	public static void commitConnection(Connection connection){
		
		try {
			connection.commit();
		}
		 catch (SQLException e) {
				closeConnection(connection);
				e.printStackTrace();
				System.exit(-1);
		}
	}	
	
	public static void executeQN(Connection connection, String s){	
		
		//System.out.println("executeQN: " + s);
		
		try {
			java.sql.Statement statement = connection.createStatement();

			statement.execute(s);
			//connection.commit();
		}  catch (SQLException e) {
			System.out.println(s);
			System.out.println("-->" + e.getErrorCode());
				e.printStackTrace();
				Utils.closeConnection(connection);
				System.exit(-1);
		}
	}

	public static void executeQ(Connection connection, String s){	

		//System.out.println(s);

		try {
			java.sql.Statement statement = connection.createStatement();
			
			//System.out.println(s);

			statement.execute(s);
			//connection.commit();
		}  catch (SQLException e) {
			System.out.println(s);
			System.out.println("-->" + e.getErrorCode());
				e.printStackTrace();
				Utils.closeConnection(connection);
				System.exit(-1);
		}
	}

	public static void executeQI(Connection connection, String s){	

		//System.out.println("executeQI: " + s);

		try {
			java.sql.Statement statement = connection.createStatement();
			//System.out.println("+++-->" + s);
			statement.execute(s);
			SQLWarning warning = statement.getWarnings();
			//if(warning == null) System.out.println("No warnings");
			while (warning != null) {
				System.out.println(" Warning: " + warning.getMessage());
				warning = warning.getNextWarning();
			}

			//connection.commit();
		}  catch (SQLException e) {
			;
		}
	}

	public static void loadMessages(Connection connection){
		
		
		
		
		try {
			
			String qm = "SELECT * from hsn_msg.ref_meldingen_in";
			Statement sm = (Statement) connection.createStatement ();
			sm.executeQuery(qm);
			ResultSet rcm = sm.getResultSet();

			while (rcm.next()){
				mesTxt.add(rcm.getString("Inhoud"));
				mesType.add(rcm.getString("Fouttype_nw"));
				mesNr.add(rcm.getInt("Foutnr_nw"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
	}
	public static void createMessageTable(Connection connection){
		
		String create = 
				
				 
				 "create table IF NOT EXISTS  hsn_msg.bfout9ft" +
				 "(" +
				 
				 " RecordID INT AUTO_INCREMENT, " +
				 " Primary Key(RecordID), " +

				 "FTTYPE       varchar(2)," +
				 "FTCODE       smallint," +
				 "FOUT         varchar(255)" +


				 ");"; 
		
		executeQ(connection, create);		
		
		executeQ(connection, "truncate hsn_msg.bfout9ft");		
		
	}

	public static void message(Connection connection, int Id, String ... args){
		
		
		Integer mesC = issuedMesNrs.get(Id);
		if(mesC == null)
			issuedMesNrs.put(Id,1);
		else{			
			if(mesC < 10)
				issuedMesNrs.put(Id,++mesC);
			else
				return;
		}
			
		
		String txt = null;
		String type = null;
		int    nr = 0;
		
		int y = 0;
		
		for(int x: mesNr){
			if(x == Id){
				txt = mesTxt.get(y);
				type = mesType.get(y);
				nr = mesNr.get(y);
				break;
			}
			y++;
		}
		
		if(txt == null) return;
		
		
		String[] parts = txt.split("[<>]");
		
		
		String t = "";
		
		
		int z = 0;
		for(String s: parts){
			
			if((z % 2) == 0)
					t = t + s;
			else{				
				if(args[z / 2] != null){
					
					t = t + args[z / 2];
				}
				else
					t = t + "???";
				
				
			}
			
			z++;
		}
		

		insertMessage(connection,  type, nr, t);
		
	}
	public static void insertMessage(Connection connection,  String ftType, int ftCode, String message){
		
		String insert = 
				 
				 "insert into hsn_msg.bfout9ft (FTTYPE, FTCODE, FOUT) values " +
				 "(" +
						 
				 
				 "\"" +			 
				 ftType +				 
				 "\"" + 
				 				 
				 "," +
				 
				 "\"" +				 
				 ftCode +
				 "\"" + 
				 
				 "," +
				 
				 "\"" + 
				 message +
				 "\"" + 
				 
				 ");"; 
		
		executeQ(connection, insert);		
	}

	
	public static int getOld_id_C() {
		return Old_id_C;
	}

	public static void setOld_id_C(int old_id_C) {
		Old_id_C = old_id_C;
	}
	
	public static int getId_C() {
		
		//int x = 1/0;
	
		return Id_C++;
	}

	public static void setId_C(int id_C) {
		Id_C = id_C;
	}

}

