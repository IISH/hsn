package nl.iisg.alfalabFrontEnd;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFWriter;
import nl.iisg.ids03.Registration;
import nl.iisg.ids03.RegistrationAddress;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cro
 * Date: 12-12-11
 * Time: 17:42
 * To change this template use File | Settings | File Templates.
 */
public class CreateB6Dbf {




    public CreateB6Dbf() {
    }


    public void createDbf(DBFWriter dbfWriter, List<RegistrationAddress> registrationAddresses) {
        try {


            createDbfFields(dbfWriter);


            for (RegistrationAddress registrationAddress : registrationAddresses) {

                Object rowData[] = new Object[28];
                
                rowData[0] = registrationAddress.getKeyToSourceRegister();
                rowData[1] = registrationAddress.getDayEntryHead();
                rowData[2] = registrationAddress.getMonthEntryHead();
                rowData[3] = registrationAddress.getYearEntryHead();
                rowData[4] = registrationAddress.getKeyToRP();

                rowData[5] = registrationAddress.getKeyToRegistrationPersons();
                rowData[6] = registrationAddress.getSequenceNumberToAddresses();
                rowData[7] = registrationAddress.getDayOfAddress();
                rowData[8] = registrationAddress.getMonthOfAddress();
                rowData[9] = registrationAddress.getYearOfAddress();
                rowData[10] = registrationAddress.getDayOfAddressAfterInterpretation();
                rowData[11] = registrationAddress.getMonthOfAddressAfterInterpretation();
                rowData[12] = registrationAddress.getYearOfAddressAfterInterpretation();
                rowData[13] = registrationAddress.getAddressType();
                rowData[14] = registrationAddress.getSynchroneNumber();
                rowData[15] = registrationAddress.getNameOfStreet();
                rowData[16] = registrationAddress.getNumber();
                rowData[17] = registrationAddress.getAdditionToNumber();
                rowData[18] = registrationAddress.getOrderNumber();
                rowData[19] = stringToDate(registrationAddress.getDate0());
                rowData[20] = registrationAddress.getInitials();
                rowData[21] = registrationAddress.getVersionLastTimeOfDataEntry();
                rowData[22] = registrationAddress.getResearchCodeOriginal();
                rowData[23] = registrationAddress.getOrderNumberOriginal();
                rowData[24] = stringToDate(registrationAddress.getDateOriginal());
                rowData[25] = registrationAddress.getInitialOriginal();
                rowData[26] = registrationAddress.getVersionOriginalDataEntry();
                
                String s = String.format("%06d%04d%02d%02d%06d", registrationAddress.getKeyToSourceRegister(), 
                												 registrationAddress.getYearEntryHead(), 
                												 registrationAddress.getMonthEntryHead(),
                												 registrationAddress.getDayEntryHead(),
                												 registrationAddress.getKeyToRP());
                rowData[27] = s;
                dbfWriter.addRecord(rowData);

            }


        } catch (DBFException e) {
            e.printStackTrace();
        }

    }

