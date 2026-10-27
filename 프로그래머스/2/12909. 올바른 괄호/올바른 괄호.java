import java.util.*;

class Solution {
    boolean solution(String s) {
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                dq.push(0);
            } else {
                if (dq.isEmpty()) return false;
                dq.pop();
            }
        }
        
        if (!dq.isEmpty()) return false;;

        return true;
    }
}