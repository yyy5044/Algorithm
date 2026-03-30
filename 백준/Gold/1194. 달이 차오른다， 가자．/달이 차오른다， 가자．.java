import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
	static char[][] map;
	static int N, M;
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		// 1. 입력부
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		int[] start = new int[] {-1,-1};
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == '0') start = new int[] {i, j};
			}
			// System.out.println(Arrays.toString(map[i]));
		}
		
		// System.out.println(Arrays.toString(start));
		
		// 2. 자료 구조, 준비 단계
		boolean[][][] visited = new boolean[N][M][1<<6];
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		visited[start[0]][start[1]][0] = true;
		dq.add(new int[] {start[0], start[1], 0, 0});
		
		// 3. 시뮬레이션
		int ans = -1;
		
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int r = cur[0], c = cur[1], state = cur[2], cnt = cur[3];
			
			if (map[r][c] == '1') {
				ans = cnt;
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				int nextState = state;
				
				if (nr < 0 || nc < 0 || nr > N-1 || nc > M-1 || map[nr][nc] == '#') continue;
				
				if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F') {
					if ((nextState&(1<<(map[nr][nc] - 'A'))) == 0) continue;
				} else if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') {
					nextState |= (1<<(map[nr][nc] - 'a'));
				}
				
				if (!visited[nr][nc][nextState]) {
					visited[nr][nc][nextState] = true;
					dq.add(new int[] {nr, nc, nextState, cnt+1});
				}
			}
		}
		
		System.out.println(ans);
	}

}
