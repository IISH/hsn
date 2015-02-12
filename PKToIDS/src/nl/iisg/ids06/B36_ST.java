 package nl.iisg.ids06;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;

@Entity
@Table(name="b36_st")
public class B36_ST extends B3_ST {
	
	@Column(name = "ORIGIN_ID")     private int         originID;
	@Column(name = "ORIGIN_ST")     private String      originStandardized;
	@Column(name = "ORIGIN_FG")     private int         originFlag;
	
	@Column(name = "ORIGIN_EQUAL")  private int         originGroup;


	@Column(name = "ADDRESS")       private String      address;
	@Column(name = "REGISTER")      private String      register;
	@Column(name = "CENSUS")        private String      census;
	
	
	
	B36_ST(){}
	

	public int getOriginID() {
		return originID;
	}

	public void setOriginID(int originID) {
		this.originID = originID;
	}

	public String getOriginStandardized() {
		return originStandardized;
	}

	public void setOriginStandardized(String originStandardized) {
		this.originStandardized = originStandardized;
	}

	public int getOriginFlag() {
		return originFlag;
	}

	public void setOriginFlag(int originFlag) {
		this.originFlag = originFlag;
	}

	public int getOriginGroup() {
		return originGroup;
	}

	public void setOriginGroup(int originGroup) {
		this.originGroup = originGroup;
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
