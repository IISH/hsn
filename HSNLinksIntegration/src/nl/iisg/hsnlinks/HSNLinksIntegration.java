package nl.iisg.hsnlinks;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JTextArea;

import nl.iisg.hsncommon.Common1;
import nl.iisg.hsncommon.ConstRelations;
import nl.iisg.hsncommon.ConstRelations2;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;

;



 

public class HSNLinksIntegration implements Runnable {
	

	private static String userid;
	private static String passwd;
	private static String server;
	
    static JTextArea textArea = null;
    static DataOutputStream out = null;

    public HSNLinksIntegration(DataOutputStream out) {
        setTextArea(textArea);
        setOut(out);
    }


	static Connection connection = null;
	private static ArrayList<String>  addList = new ArrayList<String>(); 
	private static ArrayList<String>  iList = new ArrayList<String>(); 
	private static ArrayList<String>  iiList = new ArrayList<String>(); 
	private static ArrayList<String>  icList = new ArrayList<String>(); 
	private static ArrayList<String>  cList  = new ArrayList<String>(); 
	private static ArrayList<String>  ccList  = new ArrayList<String>(); 
	private static ArrayList<PersonX>[]         a = new ArrayList[1000 * 1000]; 
	
	private static String sp01                         = "%s";                
	private static String sp02                         = sp01 + sp01;             
	private static String sp03                         = sp02 + sp02;             
	private static String sp04                         = sp03 + sp03;             
	private static String sp05                         = sp04 + sp04;             
	private static String sp06                         = sp05 + sp05;             
	private static String sp07                         = sp06 + sp06;             
	private static String sp08                         = sp07 + sp07;             
	private static String sp09                         = sp08 + sp08;             
	private static String sp10                         = sp09 + sp09;  
	private static String sp11                         = sp10 + sp10;  
	private static String sp12                         = sp11 + sp11; // 2048  
	private static String sp13                         = sp12 + sp12; // 4096  
	private static String sp14                         = sp13 + sp13; // 8192  
	private static String sp15                         = sp14 + sp14; //16384
	
	
	private static int Id_C;  

	
    public void run() {

        main(new String[0]);
    }


	
	public static void main(String[] args){


		try{

			
			print("Add Links Data started ...");


			if(args.length > 2){
				server = args[0].trim();
				userid = args[1].trim();
				passwd = args[2].trim();
				connection = Utils.connect2(server, Constants.links_hns_ids, userid,  passwd); 
				if(connection == null){
					System.out.println("Invalid User/password/server");
					System.exit(-1);
				}

			}

			
			//Utils.createMessageTable(connection);
			//Utils.loadMessages(connection);
			//Utils.executeQ(connection, "SET SESSION sql_mode=''"); // enable backslash escape
			
			print("Running initial queries,  this takes about 25 minutes... ");
			
			Query.initialQueries(connection);

			
			// Step (7) of the flow diagram is continued
			
			// Reset output tables
			
			print("Removing previous LINKS addition");
			
			Utils.executeQI(connection, "truncate hsn_ids.other_hsn_links"); 
			
			Utils.executeQ(connection, "delete from hsn_ids.individual      where id_d like 'LIN%';");
			Utils.executeQ(connection, "delete from hsn_ids.indiv_indiv     where id_d like 'LIN%';");
			Utils.executeQ(connection, "delete from hsn_ids.indiv_context   where id_d like 'LIN%';");
			Utils.executeQ(connection, "delete from hsn_ids.context         where id_d like 'LIN%';");
			Utils.executeQ(connection, "delete from hsn_ids.context_context where id_d like 'LIN%';");
			
			Utils.executeQ(connection, "delete from hsn_ids.individual      where id_d like 'CLA%';");
			Utils.executeQ(connection, "delete from hsn_ids.indiv_indiv     where id_d like 'CLA%';");
			Utils.executeQ(connection, "delete from hsn_ids.indiv_context   where id_d like 'CLA%';");
			Utils.executeQ(connection, "delete from hsn_ids.context         where id_d like 'CLA%';");
						
			print("Creating hsn_ids.other_hsn_links");
			
			
		
		
			String qc = " SELECT * FROM hsn_ids.integrated_persons  order by IDNR";
			                                                    
			
			
			System.out.println(qc);
			
			Statement sc = (Statement) connection.createStatement ();

			sc.executeQuery(qc);
			ResultSet rc = sc.getResultSet();
			
            // Process resultset
			
			int currentIDNR = 0;
			int count = 0;
			int rowCount = 0;
			
			
			Set<PersonX> sTotal = new HashSet<PersonX>();
			
			
			// Process integrated_persons
			// Fill others_hsn_links
			
			print("Combining information from HSN and LINKS ...");
			
			while (rc.next()){	
				
				
				rowCount++;
				
				//System.out.println("IDNR = " + rc.getInt("idnr"));
			
				if(currentIDNR != rc.getInt("idnr")){
					
					
					if(sTotal.size() > 0)
						handleIDNR(sTotal);
					sTotal.clear();
					currentIDNR = rc.getInt("idnr");
					count++;
					
					//if(count % 100 == 0)
						//System.out.println("Processed "+ count + " IDNRs");
					
				}
				
				PersonX px = new PersonX();
				
				px.source           = rc.getString("source_");
				px.firstname        = rc.getString("firstname"); 
				px.lastname         = rc.getString("lastname");  
				px.sex              = rc.getString("sex");  
				px.birth_location   = rc.getInt("birth_location"); 
				px.birth_day_min    = rc.getInt("birth_day_min");  
				px.birth_month_min  = rc.getInt("birth_month_min");  
				px.birth_year_min   = rc.getInt("birth_year_min"); 
				px.birth_day_max    = rc.getInt("birth_day_max");  
				px.birth_month_max  = rc.getInt("birth_month_max"); 
				px.birth_year_max   = rc.getInt("birth_year_max"); 		
				px.death_location   = rc.getInt("death_location"); 
				px.death_day_min    = rc.getInt("death_day_min");  
				px.death_month_min  = rc.getInt("death_month_min");  
				px.death_year_min   = rc.getInt("death_year_min"); 
				px.death_day_max    = rc.getInt("death_day_max");  
				px.death_month_max  = rc.getInt("death_month_max"); 
				px.death_year_max   = rc.getInt("death_year_max"); 		
				px.id_i             = rc.getInt("id_i");
				px.relation         = rc.getString("relation");
				px.idnr             = rc.getInt("idnr");
				
				 
								
				sTotal.add(px);
				
			}
			if(sTotal.size() > 0)
				handleIDNR(sTotal);
			
			flush();
			
			// Step (7) of flowchart completed
			
			// Now some preliminary actions (loading the contexts) for step (8) and (9)
			
			print("LINKS information was found for "+ count + " IDNRs");
			//if(1==1 ) System.exit(8);
			
			// Load context info
			
			print("Loading context for HSN");
			
			// Load context from HSN
			
			HashMap<String, HashMap<Integer, HashMap<String, HashMap<Integer, Integer>>>> hm = 
					new  HashMap<String, HashMap<Integer, HashMap<String, HashMap<Integer, Integer>>>>();
			
			String qco = "SELECT municipality, year, source, SEQUENCE_NUMBER, id_c FROM hsn_ids.sources_certificates order by municipality, year, source, SEQUENCE_NUMBER";
			Statement sco = (Statement) connection.createStatement ();
			sco.executeQuery(qco);
			ResultSet rco = sco.getResultSet();
			while (rco.next())
				add(hm, rco.getString("municipality"), rco.getInt("year"), rco.getString("source"), rco.getInt("SEQUENCE_NUMBER"), rco.getInt("Id_C"));
			

			// Read Municipalities from HSN CONTEXT
			
			HashMap<String, Integer> hmu = new HashMap<String, Integer>();
			
			String qcmu = "SELECT * FROM hsn_ids.context where TYPE = 'NAME' order by id_c";
			Statement scmu = (Statement) connection.createStatement ();
			scmu.executeQuery(qcmu);
			ResultSet rcmu = scmu.getResultSet();
			while (rcmu.next()){
				int Id_C = rcmu.getInt("Id_C");
				if(Id_C < 2000){  // municipalities are the first in CONTEXT
					hmu.put(rcmu.getString("VALUE"), Id_C);
				}
				else
					break;
					
					
				
			}
			
			
			
			// Find highest Id_C;
			
			int Id_C_Highest = 0;
			
			String qm = "SELECT MAX(Id_C) as m FROM hsn_ids.context";
			Statement sm = (Statement) connection.createStatement ();
			sm.executeQuery(qm);
			ResultSet rcm = sm.getResultSet();
			
			
			while (rcm.next()){
				Id_C_Highest = rcm.getInt("m");
				break;
				
			}
			
			Id_C = Id_C_Highest;
			
			//System.out.println("HSN Highest Id_C = " + Id_C_Highest);
			
			// Load context for new LINKS persons
			
			print("Loading context for LINKS");

			
			Utils.executeQI(connection, "create index id_c_i on links_ids.sources_certificates(id_c)");

			HashMap<Integer, String>  hmMunicipality = new HashMap<Integer, String>(); 
			HashMap<Integer, String>  hmName         = new HashMap<Integer, String>(); 
			HashMap<Integer, String>  hmYear         = new HashMap<Integer, String>(); 
			HashMap<Integer, String>  hmSeqNo        = new HashMap<Integer, String>(); 
			HashMap<Integer, String>  hmSource       = new HashMap<Integer, String>(); 
			HashMap<Integer, String>  hmRelation     = new HashMap<Integer, String>(); 
			
			String qico = "SELECT  " + 
					" ohl.id_i_links, " +   
					" lsc.id_C, " +  
					" lsc.source, " +  
					" lsc.municipality, " +
					" lsc.year, " +
					" lsc.SEQUENCE_NUMBER, " + 
					" lic.Relation, " + 
					" lic.id_C, " + 
					" lic.Source " + 
					" FROM  " +
					" hsn_ids.other_hsn_links as ohl, " + 
					" links_ids.indiv_context  as lic, " + 
					" links_ids.sources_certificates as lsc " + 
					" where  id_i_hsn = 0 and " + 
					" id_i_links = lic.id_i and lic.Id_C = lsc.id_c";

			
			System.out.println(qico);
			
			Statement sico = (Statement) connection.createStatement ();
			sico.executeQuery(qico);
			ResultSet rico = sico.getResultSet();
			int ccc = 0;
			while (rico.next()){
				
				
				ccc++;
				
				int id_i            = rico.getInt("ohl.id_i_links");
				int id_c            = rico.getInt("lic.Id_C");				
				
				String municipality = rico.getString("municipality");
				String name         = rico.getString("lsc.source");
				String year         = rico.getString("year");
				String seqNo        = rico.getString("SEQUENCE_NUMBER");
				
				String relation     = rico.getString("Relation");
				String source       = rico.getString("lic.Source");
				
				if(municipality != null && name != null && year != null && seqNo != null && relation != null && source != null){
					hmMunicipality.put(id_i, municipality);
					hmName.put(id_i, name);
					hmYear.put(id_i, year);
					hmSeqNo.put(id_i, seqNo);
					hmSource.put(id_i, source);
					hmRelation.put(id_i, relation);
				}
			}

			
			// Now process table links_ids.individual (8) 
			
			print("Copy links_ids -> hsn_ids for appropriate persons");
			
			
			Utils.executeQ(connection, "CREATE TABLE IF NOT EXISTS hsn_ids.indiv_indiv_2 LIKE hsn_ids.indiv_indiv");
			Utils.executeQI(connection, "CREATE INDEX ii1 ON hsn_ids.indiv_indiv_2(id_i_1)");
			Utils.executeQI(connection, "CREATE INDEX i1 ON links_ids.individual(id_i)");
			Utils.executeQ(connection, "TRUNCATE hsn_ids.indiv_indiv_2");
			
					
			String qi = "SELECT * from " + 
				    "hsn_ids.other_hsn_links as ohl, " +  
				    "links_ids.individual as li " + 
			        "where " + 
				    "ohl.id_i_links = li.id_i " + 
		           "order by idnr, ohl.id_i_links";
		
			System.out.println(qi);
			
			Statement si = (Statement) connection.createStatement ();

			si.executeQuery(qi);
			ResultSet ri = si.getResultSet();
			
			
			int prev_idnr = -1;
			int prev_id_i = -1;
			int id_i_new = 0;
			String relation = null;
			String sex = null;

			int id_i_X = 0;
			int id_i_hsn = -1;
			
			boolean doMsg = true;
			
			int id_iPerIDNR = 0;
			
			while (ri.next()){				

				
				int idnr     = ri.getInt("ohl.idnr");				
				int id_i     = ri.getInt("ohl.id_i_links");
				    
				//System.out.println("--> IDNR = " + idnr + ",  id_i_links = " + id_i);
						
				if(prev_id_i != id_i){
					
					if(++id_iPerIDNR > 100)
						continue;
					
					
					doMsg = true;
					if(id_i_hsn == 0){
						handleIndivIndiv(prev_idnr, id_i_X, relation, sex);
						
						String src = hmSource.get(prev_id_i);
						String rel = hmRelation.get(prev_id_i);
																		
						int Id_C = handleContext(prev_id_i, hmMunicipality, hmName, hmYear, hmSeqNo, hm);
						if(Id_C != 0)
							addIndivContext(connection, id_i_X, Id_C, src, rel, "Event", "Exact", 0, 0, 0);
					}
					prev_id_i = id_i;
					
					relation = null;
					sex = null;
					
					if(prev_idnr != idnr){
						id_i_new =  (1000 * 1000 * 1000) + (idnr * 1000) +  600;
						prev_idnr = idnr;
						id_iPerIDNR = 0;
					}
					
					id_i_hsn = ri.getInt("ohl.Id_i_HSN");
					
					if(id_i_hsn != 0)
						id_i_X = id_i_hsn;
					else{
						id_i_new++;
			
					id_i_X = id_i_new;
					}
				}

				
				relation       = ri.getString("ohl.relation");
				
				if(ri.getString("li.type").equalsIgnoreCase("SEX"))
					sex = ri.getString("li.value");
				
				//System.out.format("idnr = %d, id_i = %d\n",  idnr, id_i);
				
				//addIndiv(Connection connection, int Id_I, String source, String type, String value, 
				//	String dateType, String estimation, int day, int month, int year, int min_day, int min_month, int min_year,int max_day, int max_month, int max_year)
							
				// Find the corresponding HSN person we saved previously
				
				PersonX p = null;
				if(id_i_hsn != 0){
					for(PersonX px: a[idnr]){
						if(px.getId_i() == id_i_hsn && px.getSource().equalsIgnoreCase("HSN")){
							p = px;
							break;
						}
					}
				}
				else
					for(PersonX px: a[idnr]){ // Find the LINKS person we saved and change it to HSN
						if(px.getId_i() == id_i && px.getSource().equalsIgnoreCase("LINKS")){
							px.setSource("HSN");  // This will ensure relations will be set to this id_i
							px.setId_i(id_i_new);
							break;
						}
					}
				
				
				if(p != null && p.getRelation() != null && !p.getRelation().equalsIgnoreCase(relation)){
					if(doMsg){
						//System.out.println("Error: HSN ID_I  = " + p.getId_i() + " with relation " + p.getRelation() + " has been linked to LINKS ID_I = " + id_i + " and relation " + relation);
						//String message = String.format("Relation Difference:    HSN: %-30s  LINKS: %-30s\n",  p.getRelation(), relation);
						Utils.message(connection, 9101, "" + p.getId_i(), p.getRelation(), relation);
						// message(Connection connection, int Id, String ... args){
						doMsg = false;
					}
					continue;
				}
				
				
				
				
				boolean write = true;
				if(p != null && identifying(ri.getString("Type"))){
					write = false; // preset
					
					if(ri.getString("Type").equalsIgnoreCase("DEATH_DATE")){
						if(p.getDeath_year_max() == 0)
							write = true;
					}
					if(ri.getString("Type").equalsIgnoreCase("DEATH_LOCATION")){
						if(p.getDeath_location() == 0)
							write = true;
					}
					
				}
				
				if(write == true)
					addIndiv(connection,
						id_i_X, 
                        ri.getString("Source"),             
                        ri.getString("Type"),             
                        ri.getString("Value"),             
                        ri.getString("Date_type"),             
                        ri.getString("Estimation"),             
                        ri.getInt("Day"),             
                        ri.getInt("Month"),             
                        ri.getInt("Year"),             
                        ri.getInt("Start_day"),             
                        ri.getInt("Start_month"),             
                        ri.getInt("Start_year"),             
                        ri.getInt("End_day"),             
                        ri.getInt("End_month"),             
                        ri.getInt("End_year"));             
				
			}
			if(id_i_hsn == 0){
				handleIndivIndiv(prev_idnr, id_i_X, relation, sex);
				
				String src = hmSource.get(prev_id_i);
				String rel = hmRelation.get(prev_id_i);
																
				int Id_C = handleContext(prev_id_i, hmMunicipality, hmName, hmYear, hmSeqNo, hm);
				addIndivContext(connection, id_i_X, Id_C, src, rel, "Event", "Exact", 0, 0, 0);  // Part of (9) of the flow diagram
			}
			flushIndiv(connection);
			flushIndivIndiv(connection, 2);  // save to indiv_indiv_2
			flushIndivContext(connection);
			//Utils.commitConnection(connection);
			
			
			//if(1==1) System.exit(8);
			
			
			
			// process indiv_indiv_2 and indiv_indiv to set 'Sibling or Halfsibling' to 'Sibling' if appropriate and store in hsn_ids.indiv_indiv_3
			// This step is not in the flow chart
			
			Utils.executeQ(connection, "CREATE TABLE IF NOT EXISTS hsn_ids.indiv_indiv_3 LIKE hsn_ids.indiv_indiv");
			Utils.executeQI(connection, "CREATE INDEX ii1 ON hsn_ids.indiv_indiv_3(id_i_1)");
			Utils.executeQI(connection, "CREATE INDEX ii1 ON hsn_ids.indiv_indiv(id_i_1)");

			Utils.executeQ(connection, "TRUNCATE hsn_ids.indiv_indiv_3");

			
			//System.out.println("Updating hsn_ids.indiv_indiv to set 'Sibling or half-sibling' to 'Sibling' or 'Half-sibling' where appropriate");
			print("Updating hsn_ids.indiv_indiv");

			
			String qii = "SELECT * from (select 'H' as S, hsn_ids.indiv_indiv.* from hsn_ids.indiv_indiv UNION ALL "
					+ "select 'L' as S, hsn_ids.indiv_indiv_2.* from hsn_ids.indiv_indiv_2) a order by id_i_1, Id_i_2";  
			
			//System.out.println(qii);

			Statement sii = (Statement) connection.createStatement ();

			sii.executeQuery(qii);
			ResultSet rii = sii.getResultSet();
			
			
			
			int prev_id_i_1 = -1;
			int prev_id_i_2 = -1;
			
			List<INDIV_INDIV> h = new ArrayList<INDIV_INDIV>();
			List<String> s = new ArrayList<String>();
			
			int cc = 0;
			
			while (rii.next()){	
				
				
				
				if(rii.getInt("id_i_1") != prev_id_i_1 || rii.getInt("id_i_2") != prev_id_i_2){
					
					
					prev_id_i_1 = rii.getInt("id_i_1");
					prev_id_i_2 = rii.getInt("id_i_2");
					
					INDIV_INDIV ii = reduce(s, h);
					
					if(ii != null){
						addIndivIndiv(connection, 3, ii.getId_I_1(), ii.getId_I_2(), ii.getId_D(), ii.getSource(), ii.getRelation(),  "Reported", "Exact", 0, 0,0);
					}

					h.clear();
					s.clear();
				}
				
				INDIV_INDIV ii = new INDIV_INDIV();
				
				
				ii.setId_D(rii.getString("Id_D"));
				ii.setId_I_1(rii.getInt("Id_I_1"));
				ii.setId_I_2(rii.getInt("Id_I_2"));
				ii.setSource(rii.getString("source"));
				ii.setRelation(rii.getString("relation"));
				
				h.add(ii);
				s.add(rii.getString(("S")));
				
				cc++;
				if(cc % 10000 == 0)
					print("Processed " + cc + " rows"); 
				
			}
			INDIV_INDIV ii = reduce(s, h);
			
			if(ii != null)
				addIndivIndiv(connection, 3, ii.getId_I_1(), ii.getId_I_2(), ii.getId_D(), ii.getSource(), ii.getRelation(),  "Reported", "Exact", 0, 0,0);

			flushIndivIndiv(connection, 3);
			
			int r = (int) (Math.random() * 1000000);
			
			Utils.executeQ(connection, "RENAME TABLE hsn_ids.indiv_indiv TO hsn_ids.indiv_indiv_save_" + r);
			Utils.executeQ(connection, "RENAME TABLE hsn_ids.indiv_indiv_3 TO hsn_ids.indiv_indiv");
			
			
			// save the context (9) 
			
			System.out.println("Saving hsn context");
			
			save(hm, hmu, Id_C_Highest);
			flushContext(connection);
			flushContextContext(connection);
			
		    print("\nIDS - Add LINKS Data ended\n");

			System.out.println("Finished");
			
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			Utils.closeConnection(connection);			
		}	
	}
	
