package domrqlzpk1022;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class DOMRead {
    public static void main(String[] args) {
        try {
            // XML fájl beolvasása
            File inputFile = new File("hallgatoRQLZPK.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);
            
            // Normalizáljuk az XML-t (összehozzuk a szomszédos szövegeket)
            document.getDocumentElement().normalize();
            
            // A "hallgatok" gyökér elem
            Element root = document.getDocumentElement();
            
            // "hallgato" elemek listája
            NodeList hallgatokList = root.getElementsByTagName("hallgato");
            
            // Iterálás a hallgato elemeken
            for (int i = 0; i < hallgatokList.getLength(); i++) {
                Node hallgatoNode = hallgatokList.item(i);
                
                if (hallgatoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element hallgatoElement = (Element) hallgatoNode;
                    
                    // Kiíratjuk a hallgato blokk információit
                    System.out.println("Hallgato ID: " + hallgatoElement.getAttribute("id"));
                    System.out.println("Keresztnev: " + getTagValue("keresztnev", hallgatoElement));
                    System.out.println("Vezeteknev: " + getTagValue("vezeteknev", hallgatoElement));
                    System.out.println("Foglalkozas: " + getTagValue("foglalkozas", hallgatoElement));
                    System.out.println("---------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Segédmetódus, hogy kiolvassuk az adott tag értékét
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag);
        if (nodeList != null && nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            return node.getTextContent();
        }
        return "";
    }
}
