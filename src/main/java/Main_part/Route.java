package Main_part;

import java.util.*;
public class Route{

    public static ArrayDeque<Route> routes;
    public static Date arrayDequeCreation;

    static {
        routes = new ArrayDeque<>();
        arrayDequeCreation = new Date();
        creationDate = arrayDequeCreation;
    }

    /*private Route(String name, double coordinatesX, double coordinatesY, float fromX, long fromY, int fromZ, String fromName,
                 double toX, double toY, double toZ, String toName) {
        this.name = name;
        coordinates = new Coordinates(coordinatesX, coordinatesY);
        from = new LocationFrom(fromX, fromY, fromZ, fromName);
        this.toX = toX;
        this.toY = toY;
        this.toZ = toZ;
        to = new LocationTo(toX, toY, toZ, toName);
    }*/
    public Route(String name, double coordinatesX, double coordinatesY, float fromX, long fromY, int fromZ, String fromName,
                 double toX, double toY, double toZ, String toName, Double distance) {
        this.name = name;
        coordinates = new Coordinates(coordinatesX, coordinatesY);
        from = new LocationFrom(fromX, fromY, fromZ, fromName);
        to = new LocationTo(toX, toY, toZ, toName);
        this.distance = distance;
        this.id = calculateId();
        routes.add(this);
    }

    /*public Route(Long id, Date creationDate, String name, double coordinatesX, double coordinatesY, float fromX, long fromY, int fromZ, String fromName,
                 double toX, double toY, double toZ, String toName, Double distance){
        this(name, coordinatesX, coordinatesY, fromX, fromY, fromZ, fromName, toX, toY, toZ, toName);
        this.id = id;
        this.distance = distance;
        this.creationDate = creationDate;
        if(!used_id.contains(this.id)){
            routes.add(this);
            used_id.add(this.id);
        }
    }*/

    private Long id;
    private long fromY;
    private String name, fromName, toName;
    private Coordinates coordinates;
    private static final java.util.Date creationDate;
    private LocationFrom from;
    private LocationTo to;
    private double coordinatesX, coordinatesY, toX, toZ;
    private Double distance, toY;
    private float fromX;
    private Integer fromZ;
    @Override
    public String toString() {
        return Style.PURPLE + "Маршрут " + this.id + " \"" + this.name + "\"" + Style.BLACK + ": координаты: " + this.coordinates +
                ", дата создания: " + creationDate + ", старт маршрута: " + this.from + ", конец маршрута: " + this.to +
                ", расстояние: " + this.distance;
    }

    private class Coordinates {
        public Coordinates(double x, double y) {
            this.x = x;
            this.y = y;
        }

        private double x;
        private double y;

        public String toString() {
            return x + " " + y;
        }
    }

    private class LocationFrom {
        private LocationFrom(float x, long y, Integer z, String name) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.name = name;
        }

        private float x;
        private long y;
        private Integer z; //Поле не может быть null
        private String name; //Поле не может быть null

        public String toString() {
            return name + " " + x + " " + y + " " + z;
        }
    }

    private class LocationTo {
        private LocationTo(double x, Double y, double z, String name) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.name = name;
        }

        private double x;
        private Double y; //Поле не может быть null
        private double z;
        private String name; //Поле может быть null

        public String toString() {
            return name + " " + x + " " + y + " " + z;
        }
    }

    private long calculateId() {
        long a;
        a = routes.size() + 1; //размер аррэйдекью еще без этого элемента
        return a;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinatesX(double coordinatesX) {
        this.coordinatesX = coordinatesX;
        this.coordinates = new Coordinates(coordinatesX, this.coordinatesY);
    }

    public void setCoordinatesY(double coordinatesY) {
        this.coordinatesY = coordinatesY;
        this.coordinates = new Coordinates(this.coordinatesX, coordinatesY);
    }

    public void setFromX(float fromX) {
        this.fromX = fromX;
        this.from = new LocationFrom(fromX, this.fromY, this.fromZ, this.fromName);
    }

    public void setFromY(long fromY) {
        this.fromY = fromY;
        this.from = new LocationFrom(this.fromX, fromY, this.fromZ, this.fromName);
    }

    public void setFromZ(int fromZ) {
        this.fromZ = fromZ;
        this.from = new LocationFrom(this.fromX, this.fromY, fromZ, this.fromName);
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
        this.from = new LocationFrom(this.fromX, this.fromY, this.fromZ, fromName);
    }

    public void setToX(double toX) {
        this.toX = toX;
        this.to = new LocationTo(toX, this.toY, this.toZ, this.toName);
    }

    public void setToY(double toY) {
        this.toY = toY;
        this.to = new LocationTo(this.toX, toY, this.toZ, this.toName);
    }

    public void setToZ(double toZ) {
        this.toZ = toZ;
        this.to = new LocationTo(this.toX, this.toY, toZ, this.toName);
    }

    public void setToName(String toName) {
        this.toName = toName;
        this.to = new LocationTo(this.toX, this.toY, this.toZ, toName);
    }
    public void setId(long id){
        this.id = id;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getCoordinates() {
        return this.coordinates.toString();
    }

    public String getFrom() {
        return this.from.toString();
    }

    public String getTo() {
        return this.to.toString();
    }

    public double toValue() {
        return (this.toX + this.toY + this.toZ);
    }
    public Date getCreationDate(){
        return creationDate;
    }


}
