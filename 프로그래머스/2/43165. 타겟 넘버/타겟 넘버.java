import java.io.*;
import java.util.*;

class Solution {
    static int ans;
    static int[] comb;
    
    public int solution(int[] numbers, int target) {
        ans = 0;
        
        int sum = 0;
        for (int n : numbers) {
            sum += n;
        }
        
        for (int i = 0; i < numbers.length+1; i++) {
            comb = new int[i];
            dfs(0, 0, numbers, target, i, sum);
        }
        
        return ans;
    }
    
    static void dfs(int depth, int start, int[] numbers, int target, 
                    int n, int sum) {
        if (depth == n) {
            for (int i = 0; i < n; i++) {
                sum -= 2 * numbers[comb[i]];
            }
            
            if (sum == target) ans++;
            
            return;
        }
        
        for (int i = start; i < numbers.length; i++) {
            comb[depth] = i;
            dfs(depth+1, i+1, numbers, target, n, sum);
        }
    }
}