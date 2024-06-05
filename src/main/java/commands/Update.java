package commands;
import org.example.Route;
import org.example.Add;
import java.io.BufferedReader;
import java.io.IOException;

public class Update {
    static String input;
    static long id;
    public static void update(BufferedReader br, Long id) throws IOException {

        if(!Route.used_id.contains(id)){
            System.out.println("\u001B[31mЭлемент не найден\u001B[0m");
        } else {
            for (Route r : Route.routes) {
                if (r.getId() == id) {
                    var add = new Add();
                    add.updateRoute(r, br);
                }
            }
        }
    }
}
