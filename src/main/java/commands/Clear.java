package commands;
import Main_part.Route;

public class Clear {
    public static void clear(){
        Route.routes.clear();
        System.out.println("Все элементы коллекции удалены");
    }
}
