import java.io.*;
import java.util.*;

class Solution {
    static char[] nums;
    static Set<Integer> set;
    
    public int solution(String numbers) {
        set = new HashSet<>();
        nums = new char[numbers.length()];
        for (int i = 0; i < numbers.length(); i++) {
            nums[i] = numbers.charAt(i);
        }
        
        for (int n = 1; n <= numbers.length(); n++) {
            boolean[] visited = new boolean[numbers.length()];
            dfs(0, n, visited, "");
        }
        
        return set.size();
    }
    
    static void dfs(int depth, int n, boolean[] visited, String b) {
        if (depth == n) {
            int number = Integer.parseInt(b);
            
            if (number < 2) return;
            
            for (int i = 2; (long) i*i <= number; i++) {
                if (number % i == 0) return;
            }
            
            set.add(number);
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth+1, n, visited, b+nums[i]);
                visited[i] = false;
            }
        }
    }
}