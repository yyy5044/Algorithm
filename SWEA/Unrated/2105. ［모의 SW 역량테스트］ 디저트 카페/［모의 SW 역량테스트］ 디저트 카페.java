import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int[][] map;
	static int[] dr = {1,1,-1,-1}, dc = {1,-1,-1,1};
	static boolean[] visited = new boolean[101]; // 먹은 디저트
	static int maxDessert = -1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			

			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					int[] start = {r,c};
					dfs(start[0], start[1], 0, start, 0);
				}
			}
			
			sb.append("#").append(t).append(" ")
				.append(maxDessert).append("\n");
			
			maxDessert = -1;
		}
		
		System.out.println(sb);
	}
	
	private static void dfs(int r, int c, int d, int[] start, int cnt) {
		
		
		if (r == start[0] && c == start[1] && cnt>1) {
			maxDessert = Math.max(cnt, maxDessert);
			return;
		}
		
		for (int i = d; i < d+2; i++) { // 여기서는 방향 i로 쓰고 있다!!!
			if (i > 3) break;
			
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1 || visited[map[nr][nc]]) continue;
			
			// 안 먹은 디저트면
			visited[map[nr][nc]] = true;
			dfs(nr, nc, i, start, cnt+1);
			visited[map[nr][nc]] = false;
		}
		
		
	}
	
}

