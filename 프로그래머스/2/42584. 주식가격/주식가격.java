import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            int cur = prices[i];
            for (int j = i+1; j < n; j++) {
                int target = prices[j];
                
                if (cur > target) {
                    answer[i] = j-i;
                    break;
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (answer[i] == 0) {
                answer[i] = (n-1) - i;
            }
        }
        
        return answer;
    }
}