    private void createDbfFields(DBFWriter dbfWriter) throws DBFException {

        DBFField dbfFields[] = new DBFField[28];

        dbfFields[0] = new DBFField();
        dbfFields[0].setDataType(DBFField.FIELD_TYPE_N);
        dbfFields[0].setFieldLength(8);
        dbfFields[0].setFieldName("B1IDBG");

        dbfFields[1] = new DBFField();
        dbfFields[1].setDataType(DBFField.FIELD_TYPE_N);
        dbfFields[1].setFieldLength(8);
        dbfFields[1].setFieldName("B2DIBG");

        dbfFields[2] = new DBFField();
        dbfFields[2].setDataType(DBFField.FIELD_TYPE_N);
        dbfFields[2].setFieldLength(8);
        dbfFields[2].setFieldName("B2MIBG");

        dbfFields[3] = new DBFField();
        dbfFields[3].setDataType(DBFField.FIELD_TYPE_N);
        dbfFields[3].setFieldLength(8);
        dbfFields[3].setFieldName("B2JIBG");

        dbfFields[4] = new DBFField();
        dbfFields[4].setDataType(DBFField.FIELD_TYPE_N);
        dbfFields[4].setFieldLength(8);
        dbfFields[4].setFieldName("IDNR");

        dbfFields[5] = new DBFField();
        dbfFields[5].setDataType(DBFField.FIELD_TYPE_N);
        dbfFields[5].setFieldLength(8);
        dbfFields[5].setFieldName("B2RNBG");

        dbfFields[6] = new DBFField();
        dbfFields[6].setDataType(DBFField.FIELD_TYPE_N);
        dbfFields[6].setFieldLength(8);
        dbfFields[6].setFieldName("B6VRNR");

        dbfFields[7] = new DBFField();
        dbfFields[7].setDataType(DBFField.FIELD_TYPE_N);
        dbfFields[7].setFieldLength(8);
        dbfFields[7].setFieldName("B6MDNR");

        dbfFields[8] = new DBFField();
        dbfFields[8].setDataType(DBFField.FIELD_TYPE_N);
        dbfFields[8].setFieldLength(8);
        dbfFields[8].setFieldName("B6MMNR");

        dbfFields[9] = new DBFField();
        dbfFields[9].setDataType(DBFField.FIELD_TYPE_N);
        dbfFields[9].setFieldLength(8);
        dbfFields[9].setFieldName("B6MJNR");

        dbfFields[10] = new DBFField();
        dbfFields[10].setDataType(DBFField.FIELD_TYPE_N);
        dbfFields[10].setFieldLength(8);
        dbfFields[10].setFieldName("B6MDCR");

        dbfFields[11] = new DBFField();
        dbfFields[11].setDataType(DBFField.FIELD_TYPE_N);
        dbfFields[11].setFieldLength(8);
        dbfFields[11].setFieldName("B6MMCR");

        dbfFields[12] = new DBFField();
        dbfFields[12].setDataType(DBFField.FIELD_TYPE_N);
        dbfFields[12].setFieldLength(8);
        dbfFields[12].setFieldName("B6MJCR");

        dbfFields[13] = new DBFField();
        dbfFields[13].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[13].setFieldLength(2);
        dbfFields[13].setFieldName("B6TPNR");

        dbfFields[14] = new DBFField();
        dbfFields[14].setDataType(DBFField.FIELD_TYPE_N);
        dbfFields[14].setFieldLength(8);
        dbfFields[14].setFieldName("B6SINR");

        dbfFields[15] = new DBFField();
        dbfFields[15].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[15].setFieldLength(60);
        dbfFields[15].setFieldName("B6STNR");

        dbfFields[16] = new DBFField();
        dbfFields[16].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[16].setFieldLength(15);
        dbfFields[16].setFieldName("B6NRNR");


        dbfFields[17] = new DBFField();
        dbfFields[17].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[17].setFieldLength(15);
        dbfFields[17].setFieldName("B6TVNR");


        dbfFields[18] = new DBFField();
        dbfFields[18].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[18].setFieldLength(3);
        dbfFields[18].setFieldName("OPDRNR");


        dbfFields[19] = new DBFField();
        dbfFields[19].setDataType(DBFField.FIELD_TYPE_D);
        dbfFields[19].setFieldLength(8);
        dbfFields[19].setFieldName("DATUM");


        dbfFields[20] = new DBFField();
        dbfFields[20].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[20].setFieldLength(3);
        dbfFields[20].setFieldName("INIT");


        dbfFields[21] = new DBFField();
        dbfFields[21].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[21].setFieldLength(5);
        dbfFields[21].setFieldName("VERSIE");


        dbfFields[22] = new DBFField();
        dbfFields[22].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[22].setFieldLength(3);
        dbfFields[22].setFieldName("ONDRZKO");


        dbfFields[23] = new DBFField();
        dbfFields[23].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[23].setFieldLength(3);
        dbfFields[23].setFieldName("OPDRNRO");

        dbfFields[24] = new DBFField();
        dbfFields[24].setDataType(DBFField.FIELD_TYPE_D);
        dbfFields[24].setFieldLength(8);
        dbfFields[24].setFieldName("DATUMO");


        dbfFields[25] = new DBFField();
        dbfFields[25].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[25].setFieldLength(3);
        dbfFields[25].setFieldName("INITO");


        dbfFields[26] = new DBFField();
        dbfFields[26].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[26].setFieldLength(5);
        dbfFields[26].setFieldName("VERSIEO");


        dbfFields[27] = new DBFField();
        dbfFields[27].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[27].setFieldLength(20);
        dbfFields[27].setFieldName("B4KEY_");

        dbfWriter.setFields(dbfFields);
    }

        public Date stringToDate(String input){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        try {
            return df.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }


}
