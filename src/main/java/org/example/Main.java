package org.example;

import commands.SwitchCommands;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main (String[] args) throws IOException{
        var fileFill = new FileFilling();
        fileFill.write();
        var br = new BufferedReader(new InputStreamReader(System.in));
        SwitchCommands.switchCommands(br);
    }
}
