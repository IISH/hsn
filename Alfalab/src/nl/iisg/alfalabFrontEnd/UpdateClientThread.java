package nl.iisg.alfalabFrontEnd;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: cro
 * Date: 3-11-11
 * Time: 10:17
 * To change this template use File | Settings | File Templates.
 */


public class UpdateClientThread implements Runnable {
    private static final String FILE_TYPE_DBF = "FILE_TYPE_DBF";
    private static final String FILE_TYPE_PRINTFILE = "FILE_TYPE_PRINTFILE";
    private final static String SEND_FILE_COMMAND = "sendFile";


//  String printFileDirectory = "H:\\My Documents\\Eclipse\\Alfalab\\printfiles";
//   String printFileDirectory = "printfiles";
    String printFileDirectory = "C:\\Users\\HP\\Temp";
//  String dbfFileDirectory = "H:\\My Documents\\Eclipse\\Alfalab\\dbffiles";
    String dbfFileDirectory = "dbffiles";

    String dbfTimestampDir = "";

    JTextArea textArea;
    DataInputStream in;

    public UpdateClientThread(JTextArea textArea, DataInputStream in) {
        this.textArea = textArea;
        this.in = in;

    }


    public void receiveText() {
    	try {
    		while (true) {

    			String line = "";

    			line = in.readUTF();

    			if (line.equals(SEND_FILE_COMMAND)) {
    				receiveFileFromServer();
    			} else {
    				textArea.append(line + "\n");
    				textArea.setCaretPosition(textArea.getDocument().getLength());  // Force scrolling

    				if(line.equals("Invalid username and/or password")) {
    					try {
    						Thread.sleep(2000);
    					} catch (InterruptedException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    					System.exit(0);
    				}
    			}
    		}
    	} catch (IOException e) {
    		System.out.println("Update Client Thread Receive failed");
    	}
    }

    public void run() {
    	receiveText();
    }

    private String timestamp() {

    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        return simpleDateFormat.format(new Date());

    }

    private void receiveFileFromServer() {

        try {
            String fileType = in.readUTF();

            if (fileType.equals(FILE_TYPE_DBF)) {
                receiveDbfFile();
            } else if (fileType.equals(FILE_TYPE_PRINTFILE)) {
                receivePrintFile();
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    private void receivePrintFile() throws Exception {

        SimpleDateFormat timestampFileFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        SimpleDateFormat timestampDirFormat = new SimpleDateFormat("dd-MM-yyyy");
        String timestampFile = timestampFileFormat.format(new Date());
        String timestampDir = timestampDirFormat.format(new Date());

        //simpleDateFormat.format(new Date());
        byte[] myByteArray = new byte[200 * 1024 * 1024];
        int bytesRead = 0;
        int current = 0;
        String filename;

        File f;

        long fileLength = in.readLong();
//        textArea.append("File size: " + fileLength + "\n");

        
        filename = in.readUTF();

        textArea.append("Receiving print file \"" + filename + "\" from server\n");

        timestampDir = printFileDirectory + File.separator + timestampDir;

        if (!(new File(timestampDir)).exists()) {

            boolean success = (new File(timestampDir)).mkdir();

            //if (success) {
            //    textArea.append("Directory: " + timestampDir + " created");
            //}

        }

        String printFilePath = timestampDir + File.separator + timestampFile + "_" + filename;
        System.out.println("AAS " + printFilePath); 
        f = new File(printFilePath);

        boolean result;
        if (!f.exists()) {
            result = f.createNewFile();
        } else {
            throw new Exception("Error, print file already exists.");
        }

        if (!result) throw new Exception("Error creating new printfile in directory " + timestampDir);

        FileOutputStream fos = new FileOutputStream(f);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        while (current < fileLength) {
        	System.out.println("Current = " + current);
            bytesRead = in.read(myByteArray, current, ((int) fileLength - current));
            if (bytesRead < 0) {
                throw new InvalidObjectException("Error: Data stream ended prematurely. File " + filename + " not saved!");
            }
            current += bytesRead;
        }

        bos.write(myByteArray, 0, current);
        bos.flush();
        bos.close();
        fos.close();
        textArea.append("Saved print file at: \n" + f.getAbsolutePath());
//        try {
//            Runtime rt = Runtime.getRuntime();
//            rt.exec("cmd.exe /C start " + f.getAbsolutePath());
//        } catch (Exception e) {
//        }
    }

    private void receiveDbfFile() throws Exception {

        SimpleDateFormat simpleDateFormatSeconds = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        String timeStampSeconds                  = simpleDateFormatSeconds.format(new Date());
        SimpleDateFormat timestampDirFormat      = new SimpleDateFormat("dd-MM-yyyy_HH-mm");
        String timestampDir                      = timestampDirFormat.format(new Date());
        
        System.out.println("timeStampSeconds = " + timeStampSeconds);
        System.out.println("timestampDir     = " + timestampDir);

        //simpleDateFormat.format(new Date());
        byte[] myByteArray = new byte[100 * 1024 * 1024];
        int bytesRead = 0;
        int current = 0;
        String filename;

        File f;

        long fileLength = in.readLong();
        //textArea.append("File size: " + fileLength + "\n");
        
        
        filename = in.readUTF();

        textArea.append("Receiving .DBF file \"" + filename + "\" from server\n");

        if(filename.equals("B2.DBF") || filename.equals("DOUBLE_IDNRS_B2_ST.DBF") || filename.equals("DOUBLE_IDNRS_B2.DBF")){ 
            dbfTimestampDir = dbfFileDirectory + File.separator + timestampDir;
            System.out.println("dbfTimestampDir     = " + dbfTimestampDir);
        }
        
        System.out.println("(new File(timestampDir)).exists()) = " + (new File(timestampDir)).exists());

        if (!(new File(timestampDir)).exists()) {
            boolean success = (new File(dbfTimestampDir)).mkdir();
            //if (success) {
              //  textArea.append("Directory: " + dbfTimestampDir + " created\n");
            //}
        }

  //    String dbfFilePath = dbfTimestampDir + File.separator + timeStampSeconds + "_" + filename;
        String dbfFilePath = dbfTimestampDir + File.separator + filename;
        System.out.println("dbfFilePath = " + dbfFilePath);
        f = new File(dbfFilePath);

        boolean result;
        if (!f.exists()) {
            result = f.createNewFile();  
        } else {
            throw new Exception("Error, dbf file already exists.");
        }

        if (!result) throw new Exception("Error creating new dbf file in directory " + timestampDir);

        FileOutputStream fos = new FileOutputStream(f);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        while (current < fileLength) {
            bytesRead = in.read(myByteArray, current, ((int) fileLength - current));
            if (bytesRead < 0) {
                throw new InvalidObjectException("Error: Data stream ended prematurely. File " + filename + " not saved!");
            }
            current += bytesRead;
        }

        bos.write(myByteArray, 0, current);
        bos.flush();
        bos.close();
        fos.close();
        textArea.append("Saved .DBF file at: " + f.getAbsolutePath() + "\n");

    }


}
