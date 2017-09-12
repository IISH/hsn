/*
* Naam:    ConstRelations2
* Version: 0.1
* Author:  Cor Munnik
* Copyright
*/

package nl.iisg.hsncommon;

/**
 * 
 * This class contains definitions for constants used throughout the application
 * 
 *  
 */
public class ConstRelations2 {
	
  // Codes for B2FCBG - natureOfPerson 	
	
	 public static final int FIRST_APPEARANCE_OF_OP =   1;
	 public static final int OTHER_PERSON_THAN_OP =     2;
	 public static final int FURTHER_APPEARANCE_OF_OP = 5;
	
	
	
  // Codes for B3TYPE - keyToDistinguishDynamicDataType   
    
    
    public static final int RELATIE_TOT_HOOFD    =  1;
    public static final int RELATIE_TOT_HOOFD_ST = 11;
    public static final int OUDER_KIND           = 12;    
    public static final int BURGELIJKE_STAAT     =  2;
    public static final int GODSDIENST           =  3;
    public static final int ALLTOALL             =  4;
    public static final int BEROEPSTITEL         =  5;
    public static final int AANKOMST             =  6;
    public static final int VERTREK              =  7;
    
    
    	
 // Codes for B3CODE - contentTypeOfDynamicData;
	
    // B3TYPE = 1
	
	public static final int HOOFD = 								1;
	public static final int ECHTGENOTE_HOOFD = 						2;
	
	public static final int ZOON = 									3;
	public static final int DOCHTER = 								4;
	public static final int PLEEGZOON = 							5;
	public static final int PLEEGDOCHTER =                      	6;
	
	public static final int STIEFZOON = 							8;
	public static final int STIEFDOCHTER = 							9;
	
	public static final int GROOTVADER = 						   10;
	public static final int VADER =      						   11;
	public static final int BROER = 				     		   12;
	public static final int OOM =                                  13;
	public static final int NEEF_GEEN_SPECIFICATIE =               14;
	public static final int NEEF_COUSIN =                          15;
	public static final int NEEF_NEPHEW =                          16;
	public static final int ACHTERNEEF =                           17;
	public static final int OUDOOM =                               18;
	
	public static final int GROOTMOEDER =                          20;
	public static final int MOEDER =                               21;
	public static final int ZUSTER =                               22;
	public static final int TANTE =                                23;
	public static final int NICHT_GEEN_SPECIFICATIE =              24;
	public static final int NICHT_COUSIN =                         25;
	public static final int NICHT_NIECE =                          26;
	public static final int ACHTERNICHT =                          27;
	public static final int OUDTANTE =                             28;
	
	public static final int KLEINZOON =                            30;
	
	public static final int KLEINDOCHTER =                         40;
	
	public static final int COMMENSAAL =                           41;
	public static final int IN_DIENST =                            42;
	public static final int HUISHOUDSTER =                         43;
	public static final int BESTEDELING =                          44;
	public static final int WEESKIND =                             45;
	public static final int BABOE =                                46;
	public static final int GOEDE_BEKENDE =                        47;
	public static final int INWONEND =                             48;
	public static final int PUPIL =                                49;
	
	public static final int ACHTERKLEINZOON =                      51;
	public static final int ACHTERKLEINDOCHTER =                   52;
	public static final int SCHOONZOON =                           53;
	public static final int SCHOONDOCHTER =                        54;
	
	public static final int BUURVROUW =                            55;
	public static final int KENNIS_VROUW =                         56;
	public static final int BUURMAN =                              57;
	public static final int KENNIS_MAN =                           58;
	public static final int BEKENDE =                              59;
	
	public static final int SCHOONGROOTVADER =                     60;
	public static final int SCHOONVADER =                          61;
	public static final int AANGETROUWDE_STIEFVADER =              62;
	public static final int SCHOONBROER_ZWAGER =                   63;
	public static final int SCHOONNEEF_GEEN_SPECIFICATIE =         64;
	public static final int SCHOONNEEF_COUSIN =                    65;
	public static final int SCHOONNEEF_NEPHEW =                    66;
	public static final int AANGETROUWDE_ACHTERNEEF =              67;
	public static final int SCHOONOOM =                            68;
	public static final int AANGETROUWDE_OUDOOM =                  69;
	
	public static final int SCHOONGROOTMOEDER =                    70;
	public static final int SCHOONMOEDER =                         71;
	public static final int AANGETROUWDE_STIEFMOEDER =             72;
	public static final int SCHOONZUS =                            73;
	public static final int SCHOONNICHT_GEEN_SPECIFICATIE =        74;
	public static final int SCHOONNICHT_COUSIN =                   75;
	public static final int SCHOONNICHT_NIECE =                    76;
	public static final int AANGETROUWDE_ACHTERNICHT =             77;
	public static final int SCHOONTANTE =                          78;
	public static final int AANGETROUWDE_OUDTANTE =                79;
	
	public static final int VERWANT =                              80;
	
	public static final int STIEFVADER =                           81;
	public static final int STIEFMOEDER =                          82;
	public static final int STIEFSCHOONZOON =                      83;
	public static final int STIEFSCHOONDOCHTER =                   84;
	public static final int STIEFBROER =                           85;
	public static final int STIEFZUSTER =                          86;
	public static final int HALFBROER =                            87;
	public static final int HALFZUSTER =                           88;
	
	public static final int GEEN_VERWANTSCHAP =                    90;
	public static final int KOSTGANGER =                           91;
	public static final int BEDIENDE =                             92;
	public static final int DIENSTBODE =                           93;
	public static final int KNECHT =                               94;
	public static final int MEID =                                 95;
	public static final int GOUVERNANTE =                          96;
	public static final int GEZELSCHAPSDAME =                      97;
	public static final int LOGE =                                 98;
	public static final int ANDERS_BYZONDERHEDEN =                 99;
	
	public static final int EXPLICIET__HOOFD_ALLENSTAAND =        105;
	public static final int EXPLICIET_HOOFD =                     106;
	public static final int EXPLICIET_HOOFD_EERSTE_OPVOLGER =     107;
	public static final int EXPLICIET_HOOFD_TWEEDE_OPVOLGER =     108;
	public static final int EXPLICIET_HOOFD_DERDE_OPVOLGER =      109;
	
	public static final int IMPLICIET_HOOFD_TWEEDE_MAN =          110;
	public static final int IMPLICIET_HOOFD_GEHUWDE_ZOON =        111;
	public static final int IMPLICIET_HOOFD_BROER =               112;
	public static final int IMPLICIET_HOOFD_WEDUWE_OF_MAN_WEG =   113;
	public static final int IMPLICIET_HOOFD_ONGEHUWDE_ZOON =      114;
	public static final int IMPLICIET_HOOFD_SCHOONZOON =          115;
	public static final int IMPLICIET_HOOFD_DOCHTER =             116;
	public static final int IMPLICIET_HOOFD_ZWAGER =              117;
	public static final int IMPLICIET_HOOFD_ZUSTER =              118;
	public static final int IMPLICIET_HOOFD_OVERIGE_VERWANT =     119;
	public static final int IMPLICIET_HOOFD_GEEN_VERWANT =        120;
	
	public static final int BROER_OF_HALFBROER =                  121;
	public static final int ZUSTER_OF_HALFZUSTER =                122;
	public static final int ZOON_OF_STIEFZOON =                   123;
	public static final int DOCHTER_OF_STIEFDOCHTER =             124;
	public static final int ONBEKEND_WAS_PLEEGZOON =              125;
	public static final int ONBEKEND_WAS_PLEEGDOCHTER =           126;
	public static final int STIEF_OF_HALF_OF_BROER =              128;
	public static final int STIEF_OF_HALF_OF_ZUSTER =             129;
	
	public static final int VERWANTE =                            131;
	public static final int VERWANTE_VAN_VORIG_HOOFD =            132;
	public static final int KIND_PK =                             133;
	public static final int STIEFKIND_PK =                        134;
	public static final int KIND_OF_STIEFKIND =                   135;
	public static final int KLEINKIND       =                     136;
	public static final int HALFBROER_OF_NEEF =                   138;
	public static final int HALFZUSTER_OF_NICHT =                 139;
	
	public static final int HALFBROER_OF_STIEFBROER =             141; 
	public static final int HALFZUSTER_OF_STIEFZUSTER =           142;
	public static final int PLEEGVADER =                          143;
	public static final int PLEEGMOEDER =                         144;
	public static final int ECHTGENOOT_MAN_GEEN_HOOFD =           145;
	public static final int ECHTGENOTE_VROUW_GEEN_VROUW_VAN_HOOFD = 146;
	public static final int ECHTGENOOT_EXPLICIET_HOOFD =          147;
	public static final int VADER_OF_STIEFVADER =                 148;
	public static final int MOEDER_OF_STIEFMOEDER =               149;
	public static final int ECHTGENOOT_VROUWELIJKE_OP =           150;
	public static final int STIEFGROOTVADER =                     151;
	public static final int STIEFGROOTMOEDER =                    152;
	public static final int STIEFKLEINZOON =                      153;
	public static final int STIEFKLEINDOCHTER =                   154;
	public static final int STIEFKLEINKIND    =                   155;
	public static final int SIBLING           =                   156;
	public static final int STIEFSIBLING      =                   157;
	public static final int OUDER      =                          158;
	public static final int STIEFOUDER      =                     159;
	public static final int SCHOONKIND      =                     160;
	public static final int PARTNER							    = 161;
	public static final int HALFSIBLING     =                     162;
	public static final int GROOTOUDER     =                      164;
	public static final int STIEFGROOTOUDER =                     165;
	public static final int SCHOONOUDER =                         166;
	

	// B3TYPE = 2
	 
    public static final int ONGEHUWD =                              	 1;
    public static final int WEDUWNAAR_WEDUWE =                   	     2;                   
    public static final int GESCHEIDEN =                          	     3;  
    public static final int GEHUWD =                              	     5;
    public static final int ONBEKEND =                                   6;
    public static final int ONBEKEND_OP_LATER_ONBEKEND_MOMENT_GEHUWD =   9;
    
    
    // Arrays to see if a code is valid.
    
  
    public static String [] b3kode1 =        new String[1000];
    public static String [] b3kode1_English =        new String[1000];
    public static String [] b3kode1_Male  =  new String[200];
    public static String [] b3kode1_Female = new String[200];
    public static String [] b3kode1_Related = new String[200];
    public static String [] b3kode1_Marriable = new String[200];
    public static int [][]  b3kode1_No_Marriage_Allowed = new int[200][] ;    
	public static String [] b3kode2 = new String[10];
	
