package nl.iisg.ids05;



import java.util.ArrayList;

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
import nl.iisg.ref.Ref;
import nl.iisg.ref.Ref_Municipality;

@Entity
@Table(name="b1")
public class B1 {

	@Column(name="B1SDCC")       private int b1sdcc;
	@Column(name="B1SDCL")       private String b1sdcl;
	@Column(name="B1SDCY")       private int b1sdcy;
	@Column(name="B1SDCN")       private int b1sdcn;
	@Column(name="IDNR")         private int idnr;
	@Column(name="B1SDLI")       private String b1sdli;
	@Column(name="B1SDCH")       private int b1sdch;
	@Column(name="B1SDCD")       private int b1sdcd;
	@Column(name="B1SDCM")       private int b1sdcm;
	@Column(name="B1INFA")       private String b1infa;
	@Column(name="B1INLN")       private String b1inln;
	@Column(name="B1INPF")       private String b1inpf;
	@Column(name="B1INFN")       private String b1infn;
	@Column(name="B1INTT")       private String b1intt;
	@Column(name="B1INPA")       private String b1inpa;
	@Column(name="B1INAY")       private int b1inay;
	@Column(name="B1INOC")       private String b1inoc;
	@Column(name="B1INLL")       private String b1inll;
	@Column(name="B1INSG")       private String b1insg;
	@Column(name="B1RPBD")       private int b1rpbd;
	@Column(name="B1RPBM")       private int b1rpbm;
	@Column(name="B1RPBY")       private int b1rpby;
	@Column(name="B1RPBH")       private int b1rpbh;
	@Column(name="B1RPBI")       private int b1rpbi;
	@Column(name="B1RPGN")       private String b1rpgn;
	@Column(name="B1RPLL")       private String b1rpll;
	@Column(name="B1MOLN")       private String b1moln;
	@Column(name="B1MOPF")       private String b1mopf;
	@Column(name="B1MOFN")       private String b1mofn;
	@Column(name="B1MOTT")       private String b1mott;
	@Column(name="B1MOPA")       private String b1mopa;
	@Column(name="B1MOAY")       private int b1moay;
	@Column(name="B1MOCS")       private String b1mocs;
	@Column(name="B1MOOC")       private String b1mooc;
	@Column(name="B1MOLL")       private String b1moll;
	@Column(name="B1RPLN")       private String b1rpln;
	@Column(name="B1RPPF")       private String b1rppf;
	@Column(name="B1RPFN")       private String b1rpfn;
	@Column(name="B1RPTT")       private String b1rptt;
	@Column(name="B1RPPA")       private String b1rppa;
	@Column(name="D_E_P_L")      private String d_e_p_l;
	@Column(name="D_E_P_O")      private String d_e_p_o;
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="RecordID")     private int recordID;


	@Transient                  private           B0   b0   = null;               

	@Transient                  private ArrayList<B2>  b2L  = new ArrayList<B2>();
	@Transient                  private           B3   b3   = null;               
	@Transient                  private ArrayList<B4>  b4L  = new ArrayList<B4>();
	@Transient                  private           B5   b5   = null;               

	@Transient                  private ArrayList<M1>  m1L  = new ArrayList<M1>();
	@Transient                  private           D1   d1  = null;
	
    @Transient                   private A1  b1rplla;  
    @Transient                   private A1  b1molla;
    @Transient                   private A1  b1inlla;


	public void convert(EntityManager em){
		
		//System.out.println("Enter B1, idnr = "+ getIdnr());
		
		//if(getB1sdcc() != 806){
			//return;
			//System.out.println("b1sdcc = " + getB1sdcc() + ", certificate place =  " + ceCertificate.getName());
		//}


		// RP

		int Id_I_RP = 1; // Utils.getId_I();

		if(getB1rpln() != null && getB1rpln().trim().length() > 0  && !getB1rpln().trim().equalsIgnoreCase("N"))
			Utils.addIndiv(em, getIdnr(), Id_I_RP, "BC B1", "LAST_NAME", getB1rpln(), "Event", "Exact", getB1rpbd(),  getB1rpbm(), getB1rpby());
		else {
			System.out.println("Skipping IDNR " + getB0().getIdnr());
			return;
		}
		Utils.addIndiv(em, getIdnr(), Id_I_RP, "BC B1", "BIRTH_DATE", null, "Event", "Exact", getB1rpbd(),  getB1rpbm(), getB1rpby(), getB1rpbh(), getB1rpbi());
		if(getB1rppf() != null && getB1rppf().trim().length() > 0)
			Utils.addIndiv(em, getIdnr(), Id_I_RP, "BC B1", "PREFIX_LAST_NAME", getB1rppf(), "Event", "Exact", getB1rpbd(),  getB1rpbm(), getB1rpby());
		if(getB1rpfn() != null && getB1rpfn().trim().length() > 0)
			Utils.addIndiv(em, getIdnr(), Id_I_RP,"BC B1", "FIRST_NAME", getB1rpfn(), "Event", "Exact", getB1rpbd(),  getB1rpbm(), getB1rpby());
		if(getB1rpgn() != null && getB1rpgn().trim().length() > 0)
			Utils.addIndiv(em, getIdnr(), Id_I_RP, "BC B1", "SEX", Utils.sex(getB1rpgn()), "Event", "Exact", getB1rpbd(), getB1rpbm(), getB1rpby());
		Utils.addIndiv(em, getIdnr(), Id_I_RP, "BC B1", "HSN_RESEARCH_PERSON", "HSN RP", "Missing", "Time_invariant", 0, 0, 0);
		Utils.addIndiv(em, getIdnr(), Id_I_RP, "BC B1", "HSN_IDENTIFIER", "" + getIdnr(), "Missing", "Time_invariant", 0, 0, 0);



		String [] sampleCodes = {"", "Basic sample 1812-1872", "Basic sample 1873-1902", "Basic sample 1903-1922", 
				"Oversampling RDF" , "Oversampling 1903-1922" , "Oversampling OVF" , "Oversampling DUM"};

		if(getB0().getB0suhg() > 0)
			Utils.addIndiv(em, getIdnr(), Id_I_RP, "BC B1", "HSN_SAMPLE_STATUS", sampleCodes[getB0().getB0suhg()], "Missing", "Time_invariant", 0, 0, 0);


		ContextElement ceCertificate = null;
		if(getB1sdcl() != null){
			//System.out.println("b1sdcc = " + getB1sdcc());

				ceCertificate = Contxt.get2(getB1sdcl());  // Look up name in Context System
				if(ceCertificate == null)
					System.out.println("----> " + getB1sdcl() + " not found in context (= ref_location)");
			//System.out.println("b1sdcc = " + getB1sdcc() + ", certificate place =  " + ceCertificate.getName());
		}

		/*
		String certificateMunicipality = null;
		if(ceCertificate != null){
			 for(int i = 0; i < ceCertificate.getTypes().size(); i++){
				 if(ceCertificate.getTypes().get(i).equals("NAME")){
					 certificateMunicipality = ceCertificate.getValues().get(i);
					 break;
				 }
			 }
			 if(certificateMunicipality != null)
				 System.out.println("b1sdcc = " + getB1sdcc() + ", certificate place =  " + certificateMunicipality);
			 else
				 System.out.println("b1sdcc = " + getB1sdcc() + ", certificate place =  NULL");
		}
		else
			 System.out.println("b1sdcc = " + getB1sdcc() + ", certificate =  NULL");

	    */ 

		//		 Utils.addIndivContextAndContextCertificate(getB1sdcy(), getB1sdcn(), ceCertificate, em, getIdnr(), Id_I_RP, "B1ificate", "Birth", "Event", "Exact", getB1sdcd(),  getB1sdcm(), getB1sdcy());
		//	 Utils.addIndivAndContext(getB1rpll(), ceCertificate, em, getIdnr(), Id_I_RP, "BC B1", "BIRTH_LOCATION", "Event", "Exact", getB1rpbd(),  getB1rpbm(), getB1rpby());
		// }

		if(ceCertificate != null){
			
			//System.out.println("Adding Birth Certificate"); // XYZ
			
			Utils.addIndivContextAndContextCertificate(getB1sdcy(), getB1sdcn(), ceCertificate, em, getIdnr(), Id_I_RP, "Birth Certificate", "BC B1", "Child", "Event", "Exact", getB1sdcd(),  getB1sdcm(), getB1sdcy());
			
			if(getB1rplla() != null){

				int startDay1   = (new Integer(getB1rplla().getStartDate().substring(0,2))).intValue();
				int startMonth1 = (new Integer(getB1rplla().getStartDate().substring(3,5))).intValue();
				int startYear1  = (new Integer(getB1rplla().getStartDate().substring(6,10))).intValue();

				//addIndivAndContext(String quarter, String street, String number, String addition, ContextElement ce, EntityManager em, int IDNR, int Id_I, String source, String type, 
				//String dateType, String estimation, int day, int month, int year)
				
				Utils.addIndivAndContext(getB1rplla().getQuarter(), getB1rplla().getStreet(), getB1rplla().getNumber(), getB1rplla().getAddition(),
						ceCertificate, em, getIdnr(), Id_I_RP, "BC B1",  "BIRTH_LOCATION", "Event", "Exact",  
						startDay1, startMonth1, startYear1);
				
				Utils.addIndivContextAndContext(getB1rplla().getQuarter(), getB1rplla().getStreet(), getB1rplla().getNumber(), getB1rplla().getAddition(),
						ceCertificate, em, getIdnr(), Id_I_RP, "BC B1",  "Address", "Declared", "Exact",  
						startDay1, startMonth1, startYear1);

			}
				
			//Utils.addIndivAndContext(getB1rpll(), ceCertificate, em, getIdnr(), Id_I_RP, "BC B1", "BIRTH_LOCATION", "Event", "Exact", getB1rpbd(),  getB1rpbm(), getB1rpby());
		}


		// Informer

		int Id_I_IN = 0; // Utils.getId_I();
		if(getB1infa().equalsIgnoreCase("J"))
			Id_I_IN = 2;
		else
			Id_I_IN = 4;
				

		
		if(getB1inln() != null && getB1inln().trim().length() > 0 && !getB1inln().trim().equalsIgnoreCase("N")){
			Utils.addIndiv(em, getIdnr(), Id_I_IN, "BC B1",  "LAST_NAME", getB1inln(),"Missing", "Time_invariant", 0, 0, 0);
			if(getB1inpf() != null && getB1inpf().trim().length() > 0)
				Utils.addIndiv(em, getIdnr(), Id_I_IN, "BC B1",  "PREFIX_LAST_NAME", getB1inpf(),  "Missing", "Time_invariant",  0, 0, 0);
			if(getB1infn() != null && getB1infn().trim().length() > 0)
				Utils.addIndiv(em, getIdnr(), Id_I_IN, "BC B1", "FIRST_NAME", getB1infn(), "Missing", "Time_invariant",  0, 0, 0);
			if(getB1inay() > 0){
				Utils.addIndiv(em, getIdnr(), Id_I_IN, "BC B1", "AGE_YEARS", (new Integer(getB1inay())).toString(), "Declared", "Exact", getB1sdcd(),  getB1sdcm(), getB1sdcy());
				if(Utils.dateIsValid(getB1sdcd(),  getB1sdcm(), getB1sdcy()) == 0){
					int[] a = Utils.birthRange(getB1inay(), getB1sdcd(),  getB1sdcm(), getB1sdcy());
					Utils.addIndiv(em, getIdnr(), Id_I_IN, "BC B1", "BIRTH_DATE", null, "Assigned", "Age_based", a[0], a[1], a[2], a[3], a[4], a[5]);
				}
			}
			else
				Utils.addIndiv(em, getIdnr(), Id_I_IN, "BC B1", "BIRTH_DATE", null, "Assigned", "Estimated [16/100]", 1, 1, getB1sdcy() - 100, 1, 1,  getB1sdcy() - 16);

			if(getB1inoc() != null && getB1inoc().trim().length() > 0)
				Utils.addIndiv(em, getIdnr(), Id_I_IN, "BC B1", "OCCUPATION_STANDARD", getB1inoc() , "Declared", "Exact",  getB1sdcd(),  getB1sdcm(), getB1sdcy());
			if(getB1insg() != null && getB1insg().trim().length() > 0){
				Utils.addIndiv(em, getIdnr(), Id_I_IN, "BC B1", "SIGNATURE", Utils.signature(getB1insg()), "Event", "Exact", getB1sdcd(),  getB1sdcm(), getB1sdcy());
			}


			if(getB1infa().equalsIgnoreCase("J")){
				Utils.addIndiv(em, getIdnr(), Id_I_IN, "BC B1",  "SEX", "Male" ,"Missing", "Time_invariant", 0, 0, 0);
				Utils.addIndiv(em, getIdnr(), Id_I_RP, "BC B1",  "STATUS_FATHER", "Father is the informer", "Event", "Exact", getB1sdcd(),  getB1sdcm(), getB1sdcy());
			}
			else{
				Utils.addIndiv(em, getIdnr(), Id_I_IN, "BC B1",  "SEX", "Unknown" ,"Missing", "Time_invariant",  0, 0, 0);
				if(getB5() != null && getB5().getB5sdcf() != null && getB5().getB5sdcf().equalsIgnoreCase("j"))
					Utils.addIndiv(em, getIdnr(), Id_I_RP, "BC B1",  "STATUS_FATHER", "Father is not the informer", "Event", "Exact", getB1sdcd(),  getB1sdcm(), getB1sdcy());
				else
					Utils.addIndiv(em, getIdnr(), Id_I_RP, "BC B1",  "STATUS_FATHER", "Father is not known", "Event", "Exact", getB1sdcd(),  getB1sdcm(), getB1sdcy());

			}
			if(getB1infa().equalsIgnoreCase("J"))
				Utils.addIndiv(em, getIdnr(), Id_I_IN, "BC B1", "HSN_RESEARCH_PERSON", "Father RP", "Missing", "Time_invariant", 0, 0, 0);
			Utils.addIndiv(em, getIdnr(), Id_I_IN, "BC B1", "HSN_IDENTIFIER", "" + getIdnr(), "Missing", "Time_invariant", 0, 0, 0);


			String cs = "Unknown";
			if(getB1infa().equalsIgnoreCase("J")){
				if(getB1mocs() != null && getB1mocs().equals("5"))
					cs = "Married";
				else
					if(getB1mocs() != null && getB1mocs().equals("3"))
						cs = "Divorced";
			}

			Utils.addIndiv(em, getIdnr(), Id_I_IN, "BC B1", "CIVIL_STATUS", cs, "Declared", "Exact", getB1sdcd(),  getB1sdcm(), getB1sdcy());


			if(ceCertificate != null){    		 
				//Utils.addIndivContextAndContext(getB1inll(), ceCertificate, em, getIdnr(), Id_I_IN, "BC B1", "", "Event", "Exact", getB1sdcd(),  getB1sdcm(), getB1sdcy());
				
				if(getB1inlla() != null){
					
					int startDay1   = (new Integer(getB1inlla().getStartDate().substring(0,2))).intValue();
					int startMonth1 = (new Integer(getB1inlla().getStartDate().substring(3,5))).intValue();
					int startYear1  = (new Integer(getB1inlla().getStartDate().substring(6,10))).intValue();

					//addIndivAndContext(String quarter, String street, String number, String addition, ContextElement ce, EntityManager em, int IDNR, int Id_I, String source, String type, 
					//String dateType, String estimation, int day, int month, int year)
					
					//addIndivContextAndContext(String boat, String quarter, String street, String number, String addition, ContextElement ce, EntityManager em, int IDNR, int Id_I, String source, String relation, 
					//		String dateType, String estimation, int day, int month, int year)
					
					
					if(getB1infa().equalsIgnoreCase("J"))
						Utils.addIndivContextAndContext(getB1inlla().getQuarter(), getB1inlla().getStreet(), getB1inlla().getNumber(), getB1inlla().getAddition(),
								ceCertificate, em, getIdnr(), Id_I_IN, "BC B1",  "Address", "Declared", "Exact",  
								startDay1, startMonth1, startYear1);
					else
						Utils.addIndivAndContext(getB1inlla().getQuarter(), getB1inlla().getStreet(), getB1inlla().getNumber(), getB1inlla().getAddition(),
								ceCertificate, em, getIdnr(), Id_I_IN, "BC B1",  "RESIDENCE_LOCATION", "Declared", "Exact",  
								startDay1, startMonth1, startYear1);

				}

				
				
				
			}


			// Relations

			if(getB1infa().equalsIgnoreCase("J")){
				if(ceCertificate != null){
					Utils.addIndivContextAndContextCertificate(getB1sdcy(), getB1sdcn(), ceCertificate, em, getIdnr(), Id_I_IN, "Birth Certificate", "B1", "Father as Informer", "Event", "Exact", getB1sdcd(),  getB1sdcm(), getB1sdcy());
				}
				
				String relation = "Kind";
				if(getB1rpgn().equalsIgnoreCase("M")) relation = "Zoon";
				else
					if(getB1rpgn().equalsIgnoreCase("V")) relation = "Dochter";
				Utils.addIndivIndiv(em, getIdnr(), Id_I_RP, Id_I_IN, "BC B1", relation,  "Missing", "Time_invariant",0, 0,0 ); 
				Utils.addIndivIndiv(em, getIdnr(), Id_I_IN, Id_I_RP, "BC B1", "Vader",  "Missing", "Time_invariant",0, 0,0 ); 

			}
			else{
				if(ceCertificate != null)
					Utils.addIndivContextAndContextCertificate(getB1sdcy(), getB1sdcn(), ceCertificate, em, getIdnr(), Id_I_IN, "Birth Certificate", "BC B1", "Informer", "Event", "Exact", getB1sdcd(),  getB1sdcm(), getB1sdcy());
				Utils.addIndivIndiv(em, getIdnr(), Id_I_IN, Id_I_RP, "BC B1", "Onbekend",  "Event", "Exact", getB1sdcd(),  getB1sdcm(), getB1sdcy());// Informer and newly-born RP
				Utils.addIndivIndiv(em, getIdnr(), Id_I_RP, Id_I_IN, "BC B1", "Onbekend",  "Event", "Exact", getB1sdcd(),  getB1sdcm(), getB1sdcy()); // Informer and newly-born RP
			}

		}
		// Mother

		int Id_I_MO = 3; // Utils.getId_I();


		if(getB1moln() != null && getB1moln().trim().length() > 0 && !getB1moln().trim().equalsIgnoreCase("N")){
			Utils.addIndiv(em, getIdnr(), Id_I_MO, "BC B1", "LAST_NAME",getB1moln(), "Missing", "Time_invariant",0, 0,0 ); 
			if(getB1mopf() != null && getB1mopf().trim().length() > 0)
				Utils.addIndiv(em, getIdnr(), Id_I_MO, "BC B1", "PREFIX_LAST_NAME", getB1mopf(), "Missing", "Time_invariant",0, 0,0 ); 
			if(getB1mofn() != null && getB1mofn().trim().length() > 0)
				Utils.addIndiv(em, getIdnr(), Id_I_MO, "BC B1", "FIRST_NAME",  getB1mofn(), "Missing", "Time_invariant",0, 0,0 ); 
			Utils.addIndiv(em, getIdnr(), Id_I_MO, "BC B1", "SEX", "Female", "Missing", "Time_invariant", getB1sdcd(),  getB1sdcm(), getB1sdcy());
			if(getB1mooc() != null && getB1mooc().trim().length() > 0)
				Utils.addIndiv(em, getIdnr(), Id_I_MO, "BC B1", "OCCUPATION_STANDARD", getB1mooc(), "Declared", "Exact", getB1sdcd(),  getB1sdcm(), getB1sdcy());
			if(getB1mocs() != null && getB1mocs().trim().length() > 0)
				Utils.addIndiv(em, getIdnr(), Id_I_MO, "BC B1", "CIVIL_STATUS", Utils.civilStatus(getB1mocs()), "Declared", "Exact", getB1sdcd(),  getB1sdcm(), getB1sdcy());
			if(getB1moay() > 0){
				Utils.addIndiv(em, getIdnr(), Id_I_MO, "BC B1", "AGE_YEARS", (new Integer(getB1moay())).toString(), "Declared", "Exact", getB1sdcd(),  getB1sdcm(), getB1sdcy());
				if(Utils.dateIsValid(getB1sdcd(),  getB1sdcm(), getB1sdcy()) == 0){
					int[] a = Utils.birthRange(getB1moay(), getB1sdcd(),  getB1sdcm(), getB1sdcy());
					Utils.addIndiv(em, getIdnr(), Id_I_MO, "BC B1", "BIRTH_DATE", null, "Assigned", "Age_based", a[0], a[1], a[2], a[3], a[4], a[5]);
				}
			}
			else
				Utils.addIndiv(em, getIdnr(), Id_I_MO, "BC B1", "BIRTH_DATE", null, "Assigned", "Estimated [14/50]", 1, 1,  getB1sdcy() - 50, 1, 1,  getB1sdcy() - 14);
			

			Utils.addIndiv(em, getIdnr(), Id_I_MO, "BC B1", "HSN_RESEARCH_PERSON", "Mother RP", "Missing", "Time_invariant", 0, 0, 0);
			Utils.addIndiv(em, getIdnr(), Id_I_MO, "BC B1", "HSN_IDENTIFIER", "" + getIdnr(), "Missing", "Time_invariant", 0, 0, 0);


			
			if(ceCertificate != null){
				//Utils.addIndivContextAndContext(getB1moll(), ceCertificate, em, getIdnr(), Id_I_MO, "BC B1", "", "Event", "Exact", getB1sdcd(),  getB1sdcm(), getB1sdcy());
				
				if(getB1molla() != null){
					
					int startDay1   = (new Integer(getB1molla().getStartDate().substring(0,2))).intValue();
					int startMonth1 = (new Integer(getB1molla().getStartDate().substring(3,5))).intValue();
					int startYear1  = (new Integer(getB1molla().getStartDate().substring(6,10))).intValue();

					//addIndivAndContext(String quarter, String street, String number, String addition, ContextElement ce, EntityManager em, int IDNR, int Id_I, String source, String type, 
					//String dateType, String estimation, int day, int month, int year)
					
					//addIndivContextAndContext(String boat, String quarter, String street, String number, String addition, ContextElement ce, EntityManager em, int IDNR, int Id_I, String source, String relation, 
					//		String dateType, String estimation, int day, int month, int year)
					
					
					Utils.addIndivContextAndContext(getB1molla().getQuarter(), getB1molla().getStreet(), getB1molla().getNumber(), getB1molla().getAddition(),
							ceCertificate, em, getIdnr(), Id_I_MO, "BC B1",  "Address", "Declared", "Exact",  
							startDay1, startMonth1, startYear1);
				}
				
				Utils.addIndivContextAndContextCertificate(getB1sdcy(), getB1sdcn(), ceCertificate, em, getIdnr(), Id_I_MO, "Birth Certificate", "BC B1", "Mother", "Event", "Exact", getB1sdcd(),  getB1sdcm(), getB1sdcy());
			}


			// Mother relations

			Utils.addIndivIndiv(em, getIdnr(), Id_I_MO, Id_I_RP, "BC B1", "Moeder", "Missing", "Time_invariant", 0, 0,0 ); // Son and Mother
			String relation = "Kind";
			if(getB1rpgn().equalsIgnoreCase("M")) relation = "Zoon";
			else
				if(getB1rpgn().equalsIgnoreCase("V")) relation = "Dochter";

			Utils.addIndivIndiv(em, getIdnr(), Id_I_RP, Id_I_MO, "BC B1", relation, "Missing", "Time_invariant", 0, 0,0 ); // Son and Mother

			if(getB1infa().equalsIgnoreCase("J") && getB1mocs().equals("5")){
				Utils.addIndivIndiv(em, getIdnr(), Id_I_MO, Id_I_IN, "BC B1", "Echtgenote", "Declared", "Exact",getB1sdcd(),  getB1sdcm(), getB1sdcy()); 
				Utils.addIndivIndiv(em, getIdnr(), Id_I_IN, Id_I_MO, "BC B1", "Echtgenoot", "Declared", "Exact",getB1sdcd(),  getB1sdcm(), getB1sdcy()); 
			}
		} 
		
		//if(1==1) return;

		// Down the tree

		//if(getIdnr() == 41686) System.out.println("Start b2");
		
		for(B2 b2: getB2L())
			b2.convert(em);

		//if(getIdnr() == 41686) System.out.println("Start b4");

		for(B4 b4: getB4L())
			b4.convert(em);
		
		//if(getIdnr() == 41686) System.out.println("Start b5");


		if(getB1infa().equalsIgnoreCase("N") && getB5() != null)
			getB5().convert(em);

		//if(getIdnr() == 41686) System.out.println("Start m1");

		for(M1 m1: getM1L())
			m1.convert(em);

		//if(getIdnr() == 41686) System.out.println("Start d1");

		if(getD1() != null)
			getD1().convert(em);

		//System.out.println("Exit B1, idnr = "+ getIdnr());

	}



	public int getB1sdcc() {
		return b1sdcc;
	}
	public void setB1sdcc(int b1sdcc) {
		this.b1sdcc = b1sdcc;
	}
	public String getB1sdcl() {
		return b1sdcl;
	}
	public void setB1sdcl(String b1sdcl) {
		this.b1sdcl = b1sdcl;
	}
	public int getB1sdcy() {
		return b1sdcy;
	}
	public void setB1sdcy(int b1sdcy) {
		this.b1sdcy = b1sdcy;
	}
	public int getB1sdcn() {
		return b1sdcn;
	}
	public void setB1sdcn(int b1sdcn) {
		this.b1sdcn = b1sdcn;
	}
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public String getB1sdli() {
		return b1sdli;
	}
	public void setB1sdli(String b1sdli) {
		this.b1sdli = b1sdli;
	}
	public int getB1sdch() {
		return b1sdch;
	}
	public void setB1sdch(int b1sdch) {
		this.b1sdch = b1sdch;
	}
	public int getB1sdcd() {
		return b1sdcd;
	}
	public void setB1sdcd(int b1sdcd) {
		this.b1sdcd = b1sdcd;
	}
	public int getB1sdcm() {
		return b1sdcm;
	}
	public void setB1sdcm(int b1sdcm) {
		this.b1sdcm = b1sdcm;
	}
	public String getB1infa() {
		return b1infa;
	}
	public void setB1infa(String b1infa) {
		this.b1infa = b1infa;
	}
	public String getB1inln() {
		return b1inln;
	}
	public void setB1inln(String b1inln) {
		this.b1inln = b1inln;
	}
	public String getB1inpf() {
		return b1inpf;
	}
	public void setB1inpf(String b1inpf) {
		this.b1inpf = b1inpf;
	}
	public String getB1infn() {
		return b1infn;
	}
	public void setB1infn(String b1infn) {
		this.b1infn = b1infn;
	}
	public String getB1intt() {
		return b1intt;
	}
	public void setB1intt(String b1intt) {
		this.b1intt = b1intt;
	}
	public String getB1inpa() {
		return b1inpa;
	}
	public void setB1inpa(String b1inpa) {
		this.b1inpa = b1inpa;
	}
	public int getB1inay() {
		return b1inay;
	}
	public void setB1inay(int b1inay) {
		this.b1inay = b1inay;
	}
	public String getB1inoc() {
		return b1inoc;
	}
	public void setB1inoc(String b1inoc) {
		this.b1inoc = b1inoc;
	}
	public String getB1inll() {
		return b1inll;
	}
	public void setB1inll(String b1inll) {
		this.b1inll = b1inll;
	}
	public String getB1insg() {
		return b1insg;
	}
	public void setB1insg(String b1insg) {
		this.b1insg = b1insg;
	}
	public int getB1rpbd() {
		return b1rpbd;
	}
	public void setB1rpbd(int b1rpbd) {
		this.b1rpbd = b1rpbd;
	}
	public int getB1rpbm() {
		return b1rpbm;
	}
	public void setB1rpbm(int b1rpbm) {
		this.b1rpbm = b1rpbm;
	}
	public int getB1rpby() {
		return b1rpby;
	}
	public void setB1rpby(int b1rpby) {
		this.b1rpby = b1rpby;
	}
	public int getB1rpbh() {
		return b1rpbh;
	}
	public void setB1rpbh(int b1rpbh) {
		this.b1rpbh = b1rpbh;
	}
	public int getB1rpbi() {
		return b1rpbi;
	}
	public void setB1rpbi(int b1rpbi) {
		this.b1rpbi = b1rpbi;
	}
	public String getB1rpgn() {
		return b1rpgn;
	}
	public void setB1rpgn(String b1rpgn) {
		this.b1rpgn = b1rpgn;
	}
	public String getB1rpll() {
		return b1rpll;
	}
	public void setB1rpll(String b1rpll) {
		this.b1rpll = b1rpll;
	}
	public String getB1moln() {
		return b1moln;
	}
	public void setB1moln(String b1moln) {
		this.b1moln = b1moln;
	}
	public String getB1mopf() {
		return b1mopf;
	}
	public void setB1mopf(String b1mopf) {
		this.b1mopf = b1mopf;
	}
	public String getB1mofn() {
		return b1mofn;
	}
	public void setB1mofn(String b1mofn) {
		this.b1mofn = b1mofn;
	}
	public String getB1mott() {
		return b1mott;
	}
	public void setB1mott(String b1mott) {
		this.b1mott = b1mott;
	}
	public String getB1mopa() {
		return b1mopa;
	}
	public void setB1mopa(String b1mopa) {
		this.b1mopa = b1mopa;
	}
	public int getB1moay() {
		return b1moay;
	}
	public void setB1moay(int b1moay) {
		this.b1moay = b1moay;
	}
	public String getB1mocs() {
		return b1mocs;
	}
	public void setB1mocs(String b1mocs) {
		this.b1mocs = b1mocs;
	}
	public String getB1mooc() {
		return b1mooc;
	}
	public void setB1mooc(String b1mooc) {
		this.b1mooc = b1mooc;
	}
	public String getB1moll() {
		return b1moll;
	}
	public void setB1moll(String b1moll) {
		this.b1moll = b1moll;
	}
	public String getB1rpln() {
		return b1rpln;
	}
	public void setB1rpln(String b1rpln) {
		this.b1rpln = b1rpln;
	}
	public String getB1rppf() {
		return b1rppf;
	}
	public void setB1rppf(String b1rppf) {
		this.b1rppf = b1rppf;
	}
	public String getB1rpfn() {
		return b1rpfn;
	}
	public void setB1rpfn(String b1rpfn) {
		this.b1rpfn = b1rpfn;
	}
	public String getB1rptt() {
		return b1rptt;
	}
	public void setB1rptt(String b1rptt) {
		this.b1rptt = b1rptt;
	}
	public String getB1rppa() {
		return b1rppa;
	}
	public void setB1rppa(String b1rppa) {
		this.b1rppa = b1rppa;
	}
	public String getD_e_p_l() {
		return d_e_p_l;
	}
	public void setD_e_p_l(String d_e_p_l) {
		this.d_e_p_l = d_e_p_l;
	}
	public String getD_e_p_o() {
		return d_e_p_o;
	}
	public void setD_e_p_o(String d_e_p_o) {
		this.d_e_p_o = d_e_p_o;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}
	public B0 getB0() {
		return b0;
	}
	public void setB0(B0 b0) {
		this.b0 = b0;
	}
	public ArrayList<B2> getB2L() {
		return b2L;
	}
	public void setB2L(ArrayList<B2> b2l) {
		b2L = b2l;
	}
	public B3 getB3() {
		return b3;
	}
	public void setB3(B3 b3) {
		this.b3 = b3;
	}
	public ArrayList<B4> getB4L() {
		return b4L;
	}
	public void setB4L(ArrayList<B4> b4l) {
		b4L = b4l;
	}
	public B5 getB5() {
		return b5;
	}
	public void setB5(B5 b5) {
		this.b5 = b5;
	}
	public ArrayList<M1> getM1L() {
		return m1L;
	}
	public void setM1L(ArrayList<M1> m1l) {
		m1L = m1l;
	}
	public D1 getD1() {
		return d1;
	}
	public void setD1(D1 d1) {
		this.d1 = d1;
	}



	public A1 getB1rplla() {
		return b1rplla;
	}



	public void setB1rplla(A1 b1rplla) {
		this.b1rplla = b1rplla;
	}



	public A1 getB1molla() {
		return b1molla;
	}



	public void setB1molla(A1 b1molla) {
		this.b1molla = b1molla;
	}



	public A1 getB1inlla() {
		return b1inlla;
	}



	public void setB1inlla(A1 b1inlla) {
		this.b1inlla = b1inlla;
	}





}



