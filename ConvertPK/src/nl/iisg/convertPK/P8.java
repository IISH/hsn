package nl.iisg.convertPK;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import nl.iisg.ref.Ref;
import nl.iisg.ref.Ref_Address;
import nl.iisg.ref.Ref_Housenumber;
import nl.iisg.ref.Ref_Housenumberaddition;

@Entity
@Table(name="p8")
public class P8 {
	 @Id  @Column(name="IDNR")      private int     idnr; 	      // ID Number
	 @Column(name="IDNRP")          private int     idnrp;  	  // IDNR partner
     @Column(name="P8TPNR")         private int     p8tpnr;  	  // sequence number
     @Column(name="DGADRP")     	private int     dgadrp;       // day address
     @Column(name="MDADRP")     	private int     mdadrp;       // month address
     @Column(name="JRADRP")     	private int     jradrp;       // year address
     @Column(name="PLADRP")     	private String  pladrp;       // place (municipality)
     @Column(name="P8OPPD")     	private int     p8oppd;       // day of first address
     @Column(name="P8OPPM")     	private int     p8oppm;       // month of first address
     @Column(name="P8OPPJ")     	private int     p8oppj;       // year of first address
     @Column(name="P8OPWF")     	private String  p8opwf;       // type of address
     @Column(name="P8OPWL")     	private String  p8opwl;       // Name of locality
     @Column(name="P8OPWS")     	private String  p8opws;       // Name of the street
     @Column(name="P8OPWH")     	private String  p8opwh;       // House number
     @Column(name="P8OPWR")     	private String  p8opwr;       // Addition character(s) to house number
     @Column(name="P8OPWT")     	private String  p8opwt;       // Additional number to house number
     @Column(name="P8OPWP")     	private String  p8opwp;       // Postal code
     @Column(name="P8OPWB")     	private String  p8opwb;       // Description of location
     @Column(name="P8OPIL")     	private String  p8opil;       // Country from which PL-holder originates from 
     @Column(name="P8OPIJ")     	private int     p8opij;       // Year of registration of arrival from foreign country
     @Column(name="P8OPIM")     	private int     p8opim;       // Month of registration of arrival from foreign country
     @Column(name="P8OPID")     	private int     p8opid;       // Day of registration of arrival from foreign country
     @Column(name="P8OPAG")     	private String  p8opag;       // Data under research 
     @Column(name="P8OPZJ")     	private int     p8opzj;       // Year of �data under research�
     @Column(name="P8OPZM")     	private int     p8opzm;       // Month of �data under research�
     @Column(name="P8OPZD")     	private int     p8opzd;       // Day of �data under research�
     @Column(name="P8OPIO")     	private String  p8opio;       // �indicatie onjuist�; not clear what this variable means (only sporadically content)
	 @Column(name="OPDRNR")         private String  opdrnr;  	  // order number
	 @Column(name="DATUM")          private String  datum;        // date
	 @Column(name="INIT")           private String  init;         // initials 
	 @Column(name="VERSIE")         private String  versie;       // version
	 @Column(name="ONDRZKO")        private String  onderzko;     // original research
	 @Column(name="OPDRNRO")        private String  opdrnro;      // original order number  
	 @Column(name="DATUMO")         private String  datumo;       // original date   
	 @Column(name="INITO")          private String  inito;        // original initials    
	 @Column(name="VERSIEO")        private String  versieo;      // original version
	 
     @Transient 	                private PkKnd   pkHolder;     // PK-Holder

	 
	// No-arguments constructor is necessary
	    
	P8(){   }
	
