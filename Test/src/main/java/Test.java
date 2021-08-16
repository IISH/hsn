
import java.sql.*;

public class Test 
{
public static void main(String[] args)
{
    try
    {
        //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        // "jdbc:ucanaccess://C:/__tmp/test/zzz.accdb"
        //String connURL = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+"C:\\Users\\CorMunnik\\temp\\ref_age";
        
        //String connURL = "jdbc:ucanaccess://C:\\Users\\Cor Munnik\\temp\\test01.accdb";
        String connURL = "jdbc:ucanaccess://P:\\HSN\\Cor_Munnik\\PK\\PK.accdb";

            String table = "PKKND";        
            Connection conn = DriverManager.getConnection(connURL, "", "");
        Statement s = conn.createStatement();

              // Fetch table
        String selTable = "SELECT * FROM " + table + " WHERE IDNR = 344001 ";
        s.execute(selTable);
        ResultSet rs = s.getResultSet();
        
        ResultSetMetaData rsmd = rs.getMetaData();
        System.out.println("Column names as reported by ResultSetMetaData:");
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.println(rsmd.getColumnName(i) + "   " + rsmd.getColumnTypeName(i));
        }
        
        
        
        while((rs!=null) && (rs.next()))
        {
            System.out.println(rs.getInt(1) + " : " + rs.getInt(2)   + "  "  +  rs.getString(13));
            //break;
        }

        // close and cleanup
        s.close();
        conn.close();
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
}
}