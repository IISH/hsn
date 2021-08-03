/*
* Naam:    ComparatorPersonDynamic
* Version: 0.1
* Author:  Cor Munnik
* Copyright
*/
package nl.iisg.ids03;

import java.util.Comparator;

/**
 * 
 * This class is used for sorting PersonDynamic objects
 *
 */
public class ComparatorPersonDynamic implements Comparator<PersonDynamic>{
	
		
public int compare(PersonDynamic pd1, PersonDynamic pd2){
	
   if     (pd1.getKeyToRP() > pd2.getKeyToRP()) return  1;
   else if(pd1.getKeyToRP() < pd2.getKeyToRP()) return -1;
	   
   if     (pd1.getYearEntryHead() > pd2.getYearEntryHead()) return  1;
   else if(pd1.getYearEntryHead() < pd2.getYearEntryHead()) return -1;   
   
   if     (pd1.getMonthEntryHead() > pd2.getMonthEntryHead()) return  1;
   else if(pd1.getMonthEntryHead() < pd2.getMonthEntryHead()) return -1;
   
   if     (pd1.getDayEntryHead() > pd2.getDayEntryHead()) return  1;
   else if(pd1.getDayEntryHead() < pd2.getDayEntryHead()) return -1;   
   
   if     (pd1.getKeyToSourceRegister() > pd2.getKeyToSourceRegister()) return  1;
   else if(pd1.getKeyToSourceRegister() < pd2.getKeyToSourceRegister()) return -1;
   
   if     (pd1.getKeyToRegistrationPersons() > pd2.getKeyToRegistrationPersons()) return  1;
   else if(pd1.getKeyToRegistrationPersons() < pd2.getKeyToRegistrationPersons()) return -1;
   
   if     (pd1.getDynamicDataType() > pd2.getDynamicDataType()) return  1;
   else if(pd1.getDynamicDataType() < pd2.getDynamicDataType()) return -1;
   
   if     (pd1.getDynamicDataSequenceNumber() > pd2.getDynamicDataSequenceNumber()) return  1;
   else if(pd1.getDynamicDataSequenceNumber() < pd2.getDynamicDataSequenceNumber()) return -1;
   
   return 0;
   
}   
   
   
}