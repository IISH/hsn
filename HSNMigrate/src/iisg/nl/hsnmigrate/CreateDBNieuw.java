package iisg.nl.hsnmigrate;

public class CreateDBNieuw {
	
	public static final String[] tables = {"B0",   "B1",     "B2",     "B3",      "B4",      "B5",     "B6",      "D1",     "D2",     "D3",     "D4",     "M1",     "M2",     "M3",
			   "M4",     "M5",       "M6"};

    		
	 public static final String B0 = 		
    "CREATE TABLE IF NOT EXISTS b0" +
    "(" +
    "    B0SUHG       SMALLINT, " +
    "    B0SUHC       SMALLINT, " +
    "    B0SUHY       SMALLINT, " +
    "    B0SUHN       INT, " +
    "    B0SUHD       SMALLINT, " +
    "    IDNR         INT, " +
    "    B0SUHP       SMALLINT, " +
    "    B0SUHR       SMALLINT, " +
    "    B0SUHE       SMALLINT, " +
    "    RecordID     INT AUTO_INCREMENT, " +
    "    PRIMARY KEY (RecordID), " +
    "    INDEX       (IDNR) " +
    ") Engine=InnoDB;";

	 public static final String B1 = 		
   	"CREATE TABLE IF NOT EXISTS b1" +
    "(" +
    "    B1SDCC       SMALLINT, " +
    "    B1SDCY       SMALLINT, " +
    "    B1SDCN       SMALLINT, " +
    "    IDNR         INT, " +
    "    B1SDLI       CHAR(1), " +
    "    B1SDCH       SMALLINT, " +
    "    B1SDCD       SMALLINT, " +
    "    B1SDCM       SMALLINT, " +
    "    B1INFA       CHAR(1), " +
    "    B1INLN       CHAR(" + Const.Bigstring + "), " +
    "    B1INPF       CHAR(" + Const.Smallstring + "), " +
    "    B1INFN       CHAR(" + Const.Bigstring + "), " +
    "    B1INTT       CHAR(" + Const.Smallstring + "), " +
    "    B1INPA       CHAR(" + Const.Smallstring + "), " +
    "    B1INAY       SMALLINT, " +
    "    B1INOC       CHAR(" + Const.Bigstring + "), " +
    "    B1INLL       CHAR(" + Const.Bigstring + "), " +
    "    B1INSG       CHAR(1), " +
    "    B1RPBD       SMALLINT, " +
    "    B1RPBM       SMALLINT, " +
    "    B1RPBY       SMALLINT, " +
    "    B1RPBH       SMALLINT, " +
    "    B1RPBI       SMALLINT, " +
    "    B1RPGN       CHAR(1), " +
    "    B1RPLL       CHAR(" + Const.Bigstring + "), " +
    "    B1MOLN       CHAR(" + Const.Bigstring + "), " +
    "    B1MOPF       CHAR(" + Const.Smallstring + "), " +
    "    B1MOFN       CHAR(" + Const.Bigstring + "), " +
    "    B1MOTT       CHAR(" + Const.Smallstring + "), " +
    "    B1MOPA       CHAR(" + Const.Smallstring + "), " +
    "    B1MOAY       SMALLINT, " +
    "    B1MOCS       CHAR(1), " +
    "    B1MOOC       CHAR(" + Const.Bigstring + "), " +
    "    B1MOLL       CHAR(" + Const.Bigstring + "), " +
    "    B1RPLN       CHAR(" + Const.Bigstring + "), " +
    "    B1RPPF       CHAR(" + Const.Smallstring + "), " +
    "    B1RPFN       CHAR(" + Const.Bigstring + "), " +
    "    B1RPTT       CHAR(" + Const.Smallstring + "), " +
    "    B1RPPA       CHAR(" + Const.Smallstring + "), " +
    "    D_E_P_L      CHAR(5), " +
    "    D_E_P_O      CHAR(5), " +
    "    RecordID INT AUTO_INCREMENT, " +
    "    Primary Key( RecordID ), " +
    "    INDEX        (IDNR)" +
    ") Engine=InnoDB;";
    
