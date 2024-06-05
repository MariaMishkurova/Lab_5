package commands;
import org.example.Route;

public class Clear {
    public static void clear(){
        Route.routes.clear();
        System.out.println("Все элементы коллекции удалены");
    }
}
