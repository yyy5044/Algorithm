import java.io.*;
import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();
        
        for (String c : completion) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        String name = "";
        for (String p : participant) {
            int count = map.getOrDefault(p, 0);
            
            if (count != 0) {
                map.put(p, count - 1);
            } else {
                name = p;
                break;
            }
        }
        
        return name;
    }
}