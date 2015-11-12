package nl.iisg.convertPK;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import nl.iisg.ref.Ref;
import nl.iisg.ref.Ref_AINB;
import nl.iisg.ref.Ref_Address;
import nl.iisg.ref.Ref_Housenumber;
import nl.iisg.ref.Ref_Housenumberaddition;

@Entity
@Table(name="pkadres")
public class PkAdres {
	
	 @Id @Column(name="IDNR")       private int     idnr; 		  // ID Number
     @Column(name="VGNRADP")  	    private int     vgnradp;      // Sequence number address 
     @Column(name="DGADRP")     	private int     dgadrp;       // day address
     @Column(name="MDADRP")     	private int     mdadrp;       // month address
     @Column(name="JRADRP")     	private int     jradrp;       // year address
     @Column(name="VERNUM")     	private String  vernum;       // renumbering (vernummering)
     @Column(name="STRADRP")    	private String  stradrp;      // address (street)
     @Column(name="PLADRP")     	private String  pladrp;       // place (municipality)
     @Column(name="LNDADRP")     	private String  lndadrp;      // land (country)
         
	 @Column(name="OPDRNR")         private String   opdrnr;      // order number
	 @Column(name="DATUM")          private String   datum;       // date
	 @Column(name="INIT")           private String   init;        // initials 
	 @Column(name="VERSIE")         private String   versie;      // version
	 @Column(name="ONDRZKO")        private String   onderzko;    // original research
	 @Column(name="OPDRNRO")        private String   opdrnro;     // original order number  
	 @Column(name="DATUMO")         private String   datumo;      // original date   
	 @Column(name="INITO")          private String   inito;       // original initials    
	 @Column(name="VERSIEO")        private String   versieo;     // original version
	 
     @Transient 	                private PkKnd    pkHolder;      // PK-Holder

	 
	// No-arguments constructor is necessary
	    
    PkAdres(){
	    	
    }
	 
    public void convert(B6_ST b6){
    	
    	// temp
    	
    	b6.setInstitution(getPladrp());
    	b6.setLandlord(getStradrp());
    	
    	// temp
    	
    	
    	
    	
    	
    	// handle renumbering
    	
    	if(getVernum().equalsIgnoreCase("n"))
    		b6.setRenumbering(1);
    	else
        	if(getVernum().equalsIgnoreCase("v"))
        		b6.setRenumbering(4);
            	else
                	if(getVernum().equalsIgnoreCase("a"))
                		b6.setRenumbering(5);
    	
    	b6.setAddressFlag(4); // PK-version
    	
		// address date    	
		
		if(getJradrp() > 0)
			b6.setDateOfAddress(String.format("%02d-%02d-%04d", getDgadrp(), getMdadrp(), getJradrp()));
		
		
		
    	
    	// Address
		
		String address = getStradrp();
		if(address == null) return;
		
		// A2: See if address starts with an "&"
		
		if(address.length() > 1 &&  address.substring(0,1).equals("&")) return;
		
		// Try Locality
		
		address = tryLocalityInfo(b6, address);  // this sets place
		if(address == null) return;

		
		// Try Quarter
		
		address = tryQuarterInfo(b6, address);  // this sets quarter
		if(address == null) return;

				
		// Try NumberAndAddition
		
		address = tryNumberAndAdditionInfo(b6, address);  // this sets number and addition
		if(address == null) return;

				
		// Try Street
		
		address = tryStreetInfo(b6, address);  // this sets street
				
				
				
    	// municipality
    	
		String municipality = getPladrp();
		
		if(municipality == null) return;
		
		String [] b = municipality.split("!");
		
		if(b.length > 1)
			municipality = b[b.length -1];
			
		
		// Combine municipality with place
		
		if(b6.getPlace() != null)
			municipality = municipality + "!" + b6.getPlace();		
		
		// Combine municipality with country
		
		if(getLndadrp() != null && !getLndadrp().equalsIgnoreCase("NL"))
			municipality = municipality + " $" + getLndadrp();

    	ArrayList a = Utils.standardizeLocation(municipality);
    	b6.setMunicipality((String)a.get(0));
    	b6.setMunicipalityNumber((Integer)a.get(1));
				
				
    }	    
    


