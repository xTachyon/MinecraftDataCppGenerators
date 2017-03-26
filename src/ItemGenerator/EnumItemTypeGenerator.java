package ItemGenerator;


import Cxx.CppFile;
import Cxx.IncludeFileType;
import Cxx.Type;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EnumItemTypeGenerator {
    private JSONObject json;
    private String[] namespaces;
    private Item[] items;
    private StringBuilder str;
    private CppFile header;



    public EnumItemTypeGenerator(JSONObject json, String[] namespaces) {
        this.json = json;
        this.namespaces = namespaces;
    }

    public void generate() {
        generateList();
        generateString();
    }

    public String getResult() {
        return str.toString();
    }

    private void generateList() {
        List<Item> items = new ArrayList<>();

        for (Object i : json.getJSONArray("content")){
            JSONObject obj = (JSONObject)i;

            Item item = Item.parse(obj);
            if (item != null) {
                items.add(item);
            }
        }

        this.items = items.toArray(new Item[items.size()]);
    }

    private void generateString() {
        str = new StringBuilder();
        header = new CppFile(str, namespaces, "Items");

        header.writeGuardBegin();
        header.writeInclude("cstdint", IncludeFileType.Standard);
        header.writeNewline();
        header.writeNamespacesBegin();

        generateEnum();
        header.writeNewline();
        header.writeNewline();

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
