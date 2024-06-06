package commands;
import Main_part.Style;

public class Help{
    public static void help(){

        System.out.println(Style.LINE + "\n" + Style.PURPLE + "help" + Style.BLACK + "- вывести справку по доступным командам \n" + Style.PURPLE + "info" + Style.BLACK + " - " +
                "узнать информацию о коллекции\n" + Style.PURPLE + "show" + Style.BLACK + " - вывести все элементы коллекции\n" + Style.PURPLE +
                "add" + Style.BLACK + " - добавить новый элемент в коллекцию \n" + Style.PURPLE + "update (id)" + Style.BLACK +
                " - обновить значение элемента коллекции, id которого равен заданному\n" + Style.PURPLE + "remove_id (id)"
                + Style.BLACK + " - удалить элемент из коллекции по его id\n" + Style.PURPLE + "clear" + Style.BLACK + " - очистить коллекцию\n" + Style.PURPLE
                + "save - " + Style.BLACK + "сохранить коллекцию в файл" + "\n" + Style.PURPLE + "execute_script (file_name)" + Style.BLACK + "- " +
                "считать и исполнить скрипт " + "из указанного файла\n" + Style.PURPLE + "exit " + Style.BLACK + "- завершить программу " +
                "(без сохранения в файл)\n" + Style.PURPLE + "remove_head" + Style.BLACK + " - вывести первый элемент коллекции и удалить его\n" +
                Style.PURPLE + "remove_greater (name)" + Style.BLACK + " - удалить из коллекции все элементы, превышающие заданный\n" +
                Style.PURPLE + "remove_lower (name)" + Style.BLACK + " - удалить из коллекции все элементы, меньшие, чем заданный\n" +
                Style.PURPLE + "remove_distance (distance) " + Style.BLACK + "- удалить из коллекции все элементы, значение " +
                "поля distance которого эквивалентно заданному\n" + Style.PURPLE + "min_to" + Style.BLACK + "- вывести любой объект из коллекции," +
                " значение поля to которого является минимальным\n" + Style.PURPLE + "filter_distance (distance) " + Style.BLACK + " -" +
                " вывести элементы, значение поля distance которых меньше заданного\n" + Style.LINE);


    }
}
