package domrqlzpk1105;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomReadRQLZPK {
	
	public static void main(String[] args) {
		File xmlFile = new File("resources/RQLZPK_orarend.xml");
		
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			NodeList oraList = doc.getElementsByTagName("ora");
			
			for(int i = 0; i < oraList.getLength(); i++) {
				Node oraNode = oraList.item(i);
				printOraDetails((Element) oraNode);
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void printOraDetails(Element oraNode) {
		System.out.println("Id: " + oraNode.getAttribute("id"));
		System.out.println("Tipus: " + oraNode.getAttribute("tipus"));
		
		printTargy(oraNode);
		printIdopont(oraNode);
		printHelyszin(oraNode);
		printOktato(oraNode);
	}
	
	private static void printTargy(Element oraNode) {
		NodeList targyList = oraNode.getElementsByTagName("targy");
		for (int i = 0 ; i < targyList.getLength(); i++) {
			Node targyNode = targyList.item(i);
			if (targyNode.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("Tárgy:" + targyNode.getTextContent());
			}
		}
	}
	
	private static void printIdopont(Element oraNode) {
		NodeList idopontList = oraNode.getElementsByTagName("idopont");
		for(int i = 0; i < idopontList.getLength(); i++) {
			System.out.print("Idopont: ");
			NodeList childList = idopontList.item(i).getChildNodes();
			for (int j = 0; j < childList.getLength(); j++) {
				Node childNode = childList.item(j);
				if (childNode.getNodeType() == Node.ELEMENT_NODE) {
					System.out.print(childNode.getTextContent() + " ");
				}
			}
		}
	}
	
	private static void printHelyszin(Element oraNode) {
		NodeList helyszinList = oraNode.getElementsByTagName("helyszin");
		for (int i = 0; i < helyszinList.getLength(); i++) {
			Node helyszinNode = helyszinList.item(i);
			if(helyszinNode.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("\nHelyszin: " + helyszinNode.getTextContent());
			}
		}
	}
	
	private static void printOktato(Element oraNode) {
		NodeList oktatoList = oraNode.getElementsByTagName("oktato");
		for(int i = 0; i < oktatoList.getLength(); i++) {
			Node oktatoNode = oktatoList.item(i);
			if (oktatoNode.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("Oktato: " + oktatoNode.getTextContent());
			}
		}
	}
}