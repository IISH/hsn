package nl.iisg.alfalabFrontEnd;

import java.awt.*;
import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.text.DefaultCaret;
import com.intellij.uiDesigner.core.*;

/**
 * Created by IntelliJ IDEA.
 * User: cro
 * Date: 11-10-11
 * Time: 12:42
 * To change this template use File | Settings | File Templates.
 */
public class AlfalabClient extends JFrame implements ActionListener {
    private final static String GET_MESSAGE_QUEUE_COMMAND = "getMessageQueue";
    private final static String GET_DATE_COMMAND = "getDate";
    private final static String SEND_FILE_COMMAND = "sendFile";
    private final static String SERVER_EXIT_COMMAND = "exit";
    private final static String SERVER_CANCEL = "serverCancel";

    private final static String POP_REG_TEST_ERRORS = "popRegTestErrors";
    private final static String POP_REG_TO_IDS = "popRegToIDS";
    private final static String POP_REG_DELETE_FROM_DEF_DB = "popRegDeleteFromDefDB";
    private final static String POP_REG_APPEND_TO_DEF_DB = "popRegAppendToDefDB";
    private final static String POP_REG_TEST_IDNR_DOUBLES = "popRegTestIDnrDoubles";
    private final static String POP_REG_REPLACE_DEF_WITH_TEMP = "popRegReplaceDefWithTemp";
    private final static String PERSONAL_CARDS_CONVERSION = "personalCardsConversion";
    private final static String PERSONAL_CARDS_TO_IDS = "personalCardsToIDS";
    private final static String CIVIL_CERTS_CONVERSION = "civilCertsConversion";
    private final static String CIVIL_CERTS_TO_IDS= "civilCertsToIDS";
    private final static String ADD_LINKS_DATA= "addLinksData";
    private final static String BUILD_NEW_HSN = "buildNewHSN";
    private final static String INITIALISE_HSN = "initialiseHSN";
    private final static String BUILD_EXTRACTION_SET_HSN_STANDARD = "buildExtractionSetHSNStandard";
    private final static String BUILD_EXTRACTION_SET_MIGRATION_FILE = "buildExtractionSetMigrationFile";
    private final static String MISC_PRINT_HSN_POP_REG_ERRORS = "miscPrintHSNPopRegErrors";
    private final static String MISC_PRINT_HSN_POP_REG_SELECTED = "miscPrintHSNPopRegSelected";
    private final static String RESET_TEMP_DIR = "resetTempDir";

    private final static String MISC_PRINT_HSN_POP_REG_ALL = "miscPrintHSNPopRegAll";
    private final static String MISC_PRINT_CONTROLE_A_1000 = "miscPrintControleA1000";
    private final static String MISC_PRINT_CONTROLE_B_1000 = "miscPrintControleB1000";
    private final static String MISC_PRINT_CONTROLE_AB_1000 = "miscPrintControleAB1000";
    private final static String MISC_PRINT_CONTROLE_A_4000 = "miscPrintControleA4000";
    private final static String MISC_PRINT_CONTROLE_B_4000 = "miscPrintControleB4000";
    private final static String MISC_PRINT_CONTROLE_AB_4000 = "miscPrintControleAB_400";

    private final static int MAX_FILE_SIZE = 10 * 1024 * 1024; // = 10 Megabytes
    
    private final static int portNo = 8009;

    private static String serverAddress = "";

    private JButton getDateButton;
    private JButton getMessageQueueButton;
    private JButton openFileButton;
    private JFileChooser fileChooser;

    File selectedDir;
    Socket socket = null;
    DataOutputStream out = null;
    DataInputStream in = null;
    ClientConfig clientConfig;
    Thread HandleUploadAsynchrounouslyThread;

    public AlfalabClient() {

        initComponents();
		clientConfig = new ClientConfig();

        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        textArea.setLineWrap(true);

        popRegButton1.addActionListener(this);
        popRegButton2.addActionListener(this);
        personalCardsButton1.addActionListener(this);
        personalCardsButton2.addActionListener(this);

        civilCertsButton1.addActionListener(this);
        civilCertsButton2.addActionListener(this);
//        civilCertsButton.setEnabled(false);
        combineIdsSetsButton.addActionListener(this);
        combineIdsSetsButton.setEnabled(true);
//        extractionSetButton.addActionListener(this);
//      extractionSetButton.setEnabled(false);
        addLinksData.addActionListener(this);
        extractionSetButton.setEnabled(false);
        miscButton.addActionListener(this);
        initialiseIDS.addActionListener(this);

    }

