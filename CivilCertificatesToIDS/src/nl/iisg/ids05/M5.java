package nl.iisg.ids05;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import nl.iisg.idscontext.ContextElement;
import nl.iisg.idscontext.Contxt;

@Entity 
@Table(name="m5")
public class M5 {

     @Column(name="IDNR")         private int idnr;
     @Column(name="MAR_CD")       private int mar_cd;
     @Column(name="MAR_CM")       private int mar_cm;
     @Column(name="MAR_CY")       private int mar_cy;
     @Column(name="M5C_SQ")       private int m5c_sq;
     @Column(name="M5C_LN")       private String m5c_ln;
     @Column(name="M5C_PF")       private String m5c_pf;
     @Column(name="M5C_FN")       private String m5c_fn;
     @Column(name="M5C_TT")       private String m5c_tt;
     @Column(name="M5C_PA")       private String m5c_pa;
     @Column(name="M5C_BD")       private int m5c_bd;
     @Column(name="M5C_BM")       private int m5c_bm;
     @Column(name="M5C_BY")       private int m5c_by;
     @Column(name="M5C_GN")       private String m5c_gn;
     @Column(name="M5C_BL")       private String m5c_bl;
     @Column(name="M5C_RR")       private String m5c_rr;
     @Column(name="M5C_RW")       private String m5c_rw;
     @Column(name="M5C_FD")       private int m5c_fd;
     @Column(name="M5C_FM")       private int m5c_fm;
     @Column(name="M5C_FY")       private int m5c_fy;
     @Column(name="M5C_FL")       private String m5c_fl;
     @Column(name="M5C_OD")       private int m5c_od;
     @Column(name="M5C_OM")       private int m5c_om;
     @Column(name="M5C_OY")       private int m5c_oy;
     @Column(name="M5C_OL")       private String m5c_ol;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")     private int recordID;
     
