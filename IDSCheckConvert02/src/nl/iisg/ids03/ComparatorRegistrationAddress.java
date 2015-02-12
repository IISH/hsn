/*
* Naam:    ComparatorRegistrationAddress
* Version: 0.1
* Author:  Cor Munnik
* Copyright
*/
package nl.iisg.ids03;

import java.util.Comparator;

/**
 * 
 * This class is used for sorting RegistrationAddress objects
 *
 */

public class ComparatorRegistrationAddress implements Comparator<RegistrationAddress>{
	
		
public int compare(RegistrationAddress ra1, RegistrationAddress ra2){

   if     (ra1.getKeyToRP() > ra2.getKeyToRP()) return  1;
   else if(ra1.getKeyToRP() < ra2.getKeyToRP()) return -1;

   if     (ra1.getYearEntryHead() > ra2.getYearEntryHead()) return  1;
   else if(ra1.getYearEntryHead() < ra2.getYearEntryHead()) return -1;   
   
   if     (ra1.getMonthEntryHead() > ra2.getMonthEntryHead()) return  1;
   else if(ra1.getMonthEntryHead() < ra2.getMonthEntryHead()) return -1;
   
   if     (ra1.getDayEntryHead() > ra2.getDayEntryHead()) return  1;
   else if(ra1.getDayEntryHead() < ra2.getDayEntryHead()) return -1;
   
   if     (ra1.getKeyToSourceRegister() > ra2.getKeyToSourceRegister()) return  1;
   else if(ra1.getKeyToSourceRegister() < ra2.getKeyToSourceRegister()) return -1;
   
   if     (ra1.getKeyToRegistrationPersons() > ra2.getKeyToRegistrationPersons()) return  1;
   else if(ra1.getKeyToRegistrationPersons() < ra2.getKeyToRegistrationPersons()) return -1;
   
   if     (ra1.getSequenceNumberToAddresses() > ra2.getSequenceNumberToAddresses()) return  1;
   else if(ra1.getSequenceNumberToAddresses() < ra2.getSequenceNumberToAddresses()) return -1;
   
   return 0;
   
}   
   
   
}

