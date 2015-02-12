package nl.iisg.alfalabFrontEnd;


import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFWriter;
import nl.iisg.ids03.Registration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CreateB4Dbf {




    public CreateB4Dbf() {
    }


    public void createDbf(DBFWriter dbfWriter, List<Registration> registrations) {
        try {


            createDbfFields(dbfWriter);


            for (Registration registration : registrations) {

                Object rowData[] = new Object[23];
                
                rowData[0] = registration.getKeyToSourceRegister();
                rowData[1] = registration.getDayEntryHead();
                rowData[2] = registration.getMonthEntryHead();
                rowData[3] = registration.getYearEntryHead();
                rowData[4] = registration.getKeyToRP();

                rowData[5] = registration.getDayEntryRP();
                rowData[6] = registration.getMonthEntryRP();
                rowData[7] = registration.getYearEntryRP();

                rowData[8] = registration.getPageNumberOfSource();
                rowData[9] = registration.getNumberOfHousehold();
                rowData[10] = registration.getInfoFamilyCardsSystem();
                rowData[11] = registration.getSpecialDataEntryCodes();
                rowData[12] = registration.getRemarks();

                rowData[13] = registration.getOrderNumber();
                rowData[14] = stringToDate(registration.getDate0());
                rowData[15] = registration.getInitials();
                rowData[16] = registration.getVersionLastTimeOfDataEntry();
                rowData[17] = registration.getResearchCodeOriginal();
                rowData[18] = registration.getOrderNumberOriginal();
                rowData[19] = stringToDate(registration.getDateOriginal());
                rowData[20] = registration.getInitialOriginal();
                rowData[21] = registration.getVersionOriginalDataEntry();
                
                String s = String.format("%06d%04d%02d%02d%06d", registration.getKeyToSourceRegister(), 
                												 registration.getYearEntryHead(), 
                												 registration.getMonthEntryHead(),
                												 registration.getDayEntryHead(),
                												 registration.getKeyToRP());
                rowData[22] = s;


                dbfWriter.addRecord(rowData);

            }


        } catch (DBFException e) {
            e.printStackTrace();
        }

    }

    private void createDbfFields(DBFWriter dbfWriter) throws DBFException {

        DBFField dbfFields[] = new DBFField[23];

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
        dbfFields[5].setFieldName("B2FDBG");

        dbfFields[6] = new DBFField();
        dbfFields[6].setDataType(DBFField.FIELD_TYPE_N);
        dbfFields[6].setFieldLength(8);
        dbfFields[6].setFieldName("B2FMBG");

        dbfFields[7] = new DBFField();
        dbfFields[7].setDataType(DBFField.FIELD_TYPE_N);
        dbfFields[7].setFieldLength(8);
        dbfFields[7].setFieldName("B2FJBG");

        dbfFields[8] = new DBFField();
        dbfFields[8].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[8].setFieldLength(8);
        dbfFields[8].setFieldName("B2PGBG");

        dbfFields[9] = new DBFField();
        dbfFields[9].setDataType(DBFField.FIELD_TYPE_N);
        dbfFields[9].setFieldLength(8);
        dbfFields[9].setFieldName("B2VHBG");

        dbfFields[10] = new DBFField();
        dbfFields[10].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[10].setFieldLength(50);
        dbfFields[10].setFieldName("B4GKBG");

        dbfFields[11] = new DBFField();
        dbfFields[11].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[11].setFieldLength(50);
        dbfFields[11].setFieldName("B4SPBG");

        dbfFields[12] = new DBFField();
        dbfFields[12].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[12].setFieldLength(254);
        dbfFields[12].setFieldName("B4AAN");

        dbfFields[13] = new DBFField();
        dbfFields[13].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[13].setFieldLength(3);
        dbfFields[13].setFieldName("OPDRNR");

        dbfFields[14] = new DBFField();
        dbfFields[14].setDataType(DBFField.FIELD_TYPE_D);
        dbfFields[14].setFieldLength(8);
        dbfFields[14].setFieldName("DATUM");

        dbfFields[15] = new DBFField();
        dbfFields[15].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[15].setFieldLength(3);
        dbfFields[15].setFieldName("INIT");

        dbfFields[16] = new DBFField();
        dbfFields[16].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[16].setFieldLength(5);
        dbfFields[16].setFieldName("VERSIE");

        dbfFields[17] = new DBFField();
        dbfFields[17].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[17].setFieldLength(3);
        dbfFields[17].setFieldName("ONDRZKO");

        dbfFields[18] = new DBFField();
        dbfFields[18].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[18].setFieldLength(3);
        dbfFields[18].setFieldName("OPDRNRO");

        dbfFields[19] = new DBFField();
        dbfFields[19].setDataType(DBFField.FIELD_TYPE_D);
        dbfFields[19].setFieldLength(8);
        dbfFields[19].setFieldName("DATUMO");

        dbfFields[20] = new DBFField();
        dbfFields[20].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[20].setFieldLength(3);
        dbfFields[20].setFieldName("INITO");

        dbfFields[21] = new DBFField();
        dbfFields[21].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[21].setFieldLength(5);
        dbfFields[21].setFieldName("VERSIEO");
        
        dbfFields[22] = new DBFField();
        dbfFields[22].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[22].setFieldName("B4KEY_");
        dbfFields[22].setFieldLength(20);


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
