package iisg.nl.hsnmigrate;

import nl.iisg.ref.*;

public class Functions {
	
	static int famNameMesCount = 0; 
	static int prefixMesCount = 0; 
	static int firstNameMesCount = 0; 
	static int locationMesCount = 0; 
	static int professionMesCount = 0; 
	
	static int messageLimit = 500000;
	
	static int c = 0;
	
	public static int date_f(int day, int month, int year){

		final int errorBase = 100500; 
		
		int [] monthLength = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

		if(year == 0 && month == 0 && day == 0)
			return 0;
		
		if(!(year >= 1800 && year <= 1970)) return errorBase;
		if(!(month > 0    && month <= 12))      return errorBase;
		if(!(day   > 0    && (day  <=  monthLength[month] || (day <= monthLength[2] + 1 && month == 2 && year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))))) 
			return errorBase;

		return 0;
	}
	
	public static int hour_f(int hour){

		final int errorBase = 100900; 

		if(hour < -3 || hour > 23)
			return errorBase;

		return 0;
	}
	
	public static int minute_f(int minute){

		final int errorBase = 101000; 

		if(minute < -3 || minute > 59)
			return errorBase;

		return 0;
	}
	
	
	// check order of separators
	public static int vlslastname_f(String name){
		
		final int errorBase = 100600; 
		
		if(name == null || name.trim().length() == 0)
			return 0;
		
		int pos1 = name.indexOf(Constants.HSN_FIELD_SEPARATOR_VOORVOEG);
		int pos2 = name.indexOf(Constants.HSN_FIELD_SEPARATOR_PATRONIME);
		int pos3 = name.indexOf(Constants.HSN_FIELD_SEPARATOR_TITLE);
		
		boolean sole = (name.lastIndexOf(Constants.HSN_FIELD_SEPARATOR_VOORVOEG) == pos1 &&
				name.lastIndexOf(Constants.HSN_FIELD_SEPARATOR_PATRONIME) == pos2 &&
				name.lastIndexOf(Constants.HSN_FIELD_SEPARATOR_TITLE) == pos3);
		
		if(pos3 < 0) pos3 = name.length();
		if(pos2 < 0) pos2 = pos3;
		if(pos1 < 0) pos1 = pos2;
		
		if(!sole || pos1 > pos2 || pos2 > pos3 ){
			//System.out.println("Name " + name + " errorbase = " + errorBase);
			return errorBase;
		}
		//System.out.println("Name " + name + " errorbase = " + 0);
		
		return 0;
	}
	
	public static int empty_f(String x){

		final int errorBase = 100800; 

		if(x == null || x.trim().length() == 0)
			return errorBase;

		return 0;
	}
	
	public static int age_f(int age){
		
		final int errorBase = 101100; 

		if(age < -3 || age > 110)
			return errorBase;

		return 0;
	}
	
	public static int idem_f(String x){

		
		final int errorBase = 101600; 

		if(x == null || x.trim().length() == 0)
			return 0;

		x = x.trim();
		
		if(x.toUpperCase().equals("ID") || x.toUpperCase().equals("IDEM"))
			return errorBase;
		
		return 0;
	}
	
	// Check for 3 equals characters in a row: not good
	public static int threedouble_f(String x1){

		final int errorBase = 101500;
		
		if(x1 == null || x1.trim().length() == 0)
			return 0;
		
		String x = x1.trim(); 

		for(int i = 1; i < x.length() - 1; i++){			
			if(x.charAt(i-1) == x.charAt(i) && x.charAt(i) == x.charAt(i+ 1) && " 0123456789".indexOf(x.charAt(i)) < 0){
				return errorBase;
			}
		}
		
		return 0;
	}
	
	
	public static int vlslocation_f(String location){
		
		
		//System.out.println(location);
		final int errorBase = 100700; 

		if(location == null || location.trim().length() == 0)
			return 0;

		
		int pos1 = location.indexOf(Constants.HSN_FIELD_SEPARATOR_HAMLET);
		int pos2 = location.indexOf(Constants.HSN_FIELD_SEPARATOR_PROVINCE);
		int pos3 = location.indexOf(Constants.HSN_FIELD_SEPARATOR_COUNTRY);
		int pos4 = location.indexOf(Constants.HSN_FIELD_SEPARATOR_ADDRESS);
		int pos5 = location.indexOf(Constants.HSN_FIELD_SEPARATOR_BEVADM);
		
		boolean sole = (location.lastIndexOf(Constants.HSN_FIELD_SEPARATOR_HAMLET) == pos1 &&
				location.lastIndexOf(Constants.HSN_FIELD_SEPARATOR_PROVINCE) == pos2 &&
				location.lastIndexOf(Constants.HSN_FIELD_SEPARATOR_COUNTRY) == pos3 &&
		//		location.lastIndexOf(Constants.HSN_FIELD_SEPARATOR_ADDRESS) == pos4 &&
				location.lastIndexOf(Constants.HSN_FIELD_SEPARATOR_BEVADM) == pos5);
		
		if(pos5 < 0) pos5 = location.length();
		if(pos4 < 0) pos4 = pos5;
		if(pos3 < 0) pos3 = pos4;
		if(pos2 < 0) pos2 = pos3;
		if(pos1 < 0) pos1 = pos2;
		
		//System.out.println("" +sole+ " " + pos1 + " " +  pos2 + " " + pos3 + " " + pos4 + " " + pos5);
		
		if(!sole || pos1 > pos2 || pos2 > pos3 || pos3 > pos4 || pos4 > pos5)
			return errorBase;
		
		return 0;
	}
	
	public static int signature_f(String x){

		final int errorBase = 101200; 
		
		if(x == null || x.trim().length() == 0)
			return 0;

		if(x.trim().length() > 1)			
			return errorBase;
		
		x = x.trim();

		if("ABCDEHJNO".indexOf(x.toUpperCase()) < 0)			
			return errorBase;

		return 0;
	}

	public static int gender_f(String x){

		final int errorBase = 101300; 

		if(x == null || x.trim().length() == 0)
			return 0;
		
		if(x.trim().length() > 1)			
			return errorBase;

		x = x.trim();
		
		if("MV".indexOf(x.toUpperCase()) < 0)			
			return errorBase;

		return 0;
	}

	public static int civilstatusB_f(String x){

		final int errorBase = 101400; 

		if(x == null || x.trim().length() == 0)
			return 0;
		
		if(x.trim().length() > 1)			
			return errorBase;

		x = x.trim();
		
		if("12345678".indexOf(x.toUpperCase()) < 0)			
			return errorBase;

		return 0;
	}
	
	public static int civilstatusM_f(String x){

		final int errorBase = 101400; 

		if(x == null || x.trim().length() == 0)
			return 0;
		
		if(x.trim().length() > 1)			
			return errorBase;

		x = x.trim();

		if("1236".indexOf(x.toUpperCase()) < 0)	
			return errorBase;

		return 0;
	}
	
	public static int civilstatusD_f(String x){

		final int errorBase = 101400; 

		if(x == null || x.trim().length() == 0)
			return 0;
		
		if(x.trim().length() > 1)			
			return errorBase;
		
		x = x.trim();
		
		if("12356".indexOf(x.toUpperCase()) < 0)	
			return errorBase;

		return 0;
	}
	
	
	public static int certiftype_f(int x){

		final int errorBase = 101800; 

		
		if(x < 1 || x > 5)			
			return errorBase;

		return 0;
	}
	
	public static int yesno_f(String x){

		final int errorBase = 102300; 

		if(x == null || x.trim().length() == 0)
			return errorBase;
		
		if(x.trim().length() > 1)			
			return errorBase;
		
		x=x.trim();
		
		if(!x.equalsIgnoreCase("J") && !x.equalsIgnoreCase("N") && !x.equalsIgnoreCase("O"))
			return errorBase;

		return 0;
	}
	
	public static int yesnoO_f(String x){

		final int errorBase = 102300; 

		if(x == null || x.trim().length() == 0)
			return 0;

		if(x.trim().length() > 1)			
			return errorBase;
		
		x=x.trim();
		
		if(!x.equalsIgnoreCase("J") && !x.equalsIgnoreCase("N") && !x.equalsIgnoreCase("O")){
			System.out.println("Invalid permission: " + x + " length: " + x.length());
			return errorBase;
		}

		return 0;
	}
	
	public static int permission_f(String x){

		final int errorBase = 102300; 

		if(x == null || x.trim().length() == 0)
			return 0;

		if(x.trim().length() > 1)			
			return errorBase;
		
		x=x.trim();
		
		if(!x.equalsIgnoreCase("J") && !x.equalsIgnoreCase("M") && !x.equalsIgnoreCase("A") && !x.equalsIgnoreCase("N") && !x.equalsIgnoreCase("S") && !x.equalsIgnoreCase("O"))
			return errorBase;

		return 0;
	}
	
	public static int birthcode_f(int x){

		final int errorBase = 101900; 

		if(!(x > 0 && x < 10 && x != 8))
			return errorBase;
		
		return 0;
	}
	
	public static int codeconversion_f(int x){

		final int errorBase = 102600; 
		
		//System.out.println("Code = " + x + " municipality = " + Ref.getMunicipality(x));
		
		if(x <= 0)
			return errorBase;
		
		Ref_Municipality r = Ref.getMunicipality(x);
		if(r != null){		
			if(r.getMunicipalityName() == null)
				return errorBase;
			
		}
		else
			return errorBase;
		
		return 0;
	}
	
	public static int divorce_f(String x){

		final int errorBase = 102400; 

		if(x == null || x.trim().length() == 0)
			return 0;

		if(x.trim().length() > 1)			
			return errorBase;
		
		x=x.trim();
		
		if(!x.equals("1") && !x.equals("2") && !x.equals("3"))
			return errorBase;
		
		return 0;
	}
	
	public static int relation_f(String x){

		final int errorBase = 102500; 

		if(x == null || x.trim().length() == 0)
			return 0;

		if(x.trim().length() > 1)			
			return errorBase;

		x=x.trim();
		
		if(!x.equalsIgnoreCase("M") && !x.equalsIgnoreCase("V") && !x.equalsIgnoreCase("O") && !x.equalsIgnoreCase("B")) // Man, Vrouw, Onbekend, Beiden
			return errorBase;
		
		return 0;
	}
	
	public static int recognition_f(String x){

		final int errorBase = 102700; 

		if(x == null || x.trim().length() == 0)
			return 0;

		if(x.trim().length() > 1)			
			return errorBase;
		
		x=x.trim();
		
		if(!x.equals("0") && !x.equals("1") && !x.equals("2") && !x.equals("3"))
			return errorBase;
		
		return 0;
	}
	
	public static int lastname_valid_f(String name){
		
		if(name == null) return 0;
		
		for(int i = 0; i < name.length(); i++)
			if(!Character.isLetter(name.charAt(i)) && name.charAt(i) != ' ' && name.charAt(i) != ',' && name.charAt(i) != '$' && name.charAt(i) != '!')
				return 200100;
		
		return 0;

		
	}
	
	

	//
	// Reference functions
	//
	
	public static String firstname_r(String fnames,  int errCode, int idnr, int year, String db, String tbl){

		final int errorBase = 200200; 

		String result = "";
		if(fnames != null && fnames.length() != 0){
			
			String [] names = fnames.split("[ ]");
			for(String name: names){
				
				if(name.trim().length() == 0)
					continue;
				
				Ref_FirstName f = Ref.getFirstName(name);
				
				if(f != null  && f.getCode() != null && (f.getCode().equalsIgnoreCase("y") || f.getCode().equalsIgnoreCase("u")) && f.getName() != null && f.getName().length() > 0){
					result = result + f.getName() + " ";
				}
				else{
					//System.out.println("No usable name found, index = " + index);
					if(f == null){
						Ref_FirstName f1 = new Ref_FirstName();
						f1.setOriginal(name);
						f1.setCode("x");
						f1.setNeedSave(true);
						Ref.addFirstName(f1);
					}	
					if(firstNameMesCount++ < messageLimit)
						Utils.message(errorBase + errCode, idnr, year, db, tbl, name);
					result = result + name + " ";
				}
			}
			return result.trim();
		}
		return fnames;

	}

	public static String familyname_r(String name, int errCode, int idnr, int year, String db, String tbl){

		final int errorBase = 200100; 
		
		if(name != null && name.trim().length() != 0){
			Ref_FamilyName f = Ref.getFamilyName(name);

			if(f != null  && f.getCode() != null && (f.getCode().equalsIgnoreCase("y") || f.getCode().equalsIgnoreCase("u")) && f.getName() != null && f.getName().length() > 0){
				return f.getName();
			}
			else{
				//System.out.println("No usable name found, index = " + index);
				if(f == null){
					Ref_FamilyName f1 = new Ref_FamilyName();
					f1.setOriginal(name);
					f1.setNeedSave(true);
					f1.setCode("x");
					Ref.addFamilyName(f1);
				}
				if(famNameMesCount++ < messageLimit)
					Utils.message(errorBase + errCode, idnr, year, db, tbl, name);
				
				return name;
			}
		}	 
		else
			return name;
	}

	public static String prefix_r(String prefix, int errCode, int idnr, int year, String db, String tbl){

		
		final int errorBase = 200100; 

		if(prefix != null && prefix.trim().length() != 0){
			Ref_Prefix p = Ref.getPrefix(prefix);

			if(p != null  && p.getCode() != null && (p.getCode().equalsIgnoreCase("y") || p.getCode().equalsIgnoreCase("u")) && p.getPrefix() != null && p.getPrefix().length() > 0){
				return p.getPrefix();
			}
			else{
				if(p == null){
					Ref_Prefix p1 = new Ref_Prefix();
					p1.setOriginal(prefix);
					p1.setNeedSave(true);
					p1.setCode("x");
					Ref.addPrefix(p1);
				}
				if(prefixMesCount++ < messageLimit){
					Utils.message(errorBase + errCode, idnr, year, db, tbl);
				}
				return prefix;
			}
		}	    	    
		else
			return prefix;
	}

	public static String profession_r(String profession, int errCode, int idnr, int year, String db, String tbl){

		final int errorBase = 200300; 

		if(profession != null && profession.trim().length() != 0){
			Ref_Profession p = Ref.getProfession(profession);
			//System.out.println("Profession = " + p);
			if(p != null  && p.getCode() != null && (p.getCode().equalsIgnoreCase("y") || p.getCode().equalsIgnoreCase("u")) && p.getProfession() != null && p.getProfession().length() > 0){
				return p.getProfession();
			}
			else{
				if(p == null){
					//System.out.println("Adding profession");
					Ref_Profession p1 = new Ref_Profession();
					p1.setOriginal(profession);
					p1.setCode("x");
					p1.setNeedSave(true);
					Ref.addProfession(p1);
				}
				if(professionMesCount++ < messageLimit)
					Utils.message(errorBase + errCode, idnr, year, db, tbl);
				
				return profession;

			}
		}	    	    
		else
			return profession;
	}

	public static String location_r(String location, int errCode, int idnr, int year, String db, String tbl){

		
		final int errorBase = 200400; 


		if(location != null && location.trim().length() != 0){
			Ref_Location l = Ref.getLocation(location);

			if(l != null  && l.getStandardCode() != null && (l.getStandardCode().equalsIgnoreCase("y") || l.getStandardCode().equalsIgnoreCase("u")) && l.getLocation() != null && l.getLocation().length() > 0){
				return l.getLocation();
			}
			else{
				if(l == null){
					Ref_Location l1 = new Ref_Location();
					l1.setOriginal(location);
					l1.setStandardCode("x");
					l1.setNeedSave(true);
					Ref.addLocation(l1);
				}
				if(locationMesCount++ < messageLimit)
					Utils.message(errorBase + errCode, idnr, year,  db, tbl, location);
				
				return location;
				
			}
		}	    	    
		else
			return location;
	}

	public static iisg.nl.hsnnieuw.A1 location_r2(String location, int errCode, int idnr, int year, String db, String tbl){

	// This routine will process addresses that are a mix of Places and Addresses

		
		if(location == null) return null;
		
		if(location.split(" ").length == 1){
			
			Ref_Location l = Ref.getLocation(location);
			
			if(l != null  && l.getStandardCode() != null && (l.getStandardCode().equalsIgnoreCase("y"))){ 
			
				iisg.nl.hsnnieuw.A1 a1 = new iisg.nl.hsnnieuw.A1();
			
				a1.setMunicipality(l.getMunicipality());
				a1.setMunicipalityNumber(l.getLocationNo() + "");
				
				a1.setInstitution(location); // For test, save input in output table
				
				return a1;
			
			}
			
		}
		else{
			location_r(location, errCode, idnr,  year, db, tbl);
			
		}
		
		return null;
		
		
	}
	public static String[] splitField(String name){
   	 
		//System.out.println("name = " + name1);
		
		//System.out.println("Splitfield, name =  " + name1);
    	 
 		String prefix = null;   // separator = ","
 		String title = null;    // separator = "!"	    
 		String patronym = null;	// separator = "$"    
 		String remainder = null;

 		if(name != null){
 	    	name = name.split("%")[0];

 			if(name.split(",").length > 1){
 				remainder = name.split(",")[1];
 				name = name.split(",")[0];
 				if(remainder.split("\\$").length > 1){
 					prefix = remainder.split("\\$")[0];	    		
 					remainder = remainder.split("\\$")[1];
 					if(remainder.split("!").length > 1){
 						patronym =  remainder.split("!")[0];
 						title = remainder.split("!")[1];
 					}
 					else
 						patronym = remainder;
 				}
 				else{
 					if(remainder.split("!").length > 1){
 	 					prefix = remainder.split("!")[0];	    		
 						title = remainder.split("!")[1];
 					}
 					else
 						prefix = remainder;
 				}
 			}
 			else{
 				if(name.split("\\$").length > 1){
 					remainder = name.split("\\$")[1];    	
 					name = name.split("\\$")[0];
 	 				if(remainder.split("!").length > 1){
 	 					patronym = remainder.split("!")[0];
 	 					title = remainder.split("!")[1];
 	 				}
 	 				else
 	 					patronym = remainder;
 				}
 				else{
 	 				if(name.split("!").length > 1){
 	 					title = name.split("!")[1];
 	 					name = name.split("!")[0];
 	 				}
 	 				else;
 				}
 			}
 		}
    	 
    	String[] a = new String[3];

    	if(name != null){
    		a[0] = name.trim();
    		if(a[0].length() > 50)
    			a[0] = a[0].substring(0,50);
    	}

    	if(prefix != null){
    		a[1] = prefix.trim();
    		if(a[1].length() > 10)
    			a[1] = a[1].substring(0,10);
    	}

    	if(title != null){
    		a[2] = title.trim();
    		if(a[2].length() > 15)
    			a[2] = a[2].substring(0,15);
    	}

    	//System.out.println("name = " + name + " prefix = " + prefix  + " title = " + title + " patronym = " + patronym);
    	return a;
    	 
     }
}
