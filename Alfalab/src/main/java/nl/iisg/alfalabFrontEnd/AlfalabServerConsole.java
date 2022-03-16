package nl.iisg.alfalabFrontEnd;

import javax.persistence.*;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.Scanner;

import iisg.nl.hsnmigrate.StandardizeCivilCertificates;
import nl.iisg.convertPK.StandardizePersonalCards;
import nl.iisg.hsnlinks.HSNLinksIntegration;
import nl.iisg.ids.IDS;
import nl.iisg.ids03.*;
import nl.iisg.ids04.PopulationRegisterToIDS;
import nl.iisg.ids05.CivilCertificatesToIDS;
import nl.iisg.ids06.PKToIDS;
import nl.iisg.ids_init.IDS_INIT;

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
            
        } else if (command.split(" ")[0].equals(PERSONAL_CARDS_CONVERSION)) {

        	String parm = null;
        	if(command.split(" ").length > 1 && command.split(" ")[1] != null)
        		parm = command.split(" ")[1].trim();
        	else
        		parm = INPUT_DIRECTORY;
        	
            //print("Started Standardize Personal Cards. Please wait..\n");
            StandardizePersonalCards standardizePersonalCards = new StandardizePersonalCards(out, parm);
            workerThread = new Thread(standardizePersonalCards);
            workerThread.start();

        } else if (command.equals(PERSONAL_CARDS_TO_IDS)) {

            //print("Started Personal Card to IDS. Please wait..\n");
        	
        	
            PKToIDS PKToIDS = new PKToIDS(out);
            workerThread = new Thread(PKToIDS);
            workerThread.start();

        } else if (command.split(" ")[0].equals(CIVIL_CERTS_CONVERSION)) {

            //print("Started Standardize Civil Certificates. Please wait..\n");
//            StandardizePersonalCards standardizePersonalCards = new StandardizePersonalCards(out, INPUT_DIRECTORY);
        	
        	String parm = null;
        	if(command.split(" ").length > 1 && command.split(" ")[1] != null)
        		parm = command.split(" ")[1].trim();
        	else
        		parm = INPUT_DIRECTORY;
        	
            StandardizeCivilCertificates standardizeCivilCertificates = new StandardizeCivilCertificates(out, parm);
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

            PopReg popReg = new PopReg(out, INPUT_DIRECTORY);
            PopReg.setCommand(POP_REG_DELETE_FROM_DEF_DB);
            PopReg.setFile(latestReceivedFile);
            PopReg.setWithFile(this::sendFileToClient);

            workerThread = new Thread(popReg);
            workerThread.start();

        } else if (command.equals(POP_REG_APPEND_TO_DEF_DB)) {

            PopReg popReg = new PopReg(out, INPUT_DIRECTORY);
            PopReg.setCommand(POP_REG_APPEND_TO_DEF_DB);
            PopReg.setFile(latestReceivedFile);
            PopReg.setWithFile(this::sendFileToClient);

            workerThread = new Thread(popReg);
            workerThread.start();

        } else if (command.equals(POP_REG_TEST_IDNR_DOUBLES)) {

            PopReg popReg = new PopReg(out, INPUT_DIRECTORY);
            PopReg.setCommand(POP_REG_TEST_IDNR_DOUBLES);
            PopReg.setFile(latestReceivedFile);
            PopReg.setWithFile(this::sendFileToClient);

            workerThread = new Thread(popReg);
            workerThread.start();

        } else if (command.equals(POP_REG_REPLACE_DEF_WITH_TEMP)) {

            PopReg popReg = new PopReg(out, INPUT_DIRECTORY);
            PopReg.setCommand(POP_REG_REPLACE_DEF_WITH_TEMP);
            PopReg.setFile(latestReceivedFile);
            PopReg.setWithFile(this::sendFileToClient);

            workerThread = new Thread(popReg);
            workerThread.start();

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
        	        	          
            //System.out.println(System.getProperty("user.dir"));
            //Path path = Paths.get(System.getProperty("user.dir") + "\\bin\\META-INF", "pw.txt");
            
        	
        	//System.out.println("java.class.path = " + System.getProperty("java.class.path"));
        	//System.out.println("user.dir = " + System.getProperty("user.dir"));
        	//System.out.println("user.home = " + System.getProperty("user.home"));
        	
        	InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("META-INF/pw.txt");

        	boolean found = false;
        	Scanner sc = new Scanner(inputStream);
        	while(sc.hasNextLine()){
        		String line = sc.nextLine();
        		if(user.equals(line.split("/")[0]) && pasw.equals(line.split("/")[1])){
        			found = true;
        			break;
        		}
        	}
        	/*
            URL u = getClass().getProtectionDomain().getCodeSource().getLocation();
            URL u1 = new URL(u, "META-INF/pw.txt");
            
            System.out.println("u1 = " + u1.toString());
            
            System.out.println("URL = " + u1);
            boolean found = false;
            Scanner s = new Scanner(u1.openStream());
            while(s.hasNextLine()){
                String line = s.nextLine();
                //System.out.println(">>" + line);
                if(user.equals(line.split("/")[0]) && pasw.equals(line.split("/")[1])){
        			found = true;
        			break;
                }
                
            }

            InputStream is = this.getClass().getClassLoader().getResourceAsStream("foo.txt");
            */
            if(found) {
            	//System.out.println("Print to client");
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
            PrintHsnPopReg printHsnPopReg = new PrintHsnPopReg(out, INPUT_DIRECTORY);
            PrintHsnPopReg.setCommand(MISC_PRINT_HSN_POP_REG_ALL);
            PrintHsnPopReg.setWithFile(this::sendFileToClient);

            workerThread = new Thread(printHsnPopReg);
            workerThread.start();
        } else if (command.equals(MISC_PRINT_HSN_POP_REG_ERRORS)) {
            PrintHsnPopReg printHsnPopReg = new PrintHsnPopReg(out, INPUT_DIRECTORY);
            PrintHsnPopReg.setCommand(MISC_PRINT_HSN_POP_REG_ERRORS);
            PrintHsnPopReg.setWithFile(this::sendFileToClient);

            workerThread = new Thread(printHsnPopReg);
            workerThread.start();
        } else if (command.equals(MISC_PRINT_HSN_POP_REG_SELECTED)) {
            PrintHsnPopReg printHsnPopReg = new PrintHsnPopReg(out, INPUT_DIRECTORY);
            PrintHsnPopReg.setCommand(MISC_PRINT_HSN_POP_REG_SELECTED);
            PrintHsnPopReg.setWithFile(this::sendFileToClient);
            PrintHsnPopReg.setErrorLevel(in.readInt());

            workerThread = new Thread(printHsnPopReg);
            workerThread.start();
        } else {

            response = "Unknown command: " + command + "\n";
            print(response);

        }
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
    	System.out.println("Writing to Client: " + line);
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

	public Thread getWorkerThread() {
		return workerThread;
	}

	public void setWorkerThread(Thread workerThread) {
		this.workerThread = workerThread;
	}

    

}
