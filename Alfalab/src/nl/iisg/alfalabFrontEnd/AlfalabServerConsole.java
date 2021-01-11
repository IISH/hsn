package nl.iisg.alfalabFrontEnd;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.text.html.parser.Entity;

import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFWriter;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import iisg.nl.hsnmigrate.StandardizeCivilCertificates;
import nl.iisg.convertPK.StandardizePersonalCards;
import nl.iisg.hsncommon.ConstRelations2;
import nl.iisg.hsncommon.DBFHandler;
import nl.iisg.hsnlinks.HSNLinksIntegration;
import nl.iisg.ids.IDS;
import nl.iisg.ids03.*;
import nl.iisg.ids04.PopulationRegisterToIDS;
import nl.iisg.ids05.CivilCertificatesToIDS;
import nl.iisg.ids06.PKToIDS;
import nl.iisg.ids_init.IDS_INIT;
import nl.iisg.ref.*;
import word.api.interfaces.IDocument;
import word.w2004.Document2004;

public class AlfalabServerConsole implements Runnable {
	//
    private final static String GET_MESSAGE_QUEUE_COMMAND = "getMessageQueue";
    private final static String GET_DATE_COMMAND = "getDate";
    private final static String SEND_FILE_COMMAND = "sendFile";
    private final static String SERVER_EXIT_COMMAND = "exit";
    private static final String FILE_TYPE_DBF = "FILE_TYPE_DBF";
    private static final String FILE_TYPE_PRINTFILE = "FILE_TYPE_PRINTFILE";

    private final static String SERVER_CANCEL = "serverCancel";

    private final static String POP_REG_TEST_ERRORS = "popRegTestErrors";    	
    private final static String POP_REG_DELETE_FROM_DEF_DB = "popRegDeleteFromDefDB";
    private final static String POP_REG_APPEND_TO_DEF_DB = "popRegAppendToDefDB";
    private final static String POP_REG_TEST_IDNR_DOUBLES = "popRegTestIDnrDoubles";
    private final static String POP_REG_REPLACE_DEF_WITH_TEMP = "popRegReplaceDefWithTemp";
    private final static String POP_REG_TO_IDS = "popRegToIDS";
    private final static String PERSONAL_CARDS_CONVERSION = "personalCardsConversion";
    private final static String PERSONAL_CARDS_TO_IDS = "personalCardsToIDS";
    private final static String CIVIL_CERTS_CONVERSION = "civilCertsConversion";
    private final static String CIVIL_CERTS_TO_IDS= "civilCertsToIDS";
    private final static String BUILD_NEW_HSN = "buildNewHSN";
    private final static String BUILD_EXTRACTION_SET_HSN_STANDARD = "buildExtractionSetHSNStandard";
    private final static String BUILD_EXTRACTION_SET_MIGRATION_FILE = "buildExtractionSetMigrationFile";
    private final static String MISC_PRINT_HSN_POP_REG_ERRORS = "miscPrintHSNPopRegErrors";
    private final static String MISC_PRINT_HSN_POP_REG_ALL = "miscPrintHSNPopRegAll";
    private final static String MISC_PRINT_HSN_POP_REG_SELECTED = "miscPrintHSNPopRegSelected";
    private final static String RESET_TEMP_DIR = "resetTempDir";
    private final static String ADD_LINKS_DATA= "addLinksData";
    private final static String INITIALISE_HSN = "initialiseHSN";
    private final static String LOGIN = "login";

    //        private final static String INPUT_DIRECTORY = "C:\\Users\\cro\\Documents\\temp";
    //private final static String INPUT_DIRECTORY = "H:\\My Documents\\Eclipse\\Alfalab\\temp";
    private final static String INPUT_DIRECTORY = "temp";
    //public final static String INPUT_DIRECTORY = "temp";

    private final static int MAX_FILE_SIZE = 200 * 1024 * 1024; // = 100 Megabytes
    private final static int NR_OF_COLUMNS = 2;
    private final static int COLUMN_WIDTH = 50;
    
    private final static int portNo = 8009;

    private File latestReceivedFile;

    ServerSocket server = null;
    Socket client = null;
    DataInputStream in = null;
    DataOutputStream out = null;
    String line;

    EntityManagerFactory factory_original_tabs;
    EntityManager emBtables;

    Thread uiThread;
    Thread workerThread;
    Boolean flushInProgress = false;
    AlfalabServerConsole asc;  // link to the user interface AlfalabServerConsole 
    
  

    public AlfalabServerConsole(Socket socket) {

    	client = socket;

    }

    public AlfalabServerConsole() {

    	System.out.println("Here!");
    	Charset charset = Charset.defaultCharset();
        System.out.println("Default encoding: " + charset + " (Aliases: "
            + charset.aliases() + ")");
        //factory_original_tabs = Persistence.createEntityManagerFactory("b_tables");
    	System.out.println("factory_original_tabs = " + factory_original_tabs);
        //emBtables = factory_original_tabs.createEntityManager();

        //textArea.getDocument().addDocumentListener(this);
    }

    public static void main(String[] args) {

        AlfalabServerConsole alfalabServerConsole = new AlfalabServerConsole();
        alfalabServerConsole.start();

    }

    public void start() {
/*
        try {
            dispatchCommand(MISC_PRINT_HSN_POP_REG_ERRORS);
//            dispatchCommand(MISC_PRINT_HSN_POP_REG_SELECTED);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
*/
//        EntityManagerFactory factory_tabs = Persistence.createEntityManagerFactory("bdated_tables");
//        EntityManager em_tabs = factory_tabs.createEntityManager();

        listenSocket();        
        
    }
    
    public void listenSocket() {
    	
            try {
                server = new ServerSocket(portNo);
                System.out.println("Listening to port " + portNo);
                System.out.println("Started.\n");
            } catch (IOException e) {
                System.out.println("Could not listen on port " + portNo);
                System.exit(-1);
            }

            
		while (true) {
			try {
				Socket client1 = server.accept();
				
				if(uiThread == null || !uiThread.isAlive()){
					if(asc != null && asc.getWorkerThread() != null && asc.getWorkerThread().isAlive()){  // dead uiThread still has live workerThread
		                out = new DataOutputStream(client1.getOutputStream());
		                print("HSN Control Program in use\n");
		                System.out.println("Refused connection");
					}
					else{
						asc = new AlfalabServerConsole(client1);
						uiThread = new Thread(asc);
						uiThread.start();
						client = client1;
						System.out.println("Accept succeeded: " + portNo);
						System.out.println("getReceiveBufferSize = " + client.getReceiveBufferSize());
					}
				}
				else{
					//String hostname = InetAddress.getByName(client.getInetAddress().toString()).getHostName();			    
					String hostname = client1.getInetAddress().toString();
	                out = new DataOutputStream(client1.getOutputStream());
	                print("HSN Control Program in use by " + hostname + "\n");
	                client1 = null;
	                //out.writeUTF("Connection closed\n");
	                //client.close();
					System.out.println("Refused connection");

				}
				
				
			} catch (IOException e) {
				System.out.println("Accept failed: " + portNo);
				System.exit(-1);
			}
			
			

		}
    }

    public void listenSocket2() {        
        while (true) {
            try {
                server = new ServerSocket(portNo);
                System.out.println("Listening to port " + portNo);
                System.out.println("Started.\n");
            } catch (IOException e) {
                System.out.println("Could not listen on port" + portNo);
                System.exit(-1);
            }

            try {
                client = server.accept();
                System.out.println("Accept succeeded:" + portNo);
                System.out.println("getReceiveBufferSize = " + client.getReceiveBufferSize());
            } catch (IOException e) {
                System.out.println("Accept failed: " + portNo);
                System.exit(-1);
            }

            try {
                in = new DataInputStream(client.getInputStream());
                System.out.println("DataInputStream succeeded");

                out = new DataOutputStream(client.getOutputStream());
                System.out.println("DataOutStream succeeded");

            } catch (IOException e) {
                System.out.println("Accept failed: " + portNo);
                System.exit(-1);
            }

            while (true) {
                try {
   
                    System.out.println("Rx: " + line);
                    dispatchCommand(line);
                } catch (Exception e) {
                    e.printStackTrace();
                    //out.writeUTF("Connection with client lost\n");
                    closeSocket();
                    break;
                }
            }
        }
    }


    
    public void run() {
    	
		try {
			in = new DataInputStream(client.getInputStream());
			System.out.println("DataInputStream succeeded");

			out = new DataOutputStream(client.getOutputStream());
			System.out.println("DataOutStream succeeded");

		} catch (IOException e) {
			System.out.println("Accept failed: " + portNo);
			System.exit(-1);
		}

		while (true) {
			try {

				line = in.readUTF();
				System.out.println("Rx: " + line);
				dispatchCommand(line);
			} catch (Exception e) {
				//e.printStackTrace();
				//print("Connection with client lost\n");
				System.out.println("Connection with client lost\n");				
				closeSocket();
				break;
			}
		}

    	
    }

