package nl.iisg.alfalabFrontEnd;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: cro
 * Date: 11-1-12
 * Time: 11:51
 * To change this template use File | Settings | File Templates.
 */
public class ClientConfig {
    //private static final String configFileLocation = "H:\\My Documents\\Eclipse2\\Alfalab\\config\\config";
    private static final String tempFileLocation = "config" + File.separator + "temp";
    //private final static String tempFileLocation = "H:\\My Documents\\Eclipse2\\Alfalab\\config\\temp";
   private final static String configFileLocation = "config" + File.separator + "file";



    private File configFile;

    private String latestInputLocation;  // points to the input dbf directory that has been used the last time


    public ClientConfig() {
    }

    public String getLatestInputLocation() {

        try {
            String result = getConfigParameter("latestInputLocation");

            if (result == null) {
                return ".";
            } else {
                return result;
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    public void setLatestInputLocation(String location) throws Exception {

        setConfigParameter("latestInputLocation", location + File.separator);

    }


    private void setConfigParameter(String setParameter, String value) throws Exception {

        File tempFile = new File(tempFileLocation);
        File configFile = new File(configFileLocation);

        BufferedReader reader = null;
        try{
        	reader = new BufferedReader(new FileReader(configFile));
        }
        catch(FileNotFoundException e){
        	// File not found is normal
        }
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        String parameter;
        boolean found = false;

		if (reader != null) {
			while ((line = reader.readLine()) != null) {
				parameter = line.split("=")[0].trim();
				if (parameter.equals(setParameter)) {
					found = true;
					writer.write(parameter + "=" + value);
				} else
					writer.write(line);
			}
			reader.close();
		}

        if (!found)
            writer.write(setParameter + "=" + value);
        
        writer.close();

        //Delete the original file
        configFile.delete();

        boolean successful = tempFile.renameTo(configFile);

        if (!successful) {
            throw new Exception("Error while renaming temp file to config file.");
        }

    }

    private String getConfigParameter(String getParameter) throws Exception {

        String line = null;
        String parameter = null;
        String result = null;
        boolean found = false;
        
        System.out.println("getConfigParameter from " + configFileLocation);
        File configFile = new File(configFileLocation);
        if(!configFile.exists()){
        	System.out.println("File does not exist");
        	return null;
        }
     
        
        
        BufferedReader br = new BufferedReader(new FileReader(configFile));

        while ((line = br.readLine()) != null) {
        	
        	System.out.println("line =  " + line );

            parameter = line.split("=")[0].trim();
            if (parameter.equals(getParameter)) {

                result = line.split("=")[1].trim();
                found = true;

                System.out.println("found");
            }

        }

        br.close();

        if (!found) {
            return null;
        } else {
            return result;
        }

    }


}


