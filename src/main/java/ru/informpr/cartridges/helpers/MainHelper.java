package ru.informpr.cartridges.helpers;

import java.util.HashMap;
import java.util.Map;

public class MainHelper {
    public static Map<String, String> arraysToMap(String[] keys, String[] values) {
        Map<String, String> result = new HashMap<>();

        for (int i = 0; i < keys.length; i++) {
            if (keys[i].isEmpty()) {
                continue;
            }

            result.put(keys[i], values[i]);
        }
        return result;
    }
}
