import java.io.*;
import java.util.*;

class Solution {
    static int N;
    static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        N = n;
        visited = new boolean[N];
        
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                bfs(i, computers);
                ans++;
            }
        }
        
        return ans;
    }
    
    //    0 1 2
    // 0| 1 1 0
    // 1| 1 1 0
    // 2| 0 0 1
    
    static void bfs(int startCom, int[][] computers) {
        Deque<Integer> q = new ArrayDeque<>();
        visited[startCom] = true;
        q.addLast(startCom);
        
        while (!q.isEmpty()) {
            int computer = q.pollFirst();
            
            for (int i = 0; i < N; i++) {
                if (i == computer) continue; // 자기자신은 스킵
                
                if (!visited[i] && computers[computer][i] == 1) {
                    visited[i] = true;
                    q.addLast(i);
                }
            }
        }
    }
}