	private static int handleContext(int Id_I, HashMap<Integer, String> hmMunicipality, HashMap<Integer, String> hmName,
			HashMap<Integer, String> hmYear, HashMap<Integer, String> hmSeqNo,
			HashMap<String, HashMap<Integer, HashMap<String, HashMap<Integer, Integer>>>> hm){
		
		
		if(hmMunicipality.containsKey(Id_I)){
			
			String municipality = hmMunicipality.get(Id_I);
			String name         = hmName.get(Id_I);
			String year         = hmYear.get(Id_I);
			String seqNo        = hmSeqNo.get(Id_I);

			if(municipality == null || name == null || year == null || seqNo == null) return 0;
			
			//System.out.println("municipality = "+ municipality);
			//System.out.println("name = "+ name);
			//System.out.println("year = "+ year);
			//System.out.println("Seqno = "+ seqNo);

			int yearN = 0;
			int seqNoN = 0;

			try{
			
			yearN = new Integer(year);
			seqNoN = new Integer(seqNo);
			}
			catch (Exception e) {
				return 0;
			}	
			
			
			
			return(add(hm, municipality, yearN, name, seqNoN, 0));	
			
		}
		
		
		return 0;
		
	}
	
	private static INDIV_INDIV reduce(List<String> s, List<INDIV_INDIV> h){
		
		if(h.size() == 0) return null;
		
		if(h.size() == 1) 
			for(INDIV_INDIV ii: h)
				return ii;
		
		int n = 0;
		for(INDIV_INDIV ii: h){
			String a =  s.get(n++);
			if(a.equals("L"))
				return ii;
		}
		
		return null;
	}
	
	
	private static INDIV_INDIV reduce2(Set<INDIV_INDIV> h){
		
		
		if(h.size() == 0) return null;
		
		if(h.size() == 1) 
			for(INDIV_INDIV ii: h)
				return ii;
		
		
		boolean spouse = false;
		boolean sibling = false;
        boolean sibling_in_law = false;
        
		for(INDIV_INDIV ii: h){
			if(ii.getRelation() == null) continue;
			//System.out.println(ii.getRelation());
			if(ii.getRelation().equalsIgnoreCase("Spouse")) spouse = true;
			else if(ii.getRelation().equalsIgnoreCase("Sibling")) sibling = true;
			else if(ii.getRelation().equalsIgnoreCase("Sibling-in-Law")) sibling_in_law = true;
		}
				
		
		String newRelation = null;
		
		
		if(spouse) newRelation = "Spouse";
		else if(sibling) newRelation = "Sibling";
		else if(sibling_in_law) newRelation = "Sibling-in-Law";
		
		for(INDIV_INDIV ii: h){
			if(ii.getRelation() == null) continue;
			if(ii.getRelation().equalsIgnoreCase(newRelation)){
				//System.out.println("-------->" + ii.getRelation());
				return ii;
			}
		}
			
		
		return null;
	}
	
