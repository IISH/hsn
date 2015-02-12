package iisg.nl.hsnmigrate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import iisg.nl.hsnimport.Stpb;
import iisg.nl.hsnnieuw.B0;




public class Converter {
	
	String[] tablesin  = {"Stpb", "Gebknd", "Gebgtg", "Gebbyz", "Gebkant", "Gebvdr", "Gebakte", "Ovlknd", "Ovlech", "Ovlagv", "Ovlbyz", "Huwknd", "Huwafk", "Huweer",
  		   "Huwgtg", "Huwvrknd", "Huwbyz"};

	String[] tablesout = {"B0",   "B1",     "B2",     "B3",     "B4",      "B5",     "B6",      "D1",     "D2",     "D3",     "D4",     "M1",     "M2",     "M3",
		   "M4",     "M5",       "M6"};
	
	List<Stpb> stpbList;	
	

	
    
    
	
	public void run(){
	
		
		transformB0();
		
		
	}
	
	private void transformB0(){
		
		EntityManager emi = Utils.getEm_import();	
		Query q = emi.createQuery("select a from Stpb a");
		stpbList  = q.getResultList();
		EntityManager emn = Utils.getEm_nieuw();	
		emn.getTransaction().begin();

		int c = 0;
        for(Stpb stpb: stpbList){
        	
        	B0 b0 = new B0();
        	b0.transform(stpb);
        	emn.persist(b0);
        	
       }
       emn.getTransaction().commit();
       emn.clear();
       //emn.close();
		
	}

	public String[] getTablesin() {
		return tablesin;
	}

	public void setTablesin(String[] tablesin) {
		this.tablesin = tablesin;
	}

	public String[] getTablesout() {
		return tablesout;
	}

	public void setTablesout(String[] tablesout) {
		this.tablesout = tablesout;
	}

	public List<Stpb> getStpbList() {
		return stpbList;
	}

	public void setStpbList(List<Stpb> stpbList) {
		this.stpbList = stpbList;
	}

	
	
	


}
