package nl.iisg.alfalabFrontEnd;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFWriter;
import nl.iisg.ids03.Person;
import nl.iisg.ids03.PersonStandardized;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cro
 * Date: 9-12-11
 * Time: 11:37
 * To change this template use File | Settings | File Templates.
 */
public class CreateB2Dbf {

    public CreateB2Dbf() {
    }


    public void createDbf(DBFWriter dbfWriter, List<Person> deletedPersons) {
        try {


            createDbfFields(dbfWriter);


            for (Person person : deletedPersons) {

                Object rowData[] = new Object[46];
                
                rowData[0] = person.getKeyToSourceRegister();
                rowData[1] = person.getDayEntryHead();
                rowData[2] = person.getMonthEntryHead();
                rowData[3] = person.getYearEntryHead();
                rowData[4] = person.getKeyToRP();

                rowData[5] = person.getKeyToRegistrationPersons();
                rowData[6] = person.getNatureOfPerson();
                rowData[7] = person.getDayOfRegistration();
                rowData[8] = person.getMonthOfRegistration();
                rowData[9] = person.getYearOfRegistration();
                rowData[10] = person.getDayOfRegistrationAfterInterpretation();
                rowData[11] = person.getMonthOfRegistrationAfterInterpretation();
                rowData[12] = person.getYearOfRegistrationAfterInterpretation();
                rowData[13] = person.getFamilyName();
                rowData[14] = person.getFirstName();
                rowData[15] = person.getSex();

                rowData[16] = person.getDayOfBirth();
                rowData[17] = person.getMonthOfBirth();
                rowData[18] = person.getYearOfBirth();
                rowData[19] = person.getDayOfBirthAfterInterpretation();
                rowData[20] = person.getMonthOfBirthAfterInterpretation();
                rowData[21] = person.getYearOfBirthAfterInterpretation();
                rowData[22] = person.getPlaceOfBirth();

                rowData[23] = person.getDayOfDecease();
                rowData[24] = person.getMonthOfDecease();
                rowData[25] = person.getYearOfDecease();
                rowData[26] = person.getDayOfDeceaseAfterInterpretation();
                rowData[27] = person.getMonthOfDeceaseAfterInterpretation();
                rowData[28] = person.getYearOfDeceaseAfterInterpretation();
                rowData[29] = person.getPlaceOfDecease();

                rowData[30] = person.getNationality();
                rowData[31] = person.getLegalPlaceOfLiving();
                rowData[32] = person.getLegalPlaceOfLivingInCodes();
                rowData[33] = person.getRemarks();
                rowData[34] = person.getRemarks2();

                rowData[35] = person.getOrderNumber();
                rowData[36] = stringToDate(person.getDate0());
                rowData[37] = person.getInitials();
                rowData[38] = person.getVersionLastTimeOfDataEntry();
                rowData[39] = person.getResearchCodeOriginal();
                rowData[40] = person.getOrderNumberOriginal();
                //Date originalDate = stringToDate(person.getDateOriginal());
                //System.out.println("Date = " + originalDate);
                rowData[41] = stringToDate(person.getDateOriginal());
                rowData[42] = person.getInitialOriginal();
                rowData[43] = person.getVersionOriginalDataEntry();
                String s = String.format("%06d%04d%02d%02d%06d%03d", person.getKeyToSourceRegister(), 
                		                                             person.getYearEntryHead(), 
                		                                             person.getMonthEntryHead(),
                		                                             person.getDayEntryHead(),
                		                                             person.getKeyToRP(),
                		                                             person.getKeyToRegistrationPersons());
                rowData[44] = s.substring(0, 21);
                rowData[45] = s;
                dbfWriter.addRecord(rowData);

            }


        } catch (DBFException e) {
            e.printStackTrace();
        }

    }

