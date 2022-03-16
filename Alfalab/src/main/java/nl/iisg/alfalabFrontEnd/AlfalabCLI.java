package nl.iisg.alfalabFrontEnd;

import iisg.nl.hsnmigrate.StandardizeCivilCertificates;
import nl.iisg.convertPK.StandardizePersonalCards;
import nl.iisg.hsnlinks.HSNLinksIntegration;
import nl.iisg.ids.IDS;
import nl.iisg.ids03.StandardizePopulationRegister;
import nl.iisg.ids04.PopulationRegisterToIDS;
import nl.iisg.ids05.CivilCertificatesToIDS;
import nl.iisg.ids06.PKToIDS;
import nl.iisg.ids_init.IDS_INIT;

import java.io.DataOutputStream;
import java.io.File;

public class AlfalabCLI {
    private final static String POP_REG_TEST_ERRORS = "popRegTestErrors";
    private final static String POP_REG_DELETE_FROM_DEF_DB = "popRegDeleteFromDefDB";
    private final static String POP_REG_APPEND_TO_DEF_DB = "popRegAppendToDefDB";
    private final static String POP_REG_TEST_IDNR_DOUBLES = "popRegTestIDnrDoubles";
    private final static String POP_REG_REPLACE_DEF_WITH_TEMP = "popRegReplaceDefWithTemp";
    private final static String POP_REG_TO_IDS = "popRegToIDS";
    private final static String PERSONAL_CARDS_CONVERSION = "personalCardsConversion";
    private final static String PERSONAL_CARDS_TO_IDS = "personalCardsToIDS";
    private final static String CIVIL_CERTS_CONVERSION = "civilCertsConversion";
    private final static String CIVIL_CERTS_TO_IDS = "civilCertsToIDS";
    private final static String BUILD_NEW_HSN = "buildNewHSN";
    private final static String MISC_PRINT_HSN_POP_REG_ERRORS = "miscPrintHSNPopRegErrors";
    private final static String MISC_PRINT_HSN_POP_REG_ALL = "miscPrintHSNPopRegAll";
    private final static String MISC_PRINT_HSN_POP_REG_SELECTED = "miscPrintHSNPopRegSelected";
    private final static String ADD_LINKS_DATA = "addLinksData";
    private final static String INITIALISE_HSN = "initialiseHSN";

    private final static String INPUT_DIRECTORY = "temp";

    public static void main(String[] args) throws Exception {
        if (args.length == 0)
            throw new Exception("Please specify a command!");

        File f = new File(INPUT_DIRECTORY);
        System.out.println(f.getAbsolutePath());

        Thread workerThread;
        DataOutputStream out = new DataOutputStream(System.out);

        switch (args[0].trim()) {
            case PERSONAL_CARDS_CONVERSION:
                workerThread = new Thread(new StandardizePersonalCards(out, getWorkItemOrDirectory(args)));
                break;
            case PERSONAL_CARDS_TO_IDS:
                workerThread = new Thread(new PKToIDS(out));
                break;
            case CIVIL_CERTS_CONVERSION:
                workerThread = new Thread(new StandardizeCivilCertificates(out, getWorkItemOrDirectory(args)));
                break;
            case CIVIL_CERTS_TO_IDS:
                workerThread = new Thread(new CivilCertificatesToIDS(out));
                break;
            case POP_REG_TEST_ERRORS:
                workerThread = new Thread(new StandardizePopulationRegister(out, INPUT_DIRECTORY));
                break;
            case POP_REG_TO_IDS:
                workerThread = new Thread(new PopulationRegisterToIDS(out));
                break;
            case POP_REG_DELETE_FROM_DEF_DB:
                PopReg.setCommand(POP_REG_DELETE_FROM_DEF_DB);
                PopReg.setFile(getFile(args));
                workerThread = new Thread(new PopReg(out, INPUT_DIRECTORY));
                break;
            case POP_REG_APPEND_TO_DEF_DB:
                PopReg.setCommand(POP_REG_APPEND_TO_DEF_DB);
                workerThread = new Thread(new PopReg(out, INPUT_DIRECTORY));
                break;
            case POP_REG_TEST_IDNR_DOUBLES:
                PopReg.setCommand(POP_REG_TEST_IDNR_DOUBLES);
                workerThread = new Thread(new PopReg(out, INPUT_DIRECTORY));
                break;
            case POP_REG_REPLACE_DEF_WITH_TEMP:
                PopReg.setCommand(POP_REG_REPLACE_DEF_WITH_TEMP);
                workerThread = new Thread(new PopReg(out, INPUT_DIRECTORY));
                break;
            case BUILD_NEW_HSN:
                IDS.setVersion(getVersion(args, true));
                workerThread = new Thread(new IDS(out));
                break;
            case INITIALISE_HSN:
                IDS_INIT.setVersion(getVersion(args, false));
                workerThread = new Thread(new IDS_INIT(out));
                break;
            case ADD_LINKS_DATA:
                workerThread = new Thread(new HSNLinksIntegration(out));
                break;
            case MISC_PRINT_HSN_POP_REG_ALL:
                PrintHsnPopReg.setCommand(MISC_PRINT_HSN_POP_REG_ALL);
                workerThread = new Thread(new PrintHsnPopReg(out, INPUT_DIRECTORY));
                break;
            case MISC_PRINT_HSN_POP_REG_ERRORS:
                PrintHsnPopReg.setCommand(MISC_PRINT_HSN_POP_REG_ERRORS);
                workerThread = new Thread(new PrintHsnPopReg(out, INPUT_DIRECTORY));
                break;
            case MISC_PRINT_HSN_POP_REG_SELECTED:
                PrintHsnPopReg.setErrorLevel(getErrorLevel(args));
                PrintHsnPopReg.setCommand(MISC_PRINT_HSN_POP_REG_SELECTED);
                workerThread = new Thread(new PrintHsnPopReg(out, INPUT_DIRECTORY));
                break;
            default:
                throw new Exception("Unknown command: " + args[0].trim());
        }

        workerThread.start();
    }

    private static String getWorkItemOrDirectory(String[] args) {
        return args.length > 1 && args[1] != null ? args[1].trim() : INPUT_DIRECTORY;
    }

    private static File getFile(String[] args) throws Exception {
        if (args.length == 1)
            throw new Exception("Missing a filename!");

        return new File(INPUT_DIRECTORY + File.separator + args[1].trim());
    }

    private static String getVersion(String[] args, boolean hasMaxLength) throws Exception {
        if (args.length == 1)
            throw new Exception("Missing a version!");

        String version = args[1].trim();
        return hasMaxLength && version.length() > 3 ? version.substring(0, 3) : version;
    }

    private static int getErrorLevel(String[] args) throws Exception {
        if (args.length == 1)
            throw new Exception("Missing an error level!");

        return Integer.parseInt(args[1]);
    }
}
