import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			int[][] map = new int[n][n];
			int x = n / 2;
			int y = n / 2;
			
			int sum = 0;
			
			for (int i = 0; i < n; i++) {
				String line = br.readLine();
				for (int j = 0; j < n; j++) {
					map[i][j] = line.charAt(j) - '0';
					
					if (Math.abs(x - i) + Math.abs(y - j) <= n/2) {
						sum += map[i][j];
					}
				}
			}
			
			sb.append("#").append(t).append(" ");
			sb.append(sum);
			sb.append("\n");
		
		}
		System.out.println(sb);

	}
	
}
