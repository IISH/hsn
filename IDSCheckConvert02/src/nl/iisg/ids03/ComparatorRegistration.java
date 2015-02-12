/*
* Naam:    ComparatorRegistration
* Version: 0.1
* Author:  Cor Munnik
* Copyright
*/

package nl.iisg.ids03;

import java.util.Comparator;

/**
 * 
 * This class is used for sorting Registration objects
 *
 */

public class ComparatorRegistration implements Comparator<Registration>{
	
		
public int compare(Registration r1, Registration r2){

	
   if     (r1.getKeyToRP() > r2.getKeyToRP()) return 1;
   else if(r1.getKeyToRP() < r2.getKeyToRP()) return -1;

   if     (r1.getYearEntryHead() > r2.getYearEntryHead()) return  1;
   else if(r1.getYearEntryHead() < r2.getYearEntryHead()) return -1;
   
   
   if     (r1.getMonthEntryHead() > r2.getMonthEntryHead()) return  1;
   else if(r1.getMonthEntryHead() < r2.getMonthEntryHead()) return -1;
   
   if     (r1.getDayEntryHead() > r2.getDayEntryHead()) return  1;
   else if(r1.getDayEntryHead() < r2.getDayEntryHead()) return -1;
   
   if     (r1.getKeyToSourceRegister() > r2.getKeyToSourceRegister()) return  1;
   else if(r1.getKeyToSourceRegister() < r2.getKeyToSourceRegister()) return -1;

   return 0;
   
}   
   
    
}

