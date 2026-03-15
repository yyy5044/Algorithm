import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 도시 크기
			int M = Integer.parseInt(st.nextToken()); // 집 하나 당 예산
			
			ArrayList<int[]> homes = new ArrayList<int[]>();
			
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) homes.add(new int[] {i, j});
				}
				// System.out.println(Arrays.toString(map[i]));
			}
			
			int maxHome = Integer.MIN_VALUE;
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) { // centerX == c, centerY = r
					for (int k = 1; k <= 21; k++) {
						int count = 0;
						for (int i = 0; i < homes.size(); i++) {
							int[] cur = homes.get(i);
							int x = cur[1];
							int y = cur[0];
							if (Math.abs(r-y) + Math.abs(c-x) < k) {
								count++;
							}
						}
						
						int manage_price = (k*k) + ((k-1)*(k-1));
						
						if (manage_price <= M*count) {
							maxHome = Math.max(maxHome, count);
						}
					}
				}
			}
			
			sb.append("#").append(t).append(" ");
			sb.append(maxHome).append("\n");
			
		}

		System.out.println(sb);

	}
}
