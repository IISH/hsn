package nl.iisg.alfalabFrontEnd;

import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFWriter;
import nl.iisg.hsncommon.ConstRelations2;
import nl.iisg.ids03.AppendToOpslag;
import nl.iisg.ids03.Message;
import nl.iisg.ids03.Person;
import nl.iisg.ids03.PersonDynamic;
import nl.iisg.ids03.Registration;
import nl.iisg.ids03.RegistrationAddress;
import nl.iisg.ref.Ref;
import nl.iisg.ref.Ref_AINB;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;

public class PrintHsnPopReg implements Runnable {
    private final static String MISC_PRINT_HSN_POP_REG_ERRORS = "miscPrintHSNPopRegErrors";
    private final static String MISC_PRINT_HSN_POP_REG_ALL = "miscPrintHSNPopRegAll";
    private final static String MISC_PRINT_HSN_POP_REG_SELECTED = "miscPrintHSNPopRegSelected";

    private static final String FILE_TYPE_PRINTFILE = "FILE_TYPE_PRINTFILE";

    private final static int NR_OF_COLUMNS = 2;
    private final static int COLUMN_WIDTH = 50;

    static DataOutputStream out = null;
    static String command = null;
    static int errorLevel = 0;
    static String inputDirectory = null;
    static BiConsumer<File, String> withFile = null;

    public PrintHsnPopReg(DataOutputStream out, String inputDirector) {
        setInputDirectory(inputDirectory);
        setOut(out);
    }

    public void run() {
        main(new String[] {command});
    }

    public static void main(String[] args) {
        switch (args[0].trim()) {
            case MISC_PRINT_HSN_POP_REG_ALL:
                PrintHsnPopReg.printPopRegAll();
                break;
            case MISC_PRINT_HSN_POP_REG_ERRORS:
                PrintHsnPopReg.printPopRegErrors();
                break;
            case MISC_PRINT_HSN_POP_REG_SELECTED:
                PrintHsnPopReg.printPopRegSelected();
                break;
        }
    }

    public static String getCommand() {
        return command;
    }

    public static void setCommand(String command) {
        PrintHsnPopReg.command = command;
    }

    public static int getErrorLevel() {
        return errorLevel;
    }

    public static void setErrorLevel(int errorLevel) {
        PrintHsnPopReg.errorLevel = errorLevel;
    }

    public static BiConsumer<File, String> getWithFile() {
        return withFile;
    }

    public static void setWithFile(BiConsumer<File, String> withFile) {
        PrintHsnPopReg.withFile = withFile;
    }

    public static String getInputDirectory() {
        return inputDirectory;
    }

    public static void setInputDirectory(String inputDirectory) {
        PrintHsnPopReg.inputDirectory = inputDirectory;
    }

    public static DataOutputStream getOut() {
        return out;
    }

    public static void setOut(DataOutputStream out) {
        PrintHsnPopReg.out = out;
    }

    public static void print(String line) {
        if (out != null) {
            try {
                out.writeUTF(line);
            } catch (IOException e) {
                // e.printStackTrace();
                System.out.println("Client Message: " + line);
            }
        } else {
            System.out.println(line);
        }
    }

