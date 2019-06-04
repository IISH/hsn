 package nl.iisg.idscontext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class Contxt { 

	static List<ContextElement> topList = null;
	static List<ContextElement> ceList = null;
	static ContextElement top = null;
	
	static HashMap<Integer, ContextElement>  LocationID_2_Id_C; // = new HashMap<Integer, ContextElement>();
    static HashMap<String, ContextElement>  municipality_2_Id_C; // = new HashMap<String, ContextElement>();
	
	public static void main(String args[]) {

		
		//initializeContext();
		System.out.println("toplist = " + topList);
		
		//add("Nederland" , "Noord-Holland", "Amsterdam", "Oost", "Zeeburg", "Cruquiusweg 31 hs", "Pakhuis Willem I");
		
		//System.exit(0);
		
		for(ContextElement ce1: topList){		
			printContextElement(ce1);
			for(ContextElement ce2: ce1.getChildren()){			
				printContextElement(ce2);
				for(ContextElement ce3: ce2.getChildren()){			
					printContextElement(ce3);
					
					for(ContextElement ce4: ce3.getChildren()){			
						printContextElement(ce4);
						for(ContextElement ce5: ce4.getChildren()){			
							printContextElement(ce5);
							for(ContextElement ce6: ce5.getChildren()){			
								printContextElement(ce6);
								for(ContextElement ce7: ce6.getChildren()){			
									printContextElement(ce7);
								}
							}
						}
					}
				}
			}
		}
	}

	public static void printTopList(){
		
		for(ContextElement ce1: topList){		
			printContextElement(ce1);
			for(ContextElement ce2: ce1.getChildren()){			
				printContextElement(ce2);
				for(ContextElement ce3: ce2.getChildren()){			
					printContextElement(ce3);
					for(ContextElement ce4: ce3.getChildren()){			
						printContextElement(ce4);
						for(ContextElement ce5: ce4.getChildren()){			
							printContextElement(ce5);
							for(ContextElement ce6: ce5.getChildren()){			
								printContextElement(ce6);
								for(ContextElement ce7: ce6.getChildren()){			
									printContextElement(ce7);
								}
							}
						}
					}
				}
			}
		}
		
		
	}
	
	public static void initializeContext(){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("context");				
		EntityManager em = factory.createEntityManager();	

		List<CONTEXT> cList = new ArrayList<CONTEXT>();
		cList = loadContext(em);
		ceList = createContextElementList(cList);
		List<CONTEXT_CONTEXT> ccList = new ArrayList<CONTEXT_CONTEXT>();
		ccList = loadContext_Context(em);
		createContextHierarchy(ceList, ccList); // link ContextElements
		topList = createTopList(ceList);
		
		//printTopList();
		
	}

	public static List<ContextElement> createTopList(List<ContextElement> ceList){
		
		//ContextElement ce  = new ContextElement();
		//ce.types.add("Name");
		//ce.values.add("Nederland");
		//ce.types.add("Level");
		//ce.values.add("Country");
		//ce.setId_C(1);
		
		//ceList.add(0, ce); // add in front
		
		
		//for(ContextElement ce1: ceList){
		//	for(int i = 0; i < ce1.types.size(); i++){
		//		if(ce1.types.get(i).equalsIgnoreCase("Level")){
		//			if(ce1.values.get(i).equalsIgnoreCase("Province")){
		//				//System.out.println("Adding province!!!");
		//				ce.getChildren().add(ce1);
		//				ce1.setParent(ce);
		//				break;
		//			}
		//		}
		//	}
		//}
		
		ArrayList<ContextElement> topList = new ArrayList<ContextElement>();
		
		// Allocate top element
		
		ContextElement ce  = new ContextElement();
		ce.types.add("NAME");
		ce.values.add("Top CE");
		ce.types.add("LEVEL");
		ce.values.add("Top");
		
		topList.add(ce);
		
		for(ContextElement ce1: ceList){
			for(int i = 0; i < ce1.types.size(); i++){
				if(ce1.types.get(i).equalsIgnoreCase("LEVEL")){
					if(ce1.values.get(i).equalsIgnoreCase("Country")){
						ce.getChildren().add(ce1);
						
					}
				}
			}
		}
						
		top = ce;
		return topList;
		
	}
	
	
	public static void printContextElement(ContextElement ce){
		
		String level    = null;
		String name     = null;
		String street   = null;
		String num      = null;
		String add      = null;
		String seqno    = null;
		String period   = null;

		for(int i = 0; i < ce.types.size(); i++){
			if(ce.types.get(i).equalsIgnoreCase("LEVEL"))
				level = ce.values.get(i);
			if(ce.types.get(i).equalsIgnoreCase("NAME"))
				name = ce.values.get(i);
			if(ce.types.get(i).equalsIgnoreCase("STREET"))
				street = ce.values.get(i);
			if(ce.types.get(i).equalsIgnoreCase("QUARTER"))
				street = ce.values.get(i);
			if(ce.types.get(i).equalsIgnoreCase("HOUSE_NUMBER"))
				num = ce.values.get(i);
			if(ce.types.get(i).equalsIgnoreCase("PERIOD"))
				period = ce.values.get(i);
			if(ce.types.get(i).equalsIgnoreCase("SEQUENCE_NUMBER"))
				seqno = ce.values.get(i);
			
		}
		
		if(street != null){
			name = street;
			if(num != null){
				name = name + " " + num;
				if(add != null){
					name = name + " " + add;
				}
			}
		}
		
		if(level.equalsIgnoreCase("Source")){
			if(seqno != null)
				name = name + " " + seqno;
			if(period != null)
				name = name + " " + period;
			
		}
		
		//if(name == null)
			//System.out.println("ce with Id_C = " + ce.getId_C() + " Level = " + level + "has no NAME!");
		
		//if(1==1) return;
		
		
		String indent = "     ";
		String prefix = null;
		if(level.equalsIgnoreCase("Top"))          prefix  = "";
		if(level.equalsIgnoreCase("Country"))      prefix  = indent;
		if(level.equalsIgnoreCase("Province"))     prefix  = indent + indent;
		if(level.equalsIgnoreCase("Municipality")) prefix  = indent + indent + indent;
		if(level.equalsIgnoreCase("Locality"))     prefix  = indent + indent + indent + indent;
		if(level.equalsIgnoreCase("Quarter"))      prefix  = indent + indent + indent + indent + indent;
		if(level.equalsIgnoreCase("Address"))      prefix  = indent + indent + indent + indent + indent;
		if(level.equalsIgnoreCase("Source"))       prefix  = indent + indent + indent + indent + indent;
		if(level.equalsIgnoreCase("Name"))         prefix  = indent + indent + indent + indent + indent + indent + indent;
		
		System.out.println(prefix + name);
		
		if(1==1)
			return;
		
		
		//if(ce.getId_C() <= Utils.getOld_id_C()) return;
		
		System.out.println();
		
		
		do{		
			System.out.println("Id_C = " + ce.getId_C());

			for(int i = 0; i < ce.types.size(); i++){

				System.out.println(ce.types.get(i) + " = " + ce.values.get(i));

			}
			ce = ce.getParent();
		} while(ce != null); 
	}
	
	
	public static ArrayList<ContextElement> createContextElementList(List<CONTEXT> cList){
		
		ArrayList<ContextElement> ceList = new ArrayList<ContextElement>();
		
		Collections.sort(cList, new Comparator<CONTEXT>()
				{
			public int compare(CONTEXT c1, CONTEXT c2){
				if(c1.getId_C() < c2.getId_C())
					return -1;
				if(c1.getId_C() > c2.getId_C())
					return 1;
				return 0;
			}
				});
		
		
		int curr_Id_C = -1;
		
		LocationID_2_Id_C = new HashMap<Integer, ContextElement>();
		municipality_2_Id_C = new HashMap<String, ContextElement>();
		
		ContextElement ce = null;
		for(int i = 0; i < cList.size(); i++){			
			
			if(cList.get(i).getId_C() != curr_Id_C){
				curr_Id_C = cList.get(i).getId_C();
				//System.out.println("Id_C = " + curr_Id_C);
				if(ce != null){
					
					String name = "";
					boolean isMunicipality = false;
					for(int ii = 0; ii < ce.getTypes().size(); ii++){
						
						if(ce.getTypes().get(ii).equalsIgnoreCase("LEVEL") && ce.getValues().get(ii).equalsIgnoreCase("Municipality"))
							isMunicipality = true;

						if(ce.getTypes().get(ii).equalsIgnoreCase("NAME"))
							name = ce.getValues().get(ii);

					}
					
					if(isMunicipality &&  name.length() > 0)
						municipality_2_Id_C.put(name.trim(), ce);
					
					ceList.add(ce);
				}
				ce = new ContextElement();
				ce.setId_C(cList.get(i).getId_C());
				
				//int location_no = 
				
				//LocationID_2_Id_C.put(arg0, arg1)
				
				
				
			}
			
			ce.types.add(cList.get(i).getType());
			ce.values.add(cList.get(i).getValue());
			
			if(cList.get(i).getType().equalsIgnoreCase("HSN_MUNICIPALITY_CODE")){
				
				//System.out.println("adding to list");
				int hsn_municipality_code = new Integer(cList.get(i).getValue());
				LocationID_2_Id_C.put(hsn_municipality_code, ce);
				
			}
			
				
			
		}
		if(ce != null)
			ceList.add(ce);
		
		Utils.setOld_id_C(curr_Id_C);
		System.out.println("Highest Old Id_C = " + curr_Id_C);
		Utils.setId_C(curr_Id_C + 1); // for new elements
		
		return ceList;
		
	}
	
	public static void createContextHierarchy(List<ContextElement> ceList, List<CONTEXT_CONTEXT> ccList){
		
		
		for(CONTEXT_CONTEXT cc: ccList){
			
			ContextElement ceLower = new ContextElement();
			ceLower.setId_C(cc.getId_C_1());
			//System.out.println("Id_C1 = " + cc.getId_C_1());
			
			int i = Collections.binarySearch(ceList, ceLower, new Comparator<ContextElement>()
					{
				public int compare(ContextElement ce1, ContextElement ce2){
					if(ce1.getId_C() < ce2.getId_C())
						return -1;
					if(ce1.getId_C() > ce2.getId_C())
						return 1;
					return 0;
				}
					});
			
			//System.out.println(i);
			
			ContextElement ceHigher = new ContextElement();
			ceHigher.setId_C(cc.getId_C_2());
			//System.out.println("Id_C2 = " + cc.getId_C_2());
			
			int j = Collections.binarySearch(ceList, ceHigher, new Comparator<ContextElement>()
					{
				public int compare(ContextElement ce1, ContextElement ce2){
					if(ce1.getId_C() < ce2.getId_C())
						return -1;
					if(ce1.getId_C() > ce2.getId_C())
						return 1;
					return 0;
				}
					});

			//System.out.println(j);
			
			ceList.get(j).children.add(ceList.get(i));
			ceList.get(i).parent = ceList.get(j);
		}		
	}
	

	public static List<CONTEXT> loadContext(EntityManager em){
		
		Query q = em.createQuery("select c from CONTEXT c");
		return q.getResultList();	
		
	}

	public static List<CONTEXT_CONTEXT> loadContext_Context(EntityManager em){
		
		Query q = em.createQuery("select cc from CONTEXT_CONTEXT cc");
		return q.getResultList();	
		
	}

	public static ContextElement get(int municipalityCode){
		
		return(LocationID_2_Id_C.get(municipalityCode));
		
		
		//System.out.println("CEList size = " + ceList.size());
		
		//for(ContextElement ce: ceList){
		//	for(int i = 0; i < ce.getTypes().size(); i++){
		//		//System.out.println("Type = " + ce.getTypes().get(i));
		//		if(ce.getTypes().get(i).equalsIgnoreCase("HSN_MUNICIPALITY_CODE")){
		//			int x = new Integer(ce.getValues().get(i));
		//			//System.out.println("code = " + x);
		//			if(x == municipalityCode)
		//				return ce;
		//		}
		//	}
		//}

		//System.out.println("1 Municipality Code = " + municipalityCode + " not found");
		//return null;
		
		
		//ContextElement ce = new ContextElement();
		//ce.setId_C(Id_C);
		
		//int i = Collections.binarySearch(ceList, ce, new Comparator<ContextElement>()
		//		{
		//	public int compare(ContextElement ce1, ContextElement ce2){
		//		if(ce1.getId_C() < ce2.getId_C())
		//			return -1;
		//		if(ce1.getId_C() > ce2.getId_C())
		//			return 1;
		//		return 0;
		//	}
		//		});
		
				
	}
	
	public static ContextElement get2(String municipality){
		
		//System.out.println("Input " + municipality);
		
		if(municipality == null) return null;
		
		//int a;
		//if(municipality.equalsIgnoreCase("Al"))
		//	a = 1/0;
		
		//ArrayList<String> municipalities  = new ArrayList<String>();
		//ArrayList<String> levels          = new ArrayList<String>();
			
		//if(1==1) return null;
		/*
		int cnt = 0;
		for(ContextElement ce: ceList){
			cnt++;
			String name = null;
			String level = null;
			for(int i = 0; i < ce.getTypes().size(); i++){
				if(ce.getTypes().get(i).equalsIgnoreCase("NAME")){
				   name = ce.getValues().get(i);
				   //municipalities.add(name);
				}
				if(ce.getTypes().get(i).equalsIgnoreCase("LEVEL")){
				   level = ce.getValues().get(i);
				   //levels.add(level);
				}
				
				//System.out.println("name = " + name + " level = "+ level);
			}

			if(level.equalsIgnoreCase("Municipality")){ 
					if(name.trim().equalsIgnoreCase(municipality.trim()))
						//System.out.println("1 name = "+ municipality + "ce.Id_C = " + ce.getId_C());
						return ce;
			}
		}
		
		*/
		//System.out.println("1 Municipality " + municipality +  " not found");
		
		if(municipality_2_Id_C.containsKey(municipality.trim())){
			    //ContextElement ce = municipality_2_Id_C.get(municipality.trim());
				//System.out.println("2 Name = "+ municipality + " ce.Id_C = " + ce.getId_C());
				return(municipality_2_Id_C.get(municipality.trim()));
		}
		else{
			//System.out.println(municipality_2_Id_C.get("No valid Location")); // This Municipality has been added to Ref_Location
			return(municipality_2_Id_C.get("No valid Location")); 
			//System.out.println("2 Municipality " + municipality +  " not found");
			//int x = 1/0; // XYZ
			//return null;
		}
				
				
		//if(1/0==1);
		//System.exit(0); 
		
		/*
		
		if(municipalities.size() != levels.size()) System.out.println("Count mismatch");
		else{
			if(municipality.equalsIgnoreCase("Amsterdam"))
				System.out.println("+++> Adding" + municipality);
				
				//for(int i = 0; i < municipalities.size(); i++)
					//System.out.println(municipalities.get(i) + "    " + levels.get(i));
		}
		
		if(municipality.length() > 3 && municipality.substring(0, 4).equalsIgnoreCase("AMS"))
			System.out.println("+++> Adding" + municipality);
		//System.exit(9);
		// We add the municipality to the context system
		ContextElement ceNew = new ContextElement();
		ceNew.getTypes().add("LEVEL");
		ceNew.getValues().add("Municipality");
		ceNew.getTypes().add("NAME");
		ceNew.getValues().add(municipality.trim());

		// Link municipality directly to country (Unknown)

		ContextElement ceParent = null;
      	for(ContextElement ce1: topList.get(0).getChildren()){
			//System.out.println("ce = " + ce1);
			String name = null;
			String level = null;
			for(int i = 0; i < ce1.getTypes().size(); i++){
				if(ce1.getTypes().get(i).equalsIgnoreCase("NAME"))
					   name = ce1.getValues().get(i);
				if(ce1.getTypes().get(i).equalsIgnoreCase("LEVEL"))
					   level = ce1.getValues().get(i);
			}
			if(level.equalsIgnoreCase("Country") && name.equalsIgnoreCase("Unknown")){
				
				ceParent = ce1;
				break;
				
			}
		}
		//System.out.println("ceParent = " + ceParent);
		if(ceParent != null){
			ceNew.setId_C(Utils.getId_C());
			//System.out.println("New element " + municipality.trim() + " has id_c = " + ceNew.getId_C());
			ceParent.getChildren().add(ceNew);
			ceNew.setParent(ceParent);
			ceList.add(ceNew);
			return ceNew;

		}
		//System.out.println("    Name = " + municipality);// we left the municipality section
		
		*/
		//return null;
		
		
	}
	
	public static void save(){
		
		System.out.println("Highest Id_C = " + (Utils.getId_C() -1));
		
		//printTopList();
		
		//if(1==1)
		//return;
		
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("context");				
		EntityManager em = factory.createEntityManager();	

		em.getTransaction().begin();
		
		for(ContextElement ce1: topList){		
			if(ce1.getId_C() > Utils.getOld_id_C()){
				ce1.save(em);		
				link(ce1, top, em);
			}
			for(ContextElement ce2: ce1.getChildren()){			
				if(ce2.getId_C() > Utils.getOld_id_C()){
					ce2.save(em);
					link(ce2, ce1, em);

				}
				for(ContextElement ce3: ce2.getChildren()){			
					if(ce3.getId_C() > Utils.getOld_id_C()){
						ce3.save(em);
						link(ce3, ce2, em);
					}
					for(ContextElement ce4: ce3.getChildren()){			
						if(ce4.getId_C() > Utils.getOld_id_C()){
							ce4.save(em);
							link(ce4, ce3, em);
						}
						for(ContextElement ce5: ce4.getChildren()){			
							if(ce5.getId_C() > Utils.getOld_id_C()){
								ce5.save(em);
								link(ce5, ce4, em);

							}
							for(ContextElement ce6: ce5.getChildren()){			
								if(ce6.getId_C() > Utils.getOld_id_C()){
									ce6.save(em);
									link(ce6, ce5, em);

								}
								for(ContextElement ce7: ce6.getChildren()){			
									if(ce7.getId_C() > Utils.getOld_id_C()){
										ce7.save(em);
										link(ce7, ce6, em);
									}
								}
							}
						}
					}
				}
			}
		}

		em.getTransaction().commit();
		em.clear();
		
	}

	
	public static void link(ContextElement ce1, ContextElement ce2, EntityManager em){
		
		CONTEXT_CONTEXT cc = new CONTEXT_CONTEXT();
		cc.setId_C_1(ce1.getId_C());
		cc.setId_C_2(ce2.getId_C());
		cc.setSource("HSN");
		
		String level1 = null;
		
		for(int i = 0; i < ce1.getTypes().size(); i++){
			//System.out.println(ce1.getTypes().get(i) + " = " + ce1.getValues().get(i));
			if(ce1.getTypes().get(i).equalsIgnoreCase("LEVEL")){
				level1 = ce1.getValues().get(i);
				break;
			}
		}
		
		String level2 = null;
		
		for(int i = 0; i < ce2.getTypes().size(); i++){
			if(ce2.getTypes().get(i).equalsIgnoreCase("LEVEL")){
				level2 = ce2.getValues().get(i);
				break;
			}
		}
		
		//System.out.println("level1 = " + level1);
		
		cc.setRelation(level1 + " and " + level2);
		
		
		em.persist(cc);
		
	}

	public static ContextElement locateCertificate(String source, int yearCertificate, int sequenceNumberCertificate,  ContextElement ce1, String   level){

		for(ContextElement ce: ce1.getChildren()){
			
			String source1 = null;
			int yearCertificate1 = 0;
			int sequenceNumberCertificate1 = 0;
			String level1 = null;
			
			for(int i = 0; i < ce.types.size(); i++){
				if(ce.types.get(i).compareTo("LEVEL") == 0)
					level1 = ce.values.get(i);
				if(ce.types.get(i).compareTo("NAME") == 0)
					source1 = ce.values.get(i);
				if(ce.types.get(i).compareTo("PERIOD") == 0)
					yearCertificate1 = new Integer(ce.values.get(i));
				if(ce.types.get(i).compareTo("SEQUENCE_NUMBER") == 0)
					sequenceNumberCertificate1 = new Integer(ce.values.get(i));
				
			}

			if(comp(level1, "Source") == true && comp(source1.substring(0,2), source.substring(0, 2)) == true && yearCertificate1 == yearCertificate && sequenceNumberCertificate1 == sequenceNumberCertificate)
				return ce;
		}
			
		ContextElement ce = new ContextElement();
		ce.types.add("LEVEL");
		ce.values.add(level);
		ce.types.add("NAME");
		ce.values.add(source);
		ce.types.add("PERIOD");
		ce.values.add("" + yearCertificate);
		ce.types.add("SEQUENCE_NUMBER");
		ce.values.add("" + sequenceNumberCertificate);
		
		ce.setId_C(Utils.getId_C());
		ce.setParent(ce1);
		
		ce1.getChildren().add(ce);

		return ce;

		
	}

	public static ContextElement locateBoat(String boat, String street, String number, String addition, ContextElement ce1, String   level){
		
		
		// locate address
		
		ContextElement ce = locateStreet(street, number, addition, ce1, level);
		if(ce != null){
			ce.types.add("BOAT");
			ce.values.add(boat);
			return ce;
		}
			
		return null;
	}

	public static ContextElement locateStreet(String street, String number, String addition, ContextElement ce1, String   level){
		
		
		if(street == null || street.trim().length() == 0)
			return null;
		
		//System.out.print("street: " + street + " number: " + number + " addition: " + addition + "level = " + level);
		
		// Try to find address
		
		for(ContextElement ce: ce1.getChildren()){
			
			String street1 = null;
			String number1 = null;
			String addition1 = null;
			String level1 = null;
			
			for(int i = 0; i < ce.types.size(); i++){
				if(ce.types.get(i).compareTo("LEVEL") == 0)
					level1 = ce.values.get(i);
				if(ce.types.get(i).compareTo("STREET") == 0)
					street1 = ce.values.get(i);
				if(ce.types.get(i).compareTo("HOUSE_NUMBER") == 0)
					number1 = ce.values.get(i);
				if(ce.types.get(i).compareTo("HOUSE_NUMBER_EXTENSION") == 0)
					addition1 = ce.values.get(i);
				
			}

			if(comp(level1, "Address") == true && comp(street1, street) == true && comp(number1, number) == true && comp(addition1, addition) == true)
			{
				//System.out.println(" Found");
				return ce;
			}

			
		}
		
		//System.out.println(" Not found");
		
		ContextElement ce = new ContextElement();
		ce.types.add("LEVEL");
		ce.values.add(level);
		ce.types.add("STREET");
		ce.values.add(street);
		if(number != null && number.length() > 0){
			ce.types.add("HOUSE_NUMBER");
			ce.values.add(number);
		}
		if(addition != null && addition.length() > 0){
			ce.types.add("HOUSE_NUMBER_EXTENSION");
			ce.values.add(addition);
		}
		
		ce.setId_C(Utils.getId_C());
		ce.setParent(ce1);
		
		ce1.getChildren().add(ce);

		return ce;

			
	}
	
	public static ContextElement locateQuarter(String quarter, ContextElement ce1, String   level){
		
		
		if(quarter == null || quarter.trim().length() == 0)
			return null;
		
		//System.out.println("quarter: " + quarter  + "level = " + level);
		
		// Try to find quarter
		
		for(ContextElement ce: ce1.getChildren()){
			
			String quarter1 = null;
			//String number1 = null;
			//String addition1 = null;
			String level1 = null;
			
			for(int i = 0; i < ce.types.size(); i++){
				if(ce.types.get(i).compareTo("LEVEL") == 0)
					level1 = ce.values.get(i);
				if(ce.types.get(i).compareTo("QUARTER") == 0)
					quarter1 = ce.values.get(i);
				//if(ce.types.get(i).compareTo("HOUSE_NUMBER") == 0)
					//number1 = ce.values.get(i);
				//if(ce.types.get(i).compareTo("HOUSE_NUMBER_EXTENSION") == 0)
					//addition1 = ce.values.get(i);
				
			}

			if(comp(level1, "Quarter") == true && comp(quarter1, quarter) == true)
				return ce;

			
		}
		
		//System.out.println("Not found");
		
		ContextElement ce = new ContextElement();
		ce.types.add("LEVEL");
		ce.values.add(level);
		ce.types.add("QUARTER");
		ce.values.add(quarter);
		//if(number != null && number.length() > 0){
		//	ce.types.add("HOUSE_NUMBER");
		//	ce.values.add(number);
		//}
		//if(addition != null && addition.length() > 0){
		//	ce.types.add("HOUSE_NUMBER_EXTENSION");
		//	ce.values.add(addition);
		//}
		
		ce.setId_C(Utils.getId_C());
		ce.setParent(ce1);
		
		ce1.getChildren().add(ce);

		return ce;

			
	}
	
	
	public static ContextElement locate(String s, ContextElement ce1, String   level){
				
		//System.out.println("Locate " + s   + " in " + level);
		//System.out.println(s.length() +  "  " + level.length());

		for(ContextElement ce: ce1.getChildren()){
			
			
			String name = null;
			String lvl = null;
			
			for(int i = 0; i < ce.types.size(); i++){
				if(ce.types.get(i).compareTo("NAME") == 0)
					name = ce.values.get(i);
				if(ce.types.get(i).compareTo("LEVEL") == 0)
					lvl = ce.values.get(i);
			}

			//System.out.println("name = " + name + "level = " + level);

			if(name != null && lvl != null && name.equalsIgnoreCase(s) && level.equalsIgnoreCase(lvl)){
				//System.out.println("Found, level = " + level + " name = " + s);

				return ce;
			}


		}
		
		//System.out.println("Not found");
		//if(1/0 > 0); 

		
		//System.out.println("Not Found, level = " + level + " name = " + s);

		ContextElement ce = new ContextElement();
		ce.types.add("NAME");
		ce.values.add(s);
		ce.types.add("LEVEL");
		ce.values.add(level);
		ce.setId_C(Utils.getId_C());
		ce.setParent(ce1);
		
		ce1.getChildren().add(ce);

		return ce;



	}

	private static char randChar(){

		Double a = Math.random();
		String s = a.toString();
		char c = s.charAt(3);

		return c;



	}

	private static boolean comp (String s1, String s2){
		
		//System.out.println("s1 = " + s1 + " s2 = " + s2);
		
		if(s1 == null && s2 == null)
			return true;
		if(s1 != null && s2 != null && s1.trim().equalsIgnoreCase(s2.trim()) == true)
			return true;
		return false;
	}

	private static String normalize(String s){
		
		if(s == null) return s; // null is normalized
		else{
			s = s.trim();
			if(s.length() == 0)
				return null;
			else 
				return s;
		}
	}

	/*
	
	public static int addCertificate(String Country, String Province, String Municipality, String source, int yearCertificate, int sequenceNumberCertificate){
		
		if(Country != null){
			ContextElement contextCountry = locate(Country, top, "Country");
			if(Province != null){
				ContextElement contextProvince = locate(Province, contextCountry, "Province");
				if(Municipality != null){
					ContextElement contextMunicipality = locate(Municipality, contextProvince, "Municipality");
					ContextElement contextCertificate = locateCertificate(source, yearCertificate, sequenceNumberCertificate, contextMunicipality, "Source");
					return contextCertificate.getId_C();
				}
			}
		}
		
		return 0;
	}
	
	*/
	
	public static int add(String Country, String Province, String Municipality, String Locality, String Quarter, String Street, String Number, String Addition, String  Name){
		
		
		
		Country      = normalize(Country);     
		Province     = normalize(Province);     
		Municipality = normalize(Municipality);     
		Locality     = normalize(Locality);     
		Quarter      = normalize(Quarter);     
		Street       = normalize(Street);     
		Number       = normalize(Number);     
		Addition     = normalize(Addition);     
		Name         = normalize(Name);    

		//System.out.println("Country = " + Country + " Province = " + Province + " Municipality = " +  Municipality + " Locality = " + Locality + " Street " + Street);

		
		
		if(Country != null){
			ContextElement contextCountry = locate(Country, top, "Country");
			if(Province != null){
				ContextElement contextProvince = locate(Province, contextCountry, "Province");
				if(Municipality != null){
					ContextElement contextMunicipality = locate(Municipality, contextProvince, "Municipality");
					if(Locality != null){
						ContextElement contextLocality = locate(Locality, contextMunicipality, "Locality");
						if(Quarter != null){
							ContextElement contextQuarter = locateQuarter(Quarter, contextLocality, "Quarter");
							if(Street != null){
								ContextElement contextAddress = locateStreet(Street, Number, Addition,  contextQuarter, "Address");
								if(Name != null){
									ContextElement contextName = locate(Name, contextAddress, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextAddress.getId_C();
								}
							}
							else{ // No Street
								if(Name != null){
									ContextElement contextName = locate(Name, contextQuarter, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextQuarter.getId_C();
								}
							}
						}
						else{ //No quarter
							if(Street != null){
								ContextElement contextAddress = locateStreet(Street, Number, Addition, contextLocality, "Address");
								if(Name != null){
									ContextElement contextName = locate(Name, contextAddress, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextAddress.getId_C();
								}
							}
							else{ // No Street
								if(Name != null){
									ContextElement contextName = locate(Name, contextLocality, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextLocality.getId_C();
								}
							}
						}
					}  
					else{ // No locality
						if(Quarter != null){
							ContextElement contextQuarter = locateQuarter(Quarter, contextMunicipality, "Quarter");
							if(Street != null){
								ContextElement contextAddress = locateStreet(Street, Number, Addition, contextQuarter, "Address");
								if(Name != null){
									ContextElement contextName = locate(Name, contextAddress, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextAddress.getId_C();
								}
							}
							else{ // No Street
								if(Name != null){
									ContextElement contextName = locate(Name, contextQuarter, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextQuarter.getId_C();
								}
							}
						}
						else{ //No quarter
							if(Street != null){
								ContextElement contextAddress = locateStreet(Street, Number, Addition, contextMunicipality, "Address");
								if(Name != null){
									ContextElement contextName = locate(Name, contextAddress, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextAddress.getId_C();
								}
							}
							else{ // No Street
								if(Name != null){
									ContextElement contextName = locate(Name, contextMunicipality, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextMunicipality.getId_C();
								}
							}
						}
					}
				}		
				else{ // No Municipality
					if(Locality != null){
						ContextElement contextLocality = locate(Locality, contextProvince, "Locality");
						if(Quarter != null){
							ContextElement contextQuarter = locateQuarter(Quarter, contextLocality, "Quarter");
							if(Street != null){
								ContextElement contextAddress = locateStreet(Street, Number, Addition, contextQuarter, "Address");
								if(Name != null){
									ContextElement contextName = locate(Name, contextAddress, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextAddress.getId_C();
								}
							}
							else{ // No Street
								if(Name != null){
									ContextElement contextName = locate(Name, contextQuarter, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextQuarter.getId_C();
								}
							}
						}
						else{ //No quarter
							if(Street != null){
								ContextElement contextAddress = locateStreet(Street, Number, Addition, contextLocality, "Address");
								if(Name != null){
									ContextElement contextName = locate(Name, contextAddress, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextAddress.getId_C();
								}
							}
							else{ // No Street
								if(Name != null){
									ContextElement contextName = locate(Name, contextLocality, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextLocality.getId_C();
								}
							}
						}
					}  
					else{ // No locality
						if(Quarter != null){
							ContextElement contextQuarter = locateQuarter(Quarter, contextProvince, "Quarter");
							if(Street != null){
								ContextElement contextAddress = locateStreet(Street, Number, Addition, contextQuarter, "Address");
								if(Name != null){
									ContextElement contextName = locate(Name, contextAddress, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextAddress.getId_C();
								}
							}
							else{ // No Street
								if(Name != null){
									ContextElement contextName = locate(Name, contextQuarter, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextQuarter.getId_C();
								}
							}
						}
						else{ //No quarter
							if(Street != null){
								ContextElement contextAddress = locateStreet(Street, Number, Addition, contextProvince, "Address");
								if(Name != null){
									ContextElement contextName = locate(Name, contextAddress, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextAddress.getId_C();
								}
							}
							else{ // No Street
								if(Name != null){
									ContextElement contextName = locate(Name, contextProvince, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextProvince.getId_C();
								}
							}
						}
					}
				}
			}
			else{ // No Province
				if(Municipality != null){
					ContextElement contextMunicipality = locate(Municipality, contextCountry, "Municipality");
					if(Locality != null){
						ContextElement contextLocality = locate(Locality, contextMunicipality, "Locality");
						if(Quarter != null){
							ContextElement contextQuarter = locateQuarter(Quarter, contextLocality, "Quarter");
							if(Street != null){
								ContextElement contextAddress = locateStreet(Street, Number, Addition, contextQuarter, "Address");
								if(Name != null){
									ContextElement contextName = locate(Name, contextAddress, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextAddress.getId_C();
								}
							}
							else{ // No Street
								if(Name != null){
									ContextElement contextName = locate(Name, contextQuarter, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextQuarter.getId_C();
								}
							}
						}
						else{ //No quarter
							if(Street != null){
								ContextElement contextAddress = locateStreet(Street, Number, Addition, contextLocality, "Address");
								if(Name != null){
									ContextElement contextName = locate(Name, contextAddress, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextAddress.getId_C();
								}
							}
							else{ // No Street
								if(Name != null){
									ContextElement contextName = locate(Name, contextLocality, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextLocality.getId_C();
								}
							}
						}
					}  
					else{ // No locality
						if(Quarter != null){
							ContextElement contextQuarter = locateQuarter(Quarter, contextMunicipality, "Quarter");
							if(Street != null){
								ContextElement contextAddress = locateStreet(Street, Number, Addition, contextQuarter, "Address");
								if(Name != null){
									ContextElement contextName = locate(Name, contextAddress, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextAddress.getId_C();
								}
							}
							else{ // No Street
								if(Name != null){
									ContextElement contextName = locate(Name, contextQuarter, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextQuarter.getId_C();
								}
							}
						}
						else{ //No quarter
							if(Street != null){
								ContextElement contextAddress = locateStreet(Street, Number, Addition, contextMunicipality, "Address");
								if(Name != null){
									ContextElement contextName = locate(Name, contextAddress, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextAddress.getId_C();
								}
							}
							else{ // No Street
								if(Name != null){
									ContextElement contextName = locate(Name, contextMunicipality, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextMunicipality.getId_C();
								}
							}
						}
					}
				}		
				else{ // No Municipality
					if(Locality != null){
						ContextElement contextLocality = locate(Locality, contextCountry, "Locality");
						if(Quarter != null){
							ContextElement contextQuarter = locateQuarter(Quarter, contextLocality, "Quarter");
							if(Street != null){
								ContextElement contextAddress = locateStreet(Street, Number, Addition, contextQuarter, "Address");
								if(Name != null){
									ContextElement contextName = locate(Name, contextAddress, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextAddress.getId_C();
								}
							}
							else{ // No Street
								if(Name != null){
									ContextElement contextName = locate(Name, contextQuarter, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextQuarter.getId_C();
								}
							}
						}
						else{ //No quarter
							if(Street != null){
								ContextElement contextAddress = locateStreet(Street, Number, Addition, contextLocality, "Address");
								if(Name != null){
									ContextElement contextName = locate(Name, contextAddress, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextAddress.getId_C();
								}
							}
							else{ // No Street
								if(Name != null){
									ContextElement contextName = locate(Name, contextLocality, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextLocality.getId_C();
								}
							}
						}
					}  
					else{ // No locality
						if(Quarter != null){
							ContextElement contextQuarter = locateQuarter(Quarter, contextCountry, "Quarter");
							if(Street != null){
								ContextElement contextAddress = locateStreet(Street, Number, Addition, contextQuarter, "Address");
								if(Name != null){
									ContextElement contextName = locate(Name, contextAddress, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextAddress.getId_C();
								}
							}
							else{ // No Street
								if(Name != null){
									ContextElement contextName = locate(Name, contextQuarter, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextQuarter.getId_C();
								}
							}
						}
						else{ //No quarter
							if(Street != null){
								ContextElement contextAddress = locateStreet(Street, Number, Addition, contextCountry, "Address");
								if(Name != null){
									ContextElement contextName = locate(Name, contextAddress, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextAddress.getId_C();
								}
							}
							else{ // No Street
								if(Name != null){
									ContextElement contextName = locate(Name, contextCountry, "Name");
									return contextName.getId_C();
								}
								else{ // No Name
									return contextCountry.getId_C();
								}
							}
						}
					}
				}
			}
		}
		else{ // No Country
			
		}
		
		
		return 0;
		
	}








}