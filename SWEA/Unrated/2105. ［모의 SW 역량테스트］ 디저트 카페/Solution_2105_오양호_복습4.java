import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int[][] map;
	static int[] dr = {1,1,-1,-1};
	static int[] dc = {1,-1,-1,1};
	static boolean[] visited;
	static int maxDessert;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[101]; // 먹은 디저트 체크용
			
			maxDessert = -1;
			
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
				// System.out.println(Arrays.toString(map[r]));
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int[] start = {i, j};
					dfs(start[0], start[1], 0, start, 0);
				}
			}
			
			sb.append("#").append(t).append(" ");
			sb.append(maxDessert).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void dfs(int r, int c, int dir, int[] start, int dessertCnt) {
		if (r == start[0] && c == start[1] && dessertCnt > 1) {
			maxDessert = Math.max(maxDessert, dessertCnt);
			return;
		}
		
		for (int d = dir; d < dir+2; d++) {
			if (d>3) continue; // 인덱스 방어
			
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1 || visited[map[nr][nc]]) continue;
			
			visited[map[nr][nc]] = true;
			dfs(nr, nc, d, start, dessertCnt+1);
			visited[map[nr][nc]] = false;
		}
	}
}