	private static boolean identifying(String t){
		
		String[] identifiers = {"LAST_NAME", "PREFIX_LAST_NAME", "FIRST_NAME", "SEX", "BIRTH_DATE", "BIRTH_LOCATION"};  
		
		for(String s: identifiers)
			if(s.equalsIgnoreCase(t))
				return true;
		
		return false;
		
		
	}
	private static void handleIndivIndiv(int idnr, int id_i, String relation, String sex){  
		
		
		
		if(idnr < 0 || relation == null || sex == null) return;
		
		// Relation to RP
		
		//int id_rp =  (1000 * 1000 * 1000) + (idnr * 1000) +  1;
		//addIndivIndiv(connection, 2, id_i, id_rp, "CLA", "1", "" + relation,  "Reported", "Exact", 0, 0,0);
		
		
		// Relation to HSN persons
		
		if(a[idnr] != null){
			
			
			for(PersonX p: a[idnr]){
				
				if(!p.getSource().equalsIgnoreCase("HSN")) continue;
				
				
				String relation_P_To_id_i = newRelation(idnr, p.relation, p.sex, relation, sex);
				String relation_id_i_To_P = newRelation(idnr, relation, sex, p.relation, p.sex);
				
				
				//System.out.println("-------> " + p.relation + "----" + relation);
				//System.out.println("++++++++> " + relation_P_To_id_i + "++++++" + relation_id_i_To_P);
				
				if(relation_P_To_id_i != null && relation_id_i_To_P != null){
					
					
					addIndivIndiv(connection, 2, id_i, p.id_i, "CLA", "1", "" + relation_id_i_To_P,  "Reported", "Exact", 0, 0,0);
					addIndivIndiv(connection, 2, p.id_i, id_i, "CLA", "1", "" + relation_P_To_id_i,  "Reported", "Exact", 0, 0,0);
					
					
				}
				
			}
			
		}
	}
	
	
	private static String newRelation(int idnr, String relation1,  String sex1, String relation2, String sex2){
		
		System.out.format("relation1 = %s, relation2 = %s\n", relation1, relation2);
		
		//System.out.format("sex1 = %s, sex2 = %s\n", sex1, sex2);
		
		//System.out.println("1 relation1 = " + relation1);
		//System.out.println("1 relation2 = " + relation2);
		
		
		
		// We must still handle half-siblings etc
		
		if(relation1 == null || sex1 == null || relation2 == null || sex2 == null) return null;
		
		
		// Handle Stillbirth-children, nuclear family only
		
		if(relation1.equalsIgnoreCase("Stillbirth-child") && (relation2.equalsIgnoreCase("Child")))  return "Stillbirth-sibling";
		if(relation2.equalsIgnoreCase("Stillbirth-child") && (relation1.equalsIgnoreCase("Child")))  return "Sibling";

			
		// Adapt sibling and child

		if(relation1.equalsIgnoreCase("Sibling"))
			if(sex1.equalsIgnoreCase("M"))
				relation1 = "Brother";
			else
				relation1 = "Sister";
		
		if(relation2.equalsIgnoreCase("Sibling"))
			if(sex2.equalsIgnoreCase("M"))
				relation2 = "Brother";
			else
				relation2 = "Sister";

		if(relation1.startsWith("Half-Sibling"))
			if(sex1.equalsIgnoreCase("M"))
				relation1 = "Halfbrother";
			else
				relation1 = "Halfsister";
		
		if(relation2.startsWith("Half-Sibling"))
			if(sex2.equalsIgnoreCase("M"))
				relation2 = "Halfbrother";
			else
				relation2 = "Halfsister";
		
		if(relation1.equalsIgnoreCase("Child"))
			if(sex1.equalsIgnoreCase("M"))
				relation1 = "Son";
			else
				relation1 = "Daughter";
		
		if(relation2.equalsIgnoreCase("Child"))
			if(sex2.equalsIgnoreCase("M"))
				relation2 = "Son";
			else
				relation2 = "Daughter";
		
		//System.out.format("relation1 = %s, relation2 = %s\n", relation1, relation2);

		//System.out.println("2 relation1 = " + relation1);
		//System.out.println("2 relation2 = " + relation2);

		
		// Find code relation1
		
		int code1 = 0;
		for(int i = 0; i < Relations.b.length; i++){
			if(Relations.b[i] != null && Relations.b[i].equalsIgnoreCase(relation1)){
				code1 = i;
				break;
			}
		}

		if(code1 == 0){
			Utils.message(connection, 9102, "" + idnr, relation1);

			return null; 
		}
		
		//System.out.println("3 relation1 = " + relation1 + "   " + code1);
		//System.out.println("4 relation2 = " + relation2);

		
		// Find code relation2
		
		int code2 = 0;
		for(int i = 0; i < Relations.b.length; i++){
			if(Relations.b[i] != null && Relations.b[i].equalsIgnoreCase(relation2)){
				code2 = i;
				break;
			}
		}
		if(code2 == 0){
			Utils.message(connection, 9102, "" + idnr, relation2);

			return null; 
		}
		
		//System.out.println("5 relation2 = " + relation2 + "   " + code2);
		
		
		// look up first code
  	  
    	int Xcode1 = -1; 
    	
    	outer1:
    	for(int i = 0; i < ConstRelations.relCode1.length; i++){
        	for(int j = 0; j < ConstRelations.relCode1[i].length; j++){
        		if( ConstRelations.relCode1[i][j] == code1){
        			Xcode1 = i;
        			break outer1;
        		}
        	}
    	}
    	if(Xcode1 < 0){
			Utils.message(connection, 9103, "" + idnr, "" + code1);
			System.out.println("9103  1" + code1);
    		return null;
    	}
    	
		//System.out.println("6 relation1 = " + relation1 + "   " + code1 + "   " + Xcode1);

		
    	// look up second code
    	
    	int Xcode2 = -1; 
    	
    	outer2:
    	for(int i = 0; i < ConstRelations.relCode2.length; i++){
        	for(int j = 0; j < ConstRelations.relCode2[i].length; j++){
        		if( ConstRelations.relCode2[i][j] == code2){
        			Xcode2 = i;
        			break outer2; 
        		}
        	}
    	}
    	
    	
    	
    	if(Xcode2 < 0){
			Utils.message(connection, 9103, "" + idnr, "" + code2);
			System.out.println("9103  2" + code2);

    		return null;
    	}


		//System.out.println("7 relation2 = " + relation2 + "   " + code2 + "   " + Xcode2);

    	// look up code between A and B
    	
    	int AB = 0;
    	
    	if(ConstRelations.transform[Xcode1][Xcode2] != null && ConstRelations.transform[Xcode1][Xcode2].length > 0)
   		  AB = ConstRelations.transform[Xcode1][Xcode2][0]; 

		//System.out.println("8 relation1 to relation2 = " + AB);

		//System.out.format(", relation3 = %s\n", Relations.b[AB]);
    	
    	
    	// Adapt for sex

    	if(sex1.equalsIgnoreCase("V") && ConstRelations2.b3kode1_Female[AB] == null)
    		if(ConstRelations2.mToF[AB] != 0)
    			AB = ConstRelations2.mToF[AB];

    	if(sex1.equalsIgnoreCase("M") && ConstRelations2.b3kode1_Male[AB] == null)
    		if(ConstRelations2.fToM[AB] != 0)
    			AB = ConstRelations2.fToM[AB];

    	
    	
    	
    	if(AB > 0 && AB < Relations.b.length && Relations.b[AB] != null){
    		
    		String rc = Relations.b[AB];
    		
    		if(rc.equalsIgnoreCase("Son")     || rc.equalsIgnoreCase("Daughter")) rc = "Child";
    		if(rc.equalsIgnoreCase("Brother") || rc.equalsIgnoreCase("Sister"))   rc = "Sibling";
    		
    		//System.out.format("======================================>  relation3 = %s\n", rc);
    		//System.out.println("9 relation1 to relation2 = " + rc);
    		
    		return  rc;
    	}

    	else{
			Utils.message(connection, 9104, "" + idnr,  "" + relation1,  "" + relation2);
			return null;
    		
    	}
		
		
	}
	
