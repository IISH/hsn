package nl.iisg.ids05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Initialiser {
	
	public void loadTables(ArrayList<ArrayList> all, int j){
		
		EntityManagerFactory              factory = Persistence.createEntityManagerFactory("hsnnieuw");
		EntityManager                     em      = factory.createEntityManager(); 

		String [] tables = {"A1", "B0", "B1", "B2", "B3", "B4", "B5", "B6", "M1", "M2", "M3", "M4", "M5", "M6", "D1", "D2", "D3", "D4"};
		
		
		for(int i = 0; i < tables.length; i++){
			System.out.println("Loading " + tables[i]);
			//Query q = em.createQuery("select a from " + tables[i] + " a where a.idnr like %");
			//Query q = em.createQuery("select a from " + tables[i] + " a");
			//Query q = em.createQuery("select a from " + tables[i] + " a where a.idnr like '%" + j +"'");
			Query q = em.createQuery("select a from " + tables[i] + " a where a.idnr like '%0" + j +"'");

			all.get(i).clear();
			all.get(i).addAll(q.getResultList());
		}
		em.close();
		factory.close();
		
		em = null;
		factory = null;

	}	
	
	public static void createIDSTables(){
		
		EntityManagerFactory              factory = Persistence.createEntityManagerFactory("hsn_civrec_ids");
		EntityManager                     em      = factory.createEntityManager(); 
		
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

	}

	public void linkObjects(ArrayList<ArrayList> all){
		
		// Sort A1 table  

		ArrayList<A1> a1List = all.get(0);
				
		Collections.sort(a1List, new Comparator<A1>()
				{public int compare(A1 a11, A1 a12){
					if(a11.getIdnr() < a12.getIdnr())
						return -1;
					if(a11.getIdnr() > a12.getIdnr())
						return  1;
					if(a11.getRole() < a12.getRole())
						return -1;
					if(a11.getRole() > a12.getRole())
						return  1;
					return 0;}});
		
		// Sort B* tables  

		ArrayList<B0> b0List = all.get(1);
		
		Collections.sort(b0List, new Comparator<B0>()
				{public int compare(B0 b01, B0 b02){
					if(b01.getIdnr() < b02.getIdnr())
						return -1;
					if(b01.getIdnr() > b02.getIdnr())
						return 1;
					return 0;}});
		
		ArrayList<B1> b1List = all.get(2);
		
		Collections.sort(b1List, new Comparator<B1>()
				{public int compare(B1 b11, B1 b12){
					if(b11.getIdnr() < b12.getIdnr())
						return -1;
					if(b11.getIdnr() > b12.getIdnr())
						return 1;
					return 0;}});	
		
		ArrayList<B2> b2List = all.get(3);
		
		Collections.sort(b2List, new Comparator<B2>()
				{public int compare(B2 b21, B2 b22){
					if(b21.getIdnr() < b22.getIdnr())
						return -1;
					if(b21.getIdnr() > b22.getIdnr())
						return 1;
					if(b21.getB2w_sq() < b22.getB2w_sq())
						return -1;
					if(b21.getB2w_sq() > b22.getB2w_sq())
						return 1;
					return 0;}});	
		
		ArrayList<B3> b3List = all.get(4);
		
		Collections.sort(b3List, new Comparator<B3>()
				{public int compare(B3 b31, B3 b32){
					if(b31.getIdnr() < b32.getIdnr())
						return -1;
					if(b31.getIdnr() > b32.getIdnr())
						return 1;
					return 0;}});	
		
		ArrayList<B4> b4List = all.get(5);
		
		Collections.sort(b4List, new Comparator<B4>()
				{public int compare(B4 b41, B4 b42){
					if(b41.getIdnr() < b42.getIdnr())
						return -1;
					if(b41.getIdnr() > b42.getIdnr())
						return 1;
					return 0;}});	
		
		ArrayList<B5> b5List = all.get(6);
		
		Collections.sort(b5List, new Comparator<B5>()
				{public int compare(B5 b51, B5 b52){
					if(b51.getIdnr() < b52.getIdnr())
						return -1;
					if(b51.getIdnr() > b52.getIdnr())
						return 1;
					return 0;}});	
		

		ArrayList<B6> b6List = all.get(7);
		
		Collections.sort(b6List, new Comparator<B6>()
				{public int compare(B6 b61, B6 b62){
					if(b61.getIdnr() < b62.getIdnr())
						return -1;
					if(b61.getIdnr() > b62.getIdnr())
						return 1;
					return 0;}});	
		


		// sort M* tables
		
		ArrayList<M1> m1List = all.get(8);
		
		Collections.sort(m1List, new Comparator<M1>()
				{public int compare(M1 m11, M1 m12){
					if(m11.getIdnr() < m12.getIdnr())
						return -1;
					if(m11.getIdnr() > m12.getIdnr())
						return 1;
					if(m11.getMar_cy() < m12.getMar_cy())
						return -1;
					if(m11.getMar_cy() > m12.getMar_cy())
						return 1;
					return 0;}});	
		

		ArrayList<M2> m2List = all.get(9);
		
		Collections.sort(m2List, new Comparator<M2>()
				{public int compare(M2 m21, M2 m22){
					if(m21.getIdnr() < m22.getIdnr())
						return -1;
					if(m21.getIdnr() > m22.getIdnr())
						return 1;
					if(m21.getMar_cy() < m22.getMar_cy())
						return -1;
					if(m21.getMar_cy() > m22.getMar_cy())
						return 1;
					return 0;}});	
		
		ArrayList<M3> m3List = all.get(10);
		
		Collections.sort(m3List, new Comparator<M3>()
				{public int compare(M3 m31, M3 m32){
					if(m31.getIdnr() < m32.getIdnr())
						return -1;
					if(m31.getIdnr() > m32.getIdnr())
						return 1;
					if(m31.getMar_cy() < m32.getMar_cy())
						return -1;
					if(m31.getMar_cy() > m32.getMar_cy())
						return 1;
					return 0;}});	
		
		ArrayList<M4> m4List = all.get(11);
		
		Collections.sort(m4List, new Comparator<M4>()
				{public int compare(M4 m41, M4 m42){
					if(m41.getIdnr() < m42.getIdnr())
						return -1;
					if(m41.getIdnr() > m42.getIdnr())
						return 1;
					if(m41.getMar_cy() < m42.getMar_cy())
						return -1;
					if(m41.getMar_cy() > m42.getMar_cy())
						return 1;
					return 0;}});	
		
		ArrayList<M5> m5List = all.get(12);
		
		Collections.sort(m5List, new Comparator<M5>()
				{public int compare(M5 m51, M5 m52){
					if(m51.getIdnr() < m52.getIdnr())
						return -1;
					if(m51.getIdnr() > m52.getIdnr())
						return 1;
					if(m51.getMar_cy() < m52.getMar_cy())
						return -1;
					if(m51.getMar_cy() > m52.getMar_cy())
						return 1;
					return 0;}});	
		
		ArrayList<M6> m6List = all.get(13);
		
		Collections.sort(m6List, new Comparator<M6>()
				{public int compare(M6 m61, M6 m62){
					if(m61.getIdnr() < m62.getIdnr())
						return -1;
					if(m61.getIdnr() > m62.getIdnr())
						return 1;
					if(m61.getMar_cy() < m62.getMar_cy())
						return -1;
					if(m61.getMar_cy() > m62.getMar_cy())
						return 1;
					return 0;}});	
		
		
		// Sort D* tables
		
		ArrayList<D1> d1List = all.get(14);
		
		Collections.sort(d1List, new Comparator<D1>()
				{public int compare(D1 d11, D1 d12){
					if(d11.getIdnr() < d12.getIdnr())
						return -1;
					if(d11.getIdnr() > d12.getIdnr())
						return 1;
					return 0;}});	
		
		ArrayList<D2> d2List = all.get(15);
		
		Collections.sort(d2List, new Comparator<D2>()
				{public int compare(D2 d21, D2 d22){
					if(d21.getIdnr() < d22.getIdnr())
						return -1;
					if(d21.getIdnr() > d22.getIdnr())
						return 1;
					return 0;}});	
		
		ArrayList<D3> d3List = all.get(16);
		
		Collections.sort(d3List, new Comparator<D3>()
				{public int compare(D3 d31, D3 d32){
					if(d31.getIdnr() < d32.getIdnr())
						return -1;
					if(d31.getIdnr() > d32.getIdnr())
						return 1;
					return 0;}});	
		
		ArrayList<D4> d4List = all.get(17);
		
		Collections.sort(d4List, new Comparator<D4>()
				{public int compare(D4 d41, D4 d42){
					if(d41.getIdnr() < d42.getIdnr())
						return -1;
					if(d41.getIdnr() > d42.getIdnr())
						return 1;
					return 0;}});	
		


		int i_a1 = 0;
		
		int i_b1 = 0;
		int i_b2 = 0;
		int i_b3 = 0;
		int i_b4 = 0;
		int i_b5 = 0;
		int i_b6 = 0;

		int i_m1 = 0;
		int i_m2 = 0;
		int i_m3 = 0;
		int i_m4 = 0;
		int i_m5 = 0;
		int i_m6 = 0;

		int i_d1 = 0;
		int i_d2 = 0;
		int i_d3 = 0;
		int i_d4 = 0;

		
		for(B0 b0: b0List){
			
			while(i_b1 < b1List.size() && b1List.get(i_b1).getIdnr() == b0.getIdnr()){				
				
				b0.getB1L().add(b1List.get(i_b1));
				b1List.get(i_b1).setB0(b0);
				
				while(i_b2 < b2List.size() && b2List.get(i_b2).getIdnr() == b0.getIdnr()){
					b1List.get(i_b1).getB2L().add(b2List.get(i_b2));
					b2List.get(i_b2).setB1(b1List.get(i_b1));
					i_b2++;
				}				
				
				while(i_b3 < b3List.size() && b3List.get(i_b3).getIdnr() == b0.getIdnr()){
					b1List.get(i_b1).setB3(b3List.get(i_b3));
					b3List.get(i_b3).setB1(b1List.get(i_b1));
					i_b3++;
				}				

				while(i_b4 < b4List.size() && b4List.get(i_b4).getIdnr() == b0.getIdnr()){
					b1List.get(i_b1).getB4L().add(b4List.get(i_b4));
					b4List.get(i_b4).setB1(b1List.get(i_b1));
					i_b4++;
				}				

				while(i_b5 < b5List.size() && b5List.get(i_b5).getIdnr() == b0.getIdnr()){
					b1List.get(i_b1).setB5(b5List.get(i_b5));
					b5List.get(i_b5).setB1(b1List.get(i_b1));
					i_b5++;
				}				
				
				while(i_m1 < m1List.size() && m1List.get(i_m1).getIdnr() == b0.getIdnr()){
					b1List.get(i_b1).getM1L().add(m1List.get(i_m1));
					m1List.get(i_m1).setB1(b1List.get(i_b1));
					
					while(i_m2 < m2List.size() && m2List.get(i_m2).getIdnr() == b0.getIdnr()){
						
						m1List.get(i_m1).getM2L().add(m2List.get(i_m2));
						m2List.get(i_m2).setM1(m1List.get(i_m1));
						i_m2++;						
					}
					
					while(i_m3 < m3List.size() && m3List.get(i_m3).getIdnr() == b0.getIdnr()){
						
						m1List.get(i_m1).getM3L().add(m3List.get(i_m3));
						m3List.get(i_m3).setM1(m1List.get(i_m1));
						i_m3++;						
					}
					
					while(i_m4 < m4List.size() && m4List.get(i_m4).getIdnr() == b0.getIdnr()){
						
						m1List.get(i_m1).getM4L().add(m4List.get(i_m4));
						m4List.get(i_m4).setM1(m1List.get(i_m1));
						i_m4++;						
					}
					
					while(i_m5 < m5List.size() && m5List.get(i_m5).getIdnr() == b0.getIdnr()){
						
						m1List.get(i_m1).getM5L().add(m5List.get(i_m5));
						m5List.get(i_m5).setM1(m1List.get(i_m1));
						i_m5++;						
					}
					
					while(i_m6 < m6List.size() && m6List.get(i_m6).getIdnr() == b0.getIdnr()){
						
						m1List.get(i_m1).setM6(m6List.get(i_m6));
						m6List.get(i_m6).setM1(m1List.get(i_m1));
						i_m6++;						
					}
					
					
					i_m1++;

				}
				
				while(i_d1 < d1List.size() && d1List.get(i_d1).getIdnr() == b0.getIdnr()){
					
					b1List.get(i_b1).setD1(d1List.get(i_d1));
					d1List.get(i_d1).setB1(b1List.get(i_b1));
					
					while(i_d2 < d2List.size() && d2List.get(i_d2).getIdnr() == b0.getIdnr()){
						
						d1List.get(i_d1).getD2L().add(d2List.get(i_d2));
						d2List.get(i_d2).setD1(d1List.get(i_d1));
						i_d2++;						
					}
					
					while(i_d3 < d3List.size() && d3List.get(i_d3).getIdnr() == b0.getIdnr()){
						
						d1List.get(i_d1).getD3L().add(d3List.get(i_d3));
						d3List.get(i_d3).setD1(d1List.get(i_d1));
						i_d3++;						
					}
					
					while(i_d4 < d4List.size() && d4List.get(i_d4).getIdnr() == b0.getIdnr()){
						
						d1List.get(i_d1).setD4(d4List.get(i_d4));
						d4List.get(i_d4).setD1(d1List.get(i_d1));
						i_d4++;						
					}
					
					
					i_d1++;

				}
				i_b1++;
				
			}
			
			while(i_b6 < b6List.size() && b6List.get(i_b6).getIdnr() == b0.getIdnr()){				
				
				b0.getB6L().add(b6List.get(i_b6));
				b6List.get(i_b6).setB0(b0);
				
				i_b6++;
				
			}
		}
	}
}
