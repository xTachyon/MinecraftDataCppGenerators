package Cxx;

import Utils.Indent;

class ConstructorArgs {
    public String type;
    public String name;
}

public class Class {
    private CppFile file;
    private StringBuilder str;
    private Indent indent;
    private String classname;

    public Class(CppFile cpp) {
        file = cpp;
        str = cpp.getString();
        indent = cpp.getIndent();
    }

    public Class(CppFile cpp, ClassType type, String name) {
        this(cpp);
        writeBegin(type, name);
    }

    public void writeBegin(ClassType type, String name) {
        file.writeIndent();
        str.append(type.toString()).append(" ").append(name).append("\n{\n");
        indent.begin();
        classname = name;
    }

    public void writeEnd() {
        indent.end();
        str.append("};");
    }

    public void writeConstructorBegin(ConstructorArgs... args) {
        indent.write();
        str.append(classname).append("(");

        if (args.length > 0) {
            writeConstructorVariable(args[0]);
        }

        for (int i = 1; i < args.length; ++i) {
            str.append(", ");
            writeConstructorVariable(args[i]);
        }

        str.append(")\n");
        indent.write();
        str.append("{");
        indent.begin();
    }

    private void writeConstructorVariable(ConstructorArgs i) {
        str.append(i.type).append(" ").append(i.name);
    }
}
