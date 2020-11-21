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
			Document doc = db.parse(new File("XMLCIEYIE.xml")); //F�jl kiv�laszt�sa, hogy mit szeretn�nk olvasni
			doc.getDocumentElement().normalize();
			
			Element rootElement = doc.getDocumentElement(); //Kiv�lasztjuk a gy�k�r elemet
			System.out.println("Gy�k�r elem: " + rootElement.getNodeName());
			NodeList childNodes = rootElement.getChildNodes(); //A gy�k�r elemnek a gyerekeit elt�roljuk
			
			for(int i=0; i<childNodes.getLength(); i++) {//V�gigj�rjuk az elt�rolt csom�pontokat
				Node node = childNodes.item(i);//Kiv�lasztjuk az i. csom�pontot
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element)node;
					Node actualNode = element.getFirstChild();
					while(actualNode != null) {
						if(actualNode.getNodeType() == Node.ELEMENT_NODE) {
							Element actualElement = (Element)actualNode;
							System.out.println(" " + actualElement.getNodeName() + ": " + actualElement.getTextContent());//Ki�rjuk az aktu�lis elem adatait
						}
						actualNode=actualNode.getNextSibling();
					}
					System.out.println();
				}
			}
			
		}catch(ParserConfigurationException e){ //Hibakezel�k
			e.printStackTrace();
		}catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