    public void actionPerformed(ActionEvent event) {

    	
    	/**** Start Internal Class Definition ******/

    	
    	class HandleUploadAsynchrounously implements Runnable{

    		File [] files;
    		String command;

    		HandleUploadAsynchrounously(File [] files1, String command1){
    			files = files1;
    			command = command1;    			
    		}


    		public void run(){
    			
    			try {
    				out.writeUTF(RESET_TEMP_DIR);
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}


    			for (File file : files) {
    				try {
    					out.writeUTF(SEND_FILE_COMMAND);
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				//textArea.append("Uploading: " + file.getName() + "\n");
    				sendFileToServer(file);

    				//textArea.append("File sent\n");
    			}

    			try {
    				out.writeUTF(command);
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    	}

    	
    	/**** End Internal Class Definition ******/
    	
    	if(HandleUploadAsynchrounouslyThread != null && HandleUploadAsynchrounouslyThread.isAlive())
    		return;
    	
        Object source = event.getSource();
        try {
        	
            if (source == getMessageQueueButton) {

                textArea.setText("");
                out.writeUTF(GET_MESSAGE_QUEUE_COMMAND);

            } else if (source == openFileButton) {

            } else if (source == popRegButton1) {

                PopulationRegisterSubmenu popSubmenu = new PopulationRegisterSubmenu(this);
                popSubmenu.setVisible(true);
                dispatchPopRegister(popSubmenu.getAction());

            } else if (source == popRegButton2) {
            	int reply = JOptionPane.showConfirmDialog(null, "Run Population Register to IDS?", "Confirm Choice", JOptionPane.YES_NO_OPTION);
            	if (reply == JOptionPane.YES_OPTION)
            		out.writeUTF(POP_REG_TO_IDS);

            } else if (source == personalCardsButton1) {

                File[] inputDbfFiles = openDBFDir();

                // input dir selection canceled by user
                if (inputDbfFiles == null) return;

                ConfirmInputDirectory c = new ConfirmInputDirectory(this);
                c.setInputDirectoryField(selectedDir.toString());
                c.setVisible(true);

                // user pressed cancel at confirmation dialog
                if (!c.getConfirm()) return;
                
                // send DBF files to server:
                HandleUploadAsynchrounously h = new HandleUploadAsynchrounously(inputDbfFiles, PERSONAL_CARDS_CONVERSION);                
                HandleUploadAsynchrounouslyThread = new Thread(h);
                HandleUploadAsynchrounouslyThread.start();

            } else if (source == personalCardsButton2) {
            	
            	 int reply = JOptionPane.showConfirmDialog(null, "Run Personal Cards to IDS?", "Confirm Choice", JOptionPane.YES_NO_OPTION);
            	 if (reply == JOptionPane.YES_OPTION)
            		 out.writeUTF(PERSONAL_CARDS_TO_IDS);

            } else if (source == civilCertsButton1) {

                File[] inputDbfFiles = openDBFDir();

                // input dir selection canceled by user
                if (inputDbfFiles == null) return;
                
                ConfirmInputDirectory c = new ConfirmInputDirectory(this);
                c.setInputDirectoryField(selectedDir.toString());
                c.setVisible(true);

                // user pressed cancel at confirmation dialog
                if (!c.getConfirm()) return;


                // send DBF files to server:
                
                HandleUploadAsynchrounously h = new HandleUploadAsynchrounously(inputDbfFiles, CIVIL_CERTS_CONVERSION);
                
                HandleUploadAsynchrounouslyThread = new Thread(h);
                HandleUploadAsynchrounouslyThread.start();
                
            } else if (source == civilCertsButton2) {
            	int reply = JOptionPane.showConfirmDialog(null, "Run Civil Certificates IDS?", "Confirm Choice", JOptionPane.YES_NO_OPTION);
            	if (reply == JOptionPane.YES_OPTION)
            		out.writeUTF(CIVIL_CERTS_TO_IDS);
            	
            } else if (source == addLinksData) {
            	int reply = JOptionPane.showConfirmDialog(null, "Run Add Links Data?\n Is the HSN IDS Ready?", "Confirm Choice", JOptionPane.YES_NO_OPTION);
            	if (reply == JOptionPane.YES_OPTION)
            		out.writeUTF(ADD_LINKS_DATA);

            } else if (source == combineIdsSetsButton) {

                //LocationDialog locationDialog = new LocationDialog(this);
                //locationDialog.setVisible(true);
            	
            	String version = JOptionPane.showInputDialog(null, "Version: ",	"Please enter Version", 1);

            	if(version != null)
            		 out.writeUTF(BUILD_NEW_HSN + " " + version);
            	

            } else if (source == extractionSetButton) {

                LocationDialog locationDialog = new LocationDialog(this);
                locationDialog.setVisible(true);
                if (locationDialog.isSucceed()) {
                    ExtractionSetSubmenu extractionSubmenu = new ExtractionSetSubmenu(this);
                    extractionSubmenu.setVisible(true);
                }

            } else if (source == miscButton) {

                MiscSubmenu miscSubmenu = new MiscSubmenu(this);
                miscSubmenu.setVisible(true);
                dispatchMisc(miscSubmenu.getAction());

            } else if (source == initialiseIDS) {
            	
            	String version = JOptionPane.showInputDialog(null, "Version: ",	"Please enter Version", 1);

            	if(version != null)
            		 out.writeUTF(INITIALISE_HSN + " " + version);

            	//textArea.append("\nShow Persons             Started\n");
            	//textArea.append("\n");
            	//textArea.append("     \\   /                \\   /            \n");
            	//textArea.append("      \\0/                  \\0/             \n");
            	//textArea.append("       *                    *              \n");
            	//textArea.append("      ***                  ***             \n");
            	//textArea.append("     *****                *****            \n");
            	//textArea.append("      ***                  ***             \n");
            	//textArea.append("     /   \\                /   \\           \n");
            	//textArea.append("    /     \\              /     \\          \n");
            	//textArea.append("\n");
            	//textArea.append("\nShow Persons             Ended\n");

                //ViewPerson viewPersonPanel = new ViewPerson(this);
                //viewPersonPanel.setVisible(true);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dispatchMisc(String miscCommand) throws IOException {
        if (miscCommand == null) {
            return;
        } else if (miscCommand.equals(MISC_PRINT_HSN_POP_REG_ALL)) {

            out.writeUTF(MISC_PRINT_HSN_POP_REG_ALL);
//            textArea.append("Printing HSN files Population Registers (all) started.\n\n");

        } else if (miscCommand.equals(MISC_PRINT_HSN_POP_REG_ERRORS)) {

            out.writeUTF(MISC_PRINT_HSN_POP_REG_ERRORS);
//            textArea.append("Printing HSN files Population Registers based on error messages started.\n\n");

        } else if (miscCommand.equals(MISC_PRINT_CONTROLE_A_1000)) {

            out.writeUTF(MISC_PRINT_HSN_POP_REG_SELECTED);
            out.writeInt(3);
//            textArea.append("Printing HSN files Population Registers Controle A melding 1000-serie Ronde 2 started.\n\n");

        } else if (miscCommand.equals(MISC_PRINT_CONTROLE_B_1000)) {

            out.writeUTF(MISC_PRINT_HSN_POP_REG_SELECTED);
            out.writeInt(4);
//            textArea.append("Printing HSN files Population Registers Controle B melding 1000-serie Ronde 2 started.\n\n");

        } else if (miscCommand.equals(MISC_PRINT_CONTROLE_AB_1000)) {

            out.writeUTF(MISC_PRINT_HSN_POP_REG_SELECTED);
            out.writeInt(5);
//            textArea.append("Printing HSN files Population Registers Controle A/B melding 1000-serie Ronde 3 started.\n\n");

        } else if (miscCommand.equals(MISC_PRINT_CONTROLE_A_4000)) {

            out.writeUTF(MISC_PRINT_HSN_POP_REG_SELECTED);
            out.writeInt(6);
//            textArea.append("Printing HSN files Population Registers Controle A melding 4000-serie Ronde 4 started.\n\n");

        } else if (miscCommand.equals(MISC_PRINT_CONTROLE_B_4000)) {

            out.writeUTF(MISC_PRINT_HSN_POP_REG_SELECTED);
            out.writeInt(7);
//            textArea.append("Printing HSN files Population Registers Controle B melding 4000-serie Ronde 4 started.\n\n");

        } else if (miscCommand.equals(MISC_PRINT_CONTROLE_AB_4000)) {

            out.writeUTF(MISC_PRINT_HSN_POP_REG_SELECTED);
            out.writeInt(8);
//            textArea.append("Printing HSN files Population Registers Controle A/B melding 4000-serie Ronde 5 started.\n\n");

        }

    }


    /* Checks which key is pressed in the Population Register submenu and starts the corresponding procedures */
    private void dispatchPopRegister(String popRegisterCommand) throws IOException {

    	/**** Start Internal Class Definition ******/

    	
    	class HandleUploadAsynchrounously implements Runnable{

    		File [] files;
    		String command;

    		HandleUploadAsynchrounously(File [] files1, String command1){
    			files = files1;
    			command = command1;    			
    		}


    		public void run(){
    			
    			try {
    				out.writeUTF(RESET_TEMP_DIR);
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}


    			for (File file : files) {
    				try {
    					out.writeUTF(SEND_FILE_COMMAND);
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				//textArea.append("Uploading: " + file.getName() + "\n");
    				sendFileToServer(file);

    				//textArea.append("File sent\n");
    			}

    			try {
    				out.writeUTF(command);
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    	}

    	
    	/**** End Internal Class Definition ******/

    	
        if (popRegisterCommand == null) {
            return;
        } else if (popRegisterCommand.equals(POP_REG_TEST_ERRORS)) {

            File[] inputDbfFiles = openDBFDir();

            // input dir selection canceled by user
            if (inputDbfFiles == null) return;
            
            // YYY

            ConfirmInputDirectory testOnErrorsDialog = new ConfirmInputDirectory(this);
            testOnErrorsDialog.setInputDirectoryField(selectedDir.toString());
            testOnErrorsDialog.setVisible(true);

            // user pressed cancel at confirmation dialog
            if (!testOnErrorsDialog.getConfirm()) return;

            
            // send DBF files to server:
            HandleUploadAsynchrounously h = new HandleUploadAsynchrounously(inputDbfFiles, POP_REG_TEST_ERRORS);                
            HandleUploadAsynchrounouslyThread = new Thread(h);
            HandleUploadAsynchrounouslyThread.start();

        } else if (popRegisterCommand.equals(POP_REG_DELETE_FROM_DEF_DB)) {

            File inputDbfFile = openDBFFile();

            // input file selection canceled by user
            if (inputDbfFile == null) return;


            PopRegDeleteFromDefDBDialog deleteFromDefDBDialog = new PopRegDeleteFromDefDBDialog(this);
            deleteFromDefDBDialog.setInputFileField(inputDbfFile.toString());
            deleteFromDefDBDialog.setVisible(true);

            if (!deleteFromDefDBDialog.getConfirm()) return;

            // send DBF file to server:
            out.writeUTF(SEND_FILE_COMMAND);
            //textArea.append("Sending: " + inputDbfFile.getName() + "\n");
            sendFileToServer(inputDbfFile);
            //textArea.append("File sent\n");

            out.writeUTF(POP_REG_DELETE_FROM_DEF_DB);

        } else if (popRegisterCommand.equals(POP_REG_APPEND_TO_DEF_DB)) {

            //textArea.append("Append to MySQL_opslag started...\n");

            out.writeUTF(POP_REG_APPEND_TO_DEF_DB);

        } else if (popRegisterCommand.equals(POP_REG_TEST_IDNR_DOUBLES)) {

            //textArea.append("Testing on IDnr doubles started.\n");
            out.writeUTF(POP_REG_TEST_IDNR_DOUBLES);

        } else if (popRegisterCommand.equals(POP_REG_REPLACE_DEF_WITH_TEMP)) {

            //textArea.append("Replacing of MySQL opslag with MySQL temp started\n");
            out.writeUTF(POP_REG_REPLACE_DEF_WITH_TEMP);

        }


    }


    private void sendFileToServer(File file) {
        byte[] mybytearray = new byte[(int) file.length()];
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(mybytearray, 0, mybytearray.length);
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

    public void startUpdateThread() {
        UpdateClientThread updateClientThread = new UpdateClientThread(textArea, in);
        Thread thread = new Thread(updateClientThread);
        thread.start();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
        }
        AlfalabClient frame = new AlfalabClient();
        String title = "HSN Control Program";
        if (args.length > 0)
            title = title + " - " +  args[0];
        frame.setTitle(title);
        frame.setContentPane(frame.Interface);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        if (args.length > 0)
            serverAddress = args[0];
        else {
            System.out.println("No host specified.");
            System.exit(0);
        }

        frame.start();
    }

    private File[] openDBFDir() throws IOException {

        JFileChooser fileChooser = new JFileChooser(clientConfig.getLatestInputLocation());

        fileChooser.setMultiSelectionEnabled(true);

        fileChooser.setDialogTitle("Select directory containing the DBF files: ");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//        fileChooser.setAcceptAllFileFilterUsed(false);

        FilenameFilter dbfFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".dbf") || name.endsWith(".DBF") || name.endsWith(".ACCDB") || name.endsWith(".accdb");
            }
        };

        int returnVal = fileChooser.showOpenDialog(this);


        // the filechooser will return the selected directory, or the
        // parent directory if a file is chosen:
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectedDir = fileChooser.getSelectedFile();

            try {
                clientConfig.setLatestInputLocation(selectedDir.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (selectedDir.isDirectory()) {
                File[] dbfFiles = selectedDir.listFiles(dbfFilter);
                return dbfFiles;
            } else if (selectedDir.isFile()) {
                selectedDir = selectedDir.getParentFile();
                return selectedDir.listFiles(dbfFilter);
            } else {
                return null;
            }

        } else {
//            textArea.append("Open command cancelled by user.\n");
            return null;
        }
    }

    private File openDBFFile() {
        JFileChooser fileChooser = new JFileChooser(clientConfig.getLatestInputLocation());
        fileChooser.setDialogTitle("Select a DBF file: ");
        fileChooser.setAcceptAllFileFilterUsed(false);

        FilenameFilter dbfFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".dbf") || name.endsWith(".DBF");
            }
        };

        int returnVal = fileChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            selectedDir = fileChooser.getSelectedFile();

            try {
                clientConfig.setLatestInputLocation(selectedDir.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return selectedDir;

        } else {
//            textArea.append("Open command cancelled by user.\n");
            return null;
        }
    }

    private void start() {
        try {
            //socket = new Socket("10.24.63.203", 4444);
            //socket = new Socket("localhost", 4444);
            socket = new Socket(serverAddress, portNo);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            startUpdateThread();
            out.writeUTF(GET_MESSAGE_QUEUE_COMMAND);
            
            // Next code for logon
            
            Login l = new Login(in, out);
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + serverAddress);
            System.exit(1);
        } catch (IOException e) {
            System.out.println("No I/O");
            System.exit(1);
        }
    }


    private String timestamp() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        return simpleDateFormat.format(new Date());

    }

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Christian Roosendaal
		Interface = new JPanel();
		JScrollPane scrollPane1 = new JScrollPane();
		textArea = new JTextArea();
		popRegButton1 = new JButton();
		popRegButton2 = new JButton();
		personalCardsButton1 = new JButton();
		personalCardsButton2 = new JButton();
		civilCertsButton1 = new JButton();
		civilCertsButton2 = new JButton();
		combineIdsSetsButton = new JButton();
		extractionSetButton = new JButton();
		addLinksData = new JButton();
		miscButton = new JButton();
		initialiseIDS = new JButton();

		//======== Interface ========
		{

			// JFormDesigner evaluation mark
			Interface.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new  javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"HSN Control Program", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.black), Interface.getBorder())); Interface.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			Interface.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));

			Interface.setBackground(Color.lightGray);//
			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(textArea);
			}
			Interface.add(scrollPane1, new GridConstraints(0, 0, 1, 2,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
				null, new Dimension(500, 300), null));

			//---- popRegButton 1 ----
			popRegButton1.setHorizontalAlignment(SwingConstants.LEFT);
			popRegButton1.setHorizontalTextPosition(SwingConstants.TRAILING);
			popRegButton1.setText("Population Register - Standardize");
			Interface.add(popRegButton1, new GridConstraints(1, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_FIXED,
				null, null, null));

			//---- popRegButton 2 ----
			popRegButton2.setHorizontalAlignment(SwingConstants.LEFT);
			popRegButton2.setHorizontalTextPosition(SwingConstants.TRAILING);
			popRegButton2.setText("Population Register  - to IDS                             ");
			Interface.add(popRegButton2, new GridConstraints(1, 1, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_FIXED,
				null, null, null));

			//---- personalCardsButton 1 ----
			personalCardsButton1.setHorizontalAlignment(SwingConstants.LEFT);
			personalCardsButton1.setHorizontalTextPosition(SwingConstants.TRAILING);
			personalCardsButton1.setText("Personal Cards        - Standardize");
			Interface.add(personalCardsButton1, new GridConstraints(2, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_FIXED,
				null, null, null));


			//---- personalCardsButton 2 ----
			personalCardsButton2.setHorizontalAlignment(SwingConstants.LEFT);
			personalCardsButton2.setHorizontalTextPosition(SwingConstants.TRAILING);
			personalCardsButton2.setText("Personal Cards         - to IDS");
			Interface.add(personalCardsButton2, new GridConstraints(2, 1, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_FIXED,
				null, null, null));

			//---- civilCertsButton 1 ----
			civilCertsButton1.setHorizontalAlignment(SwingConstants.LEFT);
			civilCertsButton1.setHorizontalTextPosition(SwingConstants.TRAILING);
			civilCertsButton1.setText("Civil Certificates      - Standardize");
			Interface.add(civilCertsButton1, new GridConstraints(3, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_FIXED,
				null, null, null));

			//---- civilCertsButton 2 ----
			civilCertsButton2.setHorizontalAlignment(SwingConstants.LEFT);
			civilCertsButton2.setHorizontalTextPosition(SwingConstants.TRAILING);
			civilCertsButton2.setText("Civil Certificates       - to IDS");
			Interface.add(civilCertsButton2, new GridConstraints(3, 1, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_FIXED,
				null, null, null));

			//---- combineIdsSetsButton ----
			combineIdsSetsButton.setHorizontalAlignment(SwingConstants.LEFT);
			combineIdsSetsButton.setHorizontalTextPosition(SwingConstants.TRAILING);
			combineIdsSetsButton.setText("IDS                          - Integrate");
			Interface.add(combineIdsSetsButton, new GridConstraints(4, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_FIXED,
				null, null, null));

			
			//---- extractionSetButton ----
			//extractionSetButton.setHorizontalAlignment(SwingConstants.LEFT);
			//extractionSetButton.setHorizontalTextPosition(SwingConstants.TRAILING);
			//extractionSetButton.setText("IDS                            - Build Extraction Set");
			//Interface.add(extractionSetButton, new GridConstraints(4, 1, 1, 1,
			//	GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			//	GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			//	GridConstraints.SIZEPOLICY_FIXED,
			//	null, null, null));

			//---- extractionSetButton ----
			addLinksData.setHorizontalAlignment(SwingConstants.LEFT);
			addLinksData.setHorizontalTextPosition(SwingConstants.TRAILING);
			addLinksData.setText("IDS                           - Add LINKS Data");
			Interface.add(addLinksData, new GridConstraints(4, 1, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_FIXED,
				null, null, null));

			//---- miscButton ----
			miscButton.setHorizontalAlignment(SwingConstants.LEFT);
			miscButton.setHorizontalTextPosition(SwingConstants.TRAILING);
			miscButton.setText("Miscellaneous (e.g. printing population register)");
			Interface.add(miscButton, new GridConstraints(5, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_FIXED,
				null, null, null));

			//
			
			initialiseIDS.setHorizontalAlignment(SwingConstants.LEFT);
			initialiseIDS.setHorizontalTextPosition(SwingConstants.TRAILING);
			initialiseIDS.setText("IDS                          - Initialise");
			Interface.add(initialiseIDS, new GridConstraints(5, 1, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_FIXED,
				null, null, null));

			
			
			//---- showPersons ----
			//showPersons.setText("IDS                  - Initialise Context");
			//Interface.add(showPersons, new GridConstraints(5, 1, 1, 1,
			//	GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
			//	GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
			//	GridConstraints.SIZEPOLICY_FIXED,
			//	null, null, null));
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Christian Roosendaal
	private JPanel Interface;
	private JTextArea textArea;
	private JButton popRegButton1;
	private JButton popRegButton2;
	private JButton personalCardsButton1;
	private JButton personalCardsButton2;
	private JButton civilCertsButton1;
	private JButton civilCertsButton2;
	private JButton combineIdsSetsButton;
	private JButton extractionSetButton;  //addLinksData
	private JButton addLinksData;
	private JButton miscButton;
	private JButton initialiseIDS;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
