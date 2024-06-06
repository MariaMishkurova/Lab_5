package commands;

import Main_part.*;

public class Filter_distance {
    public static void filter_distance(Double distance){
        //вспомогательная переменная, чтобы определить результат выполнения цикла
        boolean b = false;

            for(Route r : Route.routes){
                if(r.getDistance() < distance){
                    System.out.println(r);
                    b = true;
                }
            }
            if (!b){
                System.out.println(Style.RED + "Таких элементов нет" + Style.BLACK);
            }

    }
}
