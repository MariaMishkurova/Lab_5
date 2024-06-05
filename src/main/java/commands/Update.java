package commands;
import org.example.FileFilling;
import org.example.Route;
import org.example.Add;

import java.io.BufferedReader;
import java.io.IOException;

public class Update {
    static String input;
    static long id;
    public static void update(BufferedReader br) throws IOException {
        input = br.readLine();
        try{
            id = Long.parseLong(input);
        } catch (NumberFormatException e){
            System.out.println("\u001B[31mВведите корректное значение\u001B[0m");
            update(br);
        }
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
