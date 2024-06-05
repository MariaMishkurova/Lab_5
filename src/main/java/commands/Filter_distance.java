package commands;

import org.example.Route;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;

public class Filter_distance {
    public static void filter_distance(BufferedReader br) throws IOException {
        double distance;
        boolean b = false;
        String input = br.readLine();
        try {
            distance = Double.parseDouble(input);
            for(Route r : Route.routes){
                if(r.getDistance() < distance){
                    System.out.println(r);
                    b = true;
                }
            }
            if (!b){
                System.out.println("\u001B[31mТаких элементов нет\u001B[0m");
            }

        } catch(NumberFormatException e){
            System.out.println("\u001B[31mПожалуйста, введите корректное значение\u001B[0m");
            filter_distance(br);
        }
    }
}
