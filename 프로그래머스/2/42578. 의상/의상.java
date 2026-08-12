import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < clothes.length; i++) {
            String category = clothes[i][1];
        
            map.put(category, map.getOrDefault(category, 0) + 1);
        }
        
        int ans = 1;
        for (int i : map.values()){
            ans *= i + 1;
        }
        
        return ans - 1;
    }
}