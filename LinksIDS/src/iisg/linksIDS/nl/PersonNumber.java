package iisg.linksIDS.nl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.security.auth.login.AccountNotFoundException;

import org.w3c.dom.stylesheets.LinkStyle;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;
//import com.mysql.jdbc.Statement;
// SELECT * FROM links_match.personNumbers as X , links_cleaned.person_c as Y where X.id_person = Y.id_person order by person_number
public class PersonNumber implements Runnable {

	//static HashMap<Integer, HashSet<Integer>>   personNumberToP_IDs     = new HashMap<Integer, HashSet<Integer>>();  
	//static HashMap<Integer, Integer>            personNumbers = new HashMap<Integer, Integer>();
	static String                               insStmt   = null;	
	//static int []                               personNumber = null;
	//static HashSet<Integer> []                  id_person = null;
	//static ArrayList<Integer>                     onlySelf = new ArrayList<Integer>();
	static int                                  max_id_person = 0;
	static int                                  max_id_matches = 100 * 1000 * 1000;
	static ArrayList<Integer>[]                 aliases = null;
	
	
	
    static BlockingQueue<ArrayList<Integer>>    queue = new LinkedBlockingQueue<ArrayList<Integer>>(1024);
    static Integer                              personNumbersWritten = new Integer(0);
	private static ArrayList<Integer>    		pLStop         = new ArrayList<Integer>(); 

    static final int                            numberOfThreads =  5;
    static final int                            accepted_Levenshtein = 0;
    
	private static String sp01                         = "%s";                
	private static String sp02                         = sp01 + sp01;             
	private static String sp03                         = sp02 + sp02;             
	private static String sp04                         = sp03 + sp03;             
	private static String sp05                         = sp04 + sp04;             
	private static String sp06                         = sp05 + sp05;             
	private static String sp07                         = sp06 + sp06;             
	private static String sp08                         = sp07 + sp07;             
	private static String sp09                         = sp08 + sp08;             
	private static String sp10                         = sp09 + sp09; // 512  
	private static String sp11                         = sp10 + sp10; // 1024 
	private static String sp12                         = sp11 + sp11; // 2048 
	private static String sp13                         = sp12 + sp12; // 4096 
	private static String sp14                         = sp13 + sp13; // 8192 
	private static String sp15                         = sp14 + sp14; // 16384 
	private static String sp16                         = sp15 + sp15; // 32768 
	private static String sp17                         = sp16 + sp16; // 65536 
	
	
	private static String userid;
	private static String passwd; 
	private static String server;
	private static Connection connection; 
	
    public void run(){
    	
    	//Connection connection = Utils.connect("//hebe/links_match?user=linksbeta&password=betalinks");
    	//Connection connection = Utils.connect("//194.171.4.70" + "links_match?user=" + userid + "&password=" + passwd); //194.171.4.70 is the 154
    	Connection connection = Utils.connect2(server, Constants.links_ids, userid,  passwd); //194.171.4.70 is the 154
    	String name = Thread.currentThread().getName();
    	
    	while(true){
    		
    		ArrayList<Integer> values = null;
			try {
				 values = queue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
				Utils.closeConnection(connection);
				System.exit(-1);
			}
    		if(values == pLStop){
    			Utils.commitConnection(connection);
    			Utils.closeConnection(connection);
    			return;
    		}

    		write(values, connection);
    		//Utils.commitConnection(connection);
    		
    		synchronized (personNumbersWritten) {
    			personNumbersWritten += 16384;
        		System.out.println("Written " + personNumbersWritten + " person numbers");
    	    }
    	}
    }    

