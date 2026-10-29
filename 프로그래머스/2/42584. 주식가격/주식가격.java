import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        Deque<Integer> dq = new ArrayDeque<>();
        int[] answer = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            while (!dq.isEmpty() && prices[dq.peek()] > prices[i]) {
                int n = dq.pop();
                answer[n] = i - n;
            }
            
            dq.push(i);
        }
        
        while (!dq.isEmpty()) {
            int n = dq.pop();
            answer[n] = prices.length - 1 - n;
        }

        return answer;
    }
}