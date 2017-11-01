package iisg.hsn.messages;

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
@Table(name="bfout9ft")
public class Message {
	@Id@Column(name = "RECORDID")  private int      recordID;   


	@Column(name = "IDNR")      	private	int    IDNR;   
	@Column(name = "FTCODE") 	    private	int    errorNumber;   
	@Column(name = "FTTYPE")	    private	String errorType;
	@Column(name = "MESSAGE")          private String errorText;

	private static List<MessageSkeleton> messageSkeletons = null;
	private static List<Message> messages = new ArrayList();


	static{
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hsn_ids_mes");
		EntityManager em = factory.createEntityManager();

		Query q = em.createQuery("select ms from MessageSkeleton ms");
		messageSkeletons = q.getResultList();  
		System.out.println("====> Message Type = " + q.getResultList().get(0).getClass());

		em.close();

	}

	Message(){}
	public Message(String errorNumber){		
		setErrorNumber(new Integer(errorNumber).intValue());
	}


	/**
	 * the message skeleton retrieved based on errorNumber
	 * the fills are merged into the message skeleton
	 * the result is stored in errorText b
	 * the Message object (this)  is written to the table "meldingen"  
	 * 
	 */

	public void save(String... fills){

		
		ArrayList<String>  errorFills = new ArrayList<String>();	

		for (String s : fills) 
			errorFills.add(s);		
	
		
		boolean found = false;
		for(MessageSkeleton ermes: messageSkeletons){

			int errorN = new Integer(ermes.getErrorNumber()).intValue();			
			if(getErrorNumber() == errorN){
				found = true;
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
		if(!found){
			int x = 0;
			int y = 1 / x; // force exception
			
		}
	}


	public static void finalise(){


		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hsn_ids_mes");
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		
		for(Message m: messages)
			em.persist(m);

		messages.clear();
		
		em.getTransaction().commit();
        em.clear();
		em.close();

	}
	
	public static void initialise(){


		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hsn_ids_mes");
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		
		String create_bfout9ft = 
				
		        "CREATE TABLE IF NOT EXISTS hsn_msg.bfout9ft" +
		    	        "(" +
		    	        "    IDNR     INT, " +
		    	        "    FTCODE   SMALLINT, " +
		    	        "    FTTYPE   CHAR(1), " +
		    	        "    MESSAGE  VARCHAR(256), " +
		    	        "    RecordID INT NOT NULL AUTO_INCREMENT, " +
		    	        "    Primary Key(RecordID)" +
		    	        ");";
		
		
		
		
		Query query = em.createNativeQuery(create_bfout9ft);  
		query.executeUpdate();
		
		query = em.createNativeQuery("truncate table hsn_msg.bfout9ft");  
		query.executeUpdate();
		
		em.getTransaction().commit();
        em.clear();
		em.close();

	}
	
	
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
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
	
	
	public int getIDNR() {
		return IDNR;
	}
	public void setIDNR(int iDNR) {
		IDNR = iDNR;
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

