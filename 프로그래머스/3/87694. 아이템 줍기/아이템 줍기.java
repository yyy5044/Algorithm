import java.util.*;

class Solution {
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,1,-1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] map = new int[102][102];
        
        for (int i = 0; i < rectangle.length; i++) {
            int x1 = 2*rectangle[i][0]; int y1 = 2*rectangle[i][1];
            int x2 = 2*rectangle[i][2]; int y2 = 2*rectangle[i][3];
            
            for (int y = y1; y <= y2; y++) {
                for (int x = x1; x <= x2; x++) {
                    map[y][x] = 1;
                }
            }
        }
        
        for (int i = 0; i < rectangle.length; i++) {
            int x1 = 2*rectangle[i][0]; int y1 = 2*rectangle[i][1];
            int x2 = 2*rectangle[i][2]; int y2 = 2*rectangle[i][3];
            
            for (int y = y1+1; y < y2; y++) {
                for (int x = x1+1; x < x2; x++) {
                    map[y][x] = 0;
                }
            }
        }
        
        boolean[][] visited = new boolean[102][102];
        Deque<int[]> dq = new ArrayDeque<>();
        int startX = 2*characterX; int startY = 2*characterY;
        int destX = 2*itemX; int destY = 2*itemY;
        dq.add(new int[]{startX, startY, 1});
        visited[startY][startX] = true;
        
        int answer = 0;
        while(!dq.isEmpty()) {
            int[] cur = dq.poll();
            
            if (cur[0] == destX && cur[1] == destY) {
                answer = cur[2];
                break;
            }
            
            for (int d = 0; d < 4; d++) {
                int nr = cur[1] + dr[d];
                int nc = cur[0] + dc[d];
                
                if (nr < 0 || nc < 0 || nr > map.length - 1 || nc > map[0].length - 1) continue;
                if (!visited[nr][nc] && map[nr][nc] == 1) {
                    dq.add(new int[] {nc, nr, cur[2] + 1});
                    visited[nr][nc] = true;
                }
            }
        }
        
        for (int i = 14; i > 0; i--) {
            for (int j = 0; j < 15; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        
        return answer / 2;
    }
}