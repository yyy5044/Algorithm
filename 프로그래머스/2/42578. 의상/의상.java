import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < clothes.length; i++) {
            map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1);
        }
        
        int ans = 1;
        
        for (int count : map.values()) {
            ans *= count + 1;
        }
        
        return ans - 1;
    }
}