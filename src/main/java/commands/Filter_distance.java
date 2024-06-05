package commands;

import org.example.Route;
import java.io.BufferedReader;
import java.io.IOException;

public class Filter_distance {
    public static void filter_distance(Double distance){
        boolean b = false;

            for(Route r : Route.routes){
                if(r.getDistance() < distance){
                    System.out.println(r);
                    b = true;
                }
            }
            if (!b){
                System.out.println("\u001B[31mТаких элементов нет\u001B[0m");
            }

    }
}
