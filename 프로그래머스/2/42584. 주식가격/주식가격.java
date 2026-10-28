import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Deque<Integer> dq = new ArrayDeque<>();
        int idx = 0;
        dq.push(idx); // 최초 원소 푸시
        for (int time = 1; time < prices.length; time++) {
            while (!dq.isEmpty()) {
                if (prices[dq.peek()] > prices[idx+1]) { // 다음 거 확인, 가격이 떨어지면
                    int tmp = dq.pop(); // 뽑고
                    answer[tmp] = time - tmp; // 시간 확정
                } else break;      
            }
            
            dq.push(++idx);
        }
        
        while (!dq.isEmpty()) {
            int i = dq.pop();
            answer[i] = prices.length - i - 1;
        }
        
        return answer;
    }
}