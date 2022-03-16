package nl.iisg.alfalabFrontEnd;

import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFWriter;
import nl.iisg.hsncommon.DBFHandler;
import nl.iisg.ids03.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiConsumer;

public class PopReg implements Runnable {
    private final static String POP_REG_DELETE_FROM_DEF_DB = "popRegDeleteFromDefDB";
    private final static String POP_REG_APPEND_TO_DEF_DB = "popRegAppendToDefDB";
    private final static String POP_REG_TEST_IDNR_DOUBLES = "popRegTestIDnrDoubles";
    private final static String POP_REG_REPLACE_DEF_WITH_TEMP = "popRegReplaceDefWithTemp";

    private static final String FILE_TYPE_DBF = "FILE_TYPE_DBF";

    static DataOutputStream out = null;
    static String command = null;
    static String inputDirectory = null;
    static File file = null;
    static BiConsumer<File, String> withFile = null;

    public PopReg(DataOutputStream out, String inputDirectory) {
        setInputDirectory(inputDirectory);
        setOut(out);
    }

    public void run() {
        main(new String[] {command});
    }

    public static void main(String[] args) {
        switch (args[0].trim()) {
            case POP_REG_DELETE_FROM_DEF_DB:
               PopReg.deleteFromDefDB();
               break;
            case POP_REG_APPEND_TO_DEF_DB:
                PopReg.appendToDefDB();
                break;
            case POP_REG_TEST_IDNR_DOUBLES:
                PopReg.testIdnrDoubles();
                break;
            case POP_REG_REPLACE_DEF_WITH_TEMP:
                PopReg.replaceDefWithTemp();
                break;
        }
    }

    public static String getCommand() {
        return command;
    }
    
    public static void setCommand(String command) {
        PopReg.command = command;
    }

    public static BiConsumer<File, String> getWithFile() {
        return withFile;
    }

    public static void setWithFile(BiConsumer<File, String> withFile) {
        PopReg.withFile = withFile;
    }

    public static File getFile() {
        return file;
    }

    public static void setFile(File file) {
        PopReg.file = file;
    }


    public static String getInputDirectory() {
        return inputDirectory;
    }

    public static void setInputDirectory(String inputDirectory) {
        PopReg.inputDirectory = inputDirectory;
    }

    public static DataOutputStream getOut() {
        return out;
    }

