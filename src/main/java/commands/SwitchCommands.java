package commands;

import java.io.BufferedReader;
import java.io.IOException;
import org.example.Add;
import org.example.FileFilling;
import org.example.Route;

public class SwitchCommands {
    public static void switchCommands(BufferedReader br) throws IOException {
        String s = br.readLine();
        String[] parts = s.split(" ");
        if (parts[0].equals("update") || parts[0].equals("remove_id") || parts[0].equals("execute_script") ||
                parts[0].equals("remove_greater") || parts[0].equals("remove_lower") || parts[0].equals("filter_distance") ||
                parts[0].equals("remove_distance")) {
                        if (parts.length != 2) {
                            System.out.println("\u001B[31mВведите корректный запрос (неверное число аргументов)\u001B[0m");
                                switchCommands(br);
                        }
        } else {
            if (parts.length != 1) {
                System.out.println("\u001B[31mВведите корректный запрос (неверное число аргументов)\u001B[0m");
                switchCommands(br);
            }
        }
            String res = parts[0];
            switch (res) {
                case "help" -> {
                    Help.help();
                    switchCommands(br);
                }
                case "info" -> {
                    Info.info();
                    switchCommands(br);
                }
                case "show" -> {
                    Show.show();
                    switchCommands(br);
                }
                case "add" -> {
                    var add = new Add();
                    add.addNew(br);
                    switchCommands(br);
                }
                case "update" -> {
                    try {
                        Long id = Long.parseLong(parts[1]);
                        Update.update(br, id);
                        switchCommands(br);
                    } catch (NumberFormatException e) {
                        System.out.println("\u001B[31mВведите корректный запрос(неверный id)\u001B[0m");
                        switchCommands(br);
                    }
                }
                case"execute_script" -> {
                    Execute_script.execute_script(br, parts[1]);
                    SwitchCommands.switchCommands(br);
                }

                case "remove_id" -> {
                    try {
                        Long id = Long.parseLong(parts[1]);
                        Remove.remove_by_id(id);
                        switchCommands(br);
                    } catch (NumberFormatException e) {
                        System.out.println("\u001B[31mВведите корректный запрос(неверный id)\u001B[0m");
                        switchCommands(br);
                    }
                }
                case "clear" -> {
                    Clear.clear();
                    switchCommands(br);
                }
                case "save" -> {
                    if (!Route.routes.isEmpty()) {
                        var fileFill = new FileFilling();
                        fileFill.write();
                        System.out.println("\u001B[32mЭлементы сохранены\u001B[0m");
                    } else {
                        System.out.println("\u001B[31mСохранять нечего\u001B[0m");
                    }
                    switchCommands(br);
                }
                case "exit" -> System.exit(0);

                case "remove_head" -> {
                    Remove.remove_head();
                    switchCommands(br);
                }
                case "remove_lower" -> {
                    Remove.remove_lower(br, parts[1]);
                    switchCommands(br);
                }
                case "remove_greater" -> {
                    Remove.remove_greater(br, parts[1]);
                    switchCommands(br);
                }
                case "remove_distance" -> {
                    try {
                        Double distance = Double.parseDouble(parts[1]);
                        Remove.remove_distance(distance);
                        switchCommands(br);
                    } catch (NumberFormatException e) {
                        System.out.println("\u001B[31mВведите корректный запрос(неверное значение дистанции)\u001B[0m");
                        switchCommands(br);
                    }
                }
                case "min_to" -> {
                    Min_to.min_to();
                    switchCommands(br);
                }
                case "filter_distance" -> {
                    try {
                        Double distance = Double.parseDouble(parts[1]);
                        Filter_distance.filter_distance(distance);
                        switchCommands(br);
                    } catch (NumberFormatException e) {
                        System.out.println("\u001B[31mВведите корректный запрос(неверное значение дистанции)\u001B[0m");
                        switchCommands(br);
                    }
                }

                default -> {
                    System.out.println("\u001B[31mПожалуйста, введите корректную команду(команды не существует)\u001B[0m");
                    Help.help();
                    switchCommands(br);
                }
            }
        }
}