	public static void personNumber(String[] args){
		
		if(args.length > 2){
			server = args[0].trim();
			userid = args[1].trim();
			passwd = args[2].trim();
			//connection = Utils.connect("//194.171.4.70" + "links_ids?user=" + userid + "&password=" + passwd); //194.171.4.70 is the 154

			// Connect to Links_IDS
			//connection = Utils.connect(db + Constants.links_ids +"?user=" + userid + "&password=" + passwd); //194.171.4.70 is the 154
			connection = Utils.connect2(server, Constants.links_ids, userid,  passwd); //194.171.4.70 is the 154
			if(connection == null){
				System.out.println("Invalid User/password/server");
				System.exit(-1);
			}

		}
		
		System.out.println("personMumber Start");
		
    	//Connection connection = Utils.connect(db + "links_ids?user=" + userid + "&password=" + passwd);
		initDB(connection);
		
    	//connection = Utils.connect(db + "links_match?user=" + userid + "&password=" + passwd);
    	connection = Utils.connect2(server, Constants.links_match, userid,  passwd);

		System.out.println("Reading matches");
		
		int totalCount = 0;
		int effectiveCount = 0;
		int pageSize = 1 * 1000 * 1000;
outer:	for(int i = 0; i < max_id_matches; i += pageSize){
			try {
				System.out.println("Scanning matches with id_matches in [" + i + ", " + (i + pageSize) + ")");
				java.sql.Statement statement = connection.createStatement();
				//String select = "select X.ego_id, X.mother_id, X.father_id, Y.ego_id, Y.mother_id, Y.father_id" +
				//		" from links_match.matches, links_base.links_base as X,  links_base.links_base as Y " +
				//		" where X.id_base = id_linksbase_1 and " +
				//		"       Y.id_base = id_linksbase_2" + 
				//		" limit " + i + ","  +  pageSize;
				String select = "select M.id_matches, " +  
						" X.ego_id,     Y.ego_id,    " + 
						" X.mother_id,  Y.mother_id,  M.value_familyname_mo," +
						" X.father_id,  Y.father_id,  M.value_familyname_fa," + 
						" X.partner_id, Y.partner_id, M.value_familyname_pa" +
						" from "
						+ " links_match.matches as M, "
						+ " links_prematch.links_base as X, "
						+ " links_prematch.links_base as Y " +
						" where " + 
						" M.ids = 'y' and " +
						" X.id_base = id_linksbase_1 and " +
						" Y.id_base = id_linksbase_2 and " + 
						" M.id_matches >= " + i + "  and " + 
						" M.id_matches <  " + (i + pageSize) ;
				
                        /*				
				        + " and (X.ego_id < "     + max_id_person + " or X.ego_id IS NULL)"
				        + " and (X.mother_id < "  + max_id_person + " or X.mother_id IS NULL)"
				        + " and (X.father_id < "  + max_id_person + " or X.father_id IS NULL)"
				        + " and (X.partner_id < " + max_id_person + " or X.partner_id IS NULL)"

				        + " and (Y.ego_id < "     + max_id_person + " or X.ego_id IS NULL)"
				        + " and (Y.mother_id < "  + max_id_person + " or X.mother_id IS NULL)"
				        + " and (Y.father_id < "  + max_id_person + " or X.father_id IS NULL)"
				        + " and (Y.partner_id < " + max_id_person + " or X.partner_id IS NULL)";
                        */ 
				        

				
				//System.out.println(select);

				ResultSet r = statement.executeQuery(select);
				int count = 0;
				
				int x = 0;
				int y = 0;
				
				while (r.next()) {

					x = r.getInt("X.ego_id");
					y = r.getInt("Y.ego_id");
					//System.out.println("X.ego_id = " + x + ", Y.ego_id = " + y);
					if(x != 0 && y != 0) 
						effectiveCount += add(x, y);

					if(r.getString("M.value_familyname_mo") != null){
						x = r.getInt("X.mother_id");
						y = r.getInt("Y.mother_id"); 
						if(x != 0 && y != 0) 
							effectiveCount += add(x, y);
					}

					if(r.getString("M.value_familyname_fa") != null){
						x = r.getInt("X.father_id");
						y = r.getInt("Y.father_id");
						if(x != 0 && y != 0) 
							effectiveCount += add(x, y);
					}


					if(r.getString("M.value_familyname_pa") != null){
						x = r.getInt("X.partner_id");
						y = r.getInt("Y.partner_id");
						if(x != 0 && y != 0) 
							effectiveCount += add(x, y);
					}

					count++;		  
					if(count % 1000 == 0)
						System.out.println("Read " + count + " matches in this range");

				}

				totalCount += count;
				//System.out.println("Read total " + totalCount + " matches");

			} catch (SQLException e) {

				System.out.println(e.getMessage());
				Utils.closeConnection(connection);
				System.exit(-1);
			}
		}
		
		System.out.println("Read " + totalCount + " matches");
		System.out.println("" + effectiveCount + " person numbers changed");
		
		// write person numbers to table
		
		//if(1==1) System.exit(0);
		
		//Utils.executeQI(connection, "drop index nr on personNumbers");
		
		ArrayList<Thread> a = new ArrayList<Thread>();
		
		for(int i = 0; i < numberOfThreads; i++){
			Thread p = new Thread(new PersonNumber());
			p.start();
			a.add(p);
		}
		
		int count = 0;
		
		ArrayList<Integer> i = new ArrayList<Integer>();

//		for (Entry<Integer, Integer> entry : personNumbers.entrySet()) {		
		
		
	    for (int j = 0; j < aliases.length; j++) {
	    	
	    	
	    	//System.out.println("Adding");
	    	
	    	if(aliases[j] == null) continue;
			count++;
			
			i.add(j);
			if(aliases[j] == null)
				i.add(j);
			else	
				i.add(aliases[j].get(0));
			if(count % 16384  == 0){
				try {
					queue.put(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Utils.closeConnection(connection);
					System.exit(-1);
				}
				i = new ArrayList<Integer>();
			}
		}
		if(i.size() > 0){
			try {
				queue.put(i);
			} catch (InterruptedException e) {
				Utils.closeConnection(connection);
				e.printStackTrace();
				System.exit(-1);
			}
			//executeQ(connection, insStmt);
		}

		
		aliases = null;
		
		
		try {
			for(int j = 0; j < numberOfThreads; j++)
				queue.put(pLStop);
			
		} catch (InterruptedException e) {
			Utils.closeConnection(connection);
			e.printStackTrace();
			System.exit(-1);
		}
		
		try {
			for(int j = 0; j < numberOfThreads; j++)
				a.get(j).join();
		} catch (InterruptedException e1) {
			Utils.closeConnection(connection);
			e1.printStackTrace();
			System.exit(-1);
		}

		System.out.println("Written " + count + " person numbers");

		Utils.executeQ(connection, "create index nr on links_ids.personNumbers(person_number)");
		


		Utils.closeConnection(connection);
		System.out.println("\nFinished creating person numbers");
	}
	
	private static void write(ArrayList<Integer> iL, Connection connection){
		
		//System.out.println("Write output table");	
		
		String insStmt = "insert into personNumbers values ";
		
		ArrayList<String> a = new ArrayList<String>();
		for(int i = 0; i < iL.size(); i += 2){			
			a.add(String.format("(%d, %d),", iL.get(i), iL.get(i + 1)));			
		}

		
		String s = "";
		if(a.size() %  16384 == 0)
			insStmt = String.format(sp15, a.toArray());
		else{
			
			String fmt = "";
			for(int i = 0; i < a.size(); i++)
				fmt += "%s";
			insStmt = String.format(fmt, a.toArray());
		}

		insStmt = insStmt.substring(0,  insStmt.length() - 1);
		String insStmt2 = "insert into personNumbers values" + insStmt;
		//System.out.println(insStmt2);
		Utils.executeQ(connection, insStmt2);
		
		
		
	}
	
	
	private static int add(int x, int y){
		
				
		if(x >= max_id_person  || y >= max_id_person) return(0);        // This could happen if table matches is out of synch with table person_c,
		                                                                // we need to exclude it because it will give an 'array index out of bound' error
		
		if(x == 0      || y == 0 ) return(0);                           // id_person starts a 1

		
		if(aliases[x] != null && (aliases[x] == aliases[y])) return(0); // This is a 'redundant' match
		
		if(aliases[x] != null && aliases[x].size() > 100) return 0;     // This means a person should not occur in more than 100 certificates
		
		if(aliases[y] != null && aliases[y].size() > 100) return 0;     // This means a person should not occur in more than 100 certificates

	   ArrayList<Integer> h = null;
	   
		if(aliases[x] == null){                                         // if x did not have aliases up to now
			h = new ArrayList<Integer>();                               // aliases[x] needs an ArrayList<Integer> (containing at least x and y) (1) 
			aliases[x] = h;                                             // indicate that x now has aliases other than x itself 
			h.add(x);                                                   // add x to it (y will be added to it below (in (2))
		}

		if(aliases[y] == null){                                         // if y did not have aliases up to now
			aliases[y] = aliases[x];                                    // this gives it an ArrayList<Integer> containing at least x, but possibly more elements if the test (1) was false,
			                                                            // in which case it will contain all already found aliases of x (including x itself) 
			aliases[x].add(y);                                          // but y still needs to be added to it (2) We could just as well write 'aliases[y].add(y)'
			
		}
		else{                                                  
			for(Integer y1: aliases[y]){                                // Loop through all aliases of y
				aliases[y1] = aliases[x];                               // every alias will from now on use the (common for x and y and all their aliases) aliases set of x... (3)				                                                         
				aliases[x].add(y1);                                     // ... to which every alias of y must be added 
				                                                        // the last time (3) is executed the last reference to aliases[y] is overwritten,  aliases[y] -> garbage collection
			}
		}
		
		//if(aliases[1] != null) System.out.println(1/0);

		return(1); 
	}
	
	private static void initDB(Connection connection){		
		
		try {

			
			System.out.println("initDB...");
			// java.sql.Statement statement = connection.createStatement();

			max_id_person = getHighestID_Person(connection);
			
			System.out.println("Call initializePersonNumbers, connection = " + connection);

			boolean firstTime = false;
		    
		    try {
		      ResultSet rs = connection.createStatement().executeQuery("select * from personNumbers_save where 1=0");
		      System.out.println("Not first time");
		      
		    }
		    catch (Exception e ) {
		      // table does not exist or some other problem
		      //e.printStackTrace();
		      System.out.println("First time");
		      firstTime = true;		      
		    }
			
		    if(firstTime){
				// Next statements only first time
				createTable(connection);                 // only first time	
				initializePersonNumbers(connection);     // only first time	
		    }
			
			
			aliases = new ArrayList[max_id_person + 1]; 
			ArrayList<Integer> h = new ArrayList<Integer>();
			int prevPersonNumber = - 1;
			//java.sql.Statement statement1 = connection.createStatement();
			
			Utils.executeQI(connection, "create index nr on personNumbers(person_number)");

			System.out.println("Reading person numbers");
			
			int count = 0;
			int countpn = 0;
			int pageSize = 1 * 1000 * 1000;
			for(int i = 0; i <= max_id_person; i += pageSize){
				String select = "select id_person, person_number from personNumbers where person_number > " + i + " and person_number <= " +  (i + pageSize) + " group by id_person order by person_number"; 
				System.out.println(select);
				ResultSet r = connection.createStatement().executeQuery(select);
				while (r.next()) {
					count++;
					if(r.getInt("person_number") != prevPersonNumber){
						countpn++;
						if(h.size() == 1){
							aliases[prevPersonNumber] = null;
							h.clear();                     // we can reuse h because it was not inserted into the aliases array
						}
						else{							
							for(Integer y1: h)
								aliases[y1] = h;
							h = new ArrayList<Integer>();  // we can *not* reuse the old h because it was inserted into the aliases array
						}
						prevPersonNumber = r.getInt("person_number");
					}
					h.add(r.getInt("id_person")); // h will contain all id_person that have the same person number
				}
				if(h != null){
					if(h.size() == 1){
						aliases[prevPersonNumber] = null;
					}
					else{
						for(Integer y1: h)
							aliases[y1] = h;
					}
				}
				r.close();
				connection.createStatement().close();
			}

			System.out.println("Read   " + count + " persons, " + countpn + " peron numbers");
			
			// Copy s to s_save and truncate s

			Utils.executeQI(connection, "drop table personNumbers_save ");
			Utils.executeQ(connection, "rename table personNumbers to personNumbers_save ");
			createTable(connection);	// create new table 'personNumbers'
			
			//s = "drop index nr on personNumbers ";
			//System.out.println(s);
			//connection.createStatement().execute(s);
		}
		catch (SQLException e) {
			e.printStackTrace();
			Utils.closeConnection(connection);
			System.exit(-1);
		}
	}

	private static void print(HashMap<Integer, HashSet<Integer>> hm){
		
		for (Entry<Integer, HashSet<Integer>> entry : hm.entrySet()) {
		    Integer key = entry.getKey();
		    System.out.println("" + key);
		    HashSet<Integer> h = (HashSet<Integer>) entry.getValue();
		    if(h == null){
		    	System.out.println("   null");
		    	continue;
		    }
		    
		    for(Integer i: h){		    	
			    System.out.println("   " + i);
		    	
		    }
		}
	}
	
	private static int getHighestID_Person(Connection connection){
		
		System.out.println("Identifying highest id_person");
		
		ResultSet r = null;
		try {
			//r = connection.createStatement().executeQuery("select max(id_person) as m FROM links_ids.personNumbers");
			r = connection.createStatement().executeQuery("select max(id_person) as m FROM links_cleaned.person_c");
			while (r.next()) {
				System.out.println("Highest id_person = " + r.getInt("m"));
				return(r.getInt("m"));
			}
			r.close();
			//connection.createStatement().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		
		return -1;
		
	}
	
	private static void createTable(Connection connection){
		try {
			
			//System.out.println("create Table, connection = " + connection);
			
			java.sql.Statement statement = connection.createStatement();
			
			//System.out.println("create Table, connection = " + connection);

			
			Utils.executeQI(connection, "drop table personNumbers");
			//System.out.println("create Table 2");
			Utils.executeQ(connection, "create table personNumbers (id_person int, person_number int)");
			//System.out.println("create Table 3");

			//String indexNr = "create index nr on links_IDS.personNumbers(person_number)";

			//s = "create unique index i on links_match.personNumbers(id_person)";
			//System.out.println(s);
			//statement.execute(s);
			

			
			connection.commit();
			
			//System.out.println("create Table 4");

		}  catch (SQLException e) {
				e.printStackTrace();
				Utils.closeConnection(connection);
				System.exit(-1);
			
		}
		
	}
	
	private static void initializePersonNumbers(Connection connection){
		
		System.out.println("insert into links_ids.personNumbers (select id_person, id_person from links_cleaned.person_c where id_person <= " + max_id_person + ")");
		Utils.executeQ(connection, "insert into links_ids.personNumbers (select id_person, id_person from links_cleaned.person_c where id_person <= " + max_id_person + ")");

	}
}