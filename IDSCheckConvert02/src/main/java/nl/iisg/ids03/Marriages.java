package nl.iisg.ids03;

public class Marriages {
	
	PersonStandardized head;
	PersonStandardized spouse;
	
	String startDate;
	String endDate;
	
	public void print(){
		System.out.format("%s        %s          %d married to %d%n", getStartDate(), getEndDate(), getHead().getKeyToPersons(), getSpouse().getKeyToPersons());
	}
	
	public PersonStandardized getHead() {
		return head;
	}
	public void setHead(PersonStandardized head) {
		this.head = head;
	}
	public PersonStandardized getSpouse() {
		return spouse;
	}
	public void setSpouse(PersonStandardized spouse) {
		this.spouse = spouse;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	} 
	
	

}
