import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken()); // 세로
		int C = Integer.parseInt(st.nextToken()); // 가로
		
		char[][] map = new char[R][C];
		for (int r = 0; r < R; r++) {
			String line = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c);
			}
		}
		
		boolean[][][] visited = new boolean[R][C][2]; // [0]: 벽 안 부숨, [1]: 벽 부숨
		
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		visited[0][0][0] = true; // 출발점: (0,0), 벽 안 부순 상태: 0, 경로: 1 (자기자신 포함)
		dq.add(new int[] {0,0,0,1}); // r, c, state, cnt
		
		int ans = -1;
		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			int r = cur[0];
			int c = cur[1];
			int state = cur[2];
			int cnt = cur[3];
			
			if (r == R-1 && c == C-1) { // 도착하면
				ans = cnt;
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				int nextState = state;
				
				if (nr < 0 || nc < 0 || nr > R-1 || nc > C-1) continue;
				
				if (map[nr][nc] == '1' && nextState == 0) { // 다음 위치가 벽이고 벽을 안 부쉈으면
					nextState = 1; // 벽을 부순 상태로 바꾸고
					if (!visited[nr][nc][nextState]) {
						visited[nr][nc][nextState] = true;
						dq.add(new int[] {nr, nc, nextState, cnt+1});
					}
				} else if (map[nr][nc] == '0') {
					if (!visited[nr][nc][state]) {
						visited[nr][nc][state] = true;
						dq.add(new int[] {nr, nc, state, cnt+1});
					}
				}
			}
		}
		
		System.out.println(ans);
		
		
	}
	
}
