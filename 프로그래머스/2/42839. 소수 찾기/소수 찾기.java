import java.io.*;
import java.util.*;

class Solution {
    static Set<Integer> set;
    static char[] arr;
    
    public int solution(String numbers) {
        // 1. 한 자리씩 떼어내서 배열에 담는다.
        // 2. 모든 경우의 수로 붙여본다.
        // 3. 완성이 되면 Set에 넣어본다.
        // 4. 전부 다 보면 Set에 있는 소수의 개수를 센다.
        set = new HashSet<>();
        
        arr = new char[numbers.length()];
        
        for (int i = 0; i < numbers.length(); i++) {
            arr[i] = numbers.charAt(i);
        }
        
        for (int i = 1; i <= numbers.length(); i++) {
            boolean[] visited = new boolean[arr.length];
            dfs(0, i, visited, "");
        }
        
        
        return set.size();
    }
    
    static void dfs(int depth, int n, boolean[] visited, String num) {
        if (depth == n) {
            int candidate = Integer.parseInt(num);
            
            if (candidate < 2) return;
            
            boolean isPrime = true;
            for (int i = 2; i < candidate; i++) {
                if (candidate % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            
            if (isPrime) {
                set.add(candidate);
            }
            
            return;
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, n, visited, num + arr[i]);
                visited[i] = false;
            }
        }
    }
}