	 public static final String B2 = 		

    "CREATE TABLE IF NOT EXISTS b2" +
    "(" +
    "    IDNR         INT, " +
    "    B2W_SQ       SMALLINT, " +
    "    B2W_LN       CHAR(" + Const.Bigstring + "), " +
    "    B2W_PF       CHAR(" + Const.Smallstring + "), " +
    "    B2W_FN       CHAR(" + Const.Bigstring + "), " +
    "    B2W_TT       CHAR(" + Const.Smallstring + "), " +
    "    B2W_PA       CHAR(" + Const.Smallstring + "), " +
    "    B2W_AY       SMALLINT, " +
    "    B2W_OC       CHAR(" + Const.Bigstring + "), " +
    "    B2W_LL       CHAR(" + Const.Bigstring + "), " +
    "    B2W_SG       CHAR(1), " +
    "    RecordID INT AUTO_INCREMENT, " +
    "    Primary Key( RecordID ), " +
    "    INDEX        (IDNR)" +
    ") Engine=InnoDB;";
    
	 public static final String B3 = 		
    
    "CREATE TABLE IF NOT EXISTS b3" +
    "(" +
    "    IDNR     INT, " +
    "    B3SDMI   TEXT, " +
    "    RecordID INT     AUTO_INCREMENT, " +
    "    Primary Key( RecordID ), " +
    "    INDEX        (IDNR)" +
    ") Engine=InnoDB;";
	 
    
	 public static final String B4 = 		

    
    "CREATE TABLE IF NOT EXISTS b4" +
    "(" +
    "    IDNR         INT, " +
    "    B4SDCT       SMALLINT, " +
    "    B4SDCD       SMALLINT, " +
    "    B4SDCM       SMALLINT, " +
    "    B4SDCY       SMALLINT, " +
    "    B4SDMD       SMALLINT, " +
    "    B4SDMM       SMALLINT, " +
    "    B4SDMY       SMALLINT, " +
    "    B4SDML       CHAR(" + Const.Bigstring + "), " +
    "    B4FALN       CHAR(" + Const.Bigstring + "), " +
    "    B4FAPF       CHAR(" + Const.Smallstring + "), " +
    "    B4FAFN       CHAR(" + Const.Bigstring + "), " +
    "    B4FATT       CHAR(" + Const.Smallstring + "), " +
    "    B4FAPA       CHAR(" + Const.Smallstring + "), " +
    "    B4SDRD       SMALLINT, " +
    "    B4SDRM       SMALLINT, " +
    "    B4SDRY       SMALLINT, " +
    "    B4SDRS       INT, " +
    "    B4SDRN       INT, " +
    "    B4RPLN       CHAR(" + Const.Bigstring + "), " +
    "    B4RPFN       CHAR(" + Const.Bigstring + "), " +
    "    B4RPPF       CHAR(" + Const.Smallstring + "), " +
    "    B4RPTT       CHAR(" + Const.Smallstring + "), " +
    "    B4RPPA       CHAR(" + Const.Smallstring + "), " +
    "    B4RPGN       CHAR(1), " +
    "    RecordID INT AUTO_INCREMENT, " +
    "    Primary Key( RecordID ), " +
    "    INDEX        (IDNR)" +
    ") Engine=InnoDB;";
    
