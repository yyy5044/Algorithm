import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

// 운영비용: K * K + (K - 1) * (K - 1)
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken()); // 집 하나가 지불 가능한 비용
			
			int[][] map = new int[n][n];
			ArrayList<int[]> houses = new ArrayList<>();
			int max = Integer.MIN_VALUE;
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						houses.add(new int[] {i, j});
					}
				}
			}
			
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					for (int i = 0; i < 2*n; i++) { // k 늘리면서
						int count = 0; // 범위 내 집 개수
						
						for (int j = 0; j < houses.size(); j++) { // 범위 내의 집 개수 세기
							int[] house = houses.get(j);
							
							if (Math.abs(r - house[0]) + Math.abs(c - house[1]) <= i-1) {
								count++;
							}
						}
						
						int cost = (i*i) + ((i-1) * (i-1));
						int budget = count * m;
						
						if (cost <= budget) {
							max = Math.max(max, count);
						}
					}
				}
			}
			
			sb.append("#").append(t).append(" ");
			sb.append(max);
			sb.append("\n");
		
		}
		System.out.println(sb);

	}
	
}