	/**
	 * 
	 * This routine tries to find Locality (=Deelgemeente) information from the string address
	 * If it finds it, it sets the appropriate fields in the ras
	 * A locality may be one element only that does not contain digits
	 * 
	 * Example: Bakkum/Straatweg 2 
	 * 
	 */


	
	private String tryLocalityInfo(B6_ST b6, String address){
		
		
		String [] a = address.split("[/]");
		
		//System.out.println(a);
		
		if(a.length > 0){
			
			String [] b = a[0].split("[ ]+");
			
			
			//System.out.println(b);

			if(b.length == 1 && b[0].length() > 2){ // We have LOCALITY/ADDRESS 				
				b6.setPlace(b[0]);
				
				address = "";
				for(int i = 1; i < a.length; i++)
					address = address + a[i] + " ";
				return address.trim();
			}
			
		}
		
		return address;

	}

	/**
	 * 
	 * This routine tries to find Quarter (=Wijk) information from the string address
	 * If it finds it, it sets the appropriate fields in the ras
	 * 
	 * Example: A 155
	 * 
	 */
	

		
	private String tryQuarterInfo(B6_ST b6, String address){


		String [] a = address.split("[ ]+");


		if(a != null && a.length > 0){
			if(a[0].equalsIgnoreCase("Wijk")){
				if(a.length > 1){
					b6.setQuarter(a[1]);
					address = "";
					for (int i = 2; i < a.length; i++)
						address = address + a[i] + " ";
				}
				return address.trim();
			}

		}


		if((a[0].length() == 1 &&   Character.isUpperCase(a[0].charAt(0)) == true) ||		
				(a[0].length() == 2 &&   Character.isUpperCase(a[0].charAt(0)) == true  && Character.isUpperCase(a[0].charAt(1)) == true)){
			b6.setQuarter(a[0]);

			address = "";
			for (int i = 1; i < a.length; i++)
				address = address + a[i] + " ";


			return address.trim();


		}

		return address;
	}
	
	/**
	 * 
	 * This routine tries to find the number and the number addition
	 * 
	 */
	private String tryNumberAndAdditionInfo(B6_ST b6, String address){
		
		
		
		String [] a = address.split("[ ]+");
		
		System.out.println(address +  "  " + a.length);

		
		for(int i = a.length  ; i > 0; i--){
			
			if(a[i-1].length() == 0) break;
			
			if(Character.isDigit(a[i-1].charAt(0)) == true){
				
				//b6.setNumber(a[i-1]);
				
				boolean setNumber = true;
				String number = "";
				String addition = "";
				for(int j = 0; j < a[i-1].length(); j++){
					
					if(Character.isDigit(a[i-1].charAt(j)) == false) setNumber = false;
					
					if(setNumber)
						number += a[i-1].charAt(j);
					else	
						addition += a[i-1].charAt(j);
						
					
				}
				
				b6.setNumber(number);
				
				
				a[i-1] = "";
				
				// All elements after this addition are also addition
				
				for(int j = i-1 ; j < a.length; j++){
					addition += a[j];
					a[j] = "";
				}
				b6.setAddition(addition);	
				
				address = "";
				for (int j = 0; j < a.length; j++)
					address = address + a[j] + " ";


				return address.trim();

				
				
				
			}
			
		}
		
		return address;
		
	}
	
	/**
	 * 
	 * This routine tries to find Street (=Straat) or Boat information from the string address
	 * If it finds it, it sets the appropriate fields in the ras
	 * 
	 */
	
	
	private String tryStreetInfo(B6_ST b6, String address){
		
		b6.setStreet(address);
		
		return null;
		
		
	}
	/**
	 * Check for slashes that are *NOT* separators between localities, streets and quarters 
	 * 
	 * 
	 * @param address
	 * @return address
	 */
	
	private String checkForSlashes(String address){
		
		String address1 = "";
		int index = 0;
		do{
			index = address.indexOf("/");
			if(index > 0 && index < address.length() - 1){
				if(address.substring(index -1, index).equalsIgnoreCase("A") && address.substring(index + 1, index + 2).equalsIgnoreCase("B")){ // A/b = aan boord
					
					address1 = address.substring(0, index - 1) + "~K25~" +   address.substring(index + 2); // change a/b to ~K25~
					address = address1;
					
				}
				else{
					if(address.substring(index -1, index).equalsIgnoreCase("V") && address.substring(index + 1, index + 2).equalsIgnoreCase("D")){ // v/d, becomes v.d.
						address1 = address.substring(0, index) + ".d." +   address.substring(index + 2);
						address = address1;
					}
					else{
						if(Character.isDigit(address.substring(index -1, index).charAt(0))){	// immediately after a number field: number - addition separator
							address1 = address.substring(0, index) + "~K26~" +   address.substring(index + 1);
							address = address1;
						}
						else{
							if(address.substring(index -1, index).equalsIgnoreCase("M") && address.substring(index + 1, index + 2).equalsIgnoreCase("Z")){ // m/z = unknown
								address1 = address.substring(0, index - 1) +    address.substring(index + 2); // remove m/z (=moet zijn)
								address = address1;
							}
							else
								index = -1;
						}
					}
				}
			}
		} while(index > 0); 
		
		
		return address;
	}