	 public static final String B5 = 		

    
    "CREATE TABLE IF NOT EXISTS b5" +
    "(" +
    "    IDNR      INT, " +
    "    B5SDCF       CHAR(1), " +
    "    B5FALN       CHAR(" + Const.Bigstring + "), " +
    "    B5FAPF       CHAR(" + Const.Smallstring + "), " +
    "    B5FAFN       CHAR(" + Const.Bigstring + "), " +
    "    B5FATT       CHAR(" + Const.Smallstring + "), " +
    "    B5FAPA       CHAR(" + Const.Smallstring + "), " +
    "    B5FAAY       SMALLINT, " +
    "    B5FAOC       CHAR(" + Const.Bigstring + "), " +
    "    B5FALL       CHAR(" + Const.Bigstring + "), " +
    "    B5FASD       SMALLINT, " +
    "    B5FASM       SMALLINT, " +
    "    B5FASY       SMALLINT, " +
    "    B5FASL       CHAR(" + Const.Bigstring + "), " +
    "    B5FADD       SMALLINT, " +
    "    B5FADM       SMALLINT, " +
    "    B5FADY       SMALLINT, " +
    "    B5FADL       CHAR(" + Const.Bigstring + "), " +
//    "    B5INFA   CHAR(1), " +
    "    RecordID INT AUTO_INCREMENT, " +
    "    Primary Key  (RecordID), " +
    "    INDEX        (IDNR)" +
    ") Engine=InnoDB;";

    
	 public static final String B6 = 		

    
    "CREATE TABLE IF NOT EXISTS b6" +
    "(" +
    "    IDNR         INT, " +
    "    B6SUHC       INT, " +
    "    B6SUHY       SMALLINT, " +
    "    B6SUHN       INT, " +
    "    B6SUHZ       SMALLINT, " +
    "    RecordID INT AUTO_INCREMENT, " +
    "    Primary Key  (RecordID), " +
    "    INDEX        (IDNR)" +
    ") Engine=InnoDB;";
    
    
	 public static final String D1 = 		

    
    "CREATE TABLE IF NOT EXISTS d1" +
    "(" +
    "    IDNR         INT, " +
    "    D1SDCC       SMALLINT, " +
    "    D1SDCL       CHAR(50), " +
    "    D1SDCN       INT, " +
    "    D1SDCH       SMALLINT, " +
    "    D1SDCI       SMALLINT, " +
    "    D1SDCD       SMALLINT, " +
    "    D1SDCM       SMALLINT, " +
    "    D1SDCY       SMALLINT, " +
    "    D1INFA       CHAR(1), " +
    "    D1INSQ       SMALLINT, " +
    "    D1INSG       CHAR(1), " +
    "    D1RPLN       CHAR(" + Const.Bigstring + "), " +
    "    D1RPPF       CHAR(" + Const.Smallstring + "), " +
    "    D1RPFN       CHAR(" + Const.Bigstring + "), " +
    "    D1RPTT       CHAR(" + Const.Smallstring + "), " +
    "    D1RPPA       CHAR(" + Const.Smallstring + "), " +
    "    D1RPOC       CHAR(" + Const.Bigstring + "), " +
    "    D1RPBC       SMALLINT, " +
    "    D1RPBL       CHAR(" + Const.Bigstring + "), " +
    "    D1RPLL       CHAR(" + Const.Bigstring + "), " +
    "    D1RPCS       CHAR(1), " +
    "    D1RPGN       CHAR(1), " +
    "    D1RPDD       SMALLINT, " +
    "    D1RPDM       SMALLINT, " +
    "    D1RPDY       SMALLINT, " +
    "    D1RPDH       SMALLINT, " +
    "    D1RPDI       SMALLINT, " +
    "    D1RPDL       CHAR(" + Const.Bigstring + "), " +
    "    D1RPAD       SMALLINT, " +
    "    D1RPAW       SMALLINT, " +
    "    D1RPAM       SMALLINT, " +
    "    D1RPAY       SMALLINT, " +
    "    D1FALN       CHAR(" + Const.Bigstring + "), " +
    "    D1FAPF       CHAR(" + Const.Smallstring + "), " +
    "    D1FAFN       CHAR(" + Const.Bigstring + "), " +
    "    D1FATT       CHAR(" + Const.Smallstring + "), " +
    "    D1FAPA       CHAR(" + Const.Smallstring + "), " +
    "    D1FACA       CHAR(1), " +
    "    D1FAOC       CHAR(" + Const.Bigstring + "), " +
    "    D1FAAY       SMALLINT, " +
    "    D1FALL       CHAR(" + Const.Bigstring + "), " +
    "    D1MOLN       CHAR(" + Const.Bigstring + "), " +
    "    D1MOPF       CHAR(" + Const.Smallstring + "), " +
    "    D1MOFN       CHAR(" + Const.Bigstring + "), " +
    "    D1MOTT       CHAR(" + Const.Smallstring + "), " +
    "    D1MOPA       CHAR(" + Const.Smallstring + "), " +
    "    D1MOCA       CHAR(1), " +
    "    D1MOOC       CHAR(" + Const.Bigstring + "), " +
    "    D1MOAY       SMALLINT, " +
    "    D1MOLL       CHAR(" + Const.Bigstring + "), " +
    "    D1SDCE       CHAR(1), " +
    "    D_E_P_L      CHAR(5), " +
    "    D_E_P_O      CHAR(5), " +
    "    RecordID INT AUTO_INCREMENT, " +
    "    Primary Key  (RecordID), " +
    "    INDEX        (IDNR)" +
    ") Engine=InnoDB;";
    
    
    
