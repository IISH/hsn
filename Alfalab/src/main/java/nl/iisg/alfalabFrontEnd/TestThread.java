package nl.iisg.alfalabFrontEnd;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFWriter;
import nl.iisg.ids03.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cro
 * Date: 3-11-11
 * Time: 11:02
 * To change this template use File | Settings | File Templates.
 */
public class TestThread {
    public TestThread() {


    }

    public static void main(String[] args) {

//        EntityManagerFactory factory_mysql_opslag = Persistence.createEntityManagerFactory("MySQL_opslag");
//        EntityManager em_mysql_opslag = factory_mysql_opslag.createEntityManager();

        EntityManagerFactory factory_mysql_opslag_org = Persistence.createEntityManagerFactory("MySQL_opslag_org");
        EntityManager em_mysql_opslag_org = factory_mysql_opslag_org.createEntityManager();
        em_mysql_opslag_org.getTransaction().begin();
        em_mysql_opslag_org.createNativeQuery("delete from hsn_popreg_total_std.b311_st;");
        em_mysql_opslag_org.getTransaction().commit();

    }

}
