package commands;

import Main_part.Route;
import Main_part.Style;

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
            System.out.println(Style.RED + "Действие невозможно" + Style.BLACK);

        }
    }
}