	 public static final String D2 = 		

    
    "CREATE TABLE IF NOT EXISTS d2" +
    "(" +
    "    IDNR         INT, " +
    "    D2S_SQ       SMALLINT, " +
    "    D2S_LN       CHAR(" + Const.Bigstring + "), " +
    "    D2S_PF       CHAR(" + Const.Smallstring + "), " +
    "    D2S_FN       CHAR(" + Const.Bigstring + "), " +
    "    D2S_TT       CHAR(" + Const.Smallstring + "), " +
    "    D2S_PA       CHAR(" + Const.Smallstring + "), " +
    "    D2S_CA       CHAR(1), " +
    "    D2S_AY       SMALLINT, " +
    "    D2S_OC       CHAR(" + Const.Bigstring + "), " +
    "    D2S_LL       CHAR(" + Const.Bigstring + "), " +
    "    RecordID INT AUTO_INCREMENT, " +
    "    Primary Key  (RecordID), " +
    "    INDEX        (IDNR)" +
    ") Engine=InnoDB;";
    
    
	 public static final String D3 = 		

    
    "CREATE TABLE IF NOT EXISTS d3" +
    "(" +
    "    IDNR         INT, " +
    "    D3I_SQ       SMALLINT, " +
    "    D3I_LN       CHAR(" + Const.Bigstring + "), " +
    "    D3I_PF       CHAR(" + Const.Smallstring + "), " +
    "    D3I_FN       CHAR(" + Const.Bigstring + "), " +
    "    D3I_TT       CHAR(" + Const.Smallstring + "), " +
    "    D3I_PA       CHAR(" + Const.Smallstring + "), " +
    "    D3I_LS       CHAR(" + Const.Bigstring + "), " +
    "    D3I_AY       SMALLINT, " +
    "    D3I_OC       CHAR(" + Const.Bigstring + "), " +
    "    D3I_LL       CHAR(" + Const.Bigstring + "), " +
    "    D3I_SG       CHAR(1), " +
    "    RecordID INT AUTO_INCREMENT, " +
    "    Primary Key  (RecordID), " +
    "    INDEX        (IDNR)" +
    ") Engine=InnoDB;";
    
    
	 public static final String D4 = 		

    
    "CREATE TABLE IF NOT EXISTS d4" +
    "(" +
    "    IDNR         INT, " +
    "    D4SDMI       TEXT, " +
    "    RecordID INT AUTO_INCREMENT, " +
    "    Primary Key  (RecordID), " +
    "    INDEX        (IDNR)" +
    ") Engine=InnoDB;";
    
    
	 public static final String M0 = 		

    
    "CREATE TABLE IF NOT EXISTS m0" +
    "(" +
    "    IDNR         INT, " +
    "    M0SDMC       INT, " +
    "    RecordID INT AUTO_INCREMENT, " +
    "    Primary Key  (RecordID), " +
    "    UNIQUE INDEX (IDNR)" +
    ") Engine=InnoDB;";
    
    
	 public static final String M1 = 		

    
    
