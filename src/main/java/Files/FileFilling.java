package Files;

import Main_part.Route;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import static Main_part.Main.getHome;

public class FileFilling {
    public static ArrayList<Long> id_in_xml = new ArrayList<>();
    String fileName;

    public void write() throws IOException {
//getHome() - метод для нахождения переменной окружения home
        fileName = getHome() + "/Route.xml";
        File file = new File(fileName);
        new RandomAccessFile(fileName, "rwd");

        /* Если нужны временные файл и директория:
        fileName = getHome();
        file = File.createTempFile("Route", ".xml", directory);
        file.deleteOnExit();
        directory.deleteOnExit();
        */


        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element headElement;

            SAXParserFactory factory2 = SAXParserFactory.newInstance();
            SAXParser parser = factory2.newSAXParser();
            XMLHandler handler = new XMLHandler();
            headElement = document.createElement("Routes");
            document.appendChild(headElement);

            //не получается парсить - значит нечего копировать, не делаем это
            try {
                parser.parse(file, handler);
            } catch (org.xml.sax.SAXParseException | IllegalArgumentException e) {
            }

            for (Route r : Route.routes) {
                    Element routeElement = document.createElement("Route");
                    //routeElement.appendChild(getRouteElements(document, "id", String.valueOf(r.getId())));
                    routeElement.appendChild(getRouteElements(document, "name", r.getName()));
               // SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    //routeElement.appendChild(getRouteElements(document, "creationDate", formater.format(r.getCreationDate())));
                    routeElement.appendChild(getRouteElements(document, "coordinates", r.getCoordinates()));
                    routeElement.appendChild(getRouteElements(document, "from", r.getFrom()));
                    routeElement.appendChild(getRouteElements(document, "to", r.getTo()));
                    routeElement.appendChild(getRouteElements(document, "distance", Double.toString(r.getDistance())));
                    headElement.appendChild(routeElement);

                }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
            id_in_xml.clear();
        } catch (SAXException | TransformerException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
    private static Node getRouteElements(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

}

