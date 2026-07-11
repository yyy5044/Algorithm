import java.io.*;
import java.util.*;

public class Solution {
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			int[][] map = new int[n][n];
			int r = 0;
			int c = 0;
			int dir = 0;
			
			for (int i = 1; i <= n*n; i++) {
				map[c][r] = i;
				
				int nr = r+dr[dir];
				int nc = c+dc[dir];
				
				if (nr < 0 || nc < 0 || nr > n-1 || nc > n-1 || map[nc][nr] != 0) {
					dir = (dir+1) % 4;
					nr = r+dr[dir];
					nc = c+dc[dir];
				}
				
				r = nr;
				c = nc;
			}
			sb.append("#").append(t).append("\n");
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
