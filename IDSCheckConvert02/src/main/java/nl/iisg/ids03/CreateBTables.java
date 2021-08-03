package nl.iisg.ids03;

public class CreateBTables {
	
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

		 " FAMILYNAME VARCHAR(" + ConstTables.Bigstring + "), " +
		 " PREFIX_FAMILYNAME VARCHAR(" + ConstTables.Smallstring + "), " +
		 " TITLE_NOBLE VARCHAR(" + ConstTables.Smallstring + "), " + 
		 " TITLE_ELSE VARCHAR(" + ConstTables.Smallstring + "),  " +
		 " FAMILYNAME_FG TINYINT, " +

		 " FIRSTNAME VARCHAR(" + ConstTables.Bigstring + "), " +
		 " FIRSTNAME_FG TINYINT, " +
		 " SEX CHAR(1), " +

		 " B2GDNR CHAR(10), " +
		 " B2GDFG TINYINT, " +

		 " BIRTH_LOCALITY_ID int, " +
		 " BIRTH_LOCALITY_ST VARCHAR(" + ConstTables.Bigstring + "), " + 
		 " BIRTH_LOCALITY_FG TINYINT, " +

		 " B2ODNR CHAR(10), " +
		 " B2ODFG TINYINT, " +

		 " DEATH_LOCALITY_ID int, " +
		 " DEATH_LOCALITY_ST VARCHAR(" + ConstTables.Bigstring + "), " + 
		 " DEATH_LOCALITY_FG TINYINT, " +

		 " NATIONALITY VARCHAR(" + ConstTables.Bigstring + "), " +

		 " LEGAL_LIVPL_ID int, " +
		 " LEGAL_LIVPL_ST VARCHAR(" + ConstTables.Bigstring + "), " + 
		 " LEGAL_LIVPL_FG TINYINT,   " +
		 " LEGAL_LIVPL_CODE VARCHAR(2), " +

		 " REMARKS VARCHAR(" + ConstTables.XBigstring + "), " +
		 " ADDITION VARCHAR(" + ConstTables.XBigstring + "), " +

		 " VERSIE VARCHAR(8), " +
		 " ONDRZKO VARCHAR(8), " +
		 " VERSIEO VARCHAR(8), " +
		 " DATUM VARCHAR(10) " +
		 " ); ";

	 
	 public static final String B311_ST = 
		 
		" CREATE TABLE IF NOT EXISTS b311_st " +
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
		 " B2FCBG SMALLINT, " +
		 " B3MDNR CHAR(10), " +
		 " B3MDFG TINYINT, " + 
		 " B3GEGEVEN VARCHAR(" + ConstTables.XBigstring + "), " +

		 " VERSIE VARCHAR(8), " +
		 " ONDRZKO VARCHAR(8), " +
		 " VERSIEO VARCHAR(8), " +
		 " DATUM VARCHAR(10) " +
		 " 		 ); ";
		 
		 
		 
	 public static final String B312_ST =
	 
	 " CREATE TABLE IF NOT EXISTS b312_st " +
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
	 " RELATION_FG TINYINT, " + 
	 " B2FCBG SMALLINT, " +
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
	 " B2FCBG SMALLINT, " +
	 " B3MDNR CHAR(10), " +
	 " B3MDFG TINYINT, " + 
	 " CIVIL_LOCALITY_ID INT, " +
	 " CIVIL_LOCALITY_ST VARCHAR(" + ConstTables.Bigstring + "), " +
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
	 " B2FCBG SMALLINT, " +
	 " B3MDNR CHAR(10), " +
	 " B3MDFG TINYINT, " + 

	 " RELIGION_ID INT, " +
	 " RELIGION_ST VARCHAR(" + ConstTables.Bigstring + "), " +
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
	 " B2FCBG SMALLINT, " +
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
	 " B2FCBG SMALLINT, " +
	 " B3MDNR CHAR(10), " +
	 " B3MDFG TINYINT, " + 

	 " OCCUPATION_ID INT, " +
	 " OCCUPATION_ST VARCHAR(" + ConstTables.Bigstring + "), " +
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
	 " B2FCBG SMALLINT, " +
	 " B3MDNR CHAR(10), " +
	 " B3MDFG TINYINT, " + 

	 " ORIGIN_ID INT, " +
	 " ORIGIN_ST VARCHAR(" + ConstTables.Bigstring + "), " +
	 " ORIGIN_FG TINYINT, " +

