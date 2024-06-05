package commands;

import java.io.BufferedReader;
import java.io.IOException;
import org.example.Add;
import org.example.FileFilling;
import org.example.Route;

public class SwitchCommands {
    public static void switchCommands(String s, BufferedReader br) throws IOException {
        switch (s) {
            case "help" -> {
                Help.help();
                s = br.readLine();
                switchCommands(s, br);
            }
            case "info" -> {
                Info.info();
                s = br.readLine();
                switchCommands(s, br);
            }
            case "show" -> {
                Show.show();
                s = br.readLine();
                switchCommands(s, br);
            }
            case "add" -> {
                var add = new Add();
                add.addNew(br);
                s = br.readLine();
                switchCommands(s, br);
            }
            case "update" -> {
                System.out.println("Введите id");
                Update.update(br);
                s = br.readLine();
                switchCommands(s, br);
            }
            case "remove_id" -> {
                Remove.remove_by_id(br);
                s = br.readLine();
                switchCommands(s, br);
            }
            case "clear" -> {
                Clear.clear();
                var fileFill = new FileFilling();
                fileFill.write();
                s = br.readLine();
                switchCommands(s, br);
            }
            case "save" -> {
                if(!Route.routes.isEmpty()) {
                    var fileFill = new FileFilling();
                    fileFill.write();
                    System.out.println("\u001B[32mЭлементы сохранены\u001B[0m");
                } else {
                    System.out.println("\u001B[31mСохранять нечего\u001B[0m");
                }
                s = br.readLine();
                switchCommands(s, br);
            }
            case "exit" -> System.exit(0);

            case "remove_head" -> {
                Remove.remove_head();
                s = br.readLine();
                switchCommands(s, br);
            }
            case "remove_lower" -> {
                Remove.remove_lower(br);
                s = br.readLine();
                switchCommands(s, br);
            }
            case "remove_greater" -> {
                Remove.remove_greater(br);
                s = br.readLine();
                switchCommands(s, br);
            }
            case "remove_distance" -> {
                Remove.remove_distance(br);
                s = br.readLine();
                switchCommands(s, br);
            }
            case "min_to" -> {
                Min_to.min_to();
                s = br.readLine();
                switchCommands(s, br);
            }
            case "filter_distance" -> {
                System.out.println("Введите значение дистанции");
                Filter_distance.filter_distance(br);
                s = br.readLine();
                switchCommands(s, br);
            }

            default -> {
                System.out.println("\u001B[31mПожалуйста, введите корректную команду\u001B[0m");
                Help.help();
                s = br.readLine();
                switchCommands(s, br);
            }
        }
    }
}
