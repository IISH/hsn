import nl.iisg.convertPK.StandardizePersonalCards;
import nl.iisg.ids03.StandardizePopulationRegister;



public class Test {
	
	public static String [] a = new String[250];
	public static String [] b = new String[250];
	public static String [] c = new String[250];
	
	public static void main( String args[]) {
		 
		b[1]	= "RP";                                     a[1]  = "";              
	  	b[2]	= "Spouse";                                 a[2]  = "S";             
	    b[3]	= "Son";                                    a[3]  = "C";             c[3]  = "M";
	    b[4]	= "Daughter";                               a[4]  = "C";             c[4]  = "F";
	    b[5]	= "Foster Son";                             a[5]  = "C";             c[5]  = "M";
	    b[6]	= "Foster Daughter";                        a[6]  = "C";             c[6]  = "F";   
		b[7]	= null;
		b[8]	= "Stepson";                                a[8]  = "C";             c[8]  = "M";
		b[9]	= "Stepdaughter";                           a[9]  = "C";             c[9]  = "F";
		b[10]	= "Grandfather";                            a[10] = "PP";            c[10] = "M";        
		b[11]	= "Father";                                 a[11] = "PP";            c[11] = "M";  
		b[12]	= "Brother";                                a[12] = "PC";            c[12] = "M";
		b[13]	= "Uncle";                                  a[13] = "PPC";           c[13] = "M";
		b[14]	= "Cousin or Nephew";                      
		b[15]	= "Cousin";                                 a[15] = "PPCC";
		b[16]	= "Nephew";
		b[17]	= "Second Cousin or Second Nephew";
	    b[18]	= "Great Uncle";
	    b[19]	= null;
	    b[20]	= "Grandmother";
		b[21]	= "Mother";
		b[22]	= "Sister";
		b[23]	= "Aunt";
		b[24]	= "Cousin or Niece";
		b[25]	= "Cousin";
		b[26]	= "Niece";
		b[27]	= "Second cousin or Second Niece";
		b[28]	= "Great Aunt";
		b[29]	= null;
		b[30]	= "Grandson";                         
		b[31]	= "Grandson by Marriage";                         
		b[39]	= "Granddaughter by Marriage";                         
		b[40]	= "Granddaughter";                      
		b[41]	= "Lodger";
		b[42]	= "Servant";
		b[43]	= "Maid";
		b[44]	= "Stepchild";
		b[45]	= "Orphan";
		b[46]	= "Baboe";
		b[47]	= "Aquaintence";
		b[48]	= "Lving in";
		b[49]	= "Pupil";
		b[50]	= null;
		b[51]	= "Great Grandson";
		b[52]	= "Great Granddaughter";
		b[53]	= "Son-in-Law";
		b[54]	= "Daughter-in-Law";
		b[55]	= "Female neighbour";
		b[56]	= "Female aquaintence";
		b[57]	= "Male Neighbour";
		b[58]	= "Male Aquaintence";
		b[59]	= "Aquaintence";
		b[60]	= "Grandfather in Law";
		b[61]	= "Father-in-Law";
		b[62]	= "Stepfather by Marriage";
		b[63]	= "Brother-in-Law";
		b[64]	= "Cousin-in-Law or Nephew-in-Law";
		b[65]	= "Cousin-in-Law";
		b[66]	= "Nephew-in-Law";
		b[67]	= "Second Cousin by Marriage or Second Nephew by Marriage";
		b[68]	= "Uncle by Marriage";
		b[69]	= "Great Uncle by Marriage";
		b[70]	= "Grandmother by Marriage";
		b[71]	= "Mother-in-Law";
		b[72]	= "Stepsister by Marriage";
	    b[73]	= "Sister-in-Law";
	    b[74]	= "Cousin-in-Law or Niece-in-Law";
	    b[75]	= "Cousin-in-Law";
	    b[76]	= "Niece-in-Law";
	    b[77]	= "Secound Cousin by Marriage or Second Niece by Marriage";
	    b[78]	= "Aunt by Marriage";
	    b[79]	= "Great Aunt by Marriage";
	    b[80]	= "Blood-related";
	    b[81]	= "Stepfather";
	    b[82]	= "Stepmother";
	    b[83]	= "Stepson-in-Law";
	    b[84]	= "Stepdaughter-in-Law";
	    b[85]	= "Stepbrother";
	    b[86]	= "Stepsister";
	    b[87]	= "Halfbrother";
	    b[88]	= "Halfsister";
	    b[89]	= null;
	    b[90]	= "Not blood-related";
		b[91]	= "Lodger";
		b[92]	= "Servant";
		b[93]	= "Servant";
		b[94]	= "Servant";
		b[95]	= "Maid";
		b[96]	= "Governess";
		b[97]	= "Lady in Waiting";
		b[98]	= "Log, ?????";
		b[99]	= "Other  -> remarks";
				
		b[105] = "Explicit Head (living alone)";
	    b[106] = "Explicit Head ";
		b[107] = "Explicit Head (Explicit Successor)";
		b[108] = "Explicit Head (2nd Explicit Successor)";
		b[109] = "Explicit Head (3rd Explicit Successor)";
		b[110] = "Explicit Head (2nd Husband)";
		b[111] = "Implicit Head (Married Son)";
		b[112] = "Implicit Head (Brother)";
		b[113] = "Implicit Head (Widow / husband gone)";
		b[114] = "Implicit Head (Unmarried Son)";
		b[115] = "Implicit Head (Son in Law)";
		b[116] = "Implicit Head (Daughter)";
		b[117] = "Implicit Head (Brother in Law)";
		b[118] = "Implicit Head (Sister)";
		b[119] = "Implicit Head (Other blood-related";
		b[120] = "Implicit Head (Not blood-related";
		b[121] = "Brother or Halfbrother";
		b[122] = "Sister or Halfsister";
		b[123] = "Son or Stepson";
		b[124] = "Daughter or Stepdaughter";
		b[125] = "Undetermined (was Fosterson)";
		b[126] = "Undetermined (was Fosterdaughter)";
		b[127] = null;
		b[128] = "Brother or (Step/half)-brother";
		b[129] = "Sister or (Step/half)-sister";
		b[130] = null;
		b[131] = "Blood-related, not encoded further";
		b[132] = "Blood-related to previous Head";
		b[133] = "Child (PC)";
		b[134] = "Stepchild (PC)";
		b[135] = "Child or Stepchild (PC)";
		b[136] = null;
		b[137] = null;
		b[138] = "(Half) Brother or Cousin";
	    b[139] = "(half) Sister of Cousin";
	    b[140] = null;
		b[141] = "(Step)-brother";
	    b[142] = "(Step)-sister";
	    b[143] = "Fosterfather";
	    b[144] = "Fostermother";
	    b[145] = "Husband (RP is not Head";
	    b[146] = "Wife (RP is not Head";
	    b[147] = "Husband (Explicit Head)";
	    b[148] = "(Step)-father";
	    b[149] = "(Step)-mother";
	    b[150] = "Husband of OP";
	    
		 
	} 
		

}
