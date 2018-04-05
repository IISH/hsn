package nl.iisg.ids_init;


import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JTextArea;

import nl.iisg.idscontext.CONTEXT;
import nl.iisg.idscontext.CONTEXT_CONTEXT;
import nl.iisg.ref.Ref_Location;
import nl.iisg.ref.Ref_Municipality;


public class IDS_INIT implements Runnable {
	
	static EntityManagerFactory              factory_ref   = Persistence.createEntityManagerFactory("ref_tables_2");
	static EntityManager                     em_ref        = factory_ref.createEntityManager(); 
	static EntityManagerFactory              factory_context = Persistence.createEntityManagerFactory("context");
	static EntityManager                     em_context      = factory_context.createEntityManager(); 

	static List<Ref_Location>                ref_location  = null;
	static List<Ref_Municipality>             ref_municipality  = null;
	static String version                           = null;
	
	
	 public void run() {

	        main(new String[0]);
	    }
	
	public static void main(String [] s){
	
		
		
		
		 print("\nIDS - Initialise          started\n");
		
		populateContext();
		
		 print("\nIDS - Initialise          ended\n");
		
	}
	
	public static JTextArea getTextArea() {
		return textArea;
	}

	public static void setTextArea(JTextArea textArea) {
		IDS_INIT.textArea = textArea;
	}

	public static DataOutputStream getOut() {
		return out;
	}

	public static void setOut(DataOutputStream out) {
		IDS_INIT.out = out;
	}

	static JTextArea textArea = null;
    static DataOutputStream out = null;
    
	 public IDS_INIT(DataOutputStream out) {
	        setTextArea(textArea);
	        setOut(out);
	    }
	 

