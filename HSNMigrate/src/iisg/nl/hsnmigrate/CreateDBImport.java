package iisg.nl.hsnmigrate;

public class CreateDBImport {

	
	public static final String[] tables = {"Stpb", "Gebakte", "Gebbyz", "Gebgtg", "Gebkant", "Gebknd", "Gebvdr",
	       "Ovlagv", "Ovlbyz", "Ovlech", "Ovlknd", 
	       "Huwknd", "Huwafk", "Huweer", "Huwgtg", "Huwvrknd", "Huwbyz", 
	       "pkknd", "plaats", "beheer"};
	
	public static final String[] tables1 = {"Stpb", "Gebakte", "Gebbyz", "Gebgtg", "Gebkant", "Gebknd", "Gebvdr",
	       "Ovlagv", "Ovlbyz", "Ovlech", "Ovlknd", 
	       "Huwknd", "Huwafk", "Huweer", "Huwgtg", "Huwvrknd", "Huwbyz"};
	
	public static final String[] tables2 =  {"plaats", "pkknd", "beheer"};

	
	 public static final String stpb = 
		 
		 "   CREATE TABLE IF NOT EXISTS stpb " +
         "   ( " +
         "   GEMNR      INT, " +
         "   PROVNR     SMALLINT, " +
         "   REGNR      SMALLINT, " +
         "   COHORTNR   SMALLINT, " +
         "   SUBCOHNR   SMALLINT, " +
         "   DOELNR     SMALLINT, " +
         "   JAAR       INT, " +
         "   AKTENR     INT, " +
         "   IDNR       INT, " +
         "   GEMEENTE   CHAR(50), " +
         "   RecordID   INT NOT NULL AUTO_INCREMENT, " +
         "   PRIMARY KEY( RecordID )" +
         ") Engine=InnoDB;";			
	
		public static final String gebakte =
			
		"CREATE TABLE IF NOT EXISTS gebakte " +
        "(" +
        "    GEMNR    INT, " +
        "    JAAR     INT, " +
        "    AKTENR   INT, " +
        "    IDNR     INT, " +
        "    GEBKODE  INT, " +
        "    OVERSAMP CHAR(1), " +
        "    ARCH     CHAR(1), " +
        "    OPDRNR   CHAR(3), " +
        "    DATUM    CHAR(10), " +
        "    INIT     CHAR(3), " +
        "    VERSIE   CHAR(5), " +
        "    ONDRZKO  CHAR(3), " +
        "    ARCHO    CHAR(1), " +
        "    OPDRNRO  CHAR(3), " +
        "    DATUMO   CHAR(10), " +
        "    INITO    CHAR(3), " +
        "    VERSIEO  CHAR(5), " +
        "   RecordID   INT NOT NULL AUTO_INCREMENT, " +
        "   PRIMARY KEY( RecordID )" +
        ") Engine=InnoDB;";			
			
			
			
		public static final String gebbyz =
			
	   "CREATE TABLE IF NOT EXISTS gebbyz " +
	   "(" +
	   "    IDNR     INT, " +
	   "    BYZNR    SMALLINT, " +
	   "    BYZ      CHAR(55), " +
	   "    SCHERM   CHAR(10), " +
	   "    ARCH     CHAR(1), " +
	   "    OPDRNR   CHAR(3), " +
	   "    DATUM    CHAR(10), " +
	   "    INIT     CHAR(3), " +
	   "    VERSIE   CHAR(5), " +
	   "    ONDRZKO  CHAR(3), " +
	   "    ARCHO    CHAR(1), " +
	   "    OPDRNRO  CHAR(3), " +
	   "    DATUMO   CHAR(10), " +
	   "    INITO    CHAR(3), " +
	   "    VERSIEO  CHAR(5), " +
       "   RecordID   INT NOT NULL AUTO_INCREMENT, " +
       "   PRIMARY KEY( RecordID )" +
	   ") Engine=InnoDB;";
			
			
			
			
			
		public static final String gebgtg =
			
	   "CREATE TABLE IF NOT EXISTS gebgtg " +
	   "(" +
	   "    IDNR     INT, " +
	   "    VLGNRGT  SMALLINT, " +
	   "    ANMGT    CHAR(50), " +
	   "    TUSGT    CHAR(10), " +
	   "    VRN1GT   CHAR(20), " +
	   "    VRN2GT   CHAR(20), " +
	   "    VRN3GT   CHAR(30), " +
	   "    LFTGT    INT, " +
	   "    BRPGT    CHAR(50), " +
	   "    ADRGT    CHAR(50), " +
	   "    HNDGT    CHAR(1), " +
	   "    ARCH     CHAR(1), " +
	   "    OPDRNR   CHAR(3), " +
	   "    DATUM    CHAR(10), " +
	   "    INIT     CHAR(3), " +
	   "    VERSIE   CHAR(5), " +
	   "    ONDRZKO  CHAR(3), " +
	   "    ARCHO    CHAR(1), " +
	   "    OPDRNRO  CHAR(3), " +
	   "    DATUMO   CHAR(10), " +
	   "    INITO    CHAR(3), " +
	   "    VERSIEO  CHAR(5), " +
       "   RecordID   INT NOT NULL AUTO_INCREMENT, " +
       "   PRIMARY KEY( RecordID )" +
	   ") Engine=InnoDB;";
			
			
			
