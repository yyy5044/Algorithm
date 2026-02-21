import java.util.*;
import java.io.*;

public class Solution {
	static int N;
	static int[][] map;
	static boolean[] visited;
	static int[] dr = {1,1,-1,-1}, dc = {1,-1,-1,1};
	static int maxDessert = -1;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());	
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			visited = new boolean[101];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int[] start = {i,j};
					dfs(i,j,0,start,0);
				}
			}
			
			sb.append("#").append(t+1).append(" ")
				.append(maxDessert).append("\n");
			
			maxDessert = -1;
		}
		
		System.out.println(sb);
	}

	private static void dfs(int r, int c, int d, int[] start, int dessertCnt) {
		if (r == start[0] && c == start[1] && dessertCnt > 0) {
			maxDessert = Math.max(maxDessert, dessertCnt);
			return;
		}
		
		for (int i = d; i < d+2; i++) {
			if (i>3) continue;
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1 || visited[map[nr][nc]]) continue;
			visited[map[nr][nc]] = true;
			dfs(nr, nc, i, start, dessertCnt+1);
			visited[map[nr][nc]] = false;
		}
	}
}
