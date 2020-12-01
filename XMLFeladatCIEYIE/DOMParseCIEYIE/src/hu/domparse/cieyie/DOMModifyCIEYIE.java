package hu.domparse.cieyie;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;



public class DOMModifyCIEYIE {
	public static void main(String[] args) throws TransformerException {
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File("XMLCIEYIE.xml")); //Fájl kiválasztása, hogy mit szeretnénk olvasni
			
			//FUTÁR NEVEK, FIZETÉS MEGVÁLTOZTATÁSA
			Node futarok = doc.getElementsByTagName("futarok").item(0); //Kiválasztjuk a futárok csomópontot
			NodeList flist = futarok.getChildNodes();
			System.out.println("-------------------[ FUTÁROK  ]-------------------");
			for (int i = 0; i < flist.getLength(); i++) {
	            Node node = flist.item(i);
	            if (node.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) node;
	               System.out.println("Név : " + eElement.getElementsByTagName("nev").item(0).getTextContent());
	               if(eElement.getElementsByTagName("nev").item(0).getTextContent().equals("Téglás Máté")) {
	            	   eElement.getElementsByTagName("nev").item(0).setTextContent("Rókás Réka");
	            	   System.out.println("-->Új név : " + eElement.getElementsByTagName("nev").item(0).getTextContent());
	               }
	               if(eElement.getElementsByTagName("nev").item(0).getTextContent().equals("Bubenkó Zsolt")) {
	            	   eElement.getElementsByTagName("nev").item(0).setTextContent("Új Zsolt");
	            	   System.out.println("-->Új név : " + eElement.getElementsByTagName("nev").item(0).getTextContent());
	               }
	               if(eElement.getElementsByTagName("fizetes").item(0).getTextContent().equals("40000")) {
	            	   eElement.getElementsByTagName("fizetes").item(0).setTextContent("400000");
	            	   System.out.println("-->Új fizetés : " + eElement.getElementsByTagName("fizetes").item(0).getTextContent()+", nála: "+eElement.getElementsByTagName("nev").item(0).getTextContent());
	               }
	            }
	        }
			System.out.println("-------------------[ FUTÁROK  ]-------------------");
			//VEVÕ NÉV, EMAIL MEGVÁLTOZTATÁS
			System.out.println("-------------------[ VEVÕK  ]-------------------");
			Node vevok = doc.getElementsByTagName("vevok").item(0); //Kiválasztjuk a vevõk csomópontot
			NodeList vlist = vevok.getChildNodes();
			
			for (int i = 0; i < vlist.getLength(); i++) {
	            Node node = vlist.item(i);
	            if (node.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) node;
	               System.out.println("Név : " + eElement.getElementsByTagName("nev").item(0).getTextContent());
	               if(eElement.getElementsByTagName("nev").item(0).getTextContent().equals("XMLTeszt Elek")) {
	            	   eElement.getElementsByTagName("nev").item(0).setTextContent("Hiteles Henrik");
	            	   System.out.println("-->Új név : " + eElement.getElementsByTagName("nev").item(0).getTextContent());
	               }
	               if(eElement.getElementsByTagName("email").item(0).getTextContent().equals("l.lajcsi@gmail.com")) {
	            	   eElement.getElementsByTagName("email").item(0).setTextContent("l.lajoska32523@gmail.com");
	            	   System.out.println("-->Új email : " + eElement.getElementsByTagName("email").item(0).getTextContent());
	               }
	            }
	        }
			System.out.println("-------------------[ VEVÕK  ]-------------------");
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
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(new File("XMLCIEYIE.updated.xml"));
	        transformer.transform(source, result);		
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