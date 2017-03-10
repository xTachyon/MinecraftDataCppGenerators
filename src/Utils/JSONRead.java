package Utils;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONRead {
    public static JSONObject readFrom(String path) throws IOException {
        String str = new String(Files.readAllBytes(Paths.get(path)));

        return new JSONObject("{ content : " + str + '}');
    }
}
