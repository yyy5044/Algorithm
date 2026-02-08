import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < 10; t++) {
			int N = Integer.parseInt(br.readLine());
			
			int[][] map = new int[16][16];
			
			for (int i = 0; i < 16; i++) {
				String line = br.readLine();
				
				for (int j = 0; j < 16; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}
			
			
			// 시계 방향
			int[] dr = {-1, 0, 1, 0};
			int[] dc = {0, 1, 0, -1};
			
			Queue<int[]> q = new LinkedList<>();
			boolean foundPath = false;
			
			int r = 1, c = 1; // 시작점 1, 1
			q.add(new int[]{r, c}); // 시작점 큐에 넣기
			
			for (;;) {
				int[] pos = q.poll(); // 꺼내보기
				r = pos[0];
				c = pos[1];
				map[r][c] = -1; // 꺼낸 좌표는 마킹 (4방향 탐색 전에 해서 여기로는 못 가게)
				// 4방향 탐색
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if (map[nr][nc] == 0) {
						q.add(new int[] {nr, nc});
					} else if (map[nr][nc] == 3) {
						foundPath = true;
						break;
					}
				}
				
				if (foundPath) break; // 찾았냐?
				if (q.isEmpty() && !foundPath) break;
			}
			
			sb.append("#"+N+" ");
			if (foundPath) {
				sb.append("1");
			} else {
				sb.append("0");
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	public static boolean isIn(int n, int r, int c) {
		return (r >= 0 && r < n && c >=0 && c < n);
	}

}
