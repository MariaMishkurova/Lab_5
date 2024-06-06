package commands;
import Main_part.Route;
import Main_part.Style;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;

public class Remove {

    public static void remove_by_id(Long id) {
        if (Route.routes.isEmpty()) {
            System.out.println(Style.RED + "Действие невозможно" + Style.BLACK);
        } else {
            if (!Route.used_id.contains(id)) {
                System.out.println(Style.RED + "Элемент не найден" + Style.BLACK);
            } else {
                int i = 0;
                for (Route r : Route.routes) {
                    if (Route.routes.size() - i < 1) {
                        System.out.println(Style.RED + "Элемент не найден" + Style.BLACK);
                        break;
                    }
                    if (r.getId() == id) {
                        Route.routes.remove(r);
                        System.out.println(Style.GREEN + "Элемент коллекции удалён" + Style.BLACK);
                        break;
                    }
                    i++;
                }
            }
        }
    }

    public static void remove_head() {
        if (Route.routes.isEmpty()) {
            System.out.println(Style.RED + "Действие невозможно" + Style.BLACK);
        } else {
            System.out.println("Первый элемент коллекции:\n" + Route.routes.getFirst());
            Route.routes.removeFirst();
            System.out.println(Style.GREEN + "Первый элемент коллекции удалён" + Style.BLACK);
        }
    }

    public static void remove_lower(BufferedReader br, String name) throws IOException {
        if(!existName(name)) {
            System.out.println(Style.RED + "Введите корректный запрос" + Style.BLACK);
            SwitchCommands.switchCommands(br);
        } else if(Route.routes.getFirst().getName().equals(name)){
            System.out.println(Style.RED + "Действие невозможно" + Style.BLACK);
        } else {
        for (Route r : Route.routes) {
            if (r.getName().equals(name)) {
                break;
            } else {
                Route.routes.remove(r);
                System.out.println(Style.GREEN + "Элемент коллекции удалён" + Style.BLACK);
            }
        }
        }
    }

    public static void remove_greater(BufferedReader br, String name) throws IOException {
        if(!existName(name)){
            System.out.println(Style.RED + "Введите корректный запрос" + Style.BLACK);
            SwitchCommands.switchCommands(br);
        } else if(Route.routes.getLast().getName().equals(name)){
            System.out.println(Style.RED + "Действие невозможно" + Style.BLACK);
        }
        else {
            Iterator<Route> iterator = Route.routes.iterator();
            boolean a = false;
            while (iterator.hasNext()) {
               Route r = iterator.next();
                if(a) {
                    iterator.remove();
                    System.out.println(Style.GREEN + "Элемент коллекции удалён" + Style.BLACK);
                }
                if (r.getName().equals(name)) {
                  a = true;
                }
            }
        }
    }
    public static void remove_distance(Double distance) throws IOException {
            Iterator<Route> iterator = Route.routes.iterator();
            if (!existDistance(distance)) {
                System.out.println(Style.RED + "Действие невозможно" + Style.BLACK);
            } else {
                while (iterator.hasNext()) {
                    Route r = iterator.next();
                    if (r.getDistance() == distance) {
                        iterator.remove();
                        System.out.println(Style.GREEN + "Элемент коллекции удалён" + Style.BLACK);
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



