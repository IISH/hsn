


package nl.iisg.ids03;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * This class initiates the check process 
 *
 */
public class CheckManager {
	
	
	public boolean Check(List<OP> ops, List<Person> persons, List<PersonDynamic> personsDynamic, List<Registration> registrations, List<RegistrationAddress> registrationAddresses){
	
		KeyChecks k = new KeyChecks();
	    boolean keysOK = k.CheckKeys(persons, personsDynamic, registrations, registrationAddresses);
	    if(!keysOK)
	    	return false;
	    
	    System.out.println("Checking Objects ....");
	    
	    int c = 0;
	    boolean returnCode = true;
	    for(OP op1: ops){
	    	if(++c % 100 == 0)
	    		System.out.println("  Checked " + c + " OP's, returnCode = " + returnCode);
	    	boolean rc = op1.check();
	    	if(rc == false)
	    		returnCode = false;
	    	
	    }
	    
	    return returnCode;
		
	}
	
	

		
	
	
	
	
	
	
	
}
