package nl.iisg.ids03;

public class CreateOriginalBTables {
	
	public static final String B2 =
		
		"CREATE TABLE IF NOT EXISTS b2" +
		 "(" +
		 " B1IDBG INT, " +
		 " B2DIBG TINYINT, " + 
		 " B2MIBG TINYINT, " + 
		 " B2JIBG SMALLINT, " + 
		 " IDNR INT, " +
		 " B2RNBG SMALLINT, " +
		 
		 " B2FCBG SMALLINT, " +
		 " B2RDNR TINYINT, " +
		 " B2RMNR TINYINT, " +
		 " B2RJNR SMALLINT, " +
		 " B2RDCR TINYINT, " +
		 " B2RMCR TINYINT, " +
		 " B2RJCR SMALLINT, " +
		 " B2ANNR VARCHAR(" + ConstTables.Bigstring + "), " +
		 " B2VNNR VARCHAR(" + ConstTables.Bigstring + "), " +
		 " B2GSNR CHAR(1), " +

		 " B2GDNR TINYINT, " +
		 " B2GMNR TINYINT, " +
		 " B2GJNR SMALLINT, " +
		 " B2GDCR TINYINT, " +
		 " B2GMCR TINYINT, " +
		 " B2GJCR SMALLINT, " +
		 " B2GNNR VARCHAR(" + ConstTables.Bigstring + "), " + 

		 " B2ODNR TINYINT, " +
		 " B2OMNR TINYINT, " +
		 " B2OJNR SMALLINT, " +
		 " B2ODCR TINYINT, " +
		 " B2OMCR TINYINT, " +
		 " B2OJCR SMALLINT, " +
		 " B2ONNR VARCHAR(" + ConstTables.Bigstring + "), " + 

		 " B2NANR VARCHAR(" + ConstTables.Bigstring + "), " +
		 " B2WDNR VARCHAR(" + ConstTables.Bigstring + "), " +
		 " B2VWNR VARCHAR(" + ConstTables.Bigstring + "), " +
		 " B2AANR VARCHAR(" + ConstTables.XBigstring + "), " +
		 " B2AAN  VARCHAR(" + ConstTables.XBigstring + "), " +

		 
		 " OPDRNR  VARCHAR(8), " +
		 " DATUM   VARCHAR(10), " +
		 " INIT    VARCHAR(8), " +
		 " VERSIE  VARCHAR(8), " +
		 " ONDRZKO VARCHAR(8), " +
		 " OPDRNRO VARCHAR(8), " +
		 " DATUMO  VARCHAR(10), " +
		 " INITO   VARCHAR(8), " +
		 " VERSIEO VARCHAR(8) " +

		 //" UNIQUE i_b2 (B1IDBG, B2DIBG, B2MIBG, B2JIBG, IDNR, B2RNBG) " +
		 
		 " ); ";
		 
	public static final String B3 = 
		
		"CREATE TABLE IF NOT EXISTS b3" +
		 "(" +
		 " B1IDBG INT, " +
		 " B2DIBG TINYINT, " + 
		 " B2MIBG TINYINT, " + 
		 " B2JIBG SMALLINT, " + 
		 " IDNR INT, " +
		 " B2RNBG SMALLINT, " +
		 
		 " B3TYPE TINYINT, " +
		 " B3VRNR SMALLINT, " +
		 
		 " B3KODE SMALLINT, " +
		 " B3RGLN SMALLINT, " +
		 " B2FCBG SMALLINT, " +

		 " B3MDNR TINYINT, " + 
		 " B3MMNR TINYINT, " + 
		 " B3MJNR SMALLINT, " + 
		 " B3MDCR TINYINT, " + 
		 " B3MMCR TINYINT, " + 
		 " B3MJCR SMALLINT, " + 

		 " B3GEGEVEN VARCHAR(" + ConstTables.XBigstring + "), " +
	
