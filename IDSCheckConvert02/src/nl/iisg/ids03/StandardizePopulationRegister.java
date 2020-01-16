/*
* Naam:    Main
* Version: 0.1
* Author:  Cor Munnik
* Copyright
*/
package nl.iisg.ids03;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nl.iisg.hsncommon.Common1;
import nl.iisg.ref.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Entity;
import javax.swing.*;


/**
 * This is the top class of the application
 * It is the main module
 */
public class StandardizePopulationRegister implements Runnable {


    String inputDirectory;
    static JTextArea textArea = null;
    static DataOutputStream out = null;

    public StandardizePopulationRegister(JTextArea textArea, String inputDirectory) {
        setInputDirectory(inputDirectory);
        setTextArea(textArea);
    }

    public StandardizePopulationRegister(String inputDirectory) {
        setInputDirectory(inputDirectory);
    }

    public StandardizePopulationRegister(DataOutputStream out, String inputDirectory) {
        setInputDirectory(inputDirectory);
        setOut(out);
    }



    public void run() {

        String[] parms = {getInputDirectory()};

        main(parms);
    }


    public static void main(String args[]) {

        List<Person> persons = new ArrayList<Person>();
        List<PersonDynamic> personsDynamic = new ArrayList<PersonDynamic>();
        List<Registration> registrations = new ArrayList<Registration>();
        List<RegistrationAddress> registrationAddresses = new ArrayList<RegistrationAddress>();
        List<OP> ops = new ArrayList<OP>();


        if (false) {
            File file = new File("h:/sysout.log");
            try {
                PrintStream printStream = new PrintStream(new FileOutputStream(file));
                System.out.println("Output in h:/sysout.log");
                System.setOut(printStream);

            } catch (FileNotFoundException e) {
            }
        }

        Runtime runtime = Runtime.getRuntime();


        String inputDirectory = null;

        print("\nPopulation Register - Standardize      started\n");


        if (args.length > 0)
            inputDirectory = args[0];
        else {
        	System.out.println("No input directory specified");
            System.exit(0);
        }
        
        String [] requiredFiles = {"B2.DBF", "B3.DBF", "B4.DBF", "B6.DBF"};

        String missingFile = Common1.nonExisitingFile(inputDirectory, requiredFiles);
        if(missingFile != null){
        	print("Required file " + missingFile + " missing\n");
        	return;
        }



        // Initialization
        
        Initialiser i = new Initialiser();

        print("Creating databases....");

        
        // Create B* tables

        i.createOriginalBTables();

        // Create B*_st tables

        i.createBTables();

        // Create Reference tables

        i.createRTables();

        // Create Message tables

        i.createMTables();

        //System.exit(0);

        // Load B*-tables and reference tables

        print("Reading input and reference files...");

        
        i.loadTables(inputDirectory, persons, personsDynamic, registrations, registrationAddresses);        	
        	
        // Save the B* Tables
        
		print("Saving input files...");        

        
        i.saveBTables(persons, personsDynamic, registrations, registrationAddresses);

        // Link the objects
        
		print("Checking input files...");        


        i.linkObjects(ops, persons, personsDynamic, registrations, registrationAddresses);

        // Checking

        CheckManager cm = new CheckManager();

        // Check the objects

        boolean checkOK = cm.Check(ops, persons, personsDynamic, registrations, registrationAddresses);
        //checkOK = true;
        
        
        // Save messages to disk
        
        print("Saving messages...");  
        
        Message.write();

        
        if(!checkOK){

        	print("Fatal error has occurred. Check bfout1ft and repair");
        	print("\nPopulation Register - Standardize      finished\n");
        	System.out.println("\nPopulation Register - Standardize      finished\n");
        	return;
        }

	    
        // Conversion

        ConvertManager cvm = new ConvertManager();


        // Convert the objects

        print("Convert objects...");    

        cvm.convert(ops);

        // Identify the Persons

        print("Identify Persons...");    

        cvm.identify(ops);

        // Give dates to Persons

        print("Date Persons...");    

        cvm.giveDate(ops);


        // Determine the relations of all persons in a registry to each other (for all registries)

        print("Determine relations between Persons...");    

        
        cvm.relateAllToAll(ops);


        // Print the objects

        cvm.print(ops);

        // Truncate the objects

		print("Truncating objects...");        

        cvm.truncate(ops);

        // Write the objects to the database

		print("Writing output tables...");        

        
        cvm.write(ops);  // write the objects to the standardized tables

		print("Writing reference tables...");        

        Ref.truncate();      // truncate ref tables
        Ref.finalise();      // write non-matched reference entries to database

        // Save messages to disk
        
        print("Saving messages...");  
        
        Message.write();


        print("\nPopulation Register - Standardize      finished\n");


    }

    public String getInputDirectory() {
        return inputDirectory;
    }


    public void setInputDirectory(String inputDirectory) {
        this.inputDirectory = inputDirectory;
    }

    public void setTextArea(JTextArea textArea) {
        StandardizePopulationRegister.textArea = textArea;
    }

    public void setOut(DataOutputStream out) {
        StandardizePopulationRegister.out = out;
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


}

  
  


