import java.io.*;
import java.util.*;

public class Main {
	static int N, M; // rows, columns
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행 개수 rows
		M = Integer.parseInt(st.nextToken()); // 열 개수 columns
		int[][] map = new int[N][M]; // 맵
		
		ArrayList<int[]> walls = new ArrayList<int[]>(); // 벽 위치 저장 리스트
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
				if (map[i][j] == 1) {
					walls.add(new int[] {i, j});
				}
			}
		}
		
		int ans = bfs(map, new int[]{0,0});
		
		System.out.println(ans);
	}
	
	private static int bfs(int[][] map, int[] pos) {
		
		// [0]: 벽 안 부숨, [1]: 벽 부숨
		boolean[][][] visited = new boolean[N][M][2];
		
		ArrayDeque<int[]> dq = new ArrayDeque<int[]>();
		visited[pos[0]][pos[1]][0] = true;
		dq.add(new int[] {pos[0], pos[1], 1, 0});
		
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int r = cur[0];
			int c = cur[1];
			int cnt = cur[2];
			int state = cur[3];
			
			if (r == (N-1) && c == (M-1)) { // 도착하면
				return cnt; // 경로 반환
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr < 0 || nc < 0 || nr > N-1 || nc > M-1) continue;
				
				if (map[nr][nc] == 0) {
					if (!visited[nr][nc][state]) {
						visited[nr][nc][state] = true;
						dq.add(new int[] {nr, nc, cnt+1, state});	
					}
				} else if (map[nr][nc] == 1) {
					if (state == 0) {
						visited[nr][nc][1] = true;
						dq.add(new int[] {nr, nc, cnt+1, 1});
					}
				}
			}
		}
		
		return -1; // 도착 못 하면 -1 반환
	}

}