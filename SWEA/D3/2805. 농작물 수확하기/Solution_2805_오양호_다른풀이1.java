import java.util.*;
import java.io.*;

public class Solution {
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());	
		for (int t = 0; t < T; t++) {		
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			for(int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j) - '0';
				}
			}
			
			int ans = bfs(map);
			
			sb.append("#").append(t+1).append(" ")
				.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}

	public static int bfs(int[][] map) {
		int N = map.length;
		int width = N/2;
		boolean[][] visited = new boolean[N][N];
		
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.add(new int[] {width, width, 0}); // 좌표(r,c)와 중심점으로부터의 거리
		visited[width][width] = true;
		int totalProfit = map[width][width];
		
		while(!q.isEmpty()) {
			int[] unbox = q.poll();
			int r = unbox[0];
			int c = unbox[1];
			int distance = unbox[2];
			
			if (distance >= width) continue; // 중심으로부터 거리가 너비에 달한다면 더 갈 필요 없음	
			
			for(int d = 0; d < 4; d++) { // 4방 탐색
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr >= 0 && nc >= 0 && nr <= N-1 && nc <= N-1) {
					if (visited[nr][nc]) continue; // 다음 갈 곳이 이미 방문했다면 skip
					
					// 방문 안 했다면 큐에 좌표, 거리 더해서 삽입
					q.add(new int[] {nr, nc, distance+1});
					visited[nr][nc] = true;
					
					// 이익도 더해주기
					totalProfit += map[nr][nc];
				}
			}
		}
		
		return totalProfit;
	}
	
}
