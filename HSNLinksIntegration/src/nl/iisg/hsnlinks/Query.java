package nl.iisg.hsnlinks;

import java.io.DataOutputStream;

import javax.swing.JTextArea;

import com.mysql.jdbc.Connection;

public class Query {

    

	
	public static final String createSourcesCertificates =
			
    " CREATE TABLE IF NOT EXISTS xxx_ids.sources_certificates(" + 
    " ID   INT NOT NULL AUTO_INCREMENT, " +  
	" Id_C INT," + 
//	" LEVEL VARCHAR(6)," + 
//  " NAME CHAR(1)," + 
    " SOURCE CHAR(1)," + 
	" MUNICIPALITY VARCHAR(50)," + 
	" YEAR CHAR(4)," + 
	" SEQUENCE_NUMBER CHAR(10)," +  
	" PRIMARY KEY(ID))"; 

	public static final String populateSourcesCertificates =
			
			
	" insert into xxx_ids.sources_certificates" + 
//  " (Id_C, LEVEL, NAME, MUNICIPALITY, YEAR, SEQUENCE_NUMBER)" + 
    " (Id_C, SOURCE, MUNICIPALITY, YEAR, SEQUENCE_NUMBER)" + 
	" SELECT c1.Id_C, " +  
	" MAX(IF(c1.Type = 'NAME' , SUBSTR(c1.Value, 1, 1), null)) as SOURCE," + 
    " MAX(IF(c2.Type = 'NAME', SUBSTR(c2.Value, 1, 50), null)) as MUNICIPALITY," + 
    " MAX(IF(c1.Type = 'PERIOD', SUBSTR(c1.Value, 1, 4), null)) as YEAR," +  
	" MAX(IF(c1.Type = 'SEQUENCE_NUMBER', SUBSTR(c1.Value, 1, 10), null)) as SEQUENCE_NUMBER" + 
    " FROM" + 
	" xxx_ids.context as c1," +  
	" xxx_ids.context as c2," + 
	" xxx_ids.context_context as cc" + 
	" where" +  
	" cc.Relation = 'Source and Municipality'" +  
	" and" +  
	" c1.id_c = cc.id_c_1 and " + 
	" c2.id_c = cc.id_c_2" +  
	" group by(c1.id_C)"; 		
			

	
	public static final String createPersonStatic =
	
	" CREATE TABLE IF NOT EXISTS xxx_ids.persons_static" +
	" (" + 
	" ID                 INT NOT NULL AUTO_INCREMENT," + 
	" Id_I               INT," + 
	" LASTNAME           VARCHAR(60)," +
	" PREFIX_LASTNAME    VARCHAR(15)," +
	" FIRSTNAME          VARCHAR(40)," +
	" SEX                CHAR(1)," + 
	" BIRTH_LOCATION     INT,"  +
	" BIRTH_DAY_MIN      INT," +
	" BIRTH_MONTH_MIN    INT," +
	" BIRTH_YEAR_MIN     INT," +
	" BIRTH_DAY_MAX      INT," +
	" BIRTH_MONTH_MAX    INT," +   
	" BIRTH_YEAR_MAX     INT," +
	" DEATH_LOCATION     INT," +   
	" DEATH_DAY_MIN      INT," +
	" DEATH_MONTH_MIN    INT," + 
	" DEATH_YEAR_MIN     INT," +
	" DEATH_DAY_MAX      INT," +
	" DEATH_MONTH_MAX    INT," + 
	" DEATH_YEAR_MAX     INT," +
	" PRIMARY KEY(ID))";

	public static final String populatePersonStatic =
	

	" INSERT INTO xxx_ids.persons_static " + 

	" (" + 
	" Id_I," +  
	" LASTNAME," + 
	" PREFIX_LASTNAME," + 
	" FIRSTNAME," +  
	" SEX," + 
	" BIRTH_LOCATION," + 
	" BIRTH_DAY_MIN," + 
	" BIRTH_DAY_MAX," + 
	" BIRTH_MONTH_MIN," + 
	" BIRTH_MONTH_MAX," + 
	" BIRTH_YEAR_MIN," + 
	" BIRTH_YEAR_MAX," + 
	" DEATH_LOCATION," + 
	" DEATH_DAY_MIN," + 
	" DEATH_DAY_MAX," + 
	" DEATH_MONTH_MIN," + 
	" DEATH_MONTH_MAX," + 
	" DEATH_YEAR_MIN," + 
	" DEATH_YEAR_MAX" + 
	" )" + 