    "CREATE TABLE IF NOT EXISTS m1" +
    "(" +
    "    IDNR         INT, " +
    "    M1SDCN       INT, " +
    "    M1SDML       CHAR(" + Const.Bigstring + "), " +
    "    M1SDMH       SMALLINT, " +
    "    MAR_CD       SMALLINT, " +
    "    MAR_CM       SMALLINT, " +
    "    MAR_CY       SMALLINT, " +
    "    M1SDSE       CHAR(1), " +
    "    M1SDSD       SMALLINT, " +
    "    M1SDSM       SMALLINT, " +
    "    M1SDSY       SMALLINT, " +
    "    M1SDSL       CHAR(" + Const.Bigstring + "), " +
    "    M1SDED       SMALLINT, " +
    "    M1SDEM       SMALLINT, " +
    "    M1SDEY       SMALLINT, " +
    "    M1SDEL       CHAR(" + Const.Bigstring + "), " +
    "    M1GRAY       SMALLINT, " +
    "    M1BRAY       SMALLINT, " +
    "    M1RPGN       CHAR(1), " +
    "    M1GRLN       CHAR(" + Const.Bigstring + "), " +
    "    M1GRPF       CHAR(" + Const.Smallstring + "), " +
    "    M1GRFN       CHAR(" + Const.Bigstring + "), " +
    "    M1GRTT       CHAR(" + Const.Smallstring + "), " +
    "    M1GRPA       CHAR(" + Const.Smallstring + "), " +
    "    M1GROC       CHAR(" + Const.Bigstring + "), " +
    "    M1GRBL       CHAR(" + Const.Bigstring + "), " +
    "    M1GRLL       CHAR(" + Const.Bigstring + "), " +
    "    M1GRLF       CHAR(" + Const.Bigstring + "), " +
    "    M1GRLO       CHAR(" + Const.Bigstring + "), " +
    "    M1GRPL       CHAR(" + Const.Bigstring + "), " +
    "    M1GRQL       CHAR(" + Const.Bigstring + "), " +
    "    M1GRCS       CHAR(1), " +
    "    M1GRSG       CHAR(1), " +
    "    M1BRLN       CHAR(" + Const.Bigstring + "), " +
    "    M1BRPF       CHAR(" + Const.Smallstring + "), " +
    "    M1BRFN       CHAR(" + Const.Bigstring + "), " +
    "    M1BRTT       CHAR(" + Const.Smallstring + "), " +
    "    M1BRPA       CHAR(" + Const.Smallstring + "), " +
    "    M1BROC       CHAR(" + Const.Bigstring + "), " +
    "    M1BRBL       CHAR(" + Const.Bigstring + "), " +
    "    M1BRLL       CHAR(" + Const.Bigstring + "), " +
    "    M1BRLF       CHAR(" + Const.Bigstring + "), " +
    "    M1BRLO       CHAR(" + Const.Bigstring + "), " +
    "    M1BRPL       CHAR(" + Const.Bigstring + "), " +
    "    M1BRQL       CHAR(" + Const.Bigstring + "), " +
    "    M1BRCS       CHAR(1), " +
    "    M1BRSG       CHAR(1), " +
    "    M1GFCA       CHAR(1), " +
    "    M1GFMP       CHAR(1), " +
    "    M1GFLN       CHAR(" + Const.Bigstring + "), " +
    "    M1GFPF       CHAR(" + Const.Smallstring + "), " +
    "    M1GFFN       CHAR(" + Const.Bigstring + "), " +
    "    M1GFTT       CHAR(" + Const.Smallstring + "), " +
    "    M1GFPA       CHAR(" + Const.Smallstring + "), " +
    "    M1GFOC       CHAR(" + Const.Bigstring + "), " +
    "    M1GFLL       CHAR(" + Const.Bigstring + "), " +
    "    M1GFDL       CHAR(" + Const.Bigstring + "), " +
    "    M1GFSG       CHAR(1), " +
    "    M1GFAY       SMALLINT, " +
    "    M1GMCA       CHAR(1), " +
    "    M1GMMP       CHAR(1), " +
    "    M1GMLN       CHAR(" + Const.Bigstring + "), " +
    "    M1GMPF       CHAR(" + Const.Smallstring + "), " +
    "    M1GMFN       CHAR(" + Const.Bigstring + "), " +
    "    M1GMTT       CHAR(" + Const.Smallstring + "), " +
    "    M1GMPA       CHAR(" + Const.Smallstring + "), " +
    "    M1GMOC       CHAR(" + Const.Bigstring + "), " +
    "    M1GMLL       CHAR(" + Const.Bigstring + "), " +
    "    M1GMDL       CHAR(" + Const.Bigstring + "), " +
    "    M1GMSG       CHAR(1), " +
    "    M1GMAY       SMALLINT, " +
    "    M1BFCA       CHAR(1), " +
    "    M1BFMP       CHAR(1), " +
    "    M1BFLN       CHAR(" + Const.Bigstring + "), " +
    "    M1BFPF       CHAR(" + Const.Smallstring + "), " +
    "    M1BFFN       CHAR(" + Const.Bigstring + "), " +
    "    M1BFTT       CHAR(" + Const.Smallstring + "), " +
    "    M1BFPA       CHAR(" + Const.Smallstring + "), " +
    "    M1BFOC       CHAR(" + Const.Bigstring + "), " +
    "    M1BFLL       CHAR(" + Const.Bigstring + "), " +
    "    M1BFDL       CHAR(" + Const.Bigstring + "), " +
    "    M1BFSG       CHAR(1), " +
    "    M1BFAY       SMALLINT, " +
    "    M1BMCA       CHAR(1), " +
    "    M1BMMP       CHAR(1), " +
    "    M1BMLN       CHAR(" + Const.Bigstring + "), " +
    "    M1BMPF       CHAR(" + Const.Smallstring + "), " +
    "    M1BMFN       CHAR(" + Const.Bigstring + "), " +
    "    M1BMTT       CHAR(" + Const.Smallstring + "), " +
    "    M1BMPA       CHAR(" + Const.Smallstring + "), " +
    "    M1BMOC       CHAR(" + Const.Bigstring + "), " +
    "    M1BMLL       CHAR(" + Const.Bigstring + "), " +
    "    M1BMDL       CHAR(" + Const.Bigstring + "), " +
    "    M1BMSG       CHAR(1), " +
    "    M1BMAY       SMALLINT, " +
    "    M1SDBB       CHAR(1), " +
    "    M1SDDP       CHAR(1), " +
    "    M1SDDE       CHAR(1), " +
    "    M1SDNM       CHAR(1), " +
    "    M1SDPN       CHAR(1), " +
    "    M1SDAF       CHAR(1), " +
    "    M1SDAP       CHAR(1), " +
    "    M1SDPC       CHAR(1), " +
    "    M1SDPG       CHAR(1), " +
    "    D_E_P_L      CHAR(5), " +
    "    D_E_P_O      CHAR(5), " +
    "    RecordID INT AUTO_INCREMENT, " +
    "    Primary Key  (RecordID), " +
    "    INDEX        (IDNR)" +
    ") Engine=InnoDB;";
    
