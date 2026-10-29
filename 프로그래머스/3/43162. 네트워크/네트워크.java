import java.util.*;

class Solution {
    static boolean[] visited;
    static int N;
    
    public int solution(int n, int[][] computers) {
        // computers 자체가 인접 행렬이네?
        visited = new boolean[n];
        N = n;
        
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                bfs(i, computers);
                count++;
            }
        }
        
        return count;
    }
    
    static void bfs(int seed, int[][] computers) {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(seed);
        visited[seed] = true;
        
        while (!dq.isEmpty()) {
            int cur = dq.poll();
            
            for (int i = 0; i < N; i++) {
                if (!visited[i] && computers[cur][i] == 1) {
                    dq.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}