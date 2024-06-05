package org.example;
import commands.Filter_distance;
import commands.SwitchCommands;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main (String[] args) throws IOException, ParserConfigurationException, SAXException {
        var fileFill = new FileFilling();
        fileFill.write();
        /*для проверок
        Route r1 = new Route("1", 1.0,1,1,1,1,"1",10,111,111,"1",5.0);
        Route r2 = new Route("2", 1.0,1,1,1,1,"1",10,11,11,"1",11.0);
        Route r3 = new Route("3", 1.0,1,1,1,1,"1",10,1,11,"1",15.0);
        Route r4 = new Route("4", 1.0,1,1,1,1,"1",10,11,111,"1",2.0);
        Route r5 = new Route("5", 1.0,1,1,1,1,"1",10,15,15,"1",11.0);
        Route r6 = new Route("6", 1.0,1,1,1,1,"1",10,1,10,"1",15.0);
        Route r7 = new Route("7", 1.0,1,1,1,1,"1",1,1,1,"1",100.0);
        Route r8 = new Route("8", 1.0,1,1,1,1,"1",1,1,1,"1",11.0);
        */
        var br = new BufferedReader(new InputStreamReader(System.in));
        SwitchCommands.switchCommands(br.readLine(), br);
    }
}
