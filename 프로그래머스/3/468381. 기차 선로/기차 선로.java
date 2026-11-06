import java.util.*;

class Solution {
    static int N, M, count;
    static int[][] g;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static boolean[][] open = {
        {false, false, false, false}, // 상좌하우
        {false, true, false, true}, // 1
        {true, false, true, false}, // 2
        {true, true, true, true},   // 3
        {true, true, false, false}, // 4
        {true, false, false, true}, // 5
        {false, false, true, true}, // 6
        {false, true, true, false}, // 7
    };
    
    static boolean[][][] visited;
    static boolean[][] preset;
    
    public int solution(int[][] grid) {
        g = grid;
        count = 0;
        N = grid.length; M = grid[0].length;
        visited = new boolean[N][M][4]; // 들어온 방향까지 고려 (r, c, d)
        preset = new boolean[N][M];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) preset[i][j] = grid[i][j] > 0;
        
        visited[0][0][3] = true;
        dfs(0, 1, 3);
        
        return count;
    }
    
    static void dfs(int r, int c, int d) {
        if (r < 0 || c < 0 || r >= N || c >= M) return; // 맵 밖이면 취소
        if (g[r][c] == -1) return; // 장애물이면 취소
        if (visited[r][c][d]) return; // 같은 칸에 같은 방향으로 들어오면 무한순환
        
        int in = (d + 2) % 4; // 여기 어디로 들어왔니
        
        boolean empty; // 여기 빈 칸인가요
        if (g[r][c] != 0) empty = false;
        else empty = true;
        
        int from = !empty ? g[r][c] : 1;
        int to   = !empty ? g[r][c] : 7;
        for (int k = from; k <= to; k++){ // 레일을 놓아보자
            if (!open[k][in]) continue; // 전 칸의 레일과 맞지 않으면 스킵
            
            int out = (k==3) ? d : other(k, in); // # 레일은 직진
            visited[r][c][d] = true;
            
            if (empty) {
                g[r][c] = k; // 비어있을 때 레일 두기
            }
            
            if (r == N-1 && c == M-1) {
                if (valid()) count++;
            } else {
                int nr = r + dr[out]; int nc = c + dc[out];
                dfs(nr, nc, out);
            }
            visited[r][c][d] = false;
            if (empty) {
                g[r][c] = 0; // 레일 치우기
            }
        }
    }
    
    static int other(int k, int in){
        for (int x = 0; x < 4; x++) {
            if (x != in && open[k][x]) return x;
        }
        return -1;
    }
    
    static boolean valid() { // 시뮬레이션이 못 잡는 케이스 잡기
        // 1. 3번 레일 사방 검사
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (g[i][j] == 3) {
                    for (int d = 0; d < 4; d++) {
                        int nr = i + dr[d]; int nc = j + dc[d];
                        if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                            return false; // 사방 중 하나라도 밖이면 무효
                        }
                        if (g[nr][nc] <= 0) {
                            return false; // 사방 중 하나라도 장애물이면 또는 빈칸이면 무효
                        }
                        if (!open[g[nr][nc]][(d + 2) % 4]) {
                            return false; // #과 이웃한 레일이 #과 연결되어 있지 않으면 무효
                        }
                    }
                }
            }
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                boolean anyVisited = false;
                for (int d = 0; d < 4; d++) {
                    if (visited[i][j][d]) {
                        anyVisited = true;
                        break;
                    }
                }
                // 원래 레일이 있던 곳을 방문한 적이 없다? -> 무효
                if (preset[i][j] && !anyVisited) return false;
            }
        }
        
        return true;
    }
}