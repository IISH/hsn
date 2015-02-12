/*
* Naam:    MessageSkeleton
* Version: 0.1
* Author:  Cor Munnik
* Copyright
*/

package nl.iisg.ids03;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
/**
 * 
 * This class contains one error message, without the fills
 *
 */

@Entity
@Table(name="ref_meldingen_br")
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
