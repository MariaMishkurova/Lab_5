package Main_part;

import Files.FileFilling;
import Files.XMLHandler;
import commands.Help;
import commands.SwitchCommands;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main (String[] args) throws IOException{
        System.out.println("\u001B[35m---------------------------------------------------ДОБРО ПОЖАЛОВАТЬ!---------------------------------------------------\u001B[0m");
        System.out.println("ВВЕДИТЕ ОДНУ ИЗ КОМАНД:");
        Help.help();
        var fileFill = new FileFilling();
        fileFill.write();
        var br = new BufferedReader(new InputStreamReader(System.in));
        SwitchCommands.switchCommands(br);
    }
    public static String getHome(){
        return System.getenv("HOME");
    }
}
