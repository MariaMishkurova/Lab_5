package commands;
import Main_part.*;

public class Show {
    public static void show(){
        if(Route.routes.isEmpty()){
            System.out.println(Style.RED + "Коллекция пуста" + Style.BLACK);
        }
        for (Route r: Route.routes){
            System.out.println(r);
        }
    }
}
