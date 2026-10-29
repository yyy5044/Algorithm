import java.util.*;

class Solution {
    static int N, M;
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,1,-1};
    
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        int answer = bfs(0,0,maps);
        return answer;
    }
    
    static int bfs(int start_r, int start_c, int[][] maps) {
        boolean[][] visited = new boolean[N][M];
        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{start_r, start_c, 1});
        visited[start_r][start_c] = true;
        
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            
            if (cur[0] == N-1 && cur[1] == M-1) {
                return cur[2];
            }
            
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                
                if (nr < 0 || nc < 0 || nr > N-1 || nc > M-1) continue;
                
                if (!visited[nr][nc] && maps[nr][nc] == 1) {
                    dq.add(new int[]{nr, nc, cur[2] + 1});
                    visited[nr][nc] = true;
                }
            }
        }
        
        return -1;
    }
}