import java.io.*;
import java.util.*;

class Solution {
    static int ans;
    static int T;
    static int[] nums;
    
    public int solution(int[] numbers, int target) {
        ans = 0;
        T = target;
        nums = numbers;
        
        dfs(0, 0);
            
        return ans;
    }
    
    static void dfs(int depth, int sum) {
        if (depth == nums.length) {
            if (sum == T) {
                ans++;
            }
            return;
        }
        
        dfs(depth+1, sum + nums[depth]);
        dfs(depth+1, sum - nums[depth]);
    }
}