		public static final String gebkant =
			
       "CREATE TABLE IF NOT EXISTS gebkant " +
	   "(" +
	   "    IDNR     INT, " +
	   "    KANTTYPE SMALLINT, " +
	   "    KANTDAG  SMALLINT, " +
	   "    KANTMND  SMALLINT, " +
	   "    KANTJR   SMALLINT, " +
	   "    KHUWDAG  SMALLINT, " +
	   "    KHUWMND  SMALLINT, " +
	   "    KHUWJR   SMALLINT, " +
	   "    KHUWGEM  CHAR(50), " +
	   "    KHUWANR  CHAR(50), " +
	   "    KANMVAD  CHAR(50), " +
	   "    KTUSVAD  CHAR(50), " +
	   "    KVRN1VAD CHAR(20), " +
	   "    KVRN2VAD CHAR(20), " +
	   "    KVRN3VAD CHAR(30), " +
	   "    KWYZDAG  SMALLINT, " +
	   "    KWYZMND  SMALLINT, " +
	   "    KWYZJR   SMALLINT, " +
	   "    KWYZKB   INT, " +
	   "    KWYZSTBL INT, " +
	   "    KGMRB    CHAR(50), " +
	   "    KGMERK   CHAR(50), " +
	   "    KWGMMR   CHAR(50), " +
	   "    KBRPMR   CHAR(50), " +
	   "    KANMGEB  CHAR(50), " +
	   "    KVRN1GEB CHAR(20), " +
	   "    KVRN2GEB CHAR(20), " +
	   "    KVRN3GEB CHAR(30), " +
	   "    KTUSGEB  CHAR(10), " +
	   "    KSEXGEB  CHAR(1), " +
	   "    ARCH     CHAR(1), " +
	   "    OPDRNR   CHAR(3), " +
	   "    DATUM    CHAR(10), " +
	   "    INIT     CHAR(3), " +
	   "    VERSIE   CHAR(5), " +
	   "    ONDRZKO  CHAR(3), " +
	   "    ARCHO    CHAR(1), " +
	   "    OPDRNRO  CHAR(3), " +
	   "    DATUMO   CHAR(10), " +
	   "    INITO    CHAR(3), " +
	   "    VERSIEO  CHAR(5), " +
       "   RecordID   INT NOT NULL AUTO_INCREMENT, " +
       "   PRIMARY KEY( RecordID )" +
	   ") Engine=InnoDB;";	
			
			
		public static final String gebknd =
			
       "CREATE TABLE IF NOT EXISTS gebknd " +
       "(" +
       "    GEMNR    SMALLINT, " +
       "    JAAR     SMALLINT, " +
       "    AKTENR   INT, " +
       "    COHORTNR SMALLINT, " +
       "    IDNR     INT, " +
       "    OVERSAMP CHAR(1), " +
       "    INVBEPER CHAR(1), " +
       "    AKTEUUR  SMALLINT, " +
       "    AKTEDAG  SMALLINT, " +
       "    AKTEMND  SMALLINT, " +
       "    LENGEB   INT, " +
       "    ANMAG    CHAR(50), " +
       "    TUSAG    CHAR(10), " +
       "    VRN1AG   CHAR(20), " +
       "    VRN2AG   CHAR(20), " +
       "    VRN3AG   CHAR(30), " +
       "    LFTAG    SMALLINT, " +
       "    BRPAG    CHAR(100), " +
       "    ADRAG    CHAR(100), " +
       "    HNDAG    CHAR(1), " +
       "    GEBDAG   SMALLINT, " +
       "    GEBMND   SMALLINT, " +
       "    GEBJR    SMALLINT, " +
       "    GEBUUR   SMALLINT, " +
       "    GEBMIN   SMALLINT, " +
       "    GEBSEX   CHAR(1), " +
       "    GEBADR   CHAR(50), " +
       "    ANMMR    CHAR(50), " +
       "    TUSMR    CHAR(10), " +
       "    VRN1MR   CHAR(20), " +
       "    VRN2MR   CHAR(20), " +
       "    VRN3MR   CHAR(30), " +
       "    LFTMR    SMALLINT, " +
       "    BRGSTMR  CHAR(1), " +
       "    BRPMR    CHAR(50), " +
       "    ADRMR    CHAR(50), " +
       "    ANMGEB   CHAR(50), " +
       "    TUSGEB   CHAR(10), " +
       "    VRN1GEB  CHAR(20), " +
       "    VRN2GEB  CHAR(20), " +
       "    VRN3GEB  CHAR(30), " +
       "    KANT     CHAR(1), " +
       "    PROBLM   CHAR(1), " +
       "    ARCH     CHAR(1), " +
       "    OPDRNR   CHAR(3), " +
       "    DATUM    CHAR(10), " +
       "    INIT     CHAR(3), " +
       "    VERSIE   CHAR(5), " +
       "    ONDRZKO  CHAR(3), " +
       "    ARCHO    CHAR(1), " +
       "    OPDRNRO  CHAR(3), " +
       "    DATUMO   CHAR(10), " +
       "    INITO    CHAR(3), " +
       "    VERSIEO  CHAR(5), " +
       "   RecordID   INT NOT NULL AUTO_INCREMENT, " +
       "   PRIMARY KEY( RecordID )" +
       ") Engine=InnoDB;";
			
