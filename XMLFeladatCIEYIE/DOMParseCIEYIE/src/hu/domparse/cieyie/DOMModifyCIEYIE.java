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
			Document doc = db.parse(new File("XMLCIEYIE.xml")); //F�jl kiv�laszt�sa, hogy mit szeretn�nk olvasni
			
			//FUT�R NEVEK, FIZET�S MEGV�LTOZTAT�SA
			Node futarok = doc.getElementsByTagName("futarok").item(0); //Kiv�lasztjuk a fut�rok csom�pontot
			NodeList flist = futarok.getChildNodes();
			System.out.println("-------------------[ FUT�ROK  ]-------------------");
			for (int i = 0; i < flist.getLength(); i++) {
	            Node node = flist.item(i);
	            if (node.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) node;
	               System.out.println("N�v : " + eElement.getElementsByTagName("nev").item(0).getTextContent());
	               if(eElement.getElementsByTagName("nev").item(0).getTextContent().equals("T�gl�s M�t�")) {
	            	   eElement.getElementsByTagName("nev").item(0).setTextContent("R�k�s R�ka");
	            	   System.out.println("-->�j n�v : " + eElement.getElementsByTagName("nev").item(0).getTextContent());
	               }
	               if(eElement.getElementsByTagName("nev").item(0).getTextContent().equals("Bubenk� Zsolt")) {
	            	   eElement.getElementsByTagName("nev").item(0).setTextContent("�j Zsolt");
	            	   System.out.println("-->�j n�v : " + eElement.getElementsByTagName("nev").item(0).getTextContent());
	               }
	               if(eElement.getElementsByTagName("fizetes").item(0).getTextContent().equals("40000")) {
	            	   eElement.getElementsByTagName("fizetes").item(0).setTextContent("400000");
	            	   System.out.println("-->�j fizet�s : " + eElement.getElementsByTagName("fizetes").item(0).getTextContent()+", n�la: "+eElement.getElementsByTagName("nev").item(0).getTextContent());
	               }
	            }
	        }
			System.out.println("-------------------[ FUT�ROK  ]-------------------");
			//VEV� N�V, EMAIL MEGV�LTOZTAT�S
			System.out.println("-------------------[ VEV�K  ]-------------------");
			Node vevok = doc.getElementsByTagName("vevok").item(0); //Kiv�lasztjuk a vev�k csom�pontot
			NodeList vlist = vevok.getChildNodes();
			
			for (int i = 0; i < vlist.getLength(); i++) {
	            Node node = vlist.item(i);
	            if (node.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) node;
	               System.out.println("N�v : " + eElement.getElementsByTagName("nev").item(0).getTextContent());
	               if(eElement.getElementsByTagName("nev").item(0).getTextContent().equals("XMLTeszt Elek")) {
	            	   eElement.getElementsByTagName("nev").item(0).setTextContent("Hiteles Henrik");
	            	   System.out.println("-->�j n�v : " + eElement.getElementsByTagName("nev").item(0).getTextContent());
	               }
	               if(eElement.getElementsByTagName("email").item(0).getTextContent().equals("l.lajcsi@gmail.com")) {
	            	   eElement.getElementsByTagName("email").item(0).setTextContent("l.lajoska32523@gmail.com");
	            	   System.out.println("-->�j email : " + eElement.getElementsByTagName("email").item(0).getTextContent());
	               }
	            }
	        }
			System.out.println("-------------------[ VEV�K  ]-------------------");
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
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(new File("XMLCIEYIE.updated.xml"));
	        transformer.transform(source, result);		
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