	" SELECT" +
	
	" Id_I," + 
	" MAX(IF(i.Type = 'LAST_NAME', SUBSTR(i.Value,1,60), null)) as LASTNAME,"+ 
	" MAX(IF(i.Type = 'PREFIX_LAST_NAME', SUBSTR(i.Value,1,15), null)) as PREFIX_LASTNAME,"+ 
	" MAX(IF(i.Type = 'FIRST_NAME', SUBSTR(i.Value,1,40), null)) as FIRSTNAME,"+ 
	" MAX(IF(i.Type = 'SEX', SUBSTR(i.Value,1,1), null)) as SEX," + 
	" MAX(IF(i.Type = 'BIRTH_LOCATION', i.Id_C, null)) as BIRTH_LOCATION," + 
	" MAX(IF(i.Type = 'BIRTH_DATE', IF(i.Start_day != 0, i.Start_day, i.day),null)) as BIRTH_DAY_MIN," + 
	" MAX(IF(i.Type = 'BIRTH_DATE', IF(i.End_day != 0, i.End_day, i.day),null)) as BIRTH_DAY_MAX," + 
	" MAX(IF(i.Type = 'BIRTH_DATE', IF(i.Start_month != 0, i.Start_month, i.month),null)) as BIRTH_MONTH_MIN," + 
	" MAX(IF(i.Type = 'BIRTH_DATE', IF(i.End_month != 0, i.End_month, i.month),null)) as BIRTH_MONTH_MAX," + 
	" MAX(IF(i.Type = 'BIRTH_DATE', IF(i.Start_year != 0, i.Start_year, i.year),null)) as BIRTH_YEAR_MIN," + 
	" MAX(IF(i.Type = 'BIRTH_DATE', IF(i.End_year != 0, i.End_year, i.year),null)) as BIRTH_YEAR_MAX," + 
	" MAX(IF(i.Type = 'DEATH_LOCATION', i.Id_C, null)) as DEATH_LOCATION," + 
	" MAX(IF(i.Type = 'DEATH_DATE', IF(i.Start_day != 0, i.Start_day, i.day),null)) as DEATH_DAY_MIN," + 
	" MAX(IF(i.Type = 'DEATH_DATE', IF(i.End_day != 0, i.End_day, i.day),null)) as DEATH_DAY_MAX," + 
	" MAX(IF(i.Type = 'DEATH_DATE', IF(i.Start_month != 0, i.Start_month, i.month),null)) as DEATH_MONTH_MIN," + 
	" MAX(IF(i.Type = 'DEATH_DATE', IF(i.End_month != 0, i.End_month, i.month),null)) as DEATH_MONTH_MAX," + 
	" MAX(IF(i.Type = 'DEATH_DATE', IF(i.Start_year != 0, i.Start_year, i.year),null)) as DEATH_YEAR_MIN," + 
	" MAX(IF(i.Type = 'DEATH_DATE', IF(i.End_year != 0, i.End_year, i.year),null)) as DEATH_YEAR_MAX" +  

	" FROM  xxx_ids.individual as i" + 

	" group by i.id_i";
	
	public static final String createKeyHSNLinks =
			
			
	" CREATE TABLE IF NOT EXISTS hsn_ids.key_hsn_links(" + 
	" ID              INT NOT NULL AUTO_INCREMENT," +  
	" Id_I_HSN 	INT," +               
	" IDNR     	INT," + 
	" PERSON_HSN      INT," + 
	" ID_I_LINKS      INT," +  
	" Relation            VARCHAR(40)," + 
    " HSN_RELEASE     VARCHAR(3)," + 
	" LINKS_RELEASE   VARCHAR(3)," + 
	" PRIMARY KEY(ID))";
			
			
			
	public static final String populateKeyHSNLinks =

	" insert into hsn_ids.key_hsn_links" +  
	" ( Id_I_HSN, IDNR, PERSON_HSN, RELATION, ID_I_LINKS)" +   
	" select distinct" + 

