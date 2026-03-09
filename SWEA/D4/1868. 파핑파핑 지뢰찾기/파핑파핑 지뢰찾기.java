import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,1,0,0,-1,1,1,-1}, dc = {0,0,-1,1,1,1,-1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j);
				}
			}
			
			ArrayList<int[]> zeros = new ArrayList<>(); // 0인 칸들 위치 저장
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == '*') continue;
					
					int count = 0; // 주변 8방향에 있는 지뢰 개수
					for (int d = 0; d < 8; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1) continue;
						
						if (map[nr][nc] == '*') count++;
					}
					map[r][c] = (char) (count + '0');
					if (map[r][c] == '0') zeros.add(new int[] {r, c});
				}
			}
			
			int count = 0;
			for (int i = 0; i < zeros.size(); i++) {
				int[] cur = zeros.get(i);
				int r = cur[0];
				int c = cur[1];
				
				if (!visited[r][c]) {
					bfs(r, c);
					count++;
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != '*' && !visited[i][j] && map[i][j] != '0') {
						count++;
					}
				}
			}
			
			sb.append("#").append(t).append(" ");
			sb.append(count).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void bfs(int start_r, int start_c) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		visited[start_r][start_c] = true;
		dq.add(new int[] {start_r, start_c});
		
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int r = cur[0];
			int c = cur[1];

			for (int d = 0; d < 8; d++) { // 8방 탐색
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1 || visited[nr][nc]) continue;
				
				if (map[r][c] == '0') {
					visited[nr][nc] = true;
					dq.add(new int[] {nr, nc});
				}
				
			}
			
		}
	}
}
