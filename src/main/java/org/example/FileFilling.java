package org.example;

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
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class FileFilling {
    public static ArrayList<String> id_in_xml = new ArrayList<>();
    public void write() throws IOException {


        final String fileName = "src/file/Routes.xml";
        File file = new File(fileName);
        RandomAccessFile access = new RandomAccessFile("src/file/Routes.xml", "rwd");

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
                    try{
                    parser.parse(file, handler);
                    } catch (org.xml.sax.SAXParseException e){}

                    for (ElementXML e : ElementXML.elementsXML) {
                        if(!id_in_xml.contains(e.id)) {
                            Element routeElement = document.createElement("Route");
                            routeElement.appendChild(getRouteElements(document, "id", e.id));
                            routeElement.appendChild(getRouteElements(document, "name", e.name));
                            routeElement.appendChild(getRouteElements(document, "creationDate", e.creationDate));
                            routeElement.appendChild(getRouteElements(document, "coordinates", e.coordinates));
                            routeElement.appendChild(getRouteElements(document, "from", e.from));
                            routeElement.appendChild(getRouteElements(document, "to", e.to));
                            routeElement.appendChild(getRouteElements(document, "distance", e.distance));
                            headElement.appendChild(routeElement);
                            id_in_xml.add(e.id);
                        }
                    }


            for (Route r : Route.routes) {
                if(!id_in_xml.contains((String.valueOf(r.getId())))) {
                    Element routeElement = document.createElement("Route");
                    routeElement.appendChild(getRouteElements(document, "id", String.valueOf(r.getId())));
                    routeElement.appendChild(getRouteElements(document, "name", r.getName()));
                    routeElement.appendChild(getRouteElements(document, "creationDate", r.getCreationDateString()));
                    routeElement.appendChild(getRouteElements(document, "coordinates", r.getCoordinates()));
                    routeElement.appendChild(getRouteElements(document, "from", r.getFrom()));
                    routeElement.appendChild(getRouteElements(document, "to", r.getTo()));
                    routeElement.appendChild(getRouteElements(document, "distance", Double.toString(r.getDistance())));
                    headElement.appendChild(routeElement);
                    id_in_xml.add(String.valueOf(r.getId()));
                }
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
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
    String id, creationDate, name, coordinates, from, to, distance,  lastElementName;
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
            ElementXML.elementsXML.add(new ElementXML(id, creationDate, name, coordinates, from, to, distance));
            id = null;
            creationDate = null;
            name = null;
            coordinates = null;
            from = null;
            to = null;
            distance = null;
        }
    }

}

class ElementXML {
    public static ArrayList<ElementXML> elementsXML = new ArrayList<>();
    String id, creationDate, name, coordinates, from, to, distance;
    public ElementXML(String id, String creationDate, String name, String coordinates, String from, String to, String distance){
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.coordinates = coordinates;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

}
