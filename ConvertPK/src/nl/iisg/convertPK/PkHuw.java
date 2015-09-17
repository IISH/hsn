package nl.iisg.convertPK;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import nl.iisg.hsncommon.Common1;

@Entity
@Table(name="pkhuw")
public class PkHuw {
	
	 @Id @Column(name="IDNR")       private  int      idnr; 			// ID Number
	 @Column(name="VNRHUWP")        private  int	  vnrhuwp;			// sequence number spouse  
	 
	 @Column(name="ANMHUWP")        private  String	  anmhuwp;        	// last name spouse
	 @Column(name="TUSHUWP")        private  String   tushuwp;       	// prefix spouse
	 @Column(name="VN1HUWP")        private  String   vn1huwp;       	// 1st firstname spouse
	 @Column(name="VN2HUWP")        private  String   vn2huwp;       	// 2nd firstname spouse 
	 @Column(name="VN3HUWP")        private  String   vn3huwp;       	// 3rd firstname spouse
	 
	 @Column(name="BRPHUWP")        private  String   brphuwp;       	// profession spouse
	 
	 @Column(name="GDGHUWP")        private  int      gdghuwp;			// day of birth spouse    
	 @Column(name="GMDHUWP")        private  int	  gmdhuwp;        	// month of birth spouse
	 @Column(name="GJRHUWP")        private  int      gjrhuwp;        	// year of birth spouse 
	 @Column(name="GDGHUWPCR")      private  int      gdghuwpcr;		// day of birth spouse after correction    
	 @Column(name="GMDHUWPCR")      private  int	  gmdhuwpcr;        // month of birth spouse after correction   
	 @Column(name="GJRHUWPCR")      private  int      gjrhuwpcr;        // year of birth spouse after correction
	 
	 @Column(name="GPLHUWP")        private  String   gplhuwp;          // birth place spouse
	 
	 @Column(name="HDGHUWP")        private  int	  hdghuwp;			// day of marriage spouse
	 @Column(name="HMDHUWP")        private  int      hmdhuwp;			// month of marriage spouse
	 @Column(name="HJRHUWP")        private  int      hjrhuwp;      	// year of marriage spouse
	 @Column(name="HPLHUWP")        private  String   hplhuwp;			// place of marriage
	 
	 
	 @Column(name="ODGHUWP")        private  int	  odghuwp;			// day of termination marriage
	 @Column(name="OMDHUWP")        private  int      omdhuwp;			// month of termination marriage
	 @Column(name="OJRHUWP")        private  int      ojrhuwp;          // year of termination marriage
	 
	 @Column(name="ORDHUWP")        private  int      ordhuwp;          // reason termination marriage (Date of mutation: Death/divorce/other

	 	                                                                    
	 /*
	  * 0	To be interpreted as ‘1’ if ojrhuwp is valid ; for the problem see description data herefore
		1	Death of the spouse: PK_holder becomes widow -> record for PK_holder with code 2 on [b3kode]
		2	Divorce  									 -> record for PK-holder and for spouse with code  3 on [b3kode]
		3	Other reason							     -> record for PK-holder and for spouse with code 12 on [b3kode]
		4	Bigamy                                       -> to be handled as divorce; code 3 on [b3kode]
		9	Reason not made explicit in source           -> to be handled as divorce; code 12 on [b3kode]

	  */
	 
	 
	 @Column(name="OPLHUWP")        private  String   oplhuwp;          // place decease spouse
	 
	 @Column(name="ADGHUWP")        private  int	  adghuwp;		    // day of departure spouse 
	 @Column(name="AMDHUWP")        private  int      amdhuwp;	    	// month of departure spouse 
	 @Column(name="AJRHUWP")        private  int      ajrhuwp;          // year of departure spouse	 
	 @Column(name="APLHUWP")        private  String   aplhuwp;          // destination departing spouse
	 
	 @Column(name="SRTHUWP")        private  String   srthuwp;          // kind of marriage (geregistreerd partnerschap etc.)
	 
	 @Column(name="DDGHUWP")        private  int      ddghuwp;          // registration day spouse	 
	 @Column(name="DMDHUWP")        private  int      dmdhuwp;		    // registration month spouse 
	 @Column(name="DJRHUWP")        private  int      djrhuwp;          // registration year spouse
	 
