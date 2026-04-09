import java.io.*;
import java.util.*;

public class Solution {
	static int N, M;
	static char[][] map;
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new char[N][M];
			
			int[] goddess = {-1, -1}; // 여신 위치
			int[] suyeon = {-1, -1}; // 수연 위치
			ArrayList<int[]> demons = new ArrayList<>(); // 악마들 위치
			
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = line.charAt(j);
					if (map[i][j] == 'D') goddess = new int[] {i, j};
					else if (map[i][j] == 'S') suyeon = new int[] {i, j};
					else if (map[i][j] == '*') demons.add(new int[] {i, j});
				}
				// System.out.println(Arrays.toString(map[i]));
			}
			
			boolean[][][] visited = new boolean[N][M][2]; // 0: 수연, 1: 악마
			ArrayDeque<int[]> dq = new ArrayDeque<>();
			for (int i = 0; i < demons.size(); i++) {
				int[] cur = demons.get(i);
				visited[cur[0]][cur[1]][1] = true;
				dq.add(new int[] {cur[0], cur[1], 1, 0}); // {r, c, state, cnt}
			}
			
			visited[suyeon[0]][suyeon[1]][0] = true;
			dq.add(new int[] {suyeon[0], suyeon[1], 0, 0});
			
			int result = -1;
			while(!dq.isEmpty()) {
				int[] cur = dq.poll();
				int r = cur[0], c = cur[1], state = cur[2], cnt = cur[3];
				
				if (r == goddess[0] && c == goddess[1]) {
					result = cnt;
					break;
				}
				
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if (nr < 0 || nc < 0 || nr > N-1 || nc > M-1 || map[nr][nc] == 'X' || visited[nr][nc][state]) continue;
					
					if (state == 0 && map[nr][nc] != '*') { // 수연
						map[nr][nc] = 'S';
						visited[nr][nc][state] = true;
						dq.add(new int[] {nr, nc, state, cnt+1});
					} else if (state == 1 && map[nr][nc] != 'D') { // 악마
						map[nr][nc] = '*';
						visited[nr][nc][state] = true;
						dq.add(new int[] {nr, nc, state, cnt+1});
					}
				}
			}
			
			String ans;
			if (result < 0) {
				ans = "GAME OVER";
			} else {
				ans = String.valueOf(result);
			}
			
			sb.append("#").append(t).append(" ");
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	
}