		public static final String gebvdr = 		

       "CREATE TABLE IF NOT EXISTS gebvdr " +
       "(" +
       "    IDNR     INT, " +
       "    GEGVR    CHAR(1), " +
       "    ANMVR    CHAR(50), " +
       "    TUSVR    CHAR(10), " +
       "    VRN1VR   CHAR(20), " +
       "    VRN2VR   CHAR(20), " +
       "    VRN3VR   CHAR(30), " +
       "    LFTVR    SMALLINT, " +
       "    BRPVR    CHAR(50), " +
       "    ADRVR    CHAR(50), " +
       "    G5OOSD   SMALLINT, " +
       "    G5OOSM   SMALLINT, " +
       "    G5OOSJ   SMALLINT, " +
       "    G5OOGS   CHAR(50), " +
       "    G5VOOD   SMALLINT, " +
       "    G5VOOM   SMALLINT, " +
       "    G5VOOJ   SMALLINT, " +
       "    G5VOGO   CHAR(50), " +
       "    ARCH     CHAR(1), " +
       "    OPDRNR   CHAR(3), " +
       "    DATUM    CHAR(10), " +
       "    INIT     CHAR(3), " +
       "    VERSIE   CHAR(5), " +
       "    ONDRZKO  CHAR(3), " +
       "    ARCHO    CHAR(1), " +
       "    OPDRNRO  CHAR(3), " +
       "    DATUMO   CHAR(10), " +
       "    INITO    CHAR(3), " +
       "    VERSIEO  CHAR(5), " +
       "   RecordID   INT NOT NULL AUTO_INCREMENT, " +
       "   PRIMARY KEY( RecordID )" +
       ") Engine=InnoDB;";

/***********************************************************/	
		public static final String ovlagv = 		

		
        "CREATE TABLE IF NOT EXISTS ovlagv " +
        "(" +
        "    IDNR       INT, " +
        "    VLGNRAG    SMALLINT, " +
        "    ANMAGV     CHAR(50), " +
        "    TUSAGV     CHAR(10), " +
        "    VRN1AGV    CHAR(20), " +
        "    VRN2AGV    CHAR(20), " +
        "    VRN3AGV    CHAR(30), " +
        "    RAGVOVL    CHAR(50), " +
        "    LFTAGV     SMALLINT, " +
        "    BRPAGV     CHAR(50), " +
        "    ADRAGV     CHAR(50), " +
        "    HNDAGV     CHAR(1), " +
        "    ARCH     CHAR(1), " +
        "    OPDRNR   CHAR(3), " +
        "    DATUM    CHAR(10), " +
        "    INIT     CHAR(3), " +
        "    VERSIE   CHAR(5), " +
        "    ONDRZKO  CHAR(3), " +
        "    ARCHO    CHAR(1), " +
        "    OPDRNRO  CHAR(3), " +
        "    DATUMO   CHAR(10), " +
        "    INITO    CHAR(3), " +
        "    VERSIEO  CHAR(5), " +
        "   RecordID   INT NOT NULL AUTO_INCREMENT, " +
        "   PRIMARY KEY( RecordID )" +
        ") Engine=InnoDB;";			
        
	public static final String ovlbyz = 		

        "CREATE TABLE IF NOT EXISTS ovlbyz " +
        "(" +
        "    IDNR       INT, " +
        "    BYZNR      SMALLINT, " +
        "    BYZ        CHAR(55), " +
        "    SCHERM     CHAR(10), " +
        "    ARCH     CHAR(1), " +
        "    OPDRNR   CHAR(3), " +
        "    DATUM    CHAR(10), " +
        "    INIT     CHAR(3), " +
        "    VERSIE   CHAR(5), " +
        "    ONDRZKO  CHAR(3), " +
        "    ARCHO    CHAR(1), " +
        "    OPDRNRO  CHAR(3), " +
        "    DATUMO   CHAR(10), " +
        "    INITO    CHAR(3), " +
        "    VERSIEO  CHAR(5), " +
        "   RecordID   INT NOT NULL AUTO_INCREMENT, " +
        "   PRIMARY KEY( RecordID )" +
        ") Engine=InnoDB;";			
        
		public static final String ovlech =
			