	 @Column(name="OPDGHUWP")       private  int      opdghuwp;		    // day GBA entry 
	 @Column(name="OPMDHUWP")       private  int      opmdhuwp;         // month GBA entry
	 @Column(name="OPJRHUWP")       private  int      opjrhuwp;         // year GBA entry
	 
	 @Column(name="OPDRNR")         private  String   opdrnr;           // order number
	 @Column(name="DATUM")          private  String   datum;            // date
	 @Column(name="INIT")           private  String   init;             // initials 
	 @Column(name="VERSIE")         private  String   versie;           // version
	 @Column(name="ONDRZKO")        private  String   onderzko;         // original research
	 @Column(name="OPDRNRO")        private  String   opdrnro;          // original order number  
	 @Column(name="DATUMO")         private  String   datumo;           // original date   
	 @Column(name="INITO")          private  String   inito;            // original initials    
	 @Column(name="VERSIEO")        private  String   versieo;          // original version
	 
     @Transient                     private  PkKnd    pkHolder;         // PK-Holder

	 
	 
	// No-arguments constructor is necessary
	    
    PkHuw(){
	    	
    }
	 
    public void convert(B2_ST b2){
    	
    	
    	// Last name may still contain prefix, remove it
    	
    	String lastName = getAnmhuwp().trim();
    	
    	//System.out.println("---> lastName = " + lastName);
    	
    	if(lastName != null){
			if(lastName.split("%").length > 1){
				lastName = lastName.split("%")[0].trim();
				b2.setFamilyNameInterpreted(2);  	
			}
			else
				b2.setFamilyNameInterpreted(1);	
    	}
    	lastName = lastName.trim();
    	String prefix = null;
    	int i = lastName.indexOf(",");
    	if(i >= 0){    		
       		prefix = lastName.substring(i+1).trim();
       		lastName = lastName.substring(0,i).trim();    		
    		
    	}
    	b2.setFamilyName(Utils.standardizeFamilyName(lastName));
    	
    	// first name
    	
    	b2.setFirstNameFlag(1);
    	String firstName ="";
    	
    	String firstName1 = getVn1huwp();
    	
    	if(firstName1 != null && firstName1.trim().length() > 0){
			if(firstName1.split("%").length > 1){
				firstName1 = firstName1.split("%")[0].trim();
		    	b2.setFirstNameFlag(2);
			}

			firstName += Utils.standardizeFirstName(firstName1);
			firstName += " ";
    	
    	}    	

    	String firstName2 = getVn2huwp();

    	if(firstName2 != null && firstName2.trim().length() > 0){
			if(firstName2.split("%").length > 1){
				firstName2 = firstName2.split("%")[0].trim();
				b2.setFirstNameFlag(2);  	
			}

			firstName += Utils.standardizeFirstName(firstName2);
			firstName += " ";
    	}    	

    	String firstName3 = getVn3huwp();
    	
    	if(firstName3 != null && firstName3.trim().length() > 0){
			if(firstName3.split("%").length > 1){
				firstName3 = firstName3.split("%")[0].trim();
				b2.setFirstNameFlag(2);  	
			}
			
			firstName += Utils.standardizeFirstName(firstName3);
    	}    	
    	
    	b2.setFirstName(firstName.trim());

    	// Prefix
    	
    	//System.out.println("---> prefix = " + prefix + " or " + Utils.standardizePrefix(prefix));

    	
    	if(getTushuwp() == null || getTushuwp().trim().length() == 0){
    		if(prefix != null)    	
    	    	b2.setPrefixLastName(Utils.standardizePrefix(prefix));
    		else;
    	}
    	else
        	b2.setPrefixLastName(Utils.standardizePrefix(getTushuwp()));
    	
    	// sex - indirect via partner
    	
    	if(getPkHolder().getGslperp().equalsIgnoreCase("M"))
    		b2.setSex("v");
    	else
    		b2.setSex("m");

    	String birthDate = String.format("%02d-%02d-%04d", getGdghuwp(), getGmdhuwp(), getGjrhuwp());
    	b2.setDateOfBirth(birthDate); 
    	
    	    	
    	// Birth date
    	
		int[] result = 	Utils.transformDateFields(getGdghuwp(), getGmdhuwp(), getGjrhuwp(), getGdghuwpcr(), getGmdhuwpcr(), getGjrhuwpcr()); 

		b2.setDateOfBirth(String.format("%02d-%02d-%04d", result[0], result[1], result[2]));
		b2.setDateOfBirthFlag(result[3]);
		
		// Birth Place

		String birthPlace = getGplhuwp();
		
    	if(birthPlace != null){
			if(birthPlace.split("%").length > 1){
				birthPlace = birthPlace.split("%")[0].trim();
				b2.setPlaceOfBirthFlag(2);  	
			}
			else
				b2.setPlaceOfBirthFlag(1);  	
    	}

    	ArrayList a = Utils.standardizeLocation(birthPlace);
    	b2.setPlaceOfBirthStandardized((String)a.get(0));
    	b2.setPlaceOfBirthID((Integer)a.get(1));
    	
    	String deceaseDate = null;

    	if((getOrdhuwp() == 0 || getOrdhuwp() == 1) && getOjrhuwp() > 0)
    		deceaseDate = String.format("%02d-%02d-%04d", getOdghuwp(), getOmdhuwp(), getOjrhuwp());
    	else
    		deceaseDate = null;
    	b2.setDateOfDecease(deceaseDate); 
    	b2.setDateOfDeceaseFlag(1);
    	
    	//b2.setEndDate(deceaseDate);
    	
    	// Decease Place

		String deceasePlace = getOplhuwp();
		
    	if(deceasePlace != null){
			if(deceasePlace.split("%").length > 1){
				deceasePlace = deceasePlace.split("%")[0].trim();
				b2.setPlaceOfDeceaseFlag(2);  	
			}
			else
				b2.setPlaceOfDeceaseFlag(1);  	
    	}
    	
    	a = Utils.standardizeLocation(deceasePlace);
    	b2.setPlaceOfDeceaseStandardized((String)a.get(0));
    	b2.setPlaceOfDeceaseID((Integer)a.get(1)); 
    	
    	// Registration date - not needed
    	
    	//String registrationDate = String.format("%02d-%02d-%04d", getDdghuwp(), getDmdhuwp(), getDjrhuwp());
    	//b2.setDateOfRegistration(registrationDate);    	
    	
    	// dating
    	
    	if(getHjrhuwp() > 0 && Common1.dayCount(getHdghuwp(), getHmdhuwp(), getHjrhuwp()) > Common1.dayCount(b2.getRegistration().getStartDate())){
    		b2.setStartDate(String.format("%02d-%02d-%04d", getHdghuwp(), getHmdhuwp(), getHjrhuwp()));
    		b2.setStartFlag(21);
    	}

    	if(getOjrhuwp() > 0){
    		if(Common1.dayCount(getOdghuwp(), getOmdhuwp(), getOjrhuwp()) < Common1.dayCount(b2.getEndDate())){
    			b2.setEndDate(String.format("%02d-%02d-%04d", getOdghuwp(), getOmdhuwp(), getOjrhuwp()));
    			b2.setEndFlag(40);
    		}
    	}
    	
    	if(getAjrhuwp() > 0){
    		if(Common1.dayCount(getAdghuwp(), getAmdhuwp(), getAjrhuwp()) < Common1.dayCount(b2.getEndDate())){
    			b2.setEndDate(String.format("%02d-%02d-%04d", getAdghuwp(), getAmdhuwp(), getAjrhuwp()));
    			b2.setEndFlag(41);
    		}
    	}

    	if(b2.getEndDate() != null &&  Common1.dayCount(b2.getEndDate()) < Common1.dayCount(b2.getStartDate())){
    		
    		b2.setStartDate(null);
    		b2.setStartFlag(0);
			b2.setEndDate(null);
			b2.setEndFlag(0);
    		
    	}



    	
    	
    	// Relation to PK-Holder of partner
    	
		B313_ST b313 = new B313_ST();
		b2.getRelationsToPKHolder().add(b313);    // Link B313_ST -> B2_ST
		b313.setPerson(b2);            			  // Link B2_ST -> B313_ST

		initialiseB3_ST(b313);
		
		
		
		
		b313.setDynamicDataType(13);
		b313.setKeyToRegistrationPersons(b2.getKeyToPersons());
		
		if(b2.getSex().equalsIgnoreCase("M"))		
			b313.setContentOfDynamicData(145);   // Spouse male
		else
			b313.setContentOfDynamicData(2);     // Spouse female

		b313.setDynamicDataSequenceNumber(1);

    	if(getHjrhuwp() != 0){
        	String marriageDate = String.format("%02d-%02d-%04d", getHdghuwp(), getHmdhuwp(), getHjrhuwp());
        	b313.setDateOfMutation(marriageDate);        	
        	b313.setDateOfMutationFlag(10); // original value
        	b313.setStartDate(marriageDate);
    	}

    	
    	// Marriage Civil Status    	
    	
    	
		B32_ST b32 = new B32_ST();
    	b2.getCivilStatus().add(b32); // Link B32_ST -> B2_ST
    	b32.setPerson(b2);            // Link B2_ST -> B32_ST

		initialiseB3_ST(b32);
		b32.setKeyToRegistrationPersons(b2.getKeyToPersons());
		
		b32.setDynamicDataType(2);
		b32.setContentOfDynamicData(5);    // 5 = married
		
    	ArrayList b = Utils.standardizeLocation(getHplhuwp());
    	b32.setCivilLocalityStandardized((String)b.get(0));
    	b32.setCivilLocalityID((Integer)b.get(1));
    	
    	if(getHjrhuwp() != 0){
        	String marriageDate = String.format("%02d-%02d-%04d", getHdghuwp(), getHmdhuwp(), getHjrhuwp());
        	b32.setDateOfMutation(marriageDate);
           	b32.setDateOfMutationFlag(10); // original value 
           	b32.setStartDate(marriageDate);
    		
    	}
    	b32.setValueOfRelatedPerson(1);  // set row number spouse = Pk-Holder = First Row 

    	int seqCivil = 1;
		b32.setDynamicDataSequenceNumber(seqCivil++);
		
    	
    	// departure of partner
    	
    	if(getAjrhuwp() > 0 && getAplhuwp() != null && getAplhuwp().trim().length() > 0 && !getAplhuwp().trim().equals("-1")){
    		
    		//System.out.println("Partner leaving ");
    		
    		B37_ST b37 = new B37_ST();
        	b2.getDestinations().add(b37); // Link B37_ST -> B2_ST
        	b37.setPerson(b2);             // Link B2_ST -> B37_ST

    		initialiseB3_ST(b37);
    		b37.setDynamicDataType(7);
    		b37.setKeyToRegistrationPersons(b2.getKeyToPersons());

        	b = Utils.standardizeLocation(getAplhuwp());
        	b37.setDestinationStandardized((String)b.get(0));
        	b37.setDestinationID((Integer)b.get(1));
        	
        	
            String departureDate = String.format("%02d-%02d-%04d", getAdghuwp(), getAmdhuwp(), getAjrhuwp());
            b37.setDateOfMutation(departureDate);
        		
        	

    		
    		b37.setDynamicDataSequenceNumber(1);
    		
    		/*
    		
    		// new civil status record for spouse with code = 11
    		
    		b32 = new B32_ST();
        	b2.getCivilStatus().add(b32); // Link B32_ST -> B2_ST
        	b32.setPerson(b2);            // Link B2_ST -> B32_ST

    		initialiseB3_ST(b32);
    		b32.setKeyToRegistrationPersons(b2.getKeyToPersons());
    		b32.setDynamicDataType(2);
    		b32.setContentOfDynamicData(11);  
    		b32.setDynamicDataSequenceNumber(seqCivil++);
    		
        	
            b32.setDateOfMutation(departureDate);
            b32.setStartDate(departureDate);
        		
        	*/
    	}

    	
		// Professions 
    	
    	if(getBrphuwp() != null){

    		int increment = 0;
    		if(b2.getStartDate() != null){
    			int date = Common1.dayCount(b2.getStartDate());
    			String[] professions = getBrphuwp().trim().split("[*]");
    			if(b2.getEndDate() != null)
    				increment = (Common1.dayCount(b2.getEndDate()) - Common1.dayCount(b2.getStartDate())) / professions.length;

    			if(increment > 0){ // because sometimes endDate is invalid


    				int seqNoPr = 1;

    				for(String profession: professions){

    					if(profession == null || profession.trim().length() == 0)
    						continue;

    					profession = profession.trim();
    					B35_ST b35 = new B35_ST();
    					b2.getProfessions().add(b35); // Link B35_ST -> B2_ST
    					b35.setPerson(b2);            // Link B2_ST -> B35_ST

    					initialiseB3_ST(b35);
    					b35.setKeyToRegistrationPersons(b2.getKeyToPersons());
    					//System.out.println("key = " + b2.getKeyToPersons());

    					b35.setDynamicDataType(5);

    					b = Utils.standardizeProfession(profession);
    					b35.setOccupationStandardized((String)b.get(0));
    					b35.setOccupationID((Integer)b.get(1));

    					b35.setStartDate(Common1.dateFromDayCount(date));
    					b35.setEndDate(Common1.dateFromDayCount(date + increment - 1));

    					date += increment;


    					b35.setOccupationFlag(3);


    					b35.setDynamicDataSequenceNumber(seqNoPr++);



    				}
    			}
    		}
    	}
    }
    
    
   	public void print(){
		
  		System.out.println("    In PkHuw, idnr = " + getIdnr());
   	}

	    
    private void initialiseB3_ST(B3_ST b3){
    	
    	b3.setKeyToRP(getIdnr());
    	b3.setEntryDateHead((b3.getPerson().getEntryDateHead()));
    	b3.setKeyToSourceRegister(getPkHolder().getPktype());
    	
    	b3.setStartDate(b3.getPerson().getStartDate());
    	b3.setStartFlag(b3.getPerson().getStartFlag());
    	b3.setEndDate(b3.getPerson().getEndDate());
    	b3.setEndFlag(b3.getPerson().getEndFlag());

    	
    	b3.setVersionLastTimeOfDataEntry(getVersie());
    	b3.setResearchCodeOriginal(getOnderzko());
    	b3.setVersionOriginalDataEntry(getVersieo());
    	b3.setDate0(getDatum());
    	
    }
    
	    
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}
	public int getVnrhuwp() {
		return vnrhuwp;
	}
	public void setVnrhuwp(int vnrhuwp) {
		this.vnrhuwp = vnrhuwp;
	}
	public String getAnmhuwp() {
		return anmhuwp;
	}
	public void setAnmhuwp(String anmhuwp) {
		this.anmhuwp = anmhuwp;
	}
	public String getTushuwp() {
		return tushuwp;
	}
	public void setTushuwp(String tushuwp) {
		this.tushuwp = tushuwp;
	}
	public String getVn1huwp() {
		return vn1huwp;
	}
	public void setVn1huwp(String vn1huwp) {
		this.vn1huwp = vn1huwp;
	}
	public String getVn2huwp() {
		return vn2huwp;
	}
	public void setVn2huwp(String vn2huwp) {
		this.vn2huwp = vn2huwp;
	}
	public String getVn3huwp() {
		return vn3huwp;
	}
	public void setVn3huwp(String vn3huwp) {
		this.vn3huwp = vn3huwp;
	}
	public String getBrphuwp() {
		return brphuwp;
	}
	public void setBrphuwp(String brphuwp) {
		this.brphuwp = brphuwp;
	}
	public int getGdghuwp() {
		return gdghuwp;
	}
	public void setGdghuwp(int gdghuwp) {
		this.gdghuwp = gdghuwp;
	}
	public int getGmdhuwp() {
		return gmdhuwp;
	}
	public void setGmdhuwp(int gmdhuwp) {
		this.gmdhuwp = gmdhuwp;
	}
	public int getGjrhuwp() {
		return gjrhuwp;
	}
	public void setGjrhuwp(int gjrhuwp) {
		this.gjrhuwp = gjrhuwp;
	}
	public int getGdghuwpcr() {
		return gdghuwpcr;
	}
	public void setGdghuwpcr(int gdghuwpcr) {
		this.gdghuwpcr = gdghuwpcr;
	}
	public int getGmdhuwpcr() {
		return gmdhuwpcr;
	}
	public void setGmdhuwpcr(int gmdhuwpcr) {
		this.gmdhuwpcr = gmdhuwpcr;
	}
	public int getGjrhuwpcr() {
		return gjrhuwpcr;
	}
	public void setGjrhuwpcr(int gjrhuwpcr) {
		this.gjrhuwpcr = gjrhuwpcr;
	}
	public String getGplhuwp() {
		return gplhuwp;
	}
	public void setGplhuwp(String gplhuwp) {
		this.gplhuwp = gplhuwp;
	}
	public int getHdghuwp() {
		return hdghuwp;
	}
	public void setHdghuwp(int hdghuwp) {
		this.hdghuwp = hdghuwp;
	}
	public int getHmdhuwp() {
		return hmdhuwp;
	}
	public void setHmdhuwp(int hmdhuwp) {
		this.hmdhuwp = hmdhuwp;
	}
	public int getHjrhuwp() {
		return hjrhuwp;
	}
	public void setHjrhuwp(int hjrhuwp) {
		this.hjrhuwp = hjrhuwp;
	}
	public String getHplhuwp() {
		return hplhuwp;
	}
	public void setHplhuwp(String hplhuwp) {
		this.hplhuwp = hplhuwp;
	}
	public int getOdghuwp() {
		return odghuwp;
	}
	public void setOdghuwp(int odghuwp) {
		this.odghuwp = odghuwp;
	}
	public int getOmdhuwp() {
		return omdhuwp;
	}
	public void setOmdhuwp(int omdhuwp) {
		this.omdhuwp = omdhuwp;
	}
	public int getOjrhuwp() {
		return ojrhuwp;
	}
	public void setOjrhuwp(int ojrhuwp) {
		this.ojrhuwp = ojrhuwp;
	}
	public int getOrdhuwp() {
		return ordhuwp;
	}
	public void setOrdhuwp(int ordhuwp) {
		this.ordhuwp = ordhuwp;
	}
	public String getOplhuwp() {
		return oplhuwp;
	}
	public void setOplhuwp(String oplhuwp) {
		this.oplhuwp = oplhuwp;
	}
	public int getAdghuwp() {
		return adghuwp;
	}
	public void setAdghuwp(int adghuwp) {
		this.adghuwp = adghuwp;
	}
	public int getAmdhuwp() {
		return amdhuwp;
	}
	public void setAmdhuwp(int amdhuwp) {
		this.amdhuwp = amdhuwp;
	}
	public int getAjrhuwp() {
		return ajrhuwp;
	}
	public void setAjrhuwp(int ajrhuwp) {
		this.ajrhuwp = ajrhuwp;
	}
	public String getAplhuwp() {
		return aplhuwp;
	}
	public void setAplhuwp(String aplhuwp) {
		this.aplhuwp = aplhuwp;
	}
	public String getSrthuwp() {
		return srthuwp;
	}
	public void setSrthuwp(String srthuwp) {
		this.srthuwp = srthuwp;
	}
	public int getDdghuwp() {
		return ddghuwp;
	}
	public void setDdghuwp(int ddghuwp) {
		this.ddghuwp = ddghuwp;
	}
	public int getDmdhuwp() {
		return dmdhuwp;
	}
	public void setDmdhuwp(int dmdhuwp) {
		this.dmdhuwp = dmdhuwp;
	}
	public int getDjrhuwp() {
		return djrhuwp;
	}
	public void setDjrhuwp(int djrhuwp) {
		this.djrhuwp = djrhuwp;
	}
	public int getOpdghuwp() {
		return opdghuwp;
	}
	public void setOpdghuwp(int opdghuwp) {
		this.opdghuwp = opdghuwp;
	}
	public int getOpmdhuwp() {
		return opmdhuwp;
	}
	public void setOpmdhuwp(int opmdhuwp) {
		this.opmdhuwp = opmdhuwp;
	}
	public int getOpjrhuwp() {
		return opjrhuwp;
	}
	public void setOpjrhuwp(int opjrhuwp) {
		this.opjrhuwp = opjrhuwp;
	}
	public String getOpdrnr() {
		return opdrnr;
	}
	public void setOpdrnr(String opdrnr) {
		this.opdrnr = opdrnr;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getInit() {
		return init;
	}
	public void setInit(String init) {
		this.init = init;
	}
	public String getVersie() {
		return versie;
	}
	public void setVersie(String versie) {
		this.versie = versie;
	}
	public String getOnderzko() {
		return onderzko;
	}
	public void setOnderzko(String onderzko) {
		this.onderzko = onderzko;
	}
	public String getOpdrnro() {
		return opdrnro;
	}
	public void setOpdrnro(String opdrnro) {
		this.opdrnro = opdrnro;
	}
	public String getDatumo() {
		return datumo;
	}
	public void setDatumo(String datumo) {
		this.datumo = datumo;
	}
	public String getInito() {
		return inito;
	}
	public void setInito(String inito) {
		this.inito = inito;
	}
	public String getVersieo() {
		return versieo;
	}
	public void setVersieo(String versieo) {
		this.versieo = versieo;
	}

	public PkKnd getPkHolder() {
		return pkHolder;
	}

	public void setPkHolder(PkKnd pkHolder) {
		this.pkHolder = pkHolder;
	}
	
	
	
	

}
