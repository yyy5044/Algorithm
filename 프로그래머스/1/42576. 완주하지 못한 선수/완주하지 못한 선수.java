import java.util.*;
import java.io.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();
        
        for (String c : completion) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        for (String p : participant) {
            int count = map.getOrDefault(p, 0);
            if (count == 0) return p;
            map.put(p, count - 1);
        }
        
        return "";
    }
}