    public void dispatchCommand(String command) throws IOException {

        String response = "";
        
        
        System.out.println("dispatchCommand command = " + command);
        System.out.println("Workerthread = " + workerThread);
        if(workerThread != null)
        	System.out.println("Alive = " + workerThread.isAlive());

        System.out.println("flush in progress =  " + flushInProgress);

        if (flushInProgress || (workerThread != null && workerThread.isAlive())) {

            if (command.equals(SEND_FILE_COMMAND)){
            		flushInProgress = true;  // will remain true until the next command that is not SEND_FILE_COMMAND
            		receiveFileFromClient(false);
            }
            else{
                print("====> Server is busy, ignoring command " + command);
                flushInProgress = false; // Now we can try receiving files again
            }
            		
            

        } else if (command.equals(GET_MESSAGE_QUEUE_COMMAND)) {

          //  print("Connected.\n");
            
        } else if (command.equals(RESET_TEMP_DIR)) {

           deleteTempFiles();
            
        } else if (command.equals(PERSONAL_CARDS_CONVERSION)) {

            //print("Started Standardize Personal Cards. Please wait..\n");
            StandardizePersonalCards standardizePersonalCards = new StandardizePersonalCards(out, INPUT_DIRECTORY);
            workerThread = new Thread(standardizePersonalCards);
            workerThread.start();

        } else if (command.equals(PERSONAL_CARDS_TO_IDS)) {

            //print("Started Personal Card to IDS. Please wait..\n");
            PKToIDS PKToIDS = new PKToIDS(out);
            workerThread = new Thread(PKToIDS);
            workerThread.start();

        } else if (command.equals(CIVIL_CERTS_CONVERSION)) {

            //print("Started Standardize Civil Certificates. Please wait..\n");
//            StandardizePersonalCards standardizePersonalCards = new StandardizePersonalCards(out, INPUT_DIRECTORY);
            StandardizeCivilCertificates standardizeCivilCertificates = new StandardizeCivilCertificates(out, INPUT_DIRECTORY);
            workerThread = new Thread(standardizeCivilCertificates);
            workerThread.start();

        } else if (command.equals(CIVIL_CERTS_TO_IDS)) {

            //print("Started Civil Certificates to IDS. Please wait..\n");
//            StandardizePersonalCards standardizePersonalCards = new StandardizePersonalCards(out, INPUT_DIRECTORY);
            CivilCertificatesToIDS   civilCertificatesToIDS = new CivilCertificatesToIDS(out);
            workerThread = new Thread(civilCertificatesToIDS);
            workerThread.start();

        } else if (command.equals(SEND_FILE_COMMAND)) {

            receiveFileFromClient(true);

        } else if (command.split(" ")[0].equals(POP_REG_TEST_ERRORS)) {

        	String parm = null;
        	if(command.split(" ").length > 1 && command.split(" ")[1] != null)
        		parm = command.split(" ")[1].trim();
        	else
        		parm = INPUT_DIRECTORY;

            //print("Started Standardize Population Register. Please wait..\n");
            StandardizePopulationRegister IDSCheckConvert = new nl.iisg.ids03.StandardizePopulationRegister(out, parm);
            workerThread = new Thread(IDSCheckConvert);
            workerThread.start();

        } else if (command.equals(POP_REG_TO_IDS)) {

            //print("Started Population Register to IDS. Please wait..\n");
            PopulationRegisterToIDS PopulationRegisterToIDS  = new PopulationRegisterToIDS(out);
            workerThread = new Thread(PopulationRegisterToIDS);
            workerThread.start();

        } else if (command.equals(POP_REG_DELETE_FROM_DEF_DB)) {


            print("\nPopulation Register - Delete           started\n");

            File f = new File(INPUT_DIRECTORY + File.separator + "DELETEFROMDEFDB.DBF");
            if (f.exists()) {
                f.delete();
            }

            boolean succeeded = latestReceivedFile.renameTo(new File(INPUT_DIRECTORY + File.separator + "DELETEFROMDEFDB.DBF"));

            if (!succeeded) {
                print("ERROR: Rename of file not succeeded, operation cancelled\n");
                return;
            }

            List<IDnr> idNrList = DBFHandler.createObjects("nl.iisg.alfalabFrontEnd.IDnr", INPUT_DIRECTORY);
            
            
            // Remove duplicates
            
            
            System.out.println("Size idNrList = " + idNrList.size());
            removeDuplicates(idNrList);

            if (idNrList.size() == 0) {
                print("No records found in input file. Delete operation cancelled\n");
                return;
            }
            
            
            // collect all data from MySQL_opslag and create dbf file:
            try {

                if (!createDbfFromDeletedIdnrs(idNrList)) {
                    return;
                }
                // delete the entries in MySQL_opslag:

                deleteFromBTables(idNrList);
                print("\nPopulation Register - Delete           finished\n");

                //deleteTempFiles();

            } catch (Exception e) {
                print("An error occurred while creating a DBF file from the IDnrs. Delete operation cancelled.\n");
                e.printStackTrace();
            }

        } else if (command.equals(POP_REG_APPEND_TO_DEF_DB)) {


            // Check if there aren't duplicate entries first:
        	
            // Create MySQL_opslag table if not exist:
            print("\nPopulation Register - Append           started\n");

        	
            createMySQL_opslagTable();

            try {
                if (checkForDuplicatesB_st()) {
                    print("Warning: duplicates detected in B*_ST tables\nAppend operation cancelled.\n");
                } else if (checkForDuplicatesB()) {
                    print("Warning: duplicates detected B* tables\nAppend operation cancelled.\n");
                } else {
                    print("No duplicates detected");
                    
                    // Start append process:
                    appendToMySQL_opslagTable();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            print("\nPopulation Register - Append           finished\n");


        } else if (command.equals(POP_REG_TEST_IDNR_DOUBLES)) {

            print("\nPopulation Register - Testing for Doubles           started\n");


            // check for duplicates, and create dbf file from found duplicates:
            try {
                if (checkForDuplicatesB_st()) {
                    print("Warning: duplicates detected in B*_ST database!\n");
                    //sendFileToClient(new File(INPUT_DIRECTORY + File.separator + "DOUBLE_IDNRS_B2_ST.DBF"), FILE_TYPE_DBF);
                } else if (checkForDuplicatesB()) {
                    print("Warning: duplicates detected in B* database!\n");
                    //sendFileToClient(new File(INPUT_DIRECTORY + File.separator + "DOUBLE_IDNRS_B2.DBF"), FILE_TYPE_DBF);
                } else {
                    print("No duplicates detected.\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            print("\nPopulation Register - Testing for Doubles           finished\n");


        } else if (command.equals(POP_REG_REPLACE_DEF_WITH_TEMP)) {


            try {
//                createDbfFromMySQLTemp();   // creates deletefromdefdb.dbf
//
//                // delete from MySQL_opslag by IDnr from deletefromdefdb.dbf:
//                List<IDnr> idNrList = Utils.createObjects("nl.iisg.alfalabFrontEnd.IDnr", INPUT_DIRECTORY);
            	
                print("\nPopulation Register - Replace           started\n");


                EntityManager em = nl.iisg.ids03.Utils.getEm_original_tabs();
                Query query = em.createQuery(AppendToOpslag.SELECT_IDNRS_FROM_B2);
                
                List<Integer> iList = query.getResultList();
                List<IDnr> idNrList = new ArrayList<IDnr>();
                for(Integer i: iList)
                	idNrList.add(new IDnr(i));
                
                removeDuplicates(idNrList);                

                               
                if (!createDbfFromDeletedIdnrs(idNrList)) {
                    System.out.println("error");
                    return;
                }
                

                print("Deleting from MySQL_opslag");
                
                deleteFromBTables(idNrList);
                



            } catch (Exception e) {
                e.printStackTrace();
            }

            appendToMySQL_opslagTable();

            print("\nPopulation Register - Replace           finished\n");
            
        } else if (command.length() >= BUILD_NEW_HSN.length() && command.substring(0, BUILD_NEW_HSN.length()).equals(BUILD_NEW_HSN)) {

        	String rest = null;
        	if(command.length() > BUILD_NEW_HSN.length()){
        		
        		rest = command.substring(BUILD_NEW_HSN.length());
            	//System.out.println("Rest: " + rest.trim() + ", length = " + rest.trim().length()); 
        		
        	}
        	
        	IDS ids = new IDS(out);    
        	
        	rest = rest.trim();
        	if(rest.length() > 3)
        		rest = rest.substring(0,3);
            ids.setVersion(rest);
            
            
            workerThread = new Thread(ids);
            workerThread.start();

        } else if (command.length() >= INITIALISE_HSN.length() && command.substring(0, INITIALISE_HSN.length()).equals(INITIALISE_HSN)) {
 
        	String rest = null;
        	if(command.length() > INITIALISE_HSN.length()){
        		
        		rest = command.substring(INITIALISE_HSN.length());
            	//print("Rest: " + rest.trim() + ", length = " + rest.trim().length()); 
        		
        	}
        	
        	
        	IDS_INIT ids_init = new IDS_INIT(out); 
        	ids_init.setVersion(rest);
        	
                        
            workerThread = new Thread(ids_init);
            workerThread.start();

            
        } else if (command.equals(ADD_LINKS_DATA)) {
 
        	
            //print("Started Personal Card to IDS. Please wait..\n");
            HSNLinksIntegration hli = new HSNLinksIntegration(out);
            workerThread = new Thread(hli);
            workerThread.start(); 


        } else if (command.split("[ /]")[0].equals(LOGIN)) {
 
        	String user = command.split("[ /]")[1];        	
        	String pasw = command.split("[ /]")[2];
        	        	          
            System.out.println(System.getProperty("user.dir"));
            Path path = Paths.get(System.getProperty("user.dir") + "\\bin\\META-INF", "pw.txt");
            
            System.out.println(path.toString());
            boolean found = false;
            try {
            	List<String> lines = Files.readAllLines(path);


            	for (String line : lines) {
            		
            		//System.out.println(line);

            		if(user.equals(line.split("/")[0]) && pasw.equals(line.split("/")[1])){
            			found = true;
            			break;

            		}

            		
            	}
            } catch (IOException e) {
            	System.out.println(e);
            }

            if(found) {
            	//print("Login OK");
            	//out.writeUTF("ok");
            }
            else {
            	print("Invalid username and/or password");
            	//out.writeUTF("ko");
            }
            //File f = System.getProperty("user.dir") + "\\bin\\META-INF\\pw.txt";
            
            
            
            //File f = new File()

            //print("Started Personal Card to IDS. Please wait..\n");
            //HSNLinksIntegration hli = new HSNLinksIntegration(out);
            //workerThread = new Thread(hli);
            //workerThread.start(); 



        } else if (command.equals(MISC_PRINT_HSN_POP_REG_ALL)) {
        	
            print("\nPopulation Register - Print All                  started\n");


            Ref.loadAINB();

            EntityManagerFactory factory_original_tabs = Persistence.createEntityManagerFactory("b_tables");
            emBtables = factory_original_tabs.createEntityManager();

            List<Person> persons;
            Query q = emBtables.createQuery("select a from Person a order by a.keyToRegistrationPersons");

            persons = q.getResultList();

            if (persons.size() == 0) {

                print("There is no data in the B2 database to print, printing action cancelled.");
                return;

            }

            List<PersonDynamic> personsDynamic;
            q = emBtables.createQuery("select a from PersonDynamic a");
            personsDynamic = q.getResultList();

            List<Registration> registrations;
            q = emBtables.createQuery("select a from Registration a");
            registrations = q.getResultList();

            List<RegistrationAddress> registrationAddresses;
            q = emBtables.createQuery("select a from RegistrationAddress a");
            registrationAddresses = q.getResultList();

            print("Server creating print file. Please wait...");
            printHSNPopRegPrintAll(persons, personsDynamic, registrations, registrationAddresses);
            //sendFileToClient(new File(INPUT_DIRECTORY + File.separator + "pop_reg_errors_all.txt"), FILE_TYPE_PRINTFILE);

            CreateWordDoc createWordDoc = new CreateWordDoc();
            createWordDoc.generatePopulationRegisterPrintout(new File(INPUT_DIRECTORY + File.separator + "pop_reg_errors_all.txt"), "pop_reg_errors_all.doc");

            sendFileToClient(new File(INPUT_DIRECTORY + File.separator + "pop_reg_errors_all.doc"), FILE_TYPE_PRINTFILE);
            print("\nPopulation Register - Print All                  finished\n");


//            deleteTempFiles();

        } else if (command.equals(MISC_PRINT_HSN_POP_REG_ERRORS)) {
        	
            print("\nPopulation Register - Print Messages           started\n");


            Ref.loadAINB();

            EntityManagerFactory factory_original_tabs = Persistence.createEntityManagerFactory("b_tables");
            emBtables = factory_original_tabs.createEntityManager();

            List<Person> persons;
            Query q = emBtables.createQuery("select a from Person a order by a.keyToRegistrationPersons");
            persons = q.getResultList();

            if (persons.size() == 0) {

                print("There is no data in the B2 database to print, printing action cancelled.");
                return;

            }

            List<PersonDynamic> personsDynamic;
            q = emBtables.createQuery("select a from PersonDynamic a");
            personsDynamic = q.getResultList();

            List<Registration> registrations;
            q = emBtables.createQuery("select a from Registration a");
            registrations = q.getResultList();

            List<RegistrationAddress> registrationAddresses;
            q = emBtables.createQuery("select a from RegistrationAddress a");
            registrationAddresses = q.getResultList();

            print("Server creating print file. Please wait...");
            printHSNPopRegErrors(persons, personsDynamic, registrations, registrationAddresses);
            //print("Printing HSN files Population Registers based on error messages finished.\n");

            CreateWordDoc createWordDoc = new CreateWordDoc();
            createWordDoc.generatePopulationRegisterPrintout(new File(INPUT_DIRECTORY + File.separator + "pop_reg_errors.txt"), "pop_reg_errors.doc");


            sendFileToClient(new File(INPUT_DIRECTORY + File.separator + "pop_reg_errors.doc"), FILE_TYPE_PRINTFILE);
            print("\nPopulation Register - Print Messages           ended\n");

//            deleteTempFiles();

        } else if (command.equals(MISC_PRINT_HSN_POP_REG_SELECTED)) {

            print("\nPopulation Register - Print *Selected* Messages      started\n");

        	
            Ref.loadAINB();

            EntityManagerFactory factory_original_tabs = Persistence.createEntityManagerFactory("b_tables");
            EntityManager emBtables = factory_original_tabs.createEntityManager();


            Query q = emBtables.createQuery("select a from Person a order by a.keyToRegistrationPersons");
            List<Person> persons = q.getResultList();

            if (persons.size() == 0) {

                print("There is no data in the B2 database to print, printing action cancelled.");
                in.readInt();
                return;

            }

            q = emBtables.createQuery("select a from PersonDynamic a");
            List<PersonDynamic> personsDynamic = q.getResultList();

            q = emBtables.createQuery("select a from Registration a");
            List<Registration> registrations = q.getResultList();

            q = emBtables.createQuery("select a from RegistrationAddress a");
            List<RegistrationAddress> registrationAddresses;
            registrationAddresses = q.getResultList();

            int errorLevel = in.readInt();
            System.out.println("Error level: " + errorLevel);

            print("Server creating print file. Please wait...");
            printHSNPopRegPrintSelectedErrors(persons, personsDynamic, registrations, registrationAddresses, errorLevel);

            if (errorLevel == 3) {
                print("Printing HSN files Population Registers Controle A melding 1000-serie Ronde 2 finished.\n");
            } else if (errorLevel == 4) {
                print("Printing HSN files Population Registers Controle B melding 1000-serie Ronde 2 finished.\n");
            } else if (errorLevel == 5) {
                print("Printing HSN files Population Registers Controle A/B melding 1000-serie Ronde 3 finished.\n");
            } else if (errorLevel == 6) {
                print("Printing HSN files Population Registers Controle A melding 4000-serie Ronde 4 finished.\n");
            } else if (errorLevel == 7) {
                print("Printing HSN files Population Registers Controle B melding 4000-serie Ronde 4 finished.\n");
            } else if (errorLevel == 8) {
                print("Printing HSN files Population Registers Controle A/B melding 4000-serie Ronde 5 finished.\n");
            }

            CreateWordDoc createWordDoc = new CreateWordDoc();
            createWordDoc.generatePopulationRegisterPrintout(new File(INPUT_DIRECTORY + File.separator + "pop_reg_errors_selected.txt"), "pop_reg_errors_selected.doc");

            sendFileToClient(new File(INPUT_DIRECTORY + File.separator + "pop_reg_errors_selected.doc"), FILE_TYPE_PRINTFILE);
            
            print("\nPopulation Register - Print *Selected* Messages      ended\n");


        } else {

            response = "Unknown command: " + command + "\n";
            print(response);

        }
    }


    // Returns false if there is nothing to delete.
    private boolean createDbfFromDeletedIdnrs(List<IDnr> idNrList) throws Exception {

        EntityManagerFactory factory_mysql_opslag_org = Persistence.createEntityManagerFactory("MySQL_opslag_org");
        EntityManager emMySQLOpslag = factory_mysql_opslag_org.createEntityManager();

        Query qOpslag;
        
        String whereClause = "(";
        
        int previousKey = -1;
        for (IDnr idnr : idNrList) {
        	if(idnr.getIdnr()!= previousKey){
        		whereClause += idnr.getIdnr();
        		whereClause += ",";
        		previousKey =idnr.getIdnr();
        	}
        }
        whereClause = whereClause.substring(0, whereClause.length() - 1); 
        whereClause += ")";      

        print("Determining IDNRs to delete...");
        
        
        qOpslag = emMySQLOpslag.createQuery(AppendToOpslag.SELECT_BY_IDNRS_FROM_B2 + whereClause);
        System.out.println(AppendToOpslag.SELECT_BY_IDNRS_FROM_B2 + whereClause);
        List<Person> deletedPersons = (List<Person>) qOpslag.getResultList();
        System.out.println("Number of B2 records " + deletedPersons.size());

        

        print("Creating B2.DBF....");


        if (deletedPersons.size() != 0) {        	
        	DBFWriter dbfWriter = new DBFWriter();
            CreateB2Dbf createB2Dbf = new CreateB2Dbf();
            createB2Dbf.createDbf(dbfWriter, deletedPersons);
            FileOutputStream fos  = new FileOutputStream(INPUT_DIRECTORY + File.separator + "B2.DBF");
            dbfWriter.write(fos);
            fos.close();
            sendFileToClient(new File(INPUT_DIRECTORY + File.separator + "B2.DBF"), FILE_TYPE_DBF);
        } else {
            print("No matching IDnr's found in Definitive B2 database\nNo DBF export created\nDelete operation cancelled\n");
            return false;
        }
        
       
        
        print("Creating B3.DBF....");


        qOpslag = emMySQLOpslag.createQuery(AppendToOpslag.SELECT_BY_IDNRS_FROM_B3 + whereClause);
        List<PersonDynamic> personDynamics  = (List<PersonDynamic>) qOpslag.getResultList();
        
        if (personDynamics.size() != 0) {
        	
        	DBFWriter dbfWriter = new DBFWriter();
            CreateB3Dbf createB3Dbf = new CreateB3Dbf();
            createB3Dbf.createDbf(dbfWriter, personDynamics);
            FileOutputStream fos = new FileOutputStream(INPUT_DIRECTORY + File.separator + "B3.DBF");
            dbfWriter.write(fos);
            fos.close();
            sendFileToClient(new File(INPUT_DIRECTORY + File.separator + "B3.DBF"), FILE_TYPE_DBF);
        }
        
        print("Creating B4.DBF....");

        
        qOpslag = emMySQLOpslag.createQuery(AppendToOpslag.SELECT_BY_IDNRS_FROM_B4 + whereClause);
        List<Registration> registrations = (List<Registration>) qOpslag.getResultList();
        
        if (registrations.size() != 0) {
        	DBFWriter dbfWriter = new DBFWriter();
            CreateB4Dbf createB4Dbf = new CreateB4Dbf();
            createB4Dbf.createDbf(dbfWriter, registrations);
            FileOutputStream fos = new FileOutputStream(INPUT_DIRECTORY + File.separator + "B4.DBF");
            dbfWriter.write(fos);
            fos.close();
            sendFileToClient(new File(INPUT_DIRECTORY + File.separator + "B4.DBF"), FILE_TYPE_DBF);
        } 
        
        
        print("Creating B6.DBF....");

        qOpslag = emMySQLOpslag.createQuery(AppendToOpslag.SELECT_BY_IDNRS_FROM_B6 + whereClause);
        List<RegistrationAddress> registrationAddresses = (List<RegistrationAddress>) qOpslag.getResultList();
        
        if (registrationAddresses.size() != 0) {
        	DBFWriter dbfWriter = new DBFWriter();
            CreateB6Dbf createB6Dbf = new CreateB6Dbf();
            createB6Dbf.createDbf(dbfWriter, registrationAddresses);
            FileOutputStream fos = new FileOutputStream(INPUT_DIRECTORY + File.separator + "B6.DBF");
            dbfWriter.write(fos);
            fos.close();
            sendFileToClient(new File(INPUT_DIRECTORY + File.separator + "B6.DBF"), FILE_TYPE_DBF);
        } 
        
        
        return true;

    }

    private void createDbfFromMySQLTemp() throws Exception {

    	System.out.println("In createDbfFromMySQLTemp");

        EntityManagerFactory factory_mysql_opslag = Persistence.createEntityManagerFactory("MySQL_opslag");
        EntityManager emMySQLOpslag = factory_mysql_opslag.createEntityManager();

        Query qTemp;
        List<Person> result;

        DBFWriter dbfWriter = new DBFWriter();
        DBFField dbfFields[] = new DBFField[1];
        dbfFields[0] = new DBFField();
        dbfFields[0].setDataType(DBFField.FIELD_TYPE_N);
        dbfFields[0].setFieldName("IDNR");
        dbfFields[0].setFieldLength(12);
        dbfWriter.setFields(dbfFields);

        qTemp = emMySQLOpslag.createQuery(AppendToOpslag.SELECT_ALL_FROM_B2);
        result = (List<Person>) qTemp.getResultList();
        for (Person person : result) {

            Object rowData[] = new Object[1];
            rowData[0] = person.getKeyToRP();
            dbfWriter.addRecord(rowData);

        }

        FileOutputStream fos = new FileOutputStream(INPUT_DIRECTORY + File.separator + "DELETEFROMDEFDB.DBF");
        dbfWriter.write(fos);
        fos.close();

    }

    private String safeGetContentOfDynamicData1(int contentOfDynamicData) {

        if (contentOfDynamicData > 0) {
            return ConstRelations2.b3kode1[contentOfDynamicData];
        } else {
            return Integer.toString(contentOfDynamicData);
        }

    }

    private String safeGetContentOfDynamicData2(int contentOfDynamicData) {

        if (contentOfDynamicData > 0) {
            return ConstRelations2.b3kode2[contentOfDynamicData].substring(0, 3) + ".";
        } else {
            return Integer.toString(contentOfDynamicData);
        }

    }

    private void createMySQL_opslagTable() {
        try {
            print("Creating tables (if not existing..)");

            
            // The B* tables
            
            EntityManager em = nl.iisg.ids03.Utils.getEm_mysql_opslag_org();
            em.getTransaction().begin();
            Query query = em.createNativeQuery(CreateOriginalBTables.B2);
            query.executeUpdate();
            query = em.createNativeQuery(CreateOriginalBTables.B3);
            query.executeUpdate();
            query = em.createNativeQuery(CreateOriginalBTables.B4);
            query.executeUpdate();
            query = em.createNativeQuery(CreateOriginalBTables.B6);
            query.executeUpdate();

            em.getTransaction().commit();
            em.clear();

            // The B*_ST tables

            em = nl.iisg.ids03.Utils.getEm_mysql_opslag();
            em.getTransaction().begin();
            query = em.createNativeQuery(CreateBTables.B2_ST);
            query.executeUpdate();
            query = em.createNativeQuery(CreateBTables.B311_ST);
            query.executeUpdate();
            query = em.createNativeQuery(CreateBTables.B312_ST);
            query.executeUpdate();
            query = em.createNativeQuery(CreateBTables.B32_ST);
            query.executeUpdate();
            query = em.createNativeQuery(CreateBTables.B33_ST);
            query.executeUpdate();
            query = em.createNativeQuery(CreateBTables.B34_ST);
            query.executeUpdate();
            query = em.createNativeQuery(CreateBTables.B35_ST);
            query.executeUpdate();
            query = em.createNativeQuery(CreateBTables.B36_ST);
            query.executeUpdate();
            query = em.createNativeQuery(CreateBTables.B37_ST);
            query.executeUpdate();
            query = em.createNativeQuery(CreateBTables.B4_ST);
            query.executeUpdate();
            query = em.createNativeQuery(CreateBTables.B6_ST);
            query.executeUpdate();

            em.getTransaction().commit();
            em.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void appendToMySQL_opslagTable() {

        EntityManager emMySQLTemp_st = nl.iisg.ids03.Utils.getEm_tabs();
        EntityManager emMySQLOpslag_st = nl.iisg.ids03.Utils.getEm_mysql_opslag();
        EntityManager emMySQLTemp = nl.iisg.ids03.Utils.getEm_original_tabs();
        EntityManager emMySQLOpslag = nl.iisg.ids03.Utils.getEm_mysql_opslag_org();

        try {

        	print("Appending from hsn_popreg_temp_org to hsn_popreg_total_org");
        	
            appendToMySQL_opslagTableB3(emMySQLOpslag, emMySQLTemp);
            appendToMySQL_opslagTableB2(emMySQLOpslag, emMySQLTemp);
            appendToMySQL_opslagTableB4(emMySQLOpslag, emMySQLTemp);
            appendToMySQL_opslagTableB6(emMySQLOpslag, emMySQLTemp);

        	print("Appending from hsn_popreg_temp_std to hsn_popreg_total_std");

            appendToMySQL_opslagTableB2_st(emMySQLOpslag_st, emMySQLTemp_st);
            appendToMySQL_opslagTableB3_st(emMySQLOpslag_st, emMySQLTemp_st);
            appendToMySQL_opslagTableB4_st(emMySQLOpslag_st, emMySQLTemp_st);
            appendToMySQL_opslagTableB6_st(emMySQLOpslag_st, emMySQLTemp_st);

//            close connections:
            emMySQLOpslag.clear();
            emMySQLTemp.clear();
            emMySQLOpslag_st.clear();
            emMySQLTemp_st.clear();


        } catch (Exception e) {
            try {
                print("Append to MySQL_opslag failed.\n");
            } catch (Exception f) {
                f.printStackTrace();
            }
            e.printStackTrace();

        }

    }

    private void appendToMySQL_opslagTableB2(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception {

        List<Person> personsTemp;


        Query q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_B2);
        personsTemp = (List<Person>) q.getResultList();

        print("Appending " +  personsTemp.size() + " rows to B2..." );

        EntityTransaction tx = emMySQLOpslag.getTransaction();
        tx.begin();
        for (Person person : personsTemp) {
            emMySQLOpslag.persist(person);
        }

        emMySQLOpslag.flush();
        tx.commit();
        emMySQLOpslag.clear();

    }

    private void appendToMySQL_opslagTableB3(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception {

        List<PersonDynamic> personDynamics;

        Query q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_B3);
        personDynamics = q.getResultList();

        print("Appending " +  personDynamics.size() + " rows to B3..." );
        
        EntityTransaction tx = emMySQLOpslag.getTransaction();
        tx.begin();
        for (PersonDynamic personDynamic : personDynamics) {
            emMySQLOpslag.persist(personDynamic);
        }
        emMySQLOpslag.flush();
        tx.commit();
        emMySQLOpslag.clear();

    }

    private void appendToMySQL_opslagTableB4(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception {

        List<Registration> registrations;

        Query q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_B4);
        registrations = q.getResultList();

        print("Appending " +  registrations.size() + " rows to B4..." );
        

        EntityTransaction tx = emMySQLOpslag.getTransaction();
        tx.begin();
        for (Registration registration : registrations) {
            emMySQLOpslag.persist(registration);
        }
        emMySQLOpslag.flush();
        tx.commit();

    }

    private void appendToMySQL_opslagTableB6(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception {

        List<RegistrationAddress> registrationAddresses;

        Query q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_B6);
        registrationAddresses = q.getResultList();

        print("Appending " +  registrationAddresses.size() + " rows to B6..." );

        
        EntityTransaction tx = emMySQLOpslag.getTransaction();
        tx.begin();
        try {
            for (RegistrationAddress registrationAddress : registrationAddresses) {
                emMySQLOpslag.persist(registrationAddress);
            }
            emMySQLOpslag.flush();
            tx.commit();
            emMySQLOpslag.clear();

        } catch (Exception e) {
            tx.rollback();
        }

    }

    private void appendToMySQL_opslagTableB2_st(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception {
        List<PersonStandardized> personsStandardizedTemp;

        Query q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_B2_ST);
        personsStandardizedTemp = (List<PersonStandardized>) q.getResultList();

        print("Appending " +  personsStandardizedTemp.size() + " rows to B2_ST..." );


        EntityTransaction tx = emMySQLOpslag.getTransaction();
        tx.begin();
        for (PersonStandardized person : personsStandardizedTemp) {
            emMySQLOpslag.persist(person);
        }

        emMySQLOpslag.flush();
        tx.commit();
        emMySQLOpslag.clear();


    }

    private void appendToMySQL_opslagTableB3_st(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception {

        List<PDS_CivilStatus> civilStatuses;
        List<PDS_OccupationalTitle> occupationalTitles;
        List<PDS_ParentsAndChildren> parentsAndChildrens;
        List<PDS_PlaceOfDestination> placeOfDestinations;
        List<PDS_PlaceOfOrigin> placeOfOrigins;
        List<PDS_RelationToHead> relationToHeads;
        List<PDS_AllToAll> allToAlls;
        List<PDS_Religion> religions;

//
        Query q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_PDS_RELATIONTOHEAD);
        relationToHeads = (List<PDS_RelationToHead>) q.getResultList();

        q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_PDS_PARENTSANDCHILDREN);
        parentsAndChildrens = (List<PDS_ParentsAndChildren>) q.getResultList();

        q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_PDS_CIVILSTATUS);
        civilStatuses = (List<PDS_CivilStatus>) q.getResultList();

        q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_PDS_RELIGION);
        religions = (List<PDS_Religion>) q.getResultList();

        q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_PDS_ALLTOALL);
        allToAlls = (List<PDS_AllToAll>) q.getResultList();

        q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_PDS_OCCUPATIONALTITLE);
        occupationalTitles = (List<PDS_OccupationalTitle>) q.getResultList();

        q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_PDS_PLACEOFORIGIN);
        placeOfOrigins = (List<PDS_PlaceOfOrigin>) q.getResultList();

        q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_PDS_PLACEOFDESTINATION);
        placeOfDestinations = (List<PDS_PlaceOfDestination>) q.getResultList();


        print("Appending " +  relationToHeads.size() + " rows to B311_ST..." );

        EntityTransaction tx = emMySQLOpslag.getTransaction();
        tx.begin();
        for (PDS_RelationToHead relationToHead : relationToHeads) {
            emMySQLOpslag.persist(relationToHead);
        }
        emMySQLOpslag.flush();
        tx.commit();
        emMySQLOpslag.clear();


        print("Appending " +  parentsAndChildrens.size() + " rows to B312_ST..." );

        tx = emMySQLOpslag.getTransaction();
        tx.begin();
        for (PDS_ParentsAndChildren parentsAndChildren : parentsAndChildrens) {
            emMySQLOpslag.persist(parentsAndChildren);
        }
        emMySQLOpslag.flush();
        tx.commit();
        emMySQLOpslag.clear();


        print("Appending " +  civilStatuses.size() + " rows to B32_ST..." );

        
        tx = emMySQLOpslag.getTransaction();
        tx.begin();
        for (PDS_CivilStatus civilStatus : civilStatuses) {
            emMySQLOpslag.persist(civilStatus);
        }
        emMySQLOpslag.flush();
        tx.commit();
        emMySQLOpslag.clear();

        print("Appending " +  religions.size() + " rows to B33_ST..." );

        
        tx = emMySQLOpslag.getTransaction();
        tx.begin();
        for (PDS_Religion religion : religions) {
            emMySQLOpslag.persist(religion);
        }
        emMySQLOpslag.flush();
        tx.commit();
        emMySQLOpslag.clear();

        
        print("Appending " +  allToAlls.size() + " rows to B34_ST..." );

        
        tx = emMySQLOpslag.getTransaction();
        tx.begin();
        for (PDS_AllToAll allToAll : allToAlls) {
            emMySQLOpslag.persist(allToAll);
        }
        emMySQLOpslag.flush();
        tx.commit();
        emMySQLOpslag.clear();

        
        print("Appending " +  occupationalTitles.size() + " rows to B35_ST..." );

        
        tx = emMySQLOpslag.getTransaction();
        tx.begin();
        for (PDS_OccupationalTitle occupationalTitle : occupationalTitles) {
            emMySQLOpslag.persist(occupationalTitle);
        }
        emMySQLOpslag.flush();
        tx.commit();
        emMySQLOpslag.clear();


        print("Appending " +  placeOfOrigins.size() + " rows to B36_ST..." );

        
        tx = emMySQLOpslag.getTransaction();
        tx.begin();
        for (PDS_PlaceOfOrigin placeOfOrigin : placeOfOrigins) {
            emMySQLOpslag.persist(placeOfOrigin);
        }
        emMySQLOpslag.flush();
        tx.commit();
        emMySQLOpslag.clear();


        print("Appending " +  placeOfDestinations.size() + " rows to B37_ST..." );

        
        tx = emMySQLOpslag.getTransaction();
        tx.begin();
        for (PDS_PlaceOfDestination placeOfDestination : placeOfDestinations) {
            emMySQLOpslag.persist(placeOfDestination);
        }
        emMySQLOpslag.flush();
        tx.commit();
        emMySQLOpslag.clear();


    }

