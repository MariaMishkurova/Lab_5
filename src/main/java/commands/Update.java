package commands;
import Main_part.Route;
import Main_part.Style;

import java.io.BufferedReader;
import java.io.IOException;

public class Update {
    public static void update(BufferedReader br, Long id, boolean console) throws IOException {

        if(!Route.used_id.contains(id)){
            System.out.println(Style.RED + "Элемент не найден" + Style.BLACK);
        } else {
            for (Route r : Route.routes) {
                if (r.getId() == id) {
                    var add = new Add();
                    add.updateRoute(r, br, console);
                }
            }
        }
    }
}
