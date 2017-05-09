package nl.iisg.ref;


public class Ref_CreateTables {
	
	
	static final int XBigstring  = 256;
	static final int Bigstring   = 80;
	static final int Smallstring = 20;
	public static final String Ref_Address  = 

		" create table if not exists ref_address " +
		" ( " +

		"address_id  int auto_increment," +
		
		" ORIGINAL varchar(" + Bigstring + "), " + 


		" STREET_OR varchar(" + Bigstring + "), " + 
		" STREET_ST varchar(" + Bigstring + "), " +

		" QUARTER_OR varchar(" + Bigstring + "), " + 
		" QUARTER_ST varchar(" + Bigstring + "), " + 

		" PLACE_OR varchar(" + Bigstring + "), " + 
		" PLACE_ST varchar(" + Bigstring + "), " + 

		" BOAT_OR varchar(" + Bigstring + "), " +
		" BOAT_ST varchar(" + Bigstring + "), " +

		" BERTH_OR varchar(" + Bigstring + "), " +
		" BERTH_ST varchar(" + Bigstring + "), " +

		" INSTIT_OR varchar(" + Bigstring + "), " +
		" INSTIT_ST varchar(" + Bigstring + "), " +

		" LANDLORD_OR varchar(" + Bigstring + "), " +
		" LANDLORD_ST varchar(" + Bigstring + "), " +

		" OTHER_OR varchar(" + Bigstring + "), " +
		" OTHER_ST varchar(" + Bigstring + "), " +

		" OA_OR varchar(" + Bigstring + "), " +

		" NIEUWCODE char(1), " +
		
		" STANDARD_SOURCE varchar(" + Smallstring + "), " +
		
		" primary key (address_id)" +

		");";


	public static final String Ref_FamilyName  = 	 

		" create table if not exists ref_familyname " +
		" ( " +
		" id_familyname  int auto_increment, " + 
		" original varchar(" + Bigstring + "), " + 
		" standard varchar(" + Bigstring + "), " + 
		" standard_code char(1), " + 
		" standard_source varchar(20), " +
		" primary key (id_familyname)" +
		" );";


	public static final String Ref_FirstName  = 

		" create table if not exists ref_firstname " +
		" ( " +
		" id_firstname int auto_increment, " + 
		" original varchar(" + Bigstring + "), " + 
		" standard varchar(" + Bigstring + "), " + 
		" standard_code char(1), " + 
		" standard_source varchar(20), " +
		" primary key (id_firstname)" +
		" );";


	public static final String Ref_Housenumber  =  

	" create table if not exists ref_housenumber " +
	" ( " +
	" ID_HUISNUMMER int auto_increment, " + 
	" ORIGINEEL varchar(" + Smallstring + "), " + 
	" NUMMER varchar(" + Smallstring + "), " + 
	" NIEUWCODE char(1), " +
	" primary key (id_huisnummer)" +
	" );";


	public static final String Ref_Housenumberaddition  =  

	" create table if not exists ref_addition " +
	" ( " +
	" ID_HUISNUMMERTOEVOEGING int auto_increment, " + 
	" ORIGINEEL varchar(" + Smallstring + "), " + 
	" TOEVOEGING varchar(" + Smallstring + "), " +  
	" NIEUWCODE char(1), " +
	" primary key (id_huisnummertoevoeging)" +
	" );";


	public static final String Ref_KG  =  

	" create table if not exists ref_religion " +  
	" ( " +
	" id_religion int auto_increment, " + 
	" original varchar(" + Bigstring + "), " + 
	" standard varchar(" + Bigstring + "), " + 
	" standard_code char(1), " + 
	" standard_source varchar(20), " +
	" primary key (id_religion)" +
" );";


	public static final String Ref_Location  = 

	" create table if not exists ref_location " +
	" ( " +
	" id_location int auto_increment, " + 
	" original varchar(" + Bigstring + "), " +
	" location_no int, " +
	" ambiguity int, " +
	" location varchar(" + Bigstring + "), " +
	" municipality varchar(" + Bigstring + "), " +
	" province varchar(" + Bigstring + "), " +
	" region varchar(" + Bigstring + "), " +
	" country varchar(" + Bigstring + "), " +
	" standard_code char(" + 1 + "), " +
	" standard_source varchar(" + Bigstring + "), " +
	" standard_method varchar(" + Bigstring + "), " +
	" primary key (id_location)" +
	" );";


	public static final String Ref_Prefix_Save  = 

		" create table if not exists ref_voorstuk " +
		" ( " +
		" ID_VOORSTUK int, " + 
		" ORIGINEEL varchar(" + Smallstring + "), " + 
		" TITEL_ADELIJK varchar(" + Smallstring + "), " +
		" TITEL_OVERIG varchar(" + Smallstring + "), " + 
		" VOORVOEGSEL varchar(" + Smallstring + "), " +  
		" NIEUWCODE char(1), " + 
		" STANDAARDCODE VARCHAR(8) " +
		" );";

	public static final String Ref_Prefix  = 

		" create table if not exists ref_prepiece " +
		" ( " +
		" id_prepiece int auto_increment, " + 
		" original varchar(" + Smallstring + "), " + 
		" title_noble varchar(" + Smallstring + "), " +
		" title_other varchar(" + Smallstring + "), " + 
		" prefix varchar(" + Smallstring + "), " +  
		" standard_code char(1), " + 
		" standard_source varchar(20), " +
		" primary key (id_prepiece)" +
		" );";



	public static final String Ref_Profession  =  

	" create table if not exists ref_occupation " +
	" ( " +
	" id_occupation int auto_increment, " + 
	" original varchar(" + Bigstring + "), " + 
	" standard varchar(" + Bigstring + "), " + 
	" standard_code char(1), " + 
	" standard_source varchar(20), " +
	" primary key (id_occupation)" +
	" );";

	public static final String Ref_Municipality  =  

			" create table if not exists ref_plaats " +
			" ( " +
			" GEMNR int, " +
			" PROVNR int, " +
			" REGNR int, " +
			" REGIO varchar(" + Bigstring + "), " +
			" VOLGNR int, " + 
			" GEMNAAM varchar(" + Bigstring + ") " +
			" );";


	public static final String Ref_Relation_B  =  

			" create table if not exists ref_relation_b " +
			" ( " +
			" id int auto_increment, " +
			" kode int, " + 
			" nederlands varchar(" + Bigstring + "), " + 
			" sex           char(1)," + 
			" engels     varchar(" + Bigstring + "), " + 
			" ids        varchar(" + Bigstring + "), " + 
			" primary key (id)" +
			" );";

	public static final String Ref_Relation_C  =  

			" create table if not exists ref_relation_c " +
			" ( " +
			" id int auto_increment, " +
			" relation_a varchar(" + Smallstring + "), " + 
			" relation_b varchar(" + Smallstring + "), " + 
			" relation_m varchar(" + Smallstring + "), " + 
			" primary key (id)" +
			" );";

}
