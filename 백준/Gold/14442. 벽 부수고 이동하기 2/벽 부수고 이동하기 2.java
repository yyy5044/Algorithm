import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static char[][] map;
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		K = Integer.parseInt(st.nextToken()); // 벽 부수기 횟수
		
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
			// System.out.println(Arrays.toString(map[i]));
		}
		
		// start: {0,0} end: {N-1, M-1}
		boolean[][][] visited = new boolean[N][M][K+1];
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		visited[0][0][0] = true;
		dq.add(new int[] {0, 0, 0, 1}); // r, c, k, cnt
		
		int ans = -1;
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int r = cur[0], c = cur[1], k = cur[2], cnt = cur[3];
			
			if (r == N-1 && c == M-1) {
				ans = cnt;
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr < 0 || nc < 0 || nr >  N-1 || nc > M-1) continue;
				
				if (map[nr][nc] == '0' && !visited[nr][nc][k]) {
					visited[nr][nc][k] = true;
					dq.add(new int[] {nr, nc, k, cnt+1});
				} else if (map[nr][nc] == '1') {
					if (k < K && !visited[nr][nc][k+1]) {
						visited[nr][nc][k+1] = true;
						dq.add(new int[] {nr, nc, k+1, cnt+1});
					}
				}
				
			}
		}
		
		System.out.println(ans);
	}

}
