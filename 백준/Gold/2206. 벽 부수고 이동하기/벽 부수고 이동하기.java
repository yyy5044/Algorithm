import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map;
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	static int ans;
	
	// 주의: 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
			// System.out.println(Arrays.toString(map[i]));
		}
		
		int[] start = {0, 0};
		int[] end = {N-1, M-1};
		
		ans = -1;
		bfs(start, end);
		
		System.out.println(ans);
	}
	
	public static void bfs(int[] start, int[] end) {
		boolean[][][] visited = new boolean[N][M][2];
		
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		visited[start[0]][start[1]][0] = true;
		dq.add(new int[] {start[0], start[1], 0, 1});
		
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int r = cur[0], c = cur[1], used = cur[2], cnt = cur[3];
			
			if (r == end[0] && c == end[1]) {
				ans = cnt;
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				int nextUsed = used;
				
				if (nr < 0 || nc < 0 || nr > N-1 || nc > M-1) continue;
				
				if (map[nr][nc] == 0) {
					if (!visited[nr][nc][used]) {
						visited[nr][nc][used] = true;
						dq.add(new int[] {nr, nc, used, cnt+1});
					}
				} else if (map[nr][nc] == 1 && used == 0) {
					nextUsed = 1;
					if (!visited[nr][nc][nextUsed]) {
						visited[nr][nc][nextUsed] = true;
						dq.add(new int[] {nr, nc, nextUsed, cnt+1});
					}	
				}
			}
		}
	}

}
