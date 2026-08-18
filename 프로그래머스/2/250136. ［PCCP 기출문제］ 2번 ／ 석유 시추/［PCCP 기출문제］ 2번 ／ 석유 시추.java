import java.io.*;
import java.util.*;

class Solution {
    static int N, M;
    static boolean[][] visited;
    
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        
        visited = new boolean[N][M];
        
        int[] cols = new int[M];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] || land[i][j] == 0) continue;
                
                int[] result = bfs(i, j, land);
                int size = result[0], minCol = result[1], maxCol = result[2];
                for (int k = minCol; k <= maxCol; k++) {
                    cols[k] += size;
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i < M; i++) {
            ans = Math.max(ans, cols[i]);
        }
        
        return ans;
    }
    
    static int[] bfs(int startR, int startC, int[][] land) {
        int size = 0;
        int minCol = startC;
        int maxCol = startC;
        
        Deque<int[]> dq = new ArrayDeque<>();
        visited[startR][startC] = true;
        dq.add(new int[] {startR, startC});
        
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            size++;
            minCol = Math.min(minCol, cur[1]);
            maxCol = Math.max(maxCol, cur[1]);
            
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (visited[nr][nc] || land[nr][nc] == 0) continue;
                
                visited[nr][nc] = true;
                dq.add(new int[] {nr, nc});
            }
        }
        
        int[] result = new int[] {size, minCol, maxCol};
        
        return result;
    }
}