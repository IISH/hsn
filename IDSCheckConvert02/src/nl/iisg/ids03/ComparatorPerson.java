/*
* Naam:    ComparatorPerson
* Version: 0.1
* Author:  Cor Munnik
* Copyright
*/
package nl.iisg.ids03;

import java.util.Comparator;

/**
 * 
 * This class is used for sorting Person objects
 *
 */
public class ComparatorPerson implements Comparator<Person>{
	
		
public int compare(Person p1, Person p2){

   if     (p1.getKeyToRP() > p2.getKeyToRP()) return 1;
   else if(p1.getKeyToRP() < p2.getKeyToRP()) return -1;
   
   if     (p1.getYearEntryHead() > p2.getYearEntryHead()) return  1;
   else if(p1.getYearEntryHead() < p2.getYearEntryHead()) return -1;
   
   if     (p1.getMonthEntryHead() > p2.getMonthEntryHead()) return  1;
   else if(p1.getMonthEntryHead() < p2.getMonthEntryHead()) return -1;
   
   if     (p1.getDayEntryHead() > p2.getDayEntryHead()) return  1;
   else if(p1.getDayEntryHead() < p2.getDayEntryHead()) return -1;
   
   if     (p1.getKeyToSourceRegister() > p2.getKeyToSourceRegister()) return  1;
   else if(p1.getKeyToSourceRegister() < p2.getKeyToSourceRegister()) return -1;
   
   if     (p1.getKeyToRegistrationPersons() > p2.getKeyToRegistrationPersons()) return 1;
   else if(p1.getKeyToRegistrationPersons() < p2.getKeyToRegistrationPersons()) return -1;
   
   return 0;
   
}   
   
   
}
