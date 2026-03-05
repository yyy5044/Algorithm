import java.io.*;
import java.util.*;

public class Solution {
	static int N, kcalLimit;
	static int[][] ings;
	static boolean[] visited;
	static int[] comb;
	static int maxScore = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 재료 개수
			kcalLimit = Integer.parseInt(st.nextToken()); // 칼로리 제한
			
			ings = new int[N][2]; // [0]: 점수, [1]: 칼로리
			visited = new boolean[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				ings[i][0] = Integer.parseInt(st.nextToken());
				ings[i][1] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 1; i <= N; i++) {
				int M = i;
				comb = new int[M];
				dfs(0, 0, M);
			}
			
			sb.append("#").append(t).append(" ")
				.append(maxScore).append("\n");
			
			maxScore = Integer.MIN_VALUE;
		}
		
		System.out.println(sb);
	}
	
	private static void dfs(int depth, int start, int M) {
		if (depth == M) { // 조합 완성
			int totalKcal = 0; // 총 칼로리
			int totalScore = 0; // 총점
			for (int i = 0; i < M; i++) {
				int n = comb[i];
				totalKcal += ings[n][1];
				totalScore += ings[n][0];
			}
			
			if (kcalLimit >= totalKcal) { // 총 칼로리가 칼로리 제한을 넘지 않을 때만
				maxScore = Math.max(maxScore, totalScore); // 최고 점수 갱신 시도
			}
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			comb[depth] = i;
			dfs(depth+1, i+1, M);
			visited[i] = false;
		}
	}
}

