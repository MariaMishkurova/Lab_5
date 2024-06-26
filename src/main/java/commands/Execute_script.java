package commands;
import Files.FileFilling;
import Main_part.*;
import java.io.*;
import java.util.ArrayList;

public class Execute_script {
    private final static ArrayList<String> executed_scripts = new ArrayList<>();
//fileName - второе (из двух) слово в введённой строке
    public static void execute_script(BufferedReader br, String fileName) throws IOException {
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println(Style.RED + "Файла не существует" + Style.BLACK);
                SwitchCommands.switchCommands(br);
            } else {
                Reader reader = new FileReader(file);
                BufferedReader buffReader = new BufferedReader(reader);
                executed_scripts.add(fileName);
                outerLoop:
                while (buffReader.ready()) {
                    String s = buffReader.readLine();
                    String[] lineParts = s.split(" ");
                    if (lineParts[0].equals("update") || lineParts[0].equals("remove_id") || lineParts[0].equals("execute_script") ||
                            lineParts[0].equals("remove_greater") || lineParts[0].equals("remove_lower") || lineParts[0].equals("filter_distance") ||
                            lineParts[0].equals("remove_distance")) {
                        if (lineParts.length != 2) {
                            System.out.println(Style.RED + "Файл содержит ошибку(неверное число аргументов)" + Style.BLACK);
                            SwitchCommands.switchCommands(br);
                        }
                    } else {
                        if (lineParts.length != 1) {
                            System.out.println(Style.RED + "Файл содержит ошибку(неверное число аргументов)" + Style.BLACK);
                            SwitchCommands.switchCommands(br);
                        }
                    }
                    String res = lineParts[0];
                    switch (res) {
                        case "help" -> Help.help();

                        case "info" -> Info.info();

                        case "show" -> Show.show();
                        case "add" -> {
                            var add = new Add();
                            add.addNew(br);
                        }
                        case "update" -> {
                            try {
                                Long id = Long.parseLong(lineParts[1]);
                                Update.update(br, id);
                            } catch (NumberFormatException e) {
                                System.out.println(Style.RED + "Файл содержит ошибку(неверный id)" + Style.BLACK);
                                break outerLoop;
                            }
                        }
                        case "execute_script" -> {
                            if (executed_scripts.contains(lineParts[1])) {
                                System.out.println("Повторное выполнение скрипта запрещено!!");
                                return;
                            }
                            if (lineParts[1].equals(fileName)) {
                                System.out.println(Style.RED + "Данный скрипт уже исполняется" + Style.BLACK);
                                return;
                            }
                                execute_script(br, lineParts[1]);
                        }

                        case "remove_id" -> {
                            try {
                                Long id = Long.parseLong(lineParts[1]);
                                Remove.remove_by_id(id);
                            } catch (NumberFormatException e) {
                                System.out.println(Style.RED + "Файл содержит ошибку(неверный id)" + Style.BLACK);
                                break outerLoop;
                            }
                        }
                        case "clear" -> Clear.clear();

                        case "save" -> {
                                var fileFill = new FileFilling();
                                fileFill.write(true);
                                System.out.println(Style.GREEN + "Элементы сохранены" + Style.BLACK);
                        }
                        case "exit" -> System.exit(0);

                        case "remove_head" -> Remove.remove_head();

                        case "remove_lower" -> {
                            try {
                                Remove.remove_lower(br, Double.parseDouble(lineParts[1]));
                            } catch (NumberFormatException e) {
                                System.out.println(Style.RED + "Файл содержит ошибку(введено не число)" + Style.BLACK);
                                break outerLoop;
                            }
                        }
                        case "remove_greater" -> {
                            try {
                                Remove.remove_greater(br, Double.parseDouble(lineParts[1]));
                            } catch (NumberFormatException e) {
                                System.out.println(Style.RED + "Файл содержит ошибку(введено не число)" + Style.BLACK);
                                break outerLoop;
                            }
                        }
                        case "remove_distance" -> {
                            try {
                                Double distance = Double.parseDouble(lineParts[1]);
                                Remove.remove_distance(distance);
                            } catch (NumberFormatException e) {
                                System.out.println(Style.RED + "Файл содержит ошибку(неверное значение дистанции)" + Style.BLACK);
                                break outerLoop;
                            }
                        }
                        case "min_to" -> Min_to.min_to();

                        case "filter_distance" -> {
                            try {
                                Double distance = Double.parseDouble(lineParts[1]);
                                Filter_distance.filter_distance(distance);
                            } catch (NumberFormatException e) {
                                System.out.println(Style.RED + "Файл содержит ошибку(неверное значение дистанции)" + Style.BLACK);
                                break outerLoop;
                            }
                        }

                        default -> {
                            System.out.println(Style.RED + "Файл содержит ошибку(команды не существует)" + Style.BLACK);
                            Help.help();
                            break outerLoop;
                        }
                    }
                }
                System.out.println("Файл прочитан");
                reader.close();
                buffReader.close();
                SwitchCommands.switchCommands(br);
            }
    }
}
