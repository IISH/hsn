package nl.iisg.ids03;

/**
 * 
 * This class contains routines that are used throughout the application
 *  
 */

public class Utils2 {
	
	
	public static int[] transformDateFields(int day, int month, int year, int dayCorrected, int monthCorrected, int yearCorrected){

		int[] orgValue = new int[3];
		int[] corValue = new int[3];
		int[] outValue = new int[4];

		orgValue[0] = day;
		orgValue[1] = month;
		orgValue[2] = year;

		corValue[0] = dayCorrected;
		corValue[1] = monthCorrected;
		corValue[2] = yearCorrected;


		int hflag = 0;
		int flag = 0; 

		for(int i = 0; i < 3; i++){
			if(orgValue[i] > 0){
				if(corValue[i] > 0){
					outValue[i] = corValue[i];
					if(flag <= 10) 
						flag = 20;
				}
				else{
					outValue[i] = orgValue[i];
					if(flag == 0)
						flag = 10;
				}
			}	
			else{
				switch(orgValue[i]){
				case -1: hflag = 11; break;
				case -2: hflag = 12; break;
				case -3: hflag = 13; break;
				default: hflag = 13;
				}				
				if(corValue[i] > 0){
					outValue[i] = corValue[i];
					flag = hflag + 10; 
				}
				else{
					outValue[i] = 0;
					flag = hflag;
				}
			}
		}
		
		if(outValue[2] > 0){
			if(outValue[1] == 0){
				outValue[1] = 7;
				outValue[0] = 1;
				flag = 41;
			}
			else
				if(outValue[0] == 0){
					outValue[0] = 1;
					flag = 42;
				}
		}

		outValue[3] = flag;
		return(outValue);
	}

	


}
