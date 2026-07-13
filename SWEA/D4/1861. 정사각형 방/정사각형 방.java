import java.util.*;
import java.io.*;

public class Solution {
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static HashMap<Integer, Integer> memo = new HashMap<>();

    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        StringBuilder sb = new StringBuilder();

        st.nextToken();
        int T = (int) st.nval;

        for (int t = 1; t <= T; t++){
            memo.clear();
            st.nextToken();
            int n = (int) st.nval;
            int[][] map = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    st.nextToken();
                    map[i][j] = (int) st.nval;
                }
            }

            int max = Integer.MIN_VALUE;
            int number = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int result = dfs(n, i, j, map);

                    if (max == result && number > map[i][j]) {
                        number = map[i][j];
                    }
                    if (max < result) {
                        max = result;
                        number = map[i][j];
                    }
                }
            }

            sb.append("#").append(t).append(" ");
            sb.append(number).append(" ").append(max);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int dfs(int n, int r, int c, int[][] map) {

        if (memo.containsKey(map[r][c])) {
            return memo.get(map[r][c]);
        }

        int best = 1;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nc < 0 || nr > n - 1 || nc > n - 1) continue;

            if (map[r][c] + 1 == map[nr][nc]) {
                best = best + dfs(n, nr, nc, map);
            }
        }

        memo.put(map[r][c], best);
        return best;
    }
}