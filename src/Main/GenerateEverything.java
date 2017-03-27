package Main;


import ItemGenerator.EnumItemTypeGenerator;
import ItemGenerator.MethodsGenerator;
import Utils.JSONRead;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class GenerateEverything {
    private String in;
    private String out;
    String[] namespaces;

    public GenerateEverything(String i, String o) {
        in = i;
        out = o + "/";
        createDirectory();

        namespaces = new String[1];
        namespaces[0] = "redi";
    }

    public void generate() throws IOException {
        generateItems();
    }

    private void generateItems() throws IOException {
        JSONObject json = JSONRead.readFrom(in + "/items.json");

        EnumItemTypeGenerator gen = new EnumItemTypeGenerator(namespaces);
        gen.generate(json);
        String result = gen.getResult();

        PrintWriter writer = new PrintWriter(out + "items.hpp");
        writer.write(result);
        writer.close();


        MethodsGenerator mg = new MethodsGenerator();
    }

    private void createDirectory() {
        File f = new File(out);

        if (!f.exists()) {
            f.mkdirs();
        }
    }
}