	public void convertAddress(B6_ST b6){
		
				
		Ref_Address  refAdd = Ref.getAddress(b6.getStreet(), b6.getQuarter(), b6.getPlace(), b6.getBoat(), b6.getBerth(), b6.getInstitution(), b6.getLandlord(), b6.getOther());
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

			refAdd.setStreetOriginal(b6.getStreet());
			refAdd.setQuarterOriginal(b6.getQuarter());
			refAdd.setPlaceOriginal(b6.getPlace());
			refAdd.setBoatOriginal(b6.getBoat());
			refAdd.setBerthOriginal(b6.getBerth());
			refAdd.setInstitutionOriginal(b6.getInstitution());
			refAdd.setLandlordOriginal(b6.getLandlord());
			refAdd.setOtherOriginal(b6.getOther());		
			
			refAdd.setCode("x");
			
			Ref.addAddress(refAdd);
			
			//setAddressID(refAdd.getAddressID());
			
		}
		
		if(b6.getNumber() != null && b6.getNumber().trim().length() != 0){
			Ref_Housenumber  refHousenumber = Ref.getHousenumber(b6.getNumber());
			if(refHousenumber != null && refHousenumber.getCode() != null && (refHousenumber.getCode().equalsIgnoreCase("y") || refHousenumber.getCode().equalsIgnoreCase("u"))){
				b6.setNumber(refHousenumber.getHousenumber());
			}
			else{

				refHousenumber = new Ref_Housenumber();
				refHousenumber.setOriginal(b6.getNumber());

				refHousenumber.setCode("x");

				Ref.addHousenumber(refHousenumber);

				//setAddressID(refAdd.getAddressID());

			}
		}

		if(b6.getAddition() != null && b6.getAddition().trim().length() != 0){
			Ref_Housenumberaddition  refHousenumberaddition = Ref.getHousenumberaddition(b6.getAddition());
			if(refHousenumberaddition != null && refHousenumberaddition.getCode() != null && (refHousenumberaddition.getCode().equalsIgnoreCase("y") || refHousenumberaddition.getCode().equalsIgnoreCase("y"))){
				b6.setAddition(refHousenumberaddition.getAddition());
			}
			else{

				refHousenumberaddition = new Ref_Housenumberaddition();
				refHousenumberaddition.setOriginal(b6.getAddition());

				refHousenumberaddition.setCode("x");

				Ref.addHousenumberaddition(refHousenumberaddition);

				//setAddressID(refAdd.getAddressID());

			}
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
		if(getLndadrp() != null)
			place = place + " $ " + getLndadrp();
		
    	ArrayList b = Utils.standardizeLocation(place);
    	b36.setOriginStandardized((String)b.get(0));
    	b36.setOriginID((Integer)b.get(1));
    	
    	if(getJradrp() != 0){
        	b36.setDateOfMutation(String.format("%02d-%02d-%04d", getDgadrp(), getMdadrp(), getJradrp()));
        	
    	}
    	
    }
    
    public void destination(B2_ST b2){
    	
    	System.out.println("   PkAddress destination country = " + getLndadrp() + " place = " + getPladrp());

		B37_ST b37 = new B37_ST();
    	b2.getDestinations().add(b37); // Link B37_ST -> B2_ST
    	b37.setPerson(b2);             // Link B2_ST -> B37_ST

		b37.setDynamicDataSequenceNumber(b2.getDestinations().size() + 1);

		initialiseB3_ST(b37);
		b37.setDynamicDataType(7);

		// Combine place with country
		
		String place = getPladrp();
		if(getLndadrp() != null)
			place = place + " $ " + getLndadrp();

		
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
	public int getVgnradp() {
		return vgnradp;
	}
	public void setVgnradp(int vgnradp) {
		this.vgnradp = vgnradp;
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
	public String getVernum() {
		return vernum;
	}
	public void setVernum(String vernum) {
		this.vernum = vernum;
	}
	public String getStradrp() {
		return stradrp;
	}
	public void setStradrp(String stradrp) {
		this.stradrp = stradrp;
	}
	public String getPladrp() {
		return pladrp;
	}
	public void setPladrp(String pladrp) {
		this.pladrp = pladrp;
	}
	public String getLndadrp() {
		return lndadrp;
	}
	public void setLndadrp(String lndarp) {
		this.lndadrp = lndarp;
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