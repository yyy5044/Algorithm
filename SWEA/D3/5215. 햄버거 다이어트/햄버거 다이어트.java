import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static int N, L;
    static int[][] indigrients;
    static int max;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            indigrients = new int[N][2];
            max = Integer.MIN_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                indigrients[i][0] = Integer.parseInt(st.nextToken());
                indigrients[i][1] = Integer.parseInt(st.nextToken());

                // System.out.println(Arrays.toString(indigrients[i]));
            }

            comb(0, 0, 0);


            sb.append("#").append(t).append(" ");
            sb.append(max).append("\n");
        }

        System.out.println(sb);
    }

    static void comb(int start, int totalKcal, int totalScore) {
        if (totalKcal <= L) max = Math.max(totalScore, max);

        if (totalKcal > L) {
            return;
        }

        for (int i = start; i < N; i++) {
            int score = indigrients[i][0];
            int kcal = indigrients[i][1];

            comb(i+1, totalKcal+kcal, totalScore+score);
        }
    }


}

