package nl.iisg.convertPK;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


import nl.iisg.hsncommon.Common1;


@Entity
@Table(name="pkeigknd")
public class PkEigknd {
	@Id  @Column(name="IDNR")       private int       idnr; 		    // ID Number
	 @Column(name="VGNRKDP")        private  int	  vgnrkdp;			// sequence number child  
	 @Column(name="ANMKNDP")        private  String	  anmkndp;        	// last name child
	 @Column(name="TUSKNDP")        private  String   tuskndp;       	// prefix child
	 @Column(name="VN1KNDP")        private  String   vn1kndp;       	// 1st firstname child
	 @Column(name="VN2KNDP")        private  String   vn2kndp;       	// 2nd firstname child 
	 @Column(name="VN3KNDP")        private  String   vn3kndp;       	// 3rd firstname child
	 @Column(name="GDGKNDP")        private  int      gdgkndp;			// day of birth child    
	 @Column(name="GMDKNDP")        private  int	  gmdkndp;        	// month of birth child
	 @Column(name="GJRKNDP")        private  int      gjrkndp;        	// year of birth child 
	 @Column(name="GDGKNDPCR")      private  int      gdgkndpcr;		// day of birth child after correction    
	 @Column(name="GMDKNDPCR")      private  int	  gmdkndpcr;        // month of birth child after correction   
	 @Column(name="GJRKNDPCR")      private  int      gjrkndpcr;        // year of birth child after correction     
	 @Column(name="GPLKNDP")        private  String   gplkndp;          // birth place child
	 @Column(name="GLNKNDP")        private  String   glnkndp;          // birth country child
	 @Column(name="RELKNDP")        private  String   relkndp;          // relationship to PK-Holder (son, daughter etc)
	 @Column(name="HDGKNDP")        private  int	  hdgkndp;			// day of marriage child
	 @Column(name="HMDKNDP")        private  int      hmdkndp;			// month of marriage child
	 @Column(name="HJRKNDP")        private  int      hjrkndp;      	// year of marriage child
	 @Column(name="HPLKNDP")        private  String   hplkndp;			// place of marriage
	 @Column(name="VNMPTNP")        private  String   vnmptnp;			// first name partner child
	 @Column(name="TUSPTNP")        private  String   tusptnp;			// prefix partner child
	 @Column(name="ANMPTNP")        private  String   anmptnp;			// last name partner child
	 @Column(name="ODGKNDP")        private  int	  odgkndp;			// day of decease child
	 @Column(name="OMDKNDP")        private  int      omdkndp;			// month of decease child
	 @Column(name="OJRKNDP")        private  int      ojrkndp;          // year of decease child
	 @Column(name="OPLKNDP")        private  String   oplkndp;          // place decease child
	 @Column(name="ADGKNDP")        private  int	  adgkndp;		    // day of departure child 
	 @Column(name="AMDKNDP")        private  int      amdkndp;	    	// month of departure child 
	 @Column(name="AJRKNDP")        private  int      ajrkndp;          // year of departure child 
	 @Column(name="APLKNDP")        private  String   aplkndp;          // destination departing child
	 @Column(name="AANTEK")         private  String   aantek;           // remark
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
	    
    PkEigknd(){
	    	
    }
	    
