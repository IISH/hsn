/*
* Naam:    ConvertManager
* Version: 0.1
* Author:  Cor Munnik
* Copyright
*/
package nl.iisg.ids03;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/**
 * 
 * This class initiates the convert process 
 *
 */

public class ConvertManager {

	public void convert(List<OP> ops){
		
		System.out.println("Converting Objects ....");

		
	    for(OP op1: ops){
	    	op1.convert();
	    	
	    }
		
	}

	public void identify(List<OP> ops){
		
		System.out.println("Identifying persons ....");

		
	    for(OP op1: ops){
	    	op1.identify();
	    	
	    }
	    
		
	}

	public void print(List<OP> ops){
		
		System.out.println("Printing data ....");

		
	    for(OP op1: ops){
	    	if(op1.getKeyToRP() != 101955) continue;
	    	op1.print();
	    	
	    }
	    
		
	}
	
	public void giveDate(List<OP> ops){


		System.out.println("Dating Objects ....");

		int o = 0;
	    for(OP op1: ops){
	    	op1.giveDate();
	    	o++;
	    }
	  
	    
	}

	public void relateAllToAll(List<OP> ops){


		System.out.println("Relating Persons ....");

		int o = 0;
	    for(OP op1: ops){
	    	op1.relateAllToAll();
	    	o++;
	    }
	  
	    
	}

	public void truncate(List<OP> ops){


		System.out.println("Truncating Objects ....");

		int o = 0;
	    for(OP op1: ops){
	    	op1.truncate();
	    	o++;
	    }
	  
	    
	}
	
	
	public void write(List<OP> ops){


		System.out.println("Writing Objects ....");
		
		Utils.getEm_tabs().getTransaction().begin();
 

		int o = 0;
	    for(OP op1: ops){
	    	op1.write();
	    	o++;
	    }
	    
		Utils.getEm_tabs().getTransaction().commit();
		Utils.getEm_tabs().clear();

	  
	    
	}
}
