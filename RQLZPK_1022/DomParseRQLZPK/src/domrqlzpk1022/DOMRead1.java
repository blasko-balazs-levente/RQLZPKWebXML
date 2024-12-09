package domrqlzpk1022;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMRead1 {

    public static void main(String[] args) {
        try {
            // XML fájl betöltése
            File xmlFile = new File("orarendRQLZPK.xml"); // Cseréld ki a fájl elérési útját, ha szükséges

            // DocumentBuilderFactory és DocumentBuilder objektum létrehozása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // XML fájl beolvasása a Document objektumba
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize(); // Az XML struktúra normalizálása

            // Az összes <ora> elem lekérése
            NodeList hoursList = doc.getElementsByTagName("ora");

            // Végigiterálunk az összes órán
            for (int i = 0; i < hoursList.getLength(); i++) {
                Node hourNode = hoursList.item(i);

                // Csak a DOM elemeket vizsgáljuk
                if (hourNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element hourElement = (Element) hourNode;

                    // Óra adatok lekérése az XML-ből
                    String id = hourElement.getAttribute("id");
                    String tipus = hourElement.getAttribute("tipus");
                    String targy = getTagValue("targy", hourElement);
                    String nap = getTagValue("nap", hourElement);
                    String tol = getTagValue("tol", hourElement);
                    String ig = getTagValue("ig", hourElement);
                    String helyszin = getTagValue("helyszin", hourElement);
                    String oktato = getTagValue("oktato", hourElement);
                    String szak = getTagValue("szak", hourElement);

                    // Óra adatainak kiírása blokk formában
                    System.out.println("Óra ID: " + id);
                    System.out.println("Típus: " + tipus);
                    System.out.println("Tárgy: " + targy);
                    System.out.println("Nap: " + nap);
                    System.out.println("Időpont: " + tol + " - " + ig);
                    System.out.println("Helyszín: " + helyszin);
                    System.out.println("Oktató: " + oktato);
                    System.out.println("Szak: " + szak);
                    System.out.println("------------------------------");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Segédfüggvény az XML tag tartalmának lekérésére
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }
}

