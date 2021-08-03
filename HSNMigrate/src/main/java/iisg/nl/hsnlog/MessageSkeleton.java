package iisg.nl.hsnlog;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Transient;
/**
 * 
 * This class contains one error message, without the fills
 *
 */

@Entity
@Table(name="ref_meldingen_bs")
public class MessageSkeleton {
	@Id @Column(name = "ID")            private		int       id;
	@Column(name = "Foutnr")            private		int       errorNumber;
    @Column(name = "Fout_omschrijving") private	    String    errorText;
    
    @Transient 							private     int       numberOfMessages;
    
    
    
	public int getErrorNumber() {
		return errorNumber;
	}
	public void setErrorNumber(int errorNumber) {
		this.errorNumber = errorNumber;
	}
	public String getErrorText() {
		return errorText;
	}
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumberOfMessages() {
		return numberOfMessages;
	}
	public void setNumberOfMessages(int numberOfMessages) {
		this.numberOfMessages = numberOfMessages;
	}



}