		 " OPDRNR  VARCHAR(8), " +
		 " DATUM   VARCHAR(10), " +
		 " INIT    VARCHAR(8), " +
		 " VERSIE  VARCHAR(8), " +
		 " ONDRZKO VARCHAR(8), " +
		 " OPDRNRO VARCHAR(8), " +
		 " DATUMO  VARCHAR(10), " +
		 " INITO   VARCHAR(8), " +
		 " VERSIEO VARCHAR(8) " +

		 //" UNIQUE i_b3 (B1IDBG, B2DIBG, B2MIBG, B2JIBG, IDNR, B2RNBG, B3TYPE, B3VRNR) " +

		 
		 " ); ";
	
	public static final String B4 = 
		
		"CREATE TABLE IF NOT EXISTS b4" +
		 "(" +
		 " B1IDBG INT, " +
		 " B2DIBG TINYINT, " + 
		 " B2MIBG TINYINT, " + 
		 " B2JIBG SMALLINT, " + 
		 " IDNR INT, " +

		 " B2FDBG TINYINT, " + 
		 " B2FMBG TINYINT, " + 
		 " B2FJBG SMALLINT, " +

		 " B2PGBG VARCHAR(" + ConstTables.Smallstring + "), " +
		 " B2VHBG SMALLINT, " +
		 " B4GKBG VARCHAR(" + ConstTables.Bigstring + "), " +
		 " B4SPBG VARCHAR(" + ConstTables.Bigstring + "), " +
		 " B4AAN  VARCHAR(" + ConstTables.XBigstring + "), " +
	
		 " OPDRNR  VARCHAR(8), " +
		 " DATUM   VARCHAR(10), " +
		 " INIT    VARCHAR(8), " +
		 " VERSIE  VARCHAR(8), " +
		 " ONDRZKO VARCHAR(8), " +
		 " OPDRNRO VARCHAR(8), " +
		 " DATUMO  VARCHAR(10), " +
		 " INITO   VARCHAR(8), " +
		 " VERSIEO VARCHAR(8) " +
		 
		 //" UNIQUE i_b4 (B1IDBG, B2DIBG, B2MIBG, B2JIBG, IDNR) " +


		 " ); ";
	
	public static final String B6 = 
		
		"CREATE TABLE IF NOT EXISTS b6" +
		 "(" +
		 " B1IDBG INT, " +
		 " B2DIBG TINYINT, " + 
		 " B2MIBG TINYINT, " + 
		 " B2JIBG SMALLINT, " + 
		 " IDNR INT, " +

		 " B2RNBG SMALLINT, " +
		 " B6VRNR SMALLINT, " +
		 
		 " B6MDNR TINYINT, " + 
		 " B6MMNR TINYINT, " + 
		 " B6MJNR SMALLINT, " +
		 " B6MDCR TINYINT, " + 
		 " B6MMCR TINYINT, " + 
		 " B6MJCR SMALLINT, " +

		 " B6TPNR CHAR(2), " +
		 " B6SINR SMALLINT, " +
		 " B6STNR VARCHAR(" + ConstTables.Bigstring + "), " +
		 " B6NRNR VARCHAR(" + ConstTables.Smallstring + "), " +
		 " B6TVNR VARCHAR(" + ConstTables.Smallstring + "), " +
	
		 " OPDRNR  VARCHAR(8), " +
		 " DATUM   VARCHAR(10), " +
		 " INIT    VARCHAR(8), " +
		 " VERSIE  VARCHAR(8), " +
		 " ONDRZKO VARCHAR(8), " +
		 " OPDRNRO VARCHAR(8), " +
		 " DATUMO  VARCHAR(10), " +
		 " INITO   VARCHAR(8), " +
		 " VERSIEO VARCHAR(8) " +
		 
		 //" UNIQUE i_b6 (B1IDBG, B2JIBG, B2MIBG, B2DIBG, IDNR, B2RNBG, B6VRNR) " +


		 " ); ";
	
	public static final String B2_TRUNCATE = "TRUNCATE TABLE b2;";
	public static final String B3_TRUNCATE = "TRUNCATE TABLE b3;";
	public static final String B4_TRUNCATE = "TRUNCATE TABLE b4;";
	public static final String B6_TRUNCATE = "TRUNCATE TABLE b6;";
	
}