        "CREATE TABLE IF NOT EXISTS ovlech " +
        "(" +
        "    IDNR       INT, " +
        "    VLGECH     SMALLINT, " +
        "    ANMEOVL    CHAR(50), " +
        "    TUSEOVL    CHAR(10), " +
        "    VRN1EOVL   CHAR(20), " +
        "    VRN2EOVL   CHAR(20), " +
        "    VRN3EOVL   CHAR(30), " +
        "    LEVEOVL    CHAR(50), " +
        "    LFTEOVL    SMALLINT, " +
        "    BRPEOVL    CHAR(50), " +
        "    ADREOVL    CHAR(50), " +
        "    ARCH     CHAR(1), " +
        "    OPDRNR   CHAR(3), " +
        "    DATUM    CHAR(10), " +
        "    INIT     CHAR(3), " +
        "    VERSIE   CHAR(5), " +
        "    ONDRZKO  CHAR(3), " +
        "    ARCHO    CHAR(1), " +
        "    OPDRNRO  CHAR(3), " +
        "    DATUMO   CHAR(10), " +
        "    INITO    CHAR(3), " +
        "    VERSIEO  CHAR(5), " +
        "   RecordID   INT NOT NULL AUTO_INCREMENT, " +
        "   PRIMARY KEY( RecordID )" +
        ") Engine=InnoDB;";			
        
		public static final String ovlknd =
			
        "CREATE TABLE IF NOT EXISTS ovlknd " +
        "(" +
        "    IDNR       INT, " +
        "    OACGEMNR   SMALLINT, " +    // Cor Changed form SMALLINT
        "    OACGEMNM   CHAR(50), " +
        "    OAKTENR    INT, " +
        "    OAKTEUUR   SMALLINT, " +
        "    OAKTEMIN   SMALLINT, " +
        "    OAKTEDAG   SMALLINT, " +
        "    OAKTEMND   SMALLINT, " +
        "    OAKTEJR    INT, " +
        "    LENGEB     FLOAT, " +
        "    AGVVADR    CHAR(1), " +
        "    NAGVR      SMALLINT, " +
        "    HNDTKV     CHAR(1), " +
        "    GEGOVL     CHAR(1), " +
        "    GEGVAD     CHAR(1), " +
        "    GEGMOE     CHAR(1), " +
        "    ANMOVL     CHAR(50), " +
        "    TUSOVL     CHAR(15), " +
        "    VRN1OVL    CHAR(20), " +
        "    VRN2OVL    CHAR(20), " +
        "    VRN3OVL    CHAR(30), " +
        "    LAAUG      SMALLINT, " +
        "    BRPOVL     CHAR(50), " +
        "    GBPOVL     SMALLINT, " +
        "    GGMOVL     CHAR(50), " +
        "    ADROVL     CHAR(50), " +
        "    BRGOVL     CHAR(1), " +
        "    OVLSEX     CHAR(1), " +
        "    OVLDAG     SMALLINT, " +
        "    OVLMND     SMALLINT, " +
        "    OVLJR      INT, " +
        "    OVLUUR     SMALLINT, " +
        "    OVLMIN     SMALLINT, " +
        "    PLOOVL     CHAR(50), " +
        "    LFTUOVL    SMALLINT, " +
        "    LFTDOVL    SMALLINT, " +
        "    LFTWOVL    SMALLINT, " +
        "    LFTMOVL    SMALLINT, " +
        "    LFTJOVL    INT, " +
        "    MREOVL     SMALLINT, " +
        "    ANMVOVL    CHAR(50), " +
        "    TUSVOVL    CHAR(10), " +
        "    VRN1VOVL   CHAR(20), " +
        "    VRN2VOVL   CHAR(20), " +
        "    VRN3VOVL   CHAR(30), " +
        "    LEVVOVL    CHAR(1), " +
        "    BRPVOVL    CHAR(50), " +
        "    LFVROVL    SMALLINT, " +
        "    ADRVOVL    CHAR(50), " +
        "    ANMMOVL    CHAR(50), " +
        "    TUSMOVL    CHAR(10), " +
        "    VRN1MOVL   CHAR(20), " +
        "    VRN2MOVL   CHAR(20), " +
        "    VRN3MOVL   CHAR(30), " +
        "    LEVMOVL    CHAR(1), " +
        "    BRPMOVL    CHAR(50), " +
        "    LFMROVL    SMALLINT, " +
        "    ADRMOVL    CHAR(50), " +
        "    EXTRACT    CHAR(1), " +
        "    PROBLM     CHAR(1), " +
        "    ARCH     CHAR(1), " +
        "    OPDRNR   CHAR(3), " +
        "    DATUM    CHAR(10), " +
        "    INIT     CHAR(3), " +
        "    VERSIE   CHAR(5), " +
        "    ONDRZKO  CHAR(3), " +
        "    ARCHO    CHAR(1), " +
        "    OPDRNRO  CHAR(3), " +
        "    DATUMO   CHAR(10), " +
        "    INITO    CHAR(3), " +
        "    VERSIEO  CHAR(5), " +
        "   RecordID   INT NOT NULL AUTO_INCREMENT, " +
        "   PRIMARY KEY( RecordID )" +
        ") Engine=InnoDB;";			
        
		public static final String huwknd =
			
