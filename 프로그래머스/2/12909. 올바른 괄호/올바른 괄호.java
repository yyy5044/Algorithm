import java.util.*;

class Solution {
    boolean solution(String s) {
        // '('는 +, ')'는 -
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') sum += 1;
            else sum -= 1;
            
            if (sum < 0) return false;
        }
        
        if (sum == 0) return true;
        
        return false;
    }
}