	 public static void print(String line) {
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
	 
	 static void populateContext(){
		 
		 
		 em_context.getTransaction().begin();

		 Query query = em_context.createNativeQuery(CreateIDSTables.CONTEXT);  
		 query.executeUpdate();

		 query = em_context.createNativeQuery(CreateIDSTables.CONTEXT_CONTEXT);  
		 query.executeUpdate();


		 query = em_context.createNativeQuery("TRUNCATE TABLE context");  
		 query.executeUpdate();
		 query = em_context.createNativeQuery("TRUNCATE TABLE context_context");  
		 query.executeUpdate();


		 query = em_context.createNativeQuery("TRUNCATE TABLE individual");  
		 query.executeUpdate();
		 query = em_context.createNativeQuery("TRUNCATE TABLE indiv_indiv");  
		 query.executeUpdate();
		 query = em_context.createNativeQuery("TRUNCATE TABLE indiv_context");  
		 query.executeUpdate();

		 
		 
		 HashMap<String, Integer> hmap = new HashMap<String, Integer>();

		 
		 String t = "select a from Ref_Municipality a";
		 
		 query = em_ref.createQuery(t);		 
		 
		 ref_municipality =  query.getResultList();
		 
		 for(Ref_Municipality rm: ref_municipality)			 
			 hmap.put( rm.getMunicipalityName(), rm.getCodeMunicipality());
			 
			 
		 
	     
		 	
		 
		 String s = "select a from Ref_Location  a "
				+ "group by a.country, a.province, a.region, a.municipality, a.location "
	            + "order by a.country, a.province, a.region, a.municipality, a.location ";
		 
		
		
		
		
		
		 query = em_ref.createQuery(s);
		 
		 System.out.println( "Starting query ");
		 
		 
		 ref_location =  query.getResultList();
	     
	     
	     System.out.println("Result"); 
	     /*
	     Collections.sort(ref_location, new Comparator<Ref_Location>()
					{
				public int compare(Ref_Location l1, Ref_Location l2){
					
					// "select * from links_general.ref_location group by location_no order by " +
					// "country, region, province,  municipality, location"
					
					String country1 = l1.getCountry() != null ? l1.getCountry() : "";  
					String country2 = l2.getCountry() != null ? l2.getCountry() : "";
					
					String region1 = l1.getRegion() != null ? l1.getRegion() : "";  
					String region2 = l2.getRegion() != null ? l2.getRegion() : "";  

					String province1 = l1.getProvince() != null ? l1.getProvince() : "";  
					String province2 = l2.getProvince() != null ? l2.getProvince() : "";
					
					String municipality1 = l1.getMunicipality() != null ? l1.getMunicipality() : "";  
					String municipality2 = l2.getMunicipality() != null ? l2.getMunicipality() : "";  

					String locality1 = l1.getLocation() != null ? l1.getLocation() : "";  
					String locality2 = l2.getLocation() != null ? l2.getLocation() : "";  

					if(country1.compareToIgnoreCase(country2) < 0) return -1;
					if(country1.compareToIgnoreCase(country2) > 0) return +1;
					
					if(region1.compareToIgnoreCase(region2) < 0) return -1;
					if(region1.compareToIgnoreCase(region2) > 0) return +1;

					if(province1.compareToIgnoreCase(province2) < 0) return -1;
					if(province1.compareToIgnoreCase(province2) > 0) return +1;

					if(municipality1.compareToIgnoreCase(municipality2) < 0) return -1;
					if(municipality1.compareToIgnoreCase(municipality2) > 0) return +1;

					if(locality1.compareToIgnoreCase(locality2) < 0) return -1;
					if(locality1.compareToIgnoreCase(locality2) > 0) return +1;

					
					
					
					return 0;

				}
					});
	     */
	     
	    	String country      = "";
			String province     = "";
			String region       = "";
			String municipality = "";
			String location     = "";
			
			int Id_C_CurrentCountry      = -1;
			int Id_C_CurrentProvince     = -1;
			int Id_C_CurrentRegion       = -1;
			int Id_C_CurrentMunicipality = -1;
			int Id_C_CurrentLocation     = -1;
			
			int Id_C = 0;
			String x = null;
	     
	     
			
	     
			for(Ref_Location rl: ref_location){
				
				//System.out.println("Looping!!!");
				
				boolean no_country = false;
				boolean no_province = false;
				boolean no_region = false;
				boolean no_municipality = false;
				boolean no_location = false;
				
				if(rl.getCountry() == null      || rl.getCountry().trim().length() == 0) no_country = true; 
				if(rl.getRegion()  == null      || rl.getRegion().trim().length()  == 0) no_region = true; 
				if(rl.getProvince()  == null    || rl.getProvince().trim().length()  == 0) no_province = true; 
				if(rl.getMunicipality() == null || rl.getMunicipality().trim().length()  == 0) no_municipality = true; 
				if(rl.getLocation()  == null    || rl.getLocation().trim().length()  == 0) no_location = true; 
				
				if(no_country && no_region && no_province && no_municipality && no_location) continue;
				
				//System.out.println("C= " + country + ", R= " + region + ", P=" + province + ", M= " + municipality + ", L=" + location);
				System.out.format("%10s %10s %10s %10s %10s\n", country, province, region, municipality, location);
				
				if(rl.getCountry() != null && !rl.getCountry().trim().equalsIgnoreCase("Unknown")){
					
					if(!rl.getCountry().trim().equalsIgnoreCase(country)){
						
						country = rl.getCountry().trim();
						province     = "";
						region       = "";
						municipality = "";
						location = "";
						
						addContext(++Id_C, "NAME", country);
						addContext(  Id_C, "LEVEL", "Country");
						
						Id_C_CurrentCountry = Id_C;
						Id_C_CurrentRegion       = -1;
						Id_C_CurrentProvince     = -1;
						Id_C_CurrentMunicipality = -1;
						Id_C_CurrentLocation     = -1;	
					}
				}
				
				if(rl.getProvince() != null && !rl.getProvince().trim().equalsIgnoreCase("Unknown")){
					
					if(!rl.getProvince().trim().equalsIgnoreCase(province)){
						province = rl.getProvince().trim();
						region = "";
						municipality = "";
						location = "";
					
					
						addContext(++Id_C, "NAME", province);
						addContext(  Id_C, "LEVEL", "Province");
					
						Id_C_CurrentProvince     = Id_C;
						Id_C_CurrentRegion       = -1;
						Id_C_CurrentMunicipality = -1;
						Id_C_CurrentLocation     = -1;	
					
						int Id_C_Temp = Id_C_CurrentRegion;
						x = "Region";
						if(Id_C_Temp == -1){
							Id_C_Temp = Id_C_CurrentCountry;
							x = "Country";
						}
					
						if(Id_C_Temp > 0)
							addContextContext( Id_C, Id_C_Temp, "Province and " + x);
					}
					
				}
				

					
				
				if(rl.getRegion() != null && !rl.getRegion().trim().equalsIgnoreCase("Unknown")){
					
					if(!rl.getRegion().trim().equalsIgnoreCase(region)){
					
						region = rl.getRegion().trim();
						municipality = "";
						location = "";					
					
						addContext(++Id_C, "NAME", region);
						addContext(  Id_C, "LEVEL", "Region");
					
						Id_C_CurrentRegion       = Id_C;
						Id_C_CurrentMunicipality = -1;
					    Id_C_CurrentLocation     = -1;	
					
					if(Id_C_CurrentCountry > 0)
						addContextContext(Id_C, Id_C_CurrentCountry, "Region and Country");
					}
				}
				
				
				

				if(rl.getMunicipality() != null && !rl.getMunicipality().trim().equalsIgnoreCase("Unknown")){

					if(!rl.getMunicipality().trim().equalsIgnoreCase(municipality)){	

						municipality = rl.getMunicipality().trim();
						location = "";

						addContext(++Id_C, "NAME", municipality);
						addContext(  Id_C, "LEVEL", "Municipality");
						
						if(hmap.get(municipality) != null){
							addContext(Id_C, "HSN_MUNICIPALITY_CODE", "" + hmap.get(municipality));													
						}

						Id_C_CurrentMunicipality = Id_C;
						Id_C_CurrentLocation     = -1;	

						int Id_C_Temp = Id_C_CurrentProvince;
						x = "Province";
						if(Id_C_Temp == -1){
							Id_C_Temp = Id_C_CurrentRegion;
							x = "Region";
						}
						if(Id_C_Temp == -1){
							Id_C_Temp = Id_C_CurrentCountry;
							x = "Country";
						}
						if(Id_C_Temp > 0)
							addContextContext( Id_C, Id_C_Temp, "Municipality and " + x);					

					}
				}
				
				if(rl.getLocation() != null && !rl.getLocation().trim().equalsIgnoreCase("Unknown")){


					if(!rl.getLocation().trim().equalsIgnoreCase(location)){

						location = rl.getLocation().trim();

						addContext(++Id_C, "NAME", location);
						addContext(  Id_C, "LEVEL", "Locality");

						//if(rl.getLocationNo() != 0)
							//addContext(Id_C, "HSN_MUNICIPALITY_CODE", "" + rl.getLocationNo());
						
						Id_C_CurrentLocation     = Id_C;	

						int Id_C_Temp = Id_C_CurrentMunicipality;
						x = "Municipality";
						if(Id_C_Temp == -1){
							Id_C_Temp = Id_C_CurrentProvince;
							x = "Province";
						}
						if(Id_C_Temp == -1){
							Id_C_Temp = Id_C_CurrentRegion;
							x = "Region";
						}
						if(Id_C_Temp == -1){
							Id_C_Temp = Id_C_CurrentCountry;
							x = "Country";
						}
						if(Id_C_Temp > 0)
							addContextContext( Id_C, Id_C_Temp, "Locality and " + x);					

					}

				}
			}
				
			em_context.getTransaction().commit();
			
			print("Highest Id_C = " + Id_C);
		 
	 }
	 
	 static void addContext(int Id_C, String type, String value){

		 CONTEXT context = new CONTEXT();

		 context.setId_C(Id_C);
		 context.setId_D(version);
		 //System.out.println(getTypes().get(i));
		 context.setSource("REF_LOCATION");
		 context.setType(type);
		 context.setValue(value);

		 em_context.persist(context);



	 }

	 static void addContextContext(int Id_C_1, int Id_C_2, String relation){
		 
		 CONTEXT_CONTEXT cc = new CONTEXT_CONTEXT();
		 cc.setId_D(version);
		 cc.setSource("REF_LOCATION");
		 cc.setId_C_1(Id_C_1);  
		 cc.setId_C_2(Id_C_2);  
		 cc.setRelation(relation);
		 
		 em_context.persist(cc);
	 }

	public static String getVersion() {
		return version;
	}

	public static void setVersion(String version) {
		IDS_INIT.version = version;
	}
	 
	 

		
	}