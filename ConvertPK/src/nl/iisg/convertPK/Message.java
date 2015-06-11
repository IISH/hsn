/*
 * Naam:    Message
 * Version: 0.1
 * Author:  Cor Munnik
 * Copyright
 */


package nl.iisg.convertPK;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class contains one error message, with the fills applied
 *
 */

@Entity
@Table(name="bfout7ft")
public class Message {
	@Column(name = "IDNR")      private int      keyToRP;   

	@Column(name = "B1IDBG")  	private int   	 keyToSourceRegister; 
	
	@Column(name = "B2DIBG")    private int      dayEntryHead;
	@Column(name = "B2MIBG")    private int      monthEntryHead; 
	@Column(name = "B2JIBG")    private int      yearEntryHead; 

	@Column(name = "B2FDBG")    private int      dayEntryRP;
	@Column(name = "B2FMBG")    private int      monthEntryRP;
	@Column(name = "B2FJBG")    private int      yearEntryRP;
	
	@Column(name = "B2RNBG")    private int      keyToRegistrationPersons;
	@Column(name = "B2FCBG")    private int      natureOfPerson;

	@Id @Column(name = "FTKODE") 	private	int    errorNumber;
	@Column(name = "FTTYPE")	    private	String errorType;
	@Column(name = "FOUT")          private String errorText;

	private static List<MessageSkeleton> messageSkeletons = null;
	private static List<Message> messages = new ArrayList();


	static{
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pk_ref_tables_mes");
		EntityManager em = factory.createEntityManager();

		Query q = em.createQuery("select ms from MessageSkeleton ms");
		messageSkeletons = q.getResultList();  
		System.out.println("====> Message Type = " + q.getResultList().get(0).getClass());

		em.close();

	}

	Message(){}
	Message(String errorNumber){		
		setErrorNumber(new Integer(errorNumber).intValue());
	}


	/**
	 * the message skeleton retrieved based on errorNumber
	 * the fills are merged into the message skeleton
	 * the result is stored in errorText
	 * the Message object (this)  is written to the table "meldingen"  
	 * 
	 */

	public void save(String... fills){

		
		ArrayList<String>  errorFills = new ArrayList<String>();	

		for (String s : fills) 
			errorFills.add(s);		
	
		for(MessageSkeleton ermes: messageSkeletons){

			int errorN = new Integer(ermes.getErrorNumber()).intValue();			
			if(getErrorNumber() == errorN){
				setErrorType(ermes.getErrorType());
				String errortxt = ermes.getErrorText();
				
				errortxt = errortxt.replaceAll("->", "~~");  // get rid of ->, use ~~ (which is hopefully not in the input)
				errortxt = errortxt.replaceAll("<1", "~@");  // get rid of <1, use ~@ (which is hopefully not in the input)

				String errorout = "";
				int index = 0;

				String[] element = errortxt.split("[<>]");       // split the string on '<' and '>'

				for(int i = 0; i < element.length; i++){
					if(i % 2 == 0){                              // the even elements are outside the <....>    
						errorout += element[i];                  // so they must be copied 'as-is'
					}
					else{                                        // the odd elements are inside the <....> 
						if(index < errorFills.size()){           // if there (still) are fills
							errorout += errorFills.get(index);   // we use the fill 
							index++;
						}
						else
							errorout += "????";                  // we use a dummy value
					}
				}
				errorout = errorout.replaceAll("~~", "->");  // get rid of ~~, back to ->
				errorout = errorout.replaceAll("~@", "<1");  // get rid of ~@, back to <1
				setErrorText(errorout);
				//if(errorN >= 4000){
				//	System.out.println();
				//	String key = String.format("%06d  %02d-%02d-%04d  %06d", getKeyToRP(), getDayEntryHead(), getMonthEntryHead(), getYearEntryHead(), getKeyToSourceRegister());
				//	System.out.println(key + "     [" + getErrorNumber() + "   " + getErrorType() + "  " + errorout.trim() + "]");
				//}
				messages.add(this); 


				//EntityManagerFactory factory = Persistence.createEntityManagerFactory("my_unit");
				//EntityManager em = factory.createEntityManager();

				//em.getTransaction().begin();
				//em.persist(this);
				//em.getTransaction().commit();
				//em.close();
				break;
			}
		}
	}


	public static void finalise(){


		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pkmessages");
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		
		for(Message m: messages)
			em.persist(m);

		messages.clear();
		
		em.getTransaction().commit();
        em.clear();
		em.close();

	}
	public int getKeyToRP() {
		return keyToRP;
	}
	public void setKeyToRP(int keyToRP) {
		this.keyToRP = keyToRP;
	}
	public int getKeyToSourceRegister() {
		return keyToSourceRegister;
	}
	public void setKeyToSourceRegister(int keyToSourceRegister) {
		this.keyToSourceRegister = keyToSourceRegister;
	}
	public int getDayEntryHead() {
		return dayEntryHead;
	}
	public void setDayEntryHead(int dayEntryHead) {
		this.dayEntryHead = dayEntryHead;
	}
	public int getMonthEntryHead() {
		return monthEntryHead;
	}
	public void setMonthEntryHead(int monthEntryHead) {
		this.monthEntryHead = monthEntryHead;
	}
	public int getYearEntryHead() {
		return yearEntryHead;
	}
	public void setYearEntryHead(int yearEntryHead) {
		this.yearEntryHead = yearEntryHead;
	}
	public int getDayEntryRP() {
		return dayEntryRP;
	}
	public void setDayEntryRP(int dayEntryRP) {
		this.dayEntryRP = dayEntryRP;
	}
	public int getMonthEntryRP() {
		return monthEntryRP;
	}
	public void setMonthEntryRP(int monthEntryRP) {
		this.monthEntryRP = monthEntryRP;
	}
	public int getYearEntryRP() {
		return yearEntryRP;
	}
	public void setYearEntryRP(int yearEntryRP) {
		this.yearEntryRP = yearEntryRP;
	}
	public int getKeyToRegistrationPersons() {
		return keyToRegistrationPersons;
	}
	public void setKeyToRegistrationPersons(int keyToRegistrationPersons) {
		this.keyToRegistrationPersons = keyToRegistrationPersons;
	}
	public int getNatureOfPerson() {
		return natureOfPerson;
	}
	public void setNatureOfPerson(int natureOfPerson) {
		this.natureOfPerson = natureOfPerson;
	}
	public int getErrorNumber() {
		return errorNumber;
	}
	public void setErrorNumber(int errorNumber) {
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
	public static List<MessageSkeleton> getMessageSkeletons() {
		return messageSkeletons;
	}
	public static void setMessageSkeletons(List<MessageSkeleton> messageSkeletons) {
		Message.messageSkeletons = messageSkeletons;
	}
	public static List<Message> getMessages() {
		return messages;
	}
	public static void setMessages(List<Message> messages) {
		Message.messages = messages;
	}




    
    

}
