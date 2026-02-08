import java.util.*;
import java.io.*;

public class Solution {
	static int[][] map;
	static int[][] mapMax;
	static int[] window;
	static int N;
	static int M;
	static int C;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			mapMax = new int[N][N - M + 1];
			window = new int[M];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int r = 0; r < N; r++) {
				for (int i = 0; i < N-M+1; i++) {
					
					for (int j = i, windowIdx = 0; j < M + i; j++, windowIdx++) {
						window[windowIdx] = map[r][j];
					}
					subset(0, 0, 0, r, i);
				}
			}
			
			int maxProfit = 0;
			for (int r1 = 0; r1 < N; r1++) {
				for (int i1 = 0; i1 < N-M+1; i1++) {
					for (int r2 = r1; r2 < N; r2++) {
						int start = (r1==r2)? (i1+(N-M+1)) : 0;
						for (int i2 = start; i2 < N-M+1; i2++) {
							if (maxProfit < mapMax[r1][i1] + mapMax[r2][i2]) {
								maxProfit = mapMax[r1][i1] + mapMax[r2][i2];
							}
						}
					}

				}
			}
			
			sb.append("#").append(t+1).append(" ").append(maxProfit).append("\n");

			// for (int i = 0; i < N; i++) System.out.println(Arrays.toString(mapMax[i]));
		}
		

		
		System.out.println(sb);
	}
	
	public static void subset(int depth, int sum, int powSum, int i, int j) {
		
		if (depth == M) {
			if (mapMax[i][j] < powSum && sum <= C) {
				mapMax[i][j] = powSum;
			}
			return;
		}
		
		subset(depth + 1, sum + window[depth], powSum + (window[depth]*window[depth]), i, j);
		subset(depth + 1, sum, powSum, i, j);
		
	}
}
