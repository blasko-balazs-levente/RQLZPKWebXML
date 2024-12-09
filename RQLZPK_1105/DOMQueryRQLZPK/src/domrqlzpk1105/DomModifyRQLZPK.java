package domrqlzpk1105;

import java.io.File;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class DomModifyRQLZPK{
	
	public static void main(String argv[]) {
		
		try {
			File inputFile = new File("hallgatokquery.xml");
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(inputFile);
			
			Node hallgatok = doc.getFirstChild();
			
			Node hallgat = doc.getElementsByTagName("hallgato").item(0);
			
			NameNodeMap attr = hallgat.getAttributes();
			Node nodeAttr = attr.getNamedItem("id");
			nodeAttr.setTextContent("02");
			
			NodeList list = hallgat.getChildNodes();
			
			for (int temp = 0; temp < list.getLength(); temp++) {
				Node node = list.item(temp);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					
					if ("keresztnev".equals(eElement.getNodeName())) {
						if("Piroska".equals(eElement.getTextContent())) {
							eElement.setTextContent("Olivia");
						}
					}
					
					if ("vezeteknev".equals(eElement.getNodeName())) {
						if("Kiss".equals(eElement.getTextContent())) {
							eElement.setTextContent("Vigh");
						}
					}
				}
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			DOMSource source = new DOMSource(doc);
			
			System.out.println("---Módosított fájl---");
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}