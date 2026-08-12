import java.io.*;
import java.util.*;

class Solution {
    static int N;
    static int count;
    
    public int solution(int[] numbers, int target) {
        N = numbers.length;
        count = 0;
        
        dfs(0, target, 0, numbers);
        
        return count;
    }
    
    static void dfs(int depth, int t, int num, int[] numbers) {
        if (depth == N) {
            if (num == t) {
                count++;
            }
            
            return;
        }
        
        dfs(depth+1, t, num+numbers[depth], numbers);
        dfs(depth+1, t, num-numbers[depth], numbers);
    }
}