package domrqlzpk1022;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class DOMWrite1 {

    public static void main(String[] args) {
        try {
            // 1. XML fájl betöltése
            File inputFile = new File("orarendRQLZPK.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);

            // 2. Fa struktúra kiírása a konzolra
            System.out.println("Fa struktúra:");
            printNode(doc.getDocumentElement(), "");

            // 3. Az XML tartalom kiírása új fájlba
            File outputFile = new File("orarend1Neptunkod.xml");
            saveXMLToFile(doc, outputFile);
            System.out.println("\nAz XML tartalom kiírva az orarend1Neptunkod.xml fájlba.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Rekurzív függvény a fa struktúra kiírására
    private static void printNode(Node node, String indent) {
        // Ha a csomópont elem, kiírjuk a nevét és értékét
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            System.out.println(indent + "<" + node.getNodeName() + ">");
        }

        // Kiírjuk az alcsomópontokat
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            printNode(children.item(i), indent + "  ");
        }

        // Ha a csomópont elem, lezárjuk a címkét
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            System.out.println(indent + "</" + node.getNodeName() + ">");
        }
    }

    // Az XML dokumentum mentése fájlba
    private static void saveXMLToFile(Document doc, File file) throws TransformerException, IOException {
        // Transformer létrehozása a fájlba történő kiíráshoz
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        // Fájlba írás
        StreamResult result = new StreamResult(file);
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, result);
    }
}
