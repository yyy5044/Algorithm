import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        
        int count = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        
        for (int i = 0; i < number.length(); i++) {
            int next = number.charAt(i) - '0';
            while (!dq.isEmpty() && dq.peek() < next && count < k) {
                dq.pop();
                count++;
            }
            dq.push(next);
        }
        
        if (k - count > 0) {
            for (int i = 0; i < k - count; i++) {
                dq.pop();
            }
        }
        
        while (!dq.isEmpty()) {
            int n = dq.pop();
            sb.append(n);
        }
        
        String answer = sb.reverse().toString();
        
        return answer;
    }
}