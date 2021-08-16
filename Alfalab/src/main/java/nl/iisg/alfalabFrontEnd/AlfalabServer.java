package nl.iisg.alfalabFrontEnd;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFWriter;

import nl.iisg.convertPK.Utils;
import nl.iisg.hsncommon.ConstRelations2;
import nl.iisg.ids03.*;
import nl.iisg.ref.*;

//import org.omg.CORBA.portable.Delegate;

public class AlfalabServer extends JFrame implements ActionListener, DocumentListener {
    private final static String GET_MESSAGE_QUEUE_COMMAND = "getMessageQueue";
    private final static String GET_DATE_COMMAND = "getDate";
    private final static String SEND_FILE_COMMAND = "sendFile";
    private final static String SERVER_EXIT_COMMAND = "exit";

    private final static String POP_REG_TEST_ERRORS = "popRegTestErrors";
    private final static String POP_REG_DELETE_FROM_DEF_DB = "popRegDeleteFromDefDB";
    private final static String POP_REG_APPEND_TO_DEF_DB = "popRegAppendToDefDB";
    private final static String POP_REG_TEST_IDNR_DOUBLES = "popRegTestIDnrDoubles";
    private final static String POP_REG_REPLACE_DEF_WITH_TEMP = "popRegReplaceDefWithTemp";
    private final static String PERSONAL_CARDS_CONVERSION = "personalCardsConversion";
    private final static String CIVIL_CERTS_CONVERSION = "civilCertsConversion";
    private final static String BUILD_NEW_HSN = "buildNewHSN";
    private final static String BUILD_EXTRACTION_SET_HSN_STANDARD = "buildExtractionSetHSNStandard";
    private final static String BUILD_EXTRACTION_SET_MIGRATION_FILE = "buildExtractionSetMigrationFile";
    private final static String MISC_PRINT_HSN_POP_REG_ERRORS = "miscPrintHSNPopRegErrors";
    private final static String MISC_PRINT_HSN_POP_REG_ALL = "miscPrintHSNPopRegAll";
    private final static String MISC_PRINT_HSN_POP_REG_SELECTED = "miscPrintHSNPopRegSelected";
    private final static String INITIALISE_HSN = "initialiseHSN";
    private final static String LOGIN = "login";

    


    private final static String INPUT_DIRECTORY = "C:\\Users\\cro\\Documents\\temp";

    private final static int MAX_FILE_SIZE = 10 * 1024 * 1024; // = 10 Megabytes

    private File latestReceivedFile;

    JPanel AlfalabServer;
    JButton receiveButton;
    JTextArea textArea;
    ServerSocket server = null;
    Socket client = null;
    DataInputStream in = null;
    DataOutputStream out = null;
    String line;

    Thread workerThread;

    public AlfalabServer() {
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
        }

