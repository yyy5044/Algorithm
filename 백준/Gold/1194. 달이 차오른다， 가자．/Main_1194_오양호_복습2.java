import java.io.*;
import java.util.*;

public class Main {
	static int N, M; // N: 세로(r), M: 가로(c)
	static char[][] map;
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		int[] start = new int[] {-1,-1};
		for (int r = 0; r < N; r++) {
			String line = br.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = line.charAt(c);
				if (map[r][c] == '0') { // 시작 위치 저장
					start[0] = r; start[1] = c;
				}
			}
		}
		
		// bfs 탐색
		boolean[][][] visited = new boolean[N][M][1<<6]; // 3차원 방문 배열
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		visited[start[0]][start[1]][0] = true; // 처음에는 아무 열쇠도 먹지 못한 0에서 시작
		dq.add(new int[] {start[0], start[1], 0, 0}); // [0]: r, [1]: c, [2]: state, [3]: cnt
		
		int ans = -1;
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int r = cur[0], c = cur[1], state = cur[2] , cnt = cur[3];
			
			if (map[r][c] == '1') { // 탈출구를 찾았으면
				ans = cnt;
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				int nextState = state;
				
				if (nr < 0 || nc < 0 || nr > N-1 || nc > M-1) continue;
				
				if (map[nr][nc] != '#') { // 다음 위치가 벽이 아니면
					if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') { // 열쇠면
						// 열쇠를 먹은 상태로 바꾸고
						nextState |= (1<<(map[nr][nc] - 'a'));
						// 방문 체크 후 큐 삽입
						if (!visited[nr][nc][nextState]) {
							visited[nr][nc][nextState] = true;
							dq.add(new int[] {nr, nc, nextState, cnt+1});
						}
					} else if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F') { // 문이면
						// 열쇠가 있는상태인지 확인하고
						if ((state &(1<< (map[nr][nc] - 'A'))) != 0) { // 있을 때만 방문 체크 후 큐 삽입
							if (!visited[nr][nc][nextState]) {
								visited[nr][nc][nextState] = true;
								dq.add(new int[] {nr, nc, nextState, cnt+1});
							}
						}
					} else { // 빈 칸 또는 도착지
						if (!visited[nr][nc][nextState]) {
							visited[nr][nc][nextState] = true;
							dq.add(new int[] {nr, nc, nextState, cnt+1});
						}
					}
				}
			}
		}
		
		System.out.println(ans);
		
	}
	
}
