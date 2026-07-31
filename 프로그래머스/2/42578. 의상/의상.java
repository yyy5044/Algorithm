import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        
        for (String[] c : clothes) {
            map.put(c[1], map.getOrDefault(c[1], 0) + 1);
        }
        
        int ans = 1;
        for (int n : map.values()) {
            ans *= n + 1;
        }
        ans -= 1;
        
        return ans;
    }
}