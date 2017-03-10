package Utils;

import java.util.List;

public class CppFile {
    private StringBuilder str;
    private String[] namespaces;
    private String macro;
    private Indent indent;

    public CppFile(StringBuilder str, String[] namespaces, String macro)  {
        this.str = str;
        this.namespaces = namespaces;
        this.macro = Utils.toUpperWithUnderscores(macro) + "_HPP";
        indent = new Indent(str, 2);
    }

    public void writeGuardBegin() {
        str.append("#ifndef ");
        str.append(macro);
        str.append("\n#define ");
        str.append(macro);
        str.append("\n\n");
    }

    public void writeGuardEnd() {
        str.append("#endif // ");
        str.append(macro);
    }

    public void writeNamespaceBegin(String namespace) {
        str.append("namespace ");
        str.append(namespace);
        str.append("\n{\n\n");
    }

    public void writeNamespaceEnd(String namespace) {
        str.append("\n\n} // namespace ");
        str.append(namespace);
        str.append("\n\n");
    }

    public void writeNamespacesBegin() {
        for (String str : namespaces) {
            writeNamespaceBegin(str);
        }
    }

    public void writeNamespacesEnd() {
        for (int i = namespaces.length - 1; i >= 0; --i) {
            writeNamespaceEnd(namespaces[i]);
        }
    }

    public void writeEnumBegin(String enumname, String type) {
        indent.write();
        str.append("enum class ").append(enumname).append(" : ").append(type).append("\n{\n");
        indent.begin();
    }

    public void writeEnumEnd(String enumname) {
        indent.end();
        str.append("}; // enum class ").append(enumname);
    }

    public <T> void writeEnum(String enumname, String type, Pair<String, String>[] pairs) {
        writeEnumBegin(enumname, type);
        indent.begin();

        for (Pair<String, String> i : pairs) {
            indent.write();
            str.append(i.first).append(" = ").append(i.second).append(",\n");
        }

        if (pairs.length > 0) {
            str.deleteCharAt(str.length() - 1);
            str.deleteCharAt(str.length() - 1);
            str.append('\n');
        }

        indent.end();
        writeEnumEnd(enumname);
    }

    public void writeIndent() {
        indent.write();
    }
}