import java.io.*;
import java.util.*;

public class Solution {
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {

			// 1. 입력부
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			char[][] map = new char[N][M];
			int[] suyeon = {-1,-1};
			int[] god = {-1,-1};
			ArrayList<int[]> demons = new ArrayList<>();
			
			for (int r = 0; r < N; r++) {
				String line = br.readLine();
				for (int c = 0; c < M; c++) {
					map[r][c] = line.charAt(c);
					if (map[r][c] == 'S') suyeon = new int[] {r, c};
					else if (map[r][c] == '*') demons.add(new int[] {r, c});
					else if (map[r][c] == 'D') god = new int[] {r, c};
				}
			}
			
			// 2. 자료구조, 준비 단계
			boolean[][][] visited = new boolean[N][M][2]; // 0: suyeon, 1: demon
			ArrayDeque<int[]> dq = new ArrayDeque<>();
			for (int i = 0; i < demons.size(); i++) {
				int[] temp = demons.get(i);
				visited[temp[0]][temp[1]][1] = true;
				dq.add(new int[] {temp[0], temp[1], 1, 0});
			}
			
			visited[suyeon[0]][suyeon[1]][0] = true;
			dq.add(new int[] {suyeon[0], suyeon[1], 0, 0}); // r, c, state, cnt
			
			int minCnt = -1;
			
			// 3. 시뮬레이션
			while(!dq.isEmpty()) {
				int[] cur = dq.poll();
				int r = cur[0], c = cur[1], state = cur[2], cnt = cur[3];
				
				if (state == 0 && r == god[0] && c == god[1]) {
					minCnt = cnt;
					break;
				}
				
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if (nr < 0 || nc < 0 || nr > N-1 || nc > M-1 || map[nr][nc] == 'X' || visited[nr][nc][state]) continue;
					
					if (state == 0) {
						if (map[nr][nc] == '*') continue;
						map[nr][nc] = 'S';
					} 
					else if (state == 1) {
						if (map[nr][nc] == 'D') continue;
						map[nr][nc] = '*';
					}
					visited[nr][nc][state] = true;
					dq.add(new int[] {nr, nc, state, cnt+1});
				}
			}
			
			String ans;
			if (minCnt == -1) ans = "GAME OVER";
			else ans = String.valueOf(minCnt);
			
			sb.append("#").append(t).append(" ");
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
}
