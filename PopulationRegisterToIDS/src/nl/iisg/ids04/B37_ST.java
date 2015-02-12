package nl.iisg.ids04;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;

import nl.iisg.idscontext.ContextElement;
import nl.iisg.idscontext.Contxt;

@Entity
@Table(name="b37_st")
public class B37_ST extends B3_ST{
	
	@Column(name = "DESTINATION_ID")  private int         destinationID;
	@Column(name = "DESTINATION_ST")  private String      destinationStandardized;
	@Column(name = "DESTINATION_FG")  private int         destinationFlag;
	
	@Column(name = "DESTINATION_EQUAL")  private int      destinationGroup;


	@Column(name = "ADDRESS")       private String      address;
	@Column(name = "REGISTER")      private String      register;
	@Column(name = "CENSUS")        private String      census;
	
	B37_ST(){}
	
	public void convert(EntityManager em){
		
		int day   = 0;
		int month = 0;
		int year  = 0;
		

		if(getStartDate() != null){

			day   = (new Integer(getStartDate().substring(0,2))).intValue();
			month = (new Integer(getStartDate().substring(3,5))).intValue();
			year  = (new Integer(getStartDate().substring(6,10))).intValue();
		}


		Utils.addIndiv(em, getKeyToRP(), getPersonStandardizedToWhomDynamicDataRefers().getPersonID(), "B37_ST", "DEPARTURE_TO", getDestinationStandardized(), day, month, year);
		
		ContextElement ce = Contxt.get2(getDestinationStandardized());		
		if(ce != null)
			Utils.addIndivAndContext(null, ce, em, getKeyToRP(), getPersonStandardizedToWhomDynamicDataRefers().getPersonID(), "B37_ST",  "DEPARTURE_TO", day, month, year);

	}




	public int getDestinationID() {
		return destinationID;
	}

	public void setDestinationID(int destinationID) {
		this.destinationID = destinationID;
	}

	public String getDestinationStandardized() {
		return destinationStandardized;
	}

	public void setDestinationStandardized(String destinationStandardized) {
		this.destinationStandardized = destinationStandardized;
	}

	public int getDestinationFlag() {
		return destinationFlag;
	}

	public void setDestinationFlag(int destinationFlag) {
		this.destinationFlag = destinationFlag;
	}

	public int getDestinationGroup() {
		return destinationGroup;
	}

	public void setDestinationGroup(int destinationGroup) {
		this.destinationGroup = destinationGroup;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getCensus() {
		return census;
	}

	public void setCensus(String census) {
		this.census = census;
	}
	
	


}