	 " ORIGIN_EQUAL TINYINT, " + 

	 " ADDRESS VARCHAR(" + ConstTables.Bigstring + "), " +
	 " REGISTER VARCHAR(" + ConstTables.Bigstring + "), " +
	 " CENSUS VARCHAR(" + ConstTables.Bigstring + "), " +


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
	 " B2FCBG SMALLINT, " +
	 " B3MDNR CHAR(10), " +
	 " B3MDFG TINYINT, " + 

	 " DESTINATION_ID INT, " +
	 " DESTINATION_ST VARCHAR(" + ConstTables.Bigstring + "), " +
	 " DESTINATION_FG TINYINT, " +

	 " DESTINATION_EQUAL TINYINT, " + 

	 " ADDRESS VARCHAR(" + ConstTables.Bigstring + "), " +
	 " REGISTER VARCHAR(" + ConstTables.Bigstring + "), " +
	 " CENSUS VARCHAR(" + ConstTables.Bigstring + "), " +


	 " VERSIE VARCHAR(8), " +
	 " ONDRZKO VARCHAR(8), " +
	 " VERSIEO VARCHAR(8), " +
	 " DATUM VARCHAR(10) " +
	 " );";

	 public static final String B4_ST = 
	 
	 " CREATE TABLE IF NOT EXISTS b4_st" +
	 " (" +

	 " B1IDBG INT, " +
	 " B2DIBG CHAR(10), " +
	 " IDNR INT, " + 

	 " B2FDBG CHAR(10), " + 

	 " REGISTER_PAGE VARCHAR(" + ConstTables.Smallstring + "), " + 
	 " REGISTER_LINE SMALLINT, " + 
	 " NAME_HEAD_GK VARCHAR(" + ConstTables.Bigstring + "), " + 
	 " SPECIAL_CODE VARCHAR(" + ConstTables.Bigstring + "), " + 
	 " SPECIAL_REMARKS VARCHAR(" + ConstTables.XBigstring + "), " + 

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

		 " MUNICIPALITY_ST VARCHAR("+ ConstTables.Bigstring + "), " + 

		 " ADDRESS_ID INT, " +
		 " STREET_ST VARCHAR(" + ConstTables.Bigstring + "), " + 
		 " QUARTER_ST VARCHAR(" + ConstTables.Bigstring + "), " + 
		 " PLACE_ST VARCHAR(" + ConstTables.Bigstring + "), " + 
		 " BOAT_ST VARCHAR(" + ConstTables.Bigstring + "), " +
		 " BERTH_ST VARCHAR(" + ConstTables.Bigstring + "), " +
		 " INSTIT_ST VARCHAR(" + ConstTables.Bigstring + "), " +
		 " LANDLORD_ST VARCHAR(" + ConstTables.Bigstring + "), " +
		 " OTHER_ST VARCHAR(" + ConstTables.Bigstring + "), " +
		 " ADDRESS_FG TINYINT, " + 

		 " NUMBER_ST VARCHAR(" + ConstTables.Smallstring + "), " +
		 " ADDITION_ST VARCHAR(" + ConstTables.Smallstring + "), " +

		 " VERSIE VARCHAR(8), " +
		 " ONDRZKO VARCHAR(8), " +
		 " VERSIEO VARCHAR(8), " +
		 " DATUM VARCHAR(10) " +
		 "  );";
	 
	 public static final String B2_ST_TRUNCATE = "TRUNCATE TABLE b2_st;";
	 public static final String B311_ST_TRUNCATE = "TRUNCATE TABLE b311_st;";
	 public static final String B312_ST_TRUNCATE = "TRUNCATE TABLE b312_st;";
	 public static final String B32_ST_TRUNCATE = "TRUNCATE TABLE b32_st;";
	 public static final String B33_ST_TRUNCATE = "TRUNCATE TABLE b33_st;";
	 public static final String B34_ST_TRUNCATE = "TRUNCATE TABLE b34_st;";
	 public static final String B35_ST_TRUNCATE = "TRUNCATE TABLE b35_st;";
	 public static final String B36_ST_TRUNCATE = "TRUNCATE TABLE b36_st;";
	 public static final String B37_ST_TRUNCATE = "TRUNCATE TABLE b37_st;";
	 public static final String B4_ST_TRUNCATE = "TRUNCATE TABLE b4_st;";
	 public static final String B6_ST_TRUNCATE = "TRUNCATE TABLE b6_st;";
	 
	 
}
