package iisg.nl.hsnmigrate;

public class CreateDBLog {

	
	 public static final String log =
		 
		 
	        "CREATE TABLE IF NOT EXISTS bfout5ft" +
	        "(" +	       
	        "    DB       VARCHAR(15), " +
	        "    Tbl      VARCHAR(10), " +
	        "    IDNR     INT, " +
	        "    Year     INT, " +
	        "    Errcode  INT, " +
	        "    Message  VARCHAR(" + Const.XBigstring + "), " +
	        "    RecordID INT NOT NULL AUTO_INCREMENT, " +
	        "    Primary Key(RecordID)" +
	        ") Engine=InnoDB;";
	 
	 public static final String logSummary =
	        
	        "CREATE TABLE IF NOT EXISTS summary" +
	        "(" +
	        "    PROV       SMALLINT, " +
	        "    3GS        VARCHAR(1), " +
	        "    COH        VARCHAR(12), " +
	        "    GEB        INT, " +
	        "    OVL        INT, " +
	        "    OVL16      INT, " +
	        "    OVL16_     FLOAT(5,2), " +
	        "    PK         INT, " +
	        "    OVLPK      INT, " +
	        "    OVLITV_    FLOAT(5,2), " +
	        "    OVLD       INT, " +
	        "    OVLD_      FLOAT(5,2), " +
	        "    VOLW       INT, " +
	        "    HUW        INT, " +
	        "    HUW1       INT, " +
	        "    HUW2_      FLOAT(5,2), " +
	        "    HUWITV     INT, " +
	        "    HUWITV_    FLOAT(5,2), " +
	        "    TOT        INT, " +
	        "    TOTD       INT, " +
	        "    PK500KPLUS INT, " +
	        "    RecordID   INT NOT NULL AUTO_INCREMENT, " +
	        "    Primary Key(RecordID)" +
	        ") Engine=InnoDB;";
	 
	 public static final String log_truncate = "TRUNCATE TABLE bfout5ft";
	 public static final String logSummary_truncate = "TRUNCATE TABLE summary";

		 
	
	
}
