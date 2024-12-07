package hu.domparse.rqlzpk;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

import java.io.File;

public class DomWriteRQLZPK {

    public static void main(String[] args) {
        try {
            // Dokumentum építő beállítása
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            // Az XML fájl betöltése
            Document document = db.parse(new File("XMLRQLZPK.xml"));
            document.getDocumentElement().normalize();

            // Fa struktúra kiírása konzolra
            System.out.println("XML Fa Struktúra:");
            printNode(document.getDocumentElement(), 0);

            // Új XML fájl létrehozása
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("XMLRQLZPK1.xml"));

            transformer.transform(source, result);

            System.out.println("Az XML fájl sikeresen mentve: XMLRQLZPK1.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fa struktúra kiíratása rekurzívan
    private static void printNode(Node node, int depth) {
        String indent = "  ".repeat(depth);
        System.out.println(indent + "Név: " + node.getNodeName() + ", Érték: " + node.getNodeValue());

        NamedNodeMap attributes = node.getAttributes();
        if (attributes != null) {
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attribute = attributes.item(i);
                System.out.println(indent + "  Attribútum: " + attribute.getNodeName() + " = " + attribute.getNodeValue());
            }
        }

        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE || 
                (child.getNodeType() == Node.TEXT_NODE && !child.getNodeValue().trim().isEmpty())) {
                printNode(child, depth + 1);
            }
        }
    }
}
