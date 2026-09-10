package web;

import java.util.Map;
import java.util.HashMap;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class FormParser{
    public static Map<String, String> mapForm(String formData) {
        Map<String, String> formMap = new HashMap<>();

        if (formData == null || formData.trim().length() == 0)
            return formMap;

        String[] pairs = formData.split("&");

        for (String pair : pairs) {
            String[] keyValue = pair.split("=", 2);
            
            if (keyValue.length == 2) {
                String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8);
                String value = URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8);
                
                formMap.put(key, value);
            } else if (keyValue.length == 1 && !keyValue[0].isEmpty()) {
                String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8);
                formMap.put(key, "");
            }
        }
        
        return formMap;
        
    }
}