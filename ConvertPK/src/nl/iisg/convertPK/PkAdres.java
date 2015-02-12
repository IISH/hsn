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
	 
    public boolean convert(B6_ST b6, String streetName){
    	
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
		
	
		if(getStradrp() == null || getStradrp().trim().length() == 0)
			return false;
		
    	
		// A1: See if we have a "*" in the record, replace it by the street/quarter name of the previous record

		String address = "";
		
		int index = getStradrp().indexOf("*");		
		if(index >= 0){
			if(streetName != null){
				address = getStradrp().substring(0, index);
				address += " ";
				address += (streetName != null ? streetName.trim() : ""); 
				address += " ";
				if(getStradrp().length() > index +1)
					address += getStradrp().substring(index +1).trim();
			}
			else
				address = getStradrp().trim();
		}	
		else
			address = getStradrp().trim();
		
		// A2: See if address starts with an "&"
		
		if(address.substring(0,1).equals("&")){
			int index1 = getStradrp().indexOf("/");		
			if(index1 >= 0){
				if(address.length() > index1 + 1)
					address = address.substring(index1 + 1);
				else
					address = "";
			}
			else
				address = "";
		}
		if(address.length() == 0)
			return false;
    	
	// B: Check for "/"
		
		address = checkForSlashes(address); // this removes slashes that are not adress separators
		
		index = address.indexOf("/");  // find the address separator
		
		if(index < 0){
			
			// B1: No "/"
			
			// C1: Try to find Quarter information
			// C2: Try to find Street information 
			
			
			address = address.replaceAll("~K26~", "/"); // replace the separator between number and addition by a slash again
			address = tryQuarterInfo(b6, address);
			if(address != null)
				address = tryStreetInfo(b6, address);
		}
		else{
			if(index > 0){  // B3
				String [] a = address.split("[/]+"); // split on "/"
				for(String s: a){
					s = s.replaceAll("~K26~", ""); // remove the separator between number and addition
					s = tryLocalityInfo(b6, s);  // C1
					if(s != null){
						s = tryQuarterInfo(b6, s); // C2
						if(s != null)
							s = tryStreetInfo(b6, s); // D
					}
				}
			}
			else // index == 0: B2
				address = tryStreetInfo(b6, address);
		}
		
		convertAddress(b6);
		if(b6.getMunicipality() != null){
			return true;
		}
		else
			return false;
    }	    
    


	/**
	 * 
	 * This routine tries to find Locality (=Deelgemeente) information from the string address
	 * If it finds it, it sets the appropriate fields in the ras
	 * A locality may be one element only that does not contain digits
	 * 
	 * Example: (!)Bakkum (deelgemeente van Castricum)
	 * 
	 */


	
	private String tryLocalityInfo(B6_ST b6, String address){

		
		int index = address.indexOf("!");
		if(index > 0){
			
			b6.setPlace(address.substring(index + 1));
			return null;
		}
		
		
		
		
		String [] a = address.split("[ ]+");

		boolean locality = true;

		if(a.length == 1){

			for(int i = 0; i < a[0].length(); i++){
				if(Character.isDigit(a[0].charAt(i))){
					locality = false;
					break;
				}
			}
		}
		else
			locality = false;
		
		
		if(locality == true){ 
			b6.setPlace(address);
			return null;
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


		String rt = null; 
		String [] a = address.split("[ ]+");

		switch(a.length){

		case 0: break;
		case 1: break;


		case 2:
			
			if(Character.isDigit(a[a.length - 1].charAt(0)) == true && a[a.length - 1].length() <= 6){
				if((a[0].length() == 1 && Character.isUpperCase(a[0].charAt(0)) == true) ||
						(a[0].length() == 2 && Character.isUpperCase(a[0].charAt(0)) == true &&  Character.isUpperCase(a[0].charAt(1))) &&
						!a[0].equalsIgnoreCase("GK") && !a[0].equalsIgnoreCase("ZF")){

				
					String number = "";
					String addition = "";
				
					for(int i = 0; i < a[a.length - 1].length(); i++){
						if(Character.isDigit(a[a.length - 1].charAt(i)) && addition.length() == 0)
							number += a[a.length - 1].charAt(i);
						else
							addition += a[a.length - 1].charAt(i);
					
					}
				
					if(number.length() > 0)
						b6.setNumber(number);
				
					if(addition.length() > 0)
						b6.setAddition(addition);
				
					b6.setQuarter(a[0]);
				
					rt = null;
				}
				else 
					rt = address;
			}
			else
				rt = address;
			break;

		default: rt = address;

		}

		return rt;
	}
	/**
	 * 
	 * This routine tries to find Street (=Straat) or Boat information from the string address
	 * If it finds it, it sets the appropriate fields in the ras
	 * The field must have at least 2 elements, a street name and a number
	 * 
	 * 
	 */
	
	
	private String tryStreetInfo(B6_ST b6, String address){
		
		
		String rt = null;
		
		boolean boat = false;
		
		if(address.indexOf("~K25~") >= 0){  // remove ~K25~ = a/b = aan boord
			address = address.replaceAll("~K25~", "");
			boat = true;
		}

		String [] a = address.trim().split("[ ]+");
		
		
		switch(a.length){
		
		case 0: break;
		case 1: break;
			
		
		case 2:
		
			if(Character.isDigit(a[a.length - 1].charAt(0)) == true && a[a.length - 1].length() <= 6){
				
				String number = "";
				String addition = "";
				
				for(int i = 0; i < a[a.length - 1].length(); i++){
					if(Character.isDigit(a[a.length - 1].charAt(i)) && addition.length() == 0)
							number += a[a.length - 1].charAt(i);
					else{
						addition +=  a[a.length - 1].charAt(i);
					}
				}
				
				if(addition.length() > 0 && addition.substring(0,1).equals("/"))
					addition = addition.substring(1);
				
				
				if(number.length() > 0)
					b6.setNumber(number);
				
				if(addition.length() > 0)
					b6.setAddition(addition);
				
				
				if(boat == true)
					b6.setBoat(a[a.length - 2]);
				else
					b6.setStreet(a[a.length - 2]);
				
				rt = null;
				
			}
			else
				rt = address;
			break;

			
		default: // minimal 3 words
			
			if(Character.isDigit(a[a.length - 1].charAt(0)) == true && a[a.length - 1].length() <= 6){
				
				String number = "";
				String addition = "";
				
				for(int i = 0; i < a[a.length - 1].length(); i++){
					if(Character.isDigit(a[a.length - 1].charAt(i)) && addition.length() == 0)
							number += a[a.length - 1].charAt(i);
					else{
						addition +=  a[a.length - 1].charAt(i);
					}
				}
				
				if(addition.length() > 0 && addition.substring(0,1).equals("/"))
					addition = addition.substring(1);
				
				if(number.length() > 0)
					b6.setNumber(number);
				
				if(addition.length() > 0)
					b6.setAddition(addition); 

				String street = "";
				for(int i = 0; i < a.length - 1; i++){ // all preceding words constitute street
					street += a[i]; 
					street += " " ;
				}		
				
				if(boat == true)
					b6.setBoat(street);
				else
					b6.setStreet(street);
				
				rt = null;
			}
			else{ // minimal 3 words, start at one but last
				if(Character.isDigit(a[a.length - 2].charAt(0)) == true && a[a.length - 2].length() <= 6){
					
					String number = "";
					String addition = "";
					
					for(int i = 0; i < a[a.length - 2].length(); i++){
						if(Character.isDigit(a[a.length - 2].charAt(i)) && addition.length() == 0)
								number += a[a.length - 2].charAt(i);
						else{
							addition +=  a[a.length - 2].charAt(i);
						}
					}
					
					if(addition.length() > 0 && addition.substring(0,1).equals("/"))
						addition = addition.substring(1);
					

					if(number.length() > 0)
						b6.setNumber(number);
					
					b6.setAddition((addition + " " + a[a.length - 1]).trim()); // add the last element to the addition 

					
					
					String street = "";
					for(int i = 0; i < a.length - 2; i++){
						street += a[i]; 
						street += " " ;
					}		
					
					if(boat == true)
						b6.setBoat(street);
					else
						b6.setStreet(street);
					
					
					rt = null;
					
				}
				else{
					rt = address;
				}
			}
		}				

		return rt;
		
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
		
    	// municipality
    	
		String locality = getPladrp();
		
		String [] b = locality.split("!");
		
		if(b.length > 1)
			locality = b[b.length -1];
			
		// Combine place with country
		
		if(getLndadrp() != null)
			locality = locality + " $ " + getLndadrp();

    	ArrayList a = Utils.standardizeLocation(locality);
    	b6.setMunicipality((String)a.get(0));
				
		Ref_Address  refAdd = Ref.getAddress(b6.getStreet(), b6.getQuarter(), b6.getPlace(), b6.getBoat(), b6.getBerth(), b6.getInstitution(), b6.getLandlord(), b6.getOther());
		if(refAdd != null && refAdd.getCode() != null && (refAdd.getCode().equalsIgnoreCase("j") == true)){
			
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
			if(refHousenumber != null && refHousenumber.getCode() != null && (refHousenumber.getCode().equalsIgnoreCase("j") == true || refHousenumber.getCode().equalsIgnoreCase("o") == true)){
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
			if(refHousenumberaddition != null && refHousenumberaddition.getCode() != null && (refHousenumberaddition.getCode().equalsIgnoreCase("j") == true)){
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