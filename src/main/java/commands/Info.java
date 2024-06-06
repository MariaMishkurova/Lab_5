package commands;
import Main_part.Route;

public class Info {
    public static void info(){
        System.out.println("Тип коллекции: " + Route.routes.getClass());
        System.out.println("Размер коллекции: " + Route.routes.size());
        System.out.println("Время создания коллекции: " + Route.arrayDequeCreation);
    }
}