	" pc.id_person_o             as Id_I_HSN," + 
	" SUBSTR(pc.id_person_o,2,6) as IDNR, " +
	" SUBSTR(pc.id_person_o,8,3) as PERSON_HSN," + 
	" IF(SUBSTR(pc.id_person_o,8,3) = '001', 'RP', IF(SUBSTR(pc.id_person_o,8,3) = '002', 'Mother', IF(SUBSTR(pc.id_person_o,8,3) = '012', 'Father', SUBSTR(pc.id_person_o,8,3)))) as RELATION," +  
	" pn.person_number           as ID_I_LINKS" +  

	" from " +
	" links_cleaned.person_c_2014_05_07  as pc," + 
	" links_ids.personNumbers as pn" +

	" where" +
	" pc.id_source = 10 and" +
	" pn.person_number NOT IN (71941747, 64541868, 64541867, 71906359, 71974403) and " +
	" pc.id_person = pn.id_person and" + 
	" SUBSTR(pc.id_person_o,2,6) >= 70000 and  SUBSTR(pc.id_person_o,2,6) <  80000"; 
			
			
	public static final String createCertificatesHSNRP =
			

	" CREATE TABLE IF NOT EXISTS hsn_ids.certificates_hsn_rp(" + 
	" ID              INT NOT NULL AUTO_INCREMENT," + 
	" IDNR     	INT," +
	" ID_I_HSN 	INT," +              
	" ID_I_LINKS      INT," +
	" Id_C_HSN        INT, " +
	" ID_C_LINKS      INT," +
	" PRIMARY KEY(ID)" +
	" )";
			
			
	public static final String populateCertificatesHSNRP =
	
	" insert into hsn_ids.certificates_hsn_rp" +  
	" (IDNR, ID_I_HSN, ID_I_LINKS, ID_C_HSN, ID_C_LINKS)" +   
	" select" +
	" khl.IDNR        as IDNR," +
	" khl.ID_I_HSN    as ID_I_HSN," +
	" khl.ID_I_LINKS  as ID_I_LINKS," +
	" hic.ID_C        as ID_C_HSN," +
	" lic.ID_C        as ID_C_LINKS" + 

	" from" +   
	" hsn_ids.key_hsn_links   as khl," +
	" hsn_ids.indiv_context   as hic," +
	" links_ids.indiv_context as lic" +

	" where " +
	" khl.Relation = 'Child' and" +
	" khl.ID_I_HSN = hic.Id_I and" +
	" khl.ID_I_LINKS = lic.Id_I and" +
	" LENGTH(hic.Relation) > 0 and" +
	" hic.Relation NOT LIKE 'LIVING_LOCATION%'" ;
	
	
	public static final String createOthersHSNLinks =
			
    " CREATE TABLE IF NOT EXISTS hsn_ids.other_hsn_links(" +  
	" ID        INT NOT NULL AUTO_INCREMENT," +
	" Id_I_HSN 	INT," +              
	" IDNR     	INT," +
	" PERSON_HSN      INT," + 
	" ID_I_LINKS      INT," + 
	" Relation            VARCHAR(60)," +
	" PRIMARY KEY(ID)" +
	" )";
	
	public static final String populateOthersHSNLinks =
			
	" INSERT INTO hsn_ids.other_hsn_links_&i" +
	" ( Id_I_HSN, IDNR, PERSON_HSN, RELATION, ID_I_LINKS)" +  

	" SELECT DISTINCT" +

	" null       as ID_I_HSN," +
	" khl.IDNR   as IDNR," +
	" 0          as PERSON_HSN," +
	" '&relation1'    as RELATION," +
	" lii.Id_i_1  as id_i_links " +
			 
	" FROM" +
	" hsn_ids.key_hsn_links as khl," + 
	" links_ids.indiv_indiv as lii" +
	" where " +
	" khl.PERSON_HSN = &person and" +
	" khl.id_i_links = lii.id_i_2 and " +
	" lii.Relation = '&relation2'";
	
	public static final String populateOthersHSNLinks2 =
			
	" INSERT INTO hsn_ids.other_hsn_links_1" +
	" ( Id_I_HSN, IDNR, PERSON_HSN, RELATION, ID_I_LINKS)" +  

	" SELECT DISTINCT" +

	" null        as ID_I_HSN," +
	" ohl2.IDNR   as IDNR," +
	" 0           as PERSON_HSN," +
	" '&relation1'     as RELATION," +
	" lii.Id_i_1  as id_i_links " +
			 