    public void convert(B2_ST b2){
    	
    	Utils.checkKeyFields(getIdnr() , "PkEigknd.DBF", getVn1kndp(), getAnmkndp(), "" + getGjrkndp()); 
    	
    	// Last name 
    	
    	String lastName = getAnmkndp().trim();
    	
    	if(lastName != null){
			if(lastName.split("%").length > 1){
				lastName = lastName.split("%")[0].trim();
				b2.setFamilyNameInterpreted(2);  	
			}
			else{
				lastName = lastName.split("%")[0].trim();
				b2.setFamilyNameInterpreted(1);
			}
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
    	
    	String firstName1 = getVn1kndp();
    	
    	if(firstName1 != null && firstName1.trim().length() > 0){
			if(firstName1.split("%").length > 1){
				firstName1 = firstName1.split("%")[0].trim();
		    	b2.setFirstNameFlag(2);
			}

			firstName += Utils.standardizeFirstName(firstName1);
			firstName += " ";
    	
    	}    	

    	String firstName2 = getVn2kndp();

    	if(firstName2 != null && firstName2.trim().length() > 0){
			if(firstName2.split("%").length > 1){
				firstName2 = firstName2.split("%")[0].trim();
				b2.setFirstNameFlag(2);  	
			}
				
			firstName += Utils.standardizeFirstName(firstName2);
			firstName += " ";
    	}    	

    	String firstName3 = getVn3kndp();
    	
    	if(firstName3 != null && firstName3.trim().length() > 0){
			if(firstName3.split("%").length > 1){
				firstName3 = firstName3.split("%")[0].trim();
				b2.setFirstNameFlag(2);  	
			}
				
			firstName += Utils.standardizeFirstName(firstName3);
    	}    	
    	
    	b2.setFirstName(firstName.trim());

    	
    	// Prefix
    	
    	if(getTuskndp() == null || getTuskndp().trim().length() == 0){
    		if(prefix != null)    	
    	    	b2.setPrefixLastName(Utils.standardizePrefix(prefix));
    		else;
    	}
    	else    	
    		b2.setPrefixLastName(Utils.standardizePrefix(getTuskndp()));
    	
    	
    	if(getRelkndp().trim().equalsIgnoreCase("ZOON") || 
    			getRelkndp().trim().equalsIgnoreCase("Z") ||
    			getRelkndp().trim().equalsIgnoreCase("STIEFZOON") ||
    			getRelkndp().trim().equalsIgnoreCase("SZ"))
    		b2.setSex("m");
    	else
        	if(getRelkndp().trim().equalsIgnoreCase("DOCHTER") || 
        			getRelkndp().trim().equalsIgnoreCase("D") ||
        			getRelkndp().trim().equalsIgnoreCase("STIEFDOCHTER") ||
        			getRelkndp().trim().equalsIgnoreCase("SD"))
        		b2.setSex("v");
        	else
        		if(!getRelkndp().trim().equalsIgnoreCase("KIND"))
        			message(getIdnr(), "7105", getRelkndp().trim());

    	
    	// Birth date
    	
		int[] result = 	Utils.transformDateFields(getGdgkndp(), getGmdkndp(), getGjrkndp(), getGdgkndpcr(), getGmdkndpcr(), getGjrkndpcr()); 

		b2.setDateOfBirth(String.format("%02d-%02d-%04d", result[0], result[1], result[2]));
		b2.setDateOfBirthFlag(result[3]);

		// Birth place
		
		String birthPlace = getGplkndp();
		
    	if(birthPlace != null){
			if(birthPlace.split("%").length > 1){
				birthPlace = birthPlace.split("%")[0].trim();
				b2.setPlaceOfBirthFlag(2);  	
			}
			else{
				birthPlace = birthPlace.split("%")[0].trim();
				b2.setPlaceOfBirthFlag(1);
			}
    	}

    	if(getGlnkndp() != null && getGlnkndp().trim().length() > 0)
    		birthPlace = birthPlace + " $ " + getGlnkndp();
    		
    	ArrayList a = Utils.standardizeLocation(birthPlace); // Combine place with Country
    	b2.setPlaceOfBirthStandardized((String)a.get(0));
    	b2.setPlaceOfBirthID((Integer)a.get(1));   	
    	
    	
    	String deceaseDate = null;
    	if(getOjrkndp() > 0)
    		deceaseDate = String.format("%02d-%02d-%04d", getOdgkndp(), getOmdkndp(), getOjrkndp());
    

    	b2.setDateOfDecease(deceaseDate);    	
    	b2.setDateOfDeceaseFlag(1);
    	
    	// Decease Place

		String deceasePlace = getOplkndp();
		
    	if(deceasePlace != null){
			if(deceasePlace.split("%").length > 1){
				deceasePlace = deceasePlace.split("%")[0].trim();
				b2.setPlaceOfDeceaseFlag(2);  	
			}
			else{
				deceasePlace = deceasePlace.split("%")[0].trim();
				b2.setPlaceOfDeceaseFlag(1);
			}
    	}

    	a = Utils.standardizeLocation(deceasePlace);
    	b2.setPlaceOfDeceaseStandardized((String)a.get(0));
    	b2.setPlaceOfDeceaseID((Integer)a.get(1));
    	
		
    	// dating
    	
    	String endDateSave = b2.getEndDate();
    	
    	// No start and end dates for children that are terminated before the PK start date 1-7-1938
    	
    	
    	if((Common1.dateIsValid(getAdgkndp(), getAmdkndp(), getAjrkndp()) == 0 && Common1.dayCount(getAdgkndp(), getAmdkndp(), getAjrkndp()) < Common1.dayCount(b2.getRegistration().getStartDate())) || 
       	   (Common1.dateIsValid(getOdgkndp(), getOmdkndp(), getOjrkndp()) == 0 && Common1.dayCount(getOdgkndp(), getOmdkndp(), getOjrkndp()) < Common1.dayCount(b2.getRegistration().getStartDate())) || 
    	   (Common1.dateIsValid(getHdgkndp(), getHmdkndp(), getHjrkndp()) == 0 && Common1.dayCount(getHdgkndp(), getHmdkndp(), getHjrkndp()) < Common1.dayCount(b2.getRegistration().getStartDate()))){ 
    		
    	    b2.setStartDate(null);
    		b2.setStartFlag(0);
			b2.setEndDate(null);
			b2.setEndFlag(0);
    		
    	}
    	else{

    		if(Common1.dayCount(b2.getDateOfBirth()) > Common1.dayCount(b2.getRegistration().getStartDate())){

    			b2.setStartDate(b2.getDateOfBirth());
    			b2.setStartFlag(22);

    		}

    		if(getOjrkndp() > 0){
    			if(Common1.dayCount(getOdgkndp(), getOmdkndp(), getOjrkndp()) < Common1.dayCount(b2.getEndDate())){
    				b2.setEndDate(String.format("%02d-%02d-%04d", getOdgkndp(), getOmdkndp(), getOjrkndp()));
    				b2.setEndFlag(40);
    			}
    		}

    		if(getAjrkndp() > 0){
    			if(Common1.dayCount(getAdgkndp(), getAmdkndp(), getAjrkndp()) < Common1.dayCount(b2.getEndDate())){
    				b2.setEndDate(String.format("%02d-%02d-%04d", getAdgkndp(), getAmdkndp(), getAjrkndp()));
    				b2.setEndFlag(41);
    			}
    		}

    		if(getHjrkndp() > 0){
    			if(Common1.dayCount(getHdgkndp(), getHmdkndp(), getHjrkndp()) < Common1.dayCount(b2.getEndDate())){
    				b2.setEndDate(String.format("%02d-%02d-%04d",getHdgkndp(), getHmdkndp(), getHjrkndp()));
    				b2.setEndFlag(42);
    			}
    		}


    		if(b2.getDateOfDecease() != null && b2.getDateOfBirth() != null &&
    				Common1.dayCount(b2.getDateOfBirth()) > Common1.dayCount(b2.getDateOfDecease()))
    			message(b2.getKeyToRP(), "4124", "" + b2.getFirstName() + " " + b2.getFamilyName());

    		if(b2.getStartDate() != null && b2.getEndDate() != null && 
    				Common1.dayCount(b2.getStartDate()) > Common1.dayCount(b2.getEndDate())){
    			message(b2.getKeyToRP(), "7136", "" + b2.getFirstName() + " " + b2.getFamilyName());

    			//	b2.setStartDate(null);
    			//	b2.setStartFlag(0);
    			//	b2.setEndDate(null);
    			b2.setEndFlag(0);

    		}

    	}
    	
    	// Relation to PK-Holder
    	
		B313_ST b313 = new B313_ST();
		b2.getRelationsToPKHolder().add(b313);    // Link B313_ST -> B2_ST
		b313.setPerson(b2);            			  // Link B2_ST -> B313_ST

		initialiseB3_ST(b313);
		b313.setDynamicDataType(13);
		b313.setKeyToRegistrationPersons(b2.getKeyToPersons());
		
		if(getRelkndp().trim().equalsIgnoreCase("ZOON") || getRelkndp().trim().equalsIgnoreCase("Z"))
			b313.setContentOfDynamicData(3);     // Son
		else
			if(getRelkndp().trim().equalsIgnoreCase("DOCHTER") || getRelkndp().trim().equalsIgnoreCase("D"))
				b313.setContentOfDynamicData(4); // Daughter
			else{
				if(getRelkndp().trim().equalsIgnoreCase("STIEFZOON") || getRelkndp().trim().equalsIgnoreCase("SZ"))
					b313.setContentOfDynamicData(8); // Stepson
				else
					if(getRelkndp().trim().equalsIgnoreCase("STIEFDOCHTER") || getRelkndp().trim().equalsIgnoreCase("SD"))
						b313.setContentOfDynamicData(9); // Stepdaughter
					else
						if(getRelkndp().trim().equalsIgnoreCase("KIND")) 
							b313.setContentOfDynamicData(133); // Kind PK

				
				b313.setStartDate(b2.getDateOfBirth());
				b313.setEndDate(endDateSave );
			}
				
		b313.setDynamicDataSequenceNumber(1);

    	// marriage
    	
    	if(getHjrkndp() > 0){
    		
    		
    		B32_ST b32 = new B32_ST();
        	b2.getCivilStatus().add(b32); // Link B32_ST -> B2_ST
        	b32.setPerson(b2);            // Link B2_ST -> B32_ST

    		initialiseB3_ST(b32);
    		b32.setKeyToRegistrationPersons(b2.getKeyToPersons());
    		
    		b32.setDynamicDataType(2);
    		b32.setContentOfDynamicData(5); // marriage
    		
   			String marriageDate = String.format("%02d-%02d-%04d", getHdgkndp(), getHmdkndp(), getHjrkndp());
   			b32.setDateOfMutation(marriageDate);
   			b32.setStartDate(marriageDate);
   			b32.setEndDate(null);  // because child leaves the PK observation by marrying


        	//a = Utils.standardizeLocation(getAplkndp());
        	
        	if(getHplkndp() != null && getHplkndp().trim().length() != 0){
            	ArrayList b = Utils.standardizeLocation(getHplkndp());
            	b32.setCivilLocalityStandardized((String)b.get(0));
            	b32.setCivilLocalityID((Integer)b.get(1));
        	}
    		
    		b32.setDynamicDataSequenceNumber(1);


    	}
    	
    	// departure
    	
    	if(getAplkndp() != null && getAplkndp().trim().length() != 0  && !getAplkndp().trim().equals("-1")){
    		
    		//System.out.println("Departure Child, destination = " + getAplkndp());
    		
    		B37_ST b37 = new B37_ST();
        	b2.getDestinations().add(b37); // Link B37_ST -> B2_ST
        	b37.setPerson(b2);             // Link B2_ST -> B37_ST

    		initialiseB3_ST(b37);
    		b37.setKeyToRegistrationPersons(b2.getKeyToPersons());
    		b37.setDynamicDataType(7);
    		

        	ArrayList b = Utils.standardizeLocation(getAplkndp());
        	b37.setDestinationStandardized((String)b.get(0));
        	b37.setDestinationID((Integer)b.get(1));
        	
        	if(getAjrkndp() != 0){
            	String departureDate = String.format("%02d-%02d-%04d", getAdgkndp(), getAmdkndp(), getAjrkndp());
            	b37.setDateOfMutation(departureDate);
        		
        	}
    		
    		b37.setDynamicDataSequenceNumber(1);


    	}
    }
	    
   	public void print(){
		
  		System.out.println("    In PkEigknd, idnr = " + getIdnr());
   	}
   	
    private static void message(int idnr, String number, String... fills) {

        //print("Messagenr: " + number);

        Message m = new Message(number);

        m.setKeyToRP(idnr);
        m.save(fills);
    }


    
    private void initialiseB3_ST(B3_ST b3){
    	
    	b3.setKeyToRP(getIdnr());
    	b3.setEntryDateHead((b3.getPerson().getEntryDateHead()));
    	b3.setKeyToSourceRegister(getPkHolder().getPktype());  	
    	
    	
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
	public int getVgnrkdp() {
		return vgnrkdp;
	}
	public void setVgnrkdp(int vgnrkdp) {
		this.vgnrkdp = vgnrkdp;
	}
	public String getAnmkndp() {
		return anmkndp;
	}
	public void setAnmkndp(String anmkndp) {
		this.anmkndp = anmkndp;
	}
	public String getTuskndp() {
		return tuskndp;
	}
	public void setTuskndp(String tuskndp) {
		this.tuskndp = tuskndp;
	}
	public String getVn1kndp() {
		return vn1kndp;
	}
	public void setVn1kndp(String vn1kndp) {
		this.vn1kndp = vn1kndp;
	}
	public String getVn2kndp() {
		return vn2kndp;
	}
	public void setVn2kndp(String vn2kndp) {
		this.vn2kndp = vn2kndp;
	}
	public String getVn3kndp() {
		return vn3kndp;
	}
	public void setVn3kndp(String vn3kndp) {
		this.vn3kndp = vn3kndp;
	}
	public int getGdgkndp() {
		return gdgkndp;
	}
	public void setGdgkndp(int gdgkndp) {
		this.gdgkndp = gdgkndp;
	}
	public int getGmdkndp() {
		return gmdkndp;
	}
	public void setGmdkndp(int gmdkndp) {
		this.gmdkndp = gmdkndp;
	}
	public int getGjrkndp() {
		return gjrkndp;
	}
	public void setGjrkndp(int gjrkndp) {
		this.gjrkndp = gjrkndp;
	}
	public int getGdgkndpcr() {
		return gdgkndpcr;
	}
	public void setGdgkndpcr(int gdgkndpcr) {
		this.gdgkndpcr = gdgkndpcr;
	}
	public int getGmdkndpcr() {
		return gmdkndpcr;
	}
	public void setGmdkndpcr(int gmdkndpcr) {
		this.gmdkndpcr = gmdkndpcr;
	}
	public int getGjrkndpcr() {
		return gjrkndpcr;
	}
	public void setGjrkndpcr(int gjrkndpcr) {
		this.gjrkndpcr = gjrkndpcr;
	}
	public String getGplkndp() {
		return gplkndp;
	}
	public void setGplkndp(String gplkndp) {
		this.gplkndp = gplkndp;
	}
	public String getGlnkndp() {
		return glnkndp;
	}
	public void setGlnkndp(String glnkndp) {
		this.glnkndp = glnkndp;
	}
	public String getRelkndp() {
		return relkndp;
	}
	public void setRelkndp(String relkndp) {
		this.relkndp = relkndp;
	}
	public int getHdgkndp() {
		return hdgkndp;
	}
	public void setHdgkndp(int hdgkndp) {
		this.hdgkndp = hdgkndp;
	}
	public int getHmdkndp() {
		return hmdkndp;
	}
	public void setHmdkndp(int hmdkndp) {
		this.hmdkndp = hmdkndp;
	}
	public int getHjrkndp() {
		return hjrkndp;
	}
	public void setHjrkndp(int hjrkndp) {
		this.hjrkndp = hjrkndp;
	}
	public String getHplkndp() {
		return hplkndp;
	}
	public void setHplkndp(String hplkndp) {
		this.hplkndp = hplkndp;
	}
	public String getVnmptnp() {
		return vnmptnp;
	}
	public void setVnmptnp(String vnmptnp) {
		this.vnmptnp = vnmptnp;
	}
	public String getTusptnp() {
		return tusptnp;
	}
	public void setTusptnp(String tusptnp) {
		this.tusptnp = tusptnp;
	}
	public String getAnmptnp() {
		return anmptnp;
	}
	public void setAnmptnp(String anmptnp) {
		this.anmptnp = anmptnp;
	}
	public int getOdgkndp() {
		return odgkndp;
	}
	public void setOdgkndp(int odgkndp) {
		this.odgkndp = odgkndp;
	}
	public int getOmdkndp() {
		return omdkndp;
	}
	public void setOmdkndp(int omdkndp) {
		this.omdkndp = omdkndp;
	}
	public int getOjrkndp() {
		return ojrkndp;
	}
	public void setOjrkndp(int ojrkndp) {
		this.ojrkndp = ojrkndp;
	}
	public String getOplkndp() {
		return oplkndp;
	}
	public void setOplkndp(String oplkndp) {
		this.oplkndp = oplkndp;
	}
	public int getAdgkndp() {
		return adgkndp;
	}
	public void setAdgkndp(int adgkndp) {
		this.adgkndp = adgkndp;
	}
	public int getAmdkndp() {
		return amdkndp;
	}
	public void setAmdkndp(int amdkndp) {
		this.amdkndp = amdkndp;
	}
	public int getAjrkndp() {
		return ajrkndp;
	}
	public void setAjrkndp(int ajrkndp) {
		this.ajrkndp = ajrkndp;
	}
	public String getAplkndp() {
		return aplkndp;
	}
	public void setAplkndp(String aplkndp) {
		this.aplkndp = aplkndp;
	}
	public String getAantek() {
		return aantek;
	}
	public void setAantek(String aantek) {
		this.aantek = aantek;
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