	private static   void handleIDNR(Set<PersonX> sTotal){
		

		ArrayList<PersonX> sLINKS = new ArrayList<PersonX>();
		ArrayList<PersonX> sHSN   = new ArrayList<PersonX>();
		
		//System.out.println("sTotal.size() = " + sTotal.size());
		
		// split in two groups
		
		for(PersonX p: sTotal){
			
			if(p.source.equalsIgnoreCase("HSN"))
				sHSN.add(p);
			else
				sLINKS.add(p);
			
		}
		
		// save the total group for later reference under idnr
		
		if(sHSN.size() > 0){
			int idnr = sHSN.get(0).idnr;
			ArrayList st = new ArrayList(sTotal);
			a[idnr] =  st;
		}
		
		// Sort on id_i (for next step)
		
		
		Collections.sort(sLINKS, new Comparator<PersonX>(){       
            
            public int compare(PersonX p1, PersonX p2){
           	 
           	 if     (p1.id_i > p2.id_i) return 1;
           	 else if(p1.id_i < p2.id_i) return -1;


           	 return 0;
            }});

		

		
		// Reduce LINKS group (because of half-siblings (in-law), spouse etc.)
		
		int prev_id_i = -1;
		Set<PersonX> s = new HashSet<PersonX>();
		Set<PersonX> t = new HashSet<PersonX>();
		
		for(PersonX p: sLINKS){
									
			if(p.id_i != prev_id_i){
				s = analyseGroup(s);  // this  reduces s to 1 (so matches half-siblings(-in law) and spouse)
				t.addAll(s);
				
				prev_id_i = p.id_i;
				
				s.clear();
			}
			s.add(p);
			
		}
		if(s.size() > 0){	
			s = analyseGroup(s);
			t.addAll(s);
		}
		
		

		// Cross-identify the two groups (This means, see which LINKS persons we already have in HSN, based on identifying attributes
		
		Set<PersonX> sHSN_ignore        = new HashSet<PersonX>();	
		
		for(PersonX p: t){  // note: loop over t now
			
			for(PersonX q:  sHSN){
				if(compare(p, q) == true){
					//System.out.println("Same: IDNR = " +  sHSN.get(0).idnr + " HSN  = " + p.id_i + " LINKS " + q.id_i);
					p.setId_i_corresponding(q.getId_i()); // this sets ID_I_HSN in hsn_ids.other_hsn_links
					if(q.getRelation() != null && q.getRelation().equalsIgnoreCase("RP"))
						p.setRelation(q.getRelation());               // take relation from HSN for RP
					break;
				}
				
			}
		}
		
		addIdnr(t);
		
	}