   "CREATE TABLE IF NOT EXISTS huwknd (" +
   "IDNR       INT, " +
   "HVLGNR     SMALLINT, " +
   "HGEMNR     INT, " +
   "HAKTENR    INT, " +
   "HPLTS      CHAR(50), " +
   "HUUR       SMALLINT, " +
   "HDAG       SMALLINT, " +
   "HMAAND     SMALLINT, " +
   "HJAAR      SMALLINT, " +
   "SCHEIDNG   CHAR(1), " +
   "SDAG       SMALLINT, " +
   "SMAAND     SMALLINT, " +
   "SJAAR      SMALLINT, " +
   "SPLTS      CHAR(50), " +
   "IDAG       SMALLINT, " +
   "IMAAND     SMALLINT, " +
   "IJAAR      SMALLINT, " +
   "IPLTS      CHAR(50), " +
   "LFTJHM     SMALLINT, " +
   "LFTJHV     SMALLINT, " +
   "GEBSEX     CHAR(1), " +
   "ANMHM      CHAR(50), " +
   "TUSHM      CHAR(10), " +
   "VRN1HM     CHAR(20), " +
   "VRN2HM     CHAR(20), " +
   "VRN3HM     CHAR(30), " +
   "BRPHM      CHAR(50), " +
   "GEBPLNHM   SMALLINT, " +
   "GEBPLHM    CHAR(50), " +
   "ADRHM      CHAR(50), " +
   "OADRHM     CHAR(50), " +
   "OADREHM    CHAR(50), " +
   "BSTHM      CHAR(1), " +
   "HNDHM      CHAR(1), " +
   "ANMHV      CHAR(50), " +
   "TUSHV      CHAR(10), " +
   "VRN1HV     CHAR(20), " +
   "VRN2HV     CHAR(20), " +
   "VRN3HV     CHAR(30), " +
   "BRPHV      CHAR(50), " +
   "GEBPLNHV   CHAR(50), " +
   "GEBPLHV    CHAR(50), " +
   "ADRHV      CHAR(50), " +
   "OADRHV     CHAR(50), " +
   "OADREHV    CHAR(50), " +
   "BSTHV      CHAR(1), " +
   "HNDHV      CHAR(1), " +
   "LEVVRHM    CHAR(1), " +
   "TOEVRHM    CHAR(1), " +
   "ANMVRHM    CHAR(50), " +
   "TUSVRHM    CHAR(10), " +
   "VRN1VRHM   CHAR(20), " +
   "VRN2VRHM   CHAR(20), " +
   "VRN3VRHM   CHAR(30), " +
   "BRPVRHM    CHAR(50), " +
   "ADRVRHM    CHAR(50), " +
   "PLOVVRHM   CHAR(50), " +
   "HNDVRHM    CHAR(1), " +
   "LFTJVRHM   SMALLINT, " +
   "LEVMRHM    CHAR(1), " +
   "TOEMRHM    CHAR(1), " +
   "ANMMRHM    CHAR(50), " +
   "TUSMRHM    CHAR(10), " +
   "VRN1MRHM   CHAR(20), " +
   "VRN2MRHM   CHAR(20), " +
   "VRN3MRHM   CHAR(30), " +
   "BRPMRHM    CHAR(50), " +
   "ADRMRHM    CHAR(50), " +
   "PLOVMRHM   CHAR(50), " +
   "HNDMRHM    CHAR(1), " +
   "LFTJMRHM   SMALLINT, " +
   "LEVVRHV    CHAR(1), " +
   "TOEVRHV    CHAR(1), " +
   "ANMVRHV    CHAR(50), " +
   "TUSVRHV    CHAR(10), " +
   "VRN1VRHV   CHAR(20), " +
   "VRN2VRHV   CHAR(20), " +
   "VRN3VRHV   CHAR(30), " +
   "BRPVRHV    CHAR(50), " +
   "ADRVRHV    CHAR(50), " +
   "PLOVVRHV   CHAR(50), " +
   "HNDVRHV    CHAR(1), " +
   "LFTJVRHV   SMALLINT, " +
   "LEVMRHV    CHAR(1), " +
   "TOEMRHV    CHAR(1), " +
   "ANMMRHV    CHAR(50), " +
   "TUSMRHV    CHAR(10), " +
   "VRN1MRHV   CHAR(20), " +
   "VRN2MRHV   CHAR(20), " +
   "VRN3MRHV   CHAR(30), " +
   "BRPMRHV    CHAR(50), " +
   "ADRMRHV    CHAR(50), " +
   "PLOVMRHV   CHAR(50), " +
   "HNDMRHV    CHAR(1), " +
   "LFTJMRHV   SMALLINT, " +
   "UGEBHUW    CHAR(1), " +
   "UOVLOUD    CHAR(1), " +
   "UOVLECH    CHAR(1), " +
   "CERTNATM   CHAR(1), " +
   "TOESTNOT   CHAR(1), " +
   "AKTEBEK    CHAR(1), " +
   "ONVERMGN   CHAR(1), " +
   "COMMAND    CHAR(1), " +
   "TOESTVGD   CHAR(1), " +
   "GEGHUW     CHAR(1), " +
   "GEGVR      CHAR(1), " +
   "GEGMR      CHAR(1), " +
   "PROBLM     CHAR(1), " +
   "NGTG       CHAR(2), " +
   "ERKEN      CHAR(1), " +
   "ARCH       CHAR(1), " +
   "OPDRNR     CHAR(3), " +
   "DATUM      CHAR(10), " +
   "INIT       CHAR(3), " +
   "VERSIE     CHAR(5), " +
   "ONDRZKO    CHAR(3), " +
   "ARCHO      CHAR(1), " +
   "OPDRNRO    CHAR(3), " +
   "DATUMO     CHAR(10), " +
   "INITO      CHAR(3), " +
   "VERSIEO    CHAR(5), " +
   "RecordID   INT NOT NULL AUTO_INCREMENT, " +
   "PRIMARY KEY( RecordID )" +
   ") Engine=InnoDB;";
    