	public void convert(B6_ST b6){
    	
		b6.setRenumbering(6); // no code, address from PL-List
    	b6.setAddressFlag(5); // PL-version
    	
		// Combine place with country

    	String place = getPladrp();
    	
		if(getP8opil() != null)
			place = place + " $ " + getP8opil();

		
    	ArrayList a = Utils.standardizeLocation(place);
    	b6.setMunicipality((String)a.get(0));
    	
    	// address
    	
		Ref_Address  refAdd = Ref.getAddress(getP8opws(), null, getP8opwl(), null, null, null, null, null);
		if(refAdd != null && refAdd.getCode() != null && (refAdd.getCode().equalsIgnoreCase("y") || refAdd.getCode().equalsIgnoreCase("u"))){
			
			b6.setStreet(refAdd.getStreet());
			b6.setQuarter(refAdd.getQuarter());
			b6.setPlace(refAdd.getPlace());
			b6.setBoat(refAdd.getBoat());
			b6.setBerth(refAdd.getBerth());
			b6.setInstitution(refAdd.getInstitution());
			b6.setLandlord(refAdd.getLandlord());
			b6.setOther(refAdd.getOther());
			b6.setAddressID(refAdd.getAddressID());
			//setAddressFlag(refAdd.); // new address type
			
		}
		else{
			
			b6.setAddressID(-1);  // indicate that we have no reference values (but the original values)
			
			refAdd = new Ref_Address();

			refAdd.setStreetOriginal(getP8opws());
			refAdd.setQuarterOriginal(null);
			refAdd.setPlaceOriginal(getP8opwl());
			refAdd.setBoatOriginal(null);
			refAdd.setBerthOriginal(null);
			refAdd.setInstitutionOriginal(null);
			refAdd.setLandlordOriginal(null);
			refAdd.setOtherOriginal(null);		
			
			refAdd.setCode("x");
			
			Ref.addAddress(refAdd);
			
			// use original values
			
			b6.setStreet(getP8opws());
			b6.setPlace(getP8opwl());
			
			//setAddressID(refAdd.getAddressID());
			
		}

    	// house number
		
		if(getP8opwh() != null && getP8opwh().trim().length() != 0){
			Ref_Housenumber  refHousenumber = Ref.getHousenumber(b6.getNumber());
			if(refHousenumber != null && refHousenumber.getCode() != null && (refHousenumber.getCode().equalsIgnoreCase("y") || refHousenumber.getCode().equalsIgnoreCase("u"))){
				b6.setNumber(refHousenumber.getHousenumber());
			}
			else{

				refHousenumber = new Ref_Housenumber();
				refHousenumber.setOriginal(getP8opwh());

				refHousenumber.setCode("x");

				Ref.addHousenumber(refHousenumber);
				
				// use original value
				
				b6.setNumber(getP8opwh()); 

			}
		}

    	// house number addition
		
		String addition = null;
		
		if(getP8opwr() != null && getP8opwr().trim().length() != 0){
			addition = getP8opwr().trim();
			if(getP8opwt() != null && getP8opwt().trim().length() != 0)
				addition += "-" + getP8opwr().trim();

		}
		else
			if(getP8opwt() != null && getP8opwt().trim().length() != 0)
				addition += getP8opwr().trim();
		

		if(addition != null && addition.trim().length() != 0){
			Ref_Housenumberaddition  refHousenumberaddition = Ref.getHousenumberaddition(b6.getAddition());
			if(refHousenumberaddition != null && refHousenumberaddition.getCode() != null && (refHousenumberaddition.getCode().equalsIgnoreCase("y") || refHousenumberaddition.getCode().equalsIgnoreCase("u"))){
				b6.setAddition(refHousenumberaddition.getAddition());
			}
			else{

				refHousenumberaddition = new Ref_Housenumberaddition();
				refHousenumberaddition.setOriginal(addition);

				refHousenumberaddition.setCode("x");

				Ref.addHousenumberaddition(refHousenumberaddition);
				
				// use original value
				
				b6.setAddition(addition); 


			}
		}

		
		// postal code
		
		if(getP8opwp() != null)
			b6.setZipCode(getP8opwp());
		
		// address date
		
		if(getJradrp() > 0){
			b6.setDateOfAddress(String.format("%02d-%02d-%04d", getDgadrp(), getMdadrp(), getJradrp()));
			b6.setStartDate(b6.getDateOfAddress());
		}
	
	}
	
    public void origin(B2_ST b2){

		B36_ST b36 = new B36_ST();
    	b2.getOrigins().add(b36); // Link B36_ST -> B2_ST
    	b36.setPerson(b2);        // Link B2_ST -> B36_ST

		b36.setDynamicDataSequenceNumber(b2.getOrigins().size() + 1);

		initialiseB3_ST(b36);
		b36.setDynamicDataType(6);
		
		// Combine place with country
		
		String place = getPladrp();
		if(getP8opil() != null)
			place = place + " $ " + getP8opil();
		
    	ArrayList b = Utils.standardizeLocation(place);
    	b36.setOriginStandardized((String)b.get(0));
    	b36.setOriginID((Integer)b.get(1));
    	
    	if(getJradrp() != 0){
        	b36.setDateOfMutation(String.format("%02d-%02d-%04d", getDgadrp(), getMdadrp(), getJradrp()));
        	
    	}
    	
    }
    
    public void destination(B2_ST b2){

    	System.out.println("   P8        destination country = " + getP8opil() + " place = " + getPladrp());

		B37_ST b37 = new B37_ST();
    	b2.getDestinations().add(b37); // Link B37_ST -> B2_ST
    	b37.setPerson(b2);             // Link B2_ST -> B37_ST

		b37.setDynamicDataSequenceNumber(b2.getDestinations().size() + 1);

		initialiseB3_ST(b37);
		b37.setDynamicDataType(7);

		// Combine place with country
		
		String place = getPladrp();
		if(getP8opil() != null)
			place = place + " $ " + getP8opil();
		
    	ArrayList b = Utils.standardizeLocation(place);
    	b37.setDestinationStandardized((String)b.get(0));
    	b37.setDestinationID((Integer)b.get(1));
    	
    	if(getJradrp() != 0){
        	b37.setDateOfMutation(String.format("%02d-%02d-%04d", getDgadrp(), getMdadrp(), getJradrp()));
        	
    	}
    	
    }
    
