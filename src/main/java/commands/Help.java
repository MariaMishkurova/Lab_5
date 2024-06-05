package commands;

public class Help{
    public static final String black = "\u001B[0m";
    public static final String purple = "\u001B[35m";
    public static void help(){
        System.out.println(purple + "help" + black + "- вывести справку по доступным командам \n" + purple + "info" + black + " - " +
                "узнать информацию о коллекции\n" + purple + "show" + black + " - вывести все элементы коллекции\n" + purple +
                "add" + black + " - добавить новый элемент в коллекцию \n" + purple + "update" + black +
                " - обновить значение элемента коллекции, id которого равен заданному\n" + purple + "remove_id"
                + black + " - удалить элемент из коллекции по его id\n" + purple + "clear" + black + " - очистить коллекцию\n" + purple
                + "save - " + black + "сохранить коллекцию в файл" + "\n" + purple + "execute_script file_name" + black + "- " +
                "считать и исполнить скрипт " + "из указанного файла\n" + purple + "exit " + black + "- завершить программу " +
                "(без сохранения в файл)\n" + purple + "remove_head" + black + " - вывести первый элемент коллекции и удалить его\n" +
                purple + "remove_greater" + black + " - удалить из коллекции все элементы, превышающие заданный\n" +
                purple + "remove_lower" + black + "- удалить из коллекции все элементы, меньшие, чем заданный\n" +
                purple + "remove_distance" + black + "- удалить из коллекции все элементы, значение " +
                "поля distance которого эквивалентно заданному\n" + purple + "min_to" + black + "- вывести любой объект из коллекции," +
                " значение поля to которого является минимальным\n" + purple + "filter_distance" + black + " -" +
                " вывести элементы, значение поля distance которых меньше заданного");
    }
}
