package progmod_gyak_beadando;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main_screen {
    
    public static int egyensúly = 0;
    public static int main_scr_fajlszamlalo = 0;
    
    public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{

        System.out.println(Main_screen_function(egyensúly));
    }
    
    public static int Main_screen_function(int egyensúly) throws XPathExpressionException, ParserConfigurationException, FileNotFoundException, SAXException, IOException{

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
         
        Document doc = db.parse(new FileInputStream(new File("program_database.xml")));
 
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);

        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();

        Scanner scanner = new Scanner(new File("c_variable.txt"));
                while(scanner.hasNextInt())
                {
                    main_scr_fajlszamlalo = scanner.nextInt();
                }
        
        XPathExpression expr = xpath.compile("//transaction[azon<='"+main_scr_fajlszamlalo+"']/price/text()");
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        ArrayList<String> list=new ArrayList<>(); 
        
        for (int i = 0; i < nodes.getLength(); i++) {
            list.add(nodes.item(i).getNodeValue());
        }
                
        int sum = 0;
        for (int j = 0; j < nodes.getLength(); j++){
            try{
            sum += parseInt(nodes.item(j).getNodeValue());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR 1 \nNem számítható helyes érték,\n"
                        + "mivel érvénytelen összegek is léteznek az adatbázisban.");
            }
        }
        
        int darabszam = list.size();
        
        try{
        egyensúly = sum / darabszam;
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR 2 \nNem számítható helyes érték, \n"
                    + "mivel még egy bejegyzés sincs az adatbázisban.");
        }
        
        return egyensúly;
    }
}