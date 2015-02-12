package iisg.nl.hsnlog;

import iisg.nl.hsnmigrate.Const;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="log")
public class Log {

     @Column(name="DB")          private String db;
     @Column(name="Tbl")         private String tbl;
     @Column(name="IDNR")        private int idnr;
     @Column(name="Year")        private int year;
     @Column(name="Errcode")     private int errcode;
     @Column(name="Message")     private String message;
     @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
     @Column(name="RecordID")    private int recordID; 
	
	 //@Transient private static List<Log> messages = new ArrayList<Log>();
	 @Transient private static List<MessageSkeleton> messageSkeletons = null;
	 @Transient private static EntityManagerFactory factory = null;
	 @Transient private static EntityManager em;
	 @Transient private static int numberOfMessages = 0;

	 
	 static {

		 
		    factory = Persistence.createEntityManagerFactory("log");
		    em = factory.createEntityManager();

			Query q = em.createQuery("select ms from MessageSkeleton ms");
			messageSkeletons = q.getResultList();
			
			int numberOfMessages = 0;
			em.getTransaction().begin();

	}	
	

public Log(){}
public Log (int errno){
	
	setErrcode(errno);
}



public void save1(){
	
	
	db= "db1";
	tbl = "tabje";
	message = "mmmmmmmmmmmmmm";
	

	setIdnr(5);
	setErrcode(8);
	
	em.getTransaction().begin();
	//em.persist(this);
	//em.getTransaction().commit();
	
}

public void save(int Idnr, int year, String... fills){  // allowed fills: database name, table name and one string field

	ArrayList<String>  errorFills = new ArrayList<String>();	

	for (String s : fills) 
		errorFills.add(s);	
	
	
	for(MessageSkeleton ms:  messageSkeletons){
		if(ms.getErrorNumber() == getErrcode() && ms.getNumberOfMessages() < 2000){
			ms.setNumberOfMessages(ms.getNumberOfMessages() + 1);
			setMessage(ms.getErrorText()); 
			break;
		}
	}
			
	if(getMessage() == null)
		return; 
	
	setIdnr(Idnr);
	setYear(year);

	if(errorFills.size() > 0)
		setDb(errorFills.get(0));
	if(errorFills.size() > 1)
		setTbl(errorFills.get(1));
	if(errorFills.size() > 2)
		setMessage(getMessage() + ": " + errorFills.get(2));
		
	if(getMessage().length() > Const.XBigstring){
		String s = getMessage().substring(0, Const.XBigstring);
		setMessage(s);
	}
	em.persist(this);
}

public static void finalise(){

	System.out.println("Start saving message table");
	//System.out.println("Total number of Messages: " + numberOfMessages);
	
	em.getTransaction().commit();
	em.clear();
	System.out.println("Finished saving message table");
	em.getTransaction().begin(); // for next run
	numberOfMessages = 0;
	//em.close();
}



public String getDb() {
	return db;
}
public void setDb(String db) {
	this.db = db;
}
public String getTbl() {
	return tbl;
}
public void setTbl(String tbl) {
	this.tbl = tbl;
}
public int getIdnr() {
	return idnr;
}
public void setIdnr(int idnr) {
	this.idnr = idnr;
}
public int getErrcode() {
	return errcode;
}
public void setErrcode(int errcode) {
	this.errcode = errcode;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public int getRecordID() {
	return recordID;
}
public void setRecordID(int recordID) {
	this.recordID = recordID;
}
public int getYear() {
	return year;
}
public void setYear(int year) {
	this.year = year;
}
 

 
}