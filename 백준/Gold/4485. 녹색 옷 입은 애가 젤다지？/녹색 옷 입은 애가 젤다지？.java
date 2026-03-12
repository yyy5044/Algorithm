import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int tc = 1;
		while(true) {
			int N = Integer.parseInt(br.readLine());
			
			if (N==0) break;
			
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			final int INF = Integer.MAX_VALUE;
			
			boolean[][] visited = new boolean[N][N];
			int[][] minDist = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				Arrays.fill(minDist[i], INF);
			}
			
			minDist[0][0] = map[0][0];
			
			for (int r = 0; r < N; r++) {
				boolean exit = false;
				for (int c = 0; c < N; c++) { // 모든 정점에 대하여
					int[] cur = {-1, -1}; 
					int min = INF;
					
					for (int i = 0; i < N; i++) {
						for (int j = 0; j < N; j++) {
							if (!visited[i][j] && min > minDist[i][j]) {
								min = minDist[i][j];
								cur = new int[] {i, j};
							}
						}
					}
					
					if (cur[0] == -1 && cur[1] == -1) {
						exit = true;
						break;
					}
					
					visited[cur[0]][cur[1]] = true; // 여기까진 맞는듯?
					
					
					for (int d = 0; d < 4; d++) {
						int nr = cur[0] + dr[d];
						int nc = cur[1] + dc[d];
						
						if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1) continue;
						
						if(!visited[nr][nc] && minDist[nr][nc] > min + map[nr][nc]) {
							minDist[nr][nc] = min + map[nr][nc];
						}
					}
				}
				if (exit) break;
			}
			
			sb.append("Problem ").append(tc).append(": ").append(minDist[N-1][N-1]).append("\n");
			tc++;
		}
		
		System.out.println(sb);
	}
	
}