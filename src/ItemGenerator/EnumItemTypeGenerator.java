package ItemGenerator;


import Cxx.CppFile;
import Cxx.IncludeFileType;
import Cxx.Type;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EnumItemTypeGenerator {
    private String[] namespaces;
    private List<Item> items;
    private StringBuilder str;
    private CppFile header;

    public EnumItemTypeGenerator(String[] namespaces) {
        this.namespaces = namespaces;
    }

    public void generate(JSONObject json) {
        items  = Item.parseItems(json);
        generateString();
    }

    public String getResult() {
        return str.toString();
    }

    private void generateString() {
        str = new StringBuilder();
        header = new CppFile(str, namespaces, "Items");

        header.writeGuardBegin();
        header.writeInclude("cstdint", IncludeFileType.Standard);
        header.writeNewline();
        header.writeNamespacesBegin();

        generateEnum();

        header.writeNamespacesEnd();
        header.writeGuardEnd();
    }

    private void generateEnum() {
        header.writeStructBegin("ItemTypeEnum");

        for (Item i : items) {
            header.writeStaticConstexpr(Type.Int, i.writename, String.valueOf(i.id));
        }

        header.writeStructEnd();
    }
}
