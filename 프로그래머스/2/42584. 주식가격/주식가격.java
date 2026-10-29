import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        Deque<Integer> dq = new ArrayDeque<>();
        int[] answer = new int[prices.length];
        
        dq.push(0);
        for (int time = 0; time < prices.length-1; time++) {
            while (!dq.isEmpty() && prices[dq.peek()] > prices[time+1]) {
                int n = dq.pop();
                answer[n] = (time + 1) - n;
            }
            
            dq.push(time+1);
        }
        
        int t = prices.length - 1;
        while (!dq.isEmpty()) {
            int n = dq.pop();
            answer[n] = t - n; 
        }
        
        return answer;
    }
}