package org.example;

import Exceptions.ImpossibleFieldException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class Add {
    String name, fromName, toName;
    double coordinatesX, coordinatesY, distance, toX, toY, toZ;
    float fromX;
    long fromY;
    int fromZ;

    private void add(BufferedReader br) throws IOException {

        System.out.print("Введите название: ");
        String input = br.readLine();
        if (!input.equals("")) {
            name = input;
        } else {
            while (input.equals("")) {
                try {
                    throw new ImpossibleFieldException();
                } catch (ImpossibleFieldException e) {
                    System.out.println("\u001B[31mПожалуйста, введите корректное значение\u001B[0m");
                    System.out.print("Введите название ещё раз: ");
                    input = br.readLine();
                }
            }
            name = input;
        }


        System.out.print("Введите координату x:");
        coordinatesX = floatCount(br);

        System.out.print("Введите координату y: ");
        coordinatesY = floatCount(br);

        System.out.print("Введите координаты начала маршрута x: ");
        fromX =  floatCount(br);

        System.out.print("y(в виде целого числа): ");
        fromY = intCount(br);

        System.out.print("z(в виде целого числа): ");
        fromZ = intCountNotNull(br);

        System.out.print("Введите название начала маршрута: ");
        input = br.readLine();
        if (!input.equals("")) {
            fromName = input;
        } else {
            while (input.equals("")) {
                try {
                    throw new ImpossibleFieldException();
                } catch (ImpossibleFieldException e) {
                    System.out.println("\u001B[31mПожалуйста, введите корректное значение\u001B[0m");
                    System.out.print("Введите название начала маршрута ещё раз: ");
                    input = br.readLine();
                }
            }
            fromName = input;
        }

        System.out.print("Введите координаты конца маршрута x: ");
        toX = floatCount(br);

        System.out.print("y: ");
        toY = floatCountNotNull(br);

        System.out.print("z: ");
        toZ = floatCount(br);

        System.out.print("Введите название конца маршрута: ");
        input = br.readLine();
        toName = input;

        System.out.print("Введите длину дистанции: ");
        distance = distanceCount(br);

    }
    public void updateRoute(Route r, BufferedReader br) throws IOException {
        add(br);
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
        System.out.println("\u001B[32mЭлемент обновлён\u001B[0m");
    }


    public void addNew(BufferedReader br) throws IOException {
        add(br);
        var route = new Route(name, coordinatesX, coordinatesY, fromX, fromY, fromZ, fromName, toX, toY, toZ, toName, distance);
        System.out.println("\u001B[32mЭлемент создан\u001B[0m");
    }



        static Float floatCountNotNull (BufferedReader br) throws IOException {
                String input = br.readLine();
                float a;
                try {
                    if (!input.isEmpty()) {
                        a = Float.parseFloat(input);
                    } else {
                        do {
                            try {
                                throw new ImpossibleFieldException();
                            } catch (ImpossibleFieldException e) {
                                System.out.println("\u001B[31mПожалуйста, введите корректное значение\u001B[0m");
                                input = br.readLine();
                            }
                        } while (input.isEmpty());
                        a = Float.parseFloat(input);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\u001B[31mПожалуйста, введите корректное значение\u001B[0m");
                    a = floatCountNotNull(br);
                }
                return a;
            }

        static int intCountNotNull (BufferedReader br) throws IOException {
            String input = br.readLine();
            int a;
            try {
                if (!input.isEmpty()) {
                    a = Integer.parseInt(input);
                } else {
                    do {
                        try {
                            throw new ImpossibleFieldException();
                        } catch (ImpossibleFieldException e) {
                            System.out.println("\u001B[31mПожалуйста, введите корректное значение\u001B[0m");
                            input = br.readLine();
                        }
                    } while (input.isEmpty());
                    a = Integer.parseInt(input);
                }
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mПожалуйста, введите корректное значение\u001B[0m");
                a = intCountNotNull(br);
            }
            return a;
        }

        private int intCount (BufferedReader br) throws IOException {
            String input = br.readLine();
            int a;
            try{
                a = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mПожалуйста, введите корректное значение\u001B[0m");
                a = intCount(br);
            }
            return a;
        }
        private static Float floatCount (BufferedReader br) throws IOException {
            String input = br.readLine();
            float a;
            try {
                a = Float.parseFloat(input);
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mПожалуйста, введите корректное значение\u001B[0m");
                a = floatCount(br);
            }
            return a;
        }
        private static Double distanceCount (BufferedReader br) throws IOException {
            String input = br.readLine();
            double a;
            try {
                if (!input.isEmpty() && Double.parseDouble(input) > 1) {
                    a = Double.parseDouble(input);
                } else {
                    do {
                        try {
                            throw new ImpossibleFieldException();
                        } catch (ImpossibleFieldException e) {
                            System.out.println("\u001B[31mПожалуйста, введите корректное значение\u001B[0m");
                            System.out.print("Введите длину дистанции ещё раз: ");
                            input = br.readLine();
                        }
                    } while (input.isEmpty() | (!input.isEmpty() && Double.parseDouble(input) <= 1));
                    a = Double.parseDouble(input);
                }
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mПожалуйста, введите корректное значение\u001B[0m");
                a = distanceCount(br);
            }
            return a;
        }


}