	public static final String huwafk =
		
   "CREATE TABLE IF NOT EXISTS huwafk (" +
   "IDNR       INT, " +
   "HDAG       SMALLINT, " +
   "HMAAND     SMALLINT, " +
   "HJAAR      SMALLINT, " +
   "HVLGNR     SMALLINT, " +
   "HWAKNR     SMALLINT, " +
   "HWAKDG     SMALLINT, " +
   "HWAKMD     SMALLINT, " +
   "HWAKJR     SMALLINT, " +
   "HWAKGR     SMALLINT, " +  // Cor Changed from SMALLINT
   "HWAKPL     CHAR(50), " +
   "ARCH       CHAR(1), " +
   "OPDRNR     CHAR(3), " +
   "DATUM      CHAR(10), " +
   "INIT       CHAR(3), " +
   "VERSIE     CHAR(5), " +
   "ONDRZKO    CHAR(3), " +
   "ARCHO      CHAR(1), " +
   "OPDRNRO    CHAR(3), " +
   "DATUMO     CHAR(10), " +
   "INITO      CHAR(3), " +
   "VERSIEO    CHAR(5), " +
   "RecordID   INT NOT NULL AUTO_INCREMENT, " +
   "PRIMARY KEY( RecordID ) " +
   ") Engine=InnoDB";
   
	public static final String huweer =
		
   "CREATE TABLE IF NOT EXISTS huweer (" +
   "IDNR       INT, " +
   "HDAG       SMALLINT, " +
   "HMAAND     SMALLINT, " +
   "HJAAR      SMALLINT, " +
   "HVLGNR      SMALLINT, " +
   "VLGNREH    SMALLINT, " +
   "HUWER      CHAR(1), " +
   "ANMEH      CHAR(50), " +
   "TUSEH      CHAR(10), " +
   "VRN1EH     CHAR(20), " +
   "VRN2EH     CHAR(20), " +
   "VRN3EH     CHAR(30), " +
   "EINDEH     CHAR(1), " +
   "ARCH       CHAR(1), " +
   "OPDRNR     CHAR(3), " +
   "DATUM      CHAR(10), " +
   "INIT       CHAR(3), " +
   "VERSIE     CHAR(5), " +
   "ONDRZKO    CHAR(3), " +
   "ARCHO      CHAR(1), " +
   "OPDRNRO    CHAR(3), " +
   "DATUMO     CHAR(10), " +
   "INITO      CHAR(3), " +
   "VERSIEO    CHAR(5), " +
   "RecordID   INT NOT NULL AUTO_INCREMENT, " +
   "PRIMARY KEY( RecordID ) " +
   ") Engine=InnoDB;";
   
	public static final String huwgtg =
		
    "CREATE TABLE IF NOT EXISTS huwgtg (" +
    "IDNR       INT, " +
    "HDAG       SMALLINT, " +
    "HMAAND     SMALLINT, " +
    "HJAAR      SMALLINT, " +
    "HVLGNR      SMALLINT, " +
    "VLGNRGT    SMALLINT, " +
    "ANMGT      CHAR(50), " +
    "TUSGT      CHAR(10), " +
    "VRN1GT     CHAR(20), " +
    "VRN2GT     CHAR(20), " +
    "VRN3GT     CHAR(30), " +
    "LFTJGT     SMALLINT, " +
    "BRPGT      CHAR(50), " +
    "ADRGT      CHAR(50), " +
    "HNDGT      CHAR(1), " +
    "RELWIE     CHAR(1), " +
    "RELGT      CHAR(50), " +
    "ARCH       CHAR(1), " +
    "OPDRNR     CHAR(3), " +
    "DATUM      CHAR(10), " +
    "INIT       CHAR(3), " +
    "VERSIE     CHAR(5), " +
    "ONDRZKO    CHAR(3), " +
    "ARCHO      CHAR(1), " +
    "OPDRNRO    CHAR(3), " +
    "DATUMO     CHAR(10), " +
    "INITO      CHAR(3), " +
    "VERSIEO    CHAR(5), " +
    "RecordID   INT NOT NULL AUTO_INCREMENT, " +
    "PRIMARY KEY( RecordID ) " +
    ") Engine=InnoDB; ";
    
	public static final String huwvrknd =
		
