package Utils;


import java.util.List;

public class Utils {

    public static String[] getNamespaces(String str) {
        return str.split(" ,;");
    }

    public static String toUpperWithUnderscores(String str) {
        return str.toUpperCase().replace(' ', '_').replace('.', '_');
    }

    public static String toTitleCase(String str) {
        StringBuilder builder = new StringBuilder(str.length());

        String[] toc = str.split("[ _,./;]");
        for (String i : toc) {
            builder.append(Character.toUpperCase(i.charAt(0))).append(i.substring(1));
        }

        return builder.toString();
    }
}