	private static void addIdnr(Set<PersonX> sLINKS){
		
		String t = null;
		
		int Id_I_HSN = 0;
		int PERSON_HSN = 0;

		for(PersonX p: sLINKS){
			
					
			
			if(p.getId_i_corresponding() != 0){
				
				Id_I_HSN = p.getId_i_corresponding();
				PERSON_HSN = Id_I_HSN % 1000;
				
                t = String.format("(\"%d\",\"%d\",\"%d\",\"%d\",\"%s\"),",  
                		Id_I_HSN, p.getIdnr(), PERSON_HSN, p.getId_i(), p.getRelation());
				
				
				
			}
				
				
			else
                t = String.format("(\"%d\",\"%d\",\"%d\",\"%d\",\"%s\"),",  
                		0, p.getIdnr(), 0, p.getId_i(), p.getRelation());
				
				
		
			addList.add(t);
			
			
			
		}
		
		if(addList.size() > 1000)
			flush();
		
		
		
	}

	private static void flush(){
		
		
		if(addList.size() == 0)
			return;
		
		
		String s = String.format(sp15.substring(0, 2 * addList.size()), addList.toArray());
		

		addList.clear();
		s = s.substring(0, s.length() -1);

		String u = "insert into hsn_ids.other_hsn_links (Id_I_HSN, IDNR, PERSON_HSN, ID_I_LINKS, RELATION) values" + s;
		//System.out.println(u.substring(0, 120));

		//String u = "insert into links_ids.context (Id_C, Id_D, Source, Type, Value, date_type, estimation, day, month, year) values" + s;
			
   		Utils.executeQ(connection, u);

		
		
	}
	
