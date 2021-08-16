package iisg.nl.hsnmigrate;

public class Statistics {
	
	
	
	
	
	public static final String truncateM0  =
		
		"TRUNCATE TABLE hsn_civrec_std.m0;";
		
		
	public static final String insertM0  =
		
	        "INSERT INTO hsn_civrec_std.m0 " +
	        "   SELECT IDNR, COUNT(MAR_CY), NULL " +
	        "   FROM hsn_civrec_std.m1 " +
	        "   GROUP BY IDNR;";
	
	
	public static final String truncateLog  =
	
		"TRUNCATE TABLE hsn_msg.summary;";
	
	
	public static final String insertLog  =
		
        "INSERT INTO hsn_msg.summary " +
        "   SELECT " +
        "       PROVNR, " +                                                     //provincie
        "       IF((B1SDCC = 806) OR (B1SDCC = 372) OR " +
        "           (B1SDCC = 439), \"*\", \"\") 3GS, " +                           //A'dam, R'dam, 's Gr.
        "       IF((B1SDCY > 1811) AND (B1SDCY < 1863), \"1812-1862\", " +
        "           IF((B1SDCY > 1862) AND (B1SDCY < 1883), \"_1863-1882\", " +
        "               IF((B1SDCY > 1882) AND (B1SDCY < 1923), \"__1883-1922\", " +
        "                   NULL))) COHORT, " +                                 //cohort
        "       COUNT(b1.IDNR), " +                                             //# geb akten
        "       COUNT(d1.IDNR), " +                                             //# ovl akten
        "       SUM((d1.D1RPDY - b1.B1RPBY) < 16), " +                          //# ovl < 16
        "       NULL, " +                                                       //% ovl < 16
        "       COUNT(pkknd.IDNR), " +                                             //# PKKND
        "       COUNT((pkknd.IDNR OR d1.IDNR)), " +                                //# ovl+PKKND
        "       NULL, " +                                                       //% nitv ovl akten
        "       SUM((D1RPDY > 0) OR (OJRPERP > 0) OR (OVLJAAR > 0)), " +        //# ovl +datum
        "       NULL, " +                                                       //% ovl +datum
        "       NULL, " +                                                       //# verwachte huw akten
        "       SUM(M0SDMC), " +                                                //# huw akten
        "       COUNT(M0SDMC), " +                                              //# gehuwde pers
        "       NULL, " +                                                       //% huw akten >1
        "       NULL, " +                                                       //# nitv huw akten
        "       NULL, " +                                                       //% nitv huw akten
        "       COUNT((d1.IDNR OR pkknd.IDNR) AND M0SDMC), " +                     //# pers +ovl/PKKND +huw
        "       COUNT((d1.IDNR OR pkknd.IDNR OR (OVLJAAR > 0)) AND M0SDMC), " +    //# pers +ovl/PKKND +huw +datum
        "       NULL, " +                                                       //PKKNDs > 500000
        "       NULL  " +                                                       //RecordID
        "   FROM " +
        "       hsn_civrec_std.b1 " +
        "           LEFT JOIN hsn_civrec_org.plaats ON B1SDCC = GEMNR " +
        "           LEFT JOIN hsn_civrec_org.pkknd ON b1.IDNR = pkknd.IDNR " +
        "           LEFT JOIN hsn_civrec_org.beheer ON b1.IDNR = beheer.IDNR " +
        "           LEFT JOIN hsn_civrec_std.d1 ON b1.IDNR = d1.IDNR " +
        "           LEFT JOIN hsn_civrec_std.m0 ON b1.IDNR = m0.IDNR " +
        "   WHERE " +
        "       (b1.IDNR < 500000) " +
        "   GROUP BY PROVNR, 3GS, COHORT";
	
	
	
	
	
	// Calculation of number of partner PKKNDs used
	
	public static final String selectLogX  =
		
        "   SELECT " +
        "       PROVNR, " +                                                     //provincie
        "       IF((B1SDCC = 806) OR (B1SDCC = 372) OR " +
        "           (B1SDCC = 439), \"*\", \"\") 3GS, " +                           //A'dam, R'dam, 's Gr.
        "       IF((B1SDCY > 1811) AND (B1SDCY < 1863), \"1812-1862\", " +
        "           IF((B1SDCY > 1862) AND (B1SDCY < 1883), \"_1863-1882\", " +
        "               IF((B1SDCY > 1882) AND (B1SDCY < 1923), \"__1883-1922\", " +
        "                   NULL))) COHORT, " +                                 //cohort
        "       COUNT(pkknd.IDNR) AS PK500" +                                      //PK partner
        "   FROM " +
        "       hsn_civrec_std.b1, hsn_civrec_org.pkknd, hsn_civrec_org.plaats " +
        "   WHERE " +
        "       (b1.IDNR = pkknd.IDNRP) AND " +
        "       (B1SDCC = GEMNR) AND " +
        "       (pkknd.IDNR > 500000) " +
        "   GROUP BY " +
        "       PROVNR, 3GS, COHORT" ;
	
	public static final String statView =
		
        "   CREATE OR REPLACE VIEW statview as SELECT " +
        "       PROVNR, " +                                                     //provincie
        "       IF((B1SDCC = 806) OR (B1SDCC = 372) OR " +
        "           (B1SDCC = 439), \"*\", \"\") 3GS, " +                           //A'dam, R'dam, 's Gr.
        "       IF((B1SDCY > 1811) AND (B1SDCY < 1863), \"1812-1862\", " +
        "           IF((B1SDCY > 1862) AND (B1SDCY < 1883), \"_1863-1882\", " +
        "               IF((B1SDCY > 1882) AND (B1SDCY < 1923), \"__1883-1922\", " +
        "                   NULL))) COHORT, " +                                 //cohort
        "       COUNT(pkknd.IDNR) AS PK500" +                                      //PK partner
        "   FROM " +
        "       hsn_civrec_std.b1, hsn_civrec_org.pkknd, hsn_civrec_org.plaats " +
        "   WHERE " +
        "       (b1.IDNR = pkknd.IDNRP) AND " +
        "       (B1SDCC = GEMNR) AND " +
        "       (pkknd.IDNR > 500000) " +
        "   GROUP BY " +
        "       PROVNR, 3GS, COHORT" ;
	
	
	public static final String updateLog1  =
		
		" UPDATE hsn_msg.summary SET PK500KPLUS=0 ";
	
		
	public static final String updateLog2  = 
	
		"UPDATE hsn_msg.summary " +
        "    SET " +
        "        PK500KPLUS = \"%PK500\" " + 
        "    WHERE " +
        "        (hsn_msg.summary.PROV = \"%PROVNR\")  AND " +
        "        (hsn_msg.summary.3GS = \"%3GS\")  AND " +
        "        (hsn_msg.summary.COH = \"%COHORT\")";
	
	
	public static final String updateLog3  =
		
		  "   UPDATE hsn_msg.summary " +
	        "   SET " +
	        "       OVL16_  =  OVL16 / OVL * 100, " +
	        "       OVLITV_ = (GEB - OVLPK) / GEB * 100, " +
	        "       OVLD_   =  OVLD / GEB * 100, " +
	        "       VOLW    =  GEB - OVL16, " +
	        "       HUW2_   = (HUW - HUW1) / HUW * 100, " +
	        "       HUWITV  = ((GEB - OVL16) * 0.9) - HUW, " +
	        "       HUWITV_ = (((GEB - OVL16) * 0.9) - HUW) / (GEB - OVL16) * 100";
	

}
