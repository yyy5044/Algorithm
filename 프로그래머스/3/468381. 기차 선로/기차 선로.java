import java.util.*;

class Solution {
    static int N, M, count; // count: 가능한 연결 수
    static int[][] g; // grid 복사
    static int[] dr = {1, 0, -1, 0}; // 하, 우, 상, 좌
    static int[] dc = {0, 1, 0, -1};

    static boolean[][] open = {
        {false, false, false, false}, 
        {false, true, false, true}, // 1 우좌
        {true, false, true, false}, // 2 하상
        {true, true, true, true}, // 3 상하좌우
        {false, false, true, true}, // 4 상좌
        {false, true, true, false}, // 5 상우
        {true, true, false, false}, // 6 하우
        {true, false, false, true}, // 7 하좌
    };
    static boolean[][][] visited; // [r][c][진입방향]
    static boolean[][] preset; // 처음부터 놓여 있던 레일
    
    public int solution(int[][] grid) {
        g = grid;
        N = g.length;
        M = g[0].length;
        visited = new boolean[N][M][4];
        preset = new boolean[N][M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                preset[r][c] = g[r][c] > 0;
            }
        }
        
        visited[0][0][1] = true; // (0,0,1) 출발 칸 방문 처리
        dfs(0,1,1); // (0,1)에 오른쪽 방향으로 진입
        return count;
    }
    
    static void dfs(int r, int c, int d) {
        // 진입했더니 맵 밖으로 이탈 -> 실패
        if (r < 0 || c < 0 || r >= N || c >= M) return;
        // 진입했더니 장애물 -> 실패
        if (g[r][c] == -1) return;
        // 같은 상태 재방문 -> 실패 (무한루프 방지)
        if (visited[r][c][d]) return;
        
        int in = (d + 2) % 4; // 이 칸을 어느 방향에서 들어왔는지
        
        boolean empty;
        if (g[r][c] == 0) empty = true;
        else empty = false;
        int from = (!empty) ? g[r][c] : 1; // 빈칸: 전부 후보
        int to   = (!empty) ? g[r][c] : 7; // 이미 레일이 있을 때는 후보가 한 개
        
        // 후보를 하나씩 시도
        for (int k = from; k <= to; k++) {
            if (!open[k][in]) continue; // 전 레일이 열린 방향이랑 후보 레일이 열린 방향이랑 안 맞으면 스킵
            
            if (empty) g[r][c] = k;
            visited[r][c][d] = true; // (r,c)로 d 방향으로 들어간 시도는 이미 했다고 마킹
            
            if (r == N-1 && c == M-1) {
              if (valid()) count++;  
            } else {
                // #(십자)는 직진, 나머지는 들어온 방향 제외 열린 곳으로
                int out = (k == 3) ? d : other(k, in);
                dfs(r + dr[out], c + dc[out], out);
            }
            
            if (empty) g[r][c] = 0;
            visited[r][c][d] = false;
            
        }
    }
    
    static int other(int k, int in) {
        for (int x = 0; x < 4; x++) {
            if (x != in && open[k][x]) return x;
        }
        return -1;
    }
    
    static boolean valid() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                // 원래 있던 레일을 안 지났으면 실패
                if (preset[r][c] && !anyVisited(r, c)) return false;
                // #으로 지나갔는데 알고보니 어느 방향이 막힌 #을 썼다면 무효
                if (g[r][c] == 3) {
                    for (int x = 0; x < 4; x++) {
                        int nr = r + dr[x];
                        int nc = c + dc[x];
                        // #의 한 면이라도 맵 밖이라면 무효
                        if (nr < 0 || nc < 0 || nr >= N || nc >= M) return false;
                        // #랑 인접한데 이어져있지 않은 레일이 있으면 무효
                        int nk = g[nr][nc];
                        if (nk <= 0 || !open[nk][(x + 2) % 4]) return false;
                    }
                }
            }
        }
        return true;
    }
    
    static boolean anyVisited(int r, int c) {
        // 어느 방향으로던 한 번이라도 이 레일을 지났었다면
        for (int x = 0; x < 4; x++) {
            if (visited[r][c][x]) return true;
        }
        
        return false;
    }
    
}