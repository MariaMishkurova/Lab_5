package Files;

import Main_part.Route;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class XMLHandler extends DefaultHandler {
    public static int count=0;
    String name, coordinates, from, to, distance, lastElementName, findText;
    ArrayList<Float> findNum = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        lastElementName = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String information = new String(ch, start, length);

        information = information.replace("\n", "").trim();

        if (!information.isEmpty()) {
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
        if ((name != null && !name.isEmpty()) && (coordinates != null && !coordinates.isEmpty()) &&
                (from != null && !from.isEmpty()) && (to != null && !to.isEmpty()) && (distance != null && !distance.isEmpty())) {

            findNumers(coordinates);
            double coordinatesX = (double) findNum.get(0);
            double coordinatesY = (double) findNum.get(1);
            findNum.clear();

            findNumers(from);
            float fromX = findNum.get(0);
            long fromY = Math.round(findNum.get(1));
            int fromZ = Math.round(findNum.get(2));
            String fromName;
            try {
                fromName = String.valueOf(findNum.get(3));
            } catch (IndexOutOfBoundsException e) {
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
            } catch (IndexOutOfBoundsException e) {
                toName = findText;
            }
            findNum.clear();

            Double rDistance = Double.parseDouble(distance);
            boolean alreadyExist = false;
            for (Route r : Route.routes) {
                if (name.equals(r.getName()) && coordinates.equals(r.getCoordinates()) && from.equals(r.getFrom()) &&
                        to.equals(r.getTo()) && rDistance == r.getDistance()) {
                    alreadyExist = true;
                    break;
                }
            }
            if (!alreadyExist) {
                new Route(name, coordinatesX, coordinatesY, fromX, fromY, fromZ, fromName,
                        toX, toY, toZ, toName, rDistance);
                count++;
            }

            name = null;
            coordinates = null;
            from = null;
            to = null;
            distance = null;
        }
    }

    private void findNumers(String str) {
        String parts[] = str.split(" ");
        for (int i = 0; i < parts.length; i++) {
            try {
                findNum.add(Float.parseFloat(parts[i]));
            } catch (NumberFormatException e) {
                findText = parts[i];
            }
        }
    }

}
