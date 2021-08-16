package nl.iisg.alfalabFrontEnd;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFWriter;
import nl.iisg.ids03.PersonDynamic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cro
 * Date: 12-12-11
 * Time: 15:46
 * To change this template use File | Settings | File Templates.
 */
public class CreateB3Dbf {



    public CreateB3Dbf() {
    }


    public void createDbf(DBFWriter dbfWriter, List<PersonDynamic> deletedPersons) {
        try {


            createDbfFields(dbfWriter);


            for (PersonDynamic person : deletedPersons) {

                Object rowData[] = new Object[29];
                
                rowData[0] = person.getKeyToSourceRegister();
                rowData[1] = person.getDayEntryHead();
                rowData[2] = person.getMonthEntryHead();
                rowData[3] = person.getYearEntryHead();
                rowData[4] = person.getKeyToRP();

                rowData[5] = person.getKeyToRegistrationPersons();
                rowData[6] = person.getDynamicDataType();
                rowData[7] = person.getDynamicDataSequenceNumber();
                rowData[8] = person.getContentOfDynamicData();
                rowData[9] = person.getValueOfRelatedPerson();
                rowData[10] = person.getNatureOfPerson();
                rowData[11] = person.getDayOfMutation();
                rowData[12] = person.getMonthOfMutation();
                rowData[13] = person.getYearOfMutation();
                rowData[14] = person.getDayOfMutationAfterInterpretation();
                rowData[15] = person.getMonthOfMutationAfterInterpretation();
                rowData[16] = person.getYearOfMutationAfterInterpretation();

                rowData[17] = person.getDynamicData2();
                rowData[18] = person.getOrderNumber();
                rowData[19] = stringToDate(person.getDate0());
                rowData[20] = person.getInitials();
                rowData[21] = person.getVersionLastTimeOfDataEntry();
                rowData[22] = person.getResearchCodeOriginal();
                rowData[23] = person.getOrderNumberOriginal();
                rowData[24] = stringToDate(person.getDateOriginal());
                rowData[25] = person.getInitialOriginal();
                rowData[26] = person.getVersionOriginalDataEntry();
                
                String s = String.format("%06d%04d%02d%02d%06d%03d", person.getKeyToSourceRegister(), 
                        person.getYearEntryHead(), 
                        person.getMonthEntryHead(),
                        person.getDayEntryHead(),
                        person.getKeyToRP(),
                        person.getKeyToRegistrationPersons());
                
               	rowData[27] = s.substring(0, 21);
               	rowData[28] = s;



                dbfWriter.addRecord(rowData);

            }


        } catch (DBFException e) {
            e.printStackTrace();
        }

    }

    private void createDbfFields(DBFWriter dbfWriter) throws DBFException {

        DBFField dbfFields[] = new DBFField[29];

        for (int i = 0; i < 17; i++) {
            dbfFields[i] = new DBFField();
            dbfFields[i].setDataType(DBFField.FIELD_TYPE_N);
            dbfFields[i].setFieldLength(8);
        }

        dbfFields[0].setFieldName("B1IDBG");
        dbfFields[1].setFieldName("B2DIBG");
        dbfFields[2].setFieldName("B2MIBG");
        dbfFields[3].setFieldName("B2JIBG");
        dbfFields[4].setFieldName("IDNR");
        
        dbfFields[5].setFieldName("B2RNBG");
        dbfFields[6].setFieldName("B3TYPE");
        dbfFields[7].setFieldName("B3VRNR");
        
        dbfFields[8].setFieldName("B3KODE");
        dbfFields[9].setFieldName("B3RGLN");
        dbfFields[10].setFieldName("B2FCBG");
        dbfFields[11].setFieldName("B3MDNR");
        dbfFields[12].setFieldName("B3MMNR");
        dbfFields[13].setFieldName("B3MJNR");
        dbfFields[14].setFieldName("B3MDCR");
        dbfFields[15].setFieldName("B3MMCR");
        dbfFields[16].setFieldName("B3MJCR");

        dbfFields[17] = new DBFField();
        dbfFields[17].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[17].setFieldLength(51);
        dbfFields[17].setFieldName("B3GEGEVEN");

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
        dbfFields[21].setFieldName("VERSIE");
        dbfFields[21].setFieldLength(5);

        dbfFields[22] = new DBFField();
        dbfFields[22].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[22].setFieldName("ONDRZKO");
        dbfFields[22].setFieldLength(3);

        dbfFields[23] = new DBFField();
        dbfFields[23].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[23].setFieldName("OPDRNRO");
        dbfFields[23].setFieldLength(3);

        dbfFields[24] = new DBFField();
        dbfFields[24].setDataType(DBFField.FIELD_TYPE_D);
        dbfFields[24].setFieldName("DATUMO");
        dbfFields[24].setFieldLength(8);

        dbfFields[25] = new DBFField();
        dbfFields[25].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[25].setFieldName("INITO");
        dbfFields[25].setFieldLength(3);

        dbfFields[26] = new DBFField();
        dbfFields[26].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[26].setFieldName("VERSIEO");
        dbfFields[26].setFieldLength(5);

        dbfFields[27] = new DBFField();
        dbfFields[27].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[27].setFieldName("B4KEY_");
        dbfFields[27].setFieldLength(20);

        dbfFields[28] = new DBFField();
        dbfFields[28].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[28].setFieldName("B2KEY_");
        dbfFields[28].setFieldLength(23);

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
