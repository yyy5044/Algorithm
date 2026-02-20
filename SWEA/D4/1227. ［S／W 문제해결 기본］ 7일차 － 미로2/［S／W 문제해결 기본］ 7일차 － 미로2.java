

import java.io.*;
import java.util.*;

public class Solution {
	static int N = 100;
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < 10; t++) {
			int T = Integer.parseInt(br.readLine());
			boolean isReachable = false;
			
			int[][] maze = new int[N][N];
			boolean[][] visited = new boolean[N][N];
			int[] start = new int[2];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					maze[i][j] = line.charAt(j) - '0';
					if (maze[i][j] == 2) start = new int[] {i,j};
				}
			}
			
			ArrayDeque<int[]> dq = new ArrayDeque<>();
			visited[start[0]][start[1]] = true;
			dq.addLast(start);
			
			while(!dq.isEmpty()) {
				int[] pos = dq.pollFirst();
				int r = pos[0];
				int c = pos[1];
				
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1 || visited[nr][nc]) continue;
					
					if (maze[nr][nc] == 0) {
						visited[nr][nc] = true;
						dq.addLast(new int[] {nr, nc});
					} else if (maze[nr][nc] == 3) {
						isReachable = true;
						break;
					}
				}
			}
			
			sb.append("#").append(T).append(" ");
			
			if (isReachable) {
				sb.append(1).append("\n");
			} else {
				sb.append(0).append("\n");
			}
		}	
		
		System.out.println(sb);
	}
	
}