    private void appendToMySQL_opslagTableB4_st(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception {
        List<RegistrationStandardized> registrationStandardizedTemp;
        Query q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_B4_ST);
        registrationStandardizedTemp = (List<RegistrationStandardized>) q.getResultList();


        EntityTransaction tx = emMySQLOpslag.getTransaction();
        print("Appending " +  registrationStandardizedTemp.size() + " rows to B4_ST3..." );


        tx.begin();
        for (RegistrationStandardized registrationStandardized : registrationStandardizedTemp) {
            emMySQLOpslag.persist(registrationStandardized);
        }
        emMySQLOpslag.flush();
        tx.commit();
        emMySQLOpslag.clear();


    }

    private void appendToMySQL_opslagTableB6_st(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception {
        List<RegistrationAddressStandardized> registrationAddressStandardizedTemp;
        Query q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_B6_ST);
        registrationAddressStandardizedTemp = (List<RegistrationAddressStandardized>) q.getResultList();

//        registrationAddressStandardizedTemp = registrationAddressStandardizedTemp.subList(0,10);

        print("Appending " +  registrationAddressStandardizedTemp.size() + " rows to B6_ST..." );

        
        EntityTransaction tx = emMySQLOpslag.getTransaction();
        tx.begin();

        try {
            for (RegistrationAddressStandardized registrationAddressStandardized : registrationAddressStandardizedTemp) {

                emMySQLOpslag.persist(registrationAddressStandardized);

            }
            emMySQLOpslag.flush();
            tx.commit();
            emMySQLOpslag.clear();


        } catch (Exception e) {
            tx.rollback();
        }

    }

