package iisg.hsn.messages;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ref_meldingen_ids")
public class MessageSkeleton {
	@Id @Column(name = "Foutnr_nw")   private		String errorNumber;
	    @Column(name = "Fouttype_nw") private		String errorType;
	    @Column(name = "Inhoud")      private       String errorText;


	public String getErrorNumber() {
		return errorNumber;
	}
	public void setErrorNumber(String errorNumber) {
		this.errorNumber = errorNumber;
	}
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	public String getErrorText() {
		return errorText;
	}
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

}
