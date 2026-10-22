import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        for (String[] arr : clothes) {
            String c = arr[1];
            if (!map.containsKey(c)) map.put(c, 0);
            map.put(c, map.get(c) + 1);
        }
        
        int ans = 1;
        for (String c : map.keySet()) {
            ans *= map.get(c) + 1;
        }
        
        
        return ans - 1;
        // [a, b]
        // [c]
        
        // 안 고르거나, a를 고르거나, b를 고르거나 -> 3가지
        // 안 고르거나, c를 고르거나
        // 둘 다 안 고르거나를 선택할 수 없으므로 -1
        
        
    }
}