    private boolean checkForDuplicatesB_st() throws Exception {
    	
            
        print("Checking for duplicate IDNR's in B2_ST");
        // Select those IDNR's from hsn_popreg_temp_std.b2 that also occur in hsn_popreg_total_std 

        EntityManager emMySQLTemp = nl.iisg.ids03.Utils.getEm_tabs();
        EntityManager emMySQLOpslag = nl.iisg.ids03.Utils.getEm_mysql_opslag();

        
        DBFWriter dbfWriter = new DBFWriter();
        DBFField dbfFields[] = new DBFField[1];
        dbfFields[0] = new DBFField();
        dbfFields[0].setDataType(DBFField.FIELD_TYPE_N);
        dbfFields[0].setFieldName("IDNR");
        dbfFields[0].setFieldLength(12);
        dbfWriter.setFields(dbfFields);

        Query qOpslag;
        Query qTemp = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_B2_ST);
        List<PersonStandardized> personsStandardizedTemp = (List<PersonStandardized>) qTemp.getResultList();
        
        if(personsStandardizedTemp.size() == 0)
        	return false;
        
        String whereClause = "(";
        
        int previousKey = -1;
        for (PersonStandardized personStandardized : personsStandardizedTemp) {
        	if(personStandardized.getKeyToRP() != previousKey){
        		whereClause += personStandardized.getKeyToRP();
        		whereClause += ",";
        		previousKey = personStandardized.getKeyToRP();
        	}
        }
        whereClause = whereClause.substring(0, whereClause.length() - 1); 
        whereClause += ")";      
        
