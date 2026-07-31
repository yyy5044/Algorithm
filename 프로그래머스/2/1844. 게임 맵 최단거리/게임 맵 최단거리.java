import java.io.*;
import java.util.*;

class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public int solution(int[][] maps) {
        int N = maps.length;
        int M = maps[0].length;
        boolean[][] visited = new boolean[N][M];
        
        int[] start = new int[]{0, 0};
        
        Deque<int[]> q = new ArrayDeque<>();
        visited[start[0]][start[1]] = true;
        q.addLast(new int[]{start[0], start[1], 1});
        
        int ans = -1;
        while(!q.isEmpty()) {
            int[] pos = q.pollFirst();
            int r = pos[0];
            int c = pos[1];
            int length = pos[2];
            
            if (pos[0] == N-1 && pos[1] == M-1) {
                ans = length;
                break;
            }
            
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if (nr < 0 || nc < 0 || nr > N-1 || nc > M-1) continue;
                
                if (!visited[nr][nc] && maps[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    q.addLast(new int[] {nr, nc, length+1});
                }
            }
        }
        
        return ans;
    }
}