	 public static final String M2 = 		

    
    "CREATE TABLE IF NOT EXISTS m2" +
    "(" +
    "    IDNR     INT, " +
    "    MAR_CD   SMALLINT, " +
    "    MAR_CM   SMALLINT, " +
    "    MAR_CY   SMALLINT, " +
    "    M2SDSQ   SMALLINT, " +
    "    M2U_UM   INT, " +
    "    M2U_UD   INT, " +
    "    M2U_UY   INT, " +
    "    M2U_UC   INT, " +
    "    M2U_UL   CHAR(50), " +
    "    RecordID INT AUTO_INCREMENT, " +
    "    Primary Key  (RecordID), " +
    "    INDEX        (IDNR)" +
    ") Engine=InnoDB;";
    
	 public static final String M3 = 		

    
    "CREATE TABLE IF NOT EXISTS m3" +
    "(" +
    "    IDNR         INT, " +
    "    MAR_CD       SMALLINT, " +
    "    MAR_CM       SMALLINT, " +
    "    MAR_CY       SMALLINT, " +
    "    M3SDSQ       SMALLINT, " +
    "    M3RPGN       CHAR(1), " +
    "    M3S_LN       CHAR(" + Const.Bigstring + "), " +
    "    M3S_PF       CHAR(" + Const.Smallstring + "), " +
    "    M3S_FN       CHAR(" + Const.Bigstring + "), " +
    "    M3S_TT       CHAR(" + Const.Smallstring + "), " +
    "    M3S_PA       CHAR(" + Const.Smallstring + "), " +
    "    M3S_SR       CHAR(1), " +
    "    RecordID INT AUTO_INCREMENT, " +
    "    Primary Key  (RecordID), " +
    "    INDEX        (IDNR)" +
    ") Engine=InnoDB;";
    
    
	 public static final String M4 = 		

    
    "CREATE TABLE IF NOT EXISTS m4" +
    "(" +
    "    IDNR         INT, " +
    "    MAR_CD       SMALLINT, " +
    "    MAR_CM       SMALLINT, " +
    "    MAR_CY       SMALLINT, " +
    "    M4SDSQ       SMALLINT, " +
    "    M4W_LN       CHAR(" + Const.Bigstring + "), " +
    "    M4W_PF       CHAR(" + Const.Smallstring + "), " +
    "    M4W_FN       CHAR(" + Const.Bigstring + "), " +
    "    M4W_TT       CHAR(" + Const.Smallstring + "), " +
    "    M4W_PA       CHAR(" + Const.Smallstring + "), " +
    "    M4W_AY       SMALLINT, " +
    "    M4W_OC       CHAR(" + Const.Bigstring + "), " +
    "    M4W_LL       CHAR(" + Const.Bigstring + "), " +
    "    M4W_SG       CHAR(1), " +
    "    M4W_LR       CHAR(1), " +
    "    M4W_LS       CHAR(" + Const.Bigstring + "), " +
    "    RecordID INT AUTO_INCREMENT, " +
    "    Primary Key  (RecordID), " +
    "    INDEX        (IDNR)" +
    ") Engine=InnoDB;";
    
    
	 public static final String M5 = 		

    
    "CREATE TABLE IF NOT EXISTS m5" +
    "(" +
    "    IDNR         INT, " +
    "    MAR_CD       SMALLINT, " +
    "    MAR_CM       SMALLINT, " +
    "    MAR_CY       SMALLINT, " +
    "    M5C_SQ       SMALLINT, " +
    "    M5C_LN       CHAR(" + Const.Bigstring + "), " +
    "    M5C_PF       CHAR(" + Const.Smallstring + "), " +
    "    M5C_FN       CHAR(" + Const.Bigstring + "), " +
    "    M5C_TT       CHAR(" + Const.Smallstring + "), " +
    "    M5C_PA       CHAR(" + Const.Smallstring + "), " +
    "    M5C_BD       SMALLINT, " +
    "    M5C_BM       SMALLINT, " +
    "    M5C_BY       SMALLINT, " +
    "    M5C_GN       CHAR(1), " +
    "    M5C_BL       CHAR(" + Const.Bigstring + "), " +
    "    M5C_RR       CHAR(1), " +
    "    M5C_RW       CHAR(1), " +
    "    M5C_FD       SMALLINT, " +
    "    M5C_FM       SMALLINT, " +
    "    M5C_FY       SMALLINT, " +
    "    M5C_FL       CHAR(" + Const.Bigstring + "), " +
    "    M5C_OD       SMALLINT, " +
    "    M5C_OM       SMALLINT, " +
    "    M5C_OY       SMALLINT, " +
    "    M5C_OL       CHAR(" + Const.Bigstring + "), " +
    "    RecordID INT AUTO_INCREMENT, " +
    "    Primary Key  (RecordID), " +
    "    INDEX        (IDNR)" +
    ") Engine=InnoDB;";
    
