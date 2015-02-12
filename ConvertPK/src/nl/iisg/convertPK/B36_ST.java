package nl.iisg.convertPK;

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
	
	/**
	 * 
	 * This routine truncates fields that are too long for the corresponding database columns
	 * 
	 */
	

	public void truncate(){	
		
		String field = getOriginStandardized();
		int allowedSize = TableDefinitions.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B36_ST", "ORIGIN_ST", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setOriginStandardized(field);
		}

		field = getAddress();
		allowedSize = TableDefinitions.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B36_ST", "ADDRESS", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setAddress(field);
		}

		field = getRegister();
		allowedSize = TableDefinitions.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B36_ST", "REGISTER", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setRegister(field);
		}

		field = getCensus();
		allowedSize = TableDefinitions.Bigstring;
		if(field != null && field.length() > allowedSize){
			message("1500", "B36_ST", "CENSUS", "" + allowedSize);
			field = field.substring(0, allowedSize);
			setCensus(field);
		}


	}


	
	public void save(EntityManager em){
		
		em.persist(this);
		
	}

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
