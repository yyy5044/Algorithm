import java.io.*;
import java.util.*;

public class Solution {
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			if (n==1) {
				sb.append("#").append(t).append(" ").append("\n").append(1).append("\n");
				continue;
			} 
			int[][] map = new int[n][n];
			
			int x = 0;
			int y = 0;
			int dir = 0;
			
			map[x][y] = 1;
			int count = 2;
			
			for (;;) {
				int nx = x + dx[dir%4];
				int ny = y + dy[dir%4];
				
				if (nx < 0 || ny < 0 || nx > n-1 || ny > n-1 || map[ny][nx] != 0) {
					dir++;
					continue;
				}
				
				map[ny][nx] = count;
				count++;
				x = nx;
				y = ny;
				if(count>n*n) break;
			}
			sb.append("#").append(t).append(" ").append("\n");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					sb.append(map[i][j]).append(" ");
				}
				sb.append("\n");
			}
			
		}

		System.out.println(sb);

	}
	
}
