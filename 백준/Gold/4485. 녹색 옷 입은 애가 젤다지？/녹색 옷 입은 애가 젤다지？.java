import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = 1;
		while(true) {
			final int INF = Integer.MAX_VALUE;
			
			int N = Integer.parseInt(br.readLine());
			
			if (N==0) break;
			
			int[][] map = new int[N][N]; // 격자 그래프
			boolean[][] visited = new boolean[N][N];
			int[][] minDist = new int[N][N];
			PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> {
				return Integer.compare(e1[2], e2[2]);
			});
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					minDist[r][c] = INF;
				}
			}
			
			minDist[0][0] = map[0][0];
			
			visited[0][0] = true;
			pq.add(new int[] {0,0, minDist[0][0]});
			
			while(!pq.isEmpty()) {
				int[] cur = pq.poll();
				int r = cur[0];
				int c = cur[1];
				int min = cur[2];
				
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1 || visited[nr][nc]) continue;
					
					if (minDist[nr][nc] > min + map[nr][nc]) {
						minDist[nr][nc] = min + map[nr][nc];
						visited[nr][nc] = true;
						pq.add(new int[] {nr, nc, minDist[nr][nc]});
					}
				}
			}
			
			sb.append("Problem ").append(tc).append(": ").append(minDist[N-1][N-1]).append("\n");
			tc++;
		}
		
		System.out.println(sb);
	}
	
}