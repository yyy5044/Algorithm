import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			int maxCount = 1;
			for (int x = 1; x < 100; x++) {
				int count = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (!visited[i][j] && map[i][j] > x) {
							dfs(i, j, x);
							count++;
						}
					}
				}
				maxCount = Math.max(maxCount, count);
				visited = new boolean[N][N];
			}
			
			sb.append("#").append(t+1).append(" ")
				.append(maxCount).append("\n");
		}	
		
		System.out.println(sb);
	}
	
	public static void dfs(int r, int c, int x) {
		visited[r][c] = true; // 나 여기 왔다. 표시
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nc < 0|| nr > N-1 || nc > N-1 || visited[nr][nc]) continue;
			
			if (map[nr][nc] > x) { // 요정이 갉아 먹지 않았다면 이동

				dfs(nr, nc, x);
			}
		}
	}
}
