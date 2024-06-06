package commands;
import org.example.Add;
import org.example.FileFilling;
import org.example.Route;

import java.io.*;

public class Execute_script {
    public static void execute_script(BufferedReader br, String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("\u001B[31mФайла не существует)\u001B[0m");
            SwitchCommands.switchCommands(br);
        } else {
            Reader reader = new FileReader(file);
            BufferedReader buffReader = new BufferedReader(reader);

            outerLoop:
            while (buffReader.ready()) {
                String s = buffReader.readLine();
                String[] lineParts = s.split(" ");
                if (lineParts[0].equals("update") || lineParts[0].equals("remove_id") || lineParts[0].equals("execute_script") ||
                        lineParts[0].equals("remove_greater") || lineParts[0].equals("remove_lower") || lineParts[0].equals("filter_distance") ||
                        lineParts[0].equals("remove_distance")) {
                    if (lineParts.length != 2) {
                        System.out.println("\u001B[31mФайл содержит ошибку(неверное число аргументов)\u001B[0m");
                        SwitchCommands.switchCommands(br);
                    }
                } else {
                    if (lineParts.length != 1) {
                        System.out.println("\u001B[31mФайл содержит ошибку(неверное число аргументов)\u001B[0m");
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
                            System.out.println("\u001B[31mФайл содержит ошибку(неверный id)\u001B[0m");
                            break outerLoop;
                        }
                    }
                    case "execute_script" -> {
                        if(lineParts[1].equals(fileName)) {
                            System.out.println("\u001B[31mДанный скрипт уже исполняется\u001B[0m");
                        } else {
                            execute_script(br, lineParts[1]);
                        }
                    }

                    case "remove_id" -> {
                        try {
                            Long id = Long.parseLong(lineParts[1]);
                            Remove.remove_by_id(id);
                        } catch (NumberFormatException e) {
                            System.out.println("\u001B[31mФайл содержит ошибку(неверный id)\u001B[0m");
                            break outerLoop;
                        }
                    }
                    case "clear" -> Clear.clear();

                    case "save" -> {
                        if (!Route.routes.isEmpty()) {
                            var fileFill = new FileFilling();
                            fileFill.write();
                            System.out.println("\u001B[32mЭлементы сохранены\u001B[0m");
                        } else {
                            System.out.println("\u001B[31mСохранять нечего\u001B[0m");
                        }
                    }
                    case "exit" -> System.exit(0);

                    case "remove_head" -> Remove.remove_head();

                    case "remove_lower" -> Remove.remove_lower(br, lineParts[1]);
                    case "remove_greater" -> Remove.remove_greater(br, lineParts[1]);

                    case "remove_distance" -> {
                        try {
                            Double distance = Double.parseDouble(lineParts[1]);
                            Remove.remove_distance(distance);
                        } catch (NumberFormatException e) {
                            System.out.println("\u001B[31mФайл содержит ошибку(неверное значение дистанции)\u001B[0m");
                            break outerLoop;
                        }
                    }
                    case "min_to" -> Min_to.min_to();

                    case "filter_distance" -> {
                        try {
                            Double distance = Double.parseDouble(lineParts[1]);
                            Filter_distance.filter_distance(distance);
                        } catch (NumberFormatException e) {
                            System.out.println("\u001B[31mФайл содержит ошибку(неверное значение дистанции)\u001B[0m");
                            break outerLoop;
                        }
                    }

                    default -> {
                        System.out.println("\u001B[31mФайл содержит ошибку(команды не существует)\u001B[0m");
                        Help.help();
                        break outerLoop;
                    }
                }
                }
                reader.close();
                buffReader.close();
                SwitchCommands.switchCommands(br);
        }
    }
}