     @Transient                   private M1   m1   = null;

 
     public void convert(EntityManager em){   	
    	 
    	 int uniquifier = getMar_cy() * 100 * 1000 + (getMar_cm() * 1000) ; // The Id_I will be like 184906xxx
    	 
    	 // Voorkinderen
    	 
    	 int Id_I_VK = 40 + getM5c_sq() + uniquifier;
    	 int Id_I_GR = 11 + uniquifier; 
    	 int Id_I_BR = 12 + uniquifier;


    	 if(getM5c_ln() != null && getM5c_ln().trim().length() > 0 && !getM5c_ln().trim().equalsIgnoreCase("N"))
    		 Utils.addIndiv(em, getIdnr(), Id_I_VK, "MC M5", "LAST_NAME", getM5c_ln(), "Missing", "Time_invariant", 0, 0, 0);
    	 else 
    		 return;
    	 if(getM5c_pf() != null && getM5c_pf().trim().length() > 0)
    		 Utils.addIndiv(em, getIdnr(), Id_I_VK, "MC M5", "PREFIX_LAST_NAME", getM5c_pf(), "Missing", "Time_invariant", 0, 0, 0);
    	 if(getM5c_fn() != null && getM5c_fn().trim().length() > 0)
    		 Utils.addIndiv(em, getIdnr(), Id_I_VK, "MC M5", "FIRST_NAME", getM5c_fn(), "Missing", "Time_invariant", 0, 0, 0);
    	 if(getM5c_gn() != null && getM5c_gn().trim().length() > 0)
    		 Utils.addIndiv(em, getIdnr(), Id_I_VK, "MC M5", "SEX", Utils.sex(getM5c_gn()), "Missing", "Time_invariant", 0, 0, 0);
		 
		 Utils.addIndiv(em, getIdnr(), Id_I_VK, "MC M5", "BIRTH_DATE", null, "Reported", "Exact", getM5c_bd(), getM5c_bm(), getM5c_by());
		 
		 Utils.addIndiv(em, getIdnr(), Id_I_VK, "MC M5", "HSN_IDENTIFIER", "" + getIdnr(), "Missing", "Time_invariant", 0, 0, 0);

		 
    	 if(getM5c_bl() != null){
    		 ContextElement ce = Contxt.get2(getM5c_bl());
    		 if(ce != null)
    			 Utils.addIndivAndContext(null, ce, em, getIdnr(), Id_I_VK, "MC M5", "BIRTH_LOCATION", "Reported", "Exact", getM5c_bd(),  getM5c_bm(), getM5c_by());
    	 }
    	 
    	 String relation = "";
    	 if(getM5c_rr() != null && getM5c_rr().equalsIgnoreCase("J")){
    		 if(getM5c_gn() != null && getM5c_gn().equalsIgnoreCase("M"))
    			 relation = "Zoon";
    		 else
    			 relation = "Dochter";
    	 }
    	 if(relation.length() != 0){
    		 if(getM1().getM1rpgn().equalsIgnoreCase("M")){
        		 Utils.addIndivIndiv(em, getIdnr(), Id_I_GR, Id_I_VK, "MC M5", "Vader", "Missing", "Time_invariant", 0, 0, 0);
        		 Utils.addIndivIndiv(em, getIdnr(), Id_I_VK, Id_I_GR, "MC M5", relation, "Missing", "Time_invariant", 0, 0, 0);

    		 }
    		 else
        		 if(getM1().getM1rpgn().equalsIgnoreCase("V")){
            		 Utils.addIndivIndiv(em, getIdnr(), Id_I_GR, Id_I_VK, "MC M5", "Moeder", "Missing", "Time_invariant", 0, 0, 0);
            		 Utils.addIndivIndiv(em, getIdnr(), Id_I_VK, Id_I_GR, "MC M5", relation, "Missing", "Time_invariant", 0, 0, 0);
        		 }
    	 }
    	 else{
    		 if(getM1().getM1rpgn().equalsIgnoreCase("M")){
        		 Utils.addIndivIndiv(em, getIdnr(), Id_I_GR, Id_I_VK, "MC M5", "Vader", "Missing", "Time_invariant", 0, 0, 0);
        		 Utils.addIndivIndiv(em, getIdnr(), Id_I_VK, Id_I_GR, "MC M5", "Kind", "Missing", "Time_invariant", 0, 0, 0);

    		 }
    		 else
        		 if(getM1().getM1rpgn().equalsIgnoreCase("V")){
            		 Utils.addIndivIndiv(em, getIdnr(), Id_I_GR, Id_I_VK, "MC M5", "Moeder", "Missing", "Time_invariant", 0, 0, 0);
            		 Utils.addIndivIndiv(em, getIdnr(), Id_I_VK, Id_I_GR, "MC M5", "Kind", "Missing", "Time_invariant", 0, 0, 0);
        		 }
    	 }
    	 
     }

     
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public int getMar_cd() {
		return mar_cd;
	}
	public void setMar_cd(int mar_cd) {
		this.mar_cd = mar_cd;
	}
	public int getMar_cm() {
		return mar_cm;
	}
	public void setMar_cm(int mar_cm) {
		this.mar_cm = mar_cm;
	}
	public int getMar_cy() {
		return mar_cy;
	}
	public void setMar_cy(int mar_cy) {
		this.mar_cy = mar_cy;
	}
	public int getM5c_sq() {
		return m5c_sq;
	}
	public void setM5c_sq(int m5c_sq) {
		this.m5c_sq = m5c_sq;
	}
	public String getM5c_ln() {
		return m5c_ln;
	}
	public void setM5c_ln(String m5c_ln) {
		this.m5c_ln = m5c_ln;
	}
	public String getM5c_pf() {
		return m5c_pf;
	}
	public void setM5c_pf(String m5c_pf) {
		this.m5c_pf = m5c_pf;
	}
	public String getM5c_fn() {
		return m5c_fn;
	}
	public void setM5c_fn(String m5c_fn) {
		this.m5c_fn = m5c_fn;
	}
	public String getM5c_tt() {
		return m5c_tt;
	}
	public void setM5c_tt(String m5c_tt) {
		this.m5c_tt = m5c_tt;
	}
	public String getM5c_pa() {
		return m5c_pa;
	}
	public void setM5c_pa(String m5c_pa) {
		this.m5c_pa = m5c_pa;
	}
	public int getM5c_bd() {
		return m5c_bd;
	}
	public void setM5c_bd(int m5c_bd) {
		this.m5c_bd = m5c_bd;
	}
	public int getM5c_bm() {
		return m5c_bm;
	}
	public void setM5c_bm(int m5c_bm) {
		this.m5c_bm = m5c_bm;
	}
	public int getM5c_by() {
		return m5c_by;
	}
	public void setM5c_by(int m5c_by) {
		this.m5c_by = m5c_by;
	}
	public String getM5c_gn() {
		return m5c_gn;
	}
	public void setM5c_gn(String m5c_gn) {
		this.m5c_gn = m5c_gn;
	}
	public String getM5c_bl() {
		return m5c_bl;
	}
	public void setM5c_bl(String m5c_bl) {
		this.m5c_bl = m5c_bl;
	}
	public String getM5c_rr() {
		return m5c_rr;
	}
	public void setM5c_rr(String m5c_rr) {
		this.m5c_rr = m5c_rr;
	}
	public String getM5c_rw() {
		return m5c_rw;
	}
	public void setM5c_rw(String m5c_rw) {
		this.m5c_rw = m5c_rw;
	}
	public int getM5c_fd() {
		return m5c_fd;
	}
	public void setM5c_fd(int m5c_fd) {
		this.m5c_fd = m5c_fd;
	}
	public int getM5c_fm() {
		return m5c_fm;
	}
	public void setM5c_fm(int m5c_fm) {
		this.m5c_fm = m5c_fm;
	}
	public int getM5c_fy() {
		return m5c_fy;
	}
	public void setM5c_fy(int m5c_fy) {
		this.m5c_fy = m5c_fy;
	}
	public String getM5c_fl() {
		return m5c_fl;
	}
	public void setM5c_fl(String m5c_fl) {
		this.m5c_fl = m5c_fl;
	}
	public int getM5c_od() {
		return m5c_od;
	}
	public void setM5c_od(int m5c_od) {
		this.m5c_od = m5c_od;
	}
	public int getM5c_om() {
		return m5c_om;
	}
	public void setM5c_om(int m5c_om) {
		this.m5c_om = m5c_om;
	}
	public int getM5c_oy() {
		return m5c_oy;
	}
	public void setM5c_oy(int m5c_oy) {
		this.m5c_oy = m5c_oy;
	}
	public String getM5c_ol() {
		return m5c_ol;
	}
	public void setM5c_ol(String m5c_ol) {
		this.m5c_ol = m5c_ol;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}
	public M1 getM1() {
		return m1;
	}
	public void setM1(M1 m1) {
		this.m1 = m1;
	}

	
}