	 public static final String M6 = 		

    
    "CREATE TABLE IF NOT EXISTS m6" +
    "(" +
    "    IDNR     INT, " +
    "    MAR_CD   SMALLINT, " +
    "    MAR_CM   SMALLINT, " +
    "    MAR_CY   SMALLINT, " +
    "    M6SDMI   TEXT, " +
    "    RecordID INT AUTO_INCREMENT, " +
    "    Primary Key  (RecordID), " +
    "    INDEX        (IDNR)" +
    ") Engine=InnoDB;";




	 public static final String LU_LN =
	
    "CREATE TABLE LU_LN" +
    "(" +
    "    ID       INT NOT NULL AUTO_INCREMENT, " +
    "    Value    CHAR(50), " +
    "    IsNew    CHAR(1), " +
    "    PRIMARY KEY(ID), " +
    "    UNIQUE INDEX(Value)" +
    ") Engine=InnoDB;";

	 public static final String LU_FN =
	
    "CREATE TABLE LU_FN" +
    "(" +
    "    ID       INT NOT NULL AUTO_INCREMENT, " +
    "    Value    CHAR(50), " +
    "    IsNew    CHAR(1), " +
    "    PRIMARY KEY (ID), " +
    "    UNIQUE INDEX (Value)" +
    ") Engine=InnoDB;";

