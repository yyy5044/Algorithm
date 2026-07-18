import java.io.*;
import java.util.*;

public class Solution {
    static int[][] positions;
    static boolean[] group;
    static long min;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int n = Integer.parseInt(br.readLine());
            positions = new int[n][2];
            group = new boolean[n];
            min = Long.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                positions[i][0] = Integer.parseInt(st.nextToken());
                positions[i][1] = Integer.parseInt(st.nextToken());
            }

            dfs(n, 0, 0);

            sb.append("#").append(t).append(" ");
            sb.append(min).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int n, int depth, int start) {
        if (depth == n/2) {
            long plusGroupX = 0;
            long plusGroupY = 0;
            long minusGroupX = 0;
            long minusGroupY = 0;
            long sum = 0;

            for (int i = 0; i < n; i++) {
                if (group[i]) {
                    plusGroupX += positions[i][0];
                    plusGroupY += positions[i][1];
                } else {
                    minusGroupX += positions[i][0];
                    minusGroupY += positions[i][1];
                }

                long tmpX = plusGroupX - minusGroupX;
                long tmpY = plusGroupY - minusGroupY;

                sum = tmpX*tmpX + tmpY*tmpY;

            }
            min = Math.min(min, sum);
            return;
        }

        for (int i = start; i < n; i++) {
            group[i] = true;
            dfs(n, depth+1, i+1);
            group[i] = false;
        }
    }

}

