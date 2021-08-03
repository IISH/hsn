package nl.iisg.alfalabFrontEnd;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFWriter;
import nl.iisg.ids03.Person;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cro
 * Date: 18-1-12
 * Time: 13:42
 * To change this template use File | Settings | File Templates.
 */
public class testDbf {
    public void createDbf(DBFWriter dbfWriter, List<Person> deletedPersons) {
            try {


                createDbfFields(dbfWriter);


//                for (Person person : deletedPersons) {

                    Object rowData[] = new Object[3];
                    rowData[0] = "test1";
                    rowData[1] = 193246;
                    rowData[2] = "test2";

                    dbfWriter.addRecord(rowData);

//                }


            } catch (DBFException e) {
                e.printStackTrace();
            }

        }

        private void createDbfFields(DBFWriter dbfWriter) throws DBFException {

            DBFField dbfFields[] = new DBFField[3];
            dbfFields[0] = new DBFField();
            dbfFields[0].setDataType(DBFField.FIELD_TYPE_C);
            dbfFields[0].setFieldName("TEST1");
            dbfFields[0].setFieldLength(20);

            dbfFields[1] = new DBFField();
            dbfFields[1].setDataType(DBFField.FIELD_TYPE_N);
            dbfFields[1].setFieldName("IDNR");
            dbfFields[1].setFieldLength(20);


            dbfFields[2] = new DBFField();
            dbfFields[2].setDataType(DBFField.FIELD_TYPE_C);
            dbfFields[2].setFieldName("TEST2");
            dbfFields[2].setFieldLength(20);

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
