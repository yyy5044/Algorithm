import java.io.*;
import java.util.*;

public class Solution {
    static int N, K;
    static int[][] map;
    static int highest;
    static boolean[][] visited;
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,1,-1};
    static int max;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            max = Integer.MIN_VALUE;
            highest = Integer.MIN_VALUE;
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    highest = Math.max(map[i][j], highest);
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == highest) {
                        dfs(new int[] {i, j}, 1, false);
                    }
                }
            }

            sb.append("#").append(t).append(" ");
            sb.append(max).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int[] start, int length, boolean construction) {
        visited[start[0]][start[1]] = true;

        max = Math.max(length, max);

        for (int d = 0; d < 4; d++) {
            int nr = start[0] + dr[d];
            int nc = start[1] + dc[d];

            if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1 || visited[nr][nc]) continue;

            if (map[start[0]][start[1]] > map[nr][nc]) { // 낮으면 그냥
                dfs(new int[]{nr, nc}, length+1, construction);
            } else if (!construction && map[nr][nc] - K < map[start[0]][start[1]]){
                int tmp = map[nr][nc];
                map[nr][nc] = map[start[0]][start[1]] - 1;
                dfs(new int[] {nr, nc}, length+1, true);
                map[nr][nc] = tmp;
            }
        }

        visited[start[0]][start[1]] = false;
    }
}