        textArea.setText("Started.\n");
        textArea.getDocument().addDocumentListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
    }

    public void start() {
        listenSocket();
    }

    public void dispatchCommand( String command ) throws IOException {
        String response = "";
        if (command.equals(GET_MESSAGE_QUEUE_COMMAND)) {

            out.writeUTF(textArea.getText());

        } else if ( command.equals(SEND_FILE_COMMAND )) {

            receiveFileFromClient();

        } else if ( command.equals(POP_REG_TEST_ERRORS )) {

            StandardizePopulationRegister IDSCheckConvert = new nl.iisg.ids03.StandardizePopulationRegister(textArea, INPUT_DIRECTORY);
            workerThread = new Thread(IDSCheckConvert);
            workerThread.start();

        } else if ( command.equals(POP_REG_DELETE_FROM_DEF_DB ) ) {

            boolean succeeded = latestReceivedFile.renameTo(new File(INPUT_DIRECTORY + "\\deletefromdefdb.dbf"));

            if(!succeeded){
                textArea.append("ERROR: Rename of file not succeeded, operation cancelled\n");
                return;
            }

            List<IDnr> idNrList = Utils.createObjects("nl.iisg.alfalabFrontEnd.IDnr", INPUT_DIRECTORY);

            // collect all data from MySQL_opslag and create dbf file:
            try {
                createDbfFromDeletedIdnrs(idNrList);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // delete the entries in MySQL_opslag:
            deleteFromBTables(idNrList);
            deleteTempFiles();

        } else if ( command.equals(POP_REG_APPEND_TO_DEF_DB) ) {


            // Check if there aren't duplicate entries first:
            try {
                if(checkForDuplicatesTempDef()){
                    textArea.append("Warning: duplicates detected!\n");
                } else {
                    textArea.append("No duplicates detected.\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Create MySQL_opslag table if not exist:
            createMySQL_opslagTable();

            // Start append process:
            appendToMySQL_opslagTable();

        } else if ( command.equals(POP_REG_TEST_IDNR_DOUBLES) ) {

            textArea.append("Testing for doubles in Idnr between Temp and Def DB...\n");


            // check for duplicates, and create dbf file from found duplicates:
            try {
                if(checkForDuplicatesTempDef()){
                    textArea.append("Warning: duplicates detected!\n");
                } else {
                    textArea.append("No duplicates detected.\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            textArea.append("*************** Done ****************");

        } else if ( command.equals(POP_REG_REPLACE_DEF_WITH_TEMP) ) {

            try {
                createDbfFromMySQLTemp();   // creates deletefromdefdb.dbf
            } catch (Exception e) {
                e.printStackTrace();
            }

            // delete from MySQL_opslag by IDnr from deletefromdefdb.dbf:
            List<IDnr> idNrList = Utils.createObjects("nl.iisg.alfalabFrontEnd.IDnr", INPUT_DIRECTORY);
            deleteFromBTables(idNrList);

            // append to MySQL_opslag:
            appendToMySQL_opslagTable();

        } else if ( command.equals(MISC_PRINT_HSN_POP_REG_ALL) ) {

            List<Person> persons = new ArrayList<Person>();
            List<PersonDynamic> personsDynamic = new ArrayList<PersonDynamic>();
            List<Registration> registrations = new ArrayList<Registration>();
            List<RegistrationAddress> registrationAddresses = new ArrayList<RegistrationAddress>();
            List<OP> ops = new ArrayList<OP>();

            Initialiser i = new Initialiser();

            // Load B*-tables and reference tables

            i.loadTables(INPUT_DIRECTORY, persons, personsDynamic, registrations, registrationAddresses);

            // Link the objects

            i.linkObjects(ops, persons, personsDynamic, registrations, registrationAddresses);

            textArea.append("Server creating print file..");
            printHSNPopRegPrintAll(ops, persons, personsDynamic, registrations, registrationAddresses);
            textArea.append("*************** Done ****************");

//            deleteTempFiles();

        } else if ( command.equals(MISC_PRINT_HSN_POP_REG_ERRORS) ) {

            List<Person> persons = new ArrayList<Person>();
            List<PersonDynamic> personsDynamic = new ArrayList<PersonDynamic>();
            List<Registration> registrations = new ArrayList<Registration>();
            List<RegistrationAddress> registrationAddresses = new ArrayList<RegistrationAddress>();
            List<OP> ops = new ArrayList<OP>();

            Initialiser i = new Initialiser();

            // Load B*-tables and reference tables
            i.loadTables(INPUT_DIRECTORY, persons, personsDynamic, registrations, registrationAddresses);

            // Link the objects
            i.linkObjects(ops, persons, personsDynamic, registrations, registrationAddresses);

            textArea.append("Server creating print file..");
            printHSNPopRegErrors(ops, persons, personsDynamic, registrations, registrationAddresses);
            textArea.append("*************** Done ****************");

            sendFileToClient(new File(INPUT_DIRECTORY + "\\testoutput.txt"));
//            deleteTempFiles();

        } else if( command.equals(MISC_PRINT_HSN_POP_REG_SELECTED) ) {

            int errorLevel = in.readInt();
            System.out.println("Error level: " + errorLevel);

            List<Person> persons = new ArrayList<Person>();
            List<PersonDynamic> personsDynamic = new ArrayList<PersonDynamic>();
            List<Registration> registrations = new ArrayList<Registration>();
            List<RegistrationAddress> registrationAddresses = new ArrayList<RegistrationAddress>();
            List<OP> ops = new ArrayList<OP>();

            Initialiser i = new Initialiser();

            // Load B*-tables and reference tables
            i.loadTables(INPUT_DIRECTORY, persons, personsDynamic, registrations, registrationAddresses);

            // Link the objects
            i.linkObjects(ops, persons, personsDynamic, registrations, registrationAddresses);

            textArea.append("Server creating print file..");
            printHSNPopRegPrintSelectedErrors(ops, persons, personsDynamic, registrations, registrationAddresses, errorLevel);
            textArea.append("*************** Done ****************");

        } else {

            response = "Unknown command" + command + " - " + command.length() + "\n";
            textArea.append(response);

        }
    }

    private void createDbfFromDeletedIdnrs(List<IDnr> idNrList) throws Exception {
        EntityManager emMySQLOpslag = nl.iisg.ids03.Utils.getEm_mysql_opslag_org();
        Query qOpslag;
        List<Person> deletedPersons = null;
        List<PersonDynamic> personDynamics = null;
        List<Registration> registrations = null;
        List<RegistrationAddress> registrationAddresses = null;

        DBFWriter dbfWriter = new DBFWriter();
        CreateB2Dbf createB2Dbf = new CreateB2Dbf();
        CreateB3Dbf createB3Dbf = new CreateB3Dbf();
        CreateB4Dbf createB4Dbf = new CreateB4Dbf();
        CreateB6Dbf createB6Dbf = new CreateB6Dbf();

        for(IDnr idnr : idNrList){
            qOpslag = emMySQLOpslag.createQuery(AppendToOpslag.SELECT_BY_IDNR_FROM_B2 + idnr + "'");
            deletedPersons = (List<Person>) qOpslag.getResultList();
        }
        createB2Dbf.createDbf(dbfWriter, deletedPersons);
        FileOutputStream fos = new FileOutputStream(INPUT_DIRECTORY + File.separator + "B2.DBF" );
        dbfWriter.write(fos);
        fos.close();

        dbfWriter = new DBFWriter();
        for(IDnr idnr : idNrList){
            qOpslag = emMySQLOpslag.createQuery(AppendToOpslag.SELECT_BY_IDNR_FROM_B3 + idnr + "'");
            personDynamics = qOpslag.getResultList();
        }
        createB3Dbf.createDbf(dbfWriter, personDynamics);
        fos = new FileOutputStream(INPUT_DIRECTORY + File.separator + "B3.DBF");
        dbfWriter.write(fos);
        fos.close();

        dbfWriter = new DBFWriter();
        for(IDnr idnr : idNrList){
            qOpslag = emMySQLOpslag.createQuery(AppendToOpslag.SELECT_BY_IDNR_FROM_B4 + idnr + "'");
            registrations = qOpslag.getResultList();
        }
        createB4Dbf.createDbf(dbfWriter, registrations);
        fos = new FileOutputStream(INPUT_DIRECTORY + File.separator + "B4.DBF");
        dbfWriter.write(fos);
        fos.close();

        dbfWriter = new DBFWriter();
        for(IDnr idnr : idNrList){
            qOpslag = emMySQLOpslag.createQuery(AppendToOpslag.SELECT_BY_IDNR_FROM_B6 + idnr + "'");
            registrationAddresses = qOpslag.getResultList();
        }
        createB6Dbf.createDbf(dbfWriter, registrationAddresses );
        fos = new FileOutputStream(INPUT_DIRECTORY + File.separator + "B6.DBF");
        dbfWriter.write(fos);
        fos.close();
    }

    private void createDbfFromMySQLTemp() throws Exception {

        EntityManager emMySQLOpslag = nl.iisg.ids03.Utils.getEm_original_tabs();
        Query qTemp;
        List<Person> result;

        DBFWriter dbfWriter = new DBFWriter();
        DBFField dbfFields[] = new DBFField[1];
        dbfFields[0] = new DBFField();
        dbfFields[0].setDataType(DBFField.FIELD_TYPE_N);
        dbfFields[0].setFieldName("IDNR");
        dbfFields[0].setFieldLength(12);
        dbfWriter.setFields(dbfFields);

        qTemp = emMySQLOpslag.createQuery(AppendToOpslag.SELECT_ALL_FROM_B2_ST);
        result = (List<Person>) qTemp.getResultList();
        for (Person person : result) {
            Object rowData[] = new Object[1];
            rowData[0] = person.getKeyToRP();
            dbfWriter.addRecord(rowData);

        }

        FileOutputStream fos = new FileOutputStream(INPUT_DIRECTORY + File.separator + "deletefromdefdb.dbf");
        dbfWriter.write(fos);
        fos.close();

    }


    private void printHSNPopRegPrintSelectedErrors(List<OP> ops,
                                                   List<Person> persons,                               // b2
                                                   List<PersonDynamic> personsDynamic,                 // b3
                                                   List<Registration> registrations,                   // b4
                                                   List<RegistrationAddress> registrationAddresses,
                                                   int errorLevel) {

        int command = errorLevel;
        boolean printFout = false;
        String output = "";


        EntityManager em = nl.iisg.ids03.Utils.getEm_log();
        Query q = em.createQuery("select m from Message m");
        List<Message> messages = q.getResultList();
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(INPUT_DIRECTORY + File.separator + "testoutput.txt"));

            for (Message m : messages) {
                if (command == 3) {
                    //for(Message m2 : messages){
                    //    if(m.getKeyToRP() == m2.getKeyToRP()){
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
                    //  }
                    //}
                }

                if (command == 4 || command == 7) {
                    printFout = true;
                }

                if (command == 5) {
                    //for(Message m2 : messages){
                    //if(m.getKeyToRP() == m2.getKeyToRP()){
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
                    //}
                    //}
                }

                if (command == 6) {
                    //for(Message m2 : messages){
                    //if(m.getKeyToRP() == m2.getKeyToRP()){
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
                    //}
                    // }
                }

                if (command == 8) {
                    //for(Message m2 : messages){
                    //if(m.getKeyToRP() == m2.getKeyToRP()){
                    if (m.getErrorType().equals("FT")) {
                        printFout = true;
                    }
                    //}
                    //}
                }


                //if (printFout) {
                if (printFout) {
                    //for (Person p : persons) {
                    //    if (p.getKeyToRP() == m.getKeyToRP()) {

                    //if (       p.getDayEntryHead()        == m.getDayEntryHead()
                    //        && p.getMonthEntryHead()      == m.getMonthEntryHead()
                    //        && p.getYearEntryHead()       == m.getYearEntryHead()
                    //        && p.getKeyToSourceRegister() == m.getKeyToSourceRegister() ) {
                    output = "VLGNR \t KODE \t ----------------- MELDINGEN -----------------------------\n";

                    if (m.getNatureOfPerson() == 1 || m.getNatureOfPerson() == 5) {
                        output += m.getKeyToRegistrationPersons() + "\t"
                                + m.getErrorNumber() + "\t"
                                + m.getErrorType() + " "
                                + m.getErrorText() + "\n\n";
                    } else {
                        output += m.getKeyToRegistrationPersons() + "\t"
                                + m.getErrorNumber() + "\t"
                                + m.getErrorType() + " !OP! "
                                + m.getErrorText() + "\n\n";
                    }
                    //} else {
                    //    output = "GEEN MELDINGEN gevonden bij de volgende inschrijving\n";
                    //}

                    try {


                        output += printRgl(m.getKeyToRP(),
                                m.getYearEntryHead(),
                                m.getMonthEntryHead(),
                                m.getDayEntryHead(),
                                m.getKeyToSourceRegister(),
                                persons,
                                registrations,
                                registrationAddresses,
                                personsDynamic);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // }
                    //}
                }

            }

            out.write(output);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


    private void printHSNPopRegPrintAll(List<OP> ops,
                                        List<Person> persons,                               // b2
                                        List<PersonDynamic> personsDynamic,                 // b3
                                        List<Registration> registrations,                   // b4
                                        List<RegistrationAddress> registrationAddresses) {

        String output = "";
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(INPUT_DIRECTORY + "\\testoutput.txt"));
            for (Person p : persons) {
                output = printRgl(p.getKeyToRP(),
                        p.getYearEntryHead(),
                        p.getMonthEntryHead(),
                        p.getDayEntryHead(),
                        p.getKeyToSourceRegister(),
                        persons,
                        registrations,
                        registrationAddresses,
                        personsDynamic);
                // System.out.println(output);
                out.write(output);

            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printHSNPopRegErrors(List<OP> ops,
                                      List<Person> persons,                               // b2
                                      List<PersonDynamic> personsDynamic,                 // b3
                                      List<Registration> registrations,                   // b4
                                      List<RegistrationAddress> registrationAddresses) {  // b6

        String output = "";


        EntityManager em = nl.iisg.ids03.Utils.getEm_log();
        Query q = em.createQuery("select m from Message m");
        List<Message> messages = q.getResultList();

        System.out.println("Opening bfout db..");
        try {

            BufferedWriter out = new BufferedWriter(new FileWriter(INPUT_DIRECTORY + "\\testoutput.txt"));
            for (Message m : messages) {

                output = "VLGNR \t KODE \t ----------------- FOUTTYPE -----------------------------\n";


                /* printft() */
                if (m.getNatureOfPerson() == 1 || m.getNatureOfPerson() == 5) {
                    output += m.getKeyToRegistrationPersons() + "\t"
                            + m.getErrorNumber() + "\t"
                            + m.getErrorType() + " "
                            + m.getErrorText() + "\n\n";
                } else {
                    output += m.getKeyToRegistrationPersons() + "\t"
                            + m.getErrorNumber() + "\t"
                            + m.getErrorType() + " !OP! "
                            + m.getErrorText() + "\n\n";
                }


                output += printRgl(m.getKeyToRP(),
                        m.getYearEntryHead(),
                        m.getMonthEntryHead(),
                        m.getDayEntryHead(),
                        m.getKeyToSourceRegister(),
                        persons,
                        registrations,
                        registrationAddresses,
                        personsDynamic);


                out.write(output);

            }
            out.close();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String printRgl(int keyToRP,
                            int yearEntryHead,
                            int monthEntryHead,
                            int dayEntryHead,
                            int keyToSourceRegister,
                            List<Person> persons,
                            List<Registration> registrations,
                            List<RegistrationAddress> registrationAddresses,
                            List<PersonDynamic> personsDynamic)
    {
        String output = "";
        boolean GGR = false;
        for(Person p : persons){
            GGR = false;
            if(keyToRP == p.getKeyToRP()
                        && yearEntryHead == p.getYearEntryHead()
                        && monthEntryHead == p.getMonthEntryHead()
                        && dayEntryHead == p.getDayEntryHead()
                        && keyToSourceRegister == p.getKeyToSourceRegister()
                        && p.getNatureOfPerson() == 1)
                {
                    if(p.getOrderNumber().equals("GGR")) GGR = true;
                    output += "IDnr: " + p.getKeyToRP()
                            + "\tBRONnr: " + p.getKeyToSourceRegister()
                            + "\tHOOFDdatum: " + String.format("%02d-%02d-%04d", p.getDayEntryHead(), p.getMonthEntryHead(), p.getYearEntryHead())
                           // + "\tOPdatum: " + String.format("%02d-%02d-%04d", p.getDayEntryRP(), p.getMonthEntryRP(), p.getYearEntryRP()) // todo: b2fdbg b2fmbg b2fjbg
                            + "\n";

                }
            }

            Ref_AINB ainb = Ref.getAINB(keyToSourceRegister);
            if (ainb != null) {
                String btp = "";
                if (ainb.getTypeRegister().equals("A")) {
                    btp = "Alleenstaandenregister ";
                } else if (ainb.getTypeRegister().equals("B")) {
                    btp = "Bevolkingsregister     ";
                } else if (ainb.getTypeRegister().equals("C")) {
                    btp = "Bev.register 1850-1862 ";
                } else if (ainb.getTypeRegister().equals("G")) {
                    btp = "Gezinskaart            ";
                } else if (ainb.getTypeRegister().equals("I")) {
                    btp = "Individueel kaartje    ";
                }

                output += btp + " "
                        + ainb.getMunicipality() + " "
                        + ainb.getStartYearRegister() + "-" + ainb.getEndYearRegister();

                if (ainb.getTypeRegister().equals("A") || ainb.getTypeRegister().equals("B")
                        || ainb.getTypeRegister().equals("C") || ainb.getTypeRegister().equals("D")) {

                    output += " [" + "p.getPageNumberOfSource()" + "/" + "p.getNumberOfHousehold()" + " b1gwbg? " + " b1ivgb? " + "]";  // todo: b2pgbg b2vhbg b1gwbg b1ivgb

                } else if (ainb.getTypeRegister().equals("G") || ainb.getTypeRegister().equals("I")) {

                    for (Registration r : registrations) {
                        if (r.getKeyToRP() == keyToRP
                                && r.getYearEntryHead() == yearEntryHead
                                && r.getMonthEntryHead() == monthEntryHead
                                && r.getDayEntryHead() == dayEntryHead) {

                            output += "    GK-info: " + r.getInfoFamilyCardsSystem();
                            if (!r.getSpecialDataEntryCodes().equals(""))
                                output += "   Verdere info: " + r.getSpecialDataEntryCodes();


                        }
                    }

                }
            } else {

                output += "   !!! Bronnummer niet in "; // todo anaam, ext ?

            }

            if(GGR) output += "   INHOUD IS GEGENEREERD";

            output += "\n";


            for (Registration r : registrations) {
                if (r.getKeyToRP() == keyToRP
                        && r.getYearEntryHead() == yearEntryHead
                        && r.getMonthEntryHead() == monthEntryHead
                        && r.getDayEntryHead() == dayEntryHead) {

                    if (r.getOrderNumber().equals("GGR")) {
                        output += "     INHOUD IS GEGENEREERD\n";
                    }

                    if (!r.getRemarks().equals("") && r.getSpecialDataEntryCodes().equals("")) { // wel bijzonderheden geen speciale codes

                        output += "\tBijzonderheden bij deze inschrijving: " + r.getRemarks() + "\n";
                        output += "\tGeen speciale code bij deze inschrijving\n";

                    } else if (r.getRemarks().equals("") && !r.getSpecialDataEntryCodes().equals("")) { // geen bijzonderheden wel speciale codes

                        output += "\tGeen bijzonderheden bij deze inschrijving (als geheel)\n";
                        output += "\tSpeciale code bij deze inschrijving:   " + r.getSpecialDataEntryCodes() + "\n";

                    } else if (!r.getRemarks().equals("") && !r.getSpecialDataEntryCodes().equals("")) { // wel bijzonderheden wel speciale codes

                        output += "\tBijzonderheden bij deze inschrijving: " + r.getRemarks() + "\n";
                        output += "\tSpeciale code bij deze inschrijving:  " + r.getSpecialDataEntryCodes() + "\n";

                    } else if (r.getRemarks().equals("") && r.getSpecialDataEntryCodes().equals("")) { //geen bijzonderheden geen speciale codes

                        output += "\tGeen bijzonderheden en geen speciale code bij deze inschrijving\n";

                    }
                    output += "VLGNR\n";
                }

            }


            for (Person p : persons) {
//                System.out.println("\n****p****\n");
                if (keyToRP == p.getKeyToRP()
                        && yearEntryHead == p.getYearEntryHead()
                        && monthEntryHead == p.getMonthEntryHead()
                        && dayEntryHead == p.getDayEntryHead()
                        && keyToSourceRegister == p.getKeyToSourceRegister()) {

                    output += "\t";
                    output += p.getKeyToRegistrationPersons() + " "
                            + "Ins. reg.: " + p.getDayOfRegistration() + "/"
                            + p.getMonthOfRegistration() + "/"
                            + p.getYearOfRegistration() + "   ";

                    if (p.getDayOfRegistrationAfterInterpretation() > 0
                            || p.getMonthOfRegistrationAfterInterpretation() > 0
                            || p.getYearOfRegistrationAfterInterpretation() > 0
                            || p.getDayOfRegistrationAfterInterpretation() == -3
                            || p.getMonthOfRegistrationAfterInterpretation() == -3
                            || p.getYearOfRegistrationAfterInterpretation() == -3) {

                        output += "Ins. datum INTERPRETATIE: " + p.getDayOfRegistrationAfterInterpretation() + "/"
                                + p.getMonthOfRegistrationAfterInterpretation() + "/"
                                + p.getYearOfRegistrationAfterInterpretation() + "    ";
                    }

                    output += p.getFamilyName() + ", " + p.getFirstName() + "\t\t\t";

                    output += p.getSex() + " ******* ";
                    output += p.getDayOfBirth() + "/" + p.getMonthOfBirth() + "/" + p.getYearOfBirth();

                    if (p.getDayOfBirthAfterInterpretation() > 0
                            || p.getMonthOfBirthAfterInterpretation() > 0
                            || p.getYearOfBirthAfterInterpretation() > 0
                            || p.getDayOfBirthAfterInterpretation() == -3
                            || p.getMonthOfBirthAfterInterpretation() == -3
                            || p.getYearOfBirthAfterInterpretation() == -3) {

                        output += "        ******* INTERPRETATIE: ";
                        output += p.getDayOfBirthAfterInterpretation() + "/" + p.getMonthOfBirthAfterInterpretation() + "/" + p.getYearOfBirthAfterInterpretation();
                        output += " te " + p.getPlaceOfBirth() + " "; // todo @cor : + b2glnr niet bekend, toevoegen?
                    }

                    if (ainb.getTypeRegister().equals("C")
                            && !p.getLegalPlaceOfLivingInCodes().equals("")
                            && !p.getLegalPlaceOfLivingInCodes().equals("N"))
                    {
                        output += "WD: " + p.getLegalPlaceOfLivingInCodes() + "  ";
                    } else if(!p.getLegalPlaceOfLiving().equals("")
                            && !p.getLegalPlaceOfLiving().equals("N")){
                        output += "WD: " + p.getLegalPlaceOfLiving() + "  ";
                    }

                    if(!p.getNationality().equals("")){
                        output += "\tNat: " + p.getNationality();
                    }

                    if (p.getYearOfDecease() != 0) {
                        output += "\tOVL: " + p.getDayOfDecease() + "/" + p.getMonthOfDecease() + "/" + p.getYearOfDecease();
                    }

                    if (!p.getRemarks().equals("") && !p.getRemarks().equals("N")) {
                        output += "\tAanmerkingen: " + p.getRemarks();
                    }

                    if (!p.getRemarks2().equals("")) {
                        output += "Bijzonderheden persoon: " + p.getRemarks2();
                    }

                    output += "\n";

                    boolean dynFound = false;
                    // zoek dynamische gegevens bij persoon:
                    for (PersonDynamic personDynamic : personsDynamic) {
                        String outputDyn = "\t\t";
                        if (
                                           personDynamic.getKeyToRP()                   == p.getKeyToRP()                    // midnr
                                        && personDynamic.getYearEntryHead()             == p.getYearEntryHead()              // mb2jibg
                                        && personDynamic.getMonthEntryHead()            == p.getMonthEntryHead()             // mb2mibg
                                        && personDynamic.getDayEntryHead()              == p.getDayEntryHead()               // mb2dibg
                                        && personDynamic.getKeyToSourceRegister()       == ainb.getKeyToSourceRegister()     // mb1idbg
                                        && personDynamic.getKeyToRegistrationPersons()  == p.getKeyToRegistrationPersons()   // mb2rnbg
//                                        && personDynamic.getDynamicDataType()           == 1                                 // mb3type       // todo: correct?
//                                        && personDynamic.getDynamicDataSequenceNumber() == 1                                 // mb2vrnr
                                )
                        {
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
                            if (personDynamic.getDynamicDataType() == 1) {
                                if (personDynamic.getDynamicDataSequenceNumber() == 1) {
                                    outputDyn += "1e relatie (niet gedat.) ";
                                    outputDyn += safeGetContentOfDynamicData1(personDynamic.getContentOfDynamicData());
                                    //outputDyn += Const.b3kode1[personDynamic.getContentOfDynamicData()];   // if vanwege null pointer errors, stond niet in originele code
                                    //outputDyn += personDynamic.getDynamicData2(); // todo: vragen aan Kees: wat gebeurd er in Clipper code?
                                } else {
                                    if (
                                               personDynamic.getDayOfMutationAfterInterpretation() > 0
                                            || personDynamic.getMonthOfMutationAfterInterpretation() > 0
                                            || personDynamic.getYearOfMutationAfterInterpretation() > 0
                                            || personDynamic.getDayOfMutationAfterInterpretation() == -3
                                            || personDynamic.getMonthOfMutationAfterInterpretation() == -3
                                            || personDynamic.getYearOfMutationAfterInterpretation() == -3) {

                                        outputDyn += personDynamic.getDynamicDataSequenceNumber() + "e relatie: ";
                                        outputDyn += safeGetContentOfDynamicData1(personDynamic.getContentOfDynamicData());
                                        //outputDyn += Const.b3kode1[personDynamic.getContentOfDynamicData()];
                                        outputDyn += personDynamic.getDynamicData2();
                                    } else {
                                        outputDyn += personDynamic.getDynamicDataSequenceNumber() + "e relatie: ";
                                        outputDyn += mutDatum;
                                        outputDyn += safeGetContentOfDynamicData1(personDynamic.getContentOfDynamicData());
                                        //outputDyn += Const.b3kode1[personDynamic.getContentOfDynamicData()];
                                        outputDyn += personDynamic.getDynamicData2();
                                    }

                                }
                            }
                            else if (personDynamic.getDynamicDataType() == 2) {
                                if (personDynamic.getDynamicDataSequenceNumber() == 1) {
                                    outputDyn += "1e burg.st: " + mutDatum + " " + safeGetContentOfDynamicData2(personDynamic.getContentOfDynamicData()); //Const.b3kode2[personDynamic.getContentOfDynamicData()];
                                } else {
                                    outputDyn += personDynamic.getDynamicDataSequenceNumber() + "e burg.st: ";
                                    outputDyn += mutDatum;
                                    outputDyn += safeGetContentOfDynamicData2(personDynamic.getContentOfDynamicData());
                                    //outputDyn += Const.b3kode2[personDynamic.getContentOfDynamicData()];
                                }
                            } else if (personDynamic.getDynamicDataType() == 3) {
                                if (personDynamic.getDynamicDataSequenceNumber() == 1) {
                                    outputDyn += "1e kergen: " + personDynamic.getDynamicData2();
                                } else {
                                    outputDyn += personDynamic.getDynamicDataSequenceNumber() + "e kerkgen: ";
                                    outputDyn += personDynamic.getDynamicData2();
                                }
                            } else if (personDynamic.getDynamicDataType() == 5) {
                                String positie = "";
                                if (personDynamic.getContentOfDynamicData() == 1) positie = "[h]";
                                if (personDynamic.getContentOfDynamicData() == 2) positie = "[o]";
                                if (personDynamic.getContentOfDynamicData() == 3) positie = "[n]";

                                if (personDynamic.getDynamicDataSequenceNumber() == 1) {
                                    outputDyn += "1e beroep: " + personDynamic.getDynamicData2();
                                } else {
                                    outputDyn += personDynamic.getDynamicDataSequenceNumber() + "e beroep: ";
                                    outputDyn += personDynamic.getDynamicData2();
                                }
                                outputDyn += positie;

                            } else if (personDynamic.getDynamicDataType() == 6) {
                                if (personDynamic.getDynamicDataSequenceNumber() == 1) {
                                    outputDyn += "1e herkmst: " + mutDatum + " " + personDynamic.getDynamicData2();
                                } else {
                                    outputDyn += personDynamic.getDynamicDataSequenceNumber() + "e herkmst: ";
                                    outputDyn += mutDatum + " ";
                                    outputDyn += personDynamic.getDynamicData2();
                                }
                            } else if (personDynamic.getDynamicDataType() == 7) {
                                if (personDynamic.getDynamicDataSequenceNumber() == 1) {
                                    outputDyn += "1e vertrek: " + mutDatum + " " + personDynamic.getDynamicData2();
                                } else {
                                    outputDyn += personDynamic.getDynamicDataSequenceNumber() + "e vertrek: ";
                                    outputDyn += mutDatum + " ";
                                    outputDyn += personDynamic.getDynamicData2();
                                }
                            }


                            output += outputDyn + "\n";
                            //output += "\n**** dyn ***\n";
                        }

                    }

                    if(!dynFound){
                        output += "Geen relatie tot hoofd gevonden bij deze persoon\n"
                                + " (rest dynamische gegevens niet opgenomen)\n";
                    }
                }


            }


            for(RegistrationAddress rAddr : registrationAddresses){
                if(keyToRP == rAddr.getKeyToRP()
                        && dayEntryHead == rAddr.getDayEntryHead()
                        && monthEntryHead == rAddr.getMonthEntryHead()
                        && yearEntryHead == rAddr.getYearEntryHead()){
                    if(rAddr.getDayOfAddressAfterInterpretation() > 0
                            || rAddr.getMonthOfAddressAfterInterpretation() > 0
                            || rAddr.getYearOfAddressAfterInterpretation() > 0
                            || rAddr.getDayOfAddressAfterInterpretation() == -3
                            || rAddr.getMonthOfAddressAfterInterpretation() == -3
                            || rAddr.getYearOfAddressAfterInterpretation() == -3){

                        output += "Adres:  " + rAddr.getDayOfAddress() + "/" + rAddr.getMonthOfAddress() + "/" + rAddr.getYearOfAddress() + "  ";
                        output += "INTERPRETATIE: " + rAddr.getDayOfAddressAfterInterpretation() + "/" + rAddr.getMonthOfAddressAfterInterpretation();
                        output += "/" + rAddr.getYearOfAddressAfterInterpretation() + "  ";
                        output += "Type: " + rAddr.getAddressType() + "  " + rAddr.getNameOfStreet() + " " + rAddr.getNumber() + " " +rAddr.getAdditionToNumber();
                    } else {
                        output += "Adres:  " + rAddr.getDayOfAddress() + "/" + rAddr.getMonthOfAddress() + "/" + rAddr.getYearOfAddress() + "  ";
                        output += "Type: " + rAddr.getAddressType() + "  " + rAddr.getNameOfStreet() + " " + rAddr.getNumber() + " " +rAddr.getAdditionToNumber();
                    }

                }


            }

            output += "\n\n";

            return output;

    }

    private String safeGetContentOfDynamicData1(int contentOfDynamicData) {

        if( contentOfDynamicData > 0 ){
            return ConstRelations2.b3kode1[contentOfDynamicData];
        } else {
            return Integer.toString(contentOfDynamicData);
        }

    }

    private String safeGetContentOfDynamicData2(int contentOfDynamicData) {

        if ( contentOfDynamicData > 0 ) {
            return ConstRelations2.b3kode2[contentOfDynamicData];
        } else {
            return Integer.toString(contentOfDynamicData);
        }

    }


    private void createMySQL_opslagTable(){

        textArea.append("Creating tables (if not existing..)\n");
        try{

            EntityManager em = nl.iisg.ids03.Utils.getEm_mysql_opslag();
            em.getTransaction().begin();
            Query query = em.createNativeQuery(CreateBTables.B2_ST);
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

            textArea.append("Done.\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void appendToMySQL_opslagTable() {

        EntityManager emMySQLTemp_st = nl.iisg.ids03.Utils.getEm_tabs();
        EntityManager emMySQLOpslag_st = nl.iisg.ids03.Utils.getEm_mysql_opslag();
        EntityManager emMySQLTemp = nl.iisg.ids03.Utils.getEm_original_tabs();
        EntityManager emMySQLOpslag = nl.iisg.ids03.Utils.getEm_mysql_opslag_org();


        textArea.append("Appending to MySQL_opslag started...\n");

        try {
            textArea.append("Appending B2 table\n");
            appendToMySQL_opslagTableB2(emMySQLOpslag, emMySQLTemp);

            textArea.append("Appending B3 table\n");
            appendToMySQL_opslagTableB3(emMySQLOpslag, emMySQLTemp);

            textArea.append("Appending B4 table\n");
            appendToMySQL_opslagTableB4(emMySQLOpslag, emMySQLTemp);

            textArea.append("Appending B6 table\n");
            appendToMySQL_opslagTableB6(emMySQLOpslag, emMySQLTemp);


            textArea.append("Appending B2_st table\n");
            appendToMySQL_opslagTableB2_st(emMySQLOpslag_st, emMySQLTemp_st);

            textArea.append("Appending B3_st table\n");
            appendToMySQL_opslagTableB3_st(emMySQLOpslag_st, emMySQLTemp_st);

            textArea.append("Appending B4_st table\n");
            appendToMySQL_opslagTableB4_st(emMySQLOpslag_st, emMySQLTemp_st);

            textArea.append("Appending B6_st table\n");
            appendToMySQL_opslagTableB6_st(emMySQLOpslag, emMySQLTemp_st);

            // close connections:
            emMySQLOpslag.clear();
            emMySQLTemp.clear();

            textArea.append("Append to MySQL_opslag done.\n");

        } catch (Exception e){

            textArea.append("Append to MySQL_opslag failed.\n");
            e.printStackTrace();

        }

    }

    private void appendToMySQL_opslagTableB2(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception{

        List<Person> personsTemp;

        Query q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_B2);
        personsTemp = (List<Person>) q.getResultList();

        textArea.append("Nr of records to append: " + personsTemp.size() + "\n");

        emMySQLOpslag.getTransaction().begin();
        for (Person person : personsTemp) {
            emMySQLOpslag.persist(person);
        }

        emMySQLOpslag.getTransaction().commit();
        emMySQLOpslag.clear();

    }

    private void appendToMySQL_opslagTableB3(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception{

        List<PersonDynamic> personDynamics;

        Query q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_B3);
        personDynamics = q.getResultList();

        textArea.append("Nr of records to append: " + personDynamics.size() + "\n");

        emMySQLOpslag.getTransaction().begin();
        for(PersonDynamic personDynamic : personDynamics){
            emMySQLOpslag.persist(personDynamic);
        }
        emMySQLOpslag.getTransaction().commit();
        emMySQLOpslag.clear();

    }


    private void appendToMySQL_opslagTableB4(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception{

        List<Registration> registrations;

        Query q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_B4);
        registrations = q.getResultList();

        textArea.append("Nr of records to append: " + registrations.size() + "\n");

        emMySQLOpslag.getTransaction().begin();
        for(Registration registration : registrations){
            emMySQLOpslag.persist(registration);
        }
        emMySQLOpslag.getTransaction().commit();
        emMySQLOpslag.clear();

    }

    private void appendToMySQL_opslagTableB6(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception{

        List<RegistrationAddress> registrationAddresses;

        Query q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_B6);
        registrationAddresses = q.getResultList();

        emMySQLOpslag.getTransaction().begin();
        for(RegistrationAddress registrationAddress : registrationAddresses){
            emMySQLOpslag.persist(registrationAddress);
        }
        emMySQLOpslag.getTransaction().commit();
        emMySQLOpslag.clear();
        
    }

    private void appendToMySQL_opslagTableB2_st(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception{
        List<PersonStandardized> personsStandardizedTemp;

        Query q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_B2_ST);
        personsStandardizedTemp = (List<PersonStandardized>) q.getResultList();

        textArea.append("Nr of records to append: " + personsStandardizedTemp.size() + "\n");

        emMySQLOpslag.getTransaction().begin();
        for (PersonStandardized person : personsStandardizedTemp) {
            emMySQLOpslag.persist(person);
        }

        emMySQLOpslag.getTransaction().commit();
        emMySQLOpslag.clear();

    }

    private void appendToMySQL_opslagTableB3_st(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception{

        List<PDS_CivilStatus> civilStatuses;
        List<PDS_OccupationalTitle> occupationalTitles;
        List<PDS_ParentsAndChildren> parentsAndChildrens;
        List<PDS_PlaceOfDestination> placeOfDestinations;
        List<PDS_PlaceOfOrigin> placeOfOrigins;
        List<PDS_RelationToHead> relationToHeads;
        List<PDS_AllToAll> allToAlls;
        List<PDS_Religion> religions;

        Query q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_PDS_CIVILSTATUS);
        civilStatuses = (List<PDS_CivilStatus>) q.getResultList();
        q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_PDS_OCCUPATIONALTITLE);
        occupationalTitles = (List<PDS_OccupationalTitle>) q.getResultList();
        q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_PDS_PARENTSANDCHILDREN);
        parentsAndChildrens = (List<PDS_ParentsAndChildren>) q.getResultList();
        q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_PDS_PLACEOFDESTINATION);
        placeOfDestinations = (List<PDS_PlaceOfDestination>) q.getResultList();
        q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_PDS_PLACEOFORIGIN);
        placeOfOrigins = (List<PDS_PlaceOfOrigin>) q.getResultList();
        q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_PDS_RELATIONTOHEAD);
        relationToHeads = (List<PDS_RelationToHead>) q.getResultList();
        q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_PDS_ALLTOALL);
        allToAlls = (List<PDS_AllToAll>) q.getResultList();
        q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_PDS_RELIGION);
        religions = (List<PDS_Religion>) q.getResultList();


        emMySQLOpslag.getTransaction().begin();
        for (PDS_CivilStatus civilStatus : civilStatuses) {
            emMySQLOpslag.persist(civilStatus);
        }
        emMySQLOpslag.getTransaction().commit();
        emMySQLOpslag.clear();


        emMySQLOpslag.getTransaction().begin();
        for (PDS_OccupationalTitle occupationalTitle : occupationalTitles) {
            emMySQLOpslag.persist(occupationalTitle);
        }
        emMySQLOpslag.getTransaction().commit();
        emMySQLOpslag.clear();


        emMySQLOpslag.getTransaction().begin();
        for (PDS_ParentsAndChildren parentsAndChildren : parentsAndChildrens) {
            emMySQLOpslag.persist(parentsAndChildren);
        }
        emMySQLOpslag.getTransaction().commit();
        emMySQLOpslag.clear();


        emMySQLOpslag.getTransaction().begin();
        for (PDS_PlaceOfDestination placeOfDestination : placeOfDestinations) {
            emMySQLOpslag.persist(placeOfDestination);
        }
        emMySQLOpslag.getTransaction().commit();
        emMySQLOpslag.clear();


        emMySQLOpslag.getTransaction().begin();
        for (PDS_PlaceOfOrigin placeOfOrigin : placeOfOrigins) {
            emMySQLOpslag.persist(placeOfOrigin);
        }
        emMySQLOpslag.getTransaction().commit();
        emMySQLOpslag.clear();


        emMySQLOpslag.getTransaction().begin();
        for (PDS_RelationToHead relationToHead : relationToHeads) {
            emMySQLOpslag.persist(relationToHead);
        }
        emMySQLOpslag.getTransaction().commit();
        emMySQLOpslag.clear();


        emMySQLOpslag.getTransaction().begin();
        for (PDS_AllToAll allToAll : allToAlls) {
            emMySQLOpslag.persist(allToAll);
        }
        emMySQLOpslag.getTransaction().commit();
        emMySQLOpslag.clear();


        emMySQLOpslag.getTransaction().begin();
        for (PDS_Religion religion : religions) {
            emMySQLOpslag.persist(religion);
        }
        emMySQLOpslag.getTransaction().commit();
        emMySQLOpslag.clear();

    }

    private void appendToMySQL_opslagTableB4_st(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception{
        List<RegistrationStandardized> registrationStandardizedTemp;
        Query q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_B4_ST);
        registrationStandardizedTemp = (List<RegistrationStandardized>) q.getResultList();


        emMySQLOpslag.getTransaction().begin();
        for (RegistrationStandardized registrationStandardized : registrationStandardizedTemp) {
            emMySQLOpslag.persist(registrationStandardized);
        }
        emMySQLOpslag.getTransaction().commit();
        emMySQLOpslag.clear();

    }

    private void appendToMySQL_opslagTableB6_st(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception{
        List<RegistrationAddressStandardized> registrationAddressStandardizedTemp;
        Query q = emMySQLTemp.createQuery(AppendToOpslag.SELECT_ALL_FROM_B6_ST);
        registrationAddressStandardizedTemp = (List<RegistrationAddressStandardized>) q.getResultList();

        emMySQLOpslag.getTransaction().begin();
        for (RegistrationAddressStandardized registrationAddressStandardized : registrationAddressStandardizedTemp) {
            emMySQLOpslag.persist(registrationAddressStandardized);
        }
        emMySQLOpslag.getTransaction().commit();
        emMySQLOpslag.clear();

    }


    private boolean checkForDuplicatesTempDef() throws Exception {
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

        boolean duplicateFound = false;

        for (PersonStandardized personStandardized : personsStandardizedTemp) {
//              System.out.println(personStandardized.getPersonID());
            qOpslag = emMySQLOpslag.createQuery(AppendToOpslag.SELECT_BY_IDNR_FROM_B2_ST + personStandardized.getKeyToRP() + "'");
            List<PersonStandardized>result = (List<PersonStandardized>)qOpslag.getResultList();

            if (result.size() != 0){
                duplicateFound = true;
                Object rowData[] = new Object[1];
                rowData[0] = personStandardized.getKeyToRP();
                dbfWriter.addRecord(rowData);
            }
        }

        FileOutputStream fos = new FileOutputStream( INPUT_DIRECTORY + "\\double_idnrs.dbf" );
        dbfWriter.write(fos);
        fos.close();

        return duplicateFound;
    }

    private void deleteFromBTable(List<IDnr> idNrList, EntityManager em, String deleteQuery){
        Query query;

        for(IDnr idNr : idNrList){
            query = em.createNativeQuery(deleteQuery + idNr.getIdnr() + "';");
            query.executeUpdate();
        }

    }

    private void deleteFromBTables(List<IDnr> idNrList){
        removeDuplicates(idNrList);

        for(IDnr idNr : idNrList){
            textArea.append(idNr.getIdnr() + "\n");
        }

        try{
            // B* tables:
            EntityManager em = nl.iisg.ids03.Utils.getEm_mysql_opslag_org();
            em.getTransaction().begin();
            deleteFromBTable(idNrList, em, DeleteFromBTables.DELETE_FROM_B2);
            deleteFromBTable(idNrList, em, DeleteFromBTables.DELETE_FROM_B3);
            deleteFromBTable(idNrList, em, DeleteFromBTables.DELETE_FROM_B4);
            deleteFromBTable(idNrList, em, DeleteFromBTables.DELETE_FROM_B6);
            em.getTransaction().commit();
            em.clear();



            // B*_st tables:
            EntityManager em_st = nl.iisg.ids03.Utils.getEm_mysql_opslag();
            em_st.getTransaction().begin();
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B2_ST);
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B311_ST);
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B312_ST);
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B32_ST);
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B33_ST);
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B34_ST);
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B35_ST);
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B36_ST);
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B37_ST);
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B4_ST);
            deleteFromBTable(idNrList, em_st, DeleteFromBTables.DELETE_FROM_B6_ST);

           // em.getTransaction().rollback();
            em_st.getTransaction().commit();
            em.clear();

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeDuplicates(List<IDnr> idNrList) {
        HashSet<IDnr> h = new HashSet<IDnr>(idNrList);
        idNrList.clear();
        idNrList.addAll(h);
    }

    public void listenSocket() {
        while (true) {
            try {
                server = new ServerSocket(4444);
            } catch (IOException e) {
                System.out.println("Could not listen on port 4444");
                System.exit(-1);
            }

            try {
                client = server.accept();
            } catch (IOException e) {
                System.out.println("Accept failed: 4444");
                System.exit(-1);
            }

            try {
                in = new DataInputStream(client.getInputStream());
                out = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                System.out.println("Accept failed: 4444");
                System.exit(-1);
            }

            while (true) {
                try {
                    line = in.readUTF();
                    //System.out.println("Rx: " + line);
                    dispatchCommand(line);
                } catch (IOException e) {
                    e.printStackTrace();
                    textArea.append("Connection with client lost\n");
                    closeSocket();
                    break;
                }
            }
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
    public void insertUpdate(DocumentEvent e) {
        try {
            if(workerThread != null && !workerThread.isAlive()) deleteTempFiles();
            out.writeUTF(textArea.getText(textArea.getText().length() - e.getLength(), e.getLength()));
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        } catch (IOException ei){
            ei.printStackTrace();
        }
    }
    public void removeUpdate(DocumentEvent e) {
    }
    public void changedUpdate(DocumentEvent e) {
    }

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
            server.close();
        } catch (IOException e) {
            System.out.println("Could not close.");
            System.exit(-1);
        }
    }


    private void receiveFileFromClient() {

        byte[] myByteArray = new byte[MAX_FILE_SIZE];
        int bytesRead = 0;
        int current = 0;

        try {
            long fileLength = in.readLong();
            String filename = in.readUTF();
            textArea.append("Server receiving file \"" + filename + "\" from client.\n");

            File f;
            f = new File(INPUT_DIRECTORY + "\\" + filename);
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(f);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            while (current < fileLength) {
                bytesRead = in.read(myByteArray, current, ((int) fileLength - current));
                if (bytesRead < 0) {
                    throw new InvalidObjectException("Server: Data stream ended prematurely");
                }
                current += bytesRead;
            }

            bos.write(myByteArray, 0, current);
            bos.flush();
            bos.close();
            fos.close();
            textArea.append("Server has received file.\n");
            latestReceivedFile = f;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    private void sendFileToClient(File file) {

        byte [] mybytearray  = new byte [(int)file.length()];
        try {
            out.writeUTF("SEND_FILE_TO_CLIENT");
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
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AlfalabServer frame = new AlfalabServer();
        frame.setTitle("Alfalab Server");
        frame.setContentPane(frame.AlfalabServer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.start();
    }
}