        qOpslag = emMySQLOpslag.createQuery(AppendToOpslag.SELECT_BY_IDNRS_FROM_B2_ST + whereClause);
        List<PersonStandardized> result = (List<PersonStandardized>) qOpslag.getResultList();
        
        previousKey = -1;
        boolean duplicateFound = false;
        for (PersonStandardized personStandardized : result) {
        	
        	if(personStandardized.getKeyToRP() != previousKey){
        		duplicateFound = true;
    			Object rowData[] = new Object[1];
    			rowData[0] = personStandardized.getKeyToRP();
    			dbfWriter.addRecord(rowData);
    			previousKey = personStandardized.getKeyToRP();
        	}
        	
        }
        
        if(duplicateFound == true){
        	FileOutputStream fos = new FileOutputStream(INPUT_DIRECTORY + File.separator + "DOUBLE_IDNRS_B2_ST.DBF");
        	dbfWriter.write(fos);
        	fos.close();
        	sendFileToClient(new File(INPUT_DIRECTORY + File.separator + "DOUBLE_IDNRS_B2_ST.DBF"), FILE_TYPE_DBF);
        }
        
        
        

        return duplicateFound;
    }

    private boolean checkForDuplicatesB() throws Exception {

        // Select those IDNR's from hsn_popreg_temp_org.b2 that also occur in hsn_popreg_total_org.b2 

        print("Checking for duplicate IDNR's in B2");

    	
        EntityManager emMySQLTemp = nl.iisg.ids03.Utils.getEm_original_tabs();
        EntityManager emMySQLOpslag = nl.iisg.ids03.Utils.getEm_mysql_opslag_org();

        DBFWriter dbfWriter = new DBFWriter();
        DBFField dbfFields[] = new DBFField[1];
        dbfFields[0] = new DBFField();
        dbfFields[0].setDataType(DBFField.FIELD_TYPE_N);
        dbfFields[0].setFieldName("IDNR");
        dbfFields[0].setFieldLength(12);
        dbfWriter.setFields(dbfFields);

        Query qOpslag;
        Query qTemp = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_B2);
        List<Person> personsTemp = (List<Person>) qTemp.getResultList();
        
        if(personsTemp.size() == 0)
        	return false;
        

        int previousKey = -1;

        String whereClause = "(";
        
        for (Person person : personsTemp) {
        	if(person.getKeyToRP() != previousKey){
        		whereClause += person.getKeyToRP();
        		whereClause += ",";
        		previousKey = person.getKeyToRP();
        	}
        }
        whereClause = whereClause.substring(0, whereClause.length() - 1); 
        whereClause += ")";      
        
        qOpslag = emMySQLOpslag.createQuery(AppendToOpslag.SELECT_BY_IDNRS_FROM_B2 + whereClause);
        List<Person> result = (List<Person>) qOpslag.getResultList();
        
        previousKey = -1;
        boolean duplicateFound = false;
        for (Person person : result) {
        	
        	if(person.getKeyToRP() != previousKey){
        		duplicateFound = true;
    			Object rowData[] = new Object[1];
    			rowData[0] = person.getKeyToRP();
    			dbfWriter.addRecord(rowData);
    			previousKey = person.getKeyToRP();
        	}
        	
        }


        if(duplicateFound == true){
        	FileOutputStream fos = new FileOutputStream(INPUT_DIRECTORY + File.separator + "DOUBLE_IDNRS_B2.DBF");
        	dbfWriter.write(fos);
        	fos.close();
        	sendFileToClient(new File(INPUT_DIRECTORY + File.separator + "DOUBLE_IDNRS_B2.DBF"), FILE_TYPE_DBF);
        }
        
        return duplicateFound;
    }

    private void deleteFromBTable(List<IDnr> idNrList, EntityManager em, String deleteQuery) {
        Query query;
        String inStatement = "(";

        for (IDnr idNr : idNrList) {
            inStatement += idNr.getIdnr() + ",";
        }
        inStatement = inStatement.substring(0, (inStatement.length() - 1));
        inStatement += ")";

        query = em.createNativeQuery(deleteQuery + inStatement + ";");
        query.executeUpdate();

//        query = em.createNativeQuery(deleteQuery + idNr.getIdnr() + "';");
//        query.executeUpdate();

    }

    private void deleteFromBTables(List<IDnr> idNrList) {
        removeDuplicates(idNrList);


          print("Deleting " + idNrList.size() + " IDnrs from opslag database");

//        for (IDnr idNr : idNrList) {
//            try {
//                print(idNr.getIdnr());
//            } catch (IOException e) {
//                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            }
//        }

        try {
            // B* tables:
            EntityManager em = nl.iisg.ids03.Utils.getEm_mysql_opslag_org();
            em.getTransaction().begin();
            System.out.println("Deleting from B2_opslag table");
            deleteFromBTable(idNrList, em, DeleteFromBTables.DELETE_FROM_B2);
            System.out.println("Deleting from B3_opslag table");
            deleteFromBTable(idNrList, em, DeleteFromBTables.DELETE_FROM_B3);
            System.out.println("Deleting from B4_opslag table");
            deleteFromBTable(idNrList, em, DeleteFromBTables.DELETE_FROM_B4);
            System.out.println("Deleting from B6_opslag table");
            deleteFromBTable(idNrList, em, DeleteFromBTables.DELETE_FROM_B6);
            em.getTransaction().commit();
            em.clear();
            

            // B*_st tables:
            EntityManager em_st = nl.iisg.ids03.Utils.getEm_mysql_opslag();
            em_st.getTransaction().begin();
            System.out.println("Deleting from B2_ST table");
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B2_ST);
            System.out.println("Deleting from B311_ST table");
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B311_ST);
            System.out.println("Deleting from B312_ST table");
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B312_ST);
            System.out.println("Deleting from B32_ST table");
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B32_ST);
            System.out.println("Deleting from B33_ST table");
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B33_ST);
            System.out.println("Deleting from B34_ST table");
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B34_ST);
            System.out.println("Deleting from B35_ST table");
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B35_ST);
            System.out.println("Deleting from B36_ST table");
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B36_ST);
            System.out.println("Deleting from B37_ST table");
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B37_ST);
            System.out.println("Deleting from B4_ST table");
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B4_ST);
            System.out.println("Deleting from B6_ST table");
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B6_ST);


            // em.getTransaction().rollback();
            em_st.getTransaction().commit();
            em.clear();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void removeDuplicates(List<IDnr> idNrList) {
        HashSet<IDnr> h = new HashSet<IDnr>(idNrList);
        idNrList.clear();
        idNrList.addAll(h);
    }


    // delete temporary input dbf files:
    private void deleteTempFiles() {
        String[] dbfFiles = new File(INPUT_DIRECTORY).list();
        if (dbfFiles != null) {
            for (int j = 0; j < dbfFiles.length; j++) {
                File entry = new File(INPUT_DIRECTORY, dbfFiles[j]);
                entry.delete();
            }
        }
    }

    // Methods triggered by updates on textField:
