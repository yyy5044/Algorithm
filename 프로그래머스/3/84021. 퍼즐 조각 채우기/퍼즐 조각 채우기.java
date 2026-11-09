import java.util.*;

class Solution {
    static int N;
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,1,-1};
    
    public int solution(int[][] game_board, int[][] table) {
        N = game_board.length;
        List<List<int[]>> blanks = extract(game_board, 0);
        List<List<int[]>> pieces = extract(table, 1);
        
        boolean[] used = new boolean[pieces.size()];
        
        int sum = 0;
        outer:
        for (List<int[]> blank : blanks) {
            String nb = normalize(blank);
            boolean matched = false;
            for (int i = 0; i < pieces.size(); i++) {
                if (used[i]) continue;
                List<int[]> piece = pieces.get(i);
                for (int d = 0; d < 4; d++) {
                    List<int[]> rp = rotate(d, piece);
                    if (nb.equals(normalize(rp))) {
                        sum += pieces.get(i).size();
                        used[i] = true;
                        matched = true;
                        break;
                    }                    
                }
                if (matched) break;
            }
        }
        
        return sum;
    }
    
    static List<int[]> rotate(int dir, List<int[]> block) {
        List<int[]> result = block;
        
        for (int d = 0; d < dir; d++) {
            List<int[]> tmp = result;
            List<int[]> cur = new ArrayList<>();
            for (int i = 0; i < block.size(); i++) {
                cur.add(new int[]{tmp.get(i)[1], (-1) * tmp.get(i)[0]});
            }
            result = cur;
        }
        
        return result;
    }
    
    static String normalize(List<int[]> block) {
        int minR = Integer.MAX_VALUE; int minC = Integer.MAX_VALUE; 
        for (int i = 0; i < block.size(); i++) {
            int[] b = block.get(i);
            minR = Math.min(minR, b[0]);
            minC = Math.min(minC, b[1]);
        }
        List<int[]> tmp = new ArrayList<>();
        for (int i = 0; i < block.size(); i++) {
            int[] b = block.get(i);
            tmp.add(new int[]{b[0] - minR, b[1] - minC});
        }
        
        Collections.sort(tmp, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            else return a[1] - b[1];
        });
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tmp.size(); i++) {
            int[] t = tmp.get(i);
            result.append(t[0]).append(",").append(t[1]).append("/");
        }
        
        return result.toString();
    }
    
    static List<List<int[]>> extract(int[][] grid, int value) {
        List<List<int[]>> result = new ArrayList<>();
        boolean[][] visited = new boolean[N][N];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] || grid[i][j] != value) continue;
                List<int[]> block = new ArrayList<>();
                Deque<int[]> dq = new ArrayDeque<>();
                
                dq.add(new int[]{i, j});
                visited[i][j] = true;
                
                while(!dq.isEmpty()) {
                    int[] cur = dq.poll();
                    block.add(cur);
                    
                    for (int d = 0; d < 4; d++) {
                        int nr = cur[0] + dr[d]; int nc = cur[1] + dc[d];
                        
                        if (nr<0 || nc<0 || nr>=N || nc>=N) continue;
                        if (visited[nr][nc] || grid[nr][nc] != value) continue;
                        dq.add(new int[]{nr,nc});
                        visited[nr][nc] = true;
                    }
                }
                
                result.add(block);
            }
        }
        
        return result;
    }
}