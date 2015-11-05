package nl.iisg.convertPK;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TableDefinitions {

	 public static final int XBigstring = 256;
	 public static final int Bigstring = 60;
	 public static final int Smallstring = 15;
	 
	
	 public static final String B2_ST = 
		 
		 "CREATE TABLE IF NOT EXISTS b2_st" +
		 "(" +
		 " B1IDBG INT, " +
		 " B2DIBG CHAR(10), " + 
		 " IDNR INT, " +
		 " B2RNBG SMALLINT, " + 
		 " B2FCBG SMALLINT, " +

		 " B2RDNR CHAR(10), " +
		 " B2RDFG TINYINT, " + 

		 " PERSON_ID INT, " +
		 " PERSON_ID_FG TINYINT, " +

		 " PERSON_ID_FA INT, " +
		 " PERSON_ID_FA_FG TINYINT, " +

		 " PERSON_ID_MO INT, " +
		 " PERSON_ID_MO_FG TINYINT, " +


		 " START_DATE CHAR(10), " + 
		 " START_FLAG SMALLINT, " +
		 " START_EST  SMALLINT, " +  

		 " END_DATE CHAR(10), " + 
		 " END_FLAG SMALLINT, " +
		 " END_EST  SMALLINT,  " + 

		 " FAMILYNAME VARCHAR(" + Bigstring + "), " +
		 " PREFIX_FAMILYNAME VARCHAR(" + Smallstring + "), " +
		 " TITLE_NOBLE VARCHAR(" + Smallstring + "), " + 
		 " TITLE_ELSE VARCHAR(" + Smallstring + "),  " +
		 " FAMILYNAME_FG TINYINT, " +

		 " FIRSTNAME VARCHAR(" + Bigstring + "), " +
		 " FIRSTNAME_FG TINYINT, " +
		 " SEX CHAR(1), " +

		 " B2GDNR CHAR(10), " +
		 " B2GDFG TINYINT, " +

		 " BIRTH_LOCALITY_ID int, " +
		 " BIRTH_LOCALITY_ST VARCHAR(" + Bigstring + "), " + 
		 " BIRTH_LOCALITY_FG TINYINT, " +

		 " B2ODNR CHAR(10), " +
		 " B2ODFG TINYINT, " +

		 " DEATH_LOCALITY_ID int, " +
		 " DEATH_LOCALITY_ST VARCHAR(" + Bigstring + "), " + 
		 " DEATH_LOCALITY_FG TINYINT, " +

		 " NATIONALITY VARCHAR(" + Bigstring + "), " +

		 " LEGAL_LIVPL_ID int, " +
		 " LEGAL_LIVPL_ST VARCHAR(" + Bigstring + "), " + 
		 " LEGAL_LIVPL_FG TINYINT,   " +
		 " LEGAL_LIVPL_CODE VARCHAR(2), " +

		 " REMARKS VARCHAR(" + XBigstring + "), " +
		 " ADDITION VARCHAR(" + XBigstring + "), " +

		 " VERSIE VARCHAR(8), " +
		 " ONDRZKO VARCHAR(8), " +
		 " VERSIEO VARCHAR(8), " +
		 " DATUM VARCHAR(10) " +
		 " ); ";

	 
		 
	 public static final String B313_ST =
		 
		 " CREATE TABLE IF NOT EXISTS b313_st " +
		 " ( " +

		 " B1IDBG INT, " +
		 " B2DIBG CHAR(10), " +
		 " IDNR INT, " +

		 " B2RNBG SMALLINT, " +
		 " B3TYPE TINYINT, " +
		 " B3VRNR SMALLINT, " +


		 " START_DATE CHAR(10), " + 
		 " START_FLAG SMALLINT, " +
		 " START_EST  SMALLINT, " +  

		 " END_DATE CHAR(10), " + 
		 " END_FLAG SMALLINT, " +
		 " END_EST  SMALLINT, " +  

		 " B3KODE SMALLINT, " +
		 " B3RGLN SMALLINT, " +
	//	 " B2FCBG SMALLINT, " +
		 " B3MDNR CHAR(10), " +
		 " B3MDFG TINYINT, " + 

		 " VERSIE VARCHAR(8), " +
		 " ONDRZKO VARCHAR(8), " +
		 " VERSIEO VARCHAR(8), " +
		 " DATUM VARCHAR(10) " +
		 " );";
		 
	 public static final String B32_ST = 
	 
	 
	 " CREATE TABLE IF NOT EXISTS b32_st" +
	 " (" +

	 " B1IDBG INT, " +
	 " B2DIBG CHAR(10), " +
	 " IDNR INT, " +

	 " B2RNBG SMALLINT, " +
	 " B3TYPE TINYINT, " +
	 " B3VRNR SMALLINT, " +

	 " START_DATE CHAR(10), " + 
	 " START_FLAG SMALLINT, " +
	 " START_EST  SMALLINT, " +  

	 " END_DATE CHAR(10), " + 
	 " END_FLAG SMALLINT, " +
	 " END_EST  SMALLINT, " +

	 " B3KODE SMALLINT, " +
	 " B3RGLN SMALLINT, " +
	 " CIVIL_STATUS_FG TINYINT, " + 
//	 " B2FCBG SMALLINT, " +
	 " B3MDNR CHAR(10), " +
	 " B3MDFG TINYINT, " + 
	 " CIVIL_LOCALITY_ID INT, " +
	 " CIVIL_LOCALITY_ST VARCHAR(" + Bigstring + "), " +
	 " CIVIL_LOCALITY_FG TINYINT, " +

	 " VERSIE VARCHAR(8), " +
	 " ONDRZKO VARCHAR(8), " +
	 " VERSIEO VARCHAR(8), " +
	 " DATUM VARCHAR(10) " +
	 " );";

	 public static final String B33_ST = 
	 
	 " CREATE TABLE IF NOT EXISTS b33_st " +
	 " (" +
	 " B1IDBG INT, " +
	 " B2DIBG CHAR(10), " +
	 " IDNR INT, " +


	 " B2RNBG SMALLINT, " +
	 " B3TYPE TINYINT, " +
	 " B3VRNR SMALLINT, " +

	 " START_DATE CHAR(10), " + 
	 " START_FLAG SMALLINT, " +
	 " START_EST  SMALLINT, " +  

	 " END_DATE CHAR(10), " + 
	 " END_FLAG SMALLINT, " +
	 " END_EST  SMALLINT, " +

	 " B3RGLN SMALLINT, " +
//	 " B2FCBG SMALLINT, " +
	 " B3MDNR CHAR(10), " +
	 " B3MDFG TINYINT, " + 

	 " RELIGION_ID INT, " +
	 " RELIGION_ST VARCHAR(" + Bigstring + "), " +
	 " RELIGION_FG TINYINT, " +

	 " VERSIE VARCHAR(8), " +
	 " ONDRZKO VARCHAR(8), " +
	 " VERSIEO VARCHAR(8), " +
	 " DATUM VARCHAR(10) " +
	 " );";
	 
	 public static final String B34_ST = 
	 
	 " CREATE TABLE IF NOT EXISTS b34_st " +
	 " (" +

	 " B1IDBG INT, " +
	 " B2DIBG CHAR(10), " +
	 " IDNR INT, " +

	 " B2RNBG SMALLINT, " +
	 " B3TYPE TINYINT, " +
	 " B3VRNR SMALLINT, " +


	 " START_DATE CHAR(10), " + 
	 " START_FLAG SMALLINT, " +
	 " START_EST  SMALLINT, " +  

	 " END_DATE CHAR(10), " + 
	 " END_FLAG SMALLINT, " +
	 " END_EST  SMALLINT, " +  

	 " B3KODE SMALLINT, " +
	 " B3RGLN SMALLINT, " +
//	 " B2FCBG SMALLINT, " +
	 " B3MDNR CHAR(10), " +
	 " B3MDFG TINYINT, " + 


	 " VERSIE VARCHAR(8), " +
	 " ONDRZKO VARCHAR(8), " +
	 " VERSIEO VARCHAR(8), " +
	 " DATUM VARCHAR(10) " +
	 " );";
	 
	 
	 public static final String B35_ST = 
	 
	 " CREATE TABLE IF NOT EXISTS b35_st " +
	 " (" +

	 " B1IDBG INT, " +
	 " B2DIBG CHAR(10), " +
	 " IDNR INT, " +

	 " B2RNBG SMALLINT, " +
	 " B3TYPE TINYINT, " +
	 " B3VRNR SMALLINT, " +

	 " START_DATE CHAR(10), " + 
	 " START_FLAG SMALLINT, " +
	 " START_EST  SMALLINT, " +  

	 " END_DATE CHAR(10), " + 
	 " END_FLAG SMALLINT, " +
	 " END_EST  SMALLINT, " +

	 " B3RGLN SMALLINT, " +
//	 " B2FCBG SMALLINT, " +
	 " B3MDNR CHAR(10), " +
	 " B3MDFG TINYINT, " + 

	 " OCCUPATION_ID INT, " +
	 " OCCUPATION_ST VARCHAR(" + Bigstring + "), " +
	 " OCCUPATION_FG TINYINT, " +

	 " VERSIE VARCHAR(8), " +
	 " ONDRZKO VARCHAR(8), " +
	 " VERSIEO VARCHAR(8), " +
	 " DATUM VARCHAR(10) " +
	 " );";
	 
	 
	 public static final String B36_ST = 
	 
	 " CREATE TABLE IF NOT EXISTS  b36_st" +
	 " (" +

	 " B1IDBG INT, " +
	 " B2DIBG CHAR(10), " +
	 " IDNR INT, " +

	 " B2RNBG SMALLINT, " +
	 " B3TYPE TINYINT, " +
	 " B3VRNR SMALLINT, " +

	 " START_DATE CHAR(10), " + 
	 " START_FLAG SMALLINT, " +
	 " START_EST  SMALLINT, " +  

	 " END_DATE CHAR(10), " + 
	 " END_FLAG SMALLINT, " +
	 " END_EST  SMALLINT, " +

	 " B3RGLN SMALLINT, " +
//	 " B2FCBG SMALLINT, " +
	 " B3MDNR CHAR(10), " +
	 " B3MDFG TINYINT, " + 

	 " ORIGIN_ID INT, " +
	 " ORIGIN_ST VARCHAR(" + Bigstring + "), " +
	 " ORIGIN_FG TINYINT, " +

	 " ORIGIN_EQUAL TINYINT, " + 

	 " ADDRESS VARCHAR(" + Bigstring + "), " +
	 " REGISTER VARCHAR(" + Bigstring + "), " +
	 " CENSUS VARCHAR(" + Bigstring + "), " +


	 " VERSIE VARCHAR(8), " +
	 " ONDRZKO VARCHAR(8), " +
	 " VERSIEO VARCHAR(8), " +
	 " DATUM VARCHAR(10) " +
	 " );";
	 
	 public static final String B37_ST = 
	 
	 " CREATE TABLE IF NOT EXISTS b37_st " +
	 " (" +

	 " B1IDBG INT, " +
	 " B2DIBG CHAR(10), " +
	 " IDNR INT, " +

	 " B2RNBG SMALLINT, " +
	 " B3TYPE TINYINT, " +
	 " B3VRNR SMALLINT, " +

	 " START_DATE CHAR(10), " + 
	 " START_FLAG SMALLINT, " +
	 " START_EST  SMALLINT, " +  

	 " END_DATE CHAR(10), " + 
	 " END_FLAG SMALLINT, " +
	 " END_EST  SMALLINT, " +

	 " B3KODE SMALLINT, " +
	 " B3RGLN SMALLINT, " +
//	 " B2FCBG SMALLINT, " +
	 " B3MDNR CHAR(10), " +
	 " B3MDFG TINYINT, " + 

	 " DESTINATION_ID INT, " +
	 " DESTINATION_ST VARCHAR(" + Bigstring + "), " +
	 " DESTINATION_FG TINYINT, " +

	 " DESTINATION_EQUAL TINYINT, " + 

	 " ADDRESS VARCHAR(" + Bigstring + "), " +
	 " REGISTER VARCHAR(" + Bigstring + "), " +
	 " CENSUS VARCHAR(" + Bigstring + "), " +


	 " VERSIE VARCHAR(8), " +
	 " ONDRZKO VARCHAR(8), " +
	 " VERSIEO VARCHAR(8), " +
	 " DATUM VARCHAR(10) " +
	 " );";

	 public static final String B4_ST = 
	 
	 " CREATE TABLE IF NOT EXISTS b4_stpk" +
	 " (" +

	 " B1IDBG INT, " +
	 " B2DIBG CHAR(10), " +
	 " IDNR INT, " + 
	 
	 " START_PK CHAR(10), " + 
	 " START_PK_FG SMALLINT, " +

	 " END_PK CHAR(10), " + 
	 " END_PK_FG SMALLINT, " +


//	 " B2FDBG CHAR(10), " + 
	 " IDNR_SPOUSE INT, " + 
	 " PK_HOLDER VARCHAR(" + XBigstring + "), " + 

//	 " REGISTER_PAGE VARCHAR(" + Smallstring + "), " + 
//	 " REGISTER_LINE TINYINT, " + 
//	 " NAME_HEAD_GK VARCHAR(" + XBigstring + "), " + 
//	 " SPECIAL_CODE VARCHAR(" + XBigstring + "), " + 
	 " REMARKS VARCHAR(" + XBigstring + "), " + 

	 " VERSIE VARCHAR(8), " +
	 " ONDRZKO VARCHAR(8), " +
	 " VERSIEO VARCHAR(8), " +
	 " DATUM VARCHAR(10) " +
	 " );";
	 
	 public static final String B6_ST = 
	 
	 " CREATE TABLE IF NOT EXISTS b6_st" +
	 "  (" +

	 " B1IDBG INT, " +
	 " B2DIBG CHAR(10), " + 
	 " IDNR INT, " +

	 " B2RNBG SMALLINT, " +
	 " B6VRNR SMALLINT, " +
	 " B6MDNR CHAR(10), " +
	 " B6MDFG TINYINT, " + 



	 " START_DATE CHAR(10), " + 
	 " START_FLAG SMALLINT, " +
	 " START_EST  SMALLINT, " +  

	 " END_DATE CHAR(10), " + 
	 " END_FLAG SMALLINT, " +
	 " END_EST  SMALLINT, " +
	 
	 " MUNICIPALITY_ST VARCHAR(" + Bigstring + "), " + 
	 " POSTAL_CODE CHAR(6), " +
	 " RENUMBERING TINYINT, " +


	 " ADDRESS_ID INT, " +
	 " STREET_ST VARCHAR(" + Bigstring + "), " + 
	 " QUARTER_ST VARCHAR(" + Bigstring + "), " + 
	 " PLACE_ST VARCHAR(" + Bigstring + "), " + 
	 " BOAT_ST VARCHAR(" + Bigstring + "), " +
	 " BERTH_ST VARCHAR(" + Bigstring + "), " +
	 " INSTIT_ST VARCHAR(" + Bigstring + "), " +
	 " LANDLORD_ST VARCHAR(" + Bigstring + "), " +
	 " OTHER_ST VARCHAR(" + Bigstring + "), " +
	 " ADDRESS_FG TINYINT, " + 

	 " NUMBER_ST VARCHAR(" + Smallstring + "), " +
	 " ADDITION_ST VARCHAR(" + Smallstring + "), " +
	 
	 " VERSIE VARCHAR(8), " +
	 " ONDRZKO VARCHAR(8), " +
	 " VERSIEO VARCHAR(8), " +
	 " DATUM VARCHAR(10) " +
	 "  );";
	 
	 public static final String BFOUT7FT = 
		 
		 
		 "create table IF NOT EXISTS bfout7ft" +
		 "(" +
		 "IDNR int," +

		 "B1IDBG int," +

		 "B2DIBG tinyint," +
		 "B2MIBG tinyint," +
		 "B2JIBG smallint," +

		 "B2FDBG tinyint," +
		 "B2FMBG tinyint," + 
		 "B2FJBG smallint," +

		 "B2RNBG int," + 
		 "B2FCBG tinyint," + 


		 "FTKODE       smallint," +
		 "FTTYPE       varchar(2)," +
		 "FOUT         varchar(" + XBigstring + ")" +

		 ");"; 
	 
	 public static final String PKKND = 		 
		 
		 "create table IF NOT EXISTS pkknd" +
		 "(" +
		 "IDNR int," +

		    "IDNRP       int," +      
		    
		    "GAKTNRP     varchar(" + Smallstring + ")," + 
		    "PKTYPE      tinyint, " +     
		    
		    "EINDAGPK    tinyint," +     
		    "EINMNDPK    tinyint," +    
		    "EINJARPK    smallint," +     
		    
		    "CTRDGP      tinyint," +      
		    "CTRMDP      tinyint," +      
		    "CTRJRP      smallint," +     

		    "CTRPARP     varchar(1)," + 
		    "GZNVRMP     varchar(" + XBigstring + ")," + 

		    
		    "ANMPERP     varchar(" + Bigstring + ")," + 
		    "TUSPERP     varchar(" + Smallstring + ")," + 
		    "VNM1PERP    varchar(" + Bigstring + ")," + 
		    "VNM2PERP    varchar(" + Bigstring + ")," +  
		    "VNM3PERP    varchar(" +  Bigstring + ")," + 
		    
		    "GDGPERP     tinyint," + 
		    "GMDPERP     tinyint," + 
		    "GJRPERP     smallint," + 
		    
		    "GDGPERPCR   tinyint," + 
		    "GMDPERPCR   tinyint," + 
		    "GJRPERPCR   smallint," + 
		    
		    "GPLPERP     varchar(" + Bigstring + ")," + 
		    "NATPERP     varchar(" + Bigstring + ")," + 
		    "GDSPERP     varchar(" + Bigstring + ")," + 
		    "GSLPERP     varchar(" + Smallstring + ")," + 
		    
		    // Information about the Father of the PK-Holder

		    "ANMVDRP     varchar(" + Bigstring + ")," + 
		    "TUSVDRP     varchar(" + Smallstring + ")," + 
		    "VNM1VDRP    varchar(" + Bigstring + ")," + 
		    "VNM2VDRP    varchar(" + Bigstring + ")," + 
		    "VNM3VDRP    varchar(" + Bigstring + ")," + 
		    
		    "GDGVDRP     tinyint," + 
		    "GMDVDRP     tinyint," + 
		    "GJRVDRP     smallint," + 
		    
		    "GDGVDRPCR   tinyint," + 
		    "GMDVDRPCR   tinyint," + 
		    "GJRVDRPCR   smallint," + 
		    
		    "GPLVDRP     varchar(" + Bigstring + ")," + 
		 
		    // Information about the Mother of the PK-Holder

		    "ANMMDRP     varchar(" + Bigstring + ")," + 
		    "TUSMDRP     varchar(" + Smallstring + ")," + 
		    "VNM1MDRP    varchar(" + Bigstring + ")," + 
		    "VNM2MDRP    varchar(" + Bigstring + ")," + 
		    "VNM3MDRP    varchar(" + Bigstring + ")," +  
		    
		    "GDGMDRP     tinyint," + 
		    "GMDMDRP     tinyint," + 
		    "GJRMDRP     smallint," + 
		    
		    "GDGMDRPCR   tinyint," + 
		    "GMDMDRPCR   tinyint," + 
		    "GJRMDRPCR   smallint," + 
		    
		    "GPLMDRP     varchar(" + Bigstring + ")," +  
		    
		    // Information about the OP
		    
		    "ODGPERP     tinyint," + 
		    "OMDPERP     tinyint," + 
		    "OJRPERP     smallint," + 

		    "OPLPERP     varchar(" + Bigstring + ")," + 
		    "OAKPERP     varchar(" + Smallstring + ")," + 
		    "ODOPERP     varchar(" + Bigstring + ")," +  
		    
		    "GEGPERP     varchar(1)," + 
		    "GEGVDRP     varchar(1)," + 
		    "GEGMDRP     varchar(1)," + 
		    
		    "PROBLMP     varchar(1)," + 
		    
		    "PSBDGP      tinyint," + 
		    "PSBMDP      tinyint," + 
		    "PSBJRP      smallint," + 
		    "PSBNRP      varchar(" + Smallstring + ")," + 

		    
		    "OPDRNR     varchar(" + Smallstring + ")," + 
			"DATUM      varchar(10)," + 
			"INIT       varchar(" + Smallstring + ")," + 
			"VERSIE     varchar(" + Smallstring + ")," + 
			"ONDRZKO    varchar(" + Smallstring + ")," + 
			"OPDRNRO    varchar(" + Smallstring + ")," + 
			"DATUMO     varchar(10)," + 
			"INITO      varchar(" + Smallstring + ")," +    
			"VERSIEO    varchar(" + Smallstring + ")" + 

		 "  );";


	 public static final String PKADDRESS = 		 
		 
		 "create table IF NOT EXISTS pkadres" +
		 "(" +
		 "IDNR int," +

		 
	     "VGNRADP 	     int," +      
	     "DGADRP    	 tinyint," +      
	     "MDADRP    	 tinyint," +      
	     "JRADRP    	 smallint," +     
	     "VERNUM    	 char(1)," +   
	     "STRADRP   	 varchar(" + Bigstring + ")," +   
	     "PLADRP    	 varchar(" + Bigstring + ")," +   
	     "LNDADRP    	 varchar(" + Bigstring + ")," +   

		    
	     "OPDRNR         varchar(" + Smallstring + ")," + 
		 "DATUM          varchar(10)," + 
		 "INIT           varchar(" + Smallstring + ")," + 
		 "VERSIE         varchar(" + Smallstring + ")," + 
		 "ONDRZKO        varchar(" + Smallstring + ")," + 
		 "OPDRNRO        varchar(" + Smallstring + ")," + 
		 "DATUMO         varchar(10)," + 
		 "INITO          varchar(" + Smallstring + ")," +    
		 "VERSIEO        varchar(" + Smallstring + ")" + 
		 "  );";

	 public static final String PKEIGKND = 		 
		 
		 "create table IF NOT EXISTS pkeigknd" +
		 "(" +
		 "IDNR int," +

		 "VGNRKDP         tinyint," + 	  
		 "ANMKNDP         varchar(" + Bigstring + ")," +   
		 "TUSKNDP         varchar(" + Smallstring + ")," +    
		 "VN1KNDP         varchar(" + Bigstring + ")," +   
		 "VN2KNDP         varchar(" + Bigstring + ")," +   
		 "VN3KNDP         varchar(" + Bigstring + ")," +   
		 "GDGKNDP         tinyint," +   
		 "GMDKNDP         tinyint," + 	  
		 "GJRKNDP         smallint," +      
		 "GDGKNDPCR       tinyint," +        
		 "GMDKNDPCR       tinyint," + 	 
		 "GJRKNDPCR       smallint," +       
		 "GPLKNDP         varchar(" + Bigstring + ")," +   
		 "GLNKNDP         varchar(" + Bigstring + ")," +   
		 "RELKNDP         varchar(" + Bigstring + ")," +   
		 "HDGKNDP         tinyint," + 	 
		 "HMDKNDP         tinyint," +      
		 "HJRKNDP         smallint," +      
		 "HPLKNDP         varchar(" + Bigstring + ")," +   
		 "VNMPTNP         varchar(" + Bigstring + ")," +   
		 "TUSPTNP         varchar(" + Smallstring + ")," +   
		 "ANMPTNP         varchar(" + Bigstring + ")," +   
		 "ODGKNDP         tinyint," + 
		 "OMDKNDP         tinyint," +     
		 "OJRKNDP         smallint," +       
		 "OPLKNDP         varchar(" + Bigstring + ")," +   
		 "ADGKNDP         tinyint," + 	 
		 "AMDKNDP         tinyint," +       
		 "AJRKNDP         smallint," +       
		 "APLKNDP         varchar(" + Bigstring + ")," +  
		 "AANTEK          varchar(" + Bigstring + ")," +    
         "OPDRNR 		  varchar(" + Smallstring + ")," + 
	     "DATUM           varchar(10)," + 
		 "INIT            varchar(" + Smallstring + ")," + 
		 "VERSIE          varchar(" + Smallstring + ")," + 
		 "ONDRZKO         varchar(" + Smallstring + ")," + 
		 "OPDRNRO         varchar(" + Smallstring + ")," + 
		 "DATUMO          varchar(10)," + 
		 "INITO           varchar(" + Smallstring + ")," +    
		 "VERSIEO         varchar(" + Smallstring + ")" + 

		 "  );";

	 public static final String PKHUW = 		 
		 
		 "create table IF NOT EXISTS pkhuw" +
		 "(" +
		 "IDNR int," +

		 "VNRHUWP         tinyint," + 	 
		 "ANMHUWP         varchar(" + Bigstring + ")," + 	  
		 "TUSHUWP         varchar(" + Smallstring + ")," +    
		 "VN1HUWP         varchar(" + Bigstring + ")," +  
		 "VN2HUWP         varchar(" + Bigstring + ")," +  
		 "VN3HUWP         varchar(" + Bigstring + ")," +    
		 "BRPHUWP         varchar(" + Bigstring + ")," +   
		 "GDGHUWP         tinyint," +     
		 "GMDHUWP         tinyint," + 	       	
		 "GJRHUWP         smallint," +       	
		 "GDGHUWPCR       tinyint," +       	    
		 "GMDHUWPCR       tinyint," + 	      
		 "GJRHUWPCR       smallint," +             
		 "GPLHUWP         varchar(" + Bigstring + ")," +    
		 "HDGHUWP         tinyint," + 	  		
		 "HMDHUWP         tinyint," +     	
		 "HJRHUWP         smallint," +         	
		 "HPLHUWP         varchar(" + Bigstring + ")," +   
		 "ODGHUWP         tinyint," + 	 		
		 "OMDHUWP         tinyint," +      		
		 "OJRHUWP         smallint," +              
		 "ORDHUWP         tinyint, "  +    
		 "OPLHUWP         varchar(" + Bigstring + ")," +    
		 "ADGHUWP         tinyint," + 	     
		 "AMDHUWP         tinyint," +      
		 "AJRHUWP         smallint," +       
		 "APLHUWP         varchar(" + Bigstring + ")," + 
		 "SRTHUWP         varchar(" + Smallstring + ")," +   
		 "DDGHUWP         tinyint," +      
		 "DMDHUWP         tinyint," +     
		 "DJRHUWP         smallint," +       
		 "OPDGHUWP        tinyint," +      
		 "OPMDHUWP        tinyint," +      
		 "OPJRHUWP        smallint," +      
		    
	     "OPDRNR     varchar(" + Smallstring + ")," + 
	     "DATUM      varchar(10)," + 
		 "INIT       varchar(" + Smallstring + ")," + 
		 "VERSIE     varchar(" + Smallstring + ")," + 
		 "ONDRZKO    varchar(" + Smallstring + ")," + 
		 "OPDRNRO    varchar(" + Smallstring + ")," + 
		 "DATUMO     varchar(10)," + 
		 "INITO      varchar(" + Smallstring + ")," +    
		 "VERSIEO    varchar(" + Smallstring + ")" + 

		 "  );";

	 public static final String PKBYZ = 		 
		 
		 "create table IF NOT EXISTS pkbyz" +
		 "(" +
		 "IDNR int," +

		 "BYZNR          tinyint," +      
		 "BYZ            varchar(" + XBigstring + ")," +   
		 "SCHERM         varchar(" + Smallstring + ")," +   	 
		    
		 "OPDRNR     	 varchar(" + Smallstring + ")," + 
		 "DATUM      	 varchar(10)," + 
		 "INIT       	 varchar(" + Smallstring + ")," + 
		 "VERSIE     	 varchar(" + Smallstring + ")," + 
		 "ONDRZKO    	 varchar(" + Smallstring + ")," + 
		 "OPDRNRO    	 varchar(" + Smallstring + ")," + 
		 "DATUMO     	 varchar(10)," + 
		 "INITO      	 varchar(" + Smallstring + ")," +    
		 "VERSIEO    	 varchar(" + Smallstring + ")" + 

		 "  );";

	 public static final String PKBRP = 		 
		 
		 "create table IF NOT EXISTS pkbrp" +
		 "(" +
		 "IDNR int," +

		 "VGNRBRP    int," +      
		 "BRPPOSP    varchar(" + Bigstring + ")," +   
		 "BEROEPP    varchar(" + Bigstring + ")," +   
		 "DHBRPP     char(1)," +   
		    
		 "OPDRNR     varchar(" + Smallstring + ")," + 
		 "DATUM      varchar(10)," + 
		 "INIT       varchar(" + Smallstring + ")," + 
		 "VERSIE     varchar(" + Smallstring + ")," + 
		 "ONDRZKO    varchar(" + Smallstring + ")," + 
		 "OPDRNRO    varchar(" + Smallstring + ")," + 
		 "DATUMO     varchar(10)," + 
		 "INITO      varchar(" + Smallstring + ")," +    
		 "VERSIEO    varchar(" + Smallstring + ")" + 

		 "  );";

	 public static final String P7 = 		 
		 
		 "create table IF NOT EXISTS p7" +
		 "(" +
		 "IDNR int," +

	     "IDNRP           int," +       
	     "P7IDBG          int," +    
		 "P7OPOG          varchar(" + Bigstring + ")," +    
		 "P7OPOL          varchar(" + Bigstring + ")," +    
		 "P7OPOR          varchar(" + Bigstring + ")," +    
		 "P7OPOB          varchar(" + Bigstring + ")," +    
		 "P7OPIO          varchar(" + Smallstring + ")," +    
		 "P7OPPG          varchar(" + Bigstring + ")," +    
		 "P7OPPC          varchar(" + Bigstring + ")," +    

	     "OPDRNR    	  varchar(" + Smallstring + ")," + 
	 	 "DATUM      	  varchar(10)," + 
		 "INIT       	  varchar(" + Smallstring + ")," + 
		 "VERSIE     	  varchar(" + Smallstring + ")," + 
		 "ONDRZKO    	  varchar(" + Smallstring + ")," + 
		 "OPDRNRO    	  varchar(" + Smallstring + ")," + 
		 "DATUMO     	  varchar(10)," + 
		 "INITO      	  varchar(" + Smallstring + ")," +    
		 "VERSIEO    	  varchar(" + Smallstring + ")" + 

		 "  );";

	 public static final String P8 = 		 
		 
		 "create table IF NOT EXISTS p8" +
		 "(" +
		 "IDNR int," +
	     "IDNRP           int," +       

		 
	     "P8TPNR         int," +      
	     "DGADRP    	 tinyint," +     
	     "MDADRP    	 tinyint," +      
	     "JRADRP    	 int," +     
	     "PLADRP    	 varchar(" + Bigstring + ")," +   
	     "P8OPPD    	 tinyint," +     
	     "P8OPPM    	 tinyint," +     
	     "P8OPPJ    	 smallint," +      
	     "P8OPWF    	 varchar(" + Smallstring + ")," +  
	     "P8OPWL    	 varchar(" + Bigstring + ")," +   
	     "P8OPWS    	 varchar(" + Bigstring + ")," +   
	     "P8OPWH    	 varchar(" + Smallstring + ")," +  
	     "P8OPWR    	 varchar(" + Smallstring + ")," +   
	     "P8OPWT    	 varchar(" + Smallstring + ")," +   
	     "P8OPWP    	 varchar(" + Smallstring + ")," +   
	     "P8OPWB    	 varchar(" + Bigstring + ")," +   
	     "P8OPIL    	 varchar(" + Bigstring + ")," +  
	     "P8OPIJ    	 smallint," +            
	     "P8OPIM    	 tinyint," +          
	     "P8OPID    	 smallint," +           
	     "P8OPAG    	 varchar(" + Smallstring + ")," +   
	     "P8OPZJ    	 tinyint," +       
	     "P8OPZM    	 tinyint," +      
	     "P8OPZD    	 smallint," +      
	     "P8OPIO    	 varchar(" + Smallstring + ")," +
	     
         "OPDRNR         varchar(" + Smallstring + ")," + 
	     "DATUM      	 varchar(10)," + 
		 "INIT       	 varchar(" + Smallstring + ")," + 
		 "VERSIE     	 varchar(" + Smallstring + ")," + 
		 "ONDRZKO    	 varchar(" + Smallstring + ")," + 
		 "OPDRNRO    	 varchar(" + Smallstring + ")," + 
		 "DATUMO     	 varchar(10)," + 
		 "INITO      	 varchar(" + Smallstring + ")," +    
		 "VERSIEO    	 varchar(" + Smallstring + ")" + 

		 "  );";

	 
	 public static final String B2_ST_TRUNCATE     = "TRUNCATE TABLE b2_st;";
	 public static final String B313_ST_TRUNCATE   = "TRUNCATE TABLE b313_st;";
	 public static final String B32_ST_TRUNCATE    = "TRUNCATE TABLE b32_st;";
	 public static final String B33_ST_TRUNCATE    = "TRUNCATE TABLE b33_st;";
	 public static final String B34_ST_TRUNCATE    = "TRUNCATE TABLE b34_st;";
	 public static final String B35_ST_TRUNCATE    = "TRUNCATE TABLE b35_st;";
	 public static final String B36_ST_TRUNCATE    = "TRUNCATE TABLE b36_st;";
	 public static final String B37_ST_TRUNCATE    = "TRUNCATE TABLE b37_st;";
	 public static final String B4_ST_TRUNCATE     = "TRUNCATE TABLE b4_stpk;";
	 public static final String B6_ST_TRUNCATE     = "TRUNCATE TABLE b6_st;";
	 public static final String BFOUT7FT_TRUNCATE  = "TRUNCATE TABLE bfout7ft;";
	 public static final String PKKND_TRUNCATE     = "TRUNCATE TABLE pkknd;";
	 public static final String PKEIGKND_TRUNCATE  = "TRUNCATE TABLE pkeigknd;";
	 public static final String PKBRP_TRUNCATE     = "TRUNCATE TABLE pkbrp;";
	 public static final String PKHUW_TRUNCATE     = "TRUNCATE TABLE pkhuw;";
	 public static final String PKBYZ_TRUNCATE     = "TRUNCATE TABLE pkbyz;";
	 public static final String PKADDRESS_TRUNCATE = "TRUNCATE TABLE pkadres;";
	 public static final String P7_TRUNCATE        = "TRUNCATE TABLE p7;";
	 public static final String P8_TRUNCATE        = "TRUNCATE TABLE p8;";
	 
	 
	 public static void createPkTables(){

			System.out.println("Start creating or resetting PK-Tables");
			
			try{
				EntityManagerFactory factory = Persistence.createEntityManagerFactory("pkimport");
				EntityManager em = factory.createEntityManager(); 
				em.getTransaction().begin();
				
				//System.out.println(PKKND);
				
				Query query = em.createNativeQuery(PKKND);  
				query.executeUpdate();  
				query = em.createNativeQuery(PKEIGKND);  
				query.executeUpdate();  
				query = em.createNativeQuery(PKBRP);  
				query.executeUpdate();  
				query = em.createNativeQuery(PKHUW);  
				query.executeUpdate();  
				query = em.createNativeQuery(PKADDRESS);  
				query.executeUpdate();  
				query = em.createNativeQuery(PKBYZ);  
				query.executeUpdate();  
				query = em.createNativeQuery(P7);  
				query.executeUpdate();  
				query = em.createNativeQuery(P8);  
				query.executeUpdate();  
				
				em.getTransaction().commit();	
	            em.clear();
				
				em.getTransaction().begin();
			
				query = em.createNativeQuery(PKKND_TRUNCATE);  
				query.executeUpdate();  
				query = em.createNativeQuery(PKEIGKND_TRUNCATE);  
				query.executeUpdate();  
				query = em.createNativeQuery(PKBRP_TRUNCATE);  
				query.executeUpdate();  
				query = em.createNativeQuery(PKHUW_TRUNCATE);  
				query.executeUpdate();  
				query = em.createNativeQuery(PKADDRESS_TRUNCATE);  
				query.executeUpdate();  
				query = em.createNativeQuery(PKBYZ_TRUNCATE);  
				query.executeUpdate();  
				query = em.createNativeQuery(P7_TRUNCATE);  
				query.executeUpdate();  
				query = em.createNativeQuery(P8_TRUNCATE);  
				query.executeUpdate();  
				
				em.getTransaction().commit();
	            em.clear();
			
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}

			
			System.out.println("Finished creating or resetting PK-Tables");


			}
	 
	 public static void createBTables(){

			System.out.println("Start creating or resetting B-Tables");
			
			
			try{
				EntityManagerFactory factory = Persistence.createEntityManagerFactory("pkdata");
				EntityManager em = factory.createEntityManager(); 
				em.getTransaction().begin();
				
				Query query = em.createNativeQuery(B2_ST);  
				query.executeUpdate();  
				query = em.createNativeQuery(B313_ST);  
				query.executeUpdate();  
				query = em.createNativeQuery(B32_ST);  
				query.executeUpdate();  
				query = em.createNativeQuery(B33_ST);  
				query.executeUpdate();  
				query = em.createNativeQuery(B34_ST);  
				query.executeUpdate();  
				query = em.createNativeQuery(B35_ST);  
				query.executeUpdate();  
				query = em.createNativeQuery(B36_ST);  
				query.executeUpdate();  
				query = em.createNativeQuery(B37_ST);  
				query.executeUpdate();  
				query = em.createNativeQuery(B4_ST);  
				query.executeUpdate();  
				query = em.createNativeQuery(B6_ST);  
				query.executeUpdate();  
				
				em.getTransaction().commit();
	            em.clear();
				
				em.getTransaction().begin();
			
				query = em.createNativeQuery(B2_ST_TRUNCATE);  
				query.executeUpdate();  
				query = em.createNativeQuery(B313_ST_TRUNCATE);  
				query.executeUpdate();  
				query = em.createNativeQuery(B32_ST_TRUNCATE);  
				query.executeUpdate();  
				query = em.createNativeQuery(B33_ST_TRUNCATE);  
				query.executeUpdate();  
				query = em.createNativeQuery(B34_ST_TRUNCATE);  
				query.executeUpdate();  
				query = em.createNativeQuery(B35_ST_TRUNCATE);  
				query.executeUpdate();  
				query = em.createNativeQuery(B36_ST_TRUNCATE);  
				query.executeUpdate();  
				query = em.createNativeQuery(B37_ST_TRUNCATE);  
				query.executeUpdate();  
				query = em.createNativeQuery(B4_ST_TRUNCATE);  
				query.executeUpdate();  
				query = em.createNativeQuery(B6_ST_TRUNCATE);  
				query.executeUpdate();  
				
				em.getTransaction().commit();
	            em.clear();

			
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}

			
			System.out.println("Finished creating or resetting B-Tables");

			}
	 
	 public static void createMTable(){

			System.out.println("Start creating or resetting Messages Table");
			
			try{
				EntityManagerFactory factory = Persistence.createEntityManagerFactory("pkmessages_create");				
				EntityManager em = factory.createEntityManager();				
				
				em.getTransaction().begin();
				Query query = em.createNativeQuery(BFOUT7FT);
				query.executeUpdate();
				em.getTransaction().commit();
	            em.clear();
				
				em.getTransaction().begin();
				query = em.createNativeQuery(BFOUT7FT_TRUNCATE);
				query.executeUpdate();
				em.getTransaction().commit();
	            em.clear();

				System.out.println("Finished creating or resetting Messages Table");

			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}

	 }
}