	" FROM" +
	" hsn_ids.other_hsn_links_2 as ohl2," + 
	" links_ids.indiv_indiv as lii" +
	" where " +
	" ohl2.id_i_links = lii.id_i_2 and " + 
	" lii.Relation = '&relation2'";
	
	public static final String cleanOthersHSNLinks =
			
	" delete from hsn_ids.other_hsn_links_1 " +
	" where exists(" +  
	"     select * from hsn_ids.key_hsn_links" +
	"     where hsn_ids.other_hsn_links_1.ID_I_LINKS = hsn_ids.key_hsn_links.ID_I_LINKS" +
	" )";
	
	public static final String createIntegratedPersons =
			
			" CREATE TABLE IF NOT EXISTS hsn_ids.integrated_persons" +
			" (" + 
			" ID                 INT NOT NULL AUTO_INCREMENT," + 
			" Source_            VARCHAR(5)," + 
			" IDNR               INT," + 
			" RELATION               VARCHAR(60)," + 
			" Id_I               INT," + 
			" LASTNAME           VARCHAR(60)," +
			" FIRSTNAME          VARCHAR(40)," +
			" SEX                CHAR(1)," + 
			" BIRTH_LOCATION     INT,"  +
			" BIRTH_DAY_MIN      INT," +
			" BIRTH_MONTH_MIN    INT," +
			" BIRTH_YEAR_MIN     INT," +
			" BIRTH_DAY_MAX      INT," +
			" BIRTH_MONTH_MAX    INT," +   
			" BIRTH_YEAR_MAX     INT," +
			" DEATH_LOCATION     INT," +   
			" DEATH_DAY_MIN      INT," +
			" DEATH_MONTH_MIN    INT," + 
			" DEATH_YEAR_MIN     INT," +
			" DEATH_DAY_MAX      INT," +
			" DEATH_MONTH_MAX    INT," + 
			" DEATH_YEAR_MAX     INT," +
			" PRIMARY KEY(ID))";
	
	
	public static final String insertLINKS =
			
			" INSERT INTO hsn_ids.integrated_persons" + 
			" (Source_, IDNR, RELATION, Id_I, FIRSTNAME, LASTNAME, SEX, BIRTH_LOCATION, BIRTH_DAY_MIN, BIRTH_MONTH_MIN, BIRTH_YEAR_MIN, "+ 
			" BIRTH_DAY_MAX, BIRTH_MONTH_MAX, BIRTH_YEAR_MAX, DEATH_LOCATION, DEATH_DAY_MIN, DEATH_MONTH_MIN, DEATH_YEAR_MIN, DEATH_DAY_MAX, DEATH_MONTH_MAX, DEATH_YEAR_MAX)" + 		
			" SELECT" +
	        " 'LINKS' as source_,  " + 
			" ohl.idnr," +
			" ohl.relation as relation," +
			" lps.id_i as id_i," +
			" lps.firstname, " +
			" lps.lastname, " +
			" lps.sex, " +
			" lps.birth_location, " +
			" lps.birth_day_min, " +
			" lps.birth_month_min, " +
			" lps.birth_year_min, " +
			" lps.birth_day_max, " +
			" lps.birth_month_max, " +
			" lps.birth_year_max, " +
			" lps.death_location, " +
			" lps.death_day_min, " +
			" lps.death_month_min, " +
			" lps.death_year_min, " +
			" lps.death_day_max, " +
			" lps.death_month_max, " +
			" lps.death_year_max " +					
			" FROM" +
			"   hsn_ids.other_hsn_links_1      as ohl, " +
			" links_ids.persons_static         as lps  " +
			" WHERE " +
			" ohl.id_i_links = lps.id_i";
	
	public static final String insertHSN1 =

