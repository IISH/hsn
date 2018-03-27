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
import java.util.Set;
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
	static ArrayList<Integer>                   onlySelf = new ArrayList<Integer>();
	static int                                  max_id_person = 0;
	static int                                  max_id_matches = 0;
	static ArrayList<Integer>[]                 aliases = null;
	//static Set<Integer>[]                       aliases = null;
	
	
	
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

		int max_idp = 0;
		int min_idp = max_id_person;

		int rangeCount = 0;
		int totalCount = 0;
		int effectiveCount = 0;
		int pageSize = 1 * 1000 * 1000;
		
		int count0 = 0;
		int count1 = 0;
		int countRedundant = 0;
		
		max_id_matches = getHighestID_Matches(connection);
		
outer:	for(int i = 173 * 1000 * 1000; i <= max_id_matches; i += pageSize){    // Reduce execution time
			try {
				System.out.print("Scanning matches with id_matches in range [" + i + ", " + (i + pageSize) + ")");
				
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
					//	" M.ids = 'y' and " +
						" M.id_match_process = 99921 and " +   // For testing, these are valid matches wrt links_base
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
					if(x != 0 && y != 0){
						//if(x > max_idp ) max_idp = x;
						//if(y > max_idp ) max_idp = y;
						//if(x < min_idp ) min_idp = x;
						//if(y < min_idp ) min_idp = y;
						
						switch(add(x, y)){
						case 0:  count0++;
						         break;
						case 1:  count1++;
						         break;
						case -1: countRedundant++;
								 break;
								 
								 default:
						         
						}
					}

					if(r.getString("M.value_familyname_mo") != null){
						x = r.getInt("X.mother_id");
						y = r.getInt("Y.mother_id"); 
						if(x != 0 && y != 0){
							switch(add(x, y)){
							case 0:  count0++;
							         break;
							case 1:  count1++;
							         break;
							case -1: countRedundant++;
									 break;
									 
									 default:
							         
							}
						}
					}

					if(r.getString("M.value_familyname_fa") != null){
						x = r.getInt("X.father_id");
						y = r.getInt("Y.father_id");
						if(x != 0 && y != 0){
							switch(add(x, y)){
							case 0:  count0++;
							         break;
							case 1:  count1++;
							         break;
							case -1: countRedundant++;
									 break;
									 
									 default:
							         
							}
						}
					}


					if(r.getString("M.value_familyname_pa") != null){
						x = r.getInt("X.partner_id");
						y = r.getInt("Y.partner_id");
						if(x != 0 && y != 0){
							switch(add(x, y)){
							case 0:  count0++;
							         break;
							case 1:  count1++;
							         break;
							case -1: countRedundant++;
									 break;
									 
									 default:
							         
							}
						}
					}

					totalCount++;		
					//rangeCount++;
					//if(count % 1000 == 0)
						//System.out.println("Read " + count + " matches in this range");

				}

				
				//System.out.println("Read total " + totalCount + " matches");

			} catch (SQLException e) {

				System.out.println(e.getMessage());
				Utils.closeConnection(connection);
				System.exit(-1);
			}
			
			//System.out.println(": " + rangeCount + " matches");
			rangeCount = 0;
			
		}
		
		System.out.println("Read " + totalCount + " matches, effective = " + count1 + ", redundant = " + countRedundant + ", invalid = " + count0);
		
		//System.out.println("" + effectiveCount + " person numbers changed");
		//System.out.println("max_idp = " + max_idp + ", min_idp = " + min_idp);
		
		// write person numbers to table
		
		//if(1==1) System.exit(0);
		
		//Utils.executeQI(connection, "drop index nr on personNumbers");

		// Copy s to s_save and truncate s
		
		connection = Utils.connect2(server, Constants.links_ids, userid,  passwd);

		Utils.executeQI(connection, "drop table personNumbers_save ");
		Utils.executeQ(connection, "rename table personNumbers to personNumbers_save ");
		createTable(connection);	// create new table 'personNumbers'
		
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
			if(aliases[j] == onlySelf)
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
	/*
	 * 
	 * Example:
	 * 
	 * Initial aliases:
	 * 
	 *  aliases[1] = null
	 *  aliases[2] = null
	 *  aliases[3] = null
	 *  aliases[4] = null
	 *  
	 * add(1, 2):
	 * 
	 * 
	 *  Allocate h1, add 1 and 2
	 *  h1 = {1, 2}
	 * 
	 *  aliases[1] = h1
	 *  aliases[2] = h1
	 *  aliases[3] = null
	 *  aliases[4] = null
	 * 
	 * add(3, 4):
	 * 
	 * Allocate h2, add 3 and 4
	 * 
	 *  h1 = {1, 2}
	 *  h2 = {3, 4}
	 * 
	 *  aliases[1] = h1
	 *  aliases[2] = h1
	 *  aliases[3] = h2
	 *  aliases[4] = h2
	 * 
	 * add(4, 2):
	 * 
	 * add 1 and 2 to h2
	 *  
	 *  h2 = {3, 4, 1, 2}
	 * 
	 *  aliases[1] = h2
	 *  aliases[2] = h2
	 *  aliases[3] = h2
	 *  aliases[4] = h2
	 * 
	 *  h1 = no reference to h1 anymore, up for storage reclaim by garbage collector
	 * 
	 */
	
	private static int add(int x, int y){
		
				
		
		try{
			
			
			//System.out.println( "add("+ x + "," + y + ")");
			
			if(aliases[x] == null){
				System.out.println("aliases[" + x + "] == null!!");
				System.exit(-2);
				
			}

			if(aliases[y] == null){
				System.out.println("aliases[" + y + "] == null!!");
				System.exit(-2);
				
			}

			
			if(x > max_id_person  || y > max_id_person) return(0);          // This could happen if table matches is out of synch with table person_c,
			// we need to exclude it because it will give an 'array index out of bound' error

			if(x == 0 || y == 0 ) return(0);                                // id_person starts a 1
			
			if(x == y) return(0);                                           // if we let this pass, we get a ConcurrentModificationException

			if(aliases[x] != onlySelf && aliases[x] == aliases[y]){
				//System.out.println("Redundant   (" + x + "," + y + ")" );
				return(-1); // This is a 'redundant' match
			}

			if(aliases[x].size() >= 100){
				//System.out.println("Person with i_person = " + x + " has more than 100 matches" );
				return 0;     
			}

			if(aliases[y].size() >= 100){
				//System.out.println("Person with i_person = " + y + " has more than 100 matches" );
				return 0;     
			}

			//System.out.println("At 10");
			
			ArrayList<Integer> h = null;

			if(aliases[x] == onlySelf){                                     // if x did not have aliases up to now
				h = new ArrayList<Integer>();                               // aliases[x] needs an ArrayList<Integer> (containing at least x and y) (1) 
				aliases[x] = h;                                             // indicate that x now has aliases other than x itself 
				h.add(x);                                                   // add x to it (y will be added to it below (in (2))
			}

			if(aliases[y] == onlySelf){                                     // if y did not have aliases up to now
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
		}
		catch(Throwable e){


			System.out.println("Exception " + e.getMessage());
			System.out.println("x, y = " + x + ","+ y);
			System.out.println("aliases[x]:");
			if(aliases[x] != null)
				for(Integer a: aliases[x])
					System.out.println(a);
			else
				System.out.println("aliases[" + x + "] is null!" );
			System.out.println("aliases[y]:");
			if(aliases[y] != null)
				for(Integer a: aliases[y])
					System.out.println(a);
			else
				System.out.println("aliases[" + y + "] is null!" );

			System.exit(-2);

		}

		
		//if(aliases[1] != null) System.out.println(1/0);

		//System.out.println("Returning 1");
		return(1); 
	}
	
	private static void initDB(Connection connection){		
		
		try {

			
			System.out.println("initDB...");
			// java.sql.Statement statement = connection.createStatement();

			max_id_person = getHighestID_Person(connection);
			
			

			boolean firstTime = true;
		    
		    try {
		      ResultSet rs = connection.createStatement().executeQuery("select * from personNumbers");
		      
				while (rs.next()) {
					firstTime = false;
					break;
				}
				rs.close();
		    }
		    catch (Exception e ) {
		      // table does not exist or some other problem
		      //e.printStackTrace();
		    }

            System.out.println("First time = " + firstTime);

		    
		    if(firstTime){
				// Next statements only first time
				createTable(connection);                 // only first time
				System.out.println("Call initializePersonNumbers, connection = " + connection);
				initializePersonNumbers(connection);     // only first time	
		    }
			
			
			aliases = new ArrayList[max_id_person + 1]; // Array of ArrayLists, position p in array describes person_id p, 
			                                            // the ArrayList contains all person_id that have the same personNumber as p and is used commonly by all these person_id
			ArrayList<Integer> h = new ArrayList<Integer>();
			int prevPersonNumber = - 1;
			//java.sql.Statement statement1 = connection.createStatement();
			
			Utils.executeQI(connection, "create index nr on personNumbers(person_number)");

			System.out.println("Reading person numbers");
			
			int count = 0;
			int countpn = 0;
			int counttot = 0;
			int pageSize = 1 * 1000 * 1000;
			//for(int i = 0; i <= max_id_person; i += pageSize){
			for(int i = 0; i <= 1; i += pageSize){  // Time reduce
				//String select = "select id_person, person_number from personNumbers where person_number > " + i + " and person_number <= " +  (i + pageSize) +
				String select = "select id_person, person_number from personNumbers where person_number >= 35240945 and person_number <= 38499635 " + // Time reduce 
         						" order by person_number, id_person";
				//System.out.print("Scanning persons with personNumber in range [" + i + ", " + (i + pageSize) + ")");
				System.out.print("Scanning persons with personNumber in range [35240945, 38499635]");  // Time reduce

				//System.out.println(select);
				ResultSet r = connection.createStatement().executeQuery(select);
				while (r.next()) {
					count++;
					if(r.getInt("person_number") != prevPersonNumber){
						countpn++;
						if(h.size() == 1){
							aliases[prevPersonNumber] = onlySelf;
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
						aliases[prevPersonNumber] = onlySelf;
					}
					else{
						for(Integer y1: h)
							aliases[y1] = h;
					}
				}
				r.close();
				connection.createStatement().close();
				System.out.println(": " + countpn + " personNumbers, " + count + " persons in range");
				counttot += count;
				count = 0;
			}

			System.out.println("Read   " + countpn + " personNumbers, " + counttot + " persons in total");

			
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
	
	private static int getHighestID_Matches(Connection connection){
		
		System.out.println("Identifying highest id_matches");
		
		ResultSet r = null;
		try {
			//r = connection.createStatement().executeQuery("select max(id_person) as m FROM links_ids.personNumbers");
			r = connection.createStatement().executeQuery("select max(id_matches) as m FROM links_match.matches");
			while (r.next()) {
				System.out.println("Highest id_matches = " + r.getInt("m"));
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
		
		//String x = "insert into links_ids.personNumbers (select id_person, id_person from links_cleaned.person_c where id_person <= " + max_id_person + ")";
		String x = "insert into links_ids.personNumbers (select id_person, id_person from links_cleaned.person_c where id_person >= 35240945 and id_person <=  38499635)"; // Reduce time
		System.out.println(x);
		Utils.executeQ(connection, x);

	}
}