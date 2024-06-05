package commands;

import org.example.Route;
import java.util.ArrayList;
import java.util.Collections;


public class Min_to {
    public static void min_to(){
        if (!Route.routes.isEmpty()) {
            ArrayList<Double> toValues = new ArrayList<>();
            for (Route r : Route.routes) {
                toValues.add(r.toValue());
            }
            for (Route r : Route.routes) {
                if(r.toValue() == Collections.min(toValues)){
                    System.out.println(r);
                    break;
                }
            }
        } else {
            System.out.println("\u001B[31mДействие невозможно\u001B[0m");

        }
    }
}