			" INSERT INTO hsn_ids.integrated_persons" +
					" (Source_, IDNR, RELATION, Id_I, FIRSTNAME, LASTNAME, SEX, BIRTH_LOCATION, BIRTH_DAY_MIN, BIRTH_MONTH_MIN, BIRTH_YEAR_MIN, "+ 
					" BIRTH_DAY_MAX, BIRTH_MONTH_MAX, BIRTH_YEAR_MAX, DEATH_LOCATION, DEATH_DAY_MIN, DEATH_MONTH_MIN, DEATH_YEAR_MIN, DEATH_DAY_MAX, DEATH_MONTH_MAX, DEATH_YEAR_MAX)" + 		
			" SELECT DISTINCT " +
			        " 'HSN' as source_,  " +
				    " ohl.idnr," +
			        " hii.Relation  as relation," +  
			        " hps.id_i as id_i," +
			        " hps.firstname," + 
			        " hps.lastname," + 
			        " hps.sex," +   
			        " hps.birth_location," +
			        " hps.birth_day_min," +  
			        " hps.birth_month_min," +
			        " hps.birth_year_min," + 
			        " hps.birth_day_max," +  
			        " hps.birth_month_max," + 
			        " hps.birth_year_max, " +  
			        " hps.death_location," +
			        " hps.death_day_min," +  
			        " hps.death_month_min," +
			        " hps.death_year_min," + 
			        " hps.death_day_max," +  
			        " hps.death_month_max," + 
			        " hps.death_year_max " +  
					" FROM "+ 
					" (select * from hsn_ids.other_hsn_links_1 group by idnr)      as ohl," +  
					" hsn_ids.persons_static                                       as hps,"+   
					" hsn_ids.indiv_indiv                                          as hii"+ 
					" WHERE" + 
					" ohl.idnr = abs(substr(hps.id_i, 2, 6)) and" +  
					" hii.id_i_1 = hps.id_i and" + 
					" hii.id_i_2 = concat(substr(id_i, 1, 7) , '001') "; 
			
	public static final String insertHSN2 =

			" INSERT INTO hsn_ids.integrated_persons" +
					" (Source_, IDNR, RELATION, Id_I, FIRSTNAME, LASTNAME, SEX, BIRTH_LOCATION, BIRTH_DAY_MIN, BIRTH_MONTH_MIN, BIRTH_YEAR_MIN, "+ 
					" BIRTH_DAY_MAX, BIRTH_MONTH_MAX, BIRTH_YEAR_MAX, DEATH_LOCATION, DEATH_DAY_MIN, DEATH_MONTH_MIN, DEATH_YEAR_MIN, DEATH_DAY_MAX, DEATH_MONTH_MAX, DEATH_YEAR_MAX)" + 		
			" SELECT DISTINCT " +
			        " 'HSN' as source_,  " +
				    " ohl.idnr," +
			        " 'RP'  as relation," +  
			        " hps.id_i as id_i," +
			        " hps.firstname," + 
			        " hps.lastname," + 
			        " hps.sex," +   
			        " hps.birth_location," +
			        " hps.birth_day_min," +  
			        " hps.birth_month_min," +
			        " hps.birth_year_min," + 
			        " hps.birth_day_max," +  
			        " hps.birth_month_max," + 
			        " hps.birth_year_max, " +  
			        " hps.death_location," +
			        " hps.death_day_min," +  
			        " hps.death_month_min," +
			        " hps.death_year_min," + 
			        " hps.death_day_max," +  
			        " hps.death_month_max," + 
			        " hps.death_year_max " +  
					" FROM "+ 
					" (select * from hsn_ids.other_hsn_links_1 group by idnr)      as ohl," +  
					" hsn_ids.persons_static                                       as hps"+   
					" WHERE" + 
					" ohl.idnr = abs(substr(hps.id_i, 2, 6)) and" +  
					" '001'    = abs(substr(hps.id_i, 8, 3)) ";  
			
	
	
	public static void initialQueries2(Connection connection){
		
		
		
		// hsn_ids.IntegratedPersons

		System.out.println("Creating hsn_ids.Integrated_persons");

		runQuery1(connection,  null, createIntegratedPersons);
		Utils.executeQ(connection, "TRUNCATE hsn_ids.integrated_persons");
		
		runQuery1(connection,  null, insertHSN1);
		runQuery1(connection,  null, insertHSN2);
		runQuery1(connection,  null, insertLINKS);
		
	}
		
