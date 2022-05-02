package bilibili.lingaly.fabricmodregister;

import com.google.gson.JsonObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class Utils {

    static void mkdirs(File file){
        if (file.isFile()) {
            file.delete();
        }

        if (!file.exists()) {
            file.mkdirs();
        }
    }

    static void createFile(File file){
        if (file.exists()){
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void writeFile(File file, JSONObject jsonObject){
        try (BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));) {
            fileWriter.write(jsonObject.toJSONString().replace("\\", ""));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void cwFile(File file, JSONObject jsonObject){
        createFile(file);
        writeFile(file, jsonObject);
    }

    static JSONObject readFile(File file){
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        try (InputStreamReader fileReader=new InputStreamReader(new FileInputStream(file.getAbsolutePath()),"UTF-8")) {
            Object object = jsonParser.parse(fileReader);
            jsonObject = (JSONObject) object;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return jsonObject;
    }
}
