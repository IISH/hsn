package nl.iisg.alfalabFrontEnd;

import word.api.interfaces.IDocument;
import word.w2004.Document2004;
import word.w2004.elements.*;
import word.w2004.style.Font;


import java.io.*;


/**
 * Created by IntelliJ IDEA.
 * User: cro
 * Date: 19-1-12
 * Time: 9:05
 */
public class CreateWordDoc {

    public String headerTitle = "HSN-PRINT inschrijvingen BEVOLKINGSREGISTER\n"; // 18 CG TIMES
    public String headerBeschrijvinig = "Projecttitel: \n\n" + "Datum:\n\n" + "Opmerkingen: \n\n"; // 14

    public final static String INPUT_DIRECTORY = "temp";
    public CreateWordDoc() {
    }

    public void generatePopulationRegisterPrintout(File file, String outputDestination) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(file));
        String line;

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File(INPUT_DIRECTORY + File.separator + outputDestination));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Create the word document
        IDocument myDoc = new Document2004();
        myDoc.addEle(Heading1.with(headerTitle).create());
        myDoc.addEle(Heading2.with("Projecttitel: ").create());
        myDoc.addEle(Heading2.with("Datum: ").create());
        myDoc.addEle(Heading2.with("Opmerkingen: ").create());
//        myDoc.addEle(Paragraph.with(headerUitleg).create());
        myDoc.addEle(new PageBreak()); //two break lines

        int i = 0;

        //int cnt = 1;
        while ((line = input.readLine()) != null) {
        	//if(cnt == 81)
        		//break;
            String ampersand = "&(?![a-zA-Z][a-zA-Z][a-zA-Z]?[a-zA-Z]?;)";
            //line =  line.replaceAll(ampersand, "&amp;");
            line =  line.replaceAll("&", "&amp;");
            //System.out.println(cnt + "   " + line);
            if(line.equals("")) line = "\n";

            myDoc.addEle(Paragraph.withPieces(ParagraphPiece.with(line).withStyle().font(Font.COURIER).bold().fontSize("7").create()));
            //cnt++;
        }

        String myWord = myDoc.getContent();

        writer.println(myWord);
        writer.close();


    }


}
