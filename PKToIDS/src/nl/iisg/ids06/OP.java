package nl.iisg.ids06;

import java.util.ArrayList;


public class OP {
    private int                                     keyToRP;
    private ArrayList<B4_ST>                        RegistrationsStandardizedOfOP = new ArrayList<B4_ST>();

    OP(){}
    
    OP(int key){
    	
    	setKeyToRP(key);
    	
    }

	public int getKeyToRP() {
		return keyToRP;
	}

	public void setKeyToRP(int keyToRP) {
		this.keyToRP = keyToRP;
	}

	public ArrayList<B4_ST> getRegistrationsStandardizedOfOP() {
		return RegistrationsStandardizedOfOP;
	}

	public void setRegistrationsStandardizedOfOP(
			ArrayList<B4_ST> registrationsStandardizedOfOP) {
		RegistrationsStandardizedOfOP = registrationsStandardizedOfOP;
	}
    
    

}
