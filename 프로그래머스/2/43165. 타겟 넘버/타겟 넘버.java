import java.util.*;

class Solution {
    static int N, count;
    
    public int solution(int[] numbers, int target) {
        N = numbers.length; count = 0;
        
        dfs(0, target, numbers, 0);
        
        return count;
    }
    
    static void dfs(int depth, int target, int[] numbers, int sum) {
        if (depth == N) {
            if (sum == target) count++;
            return;
        }
        
        dfs(depth+1, target, numbers, sum - numbers[depth]);
        dfs(depth+1, target, numbers, sum + numbers[depth]);
    }
}