package ItemGenerator;

import Cxx.ConstCharGen;
import Cxx.IConstChar;

import java.util.List;

class DisplayNameItemGenerator extends ItemConstCharGen {

    @Override
    public String getConstCharValue(Object o) {
        if (o instanceof Item) {
            return ((Item)o).displayname;
        }
        return ((Block)o).displayname;
    }
}

class NameItemGenerator extends ItemConstCharGen {

    @Override
    public String getConstCharValue(Object o) {
        if (o instanceof Item) {
            return ((Item)o).name;
        }
        return ((Block)o).name;
    }
}

public class MethodsGenerator {
    private String path;

    public MethodsGenerator(String p) {
        path = p;
    }

    public void generate(List<Item> items, List<Block> blocks) {
        ConstCharGen gen = new ConstCharGen(path + "materialdisplayname.txt");

        gen.generateMap(items, new DisplayNameItemGenerator());
        gen.generateMap(blocks, new DisplayNameItemGenerator());
        gen.generateString();
        gen.write();

        gen = new ConstCharGen(path + "materianname.txt");
        gen.generateMap(items, new NameItemGenerator());
        gen.generateMap(blocks, new NameItemGenerator());
        gen.generateString();
        gen.write();
    }
}