    private void createDbfFields(DBFWriter dbfWriter) throws DBFException {

        DBFField dbfFields[] = new DBFField[46];
        
        for (int i = 0; i < 13; i++) {
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
        dbfFields[6].setFieldName("B2FCBG");
        dbfFields[7].setFieldName("B2RDNR");
        dbfFields[8].setFieldName("B2RMNR");
        dbfFields[9].setFieldName("B2RJNR");
        dbfFields[10].setFieldName("B2RDCR");
        dbfFields[11].setFieldName("B2RMCR");
        dbfFields[12].setFieldName("B2RJCR");

        for (int i = 13; i < 15; i++) {
            dbfFields[i] = new DBFField();
            dbfFields[i].setDataType(DBFField.FIELD_TYPE_C);
            dbfFields[i].setFieldLength(50);
        }

        dbfFields[13].setFieldName("B2ANNR");
        dbfFields[14].setFieldName("B2VNNR");

        dbfFields[15] = new DBFField();
        dbfFields[15].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[15].setFieldLength(1);
        dbfFields[15].setFieldName("B2GSNR");

        for (int i = 16; i < 22; i++) {
            dbfFields[i] = new DBFField();
            dbfFields[i].setDataType(DBFField.FIELD_TYPE_N);
            dbfFields[i].setFieldLength(8);
        }

        dbfFields[16].setFieldName("B2GDNR");
        dbfFields[17].setFieldName("B2GMNR");
        dbfFields[18].setFieldName("B2GJNR");
        dbfFields[19].setFieldName("B2GDCR");
        dbfFields[20].setFieldName("B2GMCR");
        dbfFields[21].setFieldName("B2GJCR");

        dbfFields[22] = new DBFField();
        dbfFields[22].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[22].setFieldName("B2GNNR");
        dbfFields[22].setFieldLength(50);

        for (int i = 23; i < 29; i++) {
            dbfFields[i] = new DBFField();
            dbfFields[i].setDataType(DBFField.FIELD_TYPE_N);
            dbfFields[i].setFieldLength(8);
        }

        dbfFields[23].setFieldName("B2ODNR");
        dbfFields[24].setFieldName("B2OMNR");
        dbfFields[25].setFieldName("B2OJNR");
        dbfFields[26].setFieldName("B2ODCR");
        dbfFields[27].setFieldName("B2OMCR");
        dbfFields[28].setFieldName("B2OJCR");

        dbfFields[29] = new DBFField();
        dbfFields[29].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[29].setFieldName("B2ONNR");
        dbfFields[29].setFieldLength(50);

        dbfFields[30] = new DBFField();
        dbfFields[30].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[30].setFieldName("B2NANR");
        dbfFields[30].setFieldLength(40);

        dbfFields[31] = new DBFField();
        dbfFields[31].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[31].setFieldName("B2WDNR");
        dbfFields[31].setFieldLength(50);

        dbfFields[32] = new DBFField();
        dbfFields[32].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[32].setFieldName("B2VWNR");
        dbfFields[32].setFieldLength(2);

        dbfFields[33] = new DBFField();
        dbfFields[33].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[33].setFieldName("B2AANR");
        dbfFields[33].setFieldLength(100);

        dbfFields[34] = new DBFField();
        dbfFields[34].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[34].setFieldName("B2AAN");
        dbfFields[34].setFieldLength(128);

        dbfFields[35] = new DBFField();
        dbfFields[35].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[35].setFieldName("OPDRNR");
        dbfFields[35].setFieldLength(3);

        dbfFields[36] = new DBFField();
        dbfFields[36].setDataType(DBFField.FIELD_TYPE_D);
        dbfFields[36].setFieldName("DATUM");
        dbfFields[36].setFieldLength(8);

        dbfFields[37] = new DBFField();
        dbfFields[37].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[37].setFieldName("INIT");
        dbfFields[37].setFieldLength(3);

        dbfFields[38] = new DBFField();
        dbfFields[38].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[38].setFieldName("VERSIE");
        dbfFields[38].setFieldLength(5);

        dbfFields[39] = new DBFField();
        dbfFields[39].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[39].setFieldName("ONDRZKO");
        dbfFields[39].setFieldLength(3);

        dbfFields[40] = new DBFField();
        dbfFields[40].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[40].setFieldName("OPDRNRO");
        dbfFields[40].setFieldLength(3);

        dbfFields[41] = new DBFField();
        dbfFields[41].setDataType(DBFField.FIELD_TYPE_D);
        dbfFields[41].setFieldName("DATUMO");
        dbfFields[41].setFieldLength(8);

        dbfFields[42] = new DBFField();
        dbfFields[42].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[42].setFieldName("INITO");
        dbfFields[42].setFieldLength(3);

        dbfFields[43] = new DBFField();
        dbfFields[43].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[43].setFieldName("VERSIEO");
        dbfFields[43].setFieldLength(5);

        dbfFields[44] = new DBFField();
        dbfFields[44].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[44].setFieldName("B4KEY_");
        dbfFields[44].setFieldLength(20);

        dbfFields[45] = new DBFField();
        dbfFields[45].setDataType(DBFField.FIELD_TYPE_C);
        dbfFields[45].setFieldName("B2KEY_");
        dbfFields[45].setFieldLength(23);

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

    public String convertDate(String date){
    	
    	String[] e = date.split("-");
    	
    	if(e[1].substring(0, 1).equals("0"))
    		e[1] = e[1].substring(1);
    	
    	
    	return e[1] + "/" + e[0] + "/" + e[2];
    	
    }

}