    "CREATE TABLE IF NOT EXISTS huwvrknd (" +
    "IDNR       INT, " +
    "HDAG       SMALLINT, " +
    "HMAAND     SMALLINT, " +
    "HJAAR      SMALLINT, " +
    "HVLGNR     SMALLINT, " +
    "VLGNRVK    SMALLINT, " +
    "ANMVK      CHAR(50), " +
    "TUSVK      CHAR(10), " +
    "VRN1VK     CHAR(20), " +
    "VRN2VK     CHAR(20), " +
    "VRN3VK     CHAR(30), " +
    "GBDGVK     SMALLINT, " +
    "GBMDVK     SMALLINT, " +
    "GBJRVK     SMALLINT, " +
    "GESLVK     CHAR(1), " +
    "GBPLVK     CHAR(50), " +
    "ERVK       CHAR(1), " +
    "ERVKWIE    CHAR(1), " +
    "MEKDGVK    SMALLINT, " +
    "MEKMDVK    SMALLINT, " +
    "MEKJRVK    SMALLINT, " +
    "MEKPLVK    CHAR(50), " +
    "VEKDGVK    SMALLINT, " +
    "VEKMDVK    SMALLINT, " +
    "VEKJRVK    SMALLINT, " +
    "VEKPLVK    CHAR(50), " +
    "ARCH       CHAR(1), " +
    "OPDRNR     CHAR(3), " +
    "DATUM      CHAR(10), " +
    "INIT       CHAR(3), " +
    "VERSIE     CHAR(5), " +
    "OPDRNRO    CHAR(3), " +
    "ARCHO      CHAR(1), " +
    "ONDRZKO    CHAR(3), " +
    "DATUMO     CHAR(10), " +
    "INITO      CHAR(3), " +
    "VERSIEO    CHAR(5), " +
    "RecordID   INT NOT NULL AUTO_INCREMENT, " +
    "PRIMARY KEY( RecordID ) " +
    ") Engine=InnoDB;";
    
	public static final String huwbyz =
		
    "CREATE TABLE IF NOT EXISTS huwbyz (" +
    "IDNR       INT, " +
    "HDAG       SMALLINT, " +
    "HMAAND     SMALLINT, " +
    "HJAAR      SMALLINT, " +
    "HVLGNR     SMALLINT, " +
    "BYZNR      SMALLINT, " +
    "BYZ        CHAR(55), " +
    "SCHERM     CHAR(10), " +
    "ARCH       CHAR(1), " +
    "OPDRNR     CHAR(3), " +
    "DATUM      CHAR(10), " +
    "INIT       CHAR(3), " +
    "VERSIE     CHAR(5), " +
    "ONDRZKO    CHAR(3), " +
    "ARCHO      CHAR(1), " +
    "OPDRNRO    CHAR(3), " +
    "DATUMO     CHAR(10), " +
    "INITO      CHAR(3), " +
    "VERSIEO    CHAR(5), " +
    "RecordID   INT NOT NULL AUTO_INCREMENT, " +
    "PRIMARY KEY( RecordID ) " +
    ") Engine=InnoDB;";
  
	
	public static final String pkknd =
		
	     "CREATE TABLE IF NOT EXISTS pkknd (" +
         "   IDNR        INT, " +
         "   IDNRP       INT, " +
         "   GAKTNRP     CHAR(8), " +
         "   PKTYPE      SMALLINT, " +
         "   EINDAGPK    SMALLINT, " +
         "   EINMNDPK    SMALLINT, " +
         "   EINJARPK    SMALLINT, " +
         "   CTRDGP      SMALLINT, " +
         "   CTRMDP      SMALLINT, " +
         "   CTRJRP      SMALLINT, " +
         "   CTRPARP     CHAR(1), " +
         "   GZNVRMP     CHAR(50), " +
         "   ANMPERP     CHAR(50), " +
         "   TUSPERP     CHAR(10), " +
         "   VNM1PERP    CHAR(20), " +
         "   VNM2PERP    CHAR(20), " +
         "   VNM3PERP    CHAR(30), " +
         "   GDGPERP     SMALLINT, " +
         "   GMDPERP     SMALLINT, " +
         "   GJRPERP     SMALLINT, " +
         "   GDGPERPCR   SMALLINT, " +
         "   GMDPERPCR   SMALLINT, " +
         "   GJRPERPCR   SMALLINT, " +
         "   GPLPERP     CHAR(50), " +
         "   NATPERP     CHAR(40), " +
         "   GDSPERP     CHAR(20), " +
         "   GSLPERP     CHAR(1), " +
         "   ANMVDRP     CHAR(50), " +
         "   TUSVDRP     CHAR(10), " +
         "   VNM1VDRP    CHAR(20), " +
         "   VNM2VDRP    CHAR(20), " +
         "   VNM3VDRP    CHAR(100), " +
         "   GDGVDRP     SMALLINT, " +
         "   GMDVDRP     SMALLINT, " +
         "   GJRVDRP     SMALLINT, " +
         "   GDGVDRPCR   SMALLINT, " +
         "   GMDVDRPCR   SMALLINT, " +
         "   GJRVDRPCR   SMALLINT, " +
         "   GPLVDRP     CHAR(50), " +
         "   ANMMDRP     CHAR(50), " +
         "   TUSMDRP     CHAR(10), " +
         "   VNM1MDRP    CHAR(20), " +
         "   VNM2MDRP    CHAR(20), " +
         "   VNM3MDRP    CHAR(40), " +
         "   GDGMDRP     SMALLINT, " +
         "   GMDMDRP     SMALLINT, " +
         "   GJRMDRP     SMALLINT, " +
         "   GDGMDRPCR   SMALLINT, " +
         "   GMDMDRPCR   SMALLINT, " +
         "   GJRMDRPCR   SMALLINT, " +
         "   GPLMDRP     CHAR(50), " +
         "   ODGPERP     SMALLINT, " +
         "   OMDPERP     SMALLINT, " +
         "   OJRPERP     SMALLINT, " +
         "   OPLPERP     CHAR(50), " +
         "   OAKPERP     CHAR(10), " +
         "   ODOPERP     CHAR(50), " +
         "   GEGPERP     CHAR(1), " +
         "   GEGVDRP     CHAR(1), " +
         "   GEGMDRP     CHAR(1), " +
         "   PROBLMP     CHAR(1), " +
         "   PSBDGP      SMALLINT, " +
         "   PSBMDP      SMALLINT, " +
         "   PSBJRP      SMALLINT, " +
         "   PSBNRP      CHAR(20), " +
         "   OPDRNR      CHAR(3), " +
         "   DATUM       CHAR(10), " +
         "   INIT        CHAR(3), " +
         "   VERSIE      CHAR(5), " +
         "   ONDRZKO     CHAR(3), " +
         "   OPDRNRO     CHAR(3), " +
         "   DATUMO      CHAR(10), " +
         "   INITO       CHAR(3), " +
         "   VERSIEO     CHAR(5), " +
         "   RecordID    INT NOT NULL AUTO_INCREMENT, " +
         "   PRIMARY KEY( RecordID ), " +
         "   INDEX       (IDNR), " +
         "   INDEX       (IDNRP) " +
         ") Engine=InnoDB;";
			
			
	public static final String beheer =
		
