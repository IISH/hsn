package nl.iisg.ids;

public class CreatePersonTable {
	
	 public static final String PERSONS = 
		 
		 "CREATE TABLE IF NOT EXISTS persons" +
		 "(" +
		 "Id  INT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
		 "IDNR INT, " +
		 "Source VARCHAR(50), " +
		 "Id_I INT, " +
		 "Id_I_new VARCHAR(50), " +
		 "Start_code SMALLINT, " +
		 "Familyname VARCHAR(80), " +
		 "Prefix_Familyname VARCHAR(15), " +
		 "Firstname VARCHAR(80), " +
		 "Sex CHAR(1), " +
		 "Original_Relation_RP VARCHAR(100), " +
		 "Relation_RP VARCHAR(100), " +
		 "Function VARCHAR(20), " +
		 "Birthday TINYINT, " +
		 "Birthmonth TINYINT, " +
		 "Birthyear SMALLINT, " +
		 "Birthstartday TINYINT, " +
		 "Birthstartmonth TINYINT, " +
		 "Birthstartyear SMALLINT, " +
		 "Birthendday TINYINT, " +
		 "Birthendmonth TINYINT, " +
		 "Birthendyear SMALLINT, " +
		 "Id_B_C INT, " +
		 "Deathday TINYINT, " +
		 "Deathmonth TINYINT, " +
		 "Deathyear SMALLINT, " +
		 "Id_D_C INT " +
		 " ); ";

 public static final String PERSON_TRUNCATE = "TRUNCATE TABLE persons;"; 

}
