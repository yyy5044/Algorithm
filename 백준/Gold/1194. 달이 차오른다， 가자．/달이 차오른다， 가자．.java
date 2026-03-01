import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// INPUT START ---
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 세로
		int M = Integer.parseInt(st.nextToken()); // 가로
		char[][] map = new char[N][M];
		int[] start = new int[2]; // 시작 위치
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == '0') {
					start[0] = i; start[1] = j;
				}
			}
		}
		// --- INPUT END
		
		int numOfKeys = 6; // 키 종류는 6개
		boolean[][][] visited = new boolean[N][M][1<<numOfKeys];
		
		// [0], [1]: 좌표 / [2]: 상태 / [3]: 경로 길이
		ArrayDeque<int[]> dq = new ArrayDeque<>(); 
		visited[start[0]][start[1]][0] = true;
		dq.add(new int[] {start[0], start[1], 0, 0}); // 출발!
		
		int ans = -1;
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			
			int r = cur[0];
			int c = cur[1];
			int state = cur[2];
			int cnt = cur[3];
			
			if (map[r][c] == '1') { // 탈출했으면
				ans = cnt; // 경로 주고
				break; // 조기 종료
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				int nextState = state;
				
				if (nr < 0 || nc < 0 || nr > N-1 || nc > M-1 
						|| map[nr][nc] == '#') continue; // 벽이면 못 지나간다
				
				if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F') { // 다음 위치가 문이라면
					if (((1<<(map[nr][nc] - 'A'))&state) == 0) { // 열쇠 없으면 모찌나간다
						continue;
					}
				}
				
				if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') { // 다음 위치가 열쇠라면
					nextState |= (1<<(map[nr][nc] - 'a')); // 상태 변경
				}
				
				if (!visited[nr][nc][nextState]) {
					visited[nr][nc][nextState] = true;
					dq.add(new int[] {nr, nc, nextState, cnt+1}); // 경로 더해서 큐에 삽입
				} 
				
			}
		}
		
		System.out.println(ans);
	}

}