    public static void setOut(DataOutputStream out) {
        PopReg.out = out;
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

    private static void deleteFromDefDB() {
        print("\nPopulation Register - Delete           started\n");

        File f = new File(inputDirectory + File.separator + "DELETEFROMDEFDB.DBF");
        if (f.exists()) {
            f.delete();
        }

        boolean succeeded = file.renameTo(new File(inputDirectory + File.separator + "DELETEFROMDEFDB.DBF"));

        if (!succeeded) {
            print("ERROR: Rename of file not succeeded, operation cancelled\n");
            return;
        }

        List<IDnr> idNrList = DBFHandler.createObjects("nl.iisg.alfalabFrontEnd.IDnr", inputDirectory);


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
    }

    private static void appendToDefDB() {
        // Check if there aren't duplicate entries first:

        // Create MySQL_opslag table if not exist:
        print("\nPopulation Register - Append           started\n");


        createMySQL_opslagTable();

        try {
            if (checkForDuplicatesB_st()) {
                print("Warning: duplicates detected in B*_ST tables\nAppend operation cancelled.\n");
            }
            else if (checkForDuplicatesB()) {
                print("Warning: duplicates detected B* tables\nAppend operation cancelled.\n");
            }
            else {
                print("No duplicates detected");

                // Start append process:
                appendToMySQL_opslagTable();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        print("\nPopulation Register - Append           finished\n");
    }

    private static void testIdnrDoubles() {
        print("\nPopulation Register - Testing for Doubles           started\n");


        // check for duplicates, and create dbf file from found duplicates:
        try {
            if (checkForDuplicatesB_st()) {
                print("Warning: duplicates detected in B*_ST database!\n");
                //sendFileToClient(new File(inputDirectory + File.separator + "DOUBLE_IDNRS_B2_ST.DBF"), FILE_TYPE_DBF);
            }
            else if (checkForDuplicatesB()) {
                print("Warning: duplicates detected in B* database!\n");
                //sendFileToClient(new File(inputDirectory + File.separator + "DOUBLE_IDNRS_B2.DBF"), FILE_TYPE_DBF);
            }
            else {
                print("No duplicates detected.\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        print("\nPopulation Register - Testing for Doubles           finished\n");
    }

    private static void replaceDefWithTemp() {
        try {
//                createDbfFromMySQLTemp();   // creates deletefromdefdb.dbf
//
//                // delete from MySQL_opslag by IDnr from deletefromdefdb.dbf:
//                List<IDnr> idNrList = Utils.createObjects("nl.iisg.alfalabFrontEnd.IDnr", inputDirectory);

            print("\nPopulation Register - Replace           started\n");


            EntityManager em = nl.iisg.ids03.Utils.getEm_original_tabs();
            Query query = em.createQuery(AppendToOpslag.SELECT_IDNRS_FROM_B2);

            List<Integer> iList = query.getResultList();
            List<IDnr> idNrList = new ArrayList<IDnr>();
            for (Integer i : iList)
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
    }

    // Returns false if there is nothing to delete.
    private static boolean createDbfFromDeletedIdnrs(List<IDnr> idNrList) throws Exception {

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
            FileOutputStream fos  = new FileOutputStream(inputDirectory + File.separator + "B2.DBF");
            dbfWriter.write(fos);
            fos.close();

            if (withFile != null)
                withFile.accept(new File(inputDirectory + File.separator + "B2.DBF"), FILE_TYPE_DBF);
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
            FileOutputStream fos = new FileOutputStream(inputDirectory + File.separator + "B3.DBF");
            dbfWriter.write(fos);
            fos.close();

            if (withFile != null)
                withFile.accept(new File(inputDirectory + File.separator + "B3.DBF"), FILE_TYPE_DBF);
        }

        print("Creating B4.DBF....");


        qOpslag = emMySQLOpslag.createQuery(AppendToOpslag.SELECT_BY_IDNRS_FROM_B4 + whereClause);
        List<Registration> registrations = (List<Registration>) qOpslag.getResultList();

        if (registrations.size() != 0) {
            DBFWriter dbfWriter = new DBFWriter();
            CreateB4Dbf createB4Dbf = new CreateB4Dbf();
            createB4Dbf.createDbf(dbfWriter, registrations);
            FileOutputStream fos = new FileOutputStream(inputDirectory + File.separator + "B4.DBF");
            dbfWriter.write(fos);
            fos.close();

            if (withFile != null)
                withFile.accept(new File(inputDirectory + File.separator + "B4.DBF"), FILE_TYPE_DBF);
        }


        print("Creating B6.DBF....");

        qOpslag = emMySQLOpslag.createQuery(AppendToOpslag.SELECT_BY_IDNRS_FROM_B6 + whereClause);
        List<RegistrationAddress> registrationAddresses = (List<RegistrationAddress>) qOpslag.getResultList();

        if (registrationAddresses.size() != 0) {
            DBFWriter dbfWriter = new DBFWriter();
            CreateB6Dbf createB6Dbf = new CreateB6Dbf();
            createB6Dbf.createDbf(dbfWriter, registrationAddresses);
            FileOutputStream fos = new FileOutputStream(inputDirectory + File.separator + "B6.DBF");
            dbfWriter.write(fos);
            fos.close();

            if (withFile != null)
                withFile.accept(new File(inputDirectory + File.separator + "B6.DBF"), FILE_TYPE_DBF);
        }


        return true;

    }

    private static void createMySQL_opslagTable() {
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

    private static void appendToMySQL_opslagTable() {

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

    private static void appendToMySQL_opslagTableB2(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception {

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

    private static void appendToMySQL_opslagTableB3(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception {

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

    private static void appendToMySQL_opslagTableB4(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception {

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

    private static void appendToMySQL_opslagTableB6(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception {

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

    private static void appendToMySQL_opslagTableB2_st(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception {
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

    private static void appendToMySQL_opslagTableB3_st(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception {

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

    private static void appendToMySQL_opslagTableB4_st(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception {
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

    private static void appendToMySQL_opslagTableB6_st(EntityManager emMySQLOpslag, EntityManager emMySQLTemp) throws Exception {
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

    private static boolean checkForDuplicatesB_st() throws Exception {


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
            FileOutputStream fos = new FileOutputStream(inputDirectory + File.separator + "DOUBLE_IDNRS_B2_ST.DBF");
            dbfWriter.write(fos);
            fos.close();

            if (withFile != null)
                withFile.accept(new File(inputDirectory + File.separator + "DOUBLE_IDNRS_B2_ST.DBF"), FILE_TYPE_DBF);
        }




        return duplicateFound;
    }

    private static boolean checkForDuplicatesB() throws Exception {

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
            FileOutputStream fos = new FileOutputStream(inputDirectory + File.separator + "DOUBLE_IDNRS_B2.DBF");
            dbfWriter.write(fos);
            fos.close();

            if (withFile != null)
                withFile.accept(new File(inputDirectory + File.separator + "DOUBLE_IDNRS_B2.DBF"), FILE_TYPE_DBF);
        }

        return duplicateFound;
    }

    private static void deleteFromBTable(List<IDnr> idNrList, EntityManager em, String deleteQuery) {
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

    private static void deleteFromBTables(List<IDnr> idNrList) {
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

    private static void removeDuplicates(List<IDnr> idNrList) {
        HashSet<IDnr> h = new HashSet<IDnr>(idNrList);
        idNrList.clear();
        idNrList.addAll(h);
    }

}