	private static Set<PersonX> analyseGroup(Set<PersonX> s){
		
		boolean rp    = false;
		boolean spouse = false;
		boolean hssf = false;
		boolean hssm = false;
		boolean hsisf = false;
		boolean hsism = false;
		boolean hsssf = false;
		boolean hsssm = false;
		
		
		// If one of the persons 
		
		Set<PersonX> rs = new HashSet<PersonX>();
		
		
		
		for(PersonX p: s){
			
			
			if(p.relation == null) continue;
			
			if(p.relation.equals("RP")) rp = true;
			else if(p.relation.equals("Spouse")) spouse = true;
			else if(p.relation.equals("Half-Sibling (same father)")) hssf = true;
			else if(p.relation.equals("Half-Sibling (same mother)")) hssm = true;
			else if(p.relation.equals("Half-Sibling-in-law (partner has same father)")) hsisf = true;
			else if(p.relation.equals("Half-Sibling-in-law (partner has same mother)")) hsism = true;
			else if(p.relation.equals("Stillbirth-half-Sibling (same father)")) hsssf = true;
			else if(p.relation.equals("Stillbirth-half-Sibling (same mother)")) hsssm = true;
			
			
			
			
			//System.out.println(p.id_i + "  " + p.relation);
			
		}
		
		String newRelation = null;
		
		if(rp) newRelation = "RP";
		else if(spouse) newRelation = "Spouse";
		else if(hssf && hssm) newRelation = "Sibling";
		else if(hsisf && hsism) newRelation = "Sibling-in-law";
		else if(hsssf && hsssm) newRelation = "Stillbirth-sibling";
		
		if(newRelation != null){
			for(PersonX p: s){
				p.setRelation(newRelation);
				rs.add(p);
				return rs;
			}
		}
		else{
			if(s.size() > 1){
											
				for(PersonX p: s){
					rs.add(p);
					return rs;
					
				}
			}
		}
			
		return s;
	}
	
	public static void addIndiv(Connection connection, int Id_I, String source, String type, String value, 
			String dateType, String estimation, int day, int month, int year, int min_day, int min_month, int min_year,int max_day, int max_month, int max_year){
		
		//System.out.println("In addIndiv, iList.size() = " +  iList.size());
	
		
		String t = String.format("(\"%d\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%d\",\"%d\",\"%d\",\"%d\",\"%d\",\"%d\",\"%d\",\"%d\",\"%d\"),", 
				                    Id_I, "CLA", source, type, value, dateType, estimation, day, month, year, min_day, min_month, min_year, max_day, max_month,  max_year);
		
		iList.add(t);
		
		 if(iList.size() > 1000)
			 flushIndiv(connection);

		
	}

	
	public static void flushIndiv(Connection connection){
		
		//System.out.println("In flushIndiv, iList.size() = " +  iList.size());

		
		if(iList.size() == 0)
			return;
		
		
		String s = String.format(sp15.substring(0, 2 * iList.size()), iList.toArray());
		

		iList.clear();
		s = s.substring(0, s.length() -1);

		String u = "insert into hsn_ids.individual (Id_I, Id_D, Source, Type, Value, date_type, estimation, day, month, year, Start_day, Start_month, Start_year, End_day, End_month, End_year) values" + s;
		//System.out.println(u.substring(0, 120));
		//System.out.println(u);

		//String u = "insert into links_ids.context (Id_C, Id_D, Source, Type, Value, date_type, estimation, day, month, year) values" + s;
			
		Utils.executeQ(connection, u);

	}
	
	public static void addIndivIndiv(Connection connection, int table, int Id_I_1, int Id_I_2, String Id_D, String source, String relation, 
			String dateType, String estimation, int day, int month, int year){
		
//		String t = String.format("(\"%d\",\"%d\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%d\",\"%d\",\"%d\"),",  
		String t = String.format("(\"%d\",\"%d\", \"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%d\",\"%d\",\"%d\"),",  
				                    Id_I_1,  Id_I_2, Id_D, "" + source, relation, dateType, estimation, day, month, year);
		
		
		iiList.add(t);
		 if(iiList.size() > 100)
			 flushIndivIndiv(connection, table);

		
	}
	
	public static void flushIndivIndiv(Connection connection, int table){
		
		if(iiList.size() == 0)
			return;
		
		String s = String.format(sp15.substring(0, 2 * iiList.size()), iiList.toArray());
		iiList.clear();
		s = s.substring(0, s.length() -1);
		String u = "insert into hsn_ids.indiv_indiv";
		if(table != 0)
			u = u + "_" + table;
		u = u + " (Id_I_1, Id_I_2, Id_D, Source, relation, date_type, estimation, day, month, year) values" + s;
			
   		Utils.executeQ(connection, u);
	}
	
	public static void addIndivContext(Connection connection, int Id_I, int Id_C, String source, String relation, 
			String dateType, String estimation, int day, int month, int year){
		

		
		String t = String.format("(\"%d\",\"%s\",\"%d\",\"%s\",\"%s\",\"%s\",\"%s\",\"%d\",\"%d\",\"%d\"),",  
				                    Id_I, "CLA", Id_C, source, relation, dateType, estimation, day, month, year);
		
		
		icList.add(t);
		
		 if(icList.size() > 10000)
			 flushIndivContext(connection);
		
	}
	
	public static void flushIndivContext(Connection connection){
		
		if(icList.size() == 0)
			return;
		
		String s = String.format(sp15.substring(0, 2 * icList.size()), icList.toArray());
		icList.clear();
		s = s.substring(0, s.length() -1);
		String u = "insert into hsn_ids.indiv_context (Id_I, Id_D, Id_C, Source, relation, date_type, estimation, day, month, year) values" + s;
			
		//System.out.println(u.substring(0, 120));
   		Utils.executeQ(connection, u);
	}

