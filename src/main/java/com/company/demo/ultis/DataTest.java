package com.company.demo.ultis;

import com.company.demo.entity.User;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class DataTest {
    private static JSONObject users;


    public synchronized static void init() {
        try {
            File file = new File(System.getProperty("user.dir") + File.separator + "users.json");
            String content = FileUtils.readFileToString(file, "utf-8");
            users = new JSONObject(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUserTest(){
        JSONObject jsonData = users.getJSONObject("user");
        return User.builder()
                .name(jsonData.getString("name"))
                .phoneNumber(jsonData.getString("phoneNumber"))
                .build();
    }
}
