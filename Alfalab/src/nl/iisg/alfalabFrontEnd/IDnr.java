package nl.iisg.alfalabFrontEnd;

import nl.iisg.convertPK.*;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="DELETEFROMDEFDB")
public class IDnr {

	 @Id @Column(name="IDNR")       private int     idnr; 		  // ID Number

	// No-arguments constructor is necessary

    public IDnr(){
	    	
    }

    public IDnr(Integer i){
	    	setIdnr(i);
    }

	    
	public int getIdnr() {
		return idnr;
	}
	public void setIdnr(int idnr) {
		this.idnr = idnr;
	}

	 
}