	public static void addContext(Connection connection, int Id_C, String source, String type, String value){
		
		
		String t = String.format("(\"%d\",\"%s\",\"%s\",\"%s\", \"%s\"),",  
				                    Id_C, "CLA", source, type, value);
		
		//System.out.println(t);
		cList.add(t);
		
		 if(cList.size() > 10000)
			 flushContext(connection);

		
	}
	
	public static void flushContext(Connection connection){
		
		if(cList.size() == 0)
			return;
		
		String s = String.format(sp15.substring(0, 2 * cList.size()), cList.toArray());
		//System.out.println(s);
		cList.clear();
		s = s.substring(0, s.length() -1);
		String u = "insert into hsn_ids.context (Id_C, Id_D, source, type, value) values" + s;
			
		//System.out.println(u.substring(0, 120));
   		Utils.executeQ(connection, u);
	}
	
	public static void addContextContext(Connection connection, int Id_C_1, int Id_C_2, String source, String relation){
		
		
		String t = String.format("(\"%d\",\"%d\",\"%s\",\"%s\",\"%s\"),",  
				                    Id_C_1, Id_C_2, "CLA", source, relation);
		
		//System.out.println(t);
		ccList.add(t);
		
		 if(ccList.size() > 10000)
			 flushContextContext(connection);

		
	}
	public static void flushContextContext(Connection connection){
		
		if(ccList.size() == 0)
			return;
		
		String s = String.format(sp15.substring(0, 2 * ccList.size()), ccList.toArray());
		//System.out.println(s);
		ccList.clear();
		s = s.substring(0, s.length() -1);
		String u = "insert into hsn_ids.context_context (Id_C_1, Id_C_2, Id_D, source, relation) values" + s;
			
		//System.out.println(u.substring(0, 120));
   		Utils.executeQ(connection, u);
	}
	


	
	private static boolean compare(PersonX p1, PersonX p2){
		
		//System.out.println("In compare");
		
		
		boolean familyNameOK = checkFamilyName(p1.getLastname(), p2.getLastname());
		

		boolean firstNameOK  = checkFirstName(p1.getFirstname(), p2.getFirstname()); 


		//if(familyNameOK && firstNameOK && onlyNames)
			//return 0;
		
		boolean birthDateOK = checkBirthDate(p1, p2); 

		//if(birthDateOK) System.out.println("Birthday OK");
			
		
		if(familyNameOK && firstNameOK && birthDateOK){
			//System.out.println("Same!");
			return true;
		}
		else{
			//System.out.println("Different!");
			return false;
		}
		
	}
	
	private static boolean checkFamilyName(String s1, String s2){
		
		if(s1 == null || s2 == null) return false;
		if(s1.length() == 0 || s2.length() == 0) return false;

		s1 = s1.toLowerCase().trim();
		s2 = s2.toLowerCase().trim();
		
		if(s1.charAt(0) != s2.charAt(0)) return false;
		
		/*
		s1 = s1.replaceAll("y", "ij");
		s2 = s2.replaceAll("ie", "ij");
		s1 = s1.replaceAll("y", "ij");
		s2 = s2.replaceAll("ie", "ij");

		s1 = s1.replaceAll("egt", "echt");
		s2 = s2.replaceAll("egt", "echt");

		s1 = s1.replaceAll("uys", "ist");
		s2 = s2.replaceAll("uys", "ist");

		 */
		
		int distance = Common1.LevenshteinDistance(s1, s2);
		if(distance > 2)  // greater than 2 not allowed
			return false;

		if(distance == 2 && s1.length() <= 5 && s2.length() <= 5)  // distance = 2 is allowed, but not for small strings
			return false;

		
		return true;
		
	}
	
