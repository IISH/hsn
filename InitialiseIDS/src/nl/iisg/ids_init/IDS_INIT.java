package nl.iisg.ids_init;


import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JTextArea;

import nl.iisg.idscontext.CONTEXT;
import nl.iisg.idscontext.CONTEXT_CONTEXT;
import nl.iisg.ref.Ref_Location;


public class IDS_INIT implements Runnable {
	
	static EntityManagerFactory              factory_ref   = Persistence.createEntityManagerFactory("ref_tables_2");
	static EntityManager                     em_ref        = factory_ref.createEntityManager(); 
	static EntityManagerFactory              factory_context = Persistence.createEntityManagerFactory("context");
	static EntityManager                     em_context      = factory_context.createEntityManager(); 

	static List<Ref_Location>                ref_location  = null;
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
		 
		 
		 query = em_ref.createQuery("select a from Ref_Location a");
	     ref_location =  query.getResultList();
	     
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

					if(country1.compareTo(country2) < 0) return -1;
					if(country1.compareTo(country2) > 0) return +1;
					
					if(region1.compareTo(region2) < 0) return -1;
					if(region1.compareTo(region2) > 0) return +1;

					if(province1.compareTo(province2) < 0) return -1;
					if(province1.compareTo(province2) > 0) return +1;

					if(municipality1.compareTo(municipality2) < 0) return -1;
					if(municipality1.compareTo(municipality2) > 0) return +1;

					if(locality1.compareTo(locality2) < 0) return -1;
					if(locality1.compareTo(locality2) > 0) return +1;

					
					
					
					return 0;

				}
					});
	     
	     
	    	String country      = "";
			String region       = "";
			String province     = "";
			String municipality = "";
			String locality     = "";
			
			int Id_C_CurrentCountry      = -1;
			int Id_C_CurrentRegion       = -1;
			int Id_C_CurrentProvince     = -1;
			int Id_C_CurrentMunicipality = -1;
			int Id_C_CurrentLocality     = -1;
			
			int Id_C = 0;
			String x = null;
	     
	     
			
	     
			for(Ref_Location rl: ref_location){
				
				if(rl.getCountry() != null && !rl.getCountry().equals(country) && !rl.getCountry().equals("Onbekend")){
					
					country = rl.getCountry();
					addContext(++Id_C, "NAME", country);
					addContext(  Id_C, "LEVEL", "Country");
					
					Id_C_CurrentCountry = Id_C;
					Id_C_CurrentRegion       = -1;
					Id_C_CurrentProvince     = -1;
					Id_C_CurrentMunicipality = -1;
					Id_C_CurrentLocality     = -1;	
				}
				else
					if(rl.getCountry() != null && rl.getCountry().equals("Onbekend")) continue;

				
				
				
					
				
				if(rl.getRegion() != null && !rl.getRegion().equals(region) && !rl.getRegion().equals("Onbekend")){
					
					region = rl.getRegion();
					addContext(++Id_C, "NAME", region);
					addContext(  Id_C, "LEVEL", "Region");
					
					Id_C_CurrentRegion       = Id_C;
					Id_C_CurrentProvince     = -1;
					Id_C_CurrentMunicipality = -1;
					Id_C_CurrentLocality     = -1;	
					
					if(Id_C_CurrentCountry > 0)
						addContextContext(Id_C, Id_C_CurrentCountry, "Region and Country");
				}
				else
					if(rl.getRegion() != null && rl.getRegion().equals("Onbekend")){
						
						region = "";
						Id_C_CurrentRegion = -1;
						
					}
				
				
				

				if(rl.getProvince() != null && !rl.getProvince().equals(province) && !rl.getProvince().equals("Onbekend")){
					
					province = rl.getProvince();
					addContext(++Id_C, "NAME", province);
					addContext(  Id_C, "LEVEL", "Province");
					
					Id_C_CurrentProvince     = Id_C;
					Id_C_CurrentMunicipality = -1;
					Id_C_CurrentLocality     = -1;	
					
					int Id_C_Temp = Id_C_CurrentRegion;
					x = "Region";
					if(Id_C_Temp == -1){
						Id_C_Temp = Id_C_CurrentCountry;
						x = "Country";
					}
					if(Id_C_Temp > 0)
						addContextContext( Id_C, Id_C_Temp, "Province and " + x);					
					
				}
				else
					if(rl.getProvince() != null && rl.getProvince().equals("Onbekend")){
						
						province = "";
						Id_C_CurrentProvince = -1;
						
					}
				
				if(rl.getMunicipality() != null && !rl.getMunicipality().equals(municipality) && !rl.getMunicipality().equals("Onbekend")){
					
					municipality = rl.getMunicipality();
					addContext(++Id_C, "NAME", municipality);
					addContext(  Id_C, "LEVEL", "municipality");
					
					Id_C_CurrentMunicipality = Id_C;
					Id_C_CurrentLocality     = -1;	
					
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
				else
					if(rl.getMunicipality() != null && rl.getMunicipality().equals("Onbekend")){
						
						municipality = "";
						Id_C_CurrentMunicipality = -1;
						
					}
				
				if(rl.getLocation() != null && !rl.getLocation().equals(locality) && !rl.getLocation().equals("Onbekend")){
					
					locality = rl.getLocation();
					addContext(++Id_C, "NAME", locality);
					addContext(  Id_C, "LEVEL", "locality");
					
					Id_C_CurrentLocality     = Id_C;	
					
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
				else
					if(rl.getLocation() != null && rl.getLocation().equals("Onbekend")){
						
						locality = "";
						Id_C_CurrentLocality = -1;
						
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
		 context.setType(type);
		 context.setValue(value);

		 em_context.persist(context);



	 }

	 static void addContextContext(int Id_C_1, int Id_C_2, String relation){
		 
		 CONTEXT_CONTEXT cc = new CONTEXT_CONTEXT();
		 cc.setId_D(version);
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