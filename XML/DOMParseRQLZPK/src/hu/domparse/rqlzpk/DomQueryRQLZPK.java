package hu.domparse.rqlzpk;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

import java.io.File;

public class DomQueryRQLZPK {

    public static void main(String[] args) {
        try {
            // Dokumentum építő beállítása és XML fájl betöltése
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new File("XMLRQLZPK.xml"));
            document.getDocumentElement().normalize();

            System.out.println("Adatok lekérdezése az XML dokumentumból:");

            // 1. Lekérdezés: Összes "Orvos" elem neve és szakterülete
            System.out.println("\n1. Összes orvos neve és szakterülete:");
            NodeList orvosok = document.getElementsByTagName("Orvos");
            for (int i = 0; i < orvosok.getLength(); i++) {
                Element orvos = (Element) orvosok.item(i);
                String nev = orvos.getElementsByTagName("Nev").item(0).getTextContent();
                String szakterulet = orvos.getElementsByTagName("Szakterulet").item(0).getTextContent();
                System.out.println("Név: " + nev + ", Szakterület: " + szakterulet);
            }

            // 2. Lekérdezés: Egy adott OID-jú orvos elérhetőségei
            System.out.println("\n2. Adott OID-jú orvos (OID=02) elérhetőségei:");
            for (int i = 0; i < orvosok.getLength(); i++) {
                Element orvos = (Element) orvosok.item(i);
                if (orvos.getAttribute("OID").equals("02")) {
                    String telefon = orvos.getElementsByTagName("Telefon").item(0).getTextContent();
                    String email = orvos.getElementsByTagName("Email").item(0).getTextContent();
                    System.out.println("Telefon: " + telefon + ", Email: " + email);
                }
            }

            // 3. Lekérdezés: Összes "Paciens" neve és születési dátuma
            System.out.println("\n3. Összes páciens neve és születési dátuma:");
            NodeList paciensek = document.getElementsByTagName("Paciens");
            for (int i = 0; i < paciensek.getLength(); i++) {
                Element paciens = (Element) paciensek.item(i);
                String nev = paciens.getElementsByTagName("Nev").item(0).getTextContent();
                String szuletesiDatum = paciens.getElementsByTagName("SzuletesiDatum").item(0).getTextContent();
                System.out.println("Név: " + nev + ", Születési dátum: " + szuletesiDatum);
            }

            // 4. Lekérdezés: Minden "Vizsgálat" típusának és dátumának listázása
            System.out.println("\n4. Vizsgálatok típusai és dátumai:");
            NodeList vizsgalatok = document.getElementsByTagName("Vizsgalat");
            for (int i = 0; i < vizsgalatok.getLength(); i++) {
                Element vizsgalat = (Element) vizsgalatok.item(i);
                String tipus = vizsgalat.getElementsByTagName("Tipus").item(0).getTextContent();
                String datum = vizsgalat.getElementsByTagName("Datum").item(0).getTextContent();
                System.out.println("Típus: " + tipus + ", Dátum: " + datum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
