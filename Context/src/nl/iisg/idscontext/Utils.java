package nl.iisg.idscontext;

public class Utils {
	
	static int Id_C;
	static int Old_id_C;

	public static int getId_C() {
		
		//int x = 1/0; 
	
		return Id_C++;
	}

	public static void setId_C(int id_C) {
		Id_C = id_C;
	}

	public static int getOld_id_C() {
		return Old_id_C;
	}

	public static void setOld_id_C(int old_id_C) {
		Old_id_C = old_id_C;
	}
	
	
}