    private void initialiseB3_ST(B3_ST b3){
    	
    	b3.setKeyToRP(getIdnr());
    	b3.setEntryDateHead(b3.getPerson().getEntryDateHead());
    	b3.setKeyToSourceRegister(b3.getPerson().getKeyToSourceRegister());
    	
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
	public int getIdnrp() {
		return idnrp;
	}
	public void setIdnrp(int idnrp) {
		this.idnrp = idnrp;
	}
	public int getP8tpnr() {
		return p8tpnr;
	}
	public void setP8tpnr(int p8tpnr) {
		this.p8tpnr = p8tpnr;
	}
	public int getDgadrp() {
		return dgadrp;
	}
	public void setDgadrp(int dgadrp) {
		this.dgadrp = dgadrp;
	}
	public int getMdadrp() {
		return mdadrp;
	}
	public void setMdadrp(int mdadrp) {
		this.mdadrp = mdadrp;
	}
	public int getJradrp() {
		return jradrp;
	}
	public void setJradrp(int jradrp) {
		this.jradrp = jradrp;
	}
	public String getPladrp() {
		return pladrp;
	}
	public void setPladrp(String pladrp) {
		this.pladrp = pladrp;
	}
	public int getP8oppd() {
		return p8oppd;
	}
	public void setP8oppd(int p8oppd) {
		this.p8oppd = p8oppd;
	}
	public int getP8oppm() {
		return p8oppm;
	}
	public void setP8oppm(int p8oppm) {
		this.p8oppm = p8oppm;
	}
	public int getP8oppj() {
		return p8oppj;
	}
	public void setP8oppj(int p8oppj) {
		this.p8oppj = p8oppj;
	}
	public String getP8opwf() {
		return p8opwf;
	}
	public void setP8opwf(String p8opwf) {
		this.p8opwf = p8opwf;
	}
	public String getP8opwl() {
		return p8opwl;
	}
	public void setP8opwl(String p8opwl) {
		this.p8opwl = p8opwl;
	}
	public String getP8opws() {
		return p8opws;
	}
	public void setP8opws(String p8opws) {
		this.p8opws = p8opws;
	}
	public String getP8opwh() {
		return p8opwh;
	}
	public void setP8opwh(String p8opwh) {
		this.p8opwh = p8opwh;
	}
	public String getP8opwr() {
		return p8opwr;
	}
	public void setP8opwr(String p8opwr) {
		this.p8opwr = p8opwr;
	}
	public String getP8opwt() {
		return p8opwt;
	}
	public void setP8opwt(String p8opwt) {
		this.p8opwt = p8opwt;
	}
	public String getP8opwp() {
		return p8opwp;
	}
	public void setP8opwp(String p8opwp) {
		this.p8opwp = p8opwp;
	}
	public String getP8opwb() {
		return p8opwb;
	}
	public void setP8opwb(String p8opwb) {
		this.p8opwb = p8opwb;
	}
	public String getP8opil() {
		return p8opil;
	}
	public void setP8opil(String p8opil) {
		this.p8opil = p8opil;
	}
	public int getP8opij() {
		return p8opij;
	}
	public void setP8opij(int p8opij) {
		this.p8opij = p8opij;
	}
	public int getP8opim() {
		return p8opim;
	}
	public void setP8opim(int p8opim) {
		this.p8opim = p8opim;
	}
	public int getP8opid() {
		return p8opid;
	}
	public void setP8opid(int p8opid) {
		this.p8opid = p8opid;
	}
	public String getP8opag() {
		return p8opag;
	}
	public void setP8opag(String p8opag) {
		this.p8opag = p8opag;
	}
	public int getP8opzj() {
		return p8opzj;
	}
	public void setP8opzj(int p8opzj) {
		this.p8opzj = p8opzj;
	}
	public int getP8opzm() {
		return p8opzm;
	}
	public void setP8opzm(int p8opzm) {
		this.p8opzm = p8opzm;
	}
	public int getP8opzd() {
		return p8opzd;
	}
	public void setP8opzd(int p8opzd) {
		this.p8opzd = p8opzd;
	}
	public String getP8opio() {
		return p8opio;
	}
	public void setP8opio(String p8opio) {
		this.p8opio = p8opio;
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
