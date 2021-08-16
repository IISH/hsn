package nl.iisg.ids03;

public class CreateMTables {
	
	 public static final String BFOUT1FT = 
		 
		 
		 "create table IF NOT EXISTS  bfout1ft" +
		 "(" +
		 
		 " RecordID INT AUTO_INCREMENT, " +
		 " Primary Key(RecordID), " +

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
		 "FOUT         varchar(511)" +
		 

		 ");"; 
	 
	 public static final String BFOUT4FT = 

	 "create table IF NOT EXISTS  bfout4ft" +
	 "(" +
	 
	 " RecordID INT AUTO_INCREMENT, " +
	 " Primary Key(RecordID), " +

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
	 "FOUT         varchar(255)" +
	 

	 ");"; 

	 public static final String BFOUT1FT_TRUNCATE = "TRUNCATE TABLE bfout1ft;"; 
	 public static final String BFOUT4FT_TRUNCATE = "TRUNCATE TABLE bfout4ft;"; 


}
