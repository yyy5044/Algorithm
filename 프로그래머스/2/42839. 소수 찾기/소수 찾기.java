import java.io.*;
import java.util.*;

class Solution {
    static boolean[] visited;
    static char[] arr;
    static Set<Integer> set;
    
    public int solution(String numbers) {
        visited = new boolean[numbers.length()];
        arr = new char[numbers.length()];
        for (int i = 0; i < numbers.length(); i++) {
            arr[i] = numbers.charAt(i);
        }
        set = new HashSet<>();
        
        for (int i = 1; i <= numbers.length(); i++) {
            dfs(0, "", i);
        }
        
        return set.size();
    }
    
    static void dfs(int depth, String str, int n) {
        if (depth == n) {
            int num = Integer.parseInt(str);
            
            if (num == 0 || num == 1) return;
            
            for (int i = 2; i*i <= num; i++) {
                if (num % i == 0) return;
            }
            
            set.add(num);
            return;
        }
        
        for (int i = 0; i < arr.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(depth+1, str+arr[i], n);
                visited[i] = false;
            }
        }
    }
}