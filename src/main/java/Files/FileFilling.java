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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
                    routeElement.appendChild(getRouteElements(document, "id", String.valueOf(r.getId())));
                    routeElement.appendChild(getRouteElements(document, "name", r.getName()));
                SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    routeElement.appendChild(getRouteElements(document, "creationDate", formater.format(r.getCreationDate())));
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

class XMLHandler extends DefaultHandler {
    String id, creationDate, name, coordinates, from, to, distance,  lastElementName, findText;
    ArrayList <Float> findNum = new ArrayList<>();
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        lastElementName = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String information = new String(ch, start, length);

        information = information.replace("\n", "").trim();

        if (!information.isEmpty()) {
            if (lastElementName.equals("id")) {
                id = information;
            }
            if (lastElementName.equals("creationDate")) {
                creationDate = information;
            }
            if (lastElementName.equals("name")) {
                name = information;
            }
            if (lastElementName.equals("coordinates")) {
                coordinates = information;
            }
            if (lastElementName.equals("from")) {
                from = information;
            }
            if (lastElementName.equals("to")) {
                to = information;
            }
            if (lastElementName.equals("distance")) {
                distance = information;
            }
        }
    }
    @Override
    public void endElement(String uri, String localName, String qName) {
        if ( (id != null && !id.isEmpty()) && (creationDate != null && !creationDate.isEmpty()) && (name != null && !name.isEmpty()) &&
                (coordinates != null && !coordinates.isEmpty()) && (from != null && !from.isEmpty()) && (to != null && !to.isEmpty()) &&
                (distance != null && !distance.isEmpty()))
        {
            Long rId = Long.parseLong(id);
            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            findNumers(coordinates);
            double coordinatesX = (double) findNum.get(0);
            double coordinatesY = (double) findNum.get(1);
            findNum.clear();

            findNumers(from);
            float fromX = findNum.get(0);
            long fromY =  Math.round(findNum.get(1));
            int fromZ =  Math.round(findNum.get(2));
            String fromName;
            try {
                fromName = String.valueOf(findNum.get(3));
            } catch (IndexOutOfBoundsException e){
                fromName = findText;
            }

            findNum.clear();

            findNumers(to);
            double toX = (double) findNum.get(0);
            double toY = (double) findNum.get(1);
            double toZ = (double) findNum.get(2);
            String toName;
            try {
                toName = String.valueOf(findNum.get(3));
            } catch (IndexOutOfBoundsException e){
                toName = findText;
            }
            findNum.clear();

            Double rDistance = Double.parseDouble(distance);

            try {
                Date rCreationDate = formater.parse(creationDate);
                new Route(rId, rCreationDate, name, coordinatesX, coordinatesY, fromX, fromY, fromZ, fromName,
                        toX, toY, toZ, toName, rDistance);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            id = null;
            creationDate = null;
            name = null;
            coordinates = null;
            from = null;
            to = null;
            distance = null;
        }
    }
    private void findNumers(String str){
        String parts[] = str.split(" ");
        for (int i=0; i<parts.length; i++) {
            try {
                findNum.add(Float.parseFloat(parts[i]));
            } catch(java.lang.NumberFormatException e){
                findText = parts[i];
            }
        }
    }

}