	public static void initialQueries(Connection connection){
		
		System.out.println("In initialQueries");
		
		
		// This module handles steps 1 - 7 of the flow diagram
		
		/* hsn_ids.sourcesCertificates  (1) */
		
		System.out.println("Creating hsn_ids.sourcesCertificates");
		runQuery1(connection,  "hsn", createSourcesCertificates);
		Utils.executeQ(connection, "TRUNCATE hsn_ids.sources_certificates");
		runQuery1(connection,  "hsn", populateSourcesCertificates);
		
		/* links_ids.sourcesCertificates (2) */
		
		System.out.println("Creating links_ids.sourcesCertificates - TOUGH query, 10 minutes");
		runQuery1(connection,  "links", createSourcesCertificates);
		Utils.executeQ(connection, "TRUNCATE links_ids.sources_certificates");
		Utils.executeQI(connection, "CREATE INDEX c ON links_ids.context(Id_C)");
		Utils.executeQI(connection, "CREATE INDEX cc ON links_ids.context_context(Id_C_1)");
		runQuery1(connection,  "links", populateSourcesCertificates);
		
		

		/* hsn_ids.personStatic (3) */
		
		System.out.println("Creating hsn_ids.personStatic");
		runQuery1(connection,  "hsn", createPersonStatic);
		Utils.executeQ(connection, "TRUNCATE hsn_ids.persons_static");
		runQuery1(connection,  "hsn", populatePersonStatic);
		Utils.executeQI(connection, "CREATE INDEX ii on hsn_ids.persons_static(id_i)");

		/* links_ids.personStatic (4) */
		
		System.out.println("Creating links_ids.personStatic - TOUGH query, 10 minutes");
		runQuery1(connection,  "links", createPersonStatic);
		Utils.executeQ(connection, "TRUNCATE links_ids.persons_static");
		runQuery1(connection,  "links", populatePersonStatic);
		Utils.executeQI(connection, "CREATE INDEX ii on links_ids.persons_static(id_i)");
		

		
		/* hsn_ids.key_hsn_links (5) */

		System.out.println("Creating hsn_ids.key_hsn_links");
		runQuery1(connection,  null, createKeyHSNLinks);
		Utils.executeQ(connection, "TRUNCATE hsn_ids.key_hsn_links");
		runQuery1(connection,  null, populateKeyHSNLinks);
		
		
		/* hsn_ids.certificates_hsn_rps (6) */

		System.out.println("Creating hsn_ids.certificates_hsn_rp");
		runQuery1(connection,  null, createCertificatesHSNRP);
		Utils.executeQ(connection, "TRUNCATE hsn_ids.certificates_hsn_rp");
		System.out.println("Creating index on hsn_ids.indiv_context(Id_I)");
		Utils.executeQI(connection, "CREATE INDEX ic01 ON hsn_ids.indiv_context(Id_I)");
		System.out.println("Creating index on links_ids.indiv_context(Id_I)");
		Utils.executeQI(connection, "CREATE INDEX ic01 ON links_ids.indiv_context(Id_I)");
		runQuery1(connection,  null, populateCertificatesHSNRP);
		//System.out.println("Collecting data...)");

		

		

		
		/* hsn_ids.other_hsn_links  (7) */

		System.out.println("Creating hsn_ids.others_hsn_links");
		runQuery1(connection,  null, createOthersHSNLinks);
		Utils.executeQ(connection, "TRUNCATE hsn_ids.other_hsn_links");
		
		Utils.executeQ(connection, "CREATE  TABLE IF NOT EXISTS hsn_ids.other_hsn_links_1 like hsn_ids.other_hsn_links");
		System.out.println("Creating index on links_ids.indiv_indiv(Id_I_2)");
		Utils.executeQI(connection, "CREATE INDEX ic01 ON links_ids.indiv_indiv(Id_I_2)");
		System.out.println("Creating index on links_ids.indiv_indiv(Id_I_2) - done");

		Utils.executeQ(connection, "TRUNCATE hsn_ids.other_hsn_links_1");
		
		
		runQuery2(connection,  populateOthersHSNLinks,"1", "1", "Spouse", "Bride");
		runQuery2(connection,  populateOthersHSNLinks,"1", "1", "Spouse", "Groom");
		runQuery2(connection,  populateOthersHSNLinks,"1", "1", "Child", "Child");
		runQuery2(connection,  populateOthersHSNLinks,"1", "1", "Stillbirth-child", "Stillbirth-child");
		runQuery2(connection,  populateOthersHSNLinks,"1", "1", "Mother-in-law", "Mother-in-law");
		runQuery2(connection,  populateOthersHSNLinks,"1", "1", "Father-in-law", "Father-in-law");
		
		
		// Try adding key persons here
		//runQuery2(connection,  populateOthersHSNLinks,"1", "1", "Father", "Father");
		//runQuery2(connection,  populateOthersHSNLinks,"1", "1", "Mother", "Mother");
		
		//Utils.executeQ(connection, "insert into hsn_ids.other_hsn_links_1 (Id_I_HSN, IDNR, PERSON_HSN, ID_I_LINKS, Relation ) SELECT Id_I_HSN, IDNR, PERSON_HSN, ID_I_LINKS, Relation FROM hsn_ids.key_hsn_links
        //");
		
		// Try adding key persons here
		
		runQuery2(connection,  populateOthersHSNLinks,"1", "2",  "Grandfather", "Father");
		runQuery2(connection,  populateOthersHSNLinks,"1", "12", "Grandfather", "Father");
		runQuery2(connection,  populateOthersHSNLinks,"1", "2",  "Grandmother", "Mother");
		runQuery2(connection,  populateOthersHSNLinks,"1", "12", "Grandmother", "Mother");
		
		runQuery2(connection,  populateOthersHSNLinks,"1", "2",  "Half-Sibling (same mother)", "Child");
		runQuery2(connection,  populateOthersHSNLinks,"1", "12", "Half-Sibling (same father)", "Child");
		
		runQuery2(connection,  populateOthersHSNLinks,"1", "2",  "Stillbirth-half-Sibling (same mother)", "Stillbirth-child");
		runQuery2(connection,  populateOthersHSNLinks,"1", "12", "Stillbirth-half-Sibling (same father)", "Stillbirth-child");
		
		// Copy parents in law to separate table 2
		
		Utils.executeQ(connection, "CREATE  TABLE IF NOT EXISTS hsn_ids.other_hsn_links_2 like hsn_ids.other_hsn_links");
		Utils.executeQ(connection, "TRUNCATE hsn_ids.other_hsn_links_2");

		
		runQuery2(connection,  populateOthersHSNLinks,"2", "1", "Mother-in-law", "Mother-in-law");
		runQuery2(connection,  populateOthersHSNLinks,"2", "1", "Father-in-law", "Father-in-law");
		
		runQuery3(connection,  populateOthersHSNLinks2, "Mother-in-law", "Grandmother-in-law");
		runQuery3(connection,  populateOthersHSNLinks2, "Father-in-law", "Grandfather-in-law");


		runQuery3(connection,  populateOthersHSNLinks2, "Half-Sibling-in-law (partner has same mother)", "Child");
		runQuery3(connection,  populateOthersHSNLinks2, "Half-Sibling-in-law (partner has same father)", "Child");

		
		// Now remove persons from hsn_ids.others_hsn_links that already are in hsn_ids.key_hsn_links
		
		System.out.println("Remove persons from hsn_ids.others_hsn_links_1 that already are in hsn_ids.key_hsn_links");
		Utils.executeQI(connection, "create index ii on hsn_ids.key_hsn_links(id_i_links)");
		Utils.executeQI(connection, cleanOthersHSNLinks);
		
		// hsn_ids.IntegratedPersons

		System.out.println("Creating hsn_ids.integrated_persons");
		runQuery1(connection,  null, createIntegratedPersons);
		Utils.executeQ(connection, "TRUNCATE hsn_ids.integrated_persons");

		runQuery1(connection,  null, insertLINKS);
		runQuery1(connection,  null, insertHSN1);
		runQuery1(connection,  null, insertHSN2);

		
	}
		
		
	private static void runQuery1(Connection connection, String source, String query){
		
		if(source != null)
			query = query.replace("xxx", source);
		
		System.out.println(query);
		Utils.executeQ(connection, query);
		
	}
	private static void runQuery2(Connection connection, String query, String intoTable, String person , String relation1, String relation2){		

		query = query.replace("&i", intoTable);
		query = query.replace("&relation1", relation1);
		query = query.replace("&person", person);
		query = query.replace("&relation2", relation2);

		System.out.println(query);
		Utils.executeQ(connection, query);
		
	}
	private static void runQuery3(Connection connection, String query, String relation1, String relation2){

		if(relation1 != null)
			query = query.replace("&relation1", relation1);
		if(relation2 != null)
			query = query.replace("&relation2", relation2);
		System.out.println(query);
		Utils.executeQ(connection, query);
		
	}

}
