package hu.domparse.rqlzpk;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

import java.io.File;

public class DomModifyRQLZPK {

    public static void main(String[] args) {
        try {
            // Dokumentum beolvasása
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new File("XMLRQLZPK.xml"));
            document.getDocumentElement().normalize();

            System.out.println("XML dokumentum módosítása:");

         // 1. Módosítás: Egy adott orvos szakterületének módosítása
            System.out.println("\n1. Egy adott orvos (OID=03) szakterületének módosítása:");
            NodeList orvosok = document.getElementsByTagName("Orvos");
            for (int i = 0; i < orvosok.getLength(); i++) {
                Element orvos = (Element) orvosok.item(i);
                if (orvos.getAttribute("OID").equals("03")) {
                	Node nev = orvos.getElementsByTagName("Nev").item(0);
                	String orvosNev = nev.getTextContent();
                    Node szakterulet = orvos.getElementsByTagName("Szakterulet").item(0);
                    String regiSzakterulet = szakterulet.getTextContent();
                    szakterulet.setTextContent("Radiológia");
                    String ujSzakterulet = szakterulet.getTextContent();
                    System.out.println("Név:" + orvosNev);
                    System.out.println("Régi szakterület: " + regiSzakterulet);
                    System.out.println("Módosított szakterület: " + ujSzakterulet);
                }
            }

         // 2. Módosítás: Új telefonszám hozzáadása egy adott pácienshez
            System.out.println("\n2. Egy páciens (PID=02) új telefonszámának hozzáadása:");
            NodeList paciensek = document.getElementsByTagName("Paciens");
            for (int i = 0; i < paciensek.getLength(); i++) {
                Element paciens = (Element) paciensek.item(i);
                if (paciens.getAttribute("PID").equals("03")) {
                    Node paciensNev = paciens.getElementsByTagName("Nev").item(0);
                    String nev = paciensNev.getTextContent();
                    System.out.println("Név: " + nev);
                    NodeList telefonszamokLista = paciens.getElementsByTagName("Telefonszam");
                    System.out.println("Eddigi telefonszámok:");
                    for (int j = 0; j < telefonszamokLista.getLength(); j++) {
                        Node telefonszam = telefonszamokLista.item(j);
                        System.out.println(telefonszam.getTextContent());
                    }
                    Element telefonszamokElem = (Element) paciens.getElementsByTagName("Telefonszamok").item(0);
                    Element ujTelefonszam = document.createElement("Telefonszam");
                    ujTelefonszam.setTextContent("+36/70 154 3441");
                    telefonszamokElem.appendChild(ujTelefonszam);
                    System.out.println("Hozzáadott telefonszám:\n" + ujTelefonszam.getTextContent());
                }
            }


            // 3. Módosítás: Új gyógyszer hozzáadása a dokumentumhoz
            System.out.println("\n3. Új gyógyszer hozzáadása:");
            Element gyogyszerek = (Element) document.getElementsByTagName("Gyogyszerek").item(0);
            Element ujGyogyszer = document.createElement("Gyogyszer");
            ujGyogyszer.setAttribute("GID", "05");

            Element nev = document.createElement("Nev");
            nev.setTextContent("Diclofenac");
            ujGyogyszer.appendChild(nev);

            Element adagolas = document.createElement("Adagolas");
            adagolas.setTextContent("50 mg naponta 2-3 alkalommal");
            ujGyogyszer.appendChild(adagolas);

            Element leiras = document.createElement("Leiras");
            leiras.setTextContent("Gyulladáscsökkentő gyógyszer, amely az izomfájdalmak enyhítésére szolgál.");
            ujGyogyszer.appendChild(leiras);

            Element mellekhatasok = document.createElement("Mellekhatasok");
            Element mellekhatas = document.createElement("Mellekhatas");
            mellekhatas.setTextContent("Gyomorpanaszok, fejfájás");
            mellekhatasok.appendChild(mellekhatas);

            ujGyogyszer.appendChild(mellekhatasok);
            gyogyszerek.appendChild(ujGyogyszer);
            System.out.println("Hozzáadott gyógyszer:\nNév: " + nev.getTextContent()
            					+"\nAdagolás: " + adagolas.getTextContent()
            					+"\nLeírás: " + leiras.getTextContent()
            					+"\nMellékhatás: " + mellekhatas.getTextContent());

            // 4. Módosítás: Egy vizsgálat dátumának frissítése
            System.out.println("\n4. Egy vizsgálat (VID=04) dátumának frissítése:");
            NodeList vizsgalatok = document.getElementsByTagName("Vizsgalat");
            for (int i = 0; i < vizsgalatok.getLength(); i++) {
                Element vizsgalat = (Element) vizsgalatok.item(i);
                if (vizsgalat.getAttribute("VID").equals("04")) {
                	Node tipus = vizsgalat.getElementsByTagName("Tipus").item(0);
                	String vizsgalatTipus = tipus.getTextContent();
                	System.out.println("Vizsgálat típusa: " + vizsgalatTipus);
                    Node datum = vizsgalat.getElementsByTagName("Datum").item(0);
                    String regiDatum = datum.getTextContent();
                    datum.setTextContent("2025-05-01");
                    System.out.println("Régi dátum:" + regiDatum);
                    System.out.println("Frissített dátum: " + datum.getTextContent());
                }
            }

            // Módosítások mentése új fájlba
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("XMLRQLZPK_modified.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

            System.out.println("\nMódosítások mentve az XMLRQLZPK_modified.xml fájlba.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
