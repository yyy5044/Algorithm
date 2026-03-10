import java.io.*;
import java.util.*;

public class Solution {
	static int[][] memo;
	static int N;
	static int[] start, end;
	static int[][] customers;
	static boolean[] visited;
	static int minPath = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine()); // 고객 수
			StringTokenizer st = new StringTokenizer(br.readLine());
			start = new int[2];
			end = new int[2];
			visited = new boolean[N];
			memo = new int[11][11];
			
			// 시작 위치: 회사
			start[0] = Integer.parseInt(st.nextToken());
			start[1] = Integer.parseInt(st.nextToken());
			
			// 도착 위치: 집
			end[0] = Integer.parseInt(st.nextToken());
			end[1] = Integer.parseInt(st.nextToken());
			
			// 고객 위치
			customers = new int[N][2];
			for (int i = 0; i < N; i++) {
				customers[i][0] = Integer.parseInt(st.nextToken());
				customers[i][1] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					memo[i][j] = Math.abs(customers[i][0] - customers[j][0])
									+ Math.abs(customers[i][1] - customers[j][1]);
					memo[j][i] = memo[i][j];
				}
			}
			
			for (int i = 0; i < N; i++) { 
				int sum = Math.abs(start[0] - customers[i][0])
							+ Math.abs(start[1] - customers[i][1]); // 집-첫번째고객
				visited[i] = true;
				dfs(0, sum, i);
				visited[i] = false;
			}
			
			sb.append("#").append(t).append(" ");
			sb.append(minPath).append("\n");
			
			minPath = Integer.MAX_VALUE;
		}
		
		System.out.println(sb);
	}
		
	private static void dfs(int depth, int sum, int j) { // j: 전에 고른 고객
		if (sum > minPath) return;
		
		if (depth == N-1) {
			sum += Math.abs(end[0] - customers[j][0])
					+ Math.abs(end[1] - customers[j][1]); // 마지막고객-집
			
			minPath = Math.min(sum, minPath);
			return;
		}
		
		for (int i = 0; i < N; i++) { // i: 현재 고른 고객
			if (visited[i]) continue;
			
			visited[i] = true;
			dfs(depth+1, sum + memo[i][j], i);
			visited[i] = false;
		}
	}
}