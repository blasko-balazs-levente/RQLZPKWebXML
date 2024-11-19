package xpathrqlzpk1119;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class xPathRQLZPK {

	public static void main(String[] args) {
		
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse("RQLZPKHallgato.xml");
			document.getDocumentElement().normalize();
			XPath xPath = XPathFactory.newInstance().newXPath();
			String bb = "/class/hallgato";
			NodeList rqlzpk = (NodeList) xPath.compile(bb).evaluate(document, XPathConstants.NODESET);
			for(int i = 0; i < rqlzpk.getLength(); i++) {
				Node node = rqlzpk.item(i);
				System.out.println("\nAktuális elem:" + node.getNodeName());
				if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("hallgato")) {
					Element element = (Element) node;
					System.out.println("Hallgató ID:" + element.getAttribute("id"));
					System.out.println("Keresztnév:" + element.getElementsByTagName("keresztnev").item(0).getTextContent());
					System.out.println("Vezetéknév:" + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
					System.out.println("Becenév:" + element.getElementsByTagName("becenev").item(0).getTextContent());
					System.out.println("Kor:" + element.getElementsByTagName("kor").item(0).getTextContent());
				}
			}
			
		}catch(ParserConfigurationException e) {
			e.printStackTrace();
		}catch (SAXException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
