package bilibili.lingaly.fabricmodregister;

import org.json.simple.JSONObject;

import java.io.*;

public class JsonCreater {

    /**
     * 创建models/item文件以及lang文件
     *
     * @param MOD_ID     模组ID
     * @param name       物品代号
     * @param actualname 物品实际名称
     */
    public static void createItemJson(String MOD_ID, String name, String actualname) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("parent",
                "item/generated");
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("layer0",
                MOD_ID + ":item/" + name);
        jsonObject.put("textures", jsonObject1);
        File file = new File("");
        String string = file.getAbsolutePath() + "\\src\\main\\resources\\assets\\" + MOD_ID;
        Utils.mkdirs(new File(string + "\\models\\item"));
        File file3 = new File(string + "\\models\\item\\" + name + ".json");
        Utils.createFile(file3);
        Utils.writeFile(file3, jsonObject);
        Utils.mkdirs(new File(string + "\\lang"));
        File file2 = new File(string + "\\lang\\en_us.json");
        JSONObject jsonObject2;
        if (file2.exists()) {
            jsonObject2 = Utils.readFile(file2);
            jsonObject2.remove("item." + MOD_ID + "." + name);
            jsonObject2.put("item." + MOD_ID + "." + name, actualname);
        } else {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            jsonObject2 = new JSONObject();
            jsonObject2.put("item." + MOD_ID + "." + name, actualname);
        }
        Utils.writeFile(file2, jsonObject2);
    }

    /**
     * 创建models/block文件以及lang文件
     * @param MOD_ID 模组ID
     * @param name 方块代号
     * @param actualname 方块实际名称
     */
    public static void createBlockJson(String MOD_ID, String name, String actualname) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("parent",
                "block/cube_all");
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("all",
                MOD_ID + ":block/" + name);
        jsonObject.put("textures", jsonObject1);
        File file = new File("");
        String string = file.getAbsolutePath() + "\\src\\main\\resources\\assets\\" + MOD_ID;
        Utils.mkdirs(new File(string + "\\models\\block"));
        File file1 = new File(string + "\\models\\block\\" + name + ".json");
        Utils.cwFile(file1, jsonObject);
        Utils.mkdirs(new File(string + "\\lang"));
        File file2 = new File(string + "\\lang\\en_us.json");
        JSONObject jsonObject2;
        if (file2.exists()) {
            jsonObject2 = Utils.readFile(file2);
            jsonObject2.remove("block." + MOD_ID + "." + name);
            jsonObject2.put("block." + MOD_ID + "." + name, actualname);
        } else {
            Utils.createFile(file2);
            jsonObject2 = new JSONObject();
            jsonObject2.put("block." + MOD_ID + "." + name, actualname);
        }
        Utils.writeFile(file2, jsonObject2);
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("parent", MOD_ID + ":block/" + name);
        File file3 = new File(string + "\\models\\item\\" + name + ".json");
        Utils.mkdirs(new File(string + "\\models\\item"));
        Utils.cwFile(file3, jsonObject3);
        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("model", MOD_ID + ":block/" + name);
        JSONObject jsonObject5 = new JSONObject();
        jsonObject5.put("", jsonObject4);
        JSONObject jsonObject6 = new JSONObject();
        jsonObject6.put("variants", jsonObject5);
        Utils.mkdirs(new File(string + "\\blockstates"));
        File file4 = new File(string + "\\blockstates\\" + name + ".json");
        Utils.cwFile(file4, jsonObject6);
    }

    /**
     * 创建lang文件
     * @param MOD_ID 模组ID
     * @param name 物品类别代号
     * @param actualname 物品类别实际名称
     */
    public static void CreateItemGroupJson(String MOD_ID, String name, String actualname) {
        File file = new File("");
        String string = file.getAbsolutePath() + "\\src\\main\\resources\\assets\\" + MOD_ID;
        File file2 = new File(string + "\\lang\\en_us.json");
        JSONObject jsonObject;
        if (file2.exists()) {
            jsonObject = Utils.readFile(file2);
            jsonObject.remove("itemGroup." + MOD_ID + "." + name);
            jsonObject.put("itemGroup." + MOD_ID + "." + name, actualname);
        } else {
            Utils.createFile(file2);
            jsonObject = new JSONObject();
            jsonObject.put("itemGroup." + MOD_ID + "." + name, actualname);
        }
        Utils.writeFile(file2, jsonObject);
    }
}
