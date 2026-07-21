import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int[][] positions;
    static boolean[] group;
    static long min;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            positions = new int[N][2];
            group = new boolean[N];
            min = Long.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                positions[i][0] = Integer.parseInt(st.nextToken());
                positions[i][1] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);

            sb.append("#").append(t).append(" ");
            sb.append(min).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int depth, int start) {
        if (depth == N/2) {
            long sumX = 0;
            long sumY = 0;
            for (int i = 0; i < N; i++) {
                if (group[i]) {
                    sumX += positions[i][0];
                    sumY += positions[i][1];
                } else {
                    sumX -= positions[i][0];
                    sumY -= positions[i][1];
                }
            }

            long vector = sumX*sumX + sumY*sumY;

            min = Math.min(min, vector);

            return;
        }

        for (int i = start; i < N; i++) {
            group[i] = true;
            dfs(depth+1, i+1);
            group[i] = false;
        }
    }

}

