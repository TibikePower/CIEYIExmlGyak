package hu.domparse.cieyie;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DOMReadCIEYIE {
	public static void main(String[] args) {
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File("XMLCIEYIE.xml")); //Fájl kiválasztása, hogy mit szeretnénk olvasni
			doc.getDocumentElement().normalize();
			
			Element rootElement = doc.getDocumentElement(); //Kiválasztjuk a gyökér elemet
			System.out.println("Gyökér elem: " + rootElement.getNodeName());
			NodeList childNodes = rootElement.getChildNodes(); //A gyökér elemnek a gyerekeit eltároljuk
			
			for(int i=0; i<childNodes.getLength(); i++) {//Végigjárjuk az eltárolt csomópontokat
				Node node = childNodes.item(i);//Kiválasztjuk az i. csomópontot
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element)node;
					Node actualNode = element.getFirstChild();
					while(actualNode != null) {
						if(actualNode.getNodeType() == Node.ELEMENT_NODE) {
							Element actualElement = (Element)actualNode;
							System.out.println(" " + actualElement.getNodeName() + ": " + actualElement.getTextContent());//Kiírjuk az aktuális elem adatait
						}
						actualNode=actualNode.getNextSibling();
					}
					System.out.println();
				}
			}
			
		}catch(ParserConfigurationException e){ //Hibakezelõk
			e.printStackTrace();
		}catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
