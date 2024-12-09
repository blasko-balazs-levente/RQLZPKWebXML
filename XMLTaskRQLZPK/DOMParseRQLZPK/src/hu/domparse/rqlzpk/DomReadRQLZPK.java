package hu.domparse.rqlzpk;

import java.io.File;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomReadRQLZPK {

    public static void main(String[] args) {
        try {
            // Az XML fájl betöltése
            File xmlFile = new File("XMLRQLZPK.xml");

            // DOM Parser inicializálása
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            // Az XML fájl feldolgozása DOM Document objektummá
            Document doc = db.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Gyökérelem kiírása
            Element root = doc.getDocumentElement();
            System.out.println("Gyökérelem: " + root.getNodeName());

            // Minden szekció (pl. Orvosok, Paciensek, Vizsgalatok) feldolgozása
            NodeList sections = root.getChildNodes();
            StringBuilder output = new StringBuilder();

            for (int i = 0; i < sections.getLength(); i++) {
                Node section = sections.item(i);
                if (section.getNodeType() == Node.ELEMENT_NODE) {
                    Element sectionElement = (Element) section;
                    output.append(sectionElement.getNodeName()).append("\n");

                    // Gyermekelemek feldolgozása
                    NodeList items = sectionElement.getChildNodes();
                    for (int j = 0; j < items.getLength(); j++) {
                        Node item = items.item(j);
                        if (item.getNodeType() == Node.ELEMENT_NODE) {
                            Element itemElement = (Element) item;
                            output.append(itemElement.getNodeName()).append("\n");

                            // Attribútumok kiírása (ha vannak)
                            NamedNodeMap attributes = itemElement.getAttributes();
                            for (int k = 0; k < attributes.getLength(); k++) {
                                Node attribute = attributes.item(k);
                                output.append("    Azonosító: ")
                                      .append(attribute.getNodeName())
                                      .append(" = ")
                                      .append(attribute.getNodeValue()).append("\n");
                            }

                            // Belső tartalom kiírása
                            NodeList children = itemElement.getChildNodes();
                            for (int k = 0; k < children.getLength(); k++) {
                                Node child = children.item(k);
                                if (child.getNodeType() == Node.ELEMENT_NODE) {
                                    Element childElement = (Element) child;
                                    output.append("    ").append(childElement.getNodeName())
                                          .append(": ").append(childElement.getTextContent().trim()).append("\n");
                                }
                            }
                        }
                    }
                }
            }

            // Konzolra írás
            System.out.println(output);

            // Tartalom mentése fájlba
            FileWriter writer = new FileWriter("OutputRQLZPK.txt");
            writer.write(output.toString());
            writer.close();
            System.out.println("Az adatokat elmentettük az OutputRQLZPK.txt fájlba.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
