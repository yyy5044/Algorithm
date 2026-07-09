import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int[][] S = new int[n+1][n+1];
			
			for (int i = 1; i < n+1; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j < n+1; j++) {
					S[i][j] = Integer.parseInt(st.nextToken()) + S[i-1][j] + S[i][j-1] - S[i-1][j-1];
				}
			}
			
			int max = Integer.MIN_VALUE;
			for (int i = m; i < n+1; i++) {
				for (int j = m; j < n+1; j++) {
					int sum = 0;
					sum = S[i][j] - S[i-m][j] - S[i][j-m] + S[i-m][j-m];
					max = Math.max(max, sum);
				}
			}
			
			sb.append("#").append(t).append(" ");
			sb.append(max).append("\n");
			
		}

		System.out.println(sb);

	}
	
}
