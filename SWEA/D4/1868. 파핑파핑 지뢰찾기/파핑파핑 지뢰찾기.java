import java.io.*;
import java.util.*;

public class Solution {
	static int[] dr = {-1,-1,-1,0,1,1,1,0}, dc = {-1,0,1,1,1,0,-1,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 1. 입력부
			int N = Integer.parseInt(br.readLine());
			char[][] map = new char[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j);
				}
			}
			
			// 2. 전처리
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] != '*') {
						int cnt = 0;
						for (int d = 0; d < 8; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							
							if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1) continue;
							
							if (map[nr][nc] == '*') cnt++;
						}
						map[r][c] = (char)(cnt + '0');
					}
				}
			}
			
			int clickCnt = 0;
			
			boolean[][] visited = new boolean[N][N];
			
			// 1단계: 주변에 지뢰가 없는 '0'인 칸들을 먼저 찾아서 BFS (연쇄 반응)
			for (int r = 0; r < N; r++) {
			    for (int c = 0; c < N; c++) {
			        if (map[r][c] == '0' && !visited[r][c]) {
			            bfs(map, visited, N, new int[]{r, c});
			            clickCnt++;
			        }
			    }
			}

			// 2단계: 1단계를 거쳤음에도 아직 방문하지 않은(안 열린) 지뢰 아닌 칸들 처리
			for (int r = 0; r < N; r++) {
			    for (int c = 0; c < N; c++) {
			        if (map[r][c] != '*' && !visited[r][c]) {
			            clickCnt++;
			        }
			    }
			}
			
			sb.append("#").append(t).append(" ");
			sb.append(clickCnt).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void bfs(char[][] map, boolean[][] visited, int N, int[] start) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		visited[start[0]][start[1]] = true;
		dq.add(new int[] {start[0], start[1]});
		
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int r = cur[0], c = cur[1];
			
			for (int d = 0; d < 8; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1 || visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				if (map[nr][nc] == '0') {
					dq.add(new int[] {nr, nc});
				}
			}
		}
	}
}
