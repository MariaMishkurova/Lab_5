package commands;
import Main_part.*;
import Exceptions.ImpossibleFieldException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Add {
    String name, fromName, toName;
    double coordinatesX, coordinatesY, distance, toX, toY, toZ;
    float fromX;
    long fromY;
    int fromZ;

    //boolean console - true - спецификация метода для вывода в консоль (например, подсказок и повторного ввода ошибочных аргументов)
    // false - для чтения скрипта - используются во всём классе по одному принципу
    // BufferedReader br также - либо чтение строки из консоли, либо из файла, передается в параметр уже нужный
    // в самом классе новый BufferedReader не предлагается - кроме ситуации, когда прерывается чтение скрипта из-за ошибки
    // и продолжается программа, предлагая новый ввод в консоль
    private void add(BufferedReader br, boolean console) throws IOException {

        if(console){System.out.print("Введите название: ");}
        String input = br.readLine();
        if (!input.isEmpty()) {
            name = input;
        } else {
            if(!console){
                System.out.println(Style.RED + "В файле указаны недопустимые аргументы" + Style.BLACK);
                return;
            }
            while (input.isEmpty()) {
                try {
                    throw new ImpossibleFieldException();
                } catch (ImpossibleFieldException e) {
                    System.out.println(Style.RED + "Пожалуйста, введите корректное значение" + Style.BLACK);
                    System.out.print("Введите название ещё раз: ");
                    input = br.readLine();
                }
            }
            name = input;
        }


        if(console) {
            System.out.print("Введите координату x:");
        }
        coordinatesX = floatCount(br, console);

        if(console) {
            System.out.print("Введите координату y: ");
        }
        coordinatesY = floatCount(br, console);

        if(console) {
            System.out.print("Введите координаты начала маршрута x: ");
        }
        fromX =  floatCount(br, console);

        if(console) {
            System.out.print("y(в виде целого числа): ");
        }
        fromY = intCount(br, console);

        if(console) {
            System.out.print("z(в виде целого числа): ");
        }
        fromZ = intCountNotNull(br, console);

        if(console) {
            System.out.print("Введите название начала маршрута: ");
        }
        input = br.readLine();
        if (!input.isEmpty()) {
            fromName = input;
        } else {
            if(!console){
                System.out.println(Style.RED + "В файле указаны недопустимые аргументы" + Style.BLACK);
                return;
            }
            while (input.isEmpty()) {
                try {
                    throw new ImpossibleFieldException();
                } catch (ImpossibleFieldException e) {
                    System.out.println(Style.RED + "Пожалуйста, введите корректное значение" + Style.BLACK);
                    System.out.print("Введите название начала маршрута ещё раз: ");
                    input = br.readLine();
                }
            }
            fromName = input;
        }

        if(console) {
            System.out.print("Введите координаты конца маршрута x: ");
        }
        toX = floatCount(br, console);

        if(console) {
            System.out.print("y: ");
        }
        toY = floatCountNotNull(br, console);

        if(console) {
            System.out.print("z: ");
        }
        toZ = floatCount(br, console);

        if(console) {
            System.out.print("Введите название конца маршрута: ");
        }
        input = br.readLine();
        toName = input;

        if(console) {
            System.out.print("Введите длину дистанции: ");
        }
        distance = distanceCount(br, console);

    }
    public void updateRoute(Route r, BufferedReader br, boolean console) throws IOException {
        add(br, console);
        r.setName(name);
        r.setCoordinatesX(coordinatesX);
        r.setCoordinatesY(coordinatesY);
        r.setFromX(fromX);
        r.setFromY(fromY);
        r.setFromZ(fromZ);
        r.setFromName(fromName);
        r.setToX(toX);
        r.setToY(toY);
        r.setToZ(toZ);
        r.setToName(toName);
        r.setDistance(distance);
        System.out.println(Style.GREEN + "Элемент обновлён" + Style.BLACK);
    }


    public void addNew(BufferedReader br, boolean console) throws IOException {
        add(br, console);
        new Route(name, coordinatesX, coordinatesY, fromX, fromY, fromZ, fromName, toX, toY, toZ, toName, distance);
        System.out.println(Style.GREEN + "Элемент создан" + Style.BLACK);
    }



        static Float floatCountNotNull (BufferedReader br, boolean console) throws IOException {
                String input = br.readLine();
                float a;
                try {
                    if (!input.isEmpty()) {
                        a = Float.parseFloat(input);
                    } else
                    {
                        if(console){
                            do {
                                try {
                                    throw new ImpossibleFieldException();
                                } catch (ImpossibleFieldException e) {
                                    System.out.println(Style.RED + "Пожалуйста, введите корректное значение" + Style.BLACK);
                                    input = br.readLine();
                                }
                            } while (input.isEmpty());
                            a = Float.parseFloat(input);
                        } else {
                            System.out.println(Style.RED + "В файле указаны недопустимые аргументы" + Style.BLACK);
                            var brConsole = new BufferedReader(new InputStreamReader(System.in));
                            SwitchCommands.switchCommands(brConsole);
                            return null;
                        }
                    }
                } catch (NumberFormatException e) {
                    if(console){
                        System.out.println(Style.RED + "Пожалуйста, введите корректное значение" + Style.BLACK);
                        a = floatCountNotNull(br, console);
                    } else {
                        System.out.println(Style.RED + "В файле указаны недопустимые аргументы" + Style.BLACK);
                        var brConsole = new BufferedReader(new InputStreamReader(System.in));
                        SwitchCommands.switchCommands(brConsole);
                        return null;
                    }
                }
                return a;
            }

        static int intCountNotNull (BufferedReader br, boolean console) throws IOException {
            String input = br.readLine();
            int a;
            try {
                if (!input.isEmpty()) {
                    a = Integer.parseInt(input);
                } else {
                    if (console){
                        do {
                            try {
                                throw new ImpossibleFieldException();
                            } catch (ImpossibleFieldException e) {
                                System.out.println(Style.RED + "Пожалуйста, введите корректное значение" + Style.BLACK);
                                input = br.readLine();
                            }
                        } while (input.isEmpty());
                        a = Integer.parseInt(input);
                    } else {
                        System.out.println(Style.RED + "В файле указаны недопустимые аргументы" + Style.BLACK);
                        var brConsole = new BufferedReader(new InputStreamReader(System.in));
                        SwitchCommands.switchCommands(brConsole);
                        return 0;
                    }
                }
            } catch (NumberFormatException e) {
                if(console){
                System.out.println(Style.RED + "Пожалуйста, введите корректное значение" + Style.BLACK);
                a = intCountNotNull(br, console);} else {
                    System.out.println(Style.RED + "В файле указаны недопустимые аргументы" + Style.BLACK);
                    var brConsole = new BufferedReader(new InputStreamReader(System.in));
                    SwitchCommands.switchCommands(brConsole);
                    return 0;
                }
            }
            return a;
        }

        private int intCount (BufferedReader br, boolean console) throws IOException {
            String input = br.readLine();
            int a;
            try{
                a = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                if(console) {
                System.out.println(Style.RED + "Пожалуйста, введите корректное значение" + Style.BLACK);
                a = intCount(br, console);
                } else {
                    System.out.println(Style.RED + "В файле указаны недопустимые аргументы" + Style.BLACK);
                    var brConsole = new BufferedReader(new InputStreamReader(System.in));
                    SwitchCommands.switchCommands(brConsole);
                    return 0;
                }
            }
            return a;
        }
        private static Float floatCount (BufferedReader br, boolean console) throws IOException {
            String input = br.readLine();
            float a;
            try {
                a = Float.parseFloat(input);
            } catch (NumberFormatException e) {
                if(console) {System.out.println(Style.RED + "Пожалуйста, введите корректное значение" + Style.BLACK);
                a = floatCount(br, console);
                } else{
                    System.out.println(Style.RED + "В файле указаны недопустимые аргументы" + Style.BLACK);
                    var brConsole = new BufferedReader(new InputStreamReader(System.in));
                    SwitchCommands.switchCommands(brConsole);
                    return null;
                }
            }
            return a;
        }
        private static Double distanceCount (BufferedReader br, boolean console) throws IOException {
            String input = br.readLine();
            double a;
            try {
                if (!input.isEmpty() && Double.parseDouble(input) > 1) {
                    a = Double.parseDouble(input);
                } else {
                    if(!console){
                        System.out.println(Style.RED + "В файле указаны недопустимые аргументы" + Style.BLACK);
                        var brConsole = new BufferedReader(new InputStreamReader(System.in));
                        SwitchCommands.switchCommands(brConsole);
                        return null;
                    }
                    do {
                        try {
                            throw new ImpossibleFieldException();
                        } catch (ImpossibleFieldException e) {
                            System.out.println(Style.RED + "Пожалуйста, введите корректное значение" + Style.BLACK);
                            System.out.print("Введите длину дистанции ещё раз: ");
                            input = br.readLine();
                        }
                    } while (input.isEmpty() | (!input.isEmpty() && Double.parseDouble(input) <= 1));
                    a = Double.parseDouble(input);
                }
            } catch (NumberFormatException e) {
                if(!console){
                    System.out.println(Style.RED + "В файле указаны недопустимые аргументы" + Style.BLACK);
                    var brConsole = new BufferedReader(new InputStreamReader(System.in));
                    SwitchCommands.switchCommands(brConsole);
                    return null;
                }
                System.out.println(Style.RED + "Пожалуйста, введите корректное значение" + Style.BLACK);
                a = distanceCount(br, console);
            }
            return a;
        }


}