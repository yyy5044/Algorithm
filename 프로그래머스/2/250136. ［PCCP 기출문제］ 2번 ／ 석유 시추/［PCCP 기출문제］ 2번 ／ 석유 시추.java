import java.io.*;
import java.util.*;

// 1. 덩어리를 BFS로 계산한다.
// 2. 덩어리에 기여한 열을 모두 정리한다. (minCol, maxCol 두 가지 값만 있으면 범위로 표현 가능)
// 3. 기여한 열에 1번에서 구한 덩어리 크기를 모두 더한다.
// 4. 덩어리마다 반복하면 해당 열에서 얻을 수 있는 석유 크기가 구해진다.

class Solution {
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,1,-1};
    static boolean[][] visited;
    
    public int solution(int[][] land) {
        // 석유가 1, 땅이 0
        int n = land.length; // 세로 길이
        int m = land[0].length; // 가로 길이
        
        visited = new boolean[n][m];
        int[] cols = new int[m]; // 특정 열에서 얻을 수 있는 석유의 양 정보
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] != 1 || visited[i][j]) continue;
                
                // bfs로 사이즈 구하기
                int[] result = bfs(n, m, i, j, land);
                int size = result[0];
                int minCol = result[1];
                int maxCol = result[2];
                
                // 기여한 열에 전부 size 더하기
                for (int k = minCol; k < maxCol+1; k++) cols[k] += size;
                // System.out.println(Arrays.toString(cols));
            }
        }
        
        int ans = 0;
        for (int i = 0; i < m; i++) ans = Math.max(ans, cols[i]);
        
        return ans;
          
    }
    
    static int[] bfs(int n, int m, int startR, int startC, int[][] land) {
        int count = 0;
        int minCol = startC;
        int maxCol = startC;
        
        Queue<int[]> dq = new ArrayDeque<>();
        visited[startR][startC] = true;
        dq.add(new int[]{startR, startC});
        
        while(!dq.isEmpty()) {
            int[] cur = dq.poll();
            minCol = Math.min(minCol, cur[1]);
            maxCol = Math.max(maxCol, cur[1]);
            count++;
            
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (land[nr][nc] != 1 || visited[nr][nc]) continue;
                visited[nr][nc] = true;
                dq.add(new int[]{nr, nc});
            }
        }
        
        int[] result = new int[] {count, minCol, maxCol};
        
        return result;
    }
    
    
}