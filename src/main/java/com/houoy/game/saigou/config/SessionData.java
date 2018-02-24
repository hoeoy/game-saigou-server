package com.houoy.game.saigou.config;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andyzhao on 2/24/2018.
 */
public class SessionData {
    //key :user_code
    private static Map<String, String> sessionIDMap = new HashMap<String, String>();

    public static Map<String, String> getSessionIDMap() {
        return sessionIDMap;
    }

    public static void setSessionIDMap(Map<String, String> sessionIDMap) {
        SessionData.sessionIDMap = sessionIDMap;
    }
}