	 public static final String LU_OC =
		 
    "CREATE TABLE LU_OC" +
    "(" +
    "    ID       INT NOT NULL AUTO_INCREMENT, " +
    "    Value    CHAR(50), " +
    "    IsNew    CHAR(1), " +
    "    PRIMARY KEY (ID), " +
    "    UNIQUE INDEX (Value)" +
    ") Engine=InnoDB;";
	 
	 public static final String LU_LL =
		 
    "CREATE TABLE LU_LL" +
    "(" +
    "    ID       INT NOT NULL AUTO_INCREMENT, " +
    "    Value    CHAR(50), " +
    "    IsNew    CHAR(1), " +
    "    PRIMARY KEY (ID), " +
    "    UNIQUE INDEX (Value)" +
    ") Engine=InnoDB;";

	public static final String LU_PF = 
		
    "CREATE TABLE LU_PF" +
    "(" +
    "    ID       INT NOT NULL AUTO_INCREMENT, " +
    "    Value    CHAR(50), " +
    "    IsNew    CHAR(1), " +
    "    PRIMARY KEY (ID), " +
    "    UNIQUE INDEX (Value)" +
    ") Engine=InnoDB;";
	
	 public static final String LU_LS =
		 
    "CREATE TABLE LU_LS" +
    "(" +
    "    RELATIE    CHAR(50), " +
    "    NWINLST    CHAR(1), " +
    "    RecordID   INT NOT NULL AUTO_INCREMENT, " +
    "    PRIMARY KEY (RecordID), " +
    "    UNIQUE INDEX (RELATIE)" +
    ") Engine=InnoDB;";


	 public static final String B0_TRUNCATE = " TRUNCATE TABLE b0;";
	 public static final String B1_TRUNCATE = " TRUNCATE TABLE b1;";
	 public static final String B2_TRUNCATE = " TRUNCATE TABLE b2;";
	 public static final String B3_TRUNCATE = " TRUNCATE TABLE b3;";
	 public static final String B4_TRUNCATE = " TRUNCATE TABLE b4;";
	 public static final String B5_TRUNCATE = " TRUNCATE TABLE b5;";
	 public static final String B6_TRUNCATE = " TRUNCATE TABLE b6;";
	 
	 public static final String M0_TRUNCATE = " TRUNCATE TABLE m0;";
	 public static final String M1_TRUNCATE = " TRUNCATE TABLE m1;";
	 public static final String M2_TRUNCATE = " TRUNCATE TABLE m2;";
	 public static final String M3_TRUNCATE = " TRUNCATE TABLE m3;";
	 public static final String M4_TRUNCATE = " TRUNCATE TABLE m4;";
	 public static final String M5_TRUNCATE = " TRUNCATE TABLE m5;";
	 public static final String M6_TRUNCATE = " TRUNCATE TABLE m6;";

	 public static final String D1_TRUNCATE = " TRUNCATE TABLE d1;";
	 public static final String D2_TRUNCATE = " TRUNCATE TABLE d2;";
	 public static final String D3_TRUNCATE = " TRUNCATE TABLE d3;";
	 public static final String D4_TRUNCATE = " TRUNCATE TABLE d4;";


}




