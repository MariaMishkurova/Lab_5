package commands;
import org.example.Route;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;

public class Remove {
    private static String input;
    private static long id;

    public static void remove_by_id(BufferedReader br) throws IOException {
        if (Route.routes.isEmpty()) {
            System.out.println("\u001B[31mДействие невозможно\u001B[0m");
        } else {
            System.out.println("Введите id");
            input = br.readLine();
            try {
                id = Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mВведите корректное значение\u001B[0m");
                remove_by_id(br);
            }
            if (!Route.used_id.contains(id)) {
                System.out.println("\u001B[31mЭлемент не найден\u001B[0m");
            } else {
                int i = 0;
                for (Route r : Route.routes) {
                    if (Route.routes.size() - i < 1) {
                        System.out.println("\u001B[31mЭлемент не найден\u001B[0m");
                        break;
                    }
                    if (r.getId() == id) {
                        Route.routes.remove(r);
                        System.out.println("\u001B[32mЭлемент коллекции удалён\u001B[0m");
                        break;
                    }
                    i++;
                }
            }
        }
    }

    public static void remove_head() {
        if (Route.routes.isEmpty()) {
            System.out.println("\u001B[31mДействие невозможно\u001B[0m");
        } else {
            System.out.println("Первый элемент коллекции:\n" + Route.routes.getFirst());
            Route.routes.removeFirst();
            System.out.println("\u001B[32mПервый элемент коллекции удалён\u001B[0m");
        }
    }

    public static void remove_lower(BufferedReader br) throws IOException {
        System.out.println("Введите название элемента, до которого желаете всё удалить");
        String name = br.readLine();
        if(!existName(name)) {
            System.out.println("\u001B[31mВведите корректное имя\u001B[0m");
            remove_lower(br);
        } else if(Route.routes.getFirst().getName().equals(name)){
            System.out.println("\u001B[31mДействие невозможно\u001B[0m");
        } else {
        for (Route r : Route.routes) {
            if (r.getName().equals(name)) {
                break;
            } else {
                Route.routes.remove(r);
                System.out.println("\u001B[32mЭлемент коллекции удалён\u001B[0m");
            }
        }
        }
    }

    public static void remove_greater(BufferedReader br) throws IOException {
        System.out.println("Введите название элемента, после которого желаете всё удалить");
        String name = br.readLine();
        if(!existName(name)){
            System.out.println("\u001B[31mВведите корректное имя\u001B[0m");
            remove_greater(br);
        } else if(Route.routes.getLast().getName().equals(name)){
            System.out.println("\u001B[31mДействие невозможно\u001B[0m");
        }
        else {
            Iterator<Route> iterator = Route.routes.iterator();
            boolean a = false;
            while (iterator.hasNext()) {
               Route r = iterator.next();
                if(a) {
                    iterator.remove();
                    System.out.println("\u001B[32mЭлемент коллекции удалён\u001B[0m");
                }
                if (r.getName().equals(name)) {
                  a = true;
                }
            }
        }
    }
    public static void remove_distance(BufferedReader br) throws IOException {
        System.out.println("Введите значение дистанции, маршрруты с которым желаете удалить");
        double distance;
        String input = br.readLine();
        try {
            distance = Double.parseDouble(input);
            Iterator<Route> iterator = Route.routes.iterator();
            if (!existDistance(distance)) {
                System.out.println("\u001B[31mДействие невозможно\u001B[0m");
            } else {
                while (iterator.hasNext()) {
                    Route r = iterator.next();
                    if (r.getDistance() == distance) {
                        iterator.remove();
                        System.out.println("\u001B[32mЭлемент коллекции удалён\u001B[0m");
                    }
                }
            }
        } catch(NumberFormatException e){
                System.out.println("\u001B[31mПожалуйста, введите корректное значение\u001B[0m");
                remove_distance(br);
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



