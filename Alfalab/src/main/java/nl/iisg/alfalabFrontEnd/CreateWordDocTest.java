package nl.iisg.alfalabFrontEnd;

import word.api.interfaces.IDocument;
import word.w2004.Document2004;
import word.w2004.elements.BreakLine;
import word.w2004.elements.Heading1;
import word.w2004.elements.Paragraph;
import word.w2004.elements.ParagraphPiece;
import word.w2004.style.Font;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: cro
 * Date: 19-1-12
 * Time: 9:05
 * To change this template use File | Settings | File Templates.
 */
public class CreateWordDocTest {

    public CreateWordDocTest() {
        try {
            generatePopulationRegisterPrintout(new File("H:\\testPrintOutput\\pop_reg_errors_selected.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generatePopulationRegisterPrintout(File file) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(file));

        String line;
        StringBuffer result = new StringBuffer();


        PrintWriter writer = null;
        File f = new File("H:\\testPrintOutput\\pop_reg_errors_selected.doc");

        if (!f.exists()) {
            f.createNewFile();
            System.out.println("New file has been created to the current directory");
        }
        try {
            writer = new PrintWriter(new FileWriter(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //Create the word document
        IDocument myDoc = new Document2004();
        myDoc.addEle(Heading1.with("HSN-PRINT inschrijvingen BEVOLKINGSREGISTER").create());
        myDoc.addEle(new BreakLine(2)); //two break lines


        while ((line = input.readLine()) != null) {
            String ampersand = "&(?![a-zA-Z][a-zA-Z][a-zA-Z]?[a-zA-Z]?;)";
            line =  line.replaceAll(ampersand, "&amp;");
            myDoc.addEle(Paragraph.withPieces(ParagraphPiece.with(line).withStyle().font(Font.COURIER).bold().fontSize("7").create()));
        }

        String myWord = myDoc.getContent();

        writer.println(myWord);
        writer.close();


    }

    public static void main(String[] args) {

        CreateWordDocTest createWordDocTest = new CreateWordDocTest();

    }

}