//    public void insertUpdate(DocumentEvent e) {
//        try {
//            if(workerThread != null && !workerThread.isAlive()) deleteTempFiles();
//            print(textArea.getText(textArea.getText().length() - e.getLength(), e.getLength()));
//        } catch (BadLocationException e1) {
//            e1.printStackTrace();
//        } catch (IOException ei){
//            ei.printStackTrace();
//        }
//    }
//    public void removeUpdate(DocumentEvent e) {
//    }
//    public void changedUpdate(DocumentEvent e) {
//    }

    protected void finalize() {
//Clean up
        try {
            in.close();
            out.close();
            server.close();
        } catch (IOException e) {
            System.out.println("Could not close.");
            System.exit(-1);
        }
    }

    private void closeSocket() {
        // Clean up
        try {
            in.close();
            out.close();
            client.close();
        } catch (IOException e) {
            System.out.println("Could not close.");
            System.exit(-1);
        }
    }
    
    public void print(String line) {
    	if (out != null) {
    		try {
    			out.writeUTF(line);
    		} catch (IOException e) {
    			System.out.println("Client Message: " + line);
    		}
    	} else {
    		System.out.println(line);
    	}
    }


    private void receiveFileFromClient(boolean save) {

    	byte[] myByteArray = new byte[MAX_FILE_SIZE];
    	int bytesRead = 0;
    	int current = 0;

    	try {
    		//System.out.println("Receving file");
    		long fileLength = in.readLong();
    		String filename = in.readUTF();
    		if(save)
    			print("Receiving file " + filename);
    		else;
    			//print("Server busy, ignoring file " + filename);

    		File f = null;
    		File d = null;
    		FileOutputStream fos = null;
    		BufferedOutputStream bos = null;;

    		if(save){
    			d = new File(INPUT_DIRECTORY);
    			d.mkdir();
    			
    			f = new File(INPUT_DIRECTORY + "/" + filename);
    			if (!f.exists()) {
    				f.createNewFile();
    			}


    			fos = new FileOutputStream(f);
    			bos = new BufferedOutputStream(fos);

    		}

    		while (current < fileLength) {
    			bytesRead = in.read(myByteArray, current, ((int) fileLength - current));
    			if (bytesRead < 0) {
    				throw new InvalidObjectException("Server: Data stream ended prematurely");
    			}
    			current += bytesRead;
    		}


    		if(save){
    			bos.write(myByteArray, 0, current);
    			bos.flush();
    			bos.close();
    			fos.close();
    			//print("Server has received file.\n");
    			latestReceivedFile = f;
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}

    }

    private void sendFileToClient(File file, String fileType) {

        byte[] mybytearray = new byte[(int) file.length()];
        try {
            out.writeUTF(SEND_FILE_COMMAND);
            out.writeUTF(fileType);

            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(mybytearray, 0, mybytearray.length);

            System.out.println("File length: " + file.length());

            out.writeLong((int) file.length());
            out.writeUTF(file.getName());
            out.write(mybytearray, 0, mybytearray.length);
            out.flush();
            bis.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Printing methods:
    private void printHSNPopRegPrintSelectedErrors(
            List<Person> persons,                               // b2
            List<PersonDynamic> personsDynamic,                 // b3
            List<Registration> registrations,                   // b4
            List<RegistrationAddress> registrationAddresses,
            int errorLevel) {

        int command = errorLevel;
        boolean printFout = false;
        String output = "";

        System.out.println("In printHSNPopRegPrintSelectedErrors");

        List<Message> messages;
        
        factory_original_tabs = Persistence.createEntityManagerFactory("b_tables");
        emBtables = factory_original_tabs.createEntityManager();

        EntityManagerFactory factory_log = Persistence.createEntityManagerFactory("popreg_log");
        EntityManager em = factory_log.createEntityManager();
        
        System.out.println("Before sort");  

        Collections.sort(registrations, new Comparator<Registration>(){       
        
         public int compare(Registration r1, Registration r2){
        	 
        	 if     (r1.getKeyToRP() > r2.getKeyToRP()) return 1;
        	 else if(r1.getKeyToRP() < r2.getKeyToRP()) return -1;

        	 if     (r1.getYearEntryHead() > r2.getYearEntryHead()) return  1;
        	 else if(r1.getYearEntryHead() < r2.getYearEntryHead()) return -1;
   
   
        	 if     (r1.getMonthEntryHead() > r2.getMonthEntryHead()) return  1;
        	 else if(r1.getMonthEntryHead() < r2.getMonthEntryHead()) return -1;
   
        	 if     (r1.getDayEntryHead() > r2.getDayEntryHead()) return  1;
        	 else if(r1.getDayEntryHead() < r2.getDayEntryHead()) return -1;
   
        	 if     (r1.getKeyToSourceRegister() > r2.getKeyToSourceRegister()) return  1;
        	 else if(r1.getKeyToSourceRegister() < r2.getKeyToSourceRegister()) return -1;

        	 return 0;
         }});
   
  
        

        
        System.out.println("After sort");

        System.out.println("Opening bfout db..");
        try {

        	Charset utf8 = Charset.forName("UTF-8");

            //BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileWriter(INPUT_DIRECTORY + File.separator +"pop_reg_errors_selected.txt")));
            //(new OutputStreamWriter(new FileOutputStream(path),"UTF-8")); 
            BufferedWriter writer = new BufferedWriter 
            	    (new OutputStreamWriter(new FileOutputStream(INPUT_DIRECTORY + File.separator +"pop_reg_errors_selected.txt"),utf8)); 


            int i = 0;
            for (Registration r : registrations) {

            	
                if (i % 10 == 0) {
                    print(i + " of " + registrations.size() + " registrations read.");
                   // if(i >= 100) return;
                }
                i++;

            	
                int key = r.getKeyToRP();
                 

                Query q = em.createQuery("SELECT m FROM Message m WHERE m.keyToRP = ?2 " +
                        "AND m.dayEntryHead = ?3 " +
                        "AND m.monthEntryHead = ?4 " +
                        "AND m.yearEntryHead = ?5 " +
                        "AND m.keyToSourceRegister = ?6 ");

                q.setParameter(2, key).setParameter(3, r.getDayEntryHead()).setParameter(4, r.getMonthEntryHead()).setParameter(5, r.getYearEntryHead()).setParameter(6, r.getKeyToSourceRegister());

                messages = q.getResultList();

                int j = 0;

                Person person = null;


                for (Message m : messages) {
                    if (command == 3) {
                        if (
                                m.getErrorType().equals("FS")
                                        || m.getErrorType().equals("FT")
                                        || m.getErrorType().equals("FA") && m.getErrorNumber() != 1212 && m.getErrorNumber() != 1431
                                        || m.getErrorType().equals("RF")
                                        || m.getErrorType().equals("OP")
                                        || m.getErrorType().equals("NB")
                                        && ((m.getErrorNumber() > 1321
                                        && m.getErrorNumber() < 1330) || (m.getErrorNumber() > 1407
                                        && m.getErrorNumber() < 1411))
                                        || m.getErrorType().equals("VR")
                                        && (m.getErrorNumber() == 1126 || m.getErrorNumber() == 1166
                                        || m.getErrorNumber() == 1197 || m.getErrorNumber() == 1239
                                        || m.getErrorNumber() == 1242)
                                ) {
                            printFout = true;
                        }
                    }

                    if (command == 4 || command == 7) {
                        printFout = true;
                    }

                    if (command == 5) {
                        if (
                                m.getErrorType().equals("FT")
                                        || m.getErrorType().equals("FA") && m.getErrorNumber() != 1212 && m.getErrorNumber() != 1431
                                        || m.getErrorType().equals("RF")
                                        || m.getErrorType().equals("OP")
                                        || m.getErrorType().equals("NB")
                                        && ((m.getErrorNumber() > 1321
                                        && m.getErrorNumber() < 1330) || (m.getErrorNumber() > 1407
                                        && m.getErrorNumber() < 1411))
                                ) {
                            printFout = true;
                        }
                    }

                    if (command == 6) {
                        if (
                                m.getErrorType().equals("FT")
                                        || m.getErrorType().equals("VR")
                                        && (m.getErrorNumber() == 4121
                                        || m.getErrorNumber() == 4126
                                        || m.getErrorNumber() == 4134
                                        || m.getErrorNumber() == 4151
                                        || m.getErrorNumber() == 4525
                                        || m.getErrorNumber() == 4526)
                                ) {
                            printFout = true;
                        }
                    }

                    if (command == 8) {
                        if (m.getErrorType().equals("FT")) {
                            printFout = true;
                        }
                    }


                    if (printFout) {
                        output = "VLGNR\tKODE\t---------------------- MELDINGEN -----------------------------\n";
                        printFout = false;
                        /* printft() */
                        output += "  " + m.getKeyToRegistrationPersons() + "  \t"
                                + m.getErrorNumber() + "\t"
                                + m.getErrorType() + "\t"
                                + m.getErrorText() + "\n";

                        for (Person p : persons) {
                            if (m.getKeyToRP() == p.getKeyToRP()
                                    && m.getYearEntryHead() == p.getYearEntryHead()
                                    && m.getMonthEntryHead() == p.getMonthEntryHead()
                                    && m.getDayEntryHead() == p.getDayEntryHead()
                                    && m.getKeyToSourceRegister() == p.getKeyToSourceRegister()
                                    && p.getKeyToRegistrationPersons() == 1) {
                                person = p;


                            }

                        }
                    } else {
                        output = "GEEN MELDINGEN gevonden voor de volgende inschrijving\n";

                    }

                }


                if (person != null) {
                    output += printRgl(person,
                            r,
                            person.getKeyToRP(),
                            person.getYearEntryHead(),
                            person.getMonthEntryHead(),
                            person.getDayEntryHead(),
                            person.getKeyToSourceRegister(),
                            persons,
                            registrations,
                            registrationAddresses,
                            personsDynamic);

                }
                writer.write(output);
            }
            writer.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    // todo:
    private void printHSNPopRegPrintAll(List<Person> persons,                               // b2
                                        List<PersonDynamic> personsDynamic,                 // b3
                                        List<Registration> registrations,                   // b4
                                        List<RegistrationAddress> registrationAddresses) {

        String output = "";

        System.out.println("In printHSNPopRegPrintAll");
        
        factory_original_tabs = Persistence.createEntityManagerFactory("b_tables");
        emBtables = factory_original_tabs.createEntityManager();
        
        //Collections.sort(registrations);

        Collections.sort(registrations, new Comparator<Registration>(){       
            
            public int compare(Registration r1, Registration r2){
           	 
           	 if     (r1.getKeyToRP() > r2.getKeyToRP()) return 1;
           	 else if(r1.getKeyToRP() < r2.getKeyToRP()) return -1;

           	 if     (r1.getYearEntryHead() > r2.getYearEntryHead()) return  1;
           	 else if(r1.getYearEntryHead() < r2.getYearEntryHead()) return -1;
      
      
           	 if     (r1.getMonthEntryHead() > r2.getMonthEntryHead()) return  1;
           	 else if(r1.getMonthEntryHead() < r2.getMonthEntryHead()) return -1;
      
           	 if     (r1.getDayEntryHead() > r2.getDayEntryHead()) return  1;
           	 else if(r1.getDayEntryHead() < r2.getDayEntryHead()) return -1;
      
           	 if     (r1.getKeyToSourceRegister() > r2.getKeyToSourceRegister()) return  1;
           	 else if(r1.getKeyToSourceRegister() < r2.getKeyToSourceRegister()) return -1;

           	 return 0;
            }});
      
     

        try {

        	Charset utf8 = Charset.forName("UTF-8");
            BufferedWriter printFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(INPUT_DIRECTORY + File.separator +"pop_reg_errors_all.txt"),utf8)); 

            int i = 0;
            for (Registration r : registrations) {
                if (i % 10 == 0) {
                    print(i + " of " + registrations.size() + " registrations read.");
                    //if(i >= 100) return;
                }
                i++;

                int key = r.getKeyToRP();

                int j = 0;
                Person person = null;


                
                for (Person p : persons) {
                    if (r.getKeyToRP() == p.getKeyToRP()
                            && r.getYearEntryHead() == p.getYearEntryHead()
                            && r.getMonthEntryHead() == p.getMonthEntryHead()
                            && r.getDayEntryHead() == p.getDayEntryHead()
                            && r.getKeyToSourceRegister() == p.getKeyToSourceRegister()
                            && p.getKeyToRegistrationPersons() == 1) {
                        person = p;

                        output += printRgl(person,
                                r,
                                person.getKeyToRP(),
                                person.getYearEntryHead(),
                                person.getMonthEntryHead(),
                                person.getDayEntryHead(),
                                person.getKeyToSourceRegister(),
                                persons,
                                registrations,
                                registrationAddresses,
                                personsDynamic);
                        
                    }
                }


                printFile.write(output);
            }
            printFile.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void printHSNPopRegErrors(
            List<Person> persons,                               // b2
            List<PersonDynamic> personsDynamic,                 // b3
            List<Registration> registrations,                   // b4
            List<RegistrationAddress> registrationAddresses) {  // b6

        System.out.println("In printHSNPopRegErrors");
    	
        String output = "";
        List<Message> messages;
        
        factory_original_tabs = Persistence.createEntityManagerFactory("b_tables");
        emBtables = factory_original_tabs.createEntityManager();


        EntityManagerFactory factory_log = Persistence.createEntityManagerFactory("popreg_log");
        EntityManager em = factory_log.createEntityManager();
        Query s = em.createQuery("SELECT m FROM Message m");
        //Collections.sort(registrations);
        Collections.sort(registrations, new Comparator<Registration>(){       
            
            public int compare(Registration r1, Registration r2){
           	 
           	 if     (r1.getKeyToRP() > r2.getKeyToRP()) return 1;
           	 else if(r1.getKeyToRP() < r2.getKeyToRP()) return -1;

           	 if     (r1.getYearEntryHead() > r2.getYearEntryHead()) return  1;
           	 else if(r1.getYearEntryHead() < r2.getYearEntryHead()) return -1;
      
      
           	 if     (r1.getMonthEntryHead() > r2.getMonthEntryHead()) return  1;
           	 else if(r1.getMonthEntryHead() < r2.getMonthEntryHead()) return -1;
      
           	 if     (r1.getDayEntryHead() > r2.getDayEntryHead()) return  1;
           	 else if(r1.getDayEntryHead() < r2.getDayEntryHead()) return -1;
      
           	 if     (r1.getKeyToSourceRegister() > r2.getKeyToSourceRegister()) return  1;
           	 else if(r1.getKeyToSourceRegister() < r2.getKeyToSourceRegister()) return -1;

           	 return 0;
            }});
      
     


        try {
        	
        	Charset utf8 = Charset.forName("UTF-8");
            BufferedWriter printFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(INPUT_DIRECTORY + File.separator +"pop_reg_errors.txt"),utf8)); 

            int i = 0;
            for (Registration r : registrations) {
                if (i % 10 == 0) {
                    print(i + " of " + registrations.size() + " registrations read.");
                    //if(i >= 100)
                    //	break;
                }
                i++;

                int key = r.getKeyToRP();

                Query q = em.createQuery("SELECT m FROM Message m WHERE m.keyToRP = ?2 " +
                        "AND m.dayEntryHead = ?3 " +
                        "AND m.monthEntryHead = ?4 " +
                        "AND m.yearEntryHead = ?5 " +
                        "AND m.keyToSourceRegister = ?6 ");

                q.setParameter(2, key).setParameter(3, r.getDayEntryHead()).setParameter(4, r.getMonthEntryHead()).setParameter(5, r.getYearEntryHead()).setParameter(6, r.getKeyToSourceRegister());

                messages = q.getResultList();

                int j = 0;
                Person person = null;

                output = "VLGNR\tKODE\t---------------------- FOUTTYPE -----------------------------\n";
                for (Message m : messages) {

                    /* printft() */
                    output += "  " + m.getKeyToRegistrationPersons() + "  \t"
                            + m.getErrorNumber() + "\t"
                            + m.getErrorType() + "\t"
                            + m.getErrorText() + "\n";


                    for (Person p : persons) {
                        if (m.getKeyToRP() == p.getKeyToRP()
                                && m.getYearEntryHead() == p.getYearEntryHead()
                                && m.getMonthEntryHead() == p.getMonthEntryHead()
                                && m.getDayEntryHead() == p.getDayEntryHead()
                                && m.getKeyToSourceRegister() == p.getKeyToSourceRegister()
                                && p.getKeyToRegistrationPersons() == 1) {
                            person = p;
                        }

                    }


                }
                output += "\n";
                if (person != null) {
                    output += printRgl(person,
                            r,
                            person.getKeyToRP(),
                            person.getYearEntryHead(),
                            person.getMonthEntryHead(),
                            person.getDayEntryHead(),
                            person.getKeyToSourceRegister(),
                            persons,
                            registrations,
                            registrationAddresses,
                            personsDynamic);

                } else {
                    output += "Geen eerste HSN-volgnummer in Idnr: " + r.getKeyToRP() + " Hoofddatum: " + r.getDayEntryHead()
                            + "/" + r.getMonthEntryHead() + "/" + r.getYearEntryHead() + " Bronnr: /" + r.getKeyToSourceRegister() + "\n";
                    output += "------------ daarom ook geen inschrijving weergegeven ---------------------------\n";
                    output += "---------------------------------------------------------------------------------\n\n";

                }
                printFile.write(output);
            }
            printFile.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String printRgl(Person per,
                            Registration reg,
                            int keyToRP,
                            int yearEntryHead,
                            int monthEntryHead,
                            int dayEntryHead,
                            int keyToSourceRegister,
                            List<Person> persons,
                            List<Registration> registrations,
                            List<RegistrationAddress> registrationAddresses,
                            List<PersonDynamic> personsDynamic) {


        String output = "";
        boolean GGR = false;
//        for(Person p : persons){
        GGR = false;
//            if(keyToRP == p.getKeyToRP()
//                        && yearEntryHead == p.getYearEntryHead()
//                        && monthEntryHead == p.getMonthEntryHead()
//                        && dayEntryHead == p.getDayEntryHead()
//                        && keyToSourceRegister == p.getKeyToSourceRegister()
//                        && p.getNatureOfPerson() == 1)
//                {
        if (per.getOrderNumber().equals("GGR")) GGR = true;
        output += "IDnr: " + per.getKeyToRP()
                + "\tBRONnr: " + per.getKeyToSourceRegister()
                + "\tHOOFDdatum: " + String.format("%02d/%02d/%04d", per.getDayEntryHead(), per.getMonthEntryHead(), per.getYearEntryHead()) // b2mibg b2jibg
                + "\tOPdatum: " + String.format("%02d/%02d/%04d", reg.getDayEntryRP(), reg.getMonthEntryRP(), reg.getYearEntryRP()) // todo: b2fdbg b2fmbg b2fjbg
                + "\n";

//                }
//            }

        Ref_AINB ainb = Ref.getAINB(keyToSourceRegister);
        if (ainb != null) {
            String btp = "";
            if (ainb.getTypeRegister().equals("A")) {
                btp = "\tAlleenstaandenregister ";
            } else if (ainb.getTypeRegister().equals("B")) {
                btp = "\tBevolkingsregister     ";
            } else if (ainb.getTypeRegister().equals("C")) {
                btp = "\tBev.register 1850-1862 ";
            } else if (ainb.getTypeRegister().equals("G")) {
                btp = "\tGezinskaart            ";
            } else if (ainb.getTypeRegister().equals("I")) {
                btp = "\tIndividueel kaartje    ";
            }

            output += btp + " "
                    + ainb.getMunicipality().trim() + " "
                    + ainb.getStartYearRegister() + "-" + ainb.getEndYearRegister();

            if (ainb.getTypeRegister().equals("A") || ainb.getTypeRegister().equals("B")
                    || ainb.getTypeRegister().equals("C") || ainb.getTypeRegister().equals("D")) {

                output += " [" + reg.getPageNumberOfSource().trim();
                output += "/" + reg.getNumberOfHousehold();
                if (ainb.getB1gwbg() != null) output += " " + ainb.getB1gwbg().trim();
                if (ainb.getB1gwbg() != null) output += " " + ainb.getB1ivbg().trim() + "]";

            } else if (ainb.getTypeRegister().equals("G") || ainb.getTypeRegister().equals("I")) {

                for (Registration r : registrations) {
                    if (r.getKeyToRP() == keyToRP
                            && r.getYearEntryHead() == yearEntryHead
                            && r.getMonthEntryHead() == monthEntryHead
                            && r.getDayEntryHead() == dayEntryHead) {

                        output += "    GK-info: " + r.getInfoFamilyCardsSystem().trim();
                        if (!r.getSpecialDataEntryCodes().trim().equals(""))
                            output += "   Verdere info: " + r.getSpecialDataEntryCodes().trim();


                    }
                }

            }
        } else {

            output += "   !!! Bronnummer niet in "; // todo anaam, ext ?
        }

        if (GGR) output += "   INHOUD IS GEGENEREERD";

        output += "\n";


        for (Registration r : registrations) {
            if (r.getKeyToRP() == keyToRP
                    && r.getYearEntryHead() == yearEntryHead
                    && r.getMonthEntryHead() == monthEntryHead
                    && r.getDayEntryHead() == dayEntryHead) {

                if (r.getOrderNumber().equals("GGR")) {
                    output += "     INHOUD IS GEGENEREERD\n";
                }

                if (!r.getRemarks().trim().equals("") && r.getSpecialDataEntryCodes().trim().equals("")) { // wel bijzonderheden geen speciale codes

                    output += "\tBijzonderheden bij deze inschrijving: " + r.getRemarks().trim() + "\n";
                    output += "\tGeen speciale code bij deze inschrijving\n";

                } else if (r.getRemarks().trim().equals("") && !r.getSpecialDataEntryCodes().trim().equals("")) { // geen bijzonderheden wel speciale codes

                    output += "\tGeen bijzonderheden bij deze inschrijving (als geheel)\n";
                    output += "\tSpeciale code bij deze inschrijving:   " + r.getSpecialDataEntryCodes().trim() + "\n";

                } else if (!r.getRemarks().trim().equals("") && !r.getSpecialDataEntryCodes().trim().equals("")) { // wel bijzonderheden wel speciale codes

                    output += "\tBijzonderheden bij deze inschrijving: " + r.getRemarks().trim() + "\n";
                    output += "\tSpeciale code bij deze inschrijving:  " + r.getSpecialDataEntryCodes().trim() + "\n";

                } else if (r.getRemarks().trim().equals("") && r.getSpecialDataEntryCodes().trim().equals("")) { //geen bijzonderheden geen speciale codes

                    output += "\tGeen bijzonderheden en geen speciale code bij deze inschrijving\n";

                }
                output += "VLGNR\n";
            }

        }


        for (Person p : persons) {
            boolean op = false;
//                System.out.println("\n****p****\n");
            if (keyToRP == p.getKeyToRP()
                    && yearEntryHead == p.getYearEntryHead()
                    && monthEntryHead == p.getMonthEntryHead()
                    && dayEntryHead == p.getDayEntryHead()
                    && keyToSourceRegister == p.getKeyToSourceRegister()) {

                if (p.getNatureOfPerson() == 1 || p.getNatureOfPerson() == 5) op = true;
                String f = "  ";
                f += p.getKeyToRegistrationPersons() + " "
                        + "Ins. reg.: " + p.getDayOfRegistration() + "/"
                        + p.getMonthOfRegistration() + "/"
                        + p.getYearOfRegistration() + "   ";

                if (p.getDayOfRegistrationAfterInterpretation() > 0
                        || p.getMonthOfRegistrationAfterInterpretation() > 0
                        || p.getYearOfRegistrationAfterInterpretation() > 0
                        || p.getDayOfRegistrationAfterInterpretation() == -3
                        || p.getMonthOfRegistrationAfterInterpretation() == -3
                        || p.getYearOfRegistrationAfterInterpretation() == -3) {

                    f += "Ins. datum INTERPRETATIE: " + p.getDayOfRegistrationAfterInterpretation() + "/"
                            + p.getMonthOfRegistrationAfterInterpretation() + "/"
                            + p.getYearOfRegistrationAfterInterpretation() + "    ";
                }
                f += p.getFamilyName().trim() + ", " + p.getFirstName().trim();
                f = String.format("%-60s", f);
                output += f;

                output += p.getSex() + " ******* ";
                output += p.getDayOfBirth() + "/" + p.getMonthOfBirth() + "/" + p.getYearOfBirth();
                output += " te " + p.getPlaceOfBirth().trim();

                if (p.getDayOfBirthAfterInterpretation() > 0
                        || p.getMonthOfBirthAfterInterpretation() > 0
                        || p.getYearOfBirthAfterInterpretation() > 0
                        || p.getDayOfBirthAfterInterpretation() == -3
                        || p.getMonthOfBirthAfterInterpretation() == -3
                        || p.getYearOfBirthAfterInterpretation() == -3) {

                    output += "        ******* INTERPRETATIE: ";
                    output += p.getDayOfBirthAfterInterpretation() + "/" + p.getMonthOfBirthAfterInterpretation() + "/" + p.getYearOfBirthAfterInterpretation();
                    output += " te " + p.getPlaceOfBirth().trim() + " "; // todo @cor : + b2glnr niet bekend, toevoegen?
                }

                if (ainb != null && ainb.getTypeRegister().equals("C")
                        && !p.getLegalPlaceOfLivingInCodes().trim().equals("")
                        && !p.getLegalPlaceOfLivingInCodes().toUpperCase().equals("N")) {
                    output += " WD: " + p.getLegalPlaceOfLivingInCodes().trim() + "  ";
                } else if (!p.getLegalPlaceOfLiving().trim().equals("")
                        && !p.getLegalPlaceOfLiving().toUpperCase().equals("N")) {
                    output += " WD: " + p.getLegalPlaceOfLiving().trim() + "  ";
                }

                if (!p.getNationality().trim().equals("")) {
                    output += "  Nat: " + p.getNationality().trim();
                }

                output += "\n";

                if (p.getYearOfDecease() != 0) {
                    output += "\tOVL: " + p.getDayOfDecease() + "/" + p.getMonthOfDecease() + "/" + p.getYearOfDecease() + " te " + p.getPlaceOfDecease().trim() + "\n";
                }

                if (!p.getRemarks().trim().equals("") && !p.getRemarks().trim().equals("N")) {
                    output += "\tAanmerkingen: " + p.getRemarks().trim() + "\n";
                }

                if (!p.getRemarks2().trim().equals("")) {
                    output += "\tBijzonderheden persoon: " + p.getRemarks2().trim();
                    output += "\n";
                }

                boolean dynFound = false;
                // zoek dynamische gegevens bij persoon:

                List<PersonDynamic> dynamicData;

                Query q = emBtables.createQuery("select a from PersonDynamic a where a.keyToRP = " + p.getKeyToRP() +
                        " and a.yearEntryHead = " + p.getYearEntryHead() +
                        " and a.monthEntryHead = " + p.getMonthEntryHead() +
                        " and a.dayEntryHead = " + p.getDayEntryHead() +
                        " and a.keyToSourceRegister = " + p.getKeyToSourceRegister() +
                        " and a.keyToRegistrationPersons = " + p.getKeyToRegistrationPersons() +
                        " order by a.dynamicDataType");
                dynamicData = q.getResultList();
                int i = 0;

                for (PersonDynamic personDynamic : dynamicData) {
                    String outputDyn = "";

//                    if (
//                            personDynamic.getKeyToRP() == p.getKeyToRP()                    // midnr
//                                    && personDynamic.getYearEntryHead() == p.getYearEntryHead()              // mb2jibg
//                                    && personDynamic.getMonthEntryHead() == p.getMonthEntryHead()             // mb2mibg
//                                    && personDynamic.getDayEntryHead() == p.getDayEntryHead()               // mb2dibg
//                                    && personDynamic.getKeyToSourceRegister() == ainb.getKeyToSourceRegister()     // mb1idbg
//                                    && personDynamic.getKeyToRegistrationPersons() == p.getKeyToRegistrationPersons()   // mb2rnbg
//                            ) {
                    dynFound = true;
                    String mutDatum = "";

                    if (personDynamic.getDayOfMutationAfterInterpretation() > 0
                            || personDynamic.getMonthOfMutationAfterInterpretation() > 0
                            || personDynamic.getYearOfMutationAfterInterpretation() > 0
                            || personDynamic.getDayOfMutationAfterInterpretation() == -3
                            || personDynamic.getMonthOfMutationAfterInterpretation() == -3
                            || personDynamic.getYearOfMutationAfterInterpretation() == -3) {
                        mutDatum = personDynamic.getDayOfMutation() + "/"
                                + personDynamic.getMonthOfMutation() + "/"
                                + personDynamic.getYearOfMutation() + " INTERPRETATIE:"
                                + personDynamic.getDayOfMutationAfterInterpretation() + "/"
                                + personDynamic.getMonthOfMutationAfterInterpretation() + "/"
                                + personDynamic.getYearOfMutationAfterInterpretation() + " ";
                    } else {
                        mutDatum = personDynamic.getDayOfMutation() + "/"
                                + personDynamic.getMonthOfMutation() + "/"
                                + personDynamic.getYearOfMutation() + " ";
                    }

                    if (personDynamic.getDynamicDataType() == 1) {    // relatie

                        String s = "";

                        if (personDynamic.getDynamicDataSequenceNumber() == 1) {
                            if (op) {
                                if ((i % NR_OF_COLUMNS) == 0) s = "=OP\t";
                            } else {
                                if ((i % NR_OF_COLUMNS) == 0) s = "   \t";
                            }
                            s += "1e relatie (niet gedat.) ";
                            s += safeGetContentOfDynamicData1(personDynamic.getContentOfDynamicData());
                            //s += Const.b3kode1[personDynamic.getContentOfDynamicData()];   // if vanwege null pointer errors, stond niet in originele code
                            //s += personDynamic.getDynamicData2(); // todo: vragen aan Kees: wat gebeurd er in Clipper code?
                        } else {
                            if (
                                    personDynamic.getDayOfMutationAfterInterpretation() > 0
                                            || personDynamic.getMonthOfMutationAfterInterpretation() > 0
                                            || personDynamic.getYearOfMutationAfterInterpretation() > 0
                                            || personDynamic.getDayOfMutationAfterInterpretation() == -3
                                            || personDynamic.getMonthOfMutationAfterInterpretation() == -3
                                            || personDynamic.getYearOfMutationAfterInterpretation() == -3) {

                                s += personDynamic.getDynamicDataSequenceNumber() + "e relatie: ";
                                s += safeGetContentOfDynamicData1(personDynamic.getContentOfDynamicData()).trim();
                                //s += Const.b3kode1[personDynamic.getContentOfDynamicData()];
                                s += personDynamic.getDynamicData2().trim();
                            } else {
                                s += personDynamic.getDynamicDataSequenceNumber() + "e relatie: ";
                                s += mutDatum;
                                s += safeGetContentOfDynamicData1(personDynamic.getContentOfDynamicData()).trim();
                                //s += Const.b3kode1[personDynamic.getContentOfDynamicData()];
                                s += personDynamic.getDynamicData2().trim();
                            }

                        }
                        s = String.format("%-" + COLUMN_WIDTH + "s", s);
                        if ((i % NR_OF_COLUMNS) == 1) s += "\n";
                        outputDyn += s;

                    } else if (personDynamic.getDynamicDataType() == 2) {       // burgelijke staat

                        String s = "";
                        if (op) {
                            if ((i % NR_OF_COLUMNS) == 0) s = "=OP\t";
                        } else {
                            if ((i % NR_OF_COLUMNS) == 0) s = "   \t";
                        }
                        if (personDynamic.getDynamicDataSequenceNumber() == 1) {
                            s += "1e burg.st: " + mutDatum + " " + safeGetContentOfDynamicData2(personDynamic.getContentOfDynamicData()).trim(); //Const.b3kode2[personDynamic.getContentOfDynamicData()];
                        } else if (personDynamic.getDynamicDataSequenceNumber() == 2) {
                            s += "2e burg.st: ";
                            s += mutDatum + " ";
                            s += safeGetContentOfDynamicData2(personDynamic.getContentOfDynamicData()).trim();
                            //s += Const.b3kode2[personDynamic.getContentOfDynamicData()];
                        } else {
                            s += personDynamic.getDynamicDataSequenceNumber() + "e burg.st: ";
                            s += mutDatum + " ";
                            s += safeGetContentOfDynamicData2(personDynamic.getContentOfDynamicData()).trim();
                        }

                        s = String.format("%-" + COLUMN_WIDTH + "s", s);
                        if ((i % NR_OF_COLUMNS) == 1) s += "\n";
                        outputDyn += s;

                    } else if (personDynamic.getDynamicDataType() == 3) {      // kerkgenootschap

                        String s = "";
                        if (op) {
                            if ((i % NR_OF_COLUMNS) == 0) s = "=OP\t";
                        } else {
                            if ((i % NR_OF_COLUMNS) == 0) s = "   \t";
                        }

                        if (personDynamic.getDynamicDataSequenceNumber() == 1) {
                            s += "1e kerkgen: " + personDynamic.getDynamicData2().trim();
                        } else {
                            s += personDynamic.getDynamicDataSequenceNumber() + "e kerkgen: ";
                            s += personDynamic.getDynamicData2().trim();

                        }
                        s = String.format("%-" + COLUMN_WIDTH + "s", s);
                        if ((i % NR_OF_COLUMNS) == 1) s += "\n";
                        outputDyn += s;

                    } else if (personDynamic.getDynamicDataType() == 5) {       // beroep

                        String s = "";
                        if (op) {
                            if ((i % NR_OF_COLUMNS) == 0) s = "=OP\t";
                        } else {
                            if ((i % NR_OF_COLUMNS) == 0) s = "   \t";
                        }

                        if (personDynamic.getDynamicDataSequenceNumber() == 1) {
                            s += "1e beroep: " + personDynamic.getDynamicData2().trim();
                        } else {
                            s += personDynamic.getDynamicDataSequenceNumber() + "e beroep: ";
                            s += personDynamic.getDynamicData2().trim();
                        }

                        if (personDynamic.getContentOfDynamicData() == 1) s += " [h]";
                        if (personDynamic.getContentOfDynamicData() == 2) s += " [o]";
                        if (personDynamic.getContentOfDynamicData() == 3) s += " [n]";

                        s = String.format("%-" + COLUMN_WIDTH + "s", s);
                        if ((i % NR_OF_COLUMNS) == 1) s += "\n";
                        outputDyn += s;

                    } else if (personDynamic.getDynamicDataType() == 6) {       // herkomst

                        String s = "";
                        if (op) {
                            if ((i % NR_OF_COLUMNS) == 0) s = "=OP\t";
                        } else {
                            if ((i % NR_OF_COLUMNS) == 0) s = "   \t";
                        }

                        if (personDynamic.getDynamicDataSequenceNumber() == 1) {
                            s += "1e herkmst: " + mutDatum + " " + personDynamic.getDynamicData2().trim();
                        } else {
                            s += personDynamic.getDynamicDataSequenceNumber() + "e herkmst: ";
                            s += mutDatum + " ";
                            s += personDynamic.getDynamicData2().trim();
                        }
                        s = String.format("%-" + COLUMN_WIDTH + "s", s);
                        if ((i % NR_OF_COLUMNS) == 1) s += "\n";
                        outputDyn += s;

                    } else if (personDynamic.getDynamicDataType() == 7) {       // vertrek

                        String s = "";
                        if (op) {
                            if ((i % NR_OF_COLUMNS) == 0) s = "=OP\t";
                        } else {
                            if ((i % NR_OF_COLUMNS) == 0) s = "   \t";
                        }

                        if (personDynamic.getDynamicDataSequenceNumber() == 1) {
                            s += "1e vertrek: " + mutDatum + " " + personDynamic.getDynamicData2().trim();
                        } else {
                            s += personDynamic.getDynamicDataSequenceNumber() + "e vertrek: ";
                            s += mutDatum + " ";
                            s += personDynamic.getDynamicData2().trim();
                        }

                        s = String.format("%-" + COLUMN_WIDTH + "s", s);
                        if ((i % NR_OF_COLUMNS) == 1) s += "\n";
                        outputDyn += s;

                    }

                    output += outputDyn;
                    //output += "\n**** dyn ***\n";
                    //}
                    i++;
                }
                if ((i % NR_OF_COLUMNS) == 1) {
                    output += "\n";
                } else if (!dynFound) {
                    output += "Geen relatie tot hoofd gevonden bij deze persoon\n"
                            + " (rest dynamische gegevens niet opgenomen)\n";
                }
                output += "\n";
            }

        }

        output += "\n";

        for (RegistrationAddress rAddr : registrationAddresses) {
            if (keyToRP == rAddr.getKeyToRP()
                    && dayEntryHead == rAddr.getDayEntryHead()
                    && monthEntryHead == rAddr.getMonthEntryHead()
                    && yearEntryHead == rAddr.getYearEntryHead()) {
                if (rAddr.getDayOfAddressAfterInterpretation() > 0
                        || rAddr.getMonthOfAddressAfterInterpretation() > 0
                        || rAddr.getYearOfAddressAfterInterpretation() > 0
                        || rAddr.getDayOfAddressAfterInterpretation() == -3
                        || rAddr.getMonthOfAddressAfterInterpretation() == -3
                        || rAddr.getYearOfAddressAfterInterpretation() == -3) {

                    output += "Adres:  " + rAddr.getDayOfAddress() + "/" + rAddr.getMonthOfAddress() + "/" + rAddr.getYearOfAddress() + "  ";
                    output += "INTERPRETATIE: " + rAddr.getDayOfAddressAfterInterpretation() + "/" + rAddr.getMonthOfAddressAfterInterpretation();
                    output += "/" + rAddr.getYearOfAddressAfterInterpretation() + "  ";
                    output += "Type: " + rAddr.getAddressType() + "  " + rAddr.getNameOfStreet().trim() + " " + rAddr.getNumber().trim() + " " + rAddr.getAdditionToNumber().trim() + "\n";
                } else {
                    output += "Adres:  " + rAddr.getDayOfAddress() + "/" + rAddr.getMonthOfAddress() + "/" + rAddr.getYearOfAddress() + "  ";
                    output += "Type: " + rAddr.getAddressType() + "  " + rAddr.getNameOfStreet().trim() + " " + rAddr.getNumber().trim() + " " + rAddr.getAdditionToNumber().trim() + "\n";
                }

            }
        }


        output += "\n\n";

        return output;

    }

	public Thread getWorkerThread() {
		return workerThread;
	}

	public void setWorkerThread(Thread workerThread) {
		this.workerThread = workerThread;
	}

    

}
