package com.example.threadLocal;

import java.util.HashMap;
import java.util.Map;

public class UserStore {

    private final Map<Integer, String> userMap = new HashMap<>();

    public UserStore() {
        userMap.put(1, "macro");
        userMap.put(2, "jone");
    }

    public String getUserNameByUserId(Integer userId) {
        return userMap.get(userId);
    }
}
