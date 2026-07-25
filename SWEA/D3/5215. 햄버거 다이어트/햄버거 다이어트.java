import java.io.*;
import java.util.*;

public class Solution {
    static int N, L;
    static int[][] ingredients;
    static int max;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            ingredients = new int[N][2]; // 점수, 칼로리
            max = Integer.MIN_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                ingredients[i][0] = Integer.parseInt(st.nextToken());
                ingredients[i][1] = Integer.parseInt(st.nextToken());
            }

            comb(0, 0, 0);

            sb.append("#").append(t).append(" ");
            sb.append(max).append("\n");
        }

        System.out.println(sb);
    }

    static void comb(int start, int totalScore, int totalKcal) {
        if (totalKcal > L) {
            return;
        } else {
            max = Math.max(totalScore, max);
        }

        for (int i = start; i < N; i++) {
            int score = ingredients[i][0];
            int kcal = ingredients[i][1];

            if (totalKcal < L) {
                comb(i+1, totalScore + score, totalKcal + kcal);
            }
        }

    }




}

