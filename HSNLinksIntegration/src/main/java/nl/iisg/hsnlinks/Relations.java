package nl.iisg.hsnlinks;

public class Relations {
	
	
	/*
	 * 
	 * Clariah:                                       HSN


Spouse                                          2                                
Child						Son 3/Daughter 4

Mother-in-law                                   71
Father-in-law                                   61  

Grandfather                                     10
Grandmother                                     20

Half-Sibling (same mother)                      Brother or Halfbrother 121 / Sister or Halfsister 122
Half-Sibling (same father)                      " 
Sibling                                         Brother 12 / Sister 22

Grandmother-in-law                              60 
Grandfather-in-law                              70

Half-Sibling-in-law (partner has same mother)
Half-Sibling-in-law (partner has same father)
	 */
	public static String [] a = new String[250];
	public static String [] b = new String[250];
	
	static{	
		
	a[2] = "Spouse";
	a[3] = "Child";
	a[4] = "Child";
	a[5] = "Foster Child";
	a[6] = "Foster Child";
	a[7] = "null";
	a[8] = "Stepchild";
	a[9] = "Stepchild";
	a[10] = "Grandfather";
	a[11] = "Father";
	a[12] = "Sibling";
	a[13] = "Uncle";
	a[14] = "Cousin or Nephew";
	a[15] = "Cousin";
	a[16] = "Nephew";
	a[17] = "Relative";
	a[18] = "Relative";
	a[19] = "null";
	a[20] = "Grandmother";
	a[21] = "Mother";
	a[22] = "Sibling";
	a[23] = "Aunt";
	a[24] = "Cousin or Niece";
	a[25] = "Cousin";
	a[26] = "Niece";
	a[27] = "Relative";
	a[28] = "Relative";
	a[29] = "null";
	a[30] = "Grandchild";
	a[31] = "Cousin or Nephew";
	a[32] = "Cousin or Niece";
	a[40] = "Grandchild";
	a[41] = "Boarder";
	a[42] = "Servant";
	a[43] = "Maid";
	a[44] = "Foster child";
	a[45] = "Orphan";
	a[46] = "Servant";
	a[47] = "Aquaintance";
	a[48] = "Living in";
	a[49] = "Pupil";
	a[50] = "null";
	a[51] = "Great grandchild";
	a[52] = "Great grandchild";
	a[53] = "Child-in-law";
	a[54] = "Child-in-law";
	a[55] = "Neighbour";
	a[56] = "Aquaintance";
	a[57] = "Neighbour";
	a[58] = "Aquaintance";
	a[59] = "Aquaintance";
	a[60] = "Grandfather-in-law";
	a[61] = "Father-in-law";
	a[62] = "Relative";
	a[63] = "Sibling-in-law";
	a[64] = "Cousin-in-law or Nephew-in-law";
	a[65] = "Cousin-in-law";
	a[66] = "Nephew-in-law";
	a[67] = "Relative";
	a[68] = "Relative";
	a[69] = "Relative";
	a[70] = "Grandmother-in-law";
	a[71] = "Mother-in-law";
	a[72] = "Relative";
	a[73] = "Sibling-in-law";
	a[74] = "Cousin-in-law or Niece-in-law";
	a[75] = "Cousin- in-law";
	a[76] = "Niece-in-law";
	a[77] = "Relative";
	a[78] = "Relative";
	a[79] = "Relative";
	a[80] = "Relative";
	a[81] = "Stepfather";
	a[82] = "Stepmother";
	a[83] = "Stepchild-in-law";
	a[84] = "Stepchild-in-law";
	a[85] = "Step-sibling";
	a[86] = "Step-sibling";
	a[87] = "Half-sibling";
	a[88] = "Half-sibling";
	a[89] = "null";
	a[90] = "Non-relative";
	a[91] = "Boarder";
	a[92] = "Servant";
	a[93] = "Maid";
	a[94] = "Servant";
	a[95] = "Maid";
	a[96] = "Governess";
	a[97] = "Lady-in-waiting";
	a[98] = "Overnight guest";
	a[99] = "Unknown";
	a[105] = "Householder";
	a[106] = "Householder";
	a[107] = "Householder";
	a[108] = "Householder";
	a[109] = "Householder";
	a[110] = "Householder";
	a[111] = "Householder";
	a[112] = "Householder";
	a[113] = "Householder";
	a[114] = "Householder";
	a[115] = "Householder";
	a[116] = "Householder";
	a[117] = "Householder";
	a[118] = "Householder";
	a[119] = "Householder";
	a[120] = "Householder";
	a[121] = "Sibling or Half-sibling";
	a[122] = "Sibling or Half-sibling";
	a[123] = "Child or Stepchild";
	a[124] = "Child or Stepchild";
	a[125] = "Unknown";
	a[126] = "Unknown";
	a[127] = "null";
	a[128] = "Sibling or Half-sibling or Step-sibling";
	a[129] = "Sibling or Half-sibling or Step-sibling";
	a[130] = "null";
	a[131] = "Relative";
	a[132] = "Relative";
	a[133] = "Child";
	a[134] = "Stepchild";
	a[135] = "Child or Stepchild";
	a[136] = "null";
	a[137] = "null";
	a[138] = "Sibling or Half-sibling or Cousin";
	a[139] = "Sibling or Half-sibling or Cousin";
	a[140] = "null";
	a[141] = "Sibling or Step-sibling";
	a[142] = "Sibling or Step-sibling";
	a[143] = "Foster father";
	a[144] = "Foster mother";
	a[145] = "Husband";
	a[146] = "Wife";
	a[147] = "Husband";
	a[148] = "Father or Stepfather";
	a[149] = "Mother or Stepmother";
	a[150] = "Husband";
	a[201] = "Sibling-in-law";
	a[202] = "Sibling-in-law";
	a[203] = "Spouse";
	a[204] = "Cousin or Nephew";
	a[205] = "Cousin";
	a[206] = "Nephew";
	a[207] = "Clousin or Niece";
	a[208] = "Cousin";
	a[209] = "Niece";
	a[210] = "Living in";
	a[211] = "Aquaintance";
	a[212] = "Sibling-in-law";
	a[213] = "Cousin-in-law or Nephew-in-law";
	a[214] = "Cousin-in-law";
	a[215] = "Nephew-in-law";
	a[216] = "Relative";
	a[217] = "Cousin-in-law or Niece-in-law";
	a[218] = "Cousin-in-law";
	a[219] = "Niece-in-law";
	a[220] = "Relative";
	a[221] = "Stepmother";
	a[223] = "Unknown";
	a[224] = "RP";
	a[225] = "Unknown";
	a[226] = "Unknown";
	a[227] = "Non-relative";
	a[228] = "Wife";
	a[229] = "Husband";
	a[230] = "Aquaintance";
	a[231] = "Non-relative";
	a[232] = "Sibling-in-law";
	a[233] = "Unknown";
	a[234] = "Non-relative";
	a[235] = "Non-relative";
	a[236] = "Cousin-in-law";
	a[237] = "Aquaintance";
	a[238] = "Child-in-law";
	a[239] = "Child";
	a[240] = "Non-relative";
	a[241] = "Non-relative";
	a[242] = "Sibling";
	

	
	
	
	
	b[1]	= "RP";                            
  	b[2]	= "Spouse";                
    b[3]	= "Son";                             
    b[4]	= "Daughter";
    b[5]	= "Foster Son";
    b[6]	= "Foster Daughter";
	b[7]	= null;
	b[8]	= "Stepson";
	b[9]	= "Stepdaughter";
	b[10]	= "Grandfather";
	b[11]	= "Father";
	b[12]	= "Brother";
	b[13]	= "Uncle";
	b[14]	= "Cousin or Nephew";
	b[15]	= "Cousin";
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
