package Utils;


public class Indent {
    private StringBuilder str;
    private StringBuilder indent;
    private int growwith;

    public Indent(StringBuilder str, int growwith) {
        this.str = str;
        indent = new StringBuilder();
        this.growwith = growwith;
    }

    public void begin() {
        for (int i = 0; i < growwith; ++i) {
            indent.append(' ');
        }
    }

    public void write() {
        str.append(indent);
    }

    public void end() {
        for (int i = 0; i < growwith; ++i) {
            indent.deleteCharAt(indent.length() - 1);
        }
    }
}
