package commands;
import org.example.Route;
public class Show {
    public static void show(){
        if(Route.routes.isEmpty()){
            System.out.println("\u001B[31mКоллекция пуста\u001B[0m");
        }
        for (Route r: Route.routes){
            System.out.println(r);
        }
    }
}