    private static void printPopRegAll() {
        try {
            print("\nPopulation Register - Print All                  started\n");


            Ref.loadAINB();

            EntityManagerFactory factory_original_tabs = Persistence.createEntityManagerFactory("b_tables");
            EntityManager emBtables = factory_original_tabs.createEntityManager();

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
            //sendFileToClient(new File(inputDirectory + File.separator + "pop_reg_errors_all.txt"), FILE_TYPE_PRINTFILE);

            CreateWordDoc createWordDoc = new CreateWordDoc();
            createWordDoc.generatePopulationRegisterPrintout(new File(inputDirectory + File.separator + "pop_reg_errors_all.txt"), "pop_reg_errors_all.doc");


            if (withFile != null)
                withFile.accept(new File(inputDirectory + File.separator + "pop_reg_errors_all.doc"), FILE_TYPE_PRINTFILE);

            print("\nPopulation Register - Print All                  finished\n");


//            deleteTempFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printPopRegErrors() {
        try {
            print("\nPopulation Register - Print Messages           started\n");


            Ref.loadAINB();

            EntityManagerFactory factory_original_tabs = Persistence.createEntityManagerFactory("b_tables");
            EntityManager emBtables = factory_original_tabs.createEntityManager();

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
            createWordDoc.generatePopulationRegisterPrintout(new File(inputDirectory + File.separator + "pop_reg_errors.txt"), "pop_reg_errors.doc");

            if (withFile != null)
                withFile.accept(new File(inputDirectory + File.separator + "pop_reg_errors.doc"), FILE_TYPE_PRINTFILE);

            print("\nPopulation Register - Print Messages           ended\n");

    //            deleteTempFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printPopRegSelected() {
        try {
            print("\nPopulation Register - Print *Selected* Messages      started\n");


            Ref.loadAINB();

            EntityManagerFactory factory_original_tabs = Persistence.createEntityManagerFactory("b_tables");
            EntityManager emBtables = factory_original_tabs.createEntityManager();


            Query q = emBtables.createQuery("select a from Person a order by a.keyToRegistrationPersons");
            List<Person> persons = q.getResultList();

            if (persons.size() == 0) {

                print("There is no data in the B2 database to print, printing action cancelled.");
                return;

            }

            q = emBtables.createQuery("select a from PersonDynamic a");
            List<PersonDynamic> personsDynamic = q.getResultList();

            q = emBtables.createQuery("select a from Registration a");
            List<Registration> registrations = q.getResultList();

            q = emBtables.createQuery("select a from RegistrationAddress a");
            List<RegistrationAddress> registrationAddresses;
            registrationAddresses = q.getResultList();

            System.out.println("Error level: " + errorLevel);

            print("Server creating print file. Please wait...");
            printHSNPopRegPrintSelectedErrors(persons, personsDynamic, registrations, registrationAddresses, errorLevel);

            if (errorLevel == 3) {
                print("Printing HSN files Population Registers Controle A melding 1000-serie Ronde 2 finished.\n");
            }
            else if (errorLevel == 4) {
                print("Printing HSN files Population Registers Controle B melding 1000-serie Ronde 2 finished.\n");
            }
            else if (errorLevel == 5) {
                print("Printing HSN files Population Registers Controle A/B melding 1000-serie Ronde 3 finished.\n");
            }
            else if (errorLevel == 6) {
                print("Printing HSN files Population Registers Controle A melding 4000-serie Ronde 4 finished.\n");
            }
            else if (errorLevel == 7) {
                print("Printing HSN files Population Registers Controle B melding 4000-serie Ronde 4 finished.\n");
            }
            else if (errorLevel == 8) {
                print("Printing HSN files Population Registers Controle A/B melding 4000-serie Ronde 5 finished.\n");
            }

            CreateWordDoc createWordDoc = new CreateWordDoc();
            createWordDoc.generatePopulationRegisterPrintout(new File(inputDirectory + File.separator + "pop_reg_errors_selected.txt"), "pop_reg_errors_selected.doc");

            if (withFile != null)
                withFile.accept(new File(inputDirectory + File.separator + "pop_reg_errors_selected.doc"), FILE_TYPE_PRINTFILE);

            print("\nPopulation Register - Print *Selected* Messages      ended\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Printing methods:
    private static void printHSNPopRegPrintSelectedErrors(
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

        EntityManagerFactory factory_original_tabs = Persistence.createEntityManagerFactory("b_tables");
        EntityManager emBtables = factory_original_tabs.createEntityManager();

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


        Collections.sort(persons, new Comparator<Person>(){

            public int compare(Person p1, Person p2){

                if     (p1.getKeyToRP() > p2.getKeyToRP()) return 1;
                else if(p1.getKeyToRP() < p2.getKeyToRP()) return -1;

                if     (p1.getYearEntryHead() > p2.getYearEntryHead()) return  1;
                else if(p1.getYearEntryHead() < p2.getYearEntryHead()) return -1;

                if     (p1.getMonthEntryHead() > p2.getMonthEntryHead()) return  1;
                else if(p1.getMonthEntryHead() < p2.getMonthEntryHead()) return -1;

                if     (p1.getDayEntryHead() > p2.getDayEntryHead()) return  1;
                else if(p1.getDayEntryHead() < p2.getDayEntryHead()) return -1;

                if     (p1.getKeyToSourceRegister() > p2.getKeyToSourceRegister()) return  1;
                else if(p1.getKeyToSourceRegister() < p2.getKeyToSourceRegister()) return -1;

                if     (p1.getKeyToRegistrationPersons() > p2.getKeyToRegistrationPersons()) return 1;
                else if(p1.getKeyToRegistrationPersons() < p2.getKeyToRegistrationPersons()) return -1;

                return 0;
            }});


        Collections.sort(personsDynamic, new Comparator<PersonDynamic>(){


            public int compare(PersonDynamic pd1, PersonDynamic pd2){

                if     (pd1.getKeyToRP() > pd2.getKeyToRP()) return  1;
                else if(pd1.getKeyToRP() < pd2.getKeyToRP()) return -1;

                if     (pd1.getYearEntryHead() > pd2.getYearEntryHead()) return  1;
                else if(pd1.getYearEntryHead() < pd2.getYearEntryHead()) return -1;

                if     (pd1.getMonthEntryHead() > pd2.getMonthEntryHead()) return  1;
                else if(pd1.getMonthEntryHead() < pd2.getMonthEntryHead()) return -1;

                if     (pd1.getDayEntryHead() > pd2.getDayEntryHead()) return  1;
                else if(pd1.getDayEntryHead() < pd2.getDayEntryHead()) return -1;

                if     (pd1.getKeyToSourceRegister() > pd2.getKeyToSourceRegister()) return  1;
                else if(pd1.getKeyToSourceRegister() < pd2.getKeyToSourceRegister()) return -1;

                if     (pd1.getKeyToRegistrationPersons() > pd2.getKeyToRegistrationPersons()) return  1;
                else if(pd1.getKeyToRegistrationPersons() < pd2.getKeyToRegistrationPersons()) return -1;

                if     (pd1.getDynamicDataType() > pd2.getDynamicDataType()) return  1;
                else if(pd1.getDynamicDataType() < pd2.getDynamicDataType()) return -1;

                if     (pd1.getDynamicDataSequenceNumber() > pd2.getDynamicDataSequenceNumber()) return  1;
                else if(pd1.getDynamicDataSequenceNumber() < pd2.getDynamicDataSequenceNumber()) return -1;

                return 0;

            }});

        Collections.sort(registrationAddresses, new Comparator<RegistrationAddress>(){

            public int compare(RegistrationAddress ra1, RegistrationAddress ra2){

                if     (ra1.getKeyToRP() > ra2.getKeyToRP()) return  1;
                else if(ra1.getKeyToRP() < ra2.getKeyToRP()) return -1;

                if     (ra1.getYearEntryHead() > ra2.getYearEntryHead()) return  1;
                else if(ra1.getYearEntryHead() < ra2.getYearEntryHead()) return -1;

                if     (ra1.getMonthEntryHead() > ra2.getMonthEntryHead()) return  1;
                else if(ra1.getMonthEntryHead() < ra2.getMonthEntryHead()) return -1;

                if     (ra1.getDayEntryHead() > ra2.getDayEntryHead()) return  1;
                else if(ra1.getDayEntryHead() < ra2.getDayEntryHead()) return -1;

                if     (ra1.getKeyToSourceRegister() > ra2.getKeyToSourceRegister()) return  1;
                else if(ra1.getKeyToSourceRegister() < ra2.getKeyToSourceRegister()) return -1;

                if     (ra1.getKeyToRegistrationPersons() > ra2.getKeyToRegistrationPersons()) return  1;
                else if(ra1.getKeyToRegistrationPersons() < ra2.getKeyToRegistrationPersons()) return -1;

                if     (ra1.getSequenceNumberToAddresses() > ra2.getSequenceNumberToAddresses()) return  1;
                else if(ra1.getSequenceNumberToAddresses() < ra2.getSequenceNumberToAddresses()) return -1;

                return 0;
            }});




        System.out.println("After sort");

        System.out.println("Opening bfout db..");
        try {

            Charset utf8 = Charset.forName("UTF-8");

            //BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileWriter(inputDirectory + File.separator +"pop_reg_errors_selected.txt")));
            //(new OutputStreamWriter(new FileOutputStream(path),"UTF-8"));
            BufferedWriter writer = new BufferedWriter
                    (new OutputStreamWriter(new FileOutputStream(inputDirectory + File.separator +"pop_reg_errors_selected.txt"),utf8));


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
                            personsDynamic,
                            emBtables);

                }
                writer.write(output);
            }
            writer.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    // todo:
    private static void printHSNPopRegPrintAll(List<Person> persons,                               // b2
                                        List<PersonDynamic> personsDynamic,                 // b3
                                        List<Registration> registrations,                   // b4
                                        List<RegistrationAddress> registrationAddresses) {

        String output = "";

        System.out.println("In printHSNPopRegPrintAll");

        EntityManagerFactory factory_original_tabs = Persistence.createEntityManagerFactory("b_tables");
        EntityManager emBtables = factory_original_tabs.createEntityManager();

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

        Collections.sort(persons, new Comparator<Person>(){

            public int compare(Person p1, Person p2){

                if     (p1.getKeyToRP() > p2.getKeyToRP()) return 1;
                else if(p1.getKeyToRP() < p2.getKeyToRP()) return -1;

                if     (p1.getYearEntryHead() > p2.getYearEntryHead()) return  1;
                else if(p1.getYearEntryHead() < p2.getYearEntryHead()) return -1;

                if     (p1.getMonthEntryHead() > p2.getMonthEntryHead()) return  1;
                else if(p1.getMonthEntryHead() < p2.getMonthEntryHead()) return -1;

                if     (p1.getDayEntryHead() > p2.getDayEntryHead()) return  1;
                else if(p1.getDayEntryHead() < p2.getDayEntryHead()) return -1;

                if     (p1.getKeyToSourceRegister() > p2.getKeyToSourceRegister()) return  1;
                else if(p1.getKeyToSourceRegister() < p2.getKeyToSourceRegister()) return -1;

                if     (p1.getKeyToRegistrationPersons() > p2.getKeyToRegistrationPersons()) return 1;
                else if(p1.getKeyToRegistrationPersons() < p2.getKeyToRegistrationPersons()) return -1;

                return 0;
            }});


        Collections.sort(personsDynamic, new Comparator<PersonDynamic>(){


            public int compare(PersonDynamic pd1, PersonDynamic pd2){

                if     (pd1.getKeyToRP() > pd2.getKeyToRP()) return  1;
                else if(pd1.getKeyToRP() < pd2.getKeyToRP()) return -1;

                if     (pd1.getYearEntryHead() > pd2.getYearEntryHead()) return  1;
                else if(pd1.getYearEntryHead() < pd2.getYearEntryHead()) return -1;

                if     (pd1.getMonthEntryHead() > pd2.getMonthEntryHead()) return  1;
                else if(pd1.getMonthEntryHead() < pd2.getMonthEntryHead()) return -1;

                if     (pd1.getDayEntryHead() > pd2.getDayEntryHead()) return  1;
                else if(pd1.getDayEntryHead() < pd2.getDayEntryHead()) return -1;

                if     (pd1.getKeyToSourceRegister() > pd2.getKeyToSourceRegister()) return  1;
                else if(pd1.getKeyToSourceRegister() < pd2.getKeyToSourceRegister()) return -1;

                if     (pd1.getKeyToRegistrationPersons() > pd2.getKeyToRegistrationPersons()) return  1;
                else if(pd1.getKeyToRegistrationPersons() < pd2.getKeyToRegistrationPersons()) return -1;

                if     (pd1.getDynamicDataType() > pd2.getDynamicDataType()) return  1;
                else if(pd1.getDynamicDataType() < pd2.getDynamicDataType()) return -1;

                if     (pd1.getDynamicDataSequenceNumber() > pd2.getDynamicDataSequenceNumber()) return  1;
                else if(pd1.getDynamicDataSequenceNumber() < pd2.getDynamicDataSequenceNumber()) return -1;

                return 0;

            }});

        Collections.sort(registrationAddresses, new Comparator<RegistrationAddress>(){

            public int compare(RegistrationAddress ra1, RegistrationAddress ra2){

                if     (ra1.getKeyToRP() > ra2.getKeyToRP()) return  1;
                else if(ra1.getKeyToRP() < ra2.getKeyToRP()) return -1;

                if     (ra1.getYearEntryHead() > ra2.getYearEntryHead()) return  1;
                else if(ra1.getYearEntryHead() < ra2.getYearEntryHead()) return -1;

                if     (ra1.getMonthEntryHead() > ra2.getMonthEntryHead()) return  1;
                else if(ra1.getMonthEntryHead() < ra2.getMonthEntryHead()) return -1;

                if     (ra1.getDayEntryHead() > ra2.getDayEntryHead()) return  1;
                else if(ra1.getDayEntryHead() < ra2.getDayEntryHead()) return -1;

                if     (ra1.getKeyToSourceRegister() > ra2.getKeyToSourceRegister()) return  1;
                else if(ra1.getKeyToSourceRegister() < ra2.getKeyToSourceRegister()) return -1;

                if     (ra1.getKeyToRegistrationPersons() > ra2.getKeyToRegistrationPersons()) return  1;
                else if(ra1.getKeyToRegistrationPersons() < ra2.getKeyToRegistrationPersons()) return -1;

                if     (ra1.getSequenceNumberToAddresses() > ra2.getSequenceNumberToAddresses()) return  1;
                else if(ra1.getSequenceNumberToAddresses() < ra2.getSequenceNumberToAddresses()) return -1;

                return 0;
            }});



        try {

            Charset utf8 = Charset.forName("UTF-8");
            BufferedWriter printFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(inputDirectory + File.separator +"pop_reg_errors_all.txt"),utf8));

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
                                personsDynamic,
                                emBtables);

                    }
                }


                printFile.write(output);
            }
            printFile.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void printHSNPopRegErrors(
            List<Person> persons,                               // b2
            List<PersonDynamic> personsDynamic,                 // b3
            List<Registration> registrations,                   // b4
            List<RegistrationAddress> registrationAddresses) {  // b6

        System.out.println("In printHSNPopRegErrors");

        String output = "";
        List<Message> messages;

        EntityManagerFactory factory_original_tabs = Persistence.createEntityManagerFactory("b_tables");
        EntityManager emBtables = factory_original_tabs.createEntityManager();


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

        Collections.sort(persons, new Comparator<Person>(){

            public int compare(Person p1, Person p2){

                if     (p1.getKeyToRP() > p2.getKeyToRP()) return 1;
                else if(p1.getKeyToRP() < p2.getKeyToRP()) return -1;

                if     (p1.getYearEntryHead() > p2.getYearEntryHead()) return  1;
                else if(p1.getYearEntryHead() < p2.getYearEntryHead()) return -1;

                if     (p1.getMonthEntryHead() > p2.getMonthEntryHead()) return  1;
                else if(p1.getMonthEntryHead() < p2.getMonthEntryHead()) return -1;

                if     (p1.getDayEntryHead() > p2.getDayEntryHead()) return  1;
                else if(p1.getDayEntryHead() < p2.getDayEntryHead()) return -1;

                if     (p1.getKeyToSourceRegister() > p2.getKeyToSourceRegister()) return  1;
                else if(p1.getKeyToSourceRegister() < p2.getKeyToSourceRegister()) return -1;

                if     (p1.getKeyToRegistrationPersons() > p2.getKeyToRegistrationPersons()) return 1;
                else if(p1.getKeyToRegistrationPersons() < p2.getKeyToRegistrationPersons()) return -1;

                return 0;
            }});


        Collections.sort(personsDynamic, new Comparator<PersonDynamic>(){


            public int compare(PersonDynamic pd1, PersonDynamic pd2){

                if     (pd1.getKeyToRP() > pd2.getKeyToRP()) return  1;
                else if(pd1.getKeyToRP() < pd2.getKeyToRP()) return -1;

                if     (pd1.getYearEntryHead() > pd2.getYearEntryHead()) return  1;
                else if(pd1.getYearEntryHead() < pd2.getYearEntryHead()) return -1;

                if     (pd1.getMonthEntryHead() > pd2.getMonthEntryHead()) return  1;
                else if(pd1.getMonthEntryHead() < pd2.getMonthEntryHead()) return -1;

                if     (pd1.getDayEntryHead() > pd2.getDayEntryHead()) return  1;
                else if(pd1.getDayEntryHead() < pd2.getDayEntryHead()) return -1;

                if     (pd1.getKeyToSourceRegister() > pd2.getKeyToSourceRegister()) return  1;
                else if(pd1.getKeyToSourceRegister() < pd2.getKeyToSourceRegister()) return -1;

                if     (pd1.getKeyToRegistrationPersons() > pd2.getKeyToRegistrationPersons()) return  1;
                else if(pd1.getKeyToRegistrationPersons() < pd2.getKeyToRegistrationPersons()) return -1;

                if     (pd1.getDynamicDataType() > pd2.getDynamicDataType()) return  1;
                else if(pd1.getDynamicDataType() < pd2.getDynamicDataType()) return -1;

                if     (pd1.getDynamicDataSequenceNumber() > pd2.getDynamicDataSequenceNumber()) return  1;
                else if(pd1.getDynamicDataSequenceNumber() < pd2.getDynamicDataSequenceNumber()) return -1;

                return 0;

            }});

        Collections.sort(registrationAddresses, new Comparator<RegistrationAddress>(){

            public int compare(RegistrationAddress ra1, RegistrationAddress ra2){

                if     (ra1.getKeyToRP() > ra2.getKeyToRP()) return  1;
                else if(ra1.getKeyToRP() < ra2.getKeyToRP()) return -1;

                if     (ra1.getYearEntryHead() > ra2.getYearEntryHead()) return  1;
                else if(ra1.getYearEntryHead() < ra2.getYearEntryHead()) return -1;

                if     (ra1.getMonthEntryHead() > ra2.getMonthEntryHead()) return  1;
                else if(ra1.getMonthEntryHead() < ra2.getMonthEntryHead()) return -1;

                if     (ra1.getDayEntryHead() > ra2.getDayEntryHead()) return  1;
                else if(ra1.getDayEntryHead() < ra2.getDayEntryHead()) return -1;

                if     (ra1.getKeyToSourceRegister() > ra2.getKeyToSourceRegister()) return  1;
                else if(ra1.getKeyToSourceRegister() < ra2.getKeyToSourceRegister()) return -1;

                if     (ra1.getKeyToRegistrationPersons() > ra2.getKeyToRegistrationPersons()) return  1;
                else if(ra1.getKeyToRegistrationPersons() < ra2.getKeyToRegistrationPersons()) return -1;

                if     (ra1.getSequenceNumberToAddresses() > ra2.getSequenceNumberToAddresses()) return  1;
                else if(ra1.getSequenceNumberToAddresses() < ra2.getSequenceNumberToAddresses()) return -1;

                return 0;
            }});


        try {

            Charset utf8 = Charset.forName("UTF-8");
            BufferedWriter printFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(inputDirectory + File.separator +"pop_reg_errors.txt"),utf8));

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
                            personsDynamic,
                            emBtables);

                } else {
                    output += "Geen meldingen bij Idnr: " + r.getKeyToRP() + " Hoofddatum: " + r.getDayEntryHead()
                            + "/" + r.getMonthEntryHead() + "/" + r.getYearEntryHead() + " Bronnr: /" + r.getKeyToSourceRegister() + "\n";
                    output += "------------ daarom ook geen inschrijving weergegeven ---------------------------\n";
                    output += "---------------------------------------------------------------------------------\n\n";

                }
                printFile.write(output);
                //System.out.println(output); // DDSX
            }
            printFile.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void createDbfFromMySQLTemp() throws Exception {

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

        FileOutputStream fos = new FileOutputStream(inputDirectory + File.separator + "DELETEFROMDEFDB.DBF");
        dbfWriter.write(fos);
        fos.close();

    }

    private static String safeGetContentOfDynamicData1(int contentOfDynamicData) {

        if (contentOfDynamicData > 0) {
            if(ConstRelations2.b3kode1[contentOfDynamicData]  != null)
                return ConstRelations2.b3kode1[contentOfDynamicData];
            else
                return "";
        } else {
            return Integer.toString(contentOfDynamicData);
        }

    }

    private static String safeGetContentOfDynamicData2(int contentOfDynamicData) {

        if (contentOfDynamicData > 0) {
            return ConstRelations2.b3kode2[contentOfDynamicData].substring(0, 3) + ".";
        } else {
            return Integer.toString(contentOfDynamicData);
        }

    }

    private static String printRgl(Person per,
                            Registration reg,
                            int keyToRP,
                            int yearEntryHead,
                            int monthEntryHead,
                            int dayEntryHead,
                            int keyToSourceRegister,
                            List<Person> persons,
                            List<Registration> registrations,
                            List<RegistrationAddress> registrationAddresses,
                            List<PersonDynamic> personsDynamic,
                            EntityManager emBtables) {


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
}
