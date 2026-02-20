import java.io.*;
import java.util.*;

public class Solution {
	static int N, maxDessert = -1;
	static int[][] map;
	static boolean[] visited = new boolean[101];
	static int[] dr = {1,1,-1,-1}, dc = {1,-1,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int[] start = {i,j};
					dfs(i,j,0,0,start);
				}
			}
			
			sb.append("#").append(t+1).append(" ");
			sb.append(maxDessert).append("\n");
			
			maxDessert = -1;
		}	
		
		System.out.println(sb);
	}
	
	private static void dfs(int r, int c, int d, int dessertCnt, int[] start) {
		if (r==start[0] && c==start[1] && dessertCnt>0) {
			maxDessert = Math.max(maxDessert, dessertCnt);
			return;
		}
		
		for (int i = d; i < d+2; i++) {
			if (d>3) continue;
			
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nc < 0 || nr>N-1 || nc>N-1 || visited[map[nr][nc]]) continue;
			visited[map[nr][nc]] = true;
			dfs(nr, nc, i, dessertCnt+1, start);
			visited[map[nr][nc]] = false;
		}
		
	}
}
