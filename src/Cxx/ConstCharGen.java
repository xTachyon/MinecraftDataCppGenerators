package Cxx;


import Utils.MutableInt;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class ConstCharGen {
    private String filename;
    private StringBuilder str;
    private Map<Integer, String> map;
    private int max;

    public ConstCharGen(String filename) {
        this.filename = filename;
        str = new StringBuilder();
        map = new HashMap<>();
    }

    public <T> void generate(Iterable<T> list, IConstChar f) {
        generateMap(list, f);
        generateString();
    }

    public void write() {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.write(str.toString());
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public <T> void generateAndWrite(Iterable<T> list, IConstChar f) {
        generate(list, f);
        write();
    }

    public  <T> void generateMap(Iterable<T> list, IConstChar f) {
        for (T i : list) {
            int id = f.getConstCharID(i);

            map.put(id, f.getConstCharValue(i));
            max = Math.max(max, id);
        }

    }

    public void generateString() {
        str.append("const char* names[] = {\n");
        String padding = new String(new char[6]).replace('\0', ' ');

        for (int i = 0; i <= max; ++i) {
            str.append(padding).append("\"").append(map.getOrDefault(i, "UNKNOWN")).append("\",\n");
        }

        str.append(padding).append("};");
    }
}