        "CREATE TABLE IF NOT EXISTS beheer" +
        "(" +
        "    IDNR       INT, " +
        "    OVLDAG     SMALLINT, " +
        "    OVLMND     INT, " +
        "    OVLJAAR    SMALLINT, " +
        "    FASE_A     SMALLINT, " +
        "    FASE_B     SMALLINT, " +
        "    FASE_C_D   SMALLINT, " +
        "    OVLPLAATS  CHAR(50), " +
        "    INVOERSTAT CHAR(1), " +
        "    RANDOMGETA INT, " +
        "    RecordID   INT NOT NULL AUTO_INCREMENT, " +
        "    PRIMARY KEY( RecordID ), " +
        "    INDEX(IDNR) " +
        ") Engine=InnoDB;";			

			
	public static final String plaats =
		
		"CREATE TABLE IF NOT EXISTS plaats " +
        "(" +
        "    GEMNR      INT, " +
        "    PROVNR     SMALLINT, " +
        "    REGNR      SMALLINT, " +
        "    REGIO      CHAR(20), " +
        "    VOLGNR     SMALLINT, " +
        "    GEMNAAM    CHAR(50), " +
//        "    NWINLST    CHAR(1), " +
        "    RecordID   INT NOT NULL AUTO_INCREMENT, " +
        "    PRIMARY KEY( RecordID ), " +
        "    INDEX( GEMNAAM ), " +
       "    UNIQUE INDEX( GEMNR, PROVNR )" +
        ") Engine=InnoDB;";			
			
	
	public static final String stpb_truncate     = "TRUNCATE TABLE stpb";
	public static final String gebakte_truncate  = "TRUNCATE TABLE gebakte";
	public static final String gebbyz_truncate   = "TRUNCATE TABLE gebbyz";	 
	public static final String gebgtg_truncate   = "TRUNCATE TABLE gebgtg";	 
	public static final String gebkant_truncate  = "TRUNCATE TABLE gebkant";	 
	public static final String gebknd_truncate   = "TRUNCATE TABLE gebknd";	 
	public static final String gebvdr_truncate   = "TRUNCATE TABLE gebvdr";
	
	public static final String ovlagv_truncate   = "TRUNCATE TABLE ovlagv";
	public static final String ovlbyz_truncate   = "TRUNCATE TABLE ovlbyz";
	public static final String ovlech_truncate   = "TRUNCATE TABLE ovlech";
	public static final String ovlknd_truncate   = "TRUNCATE TABLE ovlknd";
	
	public static final String huwknd_truncate   = "TRUNCATE TABLE huwknd";
	public static final String huwafk_truncate   = "TRUNCATE TABLE huwafk";
	public static final String huweer_truncate   = "TRUNCATE TABLE huweer";
	public static final String huwgtg_truncate   = "TRUNCATE TABLE huwgtg";
	public static final String huwvrknd_truncate = "TRUNCATE TABLE huwvrknd";
	public static final String huwbyz_truncate   = "TRUNCATE TABLE huwbyz";
	
	public static final String plaats_truncate   = "TRUNCATE TABLE plaats";
	public static final String beheer_truncate   = "TRUNCATE TABLE beheer";
	public static final String pkknd_truncate    = "TRUNCATE TABLE pkknd";
	

			
			
			
			
			
			
			
			
			
	
	
	
	
	

}
