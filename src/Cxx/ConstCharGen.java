package Cxx;


import Utils.MutableInt;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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

    public void generate(Iterable<IConstChar> list) {
        generateMap(list);
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

    public void generateAndWrite(Iterable<IConstChar> list) {
        generate(list);
        write();
    }

    private void generateMap(Iterable<IConstChar> list) {
        for (IConstChar i : list) {
            map.put(i.getConstCharID(), i.getConstCharValue());
            max = Math.max(max, i.getConstCharID());
        }
    }

    private void generateString() {
        str.append("const char* names[] = {\n");
        String padding = new String(new char[6]).replace('\0', ' ');

        for (int i = 0; i <= max; ++i) {
            str.append(padding).append("\"").append(map.getOrDefault(i, "UNKNOWN")).append("\"\n");
        }

        str.append(padding).append("};");
    }
}
