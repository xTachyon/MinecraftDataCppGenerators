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
            JSONObject json = Utils.JSONRead.readFrom(s);
            String[] namespaces = new String[1];
            namespaces[0] = "redi";

            EnumItemTypeGenerator gen = new EnumItemTypeGenerator(json, namespaces);
            gen.generate();
            String result = gen.getResult();

            PrintWriter writer = new PrintWriter("Items.hpp");
            writer.write(result);
            writer.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
// /home/andrei/repositories/minecraft-data/data/pc/1.11/items.json
// E:\Info\repos\minecraft-data\data\pc\1.11\items.json