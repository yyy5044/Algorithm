import java.io.*;
import java.util.*;

public class Solution {
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	static int N, M;
	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 행
			M = Integer.parseInt(st.nextToken()); // 열
			
			map = new char[N][M];
			int[] D = new int[] {-1,-1}; // 여신
			int[] S = new int[] {-1,-1}; // 수연
			ArrayList<int[]> devils = new ArrayList<>(); // 악마들
			for (int r = 0; r < N; r++) {
				String line = br.readLine();
				for (int c = 0; c < M; c++) {
					map[r][c] = line.charAt(c);
					if (map[r][c] == 'D') D = new int[]{r,c};
					else if (map[r][c] == 'S') S = new int[] {r,c};
					else if (map[r][c] == '*') devils.add(new int[] {r,c});
				}
			}
			
			ArrayDeque<int[]> dq = new ArrayDeque<>();
			
			for (int i = 0; i < devils.size(); i++) {
				int[] devil = devils.get(i);
				dq.add(new int[] {devil[0], devil[1], 1}); // 악마는 1로 구분, 악마 먼저
			}
			dq.add(new int[] {S[0], S[1], 0}); // 수연은 0으로 구분, 수연이 나중
			
			boolean available = false;
			int time = 0;
			while(!dq.isEmpty()) {
				int size = dq.size();
				for (int qs = 0; qs < size; qs++) {
					int[] cur = dq.poll();
					int r = cur[0], c = cur[1], type = cur[2];
					
					if (type == 1) {
						for (int d = 0; d < 4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							
							if (nr < 0 || nc < 0 || nr > N-1 || nc > M-1) continue;
							
							if (map[nr][nc] == '.' || map[nr][nc] == 'S') {
								map[nr][nc] = '*';
								dq.add(new int[] {nr, nc, 1});
							} 
						}
					} else { // 수연이면
						if (r == D[0] && c == D[1]) { // 도착했는지 확인
							available = true;
							break;
						}
						
						for (int d = 0; d < 4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							
							if (nr < 0 || nc < 0 || nr > N-1 || nc > M-1) continue;
							
							if (map[nr][nc] == '.' || map[nr][nc] == 'D') {
								map[nr][nc] = 'S';
								dq.add(new int[] {nr, nc, 0});
							}
						}
					}
				}
				time++;
				if(available) break;
			}
			
			sb.append("#").append(t).append(" ");
			
			if (available) {
				sb.append(time-1);
			} else {
				sb.append("GAME OVER");
			}
			
			sb.append("\n");
		}

		System.out.println(sb);

	}

}