	private static boolean checkFirstName(String s1, String s2){
		
		if(s1 == null || s2 == null) return false;
		if(s1.length() == 0 || s2.length() == 0) return false;

		String name1 = s1.split(" ")[0].toLowerCase().trim();
		String name2 = s2.split(" ")[0].toLowerCase().trim();

		String [] suffixes = {"nus", "nis", "nes", "la", "is", "es", "us", "er", "nie", "nij"}; 

		if(name1.length() > 5)
			removeSuffixes(name1, suffixes);	
		
		if(name2.length() > 5)
			removeSuffixes(name2, suffixes);	

		int distance = Common1.LevenshteinDistance(name1, name2);	


		if(distance > 2)  // greater than 2 not allowed
			return false;

		if(distance == 2 && name1.length() <= 5 && name2.length() <= 5)  // distance = 2 is allowed, but not for small strings
			return false;

		
		return true;
		
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
	
	private static boolean checkBirthDate(PersonX p1, PersonX p2){
		

		int startday1   = p1.getBirth_day_min();
		int startmonth1 = p1.getBirth_month_min();
		int startyear1  = p1.getBirth_year_min();
		
		if(Common1.dateIsValid(startday1, startmonth1, startyear1) != 0) return false;
		
		int endday1   = p1.getBirth_day_max();
		int endmonth1 = p1.getBirth_month_max();
		int endyear1  = p1.getBirth_year_max();

		if(Common1.dateIsValid(endday1, endmonth1, endyear1) != 0) return false;

		int startday2   = p2.getBirth_day_min();
		int startmonth2 = p2.getBirth_month_min();
		int startyear2  = p2.getBirth_year_min();

		if(Common1.dateIsValid(startday2, startmonth2, startyear2) != 0) return false;
		
		int endday2   = p2.getBirth_day_max();
		int endmonth2 = p2.getBirth_month_max();
		int endyear2  = p2.getBirth_year_max();

		if(Common1.dateIsValid(endday2, endmonth2, endyear2) != 0) return false;
		
		int day1   = 0;
		int month1 = 0;
		int year1  = 0;
		
		if(startday1 == endday1 && startmonth1 == endmonth1 && startyear1 == endyear1){
			day1   = startday1;
			month1 = startmonth1;
			year1  = startyear1;
		}
		
		int day2   = 0;
		int month2 = 0;
		int year2  = 0;
		
		if(startday2 == endday2 && startmonth2 == endmonth2 && startyear2 == endyear2){
			day2   = startday2;
			month2 = startmonth2;
			year2  = startyear2;
		}

		//System.out.println(startday1 + "-" + startmonth1 + "-" + startyear1 + " -- " + endday1 + "-" + endmonth1 + "-" + endyear1);
		//System.out.println(startday2 + "-" + startmonth2 + "-" + startyear2 + " -- " + endday2 + "-" + endmonth2 + "-" + endyear2);
		
		
		
		//System.out.println();
		
		if(year1 != 0 && year2 != 0){  // compare two dates

			if(Math.abs(day1 - day2) > 1) // days differ significantly
				if(Math.abs(month1 - month2) != 0 || Math.abs(year1 - year2) != 0){
					//System.out.println("False 2");				
					return false;	
				}

			if(Math.abs(month1 - month2) != 0) // months differ 
				if(Math.abs(day1 - day2) > 1 || Math.abs(year1 - year2) != 0){
					//System.out.println("False 3");
					return false;
				}

			if(Math.abs(year1 - year2) != 0){ // years differ

				if(Math.abs(day1 - day2) > 1 || Math.abs(month1 - month2) != 0){
					//System.out.println("False 4");
					return false;
				}
				else{
					
				    String date1 = String.format("%02d-%02d-%04d", day1, month1, year1);
				    String date2 = String.format("%02d-%02d-%04d", day2, month2, year2);
					if(date1.substring(6,8).equals(date2.substring(6,8))){ // same century

						if(Math.abs(year1 - year2) <= 2 || 
								(date1.substring(6,7).equals(date2.substring(6,7)) &&
										date1.substring(8,9).equals(date2.substring(8,9)) &&
										date1.substring(9,10).equals(date2.substring(9,10))))
							; // ok
						else{
							//System.out.println("False 5");
							return false;
						}
					}
					else{
						//System.out.println("False 6");
						return false;
					}
				}
			}	
			
			return true;
		}
		else{		
			if(year1 != 0 && year2 == 0 && startyear2 != 0){ // compare date and interval
				
				int dayCount1      = Common1.dayCount(day1, month1, year1);
				int dayCount2Start = Common1.dayCount(startday2, startmonth2, startyear2);
				int dayCount2End   = Common1.dayCount(endday2, endmonth2, endyear2);
				
				if(dayCount2Start <= dayCount1 && dayCount1 <= dayCount2End)
					return true;
				else
					return false;
			}
			else
				if(year1 == 0 && year2 != 0 && startyear1 != 0){ // compare date and interval
					
					int dayCount2      = Common1.dayCount(day2, month2, year2);
					int dayCount1Start = Common1.dayCount(startday1, startmonth1, startyear1);
					int dayCount1End   = Common1.dayCount(endday1, endmonth1, endyear1);
					
					if(dayCount1Start <= dayCount2 && dayCount2 <= dayCount1End)
						return true;
					else
						return false;
				}
				else
					if(year1 == 0 && year2 == 0 && startyear1 != 0 && startyear2 != 0){ // compare 2  intervals
						
						int dayCount1Start = Common1.dayCount(startday1, startmonth1, startyear1);
						int dayCount1End   = Common1.dayCount(endday1, endmonth1, endyear1);
						int dayCount2Start = Common1.dayCount(startday2, startmonth2, startyear2);
						int dayCount2End   = Common1.dayCount(endday2, endmonth2, endyear2);

						
						if((dayCount1Start <= dayCount2Start && dayCount1End >= dayCount2Start) ||(dayCount1Start >= dayCount2Start && dayCount2End >= dayCount1Start))
							return true;
						else
							return false;
					}
			
			
		}


		return false;
	}
	
	

	private static void save(Map m6, Map hmu, int Id_C_Highest){

		Set m6s = m6.entrySet();
		Iterator m6i = m6s.iterator();

		while(m6i.hasNext()){
			Map.Entry m5e =(Map.Entry)m6i.next();
			String m5k = (String) m5e.getKey();
			//System.out.println("m5k = " + m5k);
			HashMap<Integer, HashMap<String, HashMap<Integer, Integer>>> m5v = 
					(HashMap<Integer, HashMap<String, HashMap<Integer, Integer>>>)m5e.getValue();

			Set m5s = m5v.entrySet();
			Iterator m5i = m5s.iterator();

			while(m5i.hasNext()){
				Map.Entry m4e =(Map.Entry)m5i.next();
				Integer m4k = (Integer) m4e.getKey();
				//System.out.println(" m4k = " + m4k);

				HashMap<String, HashMap<Integer, Integer>> m4v = 
						(HashMap<String, HashMap<Integer, Integer>>)m4e.getValue();

				Set m4s = m4v.entrySet();
				Iterator m4i = m4s.iterator();
 
				while(m4i.hasNext()){
					Map.Entry m3e =(Map.Entry)m4i.next();
					String m3k = (String) m3e.getKey();
					//System.out.println("  m3k = " + m3k);

					HashMap<Integer,  Integer> m3v = 
							(HashMap<Integer, Integer>)m3e.getValue();

					Set m3s = m3v.entrySet();
					Iterator m3i = m3s.iterator();

					while(m3i.hasNext()){
						Map.Entry m2e =(Map.Entry)m3i.next();
						Integer m2k = (Integer) m2e.getKey();
						//System.out.println("   m2k = " + m2k);

						
						Integer m2v = (Integer)m2e.getValue();
						//System.out.println("   m2v = " + m2v);
						
						if(m2v > Id_C_Highest){

							//System.out.println(m5k);
							int Id_C_Municipality = 0;
							if(hmu.get(m5k) != null)
								Id_C_Municipality = (Integer) hmu.get(m5k);

							if(Id_C_Municipality != 0){

								addContext(connection, m2v, m3k, "SEQUENCE_NUMBER", "" + m2k);
								addContext(connection, m2v, m3k, "TYPE", "" + m3k);
								addContext(connection, m2v, m3k, "YEAR", "" + m4k);

								addContextContext(connection, m2v, Id_C_Municipality, m3k, "Source and Municipality");
							}
						}
					}
				}
			}
		}
	}
	
	private static int add(HashMap<String, HashMap<Integer, HashMap<String, HashMap<Integer, Integer>>>>  m, String place, Integer year, String type, int seqNo, int Id_C_p){
		
		HashMap<Integer, HashMap<String, HashMap<Integer, Integer>>> m1 = null;
		if(m.get(place) != null)
			 m1 = (HashMap<Integer, HashMap<String, HashMap<Integer, Integer>>>) m.get(place);
		else{
			m1 = new HashMap<Integer, HashMap<String, HashMap<Integer, Integer>>>();
			m.put(place, m1);
		}
			
	    HashMap<String, HashMap<Integer, Integer>> m2 = null;
		if(m1.get(year) != null)
			 m2 = (HashMap<String, HashMap<Integer, Integer>>) m1.get(year);
		else{
			m2 = new HashMap<String, HashMap<Integer, Integer>>();
			m1.put(year, m2);
		}
			
		HashMap<Integer, Integer> m3 = null;
		if(m2.get(type) != null)
			 m3 = (HashMap<Integer, Integer>) m2.get(type);
		else{
			m3 = new  HashMap<Integer, Integer>();
			m2.put(type, m3);
		}
		
		
		if(m3.get(seqNo) != null){
			return m3.get(seqNo);
		}
		
		else{
			if(Id_C_p == 0){
				m3.put(seqNo,  ++Id_C);
				return Id_C;
			}
			else{
				m3.put(seqNo,  Id_C_p);
				return(Id_C_p); 
			}
		}
		
	}
	
	public static JTextArea getTextArea() {
		return textArea;
	}


	public static void setTextArea(JTextArea textArea) {
		HSNLinksIntegration.textArea = textArea;
	}


	public static DataOutputStream getOut() {
		return out;
	}


	public static void setOut(DataOutputStream out) {
		HSNLinksIntegration.out = out;
	}
  	
    public static void print(String line) {
    	
    	System.out.println(line);
        if (out != null) {
            try {
                out.writeUTF(line);
            } catch (IOException e) {
               // e.printStackTrace();
            	System.out.println("Client Message: " + line);
            }
        } else {
            System.out.println(line);
        }
    }

 	

}