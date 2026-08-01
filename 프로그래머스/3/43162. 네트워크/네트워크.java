import java.io.*;
import java.util.*;

//    0 1 2
// 0| 1 1 0
// 1| 1 1 0
// 2| 0 0 1

class Solution {
    static boolean[] visited;
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(i, n, computers);
                ans++;
            }
        }
        
        return ans;
    }
    
    static void bfs(int start, int n, int[][] computers) {
        Deque<Integer> q = new ArrayDeque<>();
        visited[start] = true;
        q.add(start);
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            for (int i = 0; i < n; i++) {
                if (!visited[i] && computers[cur][i] == 1) {
                    visited[i] = true;
                    q.add(i);
                }
            }
 
        }
    }
}