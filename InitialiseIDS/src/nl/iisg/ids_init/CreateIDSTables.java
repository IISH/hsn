package nl.iisg.ids_init;

public class CreateIDSTables {
	

	 public static final String CONTEXT =  
	 
	 		"CREATE TABLE IF NOT EXISTS context" + 
	 		"(" + 
	 		"Id  INT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +  
	        "Id_D VARCHAR(50), " +
	 		"Id_C INT, " + 		
	 		"Source VARCHAR(50), " +  
	 		"Type VARCHAR (50), " +  
	 		"Value VARCHAR(100), " +
	 		
			"Date_type VARCHAR(8), " +
			"Estimation VARCHAR(50), " + 
			"Day TINYINT,  " + 
			"Month TINYINT,  " + 
			"Year SMALLINT,  " +
			"Hour TINYINT,  " + 
			"Minute TINYINT,  " + 
			"Start_day TINYINT,  " + 
			"Start_month TINYINT,  " + 
			"Start_year SMALLINT,  " + 
			"End_day TINYINT,  " + 
			"End_month TINYINT,  " + 
			"End_year SMALLINT,  " + 
	        "Missing VARCHAR(50)" +
	        " );"; 
	 
	 public static final String CONTEXT_CONTEXT =  
			 
				"CREATE TABLE IF NOT EXISTS context_context" + 
				"(" + 
				"Id  INT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +  
		        "Id_D VARCHAR(50), " +
				"Id_C_1 INT, " + 		
				"Id_C_2 INT, " + 		
				"Source VARCHAR(50), " +
		        "Relation VARCHAR(100), " +

				"Date_type VARCHAR(8), " +
				"Estimation VARCHAR(50), " + 
				"Day TINYINT,  " + 
				"Month TINYINT,  " + 
				"Year SMALLINT,  " +
				"Hour TINYINT,  " + 
				"Minute TINYINT,  " + 
				"Start_day TINYINT,  " + 
				"Start_month TINYINT,  " + 
				"Start_year SMALLINT,  " + 
				"End_day TINYINT,  " + 
				"End_month TINYINT,  " + 
				"End_year SMALLINT,  " + 
				"Missing VARCHAR(50)" +
				" );"; 

}
