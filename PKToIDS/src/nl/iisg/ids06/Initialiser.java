package nl.iisg.ids06;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Initialiser {
	
	public static void loadTables(ArrayList<ArrayList> all){
		
		EntityManagerFactory              factory = Persistence.createEntityManagerFactory("hsn_perscd_std");
		EntityManager                     em      = factory.createEntityManager(); 

		String [] tables = {"B2_ST", "B313_ST", "B32_ST", "B33_ST", "B34_ST", "B35_ST", "B36_ST", "B37_ST", "B4_ST", "B6_ST"}; 
		
		System.out.println("Start loading B*_ST tables");
		
		for(int i = 0; i < all.size(); i++){
			System.out.println("Reading " + tables[i]);
			Query q = em.createQuery("select a from " + tables[i] + " a");
			System.out.println("Read    " + tables[i] + " " + q.getResultList().size() + " rows");			
			all.get(i).addAll(q.getResultList());
		}
		
		System.out.println("Finished loading B*_ST tables");
		
	}	

	public static List<OP> linkObjects(ArrayList<ArrayList> all){
		
		// Link Objects:
		//  OP <-> Registration
		//  Registration <-> Person
		//  Registration <-> RegistrationAddress
		//  Person <-> PersonDynamic       

		System.out.println("Start linking objects");
		
		ArrayList<B2_ST> persons = all.get(0);
		
		Collections.sort(persons, new Comparator<B2_ST>()
				{public int compare(B2_ST p1, B2_ST p2){

					int headYear1  = (new Integer(p1.getEntryDateHead().substring(6)).intValue());
					int headMonth1 = (new Integer(p1.getEntryDateHead().substring(3,5)).intValue());
					int headDay1   = (new Integer(p1.getEntryDateHead().substring(0,2)).intValue());

					int headYear2  = (new Integer(p2.getEntryDateHead().substring(6)).intValue());
					int headMonth2 = (new Integer(p2.getEntryDateHead().substring(3,5)).intValue());
					int headDay2   = (new Integer(p2.getEntryDateHead().substring(0,2)).intValue());

					if     (p1.getKeyToRP() > p2.getKeyToRP()) return 1;
					else if(p1.getKeyToRP() < p2.getKeyToRP()) return -1;

					if     (headYear1 > headYear2) return  1;
					else if(headYear1 < headYear2) return -1;

					if     (headMonth1 > headMonth2) return  1;
					else if(headMonth1 < headMonth2) return -1;

					if     (headDay1 > headDay2) return  1;
					else if(headDay1 < headDay2) return -1;

					if     (p1.getKeyToSourceRegister() > p2.getKeyToSourceRegister()) return  1;
					else if(p1.getKeyToSourceRegister() < p2.getKeyToSourceRegister()) return -1;

					if     (p1.getKeyToPersons() > p2.getKeyToPersons()) return 1;
					else if(p1.getKeyToPersons() < p2.getKeyToPersons()) return -1;
					return 0;}});	
		
		

		Comparator<B3_ST> c = new Comparator<B3_ST>()
		{public int compare(B3_ST p1, B3_ST p2){

			int headYear1  = (new Integer(p1.getEntryDateHead().substring(6)).intValue());
			int headMonth1 = (new Integer(p1.getEntryDateHead().substring(3,5)).intValue());
			int headDay1   = (new Integer(p1.getEntryDateHead().substring(0,2)).intValue());

			int headYear2  = (new Integer(p2.getEntryDateHead().substring(6)).intValue());
			int headMonth2 = (new Integer(p2.getEntryDateHead().substring(3,5)).intValue());
			int headDay2   = (new Integer(p2.getEntryDateHead().substring(0,2)).intValue());

			if     (p1.getKeyToRP() > p2.getKeyToRP()) return 1;
			else if(p1.getKeyToRP() < p2.getKeyToRP()) return -1;

			if     (headYear1 > headYear2) return  1;
			else if(headYear1 < headYear2) return -1;

			if     (headMonth1 > headMonth2) return  1;
			else if(headMonth1 < headMonth2) return -1;

			if     (headDay1 > headDay2) return  1;
			else if(headDay1 < headDay2) return -1;

			if     (p1.getKeyToSourceRegister() > p2.getKeyToSourceRegister()) return  1;
			else if(p1.getKeyToSourceRegister() < p2.getKeyToSourceRegister()) return -1;

			if     (p1.getKeyToRegistrationPersons() > p2.getKeyToRegistrationPersons()) return 1;
			else if(p1.getKeyToRegistrationPersons() < p2.getKeyToRegistrationPersons()) return -1;

			if     (p1.getDynamicDataSequenceNumber() > p2.getDynamicDataSequenceNumber()) return  1;
			else if(p1.getDynamicDataSequenceNumber() < p2.getDynamicDataSequenceNumber()) return -1;


			return 0;}};	
		
			ArrayList<B313_ST> relationToPKHolder = all.get(1);
			Collections.sort(relationToPKHolder, c);
				
			ArrayList<B32_ST> civilStatus = all.get(2);
			Collections.sort(civilStatus, c);
				
			ArrayList<B33_ST> religion = all.get(3);
			Collections.sort(religion, c);
				
			ArrayList<B34_ST> relations = all.get(4);
			Collections.sort(relations, c);
				
			ArrayList<B35_ST> profession = all.get(5);
			Collections.sort(profession, c);
				
			ArrayList<B36_ST> origin = all.get(6);
			Collections.sort(origin, c);
				
			ArrayList<B37_ST> destination = all.get(7);
			Collections.sort(destination, c);
			
			ArrayList<B4_ST> registrations = all.get(8);
			Collections.sort(registrations, new Comparator<B4_ST>()
					{public int compare(B4_ST r1, B4_ST r2){

						int headYear1  = (new Integer(r1.getEntryDateHead().substring(6)).intValue());
						int headMonth1 = (new Integer(r1.getEntryDateHead().substring(3,5)).intValue());
						int headDay1   = (new Integer(r1.getEntryDateHead().substring(0,2)).intValue());

						int headYear2  = (new Integer(r2.getEntryDateHead().substring(6)).intValue());
						int headMonth2 = (new Integer(r2.getEntryDateHead().substring(3,5)).intValue());
						int headDay2   = (new Integer(r2.getEntryDateHead().substring(0,2)).intValue());

						if     (r1.getKeyToRP() > r2.getKeyToRP()) return 1;
						else if(r1.getKeyToRP() < r2.getKeyToRP()) return -1;

						if     (headYear1 > headYear2) return  1;
						else if(headYear1 < headYear2) return -1;

						if     (headMonth1 > headMonth2) return  1;
						else if(headMonth1 < headMonth2) return -1;

						if     (headDay1 > headDay2) return  1;
						else if(headDay1 < headDay2) return -1;

						if     (r1.getKeyToSourceRegister() > r2.getKeyToSourceRegister()) return  1;
						else if(r1.getKeyToSourceRegister() < r2.getKeyToSourceRegister()) return -1;

						return 0;}});	
			
			ArrayList<B6_ST> addresses = all.get(9);
			
			Collections.sort(addresses, new Comparator<B6_ST>()
					{public int compare(B6_ST ra1, B6_ST ra2){

						int headYear1  = (new Integer(ra1.getDateEntryHeadOfHousehold().substring(6)).intValue());
						int headMonth1 = (new Integer(ra1.getDateEntryHeadOfHousehold().substring(3,5)).intValue());
						int headDay1   = (new Integer(ra1.getDateEntryHeadOfHousehold().substring(0,2)).intValue());

						int headYear2  = (new Integer(ra2.getDateEntryHeadOfHousehold().substring(6)).intValue());
						int headMonth2 = (new Integer(ra2.getDateEntryHeadOfHousehold().substring(3,5)).intValue());
						int headDay2   = (new Integer(ra2.getDateEntryHeadOfHousehold().substring(0,2)).intValue());

						if     (ra1.getKeyToRP() > ra2.getKeyToRP()) return 1;
						else if(ra1.getKeyToRP() < ra2.getKeyToRP()) return -1;

						if     (headYear1 > headYear2) return  1;
						else if(headYear1 < headYear2) return -1;

						if     (headMonth1 > headMonth2) return  1;
						else if(headMonth1 < headMonth2) return -1;

						if     (headDay1 > headDay2) return  1;
						else if(headDay1 < headDay2) return -1;

						if     (ra1.getKeyToSourceRegister() > ra2.getKeyToSourceRegister()) return  1;
						else if(ra1.getKeyToSourceRegister() < ra2.getKeyToSourceRegister()) return -1;
						
   					    if     (ra1.getSequenceNumberToAddresses() > ra2.getSequenceNumberToAddresses()) return  1;
					    else if(ra1.getSequenceNumberToAddresses() < ra2.getSequenceNumberToAddresses()) return -1;


						return 0;}});
			
			
			// Start linking
			
			int i_p = 0;
			int i_ra = 0;
			
			int i_pd_relationToPKHolder = 0;
			int i_pd_civilStatus = 0;
			int i_pd_religion = 0;
			int i_pd_relations = 0;
			int i_pd_profession = 0;
			int i_pd_origin = 0;
			int i_pd_destination = 0;

			int i_r = 0;
			int i_o = 1;
			
			// create OP list from registrations
			
			ArrayList<OP> ops  = new ArrayList<OP>();
			
			
			for(B4_ST r: registrations){
				boolean found = false;
				
				for(OP op: ops){
					if(r.getKeyToRP() == op.getKeyToRP()){
						found = true;
						op.getRegistrationsStandardizedOfOP().add(r);
						r.setOp(op);
						break;
					}
				}
				
				if(found == false){
					OP op1 = new OP(r.getKeyToRP());				
					ops.add(op1);
					op1.getRegistrationsStandardizedOfOP().add(r);
					r.setOp(op1); 
					
				}
			}
			
			System.out.println("Set Object Links");

			
			for(OP op: ops){
				for(B4_ST r: op.getRegistrationsStandardizedOfOP()){
					
					//System.out.println(r.getKeyToRP());
					
					while(i_ra < addresses.size() && r.contains(addresses.get(i_ra))){	
						
						addresses.get(i_ra).setRegistration(r);
						r.getAddresses().add(addresses.get(i_ra));
						i_ra++;
					}
					
					while(i_p < persons.size() && r.contains(persons.get(i_p))){  
						
						persons.get(i_p).setRegistration(r);	
						r.getPersons().add(persons.get(i_p));

						while(i_pd_relationToPKHolder < relationToPKHolder.size() && persons.get(i_p).contains(relationToPKHolder.get(i_pd_relationToPKHolder))){
							relationToPKHolder.get(i_pd_relationToPKHolder).setPerson(persons.get(i_p));
							persons.get(i_p).getRelationsToPKHolder().add(relationToPKHolder.get(i_pd_relationToPKHolder));
							i_pd_relationToPKHolder++;
						}
						
						
						while(i_pd_civilStatus < civilStatus.size() && persons.get(i_p).contains(civilStatus.get(i_pd_civilStatus))){
							civilStatus.get(i_pd_civilStatus).setPerson(persons.get(i_p));
							persons.get(i_p).getCivilStatus().add(civilStatus.get(i_pd_civilStatus));
							i_pd_civilStatus++;
						}
						
						
						while(i_pd_religion < religion.size() && persons.get(i_p).contains(religion.get(i_pd_religion))){
							religion.get(i_pd_religion).setPerson(persons.get(i_p));
							persons.get(i_p).getReligions().add(religion.get(i_pd_religion));
							i_pd_religion++;
						}
						
						while(i_pd_relations < relations.size() && persons.get(i_p).contains(relations.get(i_pd_relations))){
							relations.get(i_pd_relations).setPerson(persons.get(i_p));
							persons.get(i_p).getRelations().add(relations.get(i_pd_relations));
							i_pd_relations++;
						}
						
						while(i_pd_profession < profession.size() && persons.get(i_p).contains(profession.get(i_pd_profession))){
							profession.get(i_pd_profession).setPerson(persons.get(i_p));
							persons.get(i_p).getProfessions().add(profession.get(i_pd_profession));
							i_pd_profession++;
						}
						
						
						while(i_pd_origin < origin.size() && persons.get(i_p).contains(origin.get(i_pd_origin))){
							origin.get(i_pd_origin).setPerson(persons.get(i_p));
							persons.get(i_p).getOrigins().add(origin.get(i_pd_origin));
							i_pd_origin++;
						}
						
						
						while(i_pd_destination < destination.size() && persons.get(i_p).contains(destination.get(i_pd_destination))){
							destination.get(i_pd_destination).setPerson(persons.get(i_p));
							persons.get(i_p).getDestinations().add(destination.get(i_pd_destination));
							i_pd_destination++;
						}

						i_p++;
					}

					i_r++;
				}
			}
			
			
		
			
			System.out.println("Finished linking objects");
			return ops;		
	}
	
	public static void createIDSTables(){
		
		EntityManagerFactory              factory = Persistence.createEntityManagerFactory("hsn_perscd_ids");
		EntityManager                     em      = factory.createEntityManager(); 
		
		System.out.println("Start creating IDS-tables");

		try{
			
			em.getTransaction().begin();
			
			Query query = em.createNativeQuery(CreateIDSTables.INDIVIDUAL);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateIDSTables.INDIV_INDIV);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateIDSTables.INDIV_CONTEXT);  
			query.executeUpdate();  
			//query = em.createNativeQuery(CreateIDSTables.CONTEXT);  
			//query.executeUpdate();  
			//query = em.createNativeQuery(CreateIDSTables.CONTEXT_CONTEXT);  
			//query.executeUpdate();  
			
			em.getTransaction().commit();
			em.clear();
			
			em.getTransaction().begin();
			
			query = em.createNativeQuery(CreateIDSTables.INDIVIDUAL_TRUNCATE);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateIDSTables.INDIV_INDIV_TRUNCATE);  
			query.executeUpdate();  
			query = em.createNativeQuery(CreateIDSTables.INDIV_CONTEXT_TRUNCATE);  
			query.executeUpdate();  
			
			em.getTransaction().commit();
			em.clear();

			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Finished creating IDS-tables");
	}

	
}
