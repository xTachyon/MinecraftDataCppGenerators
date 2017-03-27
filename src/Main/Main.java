package Main;

import ItemGenerator.EnumItemTypeGenerator;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();

        try {
            GenerateEverything g = new GenerateEverything(s, "result");
            g.generate();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
// /home/andrei/repositories/minecraft-data/data/pc/1.11/items.json
// E:\Info\repos\minecraft-data\data\pc\1.11\items.json
// E:\Info\repos\minecraft-data\data\pc\1.11\