	public static int[] newHusband    =   new int[200];
	public static int[] marriedSon    =   new int[200];
	public static int[] brother       =   new int[200];
	public static int[] widow         =   new int[200];
	public static int[] unmarriedSon  =   new int[200];
	public static int[] sonInLaw      =   new int[200];
	public static int[] daughter      =   new int[200];
	public static int[] brotherInLaw  =   new int[200];
	public static int[] sister        =   new int[200];
	public static int[] mToF          =   new int[200];
	public static int[] fToM          =   new int[200];
	
static{
	
	// B3kode1
	
	b3kode1[1]	= "Hoofd";                            
  	b3kode1[2]	= "Echtgeno(o)t(e) Hoofd";                
    b3kode1[3]	= "Zoon";                             
    b3kode1[4]	= "Dochter";
    b3kode1[5]	= "Pleegzoon";
    b3kode1[6]	= "Pleegdochter";
	b3kode1[7]	= null;
	b3kode1[8]	= "Stiefzoon";
	b3kode1[9]	= "Stiefdochter";
	b3kode1[10]	= "Grootvader";
	b3kode1[11]	= "Vader";
	b3kode1[12]	= "Broer";
	b3kode1[13]	= "Oom";
	b3kode1[14]	= "Neef (geen specificatie)";
	b3kode1[15]	= "Neef (Cousin)";
	b3kode1[16]	= "Neef (Nephew)";
	b3kode1[17]	= "Achterneef";
    b3kode1[18]	= "Oudoom";
    b3kode1[19]	= null;
    b3kode1[20]	= "Grootmoeder";
	b3kode1[21]	= "Moeder";
	b3kode1[22]	= "Zuster";
	b3kode1[23]	= "Tante";
	b3kode1[24]	= "Nicht (geen specificatie)";
	b3kode1[25]	= "Nicht (Cousin)";
	b3kode1[26]	= "Nicht (Niece)";
	b3kode1[27]	= "Achternicht";
	b3kode1[28]	= "Oudtante";
	b3kode1[29]	= null;
	b3kode1[30]	= "Kleinzoon";                         
	b3kode1[40]	= "Kleindochter";                      
	b3kode1[41]	= "Commensaal";
	b3kode1[42]	= "In Dienst";
	b3kode1[43]	= "Huishoudster";
	b3kode1[44]	= "Bestedeling";
	b3kode1[45]	= "Weeskind";
	b3kode1[46]	= "Baboe";
	b3kode1[47]	= "Goede Bekende";
	b3kode1[48]	= "Inwonend";
	b3kode1[49]	= "Pupil";
	b3kode1[50]	= null;
	b3kode1[51]	= "Achterkleinzoon";
	b3kode1[52]	= "Achterkleindochter";
	b3kode1[53]	= "Schoonzoon";
	b3kode1[54]	= "Schoondochter";
	b3kode1[55]	= "Buurvrouw";
	b3kode1[56]	= "Kennis (vrouw)";
	b3kode1[57]	= "Buurman";
	b3kode1[58]	= "Kennis (man)";
	b3kode1[59]	= "Bekende";
	b3kode1[60]	= "Schoongrootvader";
	b3kode1[61]	= "Schoonvader";
	b3kode1[62]	= "Aangetrouwde Stiefvader";
	b3kode1[63]	= "Schoonbroer (zwager)";
	b3kode1[64]	= "Schoonneef (geen specificatie)";
	b3kode1[65]	= "Schoonneef (Cousin)";
	b3kode1[66]	= "Schoonneef (Nephew)";
	b3kode1[67]	= "Aangetrouwde Achterneef";
	b3kode1[68]	= "Schoonoom";
	b3kode1[69]	= "Aangetrouwde Oudoom";
	b3kode1[70]	= "Schoongrootmoeder";
	b3kode1[71]	= "Schoonmoeder";
	b3kode1[72]	= "Aangetrouwde Stiefmoeder";
    b3kode1[73]	= "Schoonzus";
    b3kode1[74]	= "Schoonnicht (geen specificatie)";
    b3kode1[75]	= "Schoonnicht (cousin)";
    b3kode1[76]	= "Schoonnicht (niece)";
    b3kode1[77]	= "Aangetrouwde Achternicht";
    b3kode1[78]	= "Schoontante";
    b3kode1[79]	= "Aangetrouwde Oudtante";
    b3kode1[80]	= "Verwant";
    b3kode1[81]	= "Stiefvader";
    b3kode1[82]	= "Stiefmoeder";
    b3kode1[83]	= "Stiefschoonzoon";
    b3kode1[84]	= "Stiefschoondochter";
    b3kode1[85]	= "Stiefbroer";
    b3kode1[86]	= "Stiefzuster";
    b3kode1[87]	= "Halfbroer";
    b3kode1[88]	= "Halfzuster";
    b3kode1[89]	= null;
    b3kode1[90]	= "Geen verwantschap";
	b3kode1[91]	= "Kostganger";
	b3kode1[92]	= "Bediende";
	b3kode1[93]	= "Dienstbode";
	b3kode1[94]	= "Knecht";
	b3kode1[95]	= "Meid";
	b3kode1[96]	= "Gouvernante";
	b3kode1[97]	= "Gezelschapsdame";
	b3kode1[98]	= "Log, ?????";
	b3kode1[99]	= "Anders  -> bijzonderheden";
			
	b3kode1[105] = "Expliciet Hoofd (alleenstaande)";
    b3kode1[106] = "Expliciet Hoofd ";
	b3kode1[107] = "Expliciet Hoofd (expl. Opvolger)";
	b3kode1[108] = "Expliciet Hoofd (2de expl. Opvolger)";
	b3kode1[109] = "Expliciet Hoofd (3de expl. Opvolger)";
	b3kode1[110] = "Expliciet Hoofd (2de man)";
	b3kode1[111] = "Impliciet Hoofd (gehuwde zoon)";
	b3kode1[112] = "Impliciet Hoofd (broer)";
	b3kode1[113] = "Impliciet Hoofd (wed. / man  weg)";
	b3kode1[114] = "Impliciet Hoofd (ongehuwde zoon)";
	b3kode1[115] = "Impliciet Hoofd (schoonzoon)";
	b3kode1[116] = "Impliciet Hoofd (dochter)";
	b3kode1[117] = "Impliciet Hoofd (zwager)";
	b3kode1[118] = "Impliciet Hoofd (zuster)";
	b3kode1[119] = "Impliciet Hoofd (overige verwant)";
	b3kode1[120] = "Impliciet Hoofd (ov. niet verwant)";
	b3kode1[121] = "Broer of halfbroer";
	b3kode1[122] = "Zuster of halfzuster";
	b3kode1[123] = "Zoon of stiefzoon";
	b3kode1[124] = "Dochter of stiefdochter";
	b3kode1[125] = "Onbepaald (was pleegzoon)";
	b3kode1[126] = "Onbepaald (was pleegdochter)";
	b3kode1[127] = null;
	b3kode1[128] = "(Stief/half) broer of broer";
	b3kode1[129] = "(Stief/half) zuster of zuster";
	b3kode1[130] = null;
	b3kode1[131] = "Verwante, niet verder gecodeerd";
	b3kode1[132] = "Verwante van vorig Hoofd";
	b3kode1[133] = "Kind (PK)";
	b3kode1[134] = "Stiefkind (PK)";
	b3kode1[135] = "Kind of stiefkind (PK)";
	b3kode1[136] = null;
	b3kode1[137] = null;
	b3kode1[138] = "(Half) broer of neef";
    b3kode1[139] = "(half) zuster of nicht";
    b3kode1[140] = null;
	b3kode1[141] = "Broer of stiefbroer";
    b3kode1[142] = "Zuster of stiefzuster";
    b3kode1[143] = "Pleegvader";
    b3kode1[144] = "Pleegmoeder";
    b3kode1[145] = "Echtgenoot (OP geen Hoofd)";
    b3kode1[146] = "Echtgenote (OP geen Hoofd)";
    b3kode1[147] = "Echtgenoot (Expliciet Hoofd)";
    b3kode1[148] = "Vader of stiefvader";
    b3kode1[149] = "Moeder of stiefmoeder";
    b3kode1[150] = "Echtgenoot (vrouwelijke) OP";
    
    b3kode1[997] = "Reeds overl. bij geboorte OP";
    b3kode1[998] = "OP reeds overl. bij geboorte";
    b3kode1[999] = "Persoon is zelf OP";
    
	// B3kode1_English
	
	b3kode1_English[1]	= "Head";                            
  	b3kode1_English[2]	= "Spouse of Head";                
    b3kode1_English[3]	= "Son";                             
    b3kode1_English[4]	= "Daughter";
    b3kode1_English[5]	= "Foster Son";
    b3kode1_English[6]	= "Foster Daughter";
	b3kode1_English[7]	= null;
	b3kode1_English[8]	= "Stepson";
	b3kode1_English[9]	= "Stepdaughter";
	b3kode1_English[10]	= "Grandfather";
	b3kode1_English[11]	= "Father";
	b3kode1_English[12]	= "Brother";
	b3kode1_English[13]	= "Uncle";
	b3kode1_English[14]	= "Cousin or Nephew";
	b3kode1_English[15]	= "Cousin";
	b3kode1_English[16]	= "Nephew";
	b3kode1_English[17]	= "Second Cousin or Second Nephew";
    b3kode1_English[18]	= "Great Uncle";
    b3kode1_English[19]	= null;
    b3kode1_English[20]	= "Grandmother";
	b3kode1_English[21]	= "Mother";
	b3kode1_English[22]	= "Sister";
	b3kode1_English[23]	= "Aunt";
	b3kode1_English[24]	= "Cousin or Niece";
	b3kode1_English[25]	= "Cousin";
	b3kode1_English[26]	= "Niece";
	b3kode1_English[27]	= "Second cousin or Second Niece";
	b3kode1_English[28]	= "Great Aunt";
	b3kode1_English[29]	= null;
	b3kode1_English[30]	= "Grandson";                         
	b3kode1_English[40]	= "Granddaughter";                      
	b3kode1_English[41]	= "Lodger";
	b3kode1_English[42]	= "Servant";
	b3kode1_English[43]	= "Maid";
	b3kode1_English[44]	= "Stepchild";
	b3kode1_English[45]	= "Orphan";
	b3kode1_English[46]	= "Baboe";
	b3kode1_English[47]	= "Aquaintence";
	b3kode1_English[48]	= "Lving in";
	b3kode1_English[49]	= "Pupil";
	b3kode1_English[50]	= null;
	b3kode1_English[51]	= "Great Grandson";
	b3kode1_English[52]	= "Great Granddaughter";
	b3kode1_English[53]	= "Son in Law";
	b3kode1_English[54]	= "Daughter in Law";
	b3kode1_English[55]	= "Female neighbour";
	b3kode1_English[56]	= "Female aquaintence";
	b3kode1_English[57]	= "Male Neighbour";
	b3kode1_English[58]	= "Male Aquaintence";
	b3kode1_English[59]	= "Aquaintence";
	b3kode1_English[60]	= "Grandfathwer in Law";
	b3kode1_English[61]	= "Father in Law";
	b3kode1_English[62]	= "Stepfather by Marriage";
	b3kode1_English[63]	= "Brother in Law";
	b3kode1_English[64]	= "Cousin in Law or Nephew in Law";
	b3kode1_English[65]	= "Cousin in Law";
	b3kode1_English[66]	= "Nephew in Law";
	b3kode1_English[67]	= "Second Cousin by Marriage or Second Nephew by Marriage";
	b3kode1_English[68]	= "Uncle by Marriage";
	b3kode1_English[69]	= "Great Uncle by Marriage";
	b3kode1_English[70]	= "Grandmother by Marriage";
	b3kode1_English[71]	= "Mother in Law";
	b3kode1_English[72]	= "Stepsister by Marriage";
    b3kode1_English[73]	= "Sister in Law";
    b3kode1_English[74]	= "Cousin in Law or Niece in Law";
    b3kode1_English[75]	= "Cousin in Law";
    b3kode1_English[76]	= "Niece in Law";
    b3kode1_English[77]	= "Secound Cousin by Marriage or Second Niece by Marriage";
    b3kode1_English[78]	= "Aunt by Marriage";
    b3kode1_English[79]	= "Great Aunt by Marriage";
    b3kode1_English[80]	= "Blood-related";
    b3kode1_English[81]	= "Stepfather";
    b3kode1_English[82]	= "Stepmother";
    b3kode1_English[83]	= "Stepson in Law";
    b3kode1_English[84]	= "Stepdaughter in Law";
    b3kode1_English[85]	= "Stepbrother";
    b3kode1_English[86]	= "Stepsister";
    b3kode1_English[87]	= "Halfbrother";
    b3kode1_English[88]	= "Halfsister";
    b3kode1_English[89]	= null;
    b3kode1_English[90]	= "Not blood-related";
	b3kode1_English[91]	= "Lodger";
	b3kode1_English[92]	= "Servant";
	b3kode1_English[93]	= "Servant";
	b3kode1_English[94]	= "Servant";
	b3kode1_English[95]	= "Maid";
	b3kode1_English[96]	= "Governess";
	b3kode1_English[97]	= "Lady in Waiting";
	b3kode1_English[98]	= "Log, ?????";
	b3kode1_English[99]	= "Other  -> remarks";
			
	b3kode1_English[105] = "Explicit Head (living alone)";
    b3kode1_English[106] = "Explicit Head ";
	b3kode1_English[107] = "Explicit Head (Explicit Successor)";
	b3kode1_English[108] = "Explicit Head (2nd Explicit Successor)";
	b3kode1_English[109] = "Explicit Head (3rd Explicit Successor)";
	b3kode1_English[110] = "Explicit Head (2nd Husband)";
	b3kode1_English[111] = "Implicit Head (Married Son)";
	b3kode1_English[112] = "Implicit Head (Brother)";
	b3kode1_English[113] = "Implicit Head (Widow / husband gone)";
	b3kode1_English[114] = "Implicit Head (Unmarried Son)";
	b3kode1_English[115] = "Implicit Head (Son in Law)";
	b3kode1_English[116] = "Implicit Head (Daughter)";
	b3kode1_English[117] = "Implicit Head (Brother in Law)";
	b3kode1_English[118] = "Implicit Head (Sister)";
	b3kode1_English[119] = "Implicit Head (Other blood-related";
	b3kode1_English[120] = "Implicit Head (Not blood-related";
	b3kode1_English[121] = "Brother or Halfbrother";
	b3kode1_English[122] = "Sister or Halfsister";
	b3kode1_English[123] = "Son or Stepson";
	b3kode1_English[124] = "Daughter or Stepdaughter";
	b3kode1_English[125] = "Undetermined (was Fosterson)";
	b3kode1_English[126] = "Undetermined (was Fosterdaughter)";
	b3kode1_English[127] = null;
	b3kode1_English[128] = "Brother or (Step/half)-brother";
	b3kode1_English[129] = "Sister or (Step/half)-sister";
	b3kode1_English[130] = null;
	b3kode1_English[131] = "Blood-related, not encoded further";
	b3kode1_English[132] = "Blood-related to previous Head";
	b3kode1_English[133] = "Child (PC)";
	b3kode1_English[134] = "Stepchild (PC)";
	b3kode1_English[135] = "Child or Stepchild (PC)";
	b3kode1_English[136] = null;
	b3kode1_English[137] = null;
	b3kode1_English[138] = "(Half) Brother or Cousin";
    b3kode1_English[139] = "(half) Sister of Cousin";
    b3kode1_English[140] = null;
	b3kode1_English[141] = "(Step)-brother";
    b3kode1_English[142] = "(Step)-sister";
    b3kode1_English[143] = "Fosterfather";
    b3kode1_English[144] = "Fostermother";
    b3kode1_English[145] = "Husband (RP is not Head";
    b3kode1_English[146] = "Wife (RP is not Head";
    b3kode1_English[147] = "Husband (Explicit Head)";
    b3kode1_English[148] = "(Step)-father";
    b3kode1_English[149] = "(Step)-mother";
    b3kode1_English[150] = "Husband of OP";
    
    b3kode1_English[997] = "Deceased at birth RP";
    b3kode1_English[998] = "RP already dead at birth";
    b3kode1_English[999] = "Person is RP himself";
    

    
    // B3kode1 - Male
    
    b3kode1_Male[3]	= "Zoon";                             
    b3kode1_Male[5]	= "Pleegzoon";
	b3kode1_Male[7]	= null;
	b3kode1_Male[8]	= "Stiefzoon";
	b3kode1_Male[10] = "Grootvader";
	b3kode1_Male[11] = "Vader";
	b3kode1_Male[12] = "Broer";
	b3kode1_Male[13] = "Oom";
	b3kode1_Male[14] = "Neef (geen specificatie)";
	b3kode1_Male[15] = "Neef (Cousin)";
	b3kode1_Male[16] = "Neef (Nephew)";
	b3kode1_Male[17] = "Achterneef";
    b3kode1_Male[18] = "Oudoom";
    b3kode1_Male[19] = null;
	b3kode1_Male[29] = null;
	b3kode1_Male[30] = "Kleinzoon";                         
	b3kode1_Male[51] = "Achterkleinzoon";
	b3kode1_Male[53] = "Schoonzoon";
	b3kode1_Male[57] = "Buurman";
	b3kode1_Male[58] = "Kennis (man)";
	b3kode1_Male[60] =	"Schoongrootvader";
	b3kode1_Male[61] = "Schoonvader";
	b3kode1_Male[62] = "Aangetrouwde Stiefvader";
	b3kode1_Male[63] = "Schoonbroer (zwager)";
	b3kode1_Male[64] = "Schoonneef (geen specificatie)";
	b3kode1_Male[65] = "Schoonneef (Cousin)";
	b3kode1_Male[66] = "Schoonneef (Nephew)";
	b3kode1_Male[67] = "Aangetrouwde Achterneef";
	b3kode1_Male[68] = "Schoonoom";
	b3kode1_Male[69] = "Aangetrouwde Oudoom";
    b3kode1_Male[81] = "Stiefvader";
    b3kode1_Male[83] = "Stiefschoonzoon";
    b3kode1_Male[85] = "Stiefbroer";
    b3kode1_Male[87] = "Halfbroer";
    b3kode1_Male[89] = null;
	b3kode1_Male[94] = "Knecht";
			
	b3kode1_Male[111] = "Implicit Head (gehuwde zoon)";
	b3kode1_Male[112] = "Implicit Head (broer)";
	b3kode1_Male[114] = "Implicit Head (ongehuwde zoon)";
	b3kode1_Male[115] = "Implicit Head (schoonzoon)";
	b3kode1_Male[117] = "Implicit Head (zwager)";
	b3kode1_Male[121] = "Broer of halfbroer";
	b3kode1_Male[123] = "Zoon of stiefzoon";
	b3kode1_Male[127] = null;
	b3kode1_Male[128] = "(Stief/half) broer of broer";
	b3kode1_Male[130] = null;
	b3kode1_Male[138] = "(Half) broer of neef";
	b3kode1_Male[141] = "Broer of stiefbroer";
    b3kode1_Male[143] = "Pleegvader";
    b3kode1_Male[145] = "Echtgenoot (OP geen Hoofd)";
    b3kode1_Male[147] = "Echtgenoot (Expliciet Hoofd)";
    b3kode1_Male[148] = "Vader of stiefvader";
    b3kode1_Male[150] = "Echtgenoot (vrouwelijke) OP";
    
    // B3kode1 - Female
    
    b3kode1_Female[4]	= "Dochter";
    b3kode1_Female[6]	= "Pleegdochter";
	b3kode1_Female[9]	= "Stiefdochter";
    b3kode1_Female[20]	= "Grootmoeder";
	b3kode1_Female[21]	= "Moeder";
	b3kode1_Female[22]	= "Zuster";
	b3kode1_Female[23]	= "Tante";
	b3kode1_Female[24]	= "Nicht (geen specificatie)";
	b3kode1_Female[25]	= "Nicht (Cousin)";
	b3kode1_Female[26]	= "Nicht (Niece)";
	b3kode1_Female[27]	= "Achternicht";
	b3kode1_Female[28]	= "Oudtante";
	b3kode1_Female[40]	= "Kleindochter";                      
	b3kode1_Female[43]	= "Huishoudster";
	b3kode1_Female[46]	= "Baboe";

	b3kode1_Female[52]	= "Achterkleindochter";
	b3kode1_Female[54]	= "Schoondochter";
	b3kode1_Female[55]	= "Buurvrouw";
	b3kode1_Female[56]	= "Kennis (vrouw)";
	b3kode1_Female[70]	= "Schoongrootmoeder";
	b3kode1_Female[71]	= "Schoonmoeder";
	b3kode1_Female[72]	= "Aangetrouwde Stiefmoeder";
    b3kode1_Female[73]	= "Schoonzus";
    b3kode1_Female[74]	= "Schoonnicht (geen specificatie)";
    b3kode1_Female[75]	= "Schoonnicht (cousin)";
    b3kode1_Female[76]	= "Schoonnicht (niece)";
    b3kode1_Female[77]	= "Aangetrouwde Achternicht";
    b3kode1_Female[78]	= "Schoontante";
    b3kode1_Female[79]	= "Aangetrouwde Oudtante";
    b3kode1_Female[82]	= "Stiefmoder";
    b3kode1_Female[84]	= "Stiefschoondochter";
    b3kode1_Female[86]	= "Stiefzuster";
    b3kode1_Female[88]	= "Halfzuster";
	b3kode1_Female[95]	= "Meid";
	b3kode1_Female[96]	= "Gouvernante";
	b3kode1_Female[97]	= "Gezelschapsdame";
	b3kode1_Female[113] = "Impliciet Hoofd (wed. / man  weg)";
	b3kode1_Female[116] = "Impliciet Hoofd (dochter)";
	b3kode1_Female[118] = "Impliciet Hoofd (zuster)";
	b3kode1_Female[122] = "Zuster of halfzuster";
	b3kode1_Female[124] = "Dochter of stiefdochter";
	b3kode1_Female[129] = "(Stief/half) zuster of zuster";
    b3kode1_Female[139] = "(half) zuster of nicht";
    b3kode1_Female[142] = "Zuster of stiefzuster";
    b3kode1_Female[144] = "Pleegmoeder";
    b3kode1_Female[146] = "Echtgenote (OP geen Hoofd)";
    b3kode1_Female[149] = "Moeder of stiefmoeder";
    
// B3kode1 - Blood related
	
    b3kode1_Related[3]	= "Zoon";                             
    b3kode1_Related[4]	= "Dochter";
	b3kode1_Related[10]	= "Grootvader";
	b3kode1_Related[11]	= "Vader";
	b3kode1_Related[12]	= "Broer";
	b3kode1_Related[13]	= "Oom";
	b3kode1_Related[14]	= "Neef_geen_specificatie";
	b3kode1_Related[15]	= "Neef_Cousin";
	b3kode1_Related[16]	= "Neef_Nephew";
	b3kode1_Related[17]	= "Achterneef";
    b3kode1_Related[18]	= "Oudoom";
    b3kode1_Related[20]	= "Grootmoeder";
	b3kode1_Related[21]	= "Moeder";
	b3kode1_Related[22]	= "Zuster";
	b3kode1_Related[23]	= "Tante";
	b3kode1_Related[24]	= "Nicht_geen specificatie";
	b3kode1_Related[25]	= "Nicht_Cousin";
	b3kode1_Related[26]	= "Nicht_Niece";
	b3kode1_Related[27]	= "Achternicht";
	b3kode1_Related[28]	= "Oudtante";
	b3kode1_Related[30]	= "Kleinzoon";                         
	b3kode1_Related[40]	= "Kleindochter";                      
	b3kode1_Related[51]	= "Achterkleinzoon";
	b3kode1_Related[87]	= "Halfbroer";
	b3kode1_Related[88]	= "Halfzuster";
	b3kode1_Related[133] = "Kind (PK)";	
	b3kode1_Related[136] = "Kleinkind";	
	b3kode1_Related[138] = "Halfbroer of neef";	
	b3kode1_Related[139] = "Halfzuster of nicht";	
	b3kode1_Related[156] = "Sibling";
	b3kode1_Related[158] = "Ouder";
	b3kode1_Related[162] = "Halfsibling";
	b3kode1_Related[164] = "Grootouder";
	
    
// B3kode1 - Marriable (The head can marry any of these persons, provided the sex is different. This is already checked by the civil servant who concludes the marriage way back)
    
  	b3kode1_Marriable[1]	= "Hoofd";  // The existence of this entry says that a (non blood related) headSuccessor can marry the (previous) head                
  	b3kode1_Marriable[2]	= "Echtgenote Hoofd";                
    b3kode1_Marriable[5]	= "Pleegzoon";
    b3kode1_Marriable[6]	= "Pleegdochter";
	b3kode1_Marriable[7]	= null;
	b3kode1_Marriable[8]	= "Stiefzoon";
	b3kode1_Marriable[9]	= "Stiefdochter";
	b3kode1_Marriable[17]	= "Achterneef";
    b3kode1_Marriable[19]	= null;
	b3kode1_Marriable[27]	= "Achternicht";
	b3kode1_Marriable[29]	= null;
	b3kode1_Marriable[41]	= "Commensaal";
	b3kode1_Marriable[42]	= "In Dienst";
	b3kode1_Marriable[43]	= "Huishoudster";
	b3kode1_Marriable[44]	= "Bestedeling";
	b3kode1_Marriable[45]	= "Weeskind";
	b3kode1_Marriable[46]	= "Baboe";
	b3kode1_Marriable[47]	= "Goede Bekende";
	b3kode1_Marriable[48]	= "Inwonend";
	b3kode1_Marriable[49]	= "Pupil";
	b3kode1_Marriable[50]	= null;
	b3kode1_Marriable[53]	= "Schoonzoon";
	b3kode1_Marriable[54]	= "Schoondochter";
	b3kode1_Marriable[55]	= "Buurvrouw";
	b3kode1_Marriable[56]	= "Kennis (vrouw)";
	b3kode1_Marriable[57]	= "Buurman";
	b3kode1_Marriable[58]	= "Kennis (man)";
	b3kode1_Marriable[59]	= "Bekende";
	b3kode1_Marriable[60]	= "Schoongrootvader";
	b3kode1_Marriable[61]	= "Schoonvader";
	b3kode1_Marriable[62]	= "Aangetrouwde Stiefvader";
	b3kode1_Marriable[63]	= "Schoonbroer (zwager)";
	b3kode1_Marriable[64]	= "Schoonneef (geen specificatie)";
	b3kode1_Marriable[65]	= "Schoonneef (Cousin)";
	b3kode1_Marriable[66]	= "Schoonneef (Nephew)";
	b3kode1_Marriable[67]	= "Aangetrouwde Achterneef";
	b3kode1_Marriable[68]	= "Schoonoom";
	b3kode1_Marriable[69]	= "Aangetrouwde Oudoom";
	b3kode1_Marriable[70]	= "Schoongrootmoeder";
	b3kode1_Marriable[71]	= "Schoonmoeder";
	b3kode1_Marriable[72]	= "Aangetrouwde Stiefmoeder";
    b3kode1_Marriable[73]	= "Schoonzus";
    b3kode1_Marriable[74]	= "Schoonnicht (geen specificatie)";
    b3kode1_Marriable[75]	= "Schoonnicht (cousin)";
    b3kode1_Marriable[76]	= "Schoonnicht (niece)";
    b3kode1_Marriable[77]	= "Aangetrouwde Achternicht";
    b3kode1_Marriable[78]	= "Schoontante";
    b3kode1_Marriable[79]	= "Aangetrouwde Oudtante";
    b3kode1_Marriable[81]	= "Stiefvader";
    b3kode1_Marriable[82]	= "Stiefmoder";
    b3kode1_Marriable[83]	= "Stiefschoonzoon";
    b3kode1_Marriable[84]	= "Stiefschoondochter";
    b3kode1_Marriable[85]	= "Stiefbroer";
    b3kode1_Marriable[86]	= "Stiefzuster";
    b3kode1_Marriable[89]	= null;
    b3kode1_Marriable[90]	= "Geen verwantschap";
	b3kode1_Marriable[91]	= "Kostganger";
	b3kode1_Marriable[92]	= "Bediende";
	b3kode1_Marriable[93]	= "Dienstbode";
	b3kode1_Marriable[94]	= "Knecht";
	b3kode1_Marriable[95]	= "Meid";
	b3kode1_Marriable[96]	= "Gouvernante";
	b3kode1_Marriable[97]	= "Gezelschapsdame";
	b3kode1_Marriable[98]	= "Log, ?????";
	b3kode1_Marriable[99]	= "Anders  -> bijzonderheden";
    
	
	// Forbidden marriages
	
	
	b3kode1_No_Marriage_Allowed[ZOON] =          new int[] {DOCHTER, GROOTMOEDER, MOEDER, ZUSTER, TANTE, KLEINDOCHTER}; 
	b3kode1_No_Marriage_Allowed[DOCHTER] =       new int[] {ZOON,    GROOTVADER,  VADER,  BROER,  OOM,   KLEINZOON}; 
	
	b3kode1_No_Marriage_Allowed[GROOTVADER] =    new int[] {DOCHTER, ZUSTER}; 
	b3kode1_No_Marriage_Allowed[GROOTMOEDER] =   new int[] {ZOON,    BROER}; 
	
	b3kode1_No_Marriage_Allowed[VADER] =         new int[] {DOCHTER, ZUSTER, KLEINDOCHTER}; 
	b3kode1_No_Marriage_Allowed[MOEDER] =        new int[] {ZOON,    BROER,  KLEINZOON}; 

	b3kode1_No_Marriage_Allowed[BROER] =         new int[] {DOCHTER, GROOTMOEDER, MOEDER, ZUSTER, TANTE, KLEINDOCHTER}; 
	b3kode1_No_Marriage_Allowed[ZUSTER] =        new int[] {ZOON,    GROOTVADER,  VADER,  BROER,  OOM,   KLEINZOON}; 

	b3kode1_No_Marriage_Allowed[OOM] =           new int[] {DOCHTER, ZUSTER}; 
	b3kode1_No_Marriage_Allowed[TANTE] =         new int[] {ZOON,    BROER}; 
	
	b3kode1_No_Marriage_Allowed[KLEINZOON] =     new int[] {DOCHTER, ZUSTER, MOEDER}; 
	b3kode1_No_Marriage_Allowed[KLEINDOCHTER] =  new int[] {ZOON,    BROER,  VADER}; 
	
			
    // B3kode2
    
    b3kode2[1] = "Ongehuwd";
    b3kode2[2] = "Weduwnaar/weduwe";
    b3kode2[3] = "Gescheiden";
    b3kode2[4] = null;
    b3kode2[5] = "Gehuwd";
    b3kode2[6] = "Onbekend";
    b3kode2[7] = null;
    b3kode2[8] = null;
    b3kode2[9] = "Onbekend -> op onbekend later moment gehuwd";
    	

    // Implicit Heads - new codes
    // To read this, pretend you are the old head
    // The question to ask for each relation is:
    // My ZOON is the xxxx of the new Husband  (Answer xxx = STIEFZOON)
    
    
    
    // 110 - New husband
     
    
    newHusband[HOOFD]  					= ECHTGENOTE_HOOFD;          // 1    
    newHusband[ECHTGENOTE_HOOFD] 		= ECHTGENOTE_HOOFD;			 // 2	
	newHusband[ZOON] 					= STIEFZOON;     			 // 3
	newHusband[DOCHTER] 				= STIEFDOCHTER;  			 //	4
	newHusband[PLEEGZOON] 				= ONBEKEND_WAS_PLEEGZOON;    //	5
	newHusband[PLEEGDOCHTER]            = ONBEKEND_WAS_PLEEGDOCHTER; // 6
	
	newHusband[STIEFZOON] 				= STIEFZOON;                 //	8
	newHusband[STIEFDOCHTER] 			= STIEFDOCHTER;  			 // 9
	
	newHusband[GROOTVADER] 				= VERWANTE;          		 // 10
	newHusband[VADER]      				= VERWANTE;          		 // 11
	newHusband[BROER] 				    = VERWANTE;          		 // 12
	newHusband[OOM]                     = VERWANTE;          		 // 13
	newHusband[NEEF_GEEN_SPECIFICATIE]  = VERWANTE;          		 // 14
	newHusband[NEEF_COUSIN]             = VERWANTE;          		 // 15
	newHusband[NEEF_NEPHEW]             = VERWANTE;          		 // 16
	newHusband[ACHTERNEEF]              = VERWANTE;          		 // 17
	newHusband[OUDOOM]                  = VERWANTE;          		 // 18
	
	newHusband[GROOTMOEDER]             = VERWANTE;          		 // 20
	newHusband[MOEDER]                  = VERWANTE;          		 // 21
	newHusband[ZUSTER]                  = VERWANTE;          		 // 22
	newHusband[TANTE]                   = VERWANTE;          		 // 23
	newHusband[NICHT_GEEN_SPECIFICATIE] = VERWANTE;          		 // 24
	newHusband[NICHT_COUSIN]            = VERWANTE;          		 // 25
	newHusband[NICHT_NIECE]             = VERWANTE;          		 // 26
	newHusband[ACHTERNICHT]             = VERWANTE;          		 // 27
	newHusband[OUDTANTE]                = VERWANTE;          		 // 28
	
	newHusband[KLEINZOON]               = KLEINZOON;                 // 30
	
	newHusband[KLEINDOCHTER]            = KLEINDOCHTER;              // 40
	
	newHusband[ACHTERKLEINZOON]         = ACHTERKLEINZOON;           // 51
	newHusband[ACHTERKLEINDOCHTER]      = ACHTERKLEINDOCHTER;        // 52
	newHusband[SCHOONZOON]              = SCHOONZOON;                // 53
	newHusband[SCHOONDOCHTER]           = SCHOONDOCHTER;             // 54
	
	newHusband[SCHOONGROOTVADER]              = SCHOONGROOTVADER;          // 60
	newHusband[SCHOONVADER]                   = SCHOONVADER;               // 61
	newHusband[AANGETROUWDE_STIEFVADER]       = AANGETROUWDE_STIEFVADER;   // 62
	newHusband[SCHOONBROER_ZWAGER]            = SCHOONBROER_ZWAGER;        // 63
	newHusband[SCHOONNEEF_GEEN_SPECIFICATIE]  = SCHOONNEEF_GEEN_SPECIFICATIE; // 64
	newHusband[SCHOONNEEF_COUSIN]             = SCHOONNEEF_COUSIN;         // 65
	newHusband[SCHOONNEEF_NEPHEW]             = SCHOONNEEF_NEPHEW;         // 66
	newHusband[AANGETROUWDE_ACHTERNEEF]       = AANGETROUWDE_ACHTERNEEF;   // 67 
	newHusband[SCHOONOOM]                     = SCHOONOOM;                 // 68
	newHusband[AANGETROUWDE_OUDOOM]           = AANGETROUWDE_OUDOOM;       // 69
	
	newHusband[SCHOONGROOTMOEDER]             = SCHOONGROOTMOEDER;         // 70
	newHusband[SCHOONMOEDER]                  = SCHOONMOEDER;              // 71
	newHusband[AANGETROUWDE_STIEFMOEDER]      = AANGETROUWDE_STIEFMOEDER;  // 72
	newHusband[SCHOONZUS]                     = SCHOONZUS;                 // 73
	newHusband[SCHOONNICHT_GEEN_SPECIFICATIE] = SCHOONNICHT_GEEN_SPECIFICATIE; // 74
	newHusband[SCHOONNICHT_COUSIN]            = SCHOONNICHT_COUSIN;        // 75
	newHusband[SCHOONNICHT_NIECE]             = SCHOONNICHT_NIECE;         // 76
	newHusband[AANGETROUWDE_ACHTERNICHT]      = AANGETROUWDE_ACHTERNICHT;  // 77
	newHusband[SCHOONTANTE]                   = SCHOONTANTE;               // 78
	newHusband[AANGETROUWDE_OUDTANTE]         = AANGETROUWDE_OUDTANTE;     // 79
	
	newHusband[STIEFVADER]              = STIEFVADER;                // 81
	newHusband[STIEFMOEDER]             = STIEFMOEDER;               // 82
	newHusband[STIEFSCHOONZOON]         = STIEFSCHOONZOON;           // 83
	newHusband[STIEFSCHOONDOCHTER]      = STIEFSCHOONDOCHTER;        // 84
	newHusband[STIEFBROER]              = STIEFBROER;                // 85
	newHusband[STIEFZUSTER]             = STIEFZUSTER;               // 86
	newHusband[HALFBROER]               = HALFBROER;                 // 87
	newHusband[HALFZUSTER]              = HALFZUSTER;                // 88
	
	newHusband[BROER_OF_HALFBROER]      = VERWANTE;                  // 121
	newHusband[ZUSTER_OF_HALFZUSTER]    = VERWANTE;                  // 122
	newHusband[ZOON_OF_STIEFZOON]       = ZOON_OF_STIEFZOON;         // 123
	newHusband[DOCHTER_OF_STIEFDOCHTER] = DOCHTER_OF_STIEFDOCHTER;   // 124
	newHusband[ONBEKEND_WAS_PLEEGZOON]  = ONBEKEND_WAS_PLEEGZOON;    // 125
	newHusband[ONBEKEND_WAS_PLEEGDOCHTER] = ONBEKEND_WAS_PLEEGDOCHTER; // 126
	newHusband[STIEF_OF_HALF_OF_BROER]    = VERWANTE;                  // 128
	newHusband[STIEF_OF_HALF_OF_ZUSTER]   = VERWANTE;                  // 129
	newHusband[VERWANTE]                  = VERWANTE;                  // 131
	newHusband[VERWANTE_VAN_VORIG_HOOFD]  = VERWANTE_VAN_VORIG_HOOFD;  // 132
	newHusband[HALFBROER_OF_STIEFBROER]     = VERWANTE;                // 141
	newHusband[HALFZUSTER_OF_STIEFZUSTER]   = VERWANTE;                // 142
	newHusband[VADER_OF_STIEFVADER]     = VERWANTE;                    // 148
	newHusband[MOEDER_OF_STIEFMOEDER]   = VERWANTE;                    // 149
	
 // 111 - Married son - 
    
    marriedSon[HOOFD]  					= -VADER;                                  // 1    
    marriedSon[ECHTGENOTE_HOOFD] 		= MOEDER + 1000 * MOEDER_OF_STIEFMOEDER;   // 2 	
	marriedSon[ZOON] 					= BROER  + 1000 * STIEF_OF_HALF_OF_BROER;  // 3
	marriedSon[DOCHTER] 				= ZUSTER + 1000 * STIEF_OF_HALF_OF_ZUSTER; // 4
	marriedSon[PLEEGZOON] 				= BROER  + 1000 * STIEF_OF_HALF_OF_BROER;  // 5
	marriedSon[PLEEGDOCHTER]            = ZUSTER + 1000 * STIEF_OF_HALF_OF_ZUSTER; // 6
	
	marriedSon[STIEFZOON] 				= STIEFZOON;                 //	8
	marriedSon[STIEFDOCHTER] 			= STIEFDOCHTER;  			 // 9
	
	marriedSon[GROOTVADER] 				= VERWANTE;          		 // 10
	marriedSon[VADER]      				= GROOTVADER;          		 // 11
	marriedSon[BROER] 				    = OOM;               		 // 12
	marriedSon[OOM]                     = OUDOOM;           		 // 13
	marriedSon[NEEF_GEEN_SPECIFICATIE]  = ACHTERNEEF;        		 // 14
	marriedSon[NEEF_COUSIN]             = ACHTERNEEF;        		 // 15
	marriedSon[NEEF_NEPHEW]             = ACHTERNEEF;        		 // 16
	marriedSon[ACHTERNEEF]              = VERWANTE;          		 // 17
	marriedSon[OUDOOM]                  = VERWANTE;          		 // 18
	
	marriedSon[GROOTMOEDER]             = VERWANTE;          		 // 20
	marriedSon[MOEDER]                  = GROOTMOEDER;       		 // 21
	marriedSon[ZUSTER]                  = TANTE;             		 // 22
	marriedSon[TANTE]                   = OUDTANTE;          		 // 23
	marriedSon[NICHT_GEEN_SPECIFICATIE] = ACHTERNICHT;       		 // 24
	marriedSon[NICHT_COUSIN]            = ACHTERNICHT;       		 // 25
	marriedSon[NICHT_NIECE]             = ACHTERNICHT;       		 // 26
	marriedSon[ACHTERNICHT]             = VERWANTE;          		 // 27
	marriedSon[OUDTANTE]                = VERWANTE;          		 // 28
	
	marriedSon[KLEINZOON]               = ZOON + 1000 * NEEF_NEPHEW; // 30
	
	marriedSon[KLEINDOCHTER]            = DOCHTER + 1000 * NICHT_NIECE;// 40
	
	marriedSon[ACHTERKLEINZOON]         = KLEINZOON;                 // 51
	marriedSon[ACHTERKLEINDOCHTER]      = KLEINDOCHTER;              // 52
	marriedSon[SCHOONZOON]              = SCHOONBROER_ZWAGER;        // 53
	marriedSon[SCHOONDOCHTER]           = ECHTGENOTE_HOOFD + 1000 * SCHOONZUS;// 54
	
	marriedSon[SCHOONGROOTVADER]              = VERWANTE;                  // 60
	marriedSon[SCHOONVADER]                   = SCHOONGROOTVADER;          // 61
	marriedSon[AANGETROUWDE_STIEFVADER]       = GROOTVADER;                // 62
	marriedSon[SCHOONBROER_ZWAGER]            = SCHOONOOM;                 // 63
	marriedSon[SCHOONNEEF_GEEN_SPECIFICATIE]  = AANGETROUWDE_ACHTERNEEF;   // 64
	marriedSon[SCHOONNEEF_COUSIN]             = AANGETROUWDE_ACHTERNEEF;   // 65
	marriedSon[SCHOONNEEF_NEPHEW]             = SCHOONNEEF_COUSIN;         // 66
	marriedSon[AANGETROUWDE_ACHTERNEEF]       = VERWANTE;                  // 67 
	marriedSon[SCHOONOOM]                     = AANGETROUWDE_OUDOOM;       // 68
	marriedSon[AANGETROUWDE_OUDOOM]           = VERWANTE;                  // 69
	
	marriedSon[SCHOONGROOTMOEDER]             = VERWANTE;                  // 70
	marriedSon[SCHOONMOEDER]                  = SCHOONGROOTMOEDER;         // 71
	marriedSon[AANGETROUWDE_STIEFMOEDER]      = GROOTMOEDER;               // 72
	marriedSon[SCHOONZUS]                     = SCHOONTANTE;               // 73
	marriedSon[SCHOONNICHT_GEEN_SPECIFICATIE] = AANGETROUWDE_ACHTERNICHT;  // 74
	marriedSon[SCHOONNICHT_COUSIN]            = AANGETROUWDE_ACHTERNICHT;  // 75
	marriedSon[SCHOONNICHT_NIECE]             = SCHOONNICHT_COUSIN;        // 76
	marriedSon[AANGETROUWDE_ACHTERNICHT]      = VERWANTE;                  // 77
	marriedSon[SCHOONTANTE]                   = AANGETROUWDE_OUDTANTE;     // 78
	marriedSon[AANGETROUWDE_OUDTANTE]         = VERWANTE;                  // 79
	
	marriedSon[STIEFVADER]              = VERWANTE;                  // 81
	marriedSon[STIEFMOEDER]             = VERWANTE;                  // 82
	marriedSon[STIEFSCHOONZOON]         = SCHOONBROER_ZWAGER;        // 83
	marriedSon[STIEFSCHOONDOCHTER]      = SCHOONZUS;                 // 84
	marriedSon[STIEFBROER]              = OOM;                       // 85
	marriedSon[STIEFZUSTER]             = TANTE;                     // 86
	marriedSon[HALFBROER]               = OOM;                       // 87
	marriedSon[HALFZUSTER]              = TANTE;                     // 88

	marriedSon[BROER_OF_HALFBROER]      = OOM;                       // 121
	marriedSon[ZUSTER_OF_HALFZUSTER]    = TANTE;                     // 122
	marriedSon[ZOON_OF_STIEFZOON]       = STIEF_OF_HALF_OF_BROER;    // 123
	marriedSon[DOCHTER_OF_STIEFDOCHTER] = STIEF_OF_HALF_OF_ZUSTER;   // 124
	marriedSon[ONBEKEND_WAS_PLEEGZOON]  = ONBEKEND_WAS_PLEEGZOON;    // 125 X
	marriedSon[ONBEKEND_WAS_PLEEGDOCHTER] = ONBEKEND_WAS_PLEEGDOCHTER; // 126 X
	marriedSon[STIEF_OF_HALF_OF_BROER]    = OOM;                       // 128
	marriedSon[STIEF_OF_HALF_OF_ZUSTER]   = TANTE;                     // 129
	marriedSon[VERWANTE]                  = VERWANTE;                  // 131
	marriedSon[VERWANTE_VAN_VORIG_HOOFD]  = VERWANTE_VAN_VORIG_HOOFD;  // 132
	marriedSon[HALFBROER_OF_STIEFBROER]     = VERWANTE;                // 141 X
	marriedSon[HALFZUSTER_OF_STIEFZUSTER]   = VERWANTE;                // 142 X
	marriedSon[VADER_OF_STIEFVADER]     = VERWANTE;                    // 148 X
	marriedSon[MOEDER_OF_STIEFMOEDER]   = VERWANTE;                    // 149 X

	
	
 // 112 - Brother -
    
    brother[HOOFD]  				= -BROER;                    // 1    
    brother[ECHTGENOTE_HOOFD] 		= SCHOONZUS;		         // 2	
	brother[ZOON] 					= NEEF_NEPHEW;     			 // 3
	brother[DOCHTER] 				= NICHT_NIECE;  			 //	4
	brother[PLEEGZOON] 				= ONBEKEND_WAS_PLEEGZOON;    //	5
	brother[PLEEGDOCHTER]           = ONBEKEND_WAS_PLEEGDOCHTER; // 6
	
	brother[STIEFZOON] 				= NEEF_NEPHEW;               //	8
	brother[STIEFDOCHTER] 			= NICHT_NIECE;  			 // 9
	
	brother[GROOTVADER] 			= GROOTVADER;        		 // 10
	brother[VADER]      			= VADER;             		 // 11
	brother[BROER] 				    = BROER + 1000 * IMPLICIET_HOOFD_BROER; // 12
	brother[OOM]                     = OOM;               		 // 13
	brother[NEEF_GEEN_SPECIFICATIE]  = ZOON + 1000 * NEEF_GEEN_SPECIFICATIE; // 14
	brother[NEEF_COUSIN]             = NEEF_COUSIN;   		     // 15 xxx
	brother[NEEF_NEPHEW]             = ZOON + 1000 * NEEF_NEPHEW; // 16 xxx
	brother[ACHTERNEEF]              = ACHTERNEEF;        		 // 17 xxx
	brother[OUDOOM]                  = OUDOOM;           		 // 18
	
	brother[GROOTMOEDER]             = GROOTMOEDER;        		 // 20
	brother[MOEDER]                  = MOEDER;          		 // 21
	brother[ZUSTER]                  = ZUSTER;          		 // 22
	brother[TANTE]                   = TANTE;           		 // 23
	brother[NICHT_GEEN_SPECIFICATIE] = DOCHTER + 1000 * NICHT_GEEN_SPECIFICATIE; // 24
	brother[NICHT_COUSIN]            = NICHT_COUSIN;      		 // 25
	brother[NICHT_NIECE]             = DOCHTER + 1000 * NICHT_NIECE; //26
	brother[ACHTERNICHT]             = ACHTERNICHT;        		 // 27
	brother[OUDTANTE]                = OUDTANTE;          		 // 28
	
	brother[KLEINZOON]               = ACHTERNEEF;                // 30
	
	brother[KLEINDOCHTER]            = ACHTERNICHT;               // 40
	
	brother[ACHTERKLEINZOON]         = VERWANTE;                  // 51
	brother[ACHTERKLEINDOCHTER]      = VERWANTE;      			  // 52
	brother[SCHOONZOON]              = SCHOONNEEF_NEPHEW;         // 53
	brother[SCHOONDOCHTER]           = SCHOONNICHT_NIECE;         // 54
	
	brother[SCHOONGROOTVADER]              = VERWANTE;            // 60
	brother[SCHOONVADER]                   = VERWANTE;            // 61
	brother[AANGETROUWDE_STIEFVADER]       = VERWANTE;  		  // 62
	brother[SCHOONBROER_ZWAGER]            = VERWANTE;        	  // 63
	brother[SCHOONNEEF_GEEN_SPECIFICATIE]  = VERWANTE; 			  // 64
	brother[SCHOONNEEF_COUSIN]             = VERWANTE;            // 65
	brother[SCHOONNEEF_NEPHEW]             = VERWANTE;         	  // 66
	brother[AANGETROUWDE_ACHTERNEEF]       = VERWANTE;  		  // 67 
	brother[SCHOONOOM]                     = VERWANTE;            // 68
	brother[AANGETROUWDE_OUDOOM]           = VERWANTE;            // 69
	
	brother[SCHOONGROOTMOEDER]             = VERWANTE;            // 70
	brother[SCHOONMOEDER]                  = VERWANTE;            // 71
	brother[AANGETROUWDE_STIEFMOEDER]      = VERWANTE;  		  // 72
	brother[SCHOONZUS]                     = VERWANTE;            // 73
	brother[SCHOONNICHT_GEEN_SPECIFICATIE] = VERWANTE; 			  // 74
	brother[SCHOONNICHT_COUSIN]            = VERWANTE;            // 75
	brother[SCHOONNICHT_NIECE]             = VERWANTE;        	  // 76
	brother[AANGETROUWDE_ACHTERNICHT]      = VERWANTE;  		  // 77
	brother[SCHOONTANTE]                   = VERWANTE;            // 78
	brother[AANGETROUWDE_OUDTANTE]         = VERWANTE;     		  // 79
	
	brother[STIEFVADER]              = VADER + 1000 * VADER_OF_STIEFVADER;      // 81
	brother[STIEFMOEDER]             = MOEDER + 1000 * MOEDER_OF_STIEFMOEDER;   // 82
	brother[STIEFSCHOONZOON]         = VERWANTE;                  // 83
	brother[STIEFSCHOONDOCHTER]      = VERWANTE;                  // 84
	brother[STIEFBROER]              = BROER  + 1000 * STIEF_OF_HALF_OF_BROER;    // 85
	brother[STIEFZUSTER]             = ZUSTER + 1000 * STIEF_OF_HALF_OF_ZUSTER;   // 86
	brother[HALFBROER]               = BROER  + 1000 * STIEF_OF_HALF_OF_BROER;    // 87
	brother[HALFZUSTER]              = ZUSTER + 1000 * STIEF_OF_HALF_OF_ZUSTER;   // 88
	
	brother[BROER_OF_HALFBROER]      = BROER_OF_HALFBROER;        // 121
	brother[ZUSTER_OF_HALFZUSTER]    = ZUSTER_OF_HALFZUSTER;      // 122
	brother[ZOON_OF_STIEFZOON]       = NEEF_NEPHEW;               // 123
	brother[DOCHTER_OF_STIEFDOCHTER] = NICHT_NIECE;               // 124
	brother[ONBEKEND_WAS_PLEEGZOON]  = ONBEKEND_WAS_PLEEGZOON;    // 125
	brother[ONBEKEND_WAS_PLEEGDOCHTER] = ONBEKEND_WAS_PLEEGDOCHTER; // 126
	brother[STIEF_OF_HALF_OF_BROER]    = STIEF_OF_HALF_OF_BROER;    // 128
	brother[STIEF_OF_HALF_OF_ZUSTER]   = STIEF_OF_HALF_OF_ZUSTER;   // 129
	brother[VERWANTE]                  = VERWANTE;                  // 131
	brother[VERWANTE_VAN_VORIG_HOOFD]  = VERWANTE_VAN_VORIG_HOOFD;  // 132
	brother[HALFBROER_OF_STIEFBROER]   = HALFBROER_OF_STIEFBROER;   // 141
	brother[HALFZUSTER_OF_STIEFZUSTER] = HALFZUSTER_OF_STIEFZUSTER; // 142
	brother[VADER_OF_STIEFVADER]     = VADER_OF_STIEFVADER;         // 148
	brother[MOEDER_OF_STIEFMOEDER]   = MOEDER_OF_STIEFMOEDER;       // 149

	
	
 // 113 - Widow -
    
    widow[HOOFD]  					= ECHTGENOTE_HOOFD;          // 1    
    widow[ECHTGENOTE_HOOFD] 		= IMPLICIET_HOOFD_WEDUWE_OF_MAN_WEG; // 2	
	widow[ZOON] 					= ZOON    + 1000 * ZOON_OF_STIEFZOON;	 // 3
	widow[DOCHTER] 				    = DOCHTER + 1000 * DOCHTER_OF_STIEFDOCHTER; //	4
	widow[PLEEGZOON] 				= ONBEKEND_WAS_PLEEGZOON;    //	5
	widow[PLEEGDOCHTER]             = ONBEKEND_WAS_PLEEGDOCHTER; // 6
	
	widow[STIEFZOON] 				= ZOON    + 1000 * ZOON_OF_STIEFZOON; // 8
	widow[STIEFDOCHTER] 			= DOCHTER + 1000 * DOCHTER_OF_STIEFDOCHTER; // 9
	
	widow[GROOTVADER] 				= SCHOONGROOTVADER;    		 // 10
	widow[VADER]      				= SCHOONVADER;       		 // 11
	widow[BROER] 				    = SCHOONBROER_ZWAGER;		 // 12
	widow[OOM]                      = SCHOONOOM;         		 // 13
	widow[NEEF_GEEN_SPECIFICATIE]   = SCHOONNEEF_GEEN_SPECIFICATIE; // 14
	widow[NEEF_COUSIN]              = SCHOONNEEF_COUSIN;   		 // 15
	widow[NEEF_NEPHEW]              = SCHOONNEEF_NEPHEW;   		 // 16
	widow[ACHTERNEEF]               = AANGETROUWDE_ACHTERNEEF;   // 17
	widow[OUDOOM]                   = AANGETROUWDE_OUDOOM; 		 // 18
	
	widow[GROOTMOEDER]             = SCHOONGROOTMOEDER;    		 // 20
	widow[MOEDER]                  = SCHOONMOEDER;         		 // 21
	widow[ZUSTER]                  = SCHOONZUS;          		 // 22
	widow[TANTE]                   = SCHOONTANTE;          		 // 23
	widow[NICHT_GEEN_SPECIFICATIE] = SCHOONNICHT_GEEN_SPECIFICATIE;	 // 24
	widow[NICHT_COUSIN]            = SCHOONNICHT_COUSIN;          	 // 25
	widow[NICHT_NIECE]             = SCHOONNICHT_NIECE;          	 // 26
	widow[ACHTERNICHT]             = AANGETROUWDE_ACHTERNICHT;       // 27
	widow[OUDTANTE]                = AANGETROUWDE_OUDTANTE;          // 28
	
	widow[KLEINZOON]               = KLEINZOON;                 // 30
	
	widow[KLEINDOCHTER]            = KLEINDOCHTER;              // 40
	
	widow[ACHTERKLEINZOON]         = ACHTERKLEINZOON;           // 51
	widow[ACHTERKLEINDOCHTER]      = ACHTERKLEINDOCHTER;        // 52
	widow[SCHOONZOON]              = SCHOONZOON;                // 53
	widow[SCHOONDOCHTER]           = SCHOONDOCHTER;             // 54
	
	widow[SCHOONGROOTVADER]              = GROOTVADER;          // 60
	widow[SCHOONVADER]                   = VADER;               // 61
	widow[AANGETROUWDE_STIEFVADER]       = STIEFVADER;		    // 62
	widow[SCHOONBROER_ZWAGER]            = BROER;        		// 63
	widow[SCHOONNEEF_GEEN_SPECIFICATIE]  = NEEF_GEEN_SPECIFICATIE; // 64
	widow[SCHOONNEEF_COUSIN]             = NEEF_COUSIN;         // 65
	widow[SCHOONNEEF_NEPHEW]             = NEEF_NEPHEW;         // 66
	widow[AANGETROUWDE_ACHTERNEEF]       = ACHTERNEEF;   		// 67 
	widow[SCHOONOOM]                     = OOM;                 // 68
	widow[AANGETROUWDE_OUDOOM]           = OUDOOM;       		// 69
	
	widow[SCHOONGROOTMOEDER]             = GROOTMOEDER;         // 70
	widow[SCHOONMOEDER]                  = MOEDER;              // 71
	widow[AANGETROUWDE_STIEFMOEDER]      = STIEFMOEDER; 	    // 72
	widow[SCHOONZUS]                     = ZUSTER;              // 73
	widow[SCHOONNICHT_GEEN_SPECIFICATIE] = NICHT_GEEN_SPECIFICATIE; // 74
	widow[SCHOONNICHT_COUSIN]            = NICHT_COUSIN;        // 75
	widow[SCHOONNICHT_NIECE]             = NICHT_NIECE;         // 76
	widow[AANGETROUWDE_ACHTERNICHT]      = ACHTERNICHT;  		// 77
	widow[SCHOONTANTE]                   = TANTE;               // 78
	widow[AANGETROUWDE_OUDTANTE]         = OUDTANTE;     		// 79
	
	widow[STIEFVADER]              = SCHOONVADER;               // 81
	widow[STIEFMOEDER]             = SCHOONMOEDER;              // 82
	widow[STIEFSCHOONZOON]         = STIEFSCHOONZOON;           // 83
	widow[STIEFSCHOONDOCHTER]      = STIEFSCHOONDOCHTER;        // 84
	widow[STIEFBROER]              = SCHOONBROER_ZWAGER;        // 85
	widow[STIEFZUSTER]             = SCHOONZUS;                 // 86
	widow[HALFBROER]               = SCHOONBROER_ZWAGER;        // 87
	widow[HALFZUSTER]              = SCHOONZUS;                 // 88
	
	widow[BROER_OF_HALFBROER]      = SCHOONBROER_ZWAGER;        // 121
	widow[ZUSTER_OF_HALFZUSTER]    = SCHOONZUS;                 // 122
	widow[ZOON_OF_STIEFZOON]       = ZOON_OF_STIEFZOON;         // 123
	widow[DOCHTER_OF_STIEFDOCHTER] = DOCHTER_OF_STIEFDOCHTER;   // 124
	widow[ONBEKEND_WAS_PLEEGZOON]  = ONBEKEND_WAS_PLEEGZOON;    // 125
	widow[ONBEKEND_WAS_PLEEGDOCHTER] = ONBEKEND_WAS_PLEEGDOCHTER; // 126
	widow[STIEF_OF_HALF_OF_BROER]    = SCHOONBROER_ZWAGER;      // 128
	widow[STIEF_OF_HALF_OF_ZUSTER]   = SCHOONZUS;               // 129
	widow[VERWANTE]                  = VERWANTE;                // 131
	widow[VERWANTE_VAN_VORIG_HOOFD]  = VERWANTE_VAN_VORIG_HOOFD; // 132
	widow[HALFBROER_OF_STIEFBROER]   = SCHOONBROER_ZWAGER;        // 141
	widow[HALFZUSTER_OF_STIEFZUSTER] = SCHOONZUS;                 // 142
	widow[VADER_OF_STIEFVADER]     = SCHOONVADER;               // 148
	widow[MOEDER_OF_STIEFMOEDER]   = SCHOONMOEDER;              // 149


	
 // 114 - Unmarried Son -
 //       Like married Son
	
 // 115 - sonInLaw -
    
    sonInLaw[HOOFD]  				= -SCHOONVADER;             // 1    
    sonInLaw[ECHTGENOTE_HOOFD] 		= SCHOONMOEDER;			    // 2	
	sonInLaw[ZOON] 					= SCHOONBROER_ZWAGER;       // 3
	sonInLaw[DOCHTER] 				= ECHTGENOTE_HOOFD + 1000 * SCHOONZUS; // 4
	sonInLaw[PLEEGZOON] 			= ONBEKEND_WAS_PLEEGZOON;   // 5
	sonInLaw[PLEEGDOCHTER]          = ONBEKEND_WAS_PLEEGDOCHTER; // 6
	
	sonInLaw[STIEFZOON] 			= SCHOONBROER_ZWAGER;       //	8
	sonInLaw[STIEFDOCHTER] 			= SCHOONZUS;     			// 9
	
	sonInLaw[GROOTVADER] 			= VERWANTE;          		 // 10
	sonInLaw[VADER]      			= SCHOONGROOTVADER;          // 11
	sonInLaw[BROER] 			    = SCHOONOOM;         		 // 12
	sonInLaw[OOM]                   = AANGETROUWDE_OUDOOM;       // 13
	sonInLaw[NEEF_GEEN_SPECIFICATIE] = AANGETROUWDE_ACHTERNEEF;  // 14
	sonInLaw[NEEF_COUSIN]           = AANGETROUWDE_ACHTERNEEF;   // 15
	sonInLaw[NEEF_NEPHEW]           = AANGETROUWDE_ACHTERNEEF;   // 16
	sonInLaw[ACHTERNEEF]            = VERWANTE;          		 // 17
	sonInLaw[OUDOOM]                = VERWANTE;          		 // 18
	
	sonInLaw[GROOTMOEDER]           = VERWANTE;          		 // 20
	sonInLaw[MOEDER]                = SCHOONGROOTMOEDER;         // 21
	sonInLaw[ZUSTER]                = SCHOONTANTE;          	 // 22
	sonInLaw[TANTE]                 = AANGETROUWDE_OUDTANTE;     // 23
	sonInLaw[NICHT_GEEN_SPECIFICATIE] = AANGETROUWDE_ACHTERNICHT; // 24
	sonInLaw[NICHT_COUSIN]          = AANGETROUWDE_ACHTERNICHT;  // 25
	sonInLaw[NICHT_NIECE]           = AANGETROUWDE_ACHTERNICHT;  // 26
	sonInLaw[ACHTERNICHT]           = VERWANTE;          		 // 27
	sonInLaw[OUDTANTE]              = VERWANTE;          		 // 28
	
	sonInLaw[KLEINZOON]               = ZOON + 1000 * NEEF_NEPHEW; // 30
	
	sonInLaw[KLEINDOCHTER]            = DOCHTER + 1000 * NICHT_NIECE; // 40
	
	sonInLaw[ACHTERKLEINZOON]         = KLEINZOON;               // 51
	sonInLaw[ACHTERKLEINDOCHTER]      = KLEINDOCHTER;            // 52
	sonInLaw[SCHOONZOON]              = IMPLICIET_HOOFD_SCHOONZOON; // 53
	sonInLaw[SCHOONDOCHTER]           = SCHOONZUS;               // 54
	
	sonInLaw[SCHOONGROOTVADER]              = VERWANTE;          // 60
	sonInLaw[SCHOONVADER]                   = SCHOONGROOTVADER;  // 61
	sonInLaw[AANGETROUWDE_STIEFVADER]       = GROOTVADER;        // 62 xxx
	sonInLaw[SCHOONBROER_ZWAGER]            = VERWANTE;          // 63
	sonInLaw[SCHOONNEEF_GEEN_SPECIFICATIE]  = VERWANTE;          // 64
	sonInLaw[SCHOONNEEF_COUSIN]             = VERWANTE;          // 65
	sonInLaw[SCHOONNEEF_NEPHEW]             = VERWANTE;          // 66
	sonInLaw[AANGETROUWDE_ACHTERNEEF]       = VERWANTE;          // 67 
	sonInLaw[SCHOONOOM]                     = VERWANTE;          // 68
	sonInLaw[AANGETROUWDE_OUDOOM]           = VERWANTE;;         // 69
	
	sonInLaw[SCHOONGROOTMOEDER]             = VERWANTE;;         // 70
	sonInLaw[SCHOONMOEDER]                  = SCHOONGROOTMOEDER; // 71
	sonInLaw[AANGETROUWDE_STIEFMOEDER]      = GROOTMOEDER;       // 72 xxx
	sonInLaw[SCHOONZUS]                     = VERWANTE;          // 73
	sonInLaw[SCHOONNICHT_GEEN_SPECIFICATIE] = VERWANTE;          // 74
	sonInLaw[SCHOONNICHT_COUSIN]            = VERWANTE;          // 75
	sonInLaw[SCHOONNICHT_NIECE]             = VERWANTE;          // 76
	sonInLaw[AANGETROUWDE_ACHTERNICHT]      = VERWANTE;          // 77
	sonInLaw[SCHOONTANTE]                   = VERWANTE;          // 78
	sonInLaw[AANGETROUWDE_OUDTANTE]         = VERWANTE;          // 79
	
	sonInLaw[STIEFVADER]              = SCHOONGROOTVADER;        // 81
	sonInLaw[STIEFMOEDER]             = SCHOONGROOTMOEDER;       // 82
	sonInLaw[STIEFSCHOONZOON]         = SCHOONBROER_ZWAGER;      // 83 xxx
	sonInLaw[STIEFSCHOONDOCHTER]      = SCHOONZUS;               // 84
	sonInLaw[STIEFBROER]              = SCHOONOOM;               // 85
	sonInLaw[STIEFZUSTER]             = SCHOONTANTE;             // 86
	sonInLaw[HALFBROER]               = SCHOONOOM;               // 87
	sonInLaw[HALFZUSTER]              = SCHOONTANTE;             // 88
	
	sonInLaw[BROER_OF_HALFBROER]      = SCHOONOOM;               // 121
	sonInLaw[ZUSTER_OF_HALFZUSTER]    = SCHOONTANTE;             // 122
	sonInLaw[ZOON_OF_STIEFZOON]       = SCHOONBROER_ZWAGER;      // 123
	sonInLaw[DOCHTER_OF_STIEFDOCHTER] = SCHOONZUS;               // 124
	sonInLaw[ONBEKEND_WAS_PLEEGZOON]  = ONBEKEND_WAS_PLEEGZOON;  // 125
	sonInLaw[ONBEKEND_WAS_PLEEGDOCHTER] = ONBEKEND_WAS_PLEEGDOCHTER; // 126
	sonInLaw[STIEF_OF_HALF_OF_BROER]    = SCHOONOOM;             // 128
	sonInLaw[STIEF_OF_HALF_OF_ZUSTER]   = SCHOONTANTE;           // 129
	sonInLaw[VERWANTE]                  = VERWANTE;              // 131
	sonInLaw[VERWANTE_VAN_VORIG_HOOFD]  = VERWANTE_VAN_VORIG_HOOFD; // 132
	sonInLaw[HALFBROER_OF_STIEFBROER]   = SCHOONOOM;               // 141
	sonInLaw[HALFZUSTER_OF_STIEFZUSTER] = SCHOONTANTE;             // 142
	sonInLaw[VADER_OF_STIEFVADER]     = VERWANTE;                // 148
	sonInLaw[MOEDER_OF_STIEFMOEDER]   = VERWANTE;                // 149

	
 // 116 - Daughter -
 // Like married son	
    
	
 // 117 - Brother in Law -
    
    brotherInLaw[HOOFD]  				= -SCHOONBROER_ZWAGER;       // 1    
    brotherInLaw[ECHTGENOTE_HOOFD] 		= SCHOONBROER_ZWAGER;		 // 2	
	brotherInLaw[ZOON] 					= NEEF_NEPHEW;     			 // 3
	brotherInLaw[DOCHTER] 				= NICHT_NIECE;  			 //	4
	brotherInLaw[PLEEGZOON] 			= ONBEKEND_WAS_PLEEGZOON;    //	5
	brotherInLaw[PLEEGDOCHTER]          = ONBEKEND_WAS_PLEEGDOCHTER; // 6
	
	brotherInLaw[STIEFZOON] 				= NEEF_NEPHEW;           //	8
	brotherInLaw[STIEFDOCHTER] 			= NICHT_NIECE;  			 // 9
	
	brotherInLaw[GROOTVADER] 				= SCHOONGROOTVADER;      // 10
	brotherInLaw[VADER]      				= SCHOONVADER;       	 // 11
	brotherInLaw[BROER] 				    = SCHOONBROER_ZWAGER;	 // 12
	brotherInLaw[OOM]                     = SCHOONOOM;         		 // 13
	brotherInLaw[NEEF_GEEN_SPECIFICATIE]  = ZOON + 1000 * SCHOONNEEF_GEEN_SPECIFICATIE;	// 14
	brotherInLaw[NEEF_COUSIN]             = SCHOONNEEF_COUSIN;   	 // 15 xxx
	brotherInLaw[NEEF_NEPHEW]             = ZOON + 1000 * SCHOONNEEF_NEPHEW; // 16
	brotherInLaw[ACHTERNEEF]              = AANGETROUWDE_ACHTERNEEF;         // 17
	brotherInLaw[OUDOOM]                  = AANGETROUWDE_OUDOOM;     // 18
	
	brotherInLaw[GROOTMOEDER]             = SCHOONGROOTMOEDER;       // 20
	brotherInLaw[MOEDER]                  = SCHOONMOEDER;          	 // 21
	brotherInLaw[ZUSTER]                  = SCHOONZUS;          	 // 22
	brotherInLaw[TANTE]                   = SCHOONTANTE;          	 // 23
	brotherInLaw[NICHT_GEEN_SPECIFICATIE] = DOCHTER + 1000 * SCHOONNICHT_GEEN_SPECIFICATIE; // 24
	brotherInLaw[NICHT_COUSIN]            = SCHOONNICHT_COUSIN;  	 // 25 xxx
	brotherInLaw[NICHT_NIECE]             = DOCHTER + 1000 * SCHOONNICHT_NIECE; // 26
	brotherInLaw[ACHTERNICHT]             = AANGETROUWDE_ACHTERNICHT; // 27
	brotherInLaw[OUDTANTE]                = AANGETROUWDE_OUDTANTE;   // 28
	
	brotherInLaw[KLEINZOON]               = ACHTERNEEF;              // 30
	
	brotherInLaw[KLEINDOCHTER]            = ACHTERNICHT;             // 40
	
	brotherInLaw[ACHTERKLEINZOON]         = VERWANTE;                // 51
	brotherInLaw[ACHTERKLEINDOCHTER]      = VERWANTE;                // 52
	brotherInLaw[SCHOONZOON]              = SCHOONNEEF_NEPHEW;       // 53
	brotherInLaw[SCHOONDOCHTER]           = SCHOONNICHT_NIECE;       // 54
	
	brotherInLaw[SCHOONGROOTVADER]              = VERWANTE;          // 60
	brotherInLaw[SCHOONVADER]                   = VERWANTE;          // 61
	brotherInLaw[AANGETROUWDE_STIEFVADER]       = VERWANTE;          // 62
	brotherInLaw[SCHOONBROER_ZWAGER]            = VERWANTE;          // 63
	brotherInLaw[SCHOONNEEF_GEEN_SPECIFICATIE]  = VERWANTE;          // 64
	brotherInLaw[SCHOONNEEF_COUSIN]             = VERWANTE;          // 65
	brotherInLaw[SCHOONNEEF_NEPHEW]             = VERWANTE;          // 66
	brotherInLaw[AANGETROUWDE_ACHTERNEEF]       = VERWANTE;          // 67 
	brotherInLaw[SCHOONOOM]                     = VERWANTE;          // 68
	brotherInLaw[AANGETROUWDE_OUDOOM]           = VERWANTE;          // 69
	
	brotherInLaw[SCHOONGROOTMOEDER]             = VERWANTE;          // 70
	brotherInLaw[SCHOONMOEDER]                  = VERWANTE;          // 71
	brotherInLaw[AANGETROUWDE_STIEFMOEDER]      = VERWANTE;          // 72
	brotherInLaw[SCHOONZUS]                     = VERWANTE;          // 73
	brotherInLaw[SCHOONNICHT_GEEN_SPECIFICATIE] = VERWANTE;          // 74
	brotherInLaw[SCHOONNICHT_COUSIN]            = VERWANTE;          // 75
	brotherInLaw[SCHOONNICHT_NIECE]             = VERWANTE;          // 76
	brotherInLaw[AANGETROUWDE_ACHTERNICHT]      = VERWANTE;          // 77
	brotherInLaw[SCHOONTANTE]                   = VERWANTE;          // 78
	brotherInLaw[AANGETROUWDE_OUDTANTE]         = VERWANTE;          // 79
	
	brotherInLaw[STIEFVADER]              = AANGETROUWDE_STIEFVADER; // 81
	brotherInLaw[STIEFMOEDER]             = AANGETROUWDE_STIEFMOEDER; // 82
	brotherInLaw[STIEFSCHOONZOON]         = VERWANTE;                // 83
	brotherInLaw[STIEFSCHOONDOCHTER]      = VERWANTE;                // 84
	brotherInLaw[STIEFBROER]              = SCHOONBROER_ZWAGER;      // 85
	brotherInLaw[STIEFZUSTER]             = SCHOONZUS;               // 86
	brotherInLaw[HALFBROER]               = SCHOONBROER_ZWAGER;      // 87
	brotherInLaw[HALFZUSTER]              = SCHOONZUS;               // 88
	
	brotherInLaw[BROER_OF_HALFBROER]      = SCHOONBROER_ZWAGER;      // 121
	brotherInLaw[ZUSTER_OF_HALFZUSTER]    = SCHOONZUS;               // 122
	brotherInLaw[ZOON_OF_STIEFZOON]       = NEEF_NEPHEW;             // 123
	brotherInLaw[DOCHTER_OF_STIEFDOCHTER] = NICHT_NIECE;             // 124
	brotherInLaw[ONBEKEND_WAS_PLEEGZOON]  = ONBEKEND_WAS_PLEEGZOON;  // 125
	brotherInLaw[ONBEKEND_WAS_PLEEGDOCHTER] = ONBEKEND_WAS_PLEEGDOCHTER; // 126
	brotherInLaw[STIEF_OF_HALF_OF_BROER]    = SCHOONBROER_ZWAGER;   // 128
	brotherInLaw[STIEF_OF_HALF_OF_ZUSTER]   = SCHOONZUS;             // 129
	brotherInLaw[VERWANTE]                  = VERWANTE;              // 131
	brotherInLaw[VERWANTE_VAN_VORIG_HOOFD]  = VERWANTE_VAN_VORIG_HOOFD; // 132
	brotherInLaw[HALFBROER_OF_STIEFBROER]   = SCHOONBROER_ZWAGER;      // 141
	brotherInLaw[HALFZUSTER_OF_STIEFZUSTER] = SCHOONZUS;               // 142
	brotherInLaw[VADER_OF_STIEFVADER]     = SCHOONVADER;             // 148
	brotherInLaw[MOEDER_OF_STIEFMOEDER]   = SCHOONMOEDER;            // 149


	
 // 118 - Sister
    
    sister[HOOFD]  					= -BROER;                    // 1    
    sister[ECHTGENOTE_HOOFD] 		= SCHOONZUS;		         // 2	
	sister[ZOON] 					= NEEF_NEPHEW;     			 // 3
	sister[DOCHTER] 				= NICHT_NIECE;  			 //	4
	sister[PLEEGZOON] 				= ONBEKEND_WAS_PLEEGZOON;    //	5
	sister[PLEEGDOCHTER]            = ONBEKEND_WAS_PLEEGDOCHTER; // 6
	
	sister[STIEFZOON] 				= NEEF_NEPHEW;               //	8
	sister[STIEFDOCHTER] 			= NICHT_NIECE;  			 // 9
	
	sister[GROOTVADER] 				= GROOTVADER;        		 // 10
	sister[VADER]      				= VADER;             		 // 11
	sister[BROER] 				    = BROER; 					 // 12
	sister[OOM]                     = OOM;               		 // 13
	sister[NEEF_GEEN_SPECIFICATIE]  = ZOON + 1000 * NEEF_GEEN_SPECIFICATIE; // 14
	sister[NEEF_COUSIN]             = NEEF_COUSIN;   		     // 15 xxx
	sister[NEEF_NEPHEW]             = ZOON + 1000 * NEEF_NEPHEW; // 16 xxx
	sister[ACHTERNEEF]              = ACHTERNEEF;        		 // 17 xxx
	sister[OUDOOM]                  = OUDOOM;           		 // 18
	
	sister[GROOTMOEDER]             = GROOTMOEDER;        		 // 20
	sister[MOEDER]                  = MOEDER;          			 // 21
	sister[ZUSTER]                  = ZUSTER + 1000 * IMPLICIET_HOOFD_ZUSTER; // 22
	sister[TANTE]                   = TANTE;           		 	 // 23
	sister[NICHT_GEEN_SPECIFICATIE] = DOCHTER + 1000 * NICHT_GEEN_SPECIFICATIE; // 24 xxx
	sister[NICHT_COUSIN]            = NICHT_COUSIN;      		 // 25 xxx
	sister[NICHT_NIECE]             = DOCHTER + 1000 * NICHT_NIECE; //26 xxx
	sister[ACHTERNICHT]             = ACHTERNICHT;        		 // 27
	sister[OUDTANTE]                = OUDTANTE;          		 // 28
	
	sister[KLEINZOON]               = ACHTERNEEF;                // 30
	
	sister[KLEINDOCHTER]            = ACHTERNICHT;               // 40
	
	sister[ACHTERKLEINZOON]         = VERWANTE;                  // 51
	sister[ACHTERKLEINDOCHTER]      = VERWANTE;      			 // 52
	sister[SCHOONZOON]              = SCHOONNEEF_NEPHEW;         // 53
	sister[SCHOONDOCHTER]           = SCHOONNICHT_NIECE;         // 54
	
	sister[SCHOONGROOTVADER]              = VERWANTE;            // 60
	sister[SCHOONVADER]                   = VERWANTE;            // 61
	sister[AANGETROUWDE_STIEFVADER]       = VERWANTE;  		     // 62
	sister[SCHOONBROER_ZWAGER]            = VERWANTE;        	 // 63
	sister[SCHOONNEEF_GEEN_SPECIFICATIE]  = VERWANTE; 			 // 64
	sister[SCHOONNEEF_COUSIN]             = VERWANTE;            // 65
	sister[SCHOONNEEF_NEPHEW]             = VERWANTE;         	 // 66
	sister[AANGETROUWDE_ACHTERNEEF]       = VERWANTE;  		     // 67 
	sister[SCHOONOOM]                     = VERWANTE;            // 68
	sister[AANGETROUWDE_OUDOOM]           = VERWANTE;            // 69
	
	sister[SCHOONGROOTMOEDER]             = VERWANTE;            // 70
	sister[SCHOONMOEDER]                  = VERWANTE;            // 71
	sister[AANGETROUWDE_STIEFMOEDER]      = VERWANTE;  		     // 72
	sister[SCHOONZUS]                     = VERWANTE;            // 73
	sister[SCHOONNICHT_GEEN_SPECIFICATIE] = VERWANTE; 			 // 74
	sister[SCHOONNICHT_COUSIN]            = VERWANTE;            // 75
	sister[SCHOONNICHT_NIECE]             = VERWANTE;        	 // 76
	sister[AANGETROUWDE_ACHTERNICHT]      = VERWANTE;  		     // 77
	sister[SCHOONTANTE]                   = VERWANTE;            // 78
	sister[AANGETROUWDE_OUDTANTE]         = VERWANTE;     		 // 79
	
	sister[STIEFVADER]              = VADER + 1000 * VADER_OF_STIEFVADER;      // 81
	sister[STIEFMOEDER]             = MOEDER + 1000 * MOEDER_OF_STIEFMOEDER;   // 82
	sister[STIEFSCHOONZOON]         = VERWANTE;                  // 83
	sister[STIEFSCHOONDOCHTER]      = VERWANTE;                  // 84
	sister[STIEFBROER]              = BROER  + 1000 * STIEF_OF_HALF_OF_BROER;    // 85
	sister[STIEFZUSTER]             = ZUSTER + 1000 * STIEF_OF_HALF_OF_ZUSTER;   // 86
	sister[HALFBROER]               = BROER  + 1000 * STIEF_OF_HALF_OF_BROER;    // 87
	sister[HALFZUSTER]              = ZUSTER + 1000 * STIEF_OF_HALF_OF_ZUSTER;   // 88

	sister[BROER_OF_HALFBROER]      = BROER_OF_HALFBROER;        // 121
	sister[ZUSTER_OF_HALFZUSTER]    = ZUSTER_OF_HALFZUSTER;      // 122
	sister[ZOON_OF_STIEFZOON]       = NEEF_NEPHEW;               // 123
	sister[DOCHTER_OF_STIEFDOCHTER] = NICHT_NIECE;               // 124
	sister[ONBEKEND_WAS_PLEEGZOON]  = ONBEKEND_WAS_PLEEGZOON;    // 125
	sister[ONBEKEND_WAS_PLEEGDOCHTER] = ONBEKEND_WAS_PLEEGDOCHTER; // 126
	sister[STIEF_OF_HALF_OF_BROER]    = STIEF_OF_HALF_OF_BROER;    // 128
	sister[STIEF_OF_HALF_OF_ZUSTER]   = STIEF_OF_HALF_OF_ZUSTER;   // 129
	sister[VERWANTE]                  = VERWANTE;                  // 131
	sister[VERWANTE_VAN_VORIG_HOOFD]  = VERWANTE_VAN_VORIG_HOOFD;  // 132
	sister[HALFBROER_OF_STIEFBROER]   = HALFBROER_OF_STIEFBROER;   // 141
	sister[HALFZUSTER_OF_STIEFZUSTER] = HALFZUSTER_OF_STIEFZUSTER; // 142
	sister[VADER_OF_STIEFVADER]     = VADER_OF_STIEFVADER;         // 148
	sister[MOEDER_OF_STIEFMOEDER]   = MOEDER_OF_STIEFMOEDER;       // 149



	// Male To Female
	// Female To Male
	
	mToF[ZOON]     = DOCHTER;
	fToM[DOCHTER]  = ZOON;
	
	mToF[SCHOONZOON]     = SCHOONDOCHTER;
	fToM[SCHOONDOCHTER]  = SCHOONZOON;
	
	mToF[STIEFSCHOONZOON]     = STIEFSCHOONDOCHTER;
	fToM[STIEFSCHOONDOCHTER]  = STIEFSCHOONZOON;
	
	mToF[STIEFZOON]     = STIEFDOCHTER;
	fToM[STIEFDOCHTER]  = STIEFZOON;
	
	mToF[KLEINZOON]     = KLEINDOCHTER;
	fToM[KLEINDOCHTER]  = KLEINZOON;
	
	mToF[ACHTERKLEINZOON]     = ACHTERKLEINDOCHTER;
	fToM[ACHTERKLEINDOCHTER]  = ACHTERKLEINZOON;
	
	mToF[BROER]     = ZUSTER;
	fToM[ZUSTER]    = BROER;
	
	mToF[STIEFBROER]     = STIEFZUSTER;
	fToM[STIEFZUSTER]    = STIEFBROER;
	
	mToF[HALFBROER]     = HALFZUSTER;
	fToM[HALFZUSTER]    = HALFBROER;
	
	mToF[SCHOONBROER_ZWAGER]     = SCHOONZUS;
	fToM[SCHOONZUS]    			 = SCHOONBROER_ZWAGER;
	
	mToF[VADER]     	= MOEDER;
	fToM[MOEDER]  		= VADER;
	
	mToF[PLEEGVADER]     	= PLEEGMOEDER;
	fToM[PLEEGMOEDER]  		= PLEEGVADER;
	
	mToF[STIEFVADER]     	= STIEFMOEDER;
	fToM[STIEFMOEDER]  		= STIEFVADER;
	
	mToF[AANGETROUWDE_STIEFVADER]     	= AANGETROUWDE_STIEFMOEDER;
	fToM[AANGETROUWDE_STIEFMOEDER] 		= AANGETROUWDE_STIEFVADER;
	
	mToF[GROOTVADER]     	= GROOTMOEDER;
	fToM[GROOTMOEDER]  		= GROOTVADER;
	
	mToF[SCHOONVADER]     	= SCHOONMOEDER;
	fToM[SCHOONMOEDER] 		= SCHOONVADER;
	
	mToF[SCHOONGROOTVADER]     	= SCHOONGROOTMOEDER;
	fToM[SCHOONGROOTMOEDER]		= SCHOONGROOTVADER;
	
	mToF[OOM]     	= TANTE;
	fToM[TANTE]		= OOM;
	
	mToF[SCHOONOOM]     	= SCHOONTANTE;
	fToM[SCHOONTANTE]		= SCHOONOOM;
	
	mToF[OUDOOM]     	= OUDTANTE;
	fToM[OUDTANTE]		= OUDOOM;
	
	mToF[AANGETROUWDE_OUDOOM]     	= AANGETROUWDE_OUDTANTE;
	fToM[AANGETROUWDE_OUDTANTE]		= AANGETROUWDE_OUDOOM;
	
	mToF[NEEF_COUSIN]     	= NICHT_COUSIN;
	fToM[NICHT_COUSIN]		= NEEF_COUSIN;
	
	mToF[NEEF_GEEN_SPECIFICATIE]     	= NICHT_GEEN_SPECIFICATIE;
	fToM[NICHT_GEEN_SPECIFICATIE]		= NEEF_GEEN_SPECIFICATIE;
	
	mToF[NEEF_NEPHEW]     	= NICHT_NIECE;
	fToM[NICHT_NIECE]		= NEEF_NEPHEW;
	
	mToF[SCHOONNEEF_COUSIN]     	= SCHOONNICHT_COUSIN;
	fToM[SCHOONNICHT_COUSIN]		= SCHOONNEEF_COUSIN;
	
	mToF[SCHOONNEEF_GEEN_SPECIFICATIE]     	= SCHOONNICHT_GEEN_SPECIFICATIE;
	fToM[SCHOONNICHT_GEEN_SPECIFICATIE]		= SCHOONNEEF_GEEN_SPECIFICATIE;
	
	mToF[SCHOONNEEF_NEPHEW]    	= SCHOONNICHT_NIECE;
	fToM[SCHOONNICHT_NIECE]		= SCHOONNEEF_NEPHEW;
	
	mToF[ACHTERNEEF]     	= ACHTERNICHT;
	fToM[ACHTERNICHT]		= ACHTERNEEF;
	
	mToF[AANGETROUWDE_ACHTERNEEF]     	= AANGETROUWDE_ACHTERNICHT;
	fToM[AANGETROUWDE_ACHTERNICHT]		= AANGETROUWDE_ACHTERNEEF;
	
	mToF[BROER_OF_HALFBROER]     	= ZUSTER_OF_HALFZUSTER;
	fToM[ZUSTER_OF_HALFZUSTER]		= BROER_OF_HALFBROER;
	
	mToF[ZOON_OF_STIEFZOON]     	= DOCHTER_OF_STIEFDOCHTER;
	fToM[DOCHTER_OF_STIEFDOCHTER]	= ZOON_OF_STIEFZOON;
	
	mToF[HALFBROER_OF_NEEF]     	= HALFZUSTER_OF_NICHT;
	fToM[HALFZUSTER_OF_NICHT]	    = HALFBROER_OF_NEEF;
	
	mToF[HALFBROER_OF_STIEFBROER]     	= HALFZUSTER_OF_STIEFZUSTER;
	fToM[HALFZUSTER_OF_STIEFZUSTER]	    = HALFBROER_OF_STIEFBROER;
	
    
}
public static String[] getB3kode1() {
	return b3kode1;
}
public static void setB3kode1(String[] b3kode1) {
	ConstRelations2.b3kode1 = b3kode1;
}
public static String[] getB3kode2() {
	return b3kode2;
}
public static void setB3kode2(String[] b3kode2) {
	ConstRelations2.b3kode2 = b3kode2;
}
public static String[] getB3kode1_English() {
	return b3kode1_English;
}
public static void setB3kode1_English(String[] b3kode1_English) {
	ConstRelations2.b3kode1_English = b3kode1_English;
}   


    
}


