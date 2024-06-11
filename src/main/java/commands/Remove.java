package commands;
import Main_part.Route;
import Main_part.Style;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;

public class Remove {

    public static void remove_by_id(Long id) {
        if (Route.routes.size() < id) {
            System.out.println(Style.RED + "Действие невозможно(элемента с таким id не существует)" + Style.BLACK);
        } else {
            Iterator<Route> iterator = Route.routes.iterator();
                while (iterator.hasNext()) {
                    Route r = iterator.next();
                    if (r.getId() == id) {
                        iterator.remove();
                        System.out.println(Style.GREEN + "Элемент коллекции удалён" + Style.BLACK);
                        break;
                    }
                }
                rebuildArrayDeque(id);
        }
    }

    public static void remove_head() {
        if (Route.routes.isEmpty()) {
            System.out.println(Style.RED + "Действие невозможно" + Style.BLACK);
        } else {
            System.out.println("Первый элемент коллекции:\n" + Route.routes.getFirst());
            Route.routes.removeFirst();
            System.out.println(Style.GREEN + "Первый элемент коллекции удалён" + Style.BLACK);
            rebuildArrayDeque(1);
        }
    }

    public static void remove_lower(BufferedReader br, double distance) throws IOException {
        Iterator<Route> iterator = Route.routes.iterator();
        while (iterator.hasNext()) {
            Route r = iterator.next();
            if(r.getDistance() < distance) {
                iterator.remove();
                System.out.println(Style.GREEN + "Элемент коллекции удалён" + Style.BLACK);
                rebuildArrayDeque(r.getId());
            }
        }
    }

    public static void remove_greater(BufferedReader br, double distance) throws IOException {
        Iterator<Route> iterator = Route.routes.iterator();
        while (iterator.hasNext()) {
            Route r = iterator.next();
            if(r.getDistance() > distance) {
                iterator.remove();
                System.out.println(Style.GREEN + "Элемент коллекции удалён" + Style.BLACK);
                rebuildArrayDeque(r.getId());
            }
        }
    }
    public static void remove_distance(Double distance) {
            Iterator<Route> iterator = Route.routes.iterator();
            if (!existDistance(distance)) {
                System.out.println(Style.RED + "Действие невозможно" + Style.BLACK);
            } else {
                while (iterator.hasNext()) {
                    Route r = iterator.next();
                    if (r.getDistance() == distance) {
                        iterator.remove();
                        System.out.println(Style.GREEN + "Элемент коллекции удалён" + Style.BLACK);
                        rebuildArrayDeque(r.getId());
                    }
                }
            }
        }
        private static void rebuildArrayDeque(long id){
        if(!Route.routes.isEmpty()){
            Iterator<Route> iterator = Route.routes.iterator();
            while (iterator.hasNext()) {
                Route r = iterator.next();
                if (r.getId() > id) {
                    long previousId = r.getId();
                    r.setId(previousId-1);
                }
            }
        }
        }
    private static boolean existName(String name){
        for (Route r : Route.routes) {
            if (r.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    private static boolean existDistance(double distance){
        for(Route r: Route.routes){
            if (r.getDistance() == distance) {
                return true;
            }
        }
        return false;
    }
}



